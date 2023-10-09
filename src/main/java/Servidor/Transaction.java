/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import java.util.List;

/**
 *
 * @author CONEXOS
 */
public class Transaction {

    private List<String> items;

    public Transaction(List<String> items) {
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    public boolean contains(String item) {
        return items.contains(item);
    }
    
    public boolean containsAllItems(List<String> candidate) {
        return items.containsAll(candidate);
    }
}
