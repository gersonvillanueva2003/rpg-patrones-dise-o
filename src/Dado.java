import java.util.Random;

public final class Dado {
    private static Random rnd = new Random(7);
    private Dado() {}
    public static void semilla(long s) { rnd = new Random(s); }
    public static boolean probabilidad(int porcentaje) { return rnd.nextInt(100) < porcentaje; }
}
