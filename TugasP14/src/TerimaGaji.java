import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class TerimaGaji extends Gaji {
    
    String url = "jdbc:mysql://localhost:3306/penerimaan_gaji";
    Scanner input = new Scanner(System.in);
    int fixGaji, hadir, id2;

    @Override
    public void Potongan(){
        if (jabatan == "Kepala Direktur"){
            this.potongan = absen*2000000;
        }
        else if (jabatan == "Direktur"){
            this.potongan = absen*1500000;
        }
        else if (jabatan == "Manager"){
            this.potongan = absen*1000000;
        }
        else if (jabatan == "Programer"){
            this.potongan = absen*600000;
        }
        else if (jabatan == "Marketing"){
            this.potongan = absen*500000;
        }
        else if (jabatan == "Akuntan"){
            this.potongan = absen*500000;
        }
    }

   @Override
   public void TotalGaji(){
       this.fixGaji = this.gaji - this.potongan;
   }

   public void Absensi(){
       this.hadir = 30 - this.absen;
   }
   public void database()throws SQLException{
    String sql = "INSERT INTO gaji (id_karyawan,nama_karyawan,jabatan,kehadiran,potongan,total_gaji) VALUES ('"+id+"', '"+nama+"','"+jabatan+"','"+hadir+"','"+potongan+"','"+fixGaji+"')";
    con = DriverManager.getConnection(url,"root","");
    Statement statement = con.createStatement();
    statement.execute(sql);
    System.out.println("Berhasil input data!!!");
    }

   public void Show() throws SQLException{

        System.out.print("Masukkan ID Karyawan : ");
        id2 = input.nextInt();

       String sql ="SELECT * FROM gaji WHERE id_karyawan = " +id2;
       con = DriverManager.getConnection(url,"root","");
       Statement statement = con.createStatement();
       ResultSet result = statement.executeQuery(sql);

       while (result.next()){
       System.out.println("\n\t\tINFO!!!!");
       System.out.print("Nama Karyawan     : ");
       System.out.println(result.getString("nama_karyawan"));
       System.out.print("Nomor ID Karyawan : ");
       System.out.println(result.getInt("id_karyawan"));
       System.out.print("Jabatan           : ");
       System.out.println(result.getString("jabatan"));
       System.out.print("Jumlah Kehadiran  : "); 
       System.out.print(result.getString("kehadiran"));
       System.out.println(" hari");
       System.out.print("Potongan Gaji     : Rp");
       System.out.println(result.getString("potongan"));
       System.out.print("Total Gaji        : Rp");
       System.out.println(result.getInt("total_gaji"));
       }
    }

    public void ubahData()throws SQLException{
        String text3 = "\nUbah Data karyawan";
		System.out.println(text3.toUpperCase());

        try {
            System.out.print("Masukkan ID Karyawan yang akan di ubah : ");
            id2 = 0;
            id2 = input.nextInt();

            String sql = "SELECT * FROM gaji WHERE id_karyawan = " +id2;
            con = DriverManager.getConnection(url,"root","");
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);

           if (result.next()){

                System.out.println("Nama Karyawan ["+result.getString("nama_karyawan")+"]\t: ");
                nama = input.nextLine();

                System.out.print("Jabatan ["+result.getString("jabatan")+"]\t: ");
                jabatan = input.nextLine();

                System.out.print("Jumlah kehadiran ["+result.getString("kehadiran")+"]\t: ");
                int kehadiran = input.nextInt();
                absen = 30 - kehadiran;
               
                Potongan();
                TotalGaji();
               
                sql = "UPDATE gaji SET nama_karyawan='"+nama+"', jabatan= '"+jabatan+"', kehadiran= '"+kehadiran+"', potongan='"+potongan+"', total_gaji='"+fixGaji+"' WHERE id_karyawan='"+id2+"'";

                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil memperbaharui data karyawan (No. ID "+id2+")");
                }
            }
            statement.close();        
        } catch (SQLException e) {
            System.err.println("Terjadi kesalahan dalam mengedit data");
            System.err.println(e.getMessage());
        }
    }

    public void delete() {
		String text4 = "\nHapus Data Karyawan";
		System.out.println(text4.toUpperCase());
		
		try{
	        showdataBase();
	        System.out.print("\nMasukan ID Karyawan yang akan di Hapus : ");
	        Integer Id2= Integer.parseInt(input.nextLine());
	        
	        String sql = "DELETE FROM gaji WHERE id_karyawan = "+Id2;
	        con = DriverManager.getConnection(url,"root","");
	        Statement statement = con.createStatement();
	        
	        if(statement.executeUpdate(sql) > 0){
	            System.out.println("Berhasil menghapus data pegawai (Nomor "+Id2+")");
	        }
	   }
		catch(SQLException e){
	        System.out.println("Terjadi kesalahan dalam menghapus data");
	    }
        catch(Exception e){
            System.out.println("masukan data yang benar");
        }
	}

    public void showdataBase() throws SQLException{
        String sql = "SELECT id_karyawan, nama_karyawan FROM gaji";
        con = DriverManager.getConnection(url,"root","");
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);
        
        while (result.next()){
            System.out.print("\nNama Karyawan\t  : ");
            System.out.print(result.getString("nama_karyawan"));
            System.out.print("\nID Karyawan\t  : ");
            System.out.print(result.getInt("id_karyawan"));
            System.out.println("");
        }

    }

    
}
