/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.btype;

import visitors.Visitor;


/**
 * TyFloat
 */
public class TyFloat extends BType {

    public TyFloat() {
    }

    public boolean match(BType t) {
        return t instanceof TyFloat;
    }

    public String toString() {
        return "Float";
    }
    public void accept(Visitor v){ v.visit(this);}
}
