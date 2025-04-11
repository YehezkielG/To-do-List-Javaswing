package component;
import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
    public Header() {
        setOpaque(false);
        JLabel title = new JLabel("Aplikasi To Do List");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        add(title);
    }
}
