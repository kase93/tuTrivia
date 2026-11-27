module tuTrivia {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.google.gson;

	opens application to javafx.graphics;
	opens controller to javafx.fxml;
	opens model to com.google.gson, javafx.base;
}