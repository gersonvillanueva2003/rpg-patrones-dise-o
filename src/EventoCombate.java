public class EventoCombate {
    public enum Tipo { INICIO, TURNO, ATAQUE, DEFENSA, ITEM, EFECTO, VENENO, REGENERACION, ESTADO, FIN }

    private final Tipo tipo;
    private final int turno;
    private final Personaje actor;
    private final Personaje objetivo;
    private final String mensaje;
    private ResultadoAtaque resultado;
    private int danoAplicado;
    private int danoResistido;
    private int hpAntes;
    private int hpDespues;
    private MotorCombate motor;

    public EventoCombate(Tipo tipo, int turno, Personaje actor, Personaje objetivo, String mensaje) {
        this.tipo = tipo; this.turno = turno; this.actor = actor; this.objetivo = objetivo; this.mensaje = mensaje;
    }

    public EventoCombate conResultado(ResultadoAtaque r, int aplicado, int resistido) {
        this.resultado = r; this.danoAplicado = aplicado; this.danoResistido = resistido; return this;
    }
    public EventoCombate conHp(int antes, int despues) { this.hpAntes = antes; this.hpDespues = despues; return this; }
    public EventoCombate conMotor(MotorCombate m) { this.motor = m; return this; }

    public Tipo getTipo() { return tipo; }
    public int getTurno() { return turno; }
    public Personaje getActor() { return actor; }
    public Personaje getObjetivo() { return objetivo; }
    public String getMensaje() { return mensaje; }
    public ResultadoAtaque getResultado() { return resultado; }
    public int getDanoAplicado() { return danoAplicado; }
    public int getDanoResistido() { return danoResistido; }
    public int getHpAntes() { return hpAntes; }
    public int getHpDespues() { return hpDespues; }
    public MotorCombate getMotor() { return motor; }
}
