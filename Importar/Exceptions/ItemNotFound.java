package Importar.Exceptions;

public class ItemNotFound extends RuntimeException {
    public ItemNotFound(String msg){
        super(msg);
    }
    public ItemNotFound(){
        super();
    }
}