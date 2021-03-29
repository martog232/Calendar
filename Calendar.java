import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Calendar {
        public static void main(String[] args) throws FileNotFoundException {
            File file=new File("C:\\Users\\marto\\IdeaProjects\\Calendar\\File.txt");
            Scanner scanner=new Scanner(file);
            System.out.println("Въведи ден:yyyy-mm-dd");
            String inputDateString = scanner.nextLine();
            LocalDate dateAfter = LocalDate.parse(inputDateString);
            LocalDate nowDate=LocalDate.now();
            long noOfDaysBetween = ChronoUnit.DAYS.between((nowDate), dateAfter);
            System.out.println("Твоят ден се пада:" + dateAfter.getDayOfWeek() + " и до него остават "+ noOfDaysBetween + " дни");
        }
    }

