package com.br.apiMinicurso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
   public Connection conexao() throws SQLException {
	   Connection conexao = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/MINICURSO",
				"postgres", "12345");
	   return conexao;
   }
}
