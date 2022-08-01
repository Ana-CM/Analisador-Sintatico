package langUtil.cellMachine.instr;
import java.util.Stack;
import langUtil.cellMachine.*;
public class SubF extends Instr {
    
    public void interpret(StackState st){ st.subF();st.pcNext(); }
     
}
