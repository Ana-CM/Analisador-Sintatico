package langUtil.cellMachine.instr;
import java.util.Stack;
import langUtil.cellMachine.*;
public class LtF extends Instr {
    
    public void interpret(StackState st){ st.ltF();st.pcNext(); }
     
}
