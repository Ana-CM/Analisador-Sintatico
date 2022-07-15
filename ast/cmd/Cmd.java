/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.cmd;

import lang.TreeNode;
import visitors.Visitor;

public abstract class Cmd extends TreeNode {
      
    public Cmd(){
    }

    public abstract void accept(Visitor v);
}
