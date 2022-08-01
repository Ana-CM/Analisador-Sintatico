package visitors;

import ast.*;

public class FunctionsVisitor extends Visitor {
     
     public FunctionsVisitor(){
     }

     
     public void visit(Program p){
         System.out.println("Funções nesse programa: " );
         for(Func f : p.getFuncs()){
             f.accept(this);
         }
     }
     
     public void visit(Add e){}
     public void visit(Sub e){}
     public void visit(Mul e){}
     public void visit(Div e){}
     public void visit(Mod e){}
     
     public void visit(And e){}
     public void visit(Lt e){}
     public void visit(Eq e){}
     public void visit(Not e){}
     
     public void visit(True e){}
     public void visit(False e){}
     public void visit(NInt e){}
     public void visit(NFloat e){}
     public void visit(Var e){}
     public void visit(Call e){}
     
     public void visit(Attr e){}
     
     public void visit(If e){}
     
     public void visit(While e){}
     
     public void visit(Print e){}
     
     public void visit(StmtList e){}
     
     public void visit(Func f){
        System.out.println(f.getID());
     }
     
     public void visit(Inst e){}
     public void visit(Return e){}
     public void visit(Param e){}
     
     public void visit(TyInt t){}
     public void visit(TyFloat t){}
     public void visit(TyBool t){}
     public void visit(TyArr t){}
}
