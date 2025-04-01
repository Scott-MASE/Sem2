package Dates_Times;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class More_Examples {
    public static void main(String[] args) {
        // Declaration of Independence: July 4, 1776
        LocalDate declarationDate = LocalDate.of(1776, Month.JULY, 4);
        System.out.println("The Declaration of Independence was signed on: " 
                           + declarationDate.getDayOfWeek());
        
        LocalDate today = LocalDate.now();
        LocalDate eligibilityDate = today.minus(18, ChronoUnit.YEARS);
        
        LocalDate personBirthDate = LocalDate.of(2005, 5, 15); // Change as needed
        System.out.println("To be served, you must be born on or before: " + eligibilityDate);
        System.out.println("Person's DOB: " + personBirthDate);
        
        if (personBirthDate.isBefore(eligibilityDate) || personBirthDate.equals(eligibilityDate)) {
            System.out.println("Yes. The person is old enough to be served alcohol.");
        } else {
            System.out.println("No. The person is too young to be served alcohol.");
        }
        
        // Regular wake-up time is 06:30
        LocalTime regularTime = LocalTime.of(6, 30);
        // Saturday wake-up time: 15 minutes later than regular time
        LocalTime saturdayTime = regularTime.plus(15, ChronoUnit.MINUTES);
        
        System.out.println("On Saturday, I wake up at: " + saturdayTime);
        
        // Current time in London
        LocalDateTime nowLocal = LocalDateTime.now();
        ZonedDateTime londonTime = nowLocal.atZone(ZoneId.of("Europe/London"));
        
        // Convert London time to Tokyo time
        ZonedDateTime tokyoTime = londonTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        
        System.out.println("Current time in London: " + londonTime);
        System.out.println("Current time in Tokyo: " + tokyoTime);
        
        ZonedDateTime nowZone = ZonedDateTime.now();
        
        String pattern1 = "yyyy/MM/dd HH:mm:ss z";
        String pattern2 = "yyyy/MM/dd";
        
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern(pattern1);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(pattern2);
        
        System.out.println("Current date/time formatted with '" + pattern1 + "': " 
                           + formatter1.format(nowZone));
        System.out.println("Current date formatted with '" + pattern2 + "': " 
                           + formatter2.format(nowZone));
        
        LocalDate today2 = LocalDate.now();
        // Next New Year: January 1 of the next year
        LocalDate nextNewYear = LocalDate.of(today2.getYear() + 1, 1, 1);
        
        long daysUntilNewYear = ChronoUnit.DAYS.between(today2, nextNewYear);
        System.out.println("Days until New Year: " + daysUntilNewYear);
        
        Locale irishLocale = new Locale("en", "IE");
        LocalDate today3 = LocalDate.now();
        
        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)
                .withLocale(irishLocale);
        
        System.out.println("Today's date in Irish full format: " + formatter.format(today3));
    }
}
