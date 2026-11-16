public class VenenoDecorator extends PersonajeDecorator {
    public static final int DANO_DOT = 3;
    public static final int TURNOS_DOT = 2;

    public VenenoDecorator(Personaje p) { super(p); }

    @Override public int getAtaque() { return super.getAtaque() + 5; }

    @Override
    public ResultadoAtaque atacarComo(Personaje atacanteFinal, Personaje objetivo) {
        ResultadoAtaque r = super.atacarComo(atacanteFinal, objetivo);
        return new ResultadoAtaque(r.getDano(), r.isCritico(), r.getEfecto() + " + veneno",
                Math.max(r.getDanoPorTurno(), DANO_DOT), Math.max(r.getTurnosDot(), TURNOS_DOT));
    }

    @Override public String getDescripcion() { return super.getDescripcion() + " + Veneno"; }
}
