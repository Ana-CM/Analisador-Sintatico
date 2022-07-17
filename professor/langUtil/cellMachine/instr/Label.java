package langUtil.cellMachine.instr;

import langUtil.cellMachine.*;
import java.util.Stack;

public class Label extends Instr {
    
    private String dest; 
    private int add;
    private Instr i;
    
    public Label(String s, Instr i){
        dest = s;
        add = -1;
        this.i = i; 
    }
    
    public Label(String s, int add, Instr i){
        dest = s;
        this.i = i;
        this.add = add;
    }
    
    public void setAdd(int n){ add = n; }
    
    public String getLabel(){return dest;}

    public void interpret(StackState st){ i.interpret(st); }
    
}
