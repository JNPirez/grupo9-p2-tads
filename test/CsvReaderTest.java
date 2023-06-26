import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import uy.edu.um.prog2.adt.exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.exceptions.FileNotValidException;


import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.fail;

public class CsvReaderTest {

    private long startTime;
    private MemoryUsage memoryBefore;
    private MemoryMXBean memoryBean;

    @BeforeClass
    public static void initialize() throws FileNotValidException {
        CsvReader.getDrivers();
        CsvReader.getCsvInfo();
    }

    @Before
    public void Start() {
        System.out.println();
        memoryBean = ManagementFactory.getMemoryMXBean();
        memoryBefore = memoryBean.getHeapMemoryUsage();
        startTime = System.currentTimeMillis();
    }


    @After
    public void end() {
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + " milliseconds");
        MemoryUsage memoryAfter = memoryBean.getHeapMemoryUsage();
        long memoryUsed = Math.abs(memoryAfter.getUsed() - memoryBefore.getUsed());
        System.out.println("Memory used: " + memoryUsed + " bytes");
        System.out.println();

    }

    /// VELOCIDAD Y RAM
    @Test
    public void testVelocidadCarga() throws FileNotValidException {
        System.out.println("Test de Velocidad y Memoria - Carga de Datos");
        CsvReader.getDrivers();
        CsvReader.getCsvInfo();
    }

    @Test
    public void testVelocidadMetodo1() throws EmptyTreeException {
        System.out.println("Test de Velocidad y Memoria - Metodo 1");

        CsvReader.Top10Pilotos(12,2021);
    }

    @Test
    public void testVelocidadMetodo2() {
        System.out.println("Test de Velocidad y Memoria - Metodo 2");
        CsvReader.topUsersConMasTweets();
    }

    @Test
    public void testVelocidadMetodo3() {
        System.out.println("Test de Velocidad y Memoria - Metodo 3");
        LocalDate dia = LocalDate.of(2021, 12, 12);
        CsvReader.HashTagsPorDia(dia);

    }

    @Test
    public void testVelocidadMetodo4() {
        System.out.println("Test de Velocidad y Memoria - Metodo 4");
        LocalDate dia = LocalDate.of(2021, 12, 12);
        CsvReader.mostUsedHashTagForADay(dia);

    }

    @Test
    public void testVelocidadMetodo5() {
        System.out.println("Test de Velocidad y Memoria - Metodo 5");
        CsvReader.top7UsersWithMoreFavourites();

    }

    @Test
    public void testVelocidadMetodo6() {
        System.out.println("Test de Velocidad y Memoria - Metodo 6");
        String f1 = "f1";
        CsvReader.buscartTweetPorPalabra(f1);
    }
}