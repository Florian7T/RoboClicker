package window;

import robot.InstructionSet;
import robot.instructions.MouseClickInstruction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class InstructionSetMakerPanel {

    static MainWindow mw;

    private static boolean timedOption = false;
    private static boolean holdOption = false;

    private static final int[] toClick = {0};

    public static JPanel createPanel(InstructionSet instructionSet){
        mw =  MainWindow.instance;
        final JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600,500));
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.setOpaque(false);

        final JPanel leftPanel = new JPanel();
        final JPanel midPanel = new JPanel();
        final JPanel setPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(150, MainWindow.contentDimension.height));
        leftPanel.setOpaque(true);
        leftPanel.setBackground(MainWindow.COLORS[1]);

        midPanel.setPreferredSize(new Dimension(125,MainWindow.contentDimension.height));
        midPanel.setOpaque(true);

        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEADING);
        flowLayout.setVgap(0);
        flowLayout.setHgap(0);

        //midPanel.setLayout(flowLayout);
        final JButton newSetButton = UIHelper.createSideButton("Create new Instructionset", () -> MainWindow.instance.setInstructionSet(new InstructionSet()));
        final JButton createButton = UIHelper.createSideButton("Add new Instruction", () -> { midPanel.setVisible(true); panel.updateUI();});
        newSetButton.setPreferredSize(new Dimension(leftPanel.getPreferredSize().width,50));
        createButton.setPreferredSize(new Dimension(leftPanel.getPreferredSize().width,50));


        final Runnable[] runnables = new Runnable[]{
                () -> {clearPanel(midPanel,"no");clearPanel(midPanel,"no");}, // Clear Panel
                () -> midPanel.updateUI(),
                () -> { // Click Mouse
                    JPanel buttonOption = new JPanel(new FlowLayout(FlowLayout.LEADING));
                    JComboBox<String> buttonBox = UIHelper.createComboBox(new String[]{"Left","Middle","Right","Browser Back","Browser Forward"},toClick);

                    buttonOption.setOpaque(false);
                    buttonOption.add(UIHelper.whiteLabel("Button: "));
                    buttonOption.add(buttonBox);
                    buttonOption.setName("nodie");
                    midPanel.add(buttonOption);
                    JPanel timedButton;
                    timedOption = true;
                    timedButton = UIHelper.createLeftToggleButton("Timed Press: ", () -> {
                        clearPanel(midPanel,"nodie");
                        final FlowLayout inpFlowLayout = new FlowLayout(FlowLayout.LEFT);
                        inpFlowLayout.setVgap(0);
                        inpFlowLayout.setHgap(2);
                        JPanel inputPanel = new JPanel(inpFlowLayout);
                        inputPanel.setForeground(Color.WHITE);
                        inputPanel.setBackground(MainWindow.COLORS[3]);
                        JTextField timeInput = new JTextField("0");
                        timeInput.setPreferredSize(new Dimension(60,25));
                        timeInput.setBorder(null);
                        timeInput.addKeyListener(new KeyListener() {
                            @Override public void keyTyped(KeyEvent e) { if (!Character.isDigit(e.getKeyChar()) || timeInput.getText().length()>7) e.consume(); }
                            @Override public void keyPressed(KeyEvent e) { }
                            @Override public void keyReleased(KeyEvent e) { }
                        });
                        timeInput.setOpaque(false);
                        timeInput.setForeground(Color.WHITE);
                        inputPanel.add(UIHelper.whiteLabel("Time: "));
                        inputPanel.add(timeInput);
                        inputPanel.add(UIHelper.whiteLabel("ms"));
                        midPanel.add(inputPanel);
                    }, () -> {
                        clearPanel(midPanel,"nodie");
                        timedOption = false;
                        holdOption = false;
                        JPanel holdButton = UIHelper.createLeftToggleButton("Hold button: ", () -> holdOption = true, () -> holdOption = false);
                        midPanel.add(holdButton);
                    });
                    timedButton.setName("nodie");
                    midPanel.add(timedButton);
                    timedOption = false;
                    holdOption = false;
                    JPanel holdButton = UIHelper.createLeftToggleButton("Hold button: ", () -> holdOption = true, () -> holdOption = false);
                    holdButton.setName("nodie2");
                    midPanel.add(holdButton);


                },
                () -> {}, // Move Mouse
                () -> {}, // Press Key
                () -> {}, // Run Command
                () -> {}  // Sleep
        };
        final JComboBox<String> cb = UIHelper.createComboBox(
                new String[]{"Click Mouse", "Move Mouse", "Press Key", "Run Command", "Sleep"},
                runnables
        );
        final JButton addButton = UIHelper.createSideButton("Add to Instructionset",()->{
            switch (cb.getSelectedIndex()){
                case 0:
                    MouseClickInstruction clickInstruction;
                    if(timedOption){
                        //clickInstruction = new MouseClickInstruction()
                    }
                    break;
            }
        });
        cb.setName("no");
        //cb.setBackground(MainWindow.COLORS[3]);
        leftPanel.add(createButton);
        leftPanel.setLayout(flowLayout);
        midPanel.add(cb);
        runnables[0].run();
        runnables[2].run();
        //runnables[0].run();

        midPanel.setBackground(MainWindow.COLORS[2]);

        setPanel.setPreferredSize(new Dimension(325,MainWindow.contentDimension.height));
        setPanel.setBackground(MainWindow.COLORS[3]);

        panel.add(leftPanel);
        panel.add(midPanel);
        midPanel.setVisible(false);
        panel.add(setPanel);
        panel.setVisible(true);
        return panel;
    }

    private static void clearPanel(JPanel panel, String name){
        int removed = 0;
        for (int j = 0; j < panel.getComponentCount(); j++) {
            //System.out.println(!((panel.getComponent(j - removed) instanceof JComboBox ||
            //        panel.getComponent(j - removed).getName() != null) && name.contains(panel.getComponent(j - removed).getName())) + " "+removed + " "+j+" "+panel.getComponent(j-removed));

            if(!((panel.getComponent(j - removed) instanceof JComboBox ||
                    panel.getComponent(j - removed).getName() != null) && name.contains(panel.getComponent(j - removed).getName()))){
                panel.remove(j-removed);
                removed++;
            }

        }
    }

}
