package GUI;

import DAOTest.NhaCungCapDao;
import DAOTest.impl.NhaCungCapImpl;
import Entities.NhaCungCap;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FrmNhaCungCap extends JFrame implements ActionListener, MouseListener, DocumentListener, KeyListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultTableModel tablemodel;
    private JTable table_1;
    private JLabel lblTieuDeTrang;
    static JPanel pnlThongTin;
    private JPanel pnlThongTinNCC;
    private JPanel pnTable;
    private JTextField txtMaNCC;
    private JTextField txtTenNCC;
    private JTextField txtSdt;
    private JTextField txtE;
    private JTextField txtDiaChi;
    private JPanel pnlChucNang;
    private TitledBorder southTitleBorder2;
    private JLabel lblMaNCC;
    private JLabel lblTenNCC;
    private JLabel lblSDT;
    private JLabel lblEmail;
    private JLabel lblaCh;
    private JLabel lblTrngThi;
    private JRadioButton rdHopTac;
    private JRadioButton rdKhong;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnReset;
    private JComboBox comboBox;
    private JLabel lblTK;
    private JLabel lblTim;
    private JRadioButton rdMNCC;
    private JRadioButton rdTenNCC;
    private JRadioButton rdSDT;
    private JRadioButton rdE;
    private JRadioButton rdStatus;
    private JButton btnTim;
    private JButton btnResetTim;
    private List<String> listMa = new ArrayList<>();
    private List<String> listNCC = new ArrayList<>();
    private List<String> listTimNCC = new ArrayList<>();

    public static JComboBox cmbNCC;

    private DefaultComboBoxModel cboModeMa = new DefaultComboBoxModel();
    private DefaultComboBoxModel cboModeNCC = new DefaultComboBoxModel();
    private DefaultComboBoxModel cboModeTimNCC = new DefaultComboBoxModel();

    private NhaCungCap listN = new NhaCungCap();
    private NhaCungCapDao nccDao = (NhaCungCapDao) Naming.lookup(URL + "NhaCungCapDao");;
    private JButton btnLuu;

    private boolean chkThem = false;
    private boolean chkSua = false;
    private boolean chkTim = false;
    private JPanel pnlTieuDe;
    private ButtonGroup gr2;
    private JLabel txtBaoLoi;

    private JTextField txtComBoBox;
    private JLabel txtBaoLoiTimKiem;
    private ButtonGroup gr1;

    private static final String URL = "rmi://192.168.1.15:6541/";



    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmNhaCungCap frame = new FrmNhaCungCap();
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
    public FrmNhaCungCap() throws RemoteException, MalformedURLException, NotBoundException {
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

        pnlThongTinNCC = new JPanel();
        pnlThongTinNCC.setBackground(new Color(255, 255, 255));
        pnlThongTinNCC.setBounds(20, 48, 1286, 253);
        pnlThongTin.add(pnlThongTinNCC);
        javax.swing.border.Border southborder = BorderFactory.createLineBorder(Color.black);
        southTitleBorder2 = new TitledBorder(southborder, "Thiết lập thông tin nhà cung cấp");
        southTitleBorder2.setTitleColor(Color.black);
        pnlThongTinNCC.setBorder(southTitleBorder2);
        pnlThongTin.add(pnlThongTinNCC);
        pnlThongTinNCC.setLayout(null);

        lblMaNCC = new JLabel("Mã nhà cung cấp:");
        lblMaNCC.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMaNCC.setHorizontalAlignment(SwingConstants.LEFT);
        lblMaNCC.setBounds(20, 22, 135, 20);
        pnlThongTinNCC.add(lblMaNCC);

        txtMaNCC = new JTextField(deFaultID());
        txtMaNCC.setEditable(false);
        txtMaNCC.setBounds(177, 22, 771, 20);
        pnlThongTinNCC.add(txtMaNCC);
        txtMaNCC.setColumns(10);

        lblTenNCC = new JLabel("Tên nhà cung cấp:");
        lblTenNCC.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTenNCC.setBounds(20, 52, 135, 20);
        pnlThongTinNCC.add(lblTenNCC);

        txtTenNCC = new JTextField();
        txtTenNCC.setBounds(177, 53, 771, 20);
        pnlThongTinNCC.add(txtTenNCC);
        txtTenNCC.setColumns(10);

        lblSDT = new JLabel("Số điện thoại:");
        lblSDT.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSDT.setBounds(20, 82, 114, 28);
        pnlThongTinNCC.add(lblSDT);

        txtSdt = new JTextField();
        txtSdt.setColumns(10);
        txtSdt.setBounds(177, 84, 771, 20);
        pnlThongTinNCC.add(txtSdt);

        lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblEmail.setBounds(20, 112, 114, 28);
        pnlThongTinNCC.add(lblEmail);

        txtE = new JTextField();
        txtE.setColumns(10);
        txtE.setBounds(177, 114, 771, 20);
        pnlThongTinNCC.add(txtE);

        lblaCh = new JLabel("Địa chỉ:");
        lblaCh.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblaCh.setBounds(20, 142, 114, 28);
        pnlThongTinNCC.add(lblaCh);

        txtDiaChi = new JTextField();
        txtDiaChi.setColumns(10);
        txtDiaChi.setBounds(177, 144, 771, 20);
        pnlThongTinNCC.add(txtDiaChi);

        lblTrngThi = new JLabel("Trạng thái:");
        lblTrngThi.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTrngThi.setBounds(20, 172, 114, 28);
        pnlThongTinNCC.add(lblTrngThi);


        rdHopTac = new JRadioButton("Đang hợp tác");
        rdHopTac.setBackground(new Color(255, 255, 255));
        rdHopTac.setBounds(177, 174, 109, 23);
        pnlThongTinNCC.add(rdHopTac);

        rdKhong = new JRadioButton("Ngưng hợp tác");
        rdKhong.setBackground(new Color(255, 255, 255));
        rdKhong.setBounds(331, 174, 162, 23);
        pnlThongTinNCC.add(rdKhong);

        pnlChucNang = new JPanel();
        pnlChucNang.setBorder(new TitledBorder(null, "Ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnlChucNang.setBackground(new Color(255, 255, 255));
        pnlChucNang.setBounds(1008, 11, 241, 189);
        pnlThongTinNCC.add(pnlChucNang);

        btnThem = new JButton("Thêm");
        btnThem.setBackground(new Color(0, 255, 255));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnThem.setIcon(new ImageIcon("Anh\\them.png"));
        btnThem.setBounds(46, 24, 160, 30);

        pnlChucNang.setLayout(null);
        pnlChucNang.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setBackground(new Color(0, 255, 255));
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSua.setIcon(new ImageIcon("Anh\\sua.png"));
        btnSua.setBounds(46, 64, 160, 30);
        pnlChucNang.add(btnSua);

        btnReset = new JButton("Làm mới");
        btnReset.setBackground(new Color(0, 255, 255));
        btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnReset.setIcon(new ImageIcon("Anh\\lammoi.png"));
        btnReset.setBounds(46, 104, 160, 30);
        pnlChucNang.add(btnReset);

        btnLuu = new JButton("Lưu");
        btnLuu.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLuu.setBackground(Color.CYAN);
        btnLuu.setIcon(new ImageIcon("Anh\\luu.png"));
        btnLuu.setBounds(46, 144, 160, 30);
        pnlChucNang.add(btnLuu);

        pnTable = new JPanel();
        pnTable.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Thông tin nhà cung cấp:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnTable.setBackground(new Color(255, 255, 255));
        pnTable.setBounds(20, 324, 1286, 417);
        pnlThongTin.add(pnTable);
        pnTable.setLayout(null);

        JScrollPane scrDSHD;
        String[] tb1 = new String[]{"STT", "Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Email", "Địa chỉ"};
        tablemodel = new DefaultTableModel(tb1, 0);
        table_1 = new JTable(tablemodel);

        table_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        table_1.setBackground(new Color(224, 255, 255));
        table_1.setForeground(new Color(0, 0, 0));
        getContentPane().add(scrDSHD = new JScrollPane(table_1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
        scrDSHD.setBounds(10, 108, 1266, 298);
        pnTable.add(scrDSHD);
        scrDSHD.setPreferredSize(new Dimension(0, 250));

        comboBox = new JComboBox();
        comboBox.setBackground(new Color(244, 244, 244));
        comboBox.setBounds(233, 27, 432, 22);
        pnTable.add(comboBox);

        lblTK = new JLabel("Nhập thông tin tìm kiếm:");
        lblTK.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTK.setBounds(21, 25, 161, 28);
        pnTable.add(lblTK);

        lblTim = new JLabel("Tìm theo:");
        lblTim.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTim.setBounds(21, 64, 161, 28);
        pnTable.add(lblTim);

        rdMNCC = new JRadioButton("Mã nhà cung cấp");
        rdMNCC.setFont(new Font("Tahoma", Font.PLAIN, 12));
        rdMNCC.setBackground(Color.WHITE);
        rdMNCC.setBounds(232, 68, 131, 23);
        pnTable.add(rdMNCC);

        rdTenNCC = new JRadioButton("Tên nhà cung cấp");
        rdTenNCC.setFont(new Font("Tahoma", Font.PLAIN, 12));
        rdTenNCC.setBackground(Color.WHITE);
        rdTenNCC.setBounds(365, 68, 132, 23);
        pnTable.add(rdTenNCC);

        rdSDT = new JRadioButton("Số điện thoại");
        rdSDT.setFont(new Font("Tahoma", Font.PLAIN, 12));
        rdSDT.setBackground(Color.WHITE);
        rdSDT.setBounds(500, 68, 98, 23);
        pnTable.add(rdSDT);

        rdE = new JRadioButton("Email");
        rdE.setFont(new Font("Tahoma", Font.PLAIN, 12));
        rdE.setBackground(Color.WHITE);
        rdE.setBounds(625, 68, 98, 23);
        pnTable.add(rdE);

        rdStatus = new JRadioButton("Trạng thái");
        rdStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
        rdStatus.setBackground(Color.WHITE);
        rdStatus.setBounds(725, 68, 131, 23);
        pnTable.add(rdStatus);

        btnTim = new JButton("Tìm kiếm");
        btnTim.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnTim.setBackground(Color.CYAN);
        btnTim.setBounds(874, 25, 141, 30);
        btnTim.setIcon(new ImageIcon("Anh\\timkiem.png"));
        pnTable.add(btnTim);

        btnResetTim = new JButton("Làm mới");
        btnResetTim.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnResetTim.setBackground(Color.CYAN);
        btnResetTim.setBounds(1064, 25, 141, 30);
        btnResetTim.setIcon(new ImageIcon("Anh\\lammoi.png"));
        pnTable.add(btnResetTim);

        lblTieuDeTrang = new JLabel("NHÀ CUNG CẤP");
        lblTieuDeTrang.setBackground(new Color(0, 255, 255));
        lblTieuDeTrang.setBounds(0, 10, 1312, 31);
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));


        pnlThongTin.add(lblTieuDeTrang);

        pnlTieuDe = new JPanel();
        pnlTieuDe.setBackground(new Color(0, 255, 255));
        pnlTieuDe.setBounds(0, 0, 1376, 38);
        pnlThongTin.add(pnlTieuDe);
        gr1 = new ButtonGroup();
        gr1.add(rdHopTac);
        gr1.add(rdKhong);

        gr2 = new ButtonGroup();
        gr2.add(rdMNCC);
        gr2.add(rdTenNCC);
        gr2.add(rdE);
        gr2.add(rdSDT);
        gr2.add(rdStatus);

        txtBaoLoiTimKiem = new JLabel();
        txtBaoLoiTimKiem.setForeground(Color.RED);
        txtBaoLoiTimKiem.setBackground(Color.WHITE);
        txtBaoLoiTimKiem.setBounds(683, 27, 174, 20);
        pnTable.add(txtBaoLoiTimKiem);

        txtTenNCC.setEditable(false);
        txtSdt.setEditable(false);
        txtDiaChi.setEditable(false);
        txtE.setEditable(false);
        btnLuu.setEnabled(false);

        txtBaoLoi = new JLabel();
        txtBaoLoi.setForeground(Color.red);
        txtBaoLoi.setBackground(new Color(255, 255, 255));
        txtBaoLoi.setBounds(177, 204, 771, 20);
        pnlThongTinNCC.add(txtBaoLoi);

        txtComBoBox = (JTextField) comboBox.getEditor().getEditorComponent();


        btnThem.addActionListener(this);
        btnReset.addActionListener(this);
        btnSua.addActionListener(this);
        btnTim.addActionListener(this);
        btnResetTim.addActionListener(this);
        table_1.addMouseListener(this);
        btnLuu.addActionListener(this);
        rdMNCC.addActionListener(this);
        rdTenNCC.addActionListener(this);
        rdSDT.addActionListener(this);
        rdE.addActionListener(this);
        rdStatus.addActionListener(this);

        txtSdt.getDocument().addDocumentListener(this);
        txtE.getDocument().addDocumentListener(this);
        txtSdt.addKeyListener(this);
        txtComBoBox.addKeyListener(this);
        txtComBoBox.getDocument().addDocumentListener(this);
        docDuLieu();
        rdHopTac.setSelected(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object o = e.getSource();
        if (o.equals(btnThem)) {
            if (chkThem == false)
                themNCC();
            else {
                txtTenNCC.setEditable(false);
                txtSdt.setEditable(false);
                txtDiaChi.setEditable(false);
                txtE.setEditable(false);
                btnThem.setText("Thêm");
                chkThem = false;
                btnLuu.setEnabled(false);
                btnThem.setIcon(new ImageIcon("Anh\\them.png"));
                rdHopTac.setEnabled(true);
                rdKhong.setEnabled(true);
            }
        } else if (o.equals(btnReset)) {
            xoaTrang();
        } else if (o.equals(btnLuu)) {
            luuThayDoi();
        } else if (o.equals(btnSua)) {
            if (chkSua == false)
                sua();
            else {
                txtTenNCC.setEditable(false);
                txtSdt.setEditable(false);
                txtDiaChi.setEditable(false);
                txtE.setEditable(false);
                btnSua.setText("Sửa");
                chkSua = false;
                btnLuu.setEnabled(false);
                btnThem.setEnabled(true);
                btnSua.setIcon(new ImageIcon("Anh\\sua.png"));
            }
        } else if (o.equals(btnTim)) {
            if (chkTim == false) {
                comboBox.setEditable(true);
                comboBox.setBackground(new Color(255, 255, 255));
                chkTim = true;
                btnTim.setText("Hủy");
                btnTim.setIcon(new ImageIcon("Anh\\huy.png"));
            } else if (chkTim == true) {
                comboBox.setEditable(false);
                comboBox.setBackground(new Color(244, 244, 244));
                chkTim = false;
                btnTim.setText("Tìm kiếm");
                btnTim.setIcon(new ImageIcon("Anh\\timkiem.png"));
            }


        } else if (o.equals(btnResetTim)) {
            comboBox.removeAllItems();
            xoaAllDataTable();
            docDuLieu();
        } else if (o.equals(rdMNCC) || o.equals(rdSDT) || o.equals(rdStatus) || o.equals(rdE) || o.equals(rdTenNCC)) {
            comboBox.setSelectedItem("");
            updateCBBox();

        }
    }

    //	Thêm nhà cung cấp
    public void luuThayDoi() {
        String tenNCCString = txtTenNCC.getText();
        String Sdt = txtSdt.getText();
        String diaChi = txtDiaChi.getText();
        int tinhTrang = 1;
        String email = txtE.getText();

        if (rdHopTac.isSelected()) {
            tinhTrang = 1;
        } else if (rdKhong.isSelected()) {
            tinhTrang = 0;
        }
        NhaCungCap ncc = valiData();
        if (ncc == null)
            return;
        else {
            try {
                if (chkThem == true && chkSua == false) {
                    NhaCungCap nhaCungCap = new NhaCungCap(txtMaNCC.getText(), tenNCCString, Sdt, email, diaChi, (short) tinhTrang);
                    boolean newL = nccDao.them(nhaCungCap);
                    if (newL == true) {
                        btnThem.setText("Thêm");
                        xoaTrang();
                        xoaAllDataTable();
                        docDuLieu();
                        txtMaNCC.setText(deFaultID());
                        btnThem.setIcon(new ImageIcon("Anh\\them.png"));
                        JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thành công");
                        chkThem = false;
                    } else

                        JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp không thành công");
                } else if (chkSua == true && chkThem == false) {
                    NhaCungCap nhaCungCap = nccDao.getMa(txtMaNCC.getText()).get(0);
                    nhaCungCap.setTenNhaCungCap(tenNCCString);
                    nhaCungCap.setSdt(Sdt);
                    nhaCungCap.setEmail(email);
                    nhaCungCap.setDiaChi(diaChi);
                    nhaCungCap.setTinhTrang((short) tinhTrang);
                    boolean newL = nccDao.sua(nhaCungCap);
                    if (newL == true) {
                        btnSua.setText("Sửa");
                        btnSua.setIcon(new ImageIcon("Anh\\sua.png"));
                        xoaTrang();
                        xoaAllDataTable();
                        docDuLieu();
                        txtMaNCC.setText(deFaultID());
                        JOptionPane.showMessageDialog(this, "Sửa thông tin nhà cung cấp thành công");
                        chkSua = false;
                    } else

                        JOptionPane.showMessageDialog(this, "Sửa thông tin nhà cung cấp không thành công");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        btnLuu.setEnabled(false);
    }

    /**
     * Dùng để lấy dự liệu từ sql lên bảng
     */
    public void docDuLieu() {
        int d = 1;
        List<NhaCungCap> list = new ArrayList<>();
        try {
            list = nccDao.getNhaCungCaps();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (NhaCungCap x : list) {

            tablemodel.addRow(new Object[]{
                    d++, x.getMaNhaCungCap(), x.getTenNhaCungCap(), x.getSdt(), x.getEmail(), x.getDiaChi()
            });
            table_1.setModel(tablemodel);
        }
    }

    /**
     * Dùng để xóa dữ liệu bảng
     */
    public void xoaAllDataTable() {
        tablemodel.addRow(new Object[]{});
        tablemodel = (DefaultTableModel) table_1.getModel();
        tablemodel.getDataVector().removeAllElements();


    }

    //	Xóa trắng các thông tin
    public void xoaTrang() {
        btnThem.setEnabled(true);
        txtMaNCC.setText(deFaultID());
        txtTenNCC.setText("");
        txtSdt.setText("");
        txtE.setText("");
        txtDiaChi.setText("");
        rdHopTac.setSelected(false);
        rdKhong.setSelected(false);
        txtTenNCC.requestFocus();
        txtTenNCC.setEditable(false);
        txtSdt.setEditable(false);
        txtDiaChi.setEditable(false);
        txtE.setEditable(false);
        xoaAllDataTable();
        docDuLieu();
    }

    //	Sửa thông tin nhà cung cấp
    public void sua() {
        btnThem.setEnabled(false);
        btnLuu.setEnabled(true);
        txtTenNCC.setEditable(true);
        txtSdt.setEditable(true);
        txtDiaChi.setEditable(true);
        txtE.setEditable(true);
        btnSua.setText("Hủy");
        chkSua = true;
        btnSua.setIcon(new ImageIcon("Anh\\huy.png"));
    }

    //	Lưu dữ liệu vào comboBOx
    public void updateCBBox() {
        comboBox.removeAllItems();
        List<NhaCungCap> listN = null;
        try {
            listN = nccDao.getNhaCungCaps();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (NhaCungCap n : listN) {
            if (rdMNCC.isSelected()) {
                comboBox.addItem(n.getMaNhaCungCap());
                comboBox.setSelectedItem("");
            } else if (rdTenNCC.isSelected()) {
                comboBox.addItem(n.getTenNhaCungCap());
                comboBox.setSelectedItem("");
            } else if (rdSDT.isSelected()) {

                comboBox.addItem(n.getSdt());
                comboBox.setSelectedItem("");
            } else if (rdE.isSelected()) {

                comboBox.addItem(n.getEmail());
                comboBox.setSelectedItem("");
            } else if (rdStatus.isSelected()) {
                comboBox.removeAllItems();
                List<NhaCungCap> lncc = null;
                try {
                    lncc = nccDao.getNhaCungCaps();
                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                HashSet<String> tinhTrang = new HashSet<>();
                String d;
                for (NhaCungCap x : lncc) {
                    if (x.getTinhTrang() == 1) {
                        d = "Đang hợp tác";
                    } else
                        d = "Ngưng hợp tác";
                    tinhTrang.add(d);
                }
                for (String tinhTrangTim : tinhTrang) {
                    comboBox.addItem(tinhTrangTim);
                }
                comboBox.setSelectedItem("Ngưng hợp tác");
            }

        }


    }

    //Tìm thông tin nhà cung cấp
    public void tim() {
        int d = 1;
        String tim = "";
        ButtonModel selectedButton = gr2.getSelection();

        if (selectedButton == null) {
            txtBaoLoiTimKiem.setText("Chọn thông tin cần tìm!");
        } else {
            if (txtComBoBox.getText() == "" || txtComBoBox.getText().isEmpty()) {
                xoaAllDataTable();
                docDuLieu();
            } else {
                tim = txtComBoBox.getText();
                txtBaoLoiTimKiem.setText("");
                xoaAllDataTable();
                if (rdTenNCC.isSelected()) {

                    List<NhaCungCap> list = null;
                    try {
                        list = nccDao.getTen(tim);
                    } catch (RemoteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    txtBaoLoiTimKiem.setText("");
                    for (NhaCungCap x : list) {
                        tablemodel.addRow(new Object[]{
                                d++, x.getMaNhaCungCap(), x.getTenNhaCungCap(), x.getSdt(), x.getEmail(), x.getDiaChi()
                        });

                    }

                } else if (rdMNCC.isSelected()) {
                    List<NhaCungCap> list = null;
                    try {
                        list = nccDao.getMa(tim);
                    } catch (RemoteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    txtBaoLoiTimKiem.setText("");
                    for (NhaCungCap x : list) {
                        tablemodel.addRow(new Object[]{
                                d++, x.getMaNhaCungCap(), x.getTenNhaCungCap(), x.getSdt(), x.getEmail(), x.getDiaChi()
                        });
                    }

                } else if (rdSDT.isSelected()) {

                    List<NhaCungCap> list = null;
                    try {
                        list = nccDao.getSDT(tim);
                    } catch (RemoteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    txtBaoLoiTimKiem.setText("");

                    for (NhaCungCap n : list) {
                        tablemodel.addRow(new Object[]{
                                d++, n.getMaNhaCungCap(), n.getTenNhaCungCap(), n.getSdt(), n.getEmail(), n.getDiaChi()
                        });
                    }

                } else if (rdE.isSelected()) {
                    List<NhaCungCap> list = null;
                    try {
                        list = nccDao.getEmail(tim);
                    } catch (RemoteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    for (NhaCungCap n : list) {
                        tablemodel.addRow(new Object[]{
                                d++, n.getMaNhaCungCap(), n.getTenNhaCungCap(), n.getSdt(), n.getEmail(), n.getDiaChi()
                        });
                    }

                } else if (rdStatus.isSelected()) {
                    List<NhaCungCap> list = null;
                    try {
                        list = nccDao.getNhaCungCaps();
                    } catch (RemoteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    txtBaoLoiTimKiem.setText("");
                    for (NhaCungCap x : list) {
                        if (tim.equalsIgnoreCase("Đang hợp tác") && x.getTinhTrang() == 1) {
                            tablemodel.addRow(new Object[]{
                                    d++, x.getMaNhaCungCap(), x.getTenNhaCungCap(), x.getSdt(), x.getEmail(), x.getDiaChi()
                            });
                            table_1.setModel(tablemodel);
                        } else if (tim.equalsIgnoreCase("Ngưng hợp tác") && x.getTinhTrang() == 0) {
                            tablemodel.addRow(new Object[]{
                                    d++, x.getMaNhaCungCap(), x.getTenNhaCungCap(), x.getSdt(), x.getEmail(), x.getDiaChi()
                            });
                            table_1.setModel(tablemodel);
                        }
                    }
                }
            }
            if (tablemodel.getRowCount() == 0) {
                txtBaoLoiTimKiem.setText("Không tìm thấy nhà cung cấp");
            }
        }

    }

    //	Kiểm tra thông tin nhà cung cấp trước khi thêm
    public NhaCungCap valiData() {
        NhaCungCap ncc;
        String maNCC = txtMaNCC.getText().trim();
        if (txtTenNCC.getText().isEmpty() || txtTenNCC.getText().trim() == "") {
            ShowErrorField("Tên nhà cung cấp không được rỗng", txtTenNCC);
            return null;
        }
        String tenNCC = txtTenNCC.getText().trim();
        if (txtSdt.getText().isEmpty() || txtSdt.getText().trim() == "") {
            ShowErrorField("Số điện thoại nhà cung cấp không được rỗng", txtSdt);
            return null;
        } else if (txtSdt.getText().trim().matches("^[0]\\d{9}$") == false) {
            ShowErrorField("Vui lòng nhập số điện thoại nhà cung cấp bằng số và gồm 10 chữ số bất đầu bằng số 0 !", txtSdt);
            return null;
        }
        String sdt = txtSdt.getText().trim();

        if (txtE.getText().isEmpty() || txtE.getText().trim() == "") {
            ShowErrorField("Email nhà cung cấp không được rỗng", txtE);
            return null;
        } else if (txtE.getText().trim().matches("^[a-zA-Z]+[\\w.%+-]*@[\\w.-]+\\.com$") == false) {
            ShowErrorField("Vui lòng nhập đúng email nhà cung cấp !\\n ví dụ:quoc@gmail.com", txtE);
            return null;
        }
        String mail = txtE.getText().trim();
        if (txtDiaChi.getText().isEmpty() || txtDiaChi.getText().trim() == "") {
            ShowErrorField("Địa chỉ không được rỗng", txtDiaChi);
            return null;
        }
        String diaChi = txtDiaChi.getText().trim();

        int tinhTrang = 2;
        if (rdHopTac.isSelected()) {
            tinhTrang = 1;
        } else if (rdKhong.isSelected()) {
            tinhTrang = 0;
        } else if (tinhTrang == 2) {
            JOptionPane.showMessageDialog(null, "Hiện có đang hợp tác với nhà cung cấp này không?");
            return null;
        }

        try {
            ncc = new NhaCungCap(maNCC, tenNCC, sdt, mail, diaChi, tinhTrang);
            return ncc;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }

    }

    private void ShowErrorField(String string, JTextField txt) {
        JOptionPane.showMessageDialog(null, string);
        txt.requestFocus();

    }

    //	check số
    public boolean isNum(String x) {
        try {
            Double.parseDouble(x);

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String deFaultID() {
        int n = 0;
        try {
            n = nccDao.soLuongNCC() + 1;
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String soLuongMa = String.format("%03d", n);
        String deFault = "NCC" + soLuongMa;
        return deFault;
    }

    public boolean themNCC() {
        btnLuu.setEnabled(true);
        txtTenNCC.setEditable(true);
        txtSdt.setEditable(true);
        txtDiaChi.setEditable(true);
        txtE.setEditable(true);
        chkThem = true;
        txtTenNCC.requestFocus();
        rdHopTac.setSelected(true);
        btnThem.setText("Hủy");
        btnThem.setIcon(new ImageIcon("Anh\\huy.png"));
        rdHopTac.setEnabled(false);
        rdKhong.setEnabled(false);
        rdHopTac.setSelected(true);
        return true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        Object o = e.getSource();
        if (o.equals(table_1)) {
            btnThem.setEnabled(false);
//			btnLuu.setEnabled(false);
            int d = 1;
            int row = table_1.getSelectedRow();
            txtMaNCC.setText(tablemodel.getValueAt(row, 1).toString());
            txtTenNCC.setText(tablemodel.getValueAt(row, 2).toString());
            txtSdt.setText(tablemodel.getValueAt(row, 3).toString());
            txtE.setText(tablemodel.getValueAt(row, 4).toString());
            txtDiaChi.setText(tablemodel.getValueAt(row, 5).toString());
            List<NhaCungCap> list = null;
            try {
                list = nccDao.getNhaCungCaps();
            } catch (RemoteException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            for (NhaCungCap x : list) {
                if (x.getMaNhaCungCap().equals(tablemodel.getValueAt(row, 1).toString())) {
                    if (x.getTinhTrang() == 1) {
                        rdHopTac.setSelected(true);
                        rdKhong.setSelected(false);
                    } else {
                        rdHopTac.setSelected(false);
                        rdKhong.setSelected(true);
                    }

                }
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

    public void checkSoDienThoai() {

        if (txtSdt.getText().trim().matches("^[0]\\d{9}$") == false && txtSdt.getText().trim().length() == 10) {
            txtBaoLoi.setText("Vui lòng nhập số điện thoại nhà cung cấp bằng số và gồm 10 chữ số bất đầu bằng số 0 !");
        } else txtBaoLoi.setText("");
    }

    public void checkMail() {
        if (txtE.getText().trim().matches("^[a-zA-Z]+[\\w.%+-]*@[\\w.-]+\\.com$") == false && txtE.getText().contains(".com")) {
            txtBaoLoi.setText("Vui lòng nhập đúng email nhà cung cấp !\\\\n ví dụ:quoc@gmail.com");
        } else txtBaoLoi.setText("");
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        if (e.getDocument() == txtSdt.getDocument()) {
            checkSoDienThoai();
        } else if (e.getDocument() == txtE.getDocument()) {
            checkMail();
        } else if (e.getDocument() == txtComBoBox.getDocument()) {
            tim();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        if (e.getDocument() == txtSdt.getDocument()) {
            checkSoDienThoai();
            if (txtSdt.getText().isEmpty() || txtSdt.getText().trim() == "") {
                txtBaoLoi.setText("");
            }
        } else if (e.getDocument() == txtE.getDocument()) {
            checkMail();
            if (txtE.getText().isEmpty() || txtE.getText().trim() == "") {
                txtBaoLoi.setText("");
            }
        } else if (e.getDocument() == txtComBoBox.getDocument()) {
            tim();
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        if (e.getDocument() == txtSdt.getDocument()) {
            checkSoDienThoai();
        } else if (e.getDocument() == txtE.getDocument()) {
            checkMail();
        } else if (e.getDocument() == txtComBoBox.getDocument()) {
            tim();
        }
    }

    // bắt Sự kiện nhập text
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        Object o = e.getSource();
        if (o.equals(txtSdt)) {
            if (!((e.getKeyChar() >= 48 && e.getKeyChar() <= 57) || e.getKeyChar() == 8)) {
                e.consume();
            }

        } else if (o.equals(txtComBoBox)) {
            tim();
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
}