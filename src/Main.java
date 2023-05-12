import controller.Server;
import controller.User;
import music.MusicThread;
import view.MainGameFrame;

import javax.swing.*;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Server server=new Server();
            User user=new User();
//            ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
            MainGameFrame mainFrame = new MainGameFrame(600, 750,server, user);
//            GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard());
            mainFrame.setVisible(true);
        });
        URL musicURL = Main.class.getResource("/music/BGM.wav");


        // 创建音乐线程实例
        MusicThread musicThread = new MusicThread(musicURL, true);

        // 创建线程并启动
        Thread music = new Thread(musicThread);
        music.start();

        // 调整音量
        musicThread.setVolume(0.5f); // 设置音量为一半
        float volume = musicThread.getVolume(); // 获取当前音量
        System.out.println("volume: " + volume);

    }
}
