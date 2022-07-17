package langUtil.cellMachine.instr;
import java.util.Stack;
import langUtil.cellMachine.*;
import java.util.Scanner;

public class ReadF extends Instr {
    
    private Scanner sc;
    
    public ReadF(){ sc = new Scanner(System.in); }
    
    public void interpret(StackState st){  
        try{
          st.push(new FloatValue(sc.nextFloat()));
          st.pcNext();
        }catch(Exception e){
            System.out.println("Erro lendo float " );
        }
    }
     
}
