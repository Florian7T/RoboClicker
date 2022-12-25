package window;

import javax.swing.*;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class CustomComboBoxUI extends BasicComboBoxUI {
    public static ComboBoxUI createUI(JComponent c) {
        return new CustomComboBoxUI();
    }

    @Override
    protected JButton createArrowButton() {
        return new BasicArrowButton(
                BasicArrowButton.SOUTH,
                MainWindow.COLORS[3], Color.blue,
                Color.WHITE, Color.blue);
    }
}
