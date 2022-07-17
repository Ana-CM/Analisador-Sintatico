package langUtil.cellMachine.instr;
import java.util.Stack;
import langUtil.cellMachine.*;
public class LtI extends Instr {
    
    public void interpret(StackState st){ st.ltI(); st.pcNext();}
     
}
