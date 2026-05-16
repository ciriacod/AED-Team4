package Importar.Exceptions;

public class ExceptionIsEmpty extends RuntimeException {
    public ExceptionIsEmpty(String msg){
        super(msg);
    }
    public ExceptionIsEmpty(){
        super();
    }
}