package hash;

/**
 * Implementación de una tabla hash usando hash cerrado (sondeo lineal).
 */
public class HashC {

    /**
     * Clase interna para representar una celda de la tabla hash.
     * El estudiante puede usar esta clase tal como está.
     */

    private static class Element {
        Register register;    // Registro guardado
        boolean isAvailable; // Indica si la celda esta disponible

        public Element() {
            this.register = null;
            this.isAvailable = true; // Inicialmente disponible
        }
    }

    private Element[] table; // Arreglo de elementos (la tabla hash)
    private int size;        // Tamaño de la tabla

    /**
     * Constructor de la clase HashC.
     * Inicializa el arreglo de Element con el tamaño indicado.
     */

    public HashC(int size) {
        this.size = size;
        this.table = new Element[size];
        for (int i = 0; i < size; i++) {
            table[i] = new Element(); // Inicializar cada celda con un objeto Element vacío
        }
    }

    /**
     * Función hash para calcular el índice a partir de la clave.
     */

    private int hash(int key) {
        return key % size; // el modulo pues
    }

    /**
     * Método para insertar un nuevo registro en la tabla hash.
     * Usa sondeo lineal para encontrar una celda disponible.
     * Si la tabla está llena, mostrar un mensaje de error.
     */

    public void insert(Register reg) {
        int initialIndex = hash(reg.getKey()); //Calcula el índice hash de la clave del registro.
        int currentIndex = initialIndex;
        int count = 0; // Para detectar si la tabla está llena. Cuenta cuántos intentos se han hecho (para evitar bucles infinitos si la tabla está llena).

        while (count < size) { //para que no se intente insertar más veces que el tamaño total de la tabla. Si se intenta más veces, significa que la tabla está llena.
            // Si la celda está disponible o la clave es la misma (para actualizar)
            if (table[currentIndex].isAvailable || (table[currentIndex].register != null && table[currentIndex].register.getKey() == reg.getKey())) { //celda vacia o disponible v: ga
                table[currentIndex].register = reg; // Inicialmente es igual a initialIndex, y se usará para recorrer la tabla.
                table[currentIndex].isAvailable = false; // Marcar como ocupada y se procede con lo lineal p
                System.out.println("Insertado: " + reg + " en índice " + currentIndex);
                return;
            }
            currentIndex = (currentIndex + 1) % size; //si el espacio actual esta ocupado y la key o clave es diferente se procede al Sondeo lineal
            count++;
        }
        System.out.println("Error: La tabla hash está llena. No se pudo insertar " + reg);
    }

    /**
     * Método para buscar un registro en la tabla por su clave.
     * Recorre usando sondeo lineal hasta encontrar la clave o determinar que no está.
     */
    public Register search(int key) {
        int initialIndex = hash(key);
        int currentIndex = initialIndex; //es el índice que se usará para recorrer la tabla.
        int count = 0;

        while (count < size) {
            // Si la celda está ocupada y contiene la clave buscada
            if (!table[currentIndex].isAvailable && table[currentIndex].register != null && table[currentIndex].register.getKey() == key) {
                return table[currentIndex].register;
            }
            // Si la celda está vacía (disponible y nunca fue ocupada), la clave no está
            if (table[currentIndex].isAvailable && table[currentIndex].register == null) {
                return null;
            }
            currentIndex = (currentIndex + 1) % size; // Sondeo lineal
            count++;
        }
        return null; // No encontrado después de recorrer toda la tabla
    }

    /**
     * Método para eliminar un registro de forma lógica (no física).
     * Marcar la celda como disponible sin eliminar el objeto Element.
     */
    public void delete(int key) {
        int initialIndex = hash(key);//
        int currentIndex = initialIndex; //  es el índice que se usará para recorrer la tabla :v
        int count = 0; //sirve para evitar bucles infinitos: no se puede revisar más posiciones que el tamaño total de la tabla.

        while (count < size) { //Recorre la tabla como máximo una vuelta completa.

            // Si la celda está ocupada y contiene la clave a eliminar
            if (!table[currentIndex].isAvailable && table[currentIndex].register != null && table[currentIndex].register.getKey() == key) {
                table[currentIndex].isAvailable = true; // Marcar como disponible lógicamente
                System.out.println("Eliminado lógicamente: " + key + " del índice " + currentIndex);
                return;  // Si la posición no está disponible (isAvailable == false), y contiene un registro con la clave buscada, lo retorna.
            }

            // Si encontramos una celda verdaderamente vacía, significa que la clave no está presente
            if (table[currentIndex].isAvailable && table[currentIndex].register == null) {
                System.out.println("Clave " + key + " no encontrada para eliminar.");
                return;
            }

            currentIndex = (currentIndex + 1) % size; // Sondeo lineal
            count++;
        }
        System.out.println("Clave " + key + " no encontrada para eliminar.");
    }

    /**
     * Método para imprimir el estado actual de la tabla hash.
     * Recorre la tabla e imprime cada índice con su contenido (o vacío).
     */
    public void printTable() {
        System.out.println("\n - Estado actual de la tabla hash -");
        for (int i = 0; i < size; i++) {
            System.out.print("Índice " + i + ": ");
            if (table[i].isAvailable) {
                if (table[i].register == null) {
                    System.out.println("[Vacío]");
                } else {
                    System.out.println("[Eliminado Lógicamente: " + table[i].register + "]"); // Celda marcada como disponible pero con un registro anterior
                }
            } else {
                System.out.println(table[i].register);
            }
        }
        System.out.println("-------------------------------------\n");
    }
}