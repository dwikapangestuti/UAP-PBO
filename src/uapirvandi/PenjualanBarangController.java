package uapirvandi;

import static db.DBHelper.getConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import db.BarangModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PenjualanBarangController implements Initializable{
    BarangModel barang = new BarangModel();

    @FXML
    private Button btnBarang;

    @FXML
    private Button btnHapus;

    @FXML
    private Button btnMakanan;

    @FXML
    private Button btn_InputBack;

    @FXML
    private TextField fieldHarga;

    @FXML
    private TextField fieldJumlah;

    @FXML
    private TextField fieldNama;

    @FXML
    private Label lblHarga;

    @FXML
    private Label lblJumlah;

    @FXML
    private Label lblNamaProduk;

    @FXML
    private TableColumn<Produk, Double> tblHarga;

    @FXML
    private TableColumn<Produk, Integer> tblJumlah;

    @FXML
    private TableColumn<Produk, String> tblNamaProduk;

    @FXML
    private TableView<Produk> tblView;
    
    @FXML
    void backToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btn_InputBack.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void toBarang(ActionEvent event) {

    }

    @FXML
    void toDelete(ActionEvent event) throws IOException {
        Barang brg = new Barang(fieldNama.getText(),Double.parseDouble(fieldHarga.getText()), Integer.parseInt(fieldJumlah.getText()));
        barang.deleteBarang(brg);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PenjualanBarang.fxml"));
        Parent root = loader.load();
                    
        Stage stage = (Stage) btnHapus.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void toMakanan(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PenjualanMakanan.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btn_InputBack.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    public void initialize(URL url, ResourceBundle rb){
        showProduk();
    }

    public ObservableList<Produk> getProdukList(){
        ObservableList<Produk> kategoriList = FXCollections.observableArrayList();
        Connection CONN = getConnection();
        String query = "SELECT Makanan.nama_produk, Makanan.harga, Makanan.jumlah FROM Makanan UNION SELECT Barang.nama_produk, Barang.harga, Barang.jumlah FROM Barang;";
        Statement st;
        ResultSet rs; 
        
        try{
            st = CONN.createStatement();
            rs = st.executeQuery(query);
            Produk makanan;
            while(rs.next()){
                makanan = new Produk(rs.getString("nama_produk"),rs.getDouble("harga"), rs.getInt("jumlah"));
                kategoriList.add(makanan);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return kategoriList;
    }
     
    public void showProduk(){
        ObservableList<Produk> list = getProdukList();
        tblNamaProduk.setCellValueFactory(new PropertyValueFactory<Produk ,String>("nama_produk"));
        tblHarga.setCellValueFactory(new PropertyValueFactory<Produk ,Double>("harga"));
        tblJumlah.setCellValueFactory(new PropertyValueFactory<Produk ,Integer>("jumlah"));
        
        tblView.setItems(list);
    }

}
