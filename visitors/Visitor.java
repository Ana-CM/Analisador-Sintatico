/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package visitors;

import ast.*;
import ast.btype.*;
import ast.cmd.*;
import ast.expr.*;
import ast.expr.binop.*;

public abstract class Visitor {

    public abstract void visit(Prog p); //V
    public abstract void visit(Definition e); //V
    public abstract void visit(Decl e); //V
    public abstract void visit(Params e); //V
    public abstract void visit(Type e); //V
    
    public abstract void visit(Plus e); //V
    public abstract void visit(Minus e); //V
    public abstract void visit(Times e); //V
    public abstract void visit(Div e); //V
    public abstract void visit(Mod e); //V
    public abstract void visit(Negative e); //V
    
    public abstract void visit(And e); //V
    public abstract void visit(Lt e); //V
    public abstract void visit(Eq e); //V
    public abstract void visit(Dif e); //V
    public abstract void visit(Not e); //v
    
    public abstract void visit(True e); //V
    public abstract void visit(False e); //V
    public abstract void visit(NumberInteger e); //V
    public abstract void visit(NumberDecimal e); //V
    public abstract void visit(LiteralCharacter e); //V
    public abstract void visit(LValue e); //V
    public abstract void visit(CallBrack e);
    public abstract void visit(Null e); //V
    public abstract void visit(New e); //V
    public abstract void visit(ParExp e); //CORRIGIR
    
    public abstract void visit(Attr e); //CORRIGIR
    public abstract void visit(If e);
    public abstract void visit(Iterate e);
    public abstract void visit(Print e); //V
    public abstract void visit(Read e); //V
    public abstract void visit(CmdBrace e);
    public abstract void visit(Func f);
    public abstract void visit(CallAttr e);
    public abstract void visit(Return e);
    
    public abstract void visit(TyInt t); //V
    public abstract void visit(TyFloat t); //V
    public abstract void visit(TyChar t); //V
    public abstract void visit(TyBool t); //V
    public abstract void visit(UserType t);
}
