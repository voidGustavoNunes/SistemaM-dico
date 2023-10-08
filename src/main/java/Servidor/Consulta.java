/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import java.io.Serializable;

/**
 *
 * @author Gustavo
 */
public class Consulta implements Serializable{
    private Sintoma[] sintomas;
    private String diagnostico;

    public Consulta() {
    }

    public Sintoma[] getSintomas() {
        return sintomas;
    }

    public void setSintomas(Sintoma[] sintomas) {
        this.sintomas = sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    
}
