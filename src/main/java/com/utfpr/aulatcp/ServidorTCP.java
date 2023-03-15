/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utfpr.aulatcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Aluno
 */
public class ServidorTCP {
    public static void main(String[] args) throws IOException {
       ServerSocket servidor = new ServerSocket(8081); 
       System.out.println("Servidor rodando na porta 8081");
       
       Socket socket = servidor.accept(); 
       
       Pessoa p = new Pessoa();
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("servidorTCP");
       EntityManager em = factory.createEntityManager();
       
       //Salvar no banco
       em.getTransaction().begin();
       em.persist(p);
       em.getTransaction().commit();
       
       //Consulta no banco
       String qry = "SELECT pessoa FROM Pessoa pessoa WHERE pessoa.nome =: nome";
       em.createQuery(qry, Pessoa.class).setParameter("nome", p.getNome()).getResultList();
       em.close();
       
       System.out.println("O cliente IP: " + socket.getInetAddress().getHostAddress() + " se conectou!");
        
       ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
       String mensagem = entrada.readUTF();
       String novaMensagem = mensagem.toUpperCase();

       DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
       saida.writeUTF(novaMensagem);

       entrada.close(); 
       saida.close(); 

       socket.close(); 
       servidor.close();
    }
}
