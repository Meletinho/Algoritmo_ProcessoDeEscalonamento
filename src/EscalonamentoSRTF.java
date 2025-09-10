import java.util.*;

class Processo {
    String nome;
    int tempoChegada;
    int tempoSurto;
    int tempoRestante;
    int tempoTermino;
    int tempoEspera;
    int tempoResposta;
    boolean iniciado;

    public Processo(String nome, int tempoChegada, int tempoSurto) {
        this.nome = nome;
        this.tempoChegada = tempoChegada;
        this.tempoSurto = tempoSurto;
        this.tempoRestante = tempoSurto;
        this.tempoTermino = 0;
        this.tempoEspera = 0;
        this.tempoResposta = -1;
        this.iniciado = false;
    }
}

public class EscalonamentoSRTF {

    public static void main(String[] args) {
        // Criando os processos conforme a tabela
        List<Processo> processos = new ArrayList<>();
        processos.add(new Processo("P1", 0, 7));
        processos.add(new Processo("P2", 2, 4));
        processos.add(new Processo("P3", 4, 1));
        processos.add(new Processo("P4", 5, 4));

        // Ordenar processos por tempo de chegada
        processos.sort(Comparator.comparingInt(p -> p.tempoChegada));

        // Simulação do escalonamento
        simularEscalonamento(processos);

        // Calcular e exibir métricas
        calcularMetricas(processos);
    }

    public static void simularEscalonamento(List<Processo> processos) {
        int tempoAtual = 0;
        int processosFinalizados = 0;
        Processo processoAtual = null;

        // Fila de processos prontos
        List<Processo> filaProntos = new ArrayList<>();

        System.out.println("Tempo\tProcesso\tAção");
        System.out.println("----------------------------------");

        while (processosFinalizados < processos.size()) {
            // Verificar se algum processo chegou neste tempo
            for (Processo p : processos) {
                if (p.tempoChegada == tempoAtual) {
                    filaProntos.add(p);
                    System.out.println(tempoAtual + "\t" + p.nome + "\t\tChegou na fila de prontos");
                }
            }

            // Ordenar a fila por tempo restante (menor primeiro)
            if (!filaProntos.isEmpty()) {
                filaProntos.sort(Comparator.comparingInt(p -> p.tempoRestante));
            }

            // Se não há processo em execução, pegar o próximo da fila
            if (processoAtual == null && !filaProntos.isEmpty()) {
                processoAtual = filaProntos.get(0);
                filaProntos.remove(0);

                if (!processoAtual.iniciado) {
                    processoAtual.tempoResposta = tempoAtual - processoAtual.tempoChegada;
                    processoAtual.iniciado = true;
                }

                System.out.println(tempoAtual + "\t" + processoAtual.nome + "\t\tIniciou execução");
            }

            // Verificar se há processo mais prioritário na fila
            if (processoAtual != null && !filaProntos.isEmpty()) {
                Processo processoMaisCurto = filaProntos.get(0);
                if (processoMaisCurto.tempoRestante < processoAtual.tempoRestante) {
                    // Preemptar o processo atual
                    filaProntos.add(processoAtual);
                    System.out.println(tempoAtual + "\t" + processoAtual.nome + "\t\tPreemptado");

                    processoAtual = processoMaisCurto;
                    filaProntos.remove(0);

                    if (!processoAtual.iniciado) {
                        processoAtual.tempoResposta = tempoAtual - processoAtual.tempoChegada;
                        processoAtual.iniciado = true;
                    }

                    System.out.println(tempoAtual + "\t" + processoAtual.nome + "\t\tIniciou execução");
                }
            }

            // Executar o processo atual
            if (processoAtual != null) {
                processoAtual.tempoRestante--;

                // Verificar se o processo terminou
                if (processoAtual.tempoRestante == 0) {
                    processoAtual.tempoTermino = tempoAtual + 1;
                    processosFinalizados++;
                    System.out.println((tempoAtual + 1) + "\t" + processoAtual.nome + "\t\tTerminou");
                    processoAtual = null;
                }
            }

            // Atualizar tempo de espera dos processos na fila
            for (Processo p : filaProntos) {
                p.tempoEspera++;
            }

            tempoAtual++;
        }
    }

    public static void calcularMetricas(List<Processo> processos) {
        System.out.println("\n\nMétricas de Desempenho:");
        System.out.println("Processo\tT.Espera\tT.Resposta\tT.Termino");
        System.out.println("-----------------------------------------------");

        for (Processo p : processos) {
            System.out.println(p.nome + "\t\t" + p.tempoEspera + "\t\t" +
                    p.tempoResposta + "\t\t" + p.tempoTermino);
        }

        // Calculo das médias
        double tempoEsperaMedio = processos.stream().mapToInt(p -> p.tempoEspera).average().orElse(0);
        double tempoRespostaMedio = processos.stream().mapToInt(p -> p.tempoResposta).average().orElse(0);

        System.out.println("-----------------------------------------------");
        System.out.printf("Média:\t\t%.2f\t\t%.2f\n", tempoEsperaMedio, tempoRespostaMedio);

        // Calcula o throughput
        int tempoTotal = processos.stream().mapToInt(p -> p.tempoTermino).max().orElse(0);
        double throughput = (double) processos.size() / tempoTotal;
        System.out.println("Throughput: " + throughput + " processos/unidade de tempo");
    }
}




