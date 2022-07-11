/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast;

import ast.btype.BType;
import lang.TreeNode;
import visitors.Visitor;


/**
 * Btype (LBrack RBrack)*
 */
public class Type extends TreeNode{

    private BType type;
    private int brack;

    public Type(BType type, int brack) {
        this.type = type;
        this.brack = brack;
    }

   public Type (BType type) {
        this.type = type;
        this.brack = 0;
    }

    public int getBrack() {
        return brack;
    }

    public BType getType() {
        return type;
    }

    public String toString() {
        String str = type.toString();
        for (int i = 0; i < brack; i++) {
            str += "[]";
        }
        return str;
    }

    public void accept(Visitor v){ v.visit(this);}
}
