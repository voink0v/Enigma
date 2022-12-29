import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd.MM.yyyy-hh.mm.ss]: ");
    private String text;
    private LocalDateTime dateTime;


    public Message(String text) {
        this.text = text;
        this.dateTime = LocalDateTime.now();
    }

    public String getText() {

        return text;
    }

    @Override
    public String toString() {
        //return dateTime.format(formatter) + text;
        return   text;
    }
}
