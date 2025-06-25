package hash;

import java.util.LinkedList;

/**
 * Clase que implementa una tabla hash con encadenamiento (listas enlazadas).
 */
public class HashO {
    private LinkedList<Register>[] table; // Arreglo de listas enlazadas
    private int size;

    /**
     * Constructor que inicializa el arreglo de listas.
     */
    public HashO(int size) {
        this.size = size;
        this.table = new LinkedList[size];
        // Inicializar cada posición con una nueva lista enlazada vacía
        for (int i = 0; i < size; i++) {
            this.table[i] = new LinkedList<>();
        }
    }

    /**
     * Función hash que determina la posición en la tabla.
     */
    private int hash(int key) {
        // Calcular el índice hash usando key % size, obteneniendo el operador del modulo v:
        return key % size;
    }

    /**
     * Inserta un registro en la posición adecuada.
     * Los elementos que colisionen deben agregarse a la lista enlazada correspondiente.
     * Si un registro con la misma clave ya existe, se actualiza el nombre.
     */
    public void insert(Register reg) {
        int index = hash(reg.getKey()); //Se obtiene el índice para la clave del registro.
        LinkedList<Register> list = table[index]; //Se accede a la lista enlazada en ese índice.

        // Buscar si el registro ya existe para actualizarlo
        for (Register existingReg : list) {
            if (existingReg.getKey() == reg.getKey()) {


                // Usamos el método setter público para actualizar el nombre
                existingReg.setName(reg.getName());//Si encuentra un registro con la misma clave actualiza su nombre usando setName.

                System.out.println("Actualizado: " + reg + " en índice " + index);
                return; //Luego termina (return) sin agregar un nuevo objeto.
            }
        }
        // Si no existe, agregarlo a la lista
        list.add(reg);
        System.out.println("Insertado: " + reg + " en índice " + index);
    }

    /**
     * Busca un registro por su clave.
     */
    public Register search(int key) {
        int index = hash(key); //Busca en la lista enlazada del índice correspondiente.
        LinkedList<Register> list = table[index];

        // Buscar el registro en la lista correspondiente
        for (Register reg : list) {
            if (reg.getKey() == key) {
                return reg;
            }
        }
        return null; // Si no se encuentra
    }

    /**
     * Elimina un registro por su clave.
     */
    public void delete(int key) {
        int index = hash(key); //Aplica la función hash a la clave y determina en qué posición del arreglo (table[]) se encuentra la lista enlazada que podría contener el registro.
        LinkedList<Register> list = table[index]; //Obtiene la lista enlazada correspondiente al índice calculado.

        // Buscar y eliminar el registro de la lista correspondiente
        // Se usa un iterador para eliminar de forma segura mientras se itera
        java.util.Iterator<Register> iterator = list.iterator(); // crea un iterador sobre la lista p

        while (iterator.hasNext()) { //Comienza un ciclo que se ejecuta mientras haya elementos en la lista
            Register reg = iterator.next(); // obtiene el siguiente registro de la lista para inspeccionarlo
            if (reg.getKey() == key) { // compara la clave actual con la clave que se desea eliminar

                iterator.remove();
                System.out.println("Eliminado: " + key + " del índice " + index);
                return;
            }
        }
        System.out.println("Clave " + key + " no encontrada para eliminar.");
    }

    /**
     * Muestra el contenido de la tabla hash.
     */
    public void printTable() {
        System.out.println("\n--- Estado actual de la tabla hash (Hash Abierto) ---");
        for (int i = 0; i < size; i++) {
            System.out.print("Índice " + i + ": ");
            if (table[i].isEmpty()) {
                System.out.println("[Vacío]");
            } else {
                System.out.println(table[i]); // LinkedList.toString() ya da una buena representación
            }
        }
        System.out.println("---------------------------------------------------\n");
    }
}