/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.btype;

import visitors.Visitor;


/**
 * TyInt
 */
public class TyInt extends BType {

    public TyInt() {
    }

    public boolean match(BType t){
        return t instanceof TyInt; 
    }
     
    public String toString(){ return "Int"; }

    public void accept(Visitor v){ v.visit(this);}
}
