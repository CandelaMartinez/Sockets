package canSockets;
import java.awt.BorderLayout;
import java.io.*;
import java.net.*;


import javax.swing.*;

//clase 190
//recibir en el area texto que envio desde cliente
//en 2 plano permanecera a la escucha y tener el puerto9999 abierto
//usando hilos hace las dos cosas al mismo tiempo

//contruir serversocket y especificar el puerto
//acepte las conexiones del exterior
//crear un flujo de entrada
//leer lo que viene en ese flujo
//lo escriba en el JTextArea
//cerrar la conexion


public class Servidor {

	public static void main(String[] args) {
		MarcoServidor mimarco= new MarcoServidor();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mimarco.setVisible(true);
		
		
		

	}

}


//hago que el marco y lamina este permanenemente a la escucha Runnable

class MarcoServidor extends JFrame implements Runnable{
	
	private JTextArea areatexto;
	
	public MarcoServidor() {
		
		setBounds(1200,300,280,350);
		JPanel milamina= new JPanel();
		milamina.setLayout(new BorderLayout());
		
		areatexto = new JTextArea();
		milamina.add(areatexto, BorderLayout.CENTER);
		
		add(milamina);
		
		//creo el hilo y lo inicio
		
		Thread mihilo= new Thread(this);
		mihilo.start();
		
	}

	
	//metodo de la interface, codigo que se encarga de estar a la escucha
	
	@Override
	public void run() {
		//System.out.println("a la escucha");
		
		
		try {
		
			//construye un puerto de servidor que pone la app a la escucha en un puerto que le indique
			ServerSocket servidor= new ServerSocket(9999);
			
			
			//bucle para poder repetir las lineas que escribo
			//en 2 plano con el hilo esta ejecutando este hilo con el bucle
			
			while(true) {
			
			//acepte nuestra app cualquier conexion que le venga del exterior
			Socket misocket= servidor.accept();
			
			//crear un flujo de datos de entrada(por que socket viaja el flujo)
			DataInputStream flujoEntrada= new DataInputStream(misocket.getInputStream());
			
			//leer lo que viene en ese flujo
			String mensajeTexto= flujoEntrada.readUTF();
			
			//lo escriba en el JTextArea
			areatexto.append("\n" + mensajeTexto);
			
			//cerrar la conexion
			misocket.close();
			
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}

