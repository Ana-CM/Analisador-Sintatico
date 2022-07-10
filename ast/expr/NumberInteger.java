package ast.expr;

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
}