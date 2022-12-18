package uapirvandi;

import java.io.IOException;

import db.MakananModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DataMakananController {
    MakananModel makanan = new MakananModel();

    @FXML
    private Button btn_InputAdd;

    @FXML
    private Button btn_InputBack;

    @FXML
    private Button btn_InputCancel;

    @FXML
    private ComboBox<?> dropdownKategori;

    @FXML
    private Text lblDiskonProduk;

    @FXML
    private Text lblExpiredProduk;

    @FXML
    private Text lblHargaProduk;

    @FXML
    private Text lblJumlahProduk;

    @FXML
    private Text lblKategori;

    @FXML
    private Text lblNamaProduk;

    @FXML
    private Text lbl_IdProduk;

    @FXML
    private TextField lbl_idProduk;

    @FXML
    private TextField lbl_inputDiskon;

    @FXML
    private TextField lbl_inputHarga;

    @FXML
    private TextField lbl_inputJumlah;

    @FXML
    private TextField lbl_inputNama;

    @FXML
    private TextField lbl_inputdt;

    @FXML
    void addData(ActionEvent event) throws IOException {
        Makanan makan = new Makanan(lbl_inputNama.getText(),Double.parseDouble(lbl_inputHarga.getText()), Integer.parseInt(lbl_inputJumlah.getText()), Double.parseDouble(lbl_inputDiskon.getText()), Integer.parseInt(lbl_idProduk.getText()), Integer.parseInt(lbl_inputdt.getText()));
        makanan.addMakanan(makan);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataMakanan.fxml"));
        Parent root = loader.load();
                    
        Stage stage = (Stage) btn_InputAdd.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void backToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Kategori.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btn_InputBack.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void cancel_InputData(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataMakanan.fxml"));
        Parent root = loader.load();
                    
        Stage stage = (Stage) btn_InputAdd.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void pilihKategori(ActionEvent event) {

    }

}