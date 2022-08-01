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

    public abstract void visit(Prog p); //
    public abstract void visit(Definition e); //
    public abstract void visit(Decl e); //
    public abstract void visit(Func f); //
    public abstract void visit(Params e); //
    public abstract void visit(Type e); //
    
    public abstract void visit(Plus e);  //
    public abstract void visit(Minus e); //
    public abstract void visit(Times e); //
    public abstract void visit(Div e); //
    public abstract void visit(Mod e); //
    public abstract void visit(Negative e); //
    
    public abstract void visit(And e); //
    public abstract void visit(Lt e); //
    public abstract void visit(Eq e); //
    public abstract void visit(Dif e); //
    public abstract void visit(Not e); //
    
    public abstract void visit(True e); //
    public abstract void visit(False e); //
    public abstract void visit(NumberInteger e); //
    public abstract void visit(NumberDecimal e); //
    public abstract void visit(LiteralCharacter e); //
    public abstract void visit(LValue e); // 
    public abstract void visit(CallBrack e); 
    public abstract void visit(Null e); //
    public abstract void visit(New e); //
    public abstract void visit(ParExp e); //
    
    public abstract void visit(Attr e); //
    public abstract void visit(If e); //
    public abstract void visit(Iterate e); //
    public abstract void visit(Print e); //
    public abstract void visit(Read e); //
    public abstract void visit(CmdBrace e); //
    public abstract void visit(CallAttr e); 
    public abstract void visit(Return e); // +/-
    
    public abstract void visit(TyInt t); //
    public abstract void visit(TyFloat t); //
    public abstract void visit(TyChar t); //
    public abstract void visit(TyBool t); //
    public abstract void visit(UserType t); //
}
