package client;


import DAOTest.NhaCungCapDao;
import DAOTest.NhanVienDao;
import Entities.NhaCungCap;
import Entities.NhanVien;


import java.rmi.Naming;
import java.util.Scanner;

public class Client {

    private static final String URL = "rmi://192.168.1.33:6541/";

    public static void main(String[] args) {
        try {
            NhaCungCapDao nhaCungCapDao = (NhaCungCapDao) Naming.lookup(URL + "NhaCungCapDao");
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

                    //         return "NhaCungCap{maNhaCungCap='" + this.maNhaCungCap + "', tenNhaCungCap='" + this.tenNhaCungCap + "', sdt='" + this.sdt + "', email='" + this.email + "', diaChi='" + this.diaChi + "', tinhTrang=" + this.tinhTrang + "}";
                    NhaCungCap nhaCungCap = new NhaCungCap("NCC222", "Nha Cung Cap 1", "0123456789", "hi@gmail.com", "Dia Chi", 1);
                    nhaCungCapDao.them(nhaCungCap);
                    break;
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }}
