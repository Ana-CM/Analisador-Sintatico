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

    public abstract void visit(Prog p); //r
    public abstract void visit(Definition e); 
    public abstract void visit(Decl e);
    public abstract void visit(Func f); //r
    public abstract void visit(Params e);   //v
    public abstract void visit(Type e); //v
    
    public abstract void visit(Plus e);  //v
    public abstract void visit(Minus e);  //v
    public abstract void visit(Times e); //v
    public abstract void visit(Div e); //v
    public abstract void visit(Mod e); //v
    public abstract void visit(Negative e); //v
    
    public abstract void visit(And e); //v
    public abstract void visit(Lt e); //v
    public abstract void visit(Eq e); //v
    public abstract void visit(Dif e); //v
    public abstract void visit(Not e); //v
    
    public abstract void visit(True e); //v
    public abstract void visit(False e); //v
    public abstract void visit(NumberInteger e); //v
    public abstract void visit(NumberDecimal e); //v
    public abstract void visit(LiteralCharacter e); //v
    public abstract void visit(LValue e); //v
    public abstract void visit(CallBrack e); 
    public abstract void visit(Null e); //v
    public abstract void visit(New e); 
    public abstract void visit(ParExp e); //v
    
    public abstract void visit(Attr e); //r
    public abstract void visit(If e); //v
    public abstract void visit(Iterate e); //v
    public abstract void visit(Print e); //v
    public abstract void visit(Read e); 
    public abstract void visit(CmdBrace e); //v
    public abstract void visit(CallAttr e); 
    public abstract void visit(Return e); //v
    
    public abstract void visit(TyInt t);  //v
    public abstract void visit(TyFloat t); //v
    public abstract void visit(TyChar t); //v
    public abstract void visit(TyBool t); //v
    public abstract void visit(UserType t); //v
}
