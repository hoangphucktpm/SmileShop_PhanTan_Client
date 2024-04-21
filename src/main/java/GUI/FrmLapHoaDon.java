package GUI;

import DAOTest.LapHoaDonDao;
import DAOTest.NhanVienDao;
import DAOTest.SanPhamDao;
import DAOTest.XemHoaDonDao;
import DAOTest.impl.LapHoaDonImpl;
import DAOTest.impl.NhanVienImpl;
import DAOTest.impl.SanPhamImpl;
import DAOTest.impl.XemHoaDonImpl;
import Entities.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;

public class FrmLapHoaDon extends JFrame implements ActionListener, MouseListener, KeyListener, DocumentListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public static DefaultTableModel tablemodel = new DefaultTableModel();
    public static DefaultTableModel modelNEw = new DefaultTableModel();
    private JLabel lblTieuDeTrang;
    static JPanel pnlThongTin;
    private JTextField txtMaKH;
    private JTextField txtTenKH;
    private JTextField txtSDT;
    private JTextField txtDiemTL;
    private JTextField txtMaHD;
    private JTextField txtNgayLap;
    private JTextField txtTongSL;
    private JTextField txtTongTienSP;
    private JTextField txtDiemTichDuoc;
    private JTextField txtDiemSuDung;
    private JTextField txtTienHoanLai;
    private JTextField txtTienKhachDua;
    private JTable table_dscho;
    private JLabel lblTongKhuyenMai;
    private JLabel lblTongThue;
    private JTextField txtTongKhuyenMai;
    private JLabel lblKhuyenMai;
    private JComboBox cboKhuyenMai;
    private JTextField txtTongThue;
    private JTextField txtTongThanhToan;
    private JButton btnThanhToan;
    private JButton btnHuyHD;
    private JButton btnLamMoiHD;
    private JButton btnHangCho;
    private JComboBox cboTimSP;
    private JTable table_SP;
    private JButton btnLamMoiThanhTimKiem;
    private JButton btnThemSP;
    private JButton btnXoaAll;
    public static TaiKhoan taiKhoan;
    public static int i = 0;
    private LapHoaDonDao LHD_dao = new LapHoaDonImpl();
    private FrmXuatHoaDon frmXuatHD = new FrmXuatHoaDon();
    private XemHoaDonDao xem_dao = new XemHoaDonImpl();
    private NhanVienDao dao = new NhanVienImpl();
    private SanPhamDao daoSP = new SanPhamImpl();
    public JTable table_CTHD;
    private JTextField txtSoLuong;

    DecimalFormat tien = new DecimalFormat(",##0");
    DecimalFormat tienNoString = new DecimalFormat("#.##");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private DateTimeFormatter formatter;

    int tongSL = 0; // Biến tổng số lượng sản phẩm
    double tongTienSP = 0; // Biến tổng tiền sản phẩm
    double tongThanhToan = 0; // Biến tổng thành toán
    double tyLeDiem = 0.01; // 1%
    float diemTichDuoc = 0; // Biến tổng điểm tích được
    double diemSD = 0;
    double tongThue = 0;
    double tongKM = 0;
    double donGia = 0;
    double thue = 0;
    double khuyenmai = 0;
    double thanhtien = 0;

    private double diemsudung;
    private double diemtl = 0;
    private double tienKMSum = 0;
    private double tongTienKM = 0;

    public String tenNhanVien = FrmDangNhap.taiKhoan.getTenTaiKhoan().getMaNhanvien();
    private JPanel pnlTieuDe;
    private JPanel pnlDanhSachCho;
    private JScrollPane scrDSHD;
    private JLabel lblSoDienThoaiKH;
    private JComboBox cboSDTKH;
    private JPanel panel_DonHang;
    private JPanel pnlThongTinKhachHang;
    private JLabel lblMaKH;
    private JLabel lblTenKH;
    private JLabel ldlSDT;
    private JLabel lblDiemTichLuy;
    private JPanel pnlThongTinHoaDon;
    private JLabel lblTienHoanLoai;
    private JLabel lblTienDua;
    private JLabel lblTongThanhToan;
    private JLabel lblDiemSuDung;
    private JLabel lblDiemTichDuoc;
    private JLabel lblTongTienThanhToan;
    private JLabel lblTongSoLuong;
    private JLabel lblNgayLap;
    private JLabel lblMaHoanDon;
    private JPanel panel_DSSP;
    private JLabel lblMaSP;
    private JPanel panel_CTHD;
    private JButton btnXoaSP;
    private JTextField textField;
    private JLabel lblloisdt;
    private JLabel lblloihd;

    private static final String URL = "rmi://HOANGPHUC:6541/";


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LapHoaDonDao lapHoaDonDao = (LapHoaDonDao) Naming.lookup(URL + "LapHoaDonDao");
                    NhanVienDao nhanVienDao = (NhanVienDao) Naming.lookup(URL + "NhanVienDao");
                    SanPhamDao sanPhamDao = (SanPhamDao) Naming.lookup(URL + "SanPhamDao");
                    XemHoaDonDao xemHoaDonDao = (XemHoaDonDao) Naming.lookup(URL + "XemHoaDonDao");
                    FrmLapHoaDon frame = new FrmLapHoaDon();
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public FrmLapHoaDon() throws RemoteException {
        pnlThongTin = new JPanel();
        getContentPane().setBackground(new Color(129, 250, 243));
        getContentPane().setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1347, 843);
        setLocationRelativeTo(null);
        setResizable(false);

        pnlThongTin.setBackground(new Color(255, 255, 255));
        pnlThongTin.setBounds(0, 0, 1333, 806);
        getContentPane().add(pnlThongTin);
        pnlThongTin.setLayout(null);
        javax.swing.border.Border southborder = BorderFactory.createLineBorder(Color.black);

        pnlTieuDe = new JPanel();
        pnlTieuDe.setBackground(new Color(0, 255, 255));
        pnlTieuDe.setBounds(0, 0, 1343, 41);
        pnlThongTin.add(pnlTieuDe);
        pnlTieuDe.setLayout(null);
        lblTieuDeTrang = new JLabel("LẬP HÓA ĐƠN");
        lblTieuDeTrang.setBounds(512, 12, 305, 25);
        pnlTieuDe.add(lblTieuDeTrang);
        lblTieuDeTrang.setBackground(new Color(0, 255, 255));
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));

        pnlDanhSachCho = new JPanel();
        pnlDanhSachCho.setBorder(
                new TitledBorder(null, "Danh sách hóa đơn chờ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnlDanhSachCho.setBackground(new Color(255, 255, 255));
        pnlDanhSachCho.setBounds(20, 48, 977, 172);
        pnlThongTin.add(pnlDanhSachCho);
        pnlDanhSachCho.setLayout(null);

        scrDSHD = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrDSHD.setPreferredSize(new Dimension(0, 250));
        scrDSHD.setBounds(10, 52, 957, 110);
        pnlDanhSachCho.add(scrDSHD);

        table_dscho = new JTable();
        table_dscho.setBackground(new Color(255, 255, 255));

        table_dscho.setModel(new DefaultTableModel(new Object[][]{},
                new String[]{"Mã HD", "Khách Hàng", "Mã nhân viên", "Số lượng sản phẩm", "Thời gian tạo"}));
        scrDSHD.setViewportView(table_dscho);

        lblSoDienThoaiKH = new JLabel("Nhập số điện thoại khách hàng:");
        lblSoDienThoaiKH.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblSoDienThoaiKH.setBounds(10, 28, 170, 15);
        pnlDanhSachCho.add(lblSoDienThoaiKH);

        cboSDTKH = new JComboBox();
        cboSDTKH.setEditable(true);
        cboSDTKH.setBounds(190, 21, 340, 21);
        pnlDanhSachCho.add(cboSDTKH);

        lblloisdt = new JLabel("");
        lblloisdt.setForeground(Color.RED);
        lblloisdt.setBackground(new Color(255, 255, 255));
        lblloisdt.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblloisdt.setBounds(562, 21, 350, 20);
        pnlDanhSachCho.add(lblloisdt);
        lblloisdt.setHorizontalAlignment(SwingConstants.CENTER);

        panel_DonHang = new JPanel();
        panel_DonHang.setForeground(new Color(0, 0, 0));
        panel_DonHang.setBackground(new Color(255, 255, 255));
        panel_DonHang.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "\u0110\u01A1n h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_DonHang.setBounds(1007, 48, 298, 757);
        pnlThongTin.add(panel_DonHang);
        panel_DonHang.setLayout(null);

        pnlThongTinKhachHang = new JPanel();
        pnlThongTinKhachHang.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlThongTinKhachHang.setBackground(new Color(255, 255, 255));
        pnlThongTinKhachHang.setBounds(10, 23, 278, 160);
        panel_DonHang.add(pnlThongTinKhachHang);
        pnlThongTinKhachHang.setLayout(null);

        lblMaKH = new JLabel("Mã khách hàng");
        lblMaKH.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblMaKH.setBounds(10, 10, 116, 18);
        pnlThongTinKhachHang.add(lblMaKH);

        lblTenKH = new JLabel("Tên khách hàng");
        lblTenKH.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblTenKH.setBounds(10, 48, 116, 18);
        pnlThongTinKhachHang.add(lblTenKH);

        ldlSDT = new JLabel("Số điện thoại");
        ldlSDT.setFont(new Font("Times New Roman", Font.BOLD, 15));
        ldlSDT.setBounds(10, 84, 116, 18);
        pnlThongTinKhachHang.add(ldlSDT);

        lblDiemTichLuy = new JLabel("Điểm tích lũy");
        lblDiemTichLuy.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblDiemTichLuy.setBounds(10, 129, 116, 18);
        pnlThongTinKhachHang.add(lblDiemTichLuy);

        txtMaKH = new JTextField();
        txtMaKH.setEditable(false);
        txtMaKH.setBounds(136, 11, 114, 19);
        pnlThongTinKhachHang.add(txtMaKH);
        txtMaKH.setColumns(10);

        txtTenKH = new JTextField();
        txtTenKH.setEditable(false);
        txtTenKH.setColumns(10);
        txtTenKH.setBounds(136, 49, 114, 19);
        pnlThongTinKhachHang.add(txtTenKH);

        txtSDT = new JTextField();
        txtSDT.setColumns(10);
        txtSDT.setBounds(136, 85, 114, 28);
        pnlThongTinKhachHang.add(txtSDT);

        txtDiemTL = new JTextField();
        txtDiemTL.setEditable(false);
        txtDiemTL.setColumns(10);
        txtDiemTL.setBounds(136, 130, 114, 19);
        pnlThongTinKhachHang.add(txtDiemTL);

        pnlThongTinHoaDon = new JPanel();
        pnlThongTinHoaDon.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlThongTinHoaDon.setBackground(Color.WHITE);
        pnlThongTinHoaDon.setBounds(10, 193, 278, 480);
        panel_DonHang.add(pnlThongTinHoaDon);
        pnlThongTinHoaDon.setLayout(null);

        lblTienHoanLoai = new JLabel("Tiền hoàn lại");
        lblTienHoanLoai.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblTienHoanLoai.setBounds(10, 429, 116, 18);
        pnlThongTinHoaDon.add(lblTienHoanLoai);

        lblTienDua = new JLabel("Tiền khách đưa");
        lblTienDua.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblTienDua.setBounds(10, 391, 116, 18);
        pnlThongTinHoaDon.add(lblTienDua);

        lblTongThanhToan = new JLabel("Tổng thanh toán");
        lblTongThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblTongThanhToan.setBounds(10, 353, 116, 18);
        pnlThongTinHoaDon.add(lblTongThanhToan);

        lblDiemSuDung = new JLabel("Điểm sử dụng");
        lblDiemSuDung.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblDiemSuDung.setBounds(10, 201, 116, 18);
        pnlThongTinHoaDon.add(lblDiemSuDung);

        lblDiemTichDuoc = new JLabel("Điểm tích được");
        lblDiemTichDuoc.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblDiemTichDuoc.setBounds(10, 163, 116, 18);
        pnlThongTinHoaDon.add(lblDiemTichDuoc);

        lblTongTienThanhToan = new JLabel("Tổng tiền SP");
        lblTongTienThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblTongTienThanhToan.setBounds(10, 124, 116, 18);
        pnlThongTinHoaDon.add(lblTongTienThanhToan);

        lblTongSoLuong = new JLabel("Tổng số lượng");
        lblTongSoLuong.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblTongSoLuong.setBounds(10, 86, 116, 18);
        pnlThongTinHoaDon.add(lblTongSoLuong);

        lblNgayLap = new JLabel("Ngày lập");
        lblNgayLap.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNgayLap.setBounds(10, 48, 116, 18);
        pnlThongTinHoaDon.add(lblNgayLap);

        lblMaHoanDon = new JLabel("Mã hóa đơn");
        lblMaHoanDon.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblMaHoanDon.setBounds(10, 9, 116, 18);
        pnlThongTinHoaDon.add(lblMaHoanDon);

        txtMaHD = new JTextField();
        txtMaHD.setHorizontalAlignment(SwingConstants.CENTER);
        txtMaHD.setFont(new Font("Times New Roman", Font.BOLD, 10));
        txtMaHD.setEditable(false);
        txtMaHD.setColumns(10);
        txtMaHD.setBounds(136, 11, 114, 19);
        pnlThongTinHoaDon.add(txtMaHD);

        txtNgayLap = new JTextField();
        txtNgayLap.setHorizontalAlignment(SwingConstants.CENTER);
        txtNgayLap.setFont(new Font("Times New Roman", Font.BOLD, 12));
        txtNgayLap.setEditable(false);
        txtNgayLap.setColumns(10);
        txtNgayLap.setBounds(136, 49, 114, 19);
        pnlThongTinHoaDon.add(txtNgayLap);

        txtTongSL = new JTextField();
        txtTongSL.setEditable(false);
        txtTongSL.setColumns(10);
        txtTongSL.setBounds(136, 87, 114, 19);
        pnlThongTinHoaDon.add(txtTongSL);

        txtTongTienSP = new JTextField();
        txtTongTienSP.setEditable(false);
        txtTongTienSP.setColumns(10);
        txtTongTienSP.setBounds(136, 125, 114, 19);
        pnlThongTinHoaDon.add(txtTongTienSP);

        txtDiemTichDuoc = new JTextField();
        txtDiemTichDuoc.setEditable(false);
        txtDiemTichDuoc.setColumns(10);
        txtDiemTichDuoc.setBounds(136, 163, 114, 19);
        pnlThongTinHoaDon.add(txtDiemTichDuoc);

        txtDiemSuDung = new JTextField();
        txtDiemSuDung.setColumns(10);
        txtDiemSuDung.setBounds(136, 201, 114, 19);
        txtDiemSuDung.setEditable(false);
        pnlThongTinHoaDon.add(txtDiemSuDung);

        txtTongThanhToan = new JTextField();
        txtTongThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 12));
        txtTongThanhToan.setEditable(false);
        txtTongThanhToan.setColumns(10);
        txtTongThanhToan.setBounds(136, 353, 114, 19);
        pnlThongTinHoaDon.add(txtTongThanhToan);

        txtTienHoanLai = new JTextField();
        txtTienHoanLai.setEditable(false);
        txtTienHoanLai.setHorizontalAlignment(SwingConstants.CENTER);
        txtTienHoanLai.setFont(new Font("Times New Roman", Font.BOLD, 13));
        txtTienHoanLai.setColumns(10);
        txtTienHoanLai.setBounds(136, 429, 114, 25);
        pnlThongTinHoaDon.add(txtTienHoanLai);

        txtTienKhachDua = new JTextField();
        txtTienKhachDua.setColumns(10);
        txtTienKhachDua.setBounds(136, 391, 114, 25);
        pnlThongTinHoaDon.add(txtTienKhachDua);

        btnThanhToan = new JButton("Thanh toán");
        btnThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnThanhToan.setIcon(new ImageIcon("Anh\\thanhtoan.png"));
        btnThanhToan.setBackground(new Color(50, 205, 50));
        btnThanhToan.setBounds(10, 712, 137, 35);
        panel_DonHang.add(btnThanhToan);

        btnHuyHD = new JButton("Hủy hóa đơn");
        btnHuyHD.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnHuyHD.setIcon(new ImageIcon("Anh\\huy.png"));
        btnHuyHD.setBackground(new Color(0, 255, 255));
        btnHuyHD.setBounds(10, 680, 137, 29);
        panel_DonHang.add(btnHuyHD);

        btnLamMoiHD = new JButton("Làm mới");
        btnLamMoiHD.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnLamMoiHD.setIcon(new ImageIcon("Anh\\lammoi.png"));
        btnLamMoiHD.setBackground(new Color(0, 255, 255));
        btnLamMoiHD.setBounds(157, 680, 131, 29);
        panel_DonHang.add(btnLamMoiHD);

        btnHangCho = new JButton("Hàng chờ");
        btnHangCho.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnHangCho.setIcon(new ImageIcon("Anh\\hangcho.png"));
        btnHangCho.setBackground(new Color(255, 165, 0));
        btnHangCho.setBounds(157, 712, 131, 35);
        panel_DonHang.add(btnHangCho);

        panel_DSSP = new JPanel();
        panel_DSSP.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Danh s\u00E1ch s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_DSSP.setBackground(Color.WHITE);
        panel_DSSP.setBounds(20, 230, 977, 127);
        pnlThongTin.add(panel_DSSP);
        panel_DSSP.setLayout(null);

        lblMaSP = new JLabel("Mã sản phẩm:");
        lblMaSP.setBounds(20, 22, 88, 15);
        lblMaSP.setFont(new Font("Times New Roman", Font.BOLD, 12));
        panel_DSSP.add(lblMaSP);

        JScrollPane scrDSNV;
        String[] tb2 = new String[]{"Mã", "Tên sản phẩm", "Loại sản phẩm", "Màu sắc", "Size", "Chất liệu", "Số lượng",
                "Đơn gía", "VAT", "Khuyến mãi(%)"};
        DefaultTableModel tablemodel = new DefaultTableModel(tb2, 0);
        table_SP = new JTable(tablemodel);

        table_SP.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        table_SP.setBackground(new Color(224, 255, 255));
        table_SP.setForeground(new Color(0, 0, 0));
        getContentPane().add(scrDSNV = new JScrollPane(table_SP, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
        scrDSNV.setBounds(10, 50, 957, 67);
        panel_DSSP.add(scrDSNV);
        scrDSNV.setPreferredSize(new Dimension(0, 250));

        cboTimSP = new JComboBox();
        cboTimSP.setEditable(true);
        cboTimSP.setBounds(130, 17, 300, 21);
        panel_DSSP.add(cboTimSP);

        btnLamMoiThanhTimKiem = new JButton("Làm mới");
        btnLamMoiThanhTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnLamMoiThanhTimKiem.setIcon(new ImageIcon("Anh\\lammoi.png"));
        btnLamMoiThanhTimKiem.setBackground(new Color(0, 255, 255));
        btnLamMoiThanhTimKiem.setBounds(850, 16, 120, 27);
        panel_DSSP.add(btnLamMoiThanhTimKiem);

        btnThemSP = new JButton("Thêm");
        btnThemSP.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnThemSP.setIcon(new ImageIcon("Anh\\themsp.png"));
        btnThemSP.setBackground(new Color(0, 255, 255));
        btnThemSP.setBounds(720, 16, 120, 27);
        panel_DSSP.add(btnThemSP);

        panel_CTHD = new JPanel();
        panel_CTHD.setBorder(new TitledBorder(null, "Chi ti\u1EBFt h\u00F3a \u0111\u01A1n", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        panel_CTHD.setBackground(new Color(255, 255, 255));
        panel_CTHD.setBounds(21, 367, 976, 440);
        pnlThongTin.add(panel_CTHD);
        panel_CTHD.setLayout(null);

        btnXoaAll = new JButton("Xóa tất cả");
        btnXoaAll.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnXoaAll.setIcon(new ImageIcon("Anh\\xoaall.png"));
        btnXoaAll.setBackground(new Color(0, 255, 255));
        btnXoaAll.setBounds(540, 390, 149, 29);
        panel_CTHD.add(btnXoaAll);

        JScrollPane scrDSNV1;
        String[] tb3 = new String[]{"STT", "Mã sản phẩm", "Tên sản phẩm", "Màu sắc", "Size", "Chất liệu", "Đơn giá",
                "Số lượng bán", "VAT", "Khuyến mãi", "Thành tiền"};
        DefaultTableModel modelNEw = new DefaultTableModel(tb3, 0);
        table_CTHD = new JTable(new DefaultTableModel(new Object[][]{}, tb3));

        table_CTHD.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
        table_CTHD.getColumnModel().getColumn(7)
                .setCellEditor(new ButtonEditor(new JTextField(), table_CTHD, table_SP, this, cboTimSP));

        lblLoiSP = new JLabel("");
        lblLoiSP.setHorizontalAlignment(SwingConstants.CENTER);
        lblLoiSP.setForeground(Color.RED);
        lblLoiSP.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblLoiSP.setBackground(Color.WHITE);
        lblLoiSP.setBounds(440, 17, 270, 20);
        panel_DSSP.add(lblLoiSP);

        table_CTHD.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        table_CTHD.setBackground(new Color(224, 255, 255));
        table_CTHD.setForeground(new Color(0, 0, 0));
        getContentPane().add(scrDSNV1 = new JScrollPane(table_CTHD, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
        scrDSNV1.setBounds(10, 21, 956, 359);
        panel_CTHD.add(scrDSNV1);
        scrDSNV1.setPreferredSize(new Dimension(0, 250));

        btnXoaSP = new JButton("Xóa 1 sản phẩm");
        btnXoaSP.setIcon(new ImageIcon("Anh\\xoa1sp.png"));
        btnXoaSP.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnXoaSP.setBackground(Color.CYAN);
        btnXoaSP.setBounds(367, 390, 149, 29);
        panel_CTHD.add(btnXoaSP);

        docDuLieuSP();
        updateComBoBox();
        txtMaHD.setText(updateMaHD());
//		updateMaHD();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy , hh:mm");
        txtNgayLap.setText(LocalDateTime.now().format(formatter));

        lblTongKhuyenMai = new JLabel("Tổng khuyến mãi");
        lblTongKhuyenMai.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblTongKhuyenMai.setBounds(10, 277, 116, 18);
        pnlThongTinHoaDon.add(lblTongKhuyenMai);

        lblTongThue = new JLabel("Tổng tiền thuế");
        lblTongThue.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblTongThue.setBounds(10, 315, 116, 18);
        pnlThongTinHoaDon.add(lblTongThue);

        txtTongKhuyenMai = new JTextField();
        txtTongKhuyenMai.setFont(new Font("Times New Roman", Font.BOLD, 10));
        txtTongKhuyenMai.setEditable(false);
        txtTongKhuyenMai.setColumns(10);
        txtTongKhuyenMai.setBounds(136, 277, 114, 19);
        pnlThongTinHoaDon.add(txtTongKhuyenMai);

        lblKhuyenMai = new JLabel("Khuyến mãi");
        lblKhuyenMai.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblKhuyenMai.setBounds(10, 239, 116, 18);
        pnlThongTinHoaDon.add(lblKhuyenMai);

        cboKhuyenMai = new JComboBox();
        cboKhuyenMai.setEditable(true);
        cboKhuyenMai.setBounds(134, 239, 116, 21);
        for (int i = 0; i <= 100; i += 5) {
            cboKhuyenMai.addItem(i);
        }
        pnlThongTinHoaDon.add(cboKhuyenMai);

        txtTongThue = new JTextField();
        txtTongThue.setFont(new Font("Tahoma", Font.PLAIN, 10));
        txtTongThue.setEditable(false);
        txtTongThue.setColumns(10);
        txtTongThue.setBounds(136, 316, 114, 19);
        pnlThongTinHoaDon.add(txtTongThue);

        lblloihd = new JLabel("");
        lblloihd.setForeground(Color.RED);
        lblloihd.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblloihd.setBackground(Color.WHITE);
        lblloihd.setBounds(10, 457, 250, 20);
        pnlThongTinHoaDon.add(lblloihd);

        txtSoLuong = new JTextField();
        txtSoLuong.setEditable(false);
        DefaultCellEditor colSoLuong = new DefaultCellEditor(txtSoLuong);

        btnLamMoiThanhTimKiem.addActionListener(this);
        btnThemSP.addActionListener(this);
        btnThanhToan.addActionListener(this);
        btnLamMoiHD.addActionListener(this);
        btnHuyHD.addActionListener(this);
        btnHangCho.addActionListener(this);
        cboTimSP.addActionListener(this);
        btnXoaAll.addActionListener(this);
        cboSDTKH.addActionListener(this);
        cboKhuyenMai.addActionListener(this);
        btnXoaSP.addActionListener(this);

        textField = (JTextField) cboSDTKH.getEditor().getEditorComponent();
        textField.addKeyListener(this);
        txtSDT.addKeyListener(this);
        txtTienKhachDua.addKeyListener(this);
        txtSoLuong.addKeyListener(this);
        table_dscho.addMouseListener(this);
        table_CTHD.addMouseListener(this);
        txtTienKhachDua.getDocument().addDocumentListener(this);
        txtDiemSuDung.getDocument().addDocumentListener(this);

    }

    //  Thực hiện các chức năng
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnLamMoiThanhTimKiem)) {
            XoatrangthanhTK();
        } else if (o.equals(btnLamMoiHD)) {
            LammoiHD();
        } else if (o.equals(btnHuyHD)) {
            HuyHD();
            xoatableCTHD();
            XoatrangthanhTK();
            xoaSLSanPham();
        } else if (o.equals(btnThemSP)) {
//            Themsoluong();
            try {
                Themsoluong();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else if (o.equals(btnHangCho)) {
            themHangCho();
        } else if (o.equals(btnThanhToan)) {
            thanhToan();
        } else if (o.equals(cboTimSP)) {
            chonSanPham();
        } else if (o.equals(btnXoaAll)) {
            XoatrangthanhTK();
            xoaSLSanPham();
            xoatableCTHD();
        } else if (o.equals(cboSDTKH)) {
            chonKhachHang();
        } else if (o.equals(btnXoaSP)) {
            xoaSanPham();
        } else if (o.equals(cboKhuyenMai)) {
            tinhThongTinThanhToan();
        }

    }

    private void updateTextFieldsWithHoaDonInfo(Map<String, Object> hoaDonVaChiTiet) {

        txtMaHD.setText(hoaDonVaChiTiet.get("MaHoaDon").toString());
        txtSDT.setText(hoaDonVaChiTiet.get("SoDienThoai").toString());
        txtDiemTichDuoc.setText(hoaDonVaChiTiet.get("DiemTichDuoc").toString());
        txtTongSL.setText(hoaDonVaChiTiet.get("SoLuong").toString());

        txtTongTienSP.setText(hoaDonVaChiTiet.get("TongTienSP").toString());
        txtDiemTL.setText(hoaDonVaChiTiet.get("DiemTichLuy").toString());
        txtDiemSuDung.setText(hoaDonVaChiTiet.get("DiemSuDung").toString());
        txtTongThanhToan.setText(hoaDonVaChiTiet.get("TongThanhToan").toString());
        txtMaKH.setText(hoaDonVaChiTiet.get("MaKhachHang").toString());
        txtTenKH.setText(hoaDonVaChiTiet.get("TenKhachHang").toString());
        tongTienSP = Double.parseDouble(txtTongTienSP.getText().replace("VNĐ", "").replace(",", ""));
        txtTongThue.setText(hoaDonVaChiTiet.get("TongThue").toString());
        txtTongKhuyenMai.setText(hoaDonVaChiTiet.get("TongKhuyenMai").toString());
        tongSL = Integer.parseInt(txtTongSL.getText());
        cboKhuyenMai.setSelectedItem(hoaDonVaChiTiet.get("PhanTramKhuyenMai").toString());
        tongThanhToan = Double.parseDouble(txtTongThanhToan.getText().replace("VNĐ", "").replace(",", ""));

    }

    private void updateTableCTHDWithChiTietHoaDon(Map<String, Object> hoaDonVaChiTiet) {
        DefaultTableModel model = (DefaultTableModel) table_CTHD.getModel();
        model.setRowCount(0);
        model.fireTableDataChanged();
        Object chiTietHoaDonObj = hoaDonVaChiTiet.get("ChiTietHoaDon");

        if (chiTietHoaDonObj instanceof List) {
            List<?> chiTietHoaDonList = (List<?>) chiTietHoaDonObj;
            int i = 0;
            for (Object chiTietObj : chiTietHoaDonList) {
                if (chiTietObj instanceof Map) {
                    Map<?, ?> chiTiet = (Map<?, ?>) chiTietObj;

                    int stt = Integer.parseInt(chiTiet.get("STT").toString());
                    String maSP = chiTiet.get("MaSanPham").toString();
                    String tenSanPham = chiTiet.get("TenSanPham").toString();
                    String mauSac = chiTiet.get("MauSac").toString();
                    String size = chiTiet.get("Size").toString();
                    String chatLieu = chiTiet.get("ChatLieu").toString();
                    String donGia = chiTiet.get("DonGia").toString();
                    int soLuongBan = Integer.parseInt(chiTiet.get("SoLuongBan").toString());
                    float vat = Float.parseFloat(chiTiet.get("VAT").toString().replace("VNĐ", "").replace(",", ""));
                    double khuyenMai = Double.parseDouble(chiTiet.get("KhuyenMai").toString().replace(",", ""));
                    String thanhTien = chiTiet.get("ThanhTien").toString();

                    model.addRow(new Object[]{stt, maSP, tenSanPham, mauSac, size, chatLieu, donGia, soLuongBan,
                            tien.format(vat), tienNoString.format(khuyenMai), thanhTien});
                    model.setValueAt(soLuongBan, i, 7);
                    model.fireTableCellUpdated(i, 7);
                    i++;
                }
            }
        }

    }

    private Map<String, Object> layThongTinHoaDonVaChiTiet() {
        Map<String, Object> hoaDonVaChiTiet = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy , HH:mm");
        String maHoaDon = txtMaHD.getText();
        String nhanVien = null;
        try {
            nhanVien = LHD_dao.getTenNV(tenNhanVien);
        } catch (Exception e) {
            nhanVien = "admin";
        }
        String ngayLap = LocalDateTime.now().format(formatter);
        String maKH = txtMaKH.getText();
        String hoTenKh = txtTenKH.getText();
        String diemtichluy = txtDiemTL.getText();

        String soDT = txtSDT.getText();
        String tongsl = txtTongSL.getText();
        String diemtichduoc = txtDiemTichDuoc.getText();
        String diemsudung = txtDiemSuDung.getText();
        String tongtiensp = txtTongTienSP.getText();
        String tongthue = txtTongThue.getText();
        String tongkm = txtTongKhuyenMai.getText();
        String phantramkhuyenmai = String.valueOf(cboKhuyenMai.getSelectedItem());
        String dtlhienco = tinhDTLHienCo(diemtichluy, diemsudung, diemtichduoc);
        String tongthanhtoan = txtTongThanhToan.getText();

        hoaDonVaChiTiet.put("MaHoaDon", maHoaDon);
        hoaDonVaChiTiet.put("NgayLap", ngayLap);
        hoaDonVaChiTiet.put("SoLuong", tongsl);
        hoaDonVaChiTiet.put("TongTienSP", tongtiensp);
        hoaDonVaChiTiet.put("DiemTichDuoc", diemtichduoc);
        hoaDonVaChiTiet.put("DiemSuDung", diemsudung);
        hoaDonVaChiTiet.put("PhanTramKhuyenMai", phantramkhuyenmai);
        hoaDonVaChiTiet.put("TongThue", tongthue);
        hoaDonVaChiTiet.put("TongKhuyenMai", tongkm);
        hoaDonVaChiTiet.put("TongThanhToan", tongthanhtoan);
        hoaDonVaChiTiet.put("SoDienThoai", soDT);
        hoaDonVaChiTiet.put("MaKhachHang", maKH);
        hoaDonVaChiTiet.put("DiemTichLuy", diemtichluy);
        hoaDonVaChiTiet.put("TenKhachHang", hoTenKh);
        hoaDonVaChiTiet.put("TenNhanVien", FrmDangNhap.taiKhoan.getTenTaiKhoan());

        // Lấy thông tin chi tiết hóa đơn từ bảng
        List<Map<String, Object>> chiTietHoaDonList = new ArrayList<>();
        for (int i = 0; i < table_CTHD.getRowCount(); i++) {
            Map<String, Object> chiTiet = new HashMap<>();
            chiTiet.put("STT", table_CTHD.getValueAt(i, 0));
            chiTiet.put("TenSanPham", table_CTHD.getValueAt(i, 2));
            chiTiet.put("MauSac", table_CTHD.getValueAt(i, 3));
            chiTiet.put("Size", table_CTHD.getValueAt(i, 4));
            chiTiet.put("ChatLieu", table_CTHD.getValueAt(i, 5));
            chiTiet.put("DonGia", table_CTHD.getValueAt(i, 6));
            chiTiet.put("SoLuongBan", table_CTHD.getValueAt(i, 7));
            chiTiet.put("VAT", table_CTHD.getValueAt(i, 8));
            chiTiet.put("KhuyenMai", table_CTHD.getValueAt(i, 9));
            chiTiet.put("ThanhTien", table_CTHD.getValueAt(i, 10));
            chiTiet.put("MaSanPham", table_CTHD.getValueAt(i, 1));
            chiTietHoaDonList.add(chiTiet);
        }

        // Thêm hóa đơn và chi tiết hóa đơn vào Map
        hoaDonVaChiTiet.put("HoaDon", hoaDonVaChiTiet);
        hoaDonVaChiTiet.put("ChiTietHoaDon", chiTietHoaDonList);
        DefaultTableModel model = (DefaultTableModel) table_CTHD.getModel();

        model.setRowCount(0);
        updateMaHD();
        HuyHD();
        xoatableCTHD();
        return hoaDonVaChiTiet;
    }

    private Map<String, Object> danhSachHoaDon = new HashMap<>();
    private DefaultTableModel model;
    private JLabel lblLoiSP;

    //	Kiểm tra trùng sản phẩm
    private boolean kiemTraTrung(String masp) {

        for (int i = 0; i < table_CTHD.getRowCount(); i++) {
            if (table_CTHD.getValueAt(i, 1).toString().equals(masp))
                return true;
        }
        return false;
    }

    //	Lấy ra số lượng ban đầu của sản phẩm
    private int getSoLuongBanDau(String masp) {

        for (int i = 0; i < table_SP.getRowCount(); i++) {
            if (table_SP.getValueAt(i, 0).toString().equals(masp))
                return Integer.parseInt(table_SP.getValueAt(i, 6).toString());
        }
        return 0;
    }

    //	Thêm sản phẩm mới vào danh sách
    public void Themsoluong() throws RemoteException {
        int selectedRow = table_SP.getSelectedRow();
        int n = table_CTHD.getRowCount() + 1;

        if (selectedRow >= 0) {
            String masp1 = (String) table_SP.getValueAt(selectedRow, 0);
            if (!kiemTraTrung(masp1)) {
                boolean check = true;

                while (check) {
                    String soLuongSPObj = JOptionPane.showInputDialog("Nhập số lượng sản phẩm mua.",
                            JOptionPane.YES_NO_CANCEL_OPTION);

                    if (soLuongSPObj == null) {
                        lblLoiSP.setText("Thêm thất bại");
                        check = false;
                    } else {
                        lblLoiSP.setText("");
                        if (soLuongSPObj.equals("0")) {
                            lblLoiSP.setText("Số lượng sản phẩm phải lớn hơn 0.");
                        } else if (soLuongSPObj.matches("^[0-9]+$")) {
                            int slInt = Integer.parseInt(soLuongSPObj);
                            int soLuongDaBan = 0;
                            try {
                                soLuongDaBan = LHD_dao.soLuongSPDaBan(masp1);
                            } catch (Exception e) {
                                soLuongDaBan = 0;
                            }
                            int soLuongNhap = 0;
                            try {
                                soLuongNhap = LHD_dao.soLuongNhap(masp1);
                            } catch (Exception e) {
                                soLuongNhap = 0;
                            }

                            if ((slInt) <= soLuongNhap) {
                                SanPham x = null;
                                try {
                                    x = daoSP.getMa(masp1);
                                } catch (Exception e) {
                                    x = null;
                                }
                                KhuyenMai khuyenMai = x.getKhuyenMai();
                                String TenKm = null;
                                if (khuyenMai != null) {
                                    TenKm = null;
                                    try {
                                        TenKm = LHD_dao.getKMTheoTen(khuyenMai.getMaKhuyenMai());
                                    } catch (Exception e) {
                                        TenKm = null;
                                    }
                                } else {
                                    // Handle the case where khuyenMai is null
                                }
                                int km = 0;
                                try {
                                    km = LHD_dao.getKMTheoPhanTram(TenKm);
                                } catch (Exception e) {
                                    km = 0;
                                }
                                double giaBan = x.getGiaBan();
                                double soTienKhuyenMai = ((giaBan * km) / 100);
                                double tienKM = 0;
                                DefaultTableModel modelNEw = (DefaultTableModel) table_CTHD.getModel();

                                float VAT = x.getVat();
                                if (x.getVat() == 1) {

                                    VAT = (float) (tinhGiaBan(x.getGianhap()) * 0.05);
                                } else
                                    VAT = 0;
                                double tongGia = 0;
                                int tongsl = 0;
                                double thanhtiencuoi = 0;

                                int soLuongBanDau = getSoLuongBanDau(x.getMaSp());
                                int soLuongConLai = soLuongBanDau - slInt;
                                table_SP.setValueAt(soLuongConLai, selectedRow, 6);
                                double thanhtien = (x.getGiaBan() - soTienKhuyenMai) * slInt;

                                modelNEw.addRow(new Object[]{table_CTHD.getRowCount() + 1, x.getMaSp(), x.getTensp(),
                                        x.getMauSac(), x.getSize(), x.getChatLieu().getMaChatLieu(),
                                        tien.format(giaBan), slInt, tien.format(VAT), tien.format(soTienKhuyenMai),
                                        tien.format(thanhtien)});

                                tongsl += slInt;
                                thanhtiencuoi += thanhtien;
                                tongGia += x.getGiaBan() * slInt;
                                tienKM = soTienKhuyenMai * slInt;
                                tienKMSum += tienKM;

                                table_CTHD.setModel(modelNEw);
                                check = false;

                                int phanTramKhuyenMai = Integer.parseInt(cboKhuyenMai.getSelectedItem().toString());

                                double tongThue = 0;

                                // Tính tổng thuế dựa trên giá trị thuế của từng sản phẩm và số lượng sản phẩm
                                for (int i = 0; i < table_CTHD.getRowCount(); i++) {
                                    double giaTriThue = Double
                                            .parseDouble(table_CTHD.getValueAt(i, 8).toString().replace(",", ""));
                                    int soLuong = Integer.parseInt(table_CTHD.getValueAt(i, 7).toString());
                                    tongThue += giaTriThue * soLuong;
                                }

                                // Cập nhật tổng số lượng, tổng tiền sản phẩm và tổng thuế
                                tongSL += tongsl;
                                tongTienSP += tongGia;

                                double phanTramGiamGia = phanTramKhuyenMai / 100.0;
                                double giamGia = tongTienSP * phanTramGiamGia;

                                tongThanhToan = tongTienSP - giamGia - diemsudung - (tienKMSum + giamGia);

                                diemTichDuoc = (float) (tongThanhToan * tyLeDiem);

                                // Cập nhật các JTextField
                                txtTongSL.setText(String.valueOf(tongSL));
                                txtTongTienSP.setText(tien.format(tongTienSP) + " VNĐ");
                                txtTongThanhToan.setText(String.valueOf(tien.format(tongThanhToan) + " VNĐ"));
                                txtDiemTichDuoc.setText(String.valueOf(tienNoString.format(diemTichDuoc)));
                                txtTongThue.setText(String.valueOf(tien.format(tongThue) + " VNĐ"));

                                txtTongKhuyenMai.setText(String.valueOf(tien.format(tienKMSum + giamGia)) + " VNĐ");

                            } else {
                                lblLoiSP.setText("Số lượng sản phẩm mua vượt quá số lượng còn lại.");
                            }
                        } else {
                            lblLoiSP.setText("Hãy nhập số nguyên dương.");
                        }
                    }
                }
            } else {
                lblLoiSP.setText("Sản phẩm đã tồn tại!");
            }
        } else {
            lblLoiSP.setText("Hãy nhập mã sản phẩm");
        }
    }

    public void timTenVaMaKhachHangBySDT(String sdt) {
        try {
            for (KhachHang khachHang : LHD_dao.timKhachHangBySDT(sdt)) {
                String maKH = khachHang.getMaKH();
                String tenKH = khachHang.getTenKH();
                float dtl = LHD_dao.getDiem(sdt);
                txtMaKH.setText(maKH);
                txtTenKH.setText(tenKH);
                txtDiemTL.setText(tienNoString.format(dtl));
            }
        } catch (Exception e) {
        }
    }

    public void docDuLieuSP() {
        List<SanPham> list = null;
        try {
            list = daoSP.getAllSP();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        DefaultTableModel tablemodel = (DefaultTableModel) table_SP.getModel();

        for (SanPham x : list) {
            KhuyenMai khuyenMai = x.getKhuyenMai();
            String tenKm = null;
            if (khuyenMai != null) {
                tenKm = null;
                try {
                    tenKm = LHD_dao.getKMTheoTen(khuyenMai.getMaKhuyenMai());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                // Handle the case where khuyenMai is null
            }
//            int km = LHD_dao.getKMTheoPhanTram(tenKm);
            try {
                int km = LHD_dao.getKMTheoPhanTram(tenKm);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

//            double giaBan = x.getGiaBan();
//            float VAT = x.getVat();
////            if (daoSP.vat(x.getMaSp()) == 1) {
//            try {
//
//                VAT = (float) (tinhGiaBan(x.getGianhap()) * 0.05);
//            } else
//                VAT = 0;
//        }
//    }
            double giaBan = x.getGiaBan();
            float VAT = x.getVat();

            if (x.getVat() == 1) {
                VAT = (float) (tinhGiaBan(x.getGianhap()) * 0.05);
            } else {
                VAT = 0;
            }
        }
    }

    public void updateComBoBox() throws RemoteException {
        List<SanPham> list = daoSP.getAllSP();
        for (SanPham x : list) {
            cboTimSP.addItem(x.getMaSp());
        }
    }

    public double tinhGiaBan(double giaNhap) {

        double m = 0;

        m = giaNhap * 2.5;

        return m;
    }

    public void XoatrangthanhTK() {
        cboTimSP.setSelectedItem("");
        deleteAllDataTable();
        docDuLieuSP();
    }

    public void LammoiHD() {
        txtDiemSuDung.setText("");
        txtTienKhachDua.setText("");
        txtTienHoanLai.setText("");
        cboKhuyenMai.setSelectedItem("0");
        txtDiemSuDung.requestFocus();
        lblloisdt.setText("");
        lblloihd.setText("");
        lblLoiSP.setText("");
    }

    public void HuyHD() {
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtSDT.setText("");
        txtDiemTL.setText("");
        txtDiemSuDung.setText("");
        txtDiemTichDuoc.setText("");
        txtTongSL.setText("");
        txtTienKhachDua.setText("");
        txtTongTienSP.setText("");
        txtTienHoanLai.setText("");
        txtTongThanhToan.setText("");
        txtSDT.requestFocus();
        cboKhuyenMai.setSelectedItem("0");
        txtTongKhuyenMai.setText("");
        txtTongThue.setText("");
        lblloisdt.setText("");
        lblloihd.setText("");
        lblLoiSP.setText("");
        deleteAllDataTable();
        docDuLieuSP();
        xoatableCTHD();
    }

    public void tinhThongTinThanhToan() {
        tongSL = 0;
        tongTienSP = 0;
        tongThanhToan = 0;
        tongThue = 0;
        tongKM = 0;
        donGia = 0;
        thue = 0;
        khuyenmai = 0;
        tienKMSum = 0;

        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");

        // Lấy giá trị khuyến mãi từ JComboBox cboKhuyenMai
        double giamGia = Double.parseDouble(cboKhuyenMai.getSelectedItem().toString()) / 100.0;

        for (int i = 0; i < table_CTHD.getRowCount(); i++) {
            int soLuong = Integer.parseInt(table_CTHD.getValueAt(i, 7).toString());
            donGia = Double.parseDouble(table_CTHD.getValueAt(i, 6).toString().replace(",", ""));
            thanhtien = Double.parseDouble(table_CTHD.getValueAt(i, 10).toString().replace(",", ""));
            thue = Double.parseDouble(table_CTHD.getValueAt(i, 8).toString().replace(",", ""));
            khuyenmai = Double.parseDouble(table_CTHD.getValueAt(i, 9).toString().replace(",", ""));

            // Tính tổng tiền sản phẩm sau khi trừ khuyến mãi và thuế
            tongSL += soLuong;
            tienKMSum += soLuong * khuyenmai;
            tongThanhToan += thanhtien;
            tongThue += thue * tongSL;
            tongTienSP += donGia * soLuong;
        }

        txtTongSL.setText(tongSL + "");

        double khuyenMaiTrenHoaDon = giamGia * tongThanhToan;
        double tongKhuyenMaiTrenHoaDon = khuyenMaiTrenHoaDon;

        // Tính toán tổng tiền sau khi áp dụng khuyến mãi
        tongThanhToan = tongThanhToan - tongKhuyenMaiTrenHoaDon;
        diemTichDuoc = (float) (tongThanhToan * tyLeDiem);
        txtTongKhuyenMai.setText(tien.format(tongKhuyenMaiTrenHoaDon + tienKMSum) + " VNĐ");
        txtTongTienSP.setText(tien.format(tongTienSP) + " VNĐ");
        txtDiemSuDung.setText("0");
        txtTongThanhToan.setText(tien.format(tongThanhToan) + " VNĐ");
        txtDiemTichDuoc.setText(tienNoString.format(diemTichDuoc));
        txtTongThue.setText(tien.format(tongThue) + " VNĐ");

    }

    // Xóa thông tin bảng
    public void deleteAllDataTable() {
        tablemodel.addRow(new Object[]{});
        tablemodel = (DefaultTableModel) table_SP.getModel();
        tablemodel.getDataVector().removeAllElements();
    }

    public void xoatableCTHD() {
        for (int j = 0; j < table_CTHD.getRowCount(); j++) {
            for (int i = 0; i < table_SP.getRowCount(); i++) {
                if (table_SP.getValueAt(i, 0).toString().equals(table_CTHD.getValueAt(j, 1).toString()))
                    table_SP.setValueAt(Integer.parseInt(table_SP.getValueAt(i, 6).toString())
                            + Integer.parseInt(table_CTHD.getValueAt(j, 7).toString()), i, 6);
            }
        }
        DefaultTableModel model = (DefaultTableModel) table_CTHD.getModel();
        model.setRowCount(0);
        tongSL = 0;
        tongTienSP = 0;
        tongThanhToan = 0;
        tyLeDiem = 0.01;
        txtTongSL.setText("");
        txtDiemTichDuoc.setText("");
        txtDiemSuDung.setText("");
        txtTongTienSP.setText("");
        txtTongThanhToan.setText("");
        txtTienHoanLai.setText("");
        txtTongKhuyenMai.setText("");
        txtTongThue.setText("");
        lblloisdt.setText("");
        lblloihd.setText("");
        lblLoiSP.setText("");
        tienKMSum = 0;
        // Cập nhật giao diện người dùng
        model.fireTableDataChanged();
    }

    public String updateMaHD() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
        String ngayLap = LocalDateTime.now().format(formatter);
        int n = 0;
        try {
            n = LHD_dao.soLuongHD() + 1 + table_dscho.getRowCount();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        String soLuongHDDformatted = String.format("%04d", n);
        String maHoaDon = "HD" + soLuongHDDformatted + tenNhanVien + ngayLap;

        List<HoaDon> hd = null;
        try {
            hd = LHD_dao.getAllLapHoaDon();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        model = (DefaultTableModel) table_dscho.getModel();
        int count = model.getRowCount();
        for (HoaDon x : hd) {
            if (tenNhanVien.equals("admin")) {
                maHoaDon = "HD" + soLuongHDDformatted + "NV000" + ngayLap;
            } else {
                while (x.getMaHoaDon().equalsIgnoreCase(maHoaDon)) {
                    n++;
                    soLuongHDDformatted = String.format("%04d", n);
                    maHoaDon = "HD" + soLuongHDDformatted + tenNhanVien + ngayLap;
                }
            }
        }

        n++;

        return maHoaDon;
    }

    // Tính điểm tích lũy hiện có
    public String tinhDTLHienCo(String diemtichluy, String diemsudung, String diemtichduoc) {
        double diemtichluyValue = Double.parseDouble(diemtichluy.replace(",", ""));
        double diemsudungValue = Double.parseDouble(diemsudung.replace(",", ""));
        double diemtichduocValue = Double.parseDouble(diemtichduoc.replace(",", ""));
        double dtlhiencoValue = diemtichluyValue - diemsudungValue + diemtichduocValue;
        return String.valueOf(dtlhiencoValue);
    }

    // Tính điểm tích lũy hiện có
    public double tinhDTLHienCoDouble(String diemtichluy, String diemsudung, String diemtichduoc) {
        double diemtichluyValue = Double.parseDouble(diemtichluy.replace(",", ""));
        double diemsudungValue = Double.parseDouble(diemsudung.replace(",", ""));
        double diemtichduocValue = Double.parseDouble(diemtichduoc.replace(",", ""));
        double dtlhiencoValue = diemtichluyValue - diemsudungValue + diemtichduocValue;
        return dtlhiencoValue;
    }

    // Phương thức kiểm tra Double hợp lệ
    private boolean isValidDouble(String input) {
        return input.matches("^-?\\d+(\\.\\d+)?$");
    }

    //	    Lấy sản phẩm để bán
    public void chonSanPham() {
        String selectedMaSP = cboTimSP.getSelectedItem().toString();

        DefaultTableModel tablemodel = (DefaultTableModel) table_SP.getModel();
        tablemodel.setRowCount(0); // Xóa dữ liệu hiện tại trong bảng

        List<SanPham> list = null;
        try {
            list = daoSP.getAllSP();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        for (SanPham x : list) {
            int soLuong = x.getSoluong();
            float vatValue = x.getVat();
            String vatStatus = (vatValue == 1) ? "Có (5%)" : "Không";

            double giaBan = x.getGiaBan();
            KhuyenMai khuyenMai = x.getKhuyenMai();
            String TenKm = null;
            if (khuyenMai != null) {
                TenKm = null;
                try {
                    TenKm = LHD_dao.getKMTheoTen(khuyenMai.getMaKhuyenMai());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                // Handle the case where khuyenMai is null
            }
            int km = Integer.parseInt(null);
            try {
                km = LHD_dao.getKMTheoPhanTram(TenKm);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < table_CTHD.getRowCount(); i++) {
                if (table_CTHD.getValueAt(i, 1).toString().equals(selectedMaSP)) {
                    try {
                        soLuong = x.getSoluong() - (int) table_CTHD.getValueAt(i, 7);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            if (x.getMaSp().equals(selectedMaSP)) {
                tablemodel.addRow(new Object[]{x.getMaSp(), x.getTensp(), x.getLoaiSanPham().getMaLoaiSP(), x.getMauSac(),
                        x.getSize(), x.getChatLieu().getMaChatLieu(), soLuong, tien.format(giaBan), vatStatus, km});
                table_SP.selectAll();
            }
        }
    }

    public void chonKhachHang() {
        String sdt = (String) cboSDTKH.getSelectedItem();
        DefaultTableModel tablemodel = (DefaultTableModel) table_dscho.getModel();
        for (int i = 0; i < table_dscho.getRowCount(); i++) {
            if (table_dscho.getValueAt(i, 1).toString().equals(sdt)) {
                String selectedMaHoaDon = table_dscho.getValueAt(i, 0).toString();
                Object hoaDonVaChiTietObj = danhSachHoaDon.get(selectedMaHoaDon);
                if (hoaDonVaChiTietObj instanceof Map) {
                    Map<String, Object> hoaDonVaChiTiet = (Map<String, Object>) hoaDonVaChiTietObj;

                    // Xóa dòng được chọn từ table_dscho
                    DefaultTableModel model = (DefaultTableModel) table_dscho.getModel();

                    // Cập nhật lại table_CTHDin hoặc các thành phần khác nếu cần
                    updateTextFieldsWithHoaDonInfo(hoaDonVaChiTiet);
                    updateTableCTHDWithChiTietHoaDon(hoaDonVaChiTiet);
                    tinhThongTinThanhToan();
                    table_dscho.getSelectionModel().setSelectionInterval(i, i);
                    table_dscho.scrollRectToVisible(table_dscho.getCellRect(i, 0, true));

                    // Đặt lblloihd về trạng thái rỗng khi chọn thành công
                    lblloihd.setText(""); // Trạng thái rỗng

                    break;
                } else {
                    lblloihd.setText("Không thể thêm hóa đơn chờ");
                }
            }
        }
    }

    //	    thêm hóa đơn vào hàng chờ
    private void themVaoDanhSachCho() {
        model = (DefaultTableModel) table_dscho.getModel();
        model.setRowCount(0);
        Set<String> uniquesdtKhachHang = new HashSet<>();

        for (Map.Entry<String, Object> entry : danhSachHoaDon.entrySet()) {
            Map<String, Object> hoaDonVaChiTiet = (Map<String, Object>) entry.getValue();
            Map<String, Object> hoaDon = (Map<String, Object>) hoaDonVaChiTiet.get("HoaDon");

            String maHoaDon = (String) hoaDon.get("MaHoaDon");
            String sdtKhachHang = (String) hoaDon.get("SoDienThoai");
            String tenNhanVien = (String) hoaDon.get("TenNhanVien");
            String ngayTao = (String) hoaDon.get("NgayLap");
            String soLuong = (String) hoaDon.get("SoLuong");

            model.addRow(
                    new Object[]{maHoaDon, sdtKhachHang, FrmDangNhap.taiKhoan.getTenTaiKhoan(), soLuong, ngayTao});
        }
        cboSDTKH.removeAllItems();
        HashSet<String> sdtDuyet = new HashSet<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String sdtKhachHang = (String) model.getValueAt(i, 1);
            sdtDuyet.add(sdtKhachHang);
        }

        for (String sdt : sdtDuyet) {
            cboSDTKH.addItem(sdt);
        }
        cboSDTKH.setSelectedItem("");

    }

    public void themHangCho() {
        if (txtSDT.getText().isEmpty()) {
            lblloihd.setText("Vui lòng nhập SĐT khách hàng");
            txtSDT.setText("");
            txtSDT.requestFocus();
            return;
        }

        if (table_CTHD.getRowCount() == 0) {
            lblloihd.setText("Chưa có sản phẩm");
            return;
        } else {
            Map<String, Object> hoaDonVaChiTiet = layThongTinHoaDonVaChiTiet();
            danhSachHoaDon.put((String) hoaDonVaChiTiet.get("MaHoaDon"), hoaDonVaChiTiet);
            themVaoDanhSachCho();

            List<Map<String, Object>> chiTietHoaDonList = (List<Map<String, Object>>) hoaDonVaChiTiet
                    .get("ChiTietHoaDon");
            for (Map<String, Object> chiTiet : chiTietHoaDonList) {
                // Xử lý chi tiết hóa đơn nếu cần thiết
            }
        }

        // Đặt lblloihd về trạng thái rỗng khi không có lỗi
        lblloisdt.setText("");
        lblloihd.setText("");
        lblLoiSP.setText("");
        txtMaHD.setText(updateMaHD());
        HuyHD();
    }

    //	    Thanh toán
    public void thanhToan() {
        if (table_CTHD.getRowCount() == 0) {
            lblloihd.setText("Hãy thêm sản phẩm để thanh toán.");
            return;
        }
        if (txtSDT.getText().isEmpty()) {
            lblloihd.setText("Hãy nhập số điện thoại khách hàng");
            txtSDT.setText("");
            txtSDT.requestFocus();
            return;
        }
        if (txtDiemSuDung.getText().isEmpty()) {
            lblloihd.setText("Hãy nhập điểm sử dụng");
            txtDiemSuDung.setText("");
            txtDiemSuDung.requestFocus();
            return;
        }

        if (txtTienKhachDua.getText().isEmpty()) {
            lblloihd.setText("Hãy nhập tiền khách đưa");
            txtTienKhachDua.setText("");
            txtTienKhachDua.requestFocus();
            return;
        }

        if (!isValidDouble(txtTienKhachDua.getText())) {
            lblloihd.setText("Hãy nhập giá trị hợp lệ");
            txtTienKhachDua.setText("");
            txtTienKhachDua.requestFocus();
            return;
        }

        double tienNhan = Double.parseDouble(txtTienKhachDua.getText());
        double thanhToan = Double.parseDouble(txtTongThanhToan.getText().replace("VNĐ", "").replace(",", ""));

        if (tienNhan < thanhToan) {
            lblloihd.setText("Số tiền khách đưa phải lớn hơn hoặc bằng tổng số tiền cần thanh toán.");
            txtTienKhachDua.setText("");
            txtTienKhachDua.requestFocus();
            return;
        }

        int dialogResult = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn muốn thanh toán hóa đơn này? Hóa đơn sẽ được in sau khi bạn đồng ý!");

        if (dialogResult == JOptionPane.YES_OPTION) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy , HH:mm");
            String maHoaDon = txtMaHD.getText();
            String nhanVien = null;
            try {
                nhanVien = LHD_dao.getTenNV(tenNhanVien);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            String ngayLap = LocalDateTime.now().format(formatter);
            String maKH = txtMaKH.getText();
            String hoTenKh = txtTenKH.getText();
            String diemtichluy = txtDiemTL.getText();

            String soDT = txtSDT.getText();
            String tongsl = txtTongSL.getText();
            String diemtichduoc = txtDiemTichDuoc.getText();
            String diemsudung = txtDiemSuDung.getText();
            String tongtiensp = txtTongTienSP.getText();
            String dtlhienco = tinhDTLHienCo(diemtichluy, diemsudung, diemtichduoc);

            String tongthanhtoan = txtTongThanhToan.getText();

            String selectedItem = String.valueOf(cboKhuyenMai.getSelectedItem());
            String numberStr = selectedItem.replaceAll("[^\\d.]", "");

            int phantramkm = 0;
            try {
                phantramkm = Integer.parseInt(numberStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            String phantramkhuyenmai = String.valueOf(phantramkm);

            double tienKD = Double.parseDouble(txtTienKhachDua.getText());
            String tienkhachdua = tien.format(tienKD) + " VNĐ";

            double tkd = 0.0;
            try {
                tkd = Double.parseDouble(tienkhachdua.replace("VNĐ", "").replace(",", ""));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            String tienhoanlai = txtTienHoanLai.getText();
            String tongkm = txtTongKhuyenMai.getText();
            String tongthue = txtTongThue.getText();

            // Xóa hàng trong bảng dựa trên điều kiện
            for (int i = 0; i < table_dscho.getRowCount(); i++) {
                if (model.getValueAt(i, 0).toString().equals(txtMaHD.getText())) {
                    model.removeRow(i);
                    break;
                }
            }

            // Cập nhật dữ liệu trong danh sách hoá đơn và cập nhật giao diện người dùng
            danhSachHoaDon.remove(txtMaHD.getText());
            frmXuatHD.setVisible(true);
            frmXuatHD.setDuLieuFrmInHd(maHoaDon, nhanVien, ngayLap, hoTenKh, dtlhienco, soDT, tongsl, diemtichduoc,
                    diemsudung, tongtiensp, tongthanhtoan, tienkhachdua, tienhoanlai, phantramkhuyenmai, tongthue,
                    tongkm);

            // Cập nhật hoá đơn và chi tiết hoá đơn trong cơ sở dữ liệu
//            LHD_dao.upDateHoaDon(maHoaDon, tkd, diemTichDuoc, tenNhanVien, maKH, tongThanhToan);
            try {
                LHD_dao.upDateHoaDon(maHoaDon, tkd, diemTichDuoc, tenNhanVien, maKH, tongThanhToan);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < table_CTHD.getRowCount(); i++) {
                try {
                    int soLuongBan = Integer.parseInt(table_CTHD.getValueAt(i, 7).toString().replace(",", ""));
                    String maSanPham = table_CTHD.getValueAt(i, 1).toString();
//                    LHD_dao.addCT_HoaDon(maHoaDon, maSanPham, soLuongBan);
                    LHD_dao.addCT_HoaDon(maHoaDon, maSanPham, soLuongBan);
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            // Cập nhật bảng chi tiết hoá đơn trong giao diện
            frmXuatHD.capNhatDuLieuTableCTHD((DefaultTableModel) table_CTHD.getModel());

            // In hoá đơn và cập nhật mã hoá đơn
            frmXuatHD.printingHoaDon();
            txtMaHD.setText(updateMaHD());

            // Xóa dữ liệu, cập nhật điểm và dọn dẹp bảng
            HuyHD();
//            LHD_dao.updateDiem(tinhDTLHienCoDouble(diemtichluy, diemsudung, diemtichduoc), soDT);
            try {
                LHD_dao.updateDiem(tinhDTLHienCoDouble(diemtichluy, diemsudung, diemtichduoc), soDT);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            deleteAllDataTable();
        }
    }

    public void xoaSanPham(int selectedRow) {
        DefaultTableModel modelCTHD = (DefaultTableModel) table_CTHD.getModel();

        if (selectedRow >= 0 && selectedRow < modelCTHD.getRowCount()) {
            int soLuongBan = Integer.parseInt(modelCTHD.getValueAt(selectedRow, 7).toString());
            int tongSoLuong = Integer.parseInt(txtTongSL.getText());
            String cleanedString = txtTongTienSP.getText().replaceAll("[^\\d.]", "");

            double tongTienSPs = Double.parseDouble(cleanedString);
            double donGia = Double.parseDouble(modelCTHD.getValueAt(selectedRow, 6).toString().replace(",", ""));

            String masp = modelCTHD.getValueAt(selectedRow, 1).toString();

            for (int i = 0; i < table_SP.getRowCount(); i++) {
                if (table_SP.getValueAt(i, 0).toString().equals(masp)) {
                    int soLuongHienTai = Integer.parseInt(table_SP.getValueAt(i, 6).toString());
                    table_SP.setValueAt(soLuongHienTai + soLuongBan, i, 6);
                    break;
                }
            }

            tongSoLuong -= soLuongBan;
            tongTienSPs -= soLuongBan * donGia;

            final int finalTongSoLuong = tongSoLuong;
            final double finalTongTienSPs = tongTienSPs;

            SwingUtilities.invokeLater(() -> {
                try {
                    if (selectedRow >= 0 && selectedRow < modelCTHD.getRowCount() && 1 >= 0
                            && 1 < modelCTHD.getColumnCount()) {
                        modelCTHD.removeRow(selectedRow);

                        if (modelCTHD.getRowCount() == 1) {
                            txtTongSL.setText("0");
                            txtTongTienSP.setText("0");
                            txtDiemTichDuoc.setText("0");
                            txtTongThanhToan.setText("0");
                            txtTongThue.setText("0");
                            txtTongKhuyenMai.setText("0");
                        } else {
                            txtTongSL.setText(String.valueOf(finalTongSoLuong));
                            txtTongTienSP.setText(String.valueOf(finalTongTienSPs));
                            float diemTichDuoc = (float) (0.01 * finalTongTienSPs);
                            txtDiemTichDuoc.setText(String.valueOf(diemTichDuoc));
                            txtTongThanhToan.setText(String.valueOf(finalTongSoLuong));

                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    class ButtonRenderer implements TableCellRenderer {
        private JPanel panel;
        private JButton btnCong;
        private JButton btnTru;
        private JLabel soLuongBan;

        public ButtonRenderer() {
            panel = new JPanel();
            btnCong = new JButton("+");
            btnTru = new JButton("-");

            btnCong = new JButton("+");
            btnCong.setFont(new Font("Times New Roman", Font.BOLD, 20));
            btnCong.setOpaque(false);
            btnCong.setContentAreaFilled(false);
            btnCong.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
            Dimension btnSize = new Dimension(25, 20);
            btnCong.setSize(30, 20);
            btnCong.setPreferredSize(btnSize);

            btnTru = new JButton("-");
            btnTru.setFont(new Font("Times New Roman", Font.BOLD, 20));
            btnTru.setOpaque(false);
            btnTru.setContentAreaFilled(false);
            btnTru.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            btnTru.setPreferredSize(btnSize);
            btnTru.setSize(30, 20);

            soLuongBan = new JLabel();
            soLuongBan.setHorizontalAlignment(SwingConstants.CENTER);
            btnCong.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int soLuong = Integer.parseInt(soLuongBan.getText());
                    soLuongBan.setText(String.valueOf(soLuong + 1));
                }
            });

            btnTru.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int soLuong = Integer.parseInt(soLuongBan.getText());
                    if (soLuong > 1) {
                        soLuongBan.setText(String.valueOf(soLuong - 1));
                    }
                }
            });

            panel.setLayout(new BorderLayout());
            panel.add(btnTru, BorderLayout.WEST);
            panel.add(soLuongBan, BorderLayout.CENTER);
            panel.add(btnCong, BorderLayout.EAST);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            soLuongBan.setText(table_CTHD.getValueAt(row, 7).toString());
            table.getModel().setValueAt(Integer.parseInt(soLuongBan.getText()), row, 7);
            return panel;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private JPanel panel;
        private JButton btnCong;
        private JButton btnTru;
        private JLabel soLuongBan;
        private int clickedRow;
        private int clickedColumn;
        private JTable table;
        private JTable table_sp;
        private JComboBox cboTimSP;

        public ButtonEditor(JTextField textField, final JTable table, final JTable table_sp, final FrmLapHoaDon frm,
                            JComboBox cboTimSP) {
            super(textField);
            panel = new JPanel();
            this.table = table;
            this.cboTimSP = cboTimSP;
            this.table_sp = table_sp;
            btnCong = new JButton("+");
            btnCong.setFont(new Font("Times New Roman", Font.BOLD, 20));
            btnCong.setOpaque(false);
            btnCong.setContentAreaFilled(false);
            btnCong.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
            Dimension btnSize = new Dimension(25, 20);
            btnCong.setSize(30, 20);
            btnCong.setPreferredSize(btnSize);

            btnTru = new JButton("-");
            btnTru.setFont(new Font("Times New Roman", Font.BOLD, 20));
            btnTru.setOpaque(false);
            btnTru.setContentAreaFilled(false);
            btnTru.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            btnTru.setPreferredSize(btnSize);
            btnTru.setSize(30, 20);

            soLuongBan = new JLabel();
            soLuongBan.setHorizontalAlignment(SwingConstants.CENTER);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    if (e.getColumn() == 7) {
                        soLuongBan.setText(model.getValueAt(clickedRow, 7).toString());
                    }
                }
            });

            soLuongBan.addMouseListener(new MouseAdapter() {
                private double phanTramKhuyenMai;

                @Override
                public void mouseClicked(MouseEvent e) {
                    soLuongBan.setText(table_CTHD.getValueAt(clickedRow, 7).toString());
                    chonSanPham();
                    int oldSoLuong = Integer.parseInt(soLuongBan.getText());
                    inputSoLuong();
                    updateModel();
                    for (int i = 0; i < table_sp.getRowCount(); i++) {
                        if (table.getRowCount() > 0) {
                            if (table_sp.getValueAt(i, 0).toString()
                                    .equals(table.getValueAt(clickedRow, 1).toString())) {
                                int soLuongSP = Integer.parseInt(table_sp.getValueAt(i, 6).toString());
                                int soLuongMoi = Integer.parseInt(table.getValueAt(clickedRow, 7).toString());
                                soLuongSP = soLuongSP + (oldSoLuong - soLuongMoi);
                                table_sp.setValueAt(soLuongSP, i, 6);
                                break;
                            }
                        }
                    }
                    frm.tinhThongTinThanhToan();
                }
            });
            btnCong.addActionListener(new ActionListener() {
                private double phanTramKhuyenMai;

                @Override
                public void actionPerformed(ActionEvent e) {
                    soLuongBan.setText(table_CTHD.getValueAt(clickedRow, 7).toString());
                    chonSanPham();

                    int soLuongSP = 0;
                    for (int i = 0; i < table_sp.getRowCount(); i++) {
                        if (table_sp.getValueAt(i, 0).toString().equals(table.getValueAt(clickedRow, 1).toString())) {
                            soLuongSP = Integer.parseInt(table_sp.getValueAt(i, 6).toString());
                        }
                    }

                    int soLuong = Integer.parseInt(soLuongBan.getText());
                    if (soLuongSP == 0) {
                        JOptionPane.showMessageDialog(null, "Số lượng vượt quá số lượng tồn", "Thông báo",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    soLuongBan.setText(String.valueOf(soLuong + 1));
                    updateModel();
                    for (int i = 0; i < table_sp.getRowCount(); i++) {
                        if (table_sp.getValueAt(i, 0).toString().equals(table.getValueAt(clickedRow, 1).toString())) {
                            int soLuongMoi = Integer.parseInt(table_sp.getValueAt(i, 6).toString()) - 1;
                            table_sp.setValueAt(soLuongMoi, i, 6);
                            break;
                        }
                    }

                    frm.tinhThongTinThanhToan();
                }
            });

            btnTru.addActionListener(new ActionListener() {

                double phanTramKhuyenMai;
                double tongTienSP;

                @Override
                public void actionPerformed(ActionEvent e) {
                    soLuongBan.setText(table_CTHD.getValueAt(clickedRow, 7).toString());
                    chonSanPham();
                    int soLuong = Integer.parseInt(soLuongBan.getText());
                    if (soLuong > 1) {
                        soLuongBan.setText(String.valueOf(soLuong - 1));
                        updateModel();
                        for (int i = 0; i < table_sp.getRowCount(); i++) {
                            if (table_sp.getValueAt(i, 0).toString()
                                    .equals(table.getValueAt(clickedRow, 1).toString())) {

                                int soLuongMoi = Integer.parseInt(table_sp.getValueAt(i, 6).toString()) + 1;
                                table_sp.setValueAt(soLuongMoi, i, 6);
                                break;
                            }
                        }
                    }
                    if (soLuong - 1 == 0) {
                        soLuongBan.setText(String.valueOf(soLuong));
                        chonSanPham();
                        updateModel();
                        for (int i = 0; i < table_sp.getRowCount(); i++) {
                            if (table_sp.getValueAt(i, 0).toString()
                                    .equals(table.getValueAt(clickedRow, 1).toString())) {

                                int soLuongMoi = Integer.parseInt(table_sp.getValueAt(i, 6).toString()) + 1;
                                table_sp.setValueAt(soLuongMoi, i, 6);
                                break;
                            }
                        }
                        ((DefaultTableModel) table.getModel()).removeRow(clickedRow);

                    }
                    frm.tinhThongTinThanhToan();
                }

            });

            panel.setLayout(new BorderLayout());
            panel.add(btnTru, BorderLayout.WEST);
            panel.add(soLuongBan, BorderLayout.CENTER);
            panel.add(btnCong, BorderLayout.EAST);
        }

        public void chonSanPham() {
            cboTimSP.setSelectedItem(table.getValueAt(clickedRow, 1).toString());
        }

        public void updatethanhTiens() {
            int cotSoLuong = 7;
            int cotDonGia = 6;
            int cotThanhTien = 10;
            int cotVAT = 8;
            int cotTongTienKM = 9;

            for (int rowIndex = 0; rowIndex < table.getRowCount(); rowIndex++) {
                try {
                    int soLuong = (int) table.getValueAt(rowIndex, cotSoLuong);
                    double donGia = Double
                            .parseDouble(table.getValueAt(rowIndex, cotDonGia).toString().replace(",", ""));
                    double VAT = Double.parseDouble(table.getValueAt(rowIndex, cotVAT).toString().replace(",", ""));
                    double tongTienKM = Double
                            .parseDouble(table.getValueAt(rowIndex, cotTongTienKM).toString().replace(",", ""));

                    DecimalFormat decimalFormat = new DecimalFormat("#,##0");

                    double thanhTien = soLuong * (donGia - tongTienKM + VAT);
                    String thanhTienDaFormat = decimalFormat.format(thanhTien);

                    table.setValueAt(thanhTienDaFormat, rowIndex, cotThanhTien);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void updateModel() {
            int row = table.getEditingRow();
            int column = table.getEditingColumn();

            if (row != -1 && column != -1 && table.getRowCount() > 0) {
                Object value = Integer.parseInt(soLuongBan.getText());
                table.getModel().setValueAt(value, row, 7);
            }
            updatethanhTiens();

        }

        private void inputSoLuong() {
            String soLuongHienTai = soLuongBan.getText();

            String input = JOptionPane.showInputDialog(null, "Nhập số lượng mới:", "Chỉnh sửa Số lượng",
                    JOptionPane.QUESTION_MESSAGE);
            int soLuongSP = 0;
            for (int i = 0; i < table_sp.getRowCount(); i++) {
                if (table_sp.getValueAt(i, 0).toString().equals(table.getValueAt(clickedRow, 1).toString())) {
                    soLuongSP = Integer.parseInt(table_sp.getValueAt(i, 6).toString());
                }
            }

            if (input != null) {
                try {
                    int soLuongNew = Integer.parseInt(input);
                    if (soLuongNew > soLuongSP) {
                        JOptionPane.showMessageDialog(null, "Số lượng vượt quá số lượng tồn", "Thông báo",
                                JOptionPane.ERROR_MESSAGE);
                    } else if (soLuongNew > 0) {
                        soLuongBan.setText(String.valueOf(soLuongNew));
                    } else if (soLuongNew == 0) {
                        chonSanPham();
                        updateModel();
                        for (int i = 0; i < table_sp.getRowCount(); i++) {
                            if (table_sp.getValueAt(i, 0).toString()
                                    .equals(table.getValueAt(clickedRow, 1).toString())) {
                                int soLuongMoi = Integer.parseInt(table_sp.getValueAt(i, 6).toString())
                                        + Integer.parseInt(soLuongHienTai);
                                table_sp.setValueAt(soLuongMoi, i, 6);
                                break;
                            }
                        }
                        ((DefaultTableModel) table.getModel()).removeRow(clickedRow);
                    } else {
                        JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên dương quá", "Thông báo",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi. Nhập dữ liệu số không hợp lệ.", "Thông báo",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                                                     int column) {
            clickedRow = row;
            clickedColumn = column;
            soLuongBan.setText(value == null ? "" : value.toString());
            table.getModel().setValueAt(Integer.parseInt(soLuongBan.getText()), row, 7);
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return soLuongBan.getText();
        }

        @Override
        public boolean stopCellEditing() {
            return super.stopCellEditing();
        }
    }

    public void hienThongTinCho() {
        int selectedRow = table_dscho.getSelectedRow();
        if (selectedRow >= 0) {
            String selectedMaHoaDon = table_dscho.getValueAt(selectedRow, 0).toString();
            Object hoaDonVaChiTietObj = danhSachHoaDon.get(selectedMaHoaDon);
            if (hoaDonVaChiTietObj instanceof Map) {
                Map<String, Object> hoaDonVaChiTiet = (Map<String, Object>) hoaDonVaChiTietObj;

                // Xóa dòng được chọn từ table_dscho
                DefaultTableModel model = (DefaultTableModel) table_dscho.getModel();

                // Cập nhật lại table_CTHDin hoặc các thành phần khác nếu cần
                updateTextFieldsWithHoaDonInfo(hoaDonVaChiTiet);
                updateTableCTHDWithChiTietHoaDon(hoaDonVaChiTiet);
                tinhThongTinThanhToan();

            } else {
                lblLoiSP.setText("Không thể thêm hóa đơn chờ");
            }
        }
    }

    // Xóa sản phẩm ra khỏi chi tiết hóa đơn
    public void xoaSanPham() {
        int selectedRow = table_CTHD.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) table_CTHD.getModel();
            DefaultTableModel model_SP = (DefaultTableModel) table_SP.getModel();

            int soLuongBan = Integer.parseInt(model.getValueAt(selectedRow, 7).toString());
            int tongSoLuong = Integer.parseInt(txtTongSL.getText());
            String cleanedString = txtTongTienSP.getText().replaceAll("[^\\d.]", "");

            double tongTienSPs = Double.parseDouble(cleanedString);
            double donGia = Double.parseDouble(model.getValueAt(selectedRow, 6).toString().replace(",", ""));
            for (int i = 0; i < table_SP.getRowCount(); i++) {
                if (table_SP.getValueAt(i, 0).toString().equals(table_CTHD.getValueAt(selectedRow, 1).toString()))
                    table_SP.setValueAt(Integer.parseInt(table_SP.getValueAt(i, 6).toString()) + soLuongBan, i, 6);
            }
            model.removeRow(selectedRow);
            tinhThongTinThanhToan();
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.");
        }
    }

    // Xóa thông tin số lượng
    public void xoaSLSanPham() {
        int selectedRow = table_CTHD.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) table_CTHD.getModel();
            DefaultTableModel model_SP = (DefaultTableModel) table_SP.getModel();

            int soLuongBan = Integer.parseInt(model.getValueAt(selectedRow, 7).toString());
            int tongSoLuong = Integer.parseInt(txtTongSL.getText());
            String cleanedString = txtTongTienSP.getText().replaceAll("[^\\d.]", "");

            double tongTienSPs = Double.parseDouble(cleanedString);
            double donGia = Double.parseDouble(model.getValueAt(selectedRow, 6).toString().replace(",", ""));
            for (int i = 0; i < table_SP.getRowCount(); i++) {
                if (table_SP.getValueAt(i, 0).toString().equals(table_CTHD.getValueAt(selectedRow, 1).toString()))
                    table_SP.setValueAt(Integer.parseInt(table_SP.getValueAt(i, 6).toString()) + soLuongBan, i, 6);
            }
            model.removeRow(selectedRow);
            tinhThongTinThanhToan();
        }
    }

    //	Bắt sự kiện click chuột trong chi tiết hóa đơn
    public void hienThongTinSanPhamTuChiTiet() {

        int selectedRow = table_CTHD.getSelectedRow();
        if (selectedRow != -1) {
            String maSPCanChon = table_CTHD.getValueAt(selectedRow, 1).toString();

            DefaultTableModel tablemodel = (DefaultTableModel) table_SP.getModel();
            tablemodel.setRowCount(0); // Xóa dữ liệu hiện tại trong bảng

            List<SanPham> list = null;
            try {
                list = daoSP.getAllSP();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            for (SanPham x : list) {
                int soLuong = x.getSoluong();
                float vatValue = x.getVat();
                String vatStatus = (vatValue == 1) ? "Có (5%)" : "Không";

                double giaBan = x.getGiaBan();
                KhuyenMai khuyenMai = x.getKhuyenMai();
                String TenKm = null;
                if (khuyenMai != null) {
//                    TenKm = LHD_dao.getKMTheoTen(khuyenMai.getMaKhuyenMai());
                    try {
                        TenKm = LHD_dao.getKMTheoTen(khuyenMai.getMaKhuyenMai());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Handle the case where khuyenMai is null
                }
//                int km = LHD_dao.getKMTheoPhanTram(TenKm);
                int km = 0; // Initialize km outside the try-catch block
                try {
                    km = LHD_dao.getKMTheoPhanTram(TenKm);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < table_CTHD.getRowCount(); i++) {
                    if (table_CTHD.getValueAt(i, 1).toString().equals(maSPCanChon)) {
                        soLuong = x.getSoluong() - (int) table_CTHD.getValueAt(i, 7);
                    }
                }
                if (x.getMaSp().equals(maSPCanChon)) {
                    tablemodel.addRow(new Object[]{x.getMaSp(), x.getTensp(), x.getLoaiSanPham().getMaLoaiSP(),
                            x.getMauSac(), x.getSize(), x.getChatLieu().getMaChatLieu(), soLuong, tien.format(giaBan),
                            vatStatus, km});
                    table_SP.selectAll();
                }
            }
        }
    }


    public void nhapTimSoDienThoaiKhachHang() {
        String sdt = textField.getText();
        if (sdt.equals(""))
            return;
        boolean coSdt = false;
        for (int i = 0; i < table_dscho.getRowCount(); i++) {
            if (table_dscho.getValueAt(i, 1).toString().equals(sdt)) {
                String selectedMaHoaDon = table_dscho.getValueAt(i, 0).toString();

                Object hoaDonVaChiTietObj = danhSachHoaDon.get(selectedMaHoaDon);
                if (hoaDonVaChiTietObj instanceof Map) {
                    Map<String, Object> hoaDonVaChiTiet = (Map<String, Object>) hoaDonVaChiTietObj;

                    // Xóa dòng được chọn từ table_dscho
                    DefaultTableModel model = (DefaultTableModel) table_dscho.getModel();

                    // Cập nhật lại table_CTHDin hoặc các thành phần khác nếu cần
                    updateTextFieldsWithHoaDonInfo(hoaDonVaChiTiet);
                    updateTableCTHDWithChiTietHoaDon(hoaDonVaChiTiet);
                    tinhThongTinThanhToan();
                    table_dscho.getSelectionModel().setSelectionInterval(i, i);
                    table_dscho.scrollRectToVisible(table_dscho.getCellRect(i, 0, true));
                    coSdt = true;
                    break;
                }
            }

        }
        if (!coSdt) {
            lblloisdt.setText("Không tìm thấy số điện thoại");
        } else {
            lblloisdt.setText("");
        }

    }

    //	Tự động điền thông tin khách hàng
    public void tuDongDienKhachHang() throws RemoteException {
        if (LHD_dao.timKhachHangBySDT(txtSDT.getText()).isEmpty()) {
            txtMaKH.setText("Khách lẻ");
            txtTenKH.setText("Khách lẻ");
            txtDiemTL.setText("0");
            txtDiemSuDung.setEditable(false);
            txtDiemSuDung.setText("0");
            txtDiemTichDuoc.setText("0");

            txtDiemTichDuoc.setText("0");

        } else {
            // Tính điểm tích được
            diemTichDuoc = (float) (tongThanhToan * tyLeDiem);

            txtDiemTichDuoc.setText(String.valueOf(tienNoString.format(diemTichDuoc)));
            txtDiemSuDung.setEditable(true);

            timTenVaMaKhachHangBySDT(txtSDT.getText());
        }
    }

    //	Tự động tính tiền thừa cho khách
    public void tuDongTinhTien() {
        try {
            double thanhToan = Double.parseDouble(txtTongThanhToan.getText().replace("VNĐ", "").replace(",", ""));

            if (txtTienKhachDua.getText() == "") {
                int tienNhan = 0;
                txtTienKhachDua.setText("0");
                double tienHoanLai = tienNhan - thanhToan;
                txtTienHoanLai.setText(tien.format(tienHoanLai) + " VNĐ");
            } else {

                double tienNhan = Double.parseDouble(txtTienKhachDua.getText());
                double tienHoanLai = tienNhan - tongThanhToan;
                txtTienHoanLai.setText(tien.format(tienHoanLai) + " VNĐ");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void tinhTienThua() {
        try {
            double thanhToan = Double.parseDouble(txtTongThanhToan.getText().replace("VNĐ", "").replace(",", ""));
            if (txtTienKhachDua.getText() == "") {

                int tienNhan = 0;
                txtTienKhachDua.setText("0");
                double tienHoanLai = tienNhan - thanhToan;
                txtTienHoanLai.setText(tien.format(tienHoanLai) + " VNĐ");
            } else {

                double tienNhan = Double.parseDouble(txtTienKhachDua.getText());
                double tienHoanLai = tienNhan - thanhToan;
                txtTienHoanLai.setText(tien.format(tienHoanLai) + " VNĐ");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    //	Tính điểm sử dụng
    public void updateThanhToan() {
        try {
            double diemsudung = Double.parseDouble(txtDiemSuDung.getText());
            double diemtl = Double.parseDouble(txtDiemTL.getText());
            if (diemsudung <= diemtl) {
                double tongTien = tongThanhToan - diemsudung;
                txtTongThanhToan.setText(tien.format(tongTien) + " VNĐ");
                lblLoiSP.setText("");
            } else {
                lblLoiSP.setText("Điểm sử dụng phải nhỏ hơn hoặc bằng điểm tích lũy.");
                diemsudung = 0;
                txtDiemSuDung.removeAll();
            }
        } catch (NumberFormatException ex) {

        }
    }

    //Bắt sự kiện nhập
    @Override
    public void insertUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        if (e.getDocument() == txtTienKhachDua.getDocument()) {
            tinhTienThua();
        } else if (e.getDocument() == txtDiemSuDung.getDocument()) {
            updateThanhToan();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        if (e.getDocument() == txtTienKhachDua.getDocument()) {
            tinhTienThua();
        } else if (e.getDocument() == txtDiemSuDung.getDocument()) {
            updateThanhToan();
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        if (e.getDocument() == txtTienKhachDua.getDocument()) {
            tinhTienThua();
        } else if (e.getDocument() == txtDiemSuDung.getDocument()) {
            updateThanhToan();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        Object o = e.getSource();
        if (o.equals(table_dscho)) {
            hienThongTinCho();
        } else if (o.equals(table_CTHD)) {
            if (e.getClickCount() == 1) {
                hienThongTinSanPhamTuChiTiet();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    //	Bắt sự kiện nhập text
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        Object o = e.getSource();
        if (o.equals(txtSDT) || o.equals(txtTienKhachDua) || o.equals(txtSoLuong) || o.equals(textField)) {
            if (!((e.getKeyChar() >= 48 && e.getKeyChar() <= 57) || e.getKeyChar() == 8)) {
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        Object o = e.getSource();
        if (o.equals(textField)) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                nhapTimSoDienThoaiKhachHang();
            }
        } else if (o.equals(txtSDT) || o.equals(textField)) {
            if (!((e.getKeyChar() >= 48 && e.getKeyChar() <= 57) || e.getKeyChar() == 8)) {
                e.consume();
            }
        } else if (o.equals(txtTienKhachDua)) {
            tuDongTinhTien();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        Object o = e.getSource();
        if (o.equals(txtSDT)) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (txtSDT.getText().length() >= 9) {
                        timTenVaMaKhachHangBySDT(txtSDT.getText());
                    } else
                        lblLoiSP.setText("Số điện thoại phải có ít là 10 số!");
                }
            }
//            tuDongDienKhachHang();
            try {
                tuDongDienKhachHang();
            } catch (RemoteException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }
}
