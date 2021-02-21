package trabalhos.trab1.models;

public class InvalidCandidateSolutionException extends Exception{
    public static final String DEFAULT_MESSAGE = "Solução candidata inválida!";
    public InvalidCandidateSolutionException(String msg){
        super(msg);
    }
    public InvalidCandidateSolutionException(){
        super(DEFAULT_MESSAGE);
    }
}
