import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MotorCombate {
    public static final int MAX_TURNOS = 100;
    public static final int POCIONES_HEROE = 2;
    public static final int CURA_POCION = 30;

    private final List<ObservadorCombate> observadores = new ArrayList<>();
    private final Map<Personaje, int[]> venenos = new IdentityHashMap<>();   // {dano, turnosRestantes}
    private final Set<Personaje> defendiendo = Collections.newSetFromMap(new IdentityHashMap<>());
    private final Map<Personaje, Integer> pociones = new IdentityHashMap<>();
    private int turno;

    public void agregarObservador(ObservadorCombate o) { observadores.add(o); }

    private void notificar(EventoCombate e) { for (ObservadorCombate o : observadores) o.actualizar(e); }

    public void iniciarCombate(Personaje heroe, Personaje enemigo) {
        iniciarCombate(heroe, enemigo, new ControladorAutomatico());
    }

    public void iniciarCombate(Personaje heroe, Personaje enemigo, ControladorTurno controlHeroe) {
        ControladorTurno controlEnemigo = new ControladorAutomatico();
        pociones.put(heroe, POCIONES_HEROE);
        pociones.put(enemigo, 0);
        notificar(new EventoCombate(EventoCombate.Tipo.INICIO, 0, heroe, enemigo, "Inicio del combate"));

        for (turno = 1; turno <= MAX_TURNOS && heroe.getHp() > 0 && enemigo.getHp() > 0; turno++) {
            notificar(new EventoCombate(EventoCombate.Tipo.TURNO, turno, heroe, enemigo, ""));
            ejecutarTurno(heroe, enemigo, controlHeroe);
            if (heroe.getHp() > 0 && enemigo.getHp() > 0) ejecutarTurno(enemigo, heroe, controlEnemigo);
            notificar(new EventoCombate(EventoCombate.Tipo.ESTADO, turno, heroe, enemigo, "").conMotor(this));
        }

        int jugados = turno - 1;
        Personaje ganador = null, perdedor = null;
        String msg;
        if (heroe.getHp() > 0 && enemigo.getHp() <= 0) {
            ganador = heroe; perdedor = enemigo;
            msg = "Ganador: " + heroe.getDescripcion() + " (HP restante: " + heroe.getHp() + ")";
        } else if (enemigo.getHp() > 0 && heroe.getHp() <= 0) {
            ganador = enemigo; perdedor = heroe;
            msg = "Ganador: " + enemigo.getNombre() + " (HP restante: " + enemigo.getHp() + ")";
        } else {
            msg = "Empate: se alcanzó el límite de " + MAX_TURNOS + " turnos.";
        }
        notificar(new EventoCombate(EventoCombate.Tipo.FIN, jugados, ganador, perdedor, msg));
    }

    private void ejecutarTurno(Personaje actor, Personaje objetivo, ControladorTurno control) {
        defendiendo.remove(actor);          // la postura defensiva dura hasta el siguiente turno propio
        aplicarVeneno(actor);
        if (actor.getHp() <= 0) return;

        Accion accion = control.elegir(actor, objetivo, this);   // Command
        accion.ejecutar(this, actor, objetivo);

        String fin = actor.alFinalizarTurno();                  // regeneración del amuleto, etc.
        if (fin != null) notificar(new EventoCombate(EventoCombate.Tipo.REGENERACION, turno, actor, objetivo, fin));
    }

    //Resolución de acciones (invocadas por los Command)

    public void resolverAtaque(Personaje actor, Personaje objetivo) {
        ResultadoAtaque r = actor.atacar(objetivo);            // Strategy + Decorator
        int dano = r.getDano();
        int resistido = 0;
        if (defendiendo.contains(objetivo)) {
            int reducido = Math.max(1, dano / 2);
            resistido = dano - reducido;
            dano = reducido;
        }
        int antes = objetivo.getHp();
        objetivo.setHp(antes - dano);
        String msg = actor.getDescripcion() + " usa " + actor.getEstrategia().getClass().getSimpleName()
                + " sobre " + objetivo.getNombre();
        notificar(new EventoCombate(EventoCombate.Tipo.ATAQUE, turno, actor, objetivo, msg)
                .conResultado(r, dano, resistido).conHp(antes, objetivo.getHp()));

        if (r.getTurnosDot() > 0 && objetivo.getHp() > 0) {
            venenos.put(objetivo, new int[]{r.getDanoPorTurno(), r.getTurnosDot()});
            notificar(new EventoCombate(EventoCombate.Tipo.EFECTO, turno, actor, objetivo,
                    objetivo.getNombre() + " queda envenenado (" + r.getDanoPorTurno() + " de daño x "
                            + r.getTurnosDot() + " turnos)"));
        }
    }

    public void resolverDefensa(Personaje actor) {
        defendiendo.add(actor);
        notificar(new EventoCombate(EventoCombate.Tipo.DEFENSA, turno, actor, null,
                actor.getNombre() + " se defiende: recibirá la mitad del daño hasta su próximo turno"));
    }

    public void resolverPocion(Personaje actor) {
        pociones.merge(actor, -1, Integer::sum);
        int antes = actor.getHp();
        actor.setHp(antes + CURA_POCION);
        notificar(new EventoCombate(EventoCombate.Tipo.ITEM, turno, actor, null,
                actor.getNombre() + " bebe una poción (" + antes + " -> " + actor.getHp()
                        + " HP, quedan " + pocionesRestantes(actor) + ")").conHp(antes, actor.getHp()));
    }

    private void aplicarVeneno(Personaje p) {
        int[] v = venenos.get(p);
        if (v == null) return;
        int antes = p.getHp();
        p.setHp(antes - v[0]);
        if (--v[1] <= 0) venenos.remove(p);
        notificar(new EventoCombate(EventoCombate.Tipo.VENENO, turno, p, null,
                "[Veneno] " + p.getNombre() + " sufre " + (antes - p.getHp()) + " de daño: " + antes + " -> " + p.getHp())
                .conHp(antes, p.getHp()).conResultado(null, antes - p.getHp(), 0));
    }

    //Consultas de estado (para HUD y controladores)
    public int pocionesRestantes(Personaje p) { return pociones.getOrDefault(p, 0); }
    public boolean estaDefendiendo(Personaje p) { return defendiendo.contains(p); }
    public boolean estaEnvenenado(Personaje p) { return venenos.containsKey(p); }
}
