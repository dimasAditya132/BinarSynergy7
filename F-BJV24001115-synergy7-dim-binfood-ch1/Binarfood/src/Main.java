//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pilihan, jumlah;
        String pesanan = "";
        int total = 0;

        while (true) {
            System.out.println("================================");
            System.out.println("** Selamat Datang di BinarFud **");
            System.out.println("================================");
            System.out.println("1. Nasi Goreng      | Rp15,000");
            System.out.println("2. Mie Goreng       | Rp13,000");
            System.out.println("3. Nasi + Ayam      | Rp18,000");
            System.out.println("4. Es Teh Manis     | Rp3,000");
            System.out.println("5. Es Jeruk         | Rp5,000");
            System.out.println("99. Pesan dan Bayar");
            System.out.println("0. Keluar aplikasi");
            System.out.print("=> ");
            pilihan = scanner.nextInt();

            if (pilihan >= 1 && pilihan <= 5) {
                System.out.println("========================");
                System.out.println("Berapa Pesanan Anda");
                System.out.println("========================");
                System.out.print("qty=> ");
                jumlah = scanner.nextInt();
                switch (pilihan) {
                    case 1:
                        pesanan += "Nasi Goreng x " + jumlah + "\n";
                        total += 15000 * jumlah;
                        break;
                    case 2:
                        pesanan += "Mie Goreng x " + jumlah + "\n";
                        total += 13000 * jumlah;
                        break;
                    case 3:
                        pesanan += "Nasi + Ayam x " + jumlah + "\n";
                        total += 18000 * jumlah;
                        break;
                    case 4:
                        pesanan += "Es Teh Manis x " + jumlah + "\n";
                        total += 3000 * jumlah;
                        break;
                    case 5:
                        pesanan += "Es Jeruk x " + jumlah + "\n";
                        total += 5000 * jumlah;
                        break;
                }
            } else if (pilihan == 99) {
                System.out.println("==========================");
                System.out.println("Konfirmasi & Pembayaran");
                System.out.println("==========================");
                System.out.println(pesanan);
                System.out.println("--------------------------");
                System.out.println("Total: Rp" + total);
                System.out.println(" ");
                System.out.println("1. Konfirmasi dan Bayar");
                System.out.println("2. Kembali ke menu utama");
                System.out.println("0. Keluar aplikasi");
                pilihan = scanner.nextInt();
                if (pilihan == 1) {
                    try {
                        FileWriter myWriter = new FileWriter("struk.txt");
                        myWriter.write("======================");
                        myWriter.write("BinarFud");
                        myWriter.write("======================");
                        myWriter.write(" ");
                        myWriter.write("Terima kasih sudah memesan di BinarFud");
                        myWriter.write("Dibawah ini adalah pesanan Anda");
                        myWriter.write(pesanan);
                        myWriter.write("--------------------------------+");
                        myWriter.write("Total: Rp" + total);
                        myWriter.write("Pembayaran : BinarCash");
                        myWriter.write("=====================");
                        myWriter.write("Simpan struk ini sebagai bukti pembayaran");
                        myWriter.write("=====================");
                        myWriter.close();
                        System.out.println("Struk pembayaran telah disimpan sebagai struk.txt");
                    } catch (IOException e) {
                        System.out.println("Terjadi kesalahan saat menyimpan struk.");
                        e.printStackTrace();
                    }
                    break;
                }
            } else if (pilihan == 0) {
                break;
            }
        }
        scanner.close();
    }
}