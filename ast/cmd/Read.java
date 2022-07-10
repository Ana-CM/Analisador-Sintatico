package ast.cmd;

import ast.expr.LValue;
/**
 * Read LValue Semi 
 */
public class Read  extends Cmd {
    
    private LValue lvalue;
    
    public Read(LValue lvalue) {
        this.lvalue = lvalue;
    }
    
    public LValue getLvalue() {
        return lvalue;
    }
    
    public String toString() {
        String s = "read " + lvalue.toString();
        s += ";";
        return s;
    }
}
