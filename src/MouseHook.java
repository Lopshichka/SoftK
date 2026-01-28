
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;

public class MouseHook {
    public static void registerHook() throws Exception {
        GlobalScreen.addNativeMouseListener(new NativeMouseListener() {
            @Override
            public void nativeMouseClicked(com.github.kwhat.jnativehook.mouse.NativeMouseEvent e) {
                TrayIconManager.closeMenu();
            }
        });
    }
}