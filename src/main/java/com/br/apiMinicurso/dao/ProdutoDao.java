package com.br.apiMinicurso.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.br.apiMinicurso.Conexao;
import com.br.apiMinicurso.model.Produto;

public class ProdutoDao {
	
	
	public Produto getProduto(int codigo) throws SQLException {
		
		PreparedStatement s = new Conexao().conexao().prepareStatement("SELECT * FROM cadproduto where codproduto = ?");
		s.setInt(1, codigo);
		ResultSet rs = s.executeQuery();
		
		if (rs.next()) {
			Produto p = new Produto();
			p.setCodigo(codigo);
			p.setDescricao(rs.getString("descproduto"));
			
			return p;
		}
		
		
		return null;
	}
	
	public Produto insereProduto(Produto p) throws SQLException {
		PreparedStatement s = new Conexao().conexao().prepareStatement("insert into cadproduto (descproduto) values (?)",Statement.RETURN_GENERATED_KEYS);
		s.setString(1, p.getDescricao());
		s.execute();
		
		ResultSet rs = s.getGeneratedKeys();
		if ( rs.next()) {
			p.setCodigo(rs.getInt(1));
		}
		
		
		
		return p;
	}
	
	public boolean deleteProduto(int codigo) throws SQLException {
		
		PreparedStatement s = new Conexao().conexao().prepareStatement("DELETE FROM cadproduto where codproduto = ?");
		s.setInt(1, codigo);
		
		
		if (s.executeUpdate()>0) {
			
			return true;
		}
		
		
		return false;
	}
	
}
