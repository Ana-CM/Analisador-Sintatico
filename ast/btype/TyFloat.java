package ast.btype;

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
}
