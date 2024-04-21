package GUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;


public class FrmXuatThongKe extends JFrame implements Printable,ActionListener {

	private static JPanel contentPane;
	public static JTable tblThongKein;
	public static JLabel lblSmile;
	public static JLabel lblDiaChi;
	public static JLabel lblDienThoai;
	public JLabel lblThongTin1 ;
	public JLabel lblThongTin3;
	private static JPanel panel;
	public static DefaultTableModel tableModel ;
	public JLabel txtThongTin1;
	public JLabel lblTieuDe;
	public JLabel lblSDTCuaHang;
	public JLabel lblDiaChiCuaHang;
	public JLabel lblThongTin2;
	public JLabel txtThongTin2;
	public JLabel txtThongTin3;
	private JScrollBar tblThongKe;
	private DefaultTableModel modelThongKe;
	private JLabel lblThongTin4;
	private JLabel txtThongTin4;
	private JLabel lblThongTin5;
	private JLabel txtThongTin5;
	private JScrollPane scrollPane_1;
	private JLabel txtThongTin6;
	private JLabel lblThongTin6;
	private JLabel lblNoiDungNgayLap;
	
	int count = 0;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmXuatThongKe frame = new FrmXuatThongKe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

	
	public FrmXuatThongKe() {
		setTitle("THỐNG KÊ NHÀ THUỐC HẠNH PHÚC");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setSize (1347, 775);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(385, 11, 807, 624);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		lblSmile = new JLabel("C\u1EECA H\u00C0NG TH\u1EDCI TRANG SMILE");
		lblSmile.setHorizontalAlignment(SwingConstants.CENTER);
		lblSmile.setBounds(10, 21, 771, 27);
		lblSmile.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblSmile.setForeground(new Color(255, 0, 0));
		panel.add(lblSmile);
		
		lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setBounds(34, 52, 58, 21);
		lblDiaChi.setForeground(new Color(0, 0, 0));
		lblDiaChi.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel.add(lblDiaChi);
		
		lblDienThoai = new JLabel("Số điện thoại:");
		lblDienThoai.setBounds(34, 83, 96, 22);
		lblDienThoai.setForeground(new Color(0, 0, 0));
		lblDienThoai.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel.add(lblDienThoai);
		
		lblThongTin1 = new JLabel("");
		lblThongTin1.setBounds(34, 152, 150, 20);
		lblThongTin1.setForeground(new Color(0, 0, 0));
		lblThongTin1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel.add(lblThongTin1);
		
		lblThongTin3 = new JLabel("");
		lblThongTin3.setBounds(34, 212, 150, 20);
		lblThongTin3.setForeground(new Color(0, 0, 0));
		lblThongTin3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel.add(lblThongTin3);
		
		lblTieuDe = new JLabel("");
		lblTieuDe.setBounds(10, 114, 771, 27);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setForeground(new Color(0, 0, 0));
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(lblTieuDe);
		

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 244, 787, 305);
		panel.add(scrollPane_1);
		tblThongKein = taoBangBaoCao(modelThongKe, count);
		
		
		
		lblSDTCuaHang = new JLabel("0368.564.833  - 0333.319.121");
		lblSDTCuaHang.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblSDTCuaHang.setBounds(141, 86, 199, 14);
		panel.add(lblSDTCuaHang);
		
		lblDiaChiCuaHang = new JLabel("12 Nguyễn Văn Bảo - Phường 4 - Gò Vấp - Thành phố Hồ Chí Minh");
		lblDiaChiCuaHang.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDiaChiCuaHang.setBounds(141, 49, 450, 27);
		panel.add(lblDiaChiCuaHang);
		
		lblThongTin2 = new JLabel("");
		lblThongTin2.setForeground(new Color(0, 0, 0));
		lblThongTin2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblThongTin2.setBounds(34, 182, 150, 20);
		panel.add(lblThongTin2);
		
		txtThongTin1 = new JLabel("");
		txtThongTin1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtThongTin1.setBounds(172, 152, 168, 20);
		panel.add(txtThongTin1);
		
		txtThongTin2 = new JLabel("");
		txtThongTin2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtThongTin2.setBounds(172, 182, 168, 20);
		panel.add(txtThongTin2);
		
		txtThongTin3 = new JLabel("");
		txtThongTin3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtThongTin3.setBounds(172, 212, 168, 20);
		panel.add(txtThongTin3);
		
		lblThongTin4 = new JLabel("");
		lblThongTin4.setForeground(Color.BLACK);
		lblThongTin4.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblThongTin4.setBounds(475, 152, 150, 20);
		panel.add(lblThongTin4);
		
		txtThongTin4 = new JLabel("");
		txtThongTin4.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtThongTin4.setBounds(613, 152, 168, 20);
		panel.add(txtThongTin4);
		
		lblThongTin5 = new JLabel("");
		lblThongTin5.setForeground(Color.BLACK);
		lblThongTin5.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblThongTin5.setBounds(475, 182, 150, 20);
		panel.add(lblThongTin5);
		
		txtThongTin5 = new JLabel("");
		txtThongTin5.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtThongTin5.setBounds(613, 182, 168, 20);
		panel.add(txtThongTin5);
		
		txtThongTin6 = new JLabel("");
		txtThongTin6.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtThongTin6.setBounds(613, 212, 168, 20);
		panel.add(txtThongTin6);
		
		lblThongTin6 = new JLabel("");
		lblThongTin6.setForeground(Color.BLACK);
		lblThongTin6.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblThongTin6.setBounds(475, 212, 116, 20);
		panel.add(lblThongTin6);
		
		lblNoiDungNgayLap = new JLabel("(Thống kê được lập vào ngày hôm nay!)");
		lblNoiDungNgayLap.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoiDungNgayLap.setForeground(Color.BLACK);
		lblNoiDungNgayLap.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNoiDungNgayLap.setBounds(10, 575, 787, 27);
		panel.add(lblNoiDungNgayLap);
		
	}
	
	public void printThongKe() {
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(this);
		boolean ok = job.printDialog();
		if(ok) {
			try {
				job.print();
			} catch (Exception e2) {
					// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		// TODO Auto-generated method stub
			Graphics2D g2d = (Graphics2D) graphics;
			if(pageIndex>0) {
				return NO_SUCH_PAGE;
			}
			g2d.translate(pageFormat.getImageableX(),pageFormat.getImageableY());
			panel.printAll(graphics);
			return PAGE_EXISTS;
		}



// Đưa dữ liệu vào hóa đơn
	public void setDuLieuThongKe(String lbl1, String lbl2, String lbl3, String lbl4, String lbl5, String lbl6, String thongTin1, String thongTin2, String thongTin3, String thongTin4, String thongTin5, String thongTin6){
		lblThongTin1.setText(lbl1);
		lblThongTin2.setText(lbl2);
		lblThongTin3.setText(lbl3);
		lblThongTin4.setText(lbl4);
		lblThongTin5.setText(lbl5);
		lblThongTin6.setText(lbl6);
		txtThongTin1.setText(thongTin1);
		txtThongTin2.setText(thongTin2);
		txtThongTin3.setText(thongTin3);
		txtThongTin4.setText(thongTin4);
		txtThongTin5.setText(thongTin5);
		txtThongTin6.setText(thongTin6);
		
	}
	
	public void capNhatBangThongKe(DefaultTableModel modelNew) {
			tableModel = (DefaultTableModel) tblThongKein.getModel();

		    for (int row = 0; row < modelNew.getRowCount(); row++) {
		        Object[] rowData = new Object[modelNew.getColumnCount()]; // Giảm một cột
		        for (int col = 0; col < modelNew.getColumnCount(); col++) {
		            rowData[col] = modelNew.getValueAt(row, col);
		        }
		        tableModel.addRow(rowData);
		    }
	    
	}
	
	public void taoBangThongKe(JTable tblNew, int n)
	{
		
		tblThongKein = tblNew;
		count = n;
		if (n == 1)
		{
			tblThongKein.getColumnModel().getColumn(0).setPreferredWidth(40);
			tblThongKein.getColumnModel().getColumn(1).setPreferredWidth(50);
			tblThongKein.getColumnModel().getColumn(2).setPreferredWidth(160);
			tblThongKein.getColumnModel().getColumn(3).setPreferredWidth(90);
			tblThongKein.getColumnModel().getColumn(4).setPreferredWidth(90);
			tblThongKein.getColumnModel().getColumn(5).setPreferredWidth(50);
			tblThongKein.getColumnModel().getColumn(6).setPreferredWidth(80);
			tblThongKein.getColumnModel().getColumn(8).setPreferredWidth(70);
			tblThongKein.getColumnModel().getColumn(11).setPreferredWidth(90);
			
			lblThongTin1.setBounds(34, 152, 150, 20);
			lblThongTin2.setBounds(34, 182, 150, 20);
			
			txtThongTin4.setBounds(613, 152, 168, 20);
			txtThongTin5.setBounds(613, 182, 168, 20);
			txtThongTin6.setBounds(613, 212, 168, 20);
		}
		else if (n == 2)
		{
			tblThongKein.getColumnModel().getColumn(0).setPreferredWidth(40);
			tblThongKein.getColumnModel().getColumn(1).setPreferredWidth(160);
			tblThongKein.getColumnModel().getColumn(2).setPreferredWidth(90);
			tblThongKein.getColumnModel().getColumn(3).setPreferredWidth(90);
			tblThongKein.getColumnModel().getColumn(4).setPreferredWidth(90);
			tblThongKein.getColumnModel().getColumn(5).setPreferredWidth(90);
			tblThongKein.getColumnModel().getColumn(6).setPreferredWidth(80);
			tblThongKein.getColumnModel().getColumn(7).setPreferredWidth(90);
			txtThongTin4.setBounds(555, 152, 168, 20);
			txtThongTin5.setBounds(555, 182, 168, 20);
			txtThongTin6.setBounds(555, 212, 168, 20);
		}
		else if (n == 3)
		{
			tblThongKein.getColumnModel().getColumn(0).setPreferredWidth(40);
			tblThongKein.getColumnModel().getColumn(1).setPreferredWidth(50);
			tblThongKein.getColumnModel().getColumn(2).setPreferredWidth(140);
			tblThongKein.getColumnModel().getColumn(3).setPreferredWidth(80);
			tblThongKein.getColumnModel().getColumn(4).setPreferredWidth(50);
			tblThongKein.getColumnModel().getColumn(5).setPreferredWidth(60);
			tblThongKein.getColumnModel().getColumn(6).setPreferredWidth(50);
			tblThongKein.getColumnModel().getColumn(7).setPreferredWidth(50);
			tblThongKein.getColumnModel().getColumn(8).setPreferredWidth(60);
			tblThongKein.getColumnModel().getColumn(9).setPreferredWidth(30);
			tblThongKein.getColumnModel().getColumn(10).setPreferredWidth(60);
			lblThongTin1.setBounds(34, 152, 170, 20);
			txtThongTin1.setBounds(210, 152, 168, 20);
			lblThongTin2.setBounds(34, 182, 170, 20);
			txtThongTin2.setBounds(210, 182, 168, 20);

		}
		tblThongKein.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(tblThongKein);
	}
	public void xoaAllDataTable() {
		
		tableModel = (DefaultTableModel) tblThongKein.getModel();
		tableModel.addRow(new Object[] {});
		tableModel.getDataVector().removeAllElements();

	}
	public String tieuDe (String title)
	{
		String tieuDe = title;
		lblTieuDe.setText(title);
		return tieuDe;
	}
	public void ngayIn(String date)
	{
		lblNoiDungNgayLap.setText(date);
	}
	public JTable taoBangBaoCao(DefaultTableModel model, int count)
	{
		JTable table_DSSP = null;
		if(count == 1)
		{
			String[] tb = new String[] {"STT","Mã","Tên","Màu Sắc","Size","KM","ĐG nhập","ĐG bán", "Số lượng", "Tiền nhập", "SL bán", "Tiền bán"};

			model = new DefaultTableModel(tb,0);
			JTable table_DS = new JTable(model);
			table_DS.setBackground(Color.WHITE);
			table_DS.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			table_DS.setBackground(new Color(224, 255, 255));
			table_DS.setForeground(new Color(0, 0, 0));
		}
		else if (count == 2)
		{
			String[] tb = new String[] {"STT","Mã Hóa Đơn","Mã Nhân viên","Ca Làm Việc","Số lượng","Ngày Lập","Loại Khách","Doanh thu"};

			model = new DefaultTableModel(tb,0);
			JTable table_DS = new JTable(model);
			table_DS.setBackground(Color.WHITE);
			table_DS.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			table_DS.setBackground(new Color(224, 255, 255));
			table_DS.setForeground(new Color(0, 0, 0));
		}
		else if (count == 3)
		{
			String[] tb = new String[] {"STT","Mã","Tên sản phẩm","Loại","Số lượng","Ngày nhập","Giá nhập","Giá bán", "Màu sắc", "Size", "SL bán"};

			model = new DefaultTableModel(tb,0);
			table_DSSP = new JTable(model);
			table_DSSP.setBackground(Color.WHITE);
			table_DSSP.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			table_DSSP.setBackground(new Color(224, 255, 255));
			table_DSSP.setForeground(new Color(0, 0, 0));
			
		}
		return table_DSSP;
	}
}