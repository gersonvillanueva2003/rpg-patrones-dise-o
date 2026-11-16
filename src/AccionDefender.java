public class AccionDefender implements Accion {
    @Override public void ejecutar(MotorCombate m, Personaje actor, Personaje objetivo) { m.resolverDefensa(actor); }
    @Override public String getNombre() { return "Defender"; }
}
