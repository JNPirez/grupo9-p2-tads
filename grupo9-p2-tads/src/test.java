import uy.edu.um.prog2.adt.*;
import uy.edu.um.prog2.adt.exceptions.*;




public class test {
    public static void main(String[] args) throws EmptyQueueException {
        // Tests de LinkedList
        System.out.println("TEST LINKEDLIST");
        // Crea una instancia de LinkedList
        LinkedList<Integer> list = new LinkedList<>();

        // Agrega elementos a la lista
        list.add(5);
        list.add(10);
        list.add(15);

        // Obtener el valor en una posición específica
        int value = list.get(1);
        System.out.println("Valor en la posición 1: " + value);

        // Verificar si la lista contiene un valor
        boolean containsValue = list.contains(10);
        System.out.println("¿Contiene el valor 10?: " + containsValue);

        // Eliminar un valor de la lista
        list.remove(10);

        // Verificar si la lista contiene un valor
        boolean containsValue2 = list.contains(10);
        System.out.println("¿Contiene el valor 10?: " + containsValue2);

        // Obtener el tamaño de la lista
        int size = list.size();
        System.out.println("Tamaño de la lista: " + size );

        System.out.println("\nTEST QUEUE");

        // Crear una instancia de Queue
        Queue<Integer> queue = new Queue<>();

        // Agregar elementos a la cola
        queue.enqueue(5);
        queue.enqueue(10);
        queue.enqueue(15);

        // Obtener el tamaño de la cola
        int sizequeue = queue.size();
        System.out.println("Tamaño de la cola: " + sizequeue);

        // Verificar si la cola está vacía
        boolean isEmpty = queue.isEmpty();
        System.out.println("¿La cola está vacía?: " + isEmpty);

        try {
            // Eliminar y obtener el primer elemento de la cola
            int firstElement = queue.dequeue();
            System.out.println("Primer elemento de la cola: " + firstElement);

            // Obtener el tamaño de la cola después de eliminar un elemento
            sizequeue = queue.size();
            System.out.println("Tamaño de la cola después de dequeue: " + sizequeue);
        } catch (EmptyQueueException e) {
            System.out.println("La cola está vacía. No se puede hacer dequeue.");
        }

        System.out.println("\nTEST STACK");
        Stack<Integer> stack = new Stack<>();

        // Verificar si la pila está vacía
        System.out.println("isEmpty: " + stack.isEmpty());  // Se espera: true

        // Agregar elementos a la pila
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);

        // Verificar si la pila está vacía después de agregar elementos
        System.out.println("isEmpty: " + stack.isEmpty());  // Se espera: false

        try {
            // Obtener el elemento en la parte superior de la pila
            System.out.println("top: " + stack.top());  // Se espera: 40

            // Realizar operaciones de eliminación
            stack.pop();
            stack.pop();
        } catch (EmptyStackException e) {
            System.out.println("EmptyStackException: " + e.getMessage());
        }

        // Verificar el elemento en la parte superior de la pila después de realizar operaciones de eliminación
        try {
            System.out.println("top: " + stack.top());  // Se espera: 20
        } catch (EmptyStackException e) {
            System.out.println("EmptyStackException: " + e.getMessage());
        }

        // Vaciar la pila
        stack.makeEmpty();

        // Verificar si la pila está vacía después de vaciarla
        System.out.println("isEmpty: " + stack.isEmpty());  // Se espera: true

        System.out.println("\nTEST ARBOL BINARIO DE BUSQUEDA");

        // Crear un árbol binario de búsqueda
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();

        try {
            // Insertar elementos
            bst.insert(5, "Data5");
            bst.insert(3, "Data3");
            bst.insert(8, "Data8");
            bst.insert(2, "Data2");
            bst.insert(4, "Data4");
            bst.insert(7, "Data7");
            bst.insert(9, "Data9");

            // Buscar elementos
            System.out.println(bst.find(3)); // Output: Data3
            System.out.println(bst.find(7)); // Output: Data7
            System.out.println(bst.find(10)); // Output: null

            // Eliminar un elemento
            bst.delete(8);
            System.out.println(bst.find(8)); // Output: null

            // Recorrido inOrder
            Queue<Integer> inOrder = bst.inOrder();
            System.out.println("InOrder traversal:");
            while (!inOrder.isEmpty()) {
                System.out.print(inOrder.dequeue() + " ");
            }
            System.out.println();

            // Recorrido preOrder
            Queue<Integer> preOrder = bst.preOrder();
            System.out.println("PreOrder traversal:");
            while (!preOrder.isEmpty()) {
                System.out.print(preOrder.dequeue() + " ");
            }
            System.out.println();

            // Recorrido postOrder
            Queue<Integer> postOrder = bst.postOrder();
            System.out.println("PostOrder traversal:");
            while (!postOrder.isEmpty()) {
                System.out.print(postOrder.dequeue() + " ");
            }
            System.out.println();

        } catch (InformacionInvalida | EmptyTreeException | EmptyQueueException e) {
            e.printStackTrace();
        }

        System.out.println("\nTEST HASH TABLE");

        // Crear una instancia de HashTable
        HashTable<String, Integer> hashTable = new HashTable<>(10);

        // Agregar elementos a la tabla hash
        hashTable.put("A", 1);
        hashTable.put("B", 2);
        hashTable.put("C", 3);

        // Obtener valores de la tabla hash
        System.out.println("Valor de 'A': " + hashTable.get("A")); // Debe imprimir 1
        System.out.println("Valor de 'B': " + hashTable.get("B")); // Debe imprimir 2
        System.out.println("Valor de 'C': " + hashTable.get("C")); // Debe imprimir 3

        // Verificar si la tabla hash contiene ciertas claves
        System.out.println("Contiene 'A': " + hashTable.contains("A")); // Debe imprimir true
        System.out.println("Contiene 'D': " + hashTable.contains("D")); // Debe imprimir false

        // Eliminar un elemento de la tabla hash
        hashTable.remove("B");
        System.out.println("Contiene 'B' después de eliminarlo: " + hashTable.contains("B")); // Debe imprimir false

        // Obtener el tamaño de la tabla hash
        System.out.println("Tamaño de la tabla hash: " + hashTable.size());

        // Verificar si la tabla hash está vacía
        System.out.println("La tabla hash está vacía: " + hashTable.isEmpty()); // Debe imprimir false

        // Imprimir la tabla hash
        hashTable.printHashTable();

        //TEST para Heap

        System.out.println("\nTEST HEAP");

        // Crear una instancia de Heap
        Heap<Integer> heap = new Heap<>();

        // Agregar elementos al Heap
        heap.add(5);
        heap.add(3);
        heap.add(8);
        heap.add(2);
        heap.add(4);
        heap.add(7);
        heap.add(9);

        // Obtener y eliminar el elemento máximo del Heap
        System.out.println("Elemento máximo: " + heap.getAndRemove()); // Debe imprimir 9

        // Obtener el tamaño del Heap
        System.out.println("Tamaño del Heap: " + heap.getSize()); // Debe imprimir 6

        // Mostrar el Heap en su representación visual
        System.out.println("Representación visual del Heap:");
        heap.printHeap();

    }
}
