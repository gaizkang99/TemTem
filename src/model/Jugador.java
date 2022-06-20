package model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name="jugador")
@XmlType(propOrder = {"id", "nombre", "alta", "objetos", "temtems"})
public class Jugador {
	@XmlAttribute(name="id")
	private String id;
	@XmlElement(name="nombre")
	private String nombre;
	@XmlElement(name="alta")
	private Alta alta;
	@XmlElementWrapper(name = "objetos")
	@XmlElement(name = "objeto")
	private ArrayList <Objeto> objetos;
	@XmlElementWrapper(name = "temtems")
	@XmlElement(name = "temtem")
	private ArrayList <Temtem> temtems;
	
	public Jugador(){}
	
	public Jugador(String id, String name, Alta alta){
		this.id = id;
		this.nombre = name;
		this.alta = alta;
	}
	
	public String getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public Alta getAlta() {
		return alta;
	}

	public ArrayList<Objeto> getObjetos() {
		return objetos;
	}

	public ArrayList<Temtem> getTemtems() {
		return temtems;
	}

	@Override
	public String toString() {
		return "Jugador [id=" + id + ", nombre=" + nombre + ", alta=" + alta ;
	}

	
	
	
	
	
}
