import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class NafaFood_Home extends JFrame {

    JPanel menu = new JPanel();
    JPanel banner = new JPanel();
    JLabel gambar = new JLabel();
    JLabel logo = new JLabel();
    JButton food_drinkButton = new JButton("Food & Drink");
    JButton nafapayButton = new JButton("NafaPay");
    JButton aboutButton = new JButton("About");

    String nama = "";
    String ID = "";

    JLabel welcome_message = new JLabel();

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    NafaFood_Home(String ID, String nama) {
        setTitle("Home - NafaFood");
        setSize(800, 600);
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.nama = nama;
        this.ID = ID;
    }

    void Style() {
        getContentPane().setLayout(new GridLayout(0, 2));

        getContentPane().add(menu);
        menu.setBackground(Color.white);
        menu.setLayout(null);

        getContentPane().add(gambar);
        gambar.setIcon(new ImageIcon("./img/banner.jpg"));
        gambar.setBounds(0, 0, 800, 600);

        menu.add(logo);
        logo.setIcon(new ImageIcon("./img/home_logo.png"));
        logo.setBounds(84, 98, 232, 50);

        menu.add(welcome_message);
        welcome_message.setBounds(111, 182, 179, 17);
        welcome_message.setFont(new Font("Montserrat", Font.PLAIN, 14));
        welcome_message.setText("Welcome, " + nama);

        menu.add(food_drinkButton);
        food_drinkButton.setBounds(86, 241, 228, 46);
        food_drinkButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
        food_drinkButton.setBorder(null);
        food_drinkButton.setBackground(Color.decode("#A8DF65"));
        food_drinkButton.setForeground(Color.decode("#FFFFFF"));

        menu.add(nafapayButton);
        nafapayButton.setBounds(86, 305, 228, 48);
        nafapayButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
        nafapayButton.setBorder(null);
        nafapayButton.setBackground(Color.decode("#A8DF65"));
        nafapayButton.setForeground(Color.decode("#FFFFFF"));

        menu.add(aboutButton);
        aboutButton.setBounds(86, 372, 228, 48);
        aboutButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
        aboutButton.setBorder(null);
        aboutButton.setBackground(Color.decode("#A8DF65"));
        aboutButton.setForeground(Color.decode("#FFFFFF"));

        setVisible(true);
    }

    void AksiReaksi() {
        food_drinkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NafaFood_FoodList NFFL = new NafaFood_FoodList(ID, nama);
                NFFL.Style();
                NFFL.AksiReaksi();
                dispose();
            }
        });

        nafapayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NafaFood_Nafapay NN = new NafaFood_Nafapay(ID, nama);
                NN.Style();
                NN.AksiReaksi();
                dispose();
            }
        });

        aboutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NafaFood_About NFA = new NafaFood_About(ID, nama);
                NFA.Style();
                NFA.AksiReaksi();
                dispose();
            }
        });
    }

}
