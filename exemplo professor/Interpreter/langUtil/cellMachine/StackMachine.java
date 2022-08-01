package langUtil.cellMachine;

import java.util.Stack;
import java.util.HashMap;
import java.util.ArrayList;
import langUtil.cellMachine.instr.*;


public class StackMachine {
   
    private StackState ss;
    private ArrayList<Instr> prog;
    private HashMap<String,Integer> lb;
    
    public StackMachine(int memSize, int progSize){
        ss = new StackState(memSize);
        prog = new ArrayList<Instr>(progSize);
        lb = new HashMap<String,Integer>();

    }
    
    private void lbCheck(Instr i,int pw){
        if(i instanceof Label){
           lb.put( ((Label)i).getLabel(),pw);
        }else if(i instanceof JumpF){
            Integer d = lb.get(((JumpF)i).getTarget());
            if( d != null ){
                ((JumpF)i).setAddDest(d);
            }
        }else if(i instanceof JumpT){
            Integer d = lb.get(((JumpT)i).getTarget());
            if( d != null ){
                ((JumpT)i).setAddDest(d);
            }
        }else if(i instanceof Jump){
            Integer d = lb.get(((Jump)i).getTarget());
            if( d != null ){
                ((Jump)i).setAddDest(d);
            }
        }else if(i instanceof Call){
            Integer d = lb.get(((Call)i).getTarget());
            if( d != null ){
                ((Call)i).setAddDest(d);
            }
        }
    }
    
    public void addInstr(Instr i){
        prog.add(i);
    }
    
    private void getLabels(){
       for(int i = 0; i < prog.size(); i++){
           if(prog.get(i) instanceof Label){
              lb.put( ((Label)prog.get(i)).getLabel(),i);
           }
        }
    }
    
    public void labelProcess(){
        getLabels();
        for(int i =0; i< prog.size(); i++){
            if(prog.get(i) instanceof JumpF){
               Integer d = lb.get(((JumpF)prog.get(i)).getTarget());
               if( d != null ){
                   ((JumpF)prog.get(i)).setAddDest(d);
               }
            }else if(prog.get(i) instanceof JumpT){
               Integer d = lb.get(((JumpT)prog.get(i)).getTarget());
               if( d != null ){
                    ((JumpT)prog.get(i)).setAddDest(d);
               }
            }else if(prog.get(i) instanceof Jump){
                Integer d = lb.get( ((Jump)prog.get(i)).getTarget() );
                if( d != null ){
                   ((Jump)prog.get(i)).setAddDest(d);
                }
            }else if(prog.get(i) instanceof Call){
                Integer d = lb.get(((Call)prog.get(i)).getTarget());
                if( d != null ){
                    ((Call)prog.get(i)).setAddDest(d);
                }
            }
        }
    }
    
    public void run(){
        ss.reset();
        while(ss.getPC() >= 0 && ss.getPC() < prog.size() && !ss.isHalted()){
           if(prog.get(ss.getPC()) != null){
              prog.get(ss.getPC()).interpret(ss);
           }else{
              System.out.println("Null Break");
              break;
           }
        }
        ss.printState();
    }
       
}
