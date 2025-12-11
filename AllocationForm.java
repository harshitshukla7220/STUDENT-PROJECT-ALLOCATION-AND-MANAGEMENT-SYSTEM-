AllocationForm.java

import javax.swing.*;
import java.awt.*;

public class AllocationForm extends JFrame {
    public AllocationForm(AllocationManager manager) {
        setTitle("Allocate Project");
        setSize(450, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(25, 118, 210),
                        getWidth(), getHeight(), new Color(13, 71, 161));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        background.setLayout(new GridBagLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(255, 255, 255, 235));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Student Name:");
        JTextField nameField = new JTextField(15);
        JLabel projectLabel = new JLabel("Project Title:");
        JTextField projectField = new JTextField(15);

        JButton saveBtn = new JButton("💾 Save Allocation");
        saveBtn.setBackground(new Color(46, 204, 113));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveBtn.setFocusPainted(false);

        saveBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String project = projectField.getText().trim();

            if (name.isEmpty() || project.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill both fields!");
                return;
            }

            manager.addAllocation(name, project);
            JOptionPane.showMessageDialog(this, "✅ Project allocated successfully!");
            dispose();
        });

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(nameLabel, gbc);
        gbc.gridx = 1; formPanel.add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(projectLabel, gbc);
        gbc.gridx = 1; formPanel.add(projectField, gbc);
        gbc.gridx = 1; gbc.gridy = 2; formPanel.add(saveBtn, gbc);

        background.add(formPanel);
        add(background);
        setVisible(true);
    }
}
