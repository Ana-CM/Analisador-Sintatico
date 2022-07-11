/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.cmd;

import ast.expr.Expr;
import visitors.Visitor;


/**
 * Print Exp Semi 
 */
public class Print extends Cmd {
    
    private Expr e;

    public Print(Expr e) {
        this.e = e;
    }

    public Expr getPrint() {
        return e;
    }

    public String toString() {
        String s = "print ";
        s += e.toString();
        s += ";";
        return s;
    }

    public void accept(Visitor v){ v.visit(this);}
}
