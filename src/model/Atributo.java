package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name="atributo")
public class Atributo {
	@XmlAttribute(name="id")
	private String id;
	@XmlValue()
	private int num;
	
	public Atributo() {}
	
	public Atributo(String id, int num) {
		this.id = id;
		this.num = num;
	}



	public String getId() {
		return id;
	}

	public int getNum() {
		return num;
	}

	@Override
	public String toString() {
		return id + " = " + num;
	}


	
	
}
