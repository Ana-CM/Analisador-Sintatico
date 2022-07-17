package langUtil.cellMachine.instr;

import java.util.Stack;
import langUtil.cellMachine.*;


public class Halt extends Instr {

    public Halt(){}
    
    public void interpret(StackState st){ st.halt();}
     
}
