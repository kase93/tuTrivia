package model;

import java.util.List;

/**
 * Representa una pregunta del juego con sus opciones de respuesta. Se
 * deserializa automáticamente desde el fichero {@code preguntas.json} mediante
 * Gson.
 */
public class Pregunta {

	/** Texto de la pregunta. */
	private String pregunta;

	/** Lista de opciones de respuesta (siempre 4 elementos). */
	private List<String> opciones;

	/** Índice (base 0) de la opción correcta dentro de {@code opciones}. */
	private int correcta;

	/**
	 * Constructor vacío requerido por Gson para la deserialización.
	 */
	public Pregunta() {
	}

	/**
	 * Crea una pregunta con todos sus datos.
	 *
	 * @param pregunta texto de la pregunta
	 * @param opciones lista de 4 opciones de respuesta
	 * @param correcta índice (base 0) de la opción correcta
	 */
	public Pregunta(String pregunta, List<String> opciones, int correcta) {
		this.pregunta = pregunta;
		this.opciones = opciones;
		this.correcta = correcta;
	}

	/** @return el texto de la pregunta */
	public String getPregunta() {
		return pregunta;
	}

	/** @param pregunta el texto de la pregunta */
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	/** @return la lista de opciones de respuesta */
	public List<String> getOpciones() {
		return opciones;
	}

	/** @param opciones la lista de opciones de respuesta */
	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}

	/** @return el índice (base 0) de la respuesta correcta */
	public int getCorrecta() {
		return correcta;
	}

	/** @param correcta el índice (base 0) de la respuesta correcta */
	public void setCorrecta(int correcta) {
		this.correcta = correcta;
	}
}
