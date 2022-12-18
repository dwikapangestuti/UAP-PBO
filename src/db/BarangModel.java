package db;

import uapirvandi.Barang;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
// import java.util.logging.Level;
// import java.util.logging.Logger;

public class BarangModel {
    private final Connection CONN;

    public BarangModel() {
        this.CONN = DBHelper.getConnection();
    }
    

    public void addBarang(Barang barang){
        String insert = "INSERT INTO barang  VALUES ('"
                        + barang.getBarcode() + "', '" +
                        barang.getExpired() + "', '" +
                        barang.getNama_produk() + "', '" +
                        barang.getHarga() + "', '" +
                        barang.getJumlah() + "', '" + 
                        barang.getDiskon() + "');";
        try {
            if(CONN.createStatement().executeUpdate(insert) > 0){
                System.out.println("Berhasil Memasukkan Data");
            }
            else{
                System.out.println("Gagal Memasukkan Data");
            }
        } catch (SQLException e) {
            System.out.println("Gagal Memasukkan Data" + e.getMessage());
        }
    }

    public void deleteBarang(Barang barang){
        String delete = "DELETE FROM Barang WHERE Barang.nama_produk = '"
                        + barang.getNama_produk()+ "';";
        
        try {
            if(CONN.createStatement().executeUpdate(delete) > 0){
                System.out.println("Berhasil Hapus Data");
            }
            else{
                System.out.println("Gagal Hapus Data");
            }
        } catch (SQLException e) {
            System.out.println("Gagal Hapus Data" + e.getMessage());
        }
    }

    public ArrayList<Barang> getBarang(){
        String query = "SELECT * FROM barang";
        ArrayList<Barang> barang = new ArrayList<Barang>();
        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);
            while(rs.next()){
                Barang temp = new Barang(rs.getString("nama_produk"), rs.getDouble("harga"), rs.getInt("jumlah"), rs.getDouble("diskon"), rs.getString("barcode"), rs.getString("expired"));
                barang.add(temp);
            }
            System.out.println("Berhasil mengambil data");
        } 
        catch (SQLException e) {
            System.out.println("Gagal mengambil data" + e.getMessage());
        }
        
        return barang;
    }

}
