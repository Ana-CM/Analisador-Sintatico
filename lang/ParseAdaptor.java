package lang;

import lang.TreeNode;


// Adaptador para classe de parser. a Função parseFile deve retornar null caso o parser resulte em erro. 

public interface ParseAdaptor{
   public abstract TreeNode parseFile(String path);
}



