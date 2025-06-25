package hash;

public class TestHashO {
    public static void main(String[] args) {
        int tableSize = 10; // Tamaño de la tabla hash

        // Crear una instancia de la tabla hash abierta
        HashO hashTable = new HashO(tableSize);

        System.out.println("--- Insertando registros en Hash Abierto ---");
        hashTable.insert(new Register(34, "Registro34A")); // hash(34) % 10 = 4
        hashTable.insert(new Register(3, "Registro3"));   // hash(3) % 10 = 3
        hashTable.insert(new Register(7, "Registro7A"));  // hash(7) % 10 = 7
        hashTable.insert(new Register(30, "Registro30")); // hash(30) % 10 = 0
        hashTable.insert(new Register(11, "Registro11")); // hash(11) % 10 = 1
        hashTable.insert(new Register(8, "Registro8"));   // hash(8) % 10 = 8
        hashTable.insert(new Register(7, "Registro7B"));  // Colisión con 7A, se añade a la misma lista
        hashTable.insert(new Register(23, "Registro23")); // hash(23) % 10 = 3 (colisión con 3)
        hashTable.insert(new Register(41, "Registro41")); // hash(41) % 10 = 1 (colisión con 11)
        hashTable.insert(new Register(16, "Registro16")); // hash(16) % 10 = 6
        hashTable.insert(new Register(34, "Registro34B"));// Clave 34 ya existe, se actualiza el nombre

        // Mostrar la tabla hash después de las inserciones
        hashTable.printTable();

        // Eliminar la clave 30
        System.out.println("--- Eliminando clave 30 de Hash Abierto ---");
        hashTable.delete(30);

        // Mostrar la tabla hash después de la eliminación
        hashTable.printTable();

        // Buscar la clave 23
        System.out.println("\n--- Buscando clave 23 en Hash Abierto ---");
        Register foundRegister = hashTable.search(23);
        if (foundRegister != null) {
            System.out.println("Registro encontrado para clave 23: " + foundRegister);
        } else {
            System.out.println("Registro con clave 23 no encontrado.");
        }

        // Probar una búsqueda de una clave que no existe
        System.out.println("\n--- Buscando clave 99 (no existente) en Hash Abierto ---");
        Register notFoundRegister = hashTable.search(99);
        if (notFoundRegister != null) {
            System.out.println("Registro encontrado para clave 99: " + notFoundRegister);
        } else {
            System.out.println("Registro con clave 99 no encontrado.");
        }

        // Probar eliminar una clave que no existe
        System.out.println("\n--- Intentando eliminar clave 50 (no existente) en Hash Abierto ---");
        hashTable.delete(50);
        hashTable.printTable();
    }
}