public interface Accion {
    void ejecutar(MotorCombate motor, Personaje actor, Personaje objetivo);
    String getNombre();
}
