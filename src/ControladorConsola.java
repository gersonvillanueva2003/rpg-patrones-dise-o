import java.util.Scanner;

public class ControladorConsola implements ControladorTurno {
    private final Scanner in;
    public ControladorConsola(Scanner in) { this.in = in; }

    @Override
    public Accion elegir(Personaje actor, Personaje objetivo, MotorCombate motor) {
        while (true) {
            System.out.println("  Acción de " + actor.getNombre()
                    + ": 1) Atacar  2) Defender  3) Usar poción (" + motor.pocionesRestantes(actor) + " restantes)");
            System.out.print("  > ");
            if (!in.hasNextLine()) { System.out.println("(sin entrada: ataca)"); return new AccionAtacar(); }
            String op = in.nextLine().trim();
            System.out.println(op);
            switch (op) {
                case "1": return new AccionAtacar();
                case "2": return new AccionDefender();
                case "3":
                    if (motor.pocionesRestantes(actor) > 0) return new AccionUsarItem();
                    System.out.println("  No te quedan pociones.");
                    break;
                default: System.out.println("  Opción inválida.");
            }
        }
    }
}
