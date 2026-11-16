public class CreadorGoblin extends CreadorEnemigo {
    @Override
    public Personaje crearEnemigo() {
        Personaje p = new PersonajeBase("Goblin salvaje", 60, 13, 5, 12);
        p.setEstrategia(new AtaquePunalada());
        return p;
    }
}
