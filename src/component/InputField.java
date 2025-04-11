package component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import task.TaskBase;
import task.TaskManager;
import task.type.Daily;
import task.type.Priority;
import task.type.Regular;

import java.awt.*;
import java.lang.reflect.Method;

import util.FileHandler;

public class InputField extends JPanel {
    public InputField() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        String[] taskTypes = { "Tugas Biasa", "Tugas Prioritas", "Tugas Rutin" };
        JComboBox<String> typeCombo = new JComboBox<>(taskTypes);
        typeCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        typeCombo.setBorder(new EmptyBorder(5, 8, 5, 8));
        typeCombo.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        typeCombo.setMaximumSize(new Dimension(250, 35));

        JTextField inputTask = new JTextField(20);
        inputTask.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        inputTask.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputTask.setBackground(Color.decode("#C7C8CC"));
        inputTask.setForeground(Color.decode("#282828"));
        inputTask.setFont(new Font("Segoe UI", Font.BOLD, 14));
        inputTask.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 1),
                BorderFactory.createEmptyBorder(3, 3, 3, 3)));
        inputTask.setMargin(new Insets(0, 10, 0, 0));

        ImageIcon icon = new ImageIcon(getClass().getResource("/icon/add.png"));
        Image img = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);
        JButton addBtn = new JButton(resizedIcon);
        addBtn.setContentAreaFilled(false);
        addBtn.setBorderPainted(false);
        addBtn.setFocusPainted(false);
        addBtn.setOpaque(false);
        addBtn.setMargin(new Insets(0, 5, 0, 0));

        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

        row.setOpaque(false);
        row.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 1),
                BorderFactory.createEmptyBorder(0, 0, 15, 0)));
        row.add(typeCombo);
        row.add(inputTask);
        row.add(addBtn);
        add(row);
        //
        TaskManager manager = new TaskManager();
        FileHandler fileHandler = new FileHandler();
        addBtn.addActionListener(e -> {
            String title = inputTask.getText();
            String type = (String) typeCombo.getSelectedItem();
            if (!title.isEmpty()) {
                TaskBase task;
                switch (type) {
                    case "Tugas Rutin":
                        task = new Daily(title);
                        break;
                    case "Tugas Prioritas":
                        task = new Priority(title);
                        break;
                    default:
                        task = new Regular(title);
                }
                manager.addTask(task);
                inputTask.setText("");
            }
            Container parent = getParent();
            try {
                Class<?> innerAppClass = Class.forName("App$InnerApp");
                if (parent != null && innerAppClass.isInstance(parent)) {
                    Method refreshMethod = innerAppClass.getMethod("refreshTaskList");
                    refreshMethod.invoke(parent);
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        });
    }
}
