/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.lang.model.element.ElementKind;

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
        

        idx = new ArrayList <Object>();

        for (TreeNode e : elements.get(1)) {
            if (e.get(Label.Exp) != null) {
                Expr exp = (Expr) e.get(Label.Exp);
                idx.add(exp);
            }
            
            else if (e.get(Label.Identifier) != null) {
                String id = (String) e.get(Label.Identifier).elements.get(0).text;
                idx.add(id);
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
     * Exp ( Comma Exp )*
     */
    public CallBrack MakeCallBrack(String input, int start, int end, List<TreeNode> elements) {
        String id = elements.get(0).text;
        Expr[] params = null;
        Expr exp = (Expr)elements.get(5);

        if (elements.get(2).elements.size() > 0) {
            params = new Expr[1 + elements.get(2).elements.get(1).elements.size()];
            for(int i = 0; i < params.length; i++){
                if ( i == 0 ) {
                    params[i] = (Expr)elements.get(2).elements.get(i);
                } else {
                    params[i] = (Expr)elements.get(2).elements.get(1).elements.get(i-1).get(Label.Exp);
                }
            }
        }

        return new CallBrack(id, params, exp);
    }

    /**
     * New Type ( LBrack Exp RBrack )?
     */
    public New MakeNew(String input, int start, int end, List<TreeNode> elements) {
        Type type = (Type) elements.get(1);
        Expr exp = null;
        if(elements.get(2).elements.size() > 1){
            exp = (Expr) elements.get(2).get(Label.Exp);
        }
        return new New(type, exp);
    }

    /**
     * "'" ("\\n" / "\\r" / "\\t" / "\\b" /  "\\\\" / (.) ) "'" Spacing 
     */
    public LiteralCharacter MakeLiteralCharacter(String input, int start, int end, List<TreeNode> elements) {
        String s = elements.get(1).text;

        switch(s) {
            case "\\n":
                return new LiteralCharacter('\n');
            case "\\r":
                return new LiteralCharacter('\r');
            case "\\t":
                return new LiteralCharacter('\t');
            case "\\b":
                return new LiteralCharacter('\b');
            case "\\\\":
                return new LiteralCharacter('\\');
            default:
                return new LiteralCharacter(s.charAt(0));
        }
    }
    
    /**
     * [0-9]+ Spacing
     */
    public NumberInteger MakeInteger (String input, int start, int end, List<TreeNode> elements) {
        int integer = Integer.parseInt(elements.get(0).text);
        return new NumberInteger(integer);
    }

    /**
     * ([0-9]* Dot [0-9]+) Spacing
     */
    public NumberDecimal MakeDecimal (String input, int start, int end, List<TreeNode> elements) {
        float decimal = Float.parseFloat(elements.get(0).text);
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

        if (elements.get(2).elements.size() > 0) {
            params = new Expr[1 + elements.get(2).elements.get(1).elements.size()];
            for(int i = 0; i < params.length; i++){
                if ( i == 0 ) {
                    params[i] = (Expr)elements.get(2).elements.get(i);
                } else {
                    params[i] = (Expr)elements.get(2).elements.get(1).elements.get(i - 1).get(Label.Exp);
                }
            }
        }

        if ( null !=  elements.get(4).get(Label.LValue) ) {
            lvalues = new LValue[1 + elements.get(4).elements.get(2).elements.size()];
            lvalues[0] = (LValue)elements.get(4).get(Label.LValue);
            int i = 1;

            for(TreeNode e: elements.get(4).elements.get(2).elements) {
                lvalues[i] = (LValue)e.get(Label.LValue);
                i++;
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
        
        exp = new Expr[1 + elements.get(2).elements.size()];

        exp[0] = (Expr)elements.get(1);
        int i = 1;

        for(TreeNode e: elements.get(2)){
            exp[i] = (Expr)e.get(Label.Exp);
            i++;
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
     * ([A-Z] ([a-z] / [0-9] / "_" / [A-Z])*) Spacing
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
        int dim = elements.get(1).elements.size();
        
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

        
        for (TreeNode e : elements.get(3)) {
            id = e.get(Label.Identifier).elements.get(0).text;
            type = (Type) e.get(Label.Type);
            pair = Map.entry(id, type);
            p.add(pair);
        }

        return new Params(p);
    }

    /**
     * Identifier LPar Params? RPar  ( Colon Type ( Comma Type)* )? LBrace Cmd* RBrace
     *  Params      <- Identifier ColonColon Type (Comma Identifier ColonColon Type)*
     */
    public Func MakeFunc (String input, int start, int end, List<TreeNode> elements) {
        String id = elements.get(0).elements.get(0).text;
        Params params = null;
        Type[] type = null;
        Cmd[] cmd = null;

        if ( elements.get(2) instanceof Params)
            params = (Params) elements.get(2);

        if ( null != elements.get(4).get(Label.Type) ) {
            type = new Type[1 + elements.get(4).elements.get(2).elements.size()];
            type[0] = (Type)elements.get(4).get(Label.Type);
            int i = 1;

            for(TreeNode e: elements.get(4).elements.get(2).elements) {
                type[i] = (Type)e.get(Label.Type);
                i++;
            }
        } 
        cmd = new Cmd[elements.get(6).elements.size()];
        for (int i = 0; i < cmd.length; i++ ) {
            cmd[i] = (Cmd) elements.get(6).elements.get(i);

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
     * Data Type LBrace Declaration+ RBrace
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
     * Spacing Definition* Func+ EndOfFile
     */
    public Prog MakeProg (String input, int start, int end, List<TreeNode> elements) {
        Definition[] defs = null;
        Func[] funcs = null;

        if ( null != elements.get(1).get(Label.Definition) ) {
            defs = new Definition[elements.get(1).elements.size()];
            for (int i = 0; i < defs.length; i++) {
                defs[i] = (Definition) elements.get(1).elements.get(i);
            }
        }

        funcs = new Func[elements.get(2).elements.size()];
        for (int i = 0; i < funcs.length; i++) {
            funcs[i] = (Func) elements.get(2).elements.get(i);
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
     * Mexp <- Sexp ((Times / Div / Mod) Sexp)*
     */
    public Expr MakeMexp (String input, int start, int end, List<TreeNode> elements) {
        Expr l = (Expr) elements.get(0);
        Expr r = null;
        
        if (0 == elements.get(1).elements.size())
            return (Expr) l;
 
        for (TreeNode e : elements.get(1)) {
            r = (Expr) e.get(Label.Sexp);
            String operator = e.elements.get(0).elements.get(0).text;

            if (operator.contains("*"))
                l = (Expr) (new Times(l, r));

            else if (operator.contains("/"))
                l = (Expr) (new Div(l, r));

            else if (operator.contains("%"))
                l = (Expr) (new Mod(l, r));
        }

        return l;
    }

    /**
     * Mexp ((Plus / Minus) Mexp)*
     */
    public Expr MakeAexp (String input, int start, int end, List<TreeNode> elements) {
        Expr l = (Expr) elements.get(0);
        Expr r = null;
        
        if (0 == elements.get(1).elements.size())
            return (Expr) l;
 
        for (TreeNode e : elements.get(1)) {
            r = (Expr) e.get(Label.Mexp);
            String operator = e.elements.get(0).elements.get(0).text;
            
            if (operator.contains("+"))
                l = (Expr) (new Plus(l, r));
        
            else if (operator.contains("-")){
                l = (Expr) (new Minus(l, r));
            }
        }

        return l;
    }

    /**
     * Aexp ((Eqeq / Ne) Aexp)* 
     */
    public Expr MakeRexp (String input, int start, int end, List<TreeNode> elements) {
        Expr l = (Expr) elements.get(0);
        Expr r = null;
        
        if (0 == elements.get(1).elements.size())
            return (Expr) l;
 
        for (TreeNode e : elements.get(1)) {
            r = (Expr) e.get(Label.Aexp);
            String operator = e.elements.get(0).elements.get(0).text;

            if (operator.contains("=="))
                l = (Expr) (new Eq(l, r));

            else if (operator.contains("!="))
                l = (Expr) (new Dif(l, r));
        }

        return l;
    }

    /**
     * Aexp Lt Aexp
     */
    public Expr MakeLt (String input, int start, int end, List<TreeNode> elements) {
        Expr left = (Expr)elements.get(0);
        Expr right = (Expr)elements.get(2);
        return (Expr) (new Lt(left, right));
    }
    
    /**
     * Rexp (And Rexp)* 
     */
    public Expr MakeExp (String input, int start, int end, List<TreeNode> elements) {
        Expr l = (Expr) elements.get(0);
        Expr r = null;
        
        if (0 == elements.get(1).elements.size())
            return (Expr) l;
 
        for (TreeNode e : elements.get(1)) {
            r = (Expr) e.get(Label.Rexp);
            String operator = e.elements.get(0).elements.get(0).text;

            if (operator.contains("&&"))
                l = (Expr) (new And(l, r));
        }

        return l;
    }

}

