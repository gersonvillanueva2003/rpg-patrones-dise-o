public class ControladorAutomatico implements ControladorTurno {
    @Override
    public Accion elegir(Personaje actor, Personaje objetivo, MotorCombate motor) {
        boolean herido = actor.getHp() * 100 < actor.getHpMax() * 30;
        if (herido && motor.pocionesRestantes(actor) > 0) return new AccionUsarItem();
        if (herido && Dado.probabilidad(30)) return new AccionDefender();
        return new AccionAtacar();
    }
}
