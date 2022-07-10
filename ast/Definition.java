package ast;

import lang.TreeNode;

/**
 * Data Type LBrace Declaration+ RBrace
 */
public class Definition extends TreeNode {
 
    private Type type;
    private Decl[] declarations;

    public Definition(Type type, Decl[] declarations) {
        this.type = type;
        this.declarations = declarations;
    }

    public Type getType() {
        return type;
    }

    public Decl[] getDeclarations() {
        return declarations;
    }

    public String toString() {
        String s = "Data ";
        s += type.toString();
        s += " {";
        
        for (int i = 0; i < declarations.length; i++) {
            s += declarations[i].toString();
            s += ",";
        }
        s = s.substring(0, s.length() - 1);

        s += "}";
        return s;
    }  
}
