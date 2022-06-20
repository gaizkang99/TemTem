package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="objeto")
@XmlType(propOrder = {"name", "id", "cantidad"})
public class Objeto {
	@XmlAttribute(name="name")
	private String name;
	@XmlAttribute(name="id")
	private String id;
	@XmlElement(name="cantidad")
	private int cantidad;
	
	public Objeto() {}

	
	
	public Objeto(String name, String id, int cantidad) {
		this.name = name;
		this.id = id;
		this.cantidad = cantidad;
	}



	public String getName() {
		return name;
	}

	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidadObjetos(int cantidad) {
		this.cantidad = this.cantidad - cantidad;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Objeto [name=" + name + ", id=" + id + ", cantidad=" + cantidad + "]";
	}

	
	
	
}
