package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AkunForm extends JFrame {
    private JPanel akunPanel;
    private JLabel lblAkunTitle;
    private JLabel lblNama;
    private JLabel lblEmail;
    private JButton btnKembali;

    public AkunForm(User user) {
        akunPanel = new JPanel();
        lblAkunTitle = new JLabel("Akun", SwingConstants.CENTER);
        lblNama = new JLabel("Nama: " + user.nama);
        lblEmail = new JLabel("Email: " + user.email);
        btnKembali = new JButton("Kembali");
        lblAkunTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblNama.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);

        akunPanel.setLayout(new GridLayout(4, 1));
        akunPanel.add(lblAkunTitle);
        akunPanel.add(lblNama);
        akunPanel.add(lblEmail);
        akunPanel.add(btnKembali);

        setTitle("Halaman Akun");
        setContentPane(akunPanel);
        setMinimumSize(new Dimension(450, 474));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainForm(null);
                dispose();
            }
        });

        setVisible(true);
    }
}