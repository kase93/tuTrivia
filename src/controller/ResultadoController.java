package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controlador de la pantalla de resultado final. Muestra el desenlace de la
 * partida (victoria o derrota), la puntuación obtenida y el nombre del jugador.
 * Permite navegar al menú principal, al ranking o iniciar una nueva partida.
 */
public class ResultadoController {

	@FXML
	private Label labelEmoji;
	@FXML
	private Label labelTitulo;
	@FXML
	private Label labelPuntuacion;
	@FXML
	private Label labelJugador;

	/**
	 * Rellena la pantalla con los datos de la partida finalizada. Muestra un emoji
	 * y un mensaje diferente según si el jugador ha ganado (completó todas las
	 * preguntas) o perdido (sin vidas).
	 *
	 * @param nombre     nombre del jugador introducido al inicio
	 * @param puntuacion puntuación total obtenida en la partida
	 * @param victoria   {@code true} si el jugador completó todas las preguntas;
	 *                   {@code false} si agotó las vidas antes de terminar
	 */
	public void setDatos(String nombre, int puntuacion, boolean victoria) {
		labelJugador.setText(nombre);
		labelPuntuacion.setText("Puntuación: " + puntuacion);

		if (victoria) {
			labelEmoji.setText("🏆");
			labelTitulo.setText("¡Has ganado!");
		} else {
			labelEmoji.setText("💀");
			labelTitulo.setText("¡Sin vidas!");
		}
	}

	/**
	 * Navega de vuelta al menú principal para iniciar una nueva partida.
	 *
	 * @param event evento de acción del botón "Jugar de nuevo"
	 */
	@FXML
	private void jugarDeNuevo(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu.fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(loader.load()));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Navega a la pantalla de ranking para consultar las mejores puntuaciones.
	 *
	 * @param event evento de acción del botón "Ver Ranking"
	 */
	@FXML
	private void verRanking(ActionEvent event) {
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
	 * Alias de {@link #jugarDeNuevo(ActionEvent)}. Navega al menú principal desde
	 * el botón "Volver al menú".
	 *
	 * @param event evento de acción del botón "← Volver al menú"
	 */
	@FXML
	private void volverAlMenu(ActionEvent event) {
		jugarDeNuevo(event);
	}
}
