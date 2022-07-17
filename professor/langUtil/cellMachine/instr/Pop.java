package langUtil.cellMachine.instr;

import java.util.Stack;
import langUtil.cellMachine.*;


public class Pop extends Instr {

    public Pop(){}
    
    public void interpret(StackState st){ st.pop();st.pcNext(); }
     
}
