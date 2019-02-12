
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author i15048
 */
public class SimulasiKredit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Silahkan masukkan input : ");
        System.out.print("Pinjaman (juta): ");
        double pinjaman = sc.nextDouble();
        System.out.print("Suku bunga tahunan (%/bulan) : ");
        double bunga = sc.nextDouble();
        System.out.print("Tenor : ");
        int tenor = sc.nextInt();
        System.out.println("Biaya admin : ");
        double biayaAdmin = sc.nextDouble();
        System.out.println("============Output simulasi====================");
        
        KreditBungaEfektif simulasiCicilan = new KreditBungaEfektif(pinjaman, bunga, tenor, biayaAdmin);
        simulasiCicilan.simulasi();
        double [] bungaPerbulan = simulasiCicilan.getBungaPerBulan();
        double [] totalAngsuran = simulasiCicilan.getTotalAngsuran();

        for (int i = 0; i < tenor; i++) {
            System.out.println("periode ke: "+ (i+1));
            System.out.printf("Angsuran pokok : Rp %.2f" , simulasiCicilan.getAngsuranPokok());
            System.out.println();
            System.out.printf("Bunga : Rp %.2f", bungaPerbulan[i]);
            System.out.println();
            System.out.println("Biaya admin : Rp "+ biayaAdmin );
            System.out.printf("Total angsuran : Rp %.2f", totalAngsuran[i] );
            System.out.println();
        }
        System.out.println("=================================================");
        System.out.printf("Total bunga : Rp %.2f ", simulasiCicilan.getTotalBunga());
        System.out.println();
        System.out.printf("Total angsuran pokok : Rp %.2f ", simulasiCicilan.getTotalAngsuranPokok());
        System.out.println();
        System.out.printf("Total admin : Rp %.2f", simulasiCicilan.getTotalAdmin());
        System.out.println();
        System.out.printf("Total keseluruhan : Rp %.2f" , simulasiCicilan.getTotalKeseluruhan());
    }
}

class KreditBungaEfektif{
    private double pinjaman;
    private double persenBunga;
    private int tenor;
    private double angsuranPokok;
    private double [] bungaPerBulan;
    private double biayaAdmin;
    private double [] totalAngsuran;
    private double totalAdmin;
    private double totalBunga;
    private double totalAngsuranPokok;
    private double totalKeseluruhan;

    public KreditBungaEfektif() {
    }

    public double getAngsuranPokok() {
        return angsuranPokok;
    }

    public void setAngsuranPokok(double angsuranPokok) {
        this.angsuranPokok = angsuranPokok;
    }

    public KreditBungaEfektif(double pinjaman, double persenBunga, int tenor, double biayaAdmin) {
        this.pinjaman = pinjaman;
        this.persenBunga = persenBunga;
        this.tenor = tenor;
        this.biayaAdmin = biayaAdmin;
    }

    public double getTotalKeseluruhan() {
        return totalKeseluruhan;
    }

    public double getPersenBunga() {
        return persenBunga;
    }

    public double [] getBungaPerBulan() {
        return bungaPerBulan;
    }

    public double getPinjaman() {
        return pinjaman;
    }

    public double getTenor() {
        return tenor;
    }   

    public double[] getTotalAngsuran() {
        return totalAngsuran;
    }

    public double getBiayaAdmin() {
        return biayaAdmin;
    }

    public double getTotalAdmin() {
        return totalAdmin;
    }

    public double getTotalAngsuranPokok() {
        return totalAngsuranPokok;
    }

    public double getTotalBunga() {
        return totalBunga;
    }

    public void setPinjaman(double pinjaman) {
        this.pinjaman = pinjaman;
    }

    public void setTenor(int tenor) {
        this.tenor = tenor;
    }

    public void setBungaPerBulan(double [] bungaPerBulan) {
        this.bungaPerBulan = bungaPerBulan;
    }

    public void setTotalAngsuran(double[] totalAngsuran) {
        this.totalAngsuran = totalAngsuran;
    }

    public void setBiayaAdmin(double biayaAdmin) {
        this.biayaAdmin = biayaAdmin;
    }

    public void setPersenBunga(double persenBunga) {
        this.persenBunga = persenBunga;
    }

    public void setTotalAdmin(double totalAdmin) {
        this.totalAdmin = totalAdmin;
    }

    public void setTotalAngsuranPokok(double totalAngsuranPokok) {
        this.totalAngsuranPokok = totalAngsuranPokok;
    }

    public void setTotalBunga(double totalBunga) {
        this.totalBunga = totalBunga;
    }
       
    public void simulasi(){
        double cicilanPokok = this.pinjaman / this.tenor;
        this.angsuranPokok = cicilanPokok;
        this.bungaPerBulan = new double[tenor];
        this.totalAngsuran = new double [tenor];
        for (int i = 0; i <tenor; i++) {
            this.bungaPerBulan[i] = ((this.pinjaman - (i * cicilanPokok)) * this.persenBunga)/(12*100);
            this.totalAngsuran[i] = this.bungaPerBulan[i] + cicilanPokok + biayaAdmin;
            this.totalBunga += bungaPerBulan[i];
            this.totalAngsuranPokok +=cicilanPokok;
            this.totalAdmin += this.biayaAdmin;
        }
        this.totalKeseluruhan = this.totalBunga + this.totalAngsuranPokok + this.totalAdmin;
    }
}
