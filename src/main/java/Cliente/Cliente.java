/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Cliente;

import Servidor.Consulta;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author CONEXOS
 */
public class Cliente extends javax.swing.JFrame {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    // Singleton instance
    private static Cliente instance;

    /**
     * Creates new form Cliente1
     */
    public Cliente() {
        initComponents();
        conectarAoServidor();
    }

    // Method to get the Singleton instance
    public static Cliente getInstance() {
        if (instance == null) {
            instance = new Cliente();
        }
        return instance;
    }

    private void conectarAoServidor() {
        try {
            socket = new Socket("localhost", 12345);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fecharConexao() {
        try {
            // Feche os fluxos e a conexão
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exibirConsultas(List<Consulta> consultas) {
        StringBuilder sb = new StringBuilder();
        if (consultas.isEmpty()) {
            sb.append("Não há Consultas Médicas Armazenadas.");
        } else {
            sb.append("Consultas Médicas Armazenadas:\n");
            for (Consulta consulta : consultas) {
                sb.append("Sintomas: ").append(consulta.getSintomas()).append("\n");
                sb.append("Diagnóstico: ").append(consulta.getDiagnostico()).append("\n\n");
            }
        }
        txtListagem.setText(sb.toString());
    }

    private void exibirDiagnosticos(List<String> diagnosticosAutomaticos) {
    StringBuilder sb = new StringBuilder();
    if (diagnosticosAutomaticos.isEmpty()) {
        sb.append("Não há Diagnósticos Frequentes para os sintomas inseridos.");
    } else {
        for (String diagnostico : diagnosticosAutomaticos) {
            sb.append(diagnostico).append("\n");
        }
    }
    txtListagem.setText(sb.toString());
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSintomas = new javax.swing.JTextField();
        txtDiagnostico = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtListagem = new javax.swing.JTextArea();
        btnAutomatico = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Sintomas (separados por vírgula):");

        jLabel2.setText("Diagnóstico:");

        btnEnviar.setText("Enviar Consulta Médica");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        btnListar.setText("Listar Consultas");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        txtListagem.setColumns(20);
        txtListagem.setRows(5);
        jScrollPane1.setViewportView(txtListagem);

        btnAutomatico.setText("Diagnóstico Automático");
        btnAutomatico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAutomaticoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSintomas)
                            .addComponent(txtDiagnostico)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEnviar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnListar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAutomatico)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSintomas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnviar)
                    .addComponent(btnListar)
                    .addComponent(btnAutomatico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        // Código para criar um novo socket e enviar uma consulta médica
        String sintomasTexto = txtSintomas.getText();
        String diagnostico = txtDiagnostico.getText();
        
        if (!sintomasTexto.isBlank() && !diagnostico.isBlank()) {
            List<String> sintomas = new ArrayList<>();
            sintomas.addAll(Arrays.asList(sintomasTexto.split(", ")));

            Consulta consulta = new Consulta(sintomas, diagnostico);

            try {
                conectarAoServidor();
                out.writeObject(consulta);
                String resposta = (String) in.readObject();
                JOptionPane.showMessageDialog(this, resposta);
                fecharConexao();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        } else {
            txtListagem.setText("Preencha todos os campos!");
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        // TODO add your handling code here:
        try {
            conectarAoServidor();
            out.writeObject("LISTAR_CONSULTAS");
            List<Consulta> consultas = (List<Consulta>) in.readObject();
            exibirConsultas(consultas);
            fecharConexao();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnAutomaticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAutomaticoActionPerformed
        // TODO add your handling code here:
        String sintomasTexto = txtSintomas.getText();
        
        if (!sintomasTexto.isBlank()) {
            try {
                conectarAoServidor();
                out.writeObject("DIAGNOSTICO_AUTOMATICO");


                List<String> sintomas = new ArrayList<>();
                sintomas.addAll(Arrays.asList(sintomasTexto.split(", ")));

                out.writeObject(sintomas);

                List<String> diagnosticosAutomaticos = (List<String>) in.readObject();

                exibirDiagnosticos(diagnosticosAutomaticos);

                fecharConexao();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAutomaticoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                getInstance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAutomatico;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnListar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtDiagnostico;
    private javax.swing.JTextArea txtListagem;
    private javax.swing.JTextField txtSintomas;
    // End of variables declaration//GEN-END:variables
}
