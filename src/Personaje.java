public interface Personaje {
    String getNombre();
    int getAtaque();
    int getDefensa();
    int getVelocidad();
    int getHp();
    void setHp(int hp);
    int getHpMax();
    EstrategiaAtaque getEstrategia();
    void setEstrategia(EstrategiaAtaque estrategia);
    ResultadoAtaque atacar(Personaje objetivo);
    String getDescripcion();

    ResultadoAtaque atacarComo(Personaje atacanteFinal, Personaje objetivo);

    default String alFinalizarTurno() { return null; }
}
