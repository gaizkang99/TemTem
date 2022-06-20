package exceptions;

import java.util.Arrays;
import java.util.List;

public class LogicExceptions extends Exception {
	public static final int TemTem_Inexistente = 0;
	public static final int Valores_Incorrectos = 1;
	public static final int Portada_Inexistente = 2;
	public static final int Accion_Inexistente = 3;
	public static final int Jugador_noTemTem = 4;
	public static final int TemTems_Lleno = 5;
	public static final int Jugador_Inexistente = 6;
	public static final int Objetos_Insuficientes = 7;
	public static final int Objeto_Inexistente = 8;
	
	private int value;
	
	public LogicExceptions (int value) {
		this.value = value;
	}
	
	private List<String> message = Arrays.asList(
			"<< TemTem no almacenado >>",
			"<< Accion incorrecta >>",
			"<< La portada no existe >>",
			"<< Esta acción no corresponde a la lógica del juego >>",
		 	"<< El jugador no contiene el TemTem demandado >>",
			"<< El jugador no puede añadir más TemTem a su lista >>",
			"<< El jugador que debe realizar la acción no existe >>",
			"<< Al jugador le quedan 0 unidades del objeto escogido >>",
			"<< El jugador no tiene el objeto escogido >>"
			);
			
	
	@Override
	public String getMessage() {
		return message.get(value);
	}
}
