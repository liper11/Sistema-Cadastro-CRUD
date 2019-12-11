package conexao;

public class DbException extends RuntimeException {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/* o metodo abaixo cria uma excessao personalizada que herda as propriedades da Runtime Exception, recebe uma mensagem como para
	 * metro e a transmite para superClasse runtime Exception
	 */
		public DbException(String msg) {
			
			super (msg);
		}

}
