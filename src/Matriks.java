import java.util. *;

class Matriks{
    /* 
    BERISI PRIMITIF-PRIMITIF DARI MATRIKS
    - INPUT DAN OUTPUT MATRIKS
    - COPY MATRIKS
    - SWAP BARIS
    */
    
    /* KAMUS LOKAL */
    int i,j,m,n;
    double[][] matriks;

    /* KONSTRUKTOR MATRIKS */
    Matriks(){}

    /* PRIMITIF INPUT MATRIKS */
    int inputRow(){ // memasukkan baris dari matriks
        Scanner in = new Scanner (System.in);
        System.out.print("Masukkan baris (m): ");
        m = in.nextInt();
        this.m = m;
        return m;
    }

    int inputCol(){ // memasukkan kolom dari matriks
        Scanner in = new Scanner (System.in);
        System.out.print("Masukkan kolom (n): ");
        n = in.nextInt();
        this.n = n;
        return n;
    }


    double[][] makeMatrix(int m, int n){ // membuat matriks sembarang dan mengembalikannya
        double[][] matriks = new double[m][n];
        this.matriks = matriks;
        return matriks;
    }

    void inputMatriks (double[][] matriks, int m, int n){ // memasukkan nilai-nilai matriks
        Scanner in = new Scanner (System.in); 
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                matriks[i][j] = in.nextDouble();
            }
        }
    }
    
    /* SELEKTOR */
    int getRow(double[][] matriks){
        // ambil baris
        return (matriks.length);
    }

    int getCol(double[][] matriks){
        // ambil kolom
        return (matriks[0].length);
    }
    
    boolean isSquare (double[][] matriks){ 
        //menentukan apakah sebuah matriks memiliki row dan col yang sama atau tidak
        return (getRow(matriks) == getCol(matriks));
    }

    /* PRIMITIF OUTPUT MATRIKS */

    void printMatriks(double[][] matriks, int m, int n){ // mencetak matriks matriks
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                System.out.print(matriks[i][j] + " ");
            }
            System.out.println();
        }
    }

    /* PRIMITIF COPY MATRIKS */
    double[][] copyMatriks(double[][] matriks, int m, int n){ // membuat copy dari matriks masukkan
        double[][] copymatriks = new double[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n ; j++){
                copymatriks[i][j] = matriks[i][j];
            }
        }
        return copymatriks;
    }

    /* OPERASI-OPERASI MATRIKS */
    double[][] makeIdentitas (int m, int n){
        // membuat matriks identitas dg ukuran m x n
        double[][] identitas = makeMatrix(m,n);
        for (int i = 0 ; i < m; i++){
            for (int j = 0; j < n; j++){
                if ( i == j){
                    identitas[i][j] = 1;
                }else{
                    identitas[i][j] = 0;
                }
            }
        }
        return identitas;
    }

    boolean checkIdentitas(double[][] identitas, int m, int n){
        boolean isIdentitas = true;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n ; j++){
                if (i == j){
                    if (identitas[i][j] != 1){
                        isIdentitas = false;
                    }
                }else{
                    if (identitas[i][j] != 0){
                        isIdentitas = false;
                    }
                }
            }
        }
        return isIdentitas;
    }

    void swapBaris(int i1, int i2){ // fungsi swap baris
        for (int i = 0; i < n; i++) {
            double temp = matriks[i1][i];
            matriks[i1][i] = matriks[i2][i];
            matriks[i2][i] = temp;
            }
        }

    int cariIndex(int idx){
        boolean found = false;
        int i = 0;
        while (!(found) && (i<n)){
            if (matriks[idx][i] != 0){
                found = true;
            }
            else{
                i++;
            }
        }
        if (found==true){
            return i;
        }
        else{
            return n;
        }
    }

    void sortingMatrix(){
        int i,j;
        int p = 0;
        if (m > 1){
            for (i = 0; i < m; i++){
                int rowMax = i;
                for (j = i + 1; j < m; j++){
                    int foundIndexLead = cariIndex(j);
                    if (foundIndexLead < cariIndex(rowMax)){
                        rowMax=j;
                        p += 1;
                    }
                }
            swapBaris(i, rowMax);
            }
        }
        System.out.print("Berapa kali switch? "+p+"\n");
    }

    double[][] perkalian(double[][] matA, double[][] matB){
        // melakukan perkalian matriks
        double[][] hasil = makeMatrix(matA.length, matB[0].length);

        for (int i = 0; i < matA.length; i++){
            for (int j = 0; j < matB[0].length; j++){
                hasil[i][j] = 0;
                for (int k = 0; k < matB.length; k++){
                    hasil[i][j] += matA[i][k] * matB[k][j];
                }
            }
        }

        return hasil;
    }

    double[][] makeMinor (double[][] matriks, int m, int n, int b, int k){
        // menerima input matriks dan mengeluarkan minor dari indeks baris b dan kolom k
        double[][] kof = new double[m-1][n-1]; // membuat matriks kofaktor
        int bkof = 0; // baris dari matriks kofaktor
        for (int i = 0; i < m; i++){
            int kkof = 0; // kolom dari matriks kofaktor
            for (int j = 0; j < n; j++){
                if (i != b && j != k){
                    kof[bkof][kkof] = matriks[i][j];
                    kkof++;
                    if(kkof == n-1){
                        kkof = 0;
                        bkof++;
                    }
                }
            }
        }
        return kof;
    }

    /* SISTEM PERSAMAAN LINEAR */

    void Gauss(double[][] matriks, int m, int n){
        // Melakukan elminasi Gauss pada matriks inputan
        /* KAMUS LOKAL */
        int i,j,k,l;
        double rasio, rasio1, simpan;
        boolean zero;

        /* ALGORITMA */
        // MULAI MEMBUAT SATU UTAMA
        // Membuat elemen pertama bukan 0 pada tiap baris menjadi 1
        for (i = 0; i < m; i++){
            j = 0;
            simpan = 1;
            zero = true;
            //ambil elemen pertama pada baris yang bukan 0
            while (j < n && zero == true){
                if (matriks[i][j] != 0){
                    simpan = matriks[i][j];
                    zero = false;
                }
                j++;
            }

            rasio = 1 / simpan;

            for (j = 0; j < n ; j++){
                matriks[i][j] *= rasio;
            }
        }

        // Swap Baris
        /*int var = 0; 
        int jumlahBaris = matriks.length; //menghitung panjang baris matriks
        int jumlahKolom = matriks[0].length; //menghitung panjang kolom matriks

        for(k = 0; k < jumlahBaris; k++){
            if (jumlahBaris <= var){  //panjang baris kurang dari 0
                break;
            }
            i = k;
            while(matriks[i][var] == 0){ //kondisi pengecekan kolom apakah ada 0 atau tidak
                i++;
                if(jumlahBaris == i){ 
                    i = k;
                    var++;
                    if(jumlahKolom == var){ //ketika jumlah kolom semua sudah dicek
                        break;
                    }
                }

            }
            matriks = swapBaris(matriks, i, k); //menukar baris pada matriks
        }*/

        // buat copy matrix
        double[][] copyMatriks = copyMatriks(matriks, m,n);
        // copy matrix sudah terdefinisi
        
        System.out.print("Matriks setelah diswitch:\n");
        printMatriks(matriks, m, n);
        System.out.print("\n");

        // SATU UTAMA SUDAH TERBENTUK

        // MULAI MELAKUKAN ALGORITMA MATTHEW

        // Bagian Pengurangan Baris
        // Definisi algoritma seperti yg sudah dibahas oleh Kelompok
        // Langkah 1) Bn - B(n-1); 2) Bn * 1/Elm pertama Bn yang bukan 0; n adalah baris
        
        for (i = 1; i < m; i++){ // ulang untuk per baris
            // jika baris sudah memenuhi syarat utama maka tidak perlu melakukan OBE
            int countSatuUtama = 0;
            j = 0;
            while(j <= i-1){
                if (matriks[i][j] == 0){
                    countSatuUtama++;
                }
                j++;
            }

            if(countSatuUtama <= i-1){ // belum satu utama lakukan OBE
                for (k = 0; k < i; k++){ // ulang untuk k- kali
                    simpan = 1;
                    zero = true;
                    for (j = 0; j < n ; j++){
                        if (matriks[i][j] != 0){
                            matriks[i][j] -= copyMatriks[k][j]; // 1)

                            if (matriks[i][j] != 0 && zero == true){
                                simpan = matriks[i][j];
                                zero = false;
                            }

                            matriks[i][j] *= 1 / simpan;

                            copyMatriks[i][j] = matriks[i][j]; // copy hasil yang sudah dikurangi ke dalam copy matriks
                        }
                    }
                }
            }
            
        }
        
        // Pengurangan Baris Selesai

        // ALGORITMA MATTHEW SELESAI
        this.matriks = matriks;
    }

    void GaussJordan(double[][] matriks, int m, int n){
        // melakukan eliminasi gauss-jordan

        /* KAMUS LOKAL */
        int i,j,k,l;
        double simpan;
        boolean one;

        /* ALGORITMA */
        //Melalui eliminasi gauss terlebih dahulu
        Gauss(matriks,m,n);
        
        //Mulai eliminasi Gauss-Jordan
        for (i = 1; i < m; i++){
            simpan = 1;
            one = true;
            for (j = 0; j < n; j++){
                // ambil elemen pertama yang sudah pasti 1 pada tiap baris
                if (matriks[i][j] == 1 && one == true){
                    for (k = 1; k <= i; k++){
                        simpan = matriks[i-k][j]; // ambil elemen atasnya
                        one = false;
                        // harus loop di kolomnya
                        for (l = 0; l<n; l++){
                            matriks[i-k][l] -= simpan * matriks[i][l];
                        }
                    }
                }
            }
        }
        this.matriks = matriks;
    }

    double[][] SPLInvers(double[][] matriks, int m, int n){
        double[][] matA = copyMatriks(matriks, m, n-1);
        double[][] matB = copyMatriks(matriks, m, 1);

        //isi matriks A
        for(int i = 0; i < m; i++){
            for (int j = 0; j < n-1; j++){
                matA[i][j] = matriks[i][j];
            }
        }

        //isi matriks B
        for(i = 0; i < m; i++){
            matB[i][0] = matriks[i][n-1];
        }

        // buat invers matA
        double[][] invMatA = InversAdj(matA,getRow(matA),getCol(matA));

        // hasil nya

        double[][] x = perkalian(invMatA, matB);
        printMatriks(x,getRow(x),getCol(x));

        return x;
    }

    double Determinan(double[][] matriks, int m, int n){
        // mencari determinan dengan menggunakan reduksi baris
        /* KAMUS LOKAL */
        int i,j,k,l;
        double rasio, rasio1, simpan;
        boolean zero;
        int det = 1;
        double pembilang = 1;
        double penyebut = 1;
        int p = 0; //jumlah switch yang terjadi
        
        if (m != 2 && n != 2){
            sortingMatrix();
        }

        /* ALGORITMA */
        // MULAI MEMBUAT SATU UTAMA
        // Membuat elemen pertama bukan 0 pada tiap baris menjadi 1
        for (i = 0; i < m; i++){
            j = 0;
            simpan = 1;
            zero = true;
            //ambil elemen pertama pada baris yang bukan 0
            while (j < n && zero == true){
                if (matriks[i][j] != 0){
                    simpan = matriks[i][j];
                    penyebut *= 1/simpan;
                    zero = false;
                }
                j++;
            }

            rasio = 1 / simpan;

            for (j = 0; j < n ; j++){
                matriks[i][j] *= rasio;
            }
        }

        // Swap Baris
        

        /*for (i = 0; i < n; i++) {
            int idx = i;
            int det = 1;
            while (matriks[idx][i] == 0 && p < n){
                idx++;

            }
            if (idx == n){
                continue;
            }
            if (idx != i) {
                for (j = 0; j < n; j++) {
                    swapBarisDet(matriks, idx, j, i, j);
                    det = (int)(det * Math.pow(-1, idx-i));
                    System.out.print(det+"\n");
                }
            }
        }*/

        // buat copy matrix
        double[][] copyMatriks = copyMatriks(matriks, m,n);
        // copy matrix sudah terdefinisi

        System.out.print("Matriks setelah diswitch:\n");
        printMatriks(matriks, m, n);
        System.out.print("\n");

        // SATU UTAMA SUDAH TERBENTUK

        // MULAI MELAKUKAN ALGORITMA MATTHEW

        // Bagian Pengurangan Baris
        // Definisi algoritma seperti yg sudah dibahas oleh Kelompok
        // Langkah 1) Bn - B(n-1); 2) Bn * 1/Elm pertama Bn yang bukan 0; n adalah baris
        
        for (i = 1; i < m; i++){ // ulang untuk per baris
            int countSatuUtama = 0;
            j = 0;
            while(j <= i-1){
                if (matriks[i][j] == 0){
                    countSatuUtama++;
                }
                j++;
            }

            if(countSatuUtama <= i-1){
                for (k = 0; k < i; k++){ // ulang untuk k- kali
                    simpan = 1;
                    zero = true;
                    for (j = 0; j < n ; j++){
                        if (matriks[i][j] != 0){
                        matriks[i][j] -= copyMatriks[k][j]; // 1)

                        if (matriks[i][j] != 0 && zero == true){
                            simpan = matriks[i][j];
                            zero = false;
                    }

                    matriks[i][j] *= 1 / simpan;

                    copyMatriks[i][j] = matriks[i][j]; // copy hasil yang sudah dikurangi ke dalam copy matriks
                }
                penyebut *= 1/simpan;
                }
            }
        }
    }
        
        // Pengurangan Baris Selesai

        // ALGORITMA MATTHEW SELESAI

        for (i = 0; i < m; i++){
            for (j = 0; j < n; j++){
                if (i == j){
                    pembilang *= matriks[i][j];
                }
            }
        }

        return (Math.pow(-1,p) * pembilang/penyebut);
    }

    void SarrusCrammer(double[][] matriks, int m, int n){
        /* Melakukan proses sarrus-crammer untuk mendapatkan hasil dari 
        sebuah SPL */

        /* KAMUS LOKAL */
        double[][] copy = copyMatriks(matriks,m,n);
        double[][] matA = copyMatriks(matriks, m, n-1); // ukuran barisnya selalu kurang dari 1
        double[][] matB = copyMatriks(matriks, m, 1); // hanya m baris x 1 kolom. diisi elemen paling ujung
        double det; // determinan dasar
        double detx; // determinan berikutnya


        /* ALGORITMA */
        //hitung determinan awalnya
        det = Determinan(copy,m,n);

        //isi matriks A
        for(int i = 0; i < m; i++){
            for (int j = 0; j < n-1; j++){
                matA[i][j] = matriks[i][j];
            }
        }

        //isi matriks B
        for(i = 0; i < m; i++){
            matB[i][0] = matriks[i][n-1];
        }
        
        //proses tukar tempat dan cari nilai x1..xN
        //dilakukan dalam suatu loop selama N kali dengan N maks adalah jumlah baris-1
        for (i = 0; i < n-1; i++){
            for (j = 0; j < m; j++){
                matA[j][i] = matB[j][0]; // menukar kolom ke N dengan matriks B
            }
            detx = Determinan(matA, getRow(matA), getCol(matA)); // menghitung determinan dari matriks A
            System.out.println("Nilai x"+(i+1)+" adalah "+(detx/det));
            matA = copyMatriks(matriks, m, n-1); // mengembalikan matriks A ke awal
        }
    }

    double DeterminanKof(double[][] matrix,int m,int n){
        //menghitung determinan dengan melakukan ekspansi kofaktor
        // melakukan rekursi
        /* KAMUS LOKAL */
        int sign = 1;
        double det = 0;
        if (m == 1 && n ==1){ // basis rekursi
            return (matrix[0][0]);
        }else{
            for (int i = 0; i < m; i++){
                double[][] kofaktor = new double[m-1][n-1];
                int row1,col1;
                int k,l;
                row1 = 0;
                for (k = 0; k < m; k++){
                    col1 = 0;
                    for (l = 0; l < n; l++){
                        if (l != i && k != 0){
                            kofaktor[row1][col1] = matrix[k][l];
                            col1 += 1;
                            if (col1 == n-1){
                                col1 = 0;
                                row1 += 1;
                            }
                        }
                    }
                }
                det += (sign * matrix[0][i] * DeterminanKof(kofaktor,kofaktor.length,kofaktor[0].length));
                sign *= -1;
            }
        }
        return det;
    }
    
    double[][] InversAdj (double[][] matrix, int m, int n){
        int i,j;
        double[][] copy = copyMatriks(matrix,m,n);
        double[][] kof = makeMatrix(m,n);

        double det = Determinan(copy, m,n);

        for (i = 0; i < m; i++){
            for (j = 0; j < n ; j++){
                double[][] minor = makeMinor(matrix,m,n,i,j);
                kof[i][j] = Math.pow(-1,i+j+2) * Determinan(minor, getRow(minor), getCol(minor));
            }
        }

        //matriks.printMatriks(kof,kof.length,kof[0].length);

        // transpose

        double[][] trans = copyMatriks(kof,getRow(kof),getCol(kof));

        for (i = 0; i<m; i++){
            for (j = 0; j < n; j++){
                trans[i][j] = Math.round((1/det)*kof[j][i]);
            }
        }

        return trans;
    }
}