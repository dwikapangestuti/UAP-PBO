package uapirvandi;

import static db.DBHelper.getConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class KategoriController implements Initializable {

    @FXML
    private Button btnBarang;

    @FXML
    private Button btnMakanan;

    @FXML
    private Button btn_InputBack;

    @FXML
    private TableView<Produk> tbView;

    @FXML
    private TableColumn<Produk, Double> tblHarga;

    @FXML
    private TableColumn<Produk, String> tblNamaProduk;

    @FXML
    void backToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnBarang.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @FXML
    void toBarang(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataBarang.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnBarang.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void toMakanan(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataMakanan.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnMakanan.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        showMakanan();
    }

    public ObservableList<Produk> getKategoriList(){
        ObservableList<Produk> kategoriList = FXCollections.observableArrayList();
        Connection CONN = getConnection();
        String query = "SELECT Makanan.nama_produk, Makanan.harga FROM Makanan UNION SELECT Barang.nama_produk, Barang.harga FROM Barang;";
        Statement st;
        ResultSet rs; 
        
        try{
            st = CONN.createStatement();
            rs = st.executeQuery(query);
            Produk makanan;
            while(rs.next()){
                makanan = new Produk(rs.getString("nama_produk"),rs.getDouble("harga"));
                kategoriList.add(makanan);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return kategoriList;
    }
     
    public void showMakanan(){
        ObservableList<Produk> list = getKategoriList();
        tblNamaProduk.setCellValueFactory(new PropertyValueFactory<Produk ,String>("nama_produk"));
        tblHarga.setCellValueFactory(new PropertyValueFactory<Produk ,Double>("harga"));
        
        tbView.setItems(list);
    }
}

