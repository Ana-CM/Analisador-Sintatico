package ast.expr;

/*
 * Esta classe representa um valor booleano False.
 */
public class False extends Expr {
      
    public False(){
    }
    
    public boolean getValue(){ return false;}
    
    //@Override
    public String toString(){
        return "false"; 
    }
}