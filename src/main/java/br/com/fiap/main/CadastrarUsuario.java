package br.com.fiap.main;

import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.apache.http.client.ClientProtocolException;
import br.com.fiap.beans.Usuario;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.services.ViaCep;

public class CadastrarUsuario {

    static String texto(String j) {
        return JOptionPane.showInputDialog(j);
    }

    public static void main(String[] args) throws ClassNotFoundException, ClientProtocolException, IOException {
        try {
            UsuarioDAO dao = new UsuarioDAO();

            Usuario novoUsuario = new Usuario();

            // Solicitar os dados na mesma ordem que na tabela
            novoUsuario.setNome(texto("Nome"));
            novoUsuario.setEmail(texto("Email"));
            novoUsuario.setSenha(texto("Senha"));
            novoUsuario.setCep(texto("CEP"));

            // Obter o endereço usando o CEP
            ViaCep viaCep = new ViaCep();
            Usuario enderecoUsuario = viaCep.getEndereco(novoUsuario.getCep());
            if (enderecoUsuario != null) {
                novoUsuario.setLogradouro(enderecoUsuario.getLogradouro());
                novoUsuario.setComplemento(enderecoUsuario.getComplemento());
                novoUsuario.setBairro(enderecoUsuario.getBairro());
                novoUsuario.setLocalidade(enderecoUsuario.getLocalidade());
                novoUsuario.setUf(enderecoUsuario.getUf());
            } else {
                JOptionPane.showMessageDialog(null, "Endereço não encontrado para o CEP fornecido.");
                return;
            }

            // Verificar se o email já existe
            if (dao.emailExiste(novoUsuario.getEmail())) {
                JOptionPane.showMessageDialog(null, "O email fornecido já está em uso. Por favor, use um email diferente.");
                return;
            }

            // Inserir o novo usuário no banco de dados
            int idGerado = dao.inserir(novoUsuario);
            if (idGerado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso! ID: " + idGerado);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
}
