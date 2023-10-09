package Servidor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Apriori {

    private List<Transaction> transactions;

    public Apriori(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<List<String>> findFrequentItemSets() {
        // Inicialize um conjunto para armazenar os itens frequentes
        List<List<String>> frequentItemSets = new ArrayList<>();

        // Encontre todos os itens únicos nos dados de transação
        Set<String> uniqueItems = new HashSet<>();
        for (Transaction transaction : transactions) {
            uniqueItems.addAll(transaction.getItems());
        }

        // Converta os itens únicos em uma lista
        List<String> allItems = new ArrayList<>(uniqueItems);

        // Comece com conjuntos de tamanho 1 (itens individuais)
        int itemSetSize = 1;

        // Inicialize frequentItemSets com os itens únicos
        for (String item : allItems) {
            frequentItemSets.add(Collections.singletonList(item));
        }

        while (true) {
            // Gere candidatos para conjuntos de tamanho (itemSetSize + 1)
            List<List<String>> candidateItemSets = generateCandidateItemSets(frequentItemSets, itemSetSize, allItems);

            // Conte a frequência de cada candidato nos dados de transação
            List<List<String>> frequentCandidates = countFrequentCandidates(candidateItemSets);

            if (frequentCandidates.isEmpty()) {
                // Se não houver conjuntos frequentes de tamanho (itemSetSize + 1), pare
                break;
            }

            // Adicione os conjuntos frequentes de tamanho (itemSetSize + 1) à lista de conjuntos frequentes
            frequentItemSets.addAll(frequentCandidates);

            // Aumente o tamanho do conjunto para a próxima iteração
            itemSetSize++;
        }

        return frequentItemSets;
    }

    // Gere candidatos para conjuntos de tamanho (itemSetSize + 1)
    private List<List<String>> generateCandidateItemSets(List<List<String>> frequentItemSets, int itemSetSize, List<String> allItems) {
        List<List<String>> candidateItemSets = new ArrayList<>();

        int numFrequentItemSets = frequentItemSets.size();

        for (int i = 0; i < numFrequentItemSets; i++) {
            List<String> itemSet1 = frequentItemSets.get(i);

            for (int j = i + 1; j < numFrequentItemSets; j++) {
                List<String> itemSet2 = frequentItemSets.get(j);

                // Verifique se os primeiros (itemSetSize - 1) itens são os mesmos
                boolean canJoin = true;
                for (int k = 0; k < itemSetSize - 1; k++) {
                    if (!itemSet1.get(k).equals(itemSet2.get(k))) {
                        canJoin = false;
                        break;
                    }
                }

                // Se os primeiros (itemSetSize - 1) itens são iguais, faça a união
                if (canJoin) {
                    List<String> newCandidateItemSet = new ArrayList<>(itemSet1);
                    newCandidateItemSet.add(itemSet2.get(itemSetSize - 1));

                    // Verifique se todos os subconjuntos do candidato são frequentes
                    boolean allSubsetsFrequent = true;
                    for (int k = 0; k < itemSetSize; k++) {
                        List<String> subset = new ArrayList<>(newCandidateItemSet);
                        subset.remove(k);
                        if (!frequentItemSets.contains(subset)) {
                            allSubsetsFrequent = false;
                            break;
                        }
                    }

                    // Se todos os subconjuntos são frequentes, adicione o candidato
                    if (allSubsetsFrequent) {
                        candidateItemSets.add(newCandidateItemSet);
                    }
                }
            }
        }

        return candidateItemSets;
    }

    // Conte a frequência de cada candidato nos dados de transação
    private List<List<String>> countFrequentCandidates(List<List<String>> candidateItemSets) {
        List<List<String>> frequentCandidates = new ArrayList<>();

        for (List<String> candidate : candidateItemSets) {
            int count = 0;
            for (Transaction transaction : transactions) {
                if (transaction.containsAllItems(candidate)) {
                    count++;
                }
            }

            // Define um limite de suporte mínimo (ajuste conforme necessário)
            int minSupport = 2;

            if (count >= minSupport) {
                frequentCandidates.add(candidate);
            }
        }

        return frequentCandidates;
    }
}
