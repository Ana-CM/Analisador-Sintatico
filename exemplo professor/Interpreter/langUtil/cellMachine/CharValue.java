package langUtil.cellMachine;

import java.util.Stack;

public class CharValue extends Value {
     
     private char x;
     
     public CharValue(char x){
        this.x = x;    
     }
     
     public int     asInt()  { throw new ValException("Conversão inválida de valor: Inteiro não pode ser convertido para Float" ); }
     public boolean asBool() { throw new ValException("Conversão inválida de valor: Booleano não pode ser convertido para Float"); }
     public float   asFloat(){ throw new ValException("Conversão inválida de valor: Booleano não pode ser convertido para Float"); }
     public char    asChar() { return x;    }
     
     public boolean equals(Value v){
          if(v instanceof CharValue){
              return (v.asChar() == x);
          }else{
              throw new ValException("Comparação de igualdade inválida: " + x + " = " + v.toString() ); 
          }
     }
     
     public String toString(){ return x + "";}
}
