package uy.edu.um.prog2.adt.test.Heap;

import org.junit.Assert;
import org.junit.Test;
import uy.edu.um.prog2.adt.heap.Heap;

public class HeapTest {
    @Test
    public void testHeapOperations() {
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
        Assert.assertEquals(9, heap.getAndRemove().intValue());

        // Obtener el tamaño del Heap
        Assert.assertEquals(6, heap.getSize());

        // Mostrar el Heap en su representación visual
        System.out.println("Representación visual del Heap:");
        heap.printHeap();
    }
}
