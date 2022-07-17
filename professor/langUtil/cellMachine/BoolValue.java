package langUtil.cellMachine;

import java.util.Stack;

public class BoolValue extends Value {
     
     private boolean b;
     
     public BoolValue(boolean b){
        this.b = b;    
     }
     
     public int     asInt()  { throw new ValException("Conversão inválida de valor: Inteiro não pode ser convertido para Boolean" ); }
     public boolean asBool() { return b; }
     public float   asFloat(){ throw new ValException("Conversão inválida de valor: Booleano não pode ser convertido para Boolean"); }
     public char    asChar() { throw new ValException("Conversão inválida de valor: Char não pode ser convertido para Boolean" );    }
     
      
     public boolean equals(Value v){
          if(v instanceof BoolValue){
              return (v.asBool() == b);
          }else{
              throw new ValException("Comparação de igualdade inválida: " + b + " = " + v.toString() ); 
          }
     }
     
     public String toString(){ return b + "";}
}
