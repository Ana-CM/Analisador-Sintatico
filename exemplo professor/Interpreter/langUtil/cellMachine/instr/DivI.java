package langUtil.cellMachine.instr;

import java.util.Stack;
import langUtil.cellMachine.*;
public class DivI extends Instr {
    
    public void interpret(StackState st){ st.divI();st.pcNext(); }
     
}
