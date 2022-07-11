/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.btype;

import visitors.Visitor;


/**
 * UserType
 */
public class UserType extends BType {

    private String name;

    public UserType(String name) {
        this.name = name;
    }

    public boolean match(BType t) {
        return t instanceof UserType && ((UserType) t).name.equals(name);
    }

    public String toString() {
        return this.name;
    }

    public void accept(Visitor v){ v.visit(this);}
}
