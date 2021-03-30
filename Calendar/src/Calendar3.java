import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Calendar3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner=new Scanner(System.in);
        int command=scanner.nextInt();
        switch (command) {
            case 1 -> birthdayCalculator(scanner);
            case 2 -> {
                scanner = new Scanner(System.in);
                dayCalculator(scanner);
            }
            case 3 -> timeZones(scanner);
        }
    }

    static void dayCalculator(Scanner scanner) {
        System.out.println("Въведи ден:yyyy-mm-dd");

        String input = scanner.nextLine();
        LocalDate dateAfter = LocalDate.parse(input);
        LocalDate nowDate = LocalDate.now();
        long noOfDaysBetween = ChronoUnit.DAYS.between((nowDate), dateAfter);
        System.out.println("Твоят ден се пада:" + dateAfter.getDayOfWeek() + " и разликата е " + Math.abs(noOfDaysBetween) + " дни");
    }
    static void birthdayCalculator(Scanner scanner){
        System.out.println("Въведи X месец/ци и Y ден/дни");
        LocalDate nowDate = LocalDate.now();
        long xMonths = scanner.nextInt();
        long yDays = scanner.nextInt();
        System.out.println("рождената ти дата се пада на " + nowDate.minusMonths(xMonths).minusDays(yDays) + " в ");
        System.out.println(nowDate.minusDays(yDays).getDayOfWeek());
    }
    static void timeZones(Scanner scanner) throws FileNotFoundException {
        List<ZonedDateTime> list=new LinkedList<>();
        int diff ;
        String[] input;
        Set<String> TimeZonesIDs = ZoneId.getAvailableZoneIds();
        File file = new File("C:\\Users\\marto\\IdeaProjects\\Calendar\\File.txt");
        System.out.println(" Choose 1 to input from file or 2 to input from the console");
        int command = scanner.nextInt();

        if (command == 1) {
            System.out.println(" You choose 1:File input. The names must start with uppercase");
            System.out.println("Your output is:");
            scanner = new Scanner(file);
        } else if (command == 2) {
            System.out.println("You choose 2:Interactive input. The names must start with uppercase");
            scanner = new Scanner(System.in);
        } else   System.out.println("Invalid command");


        input = scanner.nextLine().split(" ");

        for (String cityName : input) {

            String tzCityName = Normalizer.normalize(cityName, Normalizer.Form.NFKD)
                    .replaceAll("[^\\p{ASCII}-_ ]", "")
                    .replace(' ', '_');
            List<String>  possibleTimeZones = TimeZonesIDs.stream()
                    .filter(zid -> zid.endsWith("/" + tzCityName))
                    .collect(Collectors.toList());


            System.out.format("%-12s %s%n", cityName, possibleTimeZones);

            for (String tz:possibleTimeZones) {
                Instant nowUtc = Instant.now();
                ZoneId IDOfTimeZone = ZoneId.of(tz);
                ZonedDateTime dateTimeOnTZ = ZonedDateTime.ofInstant(nowUtc, IDOfTimeZone);
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                list.add(dateTimeOnTZ);
                System.out.println(dateTimeOnTZ.format(myFormatObj));

            }

        }
        if(list.get(0).getDayOfYear()==list.get(1).getDayOfYear()) {
            diff=Math.abs(list.get(1).getHour()-list.get(0).getHour());

        }else if(list.get(0).getDayOfYear()>list.get(1).getDayOfYear()){
            diff=24-list.get(1).getHour()+list.get(0).getHour();

        }
        else diff=24-list.get(0).getHour()+list.get(1).getHour();

        System.out.println("The time difference between the two inputted cities is: " + diff + " hours");
    }

}


