package br.com.servicotecnico2.conexao;

public class ConexaoException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    //Metodo para ajudar na tratamentos de exceções.
    public ConexaoException(String msg) {
        super(msg);
    }
}
