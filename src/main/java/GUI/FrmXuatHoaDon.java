package GUI;

import javax.swing.*;
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


public class FrmXuatHoaDon extends JFrame implements Printable,ActionListener {

	private static JPanel contentPane;
	public static JTable table_CTHDin;
	public static JLabel lblSmile;
	public static JLabel lblDiaChi;
	public static JLabel lblDienThoai;
	public static JLabel lblMHDon ;
	public static JLabel lblNgayLap;
	private static JPanel panel;
	public static DefaultTableModel tableModel ;
	public JLabel lblMaHD;
	public JLabel lblTieuDe;
	public JLabel lblGiaThue;
	public JLabel lblSDTCuaHang;
	public JLabel lblDiaChiCuaHang;
	public JLabel lblNhanVienLap;
	public JLabel lblSDTKH;
	public JLabel lblTenNV;
	public JLabel lblDate;
	public JLabel lbltenKH;
	public JLabel lblGetDienTichLuy;
	public JLabel lblGetSDT;
	public JLabel lblGetSoLuong;
	public JLabel lblTongTienSP;
	public JLabel getTongSoLuong;
	public JLabel getDiemTichDuoc;
	public JLabel getDiemSuDung;
	public JLabel getTongTien;
	public JLabel getTongThanhToan;
	public JLabel getTienKhachDua;
	public JLabel getTienHoan;
	private JScrollBar table_CTHD;
	private DefaultTableModel tableModelCTHD;
	private JLabel getPhanTramKhuyenMai;
	private JLabel getTienKM;
	private JLabel getTongThue;
	private JLabel lblTongThue;

	private static final String URL = "rmi://HOANGPHUC:6541/";


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmXuatHoaDon frame = new FrmXuatHoaDon();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

	
	public FrmXuatHoaDon() {
		setTitle("HÓA ĐƠN NHÀ THUỐC HẠNH PHÚC");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setSize( 1199, 775);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(new ImageIcon("Anh\\Logo.png").getImage());
		
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(432, 11, 613, 756);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		lblSmile = new JLabel("C\u1EECA H\u00C0NG TH\u1EDCI TRANG SMILE");
		lblSmile.setBounds(164, 20, 309, 27);
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
		
		lblMHDon = new JLabel("Mã hóa đơn:");
		lblMHDon.setBounds(34, 150, 83, 20);
		lblMHDon.setForeground(new Color(0, 0, 0));
		lblMHDon.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel.add(lblMHDon);
		
		lblNgayLap = new JLabel("Ngày lập:");
		lblNgayLap.setBounds(34, 220, 75, 20);
		lblNgayLap.setForeground(new Color(0, 0, 0));
		lblNgayLap.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel.add(lblNgayLap);
		
		lblTieuDe = new JLabel("HÓA ĐƠN THANH TOÁN");
		lblTieuDe.setBounds(199, 113, 235, 27);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setForeground(new Color(0, 0, 0));
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(lblTieuDe);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 244, 596, 271);
		panel.add(scrollPane_1);
		
		
		String[] tb = new String[] {"STT","Tên sản phẩm","Màu sắc","Size","Chất liệu","Đơn giá","SL","VAT","Khuyến mãi","Thành tiền"};

		tableModel = new DefaultTableModel(tb,0);
		table_CTHDin = new JTable(tableModel);
		table_CTHDin.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		table_CTHDin.setBackground(Color.WHITE);
		
		
		table_CTHDin.getColumnModel().getColumn(0).setPreferredWidth(40);
		table_CTHDin.getColumnModel().getColumn(1).setPreferredWidth(160);
		table_CTHDin.getColumnModel().getColumn(2).setPreferredWidth(80);
		table_CTHDin.getColumnModel().getColumn(3).setPreferredWidth(50);
		table_CTHDin.getColumnModel().getColumn(5).setPreferredWidth(70);
		table_CTHDin.getColumnModel().getColumn(6).setPreferredWidth(40);
		table_CTHDin.getColumnModel().getColumn(8).setPreferredWidth(110);
		table_CTHDin.getColumnModel().getColumn(9).setPreferredWidth(110);
		scrollPane_1.setViewportView(table_CTHDin);
		
		lblGiaThue = new JLabel("(Giá đã bao gồm thuế VAT (5%) và Khuyến mãi trên từng sản phẩm (nếu có) )");
		lblGiaThue.setBounds(69, 684, 493, 21);
		lblGiaThue.setForeground(new Color(0, 0, 0));
		lblGiaThue.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		panel.add(lblGiaThue);
		
		lblSDTCuaHang = new JLabel("0368.564.833  - 0333.319.121");
		lblSDTCuaHang.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblSDTCuaHang.setBounds(141, 86, 199, 14);
		panel.add(lblSDTCuaHang);
		
		lblDiaChiCuaHang = new JLabel("12 Nguyễn Văn Bảo - Phường 4 - Gò Vấp - Thành phố Hồ Chí Minh");
		lblDiaChiCuaHang.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDiaChiCuaHang.setBounds(141, 49, 450, 27);
		panel.add(lblDiaChiCuaHang);
		
		lblNhanVienLap = new JLabel("Nhân viên lập:");
		lblNhanVienLap.setForeground(new Color(0, 0, 0));
		lblNhanVienLap.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNhanVienLap.setBounds(34, 185, 113, 20);
		panel.add(lblNhanVienLap);
		
		lblSDTKH = new JLabel("Số điện thoại:");
		lblSDTKH.setForeground(new Color(0, 0, 0));
		lblSDTKH.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSDTKH.setBounds(332, 185, 96, 20);
		panel.add(lblSDTKH);
		
		JLabel lblDiemTichHC = new JLabel("Điểm tích lũy hiện có:");
		lblDiemTichHC.setForeground(new Color(0, 0, 0));
		lblDiemTichHC.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDiemTichHC.setBounds(332, 220, 158, 20);
		panel.add(lblDiemTichHC);
		
		JLabel lblKhachHang = new JLabel("Khách hàng: ");
		lblKhachHang.setForeground(new Color(0, 0, 0));
		lblKhachHang.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblKhachHang.setBounds(332, 150, 158, 20);
		panel.add(lblKhachHang);
		
		lblMaHD = new JLabel("");
		lblMaHD.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblMaHD.setBounds(141, 150, 168, 20);
		panel.add(lblMaHD);
		
		lblTenNV = new JLabel("");
		lblTenNV.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTenNV.setBounds(141, 185, 168, 20);
		panel.add(lblTenNV);
		
		lblDate = new JLabel("");
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDate.setBounds(141, 220, 168, 20);
		panel.add(lblDate);
		
		lbltenKH = new JLabel("");
		lbltenKH.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lbltenKH.setBounds(423, 150, 168, 20);
		panel.add(lbltenKH);
		
		lblGetDienTichLuy = new JLabel("");
		lblGetDienTichLuy.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblGetDienTichLuy.setBounds(483, 220, 108, 20);
		panel.add(lblGetDienTichLuy);
		
		lblGetSDT = new JLabel("");
		lblGetSDT.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblGetSDT.setBounds(423, 185, 168, 20);
		panel.add(lblGetSDT);
		
		lblGetSoLuong = new JLabel("Tổng số lượng:");
		lblGetSoLuong.setForeground(new Color(0, 0, 0));
		lblGetSoLuong.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblGetSoLuong.setBounds(316, 525, 118, 20);
		panel.add(lblGetSoLuong);
		
		lblTongTienSP = new JLabel("Tổng tiền SP:");
		lblTongTienSP.setForeground(new Color(0, 0, 0));
		lblTongTienSP.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTongTienSP.setBounds(316, 555, 118, 20);
		panel.add(lblTongTienSP);
		
		JLabel lblDiemTichDuoc = new JLabel("Điểm tích được:");
		lblDiemTichDuoc.setForeground(new Color(0, 0, 0));
		lblDiemTichDuoc.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDiemTichDuoc.setBounds(34, 525, 118, 20);
		panel.add(lblDiemTichDuoc);
		
		JLabel lblDiemSuaDung = new JLabel("Điểm sử dụng:");
		lblDiemSuaDung.setForeground(new Color(0, 0, 0));
		lblDiemSuaDung.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDiemSuaDung.setBounds(34, 555, 118, 20);
		panel.add(lblDiemSuaDung);
		
		JLabel lblTongTienThanhToan = new JLabel("Tổng thanh toán:");
		lblTongTienThanhToan.setForeground(new Color(0, 0, 0));
		lblTongTienThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTongTienThanhToan.setBounds(316, 585, 118, 20);
		panel.add(lblTongTienThanhToan);
		
		JLabel lblTienKhach = new JLabel("Tiền khách đưa:");
		lblTienKhach.setForeground(new Color(0, 0, 0));
		lblTienKhach.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTienKhach.setBounds(316, 615, 118, 20);
		panel.add(lblTienKhach);
		
		JLabel lblTienHoan = new JLabel("Tiền hoàn lại:");
		lblTienHoan.setForeground(new Color(0, 0, 0));
		lblTienHoan.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTienHoan.setBounds(316, 645, 118, 20);
		panel.add(lblTienHoan);
		
		JLabel lblLoiCamOn = new JLabel("Xin cảm ơn quý khách, hẹn gặp lại !");
		lblLoiCamOn.setForeground(new Color(0, 0, 0));
		lblLoiCamOn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblLoiCamOn.setBounds(185, 697, 250, 27);
		panel.add(lblLoiCamOn);
		
		getTongSoLuong = new JLabel("");
		getTongSoLuong.setHorizontalAlignment(SwingConstants.LEFT);
		getTongSoLuong.setFont(new Font("Times New Roman", Font.BOLD, 15));
		getTongSoLuong.setBounds(440, 525, 155, 20);
		panel.add(getTongSoLuong);
		
		getDiemTichDuoc = new JLabel("");
		getDiemTichDuoc.setHorizontalAlignment(SwingConstants.LEFT);
		getDiemTichDuoc.setFont(new Font("Times New Roman", Font.BOLD, 15));
		getDiemTichDuoc.setBounds(200, 525, 109, 20);
		panel.add(getDiemTichDuoc);
		
		getDiemSuDung = new JLabel("");
		getDiemSuDung.setHorizontalAlignment(SwingConstants.LEFT);
		getDiemSuDung.setFont(new Font("Times New Roman", Font.BOLD, 15));
		getDiemSuDung.setBounds(200, 555, 109, 20);
		panel.add(getDiemSuDung);
		
		getTongTien = new JLabel("");
		getTongTien.setHorizontalAlignment(SwingConstants.LEFT);
		getTongTien.setFont(new Font("Times New Roman", Font.BOLD, 15));
		getTongTien.setBounds(440, 555, 155, 20);
		panel.add(getTongTien);
		
		getTongThanhToan = new JLabel("");
		getTongThanhToan.setHorizontalAlignment(SwingConstants.LEFT);
		getTongThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 15));
		getTongThanhToan.setBounds(440, 585, 155, 20);
		panel.add(getTongThanhToan);
		
		getTienKhachDua = new JLabel("");
		getTienKhachDua.setHorizontalAlignment(SwingConstants.LEFT);
		getTienKhachDua.setFont(new Font("Times New Roman", Font.BOLD, 15));
		getTienKhachDua.setBounds(440, 615, 155, 20);
		panel.add(getTienKhachDua);
		
		getTienHoan = new JLabel("");
		getTienHoan.setHorizontalAlignment(SwingConstants.LEFT);
		getTienHoan.setFont(new Font("Times New Roman", Font.BOLD, 15));
		getTienHoan.setBounds(440, 649, 155, 20);
		panel.add(getTienHoan);
		
		lblTongThue = new JLabel("Tổng tiền thuế:");
		lblTongThue.setForeground(new Color(0, 0, 0));
		lblTongThue.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTongThue.setBounds(34, 645, 118, 20);
		panel.add(lblTongThue);
		
		getTongThue = new JLabel("");
		getTongThue.setHorizontalAlignment(SwingConstants.LEFT);
		getTongThue.setFont(new Font("Times New Roman", Font.BOLD, 15));
		getTongThue.setBounds(200, 645, 109, 20);
		panel.add(getTongThue);
		
		JLabel lblPhanTramKhuyenMai = new JLabel("Phần trăm khuyến mãi:");
		lblPhanTramKhuyenMai.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPhanTramKhuyenMai.setForeground(new Color(0, 0, 0));
		lblPhanTramKhuyenMai.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPhanTramKhuyenMai.setBounds(29, 585, 158, 20);
		panel.add(lblPhanTramKhuyenMai);
		
		JLabel lblTongKM = new JLabel("Tổng tiền KM:");
		lblTongKM.setForeground(new Color(0, 0, 0));
		lblTongKM.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTongKM.setBounds(34, 615, 118, 20);
		panel.add(lblTongKM);
		
		getPhanTramKhuyenMai = new JLabel("");
		getPhanTramKhuyenMai.setHorizontalAlignment(SwingConstants.LEFT);
		getPhanTramKhuyenMai.setFont(new Font("Times New Roman", Font.BOLD, 15));
		getPhanTramKhuyenMai.setBounds(200, 585, 109, 20);
		panel.add(getPhanTramKhuyenMai);
		
		getTienKM = new JLabel("");
		getTienKM.setHorizontalAlignment(SwingConstants.LEFT);
		getTienKM.setFont(new Font("Times New Roman", Font.BOLD, 15));
		getTienKM.setBounds(200, 615, 108, 20);
		panel.add(getTienKM);
		
	}
	
	public void printingHoaDon() {
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
	public void setDuLieuFrmInHd(String maHoaDon,String nhanVien,String ngayLap,String hoTenKh,String dtlhienco,String soDT,String tongsl,String diemtichduoc,String diemsudung,String tongtiensp,String tongthanhtoan,String tienkhachdua,String tienhoanlai, String phantramkhuyenmai, String tongthue, String tongkm ) {

		lblMaHD.setText(maHoaDon);
		lblTenNV.setText(nhanVien);
		lblDate.setText(ngayLap);
		lbltenKH.setText(hoTenKh);
		lblGetDienTichLuy.setText(dtlhienco);
		lblGetSDT.setText(soDT);
		getTongSoLuong.setText(tongsl);
		getDiemTichDuoc.setText(diemtichduoc);
		getDiemSuDung.setText(diemsudung);
		getTongTien.setText(tongtiensp);
		getTongThanhToan.setText(tongthanhtoan);
		getTienKhachDua.setText(tienkhachdua);
		getTienHoan.setText(tienhoanlai);
		getPhanTramKhuyenMai.setText(phantramkhuyenmai);
		getTongThue.setText(tongthue);
		getTienKM.setText(tongkm);
		
		
		
	}
	
	public void capNhatDuLieuTableCTHD(DefaultTableModel modelNew) {
	    DefaultTableModel tableModel = (DefaultTableModel) table_CTHDin.getModel();

	    for (int row = 0; row < modelNew.getRowCount(); row++) {
	        Object[] rowData = new Object[modelNew.getColumnCount() - 1]; // Giảm một cột
	        for (int col = 0; col < modelNew.getColumnCount(); col++) {
	            if (col != 1) {
	                rowData[col > 1 ? col - 1 : col] = modelNew.getValueAt(row, col);
	            }
	        }
	        tableModel.addRow(rowData);
	    }
	}

}
