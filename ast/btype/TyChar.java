/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.btype;

import visitors.Visitor;

/**
 * TyChar
 */
public class TyChar extends BType {

    public TyChar() {
    }

    public boolean match(BType t) {
        return t instanceof TyChar;
    }

    public String toString() {
        return "Char";
    }

    public void accept(Visitor v){ v.visit(this);}
}
