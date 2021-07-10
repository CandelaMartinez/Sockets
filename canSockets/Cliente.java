package canSockets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.*;
import java.net.*;


//clase 190
//entorno grafico
//boton enviar responda a un evento
//crear el socket dentro del metodo actionPerformed asi cuando pulso enviar, se establece la comunicacion
//crear un flujo de datos para poder trasladar informacion desde el cliente al servidor usando ese socket
//mete en el flujo lo que hay en el cuadro de texto
//configurar el servidor: ponerlo a la escucha y abra el puerto 9999


public class Cliente {

	public static void main(String[] args) {
	MarcoCliente mimarco= new MarcoCliente();
	mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}

class MarcoCliente extends JFrame{
	public MarcoCliente() {
		
		setBounds(600,300,280,350);
		LaminaMarcoCliente milamina= new LaminaMarcoCliente();
		add(milamina);
		setVisible(true);
	}
}






class LaminaMarcoCliente extends JPanel{
	
	private JTextField campo1;
	private JButton miBoton;
	
	public LaminaMarcoCliente() {
		
		JLabel texto= new JLabel("cliente");
		add(texto);
		
		campo1= new JTextField(20);
		add(campo1);
		
		miBoton= new JButton("enviar");
		
		Enviatexto mievento= new Enviatexto();
		miBoton.addActionListener(mievento);
		
		
		add(miBoton);
		
		
		
		
	}
	
	//clase interna eventos
	private class Enviatexto implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println(campo1.getText());
			
			//crear el socket: direccionIP (cmd ipconfig, puerto: cualquiera menos el 80 que lo usa el ordenador)
			
			try {
				//tiene que conectar con el servidor en esa direccion ip y por ese puerto
				//la estoy haciendo en local, si cabio la ip, la informacion viaja usando un socket al servidor
				Socket misocket= new Socket("192.168.1.46",9999);
			
				//flujo de salida del cliente hacia el servidor
				//getOutputStream me devuelve un outputStream del socket que construi
				//flujo de salida que circulara por el socket
				DataOutputStream flujoSalida= new DataOutputStream(misocket.getOutputStream());
				
				//indicar que va a circular por ese flujo
				//escribe en el flujo lo que hay en el campo1
				//ese flujo circulara por el socket, el socket se dirige a ese servidor en esa ip con ese puerto abierto
				flujoSalida.writeUTF(campo1.getText());
				
				//cerrar el flujo de datos
				flujoSalida.close();
				
				
				
			} catch (UnknownHostException e1) {
				
				e1.printStackTrace();
			
			} catch (IOException e1) {
				
				System.out.println(e1.getMessage());
				e1.printStackTrace();
			}
			
			
			
			
			
		}
		
		
	}
	
	
	
	
	
	
	
}