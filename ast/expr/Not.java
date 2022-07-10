package ast.expr;

/*
 * Esta classe representa uma expressão negação.
 */
public class Not extends Expr {
      
    Expr e; 

    public Not(Expr l){
        e = l;
    }

    public Expr getExpr(){ return e; }

    public String toString(){
        String s = e.toString();
        if(! (e instanceof NumberInteger || e instanceof NumberDecimal || e instanceof LValue || e instanceof True || e instanceof False)){
            s = "!" + s;
        }
        return s;
    }
}
