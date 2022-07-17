import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap; 

import beaver.Symbol;
import beaver.Parser;

import ast.*;
import parsers.*;
import visitors.*;

public class MiniLang{
      public static void main(String[] args){
        HashMap<String,Integer> h = new HashMap<String,Integer>();
        try{
            MiniLangLex input = new MiniLangLex(new FileReader(args[1]));
            MiniLangParser p = new MiniLangParser();
            Node result = (Node) p.parse(input);
            
            if(p.isGood()){
                System.out.println("parsing ... [ ok ]");
                if(args[0].equals("-i") ){
                   InterpretVisitor iv = new InterpretVisitor(true);
                   result.accept(iv);
                }else if(args[0].equals("-dot") ){
                   GraphVisitor gv = new GraphVisitor();
                   result.accept(gv);
                   gv.saveToFile("tree.graphviz");
                }else if(args[0].equals("-ty") ){
                   TypeCheckVisitor ty = new TypeCheckVisitor();
                   result.accept(ty);
                   if(ty.getNumErrors() > 0){
                      ty.printErrors();
                   }else{
                      System.out.println("typing  ... [ ok ]"); 
                   }
                }else if(args[0].equals("-tyi") ){
                   TypeCheckVisitor ty = new TypeCheckVisitor();
                   result.accept(ty);
                   if(ty.getNumErrors() > 0){
                      ty.printErrors();
                   }else{
                      System.out.println("typing  ... [ ok ]"); 
                      InterpretVisitor iv = new InterpretVisitor(true);
                      result.accept(iv);
                   }
                }
            }else{
                System.out.println( " Erros ocorreram durante o parser.\nAbortando");
            }
        }
        catch (IOException e){
            System.err.println("Failed to read expression: " + e.getMessage());
        }
        catch (beaver.Parser.Exception e){
            System.err.println("Invalid expression: " + e.getMessage());
        }
        catch (Exception e){
            System.err.println("Exceção: " + e.getMessage());
            e.printStackTrace();
        } 
     }
}
