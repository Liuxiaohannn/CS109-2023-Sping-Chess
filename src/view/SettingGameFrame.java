package view;

import controller.User;
import music.MusicThread;
import view.UI.ImagePanel;
import view.UI.RoundButton;
import view.UI.RoundLabel;

import javax.swing.*;
import java.awt.*;

public class SettingGameFrame extends JFrame {

  private final int WIDTH;
  private final int HEIGHT;
  private final int BUTTON_WIDTH = 200;
  private final int BUTTON_HEIGHT = 50;

  private User user;
  private MainGameFrame mainGameFrame;
  private ChessGameFrame chessGameFrame;
  private ImagePanel mainPanel;
  private MusicThread musicThread;
  private int previousSliderValue;

  private RoundLabel mainLabel;
  private RoundButton loginButton;
  private RoundButton MusicButton;
  private RoundButton backgroundButton;
  private RoundButton chessboardButton;
  private RoundButton ruleButton;
  private RoundButton backButton;
  private JButton playPauseButton;
  private JSlider volumeSlider;




  public SettingGameFrame(int width,int height,MainGameFrame mainFrame,MusicThread musicThread){
    setTitle("2023 CS109 Project Demo"); //设置标题
    this.WIDTH = width;
    this.HEIGHT = height;
    this.mainGameFrame = mainFrame;
    this.user = mainGameFrame.getUser();
    this.musicThread=musicThread;
    setSize(WIDTH, HEIGHT);
    setLocationRelativeTo(null); // Center the window.

    setLayout(null);

    initComponents();
    addBackgroundButton(this.mainGameFrame);
    addBackgroundImage();
    setupLayout();

  }
  public SettingGameFrame(int width,int height,ChessGameFrame chessGameFrame,MusicThread musicThread){
    setTitle("2023 CS109 Project Demo"); //设置标题
    this.WIDTH = width;
    this.HEIGHT = height;
    this.chessGameFrame = chessGameFrame;
    this.user = chessGameFrame.getUser();
    this.musicThread=musicThread;
    setSize(WIDTH, HEIGHT);
    setLocationRelativeTo(null); // Center the window.


    initComponents();
    addBackgroundButton(this.chessGameFrame);
    addBackgroundImage();
    setupLayout();

  }

  private void addBackgroundImage() {
    mainPanel = new ImagePanel(getClass().getResource("/MainBackground.png"));
    setContentPane(mainPanel);
    mainPanel.setLayout(null);
  }

  private void initComponents() {
    addMainLabel();
    addLoginButton();
    addMusicButton();
    addRuleButton();
    addBackButton();
  }

  private void addMainLabel() {
    mainLabel = new RoundLabel("设 置", getBackground(), 20);
    mainLabel.setFont(new Font("宋体", Font.BOLD, 30));
    mainLabel.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
    mainLabel.setVerticalAlignment(SwingConstants.CENTER);
  }

  private void addLoginButton() {
    loginButton = new RoundButton("登 录");
    loginButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    loginButton.setFont(new Font("宋体", Font.BOLD, 24));
    loginButton.addActionListener(e -> {
      // 创建登录或注册的对话框
      JDialog loginDialog = new JDialog(this, "登录", true);
      loginDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      loginDialog.setSize(300, 200);
      loginDialog.setLocationRelativeTo(this);

      // 添加用户名和密码的输入框
      JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
      inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      inputPanel.add(new JLabel("账号:"));
      JTextField usernameField = new JTextField();
      inputPanel.add(usernameField);
      inputPanel.add(new JLabel("密码:"));
      JPasswordField passwordField = new JPasswordField();
      inputPanel.add(passwordField);

      // 添加登录、注册和取消按钮
      JButton loginButton = new JButton("登录");
      JButton registerButton = new JButton("注册");
      JButton cancelButton = new JButton("取消");
      loginButton.addActionListener(e1 -> {
        // 进行登录操作
        User user = User.loadFromFile(usernameField.getText());
        if (user != null && user.getPassword().equals(passwordField.getText())) {
          updateLabel(usernameField.getText());
          JOptionPane.showMessageDialog(loginDialog, "登录成功");
          loginDialog.dispose();
        } else {
          JOptionPane.showMessageDialog(loginDialog, "账号或密码错误!");
        }
      });
      registerButton.addActionListener(e1 -> {
        // 进行注册操作
        User existingUser = User.loadFromFile(usernameField.getText());
        if (existingUser != null) {
          JOptionPane.showMessageDialog(loginDialog, "用户名已存在!");
        } else {
          User newUser = new User(usernameField.getText(), passwordField.getText());
          newUser.saveToFile();
          updateLabel(usernameField.getText());
          JOptionPane.showMessageDialog(loginDialog, "注册成功!");
          loginDialog.dispose();
        }
      });
      cancelButton.addActionListener(e1 -> loginDialog.dispose());

      JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
      buttonPanel.add(loginButton);
      buttonPanel.add(registerButton);
      buttonPanel.add(cancelButton);

      // 将所有组件添加到对话框中
      loginDialog.add(inputPanel, BorderLayout.CENTER);
      loginDialog.add(buttonPanel, BorderLayout.SOUTH);
      loginDialog.setVisible(true);
    });
  }

  private void addMusicButton() {
    MusicButton = new RoundButton("音 乐");
    MusicButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    MusicButton.setFont(new Font("宋体", Font.BOLD, 24));

    volumeSlider=new JSlider(JSlider.HORIZONTAL,0,100,50);
    previousSliderValue = volumeSlider.getValue();
    volumeSlider.addChangeListener(e -> {
      int value= volumeSlider.getValue();
      float volume=value/100.0f;
      musicThread.setVolume(volume);
    });
    float initialVolume = musicThread.getVolume();
    int initialSliderValue = (int) (initialVolume * 100);
    volumeSlider.setValue(initialSliderValue);
    if (musicThread.isPlaying){
      playPauseButton=new JButton("暂 停");
    }else{
      playPauseButton=new JButton("播 放");
    }
    playPauseButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    playPauseButton.setFont(new Font("宋体", Font.BOLD, 15));
    playPauseButton.addActionListener(e -> {
      if (musicThread.isPlaying){
        musicThread.pauseMusic();
        previousSliderValue = volumeSlider.getValue();
        volumeSlider.setValue(0);
        playPauseButton.setText("播 放");
        playPauseButton.repaint();
      }else{
        musicThread.playMusic();
        volumeSlider.setValue(previousSliderValue);
        playPauseButton.setText("暂 停");
        playPauseButton.repaint();
        volumeSlider.repaint();
      }

    });




    MusicButton.addActionListener(e -> {
      JDialog MusicButtonDialog=new JDialog(this, "音 乐", true);
      MusicButtonDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      MusicButtonDialog.setSize(300, 200);
      MusicButtonDialog.setLocationRelativeTo(this);
      JPanel MusicJpanel =new JPanel(new GridLayout());
      MusicJpanel.add(playPauseButton);
      MusicJpanel.add(volumeSlider);
      MusicButtonDialog.add(MusicJpanel, BorderLayout.CENTER);
      MusicButtonDialog.setVisible(true);
    });
  }

  private void addBackgroundButton(ChessGameFrame chessGameFrame) {
    backgroundButton = new RoundButton("背 景");
    backgroundButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    backgroundButton.setFont(new Font("宋体", Font.BOLD, 24));
    backgroundButton.addActionListener(e -> {
//      SettingGameFrame settingGameFrame=new SettingGameFrame(500,500,this.chessGameFrame);

      System.out.println("backgroundButton clicked");
      if (chessGameFrame == null) {
        System.out.println("chessGameFrame is null");
        return;
      }
      // 创建背景选择对话框
      JDialog backgroundDialog = new JDialog(this, "背 景", true);
      backgroundDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      backgroundDialog.setSize(300, 200);
      backgroundDialog.setLocationRelativeTo(this);


      // 添加背景选择按钮
      JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
      inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      JButton background1Button = new JButton("背景 1");
      JButton background2Button = new JButton("背景 2");
      JButton background3Button = new JButton("背景 3");
      JButton background4Button = new JButton("背景 4");
      background1Button.addActionListener(e1 -> {
        backgroundDialog.dispose();
        chessGameFrame.setBackgroundImage(1);
      });
      background2Button.addActionListener(e1 -> {
        backgroundDialog.dispose();
        chessGameFrame.setBackgroundImage(2);
      });
      background3Button.addActionListener(e1 -> {
        backgroundDialog.dispose();
        chessGameFrame.setBackgroundImage(3);
      });
      background4Button.addActionListener(e1 -> {
        backgroundDialog.dispose();
        chessGameFrame.setBackgroundImage(4);
      });
      inputPanel.add(background1Button);
      inputPanel.add(background2Button);
      inputPanel.add(background3Button);
      inputPanel.add(background4Button);

      backgroundDialog.add(inputPanel, BorderLayout.CENTER);
      backgroundDialog.setVisible(true);
    });
  }
private void addBackgroundButton(MainGameFrame mainFrame) {
    backgroundButton = new RoundButton("背 景");
    backgroundButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    backgroundButton.setFont(new Font("宋体", Font.BOLD, 24));
    backgroundButton.addActionListener(e -> {
      System.out.println("backgroundButton clicked");
      if (mainFrame == null) {
        System.out.println("mainFrame is null");
        return;
      }
      // 创建背景选择对话框
      JDialog backgroundDialog = new JDialog(this, "背 景", true);
      backgroundDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      backgroundDialog.setSize(300, 200);
      backgroundDialog.setLocationRelativeTo(this);


      // 添加背景选择按钮
      JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
      inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      JButton background1Button = new JButton("背景 1");
      JButton background2Button = new JButton("背景 2");
      JButton background3Button = new JButton("背景 3");
      JButton background4Button = new JButton("背景 4");
      background1Button.addActionListener(e1 -> {
        backgroundDialog.dispose();
        mainFrame.setBackgroundImage(1);
      });
      background2Button.addActionListener(e1 -> {
        backgroundDialog.dispose();
        mainFrame.setBackgroundImage(2);
      });
      background3Button.addActionListener(e1 -> {
        backgroundDialog.dispose();
        mainFrame.setBackgroundImage(3);
      });
      background4Button.addActionListener(e1 -> {
        backgroundDialog.dispose();
        mainFrame.setBackgroundImage(4);
      });
      inputPanel.add(background1Button);
      inputPanel.add(background2Button);
      inputPanel.add(background3Button);
      inputPanel.add(background4Button);

      backgroundDialog.add(inputPanel, BorderLayout.CENTER);
      backgroundDialog.setVisible(true);
    });
  }



  private void addRuleButton() {
    ruleButton = new RoundButton("规 则");
    ruleButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    ruleButton.setFont(new Font("宋体", Font.BOLD, 24));

    // Add an ActionListener to the ruleButton
    ruleButton.addActionListener(e -> showGameRules());
  }

  private void showGameRules() {
    String gameRules = "Introduction\n" +
        "Jungle or Dou Shou Qi (斗兽棋), is a modern Chinese board game with an obscure history, as\n" +
        "shown in the Figure 1. The game is played on a 7×9 board and is popular with children in the\n" +
        "Far East. Jungle is a two-player strategy game and has been cited by The Playboy Winner's\n" +
        "Guide to Board Games as resembling the Western game Stratego.\n" +
        "Pieces\n" +
        "Each player owns 8 animal pieces representing different animals of various ranks, and higher\n" +
        "ranked animals can capture the animals of lower or equal rank. But there is a special case that\n" +
        "elephant cannot capture the rat while the rat can capture the elephant.\n" +
        "(Elephant>Lion>Tiger>Leopard>Wolf>Dog>Cat>Rat)\n" +
        "Each player moves alternatively, and all pieces can move one square horizontally or\n" +
        "vertically, but not diagonally. As shown in the table, there are some special movements for\n" +
        "lion, tiger and rat. These will be explained in detail:\n" +
        " Entering the river: \n" +
        "  The rat is the only animal that may go onto a water square. \n" +
        "  After entering the river, the rat cannot be captured by any piece on land. \n" +
        "  Also, the rat in river cannot capture the elephant on the land. \n" +
        " Jumping across the river: \n" +
        "  The lion and tiger can jump over a river vertically or horizonally. They jump from a \n" +
        " square on one edge of the river to the next non-water square on the other side. \n" +
        "  If that square contains an enemy piece of equal or lower rank, the lion or tiger \n" +
        " capture it as part of their jump.\n" +
        "  However, a jumping move is blocked (not permitted) if a rat of either color \n" +
        "currently occupies any of the intervening water squares." +
        "Chessboard \n" +
        "Jungle game has an interesting chessboard with differerent terrains including dens, traps and \n" +
        "rivers. After the initialization, the pieces start on squares with pictures corresponding to their \n" +
        "animals, which are invariably shown on the Jungle board.\n" +
        "The three kinds of special terrains are explained as:\n" +
        " Dens(兽穴): It is not allowed that the piece enters its own den. If the player's piece enters \n" +
        "the dens of his/her opponent, then the player wins,.\n" +
        " Trap(陷阱): If a piece entering the opponents's trap, then its rank is reduced into 0 \n" +
        "temporarily before exiting. The trapped piece could be captured by any pieces of the \n" +
        "defensing side.\n" +
        "  River(河流): They are located in the center of the chessboard, each comprising 6 squares in a \n" +
        "2×3 rectangle. Only rats could enter the river, and lions and tigers could jump across the \n" +
        "river. \n" +
        "Rules\n" +
        "1. Game Initialization: At the beginning, all 16 pieces are put into the chessboard as the initial \n" +
        "state. The initial state is shown in Figure 6.\n" +
        "2. Game Progress: The player with blue moves first. Two players take the turn alternatively until \n" +
        "the game is finished. When a player takes turn, he/she can select one of his pieces and do \n" +
        "one of the following:\n" +
        " Moving one square horizontally or vertically. For lion, tiger and rat, they also have special \n" +
        "movements related to the river squares, which have been introduced previously.\n" +
        " Capturing an opposing piece. In all captures, the captured pieces is removed from the board \n" +
        "and the square is occupied by the capturing piece. A piece can capture any enemy piece \n" +
        "following the rules introduced in \"Pieces\".\n" +
        "3. Game Termination: A player loses the game if one of the following happens:\n" +
        " The den is entered by his/her opponents.\n" +
        " All of his/her pieces are captured and he/her is unable to do any movement.";

    JTextArea textArea = new JTextArea(gameRules);
    textArea.setWrapStyleWord(true);
    textArea.setLineWrap(true);
    textArea.setEditable(false);
    textArea.setMargin(new Insets(10, 10, 10, 10));
    textArea.setCaretPosition(0);

    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setPreferredSize(new Dimension(400, 300));

    JOptionPane.showMessageDialog(
        null,
        scrollPane,
        "游戏规则",
        JOptionPane.INFORMATION_MESSAGE
    );
  }

  private void addBackButton() {
    backButton = new RoundButton("返 回");
    backButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    backButton.setFont(new Font("宋体", Font.BOLD, 24));
    backButton.addActionListener(e -> {
      dispose();
    });
  }

  public void updateLabel(String text) {
    mainLabel.setText(text);
  }

  private void setupLayout(){
    mainPanel.add(mainLabel);
    mainPanel.add(loginButton);
    mainPanel.add(MusicButton);
    mainPanel.add(backgroundButton);
    mainPanel.add(ruleButton);
    mainPanel.add(backButton);
    mainLabel.setLocation(WIDTH / 2 - BUTTON_WIDTH / 2, HEIGHT / 8 - 50);
    loginButton.setLocation(WIDTH / 2 - BUTTON_WIDTH / 2, HEIGHT / 8 - 50 + BUTTON_HEIGHT + 35);
    MusicButton.setLocation(WIDTH / 2 - BUTTON_WIDTH / 2, HEIGHT / 8 - 50 + 2* (BUTTON_HEIGHT + 35));
    backgroundButton.setLocation(WIDTH / 2 - BUTTON_WIDTH / 2, HEIGHT / 8 - 50 + 3* (BUTTON_HEIGHT + 35));
    ruleButton.setLocation(WIDTH / 2 - BUTTON_WIDTH / 2, HEIGHT / 8 - 50 + 4 * (BUTTON_HEIGHT + 35));
    backButton.setLocation(WIDTH / 2 - BUTTON_WIDTH / 2, HEIGHT / 8 - 50 + 5 * (BUTTON_HEIGHT + 35));

    if (mainGameFrame != null) {
      updateLabel(mainGameFrame.getUser().getName());
    } if (chessGameFrame != null){
      updateLabel(chessGameFrame.getUser().getName());
    }
  }
}
