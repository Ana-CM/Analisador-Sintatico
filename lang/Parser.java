package lang;

import lang.ParseAdaptor;
import lang.LANG;
import lang.TreeNode;
import lang.ParseError;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Parser implements ParseAdaptor{
    public TreeNode parseFile(String path) {
        try {
            String string_file = Files.readString(Paths.get(path));

            try {
                TreeNode tree = LANG.parse(string_file);
                return tree;
            }
            catch (Exception  p){
                return null;
            }

        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}