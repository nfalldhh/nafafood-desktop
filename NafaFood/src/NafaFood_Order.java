import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class NafaFood_Order extends JFrame {

    JLabel Jgambar = new JLabel(new ImageIcon("img/order.gif"));
    JLabel thank = new JLabel("TERIMA KASIH :)");
    JLabel pesan = new JLabel("PESENAN AKAN SEGERA DIANTAR");
    JButton kembali = new JButton("KEMBALI");
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    String ID, nama;

    NafaFood_Order(String ID, String nama) {
        setTitle("Thanks For Order :) - NafaFood");
        setSize(800, 600);
        setResizable(false);
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ID = ID;
        this.nama = nama;
    }

    void komponevisual() {
        getContentPane().setBackground(Color.decode("#A8DF65"));
        getContentPane().setLayout(null);

        getContentPane().add(Jgambar);
        Jgambar.setBounds(276, 77, 243, 243);

        getContentPane().add(thank);
        thank.setBounds(247, 320, 307, 38);
        thank.setFont(new Font("montserrat", Font.BOLD, 36));
        thank.setForeground(Color.decode("#FFFFFF"));

        getContentPane().add(pesan);
        pesan.setBounds(245, 368, 311, 21);
        pesan.setFont(new Font("Roboto", Font.PLAIN, 18));
        pesan.setForeground(Color.decode("#FFFFFF"));

        getContentPane().add(kembali);
        kembali.setBounds(326, 419, 143, 39);
        kembali.setFont(new Font("montserrat", Font.BOLD, 14));
        kembali.setForeground(Color.decode("#000000"));
        kembali.setBackground(Color.decode("#FFFDE1"));
        kembali.setBorder(null);

        setVisible(true);

    }

    void AksiReaksi() {
        kembali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NafaFood_Home NH = new NafaFood_Home(ID, nama);
                NH.Style();
                NH.AksiReaksi();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        NafaFood_Order NafaFood_Order = new NafaFood_Order("1", "Naufal Alfadhil");
        NafaFood_Order.komponevisual();
        NafaFood_Order.AksiReaksi();

    }
}