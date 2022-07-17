package langUtil.cellMachine.instr;
import java.util.Stack;
import langUtil.cellMachine.*;
import java.util.Scanner;

public class ReadC extends Instr {
    
    private Scanner sc;
    
    public ReadC(){ sc = new Scanner(System.in); }
    
    public void interpret(StackState st){  
        try{
          st.push(new IntValue(sc.next(".").charAt(0) ));
          st.pcNext();
        }catch(Exception e){
            System.out.println("Erro lendo inteiro " );
        }
    }
     
}
