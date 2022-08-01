package langUtil.cellMachine.instr;
import java.util.Stack;
import langUtil.cellMachine.*;
public class Save extends Instr {
    
    private int arg;
    
    public Save(int add){ arg = add; }
    
    public void interpret(StackState st){ st.save(arg);st.pcNext(); }
     
}
