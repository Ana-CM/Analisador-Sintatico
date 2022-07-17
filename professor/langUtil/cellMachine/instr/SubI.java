package langUtil.cellMachine.instr;

import java.util.Stack;
import langUtil.cellMachine.*;
public class SubI extends Instr {
    
    public void interpret(StackState st){ st.subI();st.pcNext(); }
     
}
