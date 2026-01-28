
import java.util.HashMap;
import java.util.Map;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class KeyResolver {
    private static final Map<String, Integer> KEY_MAP = new HashMap<>();
    private static final Map<String, Integer> MODIFIER_MAP = new HashMap<>();

    static {
        String[] keys = {
                "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12",

                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",

                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",

                "ESCAPE", "ENTER", "SPACE", "TAB", "BACKSPACE", "DELETE",

                "CONTROL", "ALT", "SHIFT", "WINDOWS", "META",

                "INSERT", "HOME", "END", "PAGE_UP", "PAGE_DOWN",
                "UP", "DOWN", "LEFT", "RIGHT",

                "NUMPAD0", "NUMPAD1", "NUMPAD2", "NUMPAD3", "NUMPAD4",
                "NUMPAD5", "NUMPAD6", "NUMPAD7", "NUMPAD8", "NUMPAD9",
                "NUMPAD_ADD", // +
                "NUMPAD_SUBTRACT", // -
                "NUMPAD_MULTIPLY", // *
                "NUMPAD_DIVIDE", // /
                "NUMPAD_ENTER",
                "NUMPAD_DECIMAL", // .

                "BACKQUOTE", // ` ~
                "MINUS", // - _`
                "EQUALS", // = +
                "OPEN_BRACKET", // [ {
                "CLOSE_BRACKET", // ] }
                "BACK_SLASH", // \ |
                "SEMICOLON", // ; :
                "QUOTE", // ' "
                "COMMA", // , <
                "PERIOD", // . >
                "SLASH", // / ?

                "PRINTSCREEN",
                "SCROLL_LOCK",
                "PAUSE",
                "CAPS_LOCK",
                "NUM_LOCK"
        };

        for (String key : keys) {
            try {
                KEY_MAP.put(key, NativeKeyEvent.class.getField("VC_" + key).getInt(null));
            } catch (Exception ignored) {
                //
            }
        }

        KEY_MAP.put("CTRL", KEY_MAP.get("CONTROL"));
        KEY_MAP.put("WIN", KEY_MAP.get("WINDOWS"));
        KEY_MAP.put("DEL", KEY_MAP.get("DELETE"));
        KEY_MAP.put("INS", KEY_MAP.get("INSERT"));
        KEY_MAP.put("PAGEUP", KEY_MAP.get("PAGE_UP"));
        KEY_MAP.put("PAGEDOWN", KEY_MAP.get("PAGE_DOWN"));
        KEY_MAP.put("PGUP", KEY_MAP.get("PAGE_UP"));
        KEY_MAP.put("PGDN", KEY_MAP.get("PAGE_DOWN"));
        KEY_MAP.put("`", KEY_MAP.get("BACKQUOTE"));
        KEY_MAP.put("~", KEY_MAP.get("BACKQUOTE"));
        KEY_MAP.put("-", KEY_MAP.get("MINUS"));
        KEY_MAP.put("=", KEY_MAP.get("EQUALS"));
        KEY_MAP.put("[", KEY_MAP.get("OPEN_BRACKET"));
        KEY_MAP.put("]", KEY_MAP.get("CLOSE_BRACKET"));
        KEY_MAP.put("\\", KEY_MAP.get("BACK_SLASH"));
        KEY_MAP.put(";", KEY_MAP.get("SEMICOLON"));
        KEY_MAP.put("'", KEY_MAP.get("QUOTE"));
        KEY_MAP.put(",", KEY_MAP.get("COMMA"));
        KEY_MAP.put(".", KEY_MAP.get("PERIOD"));
        KEY_MAP.put("/", KEY_MAP.get("SLASH"));
        KEY_MAP.put("+", KEY_MAP.get("NUMPAD_ADD"));
        KEY_MAP.put("*", KEY_MAP.get("NUMPAD_MULTIPLY"));

        MODIFIER_MAP.put("CTRL", NativeKeyEvent.CTRL_MASK);
        MODIFIER_MAP.put("CONTROL", NativeKeyEvent.CTRL_MASK);
        MODIFIER_MAP.put("SHIFT", NativeKeyEvent.SHIFT_MASK);
        MODIFIER_MAP.put("ALT", NativeKeyEvent.ALT_MASK);
        MODIFIER_MAP.put("WIN", NativeKeyEvent.META_MASK);
        MODIFIER_MAP.put("WINDOWS", NativeKeyEvent.META_MASK);
        MODIFIER_MAP.put("META", NativeKeyEvent.META_MASK);
    }

    public static int resolve(String hotkey) {
        if (hotkey.contains("+")) {
            String[] parts = hotkey.split("\\+");
            if (parts.length == 2) {
                String modifier = parts[0].trim().toUpperCase();
                String key = parts[1].trim().toUpperCase();

                Integer keyCode = KEY_MAP.get(key);
                if (keyCode == null || keyCode == 0) {
                    Logger.log("unknown key: " + key);
                    return 0;
                }

                Integer modifierMask = MODIFIER_MAP.get(modifier);
                if (modifierMask == null || modifierMask == 0) {
                    Logger.log("unknown modifier: " + modifier);
                    return 0;
                }

                return keyCode + (modifierMask * 10000);
            }
        }

        Integer result = KEY_MAP.get(hotkey);
        if (result == null) {
            result = KEY_MAP.get(hotkey.toUpperCase());
        }
        return result != null ? result : 0;
    }

}