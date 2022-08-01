package langUtil.cellMachine.instr;

import java.util.Stack;
import langUtil.cellMachine.*;

public class AddI extends Instr {
    
    public void interpret(StackState st){ 
       st.addI(); 
       st.pcNext();
    }
     
}
