import lang.LANG;
import lang.TreeNode;
import lang.ParseError;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Example {
    public static void main(String[] args) throws ParseError {

        try {
            String string_file = Files.readString(Paths.get(args[0]));
            TreeNode tree = LANG.parse(string_file);
            
            for (TreeNode node : tree.elements) {
            System.out.println(node.offset + ", " + node.text);
        }

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}