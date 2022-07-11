/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.expr.binop;

import ast.expr.Expr;
import visitors.Visitor;

 
/*
 * Esta classe representa uma operação lógica de AND.
 */
public class And extends BinOP {

    public And(Expr l, Expr r){
        super(l,r);
    }

    public String toString(){
        String s = getLeft().toString();
        String ss = getRight().toString();
        if(getRight() instanceof Plus){
            ss = "(" + ss + ")";
        }
        return   s + " & " + ss;
    }

    public void accept(Visitor v){ v.visit(this);}
}