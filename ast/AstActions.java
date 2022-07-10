package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lang.Actions;
import lang.Label;
import lang.LANG;
import lang.ParseError;
import lang.TreeNode;

import ast.*;
import ast.btype.*;
import ast.expr.*;
import ast.expr.binop.*;
import ast.cmd.*;

public class AstActions implements Actions {
    
    /**
     * Identifier (LBrack Exp RBrack / Dot Identifier)*
     */
    public LValue MakeLValue(String input, int start, int end, List<TreeNode> elements) {
        String name = elements.get(0).elements.get(0).text;
        
        List<Object> idx = null;
        
        if (elements.size() > 1) {
            idx = new ArrayList <Object>();

            for (int i = 1; i < elements.size(); i++) {
                for (TreeNode e : elements.get(i)) {
                    if (e.get(Label.Exp) != null) {
                        Expr exp = (Expr) e.get(Label.Exp);
                        idx.add(exp);
                    }
                    
                    else if (e.get(Label.Identifier) != null) {
                        String id = (String) e.get(Label.Identifier).elements.get(0).text;
                        idx.add(id);
                    }
                }
            }
        }
        
        return new LValue(name, idx);
    }

    /**
     * LPar Exp RPar
     */
    public ParExp MakeParExp(String input, int start, int end, List<TreeNode> elements) {
        Expr expr = (Expr)elements.get(1);
        return new ParExp(expr);

    } 

    /**
     * Identifier LPar Exps? RPar LBrack Exp RBrack 
     */
    public CallBrack MakeCallBrack(String input, int start, int end, List<TreeNode> elements) {
        String id = elements.get(0).text;
        Expr[] params = null;
        Expr exp = null;

        if(elements.size() == 6){
            exp = (Expr)elements.get(4);
        } else if(elements.size() == 7){
            params = new Expr[elements.get(2).elements.size()];
            for(int i = 0; i < params.length; i++){
                if ( i == 0 ) {
                    params[i] = (Expr)elements.get(2).elements.get(i);
                } else {
                    params[i] = (Expr)elements.get(2).elements.get(i).get(Label.Exp);
                }
            }
            exp = (Expr)elements.get(elements.size() - 1);
        } 
        return new CallBrack(id, params, exp);
    }

    /**
     * New Type ( LBrack Exp RBrack )?
     */
    public New MakeNew(String input, int start, int end, List<TreeNode> elements) {
        Type type = (Type) elements.get(1);
        Expr exp = null;
        if(elements.size() > 2){
            exp = (Expr) elements.get(3);
        }
        return new New(type, exp);
    }

    /**
     * LiteralCharacter
     */
    public LiteralCharacter MakeLiteralCharacter(String input, int start, int end, List<TreeNode> elements) {
        char literalCharacter = (char)elements.get(0).elements.get(0).text.charAt(1);
        return new LiteralCharacter(literalCharacter);
    }
    
    /**
     * Integer
     */
    public NumberInteger MakeInteger (String input, int start, int end, List<TreeNode> elements) {
        int integer = Integer.parseInt(elements.get(0).elements.get(0).text);
        return new NumberInteger(integer);
    }

    /**
     * Decimal
     */
    public NumberDecimal MakeDecimal (String input, int start, int end, List<TreeNode> elements) {
        float decimal = Float.parseFloat(elements.get(0).elements.get(0).text);
        return new NumberDecimal(decimal);
    }

    /**
     * Null
     */
    public Null MakeNull (String input, int start, int end, List<TreeNode> elements) {
        return new Null();
    }

    /**
     * False
     */
    public False MakeFalse (String input, int start, int end, List<TreeNode> elements) {
        return new False();
    }

    /**
     * True
     */
    public True MakeTrue (String input, int start, int end, List<TreeNode> elements) {
        return new True();
    }

    /**
     * Negative
     */
    public Negative MakeNegative (String input, int start, int end, List<TreeNode> elements) {
        Expr exp = (Expr)elements.get(1);
        return new Negative(exp);
    }

    /**
     * Not
     */
    public Not MakeNot (String input, int start, int end, List<TreeNode> elements) {
        Expr exp = (Expr)elements.get(1);
        return new Not(exp);
    }
    
    /**
     * Identifier LPar Exps? RPar ( Lt LValue ( Comma LValue )* Gt )? Semi 
     * Exp ( Comma Exp )*
     */
    public CallAttr MakeCallAttr(String input, int start, int end, List<TreeNode> elements) {
        String id = elements.get(0).text;
        Expr[] params = null;
        LValue[] lvalues = null;

        if ( null != elements.get(2).get(Label.Exp) ) {
            params = new Expr[elements.get(2).elements.size()];
            for(int i = 0; i < params.length; i++){
                if ( i == 0 ) {
                    params[i] = (Expr)elements.get(2).elements.get(i);
                } else {
                    params[i] = (Expr)elements.get(2).elements.get(i).get(Label.Exp);
                }
            }

            if ( null !=  elements.get(4).get(Label.LValue) ) {
                lvalues = new LValue[elements.get(4).elements.size()];
                for(int i = 1; i < lvalues.length - 1; i++){
                    if ( i == 1 ) {
                        lvalues[i] = (LValue)elements.get(4).elements.get(i);
                    } else {
                        lvalues[i] = (LValue)elements.get(4).elements.get(i).get(Label.LValue);
                    }
                }
            }
        } else if ( null !=  elements.get(3).get(Label.LValue) ) {
            lvalues = new LValue[elements.get(3).elements.size()];
            for(int i = 1; i < lvalues.length - 1; i++){
                if ( i == 1 ) {
                    lvalues[i] = (LValue)elements.get(3).elements.get(i);
                } else {
                    lvalues[i] = (LValue)elements.get(3).elements.get(i).get(Label.LValue);
                }
            }
        }
    
        return new CallAttr(id, params, lvalues);
    }

    /**
     * LValue Eq Exp Semi
     */
    public Attr MakeAttr (String input, int start, int end, List<TreeNode> elements) {
        LValue lvalue = (LValue)elements.get(0);
        Expr exp = (Expr)elements.get(2);
        return new Attr(lvalue, exp);
    }

    /**
     * Return Exp ( Comma Exp )* Semi
     */
    public Return MakeReturn (String input, int start, int end, List<TreeNode> elements) {
        Expr[] exp = null;
        
        exp = new Expr[elements.size() - 2];
        for(int i = 1; i < exp.length - 1; i++){
            if ( i == 1 ) {
                exp[i] = (Expr)elements.get(i);
            } else {
                exp[i] = (Expr)elements.get(i).get(Label.Exp);
            }
        }

        return new Return(exp);
    }

    /**
     * Print Exp Semi
     */
    public Print MakePrint (String input, int start, int end, List<TreeNode> elements) {
        Expr exp = (Expr) elements.get(1);
        return new Print(exp);
    }
    
    /**
     * Read LValue Semi 
     */
    public Read MakeRead (String input, int start, int end, List<TreeNode> elements) {
        LValue lvalue = (LValue) elements.get(1);
        return new Read(lvalue);
    }

    /**
     * Iterate LPar Exp RPar Cmd
     */
    public Iterate MakeIterate (String input, int start, int end, List<TreeNode> elements) {
        Expr exp = (Expr) elements.get(2);
        Cmd cmd = (Cmd) elements.get(4);
        return new Iterate(exp, cmd);
    }

    /**
     * If LPar Exp RPar Cmd (Else Cmd)?
     */
    public If MakeIf (String input, int start, int end, List<TreeNode> elements) {
        Expr exp = (Expr) elements.get(2);
        Cmd cmd = (Cmd) elements.get(4);
        Cmd elseCmd = null;
        if ( null != elements.get(5).get(Label.Cmd) ) {
            elseCmd = (Cmd) elements.get(5).get(Label.Cmd);
        }
        return new If(exp, cmd, elseCmd);
    }
    
    /**
     * UserType
     */
    public UserType MakeUserType (String input, int start, int end, List<TreeNode> elements) {
        return new UserType(elements.get(0).text);
    }

    /**
     * Float
     */
    public TyFloat MakeTyFloat (String input, int start, int end, List<TreeNode> elements) {
        return new TyFloat();
    }

    /**
     * Bool
     */
    public TyBool MakeTyBool (String input, int start, int end, List<TreeNode> elements) {
        return new TyBool();
    }

    /**
     * Char
     */
    public TyChar MakeTyChar (String input, int start, int end, List<TreeNode> elements) {
        return new TyChar();
    }

    /**
     * Int
     */
    public TyInt MakeTyInt (String input, int start, int end, List<TreeNode> elements) {
        return new TyInt();
    }

    /**
     * Btype (LBrack RBrack)*
     */
    public Type MakeType (String input, int start, int end, List<TreeNode> elements) {
        BType btype = (BType) elements.get(0);
        int dim = elements.size() - 1;
        
        return new Type(btype, dim);
    }

    /**
     * Identifier ColonColon Type (Comma Identifier ColonColon Type)*
     */
    public Params MakeParams (String input, int start, int end, List<TreeNode> elements) {
        List<Entry<String, Type>> p = new ArrayList <Entry<String, Type>>();
        
        String id = elements.get(0).elements.get(0).text;
        Type type = (Type) elements.get(2);
        
        Entry<String, Type> pair = Map.entry(id, type);
        p.add(pair);

        if (elements.size() > 3) {
            for (int i = 3; i < elements.size(); i++) {
                id = elements.get(i).get(Label.Identifier).elements.get(0).text;
                type = (Type) elements.get(i).get(Label.Type);
                pair = Map.entry(id, type);
                p.add(pair);
            }
        }
    
        return new Params(p);
    }

    /**
     * Identifier LPar Params? RPar  ( Colon Type ( Comma Type)* )? LBrace (Cmd*) RBrace
     *  Params      <- Identifier ColonColon Type (Comma Identifier ColonColon Type)*
     */
    public Func MakeFunc (String input, int start, int end, List<TreeNode> elements) {
        String id = elements.get(0).elements.get(0).text;
        Params params = null;
        Type[] type = null;
        Cmd[] cmd = null;

        if ( null != elements.get(2).get(Label.ColonColon) ) {
            params = (Params) elements.get(2);
        }

        if ( null != elements.get(4).get(Label.Type) ) {
            type = new Type[elements.get(4).elements.size() - 1];
            for(int i = 1; i < type.length; i++){
                if ( i == 1 ) {
                    type[i] = (Type)elements.get(4).elements.get(i);
                } else {
                    type[i] = (Type)elements.get(4).elements.get(i).get(Label.Type);
                }
            }
        } else if ( null != elements.get(3).get(Label.Type) ) {
            type = new Type[elements.get(3).elements.size() - 1];
            for(int i = 1; i < type.length; i++){
                if ( i == 1 ) {
                    type[i] = (Type)elements.get(4).elements.get(i);
                } else {
                    type[i] = (Type)elements.get(4).elements.get(i).get(Label.Type);
                }
            }
        }

        int cmdId = 0;
        if ( null != elements.get(6).get(Label.Cmd) ) {
            cmdId = 6;
        } else if ( null != elements.get(5).get(Label.Cmd) ) {
            cmdId = 5;  
        } else if ( null != elements.get(4).get(Label.Cmd) ) {
            cmdId = 4;
        }

        if (cmdId != 0) {
            cmd = new Cmd[elements.get(cmdId).elements.size()];
            for (int i = 0; i < cmd.length; i++ ) {
                cmd[i] = (Cmd) elements.get(cmdId).elements.get(i);
            }
        }

        return new Func(id, params, type, cmd);
    }

    /*
     * Identifier ColonColon Type Semi
     */
    public Decl MakeDecl (String input, int start, int end, List<TreeNode> elements) {
        String id = elements.get(0).elements.get(0).text;
        Type type = (Type) elements.get(2);
        return new Decl(id, type);
    }

    /**
     * Data Type LBrace (Declaration+) RBrace
     */
    public Definition MakeDefinition (String input, int start, int end, List<TreeNode> elements) {
        Type type = (Type) elements.get(1);
        Decl[] declarations = new Decl[elements.get(3).elements.size()];

        for (int i = 0; i < declarations.length; i++) {
            declarations[i] = (Decl) elements.get(3).elements.get(i);
        }

        return new Definition(type, declarations);
    } 

    /**
     * Spacing (Definition*) (Func+) EndOfFile
     */
    public Prog MakeProg (String input, int start, int end, List<TreeNode> elements) {
        Definition[] defs = null;
        Func[] funcs = null;

        if ( null != elements.get(1).get(Label.Definition) ) {
            defs = new Definition[elements.get(1).elements.size()];
            for (int i = 0; i < defs.length; i++) {
                defs[i] = (Definition) elements.get(1).elements.get(i);
            }

            if ( null != elements.get(2).get(Label.Func) ) {
                funcs = new Func[elements.get(2).elements.size()];
                for (int i = 0; i < funcs.length; i++) {
                    funcs[i] = (Func) elements.get(2).elements.get(i);
                }
            }
        } else if ( null != elements.get(1).get(Label.Func) ) {
            funcs = new Func[elements.get(1).elements.size()];
            for (int i = 0; i < funcs.length; i++) {
                funcs[i] = (Func) elements.get(1).elements.get(i);
            }
        }

        return new Prog(defs, funcs);
    }

    /**
     * LBrace (Cmd+) RBrace 
     */
    public CmdBrace MakeCmdBrace (String input, int start, int end, List<TreeNode> elements) {
        Cmd[] cmds = new Cmd[elements.get(1).elements.size()];
        for (int i = 0; i < cmds.length; i++) {
            cmds[i] = (Cmd) elements.get(1).elements.get(i);
        }
        return new CmdBrace(cmds);
    }

    /**
     * Minus
     */
    public Minus MakeMinus (String input, int start, int end, List<TreeNode> elements) {
        Expr left = (Expr)elements.get(0);
        Expr right = (Expr)elements.get(1);
        return new Minus(left, right);
    }

    /**
     * Mexp ((Plus / Minus) Mexp)*
     */
    public BinOP MakeMexp (String input, int start, int end, List<TreeNode> elements) {
        Expr exp = (Expr)elements.get(0);
        Expr last_exp = null;
        Expr l = null;
        Expr r = null;
        Plus p = null;
        Minus m = null;

        for (int i = 1; i < elements.size(); i++) {
            
            if ( null != elements.get(i).get(Label.Plus)) {
                
                if (i == 1) {
                    l = exp;
                } else {
                    l = last_exp;
                }
                r = (Expr) elements.get(i).get(Label.Mexp);
                p = new Plus(l, r);
                last_exp = (Expr) p;
            }

            if ( null != elements.get(i).get(Label.Minus)) {
                if (i == 1) {
                    l = exp;
                } else {
                    l = last_exp;
                }
                r = (Expr) elements.get(i).get(Label.Mexp);
                m = new Minus(l, r);

                last_exp = (Expr) m;
            }
        }

        return (BinOP) last_exp;
    }
}
