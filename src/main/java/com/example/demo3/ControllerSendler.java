package com.example.demo3;

//import com.example.demo.service.impl.EmailServiceImpl;

import com.example.demo3.service.impl.EmailServiceImpl;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Component
public class ControllerSendler {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox InfoMail;

    @FXML
    private CheckBox kfMail;

    @FXML
    private Button multi;


    public ControllerSendler() {
    }

    private EmailServiceImpl emailService = new EmailServiceImpl();

    @Autowired
    public ControllerSendler(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    private ArrayList<String> list = new ArrayList<>();

    @FXML
    private Button mailSendButton;


    @FXML
    void initialize() {
        mailSendButton.setOnAction(e ->{
            if (kfMail.isSelected()){
                list.add(kfMail.getText());

            }
            if (InfoMail.isSelected()){
                list.add(InfoMail.getText());
            }
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setMultiSelectionEnabled(true);
            int option = fileChooser.showOpenDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
            }
            emailService.sendMail( fileChooser.getSelectedFiles() , list.toArray(new String[list.size()]) , "First" , "Text"  );
            System.out.println("Отправил на почту -> " + list);


            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);

            VBox vbox = new VBox(new Text("СООБЩЕНИЕ ОТПРАВЛЕНО") , new Button("Ok"));
            vbox.setAlignment(Pos.CENTER);
            vbox.setPadding(new Insets(50));

            dialogStage.setScene(new Scene(vbox));
            dialogStage.show();

        });


    }
}
