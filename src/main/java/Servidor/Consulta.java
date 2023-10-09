/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author CONEXOS
 */
public class Consulta implements Serializable {

    private List<String> sintomas;
    private String diagnostico;

    public Consulta(List<String> sintomas, String diagnostico) {
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
    }

    public List<String> getSintomas() {
        return sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }
}
