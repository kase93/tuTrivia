package persistence;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import model.Pregunta;

/**
 * Objeto de acceso a datos (DAO) para las preguntas del juego. Lee el banco de
 * preguntas desde el fichero {@code preguntas.json} ubicado en la carpeta de
 * recursos del proyecto.
 */
public class PreguntaDAO {

	/**
	 * Carga todas las preguntas disponibles desde el fichero JSON. El fichero debe
	 * estar en la raíz del classpath ({@code /preguntas.json}).
	 *
	 * @return lista de {@link Pregunta} deserializadas desde el JSON
	 * @throws NullPointerException si el fichero no se encuentra en el classpath
	 */
	public List<Pregunta> cargarPreguntas() {
		Gson gson = new Gson();
		InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/preguntas.json"));
		Pregunta[] preguntas = gson.fromJson(reader, Pregunta[].class);
		return Arrays.asList(preguntas);
	}
}