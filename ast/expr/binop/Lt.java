package ast.expr.binop;

import ast.expr.Expr;

/*
* Esta classe representa uma operação de comparação.
*/
public class Lt extends BinOP {

    public Lt(Expr l, Expr r){
        super(l,r);
    }
    
    public String toString(){
        String s = getLeft().toString();
        String ss = getRight().toString();
        if(getRight() instanceof Plus){
            ss = "(" + ss + ")";
        }
        return   s + " + " + ss;
    } 
}