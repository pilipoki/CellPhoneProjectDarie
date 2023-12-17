module darie.mitulescu {
    requires javafx.controls;
    requires javafx.fxml;

    opens darie.mitulescu to javafx.fxml;
    exports darie.mitulescu;
}
