
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

public class Softk {
    private static boolean isWaited = true;

    public static void main(String[] args) throws Exception {
        Logger.log("SoftK launched");
        try {
            TrayIconManager.delTrayIcon();
            TrayIconManager.create();
            MouseHook.registerHook();
            KeyHook.registerHook();
            BindsLoader.load();
        } catch (Exception e) {
            Logger.log(e.getMessage());
            TrayIconManager.delTrayIcon();
            System.exit(0);
        }
    }

    public static void Launch(String resource) throws IOException, InterruptedException, URISyntaxException {
        if (isWaited) {
            isWaited = false;
            new Thread(() -> {
                String res = resource
                        .replace("\n", "")
                        .replace("\r", "")
                        .replace(";", "")
                        .replace("<", "")
                        .replace(">", "")
                        .replace("$()", "");
                try {
                    String command = "";
                    if (res.matches("\\d+")) {
                        command = "cmd /c start steam://run/";
                        Runtime.getRuntime().exec(command + res);
                        Logger.log("application is running, appId: " + "\"" + res + "\"");
                    } else if (res.startsWith("http://") || res.startsWith("https://")) {
                        Desktop.getDesktop().browse(new URI(res));
                        Logger.log("website is open " + "\"" + res + "\"");
                    } else {
                        String[] supportedExtensions = {
                                ".exe", ".bat", ".cmd", ".ps1", ".vbs",
                                ".jar", ".js", ".mp3", ".mp4", ".avi",
                                ".mkv", ".mov", ".docx", ".html", ".htm",
                                ".url", ".xlsx", ".xls", ".pptx", ".pdf",
                                ".doc", ".jpeg", ".png", ".jpg", ".bmp",
                                ".gif", ".txt", ".rar", ".zip", ".md"
                        };
                        boolean isSupported = false;
                        for (String ext : supportedExtensions) {
                            if ((res.toLowerCase()).endsWith(ext)) {
                                isSupported = true;
                                break;
                            }
                        }
                        if (isSupported) {
                            Logger.log(res);
                            File file = new File(res);
                            if (!file.exists()) {
                                Logger.log("file does not exist: " + res);
                                return;
                            }
                            String name = file.getName();
                            String directory = file.getParent();
                            try {
                                try {
                                    Desktop.getDesktop().open(file);
                                } catch (Exception e) {
                                    ProcessBuilder pb = new ProcessBuilder();
                                    if (directory != null) {
                                        pb.directory(new File(directory));
                                    }
                                    pb.command(file.getAbsolutePath());
                                    Process process = pb.start();
                                }
                            } catch (Exception e) {
                            }
                            Logger.log("app/file launched, path: " + "\"" + res + "\"");
                        } else {
                            Logger.log("unable to run, unsupported file type");
                        }
                    }
                } catch (Exception e) {
                    Logger.log("failed to launch " + res + ", error: " + e.getMessage());
                }
            }).start();
            Timeout();
        }
    }

    public static void Timeout() {
        Timer utilTimer = new java.util.Timer();
        utilTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                isWaited = true;
            }
        }, ConfigLoader.get_opening_delay());
    }

}