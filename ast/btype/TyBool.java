/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.btype;

import visitors.Visitor;


/**
 * TyBool
 */
public class TyBool extends BType {

    public TyBool() {
    }

    public boolean match(BType t) {
        return t instanceof TyBool;
    }

    public String toString() {
        return "Bool";
    }

    public void accept(Visitor v){ v.visit(this);}
}