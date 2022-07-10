package ast.cmd;

import ast.expr.Expr;

/**
 * Print Exp Semi 
 */
public class Print extends Cmd {
    
    private Expr e;

    public Print(Expr e) {
        this.e = e;
    }

    public String toString() {
        String s = "print ";
        s += e.toString();
        s += ";";
        return s;
    }
}
