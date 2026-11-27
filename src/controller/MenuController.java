package controller;

import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 * Controlador de la pantalla del menú principal. Gestiona la navegación hacia
 * la pantalla de juego y el ranking, y solicita el nombre del jugador antes de
 * iniciar una partida.
 */
public class MenuController {

	/**
	 * Solicita el nombre del jugador mediante un diálogo de texto y, si se
	 * introduce un nombre válido, carga la pantalla de juego pasando el nombre al
	 * {@link JuegoController}. Si el campo se deja vacío o se cancela, no se
	 * navega.
	 *
	 * @param event evento de acción del botón "Jugar"
	 */
	@FXML
	private void jugar(ActionEvent event) {
		TextInputDialog dialogo = new TextInputDialog("Jugador");
		dialogo.setTitle("tuTrivia");
		dialogo.setHeaderText("¡Bienvenido!");
		dialogo.setContentText("Introduce tu nombre:");

		Optional<String> resultado = dialogo.showAndWait();

		if (resultado.isPresent() && !resultado.get().trim().isEmpty()) {
			String nombre = resultado.get().trim();
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/juego.fxml"));
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(new Scene(loader.load()));
				JuegoController controller = loader.getController();
				controller.setNombreJugador(nombre);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Navega a la pantalla de ranking donde se muestran los 8 mejores resultados
	 * históricos.
	 *
	 * @param event evento de acción del botón "Ver Ranking"
	 */
	@FXML
	private void abrirRanking(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ranking.fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(loader.load()));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cierra la aplicación.
	 */
	@FXML
	private void salir() {
		Platform.exit();
	}
}
