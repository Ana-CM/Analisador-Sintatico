/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast;

import lang.TreeNode;
import visitors.Visitor;


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

    public Definition[] getDefs() {
        return this.defs;
    }
    
    public Func[] getFuncs() {
        return this.funcs;
    }

    public void accept(Visitor v){ v.visit(this);}
}
