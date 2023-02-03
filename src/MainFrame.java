import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame implements ActionListener, KeyListener {
    JMenuBar menuBar;
    JMenu menuMain;
    JMenuItem menuStart;
    JMenuItem menuQuit;

    JButton buttonStart;
    JButton buttonShow;
    JButton buttonDisplay;
    JButton buttonExit;
    MazePanel playPanel;
    WelcomeFrame welcomeLabel;
    SizeInput sizeInput;

    private int girlX = 0;
    private int girlY = 1;

    int rowSize;
    int colSize;

    public static void main(String[] args) {
        MainFrame mazewindow = new MainFrame();
        mazewindow.setVisible(true);
    }

    public MainFrame() {//主窗口设置
        this.setTitle("迷宫");
        buttonStart = new JButton("开始游戏");
        buttonShow = new JButton("展示");
        buttonDisplay = new JButton("时间动画演示");
        buttonExit = new JButton("退出");
        welcomeLabel = new WelcomeFrame();
        sizeInput = new SizeInput();

        //按钮添加监听
        buttonStart.addActionListener(this);
        buttonShow.addActionListener(this);
        buttonDisplay.addActionListener(this);
        buttonExit.addActionListener(this);

        welcomeLabel.setBounds(0, 0, 800, 700);
        buttonStart.setBounds(850, 50, 100, 100);
        buttonShow.setBounds(850, 200, 100, 100);
        buttonDisplay.setBounds(850, 350, 100, 100);
        buttonExit.setBounds(850, 500, 100, 100);
        this.add(welcomeLabel);
        this.add(buttonStart);
        this.add(buttonShow);
        this.add(buttonDisplay);
        this.add(buttonExit);

        menuBar = new JMenuBar();
        menuMain = new JMenu("选项");
        menuStart = new JMenuItem("开始游戏");
        menuQuit = new JMenuItem("退出");


        menuStart.addActionListener(this);
        menuQuit.addActionListener(this);

        this.setJMenuBar(menuBar);
        menuBar.add(menuMain);
        menuMain.add(menuStart);
        menuMain.add(menuQuit);

        this.setBounds(300, 100, 1000, 775);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) { //菜单栏监听
        if (e.getSource() == menuStart) {           //开始游戏，未完善
            this.remove(welcomeLabel);
        } else if (e.getSource() == menuQuit) {      //退出
            System.exit(0);
        } else if (e.getSource() == buttonStart) {
            //对话框显示
            sizeInput.setModal(true);
            sizeInput.setVisible(true);

            this.rowSize = sizeInput.getRow();
            this.colSize = sizeInput.getCol();

            if (this.rowSize % 2 == 0)
                this.rowSize--;
            if (this.colSize % 2 == 0)
                this.colSize--;

            //游戏面板初始化
            playPanel = new MazePanel(this.rowSize, this.colSize);

            playPanel.setBounds(0, 0, 800, 700);
            this.add(playPanel);
            this.validate();
            this.repaint();

            //键盘与动作监听冲突排除
            this.setFocusable(true);
            this.requestFocus();
            this.addKeyListener(this);
            this.remove(welcomeLabel);
        } else if (e.getSource() == buttonShow) {

        } else if (e.getSource() == buttonDisplay) {

        } else {
            System.exit(0);
        }
        this.setFocusable(true);
        this.requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println(e.getKeyChar());
        switch (e.getKeyChar()) {
            case 'a':
                if (girlY > 0) {
                    girlY--;
                    if (playPanel.mazeStuff[girlX][girlY].stuffType == 1) {
                        playPanel.mazeStuff[girlX][girlY].change(0);
                        playPanel.mazeStuff[girlX][girlY + 1].change(1);
                    } else {
                        girlY++;
                        return;
                    }
                }
                break;
            case 's':
                if (girlX < rowSize - 2) {
                    girlX++;
                    if (playPanel.mazeStuff[girlX][girlY].stuffType == 1) {
                        playPanel.mazeStuff[girlX][girlY].change(0);
                        playPanel.mazeStuff[girlX - 1][girlY].change(1);
                    } else {
                        girlX--;
                        return;
                    }
                }
                break;
            case 'd':
                if (girlY < colSize - 2) {
                    girlY++;
                    if (playPanel.mazeStuff[girlX][girlY].stuffType == 1) {
                        playPanel.mazeStuff[girlX][girlY].change(0);
                        playPanel.mazeStuff[girlX][girlY - 1].change(1);
                    } else {
                        girlY--;
                        return;
                    }
                }
                break;
            case 'w':
                if (girlX > 0) {
                    girlX--;
                    if (playPanel.mazeStuff[girlX][girlY].stuffType == 1) {
                        playPanel.mazeStuff[girlX][girlY].change(0);
                        playPanel.mazeStuff[girlX + 1][girlY].change(1);
                    } else {
                        girlX++;
                        return;
                    }
                }
                break;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
