package langUtil.cellMachine.instr;

import java.util.Stack;
import langUtil.cellMachine.*;


public class PushI extends Instr {
    
    private int n;
    public PushI(int x){ n =x;}
    
    public void interpret(StackState st){ st.push(new IntValue(n));st.pcNext(); }
     
}
