/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */
package langUtil;

public class STyNull extends SType {
      
     private static STyNull st = new STyNull();
     
     private STyNull(){
     }
     
     public static STyNull newSTyNull(){ 
          return st;
     }
     
     public boolean match(SType v){
          return (v instanceof STyErr) || (v instanceof STyNull);
     }
     
     public String toString(){
     return "Null";
     }   
}