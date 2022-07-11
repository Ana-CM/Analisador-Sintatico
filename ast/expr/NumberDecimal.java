/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */


package ast.expr;
import visitors.Visitor;


public class NumberDecimal extends Expr {

    float decimal;

    public NumberDecimal(float Decimal) {
        this.decimal = Decimal;
    }

    public float getDecimal() {
        return decimal;
    }

    public String toString() {
        return decimal + "";
    }

    public void accept(Visitor v){ v.visit(this);}
}
