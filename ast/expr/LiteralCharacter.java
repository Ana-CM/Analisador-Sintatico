package ast.expr;

public class LiteralCharacter extends Expr  {
    char literalCharacter;

    public LiteralCharacter(char literalCharacter) {
        this.literalCharacter = literalCharacter;
    }

    public char getLiteralCharacter() {
        return literalCharacter;
    }

    public String toString() {
        return "'" + literalCharacter + "'";
    }
}
