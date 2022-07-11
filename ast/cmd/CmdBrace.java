/**
 * Ana Carolina Mendes Lino -  201865212AC 
 * Thiago Silva Miranda - 201865553C
 */

package ast.cmd;
import visitors.Visitor;


/*
 * LBrace Cmd+ RBrace 
 */
public class CmdBrace extends Cmd {
    
    private Cmd[] cmds;

    public CmdBrace(Cmd[] cmds){
        this.cmds = cmds;
    }

    //@Override
    public String toString(){

        String s = "{";
        for(Cmd c : cmds){
            s += c.toString();
        }
        s += "}";
        return s;
    }

    public void accept(Visitor v){ v.visit(this);}
}
