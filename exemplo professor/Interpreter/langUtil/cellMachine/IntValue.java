package langUtil.cellMachine;

import java.util.Stack;

public class IntValue extends Value {
     
     private int i;
     
     public IntValue(int i){
        this.i = i;    
     }
     
     public int     asInt()  { return i; }
     public boolean asBool() { throw new ValException("Conversão inválida de valor: Booleano não pode ser convertido para Inteiro"); }
     public float   asFloat(){ throw new ValException("Conversão inválida de valor: Booleano não pode ser convertido para Inteiro"); }
     public char    asChar() { throw new ValException("Conversão inválida de valor: Char não pode ser convertido para Inteiro" );    }
     
     public boolean equals(Value v){
          if(v instanceof IntValue){
              return (v.asInt() == i);
          }else{
              throw new ValException("Comparação de igualdade inválida: " + i + " = " + v.toString() ); 
          }
     }
     
     public String toString(){ return i + "";}
}
