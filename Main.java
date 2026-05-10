import java.util.Scanner;

public class Main {
    // Data menu restoran dalam array
    static Menu[] daftarMenu = new Menu[8];
    // Array untuk menyimpan indeks menu yang dipesan (maks 4 pesanan)
    static int[] pesananIndex = new int[4];
    // Array untuk menyimpan jumlah per item pesanan
    static int[] pesananQty = new int[4];
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Inisialisasi menu
        inisialisasiMenu();

        // Menampilkan daftar menu
        cetakMenu();

        // Proses pemesanan (4 kali input tanpa loop)
        inputSatuPesanan(0);
        inputSatuPesanan(1);
        inputSatuPesanan(2);
        inputSatuPesanan(3);

        // Hitung total dan cetak struk
        hitungDanCetakStruk();
    }

    // Method inisialisasi menu
    static void inisialisasiMenu() {
        daftarMenu[0] = new Menu("Nasi Padang", 15000, "makanan");
        daftarMenu[1] = new Menu("Sate Ayam", 20000, "makanan");
        daftarMenu[2] = new Menu("Ayam Bakar", 25000, "makanan");
        daftarMenu[3] = new Menu("Soto Betawi", 18000, "makanan");
        daftarMenu[4] = new Menu("Es Teh", 5000, "minuman");
        daftarMenu[5] = new Menu("Jus Jeruk", 8000, "minuman");
        daftarMenu[6] = new Menu("Air Mineral", 4000, "minuman");
        daftarMenu[7] = new Menu("Kopi Tubruk", 7000, "minuman");
    }

    // Method mencetak menu dikelompokkan per kategori (tanpa loop)
    static void cetakMenu() {
        System.out.println("========== DAFTAR MENU ==========");
        System.out.println("--- MAKANAN ---");
        // Akses manual tiap elemen array
        if (daftarMenu[0] != null && daftarMenu[0].getKategori().equals("makanan"))
            System.out.println("1. " + daftarMenu[0].getNama() + " - Rp" + daftarMenu[0].getHarga());
        if (daftarMenu[1] != null && daftarMenu[1].getKategori().equals("makanan"))
            System.out.println("2. " + daftarMenu[1].getNama() + " - Rp" + daftarMenu[1].getHarga());
        if (daftarMenu[2] != null && daftarMenu[2].getKategori().equals("makanan"))
            System.out.println("3. " + daftarMenu[2].getNama() + " - Rp" + daftarMenu[2].getHarga());
        if (daftarMenu[3] != null && daftarMenu[3].getKategori().equals("makanan"))
            System.out.println("4. " + daftarMenu[3].getNama() + " - Rp" + daftarMenu[3].getHarga());

        System.out.println("--- MINUMAN ---");
        if (daftarMenu[4] != null && daftarMenu[4].getKategori().equals("minuman"))
            System.out.println("1. " + daftarMenu[4].getNama() + " - Rp" + daftarMenu[4].getHarga());
        if (daftarMenu[5] != null && daftarMenu[5].getKategori().equals("minuman"))
            System.out.println("2. " + daftarMenu[5].getNama() + " - Rp" + daftarMenu[5].getHarga());
        if (daftarMenu[6] != null && daftarMenu[6].getKategori().equals("minuman"))
            System.out.println("3. " + daftarMenu[6].getNama() + " - Rp" + daftarMenu[6].getHarga());
        if (daftarMenu[7] != null && daftarMenu[7].getKategori().equals("minuman"))
            System.out.println("4. " + daftarMenu[7].getNama() + " - Rp" + daftarMenu[7].getHarga());
        System.out.println("==================================\n");
    }

    // Method untuk mencari indeks menu berdasarkan nama (tanpa loop, if berantai)
    static int cariIndeksMenu(String nama) {
        if (daftarMenu[0] != null && daftarMenu[0].getNama().equalsIgnoreCase(nama))
            return 0;
        else if (daftarMenu[1] != null && daftarMenu[1].getNama().equalsIgnoreCase(nama))
            return 1;
        else if (daftarMenu[2] != null && daftarMenu[2].getNama().equalsIgnoreCase(nama))
            return 2;
        else if (daftarMenu[3] != null && daftarMenu[3].getNama().equalsIgnoreCase(nama))
            return 3;
        else if (daftarMenu[4] != null && daftarMenu[4].getNama().equalsIgnoreCase(nama))
            return 4;
        else if (daftarMenu[5] != null && daftarMenu[5].getNama().equalsIgnoreCase(nama))
            return 5;
        else if (daftarMenu[6] != null && daftarMenu[6].getNama().equalsIgnoreCase(nama))
            return 6;
        else if (daftarMenu[7] != null && daftarMenu[7].getNama().equalsIgnoreCase(nama))
            return 7;
        else
            return -1; // tidak ditemukan
    }

    // Method input satu pesanan (dipanggil 4 kali)
    static void inputSatuPesanan(int slot) {
        System.out.print("Pesanan ke-" + (slot + 1) + " (format: NamaMenu = Jumlah, atau 'skip' untuk lewati): ");
        String line = input.nextLine();
        if (line.equalsIgnoreCase("skip") || line.trim().isEmpty()) {
            pesananIndex[slot] = -1;
            pesananQty[slot] = 0;
            return;
        }
        String[] parts = line.split("=");
        if (parts.length != 2) {
            System.out.println("Format salah! Pesanan diabaikan.");
            pesananIndex[slot] = -1;
            pesananQty[slot] = 0;
            return;
        }
        String namaPesanan = parts[0].trim();
        int jumlah;
        try {
            jumlah = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            System.out.println("Jumlah tidak valid! Pesanan diabaikan.");
            pesananIndex[slot] = -1;
            pesananQty[slot] = 0;
            return;
        }

        int indeks = cariIndeksMenu(namaPesanan);
        if (indeks == -1) {
            System.out.println("Menu '" + namaPesanan + "' tidak ditemukan! Pesanan diabaikan.");
            pesananIndex[slot] = -1;
            pesananQty[slot] = 0;
        } else {
            pesananIndex[slot] = indeks;
            pesananQty[slot] = jumlah;
        }
    }

    // Method menghitung total & mencetak struk (berisi banyak struktur keputusan)
    static void hitungDanCetakStruk() {
        // Hitung subtotal secara manual (tanpa loop)
        int subtotal = 0;
        if (pesananIndex[0] != -1)
            subtotal += pesananQty[0] * daftarMenu[pesananIndex[0]].getHarga();
        if (pesananIndex[1] != -1)
            subtotal += pesananQty[1] * daftarMenu[pesananIndex[1]].getHarga();
        if (pesananIndex[2] != -1)
            subtotal += pesananQty[2] * daftarMenu[pesananIndex[2]].getHarga();
        if (pesananIndex[3] != -1)
            subtotal += pesananQty[3] * daftarMenu[pesananIndex[3]].getHarga();

        // Biaya tambahan
        final double PAJAK_PERSEN = 0.10;
        final int BIAYA_PELAYANAN = 20000;
        double pajak = subtotal * PAJAK_PERSEN;
        double grossTotal = subtotal + pajak + BIAYA_PELAYANAN;

        // Variabel diskon
        boolean dapatDiskon = false;
        double diskon10Persen = 0;
        double diskonMinuman = 0;
        String minumanPromo = "";

        if (grossTotal > 100000) {
            dapatDiskon = true;
            diskon10Persen = grossTotal * 0.10;
        }

        if (grossTotal > 50000) {
            // Cek pesanan satu per satu apakah ada minuman (tanpa loop)
            if (pesananIndex[0] != -1 && daftarMenu[pesananIndex[0]].getKategori().equals("minuman")) {
                System.out.print("Apakah Anda ingin menerapkan beli 1 gratis 1 untuk "
                        + daftarMenu[pesananIndex[0]].getNama() + "? (ya/tidak): ");
                if (input.nextLine().trim().equalsIgnoreCase("ya")) {
                    minumanPromo = daftarMenu[pesananIndex[0]].getNama();
                    diskonMinuman = (pesananQty[0] / 2) * daftarMenu[pesananIndex[0]].getHarga();
                    grossTotal -= diskonMinuman; // potongan langsung dari gross
                }
            } else if (pesananIndex[1] != -1 && daftarMenu[pesananIndex[1]].getKategori().equals("minuman")) {
                System.out.print("Apakah Anda ingin menerapkan beli 1 gratis 1 untuk "
                        + daftarMenu[pesananIndex[1]].getNama() + "? (ya/tidak): ");
                if (input.nextLine().trim().equalsIgnoreCase("ya")) {
                    minumanPromo = daftarMenu[pesananIndex[1]].getNama();
                    diskonMinuman = (pesananQty[1] / 2) * daftarMenu[pesananIndex[1]].getHarga();
                    grossTotal -= diskonMinuman;
                }
            } else if (pesananIndex[2] != -1 && daftarMenu[pesananIndex[2]].getKategori().equals("minuman")) {
                System.out.print("Apakah Anda ingin menerapkan beli 1 gratis 1 untuk "
                        + daftarMenu[pesananIndex[2]].getNama() + "? (ya/tidak): ");
                if (input.nextLine().trim().equalsIgnoreCase("ya")) {
                    minumanPromo = daftarMenu[pesananIndex[2]].getNama();
                    diskonMinuman = (pesananQty[2] / 2) * daftarMenu[pesananIndex[2]].getHarga();
                    grossTotal -= diskonMinuman;
                }
            } else if (pesananIndex[3] != -1 && daftarMenu[pesananIndex[3]].getKategori().equals("minuman")) {
                System.out.print("Apakah Anda ingin menerapkan beli 1 gratis 1 untuk "
                        + daftarMenu[pesananIndex[3]].getNama() + "? (ya/tidak): ");
                if (input.nextLine().trim().equalsIgnoreCase("ya")) {
                    minumanPromo = daftarMenu[pesananIndex[3]].getNama();
                    diskonMinuman = (pesananQty[3] / 2) * daftarMenu[pesananIndex[3]].getHarga();
                    grossTotal -= diskonMinuman;
                }
            }

            if (dapatDiskon) {
                diskon10Persen = grossTotal * 0.10; // dihitung ulang setelah potongan minuman
                grossTotal -= diskon10Persen;
            }
        }

        double totalAkhir = grossTotal; // grossTotal sudah dikurangi diskon2 jika ada

        // --- Cetak Struk ---
        System.out.println("\n=====================================");
        System.out.println("             STRUK PEMBAYARAN        ");
        System.out.println("=====================================");
        System.out.println("Pesanan :");
        // Tampilkan tiap item pesanan (tanpa loop)
        if (pesananIndex[0] != -1) {
            Menu m = daftarMenu[pesananIndex[0]];
            int hargaTotal = pesananQty[0] * m.getHarga();
            System.out.printf("  %s (%d x Rp%d) = Rp%d\n", m.getNama(), pesananQty[0], m.getHarga(), hargaTotal);
        }
        if (pesananIndex[1] != -1) {
            Menu m = daftarMenu[pesananIndex[1]];
            int hargaTotal = pesananQty[1] * m.getHarga();
            System.out.printf("  %s (%d x Rp%d) = Rp%d\n", m.getNama(), pesananQty[1], m.getHarga(), hargaTotal);
        }
        if (pesananIndex[2] != -1) {
            Menu m = daftarMenu[pesananIndex[2]];
            int hargaTotal = pesananQty[2] * m.getHarga();
            System.out.printf("  %s (%d x Rp%d) = Rp%d\n", m.getNama(), pesananQty[2], m.getHarga(), hargaTotal);
        }
        if (pesananIndex[3] != -1) {
            Menu m = daftarMenu[pesananIndex[3]];
            int hargaTotal = pesananQty[3] * m.getHarga();
            System.out.printf("  %s (%d x Rp%d) = Rp%d\n", m.getNama(), pesananQty[3], m.getHarga(), hargaTotal);
        }

        System.out.println("-------------------------------------");
        System.out.printf("Subtotal        : Rp%,.0f\n", (double) subtotal);
        System.out.printf("Pajak (10%%)     : Rp%,.0f\n", pajak);
        System.out.printf("Biaya Pelayanan : Rp%,.0f\n", (double) BIAYA_PELAYANAN);
        System.out.println("-------------------------------------");
        System.out.printf("Total Kotor     : Rp%,.0f\n", (subtotal + pajak + BIAYA_PELAYANAN));

        // Tampilkan diskon jika ada
        if (!minumanPromo.isEmpty()) {
            System.out.printf("Promo Beli 1 Gratis 1 (%s): -Rp%,.0f\n", minumanPromo, diskonMinuman);
        }
        if (dapatDiskon) {
            System.out.printf("Diskon 10%% ( > Rp100.000): -Rp%,.0f\n", diskon10Persen);
        }
        System.out.println("-------------------------------------");
        System.out.printf("TOTAL BAYAR     : Rp%,.0f\n", totalAkhir);
        System.out.println("=====================================");
    }
}