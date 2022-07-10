package ast.expr;

/**
 * Esta classe representa um valor Nulo.
 */
public class Null extends Expr {
    
    public Null() {
    }

    public Expr getValue() {
        return null;
    }
    
    //@Override
    public String toString() {
        return "null";
    }
}
