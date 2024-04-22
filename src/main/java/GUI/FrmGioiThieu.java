package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class FrmGioiThieu extends JFrame implements ActionListener{

	JPanel contentPane;
	public JProgressBar progressBar_1;


	public FrmGioiThieu() throws RemoteException{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(new ImageIcon("Anh\\Logo.png").getImage());
		
		progressBar_1 = new JProgressBar();
		progressBar_1.setBackground(new Color(255, 255, 255));
		progressBar_1.setForeground(new Color(255, 0, 0));
		progressBar_1.setBounds(308, 308, 189, 24);
		progressBar_1.setStringPainted(true);
		progressBar_1.setToolTipText("");
		progressBar_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		contentPane.add(progressBar_1);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("Anh\\Anhdn.png"));
		lblNewLabel_1.setBounds(-96, -192, 870, 641);
		contentPane.add(lblNewLabel_1);
		
        
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
