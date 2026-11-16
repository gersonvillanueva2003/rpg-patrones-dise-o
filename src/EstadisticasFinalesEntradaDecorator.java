public class EstadisticasFinalesEntradaDecorator extends EntradaDecorator {
    private final String estadisticas;
    public EstadisticasFinalesEntradaDecorator(EntradaBitacora e, String estadisticas) { super(e); this.estadisticas = estadisticas; }
    @Override public String getTexto() { return super.getTexto() + "\n    Estadísticas finales: " + estadisticas; }
}
