import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class NafaFood_Registration extends JFrame {

    JTextField usernameField = new JTextField();
    JTextField emailField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton submitButton = new JButton();
    JButton loginButton = new JButton("Sudah Punya Akun, Daftar!");
    JLabel logo = new JLabel();

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    NafaFood_Registration() {
        super("Registrasi - NafaFood");
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
        logo.setBounds(265, 144, 252, 66);

        getContentPane().add(usernameField);
        usernameField.setBounds(265, 260, 251, 36);
        usernameField.setBorder(null);
        usernameField.setBackground(Color.decode("#FFFFFF"));
        usernameField.setBorder(new TitledBorder(new LineBorder(Color.decode("#FFFFFF")), "Nama Lengkap"));

        getContentPane().add(emailField);
        emailField.setBounds(265, 306, 251, 36);
        emailField.setBorder(null);
        emailField.setBackground(Color.decode("#FFFFFF"));
        emailField.setBorder(new TitledBorder(new LineBorder(Color.decode("#FFFFFF")), "Email"));

        getContentPane().add(passwordField);
        passwordField.setBounds(265, 352, 203, 36);
        passwordField.setBorder(null);
        passwordField.setBackground(Color.decode("#FFFFFF"));
        passwordField.setBorder(new TitledBorder(new LineBorder(Color.decode("#FFFFFF")), "Password"));

        getContentPane().add(submitButton);
        submitButton.setBounds(479, 352, 38, 36);
        submitButton.setBackground(Color.decode("#FFFDE1"));
        submitButton.setIcon(new ImageIcon("./img/login.png"));
        submitButton.setBorder(null);

        getContentPane().add(loginButton);
        loginButton.setBounds(310, 404, 161, 15);
        loginButton.setForeground(Color.white);
        loginButton.setBackground(null);
        loginButton.setBorder(null);

        setVisible(true);
    }

    void AksiReaksi() {
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = usernameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // koneksi
                Koneksi conn = new Koneksi();

                if ((!nama.isEmpty()) && (!email.isEmpty()) && (!password.isEmpty())) {
                    try {
                        conn.connect();
                        String sql = "INSERT INTO tb_user (nama, email, password, hak_akses) VALUES ('" + nama + "', '"
                                + email + "', '" + password + "', 'Pelanggan')";

                        conn.getStatement().executeUpdate(sql);

                        try {
                            ResultSet rs = conn.getStatement()
                                    .executeQuery("SELECT * FROM tb_user WHERE email ='" + email + "'");
                            if (rs.next()) {
                                conn.getStatement().executeUpdate("INSERT INTO tb_nafapay (id_user, saldo) VALUES ('"
                                        + rs.getString("id_user") + "', '0')");
                            }

                            rs.close();
                        } catch (Exception err) {
                            System.out.println("Error : " + err);
                        }

                        JOptionPane.showMessageDialog(null, "Terima Kasih! Silahkan Login");

                        NafaFood_Login NH = new NafaFood_Login();
                        NH.Style();
                        NH.AksiReaksi();
                        dispose();

                        conn.closeConnection();
                    } catch (SQLException err) {
                        System.out.println("Error: " + err);
                    } catch (Exception err) {
                        System.out.println("Error: " + err);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Silahkan Lengkapi Data!");
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NafaFood_Login NL = new NafaFood_Login();
                NL.Style();
                NL.AksiReaksi();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        NafaFood_Registration NFLogin = new NafaFood_Registration();
        NFLogin.Style();
        NFLogin.AksiReaksi();
    }

}