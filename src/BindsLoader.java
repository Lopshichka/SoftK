

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class BindsLoader {
    public static Map<Integer, String> keyMap = new HashMap<>();

    public static void load() throws IOException {
        Path binds = Path.of("user/config/binds.cfg");
        List<String> lines = Files.readAllLines(binds);
        for (String line : lines) {
            if (line.isEmpty() || line.startsWith("#"))
                continue;
            if (!line.contains("|")) {
                continue;
            }

            String[] parts = line.split("\\|");
            if (parts.length < 3)
                continue;

            String hotkeyStr = parts[0].trim();
            String value = parts[2].trim();

            int hotkeyCode = KeyResolver.resolve(hotkeyStr);

            if (hotkeyCode == 0) {
                Logger.log("incorrect key or combination: " + hotkeyStr);
                continue;
            }

            if (keyMap.containsKey(hotkeyCode)) {
                Logger.log("duplicate keys/combinations: " + hotkeyStr);
                continue;
            }

            if (value.equalsIgnoreCase("none")) {
                continue;
            }

            keyMap.put(hotkeyCode, value);
        }
    }
}