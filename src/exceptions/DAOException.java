package exceptions;

public class DAOException extends Exception{
	  private String error;
	    public DAOException(String error) {
	        this.error = error;
	    }
	    public String getError() {
	        return error;
	    }
	
	
}
