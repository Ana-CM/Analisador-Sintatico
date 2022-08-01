package langUtil.cellMachine.instr;
import java.util.Stack;
import langUtil.cellMachine.*;

public class PrintC extends Instr {
    
    public void interpret(StackState st){  
        System.out.print(st.pop().asChar());
        st.pcNext();
    }
     
}
