import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToDoList extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton, deleteButton;

    public ToDoList() {
        // === Frame setup ===
        setTitle("📝 To-Do List");
        setSize(420, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(245, 245, 250));

        // === Title ===
        JLabel titleLabel = new JLabel("Your Tasks", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(60, 60, 90));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // === Input section ===
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBackground(new Color(245, 245, 250));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        taskField = new JTextField();
        taskField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        taskField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 200), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        addButton = new JButton("Add");
        styleButton(addButton, new Color(70, 130, 180), Color.WHITE);

        inputPanel.add(taskField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.NORTH);

        // === Task List ===
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        taskList.setSelectionBackground(new Color(173, 216, 230));
        taskList.setSelectionForeground(Color.BLACK);
        taskList.setBackground(new Color(250, 250, 255));
        taskList.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        // === Bottom panel with delete button ===
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(245, 245, 250));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        deleteButton = new JButton("Delete");
        styleButton(deleteButton, new Color(220, 20, 60), Color.WHITE);
        bottomPanel.add(deleteButton, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

        // === Add button functionality ===
        addButton.addActionListener(e -> {
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                taskListModel.addElement("• " + task);
                taskField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a task!");
            }
        });

        // === Delete button functionality ===
        deleteButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskListModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(this, "Select a task to delete!");
            }
        });
    }

    // === Custom Button Styling ===
    private void styleButton(JButton button, Color bgColor, Color textColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ToDoList().setVisible(true);
        });
    }
}
