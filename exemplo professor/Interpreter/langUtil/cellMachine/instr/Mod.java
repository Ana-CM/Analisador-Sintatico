package langUtil.cellMachine.instr;
import java.util.Stack;
import langUtil.cellMachine.*;
public class Mod extends Instr {
    
    public void interpret(StackState st){ st.mod();st.pcNext(); }
     
}
