package model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="jugadores")
public class Jugadores {
	@XmlElement(name="jugador")
	private ArrayList<Jugador> j;
	
	public Jugadores(){
	}
	
	public Jugadores(ArrayList<Jugador> j){
		this.j = j;
	}
		
	public ArrayList<Jugador> getJ() {
		return j;
	}
}
