package langUtil.cellMachine.instr;
import java.util.Stack;
import langUtil.cellMachine.*;
import java.util.Scanner;

public class ReadI extends Instr {
    
    private Scanner sc;
    
    public ReadI(){ sc = new Scanner(System.in); }
    
    public void interpret(StackState st){  
        try{
          st.push(new IntValue(sc.nextInt()));
          st.pcNext();
        }catch(Exception e){
            System.out.println("Erro lendo inteiro " );
        }
    }
     
}
