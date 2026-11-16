public class AtaqueFlecha implements EstrategiaAtaque {
    @Override
    public ResultadoAtaque atacar(Personaje atacante, Personaje objetivo) {
        // La flecha atraviesa la mitad de la defensa.
        int dano = Math.max(1, atacante.getAtaque() - objetivo.getDefensa() / 2);
        boolean crit = Dado.probabilidad(15);
        if (crit) dano *= 2;
        return new ResultadoAtaque(dano, crit, "flechazo");
    }
}
