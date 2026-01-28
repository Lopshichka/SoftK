

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TrayIconManager {
    private static JPopupMenu menu;
    private static java.awt.TrayIcon trayIcon;

    public static void create() throws Exception {
        if (!SystemTray.isSupported()) {
            Logger.log("system tray is not supported");
            return;
        }
        delTrayIcon();
        Image icon = Toolkit.getDefaultToolkit().getImage("etc/assets/SoftK.png");
        createMenu();

        trayIcon = new java.awt.TrayIcon(icon, "SoftK");
        trayIcon.setImageAutoSize(true);
        trayIcon.addMouseListener(new TrayMouseListener());
        SystemTray.getSystemTray().add(trayIcon);
        Logger.log("tray icon has been created");
    }

    private static void createMenu() {
        menu = new JPopupMenu();
        menu.setBackground(new Color(45, 45, 45));
        menu.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 1));

        JMenuItem exit = new JMenuItem("Turn off SoftK");
        exit.addActionListener(e -> {
            menu.setVisible(false);
            Logger.log("turning off the program");
            TrayIconManager.delTrayIcon();
            System.exit(0);
        });

        exit.setBackground(new Color(45, 45, 45));
        exit.setForeground(Color.WHITE);
        exit.setBorder(BorderFactory.createEmptyBorder(4, 20, 4, 20));

        menu.add(exit);
    }

    static class TrayMouseListener extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                showMenu(e);
            }
            if (e.getButton() == MouseEvent.BUTTON1) {
                showMenu(e);
            }
        }

        private void showMenu(MouseEvent e) {
            menu.setLocation(e.getX(), e.getY());
            menu.setInvoker(menu);
            menu.setVisible(true);
        }
    }

    public static void delTrayIcon() {
        SystemTray systemTray = SystemTray.getSystemTray();
        systemTray.remove(trayIcon);
    }

    public static void closeMenu() {
        if (menu != null && menu.isVisible()) {
            try {
                Thread.sleep(100);
                menu.setVisible(false);
            } catch (Exception e) {
                Logger.log("couldn't close tray icon menu");
            }
        }
    }
}