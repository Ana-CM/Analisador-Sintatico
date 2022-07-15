/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.expr;


import lang.TreeNode;
import visitors.Visitor;

public abstract class Expr extends TreeNode {
      
    public Expr(){
    }
    
    public abstract void accept(Visitor v);
}
