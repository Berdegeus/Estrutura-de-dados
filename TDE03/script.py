import os
import pandas as pd

def carregar_dados(path):
    dados = []
    for pasta in sorted(os.listdir(path)):
        pasta_path = os.path.join(path, pasta)
        if os.path.isdir(pasta_path):
            run_dados = {}
            for arquivo in os.listdir(pasta_path):
                if arquivo.endswith(".csv"):
                    algoritmo = arquivo.split('_')[0]
                    tamanho = arquivo.split('_')[1].replace('.csv', '')
                    csv_path = os.path.join(pasta_path, arquivo)
                    df = pd.read_csv(csv_path)
                    run_dados[(algoritmo, tamanho)] = df
            dados.append(run_dados)
    return dados

def calcular_medias(dados):
    medias = {}
    for run in dados:
        for key, df in run.items():
            algoritmo, tamanho = key
            if key not in medias:
                medias[key] = {'Tempo(ms)': [], 'Trocas': [], 'Iteracoes': []}
            medias[key]['Tempo(ms)'].append(df['Tempo(ms)'].mean())
            medias[key]['Trocas'].append(df['Trocas'].mean())
            medias[key]['Iteracoes'].append(df['Iteracoes'].mean())
    return medias

def gerar_tabelas(dados, medias):
    # Tabela com todos os valores
    todas_execucoes = []
    for run_id, run in enumerate(dados):
        for key, df in run.items():
            algoritmo, tamanho = key
            for index, row in df.iterrows():
                todas_execucoes.append([run_id + 1, algoritmo, tamanho, row['Tempo(ms)'], row['Trocas'], row['Iteracoes']])

    df_todas_execucoes = pd.DataFrame(todas_execucoes, columns=['Run', 'Algoritmo', 'Tamanho', 'Tempo(ms)', 'Trocas', 'Iteracoes'])
    print(df_todas_execucoes)
    df_todas_execucoes.to_csv("todas_execucoes.csv", index=False)

    # Tabela de médias
    linhas_medias = []
    for key, valores in medias.items():
        algoritmo, tamanho = key
        tempo_medio = sum(valores['Tempo(ms)']) / len(valores['Tempo(ms)'])
        trocas_media = sum(valores['Trocas']) / len(valores['Trocas'])
        iteracoes_media = sum(valores['Iteracoes']) / len(valores['Iteracoes'])
        linhas_medias.append([algoritmo, tamanho, tempo_medio, trocas_media, iteracoes_media])

    df_medias = pd.DataFrame(linhas_medias, columns=['Algoritmo', 'Tamanho', 'Tempo(ms) Médio', 'Trocas Médias', 'Iterações Médias'])
    print(df_medias)
    df_medias.to_csv("resumo_medias.csv", index=False)

def main():
    path = os.getcwd()  # Diretório atual onde as pastas Run estão localizadas
    dados = carregar_dados(path)
    medias = calcular_medias(dados)
    gerar_tabelas(dados, medias)

if __name__ == "__main__":
    main()