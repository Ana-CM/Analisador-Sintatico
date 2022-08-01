package langUtil.cellMachine.instr;
import java.util.Stack;
import langUtil.cellMachine.*;
public class Not extends Instr {
    
    public void interpret(StackState st){ st.not();st.pcNext(); }
     
}
