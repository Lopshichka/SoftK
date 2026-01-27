package src;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties cfg = new Properties();
    static {
        try {
            cfg.load(new FileReader("etc/config.properties"));
        } catch (IOException e) {
            Logger.log(e.getMessage());
        }
    }

    public static int get_log_row_limit() {
        return Integer.parseInt(cfg.getProperty("log_row_limit"));
    }

    public static int get_opening_delay() {
        return Integer.parseInt(cfg.getProperty("opening_delay"));
    }
}
