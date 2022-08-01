package visitors;

import ast.*;
import java.util.HashMap;
import langUtil.cellMachine.*;

public class  MachineInterpretVisitor extends Visitor {
     private HashMap<String,Integer> env;
     private HashMap<String,Node> funcs;
     private Node main;
     
     private StackState sm;
     private int auto, des;
    
     
     public MachineInterpretVisitor(){
        env = new HashMap<String,Integer>();
        funcs = new HashMap<String,Node>();
        sm = new StackState(100);
        auto = 0;
        des = 0;
     }
     
     public void visit(Program p){
         main = null;
         for(Func f : p.getFuncs()){
              funcs.put(f.getID(),f);
              if(f.getID().equals("main") ){
                  main = f;
              }
         }
     }
     
     public void visit(Add e){
         try{
           e.getLeft().accept(this);
           e.getRight().accept(this);
           sm.addI();
         }catch(ValException x){
             throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(Sub e){
        try{
           e.getLeft().accept(this);
           e.getRight().accept(this);
           sm.subI();
        }catch(ValException x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(Mul e){
        try{ 
           e.getLeft().accept(this);
           e.getRight().accept(this);
           sm.multI();
        }catch(ValException x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(Div e){
        try{  
            e.getLeft().accept(this);
            e.getRight().accept(this);
            sm.divI();
        }catch(ValException x){
             throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(Mod e){
        try{  
            e.getLeft().accept(this);
            e.getRight().accept(this);
            sm.mod();
        }catch(ValException x){
             throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }    
     
     public  void visit(And e){
         try{  
            e.getLeft().accept(this);
            e.getRight().accept(this);
            sm.and();
        }catch(ValException x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(Lt e){
         try{   
            e.getLeft().accept(this);
            e.getRight().accept(this);
            sm.ltI();
         }catch(ValException x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(Eq e){
         try{   
            e.getLeft().accept(this);
            e.getRight().accept(this);
            sm.eq();
         }catch(ValException x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(Not e){
        try{   
             e.getExpr().accept(this);
             sm.not();
        }catch(ValException x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
          
     public  void visit(True e){ 
         try{
            sm.push(new BoolValue(true));
        }catch(ValException x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(False e){
        try{
            sm.push(new BoolValue(false));
        }catch(ValException x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(NInt e){ 
         try{   
             sm.push(new IntValue(e.getValue()));
         }catch(ValException x){
             throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(NFloat e){ 
         try{   
             sm.push(new FloatValue(e.getValue()));
         }catch(ValException x){
             throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(Var e){ 
         try{
             Integer i = env.get(e.getName());
             sm.load(i);
         }catch(ValException x){
             throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(Call e){ 
         try{
         
             Node n = funcs.get(e.getName());
             if(n != null){
                 n.accept(this);
             }
             else{
                throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") chamada a função não definida: " + e.getName() );
             }
             
         }catch(ValException x){
             throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     
     
     public  void visit(Attr e){
         try{   
            Integer i = env.get(e.getID().getName());
            e.getExp().accept(this);
            if(i != null){
                sm.save(i);
            }
            else{
               env.put(e.getID().getName(),auto);
               sm.save(auto);
               auto++;
            }
         }catch(ValException x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(If e){
        try{
            e.getTeste().accept(this);
            if(sm.pop().asBool()){
                e.getThen().accept(this);
            }else if(e.getElse() != null){
                e.getElse().accept(this);
            }
        }catch(ValException x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(While e){
        try{
            e.getTeste().accept(this);
            while(sm.pop().asBool()){
                e.getBody().accept(this);
                e.getTeste().accept(this);
            }
        }catch(ValException x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(Print e){
         try{
              e.getExpr().accept(this);
              System.out.println(sm.pop().toString());
         }catch(ValException x){
              throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(StmtList e){
          try{   
              e.getCmd1().accept(this);
              e.getCmd2().accept(this);
          }catch(ValException x){
              throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
          }  
     }
     
     public  void visit(Func f){
           for(Param p: f.getParams()){
               Integer addr = env.get(p.getID());
               if(addr == null){
                  addr = auto++;
                  env.put(p.getID(),addr);
               }
               sm.save(addr);
           }
           f.getBody().accept(this);
     }
     
     public  void visit(Inst e){
     }
     
     public  void visit(Return e){
          e.getExpr().accept(this);
     }
     
     public  void visit(Param e){ }
     
     public void visit(TyInt t)  {   }
      
     public void visit(TyFloat t){   }
     
     public void visit(TyBool t) {   }
     
     public void visit(TyArr t)  {   }    
     
}
