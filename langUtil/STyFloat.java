/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */
package langUtil;

public class STyFloat extends SType {
     
     private static STyFloat st = new STyFloat();
     
     private STyFloat(){
     }
     
     public static STyFloat newSTyFloat(){
          return st; 
     }
     
     public boolean match(SType v){
          return (v instanceof STyErr) || (v instanceof STyInt) || (v instanceof STyGeneric);
     }
     
     public String toString(){
         return "Float";
     }
}
