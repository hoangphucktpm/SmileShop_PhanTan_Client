package GUI;

import DAOTest.KhachHangDao;
import DAOTest.impl.KhachHangImpl;
import Entities.KhachHang;
import Entities.LoaiKhachHang;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class FrmKhachHang extends JFrame implements ActionListener, MouseListener, DocumentListener, KeyListener {

    private static final long serialVersionUID = 1L;
    static JPanel contentPane;
    private JTextField txtMaKH;
    private JTextField txtTenKH;
    private JTextField txtDiaChi;
    private JTextField txtSDT;
    private JTextField txtCCCD;
    private JTextField txtEmail;
    private JTextField txtDTL;
    private DefaultTableModel dataModel;
    private JTable table_1;
    private JPanel pnTable;
    private DefaultTableModel tablemodel;
    private JLabel lblTieuDeTrang;
    private JDateChooser txtChonNgay;

    private JRadioButton rdNam;
    private JRadioButton rdNu;
    private JComboBox cboLoaiKhachHang;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnReset;
    private JButton btnTim;
    private JButton btnReset2;
    private JComboBox cboTim;
    private JRadioButton rdMaKH;
    private JRadioButton rdTenKH;
    private JRadioButton rdGend;
    private JRadioButton rdLoaiKH;
    private ButtonGroup buttonGroupTK;
    private ButtonGroup buttonGroupGT;
    private JLabel lblMaKH;
    private JLabel lblGend;
    private JLabel lblTenKH;
    private JLabel lblDiaChi;
    private JLabel lblSDT;
    private JLabel lblCCCD;
    private Container lblEmail;
    private JLabel lblBirth;
    private JLabel lblDTL;
    private JLabel lblLKH;
    private JPanel panel_2;
    private JButton btnLuu;
    private JLabel lblThongTinTim;
    private JLabel lblTim;

    private boolean chkThem = false;
    private boolean chkSua = false;
    private boolean lock = false;
    private JLabel lblLoi;
    private JLabel lblLoi_Ten;
    private JLabel lblLoi_SDT;
    private JLabel lblLoi_Email;
    private JLabel lblLoi_GioiTinh;
    private JLabel lblLoi_DiaChi;
    private JLabel lblLoi_CCCD;
    private JLabel lblLoi_NgaySinh;

    private KhachHangDao khachHangImpl = (KhachHangDao) Naming.lookup(URL + "KhachHangDao");;
    List<LoaiKhachHang> listLKH = khachHangImpl.getAllLoaiKH();
    private static final String URL = "rmi://HOANGPHUC:6541/";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmKhachHang frame = new FrmKhachHang();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FrmKhachHang() throws RemoteException, MalformedURLException, NotBoundException {

        setTitle("Quản lí cửa hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setSize(1347, 843);
        setResizable(false);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel pnlTieuDe = new JPanel();
        pnlTieuDe.setBackground(new Color(0, 255, 255));
        pnlTieuDe.setBounds(0, 0, 1343, 41);
        contentPane.add(pnlTieuDe);
        pnlTieuDe.setLayout(null);
        lblTieuDeTrang = new JLabel("QUẢN LÝ KHÁCH HÀNG");
        lblTieuDeTrang.setBounds(512, 12, 305, 25);
        pnlTieuDe.add(lblTieuDeTrang);
        lblTieuDeTrang.setBackground(new Color(0, 255, 255));
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));

        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setForeground(new Color(0, 0, 0));
        pnlThongTin.setBackground(new Color(255, 255, 255));
        pnlThongTin.setBounds(20, 51, 1292, 280);
        javax.swing.border.Border southborder = BorderFactory.createLineBorder(Color.black);
        pnlThongTin.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Thi\u1EBFt l\u1EADp th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null,
                new Color(0, 0, 0)));
        contentPane.add(pnlThongTin);
        pnlThongTin.setLayout(null);

        lblMaKH = new JLabel("Mã khách hàng");
        lblMaKH.setBounds(10, 28, 114, 20);
        lblMaKH.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        pnlThongTin.add(lblMaKH);

        txtMaKH = new JTextField(deFaultID());
        txtMaKH.setBounds(128, 28, 350, 20);
        txtMaKH.setEditable(false);
        pnlThongTin.add(txtMaKH);
        txtMaKH.setColumns(10);

        lblGend = new JLabel("Giới tính");
        lblGend.setBounds(578, 28, 71, 12);
        lblGend.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        pnlThongTin.add(lblGend);

        lblTenKH = new JLabel("Tên khách hàng");
        lblTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblTenKH.setBounds(10, 74, 96, 15);
        pnlThongTin.add(lblTenKH);

        txtTenKH = new JTextField();
        txtTenKH.setBounds(128, 77, 350, 20);
        pnlThongTin.add(txtTenKH);
        txtTenKH.setColumns(10);

        lblDiaChi = new JLabel(" Địa chỉ");
        lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblDiaChi.setBounds(578, 75, 45, 13);
        pnlThongTin.add(lblDiaChi);

        txtDiaChi = new JTextField();
        txtDiaChi.setBounds(686, 75, 350, 20);
        pnlThongTin.add(txtDiaChi);
        txtDiaChi.setColumns(10);

        lblSDT = new JLabel("Số điện thoại");
        lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblSDT.setBounds(10, 124, 96, 13);
        pnlThongTin.add(lblSDT);

        txtSDT = new JTextField();
        txtSDT.setBounds(128, 124, 350, 20);
        pnlThongTin.add(txtSDT);
        txtSDT.setColumns(10);

        lblCCCD = new JLabel("CCCD");
        lblCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblCCCD.setBounds(578, 124, 45, 13);
        pnlThongTin.add(lblCCCD);

        txtCCCD = new JTextField();
        txtCCCD.setBounds(686, 124, 350, 20);
        pnlThongTin.add(txtCCCD);
        txtCCCD.setColumns(10);

        lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblEmail.setBounds(10, 165, 45, 13);
        pnlThongTin.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(128, 165, 350, 20);
        pnlThongTin.add(txtEmail);
        txtEmail.setColumns(10);

        lblBirth = new JLabel("Ngày sinh");
        lblBirth.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblBirth.setBounds(578, 184, 114, 13);
        pnlThongTin.add(lblBirth);

        txtChonNgay = new JDateChooser();
        txtChonNgay.setBounds(686, 177, 350, 20);
        txtChonNgay.setForeground(new Color(0, 0, 0));
        txtChonNgay.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txtChonNgay.setLocale(new Locale("vi", "VN"));
        txtChonNgay.setDateFormatString("dd/MM/yyyy");
        txtChonNgay.setDate(new Date(System.currentTimeMillis()));

        pnlThongTin.add(txtChonNgay);

        lblDTL = new JLabel("Điểm tích lũy");
        lblDTL.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblDTL.setBounds(10, 212, 108, 13);
        pnlThongTin.add(lblDTL);

        txtDTL = new JTextField();
        txtDTL.setBounds(128, 211, 350, 20);
        txtDTL.setEditable(false);
        pnlThongTin.add(txtDTL);
        txtDTL.setColumns(10);

        lblLKH = new JLabel("Loại khách hàng");
        lblLKH.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblLKH.setBounds(578, 229, 108, 13);
        pnlThongTin.add(lblLKH);

        cboLoaiKhachHang = new JComboBox();
        cboLoaiKhachHang.setBounds(686, 228, 350, 20);
        pnlThongTin.add(cboLoaiKhachHang);
        cboLoaiKhachHang.setEnabled(false);

        rdNam = new JRadioButton("Nam");
        rdNam.setBackground(new Color(255, 255, 255));
        rdNam.setBounds(686, 24, 132, 20);
        pnlThongTin.add(rdNam);

        rdNu = new JRadioButton("Nữ");
        rdNu.setBackground(new Color(255, 255, 255));
        rdNu.setBounds(825, 24, 103, 20);
        pnlThongTin.add(rdNu);

        buttonGroupGT = new ButtonGroup();

        // Thêm các nút radio vào ButtonGroup
        buttonGroupGT.add(rdNam);
        buttonGroupGT.add(rdNu);

        panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setBorder(
                new TitledBorder(null, "Ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.setBounds(1051, 32, 241, 189);
        pnlThongTin.add(panel_2);
        panel_2.setLayout(null);

        btnThem = new JButton("Thêm");
        btnThem.setBackground(new Color(0, 255, 255));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnThem.setIcon(new ImageIcon("Anh\\them.png"));
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnThem.setBounds(39, 21, 160, 30);
        panel_2.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setBackground(new Color(0, 255, 255));
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSua.setIcon(new ImageIcon("Anh\\sua.png"));
        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnSua.setBounds(39, 61, 160, 30);
        panel_2.add(btnSua);

        btnReset = new JButton("Làm mới");
        btnReset.setBackground(new Color(0, 255, 255));
        btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnReset.setIcon(new ImageIcon("Anh\\lammoi.png"));
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnReset.setBounds(39, 101, 160, 30);
        panel_2.add(btnReset);

        btnLuu = new JButton("Lưu");
        btnLuu.setIcon(new ImageIcon("Anh\\luu.png"));
        btnLuu.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLuu.setBackground(Color.CYAN);
        btnLuu.setBounds(39, 141, 160, 30);
        panel_2.add(btnLuu);

        lblLoi = new JLabel("");
        lblLoi.setForeground(Color.RED);
        lblLoi.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblLoi.setBounds(577, 252, 705, 20);
        pnlThongTin.add(lblLoi);

        lblLoi_Ten = new JLabel("");
        lblLoi_Ten.setForeground(Color.RED);
        lblLoi_Ten.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblLoi_Ten.setBounds(130, 100, 350, 20);
        pnlThongTin.add(lblLoi_Ten);

        lblLoi_SDT = new JLabel("");
        lblLoi_SDT.setForeground(Color.RED);
        lblLoi_SDT.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblLoi_SDT.setBounds(128, 145, 350, 20);
        pnlThongTin.add(lblLoi_SDT);

        lblLoi_Email = new JLabel("");
        lblLoi_Email.setForeground(Color.RED);
        lblLoi_Email.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblLoi_Email.setBounds(128, 188, 350, 20);
        pnlThongTin.add(lblLoi_Email);

        lblLoi_GioiTinh = new JLabel("");
        lblLoi_GioiTinh.setForeground(Color.RED);
        lblLoi_GioiTinh.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblLoi_GioiTinh.setBounds(686, 47, 350, 20);
        pnlThongTin.add(lblLoi_GioiTinh);

        lblLoi_DiaChi = new JLabel("");
        lblLoi_DiaChi.setForeground(Color.RED);
        lblLoi_DiaChi.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblLoi_DiaChi.setBounds(686, 100, 350, 20);
        pnlThongTin.add(lblLoi_DiaChi);

        lblLoi_CCCD = new JLabel("");
        lblLoi_CCCD.setForeground(Color.RED);
        lblLoi_CCCD.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblLoi_CCCD.setBounds(686, 150, 350, 20);
        pnlThongTin.add(lblLoi_CCCD);

        lblLoi_NgaySinh = new JLabel("");
        lblLoi_NgaySinh.setForeground(Color.RED);
        lblLoi_NgaySinh.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblLoi_NgaySinh.setBounds(686, 200, 350, 20);
        pnlThongTin.add(lblLoi_NgaySinh);

        JPanel lblTable = new JPanel();
        lblTable.setBackground(new Color(255, 255, 255));
        lblTable.setBorder(new TitledBorder(null, "Danh s\u00E1ch kh\u00E1ch h\u00E0ng", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        lblTable.setBounds(20, 338, 1292, 458);
        contentPane.add(lblTable);
        lblTable.setLayout(null);

        lblThongTinTim = new JLabel("Nhập thông tin tìm kiếm");
        lblThongTinTim.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblThongTinTim.setBounds(10, 24, 134, 13);
        lblTable.add(lblThongTinTim);

        cboTim = new JComboBox();
        cboTim.setBounds(154, 20, 851, 20);
        lblTable.add(cboTim);


        lblTim = new JLabel("Tìm theo");
        lblTim.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblTim.setBounds(10, 61, 81, 13);
        lblTable.add(lblTim);

        rdMaKH = new JRadioButton("Mã khách hàng");
        rdMaKH.setBackground(new Color(255, 255, 255));
        rdMaKH.setBounds(154, 57, 232, 20);
        lblTable.add(rdMaKH);

        rdTenKH = new JRadioButton("Tên khách hàng");
        rdTenKH.setBackground(new Color(255, 255, 255));
        rdTenKH.setBounds(388, 57, 219, 20);
        lblTable.add(rdTenKH);

        rdGend = new JRadioButton("Giới tính");
        rdGend.setBackground(new Color(255, 255, 255));
        rdGend.setBounds(623, 57, 172, 20);
        lblTable.add(rdGend);

        rdLoaiKH = new JRadioButton("Loại khách hàng");
        rdLoaiKH.setBackground(new Color(255, 255, 255));
        rdLoaiKH.setBounds(797, 57, 208, 20);
        lblTable.add(rdLoaiKH);

        buttonGroupTK = new ButtonGroup();

        // Thêm các nút radio vào ButtonGroup
        buttonGroupTK.add(rdMaKH);
        buttonGroupTK.add(rdTenKH);
        buttonGroupTK.add(rdGend);
        buttonGroupTK.add(rdLoaiKH);

        btnTim = new JButton("Tìm kiếm");
        btnTim.setBackground(new Color(0, 255, 255));
        btnTim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnTim.setIcon(new ImageIcon("Anh\\timkiem.png"));
        btnTim.setBounds(1027, 44, 125, 30);
        lblTable.add(btnTim);

        btnReset2 = new JButton("Làm mới");
        btnReset2.setBackground(new Color(0, 255, 255));
        btnReset2.setIcon(new ImageIcon("Anh\\lammoi.png"));
        btnReset2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnReset2.setBounds(1162, 44, 120, 30);
        lblTable.add(btnReset2);

        JScrollPane scrDSKH;
        String[] tb1 = new String[]{"Mã KH", "Tên KH", "Giới tính", "SĐT", "CCCD", "Ngày sinh", "Email", " Địa chỉ",
                " Điểm tích lũy", "Loại KH"};
        tablemodel = new DefaultTableModel(tb1, 0);
        table_1 = new JTable(tablemodel);

        table_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        table_1.setBackground(new Color(224, 255, 255));
        table_1.setForeground(new Color(0, 0, 0));
        getContentPane().add(scrDSKH = new JScrollPane(table_1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
        scrDSKH.setBounds(10, 104, 1272, 372);
        lblTable.add(scrDSKH);
        scrDSKH.setPreferredSize(new Dimension(0, 250));
        khoaTXT(lock);


        docDuLieu();
        updateCBBox();
        table_1.addMouseListener(this);
        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnReset.addActionListener(this);
        btnTim.addActionListener(this);
        btnReset2.addActionListener(this);
        btnLuu.addActionListener(this);
        txtSDT.addKeyListener(this);
        txtCCCD.addKeyListener(this);
        txtChonNgay.addKeyListener(this);


        rdMaKH.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    updateCBBoxTim();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });
        rdTenKH.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    updateCBBoxTim();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });
        rdGend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    updateCBBoxTim();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });
        rdLoaiKH.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    updateCBBoxTim();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void docDuLieu() {
        try {
            Format ngaySinh = new SimpleDateFormat("dd/MM/yyyy");
            List<KhachHang> list = khachHangImpl.getAllKH();
            LoaiKhachHang loaiKH = new LoaiKhachHang();
            for (KhachHang khachHang : list) {
                String gioiTinhText = dinhDangGTImpl(khachHang);
                float diemTichLuy = khachHang.getDiemTichLuy();
                List<KhachHang> listKH = khachHangImpl.getKH5KPoint();
                List<LoaiKhachHang> listLoaiKhachHang = khachHangImpl.getAllLoaiKH();
                LoaiKhachHang selectedLoaiKhachHang = listLoaiKhachHang.get(1);
                for (KhachHang k : listKH) {
                    if (k.getLoaiKH().getMaLoaiKH().equalsIgnoreCase("001")) {
                        k.setLoaiKH(selectedLoaiKhachHang);
                        khachHangImpl.updateKhachHang(k);
                    }
                }

                // Kiểm tra điều kiện và cập nhật loại khách hàng

                String loaiKhachHang = khachHangImpl.getLoaiKH(khachHang.getMaKH());

                tablemodel.addRow(new Object[]{khachHang.getMaKH(), khachHang.getTenKH(), gioiTinhText,
                        khachHang.getSdt(), khachHang.getCccd(), ngaySinh.format(khachHang.getNgaySinh()), khachHang.getEmail(),
                        khachHang.getDiaChi(), diemTichLuy, loaiKhachHang});
            }
            table_1.setModel(tablemodel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object o = e.getSource();
        if (o.equals(btnThem)) {
            if (chkThem == false) {
                themKH();
            } else if (chkThem == true) {
                lock = false;
                khoaTXT(lock);
                btnThem.setText("Thêm");
                chkThem = false;
                btnSua.setEnabled(true);
                btnThem.setIcon(new ImageIcon("Anh\\them.png"));
            }
        }
        if (o.equals(btnSua)) {
            if (chkSua == false) {
                sua();
            } else if (chkSua == true) {
                lock = false;
                khoaTXT(lock);
                btnSua.setText("Sửa");
                chkSua = false;
                btnThem.setEnabled(true);
                btnSua.setIcon(new ImageIcon("Anh\\sua.png"));

            }
        }
        if (o.equals(btnReset)) {
            xoaTrang();
        }
        if (o.equals(btnTim)) {
            try {
                tim();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }
        if (o.equals(btnReset2)) {
            xoaTrangTimKiem();
        } else if (o.equals(btnLuu)) {
            try {
                luuThayDoi();
            } catch (ParseException | RemoteException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void keyTyped(KeyEvent e) {
        Object o = e.getSource();
        if (o.equals(txtSDT) || o.equals(txtCCCD) || o.equals(txtChonNgay)) {
            if (!((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                e.consume();
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        Object o = e.getSource();
        if (o.equals(txtSDT) && e.getKeyCode() == KeyEvent.VK_ENTER) {
            // Xử lý khi nhấn Enter trong txtSDT
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        int d = 1;
        int row = table_1.getSelectedRow();
        txtMaKH.setText(tablemodel.getValueAt(row, 0).toString());
        txtTenKH.setText(tablemodel.getValueAt(row, 1).toString());

        txtSDT.setText(tablemodel.getValueAt(row, 3).toString());
        txtCCCD.setText(tablemodel.getValueAt(row, 4).toString());
        try {
            txtChonNgay.setDate(khachHangImpl.getAllKH().get(row).getNgaySinh());
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        txtEmail.setText(tablemodel.getValueAt(row, 6).toString());
        txtDiaChi.setText(tablemodel.getValueAt(row, 7).toString());
        txtDTL.setText(tablemodel.getValueAt(row, 8).toString());
        cboLoaiKhachHang.setSelectedItem(tablemodel.getValueAt(row, 9).toString());
        List<KhachHang> list = null;
        try {
            list = khachHangImpl.getAllKH();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        for (KhachHang x : list) {
            if (x.getMaKH().equals(tablemodel.getValueAt(row, 0).toString())) {
                if (x.getGioiTinh() == 1) {
                    rdNam.setSelected(true);
                    rdNu.setSelected(false);
                } else {
                    rdNam.setSelected(false);
                    rdNu.setSelected(true);
                }

            }
        }
        btnThem.setEnabled(false);
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

    public void updateCBBox() throws RemoteException {
        cboLoaiKhachHang.removeAllItems();

        List<LoaiKhachHang> kh = khachHangImpl.getAllLoaiKH();
        HashSet<String> lkh = new HashSet<>();

        for (LoaiKhachHang x : kh) {
            String d = x.getTenLoaiKH();
            lkh.add(d);
        }
        for (String loaiKH : lkh) {
            cboLoaiKhachHang.addItem(loaiKH);
        }
    }

    public void updateCBBoxTim() throws RemoteException {
        cboTim.removeAllItems();
        List<KhachHang> listN = khachHangImpl.getAllKH();
        for (KhachHang n : listN) {
            if (rdMaKH.isSelected()) {
                cboTim.addItem(n.getMaKH());
            } else if (rdTenKH.isSelected()) {
                cboTim.addItem(n.getTenKH());
            } else if (rdGend.isSelected()) {
                List<KhachHang> kh = khachHangImpl.getAllKH();
                HashSet<String> lkh = new HashSet<>();
                for (KhachHang x : kh) {
                    String gend = dinhDangGTImpl(x);
                    lkh.add(gend);
                }
                cboTim.removeAllItems();
                for (String GT : lkh) {
                    cboTim.addItem(GT);
                }

            } else if (rdLoaiKH.isSelected()) {
                List<KhachHang> kh = khachHangImpl.getAllKH();
                HashSet<String> lkh = new HashSet<>();

                for (KhachHang x : kh) {
                    String o = x.getLoaiKH().getTenLoaiKH();

                    lkh.add(o);
                }
                cboTim.removeAllItems();
                for (String loaiKH : lkh) {
                    cboTim.addItem(loaiKH);
                }
            }
        }

    }

    public void khoaTXT(boolean x) {
        txtEmail.setEditable(x);
        txtSDT.setEditable(x);
        txtTenKH.setEditable(x);
        txtCCCD.setEditable(x);
        txtDiaChi.setEditable(x);
        btnLuu.setEnabled(x);
    }

    public void themKH() {
        lock = true;
        khoaTXT(lock);
        chkThem = true;
        btnThem.setText("Hủy");
        btnSua.setEnabled(false);
        txtTenKH.requestFocus();
        btnThem.setIcon(new ImageIcon("Anh\\huy.png"));
    }

    public void luuThayDoi() throws RemoteException, ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String MaKH = txtMaKH.getText();
        String tenKH = txtTenKH.getText();
        String Sdt = txtSDT.getText();
        String CCCD = txtCCCD.getText();
        String ngaySinh1 = dateFormat.format(txtChonNgay.getDate());
        java.util.Date ngaySinh2 = dateFormat.parse(ngaySinh1);
        Date ngaySinh = new Date(ngaySinh2.getTime());
        String email = txtEmail.getText();
        String diaChi = txtDiaChi.getText();
        String maLoai = (String) cboLoaiKhachHang.getSelectedItem();

        List<LoaiKhachHang> listLoaiKhachHang = khachHangImpl.getAllLoaiKH();
        int selectedIndex = cboLoaiKhachHang.getSelectedIndex();
        LoaiKhachHang selectedLoaiKhachHang = listLoaiKhachHang.get(selectedIndex);

        int gioiTinh = 2;
        float dtluy = 0;


        if (rdNam.isSelected()) {
            gioiTinh = 1;
        } else if (rdNu.isSelected()) {
            gioiTinh = 0;
        }
        for (LoaiKhachHang l : listLKH) {
            if (l.getTenLoaiKH().equalsIgnoreCase(maLoai))
                maLoai = l.getMaLoaiKH();
        }
        KhachHang kh = valiData();
        if (kh == null)
            return;
        else {
            if (chkThem == true && chkSua == false) {
                try {

                    KhachHang khachHang = new KhachHang(MaKH, tenKH, Sdt, CCCD, ngaySinh, diaChi, gioiTinh, selectedLoaiKhachHang, email, dtluy);
                    boolean newL = khachHangImpl.addKhachHang(khachHang);
                    if (newL == true) {
                        xoaTrang();
                        deleteAllDataTable();
                        docDuLieu();
                        txtMaKH.setText(deFaultID());
                        chkSua = false;
                        btnSua.setIcon(new ImageIcon("Anh\\sua.png"));
                        JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công");

                    } else

                        lblLoi.setText("Thêm khách hàng không thành công, Số điện thoại đã có trong hệ thống!!!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (chkSua == true && chkThem == false) {
                float dlLuyNew = Float.parseFloat(txtDTL.getText());
                KhachHang khach = khachHangImpl.getKhachHang(MaKH);
                khach.setTenKH(tenKH);
                khach.setSdt(Sdt);
                khach.setCccd(CCCD);
                khach.setNgaySinh(ngaySinh);
                khach.setDiaChi(diaChi);
                khach.setGioiTinh(gioiTinh);
                khach.setEmail(email);
                khach.setDiemTichLuy(dlLuyNew);
                khach.setLoaiKH(selectedLoaiKhachHang);
                khach.setDiemTichLuy(dtluy);
                boolean newL = khachHangImpl.updateKhachHang(khach);
                if (newL) {
                    btnSua.setText("Sửa");
                    deleteAllDataTable();
                    docDuLieu();
                    xoaTrang();
                    JOptionPane.showMessageDialog(this, "Cập nhật khách hàng thành công");
                    chkSua = false;
                    chkThem = false;
                    lock = false;
                    khoaTXT(lock);
                    btnSua.setIcon(new ImageIcon("Anh\\sua.png"));
                    btnThem.setIcon(new ImageIcon("Anh\\them.png"));
                    btnSua.setEnabled(true);
                    btnThem.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
                }
            }

        }

    }

    //	Sửa thông tin khach hang
    public void sua() {
        lock = true;
        khoaTXT(lock);
        chkSua = true;
        btnSua.setText("Hủy");
        btnThem.setEnabled(false);
        btnSua.setIcon(new ImageIcon("Anh\\huy.png"));
//		txtTenKH.requestFocus();
    }

    public void tim() throws RemoteException {
        deleteAllDataTable();
        int d = 1;
        String tim = "";
        int gioiTinh = 0;
        try {
            tim = cboTim.getSelectedItem().toString();

        } catch (Exception e) {
            // TODO: handle exception
        }
        if (tim.equalsIgnoreCase("")) {
            lblLoi.setText("Rỗng!");
            return;
        }

        if (rdTenKH.isSelected()) {
            KhachHang kh = khachHangImpl.getKHByName(tim);
            String gioiTinhText = dinhDangGT(kh);
            taiBang(kh, kh.getMaKH(), kh.getTenKH(), gioiTinhText, kh.getLoaiKH().getTenLoaiKH());

        } else if (rdMaKH.isSelected()) {
            KhachHang kh = khachHangImpl.getKhachHang(tim);
            String gioiTinhText = dinhDangGT(kh);
            taiBang(kh, kh.getMaKH(), kh.getTenKH(), gioiTinhText, kh.getLoaiKH().getTenLoaiKH());

        } else if (rdGend.isSelected()) {
            if (cboTim.getSelectedItem().equals("Nam")) {
                gioiTinh = 1;
            } else if (cboTim.getSelectedItem().equals("Nữ"))
                gioiTinh = 0;
            List<KhachHang> listKH = khachHangImpl.getByGender(gioiTinh);
            for (KhachHang kh : listKH) {
                String gioiTinhText = dinhDangGT(kh);

                taiBang(kh, kh.getMaKH(), kh.getTenKH(), gioiTinhText, kh.getLoaiKH().getTenLoaiKH());
            }

        } else if (rdLoaiKH.isSelected()) {
            List<KhachHang> list = khachHangImpl.getKHByLoaiKH(tim);
            for (KhachHang kh : list) {
                String gioiTinhText = dinhDangGT(kh);
                taiBang(kh, kh.getMaKH(), kh.getTenKH(), gioiTinhText, kh.getLoaiKH().getTenLoaiKH());
            }

        }
    }

    //	ĐỊnh đang giới tính
    public String dinhDangGT(KhachHang kh) {
        String gend = kh.getGioiTinh() == 1 ? "Nam" : "Nữ";
        return gend;
    }

    //	ĐỊnh đang giới tính
    public String dinhDangGTImpl(KhachHang kh) {
        String gend = kh.getGioiTinh() == 1 ? "Nam" : "Nữ";
        return gend;
    }

    //	tải lên bảng thông tin tìm kiếm
    public void taiBang(KhachHang kh, String maKH, String tenKH, String gioiTinhText, String loaiKH) {
        tablemodel.addRow(new Object[]{maKH, tenKH, gioiTinhText, kh.getSdt(), kh.getCccd(),
                kh.getNgaySinh(), kh.getEmail(), kh.getDiaChi(), kh.getDiemTichLuy(),
                loaiKH});
    }

    //	Kiểm tra thông tin nhà cung cấp trước khi thêm
    public KhachHang valiData() {
        KhachHang kh;
        float dTichLuy = 0;
        String maKH = txtMaKH.getText().trim();
        if (txtTenKH.getText().isEmpty() || txtTenKH.getText().trim() == "") {
            lblLoi_Ten.setText("Tên khách hàng không được rỗng");
            txtTenKH.requestFocus();
            return null;
        } else {
            lblLoi_Ten.setText("");
        }
        String tenKH = txtTenKH.getText().trim();
        if (txtSDT.getText().isEmpty() || txtSDT.getText().trim() == "") {
            lblLoi_SDT.setText("Số điện thoại khách hàng không được rỗng");
            txtSDT.requestFocus();
            return null;
        } else if (txtSDT.getText().trim().matches("^[0]\\d{9}$") == false) {
            lblLoi_SDT.setText("Vui lòng nhập số điện thoại khách hàng bằng số và gồm 10 chữ số bất đầu bằng số 0 !");
            txtSDT.requestFocus();
            return null;
        } else {
            lblLoi_SDT.setText("");
        }
        String sdt = txtSDT.getText().trim();

        if (txtEmail.getText().isEmpty() || txtEmail.getText().trim() == "") {
            lblLoi_Email.setText("Email khách hàng không được rỗng");
            txtEmail.requestFocus();
            return null;
        } else if (txtEmail.getText().trim().matches("^\\b[\\w.%+-]+@[\\w.-]+\\.com\\b$") == false) {
            lblLoi_Email.setText("Vui lòng nhập đúng email khách hàng !\\n ví dụ:quoc@gmail.com");
            txtEmail.requestFocus();
            return null;
        } else {
            lblLoi_Email.setText("");
        }
        String mail = txtEmail.getText().trim();
        if (txtDiaChi.getText().isEmpty() || txtDiaChi.getText().trim() == "") {
            lblLoi_DiaChi.setText("Địa chỉ không được rỗng");
            txtDiaChi.requestFocus();
            return null;
        } else {
            lblLoi_DiaChi.setText("");
        }
        String diaChi = txtDiaChi.getText().trim();

        int gioiTinh = 2;
        if (rdNam.isSelected()) {
            gioiTinh = 1;
            lblLoi_GioiTinh.setText("");
        } else if (rdNu.isSelected()) {
            gioiTinh = 0;
            lblLoi_GioiTinh.setText("");
        } else if (gioiTinh == 2) {
            lblLoi_GioiTinh.setText("Chưa chọn giới tính?");
            return null;
        }

        if (txtCCCD.getText().isEmpty() || txtCCCD.getText().trim() == "") {
            lblLoi_CCCD.setText("Căn cước công dân khách hàng không được rỗng");
            txtCCCD.requestFocus();
            return null;
        } else if (txtCCCD.getText().trim().matches("^[0]\\d{11}$") == false) {
            lblLoi_CCCD.setText("Vui lòng nhập Căn cước công dân khách hàng bằng số và gồm 12 chữ số bất đầu bằng số 0 !");
            txtCCCD.requestFocus();
            return null;
        } else {
            lblLoi_CCCD.setText("");
        }
        String CCCD = txtCCCD.getText().trim();
        java.util.Date ngaySinh = txtChonNgay.getDate();
        if (ngaySinh == null) {
            lblLoi_NgaySinh.setText("Ngày sinh không được rỗng");
            return null;
        } else {

            LocalDate ngaySinhLocal = ngaySinh.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate ngayHienTaiLocal = LocalDate.now();

            int tuoi = Period.between(ngaySinhLocal, ngayHienTaiLocal).getYears();

            // Kiểm tra xem người dùng có đủ 18 tuổi không
            if (tuoi >= 16) {
                // Người dùng đủ tuổi

                lblLoi_NgaySinh.setText("");

            } else {
                lblLoi_NgaySinh.setText("Bạn chưa đủ 16 tuổi");
                return null;
            }
        }
        try {
            kh = new KhachHang(maKH, tenKH, sdt, CCCD, ngaySinh, diaChi, gioiTinh, null, mail, dTichLuy);
            return kh;

        } catch (Exception e) {
            lblLoi.setText(e.getMessage());
            return null;
        }

    }

    private void ShowErrorField(String string, JTextField txt) {
        lblLoi.setText(string);
        txt.requestFocus();

    }

    public String deFaultID() {
        List<KhachHang> listKH = null;
        try {
            listKH = khachHangImpl.getAllKH();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        if (listKH != null) {
            int n = listKH.size() + 1;
            String soLuongMa = String.format("%03d", n);
            String deFault = soLuongMa;
            return deFault;
        }
        return null;
    }

    public void xoaTrang() {

        txtMaKH.setText(deFaultID());
        txtTenKH.setText("");
        txtSDT.setText("");
        txtCCCD.setText("");
        txtChonNgay.setDate(new Date(System.currentTimeMillis()));
        txtDiaChi.setText("");
        buttonGroupGT.clearSelection();
        cboLoaiKhachHang.setSelectedItem("");
        txtEmail.setText("");
        txtDTL.setText("");
        txtTenKH.requestFocus();
        deleteAllDataTable();
        docDuLieu();
    }

    public void xoaTrangTimKiem() {
        buttonGroupTK.clearSelection();
        cboTim.removeAllItems();
        deleteAllDataTable();
        docDuLieu();
    }

    public void deleteAllDataTable() {
        tablemodel = (DefaultTableModel) table_1.getModel();
        tablemodel.getDataVector().removeAllElements();
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
