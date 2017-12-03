package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.modelo.Projeto;
import junit.framework.Assert;

public class ProjetoTest {
	private HttpServer server;
	private Client client;
	private WebTarget target;
	
	@Before
	public void inicializaTeste() {
		this.server = Servidor.startaServidor();
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		
		this.client = ClientBuilder.newClient(config);
		this.target = client.target("http://localhost:8080");
	}
	
	@After
	public void encerraTeste() {
		server.stop();
	}
	
	@Test
	public void testaProjetoResource() {
		Projeto projeto = this.target.path("/projetos/1").request().get(Projeto.class);
		
		Assert.assertEquals("Minha loja", projeto.getNome());
	}
}
