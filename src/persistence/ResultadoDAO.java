package persistence;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Resultado;

/**
 * Objeto de acceso a datos (DAO) para el ranking de jugadores. Guarda y
 * recupera los resultados en el fichero {@code resources/ranking.json}.
 * Mantiene un máximo de 8 entradas ordenadas de mayor a menor puntuación.
 */
public class ResultadoDAO {

	/** Ruta del fichero donde se almacena el ranking. */
	private static final String FILE_PATH = "resources/ranking.json";

	/**
	 * Guarda un nuevo resultado en el ranking. Añade el resultado a la lista
	 * existente, la ordena por puntuación de mayor a menor y conserva únicamente
	 * los 8 mejores.
	 *
	 * @param resultado el {@link Resultado} a guardar (nombre + puntuación)
	 */
	public void guardarResultado(Resultado resultado) {
		List<Resultado> ranking = cargarRanking();
		ranking.add(resultado);
		ranking.sort((a, b) -> b.getPuntuacion() - a.getPuntuacion());
		if (ranking.size() > 8) {
			ranking = ranking.subList(0, 8);
		}
		try (Writer writer = new FileWriter(FILE_PATH)) {
			new Gson().toJson(ranking, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga el ranking histórico desde el fichero JSON. Si el fichero no existe o
	 * está vacío, devuelve una lista vacía sin lanzar ninguna excepción.
	 *
	 * @return lista de {@link Resultado} ordenada por puntuación descendente, o
	 *         lista vacía si no hay datos guardados
	 */
	public List<Resultado> cargarRanking() {
		File fichero = new File(FILE_PATH);
		if (!fichero.exists()) {
			return new ArrayList<>();
		}
		try (Reader reader = new FileReader(fichero)) {
			Type tipo = new TypeToken<List<Resultado>>() {
			}.getType();
			List<Resultado> lista = new Gson().fromJson(reader, tipo);
			return lista != null ? lista : new ArrayList<>();
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
}
