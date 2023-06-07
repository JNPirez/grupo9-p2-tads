package uy.edu.um.prog2.adt.test.LinkedList;

import org.junit.Assert;
import org.junit.Test;
import uy.edu.um.prog2.adt.linkedlist.LinkedList;

public class LinkedListTest {
    @Test
    public void testLinkedList() {
        System.out.println("TEST LINKEDLIST");

        // Crea una instancia de LinkedList
        LinkedList<Integer> list = new LinkedList<>();

        // Agrega elementos a la lista
        list.add(5);
        list.add(10);
        list.add(15);

        // Obtener el valor en una posición específica
        int value = list.get(1);
        Assert.assertEquals(10, value);
        System.out.println("Valor en la posición 1: " + value);

        // Verificar si la lista contiene un valor
        boolean containsValue = list.contains(10);
        Assert.assertTrue(containsValue);
        System.out.println("¿Contiene el valor 10?: " + containsValue);

        // Eliminar un valor de la lista
        list.remove(10);

        // Verificar si la lista contiene un valor
        boolean containsValue2 = list.contains(10);
        Assert.assertFalse(containsValue2);
        System.out.println("¿Contiene el valor 10?: " + containsValue2);

        // Obtener el tamaño de la lista
        int size = list.size();
        Assert.assertEquals(2, size);
        System.out.println("Tamaño de la lista: " + size);
    }
}
