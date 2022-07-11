/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.expr;
import visitors.Visitor;

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

    public void accept(Visitor v){ v.visit(this);}
}
