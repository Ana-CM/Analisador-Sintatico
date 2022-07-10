package ast;

import lang.TreeNode;

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
}
