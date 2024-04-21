package client;


import DAOTest.NhanVienDao;
import DAOTest.XemHoaDonDao;


import java.rmi.Naming;
import java.util.Scanner;

public class Client {

    private static final String URL = "rmi://192.168.1.15:6541/";

    public static void main(String[] args) {
        try {
            NhanVienDao xemHoaDonDao = (NhanVienDao) Naming.lookup(URL + "XemHoaDonDao");
            Scanner scanner = new Scanner(System.in);
            int option;

            while (true) {
                System.out.println("1. Tìm ra all nh    ân viên theo công ty");
                System.out.println("2. Tìm ra danh sách nhân viên có thời gian làm việc lâu nhất");
                System.out.println("3. Tìm ra danh sách hóa đơn theo tên và mức lương");
                System.out.println("4. Thoát");
                System.out.println("Chọn chức năng: ");
                option = scanner.nextInt();
                scanner.nextLine();

                // 1 - Tìm ra all nhân viên theo công ty
                while (option == 1) {
                    System.out.println(xemHoaDonDao.getAllNV());
                    break;
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }}
