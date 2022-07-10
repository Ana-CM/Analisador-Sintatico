package ast.btype;

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
}
