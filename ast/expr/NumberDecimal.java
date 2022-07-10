package ast.expr;

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
}
