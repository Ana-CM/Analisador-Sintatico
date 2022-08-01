/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */
package langUtil;

public class STyGeneric extends SType {
          
     private static STyGeneric st = new STyGeneric();
     
     private STyGeneric(){
     }
     
     public static STyGeneric newSTyGeneric(){
          return st;
     }
     
     public boolean match(SType v){
          return true;
     }
     
     public String toString(){
          return "Generic";
     }
}