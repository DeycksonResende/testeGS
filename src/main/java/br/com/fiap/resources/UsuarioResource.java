package br.com.fiap.resources;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import org.apache.http.client.ClientProtocolException;
import br.com.fiap.beans.Usuario;
import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.services.ViaCep;

@Path("/usuario")
public class UsuarioResource {

    private UsuarioBO usuarioBo;

    public UsuarioResource() throws ClassNotFoundException, SQLException {
        usuarioBo = new UsuarioBO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirRs(Usuario usuario, @Context UriInfo uriInfo)
            throws ClientProtocolException, IOException, SQLException {
        ViaCep viaCep = new ViaCep();
        Usuario endereco = viaCep.getEndereco(usuario.getCep());

        if (endereco != null) {
            usuario.setLogradouro(endereco.getLogradouro());
            usuario.setComplemento(endereco.getComplemento());
            usuario.setBairro(endereco.getBairro());
            usuario.setLocalidade(endereco.getLocalidade());
            usuario.setUf(endereco.getUf());
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Endereço não encontrado para o CEP fornecido.")
                    .build();
        }

        usuarioBo.inserirBo(usuario);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(usuario.getIdUsuario()));
        return Response.created(builder.build()).entity(usuario).build();
    }

    @PUT
    @Path("/{idUsuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarRs(@PathParam("idUsuario") int idUsuario, Usuario usuario)
            throws ClientProtocolException, IOException, SQLException {
        usuario.setIdUsuario(idUsuario);
        ViaCep viaCep = new ViaCep();
        Usuario endereco = viaCep.getEndereco(usuario.getCep());

        if (endereco != null) {
            usuario.setLogradouro(endereco.getLogradouro());
            usuario.setComplemento(endereco.getComplemento());
            usuario.setBairro(endereco.getBairro());
            usuario.setLocalidade(endereco.getLocalidade());
            usuario.setUf(endereco.getUf());
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Endereço não encontrado para o CEP fornecido.")
                    .build();
        }

        usuarioBo.atualizarBo(usuario);
        return Response.ok(usuario).build();
    }

    @DELETE
    @Path("/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarRs(@PathParam("idUsuario") int idUsuario)
            throws SQLException {
        usuarioBo.deletarBo(idUsuario);
        return Response.noContent().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> selecionarRs() throws SQLException {
        return usuarioBo.selecionarBo();
    }
}
