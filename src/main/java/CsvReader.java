import entities.HashTag;
import entities.Tweet;
import entities.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import uy.edu.um.prog2.adt.exceptions.FileNotValidException;
import uy.edu.um.prog2.adt.hashtable.HashTable;
import uy.edu.um.prog2.adt.hashtable.MyHashTable;
import uy.edu.um.prog2.adt.linkedlist.LinkedList;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CsvReader {

    private static final String FILE_ERROR_FORMAT = "Wrong format in the file";
    private static final DateTimeFormatter FORMATTER_1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter FORMATTER_2 = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss");
    private static final String csvFile = "obligatorio2023/f1_dataset_test.csv";
    private static final String driversTxt = "obligatorio2023/drivers.txt";
    private static final MyLinkedList<User> userList = new LinkedList<>() {
    };
    private static final MyLinkedList<HashTag> hashTagList = new LinkedList<>();
    private static final MyLinkedList<Tweet> tweetsList = new LinkedList<>();
    private static final MyLinkedList<String> driversList = new LinkedList<>();

    public static void getDrivers() {
        try (BufferedReader br = new BufferedReader(new FileReader(driversTxt))) {
            String line;
            while ((line = br.readLine()) != null) {
                driversList.add(line.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getCsvInfo() throws FileNotValidException {
        MyHashTable<String,User> usersHashMap = new HashTable<>();
        try (CSVParser csvParser = CSVParser.parse(new FileReader(csvFile), CSVFormat.DEFAULT)) {
            csvParser.iterator().next();
            var autoGeneratedId = 1L;
            for (CSVRecord csvRecord : csvParser) {
                User user = new User();
                Tweet tweet = new Tweet();
                HashTag hashTagValue = new HashTag();
                try {
                    var userName = csvRecord.get(1);
                    user.setName(userName);
                    var userInMap = usersHashMap.get(userName);
                    String strDate = csvRecord.get(9).split(" ")[0];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(strDate, formatter);
                    tweet.setFecha(date);
                    tweet.setContent(csvRecord.get(10).toLowerCase());
                    tweet.setHashTags(hashTagValue);
                    tweet.setId(Long.parseLong(csvRecord.get(0)));
                    tweet.setSource(csvRecord.get(12));
                    tweet.setisRetweet(Boolean.parseBoolean(csvRecord.get(13)));
                    hashTagValue.setText(csvRecord.get(11).toLowerCase());
                    hashTagValue.setId(autoGeneratedId);
                    if (userInMap != null) {
                        userInMap.getTweets().add(tweet);
                        userInMap.incrementCountTweets();
                        hashTagList.add(hashTagValue);
                        tweetsList.add(tweet);
                        if (date.isAfter(userInMap.getLastTweet())) {
                            userInMap.setLastTweet(date);
                            userInMap.setisVerified(Boolean.parseBoolean(csvRecord.get(8))); // Actualizar el estado verificado si se encuentra un tweet más reciente
                        }
                    } else {
                        user.setId(autoGeneratedId);
                        user.setLocation(csvRecord.get(2));
                        user.setDescription(csvRecord.get(3).toLowerCase());
                        String stringDate = csvRecord.get(4);
                        LocalDate userCreated = LocalDate.parse(strDate, formatter);
                        user.setCreationDate(userCreated);
                        user.setFollowers(Double.parseDouble(csvRecord.get(5)));
                        user.setFriends(Double.parseDouble(csvRecord.get(6)));
                        user.setFavourites(Double.parseDouble(csvRecord.get(7)));
                        user.setisVerified(Boolean.parseBoolean(csvRecord.get(8)));
                        user.getTweets().add(tweet);
                        user.incrementCountTweets();
                        user.setLastTweet(date);
                        hashTagList.add(hashTagValue);
                        tweetsList.add(tweet);
                        userList.add(user);
                        usersHashMap.put(userName, user);
                        autoGeneratedId++;
                    }

                } catch (Exception ignored) {
                }
            }
        } catch (IOException e) {
            throw new FileNotValidException(FILE_ERROR_FORMAT, e);
        }
    }

/*
1) Listar los 10 pilotos activos en la temporada 2023 más mencionados en los tweets
en un mes (este mes será ingresado como 2 parámetros separados, mes y año, por
consola). Este listado debe incluir el nombre de los pilotos y la cantidad de
ocurrencias para cada uno de manera ordenada. Se espera que esta operación sea
de orden n en notación Big O
 */
    public static void Top10Pilotos(int mes, int ano) {
        getDrivers();
        HashTable<String, Integer> mencionesPiloto = new HashTable<>();

        for (int i = 0; i < tweetsList.size(); i++) {
            Tweet tweet = tweetsList.get(i);
            LocalDate tweetDate = tweet.getFecha();
            int dateMesValue = tweetDate.getMonthValue();
            int dateAno = tweetDate.getYear();
            if (dateMesValue == mes && dateAno == ano) {
                for (int j = 0; j < driversList.size(); j++) {
                    String piloto = driversList.get(j);
                    String[] pilotoSplitName = piloto.split(" ");
                    String pilotoApellido;
                    if (pilotoSplitName.length > 2) {
                        pilotoApellido = pilotoSplitName[1]+" "+pilotoSplitName[2];
                    } else{
                        pilotoApellido = pilotoSplitName[1];
                    }
                    mencionesPiloto.put(piloto, mencionesPiloto.getOrDefault(piloto, 0));
                    if (tweetsList.get(i).getContent().contains(pilotoApellido)) {
                        // Si el tweet contiene el apellido del piloto, se suma 1 a la cantidad de menciones
                        mencionesPiloto.put(piloto, mencionesPiloto.get(piloto) + 1);
                    }
                }
            }
        }
        LinkedList<String> topMenciones = new LinkedList<>();

        System.out.println("Top 10 pilotos más mencionados en el mes " + mes + " del año " + ano + ":");
        for (int i = 0; i < 10; i++) {
            int conteo = 0;
            String topPilotos = null;
            for (int j = 0; j < driversList.size(); j++) {
                String pilot = driversList.get(j);
                if (mencionesPiloto.contains(pilot)) {
                    int n = mencionesPiloto.get(pilot);
                    if (n >= conteo) {
                        conteo = n;
                        topPilotos = pilot;
                    }
                }
            }
            System.out.println(i + 1 + ") " + topPilotos + " - con " + conteo + " menciones");
            topMenciones.add(topPilotos);
            mencionesPiloto.remove(topPilotos);
        }

    }
/*
2) Top 15 usuarios con más tweets. Este listado debe incluir el nombre de usuario, la
cantidad de tweets, y si el usuario está verificado o no, ordenado por cantidad de
tweets en orden descendente. Se espera que esta operación sea de orden n en
notación Big O.
 */
    public static void topUsersConMasTweets() {
        LinkedList<User> topUsuarios = new LinkedList<>();

        for (int i = 0; i < userList.size(); i++) {
            User actual = userList.get(i);
            int p = 0;
            boolean inserted = false;
            for (int j = 0; j < topUsuarios.size(); j++) {
                User user = topUsuarios.get(j);
                if (actual.getCountTweets() > user.getCountTweets()) {
                    topUsuarios.add(p, actual);
                    inserted = true;
                    break;
                }
                p++;
            }
            if (!inserted && topUsuarios.size() < 15) {
                topUsuarios.add(actual);
            }
            if (topUsuarios.size() > 15) {
                topUsuarios.remove(topUsuarios.size() - 1);
            }
        }
        for (int i = 0; i < topUsuarios.size(); i++) {
            User user = topUsuarios.get(i);
            String verifiedStatus = user.getisVerified() ? "Verificado" : "No verificado";
            System.out.println(user.getName() + " - Tweets: " + user.getCountTweets() + " - " + verifiedStatus);
        }
    }


/*
3) Cantidad de hashtags distintos para un día dado. El día será ingresado en el formato
YYYY-MM-DD.
 */
    public static void HashTagsPorDia(LocalDate date) {
        var nuevoHashTags = new LinkedList<String>();
        for (int i = 0; i < tweetsList.size(); i++) {
            Tweet tweet = tweetsList.get(i);
            var tweetDate = tweet.getFecha();
            var textHashTag = tweet.getHashTags().getText();
            String[] hashTagsSplit = textHashTag.replaceAll("[\\[\\]' ]", "").split(",");
            if (tweetDate.equals(date)) {
                for (String hashTag : hashTagsSplit) {
                    if (!nuevoHashTags.contains(hashTag)) {
                        nuevoHashTags.add(hashTag);
                    }
                }

            }
        }
        System.out.println("Hashtags distintos en la fecha: " + date + ": " + nuevoHashTags.size());
    }


/*
4) Hashtag más usado para un día dado, sin tener en cuenta #f1. El día será ingresado
en el formato YYYY-MM-DD
 */


/*
Top 7 cuentas con más favoritos. Para este listado se deberá retornar el nombre del
usuario, junto con la cantidad de favoritos.
 */



/*
6) Cantidad de tweets con una palabra o frase específica (que será ingresado como
parámetro).
 */
    public static void buscartTweetPorPalabra(String textoBuscar) {
        var contador = 0;
        for (int i = 0; i < tweetsList.size(); i++) {
            String contenidoTweet = tweetsList.get(i).getContent();
            if (contenidoTweet != null && contenidoTweet.contains(textoBuscar.toLowerCase())) {
                contador++;
            }
        }
        System.out.println("Cantidad de tweets que contengan "+textoBuscar+" : "+ contador);
    }
}