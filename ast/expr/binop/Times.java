/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.expr.binop;

import ast.expr.Expr;
import visitors.Visitor;


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

    public void accept(Visitor v){ v.visit(this);}
}
