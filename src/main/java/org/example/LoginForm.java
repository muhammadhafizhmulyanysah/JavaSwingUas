package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JFrame {
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegister;
    private JPanel LoginPanel;

    public LoginForm(JFrame parent) {
        setTitle("Login");
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(450, 474));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String password = String.valueOf(txtPassword.getPassword());

                User user = getAuthenticatedUser (email, password); // Ambil user yang terautentikasi

                if (user != null) {
                    new  AkunForm(user);

                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Invalid email or password", "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationForm registrationForm = new RegistrationForm(LoginForm.this);
                registrationForm.setVisible(true);
                new LoginForm(null);
            }
        });
        setVisible(true);
    }

    private User getAuthenticatedUser (String email, String password) {
        User user = null;

        final String URL = "jdbc:mysql://localhost:3306/aplikasi_mahasiswa";
        final String USER = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.nama = resultSet.getString("nama");
                user.email = resultSet.getString("email");
                user.password = resultSet.getString("password");
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args) {
        new LoginForm(null);
    }
}