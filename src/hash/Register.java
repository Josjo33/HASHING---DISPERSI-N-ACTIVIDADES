package hash;

/**
 * Clase que representa un registro con una clave y un nombre.
 * El estudiante no necesita modificar esta clase.
 */

public class Register {
    private int key;    // Clave que se usará como índice en la tabla hash
    private String name; // Nombre asociado al registro

    // Constructor para inicializar el registro con clave y nombre
    public Register(int key, String name) {
        this.key = key;
        this.name = name;
    }

    // Retorna la clave del registro
    public int getKey() {
        return key;
    }

    // Retorna el nombre del registro
    public String getName() {
        return name;
    }

    // Representación en texto del objeto Register
    @Override
    public String toString() {
        return "(" + key + ", " + name + ")";
    }
}
