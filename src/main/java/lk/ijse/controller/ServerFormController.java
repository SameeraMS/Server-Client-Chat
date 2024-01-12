package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerFormController {
    public TextArea chatArea;
    public TextField txtMsg;
    private Socket clientSocket;


    public void initialize() {
        new Thread(() -> {
            boolean isStart = true;
            try {
                ServerSocket serverSocket = new ServerSocket(3005);
                chatArea.appendText("Server Started\n");

                clientSocket = serverSocket.accept();
                chatArea.appendText("Accepted client\n");

                while (isStart) {
                                DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
                                String message = dataInputStream.readUTF();
                                chatArea.appendText("Client : " + message + "\n");
                    }



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void sendOnAction(ActionEvent actionEvent) {
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                String msg = txtMsg.getText();
                dataOutputStream.writeUTF(msg);
                dataOutputStream.flush();
                chatArea.appendText("Server : " + msg + "\n");
                txtMsg.clear();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


    }
}
