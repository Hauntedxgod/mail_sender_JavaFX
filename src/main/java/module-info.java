module com.example.demo3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires spring.context;
    requires spring.beans;
    requires jakarta.mail;
    requires spring.context.support;

//    requires rt;

    opens com.example.demo3 to javafx.fxml;
    exports com.example.demo3;
}