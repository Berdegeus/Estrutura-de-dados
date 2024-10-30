import pandas as pd
import matplotlib.pyplot as plt

# Carrega os dados do arquivo resumo_medias.csv
df = pd.read_csv('resumo_medias.csv')

# Converte a coluna 'Tamanho' para int
df['Tamanho'] = df['Tamanho'].astype(int)

# Remove as entradas do GnomeSort com valores negativos ou inválidos
df = df[~((df['Algoritmo'] == 'GnomeSort') & (df['Trocas Médias'] < 0))]

# Agrupa os dados por Algoritmo e Tamanho, calculando a média dos valores
df_grouped = df.groupby(['Algoritmo', 'Tamanho']).mean().reset_index()

# Lista dos algoritmos para plotagem
algoritmos = df_grouped['Algoritmo'].unique()

# Gráfico de Tempo Médio de Execução
plt.figure(figsize=(10,6))
for algoritmo in algoritmos:
    df_alg = df_grouped[df_grouped['Algoritmo'] == algoritmo].sort_values(by='Tamanho')
    plt.plot(df_alg['Tamanho'], df_alg['Tempo(ms) Médio'], marker='o', label=algoritmo)
plt.xlabel('Tamanho do Vetor')
plt.ylabel('Tempo Médio de Execução (ms)')
plt.title('Tempo Médio de Execução em Função do Tamanho do Vetor')
plt.legend()
plt.grid(True, which="both", ls="--")
plt.xscale('log')
plt.yscale('log')
plt.savefig('grafico_tempo.png')
plt.show()

# Gráfico de Número Médio de Trocas
plt.figure(figsize=(10,6))
for algoritmo in algoritmos:
    df_alg = df_grouped[df_grouped['Algoritmo'] == algoritmo].sort_values(by='Tamanho')
    plt.plot(df_alg['Tamanho'], df_alg['Trocas Médias'], marker='o', label=algoritmo)
plt.xlabel('Tamanho do Vetor')
plt.ylabel('Número Médio de Trocas')
plt.title('Número Médio de Trocas em Função do Tamanho do Vetor')
plt.legend()
plt.grid(True, which="both", ls="--")
plt.xscale('log')
plt.yscale('log')
plt.savefig('grafico_trocas.png')
plt.show()