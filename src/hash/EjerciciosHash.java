package hash;

public class EjerciciosHash {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO EJERCICIOS DE HASH ---");

        // --- EJERCICIO 1: Insertar sin colisiones ---
        System.out.println("\n--- EJERCICIO 1: Insertar sin colisiones (Hash Cerrado) ---");
        // Usamos HashC porque es una tabla de tamaño fijo (hash cerrado)
        // el manejo de colisiones lo haremos con sondeo lineal xq es mas facil.
        // Sin embargo, con estos valores y tamaño, no habrá colisiones en el índice inicial.
        HashC hashC1 = new HashC(7); // Tabla hash de tamaño 7

        System.out.println("Insertando valores: 3, 10, 17, 24");
        hashC1.insert(new Register(3, "Val_3"));  // 3 % 7 = 3
        hashC1.insert(new Register(10, "Val_10"));// 10 % 7 = 3 (colisión en índice inicial) -> 10 % 7 = 3, pero luego buscará el siguiente disponible
        hashC1.insert(new Register(17, "Val_17"));// 17 % 7 = 3 (colisión en índice inicial)
        hashC1.insert(new Register(24, "Val_24"));// 24 % 7 = 3 (colisión en índice inicial)

        System.out.println("\nTabla Hash final para Ejercicio 1:");
        hashC1.printTable();

        // --- EJERCICIO 2: Resolver colisiones con sondeo lineal ---
        System.out.println("\n--- EJERCICIO 2: Resolver colisiones con sondeo lineal (Hash Cerrado) ---");
        HashC hashC2 = new HashC(6); // Tabla hash de tamaño 6

        System.out.println("Insertando valores: 12, 18, 24, 30");
        System.out.println("\n--- Paso a Paso Inserciones ---");
        hashC2.insert(new Register(12, "Val_12")); // 12 % 6 = 0
        hashC2.printTable(); // Mostrar tabla después de cada inserción
        hashC2.insert(new Register(18, "Val_18")); // 18 % 6 = 0 (colisión, irá al 1)
        hashC2.printTable();
        hashC2.insert(new Register(24, "Val_24")); // 24 % 6 = 0 (colisión, irá al 2)
        hashC2.printTable();
        hashC2.insert(new Register(30, "Val_30")); // 30 % 6 = 0 (colisión, irá al 3)
        hashC2.printTable();

        System.out.println("\n--- Explicación de colisiones en Ejercicio 2 ---");
        System.out.println("el sondeo lineal busca la siguiente celda disponible en secuencia (índice + 1, + 2, etc., con % size) ");
        System.out.println("hasta encontrar un espacio vacío o un registro con la misma clave para actualizar.");
        System.out.println("por ejemplo para calcular 18%6=0 pero 0 está ocupado por 12, así que 18 va al índice 1. Luego 24%6=0, 0 está ocupado, 1 está ocupado, 24 va al índice 2, y así sucesivamente.");


        // --- EJERCICIO 3: Buscar en hash abierto ---
        System.out.println("\n--- EJERCICIO 3: Buscar en hash abierto (Encadenamiento) ---");
        HashO hashO1 = new HashO(5); // Tabla hash de tamaño 5

        System.out.println("Insertando valores:");
        hashO1.insert(new Register(10, "Juan"));  // 10 % 5 = 0
        hashO1.insert(new Register(15, "Ana"));   // 15 % 5 = 0 (colisión con Juan, se añade a la lista del índice 0)
        hashO1.insert(new Register(20, "Luis"));  // 20 % 5 = 0 (colisión con Juan y Ana, se añade a la lista del índice 0)
        hashO1.insert(new Register(25, "Rosa"));  // 25 % 5 = 0 (colisión con Juan, Ana, Luis, se añade a la lista del índice 0)
        hashO1.printTable();

        System.out.println("\nBuscando el nombre asociado a la clave 20:");
        Register found20 = hashO1.search(20);
        if (found20 != null) {
            System.out.println("Clave 20 encontrada: " + found20.getName());
        } else {
            System.out.println("Clave 20 no encontrada.");
        }

        System.out.println("\n¿Qué pasa si buscas la clave 30?");
        Register found30 = hashO1.search(30); // 30 % 5 = 0
        if (found30 != null) {
            System.out.println("Clave 30 encontrada: " + found30.getName());
        } else {
            System.out.println("Clave 30 no encontrada.");
        }
        System.out.println("Explicación: La clave 30 también tiene un hash de 0 (30 % 5 = 0).");
        System.out.println("Al buscar, se iría a la lista enlazada del índice 0 y se recorrería. Como '30' no está en esa lista, la búsqueda devolvería null (no encontrado).");


        // --- EJERCICIO 4: Eliminar en hash cerrado ---
        System.out.println("\n--- EJERCICIO 4: Eliminar en hash cerrado ---");
        HashC hashC3 = new HashC(7); // Tabla hash de tamaño 7

        System.out.println("Insertando claves: 5, 12, 19");
        hashC3.insert(new Register(5, "Val_5"));  // 5 % 7 = 5
        hashC3.insert(new Register(12, "Val_12"));// 12 % 7 = 5 (colisión, irá al 6)
        hashC3.insert(new Register(19, "Val_19"));// 19 % 7 = 5 (colisión, irá al 0 si 6 está ocupado, o al siguiente disponible)
        hashC3.printTable();

        System.out.println("\nEliminando la clave 12:");
        hashC3.delete(12);
        hashC3.printTable();

        System.out.println("\n¿Cómo debe comportarse la búsqueda para la clave 19 después de la eliminación?");
        System.out.println("Buscando la clave 19...");
        Register found19 = hashC3.search(19);
        if (found19 != null) {
            System.out.println("Clave 19 encontrada: " + found19);
        } else {
            System.out.println("Clave 19 no encontrada.");
        }
        System.out.println("Explicación: Aunque la clave 12 fue eliminada lógicamente, su celda (en el índice 6 en este caso) está marcada como 'disponible' pero no 'vacía'.");
        System.out.println("La búsqueda para '19' (que tiene hash 5 y luego sondea, posiblemente pasando por el índice 6) debe poder 'saltar' la celda eliminada lógicamente y continuar el sondeo hasta encontrar '19'.");
        System.out.println("Si la celda se hubiera eliminado físicamente (poniendo null), la búsqueda de '19' se detendría prematuramente en el índice 6, asumiendo que '19' no está presente.");
        System.out.println("la implementación de `HashC.search` ya maneja esto: `if (table[currentIndex].isAvailable && table[currentIndex].register == null)` es la condición para detenerse, permitiendo pasar por registros lógicamente eliminados.");
    }
}