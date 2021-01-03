import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.Locale;

public class NafaFood_Checkout_Confirm extends JFrame {

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    JLabel judul = new JLabel(new ImageIcon("img/CHECKOUT.png"));

    JButton bayar = new JButton("BAYAR");
    JButton batal = new JButton("BATAL");

    JLabel totalharga = new JLabel("Rp 400.000");
    JLabel saldo = new JLabel("Saldo Kamu (Rp 560.000)");
    int jumSaldo;
    int hargaTotal = 0;
    String nama, ID;
    ArrayList cartList = new ArrayList();
    NumberFormat nf = NumberFormat.getInstance(new Locale("id", "ID"));

    NafaFood_Checkout_Confirm(String ID, String nama, ArrayList<String> array, int hargaTotal) {
        super("Top Up - NafaPay");
        setSize(520, 620);
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setResizable(false);
        setVisible(true);
        this.nama = nama;
        this.ID = ID;
        this.hargaTotal = hargaTotal;
        this.cartList = array;
    }

    void Style() {
        getContentPane().setBackground(Color.white);
        getContentPane().setLayout(null);

        getContentPane().add(judul);
        judul.setBounds(108, 90, 284, 59);

        getContentPane().add(bayar);
        bayar.setBounds(102, 451, 143, 50);
        bayar.setBackground(Color.decode("#A8DF65"));
        bayar.setBorder(null);
        bayar.setForeground(Color.white);

        getContentPane().add(batal);
        batal.setBounds(255, 451, 143, 50);
        batal.setBackground(Color.decode("#909090"));
        batal.setBorder(null);
        batal.setForeground(Color.white);

        getSaldo();
    }

    void getSaldo() {

        getContentPane().add(saldo);
        saldo.setBounds(145, 331, 251, 21);
        saldo.setFont(new Font("Montserrat", Font.PLAIN, 18));

        Koneksi conn = new Koneksi();
        String sql = "SELECT * FROM tb_nafapay WHERE id_user = '" + ID + "'";

        try {
            conn.connect();
            ResultSet rs = conn.getStatement().executeQuery(sql);

            if (rs.next()) {
                jumSaldo = Integer.parseInt(rs.getString("saldo"));
                if (jumSaldo < hargaTotal) {
                    saldo.setForeground(Color.RED);
                } else {
                    saldo.setForeground(Color.decode("#909090"));
                }
                saldo.setText("Saldo Kamu (Rp " + nf.format(jumSaldo) + ")");

            }

            conn.closeConnection();
            rs.close();
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }

        getContentPane().add(totalharga);
        totalharga.setBounds(131, 237, 289, 56);
        totalharga.setFont(new Font("Montserrat", Font.BOLD, 48));
        totalharga.setForeground(Color.decode("#A8DF65"));
        totalharga.setText("Rp " + nf.format(hargaTotal));
    }

    void AksiReaksi() {
        batal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NafaFood_Checkout NN = new NafaFood_Checkout(ID, nama, cartList);
                NN.Style();
                NN.AksiReaksi();
                dispose();
            }
        });

        bayar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jumSaldo < hargaTotal) {
                    JOptionPane.showMessageDialog(null, "Maaf! Saldo Kamu Tidak Cukup!");
                } else {
                    int sisa = jumSaldo - hargaTotal;

                    Koneksi conn = new Koneksi();

                    String sql = "UPDATE tb_nafapay SET saldo = '" + sisa + "' WHERE id_user = '" + ID + "'";

                    try {
                        conn.connect();
                        conn.getStatement().executeUpdate(sql);

                        NafaFood_Order order = new NafaFood_Order(ID, nama);
                        order.AksiReaksi();
                        order.komponevisual();
                        dispose();

                        conn.closeConnection();
                    } catch (Exception er) {
                        JOptionPane.showMessageDialog(null, "Something Wrong! Error : " + er);
                    }

                    JOptionPane.showMessageDialog(null, "Terima Kasih :)");
                }
            }
        });
    }

    // public static void main(String[] args) {
    // NafaFood_Checkout_Confirm NT = new NafaFood_Checkout_Confirm("1", "Naufal
    // Alfadhil", 50000);
    // NT.Style();
    // NT.AksiReaksi();
    // }

}
