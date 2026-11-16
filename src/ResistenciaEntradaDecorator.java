public class ResistenciaEntradaDecorator extends EntradaDecorator {
    private final int resistido;
    public ResistenciaEntradaDecorator(EntradaBitacora e, int resistido) { super(e); this.resistido = resistido; }
    @Override public String getTexto() { return super.getTexto() + " [RESISTENCIA: la defensa absorbió " + resistido + "]"; }
}
