package manager;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.awt.*;

import dao.Reader;
import exceptions.LogicExceptions;
import jaxb.Jaxb_Marshaller;
import jaxb.Jaxb_Unmarshaller;
import model.Alta;
import model.Atributo;
import model.Jugador;
import model.Jugadores;
import model.Objeto;
import model.Temtem;

public class Manager {
	private Jaxb_Unmarshaller jxu;
	private Jugadores j;
	private Jaxb_Marshaller jxm;
	
	public Manager() {
	this.jxu = new Jaxb_Unmarshaller();
	this.j = jxu.init();
	this.jxm = new Jaxb_Marshaller();
	}
	
	public void init() {
		try {
			showImage("Files/temtem.jpg");
			Reader r = new Reader("Files/acciones.txt");
			lecturaAcciones(r);
		} catch (LogicExceptions e) {
			System.out.println(LogicExceptions.Portada_Inexistente);
		}
		
	}

	private void lecturaAcciones(Reader r) {
		String linea="";
		while(!((linea = r.read())==null)) {
			System.out.println(linea);
			String[] acciones = linea.split(" ");
			try {
				empezarAcciones(acciones);
			} catch (LogicExceptions e) {
				System.out.println(e.getMessage());
			}
		}
		
	}

	private void empezarAcciones(String[] acciones) throws LogicExceptions {
		switch(acciones[0]) {
		case "M":
			//Mostrar las imagenes.
			//Muestra el temtem por id y muestra un error si no tenemos ese id.
			muestraTemTem(acciones);
			break;
		case "P":
			//Muestra info de un personaje sumando total de objetos y temtems en cantidad.
			mostrarInfoJugador(acciones);
			break;
		case "B":
			//Borra el temtem de un personaje por id.
			borrarTemTem(acciones);
			break;
		case "A":
			//añade un temtem por id y atributos.
			crearTemTem(acciones);
			break;
		case "S":
			//Volcado al fichero xml con los cambios realizados.
			jxm.init(j);
			break;
		case "N":
			//Posibilidad para crear un jugador nuevo.
			crearJugador(acciones);
			break;
		case "R":
			//Permite borrar un jugador entero
			borrarJugador(acciones);
			break;
		case "O":
			//Permite al jugador usar un objeto
			usarObjeto(acciones);
			break;
		case "C":
			//Permite al jugador comprar un objeto
			comprarObjeto(acciones);
			break;
		default:
			throw new LogicExceptions(LogicExceptions.Accion_Inexistente);
		}
	}
	
	public void muestraTemTem(String[] acciones) throws LogicExceptions {
		compruebaTamano(acciones.length, 2);
		boolean encontrado=false;
		for(int i=0; i<j.getJ().size();i++) {
			for(int x=0; x<j.getJ().get(i).getTemtems().size(); x++) {
				if((j.getJ().get(i).getTemtems().get(x).getId()).equals(acciones[1])) {
					encontrado=true;
					showImage("Files/"+(j.getJ().get(i).getTemtems().get(x).getNombre())+".jpg");
					for(int y=0; y<j.getJ().get(i).getTemtems().get(x).getAtributo().size();y++) {
						System.out.println(j.getJ().get(i).getTemtems().get(x).getAtributo().get(y));
					}
				}
			}
		}
		if(!encontrado)throw new LogicExceptions(LogicExceptions.TemTem_Inexistente);
	}
	
	public void mostrarInfoJugador(String[] acciones) throws LogicExceptions {
		compruebaTamano(acciones.length, 2);
		existencia(acciones[1]);
		int jugador = Integer.parseInt(acciones[1])-1;
		int totalObjetos = 0;
		int totalTemtems = j.getJ().get(jugador).getTemtems().size();
		for(int i=0; i<j.getJ().get(jugador).getObjetos().size() ;i++) {
			totalObjetos += j.getJ().get(jugador).getObjetos().get(i).getCantidad();
		}
		System.out.println(j.getJ().get(jugador).toString() + ", objetos = " + totalObjetos + ", temtems = " + totalTemtems + "]");
	}
	
	public void borrarTemTem(String[] acciones) throws LogicExceptions {
		compruebaTamano(acciones.length, 3);
		existencia(acciones[1]);
		int jugando = Integer.parseInt(acciones[1])-1;
		String borrar = acciones[2];
		boolean borrado=false;
		for(int x=0; x<j.getJ().get(jugando).getTemtems().size(); x++) {
			if(j.getJ().get(jugando).getTemtems().get(x).getId().equals(borrar)) {
				j.getJ().get(jugando).getTemtems().remove(x);
				System.out.println("<< TemTem borrado >>");
				x=j.getJ().get(jugando).getTemtems().size();
				borrado=true;
			}
		}
		if(!borrado)throw new LogicExceptions(LogicExceptions.Jugador_noTemTem);
	}
	
	public void crearTemTem(String[] acciones) throws LogicExceptions{
		compruebaTamano(acciones.length, 12);
		existencia(acciones[1]);
		int jugador = Integer.parseInt(acciones[1])-1;
		if(j.getJ().get(jugador).getTemtems().size()<6) {
			ArrayList<Atributo> atr = new ArrayList<Atributo>();
			atr.add(new Atributo("PS", Integer.parseInt(acciones[5])));
			atr.add(new Atributo("ATQ", Integer.parseInt(acciones[6])));
			atr.add(new Atributo("SATQ", Integer.parseInt(acciones[7])));
			atr.add(new Atributo("STA", Integer.parseInt(acciones[8])));
			atr.add(new Atributo("VEL", Integer.parseInt(acciones[9])));
			atr.add(new Atributo("DEF", Integer.parseInt(acciones[10])));
			atr.add(new Atributo("SDEF", Integer.parseInt(acciones[11])));
			Temtem tem = new Temtem(acciones[2], acciones[3], acciones[4], atr);
			j.getJ().get(jugador).getTemtems().add(tem);
			System.out.println("<< TemTem nuevo añadido >>");	
		}else throw new LogicExceptions(LogicExceptions.TemTems_Lleno);
		
	}
	
	public void crearJugador(String[] acciones) throws LogicExceptions {
		compruebaTamano(acciones.length, 1);
		Scanner sc = new Scanner(System.in);
		String id = String.valueOf((j.getJ().size())+1);
		System.out.println("Indica el nombre del nuevo Jugador: ");
		String nombre = sc.next();
		System.out.println("Indica la ciudad procedente del nuevo Jugador: ");
		String ciudad = sc.next();
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = new Date();        
		String fechaFormateada = formato.format(fecha);
		Alta altaNueva = new Alta(ciudad, fechaFormateada);
		Jugador nuevoJugador = new Jugador(id, nombre, altaNueva);
		j.getJ().add(nuevoJugador);
	}
	
	public void borrarJugador(String[] acciones) throws LogicExceptions {
		compruebaTamano(acciones.length, 1);
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<j.getJ().size(); i++) {
			System.out.println(j.getJ().get(i).toString());
		}
		System.out.println("Decide que jugador borrar por id: ");
		int borrado = sc.nextInt()-1;
		j.getJ().remove(borrado);
	}
	
	public void usarObjeto(String[] acciones) throws LogicExceptions {
		compruebaTamano(acciones.length, 1);
		Scanner sc = new Scanner(System.in);
		boolean existente=false;
		boolean salir=false;
		for(int i=0; i<j.getJ().size(); i++) {
			System.out.println(j.getJ().get(i).toString());
		}
		System.out.println("Decide que jugador quiere usar un objeto por id: ");
		int comprador = sc.nextInt()-1;
		System.out.println("¿Qué objeto quieres usar?");
		System.out.println("1-Balsamo\n" + "2-Revivir\n" + "3-TemTemCard");
		int objeto = sc.nextInt();
		String idObjeto = String.valueOf(objeto);
		salir=false;
		while(!salir) {
			boolean objetoExiste = false;
			switch(objeto) {
			case 1:
				for(int i=0; i<j.getJ().get(comprador).getObjetos().size(); i++) {
					if(j.getJ().get(comprador).getObjetos().get(i).getId().equals(idObjeto)) {
						j.getJ().get(comprador).getObjetos().get(i).setCantidadObjetos(1);
						objetoExiste=true;
					}
				}
				if(!existente) throw new LogicExceptions(LogicExceptions.Objeto_Inexistente);
				salir=true;
				break;
			case 2:
				for(int i=0; i<j.getJ().get(comprador).getObjetos().size(); i++) {
					if(j.getJ().get(comprador).getObjetos().get(i).getId().equals(idObjeto)) {
						j.getJ().get(comprador).getObjetos().get(i).setCantidadObjetos(1);
						objetoExiste=true;
					}
				}
				if(!existente) throw new LogicExceptions(LogicExceptions.Objeto_Inexistente);
				salir=true;
				break;
			case 3:
				for(int i=0; i<j.getJ().get(comprador).getObjetos().size(); i++) {
					if(j.getJ().get(comprador).getObjetos().get(i).getId().equals(idObjeto)) {
						j.getJ().get(comprador).getObjetos().get(i).setCantidadObjetos(1);
						objetoExiste=true;
					}
				}
				if(!existente) throw new LogicExceptions(LogicExceptions.Objeto_Inexistente);
				salir=true;
				break;
			default:
				System.out.println("Objeto no valido");
				break;
			}
		}
	}
	
	public void comprarObjeto(String[] acciones) throws LogicExceptions {
		compruebaTamano(acciones.length, 1);
		Scanner sc = new Scanner(System.in);
		boolean salir=false;
		for(int i=0; i<j.getJ().size(); i++) {
			System.out.println(j.getJ().get(i).toString());
		}
		System.out.println("Decide que jugador quiere comprar por id: ");
		int comprador = sc.nextInt()-1;
		System.out.println("¿Qué objeto quieres comprar?");
		System.out.println("1-Balsamo\n" + "2-Revivir\n" + "3-TemTemCard");
		int objeto = sc.nextInt();
		String idObjeto = String.valueOf(objeto);
		salir=false;
		while(!salir) {
			boolean existente = false;
			switch(objeto) {
			case 1:
				for(int i=0; i<j.getJ().get(comprador).getObjetos().size(); i++) {
					if(j.getJ().get(comprador).getObjetos().get(i).getId().equals(idObjeto)) {
						j.getJ().get(comprador).getObjetos().get(i).setCantidadObjetos(-1);
						existente=true;
					}
				}
				if(!existente) {
					Objeto o = new Objeto("Balsamo", idObjeto, 1);
					j.getJ().get(comprador).getObjetos().add(o);
				}
				salir=true;
				break;
			case 2:
				for(int i=0; i<j.getJ().get(comprador).getObjetos().size(); i++) {
					if(j.getJ().get(comprador).getObjetos().get(i).getId().equals(idObjeto)) {
						j.getJ().get(comprador).getObjetos().get(i).setCantidadObjetos(-1);
						existente=true;
					}
				}
				if(!existente) {
					Objeto o = new Objeto("Revivir", idObjeto, 1);
					j.getJ().get(comprador).getObjetos().add(o);
				}
				salir=true;
				break;
			case 3:
				for(int i=0; i<j.getJ().get(comprador).getObjetos().size(); i++) {
					if(j.getJ().get(comprador).getObjetos().get(i).getId().equals(idObjeto)) {
						j.getJ().get(comprador).getObjetos().get(i).setCantidadObjetos(-1);
						existente=true;
					}
				}
				if(!existente) {
					Objeto o = new Objeto("TemTemCard", idObjeto, 1);
					j.getJ().get(comprador).getObjetos().add(o);
				}
				salir=true;
				break;
			default:
				System.out.println("Objeto no valido");
				break;
			}
		}
	}
	
	//Comprueba que las acciones reciban los parámetros correctos para realizarse
	public void compruebaTamano(int a, int b) throws LogicExceptions {
		if (a != b)throw new LogicExceptions(LogicExceptions.Valores_Incorrectos);
	}
	
	//Comprueba si el jugador que se elige existe o no existe 
	public void existencia(String a) throws LogicExceptions{
		boolean existente = false;
		for(int i=0; i<j.getJ().size(); i++) {
			if(j.getJ().get(i).getId().equals(a))existente=true;
		}
		if (!existente)throw new LogicExceptions(LogicExceptions.Jugador_Inexistente);
	}
	
	
	//Muestra la imagen que recibe por parámetro y hasta que no recibe un click no desaparece
	//Para continuar con el codigo debemos pulsar un enter en consola
	public void showImage(String imagen) throws LogicExceptions{
		
		JFrame f = new JFrame();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        f.setUndecorated(true);

        ImageIcon image = new ImageIcon(imagen);

        JLabel lbl = new JLabel(image);

        f.getContentPane().add(lbl);

        f.setSize(image.getIconWidth(), image.getIconHeight());

        int x = (screenSize.width - f.getSize().width)/2;
        int y = (screenSize.height - f.getSize().height)/2;

        f.setLocation(x, y);
        f.setVisible(true);
        
        
        f.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				f.dispose();
				f.setVisible(false);
			}	
        });
        try {
			System.in.read();
		} catch (IOException e1) {
			System.out.println("ERROR");
		}
	}
	
}
