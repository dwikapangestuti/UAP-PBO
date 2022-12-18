package uapirvandi;

// import java.sql.Array;
import java.util.ArrayList;

public class Penjualan implements ProductCounter {
    private ArrayList<Produk> Produk = new ArrayList<Produk>();
    private int jumlahProduk;
    private int stok;

    public Penjualan(int jumlahProduk, int stok) {
        this.jumlahProduk = jumlahProduk;
        this.stok = stok;
    }

    public ArrayList<Produk> getProduk() {
        return Produk;
    }
    public int getJumlahProduk() {
        return jumlahProduk;
    }
    public int getStok() {
        return stok;
    }
    @Override
    public int hitungJumlahProduk() {
        return jumlahProduk;
    }
    @Override
    public int hitungHargaProduk() {
        return 0;
    }    
}
