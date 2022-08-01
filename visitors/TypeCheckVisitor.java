/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package visitors;

import ast.*;
import ast.btype.*;
import ast.cmd.*;
import ast.expr.*;
import ast.expr.binop.*;
import lang.TreeNode;
import langUtil.*;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Map.Entry;

public class TypeCheckVisitor extends Visitor {

    private STyInt tyint = STyInt.newSTyInt();
    private STyFloat tyfloat = STyFloat.newSTyFloat();
    private STyErr tyerr = STyErr.newSTyErr();
    private STyBool tybool = STyBool.newSTyBool();
    private STyChar tychar = STyChar.newSTyChar();
    private STyNull tynull = STyNull.newSTyNull();
    private STyGeneric tygeneric = STyGeneric.newSTyGeneric();

    private ArrayList<String> logError;

    private TyEnv<LocalEnv<SType>> env;
    private LocalEnv<SType> temp;
    private HashMap<String, STyUserType> data;

    private Stack<SType> stk;

    private boolean retChk;

    public TypeCheckVisitor(){
        stk = new Stack<SType>();
        env = new TyEnv<LocalEnv<SType>>();
        logError = new ArrayList<String>();
        data = new HashMap<String, STyUserType>();
    }

    public int getNumErrors(){ 
        return logError.size(); 
    }
     
    public void printErrors(){ 
        for(String s : logError){
            System.out.println(s);
        }
    }

    // Adicionar checagem de tipos de usuário
    public void visit(Prog p) {

        if (null != p.getDefs()) {
            for (Definition d : p.getDefs()) {
                String name = ((UserType) d.getType().getType()).getName();

                if(!data.containsKey(name)) {
                    HashMap<String,SType> decls = new HashMap<String,SType>();
                    for (Decl decl : d.getDeclarations()) {
                        decl.getType().accept(this);
                        decls.put(decl.getId(), stk.pop());
                    }

                    data.put(name, new STyUserType(name, decls));
                }
                else {
                    logError.add( "(" + d.text + ", at position " + d.offset  +") Duas ou mais definições de tipo com o mesmo identificador.");
                }      
            }
        }

        Boolean main = false;

        for (Func f : p.getFuncs()) {
            if (f.getId().contains("main")) {
                main = true; 

                if (f.getParams() != null && f.getParams().getP().size() != 0)
                    logError.add( "(" + f.text + ", at position " + f.offset  +") função main não deve possuir argumentos.");
            }

            STyFun ty;
            
            int numParams;
            if (f.getParams() != null) 
                numParams = f.getParams().getP().size();
            else
                numParams = 0;

            int numReturns;
            if (f.getType() != null) 
                numReturns = f.getType().length;
            else
                numReturns = 0;

            SType[] xs = new SType[numParams + numReturns];
            
            int i = 0;

            if (f.getParams() != null) {
                for(i = 0; i < f.getParams().getP().size(); i++ ){
                    f.getParams().getP().get(i).getValue().accept(this);
                    xs[i] = stk.pop();
                }
            }

            if (f.getType() != null) {
                for (Type t : f.getType()) {
                    t.accept(this);
                    xs[i] = stk.pop();
                    i++;
                }
            }

            ty = new STyFun(xs, numReturns);
            env.set(f.getId(), new LocalEnv<SType>(f.getId(),ty));
        }

        if (!main)
            logError.add("O programa não possui função main");
        
        for(Func f : p.getFuncs()){
            f.accept(this);
        }
        //env.printTable();  
    }

    public void visit(Definition e){}

    public void visit(Decl e){}

    public void visit(Func f){
        retChk = false;
        temp = env.get(f.getId());

        if (f.getParams() != null) {
            for(Entry<String, Type> p:  f.getParams().getP() ){
                p.getValue().accept(this); 
                temp.set( p.getKey(), stk.pop());
            }
        }
        
        for (Cmd c : f.getCmd())
            c.accept(this);

        STyFun tf = (STyFun)temp.getFuncType();
        if (tf.getNumReturns() == 0)
            retChk = true;

        if (!retChk) {
            logError.add( "(" + f.text + ", at position " + f.offset  +") deve retornar algum valor.");
        }
    }


    private void typeArithmeticBinOp( TreeNode n, String opName) {
        SType tyr = stk.pop();
        SType tyl = stk.pop();

        if((tyr.match(tyint))){
            if(tyl.match(tyint) || tyl.match(tyfloat)){
               stk.push(tyl);
            }else{
                logError.add("(" + n.text + ", at position " + n.offset  +") não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString());
                stk.push(tyerr);
            }
        }else if(tyr.match(tyfloat)){
            if(tyl.match(tyint) || tyl.match(tyfloat)){
                stk.push(tyl);
            }else{
                logError.add("(" + n.text + ", at position " + n.offset  +") não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString());
                stk.push(tyerr);
            } 
        }else{
            logError.add("(" + n.text + ", at position " + n.offset  +") não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString());
            stk.push(tyerr);
        }
    }

    public void visit(Plus e){
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e,"+");
    }

    public void visit(Minus e){
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e,"-");
    }

    public void visit(Times e){
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e,"*");
    }

    public void visit(Div e){
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e,"/");    
    }

    public void visit(Mod e){
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e,"%");  
    }

    public void visit(Negative e){
        e.getExpr().accept(this);
        SType ty = stk.pop();

        if(ty.match(tyint)){
            stk.push(tyint);
        }else if(ty.match(tyfloat)){
            stk.push(tyfloat);
        }else{
            logError.add("(" + e.text + ", at position " + e.offset  +") Operador - não se aplica ao tipo " + ty.toString());
            stk.push(tyerr);
        }
    }

    public void visit(And e){
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if( tyr.match(tybool) && tyl.match(tybool)){
           stk.push(tybool);
        }else{
           logError.add("(" + e.text + ", at position " + e.offset  +") Operador & não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString());
           stk.push(tyerr);
        }
    }

    public void visit(Lt e){
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if((tyr.match(tyint) || tyr.match(tyfloat) ) && (tyl.match(tyint) || tyr.match(tyfloat))){
           stk.push(tybool);
        }else{
           logError.add("(" + e.text + ", at position " + e.offset  +") Operador < não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString());
           stk.push(tyerr);
        }
    }

    public void visit(Not e){
        e.getExpr().accept(this);
        SType tyr = stk.pop();
        if(tyr.match(tybool) ){
           stk.push(tybool);
        }else{
           logError.add("(" + e.text + ", at position " + e.offset  +") Operador ! não se aplica ao tipo " + tyr.toString() );
           stk.push(tyerr);
        }
    }

    public void visit(Eq e){
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if(((tyr.match(tyint) || tyr.match(tyfloat)) && (tyl.match(tyint) || tyr.match(tyfloat))) || (tyr.match(tychar) && tyl.match(tychar)) || (tyr.match(tynull) && tyl.match(tynull))){
           stk.push(tybool);
        }
        else{
           logError.add("(" + e.text + ", at position " + e.offset  +") Operador = não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString() );
           stk.push(tyerr);
        }
    }

    public void visit(Dif e){
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if(((tyr.match(tyint) || tyr.match(tyfloat)) && (tyl.match(tyint) || tyr.match(tyfloat))) || (tyr.match(tychar) && tyl.match(tychar)) || (tyr.match(tynull) && tyl.match(tynull))){
           stk.push(tybool);
        }
        else{
           logError.add("(" + e.text + ", at position " + e.offset  +") Operador != não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString() );
           stk.push(tyerr);
        }
    }
    
    public void visit(True e){  
        stk.push(tybool);
    }

    public void visit(False e){
        stk.push(tybool);  
    }

    public void visit(TyInt t){
        stk.push(tyint);
    }

    public void visit(TyFloat t){ 
        stk.push(tyfloat);
    }

    public void visit(TyBool t){ 
        stk.push(tybool);  
    }

    public void visit(TyChar e){
        stk.push(tychar);
    }

    public void visit(NumberInteger e){
        stk.push(tyint);
    }

    public void visit(NumberDecimal e){
        stk.push(tyfloat);
    }

    public void visit(LiteralCharacter e){
        stk.push(tychar);
    }

    public void visit(Null e) {
        stk.push(tynull);
    }
    
    public void visit(Print e){
        e.getPrint().accept(this);
        stk.pop();
    }

    public void visit(Params e){}
    
    // TODO
    public void visit(Return e){
        for(Expr ex : e.getReturn()){
            ex.accept(this);
        }

        if(temp.getFuncType() instanceof STyFun){
            SType[] t = ((STyFun)temp.getFuncType()).getTypes();
            int numReturns = ((STyFun)temp.getFuncType()).getNumReturns();
            for (int i = 0; i < numReturns ; i++) {
                t[t.length - 1 - i].match(stk.pop());
            }
        }else{
           stk.pop().match(temp.getFuncType());
        }

        retChk = true;
    }

    public void visit(If e){
        boolean rt, re;
        re = true;
        e.getExp().accept(this);

        if(stk.pop().match(tybool)){
            retChk = false;
            rt = retChk;
            if(e.getElseCmd() != null){
               retChk = false;
               e.getElseCmd().accept(this);
               re = retChk;
            }
            retChk = rt && re;
        }else{
           logError.add("(" + e.text + ", at position " + e.offset  +") Expressão do IF deve ter tipo Bool");
        }
    }

    public void visit(Iterate e){
        e.getE().accept(this);
        if(stk.pop().match(tyint)){
            e.getCmd().accept(this);
        }else{
           logError.add("(" + e.text + ", at position " + e.offset  +") Expressão do Iterate deve ter tipo inteiro");
        }
    }

    public void visit(CmdBrace e){
        for (Cmd c : e.getCmds()) {
            c.accept(this);
        }
    }

    public void visit(LValue e){
        SType t = temp.get(e.getName());
        
        if(t != null){
            if(e.getIdx() != null){
                for(int i = 0; i < e.getIdx().size(); i++){
                    Object o = e.getIdx().get(i);
                    if (o instanceof Expr) {
                        if(t instanceof STyArr){
                            t = ((STyArr) t).getArg();
                        } else{
                            t = tyerr;
                        }
                    }
                    else if (o instanceof String) {
                       if(t instanceof STyUserType){
                            if (data.containsKey(((STyUserType)t).getName())) {
                                STyUserType ut = data.get(((STyUserType)t).getName());
                                
                                if(ut.getdecls().containsKey(o)) {
                                    t = ut.getdecls().get(o);
                                } else {
                                    t = tyerr;
                                }
                            } else {
                                t = tyerr;
                            }
                        } else {
                            t = tyerr;
                        }
                    } else {
                        t = tyerr;
                    }
                }
                if(t == tyerr){
                    logError.add( "(" + e.text + ", at position " + e.offset  +") Atribuição de tipos incompatíveis " + e.getName() );
                } 
            }
            stk.push(t);
        }else{
            logError.add( "(" + e.text + ", at position " + e.offset  +") Variável não declarada " + e.getName() );
        }
    }

    public void visit (Type e){
        e.getType().accept(this);
        SType t = stk.pop();

        if (t == tyerr ) {
            logError.add( "(" + e.text + ", at position " + e.offset  +") Tipo não declarado ");
            stk.push(tyerr);
        }
        else if (e.getBrack() > 0) {    
            stk.push(new STyArr(stk.pop()) );
        }
        else {
            stk.push(t);
        }
    }

    public void visit(ParExp e) {
        e.getExpr().accept(this);
    }

    public void visit(Attr e) {

        if( temp.get(e.getLvalue().getName()) == null && (e.getLvalue().getIdx().size() == 0) ) {
            e.getExp().accept(this);
            temp.set(e.getLvalue().getName(),stk.pop());
        } else {
            e.getLvalue().accept(this);
            e.getExp().accept(this);
            if(! stk.pop().match( stk.pop())){
                logError.add("(" + e.text + ", at position " + e.offset  +") Atribuição ilegal para a variável " + e.getLvalue());
            }
        }
    }

    public void visit(Read e) {}
    
    public void visit(UserType e) { 
        if (data.get(e.getName()) != null) {
            stk.push(new STyUserType(e.getName(), data.get(e.getName()).getdecls()));
        } else {
            logError.add("(" + e.text + ", at position " + e.offset  +") Definição do tipo  " + e.getName() + "não encontrada");
        }
    }

    public void visit(New e) {
        if (e.getExp() != null) {
            e.getExp().accept(this);
            if(stk.pop().match(tyint) ){
                e.getType().accept(this);
                stk.push(new STyArr(stk.pop()));
            }
        }
        else {
            e.getType().accept(this);
            stk.push(stk.pop());
        }
    }
    
    public void visit(CallBrack e) {
        LocalEnv<SType> le = env.get(e.getId());
        if (le != null) {
            STyFun tf = (STyFun)le.getFuncType();
            if(e.getParams().length == tf.getTypes().length - tf.getNumReturns()) {     
                int k = 0;
                boolean r = true;
                for(Expr x: e.getParams() ){
                    x.accept(this);
                    if(!tf.getTypes()[k].match(stk.pop())){
                        logError.add( "(" + x.text + ", at position " + x.offset  +"): " + (k+1) + "\u00BA argumento incompatível com o respectivo parâmetro de " + e.getId() );
                    }
                    k++;
                }

                e.getExp().accept(this);
                if (stk.pop().match(tyint)) {
                    stk.push(tygeneric);
                } else{
                    logError.add("(" + e.text + ", at position " + e.offset  +") Chamada da função " + e.getId() + " tem expressão inválida " );
                    stk.push(tyerr);
                }
                
            } else {
                logError.add("(" + e.text + ", at position " + e.offset  +") Chamada da função " + e.getId() + " incompatível com argumentos. " );
                stk.push(tyerr);
            }
        }else{
            logError.add("(" + e.text + ", at position " + e.offset  +") Chamada a função não declarada: " + e.getId() );
            stk.push(tyerr);
        }
    }

    public void visit(CallAttr e) {
        LocalEnv<SType> le = env.get(e.getId());
        if (le != null) {
            STyFun tf = (STyFun)le.getFuncType();
            if(e.getParams().length == tf.getTypes().length - tf.getNumReturns()) {     
                int k = 0;
                boolean r = true;
                for(Expr x: e.getParams() ){
                    x.accept(this);
                    if(!tf.getTypes()[k].match(stk.pop())){
                        logError.add( "(" + x.text + ", at position " + x.offset  +"): " + (k+1) + "\u00BA argumento incompatível com o respectivo parâmetro de " + e.getId() );
                    }
                    k++;
                }
                k = 0;
                if (e.getLvalue().length == tf.getNumReturns()) {
                    int numParams = tf.getTypes().length - tf.getNumReturns();
                    for (LValue v : e.getLvalue()) {
                        v.accept(this);
                        if(!tf.getTypes()[k + numParams].match(stk.pop())){
                            logError.add( "(" + v.text + ", at position " + v.offset  +"): " + (k+1) + "\u00BA variavel incompatível com o respectivo retorno de " + e.getId() );
                        }
                        k++;
                    }
                } else {
                    logError.add("(" + e.text + ", at position " + e.offset  +") Chamada da função " + e.getId() + " tem número de retornos invalido" );
                }
                
            } else {
                logError.add("(" + e.text + ", at position " + e.offset  +") Chamada da função " + e.getId() + " incompatível com argumentos. " );
                stk.push(tyerr);
            }
        }else{
            logError.add("(" + e.text + ", at position " + e.offset  +") Chamada a função não declarada: " + e.getId() );
            stk.push(tyerr);
        }
    }
}
