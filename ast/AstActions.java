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
        
        LValue node = new LValue(name, idx);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }

    /**
     * LPar Exp RPar
     */
    public ParExp MakeParExp(String input, int start, int end, List<TreeNode> elements) {
        Expr expr = (Expr)elements.get(1);
        ParExp node = new ParExp(expr);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
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

        CallBrack node = new CallBrack(id, params, exp);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
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

        New node = new New(type, exp);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }

   /**
     * "'" ("\\n" / "\\r" / "\\t" / "\\b" /  "\\\\" / (.) ) "'" Spacing 
     */
    public LiteralCharacter MakeLiteralCharacter(String input, int start, int end, List<TreeNode> elements) {
        String s = elements.get(1).text;
        LiteralCharacter node = null;

        switch(s) {
            case "\\n":
                node = new LiteralCharacter('\n');
                node.text = input.substring(start, end);
                node.offset = start;
                return node;
            case "\\r":
                node = new LiteralCharacter('\r');
                node.text = input.substring(start, end);
                node.offset = start;
                return node;
            case "\\t":
                node = new LiteralCharacter('\t');
                node.text = input.substring(start, end);
                node.offset = start;
                return node;
            case "\\b":
                node = new LiteralCharacter('\b');
                node.text = input.substring(start, end);
                node.offset = start;
                return node;
            case "\\\\":
                node = new LiteralCharacter('\\');
                node.text = input.substring(start, end);
                node.offset = start;
                return node;
            default:
                node = new LiteralCharacter(s.charAt(0));
                node.text = input.substring(start, end);
                node.offset = start;
                return node;
        }
    }
    
    /**
     * [0-9]+ Spacing
     */
    public NumberInteger MakeInteger (String input, int start, int end, List<TreeNode> elements) {
        int integer = Integer.parseInt(elements.get(0).text);
        NumberInteger node = new NumberInteger(integer);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }

    /**
     * ([0-9]* Dot [0-9]+) Spacing
     */
    public NumberDecimal MakeDecimal (String input, int start, int end, List<TreeNode> elements) {
        float decimal = Float.parseFloat(elements.get(0).text);
        NumberDecimal node = new NumberDecimal(decimal);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }

    /**
     * Null
     */
    public Null MakeNull (String input, int start, int end, List<TreeNode> elements) {
        Null node = new Null();
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }

    /**
     * False
     */
    public False MakeFalse (String input, int start, int end, List<TreeNode> elements) {
        False node = new False();
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }

    /**
     * True
     */
    public True MakeTrue (String input, int start, int end, List<TreeNode> elements) {
        True node = new True();
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }

    /**
     * Negative
     */
    public Negative MakeNegative (String input, int start, int end, List<TreeNode> elements) {
        Expr exp = (Expr)elements.get(1);
        Negative node = new Negative(exp);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }

    /**
     * Not
     */
    public Not MakeNot (String input, int start, int end, List<TreeNode> elements) {
        Expr exp = (Expr)elements.get(1);
        Not node = new Not(exp);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
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

        CallAttr node = new CallAttr(id, params, lvalues);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }

    /**
     * LValue Eq Exp Semi
     */
    public Attr MakeAttr (String input, int start, int end, List<TreeNode> elements) {
        LValue lvalue = (LValue)elements.get(0);
        Expr exp = (Expr)elements.get(2);
        Attr node = new Attr(lvalue, exp);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
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

        Return node = new Return(exp);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }

    /**
     * Print Exp Semi
     */
    public Print MakePrint (String input, int start, int end, List<TreeNode> elements) {
        Expr exp = (Expr) elements.get(1);
        Print node = new Print(exp);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }
    
    /**
     * Read LValue Semi 
     */
    public Read MakeRead (String input, int start, int end, List<TreeNode> elements) {
        LValue lvalue = (LValue) elements.get(1);
        Read node = new Read(lvalue);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }

    /**
     * Iterate LPar Exp RPar Cmd
     */
    public Iterate MakeIterate (String input, int start, int end, List<TreeNode> elements) {
        Expr exp = (Expr) elements.get(2);
        Cmd cmd = (Cmd) elements.get(4);
        Iterate node = new Iterate(exp, cmd);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
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
        If node = new If(exp, cmd, elseCmd);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }
    
    /**
     * ([A-Z] ([a-z] / [0-9] / "_" / [A-Z])*) Spacing
     */
    public UserType MakeUserType (String input, int start, int end, List<TreeNode> elements) {
        String id = elements.get(0).text;
        UserType node = new UserType(id);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }

    /**
     * Float
     */
    public TyFloat MakeTyFloat (String input, int start, int end, List<TreeNode> elements) {
        TyFloat node = new TyFloat();
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }

    /**
     * Bool
     */
    public TyBool MakeTyBool (String input, int start, int end, List<TreeNode> elements) {
        TyBool node = new TyBool();
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }

    /**
     * Char
     */
    public TyChar MakeTyChar (String input, int start, int end, List<TreeNode> elements) {
        TyChar node = new TyChar();
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }

    /**
     * Int
     */
    public TyInt MakeTyInt (String input, int start, int end, List<TreeNode> elements) {
        TyInt node = new TyInt();
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }

    /**
     * Btype (LBrack RBrack)*
     */
    public Type MakeType (String input, int start, int end, List<TreeNode> elements) {
        BType btype = (BType) elements.get(0);
        int dim = elements.get(1).elements.size();
        
        Type node = new Type(btype, dim);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
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

        Params node = new Params(p);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
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

        Func node = new Func(id, params, type, cmd);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }

    /*
     * Identifier ColonColon Type Semi
     */
    public Decl MakeDecl (String input, int start, int end, List<TreeNode> elements) {
        String id = elements.get(0).elements.get(0).text;
        Type type = (Type) elements.get(2);
        
        Decl node = new Decl(id, type);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
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
        
        Definition node = new Definition(type, declarations);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    } 

    /**
     * Spacing Definition* Func+ EndOfFile
     */
    public Prog MakeProg (String input, int start, int end, List<TreeNode> elements) {
        Definition[] defs = null;
        Func[] funcs = null;


        if ( 0 != elements.get(1).elements.size() ) {
            defs = new Definition[elements.get(1).elements.size()];
            for (int i = 0; i < defs.length; i++) {
                defs[i] = (Definition) elements.get(1).elements.get(i);
            }
        }

        funcs = new Func[elements.get(2).elements.size()];
        for (int i = 0; i < funcs.length; i++) {
            funcs[i] = (Func) elements.get(2).elements.get(i);
        }

        Prog node = new Prog(defs, funcs);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
    }

    /**
     * LBrace (Cmd+) RBrace 
     */
    public CmdBrace MakeCmdBrace (String input, int start, int end, List<TreeNode> elements) {
        Cmd[] cmds = new Cmd[elements.get(1).elements.size()];
        for (int i = 0; i < cmds.length; i++) {
            cmds[i] = (Cmd) elements.get(1).elements.get(i);
        }
        
        CmdBrace node = new CmdBrace(cmds);
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
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

        l.offset = start;
        l.text = input.substring(start, end);
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

        l.offset = start;
        l.text = input.substring(start, end);
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
        
        l.offset = start;
        l.text = input.substring(start, end);
        return l;
    }

    /**
     * Aexp Lt Aexp
     */
    public Expr MakeLt (String input, int start, int end, List<TreeNode> elements) {
        Expr left = (Expr)elements.get(0);
        Expr right = (Expr)elements.get(2);
        
        Expr node = (Expr)(new Lt(left, right));
        node.text = input.substring(start, end);
        node.offset = start;
        return node;
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

        l.offset = start;
        l.text = input.substring(start, end);
        return l;
    }

}
