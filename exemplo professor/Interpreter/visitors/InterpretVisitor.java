package visitors;

import ast.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Stack;

import langUtil.*;

public class  InterpretVisitor extends Visitor {

     private Stack<HashMap<String,Object>> env;     
     private HashMap<String,Func> funcs;     
     private Stack<Object> operands;
     private boolean retMode, debug;
     
     
     public InterpretVisitor(){
        env = new Stack<HashMap<String,Object>>();
        env.push(new HashMap<String,Object>());
        funcs = new  HashMap<String,Func>();
        operands = new Stack<Object>();
        retMode = false;
        debug = false;
     }
     
     public InterpretVisitor(boolean debug){
        this();
        this.debug = debug;
     }
     public void visit(Program p){
         Node main = null;
         for(Func f : p.getFuncs()){
             funcs.put(f.getID(),f);
             if(f.getID().equals("inicio")){
                 main = f;
             }
         }
         if(main == null){
            throw new RuntimeException( "Não há uma função chamada inicio ! abortando ! ");
         }
         main.accept(this);
     }
     
     public  void visit(Add e){
         try{
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq,dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop();
            operands.push( new Integer(esq.intValue() +  dir.intValue() ) ); 
         }catch(Exception x){
             throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(Sub e){
        try{
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq,dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop();
            operands.push( new Integer(esq.intValue() -  dir.intValue() ) ); 
        }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(Mul e){
        try{ 
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq,dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop();
            operands.push( new Integer(esq.intValue() *  dir.intValue() ) ); 
        }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(Div e){
        try{  
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq, dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop();
            operands.push( new Float(esq.intValue() /  dir.intValue() ) ); 
        }catch(Exception x){
             throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(Mod e){
        try{  
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq, dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop();
            operands.push( new Integer(esq.intValue() %  dir.intValue() ) ); 
        }catch(Exception x){
             throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(And e){
         try{  
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object esq,dir;
            dir = operands.pop();
            esq = operands.pop();
            operands.push( new Boolean( (Boolean)esq &&  (Boolean)dir ) ); 
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(Lt e){
         try{   
             e.getLeft().accept(this);
             e.getRight().accept(this);
             Object esq,dir;
             dir = operands.pop();
             esq = operands.pop();
             operands.push( new Boolean( (Integer)esq <  (Integer)dir ) ); 
         }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(Eq e){
         try{   
             e.getLeft().accept(this);
             e.getRight().accept(this);
             operands.push( new Boolean( operands.pop().equals(operands.pop()) ) );
         }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     
     public  void visit(Not e){
        try{   
             e.getExpr().accept(this);
             operands.push (new Boolean( ! (Boolean)operands.pop() ) );
        }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     
     // gcd (12,9)
     //
     // > 9
     //   12
     
    public  void visit(Call e){
        try{
            Func f = funcs.get(e.getName());
            if(f != null){
                 for(Expr exp : e.getArgs()){
                      exp.accept(this);
                 }
                 f.accept(this);
               
            }else{
               throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") Função não definida " +  e.getName());
            }
            
        }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(True e){ 
         try{
            operands.push(  new Boolean(true));
        }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(False e){
        try{
             operands.push(  new Boolean(false));
        }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(NInt e){ 
         try{   
              operands.push( new Integer(e.getValue()) );
         }catch(Exception x){
             throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(NFloat e){ 
         try{   
             operands.push( new Float(e.getValue() ));
         }catch(Exception x){
             throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(Var e){ 
         try{   
             Object r = env.peek().get(e.getName());
             if(r != null){   
                if(e.getIdx() != null){
                    for(Expr exp : e.getIdx()){
                        exp.accept(this);
                        r = ((ArrayList)r).get( (Integer)operands.pop());
                    }
                }
                operands.push(r);
             }
             else{throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") variável não declarada " +e.getName() );}
         }catch(Exception x){
             throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     
     
     public  void visit(Attr e){
         try{   
            Var v = e.getID();
            e.getExp().accept(this);
            Object val = operands.pop();
            
            if(v.getIdx() != null && v.getIdx().length > 0 ){
                ArrayList arr = (ArrayList)env.peek().get(v.getName());
                for(int k = 0; k < v.getIdx().length-1; k++ ){
                   v.getIdx()[k].accept(this);
                   arr = (ArrayList)arr.get( (Integer)operands.pop());
                }
                v.getIdx()[v.getIdx().length-1 ].accept(this);
                arr.set( (Integer)operands.pop(), val);
            }
            else{ env.peek().put(e.getID().getName(), val);}
            
         }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(If e){
        try{
            e.getTeste().accept(this);
            if((Boolean)operands.pop()){
                e.getThen().accept(this);
            }else if(e.getElse() != null){
                e.getElse().accept(this);
            }
        }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(While e){
        try{
            e.getTeste().accept(this);
            while( (Boolean)operands.pop()){
                e.getBody().accept(this);
                e.getTeste().accept(this);
            }
        }catch(Exception x){
           throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
        }
     }
     
     public  void visit(Print e){
         try{
              e.getExpr().accept(this);
              System.out.println(operands.pop().toString());
         }catch(Exception x){
              throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
     }
     
     public  void visit(StmtList e){
          if(retMode){ return;}
          try{
              e.getCmd1().accept(this);
              if(retMode){ return;}
              e.getCmd2().accept(this);
          }catch(Exception x){
              throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
          }  
     }

     
    public void visit(Func f){
         HashMap<String,Object> localEnv = new HashMap<String,Object>();
         for(int  i = f.getParams().length-1; i >= 0; i--){
             localEnv.put(f.getParams()[i].getID(),operands.pop());
         } 
         env.push(localEnv);
         f.getBody().accept(this);
         if(debug && f.getID().equals("inicio") ){
             
             Object[] x = env.peek().keySet().toArray(); 
             System.out.println("-------------- Memoria ----------------");
             for(int i =0; i < x.length; i++){
                 System.out.println( ((String)x[i]) + " : " +  env.peek().get(x[i]).toString() );
             }
         }
         env.pop();
         retMode= false;
         
    }
     
    public  void visit(Inst e){
         try{   
            Var v = e.getID();
            e.getSize().accept(this);
            Integer size = (Integer)operands.pop();
            ArrayList val = new ArrayList(size);

            for(int i = 0; i< size; i++){ val.add(null);    }
            
            if( env.peek().get(v.getName()) == null ){
                env.peek().put(v.getName(), val);
            }else if(  v.getIdx() != null && v.getIdx().length > 0 ){
                ArrayList arr = (ArrayList)env.peek().get(v.getName());
                for(int k = 0; k < v.getIdx().length-1; k++ ){
                   v.getIdx()[k].accept(this);
                   arr = (ArrayList)arr.get( (Integer)operands.pop());
                }
                v.getIdx()[v.getIdx().length-1 ].accept(this);
                arr.set( (Integer)operands.pop(), val);
            }
            else{
               env.peek().put(e.getID().getName(), val);
            }
            
         }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getCol() + ") " + x.getMessage() );
         }
    }
     
     public  void visit(Return e){
          e.getExpr().accept(this);
          retMode = true;   
     }
     
     public  void visit(Param e){   }
     
     public void visit(TyInt t)  {   }
      
     public void visit(TyFloat t){   }
     
     public void visit(TyBool t) {   }
          
     public void visit(TyArr t)  {   }
     
}
