package ast.btype;

import lang.TreeNode;

public  abstract class BType extends TreeNode {
    
    public BType(){
    }

    public abstract boolean match(BType t);
}