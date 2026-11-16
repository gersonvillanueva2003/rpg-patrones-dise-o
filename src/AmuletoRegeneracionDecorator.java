public class AmuletoRegeneracionDecorator extends PersonajeDecorator {
    public static final int CURACION = 2;

    public AmuletoRegeneracionDecorator(Personaje p) { super(p); }

    @Override public int getDefensa() { return super.getDefensa() + 3; }

    @Override
    public String alFinalizarTurno() {
        String previo = super.alFinalizarTurno();
        int antes = getHp();
        if (antes <= 0) return previo;
        setHp(antes + CURACION);
        if (getHp() == antes) return previo; // ya estaba a tope: nada que mostrar
        String msg = "Amuleto regenera " + (getHp() - antes) + " HP (" + antes + " -> " + getHp() + ")";
        return previo == null ? msg : previo + " | " + msg;
    }

    @Override public String getDescripcion() { return super.getDescripcion() + " + Amuleto"; }
}
