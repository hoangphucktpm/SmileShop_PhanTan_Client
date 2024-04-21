package GUI;

import DAOTest.NhanVienDao;
import DAOTest.ThongTinCaNhanDao;
import DAOTest.impl.NhanVienImpl;
import DAOTest.impl.ThongTinCaNhanImpl;
import Entities.NhanVien;
import Entities.TaiKhoan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FrmThongTinCaNhan extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JPanel pnlTaiKhoan;
    private JLabel lblMaNV;
    private JTextField txtMa;
    private JTextField txtTen;
    private JTextField txtNgaySinh;
    private JTextField txtCCCD;
    private JTextField txtSDT;
    private JTextField txtEmail;
    private JTextField txtTrangThai;
    private JTextField txtCa;
    private JTextField txtGioiTinh;
    private JLabel lblHinhAnh;
    private JLabel lblTenTK;
    private JTextField txtTenTK;
    private JPasswordField txtMauKhau;
    private JTextField txtMatKhauMoi;
    private JLabel lblTieuDeTrang;
    private JTextField txtChucVu;
    private NhanVienDao dao = new NhanVienImpl();
    private ThongTinCaNhanDao ttDao = new ThongTinCaNhanImpl();
    private JButton btnCapNhat;
    private JRadioButton rdHienMatKhau;
    private JButton btnLuu;
    private JTextField txtmanv;
    private JButton btnAnh;
    private JLabel lblKhungHinh;
    private String folderName;

    boolean check = false;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmThongTinCaNhan frame = new FrmThongTinCaNhan("");
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
    public FrmThongTinCaNhan(String username) throws RemoteException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setSize(1347, 843);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel panel_TieuDe = new JPanel();
        panel_TieuDe.setBackground(new Color(0, 255, 255));
        panel_TieuDe.setBounds(0, 0, 1343, 41);
        contentPane.add(panel_TieuDe);
        panel_TieuDe.setLayout(null);
        lblTieuDeTrang = new JLabel("THÔNG TIN CÁ NHÂN");
        lblTieuDeTrang.setBounds(512, 12, 305, 25);
        panel_TieuDe.add(lblTieuDeTrang);
        lblTieuDeTrang.setBackground(new Color(0, 255, 255));
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setBackground(new Color(255, 255, 255));
        pnlThongTin.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pnlThongTin.setBounds(20, 61, 1283, 608);
        contentPane.add(pnlThongTin);
        pnlThongTin.setLayout(null);

        JPanel pnlThongTinCaNhan = new JPanel();
        pnlThongTinCaNhan.setBackground(new Color(255, 255, 255));
        pnlThongTinCaNhan.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
                "Th\u00F4ng tin c\u00E1 nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pnlThongTinCaNhan.setBounds(23, 21, 600, 565);
        pnlThongTin.add(pnlThongTinCaNhan);
        pnlThongTinCaNhan.setLayout(null);

        lblMaNV = new JLabel("Mã nhân viên");
        lblMaNV.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblMaNV.setBounds(25, 24, 109, 13);
        pnlThongTinCaNhan.add(lblMaNV);

        txtMa = new JTextField();
        txtMa.setEditable(false);
        txtMa.setBounds(175, 22, 393, 19);
        pnlThongTinCaNhan.add(txtMa);
        txtMa.setColumns(10);

        JLabel lblTenNV = new JLabel("Tên nhân viên");
        lblTenNV.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblTenNV.setBounds(25, 80, 109, 13);
        pnlThongTinCaNhan.add(lblTenNV);

        JLabel lblNgaySinh = new JLabel("Ngày sinh");
        lblNgaySinh.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNgaySinh.setBounds(25, 137, 109, 19);
        pnlThongTinCaNhan.add(lblNgaySinh);

        JLabel lblCCCD = new JLabel("CCCD");
        lblCCCD.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblCCCD.setBounds(25, 205, 109, 13);
        pnlThongTinCaNhan.add(lblCCCD);

        JLabel lblSDT = new JLabel("Số điện thoại");
        lblSDT.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblSDT.setBounds(25, 257, 109, 15);
        pnlThongTinCaNhan.add(lblSDT);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblEmail.setBounds(25, 324, 109, 13);
        pnlThongTinCaNhan.add(lblEmail);

        JLabel lblGioiTinh = new JLabel("Giới tính");
        lblGioiTinh.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblGioiTinh.setBounds(25, 388, 109, 13);
        pnlThongTinCaNhan.add(lblGioiTinh);

        JLabel lblTrangThai = new JLabel("Trạng thái");
        lblTrangThai.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblTrangThai.setBounds(25, 452, 109, 17);
        pnlThongTinCaNhan.add(lblTrangThai);

        JLabel lblCa = new JLabel("Ca làm việc");
        lblCa.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblCa.setBounds(25, 516, 109, 13);
        pnlThongTinCaNhan.add(lblCa);

        JLabel lblChucVu = new JLabel("Chức vụ");
        lblChucVu.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblChucVu.setBounds(337, 388, 81, 13);
        pnlThongTinCaNhan.add(lblChucVu);

        txtTen = new JTextField();
        txtTen.setColumns(10);
        txtTen.setEditable(false);
        txtTen.setBounds(175, 78, 393, 19);
        pnlThongTinCaNhan.add(txtTen);

        txtNgaySinh = new JTextField();
        txtNgaySinh.setEditable(false);
        txtNgaySinh.setColumns(10);
        txtNgaySinh.setBounds(175, 138, 393, 19);
        pnlThongTinCaNhan.add(txtNgaySinh);

        txtCCCD = new JTextField();
        txtCCCD.setEditable(false);
        txtCCCD.setColumns(10);
        txtCCCD.setBounds(175, 203, 393, 19);
        pnlThongTinCaNhan.add(txtCCCD);

        txtSDT = new JTextField();
        txtSDT.setColumns(10);
        txtSDT.setEditable(false);
        txtSDT.setBounds(175, 257, 393, 19);
        pnlThongTinCaNhan.add(txtSDT);

        txtEmail = new JTextField();
        txtEmail.setColumns(10);
        txtEmail.setEditable(false);
        txtEmail.setBounds(175, 322, 393, 19);
        pnlThongTinCaNhan.add(txtEmail);

        txtTrangThai = new JTextField();
        txtTrangThai.setEditable(false);
        txtTrangThai.setColumns(10);
        txtTrangThai.setBounds(175, 452, 393, 19);
        pnlThongTinCaNhan.add(txtTrangThai);

        txtCa = new JTextField();
        txtCa.setEditable(false);
        txtCa.setColumns(10);
        txtCa.setBounds(175, 514, 393, 19);
        pnlThongTinCaNhan.add(txtCa);

        txtGioiTinh = new JTextField();
        txtGioiTinh.setEditable(false);
        txtGioiTinh.setColumns(10);
        txtGioiTinh.setBounds(175, 386, 130, 19);
        pnlThongTinCaNhan.add(txtGioiTinh);

        txtChucVu = new JTextField();
        txtChucVu.setEditable(false);
        txtChucVu.setColumns(10);
        txtChucVu.setBounds(438, 386, 130, 19);
        pnlThongTinCaNhan.add(txtChucVu);

        pnlTaiKhoan = new JPanel();
        pnlTaiKhoan.setBorder(new TitledBorder(null, "Th\u00F4ng tin t\u00E0i kho\u1EA3n", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        pnlTaiKhoan.setBackground(new Color(255, 255, 255));
        pnlTaiKhoan.setBounds(705, 391, 545, 195);
        pnlThongTin.add(pnlTaiKhoan);
        pnlTaiKhoan.setLayout(null);

        lblTenTK = new JLabel("Tên đăng nhập");
        lblTenTK.setBounds(20, 28, 134, 18);
        lblTenTK.setFont(new Font("Times New Roman", Font.BOLD, 15));
        pnlTaiKhoan.add(lblTenTK);

        JLabel lblMK = new JLabel("Mật khẩu hiện tại");
        lblMK.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblMK.setBounds(20, 88, 123, 17);
        pnlTaiKhoan.add(lblMK);

        JLabel lblMKM = new JLabel("Mật khẩu mới");
        lblMKM.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblMKM.setBounds(20, 147, 109, 21);
        pnlTaiKhoan.add(lblMKM);

        rdHienMatKhau = new JRadioButton("Hiện mật khẩu");
        rdHienMatKhau.setBackground(new Color(255, 255, 255));
        rdHienMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        rdHienMatKhau.setBounds(385, 122, 123, 21);
        pnlTaiKhoan.add(rdHienMatKhau);

        txtTenTK = new JTextField();
        txtTenTK.setEditable(false);
        txtTenTK.setBounds(173, 29, 335, 19);
        pnlTaiKhoan.add(txtTenTK);
        txtTenTK.setColumns(10);

        txtMauKhau = new JPasswordField();
        txtMauKhau.setBounds(173, 86, 335, 19);
        txtMauKhau.setEditable(false);
        pnlTaiKhoan.add(txtMauKhau);

        txtMatKhauMoi = new JTextField();
        txtMatKhauMoi.setBounds(173, 149, 335, 19);
        txtMatKhauMoi.setEditable(false);
        pnlTaiKhoan.add(txtMatKhauMoi);

        lblHinhAnh = new JLabel("Hình ảnh");
        lblHinhAnh.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblHinhAnh.setBounds(672, 42, 109, 13);
        pnlThongTin.add(lblHinhAnh);

        btnAnh = new JButton("Chọn Ảnh");
        btnAnh.setBounds(929, 316, 150, 40);
        btnAnh.setBackground(Color.CYAN);
        btnAnh.setIcon(new ImageIcon("Anh\\chonanh.png"));
        pnlThongTin.add(btnAnh);

        lblKhungHinh = new JLabel(" ");
        lblKhungHinh.setOpaque(true);
        lblKhungHinh.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblKhungHinh.setBackground(Color.WHITE);
        lblKhungHinh.setBounds(840, 43, 337, 234);
        pnlThongTin.add(lblKhungHinh);

        btnCapNhat = new JButton("Cập nhật");
        btnCapNhat.setBackground(Color.CYAN);
        btnCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnCapNhat.setIcon(new ImageIcon("Anh\\sua.png"));
        btnCapNhat.setBounds(527, 710, 135, 51);
        contentPane.add(btnCapNhat);

        btnLuu = new JButton("Lưu");
        btnLuu.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnLuu.setBackground(Color.CYAN);
        btnLuu.setBounds(684, 710, 135, 51);
        btnLuu.setIcon(new ImageIcon("Anh\\luu.png"));
        contentPane.add(btnLuu);
        load(username);
        btnLuu.setEnabled(false);
        btnCapNhat.addActionListener(this);
        btnLuu.addActionListener(this);
        rdHienMatKhau.addActionListener(this);
        btnAnh.addActionListener(this);
        btnAnh.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnCapNhat)) {
            if (check == false) {
                check = true;
                moCapNhat(check);
            } else if (check == true) {
                check = false;
                btnLuu.setEnabled(false);
                btnCapNhat.setText("Cập nhật");
                btnCapNhat.setIcon(new ImageIcon("Anh\\sua.png"));
                moCapNhat(check);
            }

        }
        if (o.equals(btnLuu)) {
            try {
                sua();
                check = false;
                btnLuu.setEnabled(false);
                btnCapNhat.setText("Cập nhật");
                btnCapNhat.setIcon(new ImageIcon("Anh\\sua.png"));
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        if (rdHienMatKhau.isSelected()) {
            txtMauKhau.setEchoChar((char) 0); // Hiển thị mật khẩu
        } else {
            txtMauKhau.setEchoChar('\u25CF'); // Ẩn mật khẩu
        }
        if (o.equals(btnAnh)) {
            chonAnh();

        }
    }

    public void load(String username) {
        try {
            NhanVien nv = dao.getNVTHeoMa(username);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String ngaySinh = "";
            if (nv.getNgaySinh() != null) {
                ngaySinh += dateFormat.format(nv.getNgaySinh());
            }
            txtNgaySinh.setText(ngaySinh);
            txtMa.setText(nv.getMaNhanvien());
            txtTen.setText(nv.getTenNhanVien());

            txtCCCD.setText(nv.getCccd());
            txtSDT.setText(nv.getSdt());
            txtEmail.setText(nv.getEmail());
            String gioiTinhText = (nv.getGioiTinh() == 1) ? "Nam" : "Nữ";
            txtGioiTinh.setText(gioiTinhText);

            String chucvuText = (nv.getChucVu() == 1) ? "Quản Lý" : "Nhân Viên";
            txtChucVu.setText(chucvuText);

            String duongDanAnh = nv.getHinhAnh();
            String CaText = (nv.getCaLamViec() == 1) ? "Ca 1" : "Ca 2";
            txtCa.setText(CaText);

            String trangThaiText = (nv.getTrangThai() == 1) ? "Đang làm việc" : "Nghỉ việc";
            txtTrangThai.setText(trangThaiText);

            TaiKhoan tk = ttDao.loadTaiKhoan(username);

            txtTenTK.setText(tk.getTenTaiKhoan().getMaNhanvien());
            txtMauKhau.setText(tk.getMatKhau());
            String tenNV = nv.getTenNhanVien();

            if (duongDanAnh != null && !duongDanAnh.isEmpty()) {
                ImageIcon imageIcon = new ImageIcon(duongDanAnh);
                Image scaledImage = imageIcon.getImage().getScaledInstance(lblKhungHinh.getWidth(),
                        lblKhungHinh.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
                lblKhungHinh.setIcon(scaledImageIcon);
            } else {
                // Xử lý khi không có hình ảnh
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sua() throws ParseException {
        String img = folderName;
        String tenNV = txtTen.getText();
        String Sdt = txtSDT.getText();
        String Email = txtEmail.getText();
        // NhanVien nv = nv.validate();
        // if (nv != null) {
        try {
            NhanVienDao dao = new NhanVienImpl();
            boolean moi = ttDao.sua(tenNV, Sdt, Email, txtMa.getText(), folderName);

            if (moi == true) {
                if (txtMatKhauMoi.getText().length() != 0) {
                    String matkhau = txtMatKhauMoi.getText();
                    ttDao.suaMK(matkhau, txtMa.getText());
                    txtMauKhau.setText(matkhau);
                    txtMatKhauMoi.setText("");
                    JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công");
                    FrmManHinhChinh.capNhatHinh(folderName);
                }

            } else {
                FrmManHinhChinh.capNhatHinh(folderName);
                JOptionPane.showMessageDialog(this, "Cập nhật nhân viên không thành công!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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

//					System.out.println(folderName);
                return folderName; // Return the file path
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return folderName;
    }

    public void moCapNhat(boolean check) {
        txtTen.setEditable(check);
        txtSDT.setEditable(check);
        txtEmail.setEditable(check);
        txtMatKhauMoi.setEditable(check);
        btnLuu.setEnabled(check);
        btnCapNhat.setText("Hủy");
        btnCapNhat.setIcon(new ImageIcon("Anh\\huy.png"));
        btnAnh.setEnabled(check);
    }
}
