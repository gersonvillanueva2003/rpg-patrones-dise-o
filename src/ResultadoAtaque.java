public class ResultadoAtaque {
    private final int dano;
    private final boolean critico;
    private final String efecto;
    private final int danoPorTurno;   // DOT (0 si no hay)
    private final int turnosDot;

    public ResultadoAtaque(int dano, boolean critico, String efecto) {
        this(dano, critico, efecto, 0, 0);
    }

    public ResultadoAtaque(int dano, boolean critico, String efecto, int danoPorTurno, int turnosDot) {
        this.dano = dano;
        this.critico = critico;
        this.efecto = efecto;
        this.danoPorTurno = danoPorTurno;
        this.turnosDot = turnosDot;
    }

    public int getDano() { return dano; }
    public boolean isCritico() { return critico; }
    public String getEfecto() { return efecto; }
    public int getDanoPorTurno() { return danoPorTurno; }
    public int getTurnosDot() { return turnosDot; }
}
