package com.br.apiMinicurso.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.br.apiMinicurso.Conexao;
import com.br.apiMinicurso.model.Cliente;
import com.br.apiMinicurso.model.Produto;

public class ClienteDao {
	
	
	public Cliente getCliente(int codigo) throws SQLException {
		
		PreparedStatement s = new Conexao().conexao().prepareStatement("SELECT * FROM cadcliente where codcliente = ?");
		s.setInt(1, codigo);
		ResultSet rs = s.executeQuery();
		
		if (rs.next()) {
			Cliente c = new Cliente();
			c.setCodigo(codigo);
			c.setNome(rs.getString("nomecliente"));
			
			return c;
		}
		
		
		return null;
	}
	
	public Cliente insereCliente(Cliente c) throws SQLException {
		PreparedStatement s = new Conexao().conexao().prepareStatement("insert into cadcliente (nomecliente,endereco, bairro, cidade, estado, numero, cep) values (?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		s.setString(1, c.getNome());
		s.setString(2, c.getLogradouro());
		s.setString(3, c.getBairro());
		s.setString(4, c.getLocalidade());
		s.setString(5, c.getUf());
		s.setString(6, c.getNumero());
		s.setString(7, c.getCep());
		s.execute();
		
		ResultSet rs = s.getGeneratedKeys();
		if ( rs.next()) {
			c.setCodigo(rs.getInt(1));
		}
		
		
		
		return c;
	}
}
