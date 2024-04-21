package xuly;

import GUI.FrmDangNhap;
import GUI.FrmGioiThieu;

import javax.swing.*;
import java.rmi.RemoteException;

public class CountDownThread extends Thread {
    /**
     * @wbp.parser.entryPoint
     */
    public void run() {
        try {
            int duration = 150; // Độ dài của tiến trình
            FrmGioiThieu frmGioiThieu = new FrmGioiThieu();
            frmGioiThieu.setVisible(true);
            frmGioiThieu.setLocationRelativeTo(null);
            frmGioiThieu.setIconImage(new ImageIcon("Anh\\Logo.png").getImage());

            for (int i = 0; i <= duration; i++) {
                try {
                    Thread.sleep(10); // Tạm dừng mỗi 10 mili giây
                    int progress = (i * 100) / duration; // Tính giá trị tiến trình
                    frmGioiThieu.progressBar_1.setValue(progress); // Cập nhật tiến trình
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(500); // Dừng 0.5 giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            frmGioiThieu.setVisible(false);
            FrmDangNhap frmDangNhap = new FrmDangNhap();
            frmDangNhap.setVisible(true);
            frmDangNhap.setLocationRelativeTo(null);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
