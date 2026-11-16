public class CriticoEntradaDecorator extends EntradaDecorator {
    public CriticoEntradaDecorator(EntradaBitacora e) { super(e); }
    @Override public String getTexto() { return super.getTexto() + " [¡CRÍTICO!]"; }
}
