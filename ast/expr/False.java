/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.expr;
import visitors.Visitor;


/*
 * Esta classe representa um valor booleano False.
 */
public class False extends Expr {
      
    public False(){
    }
    
    public boolean getValue(){ return false;}
    
    //@Override
    public String toString(){
        return "false"; 
    }

    public void accept(Visitor v){ v.visit(this);}
}