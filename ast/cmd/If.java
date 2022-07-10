package ast.cmd;

import ast.expr.Expr;

/**
 * If LPar Exp RPar Cmd (Else Cmd)? 
 */
public class If extends Cmd {
    
    private Expr exp;
    private Cmd cmd;
    private Cmd elseCmd;

    public If(Expr exp, Cmd cmd, Cmd elseCmd) {
        this.exp = exp;
        this.cmd = cmd;
        this.elseCmd = elseCmd;
    }

    public If(Expr exp, Cmd cmd) {
        this.exp = exp;
        this.cmd = cmd;
        this.elseCmd = null;
    }

    public Expr getExp() {
        return exp;
    }

    public Cmd getCmd() {
        return cmd;
    }

    public Cmd getElseCmd() {
        return elseCmd;
    }

    public String toString() {
        String s = "if(" + exp.toString() + ")" + cmd.toString();
        if(elseCmd != null){
            s += "else" + elseCmd.toString();
        }
        return s;
    }
}