package uy.edu.um.prog2.adt.test.HashTable;

import org.junit.Assert;
import org.junit.Test;
import uy.edu.um.prog2.adt.hashtable.HashTable;

public class HashTableTest {
    @Test
    public void testHashTableOperations() {
        // Crear una instancia de HashTable
        HashTable<String, Integer> hashTable = new HashTable<>(10);

        // Agregar elementos a la tabla hash
        hashTable.put("A", 1);
        hashTable.put("B", 2);
        hashTable.put("C", 3);

        // Obtener valores de la tabla hash
        Assert.assertEquals(1, hashTable.get("A").intValue());
        Assert.assertEquals(2, hashTable.get("B").intValue());
        Assert.assertEquals(3, hashTable.get("C").intValue());

        // Verificar si la tabla hash contiene ciertas claves
        Assert.assertTrue(hashTable.contains("A"));
        Assert.assertFalse(hashTable.contains("D"));

        // Eliminar un elemento de la tabla hash
        hashTable.remove("B");
        Assert.assertFalse(hashTable.contains("B"));

        // Obtener el tamaño de la tabla hash
        Assert.assertEquals(2, hashTable.size());

        // Verificar si la tabla hash está vacía
        Assert.assertFalse(hashTable.isEmpty());

        // Imprimir la tabla hash
        hashTable.printHashTable();
    }
}
