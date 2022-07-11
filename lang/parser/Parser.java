/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package lang.parser;

import lang.parser.ParseAdaptor;
import lang.LANG;
import lang.TreeNode;
import lang.ParseError;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import ast.AstActions;

public class Parser implements ParseAdaptor{
    public TreeNode parseFile(String path) {
        try {
            String string_file = Files.readString(Paths.get(path));

            try {
                TreeNode tree = LANG.parse(string_file, new AstActions());
                return tree;
            }
            catch (Exception  p){
                System.out.println(p);
                return null;
            }

        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}