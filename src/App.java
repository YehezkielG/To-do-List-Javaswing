import component.Header;
import component.InputField;
import component.TaskList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import util.FileHandler;

public class App {
    public static class InnerApp extends JPanel {
        private TaskList taskList;
        FileHandler fileHandler = new FileHandler();

        public InnerApp() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(30, 30, 30, 30));
            setBackground(Color.decode("#FAF7F0"));
            add(new Header());
            add(new InputField());
            this.taskList = new TaskList(fileHandler.loadTasks());
            add(this.taskList);
        }

        public void refreshTaskList() {
            this.taskList.refreshTasks(fileHandler.loadTasks());
        }
    }

    public static void main(String[] args) {
        InnerApp inner_app = new InnerApp();
        JScrollPane scrollPane = new JScrollPane(inner_app);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        new Window(scrollPane);
    }
}