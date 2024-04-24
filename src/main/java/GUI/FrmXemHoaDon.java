package GUI;

import DAOTest.KhachHangDao;
import DAOTest.SanPhamDao;
import DAOTest.XemHoaDonDao;

import Entities.CtHoadon;
import Entities.HoaDon;
import Entities.SanPham;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FrmXemHoaDon extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public static DefaultTableModel tablemodel = new DefaultTableModel();
    private JLabel lblTieuDeTrang;
    static JPanel pnlThongTin;
    private JTextField txtTenKH;
    private JTextField txtSDT;
    private JTextField txtMaHD;
    private JTextField txtNgayLap;
    private JTextField txtNhanVien;
    private JTextField txtTongSL;
    private JTextField txtDiemTichDuoc;
    private JTextField txtTongThanhToan;
    private JTable table_CTHD;
    private XemHoaDonDao hd_dao = (XemHoaDonDao) Naming.lookup(URL + "XemHoaDonDao");
    private KhachHangDao kh_dao = (KhachHangDao) Naming.lookup(URL + "KhachHangDao");;
    private SanPhamDao sp_Dao = (SanPhamDao) Naming.lookup(URL + "SanPhamDao");;
    public static DefaultTableModel tblModelHoaDon = new DefaultTableModel();

    private DefaultComboBoxModel cboModetenKH = new DefaultComboBoxModel();
    private List<String> listtenKH = new ArrayList<String>();
    private DefaultComboBoxModel cboModetenNV = new DefaultComboBoxModel();
    private List<String> listtenNV = new ArrayList<String>();
    private DefaultComboBoxModel cboModema = new DefaultComboBoxModel();
    private List<String> listma = new ArrayList<String>();
    private DefaultComboBoxModel cboModeNgayLap = new DefaultComboBoxModel();
    private List<String> listNgayLap = new ArrayList<String>();

    private JTable tblHD;
    private JComboBox cbTimKiem;
    private JButton btnLamMoi;
    private JButton btnTimKiem;
    private JRadioButton rdMaHD;
    private JRadioButton rdTenKH;
    private JRadioButton rdTenNV;
    private JRadioButton rdNgayLap;
    private ButtonGroup group;
    private JDateChooser txtChonNgayKT;
    private JLabel lblThongTinTim;
    private JPanel pnlDSHD;
    private JLabel lblMaHD;
    private JLabel lblNgayLap;
    private JLabel lblNhanVien;
    private JLabel lblSoLuong;
    private JLabel lblDiemTichDuoc;
    private JPanel pnlHoaDon;
    private JLabel lblTongThanhToan;
    private JPanel pnlKhachHang;
    private JLabel lblTenKH;
    private JLabel lblSDT;
    private JPanel pnlThongTinHD;
    private JPanel pnlTableCTHD;
    private JScrollPane scrDSHD;
    private JPanel pnlTieuDe;
    DecimalFormat tien = new DecimalFormat(",##0");
    List<HoaDon> list = hd_dao.getAllHoaDon();

private static final String URL = "rmi://172.20.10.5:6541/";


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    FrmXemHoaDon frame = new FrmXemHoaDon();
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
    public FrmXemHoaDon() throws RemoteException, MalformedURLException, NotBoundException {
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
        lblTieuDeTrang = new JLabel("XEM HÓA ĐƠN");
        lblTieuDeTrang.setBounds(512, 12, 305, 25);
        pnlTieuDe.add(lblTieuDeTrang);
        lblTieuDeTrang.setBackground(new Color(0, 255, 255));
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));

        pnlTableCTHD = new JPanel();
        pnlTableCTHD.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Thông tin chi tiết hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pnlTableCTHD.setBackground(new Color(255, 255, 255));
        pnlTableCTHD.setBounds(20, 48, 996, 355);
        pnlThongTin.add(pnlTableCTHD);
        pnlTableCTHD.setLayout(null);

        scrDSHD = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrDSHD.setPreferredSize(new Dimension(0, 250));
        scrDSHD.setBounds(10, 21, 976, 324);
        pnlTableCTHD.add(scrDSHD);

        table_CTHD = new JTable();
        table_CTHD.setBackground(new Color(224, 255, 255));
        table_CTHD.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"STT", "Tên sản phẩm", "Màu sắc",
                "Size", "Chất liệu", "Đơn giá", "Số lượng", "VAT", "Khuyến mãi (%)", "Thành tiền"}));
        scrDSHD.setViewportView(table_CTHD);

        pnlThongTinHD = new JPanel();
        pnlThongTinHD.setForeground(new Color(0, 0, 0));
        pnlThongTinHD.setBackground(new Color(255, 255, 255));
        pnlThongTinHD.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Thông tin hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pnlThongTinHD.setBounds(1026, 48, 280, 355);
        pnlThongTin.add(pnlThongTinHD);
        pnlThongTinHD.setLayout(null);

        pnlKhachHang = new JPanel();
        pnlKhachHang.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlKhachHang.setBackground(new Color(255, 255, 255));
        pnlKhachHang.setBounds(10, 23, 260, 80);
        pnlThongTinHD.add(pnlKhachHang);
        pnlKhachHang.setLayout(null);

        lblTenKH = new JLabel("Tên khách hàng");
        lblTenKH.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblTenKH.setBounds(10, 10, 116, 18);
        pnlKhachHang.add(lblTenKH);

        lblSDT = new JLabel("Số điện thoại");
        lblSDT.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblSDT.setBounds(10, 48, 116, 18);
        pnlKhachHang.add(lblSDT);

        txtTenKH = new JTextField();
        txtTenKH.setForeground(new Color(0, 0, 0));
        txtTenKH.setBackground(new Color(255, 255, 255));
        txtTenKH.setEditable(false);
        txtTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtTenKH.setBounds(136, 11, 114, 19);
        pnlKhachHang.add(txtTenKH);
        txtTenKH.setColumns(10);

        txtSDT = new JTextField();
        txtSDT.setBackground(new Color(255, 255, 255));
        txtSDT.setEditable(false);
        txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtSDT.setColumns(10);
        txtSDT.setBounds(136, 49, 114, 19);
        pnlKhachHang.add(txtSDT);

        pnlHoaDon = new JPanel();
        pnlHoaDon.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlHoaDon.setBackground(new Color(255, 255, 255));
        pnlHoaDon.setBounds(10, 113, 260, 229);
        pnlThongTinHD.add(pnlHoaDon);
        pnlHoaDon.setLayout(null);

        lblTongThanhToan = new JLabel("Tổng thanh toán");
        lblTongThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblTongThanhToan.setBounds(10, 200, 116, 18);
        pnlHoaDon.add(lblTongThanhToan);

        lblDiemTichDuoc = new JLabel("Điểm tích được");
        lblDiemTichDuoc.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblDiemTichDuoc.setBounds(10, 162, 116, 18);
        pnlHoaDon.add(lblDiemTichDuoc);

        lblSoLuong = new JLabel("Tổng số lượng");
        lblSoLuong.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblSoLuong.setBounds(10, 124, 116, 18);
        pnlHoaDon.add(lblSoLuong);

        lblNhanVien = new JLabel("Nhân viên");
        lblNhanVien.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNhanVien.setBounds(10, 86, 116, 18);
        pnlHoaDon.add(lblNhanVien);

        lblNgayLap = new JLabel("Ngày lập");
        lblNgayLap.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNgayLap.setBounds(10, 48, 116, 18);
        pnlHoaDon.add(lblNgayLap);

        lblMaHD = new JLabel("Mã hóa đơn");
        lblMaHD.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblMaHD.setBounds(10, 10, 116, 18);
        pnlHoaDon.add(lblMaHD);

        txtMaHD = new JTextField();
        txtMaHD.setBackground(new Color(255, 255, 255));
        txtMaHD.setEditable(false);
        txtMaHD.setFont(new Font("Times New Roman", Font.BOLD, 11));
        txtMaHD.setColumns(10);
        txtMaHD.setBounds(136, 11, 114, 19);
        pnlHoaDon.add(txtMaHD);

        txtNgayLap = new JTextField();
        txtNgayLap.setBackground(new Color(255, 255, 255));
        txtNgayLap.setEditable(false);
        txtNgayLap.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtNgayLap.setColumns(10);
        txtNgayLap.setBounds(136, 49, 114, 19);
        pnlHoaDon.add(txtNgayLap);

        txtNhanVien = new JTextField();
        txtNhanVien.setBackground(new Color(255, 255, 255));
        txtNhanVien.setEditable(false);
        txtNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtNhanVien.setColumns(10);
        txtNhanVien.setBounds(136, 87, 114, 19);
        pnlHoaDon.add(txtNhanVien);

        txtTongSL = new JTextField();
        txtTongSL.setBackground(new Color(255, 255, 255));
        txtTongSL.setEditable(false);
        txtTongSL.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtTongSL.setColumns(10);
        txtTongSL.setBounds(136, 125, 114, 19);
        pnlHoaDon.add(txtTongSL);

        txtDiemTichDuoc = new JTextField();
        txtDiemTichDuoc.setBackground(new Color(255, 255, 255));
        txtDiemTichDuoc.setEditable(false);
        txtDiemTichDuoc.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtDiemTichDuoc.setColumns(10);
        txtDiemTichDuoc.setBounds(136, 163, 114, 19);
        pnlHoaDon.add(txtDiemTichDuoc);

        txtTongThanhToan = new JTextField();
        txtTongThanhToan.setEditable(false);
        txtTongThanhToan.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtTongThanhToan.setBackground(new Color(255, 255, 255));
        txtTongThanhToan.setColumns(10);
        txtTongThanhToan.setBounds(136, 201, 114, 19);
        pnlHoaDon.add(txtTongThanhToan);

        pnlDSHD = new JPanel();
        pnlDSHD.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Danh s\u00E1ch h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null,
                new Color(0, 0, 0)));
        pnlDSHD.setBackground(Color.WHITE);
        pnlDSHD.setBounds(20, 425, 1286, 360);
        pnlThongTin.add(pnlDSHD);
        pnlDSHD.setLayout(null);

        lblThongTinTim = new JLabel("Nhập thông tin tìm kiếm");
        lblThongTinTim.setBounds(20, 22, 148, 15);
        lblThongTinTim.setFont(new Font("Times New Roman", Font.BOLD, 12));
        pnlDSHD.add(lblThongTinTim);

        JScrollPane scrDSNV;
        String[] tb2 = new String[]{"STT", "Mã hóa đơn", "Ngày lập", "Tên khách hàng", "Tên nhân viên", "Tổng tiền"};
        tblModelHoaDon = new DefaultTableModel(tb2, 0);
        tblHD = new JTable(tblModelHoaDon);
        tblHD.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int selectedRow = tblHD.getSelectedRow();
                txtMaHD.setText(tblHD.getValueAt(selectedRow, 1).toString());
                txtNhanVien.setText(tblHD.getValueAt(selectedRow, 4).toString());
                txtNgayLap.setText(tblHD.getValueAt(selectedRow, 2).toString());
                txtTenKH.setText(tblHD.getValueAt(selectedRow, 3).toString());
                txtTongThanhToan.setText(tblHD.getValueAt(selectedRow, 5).toString());

                String tongThanhToanStr = tblHD.getValueAt(selectedRow, 5).toString();
                tongThanhToanStr = tongThanhToanStr.replaceAll("[^0-9.]", "");
                double tongThanhToan = Double.parseDouble(tongThanhToanStr);
                double diemTichDuoc = tongThanhToan * 0.01;
                txtDiemTichDuoc.setText(String.valueOf(diemTichDuoc));


                String sdt = null;
                try {
                    sdt = kh_dao.getKHByName(txtTenKH.getText()).getSdt();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }

                txtSDT.setText(sdt);

                if (selectedRow >= 0) {
                    String selectedInvoiceCode = (String) tblHD.getValueAt(selectedRow, 1);
                    // change the way the CtHoadon objects are fetched
                    List<CtHoadon> list = getInvoiceDetails(selectedInvoiceCode);
//                    List<SanPham> listSP = sp_Dao.getAllSP();
                    try {
                        List<SanPham> listSP = sp_Dao.getAllSP();
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                    xoaTableChiTiet();

                    DefaultTableModel detailsModel = (DefaultTableModel) table_CTHD.getModel();
                    int i = 0;
                    int soLuong = 0;
                    double tongTienSp = 0;
                    int giaTri = 0;
                    double vat = 0;
                    for (CtHoadon x : list) {
                        if (x.getMaSanPham().getVat() == 1) {
                            vat = (x.getMaSanPham().getGianhap() * 2.5 * 0.05);
                        } else {
                            vat = 0;
                        }
                        soLuong += x.getSoLuongSP();
                        i++;
                        if(x.getMaSanPham().getKhuyenMai() == null)
                            giaTri = 0;
                        else
                            giaTri = x.getMaSanPham().getKhuyenMai().getPhanTramKhuyenMai();
                        tongTienSp += x.getMaSanPham().getGiaBan() != null ? x.getMaSanPham().getGiaBan() : 0;
                        detailsModel.addRow(new Object[]{i, x.getMaSanPham().getTensp(), x.getMaSanPham().getMauSac().toString(),
                                x.getMaSanPham().getSize().toString(), x.getMaSanPham().getChatLieu().getTenChatLieu(),
                                tien.format(x.getMaSanPham().getGiaBan()), x.getSoLuongSP(), tien.format(vat),

                                giaTri,
                                tien.format(x.getSoLuongSP() * x.getMaSanPham().getGiaBan())});
                    }
                    txtTongSL.setText(soLuong + "");
                }
            }
        });


        tblHD.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        tblHD.setBackground(new Color(224, 255, 255));
        tblHD.setForeground(new Color(0, 0, 0));
        getContentPane().add(scrDSNV = new JScrollPane(tblHD, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
        scrDSNV.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        scrDSNV.setBounds(10, 79, 1266, 271);
        pnlDSHD.add(scrDSNV);
        scrDSNV.setPreferredSize(new Dimension(0, 250));

        cbTimKiem = new JComboBox();
        cbTimKiem.setBounds(184, 19, 407, 21);
        pnlDSHD.add(cbTimKiem);
        cbTimKiem.setEditable(true);

        btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setIcon(new ImageIcon("Anh\\lammoi.png"));
        btnLamMoi.setBackground(new Color(0, 255, 255));
        btnLamMoi.setBounds(1027, 22, 117, 30);
        pnlDSHD.add(btnLamMoi);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setIcon(new ImageIcon("Anh\\timkiem.png"));
        btnTimKiem.setBackground(new Color(0, 255, 255));
        btnTimKiem.setBounds(832, 22, 125, 30);
        btnTimKiem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        pnlDSHD.add(btnTimKiem);

        JLabel lblTimKiem = new JLabel("Tìm theo");
        lblTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblTimKiem.setBounds(20, 54, 148, 15);
        pnlDSHD.add(lblTimKiem);

        rdMaHD = new JRadioButton("Mã hóa đơn");
        rdMaHD.setFont(new Font("Times New Roman", Font.BOLD, 12));
        rdMaHD.setBackground(new Color(255, 255, 255));
        rdMaHD.setBounds(184, 52, 103, 21);
        pnlDSHD.add(rdMaHD);

        rdTenKH = new JRadioButton("Tên khách hàng");
        rdTenKH.setFont(new Font("Times New Roman", Font.BOLD, 12));
        rdTenKH.setBackground(Color.WHITE);
        rdTenKH.setBounds(325, 52, 129, 21);
        pnlDSHD.add(rdTenKH);

        rdTenNV = new JRadioButton("Tên nhân viên");
        rdTenNV.setFont(new Font("Times New Roman", Font.BOLD, 12));
        rdTenNV.setBackground(Color.WHITE);
        rdTenNV.setBounds(488, 52, 103, 21);
        pnlDSHD.add(rdTenNV);

        rdNgayLap = new JRadioButton("Ngày lập");
        rdNgayLap.setFont(new Font("Times New Roman", Font.BOLD, 12));
        rdNgayLap.setBackground(Color.WHITE);
        rdNgayLap.setBounds(655, 52, 103, 21);
        pnlDSHD.add(rdNgayLap);

        txtChonNgayKT = new JDateChooser();
        txtChonNgayKT.getCalendarButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        txtChonNgayKT.setBounds(613, 19, 156, 21);
        txtChonNgayKT.setForeground(new Color(0, 0, 0));
        txtChonNgayKT.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txtChonNgayKT.setLocale(Locale.forLanguageTag("vi-VN"));
        txtChonNgayKT.setDateFormatString("dd/MM/yyyy");
        txtChonNgayKT.setVisible(false);

        pnlDSHD.add(txtChonNgayKT);

        group = new ButtonGroup();
        group.add(rdMaHD);
        group.add(rdNgayLap);
        group.add(rdTenKH);
        group.add(rdTenNV);

        docDuLieuHD();
        updateCombobox();

        btnLamMoi.addActionListener(this);
        btnTimKiem.addActionListener(this);

        rdMaHD.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                updateCombobox();
            }
        });

        rdTenKH.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                updateCombobox();
            }
        });

        rdTenNV.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                updateCombobox();
            }
        });

        rdNgayLap.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                txtChonNgayKT.setVisible(true);
            }
        });

    }

    private List<CtHoadon> getInvoiceDetails(String invoiceCode) {
//        List<CtHoadon> details = hd_dao.getCT_HoaDon(invoiceCode);
        List<CtHoadon> details = null;
        try {
            details = hd_dao.getCT_HoaDon(invoiceCode);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return details;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object o = e.getSource();
        if (o.equals(btnTimKiem)) {
            if (!rdNgayLap.isSelected()) {
                xoaTableChiTiet();
                xoaTableHoaDon();
                TimKiem();
            } else if (rdNgayLap.isSelected()) {
                xoaTableChiTiet();
                xoaTableHoaDon();
                int d = 1;
                Calendar ngayCld = Calendar.getInstance();
                ngayCld.setTime(txtChonNgayKT.getDate());
                int ngay = ngayCld.get(Calendar.DATE);
                int thang = ngayCld.get(Calendar.MONTH) + 1;
                int nam = ngayCld.get(Calendar.YEAR);
//                List<HoaDon> hds = hd_dao.getHDTheoNgayLap(ngay, thang, nam);
                List<HoaDon> hds = null;
                try {
                    hds = hd_dao.getHDTheoNgayLap(ngay, thang, nam);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }

                if (hds.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không có hóa đơn cho ngày đã chọn.", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    for (HoaDon x : hds) {
                        String nhanVien = x.getNhanVien().getTenNhanVien();
                        String khachHang = x.getKhachHang().getTenKH();
                        tblModelHoaDon.addRow(new Object[]{d++, x.getMaHoaDon(), x.getNgayLapHoaDon(), khachHang, nhanVien,
                                tien.format(x.getTongTien())});
                    }

                    tblModelHoaDon.fireTableDataChanged();
                    tblHD.setModel(tblModelHoaDon);
                }
            }

        } else if (o.equals(btnLamMoi)) {
            lamMoi();
            xoaTableChiTiet();
            cbTimKiem.setSelectedItem("");

        }

    }

    public void docDuLieuHD() {
        int d = 1;
        try {
            list = hd_dao.getAllHoaDon();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        for (HoaDon x : list) {
            String ngayLapStr = (x.getNgayLapHoaDon() != null) ? df.format(x.getNgayLapHoaDon()) : "";

            String nhanVien = x.getNhanVien().getTenNhanVien();
            String khachHang = x.getKhachHang().getTenKH();
            tblModelHoaDon.addRow(
                    new Object[]{d++, x.getMaHoaDon(), ngayLapStr, khachHang, nhanVien, tien.format(x.getTongTien())

                    });
        }
        tblHD.setModel(tblModelHoaDon);

    }

    /**
     * Dùng đọc dữ liệu từ cơ sở dữ liệu lên bảng
     */
    public void updateCombobox() {
        int d = 1;
//        List<HoaDon> list = hd_dao.getAllHoaDon();
        try {
            List<HoaDon> list = hd_dao.getAllHoaDon();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        cbTimKiem.removeAllItems();

        for (HoaDon x : list) {
            if (rdMaHD.isSelected()) {
                String maHoaDon = x.getMaHoaDon();
                addItemIfNotExists(cbTimKiem, maHoaDon);
            }
            if (rdTenKH.isSelected()) {
                String tenKH = x.getKhachHang().getTenKH();
                addItemIfNotExists(cbTimKiem, tenKH);
            }
            if (rdTenNV.isSelected()) {
                String tenNV = x.getNhanVien().getTenNhanVien();
                addItemIfNotExists(cbTimKiem, tenNV);
            }

        }
    }

    private void addItemIfNotExists(JComboBox<String> comboBox, String item) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            if (item.equals(comboBox.getItemAt(i))) {
                return;
            }
        }
        comboBox.addItem(item);
    }

    /**
     * Xóa dữ liêu bảng chi tiết
     */
    public void xoaTableChiTiet() {

        DefaultTableModel tableModel = (DefaultTableModel) table_CTHD.getModel();
        tableModel.getDataVector().removeAllElements();
    }

    /**
     * Xóa dữ liệu bảng hóa đơn
     */
    public void xoaTableHoaDon() {
        tblModelHoaDon.addRow(new Object[]{

        });
        DefaultTableModel tblModel2 = (DefaultTableModel) tblHD.getModel();
        tblModel2.getDataVector().removeAllElements();
    }

    /**
     * làm mởi lại giao diện
     */
    public void lamMoi() {
        txtMaHD.setText("");
        txtNgayLap.setText("");
        txtNhanVien.setText("");
        txtTenKH.setText("");
        txtTongSL.setText("");
        txtDiemTichDuoc.setText("");
        txtSDT.setText("");
        txtTongThanhToan.setText("");
        group.clearSelection();

        xoaTableChiTiet();
        xoaTableHoaDon();
        docDuLieuHD();

    }

    /**
     * Dùng để tìm kiếm hóa đơn
     */
    public void TimKiem() {
        xoaTableChiTiet();
        xoaTableHoaDon();
        String tim = "";

        try {
            tim = cbTimKiem.getSelectedItem().toString();

        } catch (Exception e) {
            // TODO: handle exception
        }
        if (tim.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm!");
            return;
        }

        if (rdMaHD.isSelected()) {
            int d = 1;
            String ma = cbTimKiem.getSelectedItem().toString();
//            HoaDon x = hd_dao.getHDTHeoMa(ma);
            HoaDon x = null;
            try {
                x = hd_dao.getHDTHeoMa(ma);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if (x == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn có mã " + ma);
                return;
            }

            xoaTableChiTiet();
            xoaTableHoaDon();
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

            String ngayLapStr = (x.getNgayLapHoaDon() != null) ? df.format(x.getNgayLapHoaDon()) : ""; // Kiểm tra null trước khi
            // định dạng ngày

            String nhanVien = x.getNhanVien().getTenNhanVien();
            String khachHang = x.getKhachHang().getTenKH();
            tblModelHoaDon.addRow(
                    new Object[]{d++, x.getMaHoaDon(), ngayLapStr, khachHang, nhanVien, tien.format(x.getTongTien())

                    });

            tblHD.setModel(tblModelHoaDon);

        }
        if (rdTenKH.isSelected()) {
            int d = 1;

            String ma = cbTimKiem.getSelectedItem().toString();
//            List<HoaDon> hds = hd_dao.getHDTheoTenKH(ma);
            try {
                List<HoaDon> hds = hd_dao.getHDTheoTenKH(ma);
                for (HoaDon x : hds) {

                    String nhanVien = x.getNhanVien().getTenNhanVien();
                    String khachHang = x.getKhachHang().getTenKH();
                    tblModelHoaDon.addRow(new Object[]{d++, x.getMaHoaDon(), x.getNgayLapHoaDon(), khachHang, nhanVien,
                            tien.format(x.getTongTien())

                    });
                }

                tblHD.setModel(tblModelHoaDon);
            } catch (RemoteException e) {
                e.printStackTrace();

            }
        }
        if (rdTenNV.isSelected()) {
            int d = 1;

            String ma = cbTimKiem.getSelectedItem().toString();
//            List<HoaDon> hds = hd_dao.getHDTheoTenNV(ma);
            try {
                List<HoaDon> hds = hd_dao.getHDTheoTenNV(ma);
                for (HoaDon x : hds) {

                    String nhanVien = x.getNhanVien().getTenNhanVien();
                    String khachHang = x.getKhachHang().getTenKH();
                    tblModelHoaDon.addRow(new Object[]{d++, x.getMaHoaDon(), x.getNgayLapHoaDon(), khachHang, nhanVien,
                            tien.format(x.getTongTien())

                    });
                }

                tblHD.setModel(tblModelHoaDon);
            } catch (RemoteException e) {
                e.printStackTrace();

            }
        }
    }
}
