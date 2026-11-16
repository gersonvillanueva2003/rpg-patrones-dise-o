public abstract class CreadorPersonaje {
    public abstract Personaje crearPersonaje(String nombre);

    protected void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
    }
}
