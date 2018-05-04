package aula;
import xml.Cidade;
import xml.DadosPrevisao;
import xml.Previsao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Banco {
	public Connection conectar() throws SQLException, ClassNotFoundException {
		Connection conexao = null;
		Class.forName("org.sqlite.JDBC");
		File bd = new File("bdprevisao.db");
		/* verifica se o arquivo do BD existe na raiz do projeto */
		if( !bd.exists() ){
			/* cria o arquivo do BD na raiz do projeto e cria uma conex�o para o BD */
			conexao = DriverManager.getConnection("jdbc:sqlite:bdprevisao.db");
			/* como o BD n�o existe ent�o � necess�rio criar as tabelas */
			createTableCidade();
			createTablePrevisao();
		}
		else{
			/* cria uma conex�o com o BD */
			conexao = DriverManager.getConnection("jdbc:sqlite:bdprevisao.db");
		}
		conexao.setAutoCommit(false);
		return conexao;
	}
	public boolean createTablePrevisao() throws SQLException{
		Connection conexao = null;
		conexao = DriverManager.getConnection("jdbc:sqlite:bdprevisao.db");
		Statement stmt = conexao.createStatement();
		String sql = "create table if not exists tbprevisao( " +
				"id int not null," +
				"dia date not null," +
				"tempo char(3) not null," +
				"minima float not null," +
				"maxima float not null," +
				"iuv float not null," +
				"primary key (id, dia)," +
				"foreign key (id) references tbcidade(id) " +
				")";
		stmt.executeUpdate(sql);
		stmt.close();
		return true;
	}
	public boolean createTableCidade() throws SQLException{
		Connection conexao = null;
		conexao = DriverManager.getConnection("jdbc:sqlite:bdprevisao.db");
		Statement stmt = conexao.createStatement();
		String sql = "create table if not exists tbcidade( " +
				"id int not null primary key," +
				"nome varchar(80) not null," +
				"uf char(2) not null,"+
				"atualizacao date" +
				")";
		stmt.executeUpdate(sql);
		stmt.close();
		return true;
	}
	public boolean insertCidade(Cidade cidade) throws SQLException{
		Connection conexao = null;
		conexao = DriverManager.getConnection("jdbc:sqlite:bdprevisao.db");
		/* o campo atualizacao ir� receber o valor padr�o, ou seja, null */
		String sql = "insert or ignore into tbcidade(id,nome,uf) values(?,?,?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, cidade.id );
		stmt.setString(2, cidade.nome );
		stmt.setString(3, cidade.uf );
		stmt.execute();
		stmt.close();
		conexao.commit();
		return true;
	}
	public boolean insertDados(int id) throws Exception{
		urlPrevisao previsao = new urlPrevisao();
		String p = previsao.getXMLPrevisao(String.valueOf(id));
		DadosPrevisao[] ds = previsao.xmlToObjectPrevisoes(p);
		Connection conexao = null;
		conexao = DriverManager.getConnection("jdbc:sqlite:bdprevisao.db");
		/* o campo atualizacao ir� receber o valor padr�o, ou seja, null */
		String sql = "insert or ignore into tbprevisao(id,tempo,dia,minima,maxima,iuv) values(?,?,?,?,?,?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		for(DadosPrevisao d:ds) {
			stmt.setInt(1, id);
			stmt.setString(2, d.tempo);
			stmt.setString(3, d.dia);
			stmt.setFloat(4, Float.valueOf(d.minima));
			stmt.setFloat(5, Float.valueOf(d.maxima));
			stmt.setFloat(6, Float.valueOf(d.iuv));
			stmt.execute();
		}
		stmt.close();
		System.out.println("Dados inseridos na tabela");
		return true;
		
	}
	public List<DadosPrevisao> selectDados(String sql) throws SQLException{
		Connection conexao = null;
		conexao = DriverManager.getConnection("jdbc:sqlite:bdprevisao.db");
		Statement stmt = conexao.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<DadosPrevisao> lista = new ArrayList<>();
		DadosPrevisao dados;
		while(rs.next()) {
			dados = new DadosPrevisao();
			dados.setTempo(rs.getString("tempo"));
			dados.setDia(rs.getString("dia"));
			dados.setMinima(String.valueOf(rs.getFloat("minima")));
			dados.setMaxima(String.valueOf(rs.getFloat("maxima")));
			dados.setIuv(String.valueOf(rs.getFloat("iuv")));
			lista.add(dados);
		}
		rs.close();
		stmt.close();
		return lista;
	}
	
	public List<Cidade> selectCidade(String sql) throws SQLException{
		Connection conexao = null;
		conexao = DriverManager.getConnection("jdbc:sqlite:bdprevisao.db");
		Statement stmt = conexao.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<Cidade> lista = new ArrayList<>();
		Cidade cidade;
		while ( rs.next() ) {
			cidade = new Cidade();
			cidade.setId(rs.getInt("id"));
			cidade.setNome(rs.getString("nome"));
			cidade.setUf(rs.getString("uf"));
			//cidade.setAtualizacao(rs.getString("atualizacao"));
			lista.add(cidade);
		}
		rs.close();
		stmt.close();
		conexao.commit();
		return lista;
	}

}
