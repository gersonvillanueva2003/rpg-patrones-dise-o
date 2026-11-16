import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Nombre: ");
        String nombre = in.nextLine().trim();
        System.out.println(nombre);

        System.out.print("Clase (1=Guerrero, 2=Mago, 3=Arquero, 4=Pícaro): ");
        int clase = Integer.parseInt(in.nextLine().trim());
        System.out.println(clase);

        System.out.print("Equipamiento separado por comas (1=Espada, 2=Armadura, 3=Veneno, 4=Amuleto, 5=Bendición, 0=nada): ");
        String equipo = in.nextLine().trim();
        System.out.println(equipo);

        System.out.print("Enemigo (1=Goblin, 2=Orco, 3=Dragón): ");
        int tipoEnemigo = Integer.parseInt(in.nextLine().trim());
        System.out.println(tipoEnemigo);

        System.out.print("Modo de combate (1=Manual, 2=Automático): ");
        int modo = Integer.parseInt(in.nextLine().trim());
        System.out.println(modo);
        System.out.println();

        //Factory Method: el cliente solo conoce los creadores
        CreadorPersonaje creador = switch (clase) {
            case 1 -> new CreadorGuerrero();
            case 2 -> new CreadorMago();
            case 3 -> new CreadorArquero();
            case 4 -> new CreadorPicaro();
            default -> throw new IllegalArgumentException("Clase inválida: " + clase);
        };
        Personaje heroe = creador.crearPersonaje(nombre);

        //Decorator: equipamiento en el orden elegido
        for (String t : equipo.split(",")) {
            heroe = switch (t.trim()) {
                case "1" -> new EspadaDecorator(heroe);
                case "2" -> new ArmaduraDecorator(heroe);
                case "3" -> new VenenoDecorator(heroe);
                case "4" -> new AmuletoRegeneracionDecorator(heroe);
                case "5" -> new BendicionDecorator(heroe);
                default -> heroe; // "0" o vacío: sin equipo
            };
        }

        System.out.println("Héroe: " + heroe.getDescripcion());
        System.out.println("ATK: " + heroe.getAtaque()
                + " | DEF: " + heroe.getDefensa()
                + " | VEL: " + heroe.getVelocidad()
                + " | HP: " + heroe.getHp());

        CreadorEnemigo creadorEnemigo = switch (tipoEnemigo) {
            case 1 -> new CreadorGoblin();
            case 2 -> new CreadorOrco();
            case 3 -> new CreadorDragon();
            default -> throw new IllegalArgumentException("Enemigo inválido: " + tipoEnemigo);
        };
        Personaje enemigo = creadorEnemigo.crearEnemigo();
        System.out.println("Enemigo: " + enemigo.getNombre()
                + " | ATK: " + enemigo.getAtaque() + " | DEF: " + enemigo.getDefensa()
                + " | HP: " + enemigo.getHp());
        System.out.println("Pociones del héroe: " + MotorCombate.POCIONES_HEROE
                + " (curan " + MotorCombate.CURA_POCION + " HP)\n");

        //Observer + Command + Strategy
        MotorCombate motor = new MotorCombate();
        motor.agregarObservador(new HudConsola());
        motor.agregarObservador(new Bitacora());
        ControladorTurno control = (modo == 1) ? new ControladorConsola(in) : new ControladorAutomatico();
        motor.iniciarCombate(heroe, enemigo, control);
    }
}
