package langUtil.cellMachine.instr;
import java.util.Stack;
import langUtil.cellMachine.*;
public class Eq extends Instr {
    
    public void interpret(StackState st){ st.eq();st.pcNext(); }
     
}
