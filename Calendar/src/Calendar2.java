import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Calendar2 {

    public static void main(String[] args) throws IOException {
List<ZonedDateTime>list=new LinkedList<>();
int diff ;
        Scanner scanner = new Scanner(System.in);
        String[] input;
        Set<String> zids = ZoneId.getAvailableZoneIds();
        File file = new File("C:\\Users\\marto\\IdeaProjects\\Calendar\\File.txt");
        System.out.println(" Choose 1 to input from file or 2 to input from the console");
        int command = scanner.nextInt();
        switch (command) {
            case 1 -> {
                System.out.println(" You choose 1:File input");
                scanner=new Scanner(file);
            }
            case 2 -> {
                System.out.println("You choose 2:Interactive input");
                scanner=new Scanner(System.in);
            }

        }
        input = scanner.nextLine().split(" ");

        for (String cityName : input) {

        String tzCityName = Normalizer.normalize(cityName, Normalizer.Form.NFKD)
                .replaceAll("[^\\p{ASCII}-_ ]", "")
                .replace(' ', '_');
          List<String>  possibleTimeZones = zids.stream()
                .filter(zid -> zid.endsWith("/" + tzCityName))
                .collect(Collectors.toList());


        System.out.format("%-12s %s%n", cityName, possibleTimeZones);

          for (String tz:possibleTimeZones) {
              Instant nowUtc = Instant.now();
              ZoneId IDOfTZ = ZoneId.of(tz);
              ZonedDateTime dateTimeOnTZ = ZonedDateTime.ofInstant(nowUtc, IDOfTZ);
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

