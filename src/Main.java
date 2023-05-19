import controller.User;
import music.MusicThread;
import view.MainGameFrame;

import javax.swing.*;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        URL musicURL = Main.class.getResource("/music/BGM.wav");
        MusicThread musicThread = new MusicThread(musicURL, true,true);
        SwingUtilities.invokeLater(() -> {
            User user=new User();
            MainGameFrame mainFrame = new MainGameFrame(600, 750,user,musicThread);
            mainFrame.setVisible(true);
        });
          musicThread.start();
    }
}
