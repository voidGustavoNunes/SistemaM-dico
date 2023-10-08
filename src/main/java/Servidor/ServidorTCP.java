/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

/**
 *
 * @author Gustavo
 */

import java.io.*;
import java.net.*;


public class ServidorTCP extends Thread{
    byte dados[];
    String operacaoSolicitada;
    String resposta;
    int bytesLidos;
    Socket socket;
    
    
    BufferedOutputStream buffOutputStream;
    BufferedInputStream buffInputStream;
            
    public void run(){
        //Criar o Stream para entrada e saida
        try{
            buffOutputStream = new BufferedOutputStream(socket.getOutputStream());
            
            
            buffInputStream = new BufferedInputStream(socket.getInputStream());
        }catch(IOException e){
            System.out.println(e.getMessage());
            return;
        }
        
        //Criar area para armazenamento dos dados recebidos
        dados = new byte[1000];
        
        //Aguardar a recepcao do pacote
        try{
            bytesLidos = buffInputStream.read(dados, 0, dados.length);
        }catch(IOException e){
            System.out.println(e.getMessage());
            return;
        }
    
        //Converter para uma cadeia de caracteres
        operacaoSolicitada = new String(dados, 0, dados.length);
        operacaoSolicitada = operacaoSolicitada.trim();
        
        System.out.println("\nO servidor recebeu a solicitacao: "+operacaoSolicitada);
    }
    
}
