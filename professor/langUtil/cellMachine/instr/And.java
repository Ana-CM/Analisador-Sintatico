package langUtil.cellMachine.instr;
import java.util.Stack;

import langUtil.cellMachine.*;

public class And extends Instr {
    
    public void interpret(StackState st){ st.and(); st.pcNext(); }
     
}
