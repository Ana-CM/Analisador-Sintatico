/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.expr;

import ast.Type;
import visitors.Visitor;


/*
 * Esta classe representa um novo objeto.
 * New Type ( LBrack Exp RBrack )?
 */
public class New extends Expr {
    
    private Expr exp;
    private Type type;

    public New(Type type, Expr exp){
        this.exp = exp;
        this.type = type;
    }

    public New(Type type){
        this.type = type;
        exp = null;
    }

    public Expr getExp(){ return exp;}

    public Type getType(){ return type;}

    //@Override
    public String toString(){
        String s = "new " + type.toString(); 

        if(exp != null){  
            s += "["; 
            s += exp.toString();
            s += "]";
        }  
        return  s; 
    }
    public void accept(Visitor v){ v.visit(this);}
}