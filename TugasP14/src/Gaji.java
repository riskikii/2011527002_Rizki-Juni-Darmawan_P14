import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;


public class Gaji implements PTABC{
    int id, potongan, gaji,absen,x;
    String nama, jabatan;
    
    static Connection con;

    Scanner input = new Scanner(System.in);
    String url = "jdbc:mysql://localhost:3306/penerimaan_gaji";


    @Override
    public void NoPegawai() throws SQLException{

        System.out.print("Inputkan Nomor ID : ");
        id = input.nextInt();
    }

    @Override
    public void NamaPegawai(){
        System.out.print("Inputkan Nama     : ");
        this.nama = input.nextLine();
    }

    @Override
    public void Jabatan(){
        System.out.println("\nJABATAN");
        System.out.println("1. Kepala Direktur : ");
        System.out.println("2. Direktur : ");
        System.out.println("3. Manager : ");
        System.out.println("4. Programer : ");
        System.out.println("5. Marketing : ");
        System.out.println("6. Akuntan : ");
        while (x<1||x>6){
            System.out.print("Pilih Jabatan (isi dengan nomor) : ");
            this.x = input.nextInt();
            if ((x<1)||(x>6)){
                System.out.println("Pilihan tidak tersedia");
            }else break;
        }
 
        if (x == 1){
            this.jabatan = "Kepala Direktur";
        }
        else if (x == 2){
            this.jabatan = "Direktur";
        }
        else if (x == 3){
            this.jabatan = "Manager";
        }
        else if (x == 4){
            this.jabatan = "Programer";
        }
        else if(x == 5){
            this.jabatan = "Marketig";
        }
        else if (x == 6){
            this.jabatan = "Akuntan";
        }else{
            System.out.println("Pilihan tidak tersedia");
        }
    }

    @Override
    public void JumlahHariMasuk(){
        while (absen<1||absen>30){
        System.out.print("Inputkan Daftar Tidak Hadir (1-30):");
        this.absen = input.nextInt();
            if(absen<1||absen>30){
                System.out.println("Masukkan jumlah yang benar");
            }else break;
        }
    }

    @Override
    public void GajiPokok(){
        if (jabatan == "Kepala Direktur"){
            this.gaji = 60000000;
        }
        else if (jabatan == "Direktur"){
            this.gaji = 45000000;
        }
        else if (jabatan == "Manager"){
            this.gaji = 30000000;
        }
        else if (jabatan == "Programer"){
            this.gaji = 18000000;
        }
        else if (jabatan == "Marketing" || jabatan == "Akuntan"){
            this.gaji = 15000000;
        }
    }


    public void Potongan(){
    }

    public void TotalGaji(){
    }
            
}
