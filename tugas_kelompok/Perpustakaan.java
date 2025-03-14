package tugas_kelompok;

import java.util.Scanner;




class Buku {


    private String judul;
    private String jenisBuku;
    private String penulis;
    private int tahunTerbit;
    private boolean tersedia;

    public Buku(String judul, String jenisBuku, String penulis, int tahunTerbit) {
        this.judul = judul;
        this.jenisBuku = jenisBuku;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.tersedia = true;
    }


    public void infoBuku() {
        System.out.println("Judul: " + judul);
        System.out.println("Jenis: " + jenisBuku);
        System.out.println("Penulis: " + penulis);
        System.out.println("Tahun Terbit: " + tahunTerbit);
        System.out.println("Status: " + (tersedia ? "Tersedia" : "Dipinjam"));
    }


    public String getJudul() {
        return judul;
    }

    public boolean isTersedia() {
        return tersedia;

    }

    public void setTersedia(boolean status) {
        tersedia = status;

    }
}

abstract class User {
    protected String id;
    protected String name;
    protected String email;


    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;

    }

    public void login() {
        System.out.println("User " + name + " logged in");


    }

    public void logout() {
        System.out.println("User " + name + " logged out");

    }

    abstract void showMenu();
}

class Member extends User {

    public Member(String id, String name, String email, String nomorMember) {
        super(id, name, email);
    }

    @Override
    void showMenu() {
        // tambahin untuk membernya
    }

}

class Admin extends User {

    private Buku[] koleksiBuku = new Buku[100];
    private int jumlahBuku = 0;

    public Admin(String id, String name, String email, String jabatan) {
        super(id, name, email);

    }

    @Override
    void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu Admin:");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Hapus Buku");
            System.out.println("3. Lihat Buku");
            System.out.println("4. Logout");
            System.out.print("Pilih menu: ");

            int choice = scanner.nextInt();

            scanner.nextLine();



            switch (choice) {
                case 1:
                    tambahBuku(scanner);
                    break;
                case 2:
                    hapusBuku(scanner);
                    break;
                case 3:
                    tampildaftarBuku();
                    break;
                case 4:
                    logout();
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void tambahBuku(Scanner scanner) {
        System.out.print("Judul: ");
        String judul = scanner.nextLine();
        System.out.print("Jenis: ");
        String jenis = scanner.nextLine();
        System.out.print("Penulis: ");
        String penulis = scanner.nextLine();
        System.out.print("Tahun Terbit: ");
        int tahunterbit = scanner.nextInt();

        koleksiBuku[jumlahBuku++] = new Buku(judul, jenis, penulis, tahunterbit);
        System.out.println("Buku berhasil ditambahkan!");
    }

    private void hapusBuku(Scanner scanner) {
        System.out.print("Masukkan judul buku: ");
        String judul = scanner.nextLine();

        int index = -1;

 
        for (int i = 0; i < jumlahBuku; i++) {
            if (koleksiBuku[i].getJudul().equalsIgnoreCase(judul)) {
                index = i;
                break;
            }
        }



        if (index == -1) {
            System.out.println("Buku tidak ditemukan!");
            return;
        }



        // menggeser buku setelahnhya ke bagian yang mau dihapus
        for (int i = index; i < jumlahBuku - 1; i++) {
            koleksiBuku[i] = koleksiBuku[i + 1];
        }

        // Hapus data di paling akhir
        koleksiBuku[jumlahBuku - 1] = null;
        jumlahBuku--;

        System.out.println("Buku berhasil dihapus!");

    }

    private void tampildaftarBuku() {
        if (jumlahBuku == 0) {
            System.out.println("Belum ada buku yang terdaftar");
            return;
        }

        System.out.println("\nList Buku:");

        for (int i = 0; i < jumlahBuku; i++) {
            System.out.println((i + 1) + ".");


            koleksiBuku[i].infoBuku();
            System.out.println("\n");
        }

    }
}

public class Perpustakaan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);




        System.out.println("Sistem Perpustakaan");
        System.out.println("1. Login Admin");
        System.out.println("2. Login Member");
        System.out.print("Pilih: ");


        int choice = scanner.nextInt();
        scanner.nextLine();



        User user = null;

        if (choice == 1) {
            user = new Admin("admin1", "Admin Utama", "admintest@perpustakaan.id", "Kepala Perpustakaan");
        } else {
            user = new Member("member1", "satria", "member1212@gmail.com", "08123456789");
        }

        user.login();
        user.showMenu();
        scanner.close();
    }
}