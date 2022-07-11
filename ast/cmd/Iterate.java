/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.cmd;

import ast.expr.Expr;
import visitors.Visitor;


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

    public void accept(Visitor v){ v.visit(this);}
}
