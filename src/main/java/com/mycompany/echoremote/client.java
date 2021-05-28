/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.echoremote;
import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author elias
 */
public class client{
    //principal
    public static void main(String[] args) throws Exception
    {
        System.out.println("CLIENTE EN LINEA...\n");
        
        try {  
            DatagramSocket ClienteUDP = new DatagramSocket();
            //declaramos una variable de tipo entero y le asignamos el puerto que usaremos
            int puerto = 5001;
            //dato tipo string con la direccion que usaremos 
            
            String add = JOptionPane.showInputDialog("Ingrese la ip del servidor:");
            //obtenemos la direccion ip por medio del getByName 
            InetAddress host = InetAddress.getByName(add);
            //declaramos el string para pedir el mensaje
            String message;
            //iniciamos el ciclo for
            for(;;){
                //BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
                //message = JOptionPane.showInputDialog("Ingrese una cadena:").repeat(2);
                message = JOptionPane.showInputDialog("Ingrese una cadena:");
                //System.out.println("INGRESE UNA CADENA (solamente 'enter' para salir):");
                //pedimos la cadena
                //message = entrada.readLine();
                //asignamos un array de bytes con los bytes que se obtienen de la cadena que se ingreso
                byte [] mensaje= message.getBytes();
                //de la clase de datagramas creamos la instancia de la clase en peticion 
                DatagramPacket peticion = new DatagramPacket(mensaje, mensaje.length, host, puerto);
                //hacemos el envio del datagrama hacia el server 
                ClienteUDP.send(peticion);
                //hacemos la instancia con nuevo datagrama en el cual recibiremos la respuesta
                DatagramPacket respuesta = new DatagramPacket(mensaje,mensaje.length);
                //recibimos la respuesta por parte del server
                ClienteUDP.receive(respuesta);
                //si la longitud de la respuesta es 0 terminamos el programa del cliente
                if(respuesta.getLength() == 0){
                    JOptionPane.showMessageDialog(null,"SALIENDO DEL CLIENTE...");
                    //salir del programa
                    System.exit(0);
                }else{
                //si el metodo getLength verifica que no es una cadena vacia
                //mostramos en pantalla la respuesta que nos ha mandado el server
                JOptionPane.showMessageDialog(null,"LA RESPUESTA DEL SERVER ES: " + new String(respuesta.getData(), 0, respuesta.getLength()));
            }//ciclo infinito de for...
            }
        //capturamos las excepciones..    
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
   }
    }
    
}
