/**
 * This file was generated from lang.peg
 * See https://canopy.jcoglan.com/ for documentation
 */

package lang;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TreeNode implements Iterable<TreeNode> {
    public String text;
    public int offset;
    public List<TreeNode> elements;

    Map<Label, TreeNode> labelled;

    public TreeNode() {
        this("", -1, new ArrayList<TreeNode>(0));
    }

    public TreeNode(String text, int offset, List<TreeNode> elements) {
        this.text = text;
        this.offset = offset;
        this.elements = elements;
        this.labelled = new EnumMap<Label, TreeNode>(Label.class);
    }

    public TreeNode get(Label key) {
        return labelled.get(key);
    }

    public Iterator<TreeNode> iterator() {
        return elements.iterator();
    }
}

class TreeNode1 extends TreeNode {
    TreeNode1(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(0));
        labelled.put(Label.EndOfFile, elements.get(3));
    }
}

class TreeNode2 extends TreeNode {
    TreeNode2(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Data, elements.get(0));
        labelled.put(Label.Type, elements.get(1));
        labelled.put(Label.LBrace, elements.get(2));
        labelled.put(Label.RBrace, elements.get(4));
    }
}

class TreeNode3 extends TreeNode {
    TreeNode3(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Identifier, elements.get(0));
        labelled.put(Label.ColonColon, elements.get(1));
        labelled.put(Label.Type, elements.get(2));
        labelled.put(Label.Semi, elements.get(3));
    }
}

class TreeNode4 extends TreeNode {
    TreeNode4(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Identifier, elements.get(0));
        labelled.put(Label.LPar, elements.get(1));
        labelled.put(Label.RPar, elements.get(3));
        labelled.put(Label.LBrace, elements.get(5));
        labelled.put(Label.RBrace, elements.get(7));
    }
}

class TreeNode5 extends TreeNode {
    TreeNode5(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Colon, elements.get(0));
        labelled.put(Label.Type, elements.get(1));
    }
}

class TreeNode6 extends TreeNode {
    TreeNode6(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Comma, elements.get(0));
        labelled.put(Label.Type, elements.get(1));
    }
}

class TreeNode7 extends TreeNode {
    TreeNode7(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Identifier, elements.get(0));
        labelled.put(Label.ColonColon, elements.get(1));
        labelled.put(Label.Type, elements.get(2));
    }
}

class TreeNode8 extends TreeNode {
    TreeNode8(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Comma, elements.get(0));
        labelled.put(Label.Identifier, elements.get(1));
        labelled.put(Label.ColonColon, elements.get(2));
        labelled.put(Label.Type, elements.get(3));
    }
}

class TreeNode9 extends TreeNode {
    TreeNode9(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Btype, elements.get(0));
    }
}

class TreeNode10 extends TreeNode {
    TreeNode10(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.LBrack, elements.get(0));
        labelled.put(Label.RBrack, elements.get(1));
    }
}

class TreeNode11 extends TreeNode {
    TreeNode11(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.LBrace, elements.get(0));
        labelled.put(Label.RBrace, elements.get(2));
    }
}

class TreeNode12 extends TreeNode {
    TreeNode12(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.If, elements.get(0));
        labelled.put(Label.LPar, elements.get(1));
        labelled.put(Label.Exp, elements.get(2));
        labelled.put(Label.RPar, elements.get(3));
        labelled.put(Label.Cmd, elements.get(4));
    }
}

class TreeNode13 extends TreeNode {
    TreeNode13(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Else, elements.get(0));
        labelled.put(Label.Cmd, elements.get(1));
    }
}

class TreeNode14 extends TreeNode {
    TreeNode14(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Iterate, elements.get(0));
        labelled.put(Label.LPar, elements.get(1));
        labelled.put(Label.Exp, elements.get(2));
        labelled.put(Label.RPar, elements.get(3));
        labelled.put(Label.Cmd, elements.get(4));
    }
}

class TreeNode15 extends TreeNode {
    TreeNode15(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Read, elements.get(0));
        labelled.put(Label.LValue, elements.get(1));
        labelled.put(Label.Semi, elements.get(2));
    }
}

class TreeNode16 extends TreeNode {
    TreeNode16(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Print, elements.get(0));
        labelled.put(Label.Exp, elements.get(1));
        labelled.put(Label.Semi, elements.get(2));
    }
}

class TreeNode17 extends TreeNode {
    TreeNode17(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Return, elements.get(0));
        labelled.put(Label.Exp, elements.get(1));
        labelled.put(Label.Semi, elements.get(3));
    }
}

class TreeNode18 extends TreeNode {
    TreeNode18(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Comma, elements.get(0));
        labelled.put(Label.Exp, elements.get(1));
    }
}

class TreeNode19 extends TreeNode {
    TreeNode19(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.LValue, elements.get(0));
        labelled.put(Label.Eq, elements.get(1));
        labelled.put(Label.Exp, elements.get(2));
        labelled.put(Label.Semi, elements.get(3));
    }
}

class TreeNode20 extends TreeNode {
    TreeNode20(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Identifier, elements.get(0));
        labelled.put(Label.LPar, elements.get(1));
        labelled.put(Label.RPar, elements.get(3));
        labelled.put(Label.Semi, elements.get(5));
    }
}

class TreeNode21 extends TreeNode {
    TreeNode21(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Lt, elements.get(0));
        labelled.put(Label.LValue, elements.get(1));
        labelled.put(Label.Gt, elements.get(3));
    }
}

class TreeNode22 extends TreeNode {
    TreeNode22(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Comma, elements.get(0));
        labelled.put(Label.LValue, elements.get(1));
    }
}

class TreeNode23 extends TreeNode {
    TreeNode23(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Rexp, elements.get(0));
    }
}

class TreeNode24 extends TreeNode {
    TreeNode24(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.And, elements.get(0));
        labelled.put(Label.Rexp, elements.get(1));
    }
}

class TreeNode25 extends TreeNode {
    TreeNode25(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Aexp, elements.get(2));
        labelled.put(Label.Lt, elements.get(1));
    }
}

class TreeNode26 extends TreeNode {
    TreeNode26(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Aexp, elements.get(0));
    }
}

class TreeNode27 extends TreeNode {
    TreeNode27(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Aexp, elements.get(1));
    }
}

class TreeNode28 extends TreeNode {
    TreeNode28(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Mexp, elements.get(0));
    }
}

class TreeNode29 extends TreeNode {
    TreeNode29(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Mexp, elements.get(1));
    }
}

class TreeNode30 extends TreeNode {
    TreeNode30(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Sexp, elements.get(0));
    }
}

class TreeNode31 extends TreeNode {
    TreeNode31(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Sexp, elements.get(1));
    }
}

class TreeNode32 extends TreeNode {
    TreeNode32(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Not, elements.get(0));
        labelled.put(Label.Sexp, elements.get(1));
    }
}

class TreeNode33 extends TreeNode {
    TreeNode33(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Minus, elements.get(0));
        labelled.put(Label.Sexp, elements.get(1));
    }
}

class TreeNode34 extends TreeNode {
    TreeNode34(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.New, elements.get(0));
        labelled.put(Label.Type, elements.get(1));
    }
}

class TreeNode35 extends TreeNode {
    TreeNode35(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.LBrack, elements.get(0));
        labelled.put(Label.Exp, elements.get(1));
        labelled.put(Label.RBrack, elements.get(2));
    }
}

class TreeNode36 extends TreeNode {
    TreeNode36(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Identifier, elements.get(0));
        labelled.put(Label.LPar, elements.get(1));
        labelled.put(Label.RPar, elements.get(3));
        labelled.put(Label.LBrack, elements.get(4));
        labelled.put(Label.Exp, elements.get(5));
        labelled.put(Label.RBrack, elements.get(6));
    }
}

class TreeNode37 extends TreeNode {
    TreeNode37(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.LPar, elements.get(0));
        labelled.put(Label.Exp, elements.get(1));
        labelled.put(Label.RPar, elements.get(2));
    }
}

class TreeNode38 extends TreeNode {
    TreeNode38(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Identifier, elements.get(0));
    }
}

class TreeNode39 extends TreeNode {
    TreeNode39(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.LBrack, elements.get(0));
        labelled.put(Label.Exp, elements.get(1));
        labelled.put(Label.RBrack, elements.get(2));
    }
}

class TreeNode40 extends TreeNode {
    TreeNode40(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Dot, elements.get(0));
        labelled.put(Label.Identifier, elements.get(1));
    }
}

class TreeNode41 extends TreeNode {
    TreeNode41(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Exp, elements.get(0));
    }
}

class TreeNode42 extends TreeNode {
    TreeNode42(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Comma, elements.get(0));
        labelled.put(Label.Exp, elements.get(1));
    }
}

class TreeNode43 extends TreeNode {
    TreeNode43(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode44 extends TreeNode {
    TreeNode44(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode45 extends TreeNode {
    TreeNode45(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode46 extends TreeNode {
    TreeNode46(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode47 extends TreeNode {
    TreeNode47(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(2));
    }
}

class TreeNode48 extends TreeNode {
    TreeNode48(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(2));
    }
}

class TreeNode49 extends TreeNode {
    TreeNode49(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode50 extends TreeNode {
    TreeNode50(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Dot, elements.get(1));
        labelled.put(Label.Spacing, elements.get(3));
    }
}

class TreeNode51 extends TreeNode {
    TreeNode51(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode52 extends TreeNode {
    TreeNode52(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode53 extends TreeNode {
    TreeNode53(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode54 extends TreeNode {
    TreeNode54(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode55 extends TreeNode {
    TreeNode55(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode56 extends TreeNode {
    TreeNode56(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode57 extends TreeNode {
    TreeNode57(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode58 extends TreeNode {
    TreeNode58(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode59 extends TreeNode {
    TreeNode59(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode60 extends TreeNode {
    TreeNode60(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode61 extends TreeNode {
    TreeNode61(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode62 extends TreeNode {
    TreeNode62(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode63 extends TreeNode {
    TreeNode63(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode64 extends TreeNode {
    TreeNode64(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode65 extends TreeNode {
    TreeNode65(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode66 extends TreeNode {
    TreeNode66(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode67 extends TreeNode {
    TreeNode67(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode68 extends TreeNode {
    TreeNode68(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode69 extends TreeNode {
    TreeNode69(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode70 extends TreeNode {
    TreeNode70(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode71 extends TreeNode {
    TreeNode71(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode72 extends TreeNode {
    TreeNode72(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode73 extends TreeNode {
    TreeNode73(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode74 extends TreeNode {
    TreeNode74(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode75 extends TreeNode {
    TreeNode75(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode76 extends TreeNode {
    TreeNode76(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode77 extends TreeNode {
    TreeNode77(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode78 extends TreeNode {
    TreeNode78(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode79 extends TreeNode {
    TreeNode79(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode80 extends TreeNode {
    TreeNode80(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode81 extends TreeNode {
    TreeNode81(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode82 extends TreeNode {
    TreeNode82(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode83 extends TreeNode {
    TreeNode83(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode84 extends TreeNode {
    TreeNode84(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.Spacing, elements.get(1));
    }
}

class TreeNode85 extends TreeNode {
    TreeNode85(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.EndOfLine, elements.get(2));
    }
}
