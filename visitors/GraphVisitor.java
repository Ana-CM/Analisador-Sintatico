package visitors;

import ast.*;
import ast.btype.*;
import ast.cmd.*;
import ast.expr.*;
import ast.expr.binop.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Map;
import java.util.Map.Entry;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class GraphVisitor extends Visitor {
     
    private ArrayList<String> gfz;
    private Stack<String> nodes;
    private int nc;
    
    public GraphVisitor(){
        gfz = new ArrayList<String>();
        gfz.add("digraph G {");
        nodes = new Stack<String>();
        nc = 0;
    }
     
    public void saveToFile(String s){
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(s));
            for(String l : gfz){
                out.write(l,0,l.length());
                out.newLine();
            }
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    private void addLine(String s){
        gfz.add("    " + s);
    }
    
    private String node(int t, String label){ 
        String n = "node" + (nc++);
        gfz.add(n + " [ label =\"" + label + "\"] ;");
        return n;
    }
    
    private void edge(int t, String node1, String node2 ){ 
        gfz.add( node1 + " -> " + node2 + ";" ); 
    }
    
    public void visit(Prog p){
        String s = node(0, "Program");

        if ( null != p.getDefs()) {
            for(Definition d : p.getDefs()){
                d.accept(this);
                edge(0,s,nodes.pop());    
            }
        }
        
        for(Func f : p.getFuncs()){
            f.accept(this);
            edge(0,s,nodes.pop());    
        }
        gfz.add("}");
    }

    public void visit(Definition d){
        String n = node(0, "Data");
        d.getType().accept(this);
        edge(0, n, nodes.pop());
        
        for(Decl decl : d.getDeclarations()){
            decl.accept(this);
            edge(0,n,nodes.pop());    
        }
        nodes.push(n);
    }

    public void visit(Decl d){
        String n = node(0, "::");
        String s = node(0, d.getId());
        edge(0,n,s);

        d.getType().accept(this);
        edge(0, n, nodes.pop());

        nodes.push(n);
    }

    public void visit(Params p){
        String n = node(0, "Params");
        
        for(Entry<String, Type> e : p.getP()){
            String s = node(0, e.getKey());
            edge(0,n,s);

            e.getValue().accept(this);
            edge(0,n,nodes.pop());    
        }
        nodes.push(n);
    }

    public void visit(Type t){
        String n = node(0, "Type");

        t.getType().accept(this);
        edge(0, n, nodes.pop());

        String s = node(0, t.getBrack() + "");
        edge(0,n,s);

        nodes.push(n);
    }
 
    public void visit(Plus e){
        e.getLeft().accept(this);
        e.getRight().accept(this);
        String r = nodes.pop();
        String l = nodes.pop();
        String n = node(0,"+");

        edge(0,n,l);
        edge(0,n,r);
        nodes.push(n);
    }
    
    public void visit(Minus e){
        e.getLeft().accept(this);
        e.getRight().accept(this);
        String r = nodes.pop();
        String l = nodes.pop();
        String n = node(0,"-");

        edge(0,n,l);
        edge(0,n,r);
        nodes.push(n);
    }
    
    public void visit(Times e){
        e.getLeft().accept(this);
        e.getRight().accept(this);
        String r = nodes.pop();
        String l = nodes.pop();
        String n = node(0,"*");

        edge(0,n,l);
        edge(0,n,r);
        nodes.push(n);    
    }
    
    public void visit(Div e){
        e.getLeft().accept(this);
        e.getRight().accept(this);
        String r = nodes.pop();
        String l = nodes.pop();
        String n = node(0,"/");

        edge(0,n,l);
        edge(0,n,r);
        nodes.push(n);     
    }
    
    public void visit(Mod e){
        e.getLeft().accept(this);
        e.getRight().accept(this);
        String r = nodes.pop();
        String l = nodes.pop();
        String n = node(0,"%");

        edge(0,n,l);
        edge(0,n,r);
        nodes.push(n);    
    }
    
    public void visit(And e){
        e.getLeft().accept(this);
        e.getRight().accept(this);
        String r = nodes.pop();
        String l = nodes.pop();
        String n = node(0,"&");

        edge(0,n,l);
        edge(0,n,r);
        nodes.push(n);     
    }
    
    public void visit(Lt e){
        e.getLeft().accept(this);
        e.getRight().accept(this);
        String r = nodes.pop();
        String l = nodes.pop();
        String n = node(0,"<");

        edge(0,n,l);
        edge(0,n,r);
        nodes.push(n);     
    }
    
    public void visit(Eq e){
        e.getLeft().accept(this);
        e.getRight().accept(this);
        String r = nodes.pop();
        String l = nodes.pop();
        String n = node(0,"=");

        edge(0,n,l);
        edge(0,n,r);
        nodes.push(n);     
    }
    
    public void visit(Not e){
        e.getExpr().accept(this);
        String r = nodes.pop();
        String n = node(0,"!");

        edge(0,n,r);
        nodes.push(n);     
    }

    public void visit(Negative e){
        e.getExpr().accept(this);
        String r = nodes.pop();
        String n = node(0,"-");

        edge(0,n,r);
        nodes.push(n);     
    }

    public void visit(Dif e){
        e.getLeft().accept(this);
        e.getRight().accept(this);
        String r = nodes.pop();
        String l = nodes.pop();
        String n = node(0,"!=");

        edge(0,n,l);
        edge(0,n,r);
        nodes.push(n);     
    }

    public void visit(True e){  
        nodes.push(node(0,"true")); 
    }
    
    public void visit(False e){  
        nodes.push(node(0,"false")); 
    }

    public void visit(NumberInteger e){   
        nodes.push(node(0,""+e.getNumber_integer())); 
    }

    public void visit(NumberDecimal e){ 
        nodes.push(node(0,""+e.getDecimal())); 
    }

    public void visit(LiteralCharacter e){
        nodes.push(node(0,""+e.getLiteralCharacter())); 
    }

    public void visit(TyInt t){   
        nodes.push(node(0,"Int")); 
    }

    public void visit(TyFloat t){ 
        nodes.push(node(0,"Float")); 
    }

    public void visit(TyBool t){  
        nodes.push(node(0,"Bool")); 
    }

    public void visit(TyChar t){  
        nodes.push(node(0,"Char")); 
    }

    public void visit(UserType t){  
        nodes.push(node(0,"UserType " + t.getName())); 
    }

    public void visit(Null t){
        nodes.push(node(0,"null")); 
    }

    public void visit(Print e){
        String s = node(0,"print");
        e.getPrint().accept(this);
        edge(0,s,nodes.pop());
        nodes.push(s);
    }

    public void visit(New e){
        String s = node(0,"new");
        e.getType().accept(this);
        edge(0,s,nodes.pop());
        nodes.push(s);
    }

    public void visit(Read e){
        String s = node(0,"read");
        e.getRead().accept(this);
        edge(0,s,nodes.pop());
        nodes.push(s);
    }

    public void visit(LValue l){
        String n = node(0, "LValue");
        
        String s = node(0, l.getName());
        edge(0, n, s);

        for(Object id : l.getIdx()){
            if (id instanceof Expr ) {
                ((Expr) id).accept(this);
                edge(0, n, nodes.pop());
            } else if (id instanceof String) {
                String m = node(0, ((String) id));
                edge(0, n, m);
            }

        }
        nodes.push(n);
    }

    public void visit(Attr e){
        e.getLvalue().accept(this);
        e.getExp().accept(this);
        String n = node(0, "<-");
        edge(0,n,nodes.pop());
        edge(0,n,nodes.pop());
        nodes.push(n);
    }

    public void visit(ParExp e){
        String n = node(0, "( )");
        e.getExpr().accept(this);
        edge(0,n,nodes.pop());
        nodes.push(n);
    }

    public void visit(If e){
        String s = node(0,"If"); 
        e.getExp().accept(this);
        edge(0,s,nodes.pop());
        e.getCmd().accept(this);
        edge(0,s,nodes.pop());
        if(e.getElseCmd() != null){
            e.getElseCmd().accept(this);
            edge(0,s,nodes.pop());   
        }
        nodes.push(s);
    }

    public void visit(Iterate e){
        String s = node(0,"Iterate"); 
        e.getE().accept(this);
        edge(0,s,nodes.pop());
        
        e.getCmd().accept(this);
        edge(0,s,nodes.pop());
        nodes.push(s);
    }

    public void visit(Func f){
        String n = node(0,"Func " + f.getId());

        if ( null != f.getParams()) {
            f.getParams().accept(this);
            edge(0,n,nodes.pop());
        }
        
        if ( null != f.getType()) {
            String types = node(0,"Types");
            for(Type t : f.getType()){
                t.accept(this);
                edge(0,types,nodes.pop());
            }
            edge(0,n,types);
        }

        String cmds = node(0,"Cmds");
        for(Cmd c : f.getCmd()){
            c.accept(this);
            edge(0,cmds,nodes.pop());
        }
        edge(0,n,cmds);

        nodes.push(n);
    }

    public void visit(CmdBrace c) {
        String n = node(0,"CmdBrace");
        
        for (Cmd c1 : c.getCmds()) {
            c1.accept(this);
            edge(0,n,nodes.pop());
        }
        nodes.push(n);
    }

    public void visit(CallBrack e){   
        String n = node(0, "Call " + e.getId());
        String params = node(0,"Params");
        if(e.getParams() != null){
            for(Expr ex : e.getParams()){
                ex.accept(this);
                edge(0,params,nodes.pop());
            }
            edge(0,n,params);
        }
        e.getExp().accept(this);
        edge(0,n,nodes.pop());
        nodes.push(n);
    }

    public void visit(CallAttr e){   
        String n = node(0, "Call " + e.getId());
        String params = node(0,"Params");
        if(e.getParams() != null){
            for(Expr ex : e.getParams()){
                ex.accept(this);
                edge(0,params,nodes.pop());
            }
            edge(0,n,params);
        }

        String lvalues = node(0,"LValues");
        if(e.getLvalue() != null){
            for(LValue l : e.getLvalue()){
                l.accept(this);
                edge(0,lvalues,nodes.pop());
            }
            edge(0,n,lvalues);
        }
        nodes.push(n);
    }

    public void visit(Return e){
        String s = node(0,"return");

        for(Expr ex : e.getReturn()){
            ex.accept(this);
            edge(0,s,nodes.pop());
        }
        nodes.push(s);
    }

}
