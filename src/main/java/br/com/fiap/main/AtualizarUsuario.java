package br.com.fiap.main;

import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import org.apache.http.client.ClientProtocolException;

import br.com.fiap.beans.Usuario;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.services.ViaCep;

public class AtualizarUsuario {

    static String texto(String j) {
        return JOptionPane.showInputDialog(j);
    }

    static int inteiro(String j) {
        return Integer.parseInt(JOptionPane.showInputDialog(j));
    }

    public static void main(String[] args) throws ClassNotFoundException, ClientProtocolException, IOException {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            int idUsuario = inteiro("Insira o idUsuario a ser alterado");
            Usuario usuarioExistente = dao.buscarPorId(idUsuario);

            if (usuarioExistente != null) {
                usuarioExistente.setNome(texto("Nome"));
                usuarioExistente.setEmail(texto("Email"));
                usuarioExistente.setSenha(texto("Senha"));
                usuarioExistente.setCep(texto("CEP"));

                ViaCep viaCep = new ViaCep();
                Usuario enderecoUsuario = viaCep.getEndereco(usuarioExistente.getCep());
                if (enderecoUsuario != null) {
                    usuarioExistente.setLogradouro(enderecoUsuario.getLogradouro());
                    usuarioExistente.setComplemento(enderecoUsuario.getComplemento());
                    usuarioExistente.setBairro(enderecoUsuario.getBairro());
                    usuarioExistente.setLocalidade(enderecoUsuario.getLocalidade());
                    usuarioExistente.setUf(enderecoUsuario.getUf());
                } else {
                    JOptionPane.showMessageDialog(null, "Endereço não encontrado para o CEP fornecido.");
                    return;
                }

                boolean sucesso = dao.atualizar(usuarioExistente);
                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuário com ID " + idUsuario + " não encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário: " + e.getMessage());
        }
    }
}
