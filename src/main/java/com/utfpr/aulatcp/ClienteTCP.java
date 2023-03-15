/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utfpr.aulatcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Aluno
 */
public class ClienteTCP {
 public static void main(String[] args) throws IOException {
     Socket socket = new Socket("127.0.0.1", 8081);
     
     DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
     saida.writeUTF("Olá servidor");
     
     DataInputStream entrada = new DataInputStream(socket.getInputStream());

     String novaMensagem = entrada.readUTF(); 
     System.out.println(novaMensagem);
 
     entrada.close();
     saida.close();

     socket.close();
 }   
}
