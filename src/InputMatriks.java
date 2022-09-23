/*
DESKRIPSI : Program input matriks.
Menerima input baris m dan kolom n. Lalu dibuat matriks dengan ukuran tersebut.
Kemudian dilakukan input koefisien aij dan koefisien bij dalam bentuk matriks.
Semua input dilakukan melalui keyboard. Matriks dibuat dengan array dalam array
*/

import java.util. *;
public class InputMatriks{
    static void printMatriks(double[][] matriks, int m, int n){
        int i,j;
        for (i = 0; i < m; i++){
            for (j = 0; j < n; j++){
                System.out.print(matriks[i][j]+" ");
            }
            System.out.println();
        }
    }

    static double[][] inputMatriks(int m,int n){
        Scanner in = new Scanner(System.in);
        int i,j;
    
        //membuat matriks ukuran m x ns
        double[][] matriks = new double[m][n]; // matriks ukuran m baris n kolom sudah terinisialisasi

        //prosedur memasukkan elemen ke matriks
        // memasukkan elemen harus dalam satu baris yang dipisahkan oleh satu buah spasi
        // contoh : 1 2 3
        for (i = 0; i < m; i++){
            for (j = 0; j < n; j++){
                matriks[i][j] = in.nextInt();
            }
        } 

        return (matriks);
    }
    
    public static void main(String[] args){
        Scanner in = new Scanner (System.in);
        int m,n,i,j;
        //input m
        System.out.print("Masukkan ukuran baris matriks (m): ");
        m = in.nextInt();

        //input n
        System.out.print("Masukkan ukuran kolom matriks (n): ");
        n = in.nextInt();

        double matriks[][] = inputMatriks(m,n);

        printMatriks(matriks,m,n);
    }
}