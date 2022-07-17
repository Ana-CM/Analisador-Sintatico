package visitors;

import ast.*;

public class CountVisitor extends Visitor {
     
     private int n;
     public CountVisitor(){
        n = 0;
     }
     
     public int getNumAttr(){return n;}
     
     public void visit(Program p){
         for(Func f : p.getFuncs()){
             System.out.println("Contantodo em " + f.getID());
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
     
     public void visit(Attr e){
        n++;
     }
     
     public void visit(If e){
         e.getThen().accept(this);
         if(e.getElse() != null){
            e.getElse().accept(this);
         }
     }
     
     public void visit(While e){
         e.getBody().accept(this);
     }
     
     public void visit(Print e){}
     
     public void visit(StmtList e){
          e.getCmd1().accept(this);
          e.getCmd2().accept(this);
     }
     
     public void visit(Func f){
         f.getBody().accept(this);
     }
     
     public void visit(Inst e){}
     public void visit(Return e){}
     public void visit(Param e){}
     
     public void visit(TyInt t){}
     public void visit(TyFloat t){}
     public void visit(TyBool t){}
     public void visit(TyArr t){}
}
