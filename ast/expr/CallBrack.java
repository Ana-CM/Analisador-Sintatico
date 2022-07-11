/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.expr;
import visitors.Visitor;


/**
 * Esta classe representa uma chamada de função.
 * Identifier LPar Exps? RPar LBrack Exp RBrack 
 */
public class CallBrack extends Expr{
        
    private String id;
    private Expr[] params;
    private Expr exp;
    
    public CallBrack(String id, Expr[] params, Expr exp){
        this.id = id;
        this.params = params;
        this.exp = exp;
    }

    public CallBrack(String id, Expr exp){
        this.id = id;
        this.exp = exp;
        this.params = null;
    }

    public String getId() {
        return id;
    }

    public Expr[] getParams() {
        return params;
    }

    public Expr getExp() {
        return exp;
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

        s += ")" + "[" + exp.toString() + "]";
        return s;
    }

    public void accept(Visitor v){ v.visit(this);}
}
