package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class TitleBar {
    static Point winCoords = null;
    public static JPanel createPanel(JFrame frame){
        final JPanel titleBar = new JPanel();
        titleBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        titleBar.setLayout(new BoxLayout(titleBar,BoxLayout.LINE_AXIS));

        final JButton minimizeButton = UIHelper.createTransparentButton("--", () -> frame.setState(JFrame.ICONIFIED));
        final JButton exitButton = UIHelper.createTransparentButton("x", () -> System.exit(0));

        titleBar.setBackground(MainWindow.COLORS[0]);
        titleBar.setPreferredSize(new Dimension(800,25));
        exitButton.setPreferredSize(new Dimension(40,25));
        minimizeButton.setPreferredSize(new Dimension(40,25));

        titleBar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                winCoords = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                winCoords = null;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        titleBar.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                final Point currLoc = e.getLocationOnScreen();
                frame.setLocation(currLoc.x - winCoords.x, currLoc.y - winCoords.y);
            }
            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        titleBar.add(exitButton);
        titleBar.add(minimizeButton);
        return titleBar;
    }
}
