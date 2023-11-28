package com.mycompany.mensajeriacliente;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private TextArea textoRecibir;

    @FXML
    private TextField textoEnviar;

    private final SocketTCPClient socketC = new SocketTCPClient("localhost", 49171);
    
    private static int numStart = 0;
    private void start(){
        if (numStart == 0) {
            try { 
                socketC.start();
                numStart++;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            
        }
    }

    @FXML
    private void click() throws IOException {
        start();
        
        if (!"exit".equalsIgnoreCase(textoEnviar.getText())) {
            Platform.runLater(() -> {
                textoRecibir.appendText("\n" + textoEnviar.getText() + "\n");
            });

            socketC.enviarMensajeTexto(textoEnviar.getText());

            Platform.runLater(() -> {
                try {
                    textoRecibir.appendText("\n" + socketC.leerMensajeTexto() + "\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        } else {
            socketC.stop();
        }

    }

}
