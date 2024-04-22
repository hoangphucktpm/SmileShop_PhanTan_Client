package GUI;

import DAOTest.ThongTinCaNhanDao;
import DAOTest.impl.ThongTinCaNhanImpl;
import xuly.SendEmailSMTP;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class FrmQuenMatKhau extends JFrame implements ActionListener {

    JPanel contentPane;
    private JTextField txtUserName;
    private JButton btnQuenMK;
    private JLabel lblTieuDe;
    private JTextField txtMail;
    private JButton btnQMK;
    private JTextField yourPasswordField;
    private JButton btnQuayLai;

    public String email = "";
    public String user = "";
    public String newPass = matKhauMoi();
    SendEmailSMTP sendMail = new SendEmailSMTP();
    ThongTinCaNhanDao dao = (ThongTinCaNhanDao) Naming.lookup(URL + "ThongTinCaNhanDao");

    private static final String URL = "rmi://HOANGPHUC:6541/";



    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmQuenMatKhau frame = new FrmQuenMatKhau();
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
    public FrmQuenMatKhau() throws RemoteException, MalformedURLException, NotBoundException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 400);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setIconImage(new ImageIcon("Anh\\Logo.png").getImage());

        btnQuayLai = new JButton("");
        btnQuayLai.setBackground(new Color(255, 255, 255));
        btnQuayLai.setIcon(new ImageIcon("Anh\\quaylai.png"));
        btnQuayLai.setBounds(10, 10, 85, 30);
        btnQuayLai.setBorderPainted(false);
        contentPane.add(btnQuayLai);

        JLabel lblUserName = new JLabel("USERNAME\r\n");
        lblUserName.setBackground(new Color(240, 240, 240));
        lblUserName.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblUserName.setBounds(449, 55, 337, 35);
        contentPane.add(lblUserName);

        txtUserName = new JTextField();
        txtUserName.setBounds(449, 100, 268, 30);
        contentPane.add(txtUserName);
        txtUserName.setColumns(10);

        JLabel lblMail = new JLabel("EMAIL");
        lblMail.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblMail.setBounds(449, 140, 160, 35);
        contentPane.add(lblMail);

        AbstractAction actionQMK = new AbstractAction("Cấp lại mật khẩu") {
            public void actionPerformed(ActionEvent e) {
            }
        };

        btnQuenMK = new JButton(actionQMK);
        btnQuenMK.setIcon(new ImageIcon("Anh\\door_1828377.png"));
        btnQuenMK.setBackground(Color.GREEN);
        btnQuenMK.setBackground(new Color(0, 255, 0));
        btnQuenMK.setFont(new Font("Times New Roman", Font.BOLD, 20));
        getRootPane().setDefaultButton(btnQuenMK);
        btnQuenMK.setBounds(449, 248, 268, 35);
        contentPane.add(btnQuenMK);

        lblTieuDe = new JLabel("QUÊN MẬT KHẨU");
        lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblTieuDe.setBounds(451, 10, 325, 35);
        contentPane.add(lblTieuDe);

        JLabel lblHinh = new JLabel("New label");
        lblHinh.setIcon(new ImageIcon("Anh\\Anhdn.png"));
        lblHinh.setBounds(-284, -152, 689, 641);
        contentPane.add(lblHinh);

        txtMail = new JTextField();
        txtMail.setColumns(10);
        txtMail.setBounds(449, 185, 268, 30);
        contentPane.add(txtMail);

        btnQuenMK.addActionListener(this);
        btnQuayLai.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnQuenMK)) {
            if (valiData()) {
                JDialog loadingDialog = new JDialog();
                loadingDialog.setTitle("Vui lòng chờ...");
                loadingDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                loadingDialog.setSize(500, 60);
                loadingDialog.setLayout(new BorderLayout());
                loadingDialog.setLocationRelativeTo(null);

                JProgressBar progressBar = new JProgressBar();
                progressBar.setMinimum(1);
                progressBar.setMaximum(100);
                progressBar.setStringPainted(true);
                progressBar.setForeground(Color.RED);
                loadingDialog.add(progressBar, BorderLayout.CENTER);

                new Thread(() -> {
                    for (int i = 1; i <= 100; i++) {
                        try {
                            Thread.sleep(70);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        final int progress = i;
                        SwingUtilities.invokeLater(() -> {
                            progressBar.setValue(progress);
                            progressBar.setString(progress + "%");
                        });
                    }

                    boolean emailSent = sendMail.sendMail(email, user, newPass);

                    SwingUtilities.invokeLater(() -> {
                        try {
                            loadingDialog.dispose();
                            if (emailSent) {
                                dao.suaMK(newPass, user);
                                FrmDangNhap frmDangNhap = new FrmDangNhap();
                                frmDangNhap.setVisible(true);
                                frmDangNhap.setLocationRelativeTo(null);
                                setVisible(false);
                                JOptionPane.showMessageDialog(null, "Thay đổi mật khẩu thành công. Hãy kiểm tra Email của bạn.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Lỗi khi gửi Email.");
                            }
                        } catch (RemoteException | MalformedURLException | NotBoundException ex) {
                            ex.printStackTrace();
                        }
                    });
                }).start();

                loadingDialog.setVisible(true);
            }


        } else if (o.equals(btnQuayLai)) {
//            FrmDangNhap frmDangNhap = new FrmDangNhap();
            try {
                FrmDangNhap frmDangNhap = new FrmDangNhap();
                frmDangNhap.setVisible(true);
                frmDangNhap.setLocationRelativeTo(null);
                setVisible(false);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            } catch (NotBoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public boolean valiData() {
        try {
            if (txtUserName.getText().trim().isEmpty()) {
                ShowErrorField("Tên đăng nhập không được rỗng", txtUserName);
                return false;
            } else if (dao.tenNV(txtUserName.getText()).isBlank() || dao.tenNV(txtUserName.getText()).isEmpty() || dao.tenNV(txtUserName.getText()).equalsIgnoreCase("")) {
                ShowErrorField("Nhân viên không tồn tại", txtUserName);
                System.out.println("Tên " + dao.tenNV(txtUserName.getText()));
                return false;
            } else {
                user = txtUserName.getText().trim();
            }


            if (txtMail.getText().trim().isEmpty()) {
                ShowErrorField("Email không được rỗng", txtMail);
                return false;
            } else if (!isValidEmail(txtMail.getText().trim())) {
                ShowErrorField("Vui lòng nhập đúng địa chỉ email hợp lệ", txtMail);
                return false;
            } else if (!txtMail.getText().equalsIgnoreCase(dao.mailNhanVien(user))) {
                ShowErrorField("Mail không phải của nhân viên này.", txtMail);
                return false;
            } else {
                email = txtMail.getText().trim();
            }
            // Nếu tất cả ràng buộc được thỏa mãn, bạn có thể trả về email hoặc mật khẩu hoặc cả hai tùy theo yêu cầu của bạn.
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void ShowErrorField(String string, JTextField textField2) {
        JOptionPane.showMessageDialog(textField2, string);

    }

    private boolean isValidEmail(String email) {
        // Sử dụng biểu thức chính quy hợp lệ để kiểm tra email.
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    //	random mật khẩu mới
    public String matKhauMoi() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int index = (int) (Math.random() * characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }

}