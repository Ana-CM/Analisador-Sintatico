package ast.expr;

/**
 * Esta classe representa uma chamada de função.
 * Identifier LPar Exps? RPar LBrack Exp RBrack 
 */
public class CallBrack {
        
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
}
