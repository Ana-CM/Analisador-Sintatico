/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.expr;
import visitors.Visitor;


public class NumberInteger extends Expr {

    int number_integer;

    public NumberInteger(int number_integer) {
        this.number_integer = number_integer;
    }

    public int getNumber_integer() {
        return number_integer;
    }

    public String toString() {
        return number_integer + "";
    }

    public void accept(Visitor v){ v.visit(this);}
}