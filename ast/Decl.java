/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast;

import lang.TreeNode;
import visitors.Visitor;


/**
 * Identifier ColonColon Type Semi
 */
public class Decl extends TreeNode {

    private String id;
    private Type type;

    public Decl(String id, Type type) {
        this.id = id;
        this.type = type;
    }

    public String toString() {
        String s = id;
        s += "::";
        s += this.type.toString();
        s += ";";

        return s;
    }

    public Type getType() {
        return type;
    }

    public String getId() {
        return this.id;
    }

    public void accept(Visitor v){ v.visit(this);}
}
