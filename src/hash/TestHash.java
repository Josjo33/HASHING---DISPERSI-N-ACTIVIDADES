package hash;

public class TestHash {
    public static void main(String[] args) {
        // Tamaño de la tabla hash
        int tableSize = 10; // Puedes ajustar el tamaño según sea necesario para observar colisiones

        // Crear una instancia de la tabla hash
        HashC hashTable = new HashC(tableSize);

        // Valores a insertar: 34, 3, 7, 30, 11, 8, 7, 23, 41, 16, 34
        // (con diferentes nombres para los valores repetidos)

        System.out.println("--- Insertando registros ---");
        hashTable.insert(new Register(34, "Registro34A"));
        hashTable.insert(new Register(3, "Registro3"));
        hashTable.insert(new Register(7, "Registro7A"));
        hashTable.insert(new Register(30, "Registro30"));
        hashTable.insert(new Register(11, "Registro11"));
        hashTable.insert(new Register(8, "Registro8"));
        hashTable.insert(new Register(7, "Registro7B")); // Valor repetido, observa el comportamiento
        hashTable.insert(new Register(23, "Registro23"));
        hashTable.insert(new Register(41, "Registro41"));
        hashTable.insert(new Register(16, "Registro16"));
        hashTable.insert(new Register(34, "Registro34B")); // Valor repetido, observa el comportamiento

        // Mostrar la tabla hash después de las inserciones
        hashTable.printTable();

        // Eliminar la clave 30
        System.out.println("--- Eliminando clave 30 ---");
        hashTable.delete(30);

        // Mostrar la tabla hash después de la eliminación
        hashTable.printTable();

        // Buscar la clave 23
        System.out.println("--- Buscando clave 23 ---");
        Register foundRegister = hashTable.search(23);
        if (foundRegister != null) {
            System.out.println("Registro encontrado para clave 23: " + foundRegister);
        } else {
            System.out.println("Registro con clave 23 no encontrado.");
        }

        // Probar una búsqueda de una clave que no existe
        System.out.println("\n--- Buscando clave 99 (no existente) ---");
        Register notFoundRegister = hashTable.search(99);
        if (notFoundRegister != null) {
            System.out.println("Registro encontrado para clave 99: " + notFoundRegister);
        } else {
            System.out.println("Registro con clave 99 no encontrado.");
        }

        // Probar eliminar una clave que no existe
        System.out.println("\n--- Intentando eliminar clave 50 (no existente) ---");
        hashTable.delete(50);
        hashTable.printTable();
    }
}

