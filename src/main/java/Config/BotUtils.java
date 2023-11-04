package Config;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BotUtils {

    public static String getCurrentTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

}
