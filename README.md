# 📚 Projeto de Simulação de Escalonamento SRTF

## 🎯 Descrição do Projeto

Este projeto implementa uma **simulação detalhada do algoritmo de escalonamento Shortest Remaining Time First (SRTF)**, um algoritmo preemptivo que sempre seleciona o processo com o menor tempo restante de execução. Desenvolvido em Java, o simulador demonstra o comportamento desse algoritmo em um ambiente de sistema operacional, calculando métricas de desempenho essenciais para análise de eficiência.

---

## ⚙️ Características do Algoritmo SRTF

### Princípio de Funcionamento
O SRTF (Shortest Remaining Time First) é um algoritmo de escalonamento preemptivo que:
- Sempre seleciona o processo com o **menor tempo restante de execução**
- **Preempta** o processo em execução quando um novo processo com tempo de surto menor chega
- Minimiza o **tempo médio de espera** (teoricamente ótimo)
- Oferece alta **responsividade** para processos curtos

### Vantagens
- ✅ Menor tempo médio de espera entre algoritmos de escalonamento
- ✅ Eficiência no uso da CPU
- ✅ Responsividade para processos curtos
- ✅ Prevenção contra starvation através de preempção

### Desvantagens
- ⚠️ Dificuldade de implementação em sistemas reais
- ⚠️ Impossibilidade de conhecer tempo de execução futuro
- ⚠️ Overhead de preempção constante
- ⚠️ Potencial starvation de processos longos

---

## 📊 Processos Utilizados na Simulação

| Processo | Tempo de Chegada | Tempo de Surto |
|----------|------------------|----------------|
| P1       | 0                | 7              |
| P2       | 2                | 4              |
| P3       | 4                | 1              |
| P4       | 5                | 4              |

---

## ⏱️ Cronograma de Execução

```
Tempo 0-2:   P1 executa (ininterrupto)
Tempo 2-4:   P2 executa (preempta P1 - menor tempo restante)
Tempo 4-5:   P3 executa (preempta P2 - tempo restante menor)
Tempo 5-7:   P2 executa (P3 terminou, P2 tem menor tempo restante)
Tempo 7-11:  P4 executa (P2 terminou, P4 tem menor tempo que P1)
Tempo 11-16: P1 executa (todos os outros processos terminaram)
```

### Pontos de Preempção:
1. **Tempo 2**: P2 chega (tempo restante: 4) vs P1 (tempo restante: 5) → PREEMPÇÃO
2. **Tempo 4**: P3 chega (tempo restante: 1) vs P2 (tempo restante: 2) → PREEMPÇÃO

---

## 📈 Métricas de Desempenho

### Resultados Obtidos:

| Processo | Tempo de Espera | Tempo de Resposta | Tempo de Término |
|----------|-----------------|-------------------|------------------|
| P1       | 9               | 0                 | 16               |
| P2       | 1               | 0                 | 7                |
| P3       | 0               | 0                 | 5                |
| P4       | 2               | 2                 | 11               |

### Estatísticas Consolidadas:
- **Tempo médio de espera**: 3.00 unidades de tempo
- **Tempo médio de resposta**: 0.50 unidades de tempo  
- **Throughput**: 0.25 processos/unidade de tempo
- **Makespan**: 16 unidades de tempo

---

## 🏗️ Estrutura do Código

### Classe Processo
Armazena todas as informações de um processo:
- Nome, tempo de chegada, tempo de surto
- Tempo restante, tempo de término
- Métricas de desempenho (espera, resposta)

### Lógica Principal
1. **Inicialização**: Criação dos processos com seus parâmetros
2. **Simulação**: Loop principal que avança por unidades de tempo
3. **Verificação de chegada**: Adiciona processos à fila de prontos
4. **Ordenação SRTF**: Ordena a fila por tempo restante (menor primeiro)
5. **Preempção**: Verifica se processos novos devem preemptar o atual
6. **Execução**: Processo em execução tem seu tempo restante decrementado
7. **Cálculo de métricas**: Atualização contínua dos tempos de espera

### Algoritmo de Decisão
```java
if (processoAtual != null && !filaProntos.isEmpty()) {
    Processo processoMaisCurto = filaProntos.get(0);
    if (processoMaisCurto.tempoRestante < processoAtual.tempoRestante) {
        // Realiza preempção
        filaProntos.add(processoAtual);
        processoAtual = processoMaisCurto;
        filaProntos.remove(0);
    }
}
```

---

## 🚀 Como Executar

### Pré-requisitos
- Java JDK 8 ou superior
- IDE Java (IntelliJ, Eclipse) ou terminal

### Compilação e Execução
```bash
javac EscalonamentoSRTF.java
java EscalonamentoSRTF
```

### Saída Esperada
O programa exibirá:
- Linha do tempo detalhada com todas as transições
- Tabela completa com métricas de cada processo
- Estatísticas consolidadas de desempenho

---

## 🎓 Aplicações e Importância Acadêmica

Este simulador é valioso para:
- Compreensão prática de algoritmos de escalonamento
- Análise comparativa de políticas de escalonamento
- Visualização do comportamento de preempção
- Cálculo de métricas de desempenho de sistemas
- Estudo de casos de uso em sistemas operacionais

---

## 📋 Considerações Finais

Esta implementação demonstra com precisão o comportamento teórico do algoritmo SRTF, incluindo todos os pontos de preempção e cálculos de métricas. O projeto serve como base sólida para o entendimento dos trade-offs envolvidos no escalonamento de processos e oferece insights valiosos sobre o funcionamento interno de sistemas operacionais.

O código foi desenvolvido com foco em:
- Clareza e legibilidade
- Precisão na implementação do algoritmo
- Cálculo correto de todas as métricas
- Facilidade de extensão para outros algoritmos

---

**Desenvolvido para fins educacionais** - Simulador de Escalonamento SRTF © 2024
