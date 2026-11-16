public class BendicionDecorator extends PersonajeDecorator {
    public BendicionDecorator(Personaje p) { super(p); }

    private static int mas15(int v) { return (v * 115 + 50) / 100; }

    @Override public int getAtaque() { return mas15(super.getAtaque()); }
    @Override public int getDefensa() { return mas15(super.getDefensa()); }
    @Override public int getVelocidad() { return mas15(super.getVelocidad()); }
    @Override public String getDescripcion() { return super.getDescripcion() + " + Bendición"; }
}
