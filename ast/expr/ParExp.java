/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */


package ast.expr;

import visitors.Visitor;


/*
 * Esta classe representa uma expressão entre parênteses.
 * LPar Exp RPar  
 */
public class ParExp extends Expr{

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

    public void accept(Visitor v){ v.visit(this);}
}
