import uy.edu.um.prog2.adt.exceptions.FileNotValidException;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotValidException {
        //CsvReader.getDrivers();
        CsvReader.getCsvInfo();

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            Menu.printMenu();
            option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    System.out.print("Ingrese el mes(MM):  ");
                    int month = scanner.nextInt();
                    System.out.print("Ingrese el año(YYYY):  ");
                    int year = scanner.nextInt();
                    System.out.println("Aguarde mientras se realiza la consulta...");
                    CsvReader.Top10Pilotos(month,year);
                    break;
                case 2:
                    System.out.println("Aguarde mientras se realiza la consulta...");
                    CsvReader.topUsersConMasTweets();
                    break;
                case 3:
                    System.out.print("Ingrese la fecha(YYYY-MM-DD):  ");
                    String fechaText = scanner.nextLine();
                    LocalDate fecha = LocalDate.parse(fechaText);
                    System.out.println("Aguarde mientras se realiza la consulta...");
                    CsvReader.HashTagsPorDia(LocalDate.of(fecha.getYear(), fecha.getMonth(), fecha.getDayOfMonth()));
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.print("Ingrese el texto a buscar: ");
                    String textoBusqueda = scanner.nextLine();
                    System.out.println("Aguarde mientras se realiza la consulta...");
                    CsvReader.buscartTweetPorPalabra(textoBusqueda);
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, elija una opción válida.");
            }
        } while (option != 0);

    }
}