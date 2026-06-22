package Semana_11.hash;

public class Register<E> implements Comparable<Register<E>> {
    private int key;
    private E value;
    
    public Register (int key, E value) {
        this.key = key;
        this.value = value;
    }
    
    public int getKey() {
        return key;
    }
    
    public E getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }

    @Override
    public int compareTo(Register<E> r) {
        return this.key - r.key;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Register) {
            Register<E> r = (Register<E>)o;
            return r.key == this.key;
        }
        return false;
    }
}
