package jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import model.Jugadores;

public class Jaxb_Marshaller {
	
	public Jaxb_Marshaller init (Jugadores j) {
		try {
			JAXBContext context = JAXBContext.newInstance(Jugadores.class);
			Marshaller marshaller = context.createMarshaller();
			//j = createXml();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(j, new File("Files/salida.xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// new File("Files/salida.xml"
}