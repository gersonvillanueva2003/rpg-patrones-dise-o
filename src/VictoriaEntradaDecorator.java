public class VictoriaEntradaDecorator extends EntradaDecorator {
    public VictoriaEntradaDecorator(EntradaBitacora e) { super(e); }
    @Override public String getTexto() { return super.getTexto() + " [VICTORIA]"; }
}
