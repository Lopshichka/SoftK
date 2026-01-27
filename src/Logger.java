package src;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Logger {
    private static final DateTimeFormatter TimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter DateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Path logs = Path.of("logs/app.log");
    static {
        try {
            List<String> lines = (Files.readAllLines(logs));
            if (lines.size() > ConfigLoader.get_log_row_limit()) {
                Files.writeString(logs, "");
                log("log file has been cleared");
            }
        } catch (Exception e) {
            log("couldn't clear log file");
        }
    }

    public static void log(String text) {
        String date = LocalDate.now().format(DateFormatter);
        String time = LocalTime.now().format(TimeFormatter);
        try {
            Files.writeString(logs, String.format("[%s | %s] > %s\n", date, time, text), StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (Exception e) {
            //
        }
    }
}
