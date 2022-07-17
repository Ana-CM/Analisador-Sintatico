package langUtil.cellMachine;

import java.util.Stack;

public class FloatValue extends Value {
     
     private float i;
     
     public FloatValue(float i){
        this.i = i;    
     }
     
     public int     asInt()  { throw new ValException("Conversão inválida de valor: Inteiro não pode ser convertido para Float" );  }
     public boolean asBool() { throw new ValException("Conversão inválida de valor: Booleano não pode ser convertido para Float" ); }
     public float   asFloat(){ return i;   }
     public char    asChar() { throw new ValException("Conversão inválida de valor: Char não pode ser convertido para Float" );     }
      
     public boolean equals(Value v){
          if(v instanceof FloatValue){
              return (v.asFloat() == i);
          }else{
              throw new ValException("Comparação de igualdade inválida: " + i + " = " + v.toString() ); 
          }
     }
     
     public String toString(){ return i + "";}
}
