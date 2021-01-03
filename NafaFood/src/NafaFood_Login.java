import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class NafaFood_Login extends JFrame {

    JTextField emailField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton submitButton = new JButton();
    JButton registrasiButton = new JButton("Belum Punya Akun, Daftar!");
    JLabel logo = new JLabel();

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    NafaFood_Login() {
        super("Login - NafaFood");
        setSize(800, 600);
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    void Style() {
        getContentPane().setBackground(Color.decode("#A8DF65"));
        getContentPane().setLayout(null);

        getContentPane().add(logo);
        logo.setIcon(new ImageIcon("./img/logo.png"));
        logo.setBounds(265, 164, 252, 66);

        getContentPane().add(emailField);
        emailField.setBounds(265, 281, 251, 36);
        emailField.setBorder(null);
        emailField.setBackground(Color.decode("#FFFFFF"));
        emailField.setBorder(new TitledBorder(new LineBorder(Color.decode("#FFFFFF")), "Email"));

        getContentPane().add(passwordField);
        passwordField.setBounds(265, 327, 203, 36);
        passwordField.setBorder(null);
        passwordField.setBackground(Color.decode("#FFFFFF"));
        passwordField.setBorder(new TitledBorder(new LineBorder(Color.decode("#FFFFFF")), "Password"));

        getContentPane().add(submitButton);
        submitButton.setBounds(479, 327, 38, 36);
        submitButton.setBackground(Color.decode("#FFFDE1"));
        submitButton.setIcon(new ImageIcon("./img/login.png"));
        submitButton.setBorder(null);

        getContentPane().add(registrasiButton);
        registrasiButton.setBounds(310, 383, 161, 15);
        registrasiButton.setForeground(Color.white);
        registrasiButton.setBackground(null);
        registrasiButton.setBorder(null);

        setVisible(true);
    }

    void AksiReaksi() {
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // koneksi
                Koneksi conn = new Koneksi();
                String sql = "SELECT * FROM tb_user WHERE email='" + email + "' AND password='" + password + "'";

                try {
                    conn.connect();
                    ResultSet rs = conn.getStatement().executeQuery(sql);

                    if (rs.next()) {
                        if ((rs.getString("email").equals(email)) && (rs.getString("password").equals(password))) {
                            NafaFood_Home NH = new NafaFood_Home(rs.getString("id_user"), rs.getString("nama"));
                            NH.Style();
                            NH.AksiReaksi();
                            dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Login Salah", "Konfirmasi",
                                JOptionPane.INFORMATION_MESSAGE);
                    }

                    rs.close();
                    conn.closeConnection();
                } catch (SQLException err) {
                    System.out.println("Error: " + err);
                } catch (Exception err) {
                    System.out.println("Error: " + err);
                }
            }
        });

        registrasiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NafaFood_Registration NR = new NafaFood_Registration();
                NR.Style();
                NR.AksiReaksi();
                dispose();
            }
        });

    }

    public static void main(String[] args) {
        NafaFood_Login NFLogin = new NafaFood_Login();
        NFLogin.Style();
        NFLogin.AksiReaksi();
    }

}