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
                    // Se o cliente solicitar listar as consultas
                    out.writeObject(consultas); // Envie a lista de consultas de volta para o cliente
                } else if (request instanceof String && ((String) request).equals("DIAGNOSTICO_AUTOMATICO")) {
                    // Tratamento da solicitação do cliente para diagnóstico automático
                    List<String> diagnosticos = realizarDiagnosticoAutomatico();
                    out.writeObject(diagnosticos);
                } else {
                    // Caso contrário, trata como uma consulta médica normal
                    Consulta consulta = (Consulta) request;
                    consultas.add(consulta);
                    System.out.println("Consulta médica recebida e armazenada.");

                    // Aqui você pode adicionar lógica para diagnóstico automático usando o algoritmo Apriori, se necessário.
                    out.writeObject("Consulta médica recebida com sucesso.");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    // Função para realizar diagnóstico automático com Apriori
    private static List<String> realizarDiagnosticoAutomatico() {
        // 1. Analisar as consultas armazenadas e preparar os dados
        List<Transaction> transactions = new ArrayList<>();
        for (Consulta consulta : consultas) {
            List<String> sintomasDiagnostico = new ArrayList<>(consulta.getSintomas());
            sintomasDiagnostico.add(consulta.getDiagnostico());
            transactions.add(new Transaction(sintomasDiagnostico));
        }

        // 2. Executar o algoritmo Apriori
        Apriori apriori = new Apriori(transactions);
        List<List<String>> frequentItemSets = apriori.findFrequentItemSets();

        // 3. Gerar diagnósticos automáticos
        List<String> diagnosticosAutomaticos = new ArrayList<>();
        for (List<String> frequentItemSet : frequentItemSets) {
//            if (frequentItemSet.size() > 1) {;
                // O frequentItemSet contém diagnósticos associados a sintomas
                diagnosticosAutomaticos.add("Diagnóstico automático: " + frequentItemSet);
//            }
        }

        return diagnosticosAutomaticos;
    }
}
