/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */


package ast.expr;

import visitors.Visitor;

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

    public void accept(Visitor v){ v.visit(this);}
}
