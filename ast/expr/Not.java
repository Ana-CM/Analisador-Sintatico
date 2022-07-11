/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.expr;
import visitors.Visitor;


/*
 * Esta classe representa uma expressão negação.
 */
public class Not extends Expr {
      
    Expr e; 

    public Not(Expr l){
        e = l;
    }

    public Expr getExpr(){ return e; }

    public String toString(){
        String s = e.toString();
        if(! (e instanceof NumberInteger || e instanceof NumberDecimal || e instanceof LValue || e instanceof True || e instanceof False)){
            s = "!" + s;
        }
        return s;
    }
    public void accept(Visitor v){ v.visit(this);}
}
