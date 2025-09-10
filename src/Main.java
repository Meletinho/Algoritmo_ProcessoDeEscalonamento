import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        }

          }


