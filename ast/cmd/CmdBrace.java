package ast.cmd;

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
}
