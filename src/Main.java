import controller.Server;
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
            Server server=new Server();
            User user=new User();
            MainGameFrame mainFrame = new MainGameFrame(600, 750,server, user,musicThread);
            mainFrame.setVisible(true);
        });
          musicThread.start();
//        Thread music = new Thread(musicThread);
//        music.start();

        // 调整音量
        musicThread.setVolume(0.5f); // 设置音量为一半
        float volume = musicThread.getVolume(); // 获取当前音量
        System.out.println("volume: " + volume);

    }
}
