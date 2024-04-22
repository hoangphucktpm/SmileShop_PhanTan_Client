package GUI;

import DAOTest.ThongKeHoaDonDao;
import DAOTest.TinhTrangSanPhamDao;
import DAOTest.impl.TinhTrangSanPhamImpl;
import Entities.ThongKeSanPham;
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
import java.util.List;
import java.util.*;

public class FrmThongKeTinhTrangSP extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    public static DefaultTableModel tablemodel = new DefaultTableModel();
    private JTable table_DSSP;
    private JLabel lblTieuDeTrang;
    JPanel pnlThongTin;
    private JPanel pnlThongTinThongKe;
    private JLabel lblTSP;
    private JLabel lblTL;
    private JRadioButton rdQL;
    private JRadioButton rdNV;
    private JLabel lblChucVu;
    private JPanel pnlHinhThuc;
    private JLabel lblDate;
    private JPanel pnTable;
    private JButton btnXem;
    private JDateChooser txtChonNgay;
    private JRadioButton rdDaBan;
    private JRadioButton rdSapHet;
    private JRadioButton rdDaHet;
    private JRadioButton rdMoiNhap;
    private JRadioButton rdConLai;
    private JRadioButton rdQuaLau;
    private JPanel pnlTieuDe;

    private JTextField textHHDL;
    private JTextField textSPDB;
    private JPanel pnlBieuDo;
    private ChartPanel chartPanel;

    private TinhTrangSanPhamDao daoImpl = (TinhTrangSanPhamDao) Naming.lookup(URL + "TinhTrangSanPhamDao");



    DecimalFormat tien = new DecimalFormat("#,##0");
    private JButton btnInThongKe;
    String hinhThucThongKe;

    FrmXuatThongKe frmInTK = new FrmXuatThongKe();

    private static final String URL = "rmi://192.168.1.33:6541/";


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmThongKeTinhTrangSP frame = new FrmThongKeTinhTrangSP();
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
    public FrmThongKeTinhTrangSP() throws RemoteException, MalformedURLException, NotBoundException {
        pnlThongTin = new JPanel();
        getContentPane().setBackground(new Color(129, 250, 243));
        getContentPane().setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1347, 843);
        setLocationRelativeTo(null);
        setResizable(false);
        pnlThongTin.setLayout(null);

        pnlThongTin.setBackground(new Color(255, 255, 255));
        pnlThongTin.setBounds(0, 0, 1333, 806);
        getContentPane().add(pnlThongTin);

        pnlThongTinThongKe = new JPanel();
        pnlThongTinThongKe.setBounds(20, 72, 419, 198);
        pnlThongTinThongKe.setBackground(new Color(255, 255, 255));
        pnlThongTin.add(pnlThongTinThongKe);
        javax.swing.border.Border southborder = BorderFactory.createLineBorder(Color.black);
        TitledBorder southTitleBorder2 = new TitledBorder(southborder, "Thông tin thống kê");
        southTitleBorder2.setTitleColor(Color.black);
        pnlThongTinThongKe.setBorder(southTitleBorder2);
        pnlThongTin.add(pnlThongTinThongKe);
        pnlThongTinThongKe.setLayout(null);

        lblTSP = new JLabel("Tổng sản phẩm:");
        lblTSP.setBounds(23, 69, 159, 15);
        pnlThongTinThongKe.add(lblTSP);
        lblTSP.setHorizontalAlignment(SwingConstants.LEFT);
        lblTSP.setFont(new Font("Tahoma", Font.BOLD, 12));

        textHHDL = new JTextField();
        textHHDL.setBounds(212, 65, 133, 24);
        pnlThongTinThongKe.add(textHHDL);
        textHHDL.setEditable(false);
        textHHDL.setBackground(new Color(255, 255, 255));
        textHHDL.setHorizontalAlignment(SwingConstants.LEFT);
        textHHDL.setForeground(new Color(0, 0, 0));
        textHHDL.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textHHDL.setColumns(10);
        textHHDL.setBorder(new LineBorder(new Color(0, 0, 0)));

        lblTL = new JLabel("Tổng loại sản phẩm:\r\n");
        lblTL.setBounds(23, 122, 133, 15);
        pnlThongTinThongKe.add(lblTL);
        lblTL.setHorizontalAlignment(SwingConstants.LEFT);
        lblTL.setFont(new Font("Tahoma", Font.BOLD, 12));

        textSPDB = new JTextField();
        textSPDB.setBounds(212, 118, 133, 24);
        pnlThongTinThongKe.add(textSPDB);
        textSPDB.setEditable(false);
        textSPDB.setHorizontalAlignment(SwingConstants.LEFT);
        textSPDB.setForeground(new Color(0, 0, 0));
        textSPDB.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textSPDB.setColumns(10);
        textSPDB.setBorder(new LineBorder(new Color(0, 0, 0)));
        textSPDB.setBackground(new Color(255, 255, 255));

        rdQL = new JRadioButton("Quản lý");
        rdQL.setBounds(123, 269, 119, 33);
        rdQL.setEnabled(false);
        rdQL.setFont(new Font("Tahoma", Font.PLAIN, 12));
        rdQL.setBackground(new Color(255, 255, 255));
        pnlThongTin.add(rdQL);

        rdNV = new JRadioButton("Nhân viên");
        rdNV.setBounds(244, 269, 119, 33);
        rdNV.setEnabled(false);
        rdNV.setFont(new Font("Tahoma", Font.PLAIN, 12));
        rdNV.setBackground(new Color(255, 255, 255));
        pnlThongTin.add(rdNV);

        lblChucVu = new JLabel("Chức vụ:");
        lblChucVu.setBounds(20, 272, 85, 27);
        lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 12));
        pnlThongTin.add(lblChucVu);

        pnlHinhThuc = new JPanel();
        pnlHinhThuc.setBounds(20, 309, 1282, 92);
        pnlHinhThuc.setBorder(
                new TitledBorder(new LineBorder(new Color(0, 0, 0)), "H\u00ECnh th\u1EE9c th\u1ED1ng k\u00EA",
                        TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pnlHinhThuc.setBackground(new Color(255, 255, 255));
        pnlThongTin.add(pnlHinhThuc);
        pnlHinhThuc.setLayout(null);

        lblDate = new JLabel("Ngày:");
        lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblDate.setBounds(10, 28, 66, 27);
        pnlHinhThuc.add(lblDate);

        txtChonNgay = new JDateChooser();
        txtChonNgay.setBounds(69, 28, 115, 27);
        txtChonNgay.setForeground(new Color(0, 0, 0));
        txtChonNgay.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txtChonNgay.setLocale(new Locale("vi", "VN"));
        txtChonNgay.setDateFormatString("dd/MM/yyyy");
        txtChonNgay.setDate(new Date(System.currentTimeMillis()));

        pnlHinhThuc.add(txtChonNgay);

        rdDaBan = new JRadioButton("Đã bán");
        rdDaBan.setBackground(new Color(255, 255, 255));
        rdDaBan.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdDaBan.setBounds(230, 28, 115, 27);
        pnlHinhThuc.add(rdDaBan);

        rdSapHet = new JRadioButton("Sắp hết hàng");
        rdSapHet.setBackground(new Color(255, 255, 255));
        rdSapHet.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdSapHet.setBounds(230, 58, 115, 27);
        pnlHinhThuc.add(rdSapHet);

        rdDaHet = new JRadioButton("Đã hết hàng");
        rdDaHet.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdDaHet.setBackground(new Color(255, 255, 255));
        rdDaHet.setBounds(420, 28, 115, 27);
        pnlHinhThuc.add(rdDaHet);

        rdMoiNhap = new JRadioButton("Mới nhập");
        rdMoiNhap.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdMoiNhap.setBackground(new Color(255, 255, 255));
        rdMoiNhap.setBounds(420, 58, 115, 27);
        pnlHinhThuc.add(rdMoiNhap);

        rdConLai = new JRadioButton("Còn lại");
        rdConLai.setBackground(new Color(255, 255, 255));
        rdConLai.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdConLai.setBounds(610, 28, 115, 27);
        pnlHinhThuc.add(rdConLai);

        rdQuaLau = new JRadioButton("Tồn kho quá lâu");
        rdQuaLau.setBackground(new Color(255, 255, 255));
        rdQuaLau.setFont(new Font("Tahoma", Font.BOLD, 12));
        rdQuaLau.setBounds(800, 28, 146, 27);
        pnlHinhThuc.add(rdQuaLau);

        pnTable = new JPanel();
        pnTable.setBounds(20, 412, 1282, 322);
        pnTable.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch:", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        pnTable.setBackground(new Color(255, 255, 255));
        pnlThongTin.add(pnTable);
        pnTable.setLayout(null);

        JScrollPane scrDSHD;
        table_DSSP = bangThongKe(tablemodel);

        table_DSSP.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        table_DSSP.setBackground(new Color(224, 255, 255));
        table_DSSP.setForeground(new Color(0, 0, 0));
        table_DSSP.getColumnModel().getColumn(0).setPreferredWidth(40);
        table_DSSP.getColumnModel().getColumn(1).setPreferredWidth(40);
        table_DSSP.getColumnModel().getColumn(2).setPreferredWidth(140);
        table_DSSP.getColumnModel().getColumn(3).setPreferredWidth(80);
        table_DSSP.getColumnModel().getColumn(4).setPreferredWidth(50);
        table_DSSP.getColumnModel().getColumn(5).setPreferredWidth(50);
        table_DSSP.getColumnModel().getColumn(6).setPreferredWidth(80);
        table_DSSP.getColumnModel().getColumn(8).setPreferredWidth(40);

        getContentPane().add(scrDSHD = new JScrollPane(table_DSSP, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
        scrDSHD.setBounds(10, 21, 1262, 291);
        pnTable.add(scrDSHD);
        scrDSHD.setPreferredSize(new Dimension(0, 250));

        btnXem = new JButton("Xem báo cáo");
        btnXem.setIcon(new ImageIcon("Anh\\xembaocao.png"));
        btnXem.setBounds(660, 745, 188, 50);
        btnXem.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnXem.setBackground(new Color(0, 255, 255));
        pnlThongTin.add(btnXem);

        pnlTieuDe = new JPanel();
        pnlTieuDe.setBounds(0, 0, 1365, 38);
        pnlTieuDe.setBackground(new Color(0, 255, 255));
        pnlThongTin.add(pnlTieuDe);
        pnlTieuDe.setLayout(null);

        lblTieuDeTrang = new JLabel("THỐNG KÊ TÌNH TRẠNG SẢN PHẨM");
        lblTieuDeTrang.setBounds(468, 13, 517, 25);
        pnlTieuDe.add(lblTieuDeTrang);
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));

        pnlBieuDo = new JPanel();
        javax.swing.border.Border borderBieuDo = BorderFactory.createLineBorder(Color.black);
        TitledBorder borderBieuDo2 = new TitledBorder(borderBieuDo, "Top sản phẩm");
        borderBieuDo2.setTitleColor(Color.black);
        pnlBieuDo.setBorder(borderBieuDo2);
        pnlBieuDo.setLayout(null);
        pnlBieuDo.setBackground(Color.WHITE);
        pnlBieuDo.setBounds(449, 52, 851, 227);
        pnlThongTin.add(pnlBieuDo);
        chartPanel = new ChartPanel(bieuDo());
        chartPanel.setMaximumDrawWidth(2000);
        chartPanel.setMaximumDrawHeight(1000);
        chartPanel.setBackground(new Color(255, 255, 255));
        chartPanel.setSize(831, 193);
        chartPanel.setLocation(10, 23);

        chartPanel.setPreferredSize(new Dimension(560, 367));
        pnlBieuDo.add(chartPanel);

        phanQuyen();
        ButtonGroup gr = new ButtonGroup();
        gr.add(rdDaBan);
        gr.add(rdDaHet);
        gr.add(rdConLai);
        gr.add(rdQuaLau);
        gr.add(rdSapHet);
        gr.add(rdMoiNhap);

        btnInThongKe = new JButton("Xuất báo cáo");
        btnInThongKe.setIcon(new ImageIcon("Anh\\xuatTK.png"));
        btnInThongKe.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnInThongKe.setBackground(Color.CYAN);
        btnInThongKe.setBounds(462, 745, 188, 50);
        pnlThongTin.add(btnInThongKe);

        btnXem.addActionListener(this);
        btnInThongKe.addActionListener(this);
        btnInThongKe.setEnabled(false);

    }

    public void docDuLieuHetHang() {
        try {
            int d = 1;
            xoaAllDataTable();
            List<ThongKeSanPham> list = daoImpl.getSPOUT();
            String soLuongLoaiSP;
            HashSet<String> lsp = new HashSet<>();

            int tongSanPham = 0;
            int tongLoai = 0;

            if (list == null || list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có sản phẩm nào hết hàng.");
                btnInThongKe.setEnabled(false);
                textSPDB.setText(String.valueOf(0));
                textHHDL.setText(String.valueOf(0));
            } else {
                for (ThongKeSanPham x : list) {
                    soLuongLoaiSP = x.getLoai();
                    lsp.add(soLuongLoaiSP);

                    tongSanPham++;
                    tablemodel.addRow(new Object[]{d++, x.getMaSP(), x.getTenSP(), x.getLoai(), x.getSoLuongNhap(),
                            x.getNgayNhap(), tien.format(x.getGiaNhap()), tien.format(x.getGiaBan()), x.getMauSac(),
                            x.getKichThuoc()});
                }
                int soLuongLoai = lsp.size();
                hinhThucThongKe = "THỐNG KÊ NHỮNG SẢN PHẨM ĐÃ HẾT HÀNG";
                btnInThongKe.setEnabled(true);
                textSPDB.setText(String.valueOf(soLuongLoai));
                textHHDL.setText(String.valueOf(tongSanPham));
                lblTSP.setText("Tổng sản phẩm:");
                table_DSSP.setModel(tablemodel);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //	Tìm theo sản phẩm còn hàng
    public void docDuLieuConHang() {
        try {
            List<ThongKeSanPham> list = daoImpl.getSPRE();
            if (list == null || list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tất cả sản phẩm đều hết.");
                btnInThongKe.setEnabled(false);
                textSPDB.setText(String.valueOf(0));
                textHHDL.setText(String.valueOf(0));
            } else {
                layDS(list);
                hinhThucThongKe = "THỐNG KÊ NHỮNG SẢN PHẨM CÒN HÀNG";
                btnInThongKe.setEnabled(true);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //	Tìm theo sản phẩm sắp hết hàng
    public void docDuLieuSapHet() {
        try {
            List<ThongKeSanPham> list = daoImpl.getALMOUT();
            if (list == null || list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tất cả sản phẩm đều trong tình trạng tốt.");
                btnInThongKe.setEnabled(false);
                textSPDB.setText(String.valueOf(0));
                textHHDL.setText(String.valueOf(0));
            } else {
                layDS(list);
                hinhThucThongKe = "THỐNG KÊ NHỮNG SẢN PHẨM SẮP HẾT HÀNG";
                btnInThongKe.setEnabled(true);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //	Tìm theo sản phẩm bán ế
    public void docDuLieuOld() {
        try {
            List<ThongKeSanPham> list = daoImpl.getOld();
            if (list == null || list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tất cả sản phẩm đều trong tình trạng tốt.");
                btnInThongKe.setEnabled(false);
                textSPDB.setText(String.valueOf(0));
                textHHDL.setText(String.valueOf(0));
            } else {
                layDS(list);
                hinhThucThongKe = "THỐNG KÊ NHỮNG SẢN PHẨM TỒN KHO QUÁ LÂU";
                btnInThongKe.setEnabled(true);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //	Tìm theo sản phẩm mới nhập theo ngày
    public void docDuLieuMoiNhap() throws ParseException {
        try {
            int d = 1;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngayNhap = dateFormat.format(txtChonNgay.getDate());
            Date ngayNhap1 = dateFormat.parse(ngayNhap);
            Date ngayNhapsql = new Date(ngayNhap1.getTime());

            int day;
            int month;
            int year;
            Calendar ngayCld = Calendar.getInstance();
            ngayCld.setTime(txtChonNgay.getDate());
            day = ngayCld.get(Calendar.DATE);
            month = ngayCld.get(Calendar.MONTH) + 1;
            year = ngayCld.get(Calendar.YEAR);
            hinhThucThongKe = "THỐNG KÊ NHỮNG SẢN PHẨM MỚI NHẬP NGÀY " + day + " THÁNG " + month + " NĂM " + year;

            List<ThongKeSanPham> list = daoImpl.getNewAdd(ngayNhapsql);
            if (list == null || list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có sản phẩm nào được nhập vào ngày " + ngayNhap);
                btnInThongKe.setEnabled(false);
                textSPDB.setText(String.valueOf(0));
                textHHDL.setText(String.valueOf(0));
            } else {
                layDS(list);
                btnInThongKe.setEnabled(true);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public double tinhGiaBan(double giaNhap) {

        double m = 0;

        m = giaNhap * 2.5;

        return m;
    }

    public void xoaAllDataTable() {
        tablemodel.addRow(new Object[]{});
        tablemodel = (DefaultTableModel) table_DSSP.getModel();
        tablemodel.getDataVector().removeAllElements();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object o = e.getSource();
        if (o.equals(btnXem)) {

            if (rdDaHet.isSelected()) {
                xoaAllDataTable();
                docDuLieuHetHang();
            } else if (rdConLai.isSelected()) {
                xoaAllDataTable();
                docDuLieuConHang();
            } else if (rdSapHet.isSelected()) {
                xoaAllDataTable();
                docDuLieuSapHet();
            } else if (rdMoiNhap.isSelected()) {
                xoaAllDataTable();
                try {
                    docDuLieuMoiNhap();
                } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else if (rdQuaLau.isSelected()) {
                xoaAllDataTable();
                docDuLieuOld();
            } else if (rdDaBan.isSelected()) {
                xoaAllDataTable();
                try {
                    sanPhamDaBan();
                } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        } else if (o.equals(btnInThongKe)) {
            inThongKe();
        }
    }

    //	lấy danh sách các sản phẩm còn hàng, sắp hết, mới nhập
    public void layDS(List<ThongKeSanPham> list) {
        int d = 1;
        Set<String> uniqueProducts = new HashSet<>();
        Set<String> lsp = new HashSet<>();

        int tongSanPham = 0;

        for (ThongKeSanPham x : list) {
            // Create a unique key for each product based on maSP and loai
            String uniqueKey = x.getMaSP() + "|" + x.getLoai();

            // Check if this key has already been processed
            if (!uniqueProducts.contains(uniqueKey)) {
                uniqueProducts.add(uniqueKey); // Add to set to mark this product as processed

                lsp.add(x.getLoai());

                tongSanPham += x.getSoLuongNhap();
                tablemodel.addRow(new Object[]{d++, x.getMaSP(), x.getTenSP(), x.getLoai(), x.getSoLuongNhap(),
                        x.getNgayNhap(), tien.format(x.getGiaNhap()), tien.format(x.getGiaBan()), x.getMauSac(),
                        x.getKichThuoc()});
            }
        }

        int soLuongLoai = lsp.size();
        textSPDB.setText(String.valueOf(soLuongLoai));
        textHHDL.setText(String.valueOf(tongSanPham));
        lblTSP.setText("Tổng số lượng sản phẩm:");
        // Assume there's some missing code here to update the GUI or a table display
    }

    //	Lấy danh sách sản phẩm đã bán theo ngày
    public void sanPhamDaBan() throws ParseException {
        try {
            int d = 1;
            xoaAllDataTable();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngayBan = dateFormat.format(txtChonNgay.getDate());
            Date ngayBan1 = dateFormat.parse(ngayBan);
            Date ngayBansql = new Date(ngayBan1.getTime());

            Calendar ngayCld = Calendar.getInstance();
            ngayCld.setTime(txtChonNgay.getDate());

            int day = ngayCld.get(Calendar.DATE);
            int month = ngayCld.get(Calendar.MONTH) + 1;
            int year = ngayCld.get(Calendar.YEAR);

            List<ThongKeSanPham> list = daoImpl.getSold(day, month, year);
            String soLuongLoaiSP;
            HashSet<String> lsp = new HashSet<>();

            int tongSanPham = 0;
            int tongLoai = 0;

            Set<String> uniqueProducts = new HashSet<>();

            hinhThucThongKe = "THỐNG KÊ NHỮNG SẢN PHẨM ĐƯỢC BÁN VÀO NGÀY " + day + " THÁNG " + month + " NĂM " + year;

            if (list == null || list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có sản phẩm nào được bán vào ngày " + ngayBan);
                btnInThongKe.setEnabled(false);
                textSPDB.setText(String.valueOf(0));
                textHHDL.setText(String.valueOf(0));
            } else {
                for (ThongKeSanPham x : list) {
                    String uniqueKey = x.getMaSP() + "|" + x.getLoai();
                    if (!uniqueProducts.contains(uniqueKey)) {
                        uniqueProducts.add(uniqueKey);
                        soLuongLoaiSP = x.getLoai();
                        lsp.add(soLuongLoaiSP);
                        int soLuongBan = daoImpl.soLuongBan(x.getMaSP(), day, month, year);
                        tongSanPham += soLuongBan;
                        tablemodel.addRow(new Object[]{d++, x.getMaSP(), x.getTenSP(), x.getLoai(), x.getSoLuongNhap(),
                                x.getNgayNhap(), tien.format(x.getGiaNhap()), tien.format(x.getGiaBan()), x.getMauSac(),
                                x.getKichThuoc(), soLuongBan});
                    }
                }
                int soLuongLoai = lsp.size();
                btnInThongKe.setEnabled(true);
                textSPDB.setText(String.valueOf(soLuongLoai));
                textHHDL.setText(String.valueOf(tongSanPham));
                lblTSP.setText("Tổng số lượng sản phẩm:");
                table_DSSP.setModel(tablemodel);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //	Phân quyền chức năng người dùng
    public void phanQuyen() {
        if (!FrmDangNhap.TrangThaiDangNhapNhanVien && FrmDangNhap.TrangThaiDangNhapQuanLy) {
            rdQL.setSelected(true);
            rdSapHet.setEnabled(false);
            rdMoiNhap.setEnabled(false);
        } else if (FrmDangNhap.TrangThaiDangNhapNhanVien && !FrmDangNhap.TrangThaiDangNhapQuanLy) {
            rdNV.setSelected(true);
            rdDaHet.setEnabled(false);
            rdConLai.setEnabled(false);
            rdQuaLau.setEnabled(false);

        }
    }

    public JFreeChart bieuDo() {
        JFreeChart barChart = ChartFactory.createBarChart("TOP SẢN PHẨM BÁN CHẠY NHẤT TRONG THÁNG", null, null,
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        TextTitle title = new TextTitle("TOP SẢN PHẨM BÁN CHẠY NHẤT TRONG THÁNG");
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
                double tongHoaDon = daoImpl.tongTienBan(i);
                String sanPhamTops = daoImpl.sanPhamTop(i);
                if (tongHoaDon != 0) {
                    dataset.addValue(tongHoaDon / 1000000, "Số tiền", sanPhamTops + "\n" + tien.format(tongHoaDon));
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

        String[] tb = new String[]{"STT", "Mã", "Tên sản phẩm", "Loại", "Số lượng", "Ngày nhập", "Giá nhập",
                "Giá bán", "Màu sắc", "Size", "SL bán"};

        model = new DefaultTableModel(tb, 0);
        JTable table_DSSP = new JTable(model);
        table_DSSP.setBackground(Color.WHITE);
        table_DSSP.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        table_DSSP.setBackground(new Color(224, 255, 255));
        table_DSSP.setForeground(new Color(0, 0, 0));

        return table_DSSP;
    }

    public void inThongKe() {

        String lbl1 = lblTSP.getText();
        String lbl2 = lblTL.getText();
        String lbl3 = "";
        String lbl4 = "";
        String lbl5 = "";
        String lbl6 = "";
        String tongLoai = textSPDB.getText();
        String tongSanPham = textHHDL.getText();
        String tienLoi = "";
        String tienSP = "";
        String tienGiam = "";
        frmInTK.setVisible(true);
        frmInTK.setDuLieuThongKe(lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, tongSanPham, tongLoai, tienLoi, tienSP, tienGiam,
                "");

        frmInTK.taoBangThongKe(bangThongKe(tablemodel), 3);
        frmInTK.xoaAllDataTable();
        frmInTK.capNhatBangThongKe(tablemodel);
        frmInTK.tieuDe(hinhThucThongKe);
        Date currentDate = new Date();

        // Định dạng ngày
        SimpleDateFormat dateFormat = new SimpleDateFormat("'(Thống kê được in vào ngày' d 'tháng' M 'năm' y)");
        String formattedDate = dateFormat.format(currentDate);
        frmInTK.ngayIn(formattedDate);
        frmInTK.printThongKe();

    }
}