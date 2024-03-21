module Project {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
	opens application.healthSoftware.data to javafx.base;
}
