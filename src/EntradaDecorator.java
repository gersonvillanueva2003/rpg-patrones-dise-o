public abstract class EntradaDecorator implements EntradaBitacora {
    protected final EntradaBitacora entrada;
    public EntradaDecorator(EntradaBitacora entrada) { this.entrada = entrada; }
    @Override public String getTexto() { return entrada.getTexto(); }
}
