package langUtil.cellMachine.instr;

import java.util.Stack;
import langUtil.cellMachine.*;
public abstract class Instr {
    
    public abstract void interpret(StackState st);
     
}
