package model;

public class NoElementException extends Exception {
    public NoElementException(String notFound) {
        super(notFound);
    }
}
