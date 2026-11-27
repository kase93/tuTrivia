package model;

import java.util.List;

/**
 * Representa una partida en curso del juego tuTrivia. Gestiona el estado de la
 * partida: pregunta actual, puntuación y progreso.
 */
public class Partida {

	/** Lista de preguntas cargadas para esta partida. */
	private List<Pregunta> preguntas;

	/** Índice de la pregunta que se está mostrando actualmente. */
	private int indicePregunta;

	/** Puntuación acumulada del jugador en esta partida. */
	private int puntuacion;

	/**
	 * Crea una nueva partida con la lista de preguntas proporcionada. La puntuación
	 * inicial es 0 y se empieza por la primera pregunta.
	 *
	 * @param preguntas lista de preguntas que se usarán en la partida
	 */
	public Partida(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
		this.indicePregunta = 0;
		this.puntuacion = 0;
	}

	/**
	 * Devuelve la pregunta correspondiente al turno actual.
	 *
	 * @return la {@link Pregunta} en la posición actual
	 */
	public Pregunta getPreguntaActual() {
		return preguntas.get(indicePregunta);
	}

	/**
	 * Comprueba si la respuesta seleccionada es correcta. Si lo es, incrementa la
	 * puntuación en 10 puntos.
	 *
	 * @param respuestaSeleccionada índice (base 0) de la opción elegida por el
	 *                              jugador
	 * @return {@code true} si la respuesta es correcta; {@code false} en caso
	 *         contrario
	 */
	public boolean comprobarRespuesta(int respuestaSeleccionada) {
		if (respuestaSeleccionada == getPreguntaActual().getCorrecta()) {
			puntuacion += 10;
			return true;
		}
		return false;
	}

	/**
	 * Avanza al siguiente índice de pregunta.
	 */
	public void siguientePregunta() {
		indicePregunta++;
	}

	/**
	 * Indica si el jugador ha respondido todas las preguntas de la partida.
	 *
	 * @return {@code true} si no quedan más preguntas; {@code false} si aún hay
	 *         preguntas
	 */
	public boolean haTerminado() {
		return indicePregunta >= preguntas.size();
	}

	/**
	 * Devuelve la puntuación acumulada en la partida actual.
	 *
	 * @return puntuación total obtenida hasta el momento
	 */
	public int getPuntuacion() {
		return puntuacion;
	}
}
