/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */
package langUtil;
import java.util.HashMap;

public class STyUserType extends SType {
     
    private HashMap<String,SType> decls;
    private String name;  
    
    public STyUserType(String name, HashMap<String,SType> decls){
        this.name = name;
        this.decls = decls;
    }
    
    public String getName(){
        return name;
    }
    
    public HashMap<String,SType> getdecls(){
        return decls;
    }
        
    public boolean match(SType v){
        return (v instanceof STyGeneric) || (v instanceof STyErr) || ((v instanceof STyUserType) && ((STyUserType)v).name == name);
    }
    
    public String toString(){
        String s = name + "{";
        for (String k : decls.keySet()) {
            s += k + "::" + decls.get(k).toString() + ";";
        }
        s += "}";
        return s;
    }
}
