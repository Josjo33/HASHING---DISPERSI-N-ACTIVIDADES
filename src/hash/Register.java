package hash;

/**
 * Clase que representa un registro con clave y nombre.
 * No requiere modificaciones por parte del estudiante.
 */

public class Register {
    private int key;
    private String name;

    public Register(int key, String name) {
        this.key = key;
        this.name = name;
    }

    public int getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    // ¡NUEVO MÉTODO A AGREGAR!
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "(" + key + ", " + name + ")";
    }
}