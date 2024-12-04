package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JDialog {
    private JPanel MainPanel;
    private JButton btnData;
    private JButton btnAkun;

    public MainForm(JFrame parent) {
        super(parent);
        setTitle("Home Page");
        setContentPane(MainPanel);
        setMinimumSize(new Dimension(450, 474));
        setLocationRelativeTo(parent);

        btnAkun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainForm mainForm = new MainForm(parent);
                mainForm.setVisible(true);
                dispose();
            }
        });


        btnData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainScreen mainScreen = new MainScreen();
                mainScreen.setVisible(true);
                dispose();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        new MainForm(frame);
    }
}