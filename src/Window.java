import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Window extends JFrame{
    public Window(JScrollPane scrollpane){
        add(scrollpane);
        setTitle("To Do List App");
        setSize(800,1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        setVisible(true);
    }
}
