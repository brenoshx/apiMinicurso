package com.br.apiMinicurso.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.br.apiMinicurso.dao.ClienteDao;
import com.br.apiMinicurso.dao.ProdutoDao;
import com.br.apiMinicurso.model.Cliente;
import com.br.apiMinicurso.model.Produto;

@RestController
@RequestMapping("/api-teste/")
public class TesteController {

	
	@RequestMapping("/produto")
    public String home(){
        return "Teste API REST";
    }

	
	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> buscaProduto(@PathVariable("id") int codigo) {
		try {
			Produto p = new ProdutoDao().getProduto(codigo);
			if (p != null) {
				return ResponseEntity.status(HttpStatus.OK).body(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
		
		
		
//		if ( codigo == 1 ) {
//			Produto p = new Produto();
//			p.setCodigo( 1 );
//			p.setDescricao("PRODUTO TESTE");
//			
//			return ResponseEntity.status(HttpStatus.OK).body(p);
//		}
//		
		
		
		return ResponseEntity.notFound().build();
	}
	
	
	
	@RequestMapping(value = "/produto/", method =  RequestMethod.POST)
    public ResponseEntity<Produto> Incluir( @RequestBody Produto p)
    {
		try {
			p = new ProdutoDao().insereProduto(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(p);
    }
	
	
	@RequestMapping(value = "/produto/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteProduto(@PathVariable("id") int codigo) {
		try {
			boolean ok = new ProdutoDao().deleteProduto(codigo);
			if (ok) {
				return  ResponseEntity.status(HttpStatus.OK).body("REGISTRO DELETADO COM SUCESSO!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}

		return ResponseEntity.notFound().build();
	}

	
	@RequestMapping(value = "/cliente/", method =  RequestMethod.POST)
    public ResponseEntity<Cliente> Incluir( @RequestBody Cliente c)
    {
		String URI = "https://viacep.com.br/ws/" + c.getCep() + "/json/";
		RestTemplate restTemplate = new RestTemplate();
		Cliente rc = (Cliente)(restTemplate.getForObject(URI, Cliente.class));

		rc.setNome( c.getNome() );
		rc.setNumero(c.getNumero());
		
		try {
			rc = new ClienteDao().insereCliente(rc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(rc);
    }
}
