public class CreadorOrco extends CreadorEnemigo {
    @Override
    public Personaje crearEnemigo() {
        Personaje p = new PersonajeBase("Orco brutal", 90, 18, 9, 8);
        p.setEstrategia(new AtaqueEspada());
        return p;
    }
}
