package Servidor;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


public class ServidorTCP extends Thread {

    private Socket socket;
    private BufferedOutputStream buffOutputStream;
    private BufferedInputStream buffInputStream;
    private ArrayList<Consulta> consulta;

    public ServidorTCP(Socket socket) {
        this.socket = socket;
        this.consulta = new ArrayList<>();

    }

    @Override
    public void run() {
        try {
            prepararStreams();
            processarRequisicao();
        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            fecharStreamsESocket();
        }
    }

    private void prepararStreams() throws IOException {
        buffOutputStream = new BufferedOutputStream(socket.getOutputStream());
        buffInputStream = new BufferedInputStream(socket.getInputStream());
    }

    private void processarRequisicao() throws IOException {
        byte[] buffer = new byte[1000];
        // Aguardar a recepção do pacote
        int bytesLidos = buffInputStream.read(buffer);
        if (bytesLidos > 0) {
            // Converter para uma cadeia de caracteres
            String requisicao = new String(buffer, 0, bytesLidos).trim();
            System.out.println("\nServidor recebeu a requisição: " + requisicao);

            String resposta = obterRespostaParaRequisicao(requisicao);
            // Enviar a resposta ao cliente
            buffOutputStream.write(resposta.getBytes());
            buffOutputStream.flush();
        }
    }

    // Identificar e providenciar o serviço solicitado
    private String obterRespostaParaRequisicao(String requisicao) { //modificar
        switch (requisicao) {
            case "SERVICO_A":
                return "Resposta para SERVICO_A";
            case "SERVICO_B":
                return "Resposta para SERVICO_B";
            default:
                return "Serviço não reconhecido.";
        }
    }

    private void fecharStreamsESocket() {
        try {
            if (buffInputStream != null) {
                buffInputStream.close();
            }
            if (buffOutputStream != null) {
                buffOutputStream.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Erro ao fechar streams e socket: " + e.getMessage());
        }
    }

    private void registrarConsulta(String requisicao) {
        // Dividir a requisição em sintomas e diagnóstico
        String[] partes = requisicao.split(",");

        // Extrair os sintomas
        List<Sintoma> sintomas = new ArrayList<>();
        for (int i = 0; i < partes.length - 1; i++) {
            Sintoma sintoma = new Sintoma();
            sintoma.setNome(partes[i].trim());
            sintomas.add(sintoma);
        }

        // Extrair o diagnóstico
        String diagnostico = partes[partes.length - 1].trim();

        // Criar a consulta
        Consulta consultas = new Consulta();
        
        consultas.setSintomas(sintomas.toArray(new Sintoma[0]));
        consultas.setDiagnostico(diagnostico);

        // Registrar a consulta
        consulta.add(consultas);

        // Imprimir a consulta registrada
        System.out.println("Consulta registrada: " + consultas);
    }
}
