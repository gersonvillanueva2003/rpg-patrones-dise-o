public class AccionUsarItem implements Accion {
    @Override public void ejecutar(MotorCombate m, Personaje actor, Personaje objetivo) { m.resolverPocion(actor); }
    @Override public String getNombre() { return "Usar poción"; }
}
