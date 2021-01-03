import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;

public class test extends JFrame {

    test(){
        super("test upload");
        setSize(500, 500);
        setLocation(10, 10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void Style(){
        // File sumber = new File("C:/Users/Nafaarts/Pictures/2x/logo-himati.png");
        // untuk filechooser
        // JFileChooser fileChooser = new JFileChooser();
        // fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        // int result = fileChooser.showOpenDialog(this);
        // if (result == JFileChooser.APPROVE_OPTION) {
        //     File selectedFile = fileChooser.getSelectedFile();
        //     System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        // } 

        // String pathSumber = sumber.getAbsolutePath();
        // ImageIcon gambar = new ImageIcon(pathSumber);
        // gambar = new ImageIcon(gambar.getImage().getScaledInstance(gambar.getIconWidth()*10/100  , gambar.getIconHeight()*10/100, Image.SCALE_SMOOTH));
        // JLabel frame = new JLabel();

        // getContentPane().add(frame);
        // frame.setBounds(300, 300, 10, 10);
        // frame.setIcon(gambar);
        // frame.setBackground(Color.red);

        setVisible(true);
    }

    void AksiReaksi() {
        // buka.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         int ambil = fc.showOpenDialog(tempat);
        //         if (ambil == JFileChooser.APPROVE_OPTION) {
        //             String sumber = fc.getSelectedFile().getPath();
        //             tempat.setIcon(new ImageIcon(sumber));
        //             File file = new File(sumber);
        //             try {
        //                 FileInputStream fis = new FileInputStream(sumber);
        //             } catch (Exception ex) {
        //                 System.out.print(ex);
        //             }
        //         }
        //     }
        // });
    }

    public static void main(String[] args) {
        test test = new test();
        test.Style();
        //  File output = new File("./img/logo-himati-copy.png");

        //  System.out.println(sumber.getAbsolutePath());
        //  try {
        //      Files.copy(sumber.toPath(), output.toPath());
        //      System.out.println("Copied");
        //  } catch (Exception e) {
        //      System.out.print("error : " + e);
        //  }
    }
}