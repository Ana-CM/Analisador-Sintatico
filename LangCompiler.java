/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */
import java.io.*;
import lang.parser.ParseAdaptor;
import lang.TreeNode;
import lang.parser.Parser;
import lang.parser.TestParser;
import visitors.*;
import ast.*;

public class LangCompiler{
// Recupera o nome base (sem extensão) de um arquivo.
public static void main(String[] args){
    if( args.length < 1 ){
        System.out.println("Lang compiler v 0.0.1 - Maio de 2020");
        System.out.println("Use java -cp . Lang ação <Caminho para código Fonte> ");
        System.out.println("Ação (uma das seguintes possibilidades): ");
        
        System.out.println(" -bs : Executa uma bateria de testes sintáticos");
        System.out.println(" -bty : Executa uma bateria de testes no sistemas de tipos");
        System.out.println(" -bsm : Executa uma bateria de testes no interpretador");          
    
        System.out.println(" -pp: Pretty print program.");        
        System.out.println(" -tp: Verficar tipos e imprimir o ambiente de tipos");
        System.out.println(" -i : Apenas interpretar");
        
        System.out.println(" -ti: Verificar tipos e depois interpretar");
        System.out.println(" -dti: Verificar tipos, imprimir o ambiente de tipos e depois interpretar");
        System.out.println(" -gvz: Create a dot file. (Feed it to graphviz dot tool to generate graphical representation of the AST)");
        
    }
    try{
    ParseAdaptor langParser = new Parser();
        
        if(args[0].equals("-bs") ){
            System.out.println("Executando bateria de testes sintáticos:");
            TestParser tp = new TestParser(langParser);
            return;
        }if(args[0].equals("-byt") ){
            System.out.println("Executando bateria de testes sintáticos:");
            // TestParser tp = new TestParser(langParser); ;
            return;
        } if(args[0].equals("-bsm") ){
            System.out.println("Executando bateria de testes sintáticos:");
            // TestParser tp = new TestParser(langParser); 
            return;
        }
        if(args[0].equals("-bsm") ){
            System.out.println("Executando bateria de testes sintáticos:");
            // TestParser tp = new TestParser(langParser); 
            return;
        }
        if(args.length != 2){
            System.out.println("Para usar essa opção, especifique um nome de arquivo");
            return; 
        }
        Prog result = (Prog) langParser.parseFile(args[1]);
        if(result == null){
            System.err.println("Aborting due to syntax error(s)");
            System.exit(1);
        }
        else if(args[0].equals("-i") ){
            InterpretVisitor iv = new InterpretVisitor();
            result.accept(iv);
        }
        else if(args[0].equals("-gvz") ){
            GraphVisitor gv = new GraphVisitor();
            result.accept(gv);
            gv.saveToFile("tree.graphviz");
        }
        else if(args[0].equals("-ii") ){
            // iv = new InteractiveInterpreterVisitor();
            // result.accept(iv);
        }
        else if(args[0].equals("-tp") ){
            TypeCheckVisitor iv = new TypeCheckVisitor();
            result.accept(iv);
            if(iv.getNumErrors() > 0){
                iv.printErrors();
            }else{
                System.out.println("typing  ... [ ok ]"); 
            }
        }
        else if(args[0].equals("-pp") ){
            // iv = new PPrint();
            //result.accept(iv);
            //((PPrint)iv).print();
        }
    }catch(Exception e){
        e.printStackTrace();
    }
   }
}
 
