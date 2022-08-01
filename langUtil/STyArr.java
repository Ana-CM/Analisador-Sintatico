/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */
package langUtil;

public class STyArr extends SType {
     
    private SType a;
    
    public STyArr(SType t){
        a = t;
    }
    
    public SType getArg(){
        return a;
    }
        
    public boolean match(SType v){
        return (v instanceof STyGeneric)|| (v instanceof STyErr) || (v instanceof STyArr) && (a.match( ((STyArr)v).getArg() ) );
    }
    
    public String toString(){
        return a.toString() + "[]";
    }
}
