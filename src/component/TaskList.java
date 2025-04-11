package component;

import java.util.ArrayList;

import javax.swing.*;

import task.TaskBase;
import task.TaskManager;
import task.type.Daily;
import task.type.Priority;
import task.type.Regular;

import java.awt.*;
import java.lang.reflect.Method;

public class TaskList extends JPanel {
    ArrayList<TaskBase> tasks = new ArrayList<>();

    public TaskList(ArrayList<TaskBase> tasks) {
        this.tasks = tasks;
        setupLayout();
    }

    void refreshTasks() {
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
    }

    private void setupLayout() {
        setOpaque(false);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(15, 0, 0, 0)));
        removeAll();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        for (TaskBase task : tasks) {
            JPanel taskPanel = new JPanel(new BorderLayout());
            taskPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            taskPanel.setBackground(Color.decode(getColorForTask(task)));
            taskPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
                    BorderFactory.createEmptyBorder(5, 3, 5, 3)
            ));
        
            JLabel typeLabel;
            switch (task.getType()) {
                case "Daily":
                    typeLabel = new JLabel("[Rutin]");
                    break;
                case "Priority":
                    typeLabel = new JLabel("[Prioritas]");
                    break;
                default:
                    typeLabel = new JLabel("[Biasa]");
            }
            typeLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            typeLabel.setForeground(Color.DARK_GRAY);
        
            JCheckBox checkBox = new JCheckBox();
            checkBox.setSelected(task.isCompleted());
            checkBox.setOpaque(false);
            checkBox.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0, 0, 0), 1, true),
                    BorderFactory.createEmptyBorder(8, 0, 0, 0)
            ));
        
            JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            leftPanel.setOpaque(false);
            leftPanel.add(typeLabel);
            leftPanel.add(checkBox);
        
            JLabel titleLabel = new JLabel(task.getName());
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
            ImageIcon icon = new ImageIcon(getClass().getResource("/icon/cancel.png"));
            Image img = icon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(img);
            JButton deleteButton = new JButton(resizedIcon);
            deleteButton.setFocusPainted(false);
            deleteButton.setContentAreaFilled(false);
            deleteButton.setBorderPainted(false);
            deleteButton.setOpaque(false);
            deleteButton.setToolTipText("Hapus tugas");
        
            deleteButton.addActionListener(e -> {
                int index = tasks.indexOf(task);
                if (index != -1) {
                    new TaskManager().removeTask(index);
                }
                refreshTasks();
            });
        
            taskPanel.add(leftPanel, BorderLayout.WEST);
            taskPanel.add(titleLabel, BorderLayout.CENTER);
            taskPanel.add(deleteButton, BorderLayout.EAST);
        
            add(Box.createVerticalStrut(8));
            add(taskPanel);
        
            if (!checkBox.isSelected()) {
                checkBox.addItemListener(e -> {
                    int index = tasks.indexOf(task);
                    if (index != -1) {
                        new TaskManager().setComplated(index);
                    }
                    refreshTasks();
                });
            } else {
                checkBox.addItemListener(e -> {
                    checkBox.setSelected(true); 
                });
            }
        }
        
        revalidate();
        repaint();
    }

    public void refreshTasks(ArrayList<TaskBase> updatedTasks) {
        this.tasks = updatedTasks;
        setupLayout();
        revalidate();
        repaint();
    }

    private String getColorForTask(TaskBase task) {
        if (task instanceof Regular) {
            return "#EAEAEA";
        } else if (task instanceof Daily) {
            return "#BAD7E9";
        } else if (task instanceof Priority) {
            return "#EF6262";
        } else {
            return "#FF6868";
        }
    }
}
