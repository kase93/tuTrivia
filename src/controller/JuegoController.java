package controller;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Partida;
import model.Pregunta;
import model.Resultado;
import persistence.PreguntaDAO;
import persistence.ResultadoDAO;

/**
 * Controlador de la pantalla de juego. Gestiona la interacción entre la vista
 * {@code juego.fxml} y el modelo {@link Partida}. Muestra las preguntas, evalúa
 * las respuestas, controla las vidas y detecta el fin de la partida.
 */
public class JuegoController {

	@FXML
	private Label labelPregunta;
	@FXML
	private Label labelPuntuacion;
	@FXML
	private Label labelVidas;
	@FXML
	private Button btnOpcion0;
	@FXML
	private Button btnOpcion1;
	@FXML
	private Button btnOpcion2;
	@FXML
	private Button btnOpcion3;

	/** Modelo de la partida en curso. */
	private Partida partida;

	/** Vidas restantes del jugador. Se inicia con 3. */
	private int vidas = 3;

	/** Nombre del jugador introducido en el menú principal. */
	private String nombreJugador = "Jugador";

	/**
	 * Método de inicialización llamado automáticamente por JavaFX al cargar el
	 * FXML. Carga las preguntas desde el DAO y muestra la primera pregunta.
	 */
	@FXML
	public void initialize() {
		PreguntaDAO dao = new PreguntaDAO();
		partida = new Partida(dao.cargarPreguntas());
		mostrarPregunta();
	}

	/**
	 * Establece el nombre del jugador para esta partida. Debe llamarse desde
	 * {@link MenuController} tras cargar este controlador.
	 *
	 * @param nombre nombre introducido por el jugador
	 */
	public void setNombreJugador(String nombre) {
		this.nombreJugador = nombre;
	}

	/**
	 * Actualiza la vista con los datos de la pregunta actual: texto de la pregunta,
	 * opciones de respuesta, puntuación y vidas.
	 */
	private void mostrarPregunta() {
		Pregunta p = partida.getPreguntaActual();
		labelPregunta.setText(p.getPregunta());
		List<String> opciones = p.getOpciones();
		btnOpcion0.setText(opciones.get(0));
		btnOpcion1.setText(opciones.get(1));
		btnOpcion2.setText(opciones.get(2));
		btnOpcion3.setText(opciones.get(3));
		actualizarEstado();
	}

	/**
	 * Actualiza los labels de puntuación y vidas en la barra superior.
	 */
	private void actualizarEstado() {
		labelPuntuacion.setText("Puntuación: " + partida.getPuntuacion());
		labelVidas.setText("Vidas: " + "❤ ".repeat(vidas).trim());
	}

	/**
	 * Maneja el evento de pulsación de cualquiera de los 4 botones de opción.
	 * Evalúa la respuesta, descuenta una vida si es incorrecta, avanza a la
	 * siguiente pregunta y comprueba si la partida ha terminado.
	 *
	 * @param event evento de acción generado por el botón pulsado
	 */
	@FXML
	private void comprobarRespuesta(ActionEvent event) {
		Button pulsado = (Button) event.getSource();
		int indice = obtenerIndiceBoton(pulsado);

		boolean correcta = partida.comprobarRespuesta(indice);
		if (!correcta) {
			vidas--;
		}

		partida.siguientePregunta();

		if (vidas <= 0 || partida.haTerminado()) {
			finalizarPartida((Stage) ((Node) event.getSource()).getScene().getWindow());
		} else {
			mostrarPregunta();
		}
	}

	/**
	 * Devuelve el índice numérico (0-3) correspondiente al botón pulsado.
	 *
	 * @param b el botón que ha sido pulsado
	 * @return índice del botón: 0, 1, 2 o 3
	 */
	private int obtenerIndiceBoton(Button b) {
		if (b == btnOpcion0) {
			return 0;
		}
		if (b == btnOpcion1) {
			return 1;
		}
		if (b == btnOpcion2) {
			return 2;
		}
		return 3;
	}

	/**
	 * Finaliza la partida: guarda el resultado y navega a la pantalla de resultado.
	 * Determina si el jugador ha ganado (completó todas las preguntas) o perdido
	 * (sin vidas).
	 *
	 * @param stage el {@link Stage} actual sobre el que se carga la nueva escena
	 */
	private void finalizarPartida(Stage stage) {
		new ResultadoDAO().guardarResultado(new Resultado(nombreJugador, partida.getPuntuacion()));
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/resultado.fxml"));
			stage.setScene(new Scene(loader.load()));
			ResultadoController controller = loader.getController();
			controller.setDatos(nombreJugador, partida.getPuntuacion(), vidas > 0);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
