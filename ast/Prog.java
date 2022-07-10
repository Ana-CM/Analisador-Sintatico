package ast;

import lang.TreeNode;

/**
 * Spacing Definition* Func+ EndOfFile
 */
public class Prog extends TreeNode{

    private Definition[] defs;
    private Func[] funcs;

    public Prog(Definition[] defs, Func[] funcs) {
        this.defs = defs;
        this.funcs = funcs;
    }

    public Prog(Func[] funcs) {
        this.defs = null;
        this.funcs = funcs;
    }

    public String toString() {
        String s = "";
        for (Definition def : defs) {
            s += def.toString();
        }

        for (Func f : funcs) {
            s += f.toString();
        }
        return s;
    }
}
