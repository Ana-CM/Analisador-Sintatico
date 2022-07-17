package visitors;

import ast.*;
import ast.btype.*;
import ast.cmd.*;
import ast.expr.*;
import ast.expr.binop.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Stack;

import lang.TreeNode;

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

    public void visit(Prog p){ //FIXME: Conferir se esta funcao está correta.
        Func main = null;
        for(Func f : p.getFuncs()){
            funcs.put(f.getId(),f);
            if(f.getId().equals("main")){
                main = f;
            }
        }
        if(main == null){
            throw new RuntimeException( "Não há uma função chamada main ! abortando ! ");
        }
        main.accept(this);
    }

    public void visit(Func f){
        HashMap<String,Object> localEnv = new HashMap<String,Object>();
        for(int i = f.getParams().getP().size()-1; i >= 0; i--){
            localEnv.put(f.getParams().getP().get(i).getKey(),operands.pop());
        } 
        env.push(localEnv);
        for (Cmd c : f.getCmd()) {
            if (retMode) break;
            c.accept(this);
        }
        if(debug && f.getId().equals("main") ){
            
            Object[] x = env.peek().keySet().toArray(); 
            System.out.println("-------------- Memoria ----------------");
            for(int i =0; i < x.length; i++){
                System.out.println( ((String)x[i]) + " : " +  env.peek().get(x[i]).toString() );
            }
        }
        env.pop();
        retMode= false;
    }

    public void visit(Params e){}

    public void visit(Type e){}

    public  void visit(LValue e){ 
        try{   
            Object r = env.peek().get(e.getName());
            if(r != null){   
                if(e.getIdx() != null){
                    for(int i = 0; i < e.getIdx().size(); i++){
                        Object o = e.getIdx().get(i);
                        if (o instanceof Expr) {
                            ((Expr)o).accept(this);
                            r = ((ArrayList)r).get( (Integer)operands.pop());
                        }
                        else if (o instanceof String) {
                            r = ((HashMap)r).get(o);
                        }
                    }
                }
                operands.push(r);
            }
            else{throw new RuntimeException( " (" + e.text + ", at position " + e.offset  +") variável não declarada " + e.getName() );}
        } catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    // public void visit(New e){ 
    //     try{
    //         LValue v = e.get
    //         e.getSize().accept(this);
    //         Integer size = (Integer)operands.pop();
    //         ArrayList val = new ArrayList(size);





    //         //----
            

    //         for(int i = 0; i< size; i++){ val.add(null);    }
            
    //         if( env.peek().get(v.getName()) == null ){
    //             env.peek().put(v.getName(), val);
    //         }else if(  v.getIdx() != null && v.getIdx().length > 0 ){
    //             ArrayList arr = (ArrayList)env.peek().get(v.getName());
    //             for(int k = 0; k < v.getIdx().length-1; k++ ){
    //                v.getIdx()[k].accept(this);
    //                arr = (ArrayList)arr.get( (Integer)operands.pop());
    //             }
    //             v.getIdx()[v.getIdx().length-1 ].accept(this);
    //             arr.set( (Integer)operands.pop(), val);
    //         }
    //         else{
    //            env.peek().put(e.getID().getName(), val);
    //         }
    //     }catch(Exception x){
    //         throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
    //     }
    // }

    // public  void visit(Attr e){
    //     try{   
    //         LValue v = e.getLvalue();
    //         e.getExp().accept(this);
    //         Object val = operands.pop();
            
    //         if(v.getIdx() != null && v.getIdx().size() > 0 ){

    //             if (env.peek().get(v.getName()) instanceof ArrayList) {
    //                 ArrayList arr = (ArrayList)env.peek().get(v.getName());
    //                 for(int k = 0; k < v.getIdx().size()-1; k++ ){
    //                     v.getIdx().get(k).accept(this);
    //                     arr = (ArrayList)arr.get( (Integer)operands.pop());
    //                 }
    //                 v.getIdx().get(v.getIdx().size()-1).accept(this);
    //                 arr.set( (Integer)operands.pop(), val);
    //             }
    //             else if (env.peek().get(v.getName()) instanceof HashMap) {
                    
    //             }
    //         }
    //         else{ env.peek().put(e.getLvalue().getName(), val);}
        
    //     }catch(Exception x){
    //         throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
    //     }
    // }

    public  void visit(Plus e){
        try{
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq,dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop(); 

            if(esq instanceof Float || dir instanceof Float){
                operands.push(esq.floatValue() + dir.floatValue());
            }
            else {
               operands.push(esq.intValue() + dir.intValue());
            }
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public  void visit(Minus e){
        try{
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq,dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop(); 

            if(esq instanceof Float || dir instanceof Float){
                operands.push(esq.floatValue() - dir.floatValue());
            }
            else {
               operands.push(esq.intValue() - dir.intValue());
            }
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public  void visit(Times e){
        try{
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq,dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop(); 

            if(esq instanceof Float || dir instanceof Float){
                operands.push(esq.floatValue() * dir.floatValue());
            }
            else {
               operands.push(esq.intValue() * dir.intValue());
            }
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public void visit(Div e){
        try{
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq,dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop(); 

            if(esq instanceof Float || dir instanceof Float){
                operands.push(esq.floatValue() / dir.floatValue());
            }
            else {
               operands.push(esq.intValue() / dir.intValue());
            }
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public void visit(Mod e){
        try{
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Number esq,dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop(); 

            if(esq instanceof Float || dir instanceof Float){
                operands.push(esq.floatValue() % dir.floatValue());
            }
            else {
               operands.push(esq.intValue() % dir.intValue());
            }
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public void visit(ParExp e){
        try{
            e.getExpr().accept(this);
            operands.push(operands.pop());
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public void visit(Negative e){
        try{
            e.getExpr().accept(this);
            Number num;
            num = (Number)operands.pop();

            if(num instanceof Float ){
                operands.push( - num.floatValue());
            }
            else {
                operands.push( - num.intValue());
            }
            
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public void visit(Not e){
        try{
            e.getExpr().accept(this);
            Object value;
            value = operands.pop();
            operands.push( ! (Boolean)value ); 
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public void visit(And e){
        try{  
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object esq,dir;
            dir = operands.pop();
            esq = operands.pop();
            operands.push( (Boolean)esq && (Boolean)dir ); 
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public void visit(Lt e){
        try{   
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object esq,dir;
            dir = operands.pop();
            esq = operands.pop();
            operands.push( (Float)esq < (Float)dir ); 
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public void visit(Eq e){
        try{   
            e.getLeft().accept(this);
            e.getRight().accept(this);
            operands.push( operands.pop().equals(operands.pop()) );
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public void visit(Dif e){
        try{   
            e.getLeft().accept(this);
            e.getRight().accept(this);
            operands.push( !operands.pop().equals(operands.pop()) );
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public void visit(True e){ 
        try{
            operands.push(true);
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public void visit(False e){ 
        try{
            operands.push(false);
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public void visit(NumberInteger e){ 
        try{   
            operands.push( e.getNumber_integer() );
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }
    
    public void visit(NumberDecimal e){ 
        try{   
            operands.push( e.getDecimal() );
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public void visit(LiteralCharacter e){ 
        try{   
            operands.push( e.getLiteralCharacter() );
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }
    
    public void visit(Null e){ 
        try{   
            operands.push( e.getValue() );
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public void visit(Print e){
        try{
            e.getPrint().accept(this);
            System.out.println(operands.pop().toString());
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public void visit(Read e){
        try{
            LValue v = e.getRead();
            System.out.println(operands.pop().toString());
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public void visit(If e){
        try{
            e.getExp().accept(this);
            if((Boolean)operands.pop()){
                e.getCmd().accept(this);
            }else if(e.getElseCmd() != null){
                e.getElseCmd().accept(this);
            }
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public void visit(Iterate e){
        try{
            e.getE().accept(this);
            float num = (float) operands.pop();
            for (int i = 0; i < num; i++) {
                e.getCmd().accept(this);
            }
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public void visit(CmdBrace e){
        try{
            for (Cmd c : e.getCmds()) {
                if (retMode) return;
                c.accept(this);
            }
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    } 
    
    public void visit(Return e){
        for(Expr ex : e.getReturn()){
            ex.accept(this);
        }
        retMode = true;   
    }
    
    public void visit(TyInt t) { }

    public void visit(TyFloat t) { }
    
    public void visit(TyChar t) { }

    public void visit(TyBool t) { }

    public void visit(UserType t) { }

    /**
    
    
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

    **/
}
