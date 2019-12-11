package conexao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;



public class Conexao {

   private static Connection conn = null;
   
   
   /*este metodo abaixo é o metodo que conecta a aplicação ao banco de dados, este metodo utiliza o obj retornado pelo
    * metodo propriedades(), carregando as propriedades em uma variavel do tipo Connection caso a variavel Connection conn esteja
    * com o valor NULL . 
    */
   public static Connection conectar() {
	   
	   if (conn == null) {
		   
		   try {
			   
			   Properties props = propriedades();
			   String url = props.getProperty("dburl");
			   conn = DriverManager.getConnection(url,props);
			   
			  // JOptionPane.showMessageDialog(null, "CONECTADO !!!");
			 
		   } catch (SQLException e) {
			   
			   throw new DbException(e.getMessage());
		   }
	   }
	   
	   return conn;
	   
   }
   
   
   
   
   /* aqui abaixo esta o metodo responsavel por carregar as propriedades de conexao com o banco de dados , essas propriedades estão
    * no arquivo db.properties que é carregado atraves de um obj FileInputStream, esse metodo retorna um obj do Tipo Properties para 
    * o metodo conectar , que tenta obter a conexão obtendo as propriedades retornadas por este método
    */
   public static  Properties propriedades() {
	   
	   try (FileInputStream fs = new FileInputStream("db.properties")){
		   
		    Properties props = new Properties();
		    props.load(fs);
		    return props;
	   }catch (IOException e) {
		   
		   throw new DbException(e.getMessage());
	   }
	   
   }
   
   /* este metodo abaixo fecha a conexao, caso necessario*/
   
   public static void fecharConexao(Connection conn) {
	   
	   if (conn !=null) {
		   
		   try {
			   
			   conn.close();
		   } catch (SQLException e) {
			   
			   throw new DbException(e.getMessage());
		   }
	   }
   }
   
   /* esse metodo 'fecha ' o ResultSet , caso necessario*/
   
   public static ResultSet fecharResultSet(ResultSet rs) {
	   
	   if (rs!=null) {
		   
		   try {
			   rs.close();
			   
		   } catch(SQLException e) {
			   
			   throw new DbException(e.getMessage());
		   }
	   }
	   
	   return rs;
   }
   
   /* esse metodo fecha o Prepared Statement, caso necessario */
   public static PreparedStatement fecharPreparedStatement(PreparedStatement stmt) {
	   
	   if (stmt !=null) {
		   
		   try {
			   
			   stmt.close();
		   }catch (SQLException e) {
			   
			   throw new DbException(e.getMessage());
		   }
	   }
	   
	   return stmt;
   }
}
