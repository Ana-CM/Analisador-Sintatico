package langUtil.cellMachine.instr;
import java.util.Stack;
import langUtil.cellMachine.*;
public class Ret extends Instr {
    
    public void interpret(StackState st){ 
        st.savePC();
        st.pcNext();
    }
     
}
