public abstract class PersonajeDecorator implements Personaje {
    protected final Personaje personaje;

    public PersonajeDecorator(Personaje personaje) { this.personaje = personaje; }

    @Override public String getNombre() { return personaje.getNombre(); }
    @Override public int getAtaque() { return personaje.getAtaque(); }
    @Override public int getDefensa() { return personaje.getDefensa(); }
    @Override public int getVelocidad() { return personaje.getVelocidad(); }
    @Override public int getHp() { return personaje.getHp(); }
    @Override public void setHp(int hp) { personaje.setHp(hp); }
    @Override public int getHpMax() { return personaje.getHpMax(); }
    @Override public EstrategiaAtaque getEstrategia() { return personaje.getEstrategia(); }
    @Override public void setEstrategia(EstrategiaAtaque e) { personaje.setEstrategia(e); }
    @Override public ResultadoAtaque atacar(Personaje objetivo) { return atacarComo(this, objetivo); }
    @Override public ResultadoAtaque atacarComo(Personaje f, Personaje objetivo) { return personaje.atacarComo(f, objetivo); }
    @Override public String getDescripcion() { return personaje.getDescripcion(); }

    @Override
    public String alFinalizarTurno() { return personaje.alFinalizarTurno(); }
}
