module gal.iespazodamerce.visteisminasfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens gal.iespazodamerce.visteisminasfx to javafx.fxml;
    exports gal.iespazodamerce.visteisminasfx;
    exports gal.iespazodamerce.visteisminasfx.view;
    opens gal.iespazodamerce.visteisminasfx.view to javafx.fxml;
}