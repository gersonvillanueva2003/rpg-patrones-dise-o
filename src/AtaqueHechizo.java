public class AtaqueHechizo implements EstrategiaAtaque {
    @Override
    public ResultadoAtaque atacar(Personaje atacante, Personaje objetivo) {
        // La magia ignora la defensa del objetivo.
        int dano = atacante.getAtaque();
        boolean crit = Dado.probabilidad(5);
        if (crit) dano = (int) Math.round(dano * 1.5);
        return new ResultadoAtaque(dano, crit, "hechizo");
    }
}
