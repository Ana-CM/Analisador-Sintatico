/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */
package langUtil;

public class STyErr extends SType {
     
     private static STyErr st = new STyErr();;
     
     private STyErr(){
     }
     
     public static STyErr newSTyErr(){
          return st; 
     }
     
     public boolean match(SType v){
          return true;
     }
     
     public String toString(){
         return "TyError";
     }
}
