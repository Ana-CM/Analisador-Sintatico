/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.btype;

import lang.TreeNode;
import visitors.Visitor;

public  abstract class BType extends TreeNode {
    
    public BType(){
    }

    public abstract boolean match(BType t);

    public abstract void accept(Visitor v);
}