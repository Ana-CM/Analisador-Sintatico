/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.cmd;

import ast.expr.Expr;
import ast.expr.LValue;
import visitors.Visitor;


/**
 * Esta classe representa uma chamada de função.
 * Identifier LPar Exps? RPar ( Lt LValue ( Comma LValue )* Gt )? Semi 
 */
public class CallAttr extends Cmd {
    
    private String id;
    private Expr[] params;
    private LValue[] lvalue;
    
    public CallAttr(String id, Expr[] params, LValue[] lvalue){
        this.id = id;
        this.params = params;
        this.lvalue = lvalue;
    }

    public CallAttr(String id, LValue[] lvalue){
        this.id = id;
        this.lvalue = lvalue;
        this.params = null;
    }

    public String getId() {
        return id;
    }

    public Expr[] getParams() {
        return params;
    }

    public LValue[] getLvalue() {
        return lvalue;
    }

    public String toString() {
        String s = id + "(";

        if(params != null){
            for(Expr e : params ){
                s += e.toString();
                s += ",";
            }
            s = s.substring(0, s.length() - 1);
        }

        s += ")";
        s += "<";
        
        for(LValue l : lvalue){
            s += l.toString();
            s += ",";
        }
        s = s.substring(0, s.length() - 1);
        s += ">";
        s += ";";

        return s;
    }

    public void accept(Visitor v){ v.visit(this);}
}
