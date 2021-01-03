import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

class NafaFood_About extends JFrame {

    JPanel menu = new JPanel();
    JLabel gambar = new JLabel();
    JButton judul = new JButton("ABOUT ME");
    JLabel namaLabel = new JLabel("Nama : Muhammad Naufal Alfadhil");
    JLabel email = new JLabel("Email : naufal@nafaarts.com");
    JLabel web = new JLabel("Web : www.nafaarts.com");
    JLabel instagram = new JLabel("Instagram : @nfalldh");
    JButton kembali = new JButton("< Kembali");
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    String nama;
    String ID;

    NafaFood_About(String ID, String nama) {
        setTitle("About Me - NafaFood");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setResizable(false);
        this.nama = nama;
        this.ID = ID;
    }

    void Style() {
        getContentPane().setLayout(new GridLayout(0, 2));

        getContentPane().add(gambar);
        gambar.setIcon(new ImageIcon("./img/about.png"));

        getContentPane().add(menu);
        menu.setBackground(Color.white);
        menu.setLayout(null);

        menu.add(namaLabel);
        namaLabel.setFont(new Font("Montserrat", Font.PLAIN, 14));
        namaLabel.setBounds(63, 244, 250, 17);

        menu.add(email);
        email.setFont(new Font("Montserrat", Font.PLAIN, 14));
        email.setBounds(63, 279, 250, 17);

        menu.add(web);
        web.setFont(new Font("Montserrat", Font.PLAIN, 14));
        web.setBounds(63, 314, 250, 17);

        menu.add(instagram);
        instagram.setFont(new Font("Montserrat", Font.PLAIN, 14));
        instagram.setBounds(63, 349, 250, 17);

        menu.add(kembali);
        kembali.setFont(new Font("Montserrat", Font.PLAIN, 14));
        kembali.setBounds(63, 392, 70, 17);
        kembali.setBackground(null);
        kembali.setBorder(null);
        kembali.setForeground(Color.decode("#A8DF65"));

        menu.add(judul);
        judul.setFont(new Font("Montserrat", Font.PLAIN, 24));
        judul.setBounds(63, 152, 275, 54);
        judul.setBorder(null);
        judul.setBackground(Color.decode("#A8DF65"));
        judul.setForeground(Color.white);

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
        judul.addActionListener(new ActionListener() // untuk tombol facebook
        {
            public void actionPerformed(ActionEvent e) {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();

                    try {
                        URI uri = new URI("http://www.nafaarts.com");
                        desktop.browse(uri);
                    } catch (IOException ex) {
                    } catch (URISyntaxException ex) {
                    }
                } else {
                }
            }
        });
    }

}