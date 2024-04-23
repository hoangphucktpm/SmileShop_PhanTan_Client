package GUI;

import DAOTest.NhanVienDao;
import DAOTest.ThongKeDoanhThuDao;
import DAOTest.impl.NhanVienImpl;
import DAOTest.impl.ThongKeDoanhThuImpl;
import Entities.NhanVien;
import Entities.ThongKeDoanhThu;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class    FrmThongKeDoanhThu extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    public static DefaultTableModel tablemodel = new DefaultTableModel();
    private JTable table_DS;
    private JLabel lblTieuDeTrang;
    static JPanel pnlThongTin;
    private JPanel pnlThongTinTK;
    private JLabel lblTongTienBan;
    private JLabel lblTienNhap;
    private JLabel lblLoiNhuan;
    private JRadioButton rdQL;
    private JRadioButton rdNV;
    private JLabel lblChucVu;
    private JPanel pnlHinhThuc;
    private JLabel lblSeach;
    private JPanel pnTable;
    private JButton btnXem;
    private JRadioButton rdMonth;
    private JRadioButton rdBoth;
    private JLabel lblQuy;
    private JComboBox cbMonth;
    private JLabel lblMonth;
    private JLabel lblNYear;
    private JTextField txtNam;
    private JLabel lblCa;
    private JComboBox cboCa;
    private JRadioButton rdNam;
    private JRadioButton rdQuy;
    private JPanel pnThoiDiem;
    private JComboBox cboQuy;
    private JTextField textTTBD;
    private JTextField textTienNhap;
    private JTextField textLoiNhuan;

    private double tienBan;
    private double tienNhap;
    private double loiNhuan;
    private double tongTienSanPham;
    private double tienGiam;

    DecimalFormat tien = new DecimalFormat("#,##0");
    DecimalFormat tienSo = new DecimalFormat("###0");

    private ButtonGroup gr;
    private ButtonGroup grChuc;
    private JPanel pnlBieuDo;
    private ChartPanel chartPanel;
    private JButton btnInThongKe;

    private FrmXuatThongKe frmInTK = new FrmXuatThongKe();
    private JTextField txtTienGiam;
    private JLabel lblTongTienSP;
    private JTextField txtTongTienSP;
    private int quy;
    private int thang;
    private int nam;
    private double tienMua;
    private JLabel lblTienKhuyenMai;
    private NhanVienDao daoNV = (NhanVienDao) Naming.lookup(URL + "NhanVienDao");
    private ThongKeDoanhThuDao daoTKDTImpl = (ThongKeDoanhThuDao) Naming.lookup(URL + "ThongKeDoanhThuDao");


private static final String URL = "rmi://192.168.1.15:6541/";

    public static void main(String[] args) {
        try {
                 FrmThongKeDoanhThu frm = new FrmThongKeDoanhThu();

                frm.setVisible(true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    public FrmThongKeDoanhThu() throws RemoteException, MalformedURLException, NotBoundException {
        pnlThongTin = new JPanel();
        pnlThongTin.setBorder(new LineBorder(new Color(0, 0, 0)));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1347, 843);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);
        pnlThongTin.setBackground(new Color(255, 255, 255));
        pnlThongTin.setBounds(0, 0, 1328, 806);
        getContentPane().add(pnlThongTin);
        pnlThongTin.setLayout(null);

        pnlThongTinTK = new JPanel();
        pnlThongTinTK.setBackground(new Color(255, 255, 255));
        pnlThongTinTK.setBounds(20, 62, 640, 144);
        pnlThongTin.add(pnlThongTinTK);

        javax.swing.border.Border southborder = BorderFactory.createLineBorder(Color.black);
        TitledBorder southTitleBorder2 = new TitledBorder(southborder, "Thông tin thống kê");
        southTitleBorder2.setTitleColor(Color.black);
        pnlThongTinTK.setBorder(southTitleBorder2);
        pnlThongTin.add(pnlThongTinTK);
        pnlThongTinTK.setLayout(null);

        textTTBD = new JTextField();
        textTTBD.setBounds(188, 31, 126, 24);
        pnlThongTinTK.add(textTTBD);
        textTTBD.setEditable(false);
        textTTBD.setHorizontalAlignment(SwingConstants.LEFT);
        textTTBD.setForeground(new Color(0, 0, 0));
        textTTBD.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textTTBD.setColumns(10);
//		textTTBD.setBorder(null);
        textTTBD.setBackground(new Color(255, 255, 255));

        lblTongTienBan = new JLabel("Tổng tiền bán được:");
        lblTongTienBan.setBounds(53, 35, 145, 15);
        pnlThongTinTK.add(lblTongTienBan);
        lblTongTienBan.setHorizontalAlignment(SwingConstants.LEFT);
        lblTongTienBan.setFont(new Font("Tahoma", Font.BOLD, 12));

        lblTienNhap = new JLabel("Tiền Nhập:");
        lblTienNhap.setBounds(53, 70, 145, 15);
        pnlThongTinTK.add(lblTienNhap);
        lblTienNhap.setHorizontalAlignment(SwingConstants.LEFT);
        lblTienNhap.setFont(new Font("Tahoma", Font.BOLD, 12));

        textTienNhap = new JTextField();
        textTienNhap.setBounds(188, 66, 126, 24);
        pnlThongTinTK.add(textTienNhap);
        textTienNhap.setEditable(false);
        textTienNhap.setHorizontalAlignment(SwingConstants.LEFT);
        textTienNhap.setForeground(new Color(0, 0, 0));
        textTienNhap.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textTienNhap.setColumns(10);
//		textTienNhap.setBorder(null);
        textTienNhap.setBackground(new Color(255, 255, 255));

        lblLoiNhuan = new JLabel("Lợi nhuận:");
        lblLoiNhuan.setBounds(53, 105, 145, 15);
        pnlThongTinTK.add(lblLoiNhuan);
        lblLoiNhuan.setHorizontalAlignment(SwingConstants.LEFT);
        lblLoiNhuan.setFont(new Font("Tahoma", Font.BOLD, 12));

        textLoiNhuan = new JTextField();
        textLoiNhuan.setBounds(188, 101, 126, 24);
        pnlThongTinTK.add(textLoiNhuan);
        textLoiNhuan.setEditable(false);
        textLoiNhuan.setHorizontalAlignment(SwingConstants.LEFT);
        textLoiNhuan.setForeground(new Color(0, 0, 0));
        textLoiNhuan.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textLoiNhuan.setColumns(10);
//		textLoiNhuan.setBorder(null);
        textLoiNhuan.setBackground(new Color(255, 255, 255));

        lblTienKhuyenMai = new JLabel("Tiền giảm:");
        lblTienKhuyenMai.setHorizontalAlignment(SwingConstants.LEFT);
        lblTienKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTienKhuyenMai.setBounds(347, 70, 145, 15);
        pnlThongTinTK.add(lblTienKhuyenMai);

        txtTienGiam = new JTextField();
        txtTienGiam.setHorizontalAlignment(SwingConstants.LEFT);
        txtTienGiam.setForeground(Color.BLACK);
        txtTienGiam.setFont(new Font("Times New Roman", Font.BOLD, 14));
        txtTienGiam.setEditable(false);
        txtTienGiam.setColumns(10);
        txtTienGiam.setBackground(Color.WHITE);
        txtTienGiam.setBounds(482, 66, 126, 24);
        pnlThongTinTK.add(txtTienGiam);

        lblTongTienSP = new JLabel("Tổng tiền sản phẩm:");
        lblTongTienSP.setHorizontalAlignment(SwingConstants.LEFT);
        lblTongTienSP.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTongTienSP.setBounds(347, 35, 145, 15);
        pnlThongTinTK.add(lblTongTienSP);

        txtTongTienSP = new JTextField();
        txtTongTienSP.setHorizontalAlignment(SwingConstants.LEFT);
        txtTongTienSP.setForeground(Color.BLACK);
        txtTongTienSP.setFont(new Font("Times New Roman", Font.BOLD, 14));
        txtTongTienSP.setEditable(false);
        txtTongTienSP.setColumns(10);
        txtTongTienSP.setBackground(Color.WHITE);
        txtTongTienSP.setBounds(482, 31, 126, 24);
        pnlThongTinTK.add(txtTongTienSP);

        rdQL = new JRadioButton("Quản lý");
        rdQL.setEnabled(false);
        rdQL.setFont(new Font("Tahoma", Font.PLAIN, 12));
        rdQL.setBackground(new Color(255, 255, 255));
        rdQL.setBounds(133, 231, 119, 33);
        pnlThongTin.add(rdQL);

        rdNV = new JRadioButton("Nhân viên");
        rdNV.setEnabled(false);
        rdNV.setFont(new Font("Tahoma", Font.PLAIN, 12));
        rdNV.setBackground(new Color(255, 255, 255));
        rdNV.setBounds(254, 231, 119, 33);
        pnlThongTin.add(rdNV);

        lblChucVu = new JLabel("Chức vụ:");
        lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblChucVu.setBounds(30, 234, 85, 27);
        pnlThongTin.add(lblChucVu);

        pnlHinhThuc = new JPanel();
        pnlHinhThuc.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "H\u00ECnh th\u1EE9c th\u1ED1ng k\u00EA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pnlHinhThuc.setBackground(new Color(255, 255, 255));
        pnlHinhThuc.setBounds(20, 271, 640, 127);
        pnlThongTin.add(pnlHinhThuc);
        pnlHinhThuc.setLayout(null);

        lblSeach = new JLabel("Tìm theo:");
        lblSeach.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSeach.setBounds(15, 20, 66, 27);
        pnlHinhThuc.add(lblSeach);

        rdQuy = new JRadioButton("Quý");
        rdQuy.setBackground(new Color(255, 255, 255));
        rdQuy.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdQuy.setBounds(100, 23, 109, 23);
        pnlHinhThuc.add(rdQuy);

        rdMonth = new JRadioButton("Tháng");
        rdMonth.setBackground(new Color(255, 255, 255));
        rdMonth.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdMonth.setBounds(100, 58, 109, 23);
        pnlHinhThuc.add(rdMonth);

        rdNam = new JRadioButton("Năm");
        rdNam.setBackground(new Color(255, 255, 255));
        rdNam.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdNam.setBounds(100, 93, 66, 23);
        pnlHinhThuc.add(rdNam);

        rdBoth = new JRadioButton("Cả tháng và năm");
        rdBoth.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdBoth.setBackground(new Color(255, 255, 255));
        rdBoth.setBounds(277, 23, 136, 23);
        pnlHinhThuc.add(rdBoth);
        pnTable = new JPanel();
        pnTable.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnTable.setBackground(new Color(255, 255, 255));
        pnTable.setBounds(20, 409, 1286, 325);

        pnlThongTin.add(pnTable);
        pnTable.setLayout(null);
        table_DS = bangThongKe(tablemodel);
        table_DS.getColumnModel().getColumn(0).setPreferredWidth(40);
        table_DS.getColumnModel().getColumn(1).setPreferredWidth(40);
        table_DS.getColumnModel().getColumn(2).setPreferredWidth(140);
        table_DS.getColumnModel().getColumn(3).setPreferredWidth(80);
        table_DS.getColumnModel().getColumn(4).setPreferredWidth(50);
        table_DS.getColumnModel().getColumn(5).setPreferredWidth(50);
        table_DS.getColumnModel().getColumn(6).setPreferredWidth(80);
        table_DS.getColumnModel().getColumn(8).setPreferredWidth(70);
        table_DS.getColumnModel().getColumn(9).setPreferredWidth(120);
//		table_DS.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        JScrollPane scrDSHD;
        getContentPane().add(scrDSHD = new JScrollPane(table_DS, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
        scrDSHD.setBounds(10, 21, 1266, 294);
        scrDSHD.setPreferredSize(new Dimension(0, 250));
        pnTable.add(scrDSHD);

        btnXem = new JButton("Xem báo cáo");
        btnXem.setIcon(new ImageIcon("Anh\\xembaocao.png"));
        btnXem.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnXem.setBackground(new Color(0, 255, 255));
        btnXem.setBounds(672, 745, 188, 50);
        pnlThongTin.add(btnXem);

        pnThoiDiem = new JPanel();
        pnThoiDiem.setBackground(new Color(255, 255, 255));
        pnThoiDiem.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Th\u1EDDi \u0111i\u1EC3m th\u1ED1ng k\u00EA:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnThoiDiem.setBounds(693, 271, 613, 127);
        pnlThongTin.add(pnThoiDiem);
        pnThoiDiem.setLayout(null);

        lblQuy = new JLabel("Quý:");
        lblQuy.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblQuy.setBounds(68, 20, 70, 25);
        pnThoiDiem.add(lblQuy);

        cboQuy = new JComboBox();
        cboQuy.setBackground(new Color(255, 255, 255));
        cboQuy.setBounds(133, 22, 109, 22);
        pnThoiDiem.add(cboQuy);

        cbMonth = new JComboBox();
        cbMonth.setBackground(new Color(255, 255, 255));
        cbMonth.setBounds(133, 55, 109, 22);
        pnThoiDiem.add(cbMonth);

        lblMonth = new JLabel("Tháng:");
        lblMonth.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMonth.setBounds(68, 49, 46, 32);
        pnThoiDiem.add(lblMonth);

        lblNYear = new JLabel("Năm:");
        lblNYear.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNYear.setBackground(new Color(255, 255, 255));
        lblNYear.setBounds(68, 90, 46, 26);
        pnThoiDiem.add(lblNYear);

        txtNam = new JTextField();
        txtNam.setBounds(133, 90, 109, 22);
        pnThoiDiem.add(txtNam);
        txtNam.setColumns(10);

        lblCa = new JLabel("Ca làm việc:");
        lblCa.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCa.setBounds(354, 23, 77, 25);
        pnThoiDiem.add(lblCa);

        cboCa = new JComboBox();
        cboCa.setBackground(new Color(255, 255, 255));
        cboCa.setBounds(441, 23, 109, 23);
        pnThoiDiem.add(cboCa);

        JPanel pnlTieuDeTrang = new JPanel();
        pnlTieuDeTrang.setBackground(new Color(0, 255, 255));
        pnlTieuDeTrang.setBounds(0, 0, 1343, 42);
        pnlThongTin.add(pnlTieuDeTrang);
        pnlTieuDeTrang.setLayout(null);
        txtNam.setEnabled(false);
        gr = new ButtonGroup();
        gr.add(rdBoth);
        gr.add(rdMonth);
        gr.add(rdNam);
        gr.add(rdQuy);

        grChuc = new ButtonGroup();
        grChuc.add(rdQL);
        grChuc.add(rdNV);
        lblTieuDeTrang = new JLabel("THỐNG KÊ DOANH THU");
        pnlTieuDeTrang.add(lblTieuDeTrang);
        lblTieuDeTrang.setBackground(new Color(0, 255, 255));
        lblTieuDeTrang.setBounds(557, 17, 251, 25);
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));


        pnlBieuDo = new JPanel();
        javax.swing.border.Border borderBieuDo = BorderFactory.createLineBorder(Color.black);
        TitledBorder borderBieuDo2 = new TitledBorder(borderBieuDo, "Top khách hàng");
        borderBieuDo2.setTitleColor(Color.black);
        pnlBieuDo.setBorder(borderBieuDo2);
        pnlBieuDo.setLayout(null);
        pnlBieuDo.setBackground(Color.WHITE);
        pnlBieuDo.setBounds(693, 64, 613, 200);
        pnlThongTin.add(pnlBieuDo);
        chartPanel = new ChartPanel(bieuDoDoanhThu());
        chartPanel.setMaximumDrawWidth(2000);
        chartPanel.setMaximumDrawHeight(1000);
        chartPanel.setBackground(new Color(255, 255, 255));
        chartPanel.setSize(593, 156);
        chartPanel.setLocation(10, 23);
        pnlBieuDo.add(chartPanel);

        btnInThongKe = new JButton("Xuất báo cáo");
        btnInThongKe.setIcon(new ImageIcon("Anh\\xuatTK.png"));
        btnInThongKe.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnInThongKe.setBackground(Color.CYAN);
        btnInThongKe.setBounds(474, 745, 188, 50);
        pnlThongTin.add(btnInThongKe);
        upDateCBCa();
        phanQuyen();

        rdQuy.addActionListener(this);
        btnXem.addActionListener(this);
        rdMonth.addActionListener(this);
        rdNam.addActionListener(this);
        rdBoth.addActionListener(this);
        btnInThongKe.addActionListener(this);
        btnInThongKe.setEnabled(false);
    }



    public void upDateComboBox() {
        cboQuy.removeAllItems();
        cbMonth.removeAllItems();
        txtNam.setEnabled(false);
        if (rdQuy.isSelected()) {

            cboQuy.addItem(1);
            cboQuy.addItem(2);
            cboQuy.addItem(3);
            cboQuy.addItem(4);
        } else if (rdMonth.isSelected()) {
            for (int i = 1; i <= 12; i++) {
                cbMonth.addItem(i);
            }
        } else if (rdNam.isSelected()) {
            txtNam.setEnabled(true);
        } else if (rdBoth.isSelected()) {
            txtNam.setEnabled(true);
            for (int i = 1; i <= 12; i++) {
                cbMonth.addItem(i);
            }
        }
    }

    //	Doanh thu theo quý
    public void doanhThuTheoQuy() {
        try {
            int n = 1;
            quy = 0;
            int ca = 0;
            if (cboCa.getSelectedItem().equals("Tất cả")) {
                ca = 0;
            } else if ((int) cboCa.getSelectedItem() == 1)
                ca = 1;
            else if ((int) cboCa.getSelectedItem() == 2)
                ca = 2;
            quy = (int) cboQuy.getSelectedItem();
            List<ThongKeDoanhThu> list = daoTKDTImpl.getDTQuy(quy, ca);
            tienBan = daoTKDTImpl.tongDoanhThuQuy(quy, ca);
            if (list == null || list.isEmpty()) {
                btnInThongKe.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Không có doanh thu trong quý " + quy);
                textTTBD.setText(String.valueOf(0 + " VNĐ"));
                textTienNhap.setText(String.valueOf(0 + " VNĐ"));
                textLoiNhuan.setText(String.valueOf(0 + " VNĐ"));

            } else {
                btnInThongKe.setEnabled(true);
                layThongTin(list, n);

            }
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //	Doanh thu theo tháng
    public void doanhThuTheoThang() {
        try {
            int n = 1;
            thang = 0;
            int ca = 0;
            if (cboCa.getSelectedItem().equals("Tất cả")) {
                ca = 0;
            } else if ((int) cboCa.getSelectedItem() == 1)
                ca = 1;
            else if ((int) cboCa.getSelectedItem() == 2)
                ca = 2;
            thang = (int) cbMonth.getSelectedItem();

            List<ThongKeDoanhThu> list = daoTKDTImpl.getDTThang(thang, ca);
            tienBan = daoTKDTImpl.tongDoanhThuThang(thang, ca);

            if (list == null || list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có doanh thu trong tháng " + thang);
                btnInThongKe.setEnabled(false);
                textTTBD.setText(String.valueOf(0 + " VNĐ"));
                textTienNhap.setText(String.valueOf(0 + " VNĐ"));
                textLoiNhuan.setText(String.valueOf(0 + " VNĐ"));
            } else {
                layThongTin(list, n);
                btnInThongKe.setEnabled(true);
            }
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //	Doanh thu theo năm
    public void doanhThuTheoNam() {
        try {
            int n = 1;
            nam = 0;
            int ca = 0;
            if (cboCa.getSelectedItem().equals("Tất cả")) {
                ca = 0;
            } else if ((int) cboCa.getSelectedItem() == 1)
                ca = 1;
            else if ((int) cboCa.getSelectedItem() == 2)
                ca = 2;

            if (txtNam.getText().isBlank() || txtNam.getText().toString() == "") {
                JOptionPane.showMessageDialog(this, "Hãy nhập năm cần xem thống kê!");
                txtNam.requestFocus();
            } else {
                nam = Integer.parseInt(txtNam.getText());
                if (nam == 0) {
                    JOptionPane.showMessageDialog(this, "Hãy nhập năm cần xem thống kê!");
                    txtNam.requestFocus();
                    return;
                }
                List<ThongKeDoanhThu> list = daoTKDTImpl.getDTNam(nam, ca);
                tienBan = daoTKDTImpl.tongDoanhThuNam(nam, ca);

                if (list == null || list.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không có doanh thu trong năm " + nam);
                    btnInThongKe.setEnabled(false);
                    textTTBD.setText(String.valueOf(0 + " VNĐ"));
                    textTienNhap.setText(String.valueOf(0 + " VNĐ"));
                    textLoiNhuan.setText(String.valueOf(0 + " VNĐ"));
                } else {

                    layThongTin(list, n);
                    btnInThongKe.setEnabled(true);

                }
            }

        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //	Doanh thu theo năm
    public void doanhThuThangNam() {
        try {
            int n = 1;
            thang = 0;
            nam = 0;
            thang = (int) cbMonth.getSelectedItem();
            nam = Integer.parseInt(txtNam.getText());
            int ca = 0;
            if (cboCa.getSelectedItem().equals("Tất cả")) {
                ca = 0;
            } else if ((int) cboCa.getSelectedItem() == 1)
                ca = 1;
            else if ((int) cboCa.getSelectedItem() == 2)
                ca = 2;

            if (txtNam.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Hãy nhập năm cần xem thống kê!");
                txtNam.requestFocus();
            } else {
                if (nam == 0) {
                    JOptionPane.showMessageDialog(this, "Hãy nhập năm cần xem thống kê!");
                    txtNam.requestFocus();
                    return;
                }
                List<ThongKeDoanhThu> list = daoTKDTImpl.getDTThangNam(thang, nam, ca);
                tienBan = daoTKDTImpl.tongDoanhThuThangNam(thang, nam, ca);

                if (list == null || list.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không có doanh thu trong tháng " + thang + " năm " + nam);
                    btnInThongKe.setEnabled(false);
                    textTTBD.setText(String.valueOf(0 + " VNĐ"));
                    textTienNhap.setText(String.valueOf(0 + " VNĐ"));
                    textLoiNhuan.setText(String.valueOf(0 + " VNĐ"));
                } else {
                    layThongTin(list, n);
                    btnInThongKe.setEnabled(true);
                }
            }

        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Lấy thông tin thích hợp vào bảng
    public void layThongTin(List<ThongKeDoanhThu> list, int n) {

        tienNhap = 0;
        loiNhuan = 0;
        tongTienSanPham = 0;

        for (ThongKeDoanhThu dt : list) {
            tablemodel.addRow(new Object[]{
                    n++, dt.getMaSP(), dt.getTenSP(), dt.getMauSac(), dt.getSize(), dt.getKhuyenMai(), tien.format(dt.getGiaNhap()), tien.format(dt.getGiaBan()), dt.getSoLuong(), tien.format(dt.getSoLuongBan() * dt.getGiaNhap()), dt.getSoLuongBan(), tien.format((dt.getSoLuongBan() * dt.getGiaBan()))

            });
            tongTienSanPham += dt.getSoLuongBan() * dt.getGiaBan();
            tienNhap += dt.getSoLuongBan() * dt.getGiaNhap();
            loiNhuan = tienBan - tienNhap;
            textTienNhap.setText(String.valueOf(tien.format(tienNhap) + " VNĐ"));
            textLoiNhuan.setText(String.valueOf(tien.format(loiNhuan) + " VNĐ"));
            txtTienGiam.setText(tien.format(tongTienSanPham - tienBan) + " VNĐ");
            txtTongTienSP.setText(tien.format(tongTienSanPham) + " VNĐ");
            textTTBD.setText(String.valueOf(tien.format(tienBan) + " VNĐ"));
        }
        table_DS.setModel(tablemodel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object o = e.getSource();
        if (o.equals(rdQuy)) {
            upDateComboBox();
        } else if (o.equals(rdMonth)) {
            upDateComboBox();
        } else if (o.equals(rdNam)) {
            upDateComboBox();
        } else if (o.equals(rdBoth)) {
            upDateComboBox();
        } else if (o.equals(btnXem)) {
            xoaAllDataTable();
            if (rdQuy.isSelected()) {
                doanhThuTheoQuy();
            } else if (rdMonth.isSelected()) {
                doanhThuTheoThang();
            } else if (rdNam.isSelected()) {
                doanhThuTheoNam();
            } else if (rdBoth.isSelected()) {
                doanhThuThangNam();
            }

        } else if (o.equals(btnInThongKe)) {
            inThongKe();
        }
    }

    public void xoaAllDataTable() {
        tablemodel.addRow(new Object[]{});
        tablemodel = (DefaultTableModel) table_DS.getModel();
        tablemodel.getDataVector().removeAllElements();

    }

    public void upDateCBCa() {
        cboCa.addItem("Tất cả");
        cboCa.addItem(1);
        cboCa.addItem(2);
    }

    //	Phân quyền chức năng của người dùng
    public void phanQuyen() {
        try {
            if (!FrmDangNhap.TrangThaiDangNhapNhanVien && FrmDangNhap.TrangThaiDangNhapQuanLy) {
                rdQL.setSelected(true);

            } else if (FrmDangNhap.TrangThaiDangNhapNhanVien && !FrmDangNhap.TrangThaiDangNhapQuanLy) {
                rdNV.setSelected(true);
                String nhanVienDN = FrmDangNhap.usernameToGetNhanVien;
                List<NhanVien> listNV = daoNV.getAllNV();
                for (NhanVien n : listNV) {
                    if (n.getMaNhanvien().equalsIgnoreCase(nhanVienDN))
                        cboCa.setSelectedItem(n.getCaLamViec());
                }
                cboCa.setEnabled(false);

            }
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public JFreeChart bieuDoDoanhThu() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "KHÁCH HÀNG MUA NHỀU SẢN PHẨM NHẤT",
                null, "triệu VNĐ",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        TextTitle title = new TextTitle("KHÁCH HÀNG MUA NHỀU SẢN PHẨM NHẤT");
        title.setFont(new Font("Times New Roman", Font.BOLD, 20));
        barChart.setTitle(title);

        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // Tùy chỉnh trục x (CategoryAxis)
        CategoryAxis xAxis = plot.getDomainAxis();
        xAxis.setTickLabelFont(new Font("Times New Roman", Font.PLAIN, 13));
        xAxis.setTickLabelPaint(Color.BLACK);
        xAxis.setMaximumCategoryLabelLines(2);
        // Tùy chỉnh trục y (NumberAxis)
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setTickLabelFont(new Font("Times New Roman", Font.PLAIN, 13));
        yAxis.setLabel("Số tiền (triệu VNĐ)");
        yAxis.setTickLabelPaint(Color.BLACK);
        yAxis.setAxisLinePaint(Color.BLACK);

        renderer.setSeriesPaint(0, new Color(52, 155, 235));
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setMaximumBarWidth(0.05);


        plot.getRangeAxis().setLowerMargin(0.2);
        plot.getRangeAxis().setUpperMargin(0.2);


        plot.setBackgroundPaint(new Color(223, 229, 235));
        return barChart;
    }

    public CategoryDataset createDataset() {
        try {
            final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (int i = 1; i <= 5; i++) {
                tienMua = daoTKDTImpl.tongDoanhThuCuaKhachHang(i);
                String tenKH = daoTKDTImpl.topKhachHang(i);
                if (tienMua != 0) {
                    double formattedTienMua = Double.parseDouble(tienSo.format(tienMua)) / 1000000;
                    String label = tenKH + "\n" + tien.format(tienMua);
                    dataset.addValue(formattedTienMua, "Số tiền", label);
                }
            }
            return dataset;
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void capNhatBieuDo() {
        chartPanel.setChart(bieuDoDoanhThu());
        chartPanel.repaint();
    }

    public void inThongKe() {
        String hinhThucThongKe = "";
        if (rdQuy.isSelected()) {
            hinhThucThongKe = "QUÝ " + quy;
        } else if (rdMonth.isSelected()) {
            hinhThucThongKe = "THÁNG " + thang;
        } else if (rdNam.isSelected()) {
            hinhThucThongKe = "NĂM " + nam;
        } else if (rdBoth.isSelected()) {
            hinhThucThongKe = "THÁNG " + thang + " NĂM " + nam;
        }
        String lbl1 = lblTongTienBan.getText();
        String lbl2 = lblTienNhap.getText();
        String lbl3 = lblLoiNhuan.getText();
        String lbl4 = lblTongTienSP.getText();
        String lbl5 = lblTienKhuyenMai.getText();
        String lbl6 = "Ca: ";
        String tienBan = textTTBD.getText();
        String tienNhap = textTienNhap.getText();
        String tienLoi = textLoiNhuan.getText();
        String tienSP = txtTongTienSP.getText();
        String tienGiam = txtTienGiam.getText();
        String ca = cboCa.getSelectedItem().toString();
        frmInTK.setVisible(true);
        frmInTK.setDuLieuThongKe(lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, tienBan, tienNhap, tienLoi, tienSP, tienGiam, ca);
        frmInTK.taoBangThongKe(bangThongKe(tablemodel), 1);
        frmInTK.xoaAllDataTable();
        frmInTK.capNhatBangThongKe(tablemodel);
        frmInTK.tieuDe("THỐNG KÊ DOANH THU THEO " + hinhThucThongKe);
        Date currentDate = new Date();

        // Định dạng ngày
        SimpleDateFormat dateFormat = new SimpleDateFormat("'(Thống kê được in vào ngày' d 'tháng' M 'năm' y)");
        String formattedDate = dateFormat.format(currentDate);
        frmInTK.ngayIn(formattedDate);
        frmInTK.printThongKe();

    }

    public JTable bangThongKe(DefaultTableModel model) {


        String[] tb = new String[]{"STT", "Mã", "Tên", "Màu Sắc", "Size", "KM", "ĐG nhập", "ĐG bán", "Số lượng", "Tiền nhập", "SL bán", "Tiền bán"};

        model = new DefaultTableModel(tb, 0);
        JTable table_DS = new JTable(model);
        table_DS.setBackground(Color.WHITE);
        table_DS.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        table_DS.setBackground(new Color(224, 255, 255));
        table_DS.setForeground(new Color(0, 0, 0));

        return table_DS;
    }
}