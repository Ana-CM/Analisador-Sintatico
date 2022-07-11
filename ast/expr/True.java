/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.expr;
import visitors.Visitor;

/*
 * Esta classe representa uma valor booleano True.
 */
public class True extends Expr {
      
    public True(){
    }

    public boolean getValue(){ return true;}

    //@Override
    public String toString(){
        return   "true"; 
    }

    public void accept(Visitor v){ v.visit(this);}
}