package langUtil.cellMachine.instr;


import langUtil.cellMachine.*;
import java.util.Stack;

public class JumpT extends Instr {
    
    private String dest; 
    private int add;
    
    public JumpT(String s){
        dest = s;
        add = -1;
    }
    
    public JumpT(String s, int add){
        dest = s;
        this.add = add;
    }
  
    public String getTarget(){ return dest;}
    
    public void setAddDest(int add){
        this.add = add;
    }
    
    public void interpret(StackState st){ 
        if(add >= 0){
           if(st.pop().asBool()){
               st.push(new IntValue(add));
               st.savePC();
           }
        }else{ st.pcNext(); }
        
    }
    
}
