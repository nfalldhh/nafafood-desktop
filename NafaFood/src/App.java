// package newbieilmuDemo;

// import java.awt.BorderLayout;
// import java.awt.Component;
// import java.awt.EventQueue;
// import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.Statement;
// import java.text.SimpleDateFormat;
// import java.util.HashMap;
// import java.util.logging.Level;
// import java.util.logging.Logger;

// import javax.imageio.ImageIO;
// import javax.swing.JFrame;
// import javax.swing.JPanel;
// import javax.swing.border.EmptyBorder;
// import javax.swing.JLabel;
// import javax.swing.JOptionPane;
// import javax.swing.JTextField;
// import javax.swing.JScrollPane;
// import javax.swing.JTextArea;
// import javax.swing.UIManager;

// import net.sf.jasperreports.engine.JasperFillManager;
// import net.sf.jasperreports.engine.JasperPrint;
// import net.sf.jasperreports.engine.JasperReport;
// import net.sf.jasperreports.engine.util.JRLoader;
// import net.sf.jasperreports.view.JasperViewer;

// import org.eclipse.swt.graphics.Image;
// import org.freixas.jcalendar.JCalendarCombo;

// import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
// import com.mysql.jdbc.Blob;
// import com.mysql.jdbc.PreparedStatement;

// import javax.swing.DefaultComboBoxModel;
// import javax.swing.JTable;
// import javax.swing.plaf.metal.MetalLookAndFeel;
// import javax.swing.table.DefaultTableModel;
// import javax.swing.table.TableCellRenderer;
// import javax.swing.table.TableColumn;
// import javax.swing.table.TableColumnModel;
// import javax.swing.JButton;
// import javax.swing.JFileChooser;
// import javax.swing.SwingConstants;
// import javax.swing.ImageIcon;
// import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;
// import java.awt.image.BufferedImage;
// import java.io.File;
// import java.io.FileInputStream;

// /*
// * @Anugrah Bagus S
// *
// */


// public class demoBlob extends JFrame {

// 	private JPanel contentPane;
// 	private JTextField txtIdPelanggan;
// 	private JTextField txtNmPelanggan;
// 	private JTable table;
// 	DefaultTableModel tabelModel;
// 	String data[] = {"IdPelanggan","NmPelanggan","Tempat","TglLahir"};
// 	private JFileChooser fc = new JFileChooser();
//     Blob blob;
//     BufferedImage img;
// 	/**
// 	 * Launch the application.
// 	 */
// 	public static void main(String[] args) {
// 		EventQueue.invokeLater(new Runnable() {
// 			public void run() {
// 				try {
// 					UIManager.setLookAndFeel(new AluminiumLookAndFeel());
// 					demoBlob frame = new demoBlob();
// 					frame.setVisible(true);
// 				} catch (Exception e) {
// 					e.printStackTrace();
// 				}
// 			}
// 		});
// 	}

// 	/**
// 	 * Create the frame.
// 	 */
// 	public demoBlob() {
// 		setTitle("[[::Data Pelanggan::]]");
// 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// 		setBounds(100, 100, 625, 471);
// 		contentPane = new JPanel();
// 		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
// 		setContentPane(contentPane);
// 		contentPane.setLayout(null);
		
// 		JLabel lblIdPelanggan = new JLabel("Id Pelanggan :");
// 		lblIdPelanggan.setHorizontalTextPosition(SwingConstants.RIGHT);
// 		lblIdPelanggan.setHorizontalAlignment(SwingConstants.RIGHT);
// 		lblIdPelanggan.setBounds(29, 42, 102, 17);
// 		contentPane.add(lblIdPelanggan);
		
// 		JLabel lblNmPelanggan = new JLabel("Nm Pelanggan :");
// 		lblNmPelanggan.setHorizontalTextPosition(SwingConstants.RIGHT);
// 		lblNmPelanggan.setHorizontalAlignment(SwingConstants.RIGHT);
// 		lblNmPelanggan.setBounds(29, 71, 102, 17);
// 		contentPane.add(lblNmPelanggan);
		
// 		JLabel lblTempat = new JLabel("Tempat :");
// 		lblTempat.setHorizontalTextPosition(SwingConstants.RIGHT);
// 		lblTempat.setHorizontalAlignment(SwingConstants.RIGHT);
// 		lblTempat.setBounds(29, 100, 102, 17);
// 		contentPane.add(lblTempat);
		
// 		JLabel lblTglLahir = new JLabel("Tgl Lahir :");
// 		lblTglLahir.setHorizontalTextPosition(SwingConstants.RIGHT);
// 		lblTglLahir.setHorizontalAlignment(SwingConstants.RIGHT);
// 		lblTglLahir.setBounds(29, 170, 102, 17);
// 		contentPane.add(lblTglLahir);
		
// 		final JLabel lblfotoTerkini = new JLabel("::Foto::");
// 		lblfotoTerkini.setBounds(483, 42, 54, 17);
// 		contentPane.add(lblfotoTerkini);
		
// 		final JLabel lblFoto = new JLabel();
// 		lblFoto.setBounds(453, 71, 132, 77);
// 		contentPane.add(lblFoto);
		
// 		txtIdPelanggan = new JTextField();
// 		txtIdPelanggan.setBounds(144, 40, 132, 21);
// 		contentPane.add(txtIdPelanggan);
// 		txtIdPelanggan.setColumns(10);
// 		txtIdPelanggan.setEnabled(false);
		
// 		txtNmPelanggan = new JTextField();
// 		txtNmPelanggan.setColumns(10);
// 		txtNmPelanggan.setBounds(144, 69, 132, 21);
// 		contentPane.add(txtNmPelanggan);
		
// 		JScrollPane scrollPane = new JScrollPane();
// 		scrollPane.setBounds(144, 100, 209, 60);
// 		contentPane.add(scrollPane);
		
// 		final JTextArea txaTempat = new JTextArea();
// 		scrollPane.setViewportView(txaTempat);
		
// 		final JCalendarCombo cmbTglLhr = new JCalendarCombo();
// 		cmbTglLhr.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
// 		cmbTglLhr.setEditable(true);
// 		cmbTglLhr.setBounds(144, 165, 156, 26);
// 		contentPane.add(cmbTglLhr);
		
// 		JScrollPane pane = new JScrollPane();
// 		pane.setBounds(29, 220, 551, 146);
// 		contentPane.add(pane);
		
// 		/*table = new JTable();
// 		table.setModel(new DefaultTableModel(
// 			new Object[][] {
// 				{null, null, null, null, null},
// 				{null, null, null, null, null},
// 				{null, null, null, null, null},
// 				{null, null, null, null, null},
// 				{null, null, null, null, null},
// 			},
// 			new String[] {
// 				"IdPelanggan", "NmPelanggan", "Tempat", "TglLahir", "Foto"
// 			}
// 		));
// 		pane.setViewportView(table);*/
		
// 		tabelModel = new DefaultTableModel(null,data);
// 		table = new JTable();
// 		table.addMouseListener(new MouseAdapter() {
// 			@Override
// 			public void mouseClicked(MouseEvent me) 
// 			{
// 				int pilih = table.getSelectedRow();
// 				if(pilih < 0)
// 				{
// 					return;
// 				}
				
// 				String idpelanggan = (String) tabelModel.getValueAt(pilih,0);
// 				txtIdPelanggan.setText(idpelanggan);
// 				String nmpelanggan = (String) tabelModel.getValueAt(pilih,1);
// 				txtNmPelanggan.setText(nmpelanggan);
// 				String tempat = (String) tabelModel.getValueAt(pilih,2);
// 				txaTempat.setText(tempat);
// 				String tgllhr = (String) tabelModel.getValueAt(pilih,3);
// 				cmbTglLhr.setSelectedItem(tgllhr);
// 				//String foto = (String) tabelModel.getValueAt(pilih,4);
// 				//lblFoto.setText(foto);
				
// 			}
// 		});
// 		table.setModel(tabelModel);
// 		pane.setViewportView(table);
		
// 		final JButton btnSimpan = new JButton("Simpan");
// 		btnSimpan.addActionListener(new ActionListener() {
// 			public void actionPerformed(ActionEvent arg0) {
// 				String sumber = fc.getSelectedFile().getPath();
//                 try
//                 {
//                 File fileGambar = new File(sumber);
//                 FileInputStream fis = new FileInputStream(fileGambar);
//                 Class.forName("com.mysql.jdbc.Driver").newInstance();	
//         		Connection konek = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "root");
//                 String query = "insert into datapelanggan values(?,?,?,?,?)";
//                 PreparedStatement prepare = (PreparedStatement) konek.prepareStatement(query);
                
//                 prepare.setString(1,txtIdPelanggan.getText());
//                 prepare.setString(2,txtNmPelanggan.getText());
//                 prepare.setString(3,txaTempat.getText());
//                 prepare.setString(4,(String) cmbTglLhr.getSelectedItem());
//                 prepare.setBinaryStream(5, fis, (int)fileGambar.length());
//                 prepare.executeUpdate();
//                 JOptionPane.showMessageDialog(null,"Data berhasil ditambahkan ke dalam database","Pesan",JOptionPane.INFORMATION_MESSAGE);
//                 tampilTabel();
//                 }
//                 catch(Exception ex)
//                 {
//                 JOptionPane.showMessageDialog(null,"Data gagal ditambahkan ke dalam database","Pesan",JOptionPane.INFORMATION_MESSAGE);
//                 System.out.println(ex);    
//                 }
// 			}
// 		});
// 		btnSimpan.setBounds(29, 383, 99, 27);
// 		contentPane.add(btnSimpan);
		
// 		JButton btnCetak = new JButton("Cetak");
// 		btnCetak.addActionListener(new ActionListener() {
// 			public void actionPerformed(ActionEvent e) {
// 				try{
// 					String nmFile = "src/Report/datapelanggan.jasper";
// 					Class.forName("com.mysql.jdbc.Driver").newInstance();	
// 		        	Connection konek = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "root");
// 					HashMap hash = new HashMap(1);
// 					hash.put("idpelanggan", txtIdPelanggan.getText());
// 					File file = new File(nmFile);
// 					JasperReport jasperreport = (JasperReport)JRLoader.loadObject(file.getPath());
// 					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport,hash,konek);
// 					JasperViewer.viewReport(jasperPrint,false);
// 					}
// 					catch(Exception ex){
// 						}
				
// 			}
// 		});
// 		btnCetak.setBounds(126, 383, 99, 27);
// 		contentPane.add(btnCetak);
		
// 		JButton btnHapus = new JButton("Hapus");
// 		btnHapus.addActionListener(new ActionListener() {
// 			public void actionPerformed(ActionEvent e) {
// 				try
// 				{
// 					Class.forName("com.mysql.jdbc.Driver").newInstance();	
// 		        	Connection konek = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "root");
// 					String query = "DELETE FROM datapelanggan WHERE idpelanggan = ?";
// 					PreparedStatement prepare = (PreparedStatement) konek.prepareStatement(query);
					
// 					prepare.setString(1,txtIdPelanggan.getText());
// 					prepare.executeUpdate();
// 					JOptionPane.showMessageDialog(null,"Data berhasil dihapus","Pesan",JOptionPane.INFORMATION_MESSAGE);
// 					prepare.close();
// 					tampilTabel();
// 					txtNmPelanggan.setText("");
// 					txaTempat.setText("");
// 					cmbTglLhr.setSelectedIndex(0);
// 				}
// 				catch(Exception ex)
// 				{
// 					System.out.println(ex);
// 					JOptionPane.showMessageDialog(null,"Data gagal diupdate","Pesan",JOptionPane.INFORMATION_MESSAGE);
// 				}
// 			}
// 		});
// 		btnHapus.setBounds(224, 383, 99, 27);
// 		contentPane.add(btnHapus);
		
// 		JButton btnKeluar = new JButton("Keluar");
// 		btnKeluar.addActionListener(new ActionListener() {
// 			public void actionPerformed(ActionEvent e) {
// 				Keluar();
// 			}
// 		});
// 		btnKeluar.setBounds(481, 383, 99, 27);
// 		contentPane.add(btnKeluar);
		
// 		final JButton btnupload = new JButton("::Upload::");
// 		btnupload.addActionListener(new ActionListener() {
// 			public void actionPerformed(ActionEvent e) {
// 				int buka = fc.showOpenDialog(btnupload);
// 	               if(buka == JFileChooser.APPROVE_OPTION)
// 	               {
// 	                   String sumber = fc.getSelectedFile().getPath();
// 	                   lblFoto.setIcon(new ImageIcon(sumber));
// 	                   File file = new File(sumber);
// 	                   try
// 	                   {
// 	                       FileInputStream fis = new FileInputStream(sumber);
// 	                   }
// 	                   catch(Exception ex)
// 	                   {
// 	                     Logger.getLogger(demoBlob.class.getName()).log(Level.SEVERE, null, ex);
// 	                   }
	            	   
	            	   
// 	               }
// 			}
// 		});
// 		btnupload.setBounds(453, 165, 127, 27);
// 		contentPane.add(btnupload);
		
// 		JLabel lblgambar = new JLabel("");
// 		lblgambar.setIcon(new ImageIcon("/home/newbieilmu/workspace/app.newbieilmu/src/icon/bg.jpg"));
// 		lblgambar.setBounds(0, 0, 615, 438);
// 		contentPane.add(lblgambar);
		
	
// 		autonumber();
// 		tampilTabel();
		
// 	}
	 
	
// 	void autonumber(){ 
// 		try{
// 		Class.forName("com.mysql.jdbc.Driver").newInstance();	
// 		Connection konek = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "root");
// 		Statement state = konek.createStatement();
// 		String sql = "select max(right(idpelanggan,4)) as no_terakhir from datapelanggan";
// 		ResultSet rs = state.executeQuery(sql);
// 		if(rs.first()==false)
// 		{
// 			txtIdPelanggan.setText("PG0001");
// 		}
// 		else{
// 			rs.last();
// 			int no=rs.getInt(1)+1;
// 			String cno=String.valueOf(no);
// 			int pjg_cno=cno.length();
// 			for(int i=0;i<2 - pjg_cno; i++);
// 			{
// 				cno="000"+cno;
// 			}
// 			txtIdPelanggan.setText("PG"+cno);
// 		}
// 		rs.close();
// 	  	konek.close();
// 		}
// 		catch(Exception ex){
			
// 		}
// 		}

// 	public void tampilTabel()
// 	{
// 		try
// 		{
// 			int vblob;
//             ImageIcon gambar;
// 			hapusIsiTabel();
// 			Class.forName("com.mysql.jdbc.Driver").newInstance();	
// 			Connection konek = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "root");
// 			Statement state = konek.createStatement();
// 			String query = "select * from datapelanggan";
// 			ResultSet rs = state.executeQuery(query);
// 			while(rs.next())
// 			{
			
// 			Object obj[] = new Object[4];
// 			obj[0] = rs.getString(1);
// 			obj[1] = rs.getString(2);
// 			obj[2] = rs.getString(3);
// 			obj[3] = rs.getString(4);		
// 			tabelModel.addRow(obj);
// 			sesuaikanKolom();
// 			}
// 			rs.close();
// 			state.close();
// 		}
// 		catch(Exception ex)
// 		{
// 			System.out.println(ex);
// 		}
// 	}
	
// 	void sesuaikanKolom() {
//         //cara untuk menyesuaikan kolom dari tabel adalah mengambil
//         // lebar kolom yang ada kemudian sesuaikan
//         TableColumnModel modelKolom = table.getColumnModel();

//         for (int kol = 0; kol < modelKolom.getColumnCount(); kol++) {
//             int lebarKolomMax = 0;
//             for (int baris = 0; baris < table.getRowCount(); baris++) {
//                 TableCellRenderer rend = table.getCellRenderer(baris, kol);
//                 Object nilaiTablel = table.getValueAt(baris, kol);
//                 Component comp = rend.getTableCellRendererComponent(table, nilaiTablel, false, false, baris, kol);
//                 lebarKolomMax = Math.max(comp.getPreferredSize().width, lebarKolomMax);
//             }//akhir for baris
//             TableColumn kolom = modelKolom.getColumn(kol);
//             kolom.setPreferredWidth(lebarKolomMax);
//         }//akhir for kolom
//     }
	
// 	public void hapusIsiTabel()
// 	{
// 		int a = table.getRowCount();
// 		int brs;
		
// 		for(brs=0;brs<a;brs++)
// 		{
// 			tabelModel.removeRow(0);
// 		}
// 	}
	
// 	void Keluar(){
// 		try {
// 	    	int reply = JOptionPane.showConfirmDialog (this,
// 					"Yakin Mau Keluar?",
// 					"Sistem - Keluar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
// 			if (reply == JOptionPane.YES_OPTION) {
// 				setVisible (false);		//Menyembunyikan Frame.
// 				dispose();            	//Membersihkan Resource dari system memori
// 				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Membebaskan aplikasi dari memori
// 				//System.exit (0);        //Keluar dari Aplikasi.
// 			}
// 		} 
// 		catch (Exception ex) {
// 		}
// 	}
// }