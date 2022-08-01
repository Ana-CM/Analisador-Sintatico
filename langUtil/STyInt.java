/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */
package langUtil;

public class STyInt extends SType {
     
     private static STyInt st = new STyInt();
     
     private STyInt(){
     }
     
     public static STyInt newSTyInt(){ 
          return st;
     }
     
     public boolean match(SType v){
          return (v instanceof STyErr) ||(v instanceof STyInt) || (v instanceof STyGeneric);
     }
     
     public String toString(){
         return "Int";
     }   
}
