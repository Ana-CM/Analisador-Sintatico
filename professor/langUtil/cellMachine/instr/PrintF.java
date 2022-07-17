package langUtil.cellMachine.instr;

import java.util.Stack;
import langUtil.cellMachine.*;

public class PrintF extends Instr {
    
    public void interpret(StackState st){  
        System.out.print(st.pop().asFloat());
        st.pcNext();
    }
     
}
