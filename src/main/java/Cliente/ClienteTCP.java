/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;


import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Gustavo
 */
public class ClienteTCP {

    private static final int PORTA_SERVICO = 2000;

    public static void main() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        int portaServidor = PORTA_SERVICO;
        BufferedOutputStream buffOutputStream;
        BufferedInputStream buffInputStream;
    
        //Cria o ponto de transporte para conexao
        try{
            serverSocket = new ServerSocket(PORTA_SERVICO);
        
        }catch(IOException e){
            System.out.println(e.getMessage());
            return;
        
        }
        //Permanece prestando o servico
        while(true){
            //Aguarda conexao
            try{
                socket = serverSocket.accept();
            
            }
            catch(IOException e){
                System.out.println(e.getMessage());
                return;
            
            }
            //Criar thread responsavel por prestar servico
            
        }
    
    
    }


}
