public class CreadorDragon extends CreadorEnemigo {
    @Override
    public Personaje crearEnemigo() {
        Personaje p = new PersonajeBase("Dragón ancestral", 150, 26, 15, 10);
        p.setEstrategia(new AtaqueHechizo());
        return p;
    }
}
