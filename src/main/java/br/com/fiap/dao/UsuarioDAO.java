package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Usuario;
import br.com.fiap.conexoes.ConexaoFactory;

public class UsuarioDAO {

    private Connection conexao;

    public UsuarioDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    // Inserir
    public int inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO T_Blue_Future_Usuario (nome, email, senha, cep, logradouro, complemento, bairro, localidade, uf) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, new String[]{"ID_Usuario"})) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getCep());
            stmt.setString(5, usuario.getLogradouro());
            stmt.setString(6, usuario.getComplemento());
            stmt.setString(7, usuario.getBairro());
            stmt.setString(8, usuario.getLocalidade());
            stmt.setString(9, usuario.getUf());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Retorna o ID gerado
                    }
                }
            }
        }
        return 0;
    }

    // Verificar se o email já existe
    public boolean emailExiste(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM T_Blue_Future_Usuario WHERE email = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // Atualizar
    public boolean atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE T_Blue_Future_Usuario SET nome=?, email=?, senha=?, cep=?, logradouro=?, complemento=?, bairro=?, localidade=?, uf=? WHERE ID_Usuario=?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getCep());
            stmt.setString(5, usuario.getLogradouro());
            stmt.setString(6, usuario.getComplemento());
            stmt.setString(7, usuario.getBairro());
            stmt.setString(8, usuario.getLocalidade());
            stmt.setString(9, usuario.getUf());
            stmt.setInt(10, usuario.getIdUsuario());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Deletar
    public boolean deletar(int idUsuario) throws SQLException {
        String sql = "DELETE FROM T_Blue_Future_Usuario WHERE ID_Usuario=?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Selecionar todos os usuários
    public List<Usuario> selecionar() throws SQLException {
        String sql = "SELECT * FROM T_Blue_Future_Usuario";
        List<Usuario> listaUsuarios = new ArrayList<>();
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("ID_Usuario"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("cep"),
                    rs.getString("logradouro"),
                    rs.getString("complemento"),
                    rs.getString("bairro"),
                    rs.getString("localidade"),
                    rs.getString("uf")
                );
                listaUsuarios.add(usuario);
            }
        }
        return listaUsuarios;
    }

    // Buscar por ID
    public Usuario buscarPorId(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM T_Blue_Future_Usuario WHERE ID_Usuario = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("ID_Usuario"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("cep"),
                            rs.getString("logradouro"),
                            rs.getString("complemento"),
                            rs.getString("bairro"),
                            rs.getString("localidade"),
                            rs.getString("uf")
                    );
                }
            }
        }
        return null;  // Retorna null se o usuário não for encontrado
    }
}
