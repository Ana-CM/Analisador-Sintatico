package langUtil.cellMachine.instr;


import langUtil.cellMachine.*;
import java.util.Stack;

public class Call extends Instr {
    
    private String dest; 
    private int add;
    
    public Call(String s){
        dest = s;
        add = -1;
    }
    
    public Call(String s, int add){
        dest = s;
        this.add = add;
    }
  
    public String getTarget(){ return dest;}
    public void setAddDest(int add){
        this.add = add;
    }
    
    public void interpret(StackState st){ 
        st.loadPC();
        st.push(new IntValue(1));
        st.addI();
        if(add >= 0){
           st.push(new IntValue(add));
           st.savePC();
        }
    }
    
}
