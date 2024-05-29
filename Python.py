import json
import os
import oracledb
import PySimpleGUI as sg

# Credenciais do banco de dados
credenciais_path = "C:\\Users\\Desktop\\OneDrive\\Documentos\\GS Global Solution 1_ano\\COMPUTATIONAL THINKING USING PYTHON\\credenciais.json"
with open(credenciais_path) as f:
    credenciais = json.load(f)

os.environ["USER"] = credenciais["USER"]
os.environ["PASS"] = credenciais["PASS"]
USER = os.environ.get("USER")
PASS = os.environ.get("PASS")

dsnStr = oracledb.makedsn("oracle.fiap.com.br", 1521, "ORCL")
connection = oracledb.connect(user=USER, password=PASS, dsn=dsnStr)

# Função para criar a interface inicial
def criar_menu():
    layout = [
        [sg.Text('Escolha uma tabela para manipular')],
        [sg.Button('T_Blue_Future_Poluicao_Agua_Cidades')],
        [sg.Button('T_Blue_Future_Producao_de_Plastico_Global')],
        [sg.Button('T_Blue_Future_Usuario')],
        [sg.Button('Sair')]
    ]
    
    return sg.Window('Menu Principal', layout)

# Função para criar o menu de CRUD
def criar_menu_crud(tabela):
    layout = [
        [sg.Text(f'Manipulando a tabela: {tabela}')],
        [sg.Button('Adicionar')],
        [sg.Button('Visualizar')],
        [sg.Button('Atualizar')],
        [sg.Button('Deletar')],
        [sg.Button('Voltar')]
    ]
    
    return sg.Window(f'Menu CRUD - {tabela}', layout)

# Função para criar a interface de seleção de visualização
def criar_menu_selecao_visualizacao():
    layout = [
        [sg.Text('Escolha uma opção de visualização')],
        [sg.Button('Visualizar Todos')],
        [sg.Button('Visualizar por ID')],
        [sg.Button('Voltar')]
    ]
    
    return sg.Window('Seleção de Visualização', layout)

# Função para criar a interface de leitura
def criar_menu_visualizar(tabela, colunas):
    layout = [
        [sg.Text(f'Selecione uma coluna para visualizar os dados ou veja todos os dados de {tabela}')],
        [sg.Listbox(values=colunas, select_mode='single', key='-COLUNA-', size=(40, len(colunas)))],
        [sg.Button('Ver Coluna')],
        [sg.Button('Ver Todos')],
        [sg.Button('Voltar')]
    ]
    
    return sg.Window(f'Visualizar - {tabela}', layout)

# Função para exibir os dados
def exibir_dados(tabela, dados, id_selecionado=None):
    headings = [desc[0] for desc in dados.description]
    valores = [list(row) for row in dados.fetchall()]
    
    layout = [
        [sg.Table(values=valores, headings=headings, display_row_numbers=True, auto_size_columns=True)],
        [sg.Button('Voltar')]
    ]
    
    return sg.Window(f'Dados - {tabela}', layout)

# Função para criar a interface de atualização
def criar_menu_atualizar(tabela, colunas):
    layout = [
        [sg.Text(f'Selecione uma coluna para atualizar os dados ou atualize todos os dados de {tabela}')],
        [sg.Listbox(values=colunas, select_mode='single', key='-COLUNA-', size=(40, len(colunas)))],
        [sg.Button('Atualizar Coluna')],
        [sg.Button('Atualizar Todos')],
        [sg.Button('Voltar')]
    ]
    
    return sg.Window(f'Atualizar - {tabela}', layout)

# Função para atualizar um registro
# Função para atualizar um registro
def atualizar_registro(tabela):
    cursor = connection.cursor()
    
    # Obter as colunas da tabela
    cursor.execute(f"SELECT * FROM {tabela} WHERE ROWNUM = 1")
    colunas = [desc[0] for desc in cursor.description]

    layout_id = [
        [sg.Text('Insira o ID para atualização')],
        [sg.Input(key='-ID-', size=(20, 1))],
        [sg.Button('Confirmar'), sg.Button('Cancelar')]
    ]
    window_id = sg.Window('Seleção de ID para Atualização', layout_id)
    event_id, values_id = window_id.read()
    window_id.close()

    if event_id == sg.WIN_CLOSED or event_id == 'Cancelar':
        return

    id_selecionado = values_id['-ID-']
    cursor.execute(f"SELECT * FROM {tabela} WHERE {colunas[0]} = :id", {'id': id_selecionado})
    dados_atuais = cursor.fetchall()

    if not dados_atuais:
        sg.popup(f'ID {id_selecionado} não encontrado na tabela {tabela}.')
        return

    layout_atualizar = [[sg.Text(f'Atualizando dados do ID {id_selecionado} na tabela {tabela}')]]
    
    for i, coluna in enumerate(colunas):
        layout_atualizar.append([sg.Text(f'{coluna}:'), sg.Input(default_text=dados_atuais[0][i], key=f'-{coluna}-', size=(40, 1))])
    layout_atualizar.append([sg.Button('Atualizar'), sg.Button('Cancelar')])
    
    window_atualizar = sg.Window('Atualização de Dados', layout_atualizar)
    
    while True:
        event_atualizar, values_atualizar = window_atualizar.read()

        if event_atualizar == sg.WIN_CLOSED or event_atualizar == 'Cancelar':
            window_atualizar.close()
            return
        elif event_atualizar == 'Atualizar':
            novos_valores = {coluna: values_atualizar[f'-{coluna}-'] for coluna in colunas}
            sql_update = f"UPDATE {tabela} SET "
            sql_update += ", ".join([f"{coluna} = :{coluna}" for coluna in colunas])
            sql_update += f" WHERE {colunas[0]} = :id"

            cursor.execute(sql_update, {**novos_valores, 'id': id_selecionado})
            connection.commit()
            sg.popup('Dados atualizados com sucesso!')
            window_atualizar.close()
            return

# Funções CRUD (estas devem ser implementadas)
def visualizar_registro(tabela):
    cursor = connection.cursor()
    cursor.execute(f"SELECT * FROM {tabela} WHERE ROWNUM = 1")
    colunas = [desc[0] for desc in cursor.description]
    
    window_selecao = criar_menu_selecao_visualizacao()
    
    while True:
        event_selecao, values_selecao = window_selecao.read()
        
        if event_selecao == sg.WIN_CLOSED or event_selecao == 'Voltar':
            window_selecao.close()
            return
        elif event_selecao == 'Visualizar Todos':
            window_selecao.close()
            window_visualizar = criar_menu_visualizar(tabela, colunas)
            while True:
                event_visualizar, values_visualizar = window_visualizar.read()
                
                if event_visualizar == sg.WIN_CLOSED or event_visualizar == 'Voltar':
                    window_visualizar.close()
                    return
                elif event_visualizar == 'Ver Coluna':
                    coluna = values_visualizar['-COLUNA-'][0]
                    cursor.execute(f"SELECT {coluna} FROM {tabela}")
                    dados = cursor
                    window_visualizar.hide()
                    window_dados = exibir_dados(tabela, dados)
                    window_dados.read(close=True)
                    window_visualizar.un_hide()
                elif event_visualizar == 'Ver Todos':
                    cursor.execute(f"SELECT * FROM {tabela}")
                    dados = cursor
                    window_visualizar.hide()
                    window_dados = exibir_dados(tabela, dados)
                    window_dados.read(close=True)
                    window_visualizar.un_hide()
        elif event_selecao == 'Visualizar por ID':
            window_selecao.close()
            layout_id = [
                [sg.Text('Insira o ID para visualização')],
                [sg.Input(key='-ID-', size=(20, 1))],
                [sg.Button('Confirmar'), sg.Button('Cancelar')]
            ]
            window_id = sg.Window('Seleção por ID', layout_id)
            while True:
                event_id, values_id = window_id.read()
                if event_id == sg.WIN_CLOSED or event_id == 'Cancelar':
                    window_id.close()
                    return
                elif event_id == 'Confirmar':
                    id_selecionado = values_id['-ID-']
                    cursor.execute(f"SELECT * FROM {tabela} WHERE {colunas[0]} = :id", {'id': id_selecionado})
                    dados = cursor
                    window_id.close()
                    window_visualizar = criar_menu_visualizar(tabela, colunas)
                    while True:
                        event_visualizar, values_visualizar = window_visualizar.read()
                        
                        if event_visualizar == sg.WIN_CLOSED or event_visualizar == 'Voltar':
                            window_visualizar.close()
                            return
                        elif event_visualizar == 'Ver Coluna':
                            coluna = values_visualizar['-COLUNA-'][0]
                            cursor.execute(f"SELECT {coluna} FROM {tabela} WHERE {colunas[0]} = :id", {'id': id_selecionado})
                            dados = cursor
                            window_visualizar.hide()
                            window_dados = exibir_dados(tabela, dados, id_selecionado)
                            window_dados.read(close=True)
                            window_visualizar.un_hide()
                        elif event_visualizar == 'Ver Todos':
                            window_visualizar.hide()
                            window_dados = exibir_dados(tabela, dados, id_selecionado)
                            window_dados.read(close=True)
                            window_visualizar.un_hide()
# Função para criar a interface de inserção
def criar_menu_inserir(tabela, colunas):
    layout = [
        [sg.Text(f'Inserir dados na tabela: {tabela}')],
    ]
    
    for coluna in colunas:
        layout.append([sg.Text(f'{coluna}:'), sg.Input(key=f'-{coluna}-', size=(40, 1))])
        
    layout.append([sg.Button('Inserir Dados'), sg.Button('Cancelar')])
    
    return sg.Window(f'Inserir Dados - {tabela}', layout)

# Função para inserir um novo registro
def inserir_registro(tabela, colunas, values_inserir):
    cursor = connection.cursor()
    
    colunas_str = ', '.join(colunas)
    valores_str = ', '.join([f':{coluna}' for coluna in colunas])
    sql_insert = f"INSERT INTO {tabela} ({colunas_str}) VALUES ({valores_str})"
    
    try:
        cursor.execute(sql_insert, values_inserir)
        connection.commit()
        sg.popup('Dados inseridos com sucesso!')
    except Exception as e:
        sg.popup_error(f'Erro ao inserir dados: {str(e)}')
    
    cursor.close()
                            
def criar_menu_deletar(tabela):
    layout = [
        [sg.Text(f'Digite o ID do registro que deseja deletar na tabela: {tabela}')],
        [sg.Input(key='-ID-')],
        [sg.Button('Deletar'), sg.Button('Cancelar')]
    ]
    
    return sg.Window(f'Deletar - {tabela}', layout)

# Função para deletar um registro
def deletar_registro(tabela, id_coluna, id_deletar):
    try:
        cursor = connection.cursor()
        delete_query = f"DELETE FROM {tabela} WHERE {id_coluna} = :id"
        cursor.execute(delete_query, {'id': id_deletar})
        connection.commit()
        deleted_rows = cursor.rowcount  # Verifica quantas linhas foram deletadas
        cursor.close()

        if deleted_rows > 0:
            sg.popup(f'Registro com {id_coluna} {id_deletar} deletado com sucesso!')
        else:
            sg.popup(f'Nenhum registro com {id_coluna} {id_deletar} encontrado para deletar.')
    except oracledb.DatabaseError as e:
        error, = e.args
        print(f"Erro ao deletar registro: {error.message}")

# Atualizar a função main() para incluir a exclusão de dados
def main():
    window = criar_menu()
    tabela_selecionada = None
    
    while True:
        event, values = window.read()
        
        if event == sg.WIN_CLOSED or event == 'Sair':
            break
        elif event in ['T_Blue_Future_Poluicao_Agua_Cidades', 'T_Blue_Future_Producao_de_Plastico_Global', 'T_Blue_Future_Usuario']:
            tabela_selecionada = event
            window.close()  # Fecha a janela atual
            window_crud = criar_menu_crud(tabela_selecionada)
            
            while True:
                event_crud, values_crud = window_crud.read()
                
                if event_crud == sg.WIN_CLOSED or event_crud == 'Voltar':
                    window_crud.close()
                    window = criar_menu()  # Reabre o menu principal
                    break
                elif event_crud == 'Adicionar':
                    window_crud.close()
                    cursor = connection.cursor()
                    cursor.execute(f"SELECT * FROM {tabela_selecionada} WHERE ROWNUM = 1")
                    colunas = [desc[0] for desc in cursor.description]
                    window_inserir = criar_menu_inserir(tabela_selecionada, colunas)
                    
                    while True:
                        event_inserir, values_inserir = window_inserir.read()
                        
                        if event_inserir == sg.WIN_CLOSED or event_inserir == 'Cancelar':
                            window_inserir.close()
                            break
                        elif event_inserir == 'Inserir Dados':
                            values_inserir = {coluna: values_inserir[f'-{coluna}-'] for coluna in colunas}
                            inserir_registro(tabela_selecionada, colunas, values_inserir)
                            window_inserir.close()
                            window_inserir = criar_menu_inserir(tabela_selecionada, colunas)
                        
                    cursor.close()
                    window_crud = criar_menu_crud(tabela_selecionada)
                elif event_crud == 'Visualizar':
                    window_crud.close()
                    visualizar_registro(tabela_selecionada)
                    window_crud = criar_menu_crud(tabela_selecionada)
                elif event_crud == 'Atualizar':
                    window_crud.close()
                    if tabela_selecionada:
                        atualizar_registro(tabela_selecionada)
                    window_crud = criar_menu_crud(tabela_selecionada)
                elif event_crud == 'Deletar':
                    window_crud.close()
                    window_deletar = criar_menu_deletar(tabela_selecionada)
                    
                    while True:
                        event_deletar, values_deletar = window_deletar.read()

                        if event_deletar == sg.WIN_CLOSED or event_deletar == 'Cancelar':
                            window_deletar.close()
                            break
                        elif event_deletar == 'Deletar':
                            id_deletar = values_deletar['-ID-']
                            if tabela_selecionada == 'T_Blue_Future_Poluicao_Agua_Cidades':
                                id_coluna = 'ID_Poluicao_Agua_Cidades'
                            elif tabela_selecionada == 'T_Blue_Future_Producao_de_Plastico_Global':
                                id_coluna = 'ID_Producao_de_Plastico_Global'
                            elif tabela_selecionada == 'T_Blue_Future_Usuario':
                                id_coluna = 'ID_Usuario'
                            else:
                                id_coluna = 'ID'  # Caso de segurança, deve ser substituído pelo nome correto

                            deletar_registro(tabela_selecionada, id_coluna, id_deletar)
                            window_deletar.close()
                            window_deletar = criar_menu_deletar(tabela_selecionada)

                    window_crud = criar_menu_crud(tabela_selecionada)
                elif event == 'Quit':
                    window.close()

if __name__ == '__main__':
    main()