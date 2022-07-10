package ast.cmd;

import ast.expr.Expr;

/**
 * Return Exp ( Comma Exp )* Semi 
 */
public class Return extends Cmd {
    
    private Expr[] e;

    public Return(Expr[] e) {
        this.e = e;
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
}
