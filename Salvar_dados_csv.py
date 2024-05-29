import oracledb
import pandas as pd
import json
import threading
import time

# Função para ler as credenciais do arquivo JSON
def ler_credenciais(caminho_credenciais):
    with open(caminho_credenciais, 'r') as file:
        credenciais = json.load(file)
    return credenciais

# Função para conectar ao banco de dados
def conectar_banco(credenciais):
    dsn = oracledb.makedsn('oracle.fiap.com.br', 1521, sid='orcl')
    conn = oracledb.connect(user=credenciais['USER'], password=credenciais['PASS'], dsn=dsn)
    return conn

# Função para ler os arquivos CSV
def ler_csv(caminho_csv):
    df = pd.read_csv(caminho_csv)
    return df

# Função para verificar se uma linha já existe no banco de dados
def linha_existe(cursor, tabela, linha):
    condicoes = ' AND '.join([f"{col} = :{col}" for col in linha.keys()])
    sql = f"SELECT COUNT(*) FROM {tabela} WHERE {condicoes}"
    cursor.execute(sql, linha)
    count = cursor.fetchone()[0]
    return count > 0

# Função para inserir os dados no banco de dados
def inserir_dados(conn, df, tabela):
    cursor = conn.cursor()

    # Ajuste de nomes das colunas para corresponder ao banco de dados Oracle
    if tabela == 'T_Blue_Future_Producao_de_Plastico_Global':
        df.columns = ['Entidade', 'Ano', 'Produção_Anual_de_Plastico']
    elif tabela == 'T_Blue_Future_Poluicao_Agua_Cidades':
        df.columns = ['Cidade', 'Regiao', 'Entidade', 'Qualidade_do_Ar', 'Poluicao_da_Agua']

    # Preparar SQL dinâmico com marcadores de ligação
    colunas = ', '.join(df.columns)
    marcadores = ', '.join([':' + col.replace(' ', '_') for col in df.columns])
    sql = f'INSERT INTO {tabela} ({colunas}) VALUES ({marcadores})'

    for _, row in df.iterrows():
        linha_dict = row.to_dict()
        if not row.isnull().values.any():
            if not linha_existe(cursor, tabela, linha_dict):
                cursor.execute(sql, linha_dict)
                print(f"Linha inserida: {linha_dict}")
            else:
                print(f"Linha já existente, não inserida: {linha_dict}")

    conn.commit()
    cursor.close()

# Função para salvar os dados do CSV no banco a cada intervalo de tempo
def salvar_periodicamente(conn, caminho_csv, tabela, intervalo_segundos):
    while True:
        df = ler_csv(caminho_csv)
        inserir_dados(conn, df, tabela)
        print(f'Dados do arquivo {caminho_csv} salvos no banco de dados.')

        # Aguardar o intervalo especificado em segundos antes de verificar novamente
        time.sleep(intervalo_segundos)

# Caminhos dos arquivos CSV
caminho_csv1 = r'C:\Users\Desktop\OneDrive\Documentos\GS Global Solution 1_ano\COMPUTATIONAL THINKING USING PYTHON\1- producao-de-plastico-global.csv'
caminho_csv2 = r'C:\Users\Desktop\OneDrive\Documentos\GS Global Solution 1_ano\COMPUTATIONAL THINKING USING PYTHON\5- poluicao-agua-cidades.csv'

# Caminho do arquivo de credenciais
caminho_credenciais = r'C:\Users\Desktop\OneDrive\Documentos\GS Global Solution 1_ano\COMPUTATIONAL THINKING USING PYTHON\credenciais.json'

# Ler as credenciais
credenciais = ler_credenciais(caminho_credenciais)

# Conectar ao banco de dados
conn = conectar_banco(credenciais)

# Criar threads para salvar os dados dos CSVs no banco a cada intervalo de tempo
intervalo_minutos = 1
intervalo_segundos = intervalo_minutos * 10

thread_csv1 = threading.Thread(target=salvar_periodicamente, args=(conn, caminho_csv1, 'T_Blue_Future_Producao_de_Plastico_Global', intervalo_segundos))
thread_csv2 = threading.Thread(target=salvar_periodicamente, args=(conn, caminho_csv2, 'T_Blue_Future_Poluicao_Agua_Cidades', intervalo_segundos))

# Iniciar as threads
thread_csv1.start()
thread_csv2.start()

# Aguardar as threads terminarem (o que nunca acontecerá neste caso)
thread_csv1.join()
thread_csv2.join()

# Fechar a conexão (este código nunca será executado devido ao loop infinito)
# conn.close()
