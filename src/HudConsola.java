public class HudConsola implements ObservadorCombate {
    @Override
    public void actualizar(EventoCombate e) {
        switch (e.getTipo()) {
            case INICIO -> System.out.println("=== COMBATE INICIADO ===");
            case TURNO -> System.out.println("\n--- TURNO " + e.getTurno() + " ---");
            case ATAQUE -> {
                ResultadoAtaque r = e.getResultado();
                System.out.println(e.getMensaje());
                System.out.println("  Daño: " + r.getDano() + (r.isCritico() ? " (¡CRÍTICO!)" : "")
                        + " | Efecto: " + r.getEfecto());
                if (e.getDanoResistido() > 0)
                    System.out.println("  [Defensa] " + e.getObjetivo().getNombre() + " resiste " + e.getDanoResistido()
                            + " -> daño aplicado: " + e.getDanoAplicado());
                System.out.println("  " + e.getObjetivo().getNombre() + " HP: " + e.getHpAntes() + " -> " + e.getHpDespues());
            }
            case DEFENSA, ITEM, EFECTO, VENENO, REGENERACION -> System.out.println("  " + e.getMensaje());
            case ESTADO -> {
                MotorCombate m = e.getMotor();
                System.out.println("  [ESTADO] " + linea(e.getActor(), m));
                System.out.println("           " + linea(e.getObjetivo(), m));
            }
            case FIN -> {
                System.out.println("\nCOMBATE FINALIZADO");
                System.out.println(e.getMensaje());
            }
        }
    }

    private String linea(Personaje p, MotorCombate m) {
        int llenos = p.getHpMax() == 0 ? 0 : Math.round(10f * p.getHp() / p.getHpMax());
        StringBuilder sb = new StringBuilder(p.getNombre()).append(" [");
        for (int i = 0; i < 10; i++) sb.append(i < llenos ? '#' : '-');
        sb.append("] ").append(p.getHp()).append('/').append(p.getHpMax());
        if (m.estaDefendiendo(p)) sb.append(" (defendiendo)");
        if (m.estaEnvenenado(p)) sb.append(" (envenenado)");
        if (m.pocionesRestantes(p) > 0) sb.append(" | pociones: ").append(m.pocionesRestantes(p));
        return sb.toString();
    }
}
