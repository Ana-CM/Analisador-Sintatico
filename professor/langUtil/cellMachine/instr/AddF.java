package langUtil.cellMachine.instr;

import java.util.Stack;
import langUtil.cellMachine.*;

public class AddF extends Instr {
    
    public void interpret(StackState st){ 
        st.addF(); 
        st.pcNext();
    }
     
}
