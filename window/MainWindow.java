package window;

import robot.Instruction;
import robot.InstructionSet;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class MainWindow {
    public static final Color[] COLORS = new Color[]{
            new Color(3,30,60),
            new Color(3,54,73),
            new Color(3,65,85),
            new Color(3,80,100),
            new Color(90,160,230)
    };

    public static MainWindow instance;
    public static final JFrame frame = new JFrame("RoboClicker");
    public static boolean saved = true;
    public static Robot robot;

    public static final Dimension contentDimension = new Dimension(600,470);
    public InstructionSet currentSet = null;
    public static boolean isWindows;
    public static JPanel content;
    public MainWindow() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, AWTException {
        instance = this;
        isWindows = System.getProperty("os.name").contains("Win");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        robot = new Robot();

        UIManager.put("ComboBox.background", new ColorUIResource(COLORS[3]));
        UIManager.put("ComboBox.foreground", new ColorUIResource(Color.WHITE));
        UIManager.put("ComboBox.buttonBackround", new ColorUIResource(Color.YELLOW));
        UIManager.put("ComboBox.buttonDarkShadow", new ColorUIResource(Color.red));
        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(COLORS[2]));
        UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.white));


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit on close
        frame.setSize(800,500);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        final Container pane = frame.getContentPane();
        final JPanel titleBar = TitleBar.createPanel(frame);
        content = UIHelper.createTransparentPanel();
        final JPanel buttonContainer = UIHelper.createTransparentPanel();

        frame.add(titleBar);
        frame.add(content);


        buttonContainer.setOpaque(true);
        buttonContainer.setBackground(COLORS[0]);
        pane.setBackground(COLORS[1]);
        frame.setUndecorated(true);

        final FlowLayout flowLayout = new FlowLayout(FlowLayout.LEADING);
        flowLayout.setHgap(0);
        flowLayout.setVgap(0);
        content.setLayout(flowLayout);

        buttonContainer.setLayout(new BoxLayout(buttonContainer,BoxLayout.Y_AXIS));
        JButton btn = UIHelper.createSideButton("Make Instructionset", () -> changePanel(InstructionSetMakerPanel.createPanel(currentSet)));
        JButton btn2 = UIHelper.createSideButton("Run Instructionset", () -> changePanel(new JPanel()));
        buttonContainer.add(btn);
        buttonContainer.add(btn2);
        buttonContainer.setPreferredSize(new Dimension(200,500));
        content.add(buttonContainer);
        content.add(InstructionSetMakerPanel.createPanel(currentSet));

        final FlowLayout flowLayout2 = new FlowLayout(FlowLayout.LEADING);
        flowLayout2.setHgap(0);
        flowLayout2.setVgap(0);
        frame.setLayout(flowLayout2);




        frame.setVisible(true);

        //while (true);
    }

    public void setInstructionSet(InstructionSet instructionSet){
        this.currentSet = instructionSet;
    }

    private void changePanel(JPanel panel){
        content.remove(1);
        content.add(panel);
        content.updateUI();
    }
}
