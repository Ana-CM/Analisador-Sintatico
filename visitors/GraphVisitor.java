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
        for(Definition d : p.getDefs()){
            d.accept(this);
            edge(0,s,nodes.pop());    
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

    public void Type(Type t){
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
        e.getExpr().accept(this);
        nodes.push(nodes.pop());
    }


    //-------

    public void visit(Return e){
        String s = node(0,"@");
        e.getReturn().accept(this);
        edge(0,s,nodes.pop());
        nodes.push(s);
    }

    public void visit(If e){
        String s = node(0,"If"); 
        e.getTeste().accept(this);
        edge(0,s,nodes.pop());
        e.getThen().accept(this);
        edge(0,s,nodes.pop());
        if(e.getElseCmd() != null){
            e.getElse().accept(this);
            edge(0,s,nodes.pop());   
        }
        nodes.push(s);
    }

    public void visit(Var e){    nodes.push(node(0,""+e.toString()));}
    
    public void visit(While e){
        String s = node(0,"If"); 
        e.getTeste().accept(this);
        edge(0,s,nodes.pop());
        
        e.getBody().accept(this);
        edge(0,s,nodes.pop());
        nodes.push(s);
    }
    
    public void visit(Call e){   
        String n = node(0, "Call " + e.getName());
        String args = node(0,"Args");
        if(e.getArgs() != null){
        for(Expr ex : e.getArgs()){
            ex.accept(this);
            edge(0,args,nodes.pop());
        }
        edge(0,n,args);
        }
        nodes.push(n);
    }
    
    public void visit(StmtList e){
        String n = node(0,"StmtList");
        e.getCmd1().accept(this); 
        edge(0,n,nodes.pop());
        
        e.getCmd2().accept(this);
        edge(0,n,nodes.pop());
        nodes.push(n);
    }
    
    public void visit(Func f){
        String n = node(0,"Func " + f.getID());
        f.getTipo().accept(this);
        edge(0,n,nodes.pop());
        
        String prs = node(0,"Params");
        for(Param p : f.getParams()){
            p.accept(this);
            edge(0,prs,nodes.pop());
        }
        edge(0,n,prs);
        f.getBody().accept(this);
        edge(0,n,nodes.pop());
        nodes.push(n);
    }
    
    public void visit(Inst e){
        String n = node(0,"$");
        e.getID().accept(this);
        edge(0,n,nodes.pop());
        
        e.getTipo().accept(this);
        
        e.getSize();
        edge(0,n,nodes.pop());
        nodes.push(n);
    }
    
    public void visit(TyArr t){   
        String s = node(0,"TyArr");
        t.getTyArg().accept(this);
        edge(0,s,nodes.pop());
        nodes.push(s);
    }
}
