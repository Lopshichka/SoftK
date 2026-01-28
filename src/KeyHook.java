
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.util.Map;
import java.io.IOException;

public class KeyHook {
    private static Map<Integer, String> keyMap;
    public static String resource = "";

    private static boolean ctrlPressed = false;
    private static boolean shiftPressed = false;
    private static boolean altPressed = false;
    private static boolean metaPressed = false;

    public static void registerHook() throws Exception {
        keyMap = BindsLoader.keyMap;
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
            public void nativeKeyPressed(NativeKeyEvent e) {
                int keyCode = e.getKeyCode();

                updateModifiers(keyCode, true);

                checkCombination(keyCode);
            }

            public void nativeKeyReleased(NativeKeyEvent e) {
                int keyCode = e.getKeyCode();

                updateModifiers(keyCode, false);
            }

            private void updateModifiers(int keyCode, boolean pressed) {
                if (keyCode == NativeKeyEvent.VC_CONTROL) {
                    ctrlPressed = pressed;
                } else if (keyCode == NativeKeyEvent.VC_SHIFT) {
                    shiftPressed = pressed;
                } else if (keyCode == NativeKeyEvent.VC_ALT) {
                    altPressed = pressed;
                } else if (keyCode == NativeKeyEvent.VC_META) {
                    metaPressed = pressed;
                }
            }

            private void checkCombination(int keyCode) {
                if (keyCode == NativeKeyEvent.VC_CONTROL ||
                        keyCode == NativeKeyEvent.VC_SHIFT ||
                        keyCode == NativeKeyEvent.VC_ALT ||
                        keyCode == NativeKeyEvent.VC_META) {
                    return;
                }

                int modifierMask = 0;
                if (ctrlPressed)
                    modifierMask |= NativeKeyEvent.CTRL_MASK;
                if (shiftPressed)
                    modifierMask |= NativeKeyEvent.SHIFT_MASK;
                if (altPressed)
                    modifierMask |= NativeKeyEvent.ALT_MASK;
                if (metaPressed)
                    modifierMask |= NativeKeyEvent.META_MASK;

                int combinationCode = keyCode + (modifierMask * 10000);
                if (keyMap.containsKey(combinationCode)) {
                    resource = keyMap.get(combinationCode);
                    try {
                        Softk.Launch(resource);
                    } catch (Exception e1) {
                        Logger.log(e1.getMessage());
                    }
                } else if (modifierMask == 0 && keyMap.containsKey(keyCode)) {
                    resource = keyMap.get(keyCode);
                    try {
                        Softk.Launch(resource);
                    } catch (Exception e1) {
                        Logger.log(e1.getMessage());
                    }
                }
            }
        });
    }
}