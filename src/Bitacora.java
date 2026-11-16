import java.util.ArrayList;
import java.util.List;

public class Bitacora implements ObservadorCombate {
    private final List<EntradaBitacora> entradas = new ArrayList<>();
    private Personaje heroe;
    private int danoHeroe, danoEnemigo, criticos, pocionesUsadas;

    @Override
    public void actualizar(EventoCombate e) {
        String t = "T" + e.getTurno() + " ";
        switch (e.getTipo()) {
            case INICIO -> {
                heroe = e.getActor();
                entradas.add(new EntradaBase("Inicia el combate: " + e.getActor().getNombre()
                        + " vs " + e.getObjetivo().getNombre()));
            }
            case ATAQUE -> {
                ResultadoAtaque r = e.getResultado();
                EntradaBitacora en = new EntradaBase(t + e.getActor().getNombre() + " ataca a "
                        + e.getObjetivo().getNombre() + " con " + e.getActor().getEstrategia().getClass().getSimpleName()
                        + ": " + e.getDanoAplicado() + " de daño (" + e.getHpAntes() + " -> " + e.getHpDespues() + ")");
                if (r.isCritico()) { en = new CriticoEntradaDecorator(en); criticos++; }
                if (e.getDanoResistido() > 0) en = new ResistenciaEntradaDecorator(en, e.getDanoResistido());
                entradas.add(en);
                if (e.getActor() == heroe) danoHeroe += e.getDanoAplicado(); else danoEnemigo += e.getDanoAplicado();
            }
            case VENENO -> {
                entradas.add(new EntradaBase(t + e.getMensaje()));
                if (e.getActor() == heroe) danoEnemigo += e.getDanoAplicado(); else danoHeroe += e.getDanoAplicado();
            }
            case ITEM -> {
                entradas.add(new EntradaBase(t + e.getMensaje()));
                if (e.getActor() == heroe) pocionesUsadas++;
            }
            case DEFENSA, EFECTO, REGENERACION -> entradas.add(new EntradaBase(t + e.getMensaje()));
            case FIN -> {
                EntradaBitacora en = new EntradaBase(e.getMensaje());
                if (e.getActor() != null && e.getActor() == heroe) en = new VictoriaEntradaDecorator(en);
                en = new EstadisticasFinalesEntradaDecorator(en, "turnos=" + e.getTurno()
                        + ", daño del héroe=" + danoHeroe + ", daño del enemigo=" + danoEnemigo
                        + ", críticos=" + criticos + ", pociones usadas=" + pocionesUsadas);
                entradas.add(en);
                imprimir();
            }
            default -> { }
        }
    }

    public void imprimir() {
        System.out.println("\nBITÁCORA DE COMBATE");
        for (EntradaBitacora en : entradas) System.out.println("  - " + en.getTexto());
    }
}
