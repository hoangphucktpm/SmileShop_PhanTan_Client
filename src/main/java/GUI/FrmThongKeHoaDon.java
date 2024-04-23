package GUI;

import DAOTest.NhanVienDao;
import DAOTest.ThongKeHoaDonDao;
import DAOTest.impl.NhanVienImpl;
import DAOTest.impl.ThongKeHoaDonImpl;
import Entities.NhanVien;
import Entities.ThongKeHoaDon;
import com.toedter.calendar.JDateChooser;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FrmThongKeHoaDon extends JFrame implements ActionListener {

    public static DefaultTableModel tablemodel = new DefaultTableModel();
    private JTable table_1;
    private static final long serialVersionUID = 1L;
    private JTextField txtTenNV;
    private JLabel lblTieuDeTrang;
    JPanel pnlThongTin;
    private JPanel pnlThongTinTK;
    private JLabel lblHHDLTT;
    private JLabel lblSPDBTT;
    private JLabel lblTTTT;
    private JRadioButton rdQL;
    private JRadioButton rdNV;
    private JLabel lblChucVu;
    private JPanel pnlHinhThuc;
    private JLabel lblDate;
    private JDateChooser txtChonNgay;
    private JLabel lblNV;
    private JComboBox cbNV;
    private JLabel lblTenNV;
    private JLabel lblCa;
    private JComboBox cbCa;
    private JPanel pnTable;
    private JButton btnXem;
    private JPanel pnlTieuDe;
    private JTextField textHDDL;
    private JTextField textSPDB;
    private JTextField textTT;
    private JPanel pnlBieuDo;
    public ChartPanel chartPanel;
    private int day;
    private int month;
    private int year;

    private JButton btnInThongKe;
    private FrmXuatThongKe frmInTK = new FrmXuatThongKe();

    private NhanVienDao daoNV = (NhanVienDao) Naming.lookup(URL + "NhanVienDao");
    private ThongKeHoaDonDao daoImpl = (ThongKeHoaDonDao) Naming.lookup(URL + "ThongKeHoaDonDao");
private static final String URL = "rmi://192.168.1.15:6541/";

    public static void main(String[] args) throws MalformedURLException, NotBoundException {
        try {
            FrmThongKeHoaDon frm = new FrmThongKeHoaDon();

            frm.setVisible(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public FrmThongKeHoaDon() throws RemoteException, MalformedURLException, NotBoundException {

        pnlThongTin = new JPanel();
        getContentPane().setBackground(new Color(129, 250, 243));
        getContentPane().setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1347, 843);
        setLocationRelativeTo(null);
        setResizable(false);

        lblTieuDeTrang = new JLabel("THỐNG KÊ HÓA ĐƠN ĐÃ LẬP");
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        lblTieuDeTrang.setBounds(512, 12, 305, 25);
        lblTieuDeTrang.setBounds(0, 0, 1343, 41);
        pnlThongTin.add(lblTieuDeTrang);

        pnlThongTin.setBackground(new Color(255, 255, 255));
        pnlThongTin.setBounds(0, 0, 1333, 806);
        getContentPane().add(pnlThongTin);
        pnlThongTin.setLayout(null);

        pnlThongTinTK = new JPanel();
        pnlThongTinTK.setBackground(new Color(255, 255, 255));
        pnlThongTinTK.setBounds(20, 52, 353, 227);
        pnlThongTin.add(pnlThongTinTK);
        javax.swing.border.Border southborder = BorderFactory.createLineBorder(Color.black);
        TitledBorder southTitleBorder2 = new TitledBorder(southborder, "Thông tin thống kê");
        southTitleBorder2.setTitleColor(Color.black);
        pnlThongTinTK.setBorder(southTitleBorder2);
        pnlThongTin.add(pnlThongTinTK);
        pnlThongTinTK.setLayout(null);

        lblHHDLTT = new JLabel("Số hóa đơn đã lập:");
        lblHHDLTT.setBounds(37, 51, 133, 15);
        pnlThongTinTK.add(lblHHDLTT);
        lblHHDLTT.setHorizontalAlignment(SwingConstants.LEFT);
        lblHHDLTT.setFont(new Font("Times New Roman", Font.BOLD, 14));

        textHDDL = new JTextField();
        textHDDL.setBounds(173, 46, 123, 24);
        pnlThongTinTK.add(textHDDL);
        textHDDL.setEditable(false);
        textHDDL.setHorizontalAlignment(SwingConstants.LEFT);
        textHDDL.setForeground(new Color(0, 0, 0));
        textHDDL.setFont(new Font("Times New Roman", Font.BOLD, 20));
        textHDDL.setColumns(10);
        textHDDL.setBackground(new Color(255, 255, 255));

        lblSPDBTT = new JLabel("Số sản phẩm đã bán:");
        lblSPDBTT.setBounds(37, 103, 133, 15);
        pnlThongTinTK.add(lblSPDBTT);
        lblSPDBTT.setHorizontalAlignment(SwingConstants.LEFT);
        lblSPDBTT.setFont(new Font("Times New Roman", Font.BOLD, 14));

        textSPDB = new JTextField();
        textSPDB.setBounds(173, 98, 123, 24);
        pnlThongTinTK.add(textSPDB);
        textSPDB.setEditable(false);
        textSPDB.setHorizontalAlignment(SwingConstants.LEFT);
        textSPDB.setForeground(new Color(0, 0, 0));
        textSPDB.setFont(new Font("Times New Roman", Font.BOLD, 20));
        textSPDB.setColumns(10);
        textSPDB.setBackground(new Color(255, 255, 255));

        lblTTTT = new JLabel("Tổng tiền bán:\r\n");
        lblTTTT.setBounds(37, 154, 133, 15);
        pnlThongTinTK.add(lblTTTT);
        lblTTTT.setHorizontalAlignment(SwingConstants.LEFT);
        lblTTTT.setFont(new Font("Times New Roman", Font.BOLD, 14));

        textTT = new JTextField();
        textTT.setBounds(173, 149, 123, 24);
        pnlThongTinTK.add(textTT);
        textTT.setEditable(false);
        textTT.setHorizontalAlignment(SwingConstants.LEFT);
        textTT.setForeground(new Color(0, 0, 0));
        textTT.setFont(new Font("Times New Roman", Font.BOLD, 16));
        textTT.setColumns(10);
        textTT.setBackground(new Color(255, 255, 255));

        rdQL = new JRadioButton("Quản lý");
        rdQL.setEnabled(false);
        rdQL.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        rdQL.setBackground(new Color(255, 255, 255));
        rdQL.setBounds(133, 286, 119, 33);
        pnlThongTin.add(rdQL);

        rdNV = new JRadioButton("Nhân viên");
        rdNV.setEnabled(false);
        rdNV.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        rdNV.setBackground(new Color(255, 255, 255));
        rdNV.setBounds(254, 286, 119, 33);
        pnlThongTin.add(rdNV);

        lblChucVu = new JLabel("Chức vụ:");
        lblChucVu.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblChucVu.setBounds(30, 289, 85, 27);
        pnlThongTin.add(lblChucVu);

        pnlHinhThuc = new JPanel();
        pnlHinhThuc.setBorder(
                new TitledBorder(new LineBorder(new Color(0, 0, 0)), "H\u00ECnh th\u1EE9c th\u1ED1ng k\u00EA",
                        TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pnlHinhThuc.setBackground(new Color(255, 255, 255));
        pnlHinhThuc.setBounds(20, 326, 1280, 81);
        pnlThongTin.add(pnlHinhThuc);
        pnlHinhThuc.setLayout(null);

        lblDate = new JLabel("Ngày:");
        lblDate.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblDate.setBounds(29, 32, 45, 27);
        pnlHinhThuc.add(lblDate);

        txtChonNgay = new JDateChooser();
        txtChonNgay.setBounds(74, 32, 115, 27);
        txtChonNgay.setForeground(new Color(0, 0, 0));
        txtChonNgay.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txtChonNgay.setLocale(new Locale("vi", "VN"));
        txtChonNgay.setDateFormatString("dd/MM/yyyy");
        txtChonNgay.setDate(new Date(System.currentTimeMillis()));

        pnlHinhThuc.add(txtChonNgay);

        lblNV = new JLabel("Mã nhân viên:");
        lblNV.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblNV.setHorizontalAlignment(SwingConstants.LEFT);
        lblNV.setBounds(298, 33, 99, 27);
        pnlHinhThuc.add(lblNV);

        cbNV = new JComboBox();
        cbNV.setBounds(399, 32, 131, 27);
        pnlHinhThuc.add(cbNV);

        lblTenNV = new JLabel("Tên nhân viên:");
        lblTenNV.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblTenNV.setBounds(678, 32, 106, 27);
        pnlHinhThuc.add(lblTenNV);

        txtTenNV = new JTextField();
        txtTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtTenNV.setEditable(false);
        txtTenNV.setBounds(785, 32, 162, 27);
        pnlHinhThuc.add(txtTenNV);
        txtTenNV.setColumns(10);

        lblCa = new JLabel("Ca làm việc:");
        lblCa.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblCa.setBounds(1083, 32, 92, 27);
        pnlHinhThuc.add(lblCa);

        cbCa = new JComboBox();
        cbCa.setBounds(1166, 33, 92, 27);
        pnlHinhThuc.add(cbCa);

        pnTable = new JPanel();
        pnTable.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch:", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        pnTable.setBackground(new Color(255, 255, 255));
        pnTable.setBounds(20, 418, 1280, 316);
        pnlThongTin.add(pnTable);
        pnTable.setLayout(null);

        JScrollPane scrDSHD;

        table_1 = bangThongKe(tablemodel);

        table_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        table_1.setBackground(new Color(224, 255, 255));
        table_1.setForeground(new Color(0, 0, 0));
        getContentPane().add(scrDSHD = new JScrollPane(table_1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
        scrDSHD.setBounds(10, 21, 1270, 326);
        pnTable.add(scrDSHD);
        scrDSHD.setPreferredSize(new Dimension(0, 250));

        btnXem = new JButton("Xem báo cáo");
        btnXem.setIcon(new ImageIcon("Anh\\xembaocao.png"));
        btnXem.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnXem.setBackground(new Color(0, 255, 255));
        btnXem.setBounds(644, 745, 188, 50);
        pnlThongTin.add(btnXem);

        pnlTieuDe = new JPanel();
        pnlTieuDe.setBackground(new Color(0, 255, 255));
        pnlTieuDe.setBounds(0, 0, 1338, 38);
        pnlThongTin.add(pnlTieuDe);

        pnlBieuDo = new JPanel();
        javax.swing.border.Border borderBieuDo = BorderFactory.createLineBorder(Color.black);
        TitledBorder borderBieuDo2 = new TitledBorder(borderBieuDo, "Top nhân viên");
        borderBieuDo2.setTitleColor(Color.black);
        pnlBieuDo.setBorder(borderBieuDo2);
        pnlBieuDo.setLayout(null);
        pnlBieuDo.setBackground(Color.WHITE);
        pnlBieuDo.setBounds(417, 52, 883, 227);
        pnlThongTin.add(pnlBieuDo);
        chartPanel = new ChartPanel(bieuDo());

        chartPanel.setMaximumDrawWidth(2000);
        chartPanel.setMaximumDrawHeight(1000);
        chartPanel.setBackground(new Color(255, 255, 255));
        chartPanel.setSize(863, 193);
        chartPanel.setLocation(10, 23);

        chartPanel.setPreferredSize(new Dimension(560, 367));

        pnlBieuDo.add(chartPanel);

        btnInThongKe = new JButton("Xuất báo cáo");
        btnInThongKe.setIcon(new ImageIcon("Anh\\xuatTK.png"));
        btnInThongKe.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnInThongKe.setBackground(Color.CYAN);
        btnInThongKe.setBounds(446, 745, 188, 50);
        pnlThongTin.add(btnInThongKe);
        chartPanel.repaint();

        btnInThongKe.setEnabled(false);

        upDateComboBox();
        phanQuyen();

        cbNV.addActionListener(this);
        btnXem.addActionListener(this);
        btnInThongKe.addActionListener(this);
        ;
    }



    public void xoaAllDataTable() {
        tablemodel.addRow(new Object[]{});
        tablemodel = (DefaultTableModel) table_1.getModel();
        tablemodel.getDataVector().removeAllElements();

    }

    //	Cập nhật các comboBox
    public void upDateComboBox() {
        try {
            cbCa.removeAllItems();
            cbNV.removeAllItems();
            cbCa.addItem("Tất cả");
            cbCa.addItem(1);
            cbCa.addItem(2);

            List<NhanVien> listNV = daoNV.getAllNV();
            if (listNV != null && !listNV.isEmpty()) {
                cbNV.addItem("Tất cả");
                for (NhanVien n : listNV) {
                    if (!n.getMaNhanvien().contains("QL")) {
                        cbNV.addItem(n.getMaNhanvien());
                    }

                }
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //	Hàm lấy tên nhân viên
    private void chonTenNhanVien() {
        try {

            List<NhanVien> listNV = daoNV.getAllNV();
            String ma = (String) cbNV.getSelectedItem();
            for (NhanVien n : listNV) {
                if (ma.equalsIgnoreCase(n.getMaNhanvien()))
                    txtTenNV.setText(n.getTenNhanVien());
                else if (ma.equalsIgnoreCase("Tất cả"))
                    txtTenNV.setText("");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void hoaDonTheoNgayVaCa() throws ParseException {
        try {
            int n = 1;
            int ca = 0;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngayBan = dateFormat.format(txtChonNgay.getDate());
            Date ngayBan1 = dateFormat.parse(ngayBan);
            Date ngayBansql = new Date(ngayBan1.getTime());

            Calendar ngayCld = Calendar.getInstance();
            ngayCld.setTime(txtChonNgay.getDate());

            day = ngayCld.get(Calendar.DATE);
            month = ngayCld.get(Calendar.MONTH) + 1;
            year = ngayCld.get(Calendar.YEAR);

            if (cbCa.getSelectedItem().equals("Tất cả"))
                ca = 0;
            else if ((int) cbCa.getSelectedItem() == 1)
                ca = 1;
            else if ((int) cbCa.getSelectedItem() == 2)
                ca = 2;
            if (cbNV.getSelectedItem().equals("Tất cả")) {
                btnInThongKe.setEnabled(true);
                List<ThongKeHoaDon> list = daoImpl.getHoaDonTheoNgayVaCa(day, month, year, ca);
                if (list != null && !list.isEmpty())
                    docDuLieu(list, n);
                else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn");
                    btnInThongKe.setEnabled(false);
                    textHDDL.setText(String.valueOf(0));
                    textSPDB.setText(String.valueOf(0));
                    textTT.setText(String.valueOf(0));
                }

            } else {
                btnInThongKe.setEnabled(true);
                String maNV = (String) cbNV.getSelectedItem();
                List<ThongKeHoaDon> listNV = daoImpl.getHoaDonTheoNV(day, month, year, maNV, ca);
                if (listNV != null && !listNV.isEmpty())
                    docDuLieu(listNV, n);
                else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn");
                    btnInThongKe.setEnabled(false);
                    textHDDL.setText(String.valueOf(0));
                    textSPDB.setText(String.valueOf(0));
                    textTT.setText(String.valueOf(0));
                }

            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void docDuLieu(List<ThongKeHoaDon> list, int n) {
        DecimalFormat tien = new DecimalFormat("#,##0 VNĐ");
        int soSP = 0;
        double sum = 0;
        for (ThongKeHoaDon hd : list) {
            tablemodel.addRow(new Object[]{n++, hd.getMaHoaDon(), hd.getMaNhanVien(), hd.getCaLamViec(),
                    hd.getSoLuongSP(), hd.getNgayLap(), hd.getLoaiKH(), tien.format(hd.getDoanhThu())

            });
            soSP += hd.getSoLuongSP();
            sum += hd.getDoanhThu();

        }
        table_1.setModel(tablemodel);
        textHDDL.setText(String.valueOf(n - 1));
        textSPDB.setText(String.valueOf(soSP));
        textTT.setText(String.valueOf(tien.format(sum)));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object o = e.getSource();
        chonTenNhanVien();
        if (o.equals(btnXem)) {
            xoaAllDataTable();
            try {
                hoaDonTheoNgayVaCa();

            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (o.equals(btnInThongKe)) {
            inThongKe();
        }
    }

    //	Phân quyền chức năng người dùng
    public void phanQuyen() {
        try {
            if (!FrmDangNhap.TrangThaiDangNhapNhanVien && FrmDangNhap.TrangThaiDangNhapQuanLy) {
                rdQL.setSelected(true);

            } else if (FrmDangNhap.TrangThaiDangNhapNhanVien && !FrmDangNhap.TrangThaiDangNhapQuanLy) {
                rdNV.setSelected(true);
                String nhanVienDN = FrmDangNhap.taiKhoan.getTenTaiKhoan().getMaNhanvien();
                List<NhanVien> listNV = daoNV.getAllNV();
                cbNV.setSelectedItem(nhanVienDN);
                for (NhanVien n : listNV) {
                    if (nhanVienDN.equalsIgnoreCase(n.getMaNhanvien())) {
                        txtTenNV.setText(n.getTenNhanVien());
                        cbCa.setSelectedItem(n.getCaLamViec());
                    }

                }
                cbNV.setEnabled(false);
                cbCa.setEnabled(false);

            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public JFreeChart bieuDo() {
        JFreeChart barChart = ChartFactory.createBarChart("NHÂN VIÊN LẬP ĐƯỢC NHIỀU HÓA ĐƠN NHẤT TRONG THÁNG", null,
                "Số hóa đơn", createDataset(), PlotOrientation.VERTICAL, false, false, false);
        TextTitle title = new TextTitle("NHÂN VIÊN LẬP ĐƯỢC NHIỀU HÓA ĐƠN NHẤT TRONG THÁNG");
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
                int tongHoaDon = daoImpl.tongHoaDon(i);
                String nhanVienTop = daoImpl.nhanVienTop(i);
                if (tongHoaDon != 0) {
                    dataset.addValue(tongHoaDon, "Hóa Đơn", nhanVienTop + "\n" + tongHoaDon + " hóa đơn");
                }
            }
            return dataset;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void capNhatBieuDo() {
        chartPanel.setChart(bieuDo());
        chartPanel.repaint();
    }

    public JTable bangThongKe(DefaultTableModel model) {

        String[] tb = new String[]{"STT", "Mã Hóa Đơn", "Mã Nhân viên", "Ca Làm Việc", "Số lượng", "Ngày Lập",
                "Loại Khách", "Doanh thu"};

        model = new DefaultTableModel(tb, 0);
        JTable table_DS = new JTable(model);
        table_DS.setBackground(Color.WHITE);
        table_DS.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        table_DS.setBackground(new Color(224, 255, 255));
        table_DS.setForeground(new Color(0, 0, 0));

        return table_DS;
    }

    public void inThongKe() {
        String hinhThucThongKe = "";
        hinhThucThongKe = "NGÀY " + day + " THÁNG " + month + " NĂM " + year;
        String lbl1 = lblHHDLTT.getText();
        String lbl2 = lblSPDBTT.getText();
        String lbl3 = lblTTTT.getText();
        String lbl4 = "Nhân viên: ";
        String lbl5 = "Họ tên: ";
        String lbl6 = "Ca: ";
        String TonghoaDon = textHDDL.getText();
        String soSP = textSPDB.getText();
        String tongTien = textTT.getText();
        String nhanVien = cbNV.getSelectedItem().toString();
        String tenNhanVien = txtTenNV.getText();
        String ca = cbCa.getSelectedItem().toString();
        frmInTK.setVisible(true);
        frmInTK.setDuLieuThongKe(lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, TonghoaDon, soSP, tongTien, nhanVien, tenNhanVien,
                ca);
        frmInTK.taoBangThongKe(bangThongKe(tablemodel), 2);
        frmInTK.xoaAllDataTable();
        frmInTK.capNhatBangThongKe(tablemodel);
        frmInTK.tieuDe("THỐNG KÊ HÓA ĐƠN ĐÃ LẬP VÀO " + hinhThucThongKe);
        Date currentDate = new Date();

        // Định dạng ngày
        SimpleDateFormat dateFormat = new SimpleDateFormat("'(Thống kê được in vào ngày' d 'tháng' M 'năm' y)");
        String formattedDate = dateFormat.format(currentDate);
        frmInTK.ngayIn(formattedDate);
        frmInTK.printThongKe();

    }
}