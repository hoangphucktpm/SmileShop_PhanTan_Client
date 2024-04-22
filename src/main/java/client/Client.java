package client;


import DAOTest.NhanVienDao;


import java.rmi.Naming;
import java.util.Scanner;

public class Client {

    private static final String URL = "rmi://HOANGPHUC:6541/";

    public static void main(String[] args) {
        try {
            NhanVienDao nhanVienDao = (NhanVienDao) Naming.lookup(URL + "NhanVienDao");
            Scanner scanner = new Scanner(System.in);
            int option;

            while (true) {
                System.out.println("1. Tìm ra tất ca nhân viên");
                System.out.println("2. Tìm ra danh sách nhân viên có thời gian làm việc lâu nhất");
                System.out.println("3. Tìm ra danh sách hóa đơn theo tên và mức lương");
                System.out.println("4. Thoát");
                System.out.println("Chọn chức năng: ");
                option = scanner.nextInt();
                scanner.nextLine();

                // 1 - Tìm ra all nhân viên theo công ty
                while (option == 1) {
                    System.out.println(nhanVienDao.getAllNV());
                    break;
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }}
