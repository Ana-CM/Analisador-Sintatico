package langUtil.cellMachine.instr;

import java.util.Stack;
import langUtil.cellMachine.*;


public class PushC extends Instr {
    
    private char n;
    public PushC(char x){ n = x;}
    
    public void interpret(StackState st){ st.push(new CharValue(n) );st.pcNext(); }
     
}
