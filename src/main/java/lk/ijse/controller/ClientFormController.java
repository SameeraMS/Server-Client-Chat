package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientFormController {

    public TextArea chatArea;
    public TextField txtMsg;
    private Socket socket;

    public void initialize() {
        new Thread(() -> {
            boolean isStart = true;

            try {
                socket = new Socket("localhost", 3005);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                while (isStart) {
                    String msg = dataInputStream.readUTF();
                    chatArea.appendText("Server: " + msg+"\n");

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void sendOnAction(ActionEvent actionEvent) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String message = txtMsg.getText();
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
            chatArea.appendText("Client: " + message+"\n");
            txtMsg.clear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
