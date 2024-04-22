package GUI;

import DAOTest.NhanVienDao;
import DAOTest.impl.NhanVienImpl;
import Entities.NhanVien;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class FrmNhanVien extends JFrame implements ActionListener, MouseListener, ItemListener, DocumentListener, KeyListener {

    private static final long serialVersionUID = 1L;
    private JTextField txtMaNV;
    private JTextField textTen;
    private JTextField txtSDT;
    private JTextField txtCCCD;
    private JTextField txtEmail;
    private JTextField txtDiaChi;
    private JPanel contentPane;
    private JLabel lblTieuDeTrang;
    private NhanVienDao dao;

    {
        try {
            dao = (NhanVienDao) Naming.lookup(URL + "NhanVienDao");
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private DefaultTableModel tablemodel;
    private JTable table_DSKH;
    private JDateChooser txtNgay;
    private JComboBox cbChucVu;
    private JRadioButton rdNam;
    private JRadioButton rdNu;
    private JRadioButton rdĐangLam;
    private JRadioButton rdNghi;
    private JRadioButton rdCa2;
    private JRadioButton rdCa1;
    private JButton btnThem;
    private ButtonGroup buttonGroupTK;
    private JPanel pnlDanhSach;
    private JLabel lblThongTinTim;
    private JComboBox cbThongTinTim;
    private JLabel lblTim;
    private JRadioButton rdMaNV;
    private JRadioButton rdTenNV;
    private JRadioButton rdSDT;
    private JRadioButton rdCa;
    private JButton btnTimkiem;
    private JButton btnLamMoiThanh;
    private JRadioButton rdChuc;
    private JLabel lblGioiTinh;
    private JLabel lblCa;
    private JLabel lblTrangThai;
    private JLabel lblHinhAnh;

    private DefaultComboBoxModel cboModeChucVu = new DefaultComboBoxModel();
    private List<String> listChucVu = new ArrayList<>();
    private DefaultComboBoxModel cboModelChucVu;
    private JComboBox comboBox;
    private JButton btnLamMoi;
    private ButtonGroup gr1;
    private ButtonGroup gr2;
    private ButtonGroup gr3;
    private ButtonGroup buttonGroupGT;
    private JButton btnSua;
    private ButtonGroup buttonGroupCLV;
    private ButtonGroup gr4;
    private Component label;
    private String folderName;
    private String filename = null;
    private JButton btnChonAnh;
    private JLabel lblKhungHinh;

    boolean lock = false;
    boolean chkThem = false;
    boolean chkSua = false;
    private JButton btnLuu;
    String maBanDau = "";
    private JLabel lbl_LoiTen;
    private JLabel lbl_LoiEmail;
    private JLabel lbl_LoiDiaChi;
    private JLabel lbl_LoiCCCD;
    private JLabel lbl_LoiSDT;
    private JLabel lbl_LoiCa;
    private JLabel lbl_LoiGioiTinh;
    private JLabel lbl_LoiNgay;
    private JPanel pnlNhapThongTin;
    private JLabel lblMaNV;
    private JPanel pnlTieuDe;
    private JLabel lblTenNV;
    private JLabel lblSDT;
    private JLabel lblCCCD;
    private JLabel lblMail;
    private JLabel lblNgaySinh;
    private JLabel lblDiaChi;
    private Container cbChuc;
    private JPanel pnlNutChucNang;

    private static final String URL = "rmi://HOANGPHUC:6541/";


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmNhanVien frame = new FrmNhanVien();
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
    public FrmNhanVien() throws RemoteException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1347, 843);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        pnlTieuDe = new JPanel();
        pnlTieuDe.setBackground(new Color(0, 255, 255));
        pnlTieuDe.setBounds(0, 0, 1343, 41);
        contentPane.add(pnlTieuDe);
        pnlTieuDe.setLayout(null);
        lblTieuDeTrang = new JLabel("QUẢN LÝ NHÂN VIÊN");
        lblTieuDeTrang.setBounds(512, 12, 305, 25);
        pnlTieuDe.add(lblTieuDeTrang);
        lblTieuDeTrang.setBackground(new Color(0, 255, 255));
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));

        pnlNhapThongTin = new JPanel();
        pnlNhapThongTin.setBackground(new Color(255, 255, 255));
        pnlNhapThongTin.setLayout(null);
        pnlNhapThongTin.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Thi\u1EBFt l\u1EADp th\u00F4ng tin nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null,
                new Color(0, 0, 0)));
        pnlNhapThongTin.setBounds(20, 48, 1282, 302);
        contentPane.add(pnlNhapThongTin);

        lblMaNV = new JLabel("Mã nhân viên");
        lblMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblMaNV.setBounds(10, 28, 114, 12);
        pnlNhapThongTin.add(lblMaNV);

        txtMaNV = new JTextField();
        txtMaNV.setEditable(false);
        txtMaNV.setColumns(10);
        txtMaNV.setBounds(128, 28, 204, 20);
        txtMaNV.setText(defaultID());
        pnlNhapThongTin.add(txtMaNV);

        lblCa = new JLabel("Ca làm việc");
        lblCa.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblCa.setBounds(342, 28, 71, 12);
        pnlNhapThongTin.add(lblCa);

        lblTenNV = new JLabel("Tên nhân viên");
        lblTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblTenNV.setBounds(10, 74, 96, 15);
        pnlNhapThongTin.add(lblTenNV);

        textTen = new JTextField();
        textTen.setColumns(10);
        textTen.setBounds(128, 77, 204, 20);
        pnlNhapThongTin.add(textTen);
        textTen.requestFocus();

        lblSDT = new JLabel("Số điện thoại");
        lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblSDT.setBounds(10, 124, 96, 13);
        pnlNhapThongTin.add(lblSDT);

        txtSDT = new JTextField();
        txtSDT.setColumns(10);
        txtSDT.setBounds(128, 124, 204, 20);
        pnlNhapThongTin.add(txtSDT);

        lblCCCD = new JLabel("CCCD");
        lblCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblCCCD.setBounds(10, 262, 45, 13);
        pnlNhapThongTin.add(lblCCCD);

        txtCCCD = new JTextField();
        txtCCCD.setColumns(10);
        txtCCCD.setBounds(128, 259, 204, 20);
        pnlNhapThongTin.add(txtCCCD);

        lblMail = new JLabel("Email");
        lblMail.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblMail.setBounds(10, 165, 45, 13);
        pnlNhapThongTin.add(lblMail);

        txtEmail = new JTextField();
        txtEmail.setColumns(10);
        txtEmail.setBounds(128, 165, 204, 20);
        pnlNhapThongTin.add(txtEmail);

        lblNgaySinh = new JLabel("Ngày sinh");
        lblNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblNgaySinh.setBounds(342, 170, 114, 13);
        pnlNhapThongTin.add(lblNgaySinh);

        lblDiaChi = new JLabel(" Địa chỉ");
        lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblDiaChi.setBounds(10, 212, 108, 13);
        pnlNhapThongTin.add(lblDiaChi);

        txtDiaChi = new JTextField();
        txtDiaChi.setColumns(10);
        txtDiaChi.setBounds(128, 211, 204, 20);
        pnlNhapThongTin.add(txtDiaChi);

        cbChuc = new JLabel("Chức vụ");
        cbChuc.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        cbChuc.setBounds(342, 212, 108, 13);
        pnlNhapThongTin.add(cbChuc);

        cbChucVu = new JComboBox();
        cbChucVu.setBounds(450, 211, 204, 20);
        pnlNhapThongTin.add(cbChucVu);

        rdCa1 = new JRadioButton("Ca 1");
        rdCa1.setBackground(new Color(255, 255, 255));
        rdCa1.setBounds(450, 24, 132, 20);
        pnlNhapThongTin.add(rdCa1);

        rdCa2 = new JRadioButton("Ca 2");
        rdCa2.setBackground(new Color(255, 255, 255));
        rdCa2.setBounds(589, 24, 103, 20);
        pnlNhapThongTin.add(rdCa2);

        buttonGroupCLV = new ButtonGroup();

        // Thêm các nút radio vào ButtonGroup
        buttonGroupCLV.add(rdCa1);
        buttonGroupCLV.add(rdCa2);

        pnlNutChucNang = new JPanel();
        pnlNutChucNang.setBackground(new Color(255, 255, 255));
        pnlNutChucNang.setLayout(null);
        pnlNutChucNang.setBorder(
                new TitledBorder(null, "Ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnlNutChucNang.setBounds(1030, 58, 230, 189);
        pnlNhapThongTin.add(pnlNutChucNang);

        btnThem = new JButton("Thêm");
        btnThem.setBackground(new Color(0, 255, 255));
        btnThem.setIcon(new ImageIcon("Anh\\them.png"));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnThem.setBounds(36, 24, 160, 30);
        pnlNutChucNang.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setBackground(new Color(0, 255, 255));
        btnSua.setIcon(new ImageIcon("Anh\\sua.png"));
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSua.setBounds(36, 65, 160, 30);
        pnlNutChucNang.add(btnSua);

        btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setBackground(new Color(0, 255, 255));
        btnLamMoi.setIcon(new ImageIcon("Anh\\lammoi.png"));
        btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLamMoi.setBounds(36, 105, 160, 30);
        pnlNutChucNang.add(btnLamMoi);

        btnLuu = new JButton("Lưu");
        btnLuu.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLuu.setBackground(Color.CYAN);
        btnLuu.setBounds(36, 143, 160, 30);
        btnLuu.setIcon(new ImageIcon("Anh\\luu.png"));
        pnlNutChucNang.add(btnLuu);

        lblGioiTinh = new JLabel("Giới tính");
        lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblGioiTinh.setBounds(342, 78, 71, 12);
        pnlNhapThongTin.add(lblGioiTinh);

        rdNam = new JRadioButton("Nam");
        rdNam.setBackground(new Color(255, 255, 255));
        rdNam.setBounds(450, 74, 132, 20);
        pnlNhapThongTin.add(rdNam);

        rdNu = new JRadioButton("Nữ");
        rdNu.setBackground(new Color(255, 255, 255));
        rdNu.setBounds(589, 74, 103, 20);
        pnlNhapThongTin.add(rdNu);

        lblTrangThai = new JLabel("Trạng thái");
        lblTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblTrangThai.setBounds(342, 128, 71, 12);
        pnlNhapThongTin.add(lblTrangThai);

        rdĐangLam = new JRadioButton("Đang làm");
        rdĐangLam.setBackground(new Color(255, 255, 255));
        rdĐangLam.setBounds(450, 124, 132, 20);
        pnlNhapThongTin.add(rdĐangLam);
        rdĐangLam.setSelected(true);

        rdNghi = new JRadioButton("Đã nghỉ");
        rdNghi.setBackground(new Color(255, 255, 255));
        rdNghi.setBounds(589, 124, 103, 20);
        pnlNhapThongTin.add(rdNghi);

        lblHinhAnh = new JLabel("Hình ảnh");
        lblHinhAnh.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblHinhAnh.setBounds(718, 29, 76, 13);
        pnlNhapThongTin.add(lblHinhAnh);

        btnChonAnh = new JButton("Chọn ảnh");
        btnChonAnh.setBounds(855, 226, 130, 35);
        btnChonAnh.setBackground(new Color(0, 255, 255));
        btnChonAnh.setIcon(new ImageIcon("Anh\\chonanh.png"));
        pnlNhapThongTin.add(btnChonAnh);

        lblKhungHinh = new JLabel(" ");
        lblKhungHinh.setBackground(new Color(255, 255, 255));
        lblKhungHinh.setBounds(816, 28, 204, 178);
        pnlNhapThongTin.add(lblKhungHinh);
        lblKhungHinh.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblKhungHinh.setOpaque(true);
        lblKhungHinh.setBackground(Color.WHITE);

        txtNgay = new JDateChooser();
        txtNgay.getCalendarButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        txtNgay.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txtNgay.setLocale(new Locale("vi", "VN"));
        txtNgay.setForeground(Color.BLACK);
        txtNgay.setDateFormatString("dd/MM/yyyy");
        txtNgay.setBounds(450, 165, 204, 20);
        txtNgay.setDate(new Date(System.currentTimeMillis()));
        pnlNhapThongTin.add(txtNgay);

        pnlDanhSach = new JPanel();
        pnlDanhSach.setBackground(new Color(255, 255, 255));
        pnlDanhSach.setLayout(null);
        pnlDanhSach.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Danh s\u00E1ch nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null,
                new Color(0, 0, 0)));
        pnlDanhSach.setBounds(20, 360, 1284, 414);
        contentPane.add(pnlDanhSach);

        lblThongTinTim = new JLabel("Nhập thông tin tìm kiếm");
        lblThongTinTim.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblThongTinTim.setBounds(10, 20, 134, 13);
        pnlDanhSach.add(lblThongTinTim);

        cbThongTinTim = new JComboBox();
        cbThongTinTim.setBounds(154, 20, 851, 20);
        pnlDanhSach.add(cbThongTinTim);

        lblTim = new JLabel("Tìm theo");
        lblTim.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblTim.setBounds(10, 61, 45, 13);
        pnlDanhSach.add(lblTim);

        rdMaNV = new JRadioButton("Mã nhân viên");
        rdMaNV.setBackground(new Color(255, 255, 255));
        rdMaNV.setBounds(154, 61, 172, 20);
        pnlDanhSach.add(rdMaNV);

        rdTenNV = new JRadioButton("Tên nhân viên");
        rdTenNV.setBackground(new Color(255, 255, 255));
        rdTenNV.setBounds(338, 61, 127, 20);
        pnlDanhSach.add(rdTenNV);

        rdSDT = new JRadioButton("Số điện thoại");
        rdSDT.setBackground(new Color(255, 255, 255));
        rdSDT.setBounds(518, 61, 172, 20);
        pnlDanhSach.add(rdSDT);

        rdCa = new JRadioButton("Ca làm việc");
        rdCa.setBackground(new Color(255, 255, 255));
        rdCa.setBounds(717, 61, 127, 20);
        pnlDanhSach.add(rdCa);

        btnTimkiem = new JButton("Tìm kiếm");
        btnTimkiem.setBackground(new Color(0, 255, 255));
        btnTimkiem.setIcon(new ImageIcon("Anh\\timkiem.png"));
        btnTimkiem.setBounds(1075, 20, 130, 30);
        pnlDanhSach.add(btnTimkiem);

        btnLamMoiThanh = new JButton("Làm mới");
        btnLamMoiThanh.setBackground(new Color(0, 255, 255));
        btnLamMoiThanh.setIcon(new ImageIcon("Anh\\lammoi.png"));
        btnLamMoiThanh.setBounds(1075, 65, 130, 30);
        pnlDanhSach.add(btnLamMoiThanh);

        rdChuc = new JRadioButton("Chức vụ");
        rdChuc.setBackground(new Color(255, 255, 255));
        rdChuc.setBounds(894, 61, 101, 20);
        pnlDanhSach.add(rdChuc);

        gr4 = new ButtonGroup();
        gr4.add(rdMaNV);
        gr4.add(rdTenNV);
        gr4.add(rdChuc);
        gr4.add(rdSDT);
        gr4.add(rdCa);

        JScrollPane scrDSNV;
        String[] tb1 = new String[]{"STT", "Mã NV", "Tên NV", "NgaySinh", "CCCD", "Sdt", "GioiTinh", "TrangThai",
                "CaLamViec", "ChucVu", "Email", "DiaChi"};
        tablemodel = new DefaultTableModel(tb1, 0);
        table_DSKH = new JTable(tablemodel);

        table_DSKH.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        table_DSKH.setBackground(new Color(224, 255, 255));
        table_DSKH.setForeground(new Color(0, 0, 0));
        getContentPane().add(scrDSNV = new JScrollPane(table_DSKH, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
        scrDSNV.setBounds(10, 104, 1262, 300);
        pnlDanhSach.add(scrDSNV);
        scrDSNV.setPreferredSize(new Dimension(0, 250));

        gr1 = new ButtonGroup();
        gr1.add(rdCa1);
        gr1.add(rdCa2);

        gr2 = new ButtonGroup();
        gr2.add(rdNam);
        gr2.add(rdNu);

        gr3 = new ButtonGroup();
        gr3.add(rdĐangLam);
        gr3.add(rdNghi);

        lbl_LoiTen = new JLabel("");
        lbl_LoiTen.setForeground(new Color(255, 0, 0));
        lbl_LoiTen.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lbl_LoiTen.setBackground(new Color(255, 0, 0));
        lbl_LoiTen.setBounds(128, 104, 204, 20);
        pnlNhapThongTin.add(lbl_LoiTen);

        lbl_LoiEmail = new JLabel("");
        lbl_LoiEmail.setForeground(new Color(255, 0, 0));
        lbl_LoiEmail.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lbl_LoiEmail.setBackground(Color.RED);
        lbl_LoiEmail.setBounds(128, 186, 204, 20);
        pnlNhapThongTin.add(lbl_LoiEmail);

        lbl_LoiDiaChi = new JLabel("");
        lbl_LoiDiaChi.setForeground(new Color(255, 0, 0));
        lbl_LoiDiaChi.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lbl_LoiDiaChi.setBackground(Color.RED);
        lbl_LoiDiaChi.setBounds(128, 237, 204, 20);
        pnlNhapThongTin.add(lbl_LoiDiaChi);

        lbl_LoiCCCD = new JLabel("");
        lbl_LoiCCCD.setForeground(new Color(255, 0, 0));
        lbl_LoiCCCD.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lbl_LoiCCCD.setBackground(Color.RED);
        lbl_LoiCCCD.setBounds(128, 280, 204, 20);
        pnlNhapThongTin.add(lbl_LoiCCCD);

        lbl_LoiSDT = new JLabel("");
        lbl_LoiSDT.setForeground(new Color(255, 0, 0));
        lbl_LoiSDT.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lbl_LoiSDT.setBackground(Color.RED);
        lbl_LoiSDT.setBounds(128, 143, 204, 20);
        pnlNhapThongTin.add(lbl_LoiSDT);

        lbl_LoiCa = new JLabel("");
        lbl_LoiCa.setForeground(new Color(255, 0, 0));
        lbl_LoiCa.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lbl_LoiCa.setBackground(Color.RED);
        lbl_LoiCa.setBounds(450, 50, 204, 20);
        pnlNhapThongTin.add(lbl_LoiCa);

        lbl_LoiGioiTinh = new JLabel("");
        lbl_LoiGioiTinh.setForeground(new Color(255, 0, 0));
        lbl_LoiGioiTinh.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lbl_LoiGioiTinh.setBackground(Color.RED);
        lbl_LoiGioiTinh.setBounds(450, 98, 204, 20);
        pnlNhapThongTin.add(lbl_LoiGioiTinh);

        lbl_LoiNgay = new JLabel("");
        lbl_LoiNgay.setForeground(new Color(255, 0, 0));
        lbl_LoiNgay.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lbl_LoiNgay.setBackground(Color.RED);
        lbl_LoiNgay.setBounds(450, 193, 204, 20);
        pnlNhapThongTin.add(lbl_LoiNgay);

        docDuLieu();
        UpdateComBoBox();
        upDateComBoBoxChucVu();
        khoaText(lock);

        table_DSKH.addMouseListener(this);

        btnThem.addActionListener(this);
        btnChonAnh.addActionListener(this);
        btnLamMoi.addActionListener(this);
        btnTimkiem.addActionListener(this);
        btnSua.addActionListener(this);
        btnLuu.addActionListener(this);
        btnLamMoiThanh.addActionListener(this);

        rdMaNV.addActionListener(this);
        rdTenNV.addActionListener(this);
        rdSDT.addActionListener(this);
        rdCa.addActionListener(this);
        rdChuc.addActionListener(this);
        txtCCCD.addKeyListener(this);
        txtSDT.addKeyListener(this);
        txtNgay.addKeyListener(this);


        cbChucVu.addItemListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        btnThem.setEnabled(false);
        int d = 1;
        int row = table_DSKH.getSelectedRow();
        txtMaNV.setText(tablemodel.getValueAt(row, 1).toString());
        maBanDau = tablemodel.getValueAt(row, 1).toString();
        textTen.setText(tablemodel.getValueAt(row, 2).toString());
        java.util.Date utilDate = (java.util.Date) tablemodel.getValueAt(row, 3);
        Date ngaySinh = new Date(utilDate.getTime());
        txtNgay.setDate(ngaySinh);
        txtCCCD.setText(tablemodel.getValueAt(row, 4).toString());
        txtSDT.setText(tablemodel.getValueAt(row, 5).toString());
        String cbGioiTinh = tablemodel.getValueAt(row, 6).toString();
        String cbTrangThai = tablemodel.getValueAt(row, 7).toString();
        String cbCa = tablemodel.getValueAt(row, 8).toString();
        // Lấy giá trị chức vụ từ dòng đã chọn
        String selectedChucVu = (String) tablemodel.getValueAt(row, 9); // columnIndex là vị trí cột chức vụ
        // Đặt giá trị vào ComboBox
        cboModeChucVu.setSelectedItem(selectedChucVu);
        txtEmail.setText(tablemodel.getValueAt(row, 10).toString());
        txtDiaChi.setText(tablemodel.getValueAt(row, 11).toString());
        try {
            List<NhanVien> list = dao.getAllNV();
            for (NhanVien x : list) {
                if (x.getMaNhanvien().equals(tablemodel.getValueAt(row, 1).toString())) {
                    if (x.getTrangThai() == 1) {
                        rdĐangLam.setSelected(true);
                        rdNghi.setSelected(false);
                    } else {
                        rdĐangLam.setSelected(false);
                        rdNghi.setSelected(true);
                    }

                    if (x.getGioiTinh() == 1) {
                        rdNam.setSelected(true);
                        rdNu.setSelected(false);
                    } else {
                        rdNam.setSelected(false);
                        rdNu.setSelected(true);
                    }

                    if (x.getCaLamViec() == 1) {
                        rdCa1.setSelected(true);
                        rdCa2.setSelected(false);
                    } else {
                        rdCa1.setSelected(false);
                        rdCa2.setSelected(true);
                    }
                    ImageIcon imageIcon = new ImageIcon(
                            new ImageIcon(dao.getNVTHeoMa(tablemodel.getValueAt(row, 1).toString()).getHinhAnh()).getImage()
                                    .getScaledInstance(lblKhungHinh.getWidth(), lblKhungHinh.getHeight(),
                                            Image.SCALE_SMOOTH));
                    lblKhungHinh.setIcon(imageIcon);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void upDateComBoBoxChucVu() {
        List<NhanVien> list = null;
        try {
            list = dao.getAllNV();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String chuc = "";
        HashSet<String> cvnv = new HashSet<>();

        cboModeChucVu.addElement("Nhân Viên");
        cboModeChucVu.addElement("Quản Lý");

        for (NhanVien nv : list) {
            if (nv.getChucVu() == 1)
                chuc = "Quản lý";
            else if (nv.getChucVu() == 0)
                chuc = "Nhân viên";
            cvnv.add(chuc);

        }
        // Đặt lại mô hình của combobox

        cbChucVu.setModel(cboModeChucVu);
    }

    public void docDuLieu() {

        int d = 1;
        List<NhanVien> list = null;
        try {
            list = dao.getAllNV();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<Integer> addedChucVu = new ArrayList<>(); // Sử dụng List để kiểm tra giá trị đã được thêm

        // Xóa tất cả các dòng hiện có trong bảng
        DefaultTableModel tablemodel = (DefaultTableModel) table_DSKH.getModel();
        tablemodel.setRowCount(0);

        for (NhanVien x : list) {
            int chucVu = x.getChucVu();
            String chucVuText = "";
            int ca = x.getCaLamViec();
            String catext = "";

            if (chucVu == 1) {
                chucVuText = "Quản lý";
            } else {
                chucVuText = "Nhân viên";
            }

            String trangThaiText = x.getTrangThai() == 1 ? "Đang làm" : "Đã nghỉ";

            String gioiTinhText = x.getGioiTinh() == 1 ? "Nam" : "Nữ";
            if (ca == 1) {
                catext = "Ca 1";
            } else {
                catext = "Ca 2";
            }
//	        String calam = x.getCaLamViec();

            tablemodel.addRow(new Object[]{d++, x.getMaNhanvien(), x.getTenNhanVien(), x.getNgaySinh(), x.getCccd(),
                    x.getSdt(), gioiTinhText, trangThaiText, x.getCaLamViec(), chucVuText, x.getEmail(),
                    x.getDiaChi()});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem)) {
            if (chkThem == false)
                them();
            else if (chkThem == true) {
                luuThanhCong();
            }
        } else if (o.equals(btnLamMoi)) {
            xoaTrang();

        } else if (o.equals(btnTimkiem)) {
            deleteAllDataTable();
            tim();
        } else if (o.equals(btnChonAnh)) {
            chonAnh();
        } else if (o.equals(btnSua)) {
            if (chkSua == false)
                sua();
            else if (chkSua == true) {
                luuThanhCong();
            }
        } else if (o.equals(btnLamMoiThanh)) {
            try {
                xoaTrangThanhTK();
                xoaTrang();
                cbThongTinTim.removeAllItems();
            } catch (ClassNotFoundException | SQLException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
        } else if (o.equals(btnLuu)) {
            try {
                luuThongTin();

            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (o.equals(rdCa) || o.equals(rdChuc) || o.equals(rdMaNV) || o.equals(rdSDT) || o.equals(rdTenNV)) {
            UpdateComBoBox();
        }

    }

    public void phatSinhMa() {

    }

    public void luuThongTin() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String maNV = txtMaNV.getText();
        String tenNV = textTen.getText();
        String ngaySinh1 = dateFormat.format(txtNgay.getDate());
        java.util.Date ngaySinh2 = dateFormat.parse(ngaySinh1);
        Date ngaySinhsql = new Date(ngaySinh2.getTime());
        String Cccd = txtCCCD.getText();
        String Sdt = txtSDT.getText();
        int gioiTinh = 0;
        int trangThai = 1;
        int caLamViec = 1;
        int chucVu = 0;
        String chucVuCbb = (String) cboModeChucVu.getSelectedItem();
        if (chucVuCbb.equalsIgnoreCase("Quản lý")) {
            chucVu = 1;
        } else if (chucVuCbb.equalsIgnoreCase("Nhân viên"))
            chucVu = 0;
        String chucVuText = "";
        String img = folderName;
        String Email = txtEmail.getText();
        String diaChi = txtDiaChi.getText();

        if (rdNam.isSelected()) {
            gioiTinh = 1;
        } else if (rdNu.isSelected()) {
            gioiTinh = 0;
        }

        if (rdĐangLam.isSelected()) {
            trangThai = 1;
        } else if (rdNghi.isSelected()) {
            trangThai = 2;
        }
        if (rdCa1.isSelected()) {
            caLamViec = 1;
        } else if (rdCa2.isSelected()) {
            caLamViec = 2;
        }
        NhanVien nv = valiData();
        if (nv == null)
            return;
        else {
            if (chkThem == true && chkSua == false) {
                try {
                    boolean moi = dao.them(maNV, tenNV, ngaySinhsql, Cccd, Sdt, gioiTinh, trangThai, caLamViec, chucVu,
                            img, Email, diaChi);
                    if (moi == true) {
                        dao.addTaiKhoan(maNV);
                        System.out.println(maNV);
                        xoaTrang();
                        deleteAllDataTable();
                        docDuLieu();
                        txtMaNV.setText(defaultID());
                        JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
                        luuThanhCong();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm nhân viên không thành công");
                    }

                } catch (Exception e) {
                    e.printStackTrace();

                }
            } else if (chkSua == true && chkThem == false) {
                try {
                    if (img != null) {
                        if (dao.sua(maBanDau, tenNV, ngaySinhsql, Cccd, Sdt, gioiTinh, trangThai, caLamViec,
                                chucVu, Email, diaChi, img, maNV)) {
                            xoaTrang();
                            deleteAllDataTable();
                            docDuLieu();
                            txtMaNV.setText(defaultID());
                            JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công");
                            luuThanhCong();
                        } else
                            JOptionPane.showMessageDialog(this, "Cập nhật nhân viên không thành công!");
                    } else {
                        boolean moi = dao.suakhonganh(maBanDau, tenNV, ngaySinhsql, Cccd, Sdt, gioiTinh, trangThai,
                                caLamViec, chucVu, Email, diaChi, maNV);
                        if (moi == true) {
                            xoaTrang();
                            deleteAllDataTable();
                            docDuLieu();
                            txtMaNV.setText(defaultID());
                            JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công");
                            luuThanhCong();

                        } else {
                            JOptionPane.showMessageDialog(this, "Cập nhật nhân viên không thành công!");
                        }
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //	Hàm xử lý sau khi lưu thay đổi thành công
    public void luuThanhCong() {
        chkSua = false;
        chkThem = false;
        btnSua.setText("Sửa");
        btnThem.setText("Thêm");
        lock = false;
        khoaText(lock);
        btnThem.setEnabled(true);
        btnThem.setIcon(new ImageIcon("Anh\\them.png"));
        btnSua.setIcon(new ImageIcon("Anh\\sua.png"));
        btnSua.setEnabled(true);
    }

    public void deleteAllDataTable() {
        tablemodel = (DefaultTableModel) table_DSKH.getModel();
        tablemodel.getDataVector().removeAllElements();
    }

    private void xoaTrang() {
        txtMaNV.setText(defaultID());
        textTen.setText("");
        txtNgay.setDate(new Date(System.currentTimeMillis()));
        txtCCCD.setText("");
        txtSDT.setText("");
        rdNam.setSelected(false);
        rdNu.setSelected(false);
        rdĐangLam.setSelected(false);
        rdNghi.setSelected(false);
        rdCa1.setSelected(false);
        rdCa2.setSelected(false);
        txtEmail.setText("");
        txtDiaChi.setText("");
        btnThem.setEnabled(true);
        btnSua.setEnabled(true);
        gr1.clearSelection();
        gr2.clearSelection();
        gr3.clearSelection();
        textTen.requestFocus();

        deleteAllDataTable();
        docDuLieu();

    }

    private String defaultID() {
        int n = 0;
        try {
            n = dao.getAllNV().size() + 1;
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String soLuongNV = String.format("%03d", n);
        String deFault = "NV" + soLuongNV;
        return deFault;
    }

    private void UpdateComBoBox() {

//		Lưu dữ liệu vào comboBOx

        try {
            List<NhanVien> listNV = dao.getAllNV();
            List<String> trangThaiList = new ArrayList<>();
            cbThongTinTim.removeAllItems();
            cbThongTinTim.setEditable(true);

            for (NhanVien n : listNV) {
                if (rdMaNV.isSelected() && n.getMaNhanvien() != null) {
                    cbThongTinTim.addItem(n.getMaNhanvien());
                } else if (rdTenNV.isSelected() && n.getTenNhanVien() != null) {
                    cbThongTinTim.addItem(n.getTenNhanVien());

                } else if (rdSDT.isSelected() && n.getSdt() != null) {
                    cbThongTinTim.addItem(n.getSdt());

                }
            }
            if (rdCa.isSelected()) {
                cbThongTinTim.addItem("1");
                cbThongTinTim.addItem("2");
            }
            if (rdChuc.isSelected()) {
                cbThongTinTim.addItem("Quản lý");
                cbThongTinTim.addItem("Nhân viên");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sua() {
        lock = true;
        khoaText(lock);
        btnSua.setText("Hủy");
        btnSua.setIcon(new ImageIcon("Anh\\huy.png"));
        btnThem.setEnabled(false);
        chkSua = true;

    }

    public void them() {
        lock = true;
        khoaText(lock);
        btnThem.setText("Hủy");
        btnThem.setIcon(new ImageIcon("Anh\\huy.png"));
        btnSua.setEnabled(false);
        chkThem = true;

    }

    public NhanVien valiData() {
        NhanVien nv;
        String maNV = txtMaNV.getText().trim();
//		txtmanv.setText(defaultID());
        if (textTen.getText().isEmpty() || textTen.getText().trim() == "") {

            lbl_LoiTen.setText("Tên không được rỗng");
            return null;
        } else {
            lbl_LoiTen.setText("");
        }
        String tenNV = textTen.getText().trim();
        if (txtSDT.getText().isEmpty() || txtSDT.getText().trim() == "") {
            lbl_LoiSDT.setText("Số điện thoại không được rỗng");
            return null;
        } else if (txtSDT.getText().trim().matches("^[0]\\d{9}$") == false) {
            lbl_LoiSDT.setText("Số điện thoại không hợp lệ");
            return null;

        } else {
            lbl_LoiSDT.setText("");
        }
        String sdt = txtSDT.getText().trim();

        if (txtEmail.getText().isEmpty() || txtEmail.getText().trim() == "") {
            lbl_LoiEmail.setText("Email không được rỗng");
            return null;
        } else if (txtEmail.getText().trim().matches("^\\b[\\w.%+-]+@[\\w.-]+\\.com\\b$") == false) {
            lbl_LoiEmail.setText("Email không hợp lệ");
            return null;
        } else {
            lbl_LoiEmail.setText("");
        }
        String mail = txtEmail.getText().trim();
        if (txtDiaChi.getText().isEmpty() || txtDiaChi.getText().trim() == "") {
            lbl_LoiDiaChi.setText("Địa chỉ không được rỗng");
            return null;
        } else {
            lbl_LoiDiaChi.setText("");
        }
        String diaChi = txtDiaChi.getText().trim();
        String CCCD = txtCCCD.getText().trim();
        if (txtCCCD.getText().isEmpty() || txtCCCD.getText().trim() == "") {
            lbl_LoiCCCD.setText("CCCD không được rỗng");
            return null;
        } else if (txtCCCD.getText().trim().matches("^[0]\\d{11}$") == false) {
            lbl_LoiCCCD.setText("CCCD không hợp lệ");
            return null;
        } else {
            lbl_LoiCCCD.setText("");
        }
        int ca = 0;
        if (rdCa1.isSelected()) {
            ca = 1;
            lbl_LoiCa.setText("");
        } else if (rdCa2.isSelected()) {
            ca = 2;
            lbl_LoiCa.setText("");
        } else if (ca == 0) {
            lbl_LoiCa.setText("Chưa chọn ca làm");
            return null;
        }
        int gioiTinh = 0;
        if (rdNam.isSelected()) {
            gioiTinh = 1;
            lbl_LoiGioiTinh.setText("");
        } else if (rdNu.isSelected()) {
            gioiTinh = 2;
            lbl_LoiGioiTinh.setText("");
        } else if (gioiTinh == 0) {
            lbl_LoiGioiTinh.setText("Chưa chọn giới tính");
            return null;
        }

        java.util.Date ngaySinh = txtNgay.getDate();
        if (ngaySinh == null) {
            lbl_LoiNgay.setText("Ngày sinh không được rỗng");
            return null;
        } else {

            LocalDate ngaySinhLocal = ngaySinh.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate ngayHienTaiLocal = LocalDate.now();

            int tuoi = Period.between(ngaySinhLocal, ngayHienTaiLocal).getYears();

            // Kiểm tra xem người dùng có đủ 18 tuổi không
            if (tuoi >= 18) {
                // Người dùng đủ tuổi
                lbl_LoiNgay.setText("");
            } else {
                lbl_LoiNgay.setText("Bạn chưa đủ 18 tuổi");
                return null;
            }
        }
        try {
            nv = new NhanVien(maNV, tenNV, ngaySinh, CCCD, sdt, gioiTinh, 1, ca, 0, mail, diaChi, folderName);
            return nv;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    private void ShowErrorField(String string, JTextField txt) {
        JOptionPane.showMessageDialog(null, string);
        txt.requestFocus();

    }

    private void xoaTrangThanhTK() throws ClassNotFoundException, SQLException {
        gr4.clearSelection();
        cbThongTinTim.setSelectedItem("");
        deleteAllDataTable();
        docDuLieu();
    }

    // chon anh
    public String chonAnh() {
        try {
            String user = System.getProperty("user.dir");
            JFileChooser fileChooser = new JFileChooser(user + "\\AnhNhanVien");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("png file", "png");
            FileNameExtensionFilter filterJPG = new FileNameExtensionFilter("jpg file", "jpg");

            fileChooser.addChoosableFileFilter(filter);
            fileChooser.addChoosableFileFilter(filterJPG);
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();

                // Load the selected image into the label
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(filePath).getImage()
                        .getScaledInstance(lblKhungHinh.getWidth(), lblKhungHinh.getHeight(), Image.SCALE_SMOOTH));
                lblKhungHinh.setIcon(imageIcon);
                File file = new File(filePath);

                String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
                folderName = file.getParentFile().getName() + "\\\\" + fileName;

//				System.out.println(folderName);
                return folderName; // Return the file path
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return folderName;
    }

    public void tim() {
        try {
            deleteAllDataTable();
            int d = 1;
            List<NhanVien> list = dao.getAllNV();
            List<Integer> addedChucVu = new ArrayList<>(); // Sử dụng List để kiểm tra giá trị đã được thêm

            // Xóa tất cả các dòng hiện có trong bảng
            DefaultTableModel tablemodel = (DefaultTableModel) table_DSKH.getModel();
            tablemodel.setRowCount(0);
            cbThongTinTim.setEditable(true);
            for (NhanVien x : list) {
                String trangThaiText = x.getTrangThai() == 1 ? "Đang làm" : "Đã nghỉ";
                int ca = 2;
                String tim = "";
                try {
                    tim = cbThongTinTim.getSelectedItem().toString();

                } catch (Exception e) {
                    // TODO: handle exception
                }
                if (tim.equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(this, "Hãy chọn và nhập phương thức tìm kiếm!");
                    return;
                }
//			Tìm theo tên
                if (rdTenNV.isSelected()) {
                    deleteAllDataTable();

                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    NhanVien nv = dao.getNVTHeoTen(tim);
                    bangTimKiem(nv, d);
                }
//			tìm theo mã
                else if (rdMaNV.isSelected()) {
                    deleteAllDataTable();

                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    NhanVien nv = dao.getNVTHeoMa(tim);
                    bangTimKiem(nv, d);
                }
//			Tìm theo số điện thoại
                else if (rdSDT.isSelected()) {
                    deleteAllDataTable();

                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    NhanVien nv = dao.getNVTHeoSdt(tim);
                    bangTimKiem(nv, d);
                }
//			Tìm theo Ca làm việc
                else if (rdCa.isSelected()) {
                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    deleteAllDataTable();
                    if (cbThongTinTim.getSelectedItem().equals("1")) {
                        ca = 1;
                    } else if (cbThongTinTim.getSelectedItem().equals("2"))
                        ca = 2;

                    List<NhanVien> listNV = dao.getNVTHeoCa(ca);
                    int index = 0;
                    for (NhanVien nv : listNV) {
                        bangTimKiem(nv, ++index);
                    }
                }
//			Tìm theo chức vụ
                else if (rdChuc.isSelected()) {
                    deleteAllDataTable();

                    String chuc = cbThongTinTim.getSelectedItem().toString();
                    int indexChuc = 0;
                    if (chuc.equalsIgnoreCase("Quản lý"))
                        indexChuc = 1;
                    else
                        indexChuc = 0;

                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    List<NhanVien> listChucVu = dao.getNVTHeoChuc(indexChuc);
                    int index = 0;
                    for (NhanVien nv : listChucVu)

                        if (nv.getChucVu() == 1) {
                            bangTimKiem(nv, ++index);
                        } else {
                            bangTimKiem(nv, ++index);
                        }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //	Xuất kết quả tìm kiếm
    public void bangTimKiem(NhanVien nv, int d) {
        int cv = nv.getChucVu();
        String chucVuText = "";
        if (cv == 0) {
            chucVuText = "Nhân viên";
        } else if (cv != 0) {
            chucVuText = "Quản lý";
        }
        String gend = nv.getGioiTinh() == 1 ? "Nam" : "Nữ";
        String status = nv.getTrangThai() == 1 ? "Đang làm" : "Đã nghỉ";
        tablemodel.addRow(new Object[]{d, nv.getMaNhanvien(), nv.getTenNhanVien(), nv.getNgaySinh(), nv.getCccd(),
                nv.getSdt(), gend, status, nv.getCaLamViec(), chucVuText, nv.getEmail(), nv.getDiaChi()});
    }

    //	Khóa các textField
    public void khoaText(boolean lock) {
        txtDiaChi.setEditable(lock);
        textTen.setEditable(lock);
        txtEmail.setEditable(lock);
        txtSDT.setEditable(lock);
        txtCCCD.setEditable(lock);
        btnLuu.setEnabled(lock);
        cbChucVu.setEditable(lock);

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        try {
            Object o = e.getSource();
            if (o.equals(cbChucVu)) {
                if (e.getStateChange() == ItemEvent.SELECTED && !isEventFromTable()) {
                    String chucVu = "";
                    if (cboModeChucVu.getSelectedItem().toString().equalsIgnoreCase("Nhân viên")) {
                        Long soLuongNVDL = dao.soLuongNV();
                        List<NhanVien> list = dao.getAllNV();
                        int n = 1;
                        chucVu = "NV";
                        String soLuongNV = String.format("%03d", n);
                        String deFault = "";
                        if (soLuongNVDL <= 0) {
                            deFault = chucVu + soLuongNV;
                        } else {
                            for (NhanVien nv : list) {
                                while (nv.getMaNhanvien().equalsIgnoreCase(chucVu + soLuongNV)) {

                                    n++;
                                    soLuongNV = String.format("%03d", n);
                                    deFault = chucVu + soLuongNV;

                                }
                            }
                        }

                        txtMaNV.setText(deFault);
                    } else {
                        int l = 1;
                        Long soLuongQLDL = dao.soLuongQL();
                        String soLuongQL = String.format("%03d", l);
                        chucVu = "QL";
                        String deFaultQL = chucVu + soLuongQL;
                        List<NhanVien> list = dao.getAllNV();
                        if (soLuongQLDL <= 0) {
                            deFaultQL = chucVu + soLuongQL;
                        } else {
                            for (NhanVien nv : list) {
                                while (nv.getMaNhanvien().equalsIgnoreCase(chucVu + soLuongQL)) {

                                    l++;
                                    soLuongQL = String.format("%03d", l);
                                    deFaultQL = chucVu + soLuongQL;
                                }
                            }

                        }

                        txtMaNV.setText(deFaultQL);
                    }

                }
            }

        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
    }

    // Kiểm tra xem sự kiện có phát sinh từ table hay không
    // Trong trường hợp này, giả sử table_DSKH là đối tượng JTable
    private boolean isEventFromTable() {

        return table_DSKH.isFocusOwner();
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

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        Object o = e.getSource();
        if (o.equals(txtNgay) || o.equals(txtCCCD) || o.equals(txtSDT)) {
            if (!((e.getKeyChar() >= 48 && e.getKeyChar() <= 57) || e.getKeyChar() == 8)) {
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub

    }
}
