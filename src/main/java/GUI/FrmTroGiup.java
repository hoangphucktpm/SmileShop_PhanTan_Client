package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class FrmTroGiup extends JFrame implements ActionListener {

    private JButton btnHoTro;
	private JLabel lblicon;
	private JPanel pnlTieuDe;
	private JLabel lblTieuDeTrang;
	private JPanel pnlThongTin;

	
    public FrmTroGiup() {
    	pnlThongTin = new JPanel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1347, 843);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(255, 255, 255));
    	setIconImage(new ImageIcon("Anh\\Logo.png").getImage());

        pnlThongTin.setBackground(new Color(255, 255, 255));
        pnlThongTin.setBounds(0, 0, 1347, 806);
        getContentPane().add(pnlThongTin);
        pnlThongTin.setLayout(null);

        pnlTieuDe = new JPanel();
        pnlTieuDe.setBackground(new Color(0, 255, 255));
        pnlTieuDe.setBounds(0, 0, 1347, 41);
        pnlThongTin.add(pnlTieuDe);
        pnlTieuDe.setLayout(null);

        lblTieuDeTrang = new JLabel("TRỢ GIÚP");
        lblTieuDeTrang.setBounds(512, 12, 305, 25);
        pnlTieuDe.add(lblTieuDeTrang);
        lblTieuDeTrang.setBackground(new Color(0, 255, 255));
        lblTieuDeTrang.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeTrang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));

       

        btnHoTro = new JButton("Hỗ Trợ");
        btnHoTro.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnHoTro.setBackground(Color.CYAN);
        btnHoTro.setBounds(774, 432, 228, 55);
        pnlThongTin.add(btnHoTro);
        
        JLabel lblicon = new JLabel();
        try {
            BufferedImage img = ImageIO.read(new File("Anh\\guide (2).png"));
            Image scaledImage = img.getScaledInstance(366, 428, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImage);
            lblicon.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lblicon.setBounds(67, 147, 366, 428);
        pnlThongTin.add(lblicon);
        
        JLabel lbltieude = new JLabel("MỜI BẠN NHẤP VÀO NÚT HỖ TRỢ BÊN DƯỚI");
        lbltieude.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lbltieude.setBounds(668, 245, 445, 61);
        pnlThongTin.add(lbltieude);
        
        JLabel lblcDn = new JLabel("ĐỂ ĐƯỢC DẪN ĐẾN WEBSITE HỖ TRỢ CỦA SMILE SHOP");
        lblcDn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblcDn.setBounds(615, 293, 597, 61);
        pnlThongTin.add(lblcDn);

        btnHoTro.addActionListener(this);

        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnHoTro) {
            try {
                URI uri = new URI("https://by.tn/helpsmileshop"); // Thay đổi URL tại đây
                Desktop.getDesktop().browse(uri);
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new FrmTroGiup();
        });
    }
}
