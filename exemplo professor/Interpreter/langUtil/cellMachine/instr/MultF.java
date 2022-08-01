package langUtil.cellMachine.instr;
import java.util.Stack;
import langUtil.cellMachine.*;
public class MultF extends Instr {
    
    public void interpret(StackState st){ st.multF();st.pcNext(); }
     
}
