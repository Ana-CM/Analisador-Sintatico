package ast.expr;

/*
 * Esta classe representa uma expressão entre parênteses.
 * LPar Exp RPar  
 */
public class ParExp {

    private Expr expr;

    public ParExp(Expr expr) {
        this.expr = expr;
    }

    public Expr getExpr() {
        return expr;
    }

    public String toString() {
        return "(" + expr.toString() + ")";
    }
    
}
