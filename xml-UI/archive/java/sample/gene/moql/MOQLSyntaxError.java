package gene.moql;

public class MOQLSyntaxError extends RuntimeException{
    public MOQLSyntaxError(String message) {
        super(message);
    }
}
