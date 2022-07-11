/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.cmd;

import visitors.Visitor;
import ast.expr.LValue;
/**
 * Read LValue Semi 
 */
public class Read  extends Cmd {
    
    private LValue lvalue;
    
    public Read(LValue lvalue) {
        this.lvalue = lvalue;
    }
    
    public LValue getRead() {
        return lvalue;
    }
    
    public String toString() {
        String s = "read " + lvalue.toString();
        s += ";";
        return s;
    }

    public void accept(Visitor v){ v.visit(this);}
}
