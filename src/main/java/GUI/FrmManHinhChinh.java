package GUI;

import DAOTest.NhanVienDao;
import DAOTest.impl.NhanVienImpl;
import Entities.NhanVien;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class FrmManHinhChinh extends JFrame implements ActionListener, MenuListener {

    private static final long serialVersionUID = 1L;
    private static final String ResizeType = null;
    private String currentUser;
    private JPanel contentPane;
    private JMenuBar menuBar_NhaCungCap;
    private JMenuBar menuBar_HoaDon;
    private JMenuBar menuBar_DangXuat;
    private FrmSanPham frmSP = new FrmSanPham();
    private FrmNhaCungCap frmTK = new FrmNhaCungCap();
    private FrmLapHoaDon frmLHĐ = new FrmLapHoaDon();
    private FrmTroGiup frmTG = new FrmTroGiup();
    private FrmXemHoaDon frmXHĐ = new FrmXemHoaDon();
    private FrmKhuyenMai frmKM = new FrmKhuyenMai();
    private static JMenu mnNhaCungCap;
    private static JMenuItem DangXuat;
    private JMenuBar menuBar_KhachHang;
    private FrmKhachHang frmKH = new FrmKhachHang();
    private static JMenu mnKhachHang;
    private FrmNhanVien frmNhanVien = new FrmNhanVien();
    static JMenu mnNhanVien;
    private FrmSanPham frmSanPham = new FrmSanPham();
    private static JMenu mnSanPham;
    private FrmThongTinCaNhan frmTTCN;
    private FrmDangNhap frmDangNhap = new FrmDangNhap();
    private static JMenu mnDangNhap;
    private JMenuBar menuBar_ThongKe;
    private FrmThongKeDoanhThu frmTKDT = new FrmThongKeDoanhThu();
    private FrmThongKeHoaDon frmTKHĐ = new FrmThongKeHoaDon();
    private FrmThongKeTinhTrangSP frmTKTTSP = new FrmThongKeTinhTrangSP();
    private JMenu mnDangXuat;
    private static JMenu mnTroGiup;
    private JMenuItem mntmThongKeHoaDonDaLap;
    private JMenuItem mntmThongKeTTSP;
    private static JButton btnThongTinCaNhan;
    private JMenuItem mntmDangXuat;
    JMenuItem mntmLapHoaDon;
    private JMenuItem mntmXemHoaDon;

    JMenu mnKhuyenMai;
    private JMenu mnThongKe;
    private static JMenuItem mntmThongKeDoanhThu;
    private JTabbedPane tabbedPane;
    private Label ID;
    private String tennv;

    private NhanVienDao dao = (NhanVienDao) Naming.lookup(URL + "NhanVienDao");
    private JTextField lblTenDangNhap;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private Label lblThongTinCaNhan;
    private Label lblTenShop;
    private JToolBar toolBar_1;
    private JMenuBar menuBar_HoaDon_1;
    private JMenu mnHoaDon;
    private JMenuBar menuBar_SanPham;
    private JMenuBar menuBar_NhanVien;
    private JMenuBar menuBar_KhuyenMai;
    private JToolBar toolBar;

private static final String URL = "rmi://172.20.10.5:6541/";


    public static void main(String[] args) {
        try {
            FrmManHinhChinh frame = new FrmManHinhChinh("");
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FrmManHinhChinh(String username) throws RemoteException, MalformedURLException, NotBoundException {
        setTitle("CỬA HÀNG THỜI TRANG SMILE");
        getContentPane().setForeground(new Color(0, 0, 0));
        getContentPane().setBackground(new Color(255, 255, 255));
        setBackground(new Color(255, 255, 255));
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 880);
        this.currentUser = username;
        Border border1 = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
        Border border2 = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.cyan);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setIconImage(new ImageIcon("Anh\\Logo.png").getImage());

        lblTenDangNhap = new JTextField();
        lblTenDangNhap.setBackground(new Color(0, 255, 255));
        lblTenDangNhap.setEditable(false);
        lblTenDangNhap.setBounds(28, 125, 145, 19);
        lblTenDangNhap.setFont(new Font("Times New Roman", Font.BOLD, 15));
        contentPane.add(lblTenDangNhap);
        lblTenDangNhap.setColumns(10);
        lblTenDangNhap.setBorder(null);
        lblTenDangNhap.setHorizontalAlignment(SwingConstants.CENTER);

        btnThongTinCaNhan = new JButton("");
        btnThongTinCaNhan.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, ActionEvent.ALT_MASK), "F12");
        btnThongTinCaNhan.getActionMap().put("F12", (Action) new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnThongTinCaNhan.doClick();
            }
        });
        btnThongTinCaNhan.setForeground(new Color(0, 0, 0));
        btnThongTinCaNhan.setBackground(new Color(0, 255, 255));

        btnThongTinCaNhan.setBounds(38, 35, 125, 88);
        contentPane.add(btnThongTinCaNhan);
        btnThongTinCaNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to execute when the button is clicked
                // For example, open a new window with personal information
            }
        });

        NhanVien nv = dao.getNVTHeoMa(username);
        lblTenDangNhap.setText(nv.getTenNhanVien());

        String duongDanAnh = nv.getHinhAnh(); // Giả sử đây là đường dẫn tới hình ảnh
        capNhatHinh(duongDanAnh);

        ID = new Label("ID: " + currentUser);
        ID.setFont(new Font("Times New Roman", Font.BOLD, 12));
        ID.setBackground(new Color(0, 255, 255));
        ID.setBounds(73, 150, 59, 21);
        getContentPane().add(ID);

        lblThongTinCaNhan = new Label("");
        lblThongTinCaNhan.setBackground(new Color(0, 255, 255));

        lblThongTinCaNhan.setBounds(0, 35, 200, 143);
        getContentPane().add(lblThongTinCaNhan);

        lblTenShop = new Label("SMILE SHOP");
        lblTenShop.setAlignment(Label.CENTER);
        lblTenShop.setBackground(new Color(0, 255, 255));
        lblTenShop.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblTenShop.setBounds(0, 0, 200, 43);
        getContentPane().add(lblTenShop);

        toolBar_1 = new JToolBar();
        toolBar_1.setOrientation(SwingConstants.VERTICAL);
        toolBar_1.setForeground(new Color(0, 0, 0));
        toolBar_1.setEnabled(false);
        toolBar_1.setFloatable(false);
        toolBar_1.setBackground(new Color(0, 255, 255));
        toolBar_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        toolBar_1.setBounds(0, 177, 200, 353);
        getContentPane().add(toolBar_1);

        menuBar_HoaDon_1 = new JMenuBar();
        menuBar_HoaDon_1.setBackground(new Color(0, 255, 255));

        toolBar_1.add(menuBar_HoaDon_1);

        mnHoaDon = new JMenu("Hóa Đơn");
        mnHoaDon.setBackground(new Color(0, 255, 255));
        mnHoaDon.setIcon(new ImageIcon("Anh\\hoadon (1).png"));
        mnHoaDon.setForeground(new Color(0, 0, 0));
        mnHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 15));
        menuBar_HoaDon_1.add(mnHoaDon);
        mnHoaDon.setPreferredSize(new Dimension(220, 50));
        mnHoaDon.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.black));
        menuBar_HoaDon_1.setBorder(border2);

        mntmLapHoaDon = new JMenuItem("Lập Hóa Đơn");
        mntmLapHoaDon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.ALT_MASK));
        buttonGroup.add(mntmLapHoaDon);
        mntmLapHoaDon.setHorizontalAlignment(SwingConstants.RIGHT);
        mntmLapHoaDon.setIcon(new ImageIcon("Anh\\hoadon1.png"));
        mntmLapHoaDon.setBackground(new Color(255, 255, 255));
        mntmLapHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 15));
        mntmLapHoaDon.setForeground(new Color(0, 0, 0));
        mnHoaDon.add(mntmLapHoaDon);

        mntmXemHoaDon = new JMenuItem("Xem Hóa Đơn");
        mntmXemHoaDon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.ALT_MASK));
        mntmXemHoaDon.setHorizontalAlignment(SwingConstants.RIGHT);
        mntmXemHoaDon.setIcon(new ImageIcon("Anh\\hoadon.png"));
        mntmXemHoaDon.setForeground(Color.BLACK);
        mntmXemHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 15));
        mntmXemHoaDon.setBackground(Color.WHITE);
        mnHoaDon.add(mntmXemHoaDon);

        menuBar_SanPham = new JMenuBar();
        menuBar_SanPham.setBackground(new Color(0, 255, 255));
        toolBar_1.add(menuBar_SanPham);
        menuBar_SanPham.setBorder(border1);

        mnSanPham = new JMenu("Sản Phẩm");
        mnSanPham.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.ALT_MASK), "F3");
        mnSanPham.getActionMap().put("F3", (Action) new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnSanPham.doClick();
            }
        });

        mnSanPham.setIcon(new ImageIcon("Anh\\sanpham.png"));
        mnSanPham.setBackground(new Color(0, 255, 255));
        mnSanPham.setForeground(new Color(0, 0, 0));
        mnSanPham.setFont(new Font("Times New Roman", Font.BOLD, 15));
        menuBar_SanPham.add(mnSanPham);
        mnSanPham.setPreferredSize(new Dimension(220, 50));
        mnSanPham.setBorder(BorderFactory.createLineBorder(Color.cyan, 1));

        menuBar_KhachHang = new JMenuBar();
        menuBar_KhachHang.setBackground(new Color(0, 255, 255));
        toolBar_1.add(menuBar_KhachHang);
        menuBar_KhachHang.setBorder(border1);

        mnKhachHang = new JMenu("Khách Hàng");
        mnKhachHang.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK), "F4");
        mnKhachHang.getActionMap().put("F4", (Action) new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnKhachHang.doClick();
            }
        });
        mnKhachHang.setIcon(new ImageIcon("Anh\\khachhang.png"));
        mnKhachHang.setBackground(new Color(0, 255, 255));
        mnKhachHang.setFont(new Font("Times New Roman", Font.BOLD, 15));
        mnKhachHang.setForeground(new Color(0, 0, 0));
        menuBar_KhachHang.add(mnKhachHang);
        mnKhachHang.setPreferredSize(new Dimension(220, 50));
        mnKhachHang.setBorder(BorderFactory.createLineBorder(Color.cyan, 1));

        menuBar_NhanVien = new JMenuBar();
        menuBar_NhanVien.setBackground(new Color(0, 255, 255));
        toolBar_1.add(menuBar_NhanVien);
        menuBar_NhanVien.setBorder(border1);

        mnNhanVien = new JMenu("Nhân Viên");
        mnNhanVien.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.ALT_MASK), "F5");
        mnNhanVien.getActionMap().put("F5", (Action) new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnNhanVien.doClick();
            }
        });
        mnNhanVien.setIcon(new ImageIcon("Anh\\nhanvien.png"));
        mnNhanVien.setBackground(new Color(0, 255, 255));
        mnNhanVien.setForeground(new Color(0, 0, 0));
        mnNhanVien.setFont(new Font("Times New Roman", Font.BOLD, 15));
        menuBar_NhanVien.add(mnNhanVien);
        mnNhanVien.setPreferredSize(new Dimension(220, 50));
        mnNhanVien.setBorder(BorderFactory.createLineBorder(Color.cyan, 1));

        menuBar_NhaCungCap = new JMenuBar();
        menuBar_NhaCungCap.setBackground(new Color(0, 255, 255));
        toolBar_1.add(menuBar_NhaCungCap);

        mnNhaCungCap = new JMenu("Nhà Cung Cấp");
        mnNhaCungCap.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, ActionEvent.ALT_MASK), "F6");
        mnNhaCungCap.getActionMap().put("F6", (Action) new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnNhaCungCap.doClick();
            }
        });
        mnNhaCungCap.setIcon(new ImageIcon("Anh\\nhacungcap.png"));
        mnNhaCungCap.setBackground(new Color(0, 255, 255));
        mnNhaCungCap.setForeground(new Color(0, 0, 0));
        mnNhaCungCap.setFont(new Font("Times New Roman", Font.BOLD, 15));
        menuBar_NhaCungCap.add(mnNhaCungCap);
        menuBar_NhaCungCap.setBorder(border1);

        mnNhaCungCap.setPreferredSize(new Dimension(220, 50));
        mnNhaCungCap.setBorder(BorderFactory.createLineBorder(Color.cyan, 1));

        menuBar_KhuyenMai = new JMenuBar();
        menuBar_KhuyenMai.setBackground(new Color(0, 255, 255));
        toolBar_1.add(menuBar_KhuyenMai);

        mnKhuyenMai = new JMenu("Khuyến Mãi");
        mnKhuyenMai.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, ActionEvent.ALT_MASK), "F7");
        mnKhuyenMai.getActionMap().put("F7", (Action) new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnKhuyenMai.doClick();
            }
        });
        mnKhuyenMai.setIcon(new ImageIcon("Anh\\kmai.png"));
        mnKhuyenMai.setBackground(new Color(0, 255, 255));
        mnKhuyenMai.setFont(new Font("Times New Roman", Font.BOLD, 15));
        mnKhuyenMai.setForeground(new Color(0, 0, 0));
        menuBar_KhuyenMai.add(mnKhuyenMai);

        menuBar_KhuyenMai.add(mnKhuyenMai);
        menuBar_KhuyenMai.setBorder(border1);
        mnKhuyenMai.setPreferredSize(new Dimension(220, 50));
        mnKhuyenMai.setBorder(BorderFactory.createLineBorder(Color.cyan, 1));

        Panel panel = new Panel();

        panel.setBackground(new Color(0, 255, 255));
        panel.setBounds(0, 177, 200, 666);
        getContentPane().add(panel);
        panel.setLayout(null);

        toolBar = new JToolBar();
        toolBar.setBounds(0, 490, 200, 176);
        panel.add(toolBar);
        toolBar.setFloatable(false);
        toolBar.setEnabled(false);
        toolBar.setBackground(new Color(0, 255, 255));
        toolBar.setOrientation(SwingConstants.VERTICAL);

        JMenuBar menuBar_ThongKe_1 = new JMenuBar();
        menuBar_ThongKe_1.setEnabled(false);
        menuBar_ThongKe_1.setBackground(new Color(0, 255, 255));
        toolBar.add(menuBar_ThongKe_1);

        mnThongKe = new JMenu("Thống Kê");
        mnThongKe.setIcon(new ImageIcon("Anh\\thongke.png"));
        mnThongKe.setBackground(new Color(0, 255, 255));
        mnThongKe.setForeground(new Color(0, 0, 0));
        mnThongKe.setFont(new Font("Times New Roman", Font.BOLD, 15));
        menuBar_ThongKe_1.add(mnThongKe);
        mnThongKe.setPreferredSize(new Dimension(220, 50));
        mnThongKe.setBorder(BorderFactory.createLineBorder(Color.cyan, 1));
        mnThongKe.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.black));
        menuBar_ThongKe_1.setBorder(border2);

        mntmThongKeDoanhThu = new JMenuItem("Doanh Thu");
        mntmThongKeDoanhThu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, ActionEvent.ALT_MASK));
        mntmThongKeDoanhThu.setIcon(new ImageIcon("Anh\\thogkedoanhthu.png"));
        mntmThongKeDoanhThu.setBackground(new Color(240, 240, 240));
        mntmThongKeDoanhThu.setForeground(new Color(0, 0, 0));
        mntmThongKeDoanhThu.setFont(new Font("Times New Roman", Font.BOLD, 15));
        mnThongKe.add(mntmThongKeDoanhThu);

        mntmThongKeHoaDonDaLap = new JMenuItem("Hóa Đơn Đã Lập");
        mntmThongKeHoaDonDaLap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, ActionEvent.ALT_MASK));
        mntmThongKeHoaDonDaLap.setIcon(new ImageIcon("Anh\\tkhoadon.png"));
        mntmThongKeHoaDonDaLap.setForeground(new Color(0, 0, 0));
        mntmThongKeHoaDonDaLap.setFont(new Font("Times New Roman", Font.BOLD, 15));
        mnThongKe.add(mntmThongKeHoaDonDaLap);

        mntmThongKeTTSP = new JMenuItem("Tình Trạng Sản Phẩm");
        mntmThongKeTTSP.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0));
        mntmThongKeTTSP.setIcon(new ImageIcon("Anh\\tksp.png"));
        mntmThongKeTTSP.setForeground(new Color(0, 0, 0));
        mntmThongKeTTSP.setFont(new Font("Times New Roman", Font.BOLD, 15));
        mnThongKe.add(mntmThongKeTTSP);

        JMenuBar menuBar_TroGiup = new JMenuBar();
        menuBar_TroGiup.setBackground(new Color(0, 255, 255));
        toolBar.add(menuBar_TroGiup);

        mnTroGiup = new JMenu("Trợ Giúp");
        mnTroGiup.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, ActionEvent.ALT_MASK), "F11");
        mnTroGiup.getActionMap().put("F11", (Action) new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnTroGiup.doClick();
            }
        });
        mnTroGiup.setIcon(new ImageIcon("Anh\\trogiup.png"));
        mnTroGiup.setBackground(new Color(0, 255, 255));
        mnTroGiup.setFont(new Font("Times New Roman", mnTroGiup.getFont().getStyle() & ~Font.ITALIC | Font.BOLD,
                mnTroGiup.getFont().getSize() + 3));
        mnTroGiup.setForeground(new Color(0, 0, 0));
        menuBar_TroGiup.add(mnTroGiup);
        mnTroGiup.setPreferredSize(new Dimension(220, 50));
        mnTroGiup.setBorder(BorderFactory.createLineBorder(Color.cyan, 1));
        mnTroGiup.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        menuBar_TroGiup.setBorder(border2);

        JMenuBar menuBar_DangXuat_1 = new JMenuBar();
        menuBar_DangXuat_1.setBackground(new Color(0, 255, 255));
        toolBar.add(menuBar_DangXuat_1);

        mnDangXuat = new JMenu("Đăng Xuất");
        mnDangXuat.setIcon(new ImageIcon("Anh\\dangxuat.png"));
        mnDangXuat.setBackground(new Color(0, 255, 255));
        mnDangXuat.setForeground(new Color(0, 0, 0));
        mnDangXuat.setFont(new Font("Times New Roman", Font.BOLD, 15));
        menuBar_DangXuat_1.add(mnDangXuat);
        mnDangXuat.setPreferredSize(new Dimension(220, 50));
        mnDangXuat.setBorder(BorderFactory.createLineBorder(Color.cyan, 1));
        menuBar_DangXuat_1.setBorder(border1);

        mntmDangXuat = new JMenuItem("Đăng xuất");
        mntmDangXuat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        mntmDangXuat.setHorizontalAlignment(SwingConstants.RIGHT);
        mntmDangXuat.setIcon(new ImageIcon("Anh\\icondangxuat.png"));
        mntmDangXuat.setBackground(new Color(255, 255, 255));
        mntmDangXuat.setFont(new Font("Times New Roman", Font.BOLD, 15));
        mntmDangXuat.setForeground(new Color(0, 0, 0));
        mnDangXuat.add(mntmDangXuat);
        mntmThongKeDoanhThu.addActionListener(this);
        mntmThongKeHoaDonDaLap.addActionListener(this);
        mntmThongKeTTSP.addActionListener(this);
        mntmDangXuat.addActionListener(this);

        tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.setBackground(Color.WHITE);
        tabbedPane.setBounds(193, -11, 1347, 854);
        contentPane.add(tabbedPane);

        mntmLapHoaDon.addActionListener(this);
        mntmXemHoaDon.addActionListener(this);
        mnNhaCungCap.addMenuListener(new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {
                // TODO Auto-generated method stub
                tabbedPane.remove(tabbedPane.getSelectedComponent());
                tabbedPane.add(frmTK.pnlThongTin);
                tabbedPane.setSelectedComponent(frmTK.pnlThongTin);
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void menuCanceled(MenuEvent e) {
                // TODO Auto-generated method stub

            }
        });

        mnKhachHang.addMenuListener(new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {
                // TODO Auto-generated method stub
                tabbedPane.remove(tabbedPane.getSelectedComponent());
                tabbedPane.add(frmKH.contentPane);
                tabbedPane.setSelectedComponent(frmKH.contentPane);
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void menuCanceled(MenuEvent e) {
                // TODO Auto-generated method stub

            }
        });

        mnNhanVien.addMenuListener(new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {
                // TODO Auto-generated method stub
                tabbedPane.remove(tabbedPane.getSelectedComponent());
                tabbedPane.add(frmNhanVien.getContentPane());
                tabbedPane.setSelectedComponent(frmNhanVien.getContentPane());
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                // TODO Auto-generated method stub

            }


            @Override
            public void menuCanceled(MenuEvent e) {
                // TODO Auto-generated method stub

            }
        });

        mnSanPham.addMenuListener(new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {
                // TODO Auto-generated method stub
                tabbedPane.remove(tabbedPane.getSelectedComponent());
                tabbedPane.add(frmSanPham.getContentPane());
                tabbedPane.setSelectedComponent(frmSanPham.getContentPane());
                frmSP.docDuLieu();
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void menuCanceled(MenuEvent e) {
                // TODO Auto-generated method stub

            }
        });

        mnTroGiup.addMenuListener(new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {
                // TODO Auto-generated method stub
                tabbedPane.remove(tabbedPane.getSelectedComponent());
                tabbedPane.add(frmTG.getContentPane());
                tabbedPane.setSelectedComponent(frmTG.getContentPane());
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void menuCanceled(MenuEvent e) {
                // TODO Auto-generated method stub

            }
        });

        mnKhuyenMai.addMenuListener(new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {
                // TODO Auto-generated method stub
                tabbedPane.remove(tabbedPane.getSelectedComponent());
                tabbedPane.add(frmKM.pnlThongTin);
                tabbedPane.setSelectedComponent(frmKM.pnlThongTin);
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void menuCanceled(MenuEvent e) {
                // TODO Auto-generated method stub

            }
        });
        btnThongTinCaNhan.addActionListener(this);

    }

    @Override
    public void menuSelected(MenuEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void menuDeselected(MenuEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void menuCanceled(MenuEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // TODO Auto-generated method stub
            Object o = e.getSource();
            if (o.equals(mntmLapHoaDon)) {
                try {

                } catch (Exception e2) {
                    // TODO: handle exception

                }
                tabbedPane.remove(tabbedPane.getSelectedComponent());
                tabbedPane.add(frmLHĐ.pnlThongTin);
                tabbedPane.setSelectedComponent(frmLHĐ.pnlThongTin);

            } else if (o.equals(mntmXemHoaDon)) {

                tabbedPane.remove(tabbedPane.getSelectedComponent());
                tabbedPane.add(frmXHĐ.pnlThongTin);
                tabbedPane.setSelectedComponent(frmXHĐ.pnlThongTin);
            } else if (o.equals(mntmThongKeDoanhThu)) {

                tabbedPane.remove(tabbedPane.getSelectedComponent());
                tabbedPane.add(frmTKDT.pnlThongTin);
                tabbedPane.setSelectedComponent(frmTKDT.pnlThongTin);
                frmTKDT.capNhatBieuDo();
            } else if (o.equals(mntmThongKeHoaDonDaLap)) {

                tabbedPane.remove(tabbedPane.getSelectedComponent());
                tabbedPane.add(frmTKHĐ.pnlThongTin);
                tabbedPane.setSelectedComponent(frmTKHĐ.pnlThongTin);
                frmTKHĐ.capNhatBieuDo();

            } else if (o.equals(mntmThongKeTTSP)) {

                tabbedPane.remove(tabbedPane.getSelectedComponent());
                tabbedPane.add(frmTKTTSP.pnlThongTin);
                tabbedPane.setSelectedComponent(frmTKTTSP.pnlThongTin);
                frmTKTTSP.capNhatBieuDo();
            } else if (o.equals(btnThongTinCaNhan)) {
                frmTTCN = new FrmThongTinCaNhan(currentUser);
                tabbedPane.remove(tabbedPane.getSelectedComponent());
                tabbedPane.add(frmTTCN.getContentPane());
                tabbedPane.setSelectedComponent(frmTTCN.getContentPane());

            } else if (o.equals(mntmDangXuat)) {
                int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận đăng xuất",
                        JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    this.setVisible(false);
                    try {
                        FrmDangNhap frmDN = new FrmDangNhap();
                        frmDN.setVisible(true);
                    } catch (RemoteException | MalformedURLException | NotBoundException e1) {
                        e1.printStackTrace();
                        // Handle the exception (e.g., show an error message to the user)
                    }
                }

            }
        } catch (RemoteException | MalformedURLException | NotBoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public static void capNhatHinh(String duongDanAnh) {
        if (duongDanAnh != null && !duongDanAnh.isEmpty()) {
            ImageIcon icon = new ImageIcon(duongDanAnh);
            Image image = icon.getImage(); // Lấy hình ảnh từ ImageIcon
            Image newImage = image.getScaledInstance(btnThongTinCaNhan.getWidth(), btnThongTinCaNhan.getHeight(),
                    Image.SCALE_SMOOTH);
            // Chỉnh kích thước hình ảnh để phù hợp với nút

            // Tạo một ImageIcon mới với hình ảnh đã được điều chỉnh kích thước
            ImageIcon newIcon = new ImageIcon(newImage);

            // Đặt Icon mới cho nút
            btnThongTinCaNhan.setIcon(newIcon);

        } else {
            // Xử lý khi không có hình ảnh sau đăng nhập
            // Có thể thiết lập ảnh mặc định hoặc thực hiện các xử lý khác ở đây
        }
    }
}
