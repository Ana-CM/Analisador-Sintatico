package ast;

import ast.cmd.Cmd;
import lang.TreeNode;

/**
 * Identifier LPar Params? RPar  ( Colon Type ( Comma Type)* )? LBrace Cmd* RBrace
 */
public class Func extends TreeNode{

    private String id;
    private Params params;
    private Type[] type;
    private Cmd[] cmd;

    public Func(String id, Params params, Type[] type, Cmd[]  cmd) {
        this.id = id;
        this.params = params;
        this.type = type;
        this.cmd = cmd;
    }

    public Func(String id, Params params, Type[] type) {
        this.id = id;
        this.params = params;
        this.type = type;
        this.cmd = null;
    }

    public Func (String id, Type[] type, Cmd[]  cmd) {
        this.id = id;
        this.params = null;
        this.type = type;
        this.cmd = cmd;
    }

    public Func(String id, Params params, Cmd[]  cmd) {
        this.id = id;
        this.params = params;
        this.type = null;
        this.cmd = cmd;
    }

    public Func(String id, Type[] type) {
        this.id = id;
        this.params = null;
        this.type = type;
        this.cmd = null;
    }

    public Func(String id, Cmd[]  cmd) {
        this.id = id;
        this.params = null;
        this.type = null;
        this.cmd = cmd;
    }

    public Func(String id, Params params) {
        this.id = id;
        this.params = params;
        this.type = null;
        this.cmd = null;
    }

    public Func(String id ) {
        this.id = id;
        this.params = null;
        this.type = null;
        this.cmd = null;
    }

    public String getId() {
        return id;
    }

    public Params getParams() {
        return params;
    }

    public Type[] getType() {
        return type;
    }

    public Cmd[] getCmd() {
        return cmd;
    }
    
    public String toString() {
       
        String s = id + "(";

        if(params != null){
            s += params.toString();
        }

        s += ")";

        if(type != null){
            s += ":";
            for(Type t : type){
                s += t.toString();
                s += ",";
            }
            s = s.substring(0, s.length() - 1);
        }

        s += "{";

        if(cmd != null){
            for(Cmd c : cmd){
                s += c.toString();
                s += ";";
            }
        }

        s += "}";

        return s;
    }
}
  