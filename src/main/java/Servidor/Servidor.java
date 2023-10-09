/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.Servidor to edit this template
 */
package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author CONEXOS
 */
public class Servidor {

    private static List<Consulta> consultas = new ArrayList<>();
    private static final int PORTA = 12345;

    public static void main(String[] args) {

        try {
            ServerSocket servidorSocket = new ServerSocket(PORTA);
            System.out.println("Servidor está ouvindo na porta " + PORTA);

            while (true) {
                Socket clienteSocket = servidorSocket.accept();
                System.out.println("Cliente conectado");

                Thread thread = new Thread(new ClienteHandler(clienteSocket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClienteHandler implements Runnable {

        private Socket clienteSocket;

        public ClienteHandler(Socket socket) {
            this.clienteSocket = socket;
        }

        @Override
        public void run() {

            try (
                    ObjectOutputStream out = new ObjectOutputStream(clienteSocket.getOutputStream()); ObjectInputStream in = new ObjectInputStream(clienteSocket.getInputStream())) {

                Object request = in.readObject();

                if (request instanceof String && ((String) request).equals("LISTAR_CONSULTAS")) {
                    out.writeObject(consultas);
                } else if (request instanceof String && ((String) request).equals("DIAGNOSTICO_AUTOMATICO")) {
                    List<String> sintomas = (List<String>) in.readObject();

                    List<String> diagnosticosAutomaticos = realizarDiagnosticoAutomatico(sintomas);

                    out.writeObject(diagnosticosAutomaticos);
                } else {
                    Consulta consulta = (Consulta) request;
                    consultas.add(consulta);
                    System.out.println("Consulta médica recebida e armazenada.");
                    out.writeObject("Consulta médica recebida com sucesso.");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<String> realizarDiagnosticoAutomatico(List<String> sintomas) {

    // Crie uma lista de transações a partir da lista de sintomas
    List<Transaction> transactions = new ArrayList<>();
    List<String> itensDiagnostico = new ArrayList<>();
    for (String sintoma : sintomas) {
        for (Consulta consulta :  consultas) {
            if (consulta.getSintomas().contains(sintoma)) {
                itensDiagnostico.add(consulta.getDiagnostico());
            }
        }
    }
    transactions.add(new Transaction(itensDiagnostico));

    Apriori apriori = new Apriori(transactions);

    List<List<String>> frequentItemSets = apriori.findFrequentItemSets();

    List<String> diagnosticosAutomaticos = new ArrayList<>();
    for (List<String> frequentItemSet : frequentItemSets) {
        String diagnosticoMaisFrequente = "";
        int count = 0;
        for (String diagnostico : frequentItemSet) {
            int countDiagnostico = 0;
            for (Transaction transaction : transactions) {
                if (transaction.contains(diagnostico)) {
                    countDiagnostico++;
                }
            }
            if (countDiagnostico > count) {
                count = countDiagnostico;
                diagnosticoMaisFrequente = diagnostico;
            }
            
        }

        diagnosticosAutomaticos.add(diagnosticoMaisFrequente);
    }

    return diagnosticosAutomaticos;
}

}
