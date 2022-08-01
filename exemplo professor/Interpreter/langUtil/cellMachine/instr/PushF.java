package langUtil.cellMachine.instr;

import java.util.Stack;
import langUtil.cellMachine.*;


public class PushF extends Instr {
    
    private float n;
    public PushF(float x){ n =x;}
    
    public void interpret(StackState st){ st.push(new FloatValue(n)); st.pcNext();}
     
}
