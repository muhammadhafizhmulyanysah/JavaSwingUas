import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class RegistrationForm extends JDialog{
    private JTextField txtEmail;
    private JTextField txtnama;
    private JPasswordField txtPassword;
    private JButton btnRegister;
    private JButton btnSignin;
    private JPanel registerPanel;

    public RegistrationForm(JFrame parent){
        super(parent);
        setTitle("Create a new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        btnSignin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginForm(null);
            }
        });

        setVisible(true);
    }

    private void registerUser(){
        String nama = txtnama.getText();
        String email = txtEmail.getText();
        String password = String.valueOf(txtPassword.getPassword());

        if (nama.isEmpty() || email.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this, "please enter all fields","Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = addUserToDatabase(nama, email,password);
        if (user != null){
            JOptionPane.showMessageDialog(this,"Successful registration");
        }
        else {
            JOptionPane.showMessageDialog(this,"failed to register new user","try again",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public User user;
    private User addUserToDatabase(String nama, String email, String password){
        User user = null;
        final String URL = "jdbc:mysql://localhost:3306/aplikasi_mahasiswa";
        final String USER = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO users (nama, email, password)" + "values(?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,nama);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3, password);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.nama = nama;
                user.email = email;
                user.password = password;
            }

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args) {
        RegistrationForm frm = new RegistrationForm(null);
        User user = frm.user;
        if (user != null){
            System.out.println("Successful registration of: " + user.nama);
        }
        else {
            System.out.println("Registration canceled");
        }
    }
}
