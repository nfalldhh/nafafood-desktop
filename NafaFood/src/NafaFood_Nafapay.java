import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.sql.ResultSet;

import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;

class NafaFood_Nafapay extends JFrame {

    JLabel logo = new JLabel();
    JLabel balance = new JLabel("Rp 150.000");
    JButton topopButton = new JButton("Isi Saldo");
    JButton backButton = new JButton("Kembali");
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    NumberFormat nf = NumberFormat.getInstance(new Locale("id", "ID"));

    String nama, ID;

    NafaFood_Nafapay(String ID, String nama) {
        super("NafaPay - Online Wallet");
        setSize(800, 600);
        setLocation(100, 100);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.nama = nama;
        this.ID = ID;
    }

    void Style() {
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.decode("#A8DF65"));

        getContentPane().add(logo);
        logo.setBounds(30, 23, 146, 40);
        logo.setIcon(new ImageIcon("./img/nafapay_logo.png"));

        getSaldo();

        getContentPane().add(topopButton);
        topopButton.setBounds(229, 314, 153, 34);
        topopButton.setFont(new Font("montserrat", Font.PLAIN, 18));
        topopButton.setBackground(Color.decode("#FFFDE1"));
        topopButton.setBorder(null);

        getContentPane().add(backButton);
        backButton.setBounds(398, 314, 153, 34);
        backButton.setFont(new Font("montserrat", Font.PLAIN, 18));
        backButton.setBackground(Color.decode("#FFFDE1"));
        backButton.setBorder(null);

        setVisible(true);
    }

    void getSaldo() {
        Koneksi conn = new Koneksi();
        try {
            conn.connect();
            ResultSet rs = conn.getStatement().executeQuery("SELECT * FROM tb_nafapay WHERE id_user = '" + ID + "'");

            if (rs.next()) {
                getContentPane().add(balance);
                balance.setBounds(235, 213, 400, 59);
                balance.setFont(new Font("montserrat", Font.PLAIN, 48));
                balance.setForeground(Color.decode("#FFFDE1"));
                int saldo = Integer.parseInt(rs.getString("saldo"));
                balance.setText("Rp. " + nf.format(saldo) + ",00");
            }

            conn.closeConnection();
            rs.close();
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    void AksiReaksi() {
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NafaFood_Home NH = new NafaFood_Home(ID, nama);
                NH.Style();
                NH.AksiReaksi();
                dispose();
            }
        });
        topopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NafaFood_TopUp NT = new NafaFood_TopUp(ID, nama);
                NT.Style();
                NT.AksiReaksi();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        NafaFood_Nafapay NFNP = new NafaFood_Nafapay("1", "Naufal Alfadhil");
        NFNP.Style();
        NFNP.AksiReaksi();
    }
}
