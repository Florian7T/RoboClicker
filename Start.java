import window.MainWindow;

import javax.swing.*;
import java.awt.*;

public class Start {
    public static void main(String[] args){

        try {
            final MainWindow mw = new MainWindow();
        } catch (UnsupportedLookAndFeelException |
                ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                AWTException e) {
            e.printStackTrace();
        }
    }
}
