package ast.expr.binop;

import ast.expr.Expr;

/*
 * Esta classe representa uma expressão de multiplicação.
 */
public class Times extends BinOP {
    public Times(Expr l, Expr r){
        super(l,r);
    }

    //@Override
    public String toString(){
        String s = getLeft().toString();
        if(getLeft() instanceof Times || getLeft() instanceof Plus){
            s = "(" + s + ")";
        }
        String ss = getRight().toString();
        if( getRight() instanceof Plus){
            ss = "(" + ss + ")";
        }
        return   s + " * " + ss;
    }
}
