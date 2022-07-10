package ast.cmd;

import ast.expr.Expr;
import ast.expr.LValue;

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
}
