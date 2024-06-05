package br.com.fiap.main;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.Usuario;
import br.com.fiap.bo.UsuarioBO;

public class SelecionarUsuario {

    public static void main(String[] args) {
        try {
            UsuarioBO usuarioBo = new UsuarioBO();
            List<Usuario> listaUsuarios = usuarioBo.selecionarBo();

            if (listaUsuarios.isEmpty()) {
                System.out.println("Nenhum usuário encontrado.");
            } else {
                for (Usuario usuario : listaUsuarios) {
                    System.out.println("ID: " + usuario.getIdUsuario());
                    System.out.println("Nome: " + usuario.getNome());
                    System.out.println("Email: " + usuario.getEmail());
                    System.out.println("CEP: " + usuario.getCep());
                    System.out.println("Logradouro: " + usuario.getLogradouro());
                    System.out.println("Complemento: " + usuario.getComplemento());
                    System.out.println("Bairro: " + usuario.getBairro());
                    System.out.println("Localidade: " + usuario.getLocalidade());
                    System.out.println("UF: " + usuario.getUf());
                    System.out.println("----------------------------------------------------");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao selecionar usuários.");
        }
    }
}
