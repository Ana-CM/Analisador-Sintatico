/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.expr.binop;

import ast.expr.Expr;

/*
 * Esta classe representa uma Operação binária.
 */
public abstract class BinOP extends Expr {
      
    private Expr l;
    private Expr r;
      
    public BinOP(Expr l, Expr r){
        this.l = l;
        this.r = r;
    }
      
    public void setLeft(Expr n){  l = n; }
    public void setRight(Expr n){ r = n; }
      
    public Expr getLeft(){ return l;}
    public Expr getRight(){ return r;}
}