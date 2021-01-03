import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;

/**
 * NafaFood_FoodList
 */
class NafaFood_FoodList extends JFrame {

    JLabel logo = new JLabel();
    JButton homeButton = new JButton();
    JButton cart = new JButton();

    JPanel Foods = new JPanel();
    JScrollPane pane = new JScrollPane(Foods);

    JPanel listPanel = new JPanel();
    JScrollPane scrollPane = new JScrollPane(listPanel);

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    String nama = "";
    String ID = "";
    ArrayList cartList = new ArrayList();

    NumberFormat nf = NumberFormat.getInstance(new Locale("id", "ID"));

    NafaFood_FoodList(String ID, String nama) {
        super("List - NafaFood");
        setSize(800, 600);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.nama = nama;
        this.ID = ID;
    }

    void Style() {
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.white);

        getContentPane().add(logo);
        logo.setBounds(29, 23, 168, 40);
        logo.setIcon(new ImageIcon("./img/Logo_ListFood.png"));

        getContentPane().add(homeButton);
        homeButton.setBounds(710, 31, 30, 26);
        homeButton.setIcon(new ImageIcon("./img/home.png"));
        homeButton.setBorder(null);
        homeButton.setBackground(null);

        getContentPane().add(cart);
        cart.setBackground(null);
        cart.setBorder(null);
        cart.setBounds(625, 25, 78, 41);
        cart.setIcon(new ImageIcon("./img/cart.png"));

        List();

        setVisible(true);
    }

    void List() {

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(35, 95, 710, 426);
        getContentPane().add(scrollPane);
        scrollPane.setBorder(null);

        JPanel borderlaoutpanel = new JPanel();
        scrollPane.setViewportView(borderlaoutpanel);
        borderlaoutpanel.setLayout(new BorderLayout(0, 0));
        borderlaoutpanel.setBackground(Color.white);

        JPanel columnpanel = new JPanel();
        borderlaoutpanel.add(columnpanel, BorderLayout.NORTH);
        columnpanel.setLayout(new GridLayout(0, 2, 10, 10));
        columnpanel.setBackground(Color.white);

        Koneksi conn = new Koneksi();

        try {
            conn.connect();
            String sql = "SELECT * FROM tb_makanan";
            ResultSet rs = conn.getStatement().executeQuery(sql);
            while (rs.next()) {
                JPanel rowPanel = new JPanel();
                rowPanel.setPreferredSize(new Dimension(200, 100));
                columnpanel.add(rowPanel);
                rowPanel.setLayout(null);

                JLabel FoodImg = new JLabel();
                JLabel FoodTitle = new JLabel();
                JLabel FoodPrice = new JLabel();
                JLabel FoodOwner = new JLabel();
                JButton OrderBtn = new JButton("Pesan");

                ImageIcon gambar = new ImageIcon("img/Foods/" + rs.getString("gambar"));

                gambar = new ImageIcon(gambar.getImage().getScaledInstance(130, 103, Image.SCALE_SMOOTH));
                rowPanel.add(FoodImg);
                FoodImg.setIcon(gambar);
                FoodImg.setBounds(0, 0, 130, 103);

                rowPanel.add(FoodTitle);
                FoodTitle.setText(rs.getString("nama_makanan"));
                FoodTitle.setFont(new Font("Montserrat", Font.PLAIN, 14));
                FoodTitle.setBounds(142, 12, 170, 17);

                rowPanel.add(FoodPrice);
                int harga = Integer.parseInt(rs.getString("harga"));
                FoodPrice.setText(nf.format(harga));
                FoodPrice.setFont(new Font("Montserrat", Font.PLAIN, 10));
                FoodPrice.setBounds(142, 35, 84, 12);

                rowPanel.add(FoodOwner);
                FoodOwner.setText(rs.getString("owner"));
                FoodOwner.setFont(new Font("Montserrat", Font.PLAIN, 10));
                FoodOwner.setBounds(142, 53, 84, 12);

                final String id_makanan = rs.getString("id_makanan");
                rowPanel.add(OrderBtn);
                OrderBtn.setFont(new Font("Montserrat", Font.PLAIN, 10));
                OrderBtn.setBounds(142, 73, 94, 20);
                OrderBtn.setBorder(null);
                OrderBtn.setBackground(Color.decode("#A8DF65"));
                OrderBtn.setForeground(Color.white);
                OrderBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        cartList.add(id_makanan);
                        cart.setText("" + cartList.size());
                    }
                });
            }
            conn.closeConnection();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    void AksiReaksi() {
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NafaFood_Home NH = new NafaFood_Home(ID, nama);
                NH.Style();
                NH.AksiReaksi();
                dispose();
            }
        });
        cart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NafaFood_Checkout NC = new NafaFood_Checkout(ID, nama, cartList);
                NC.Style();
                NC.AksiReaksi();
                dispose();
            }
        });
    }

    // public static void main(String[] args) {
    // NafaFood_FoodList NFH = new NafaFood_FoodList("Naufal Alfadhil");
    // NFH.Style();
    // NFH.AksiReaksi();
    // }
}