package ast.btype;

/**
 * TyInt
 */
public class TyInt extends BType {

    public TyInt() {
    }

    public boolean match(BType t){
        return t instanceof TyInt; 
    }
     
    public String toString(){ return "Int"; }
}
