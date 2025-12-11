AdminDashboard.java

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {
    private AllocationManager manager;

    public AdminDashboard() {
        manager = new AllocationManager();

        setTitle("Admin Dashboard");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Gradient background
        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(30, 136, 229),
                        getWidth(), getHeight(), new Color(21, 101, 192));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        background.setLayout(new GridBagLayout());

        JLabel heading = new JLabel("Admin Dashboard");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setForeground(Color.WHITE);

        JButton addAllocationBtn = createButton("➕ Allocate Project");
        JButton viewAllocationsBtn = createButton("📋 View Allocations");

        addAllocationBtn.addActionListener(e -> new AllocationForm(manager));
        viewAllocationsBtn.addActionListener(e -> new ViewAllocations(manager));

        JPanel card = new JPanel(new GridLayout(3, 1, 10, 10));
        card.setBackground(new Color(255, 255, 255, 220));
        card.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        card.add(heading);
        card.add(addAllocationBtn);
        card.add(viewAllocationsBtn);

        background.add(card);
        add(background);
        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(33, 150, 243));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 2));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(25, 118, 210));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(33, 150, 243));
            }
        });
        return btn;
    }
}
