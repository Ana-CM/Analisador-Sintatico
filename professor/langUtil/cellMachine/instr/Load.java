package langUtil.cellMachine.instr;
import java.util.Stack;
import langUtil.cellMachine.*;
public class Load extends Instr {
    
    private int arg;
    
    public Load(int add){ arg = add;}
    
    public void interpret(StackState st){ st.load(arg); st.pcNext();}
     
}
