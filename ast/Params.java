package ast;

import lang.TreeNode;

import java.util.List;
import java.util.Map.Entry;

/**
 * Identifier ColonColon Type (Comma Identifier ColonColon Type)*
 */
public class Params extends TreeNode{

    private List<Entry<String, Type>> p;

    public Params(List<Entry<String, Type>> p) {
        this.p = p;
    }

    public String toString() {
        String s = "";
        for (Entry<String, Type> id : p) {
            s += id.getKey();
            s += "::";
            s += id.getValue().toString();
            s += ",";
        }
        s = s.substring(0, s.length() - 1);
        return s;
    }
}
