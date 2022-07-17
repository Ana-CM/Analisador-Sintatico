package visitors;

import ast.*;
import ast.btype.*;
import ast.cmd.*;
import ast.expr.*;
import ast.expr.binop.*;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;

public class  InterpretVisitor extends Visitor {

    private Stack<HashMap<String,Object>> env;     
    private HashMap<String,Func> funcs;
    private HashMap<String,Definition> data;       
    private Stack<Object> operands;
    private boolean retMode, debug;
    
    
    public InterpretVisitor(){
        env = new Stack<HashMap<String,Object>>();
        env.push(new HashMap<String,Object>());
        funcs = new HashMap<String,Func>();
        data = new HashMap<String, Definition>();
        operands = new Stack<Object>();
        retMode = false;
        debug = false;
    }
    
    public InterpretVisitor(boolean debug){
        this();
        this.debug = debug;
    }

    public void visit(Prog p){
        Func main = null;
        
        if (null != p.getDefs()) {
            for(Definition d : p.getDefs()){
                UserType ut = (UserType) d.getType().getType();
                data.put(ut.getName(),d);
            }
        }
        
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

    public void visit(Definition e){}

    public void visit(Decl e){}

    public void visit(Func f){
        HashMap<String,Object> localEnv = new HashMap<String,Object>();

        if (null != f.getParams()) {
            for(int i = f.getParams().getP().size()-1; i >= 0; i--){
                localEnv.put(f.getParams().getP().get(i).getKey(),operands.pop());
            }
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
        retMode = false;
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

    public void visit(New e){ 
        try{
            Object val;
            Type t = e.getType();

            if (null != e.getExp()) {
                e.getExp().accept(this);
                Integer size = (Integer)operands.pop();
                val = new ArrayList(size);

                if (t.getType() instanceof UserType) {
                    Definition def = data.get(((UserType)t.getType()).getName());

                    for(int i = 0; i< size; i++){ 
                        HashMap<String, Object> map = new HashMap<String, Object>();

                        for(Decl decl : def.getDeclarations()){
                            map.put(decl.getId(), null);
                        }
                        ((ArrayList)val).add(map);

                    }
                } else {
                    for(int i = 0; i< size; i++){ 
                        ((ArrayList)val).add(null);
                    }
                }
            } 
            else if(t.getType() instanceof UserType){
                Definition def = data.get(((UserType)t.getType()).getName());
                HashMap<String, Object> map = new HashMap<String, Object>();

                for(Decl decl : def.getDeclarations()){
                    map.put(decl.getId(), null);
                }
                val = map;
            }
            else {
                val = new Object();
            }

            operands.push(val);

        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public  void visit(Attr e){
        try{   
            LValue v = e.getLvalue();
            e.getExp().accept(this);
            Object val = operands.pop();
            Object o;

            if(v.getIdx() != null && v.getIdx().size() > 0 ){
                Object r = env.peek().get(v.getName());

                for(int k = 0; k < v.getIdx().size()-1; k++ ){
                    o = v.getIdx().get(k);
                    if (o instanceof Expr) {
                        ((Expr)o).accept(this);
                        r = ((ArrayList)r).get( (Integer)operands.pop());
                    }
                    else if (o instanceof String) {
                        r = ((HashMap)r).get(o);
                    }
                }

                o = v.getIdx().get(v.getIdx().size()-1);
                if (o instanceof Expr) {
                    ((Expr)v.getIdx().get(v.getIdx().size()-1)).accept(this);
                    ((ArrayList)r).set( (Integer)operands.pop(), val);
                }
                else if (o instanceof String) {
                    if (!((HashMap)r).containsKey(o)) {
                        throw new RuntimeException( "Atributo " + o + " não declarado");
                    }
                    ((HashMap)r).put(o, val);
                }
            }
            else{ env.peek().put(e.getLvalue().getName(), val);}
        
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public  void visit(CallBrack e){
        try{
            e.getExp().accept(this);
            int pos = (int)operands.pop();
            Func f = funcs.get(e.getId());
            Object result = null;

            if(f != null){
                for(Expr exp : e.getParams()){
                    exp.accept(this);
                }
                f.accept(this);
                
                int size = (int) operands.pop();
                for (int i = size - 1; i >= 0; i--) {
                    if (pos == i) 
                        result = operands.pop();
                    else
                        operands.pop();
                }

                if (null != result) {
                    operands.push(result);
                } else{
                    throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + "posição " + pos + " é invalida");
                }
            }else{
                throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") Função não definida " +  e.getId());
            }
            
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public  void visit(CallAttr e){
        try{
            LValue[] vs = e.getLvalue();
            Func f = funcs.get(e.getId());

            if(f != null){
                for(Expr exp : e.getParams()){
                    exp.accept(this);
                }
                f.accept(this);
                
                operands.pop();
                for (int i = vs.length - 1; i >= 0; i--) {
                    Object exp = operands.pop();
                    
                    if (exp instanceof Integer) {
                        exp = new NumberInteger((int)exp);
                    }
                    else if (exp instanceof Float) {
                        exp = new NumberDecimal((float)exp);
                    }
                    else if (exp instanceof Boolean && (boolean)exp == true) {
                        exp = new True();
                    }
                    else if (exp instanceof Boolean && (boolean)exp == false) {
                        exp = new False();
                    }
                    else if (exp instanceof Character) {
                        exp = new LiteralCharacter((char)exp);
                    }

                    Attr a = new Attr(vs[i], (Expr)exp);
                    a.accept(this);
                }
            }else{
                throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") Função não definida " +  e.getId());
            }
            
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

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
            Number esq,dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop(); 
            if(esq instanceof Float || dir instanceof Float){
                operands.push(esq.floatValue() < dir.floatValue());
            }
            else {
               operands.push(esq.intValue() < dir.intValue());
            }
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
            System.out.print(operands.pop().toString());
        }catch(Exception x){
            throw new RuntimeException( " (" + e.text + ", at position " + e.offset  + ") " + x.getMessage() );
        }
    }

    public void visit(Read e){
        try{
            LValue v = e.getRead();
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            Object o;

            if(v.getIdx() != null && v.getIdx().size() > 0 ){
                Object r = env.peek().get(v.getName());

                for(int k = 0; k < v.getIdx().size()-1; k++ ){
                    o = v.getIdx().get(k);
                    if (o instanceof Expr) {
                        ((Expr)o).accept(this);
                        r = ((ArrayList)r).get( (Integer)operands.pop());
                    }
                    else if (o instanceof String) {
                        r = ((HashMap)r).get(o);
                    }
                }

                o = v.getIdx().get(v.getIdx().size()-1);
                if (o instanceof Expr) {
                    ((Expr)v.getIdx().get(v.getIdx().size()-1)).accept(this);
                    ((ArrayList)r).set( (Integer)operands.pop(), input);
                }
                else if (o instanceof String) {
                    if (!((HashMap)r).containsKey(o)) {
                        throw new RuntimeException( "Atributo " + o + " não declarado ");
                    }
                    ((HashMap)r).put(o, input);
                }
            }
            else{ env.peek().put(v.getName(), input);}

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
            int num = (int) operands.pop();
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
        int i = 0;
        for(Expr ex : e.getReturn()){
            ex.accept(this);
            i++;
        }
        operands.push(i);
        retMode = true;   
    }
    
    public void visit(TyInt t) { }

    public void visit(TyFloat t) { }
    
    public void visit(TyChar t) { }

    public void visit(TyBool t) { }

    public void visit(UserType t) { }

}
