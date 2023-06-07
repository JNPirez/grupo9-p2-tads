package uy.edu.um.prog2.adt.test.Queue;

import org.junit.Assert;
import org.junit.Test;
import uy.edu.um.prog2.adt.queue.Queue;
import uy.edu.um.prog2.adt.exceptions.EmptyQueueException;

public class QueueTest {
    @Test
    public void testQueue() throws EmptyQueueException {
        System.out.println("TEST QUEUE");

        // Crear una instancia de Queue
        Queue<Integer> queue = new Queue<>();

        // Agregar elementos a la cola
        queue.enqueue(5);
        queue.enqueue(10);
        queue.enqueue(15);

        // Obtener el tamaño de la cola
        int sizequeue = queue.size();
        Assert.assertEquals(3, sizequeue);
        System.out.println("Tamaño de la cola: " + sizequeue);

        // Verificar si la cola está vacía
        boolean isEmpty = queue.isEmpty();
        Assert.assertFalse(isEmpty);
        System.out.println("¿La cola está vacía?: " + isEmpty);

        try {
            // Eliminar y obtener el primer elemento de la cola
            int firstElement = queue.dequeue();
            Assert.assertEquals(5, firstElement);
            System.out.println("Primer elemento de la cola: " + firstElement);

            // Obtener el tamaño de la cola después de eliminar un elemento
            sizequeue = queue.size();
            Assert.assertEquals(2, sizequeue);
            System.out.println("Tamaño de la cola después de dequeue: " + sizequeue);
        } catch (EmptyQueueException e) {
            System.out.println("La cola está vacía. No se puede hacer dequeue.");
        }
    }
}
