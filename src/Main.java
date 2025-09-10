import java.util.*;

public class Main {

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


        public class SimulacaoEscalonamento {

            public static void main(String[] args) {

                //Cria processos conforme a tabela
                List<Processo> processos = new ArrayList<>();
                processos.add(new Processo ("P1", 0, 7));
                processos.add(new Processo("P1", 0, 4));
                processos.add(new Processo("P2", 4, 1));
                processos.add(new Processo("P3", 5, 4));

                //Ordenar processos por tempo de chegada
                processos.sort(Comparator.comparingInt(p -> p.tempoChegada));

                //Simula o escalonamento
                simularEscalonamento(processos);

                //Calcular a exibir métricas
                calcularMetricas(processos);
            }

            public static void simularEscalonamento(List<Processo> processos) {
                int tempoAtual = 0;
                int processosFinalizados = 0;
                Processo processoAtual = null;

                Queue<Processo> filaProntos = new LinkedList<>();

                System.out.println("TEMPO/ PROCESSO / AÇÃO ");
                System.out.println("-----------------------------");

                while(processosFinalizados < processos.size()) {
                    //Verifica se algum processo chega nesse tempo
                    for (Processo processo : processos) {
                        if(processo.tempoChegada == tempoAtual){
                            filaProntos.add(processo);
                            System.out.println(tempoAtual + processo.nome +
                                    " Chegou na fila de prontos ");
                        }
                    }
                }

                //Se não há processo em execução, pega a próxima fila
                if(processoAtual == null || !filaProntos.isEmpty()) {
                    processoAtual = encontrarProcessoMaisCurto(filaProntos);
                    filaProntos.remove(processoAtual);
                }

                if(!processoAtual.iniciado) {
                    processoAtual.tempoResposta = tempoAtual - processoAtual.tempoChegada;
                    processoAtual.iniciado = true;
                }

            }
        }

          }


