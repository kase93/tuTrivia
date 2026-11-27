package model;

/**
 * Representa el resultado de una partida: nombre del jugador y puntuación
 * obtenida. Se serializa y deserializa automáticamente con Gson para el fichero
 * {@code ranking.json}.
 */
public class Resultado {

	/** Nombre del jugador introducido al inicio de la partida. */
	private String jugador;

	/** Puntuación total obtenida al finalizar la partida. */
	private int puntuacion;

	/**
	 * Constructor vacío requerido por Gson para la deserialización.
	 */
	public Resultado() {
	}

	/**
	 * Crea un resultado con el nombre del jugador y su puntuación.
	 *
	 * @param jugador    nombre del jugador
	 * @param puntuacion puntuación total obtenida
	 */
	public Resultado(String jugador, int puntuacion) {
		this.jugador = jugador;
		this.puntuacion = puntuacion;
	}

	/** @return el nombre del jugador */
	public String getJugador() {
		return jugador;
	}

	/** @param jugador el nombre del jugador */
	public void setJugador(String jugador) {
		this.jugador = jugador;
	}

	/** @return la puntuación obtenida */
	public int getPuntuacion() {
		return puntuacion;
	}

	/** @param puntuacion la puntuación obtenida */
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
}
