/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.cmd;

import ast.expr.Expr;
import visitors.Visitor;

/**
 * Return Exp ( Comma Exp )* Semi 
 */
public class Return extends Cmd {
    
    private Expr[] e;

    public Return(Expr[] e) {
        this.e = e;
    }

    public Expr[] getReturn() {
        return e;
    }

    public String toString() {
        String s = "return ";
        for (Expr expr : e) {
            s += expr.toString();
            s += ",";
        }
        s = s.substring(0, s.length() - 1);
        s += ";";
        return s;
    }

    public void accept(Visitor v){ v.visit(this);}
}
