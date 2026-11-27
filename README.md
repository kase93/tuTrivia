# tuTrivia

## 1. Nombre del juego

**tuTrivia** — juego de preguntas y respuestas de cultura general.

## 2. Autoría

Desarrollado por Andrés Chaves Fernández.

## 3. Descripción breve

tuTrivia es una aplicación de escritorio en Java con JavaFX. El jugador responde 10 preguntas de cultura general eligiendo entre 4 opciones, con 3 vidas y puntuación acumulada. Al finalizar, el resultado se guarda en un ranking local.

## 4. Instrucciones de ejecución

### Opción A — Maven (recomendada)

Requisitos: **JDK 21 o superior** y **Maven 3.9+**. Las dependencias (JavaFX y Gson) se descargan automáticamente.

```bash
# Compilar y empaquetar
mvn clean package

# Ejecutar la aplicación
mvn javafx:run
```

> El fichero `target/tuTrivia-1.0.0.jar` generado por `mvn package` contiene
> las clases y los recursos, pero necesita JavaFX y Gson en el *module path*
> para ejecutarse; la forma sencilla de lanzar el juego es `mvn javafx:run`.

### Opción B — Eclipse

1. Asegúrate de tener instalado JDK 21 o superior.
2. Importa el proyecto (`File > Import > Existing Projects into Workspace`).
3. Configura las dependencias: *User Library* `javaFX` (SDK de JavaFX 21+) y el
   JAR de Gson 2.x añadido al *module path*.
4. Marca `resources/` como *Source Folder*.
5. Ejecuta la clase `application.Main` con el botón Run.

## 5. Tecnologías utilizadas

| Tecnología | Uso |
|---|---|
| Java 21 | Lenguaje principal |
| JavaFX 21 (LTS) | Interfaz gráfica |
| FXML + CSS | Diseño y estilos de vistas |
| Gson 2.14 | Lectura y escritura de JSON |
| Maven | Gestión de dependencias y compilación |

## 6. Estructura del proyecto

```text
tuTrivia/
├── src/
│   ├── application/        # Punto de entrada (Main.java)
│   ├── controller/         # MenuController, JuegoController, RankingController, ResultadoController
│   ├── model/              # Partida, Pregunta, Resultado
│   ├── persistence/        # PreguntaDAO, ResultadoDAO
│   └── view/               # FXML, CSS
├── resources/
│   ├── preguntas.json
│   └── ranking.json
├── Info/
│   ├── TrabajoFinal.pdf
│   └── tuTrivia Documentacion.pdf
├── pom.xml                 # Build con Maven (JavaFX + Gson)
└── README.md
```

> El proyecto está modularizado (`src/module-info.java`), por lo que JavaFX y
> Gson deben estar en el *module path*, no en el *class path*.

## 7. Funcionalidades implementadas

| Código | Funcionalidad | Descripción |
|---|---|---|
| F01 | Iniciar partida | El jugador introduce su nombre y comienza una nueva partida |
| F02 | Desarrollar partida | Se muestran 10 preguntas con 4 opciones cada una |
| F03 | Calcular puntuación | Cada acierto suma 10 puntos; cada fallo resta 1 vida |
| F04 | Guardar resultado | Al finalizar se guarda nombre y puntuación en `ranking.json` |
| F05 | Mostrar ranking | Pantalla con los 8 mejores resultados ordenados por puntuación |
| F06 | Pantalla de resultado | Muestra si el jugador ha ganado o perdido con su puntuación final |

## 8. Sistema de persistencia elegido

Ficheros JSON. Dos archivos: `preguntas.json` con el banco de preguntas, y `ranking.json` con el historial de resultados.

## 9. Justificación de la persistencia

Se eligieron ficheros JSON por su simplicidad, compatibilidad con Gson y porque el volumen de datos no requiere una base de datos. Además, las preguntas son fácilmente editables sin tocar el código.

## 10. Diseño de datos

**preguntas.json** — se lee al iniciar cada partida, nunca se escribe en ejecución.

```json
[
  {
    "pregunta": "¿Cuál es la capital de Francia?",
    "opciones": ["Madrid", "París", "Roma", "Berlín"],
    "correcta": 1
  }
]
```

- `pregunta`: texto de la pregunta mostrado en pantalla.
- `opciones`: array de 4 posibles respuestas.
- `correcta`: índice (base 0) de la opción correcta.

**ranking.json** — se lee al abrir el ranking y se escribe al finalizar cada partida. Si no existe, se crea automáticamente.

```json
[
  { "jugador": "Andres", "puntuacion": 90 },
  { "jugador": "Jugador1", "puntuacion": 20 }
]
```

- `jugador`: nombre introducido por el jugador al inicio de la partida.
- `puntuacion`: puntuación total obtenida. Máximo 8 entradas, ordenadas de mayor a menor.

## 11. Capturas de pantalla

*Hay 6 capturas de pantalla en "tuTrivia/resources/images/".

## 12. Problemas encontrados

- **Gson en proyecto modular**: debía añadirse al *modulepath* y declararse en `module-info.java`, no al *classpath*.
- **Ruta de recursos**: `resources/` debía marcarse como *Source Folder* para que `getResourceAsStream()` funcionase.
- **CONSTRAINED_RESIZE_POLICY deprecated**: actualizado a `CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN` en versiones recientes de JavaFX.

## 13. Mejoras futuras

- Temporizador por pregunta.
- Categorías y niveles de dificultad.
- Mostrar la respuesta correcta al fallar.
- Banco de preguntas ampliado con selección aleatoria.
- Sonidos de acierto y fallo.

## 14. Licencia

Este proyecto se distribuye bajo la licencia **MIT** (ver [LICENSE](LICENSE)).
