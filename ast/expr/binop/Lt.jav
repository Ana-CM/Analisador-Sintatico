package ast.binop;

import ast.Expr;

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
        if(getRight() instanceof Add){
            ss = "(" + ss + ")";
        }
        return   s + " + " + ss;
    } 
}