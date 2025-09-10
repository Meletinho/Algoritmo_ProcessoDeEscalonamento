import java.util.*;
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

public class Main {

    public static void main(String[] args) {
        // Criando os processos conforme a tabela
        List<Processo> processos = new ArrayList<>();
        processos.add(new Processo("P1", 0, 7));
        processos.add(new Processo("P2", 2, 4));
        processos.add(new Processo("P3", 4, 1));
        processos.add(new Processo("P4", 5, 4));

        processos.sort(Comparator.comparingInt(p -> p.tempoChegada));

        simularEscalonamento(processos);

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

            // Verifica se algum processo chegou neste tempo
            while (processosFinalizados < processos.size()) {
                for (Processo processo : processos) {
                    if (processo.tempoChegada == tempoAtual) {
                        System.out.println(tempoAtual + "\t" +
                                processo.nome +
                                "\t\tChegou na fila de prontos");
                    }
                }
            }


            // Ordena a fila por tempo restante
            if (!filaProntos.isEmpty()) {
                filaProntos.sort(Comparator.comparingInt(p -> p.tempoChegada));
            }

            if (!processoAtual.iniciado) {

                processoAtual.tempoResposta = tempoAtual - processoAtual.tempoChegada;
                processoAtual.iniciado = true;
            }

            System.out.println(tempoAtual + "\t" + processoAtual.nome +
                    "\t\t Iniciou a execução");
        }


            // Se há processo em execução, executá-lo
            if (processoAtual != null && !filaProntos.isEmpty()) {
                Processo processoMaisCurto = filaProntos.get(0);

                // Verificar se o processo terminou
                if (processoAtual.tempoRestante < processoAtual.tempoRestante) {

                    //Preemptar o processo atual
                    filaProntos.add(processoAtual);
                    System.out.println(tempoAtual + "\t"
                            + processoAtual.nome + "\t\tPreemptado");
                    // Se há processos na fila, pegar o próximo
                    if (!filaProntos.isEmpty()) {
                        processoAtual = encontrarProcessoMaisCurto(filaProntos);
                        filaProntos.remove(processoAtual);

                        if (!processoAtual.iniciado) {
                            processoAtual.tempoResposta = tempoAtual + 1 - processoAtual.tempoChegada;
                            processoAtual.iniciado = true;
                        }

                        System.out.println((tempoAtual + 1) + "\t" + processoAtual.nome + "\t\tIniciou execução");
                    }
                } else {
                    // Verificar se há processo mais prioritário na fila
                    if (!filaProntos.isEmpty()) {
                        Processo processoMaisCurto = encontrarProcessoMaisCurto(filaProntos);
                        if (processoMaisCurto.tempoSurto < processoAtual.tempoRestante) {
                            // Preemptar o processo atual
                            filaProntos.add(processoAtual);
                            System.out.println((tempoAtual + 1) + "\t" + processoAtual.nome + "\t\tPreemptado");

                            processoAtual = processoMaisCurto;
                            filaProntos.remove(processoAtual);

                            if (!processoAtual.iniciado) {
                                processoAtual.tempoResposta = tempoAtual + 1 - processoAtual.tempoChegada;
                                processoAtual.iniciado = true;
                            }

                            System.out.println((tempoAtual + 1) + "\t" + processoAtual.nome + "\t\tIniciou execução");
                        }
                    }
                }
            }

            // Atualizar tempo de espera dos processos na fila
            for (Processo p : filaProntos) {
                p.tempoEspera++;
            }

            tempoAtual++;
        }
    }

    public static Processo encontrarProcessoMaisCurto(Queue<Processo> fila) {
        return fila.stream()
                .min(Comparator.comparingInt(p -> p.tempoRestante))
                .orElse(null);
    }

    public static void calcularMetricas(List<Processo> processos) {
        System.out.println("\n\nMétricas de Desempenho:");
        System.out.println("Processo\tT.Espera\tT.Resposta\tT.Termino");
        System.out.println("-----------------------------------------------");

        for (Processo p : processos) {
            System.out.println(p.nome + "\t\t" + p.tempoEspera + "\t\t" +
                    p.tempoResposta + "\t\t" + p.tempoTermino);
        }

        // Calcular médias
        double tempoEsperaMedio = processos.stream().mapToInt(p -> p.tempoEspera).average().orElse(0);
        double tempoRespostaMedio = processos.stream().mapToInt(p -> p.tempoResposta).average().orElse(0);

        System.out.println("-----------------------------------------------");
        System.out.println("Média:\t\t" + tempoEsperaMedio + "\t\t" + tempoRespostaMedio);
    }
}





