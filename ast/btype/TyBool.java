package ast.btype;

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
}