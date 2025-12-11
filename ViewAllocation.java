ViewAllocation.java

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewAllocations extends JFrame {
    private AllocationManager manager;
    private DefaultTableModel model;

    public ViewAllocations(AllocationManager manager) {
        this.manager = manager;

        setTitle("View All Project Allocations");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(63, 81, 181),
                        getWidth(), getHeight(), new Color(92, 107, 192));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        background.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("📋 Project Allocations", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        background.add(title, BorderLayout.NORTH);

        String[] columns = {"Student Name", "Project Title", "Status"};
        model = new DefaultTableModel(columns, 0);
        loadTableData();

        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.setRowHeight(25);
        table.setSelectionBackground(new Color(30, 136, 229));
        table.setSelectionForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        background.add(scrollPane, BorderLayout.CENTER);

        JButton toggleBtn = new JButton("🔄 Toggle Status");
        toggleBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        toggleBtn.setBackground(new Color(255, 193, 7));
        toggleBtn.setFocusPainted(false);

        toggleBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a record!");
                return;
            }
            String current = (String) model.getValueAt(selectedRow, 2);
            String newStatus = current.equals("Submitted") ? "Pending" : "Submitted";
            model.setValueAt(newStatus, selectedRow, 2);
            manager.updateStatus(selectedRow, newStatus);
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(toggleBtn);
        background.add(bottomPanel, BorderLayout.SOUTH);

        add(background);
        setVisible(true);
    }

    private void loadTableData() {
        ArrayList<Student> students = manager.getAllocations();
        model.setRowCount(0);
        for (Student s : students) {
            model.addRow(new Object[]{s.getName(), s.getProject(), s.getStatus()});
        }
    }
}
