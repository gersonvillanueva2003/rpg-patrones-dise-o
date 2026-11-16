public class AtaquePunalada implements EstrategiaAtaque {
    @Override
    public ResultadoAtaque atacar(Personaje atacante, Personaje objetivo) {
        int dano = Math.max(1, atacante.getAtaque() - objetivo.getDefensa());
        boolean crit = Dado.probabilidad(25);
        if (crit) dano *= 2;
        return new ResultadoAtaque(dano, crit, "puñalada");
    }
}
