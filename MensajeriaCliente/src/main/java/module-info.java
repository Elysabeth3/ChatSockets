module com.mycompany.mensajeriacliente {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.mensajeriacliente to javafx.fxml;
    exports com.mycompany.mensajeriacliente;
}
