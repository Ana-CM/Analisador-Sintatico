package ast.cmd;

import ast.expr.Expr;

/**
 * Iterate LPar Exp RPar Cmd 
 */
public class Iterate extends Cmd {
    
    private Expr e;
    private Cmd cmd;

    public Iterate(Expr e, Cmd cmd) {
        this.e = e;
        this.cmd = cmd;
    }

    public String toString() {
        String s = "iterate (";
        s += e.toString();
        s += ")";
        s += cmd.toString();
        return s;
    }
}
