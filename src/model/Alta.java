package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name="alta")
public class Alta {
	@XmlAttribute(name="ciudad")
	private String ciudad;
	@XmlValue
	private String alta;
	
	
	public Alta() {}
	
	
	
	public Alta(String ciudad, String alta) {
		this.ciudad = ciudad;
		this.alta = alta;
	}



	public Alta(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public String getCiudad() {
		return ciudad;
	}

	
	public String getAlta() {
		return alta;
	}

	@Override
	public String toString() {
		return "Alta [ciudad=" + ciudad + ", alta=" + alta + "]";
	}

	
	
	
}
