/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.echoremote;
import java.net.*;
import java.io.*;
/**
 *
 * @author elias
 */
public class server{
    //main principal
    public static void main(String[] args)throws Exception{
        
        System.out.println("EL SERVER ESTA ACTIVO...");
        
        try {
            DatagramSocket server = new DatagramSocket(5001);
            byte[] buffer = new byte[1024];
            byte[] buff;
            //este ciclo siempre se cumplira
              for(;;){   
                DatagramPacket peticion = new DatagramPacket(buffer,buffer.length);
                //hacemos captura de la peticion que llega del cliente
                server.receive(peticion);
                if(peticion.getLength() == 0){
                int puertoCliente = peticion.getPort();
                 // System.out.println("port :" + puertoCliente);
                //obtenemos la direccion que contiene la peticion
                InetAddress direccion = peticion.getAddress();    
                String message = new String(peticion.getData()); 
                //obtenemos los bytes del mensaje
                buffer = message.getBytes();
                //preparamos para enviar la respuesta
                DatagramPacket respuesta = new DatagramPacket(buffer, message.length(), direccion, puertoCliente);
                //enviamos la respuesta por medio del datagram socket
                server.send(respuesta);
                //salir del programa
                server.close();    
                }else{
                //obtenemos el puerto de la peticion
                int puertoCliente = peticion.getPort();
                 // System.out.println("port :" + puertoCliente);
                //obtenemos la direccion que contiene la peticion
                InetAddress direccion = peticion.getAddress();
                  System.out.println("dirr : " + direccion);
                //mostramos en pantalla la peticion que ha llegado al server
                System.out.println("EL MENSAJE DEL CLIENTE ES: "+ new String(peticion.getData(),0,peticion.getLength()));               
                //creamos un nuevo mensaje con los datos de la peticion
                String message = new String(peticion.getData());
                //obtenemos los bytes del mensaje
                buff = message.getBytes();
                //preparamos para enviar la respuesta
                DatagramPacket respuesta = new DatagramPacket(buff,message.length(), direccion, puertoCliente);
                //enviamos la respuesta por medio del datagram socket
                server.send(respuesta);  
                }  
            }
        //capturamos las excepciones    
        }catch (SocketException e) 
        {
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        
    }
    
}
