package br.edu.ifba.redesMoveis.usecases.operations;

import java.util.List;
import java.util.Map;

public interface Operations<T> {
    // (d.1) imprimir a lista de objetos, pessoas ou animais sobre os quais ocorre o
    // monitoramento;
    public void print(List<T> people);

    // (d.2) imprimir a lista das leituras por cada objeto, pessoa ou animal sobre o
    // qual ocorre o monitoramento.
    public void printWithPhone(List<T> people);

    // (d.3) ordenação crescente dos dados considerando os valores lidos dos
    // sensores para cada coisa monitorada;
    public List<T> orderByConsumption(List<T> people);

    // (d.4) deve ser adicionada, a critério do desenvolvedor, uma funcionalidade
    // extra, relativa ao mini-mundo escolhido, cuja complexidade seja ou O(N^3) ou
    // O(2^N) ou O(N!).
    public Map<String, Integer> findPeakTime(List<T> people);
}