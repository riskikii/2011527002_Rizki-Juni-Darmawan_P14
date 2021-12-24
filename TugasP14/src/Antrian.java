import java.util.Scanner;
import java.util.InputMismatchException;

public class Antrian {

    public static String queue[]= new String[5];
    public static int x = 0;

    public Antrian (int i){

    }

    public boolean queueStorage(){
        if (x < queue.length){
            return true;
        }else
        {
            return false;
        }
    }

    public void createQueue(){
        String nama;
        System.out.print("Masukkan Nama Pengantri : ");
        Scanner input = new Scanner(System.in);
        nama = input.nextLine();
        queue[x] = nama;
        x++;        
    }

    public void removeQueue(){
        x--;
        for(int i=0; i<x; i++){
            queue[i] = queue [i+1];
        }
    }

    public void displayQue(){
        System.out.println("Daftar Antrian : ");
        for (int i = 0; i < x; i++){
            System.out.println(+i+1+ "." +queue[i]);
        }
        System.out.println("");
    }


}
