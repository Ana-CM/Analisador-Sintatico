/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.expr;

import java.util.ArrayList;
import java.util.List;
import visitors.Visitor;


/*
 * Identifier (LBrack Exp RBrack / Dot Identifier)*
 */
public class LValue extends Expr{
    List<Object> idx = new ArrayList <Object>();
    private String name;

    public LValue(String name, List<Object> idx){
        this.idx = idx;
        this.name = name;
    }

    public LValue(String name){
        this.name = name;
        idx = null;
    }

    public List<Object> getIdx(){ return idx;}

    public String getName(){ return name;}

    public String toString(){
        String s = name;
        if(idx != null){  
            for(Object e : idx ){

                if (e instanceof Expr ) {
                    s += "["; 
                    s += e.toString();
                    s += "]";
                }
                else if (e instanceof String) {
                    s += "."; 
                    s += e.toString();
                }
            }
        }  
        return  s; 
    }
    public void accept(Visitor v){ v.visit(this);}
}