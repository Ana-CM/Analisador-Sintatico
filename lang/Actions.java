/**
 * This file was generated from lang.peg
 * See https://canopy.jcoglan.com/ for documentation
 */

package lang;

import java.util.List;

public interface Actions {
    public TreeNode MakeAexp(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeAttr(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeCallAttr(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeCallBrack(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeCmdBrace(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeDecimal(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeDecl(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeDefinition(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeExp(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeFalse(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeFunc(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeIf(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeInteger(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeIterate(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeLValue(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeLiteralCharacter(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeLt(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeMexp(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeNegative(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeNew(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeNot(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeNull(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeParExp(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeParams(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakePrint(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeProg(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeRead(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeReturn(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeRexp(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeTrue(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeTyBool(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeTyChar(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeTyFloat(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeTyInt(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeType(String input, int start, int end, List<TreeNode> elements);
    public TreeNode MakeUserType(String input, int start, int end, List<TreeNode> elements);
}
