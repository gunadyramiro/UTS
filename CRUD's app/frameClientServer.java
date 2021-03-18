package clientServer;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;
import IconMakeOver.IconLabel;

@SuppressWarnings("serial")
public class frameClientServer extends JFrame {

	private JPanel contentPane;
	private JTextField txtNo;
	private JTextField txtBarang;
	private JLabel lblJumlah;
	private JLabel lblBarang;
	private JLabel lblNo;
	private JTextField txtJumlah;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnUbah;
	private JButton btnSimpan;
	private JButton btnHapus;
	private DefaultTableModel tabelModel;
	String header[] = { "No", "Nama", "Jumlah" };
	private JLabel lblWall;

	/**
	 * Create the frame.
	 */
	public frameClientServer() {
		setResizable(false);
		setTitle("Frame Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtNo = new JTextField();
		txtNo.setBounds(103, 12, 164, 25);
		contentPane.add(txtNo);
		txtNo.setColumns(10);

		btnSimpan = new ClButtonTransparan("Simpan");
		btnSimpan.setToolTipText("Simpan");
		btnSimpan.setText("");
		btnSimpan.setIcon(new ImageIcon(frameClientServer.class
				.getResource("/clientServer/document-new.png")));
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection konek = Koneksi.getKoneksi();
					String sql = "INSERT INTO data VALUES(?,?,?)";
					PreparedStatement prepare = konek.prepareStatement(sql);

					prepare.setString(1, txtNo.getText());
					prepare.setString(2, txtBarang.getText());
					prepare.setString(3, txtJumlah.getText());
					prepare.executeUpdate();

					JOptionPane.showMessageDialog(null,
							"Data berhasil disimpan");
					tampilTabel();
					prepare.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Data gagal disimpan");
					System.out.println(ex);
				} finally {
					txtNo.setText("");
					txtBarang.setText("");
					txtJumlah.setText("");
				}
			}
		});
		btnSimpan.setBounds(555, 186, 61, 46);
		contentPane.add(btnSimpan);

		lblNo = new JLabel("No : ");
		lblNo.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		lblNo.setForeground(new Color(255, 255, 255));
		lblNo.setBounds(12, 14, 84, 15);
		contentPane.add(lblNo);

		lblBarang = new JLabel("Barang : ");
		lblBarang.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		lblBarang.setForeground(new Color(255, 255, 255));
		lblBarang.setBounds(12, 72, 84, 15);
		contentPane.add(lblBarang);

		txtBarang = new JTextField();
		txtBarang.setBounds(103, 70, 192, 25);
		contentPane.add(txtBarang);
		txtBarang.setColumns(10);

		lblJumlah = new JLabel("Jumlah : ");
		lblJumlah.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		lblJumlah.setForeground(new Color(255, 255, 255));
		lblJumlah.setBounds(12, 131, 84, 15);
		contentPane.add(lblJumlah);

		txtJumlah = new JTextField();
		txtJumlah.setBounds(103, 126, 177, 25);
		contentPane.add(txtJumlah);
		txtJumlah.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(103, 174, 378, 183);
		contentPane.add(scrollPane);

		tabelModel = new DefaultTableModel(null, header);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int pilih = table.getSelectedRow();

				if (pilih == -1) {
					return;
				}

				String no = (String) tabelModel.getValueAt(pilih, 0);
				txtNo.setText(no);
				String barang = (String) tabelModel.getValueAt(pilih, 1);
				txtBarang.setText(barang);
				String jumlah = (String) tabelModel.getValueAt(pilih, 2);
				txtJumlah.setText(jumlah);
			}
		});
		table.setModel(tabelModel);
		scrollPane.setViewportView(table);

		btnUbah = new ClButtonTransparan("Ubah");
		btnUbah.setToolTipText("Ubah");
		btnUbah.setText("");
		btnUbah.setIcon(new ImageIcon(frameClientServer.class
				.getResource("/clientServer/edit-clear.png")));
		btnUbah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection konek = Koneksi.getKoneksi();
					String sql = "UPDATE data SET nama = ?, jumlah = ? WHERE no = ?";
					PreparedStatement prepare = konek.prepareStatement(sql);

					prepare.setString(1, txtBarang.getText());
					prepare.setString(2, txtJumlah.getText());
					prepare.setString(3, txtNo.getText());

					prepare.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data berhasil diubah");
					tampilTabel();
					prepare.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Data gagal diubah");
				}

				finally {
					txtNo.setText("");
					txtBarang.setText("");
					txtJumlah.setText("");
				}
			}
		});
		btnUbah.setBounds(555, 244, 61, 44);
		contentPane.add(btnUbah);

		btnHapus = new ClButtonTransparan("Hapus");
		btnHapus.setToolTipText("Hapus");
		btnHapus.setText("");
		btnHapus.setIcon(new ImageIcon(frameClientServer.class
				.getResource("/clientServer/window-close.png")));
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection konek = Koneksi.getKoneksi();
					String sql = "DELETE FROM data WHERE no = ?";
					PreparedStatement prepare = konek.prepareStatement(sql);

					prepare.setString(1, txtNo.getText());
					prepare.executeUpdate();

					JOptionPane
							.showMessageDialog(null, "Data berhasil dihapus");
					tampilTabel();
					prepare.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Data gagal dihapus");
				}

				finally {
					txtNo.setText("");
					txtBarang.setText("");
					txtJumlah.setText("");
				}
			}
		});
		btnHapus.setBounds(555, 299, 61, 46);
		contentPane.add(btnHapus);

		JPanel panel = new ClPanelTransparan();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(595, 174, 33, 195);
		contentPane.add(panel);

		IconLabel lblicon = new IconLabel();
		lblicon.setIconReflect(new ImageIcon(frameClientServer.class
				.getResource("/clientServer/preferences-system-session.png")));
		lblicon.setBounds(390, 14, 149, 148);
		contentPane.add(lblicon);

		lblWall = new JLabel("wall");
		lblWall.setIcon(new ImageIcon(frameClientServer.class
				.getResource("/clientServer/wall.jpg")));
		lblWall.setBounds(0, 0, 628, 369);
		contentPane.add(lblWall);
		setLocationRelativeTo(null);
		tampilTabel();
	}

	public void tampilTabel() {
		tabelModel.getDataVector().removeAllElements();
		tabelModel.fireTableDataChanged();
		try {
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String sql = "SELECT * FROM data";

			ResultSet rs = state.executeQuery(sql);
			while (rs.next()) {
				Object obj[] = new Object[3];
				obj[0] = rs.getString(1);
				obj[1] = rs.getString(2);
				obj[2] = rs.getString(3);

				tabelModel.addRow(obj);
			}

			rs.close();
			state.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager
							.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					frameClientServer frame = new frameClientServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

