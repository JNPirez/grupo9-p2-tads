package uy.edu.um.prog2.adt.test.Stack;

import org.junit.Assert;
import org.junit.Test;
import uy.edu.um.prog2.adt.exceptions.EmptyStackException;
import uy.edu.um.prog2.adt.stack.Stack;

public class StackTest {
    @Test
    public void testStackOperations() {
        Stack<Integer> stack = new Stack<>();

        // Verificar si la pila está vacía
        Assert.assertTrue(stack.isEmpty());

        // Agregar elementos a la pila
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);

        // Verificar si la pila está vacía después de agregar elementos
        Assert.assertFalse(stack.isEmpty());

        try {
            // Obtener el elemento en la parte superior de la pila
            Assert.assertEquals(40, stack.top().intValue());

            // Realizar operaciones de eliminación
            stack.pop();
            stack.pop();
        } catch (EmptyStackException e) {
            System.out.println("EmptyStackException: " + e.getMessage());
        }

        // Verificar el elemento en la parte superior de la pila después de realizar operaciones de eliminación
        try {
            Assert.assertEquals(20, stack.top().intValue());
        } catch (EmptyStackException e) {
            System.out.println("EmptyStackException: " + e.getMessage());
        }

        // Vaciar la pila
        stack.makeEmpty();

        // Verificar si la pila está vacía después de vaciarla
        Assert.assertTrue(stack.isEmpty());
    }
}
