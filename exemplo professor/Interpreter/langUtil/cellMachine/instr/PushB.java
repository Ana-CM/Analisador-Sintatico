package langUtil.cellMachine.instr;

import java.util.Stack;
import langUtil.cellMachine.*;


public class PushB extends Instr {
    
    private boolean n;
    public PushB(boolean x){ n = x;}
    
    public void interpret(StackState st){ st.push(new BoolValue(n) );st.pcNext(); }
     
}
