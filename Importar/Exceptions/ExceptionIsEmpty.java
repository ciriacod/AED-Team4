package Importar.Exceptions;

public class ExceptionIsEmpty extends Exception {
    ExceptionIsEmpty(String msg){
        super(msg);
    }
    ExceptionIsEmpty(){
        super();
    }
}