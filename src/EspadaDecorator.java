public class EspadaDecorator extends PersonajeDecorator {
    public EspadaDecorator(Personaje p) { super(p); }
    @Override public int getAtaque() { return super.getAtaque() + 10; }
    @Override public String getDescripcion() { return super.getDescripcion() + " + Espada"; }
}
