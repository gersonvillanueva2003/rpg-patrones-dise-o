public class PersonajeBase implements Personaje {
    private final String nombre;
    private int hp;
    private final int hpMax;
    private final int ataque;
    private final int defensa;
    private final int velocidad;
    private EstrategiaAtaque estrategia;

    public PersonajeBase(String nombre, int hp, int ataque, int defensa, int velocidad) {
        this.nombre = nombre;
        this.hp = hp;
        this.hpMax = hp;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
    }

    @Override public String getNombre() { return nombre; }
    @Override public int getAtaque() { return ataque; }
    @Override public int getDefensa() { return defensa; }
    @Override public int getVelocidad() { return velocidad; }
    @Override public int getHp() { return hp; }
    @Override public void setHp(int hp) { this.hp = Math.max(0, Math.min(hp, hpMax)); }
    @Override public int getHpMax() { return hpMax; }
    @Override public EstrategiaAtaque getEstrategia() { return estrategia; }
    @Override public void setEstrategia(EstrategiaAtaque e) { this.estrategia = e; }

    @Override
    public ResultadoAtaque atacar(Personaje objetivo) { return atacarComo(this, objetivo); }

    @Override
    public ResultadoAtaque atacarComo(Personaje atacanteFinal, Personaje objetivo) {
        // Strategy: se delega la forma de atacar; el atacante es el personaje decorado completo.
        return estrategia.atacar(atacanteFinal, objetivo);
    }

    @Override public String getDescripcion() { return nombre + " (base)"; }
}
