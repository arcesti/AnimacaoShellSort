module com.example.trabalhoanimacao {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.trabalhoanimacao to javafx.fxml;
    exports com.example.trabalhoanimacao;
}