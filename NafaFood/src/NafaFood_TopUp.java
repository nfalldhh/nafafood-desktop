import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class NafaFood_TopUp extends JFrame {

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    JLabel judul = new JLabel(new ImageIcon("img/topup.png"));
    JLabel nafabank = new JLabel(new ImageIcon("img/nafabank.png"));

    JButton konfirmasi = new JButton("KONFIRMASI");
    JButton batal = new JButton("BATAL");

    JLabel pinTX = new JLabel("M-PIN");
    JLabel jumlahTX = new JLabel("JUMLAH");

    JPasswordField pin = new JPasswordField();
    JTextField jumlah = new JTextField();

    String nama, ID;

    NafaFood_TopUp(String ID, String nama) {
        super("Top Up - NafaPay");
        setSize(520, 620);
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setResizable(false);
        setVisible(true);
        this.nama = nama;
        this.ID = ID;
    }

    void Style() {
        getContentPane().setBackground(Color.white);
        getContentPane().setLayout(null);

        getContentPane().add(judul);
        judul.setBounds(161, 63, 179, 88);

        getContentPane().add(nafabank);
        nafabank.setBounds(157, 492, 186, 34);

        getContentPane().add(konfirmasi);
        konfirmasi.setBounds(102, 389, 143, 50);
        konfirmasi.setBackground(Color.decode("#A8DF65"));
        konfirmasi.setBorder(null);
        konfirmasi.setForeground(Color.white);

        getContentPane().add(batal);
        batal.setBounds(255, 389, 143, 50);
        batal.setBackground(Color.decode("#909090"));
        batal.setBorder(null);
        batal.setForeground(Color.white);

        getContentPane().add(pin);
        pin.setBounds(102, 223, 296, 50);
        pin.setBackground(Color.decode("#E5E5E5"));
        pin.setBorder(null);

        getContentPane().add(jumlah);
        jumlah.setBounds(102, 306, 296, 50);
        jumlah.setBackground(Color.decode("#E5E5E5"));
        jumlah.setBorder(null);

        getContentPane().add(pinTX);
        pinTX.setBounds(102, 203, 64, 14);
        pinTX.setFont(new Font("Montserrat", Font.PLAIN, 12));

        getContentPane().add(jumlahTX);
        jumlahTX.setBounds(102, 288, 64, 14);
        jumlahTX.setFont(new Font("Montserrat", Font.PLAIN, 12));
    }

    void AksiReaksi() {
        konfirmasi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pinn = new String(pin.getPassword());
                if (pinn.length() == 6) {
                    Koneksi conn = new Koneksi();
                    String sql = "SELECT * FROM tb_nafapay WHERE id_user = '" + ID + "'";
                    int topup = Integer.parseInt(jumlah.getText());

                    try {
                        conn.connect();
                        ResultSet rs = conn.getStatement().executeQuery(sql);

                        if (rs.next()) {
                            int Saldo = Integer.parseInt(rs.getString("saldo"));

                            Saldo += topup;

                            String sql_update = "UPDATE tb_nafapay SET saldo = '" + Saldo + "' WHERE id_user = '" + ID
                                    + "'";

                            try {
                                conn.connect();
                                conn.getStatement().executeUpdate(sql_update);
                                JOptionPane.showMessageDialog(null, "Terima Kasih!", "Konfirmasi",
                                        JOptionPane.INFORMATION_MESSAGE);
                                conn.closeConnection();
                            } catch (Exception err) {
                                System.out.println("Error : " + err);
                            }

                        }

                        NafaFood_Nafapay NN = new NafaFood_Nafapay(ID, nama);
                        NN.Style();
                        NN.AksiReaksi();
                        dispose();

                        rs.close();
                        conn.closeConnection();
                    } catch (Exception err) {
                        System.out.println("Error : " + err);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pin Harus berisikan 6 Digit", "Konfirmasi",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        batal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NafaFood_Nafapay NN = new NafaFood_Nafapay(ID, nama);
                NN.Style();
                NN.AksiReaksi();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        NafaFood_TopUp NT = new NafaFood_TopUp("1", "Naufal Alfadhil");
        NT.Style();
        NT.AksiReaksi();
    }

}
