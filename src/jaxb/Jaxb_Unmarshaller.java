package jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.Jugador;
import model.Jugadores;

public class Jaxb_Unmarshaller {
	public Jugadores init () {
		Jugadores jugadores = null;
		try {
			JAXBContext context = JAXBContext.newInstance(Jugadores.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			jugadores = (Jugadores) unmarshaller.unmarshal(new File("Files/entrada.xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		if (jugadores == null) System.out.println("ERROR MARSHALLING");
		return jugadores;
	}

}
