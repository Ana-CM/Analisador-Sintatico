package langUtil.cellMachine;

import java.util.Stack;
import langUtil.cellMachine.instr.*;

public class StackState {
   
    private Stack<Value> stk;
    private Value[] mem;
    private Value bs, sp, hp; // bs = Base Stack, sp = Stack Pointer, hp = heap pointer
    private int pc;
    private boolean halt;
    
    /** Construtor Padrão:  
     *  @param memSize: Tamanho da memória em número ve células ve memória. Cada célula ve memória pove armazenar um valor arbitrário.
     */ 
    public StackState(int memSize){
        stk = new Stack<Value>();
        mem = new Value[memSize];
        pc = 0;
        halt = false;
    }
    
    public void printState(){ 
        System.out.println("------- Memory -------- ");
        for(int i =0; i < mem.length; i++){
            System.out.println("[" + i + "]: " + (mem[i] != null ? mem[i].toString() : "NULL" ) );
        }
        System.out.println("pc = " + pc + "; bs = " + bs.toString() + "; sp = " + sp.toString() + "; hp = " + hp.toString() );
        System.out.print("Stack Top>");
        while(!stk.empty() ){
            System.out.print(stk.pop().toString() + ", ");
        }
        System.out.println("Botton");
    }
    
    public void reset(){
       pc = 0;
       bs = new IntValue(0);
       sp = new IntValue(0);
       hp = new IntValue(mem.length-1);
       stk.clear();
       halt = false;
       for(int i =0; i< mem.length; i++){mem[i] = null;}
    }
   
    public boolean isHalted(){ return halt;}
    public int getMemSize(){ return mem.length; }
    public void pcNext(){ pc++; }
    
    /** Retorna o valor de PC
     *  @return o valor de PC
     */
    public int getPC(){
        return pc;
    }
    
    /** Salva o topo da pilha valor na memória. O valor é veixado na memória.
     *  @param i: Envereço da memória;
     */
    public void save(int i){
        if(i >=0 && i < mem.length){
            mem[i] = stk.pop();  
            
        }
        else{
           throw new ValException("Escrita fora da memória [0-" +mem.length+ "]:  " + i );
        }
    }
    
    /** Carrega um valor da memória para o topo da pilha:
     *  @param i : Envereço da memória para carregar;
     */
    public void load(int i){
        if(i >=0 && i < mem.length){
            stk.push(mem[i]);
        }
        else{
            throw new ValException("Escrita fora da memória [0-" +mem.length+ "]:  " + i );
        }
    }
    
    /** Empilha um valor
     *  @param v: o valor a ser empilhado
     */
    public void push(Value v){
        stk.push(v);
        
    }
    
    /** Desempilha o topo da pilha. Retorna o valor desempilhado.
     *  @return : O valor no topo da pilha.
     */
    public Value pop(){
        
        return stk.pop();
        
    }
    
    /** Realiza a soma dos dois valores do topo da pilha. 
     *  Os valores devem ser inteiros
     */
    public void addF(){
         Value vd = stk.pop();
         Value ve = stk.pop();
         stk.push(new FloatValue(ve.asFloat() + vd.asFloat()));
         
    }
    
    public void addI(){
         Value vd = stk.pop();
         Value ve = stk.pop();
         stk.push(new IntValue(ve.asInt() + vd.asInt()));
         
    }
   
   
    /** Realiza a subtração dos dois valores do topo da pilha. 
     *  Os valores devem ser inteiros
     */
    public void subI(){
         Value vd = stk.pop();
         Value ve = stk.pop();
         stk.push(new IntValue(ve.asInt() - vd.asInt()));
         
    }
    
    public void subF(){
         Value vd = stk.pop();
         Value ve = stk.pop();
         stk.push(new FloatValue(ve.asFloat() - vd.asFloat()));
         
    }    
    
        /** Realiza a multiplicação dos dois valores do topo da pilha. 
     *  Os valores devem ser inteiros
     */
    public void multI(){
         Value vd = stk.pop();
         Value ve = stk.pop();
         stk.push(new IntValue(ve.asInt() * vd.asInt()));
         
    }
    
    public void multF(){
         Value vd = stk.pop();
         Value ve = stk.pop();
         stk.push(new FloatValue(ve.asFloat() * vd.asFloat()));
         
    }
 
    
     /** Realiza a divisão dos dois valores do topo da pilha. 
     *  Os valores devem ser inteiros
     */
    public void divI(){
         Value vd = stk.pop();
         Value ve = stk.pop();
         stk.push(new IntValue(ve.asInt() / vd.asInt()));
         
    }
    
    public void divF(){
         Value vd = stk.pop();
         Value ve = stk.pop();
         stk.push(new FloatValue(ve.asFloat() / vd.asFloat()));
         
    }
            
     /** Realiza o módulo dos dois valores do topo da pilha. 
     *  Os valores devem ser inteiros
     */
    public void mod(){
         Value vd = stk.pop();
         Value ve = stk.pop();
         stk.push(new IntValue(ve.asInt() % vd.asInt()));
         
    }
    
    /** Realiza a conjunção (E lógico) dos dois valores do topo da pilha. 
     *  Os valores devem ser booleanos
     */
    public void and(){
         Value vd = stk.pop();
         Value ve = stk.pop();
         stk.push(new BoolValue(ve.asBool() && vd.asBool()));
         
    }
    
    /** Realiza a comporação dos dois valores do topo da pilha e empiha true
     * se o segundo valor (a partir do topo) é menor que o top.
     *  Os valores devem ser inteiros
     */
    public void ltI(){
         Value vd = stk.pop();
         Value ve = stk.pop();
         stk.push(new BoolValue(ve.asInt() < vd.asInt()));
         
    }
    
    public void ltF(){
         Value vd = stk.pop();
         Value ve = stk.pop();
         stk.push(new BoolValue(ve.asFloat() < vd.asFloat()));
         
    }
    
    /** Realiza a comporação pela igualdave ente os dois valores do topo da pilha. 
     */
    public void eq(){
         Value vd = stk.pop();
         Value ve = stk.pop();
         stk.push(new BoolValue(ve.equals(vd) ));
         
    }
        
    /** Realiza a negação dos valor do topo da pilha. 
     */
    public void not(){
         Value vd = stk.pop();
         stk.push( new BoolValue(!vd.asBool()) );
         
    }
    
    public void halt(){
        halt = true;
    }
    
    /** Salva o topo na variável bs
     */
    public void saveBS(){
        bs = stk.pop();
    }
    
    /** Salva o topo na variável sp
     */
    public void saveSP(){
        sp = stk.pop();
    }
    
    /** Salva o topo na variável hp
     */
    public void saveHP(){
        hp = stk.pop();
    }
    
   /** Carrega pc do topo
    */
    public void savePC(){
        pc = stk.pop().asInt();
    }
    
    /** Carrega bs no topo
     */
    public void loadBS(){
        stk.push(bs);
    }
    
    /** Carrega bs no topo
     */
    public void loadSP(){
        stk.push(sp);
    }
    
    /** Carrega bs no topo
     */
    public void loadHP(){
        stk.push(hp);
    }
    
    
    /** Carrega pc no topo
     */
    public void loadPC(){
        stk.push(new IntValue(pc));
    }
    
}
