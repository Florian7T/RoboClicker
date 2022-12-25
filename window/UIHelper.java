package window;

import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalComboBoxButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Function;

public class UIHelper {
    public static Color ALPHA = new Color(0,0,0,0);

    public static JComboBox<String> createComboBox(String[] args, Runnable[] runnables){
        final JComboBox<String> box = new JComboBox<>(args);
        box.setFocusable(false);
        box.setEditable(true);
        box.setBorder(null);
        box.setUI(CustomComboBoxUI.createUI(box));
        final Component cbEditor = box.getEditor().getEditorComponent();
        cbEditor.setBackground(MainWindow.COLORS[3]);
        cbEditor.setForeground(Color.WHITE);
        cbEditor.setFocusable(false);

        box.setForeground(Color.WHITE);
        box.setPreferredSize(new Dimension(90,25));

        box.addActionListener(e -> {
            if(runnables != null){
                runnables[0].run();
                runnables[box.getSelectedIndex()+2].run();
                runnables[1].run();
            }
        });
        return box;
    }

    public static JComboBox<String> createComboBox(String[] args, int[] toSet){
        final JComboBox<String> box = new JComboBox<>(args);
        box.setFocusable(false);
        box.setEditable(true);
        box.setBorder(new EmptyBorder(0,10,0,0));
        ((JLabel)box.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
        box.setUI(CustomComboBoxUI.createUI(box));
        final Component cbEditor = box.getEditor().getEditorComponent();
        cbEditor.setBackground(MainWindow.COLORS[3]);
        cbEditor.setForeground(Color.WHITE);
        cbEditor.setFocusable(false);

        box.setForeground(Color.WHITE);
        box.setPreferredSize(new Dimension(90,25));

        box.addActionListener(e -> {
            toSet[0] = box.getSelectedIndex();
        });
        return box;
    }

    public static JPanel createTransparentPanel(){
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        return panel;
    }

    public static JButton createTransparentButton(String ctx, Runnable runnable){
        JButton button = new JButton(ctx);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);

        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                runnable.run();
            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBorderPainted(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBorderPainted(false);
            }
        });
        return button;
    }

    public static JButton createSideButton(String ctx, Runnable runnable){
        JButton button = new JButton(ctx);
        button.setMinimumSize(new Dimension(200,50));
        button.setMaximumSize(new Dimension(200,50));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getButton());runnable.run();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBorderPainted(false);
            }
        });
        return button;
    }

    public static JPanel createLeftToggleButton(String ctx, Runnable runnable, Runnable runnable2){
        JPanel headPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headPanel.setBackground(MainWindow.COLORS[2]);
        buttonPanel.setBackground(MainWindow.COLORS[2]);

        final JLabel buttonLabel = new JLabel(ctx);
        buttonLabel.setForeground(Color.WHITE);
        buttonPanel.add(buttonLabel);

        JButton button = new JButton("no");
        final boolean[] timedToggle = {false};
        button.setPreferredSize(new Dimension(40,25));
        button.setBackground(MainWindow.COLORS[3]);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setBorder(new EmptyBorder(0,0,0,0));
        //runnable2.run();
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                timedToggle[0] = !timedToggle[0];
                if(timedToggle[0]) {
                    runnable.run();
                    button.setText("yes");
                }
                else {
                    runnable2.run();
                    button.setText("no");
                }
                MainWindow.content.updateUI();
            }
            @Override public void mousePressed(MouseEvent e) { }
            @Override public void mouseReleased(MouseEvent e) { }
            @Override public void mouseEntered(MouseEvent e) { }
            @Override public void mouseExited(MouseEvent e) { }
        });
        buttonPanel.add(button);
        headPanel.add(buttonPanel);
        return headPanel;
    }

    public static JLabel whiteLabel(String ctx){
        final JLabel jLabel = new JLabel(ctx);
        jLabel.setForeground(Color.WHITE);
        return jLabel;
    }

    private static ListCellRenderer boxRenderer = new ListCellRenderer<String>() {

        private JList<? extends String> list;
        private final JLabel label = new JLabel() {
            @Override
            public void paintComponent(Graphics g) {
                // Check if parent's parent is the combobox or the dropdown
                int part = getParent().getParent() == list ? 0 : 1;
                label.setText(label.getText().split("\\|")[part]);
                super.paintComponent(g);
            }
        };

        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
            this.list = list;
            label.setText(value);
            label.setOpaque(true);
            if (isSelected) {
                label.setForeground(list.getSelectionForeground());
                label.setBackground(list.getSelectionBackground());
            } else {
                label.setForeground(list.getForeground());
                label.setBackground(list.getBackground());
            }
            return label;
        }
    };
}
