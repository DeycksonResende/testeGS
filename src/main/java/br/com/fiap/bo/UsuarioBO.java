package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.Usuario;
import br.com.fiap.dao.UsuarioDAO;

public class UsuarioBO {

    private UsuarioDAO usuarioDAO;

    public UsuarioBO() throws ClassNotFoundException, SQLException {
        this.usuarioDAO = new UsuarioDAO();
    }

    public void inserirBo(Usuario usuario) throws SQLException {
        usuarioDAO.inserir(usuario);
    }

    public void atualizarBo(Usuario usuario) throws SQLException {
        usuarioDAO.atualizar(usuario);
    }

    public void deletarBo(int idUsuario) throws SQLException {
        usuarioDAO.deletar(idUsuario);
    }

    public List<Usuario> selecionarBo() throws SQLException {
        return usuarioDAO.selecionar();
    }

    public Usuario buscarPorIdBo(int idUsuario) throws SQLException {
        return usuarioDAO.buscarPorId(idUsuario);
    }
}
