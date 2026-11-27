package controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Resultado;
import persistence.ResultadoDAO;

/**
 * Controlador de la pantalla de ranking. Carga los 8 mejores resultados desde
 * {@link ResultadoDAO} y los muestra en una {@link TableView} ordenada por
 * puntuación descendente.
 */
public class RankingController {

	@FXML
	private TableView<Resultado> tablaRanking;
	@FXML
	private TableColumn<Resultado, String> colJugador;
	@FXML
	private TableColumn<Resultado, Integer> colPuntuacion;

	/**
	 * Método de inicialización llamado automáticamente por JavaFX al cargar el
	 * FXML. Configura las columnas de la tabla y carga los datos del ranking.
	 */
	@FXML
	public void initialize() {
		colJugador.setCellValueFactory(new PropertyValueFactory<>("jugador"));
		colPuntuacion.setCellValueFactory(new PropertyValueFactory<>("puntuacion"));

		tablaRanking.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
		colJugador.setMaxWidth(Double.MAX_VALUE);
		colPuntuacion.setMaxWidth(Double.MAX_VALUE);
		colJugador.prefWidthProperty().bind(tablaRanking.widthProperty().multiply(0.7));
		colPuntuacion.prefWidthProperty().bind(tablaRanking.widthProperty().multiply(0.3));

		List<Resultado> ranking = new ResultadoDAO().cargarRanking();
		tablaRanking.setItems(FXCollections.observableArrayList(ranking));
	}

	/**
	 * Navega de vuelta a la pantalla del menú principal.
	 *
	 * @param event evento de acción del botón "Volver al menú"
	 */
	@FXML
	private void volverAlMenu(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu.fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(loader.load()));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
