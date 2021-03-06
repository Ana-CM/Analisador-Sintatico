/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */
package langUtil;

public class STyChar extends SType {
     
     private static STyChar st = new STyChar();
     
     private STyChar(){
     }
     
     public static STyChar newSTyChar(){ 
          return st;
     }
     
     public boolean match(SType v){
          return (v instanceof STyErr) || (v instanceof STyChar) || (v instanceof STyGeneric);
     }
     
     public String toString(){
         return "Char";
     }   
}
