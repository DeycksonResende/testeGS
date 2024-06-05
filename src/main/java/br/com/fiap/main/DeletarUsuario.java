package br.com.fiap.main;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.fiap.dao.UsuarioDAO;

public class DeletarUsuario {

    static int inteiro(String j) {
        return Integer.parseInt(JOptionPane.showInputDialog(j));
    }

    public static void main(String[] args) {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            
            // Solicitar ao usuário o ID do usuário a ser deletado
            int idUsuario = inteiro("Insira o ID do usuário a ser deletado:");

            System.out.println("Deletar usuário com ID: " + idUsuario);
            boolean sucesso = dao.deletar(idUsuario);
            if (sucesso) {
                System.out.println("Usuário deletado com sucesso.");
            } else {
                System.out.println("Nenhum usuário foi deletado.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar usuário.");
        }
    }
}
