/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package lang.parser;

import lang.TreeNode;


// Adaptador para classe de parser. a Função parseFile deve retornar null caso o parser resulte em erro. 

public interface ParseAdaptor{
   public abstract TreeNode parseFile(String path);
}



