package br.com.fiap.services;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;
import br.com.fiap.beans.Usuario;

public class ViaCep {

    public Usuario getEndereco(String cep) throws ClientProtocolException, IOException {
        Usuario endereco = null;

        // request
        HttpGet request = new HttpGet("https://viacep.com.br/ws/" + cep + "/json/");

        // client
        CloseableHttpClient httpCliente = HttpClientBuilder.create().disableRedirectHandling().build();

        // response
        CloseableHttpResponse response = httpCliente.execute(request);

        // entity
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            String result = EntityUtils.toString(entity);
            Gson gson = new Gson();
            endereco = gson.fromJson(result, Usuario.class);
        }

        return endereco;
    }
}
