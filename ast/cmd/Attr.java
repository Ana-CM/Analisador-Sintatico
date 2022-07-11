/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.cmd;

import ast.expr.Expr;
import ast.expr.LValue;
import visitors.Visitor;


/**
 * LValue Eq Exp Semi 
 */
public class Attr extends Cmd {

    private LValue lvalue;
    private Expr exp;
    
    public Attr(LValue lvalue, Expr exp) {
        this.lvalue = lvalue;
        this.exp = exp;
    }
    
    public LValue getLvalue() {
        return lvalue;
    }
    
    public Expr getExp() {
        return exp;
    }
    
    public String toString() {
        String s = lvalue.toString() + "=" + exp.toString();
        s += ";";
        return s;
    }

    public void accept(Visitor v){ v.visit(this);}
}
