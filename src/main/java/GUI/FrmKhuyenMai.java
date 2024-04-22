package GUI;

import DAOTest.KhuyenMaiDao;
import DAOTest.SanPhamDao;
import DAOTest.impl.KhuyenMaiImpl;
import DAOTest.impl.SanPhamImpl;
import Entities.KhuyenMai;
import Entities.SanPham;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FrmKhuyenMai extends JFrame implements ActionListener, MouseListener, ItemListener {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel tablemodel;
    private JLabel lblTieuDeTrang;
    static JPanel pnlThongTin;
    private JTextField txtMaKM;
    private JTextField txtTenKM;
    private JDateChooser txtChonNgayBD;
    private JTable tableThongTinKM;
    private KhuyenMaiDao kmDao = (KhuyenMaiDao) Naming.lookup(URL + "KhuyenMaiDao");;
    private JButton btnThem;
    private JPanel panel_TieuDe;
    private JPanel panel_CTKM;
    private JLabel lblMaKM;
    private JLabel lblTenKM;
    private JLabel lblKM;
    private JLabel lblStartDate;
    private JLabel lblEndDate;
    private JLabel lblTrangThai;
    private JLabel lblApDungAll;
    private JComboBox cboPhanTram;
    private JDateChooser txtChonNgayKT;
    private JRadioButton rdApDung;
    private JRadioButton rdHetHan;
    private JCheckBox chkApAll;
    private JPanel panel_DSSP;
    private JLabel lblTimKiemSP;
    private JComboBox cbbTimKiemSP;
    private JPanel panel_Chucnang;
    private JDateChooser txtTimStartDate;
    private JDateChooser txtTimEndDate;
    private DefaultTableModel tablemodel1;
    private JButton btnLamMoiCT;
    private JComboBox<String> cbbTimKiem;

    private DefaultComboBoxModel cboModePhanTram = new DefaultComboBoxModel();

    private List<String> listPhantram = new ArrayList<>();
    private ButtonGroup gr1;
    private JComboBox comboBox;
    private boolean khuyenMai;
    private boolean kmmoi;
    private JTable table_SP;
    private DefaultComboBoxModel cboModelPhanTram;
    private JButton btnSua;
    private JPanel panel_TKCTKM;
    private SimpleDateFormat sdf;
    private Date dateBD;
    private Date dateKT;
    private JRadioButton rdMa;
    private JRadioButton rdTen;
    private JRadioButton rdPhanTram;
    private JRadioButton rdTrangThai;
    private ButtonGroup gr2;
    private JButton btnTimKiem;
    private String trangThaiText;

    boolean lock = false;
    boolean chkThem = false;
    boolean chkSua = false;
    private SanPhamDao daoSP = (SanPhamDao) Naming.lookup(URL + "SanPhamDao");;
    private JButton btnLamMoiTimKiem;
    private JButton btnLuu;

    private List<String> selectedRowsValues = new ArrayList<>();
    private static final String URL = "rmi://HOANGPHUC:6541/";


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmKhuyenMai frame = new FrmKhuyenMai();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public FrmKhuyenMai() throws RemoteException, MalformedURLException, NotBoundException {
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

        panel_TieuDe = new JPanel();
        panel_TieuDe.setBackground(new Color(0, 255, 255));
        panel_TieuDe.setBounds(0, 0, 1343, 41);
        pnlThongTin.add(panel_TieuDe);
        panel_TieuDe.setLayout(null);
        lblTieuDeTrang = new JLabel("CH\u01AF\u01A0NG TR\u00CCNH KHUY\u1EBEN M\u00C3I");
        lblTieuDeTrang.setBounds(512, 12, 319, 25);
        panel_TieuDe.add(lblTieuDeTrang);
        lblTieuDeTrang.setBackground(new Color(0, 255, 255));
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));

        panel_CTKM = new JPanel();
        panel_CTKM.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Thi\u1EBFt l\u1EADp ch\u01B0\u01A1ng tr\u00ECnh khuy\u1EBFn m\u00E3i", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_CTKM.setBackground(new Color(255, 255, 255));
        panel_CTKM.setBounds(20, 48, 1292, 291);
        pnlThongTin.add(panel_CTKM);
        panel_CTKM.setLayout(null);

        lblMaKM = new JLabel("M\u00E3 khuy\u1EBFn m\u00E3i");
        lblMaKM.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblMaKM.setBounds(10, 24, 148, 15);
        panel_CTKM.add(lblMaKM);

        lblTenKM = new JLabel("T\u00EAn khuy\u1EBFn m\u00E3i");
        lblTenKM.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblTenKM.setBounds(10, 59, 148, 15);
        panel_CTKM.add(lblTenKM);

        lblKM = new JLabel("Khuyến mãi (%):");
        lblKM.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblKM.setBounds(10, 99, 148, 15);
        panel_CTKM.add(lblKM);

        lblStartDate = new JLabel("Ng\u00E0y b\u1EAFt \u0111\u1EA7u");
        lblStartDate.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblStartDate.setBounds(10, 138, 148, 15);
        panel_CTKM.add(lblStartDate);

        txtChonNgayBD = new JDateChooser();
        txtChonNgayBD.getCalendarButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        txtChonNgayBD.setBounds(153, 138, 289, 21);
        txtChonNgayBD.setForeground(new Color(0, 0, 0));
        txtChonNgayBD.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txtChonNgayBD.setLocale(Locale.forLanguageTag("vi-VN"));
        txtChonNgayBD.setDateFormatString("dd/MM/yyyy");
        txtChonNgayBD.setDate(new Date(System.currentTimeMillis()));

        panel_CTKM.add(txtChonNgayBD);

        lblEndDate = new JLabel("Ng\u00E0y k\u1EBFt th\u00FAc");
        lblEndDate.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblEndDate.setBounds(10, 179, 148, 15);
        panel_CTKM.add(lblEndDate);

        lblTrangThai = new JLabel("Tr\u1EA1ng th\u00E1i");
        lblTrangThai.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblTrangThai.setBounds(10, 220, 148, 15);
        panel_CTKM.add(lblTrangThai);

        lblApDungAll = new JLabel("\u00C1p d\u1EE5ng t\u1EA5t c\u1EA3 s\u1EA3n ph\u1EA9m");
        lblApDungAll.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblApDungAll.setBounds(10, 262, 148, 15);
        panel_CTKM.add(lblApDungAll);

        txtMaKM = new JTextField(deFaultID());
        txtMaKM.setBounds(153, 22, 289, 21);
        panel_CTKM.add(txtMaKM);
        txtMaKM.setColumns(10);

        txtTenKM = new JTextField();
        txtTenKM.setColumns(10);
        txtTenKM.setBounds(153, 57, 289, 21);
        panel_CTKM.add(txtTenKM);

        cboPhanTram = new JComboBox();
        cboPhanTram.setBounds(153, 96, 289, 21);
        panel_CTKM.add(cboPhanTram);

        txtChonNgayKT = new JDateChooser();
        txtChonNgayKT.getCalendarButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        txtChonNgayKT.setBounds(153, 169, 289, 21);
        txtChonNgayKT.setForeground(new Color(0, 0, 0));
        txtChonNgayKT.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txtChonNgayKT.setLocale(Locale.forLanguageTag("vi-VN"));
        txtChonNgayKT.setDateFormatString("dd/MM/yyyy");
        txtChonNgayKT.setDate(new Date(System.currentTimeMillis()));

        panel_CTKM.add(txtChonNgayKT);

        rdApDung = new JRadioButton("\u0110ang \u00E1p d\u1EE5ng");
        rdApDung.setBackground(new Color(255, 255, 255));
        rdApDung.setBounds(153, 217, 103, 21);
        panel_CTKM.add(rdApDung);

        rdHetHan = new JRadioButton("\u0110\u00E3 h\u1EBFt h\u1EA1n");
        rdHetHan.setBackground(Color.WHITE);
        rdHetHan.setBounds(281, 217, 103, 21);
        panel_CTKM.add(rdHetHan);

        chkApAll = new JCheckBox("\u00C1p d\u1EE5ng");

        chkApAll.setBackground(new Color(255, 255, 255));
        chkApAll.setBounds(153, 259, 93, 21);
        panel_CTKM.add(chkApAll);

        panel_DSSP = new JPanel();
        panel_DSSP.setBorder(new TitledBorder(null, "Danh s\u00E1ch s\u1EA3n ph\u1EA9m", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        panel_DSSP.setBackground(new Color(255, 255, 255));
        panel_DSSP.setBounds(498, 22, 537, 255);
        panel_CTKM.add(panel_DSSP);
        panel_DSSP.setLayout(null);

        lblTimKiemSP = new JLabel("T\u00ECm ki\u1EBFm s\u1EA3n ph\u1EA9m");
        lblTimKiemSP.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblTimKiemSP.setBounds(10, 20, 148, 15);
        panel_DSSP.add(lblTimKiemSP);

        cbbTimKiemSP = new JComboBox();
        cbbTimKiemSP.setEditable(true);
        cbbTimKiemSP.setBounds(131, 15, 396, 25);
        panel_DSSP.add(cbbTimKiemSP);

        JScrollPane scrDSSP;
        String[] head = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Chọn"};
        tablemodel = new DefaultTableModel(head, 0);
        table_SP = new JTable(tablemodel);
        table_SP.setEnabled(false);

        table_SP.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        table_SP.setBackground(new Color(204, 255, 255));
        table_SP.setForeground(new Color(0, 0, 0));
        getContentPane().add(scrDSSP = new JScrollPane(table_SP, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
        scrDSSP.setBounds(10, 45, 517, 200);
        panel_DSSP.add(scrDSSP);
        scrDSSP.setPreferredSize(new Dimension(0, 250));

        panel_Chucnang = new JPanel();
        panel_Chucnang.setBorder(
                new TitledBorder(null, "Ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_Chucnang.setBackground(new Color(255, 255, 255));
        panel_Chucnang.setBounds(1041, 60, 241, 189);
        panel_CTKM.add(panel_Chucnang);
        panel_Chucnang.setLayout(null);

        btnThem = new JButton("Thêm");
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnThem.setBackground(new Color(0, 255, 255));
        btnThem.setBounds(42, 21, 160, 30);
        btnThem.setIcon(new ImageIcon("Anh\\them.png"));
        panel_Chucnang.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSua.setBackground(new Color(0, 255, 255));
        btnSua.setBounds(42, 61, 160, 30);
        btnSua.setIcon(new ImageIcon("Anh\\sua.png"));
        panel_Chucnang.add(btnSua);

        btnLamMoiCT = new JButton("Làm mới");
        btnLamMoiCT.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLamMoiCT.setBackground(new Color(0, 255, 255));
        btnLamMoiCT.setBounds(42, 101, 160, 30);
        btnLamMoiCT.setIcon(new ImageIcon("Anh\\lammoi.png"));
        panel_Chucnang.add(btnLamMoiCT);

        btnLuu = new JButton("Lưu");
        btnLuu.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLuu.setBackground(Color.CYAN);
        btnLuu.setBounds(42, 141, 160, 30);
        btnLuu.setIcon(new ImageIcon("Anh\\luu.png"));
        panel_Chucnang.add(btnLuu);

        panel_TKCTKM = new JPanel();
        panel_TKCTKM.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Th\u00F4ng tin ch\u01B0\u01A1ng tr\u00ECnh khuy\u1EBFn m\u00E3i", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_TKCTKM.setBackground(Color.WHITE);
        panel_TKCTKM.setBounds(20, 349, 1292, 447);
        pnlThongTin.add(panel_TKCTKM);
        panel_TKCTKM.setLayout(null);

        JScrollPane scrDSNV;
        String[] tb2 = new String[]{"Mã KM", "Tên khuyến mãi", "Phần trăm khuyến mãi", "Ngày bắt đầu",
                "Ngày kết thúc", "Trạng thái", "Số lượng SP áp dụng"};
        tablemodel1 = new DefaultTableModel(tb2, 0);
        tableThongTinKM = new JTable(tablemodel1);
        docDuLieu();

        tableThongTinKM.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        tableThongTinKM.setBackground(new Color(224, 255, 255));
        tableThongTinKM.setForeground(new Color(0, 0, 0));
        getContentPane().add(scrDSNV = new JScrollPane(tableThongTinKM, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);

        scrDSNV.setBounds(10, 79, 1272, 358);
        panel_TKCTKM.add(scrDSNV);
        scrDSNV.setPreferredSize(new Dimension(0, 250));

        btnLamMoiTimKiem = new JButton("Làm mới");
        btnLamMoiTimKiem.setBackground(new Color(0, 255, 255));
        btnLamMoiTimKiem.setBounds(1151, 22, 120, 30);
        btnLamMoiTimKiem.setIcon(new ImageIcon("Anh\\lammoi.png"));
        panel_TKCTKM.add(btnLamMoiTimKiem);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setBackground(new Color(0, 255, 255));
        btnTimKiem.setBounds(1018, 22, 120, 30);
        btnTimKiem.setIcon(new ImageIcon("Anh\\timkiem.png"));
        panel_TKCTKM.add(btnTimKiem);

        JLabel lblNhapTim = new JLabel("Nhập thông tin tìm kiếm:");
        lblNhapTim.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblNhapTim.setBounds(20, 22, 140, 15);
        panel_TKCTKM.add(lblNhapTim);

        JLabel lblTimTheo = new JLabel("Tìm theo:");
        lblTimTheo.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblTimTheo.setBounds(20, 54, 99, 15);
        panel_TKCTKM.add(lblTimTheo);

        JLabel lblNgayBatDau = new JLabel("Ng\u00E0y b\u1EAFt \u0111\u1EA7u");
        lblNgayBatDau.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblNgayBatDau.setBounds(729, 22, 84, 15);
        panel_TKCTKM.add(lblNgayBatDau);

        JLabel lblNgayKetThuc = new JLabel("Ng\u00E0y k\u1EBFt th\u00FAc");
        lblNgayKetThuc.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblNgayKetThuc.setBounds(729, 54, 84, 15);
        panel_TKCTKM.add(lblNgayKetThuc);

        cbbTimKiem = new JComboBox();
        cbbTimKiem.setEditable(true);
        cbbTimKiem.setBounds(172, 22, 493, 20);
        panel_TKCTKM.add(cbbTimKiem);

        txtTimStartDate = new JDateChooser();
        txtTimStartDate.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txtTimStartDate.setLocale(new Locale("vi", "VN"));
        txtTimStartDate.setForeground(Color.BLACK);
        txtTimStartDate.setDateFormatString("dd/MM/yyyy");
        txtTimStartDate.setBounds(834, 22, 140, 18);
        panel_TKCTKM.add(txtTimStartDate);

        txtTimEndDate = new JDateChooser();
        txtTimEndDate.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txtTimEndDate.setLocale(new Locale("vi", "VN"));
        txtTimEndDate.setForeground(Color.BLACK);
        txtTimEndDate.setDateFormatString("dd/MM/yyyy");
        txtTimEndDate.setBounds(834, 51, 140, 18);
        panel_TKCTKM.add(txtTimEndDate);

        rdMa = new JRadioButton("Mã khuyến mãi");
        rdMa.setBackground(Color.WHITE);
        rdMa.setBounds(172, 51, 116, 21);
        panel_TKCTKM.add(rdMa);

        rdTen = new JRadioButton("Tên khuyến mãi");
        rdTen.setBackground(Color.WHITE);
        rdTen.setBounds(290, 52, 134, 21);
        panel_TKCTKM.add(rdTen);

        rdPhanTram = new JRadioButton("Phần trăm khuyến mãi");
        rdPhanTram.setBackground(Color.WHITE);
        rdPhanTram.setBounds(426, 51, 164, 21);
        panel_TKCTKM.add(rdPhanTram);

        rdTrangThai = new JRadioButton("Trạng thái");
        rdTrangThai.setBackground(Color.WHITE);
        rdTrangThai.setBounds(592, 52, 91, 21);
        panel_TKCTKM.add(rdTrangThai);

        gr1 = new ButtonGroup();
        gr1.add(rdApDung);
        gr1.add(rdHetHan);

        gr2 = new ButtonGroup();
        gr2.add(rdMa);
        gr2.add(rdTen);
        gr2.add(rdPhanTram);
        gr2.add(rdTrangThai);

        txtTenKM.setEditable(false);
        txtMaKM.setEditable(false);
        cboPhanTram.setEnabled(false);
        cbbTimKiemSP.setEnabled(false);
        txtChonNgayKT.getCalendarButton().setEnabled(false);
        txtChonNgayBD.getCalendarButton().setEnabled(false);
        rdApDung.setEnabled(false);
        rdHetHan.setEnabled(false);

        khoaText(lock);
        updateCombobox();
        docDuLieuSP();

        btnThem.addActionListener(this);
        btnLamMoiCT.addActionListener(this);
        btnSua.addActionListener(this);
        btnTimKiem.addActionListener(this);
        btnLamMoiTimKiem.addActionListener(this);
        btnLuu.addActionListener(this);
        rdMa.addActionListener(this);
        rdTen.addActionListener(this);
        rdPhanTram.addActionListener(this);
        rdTrangThai.addActionListener(this);
        cbbTimKiemSP.addActionListener(this);

        chkApAll.addItemListener(this);

        tableThongTinKM.addMouseListener(this);

        flag = 1;
        table_SP.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return; // Bỏ qua các sự kiện không phải từ người dùng
                }
                checkedBox();
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem)) {
            if (chkThem == false)
//                themKM();
                try {
                    themKM();
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            else if (chkThem == true) {
                chkThem = false;
                lock = false;
                khoaText(lock);
                btnThem.setText("Thêm");
                btnThem.setIcon(new ImageIcon("Anh\\them.png"));
                btnSua.setEnabled(true);
//                docDuLieuSP();
                try {
                    docDuLieuSP();
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            }
        } else if (o.equals(btnLamMoiCT)) {
            try {
                xoaTrang();
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
        } else if (o.equals(btnTimKiem)) {
            if (gr2.isSelected(null)) {
                timTheoNgay();
            } else
                try {
                    TimKiem();
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
        } else if (o.equals(btnLamMoiTimKiem)) {
//            xoaTrangThanhTK();
//            xoaTrang();
            try {
                xoaTrangThanhTK();
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
            try {
                xoaTrang();
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
        } else if (o.equals(btnSua)) {
            if (chkSua == false)
//                suaKM();
                try {
                    suaKM();
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            else if (chkSua == true) {
                chkSua = false;
                lock = false;
                khoaText(lock);
                btnSua.setText("Sửa");
                btnSua.setIcon(new ImageIcon("Anh\\sua.png"));
                btnThem.setEnabled(true);
            }
        } else if (o.equals(btnLuu)) {
            try {
                LuuThongTin();

            } catch (ClassNotFoundException | ParseException | SQLException | RemoteException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (o.equals(rdMa) || o.equals(rdPhanTram) || o.equals(rdTen) || o.equals(rdTrangThai)) {
//            updateCombobox();
            try {
                updateCombobox();
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
        } else if (o.equals(cbbTimKiemSP)) {
//            timSanPham();
            try {
                timSanPham();
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
        }

    }

    private int flag = 0;

    // Sửa chương trình khuyến mãi
    private void suaKM() throws RemoteException {
        lock = true;
        chkSua = true;
        khoaText(lock);
        btnSua.setIcon(new ImageIcon("Anh\\huy.png"));
        btnSua.setText("Hủy");
        btnThem.setEnabled(false);
        docDuLieuSP();
        locDanhSachSanPhamApDung(txtMaKM.getText());
    }

    //Cho phép thêm khuyến mãi
    private void themKM() throws RemoteException {
        lock = true;
        chkThem = true;
        khoaText(lock);
        btnThem.setText("Hủy");
        btnThem.setIcon(new ImageIcon("Anh\\huy.png"));
        btnSua.setEnabled(false);
        selectedRowsValues.clear();
        locDanhSachSanPhamApDung(null);

    }

    // Xóa trắng thông tin
    private void xoaTrang() throws RemoteException {

        txtMaKM.setText(deFaultID());
        txtTenKM.setText("");
        txtChonNgayBD.setDate(null);
        txtChonNgayKT.setDate(null);
        rdApDung.setSelected(false);
        rdHetHan.setSelected(false);
        gr1.clearSelection();
        cboPhanTram.setSelectedItem("");
        cboModePhanTram.setSelectedItem("");
        chkApAll.setSelected(false);
        cbbTimKiemSP.setSelectedItem("");
        txtTenKM.requestFocus();
        deleteAllDataTable();
        docDuLieu();
        docDuLieuSP();

        chkSua = false;
        chkThem = false;
        lock = false;
        khoaText(lock);
        btnSua.setText("Sửa");
        btnSua.setIcon(new ImageIcon("Anh\\sua.png"));
        btnThem.setEnabled(true);
        btnThem.setText("Thêm");
        btnThem.setIcon(new ImageIcon("Anh\\them.png"));
        btnSua.setEnabled(true);

    }

    // Làm mới thanh tìm kiếm
    private void xoaTrangThanhTK() throws RemoteException {
        gr2.clearSelection();
        cbbTimKiem.setSelectedItem("");
        deleteAllDataTable();
        docDuLieu();
    }

    // Xóa thông tin bảng
    public void deleteAllDataTable() {
        tablemodel1 = (DefaultTableModel) tableThongTinKM.getModel();
        tablemodel1.getDataVector().removeAllElements();
    }

    // Thêm khuyến mãi mới và sửa khuyến mãi
    public void LuuThongTin() throws ParseException, ClassNotFoundException, SQLException, RemoteException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String maKM = txtMaKM.getText();
        String tenkm = txtTenKM.getText();
        String ngaybd = dateFormat.format(txtChonNgayBD.getDate());
        String ngaykt = dateFormat.format(txtChonNgayKT.getDate());
        java.util.Date ngaybd1 = dateFormat.parse(ngaybd);
        java.util.Date ngaykt1 = dateFormat.parse(ngaykt);
        Date ngaybdsql = new Date(ngaybd1.getTime());
        Date ngayktsql = new Date(ngaykt1.getTime());
        boolean trangThai = true;
        int stat = 1;// Mặc định là đang áp dụng
        if (rdHetHan.isSelected()) {
            stat = 0;
            trangThai = false;
        }
        int phanTramText = (int) cboPhanTram.getSelectedItem();
        int soluong = selectedRowsValues.size();
        KhuyenMai km = valiData();
        if (km == null)
            return;
        else {
            if (chkThem == true && chkSua == false) {
                try {
                    boolean kmmoi = kmDao.themKhuyenMai(km);
                    if (kmmoi == true) {
                        xoaTrang();
                        deleteAllDataTable();
//			                    add sản phẩm khuyến mãi
                        for (String maSPAP : selectedRowsValues) {
                            kmDao.adDSPKM(maSPAP, maKM);
                        }

                        docDuLieu();
                        txtMaKM.setText(deFaultID());

                        JOptionPane.showMessageDialog(this, "Thêm chương trình khuyến mãi thành công!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm chương trình khuyến mãi không thành công!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (chkSua == true && chkThem == false) {
                if (km != null) {
                    KhuyenMaiDao kmDao = new KhuyenMaiImpl();
                    kmDao.capNhatNull(maKM);
                    for (String maSPAP : selectedRowsValues) {
                        kmDao.adDSPKM(maSPAP, maKM);
                        System.out.println(maSPAP);
                    }
                    boolean kmmoi = kmDao.updateKhuyenMai(km);

                    if (kmmoi) {
                        xoaTrang();
                        deleteAllDataTable();
                        docDuLieu();
                        docDuLieuSP();
                        JOptionPane.showMessageDialog(this, "Cập nhật chương trình khuyến mãi thành công!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Cập nhật chương trình khuyến mãi không thành công!");
                    }
                }
            }
            chkSua = false;
            chkThem = false;
            lock = false;
            khoaText(lock);
            btnSua.setText("Sửa");
            btnSua.setIcon(new ImageIcon("Anh\\sua.png"));
            btnThem.setEnabled(true);
            btnThem.setText("Thêm");
            btnThem.setIcon(new ImageIcon("Anh\\them.png"));
            btnSua.setEnabled(true);
        }
    }


    // Kiểm tra dữ liệu
    private KhuyenMai valiData() {
        String tenkm = txtTenKM.getText().trim();
        if (tenkm.isEmpty()) {
            ShowErrorField("Tên chương trình khuyến mãi không được rỗng!", txtTenKM);
            return null;
        }

        java.util.Date ngaybd = txtChonNgayBD.getDate();
        java.util.Date ngaykt = txtChonNgayKT.getDate();
        if (ngaybd == null || ngaykt == null || ngaykt.before(ngaybd)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu và ngày kết thúc phải hợp lệ và ngày kết thúc phải sau ngày bắt đầu.");
            return null;
        }

        int phanTram = (int) cboPhanTram.getSelectedItem();
        if (phanTram < 0 || phanTram > 100) { // check if the discount is between 0 and 100
            JOptionPane.showMessageDialog(this, "Phần trăm khuyến mãi phải từ 0 đến 100.");
            return null;
        }

        // convert java.util.Date to java.time.LocalDate
        LocalDate ngaybdLocalDate = ngaybd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ngayktLocalDate = ngaykt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return new KhuyenMai(txtMaKM.getText().trim(), tenkm, phanTram, ngaybdLocalDate, ngayktLocalDate, 0, 0);
    }


    private void ShowErrorField(String message, JTextField txtField) {
        JOptionPane.showMessageDialog(null, message);
        txtField.requestFocus();
    }

    // Đọc dữ liệu lên
    public void docDuLieu() throws RemoteException {
        List<KhuyenMai> list = kmDao.getAllKhuyenMai();
        tablemodel1 = (DefaultTableModel) tableThongTinKM.getModel();
        DefaultComboBoxModel<Integer> cboModelPhanTram = new DefaultComboBoxModel<>();
        cboPhanTram.setModel(cboModelPhanTram);

        java.util.Date ngayht = new java.util.Date(); // Lấy ngày hiện tại
        rdApDung.setSelected(true);

        for (KhuyenMai x : list) {
            String trangThaiText;
            kmDao.hetHan();
            if (x.getNgayKetThuc().isBefore(LocalDate.now())) {
                trangThaiText = "Hết hạn";
                x.setTrangThai(0); // Cập nhật trạng thái là "Hết hạn"
                x.setSoLuongSanPhamKM(0);
            } else {
                trangThaiText = "Đang áp dụng";
                x.setTrangThai(1);
            }

            tablemodel1.addRow(new Object[]{x.getMaKhuyenMai(), x.getTenKhuyenMai(), x.getPhanTramKhuyenMai(),
                    x.getNgayBatDau(), x.getNgayKetThuc(), trangThaiText, x.getSoLuongSanPhamKM()});
        }
        for (int i = 0; i <= 100; i += 5) {
            cboModelPhanTram.addElement(i);
        }
    }

    // Đọc dữ liệu sản phẩm
    public void docDuLieuSP() throws RemoteException {
        List<SanPham> list = daoSP.getAllSP();
        JCheckBox chkADD = new JCheckBox();
        tablemodel.setRowCount(0);
        for (SanPham x : list) {
            cbbTimKiemSP.addItem(x.getMaSp());
            table_SP.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JCheckBox()));
            table_SP.getColumnModel().getColumn(2).setCellRenderer(table_SP.getDefaultRenderer(Boolean.class));

//            boolean isPromoted = x.getKhuyenMai() != null && x.getKhuyenMai().getTrangThai() == 1;

            tablemodel.addRow(new Object[]{x.getMaSp(), x.getTensp(), null});
        }
    }

    // Đọc dữ liệu sản phẩm
    public void docDuLieuSPByMaKM(String makm) throws RemoteException {
        flag = 0;
        List<SanPham> list = daoSP.getAllSP();

        tablemodel.setRowCount(0);
        for (SanPham x : list) {
            KhuyenMai khuyenMai = x.getKhuyenMai();
            boolean isKhuyenMaiApplied = khuyenMai != null && khuyenMai.getMaKhuyenMai().equals(makm);
            tablemodel.addRow(new Object[]{x.getMaSp(), x.getTensp(), isKhuyenMaiApplied});
        }
    }

    // Mã khuyến mãi tự động
    public String deFaultID() throws RemoteException {
        Long n = kmDao.soLuongCTKM() + 1;
        String soLuongKM = String.format("%03d", n);
        String deFault = "KM" + soLuongKM;
        return deFault;
    }

    //	Lưu dữ liệu vào comboBOx
    public void updateCombobox() throws RemoteException {

        List<KhuyenMai> listKM = kmDao.getAllKhuyenMai();
        List<String> trangThaiList = new ArrayList<>();
        cbbTimKiem.removeAllItems();

        for (KhuyenMai n : listKM) {
            if (rdMa.isSelected()) {
                cbbTimKiem.addItem(n.getMaKhuyenMai());
            } else if (rdTen.isSelected()) {
                cbbTimKiem.addItem(n.getTenKhuyenMai());
            } else if (rdPhanTram.isSelected()) {
                cbbTimKiem.removeAllItems();
                for (int i = 0; i <= 100; i += 5) {
                    cbbTimKiem.addItem(String.valueOf(i));
                }
            } else if (rdTrangThai.isSelected()) {

                String trangThaiText = n.getTrangThai() == 0 ? "Đang áp dụng" : "Hết hạn";

                // Kiểm tra xem giá trị trạng thái đã tồn tại trong danh sách chưa
                if (!trangThaiList.contains(trangThaiText)) {
                    trangThaiList.add(trangThaiText);
                    cbbTimKiem.addItem(trangThaiText);

                }
            }
        }

    }

    // Tìm chương trình khuyến mãi
    public void TimKiem() throws RemoteException {
        deleteAllDataTable();
        String tim = "";
        java.util.Date timngaybd = txtTimStartDate.getDate();
        java.util.Date timngaykt = txtTimEndDate.getDate();

        try {
            tim = cbbTimKiem.getSelectedItem().toString();

        } catch (Exception e) {
            // TODO: handle exception
        }
        if (tim.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm!");
            return;
        }

        if (rdTen.isSelected()) {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            KhuyenMai x = kmDao.getKMTheoTen(tim);
            String trangThaiText = x.getTrangThai() == 0 ? "Đang áp dụng" : "Hết hạn";
            tablemodel1.addRow(new Object[]{x.getMaKhuyenMai(), x.getTenKhuyenMai(), x.getPhanTramKhuyenMai(),
                    x.getNgayBatDau(), x.getNgayKetThuc(), trangThaiText, x.getSoLuongSanPhamKM()});
        }

        if (rdMa.isSelected()) {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            KhuyenMai x = kmDao.getKMTHeoMa(tim);
            String trangThaiText = x.getTrangThai() == 0 ? "Đang áp dụng" : "Hết hạn";
            tablemodel1.addRow(new Object[]{x.getMaKhuyenMai(), x.getTenKhuyenMai(), x.getPhanTramKhuyenMai(),
                    x.getNgayBatDau(), x.getNgayKetThuc(), trangThaiText, x.getSoLuongSanPhamKM()});
        }

        if (rdPhanTram.isSelected()) {
            List<KhuyenMai> list = kmDao.getAllKhuyenMai();
            boolean timThay = false;
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

            for (KhuyenMai x : list) {
                if (x.getPhanTramKhuyenMai() == Integer.parseInt(tim)) {
                    String trangThaiText = x.getTrangThai() == 0 ? "Đang áp dụng" : "Hết hạn";
                    tablemodel1.addRow(new Object[]{x.getMaKhuyenMai(), x.getTenKhuyenMai(), x.getPhanTramKhuyenMai(),
                            x.getNgayBatDau(), x.getNgayKetThuc(), trangThaiText, x.getSoLuongSanPhamKM()});

                    timThay = true; // Đánh dấu là đã tìm thấy ít nhất một khuyến mãi
                }
            }

            if (!timThay) {

                JOptionPane.showMessageDialog(this, "Không tìm thấy giá trị phần trăm nào!");

            }
        }

        if (rdTrangThai.isSelected()) {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//            List<KhuyenMai> list = kmDao.getAllKhuyenMai();
            List<KhuyenMai> list = null;
            try {
                list = kmDao.getAllKhuyenMai();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            for (KhuyenMai x : list) {
                String trangThaiText = x.getTrangThai() == 0 ? "Đang áp dụng" : "Hết hạn";

                if (("Đang áp dụng".equals(tim) && x.getTrangThai() == 0)
                        || ("Hết hạn".equals(tim) && x.getTrangThai() == 1)) {
                    // Thay đổi cách thức hiển thị thông tin khuyến mãi tại đây
                    tablemodel1.addRow(new Object[]{x.getMaKhuyenMai(), x.getTenKhuyenMai(), x.getPhanTramKhuyenMai(),
                            df.format(x.getNgayBatDau()), df.format(x.getNgayKetThuc()), trangThaiText, x.getSoLuongSanPhamKM()});
                }
            }
        }

    }

    public void timTheoNgay() {
        deleteAllDataTable();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            String ngaybd = dateFormat.format(txtTimStartDate.getDate());
            String ngaykt = dateFormat.format(txtTimEndDate.getDate());
            java.util.Date ngaybd1 = dateFormat.parse(ngaybd);
            java.util.Date ngaykt1 = dateFormat.parse(ngaykt);

            // Convert java.util.Date to java.time.LocalDate
            LocalDate ngaybdsql = ngaybd1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate ngayktsql = ngaykt1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // change the way the KhuyenMai objects are fetched
            List<KhuyenMai> list = kmDao.getTheoThoiGian(ngaybdsql, ngayktsql);

            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy khuyến mãi trong khoảng thời gian này.");
            } else {
                for (KhuyenMai x : list) {
                    String trangThaiText = x.getTrangThai() == 1 ? "Đang áp dụng" : "Hết hạn";

                    // change the parameters passed to the tablemodel1.addRow method
                    tablemodel1.addRow(new Object[]{x.getMaKhuyenMai(), x.getTenKhuyenMai(), x.getPhanTramKhuyenMai(),
                            x.getNgayBatDau(), x.getNgayKetThuc(), trangThaiText});
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngày không hợp lệ. Hãy nhập ngày theo định dạng dd/MM/yyyy.");
        }
    }

    //Khóa cái textField
    public void khoaText(boolean lock) {
        txtTenKM.setEditable(lock);
        cboPhanTram.setEnabled(lock);
        cbbTimKiemSP.setEnabled(lock);
        txtChonNgayKT.getCalendarButton().setEnabled(lock);
        txtChonNgayBD.getCalendarButton().setEnabled(lock);
        chkApAll.setEnabled(lock);
        btnLuu.setEnabled(lock);
        table_SP.setEnabled(lock);
    }

    // Sự kiện click
    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(tableThongTinKM)) {
            try {
                clickTrongKhuyenMai();
            } catch (RemoteException ex) {
                ex.printStackTrace();
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

    public void clickTrongKhuyenMai() throws RemoteException {
        docDuLieuSP();
        btnThem.setEnabled(false);
        int row = tableThongTinKM.getSelectedRow();

        txtMaKM.setText(tablemodel1.getValueAt(row, 0).toString());

        txtTenKM.setText(tablemodel1.getValueAt(row, 1).toString());
        int phanTram = (int) tablemodel1.getValueAt(row, 2);
        cboPhanTram.setSelectedItem(phanTram);

        LocalDate ngayBatDau = (LocalDate) tablemodel1.getValueAt(row, 3);
        LocalDate ngayKetThuc = (LocalDate) tablemodel1.getValueAt(row, 4);

        txtChonNgayBD.setDate(Date.valueOf(ngayBatDau));
        txtChonNgayKT.setDate(Date.valueOf(ngayKetThuc));

        table_SP.setEnabled(false);
        docDuLieuSPByMaKM(txtMaKM.getText());
        selectedRowsValues = kmDao.dsMaSPKM(txtMaKM.getText());
        // Lấy trạng thái từ dòng đã chọn trong bảng
        String trangThaiText = tablemodel1.getValueAt(row, 5).toString();

        // Kiểm tra và cập nhật trạng thái theo giá trị
        if (trangThaiText.equals("Đang áp dụng")) {
            rdApDung.setSelected(true);
            rdHetHan.setSelected(false);
        } else if (trangThaiText.equals("Hết hạn")) {
            rdApDung.setSelected(false);
            rdHetHan.setSelected(true);
        }
    }

    //	Tự động check các sản phẩm đã có khuyến mãi
    public void locDanhSachSanPhamApDung(String maKM) throws RemoteException {
        List<SanPham> list = daoSP.getAllSP();
        // Kiểm tra giá trị cột 2 của mỗi dòng
        for (SanPham x : list) {
            String km = kmDao.layKhuyenMaiTuSanPham(x.getMaSp());
            if (km != null && !km.equalsIgnoreCase(maKM)) {
                for (int row = 0; row < tablemodel.getRowCount(); row++) {
                    String value = (String) tablemodel.getValueAt(row, 0);
                    if (x.getMaSp().equalsIgnoreCase(value)) {
                        tablemodel.removeRow(row);
                    }
                }
            }
        }
    }

    //Tìm kiếm sản phẩm để áp dụng khuyến mãi
    public void timSanPham() throws RemoteException {
        if (flag == 1) {
            String selectedMaSP = cbbTimKiemSP.getSelectedItem().toString();

            DefaultTableModel tablemodel = (DefaultTableModel) table_SP.getModel();
            tablemodel.setRowCount(0); // Xóa dữ liệu hiện tại trong bảng

            List<SanPham> list = daoSP.getAllSP();
            for (SanPham x : list) {
                if (x.getMaSp().equals(selectedMaSP)) {
                    tablemodel.addRow(new Object[]{x.getMaSp(), x.getTensp(), false});
                }
            }
        }
    }

    //	Bắt sự kiện click ô checkBox
    public void checkedBox() {

        int selectedRow = table_SP.getSelectedRow();
        int columnIndex = 2; // Chỉ mục cột 2, có thể thay đổi theo cột của bạn

        if (selectedRow != -1 && table_SP.getSelectedColumn() == columnIndex) {
            // Lấy giá trị của ô checkbox
            String selectedValue = tablemodel.getValueAt(selectedRow, 0).toString();

            boolean isChecked = (boolean) tablemodel.getValueAt(selectedRow, columnIndex);

            if (isChecked) {
                selectedRowsValues.add(selectedValue);

            } else {
                selectedRowsValues.remove(selectedValue);
            }

        }
    }

    //Bắt sự kiện click chọn tắt cả sản phẩm (CheckBox)
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == chkApAll) {
            if (chkApAll.isSelected()) {
                for (int i = 0; i < table_SP.getRowCount(); i++) {
                    selectedRowsValues.add(table_SP.getValueAt(i, 0).toString());
                    String selectedValue = tablemodel.getValueAt(i, 0).toString();
                    tablemodel.setValueAt(true, i, 2);
                }
            } else {
//                List<SanPham> list = daoSP.getAllSP();
                List<SanPham> list = null;
                try {
                    list = daoSP.getAllSP();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                selectedRowsValues.clear();
                for (int i = 0; i < table_SP.getRowCount(); i++) {

                    for (SanPham s : list) {

                        if (s.getMaSp().equalsIgnoreCase(table_SP.getValueAt(i, 0).toString())) {
//                            String km = kmDao.layKhuyenMaiTuSanPham(s.getMaSp());
                            String km = null;
                            try {
                                km = kmDao.layKhuyenMaiTuSanPham(s.getMaSp());
                            } catch (RemoteException e1) {
                                e1.printStackTrace();
                            }
                            if (km == null)
                                tablemodel.setValueAt(false, i, 2);

                        }

                    }
                    if ((Boolean) table_SP.getValueAt(i, 2) == true) {
                        selectedRowsValues.add(table_SP.getValueAt(i, 0).toString());
                    }
                }
            }
        }
    }
}