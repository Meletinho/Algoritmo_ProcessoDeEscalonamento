import java.util.ArrayList;
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


        

          }


