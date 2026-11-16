public class EntradaBase implements EntradaBitacora {
    private final String texto;
    public EntradaBase(String texto) { this.texto = texto; }
    @Override public String getTexto() { return texto; }
}
