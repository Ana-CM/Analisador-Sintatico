package ast.expr;

/*
 * Esta classe representa uma valor booleano True.
 */
public class True extends Expr {
      
    public True(){
    }

    public boolean getValue(){ return true;}

    //@Override
    public String toString(){
        return   "true"; 
    }
}