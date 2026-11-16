public class AccionAtacar implements Accion {
    @Override public void ejecutar(MotorCombate m, Personaje actor, Personaje objetivo) { m.resolverAtaque(actor, objetivo); }
    @Override public String getNombre() { return "Atacar"; }
}
