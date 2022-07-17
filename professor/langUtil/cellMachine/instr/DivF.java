package langUtil.cellMachine.instr;
import java.util.Stack;
import langUtil.cellMachine.*;
public class DivF extends Instr {
    
    public void interpret(StackState st){ st.divF();st.pcNext(); }
     
}
