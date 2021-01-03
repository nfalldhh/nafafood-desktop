import java.awt.Color;
import java.awt.Font;
import java.awt.*;

import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.Locale;
import java.sql.*;
import java.awt.event.*;

import javax.swing.*;

class NafaFood_Checkout extends JFrame {

    JLabel logo = new JLabel();
    JButton homeButton = new JButton();
    JButton cart = new JButton();

    JPanel checkoutPanel = new JPanel();
    JLabel totalPrice = new JLabel();
    JButton payButton = new JButton("Bayar Dengan NafaPay");

    int hargaTotal = 0;
    String nama = "";
    String ID = "";
    ArrayList cartList = new ArrayList();
    NumberFormat nf = NumberFormat.getInstance(new Locale("id", "ID"));
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    NafaFood_Checkout(String ID, String nama, ArrayList<String> array) {
        super("List - NafaFood");
        setSize(800, 600);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.cartList = array;
        this.nama = nama;
        this.ID = ID;
        setVisible(true);
    }

    void Style() {
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.white);

        getContentPane().add(cart);
        cart.setBackground(null);
        cart.setBorder(null);
        cart.setBounds(625, 25, 78, 41);
        cart.setIcon(new ImageIcon("./img/cart.png"));
        cart.setText("" + cartList.size());

        getContentPane().add(homeButton);
        homeButton.setBounds(710, 31, 30, 26);
        homeButton.setIcon(new ImageIcon("./img/home.png"));
        homeButton.setBorder(null);
        homeButton.setBackground(null);
        getContentPane().add(logo);
        logo.setBounds(29, 23, 168, 40);
        logo.setIcon(new ImageIcon("./img/Logo_ListFood.png"));

        getContentPane().add(checkoutPanel);
        checkoutPanel.setBounds(35, 124, 710, 143);
        checkoutPanel.setLayout(null);
        checkoutPanel.setBackground(Color.decode("#A8DF65"));

        checkoutPanel.add(payButton);
        payButton.setBounds(420, 55, 243, 35);
        payButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
        payButton.setBackground(Color.decode("#FFFDE1"));
        payButton.setBorder(null);

        checkoutPanel.add(totalPrice);
        totalPrice.setBounds(40, 50, 300, 44);
        totalPrice.setFont(new Font("Montserrat", Font.PLAIN, 36));
        totalPrice.setForeground(Color.decode("#FFFDE1"));
        totalPrice.setBackground(Color.red);

        List();

    }

    void List() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(35, 300, 710, 220);
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
        int i;
        for (i = 0; i < cartList.size(); i++) {
            try {
                conn.connect();
                String sql = "SELECT * FROM tb_makanan WHERE id_makanan = " + cartList.get(i);
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
                    JButton DeleteBtn = new JButton("Batal");

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

                    final int id_makanan = i;
                    rowPanel.add(DeleteBtn);
                    DeleteBtn.setFont(new Font("Montserrat", Font.PLAIN, 10));
                    DeleteBtn.setBounds(142, 73, 94, 20);
                    DeleteBtn.setBorder(null);
                    DeleteBtn.setBackground(Color.decode("#A8DF65"));
                    DeleteBtn.setForeground(Color.white);
                    DeleteBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            cartList.remove(id_makanan);
                            cart.setText("" + cartList.size());
                            NafaFood_Checkout NC = new NafaFood_Checkout(ID, nama, cartList);
                            NC.Style();
                            NC.AksiReaksi();
                            dispose();
                        }
                    });

                    hargaTotal += harga;
                }
                conn.closeConnection();
                rs.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }

        totalPrice.setText("Rp. " + nf.format(hargaTotal));
    }

    void AksiReaksi() {
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                NafaFood_FoodList NF = new NafaFood_FoodList(ID, nama);
                NF.AksiReaksi();
                NF.Style();
                dispose();
            }
        });
        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                NafaFood_Checkout_Confirm NT = new NafaFood_Checkout_Confirm(ID, nama, cartList, hargaTotal);
                NT.Style();
                NT.AksiReaksi();
                dispose();
            }
        });
    }
}