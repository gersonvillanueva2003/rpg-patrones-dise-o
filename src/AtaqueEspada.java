public class AtaqueEspada implements EstrategiaAtaque {
    @Override
    public ResultadoAtaque atacar(Personaje atacante, Personaje objetivo) {
        int dano = Math.max(1, atacante.getAtaque() - objetivo.getDefensa());
        boolean crit = Dado.probabilidad(10);
        if (crit) dano *= 2;
        return new ResultadoAtaque(dano, crit, "espadazo");
    }
}
