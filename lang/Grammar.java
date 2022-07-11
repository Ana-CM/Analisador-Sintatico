/**
 * This file was generated from lang.peg
 * See https://canopy.jcoglan.com/ for documentation
 */

package lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

abstract class Grammar {
    static TreeNode FAILURE = new TreeNode();

    int inputSize, offset, failure;
    String input;
    List<String[]> expected;
    Map<Label, Map<Integer, CacheRecord>> cache;
    Actions actions;

    private static Pattern REGEX_1 = Pattern.compile("\\A[a-z]");
    private static Pattern REGEX_2 = Pattern.compile("\\A[a-z]");
    private static Pattern REGEX_3 = Pattern.compile("\\A[0-9]");
    private static Pattern REGEX_4 = Pattern.compile("\\A[A-Z]");
    private static Pattern REGEX_5 = Pattern.compile("\\A[A-Z]");
    private static Pattern REGEX_6 = Pattern.compile("\\A[a-z]");
    private static Pattern REGEX_7 = Pattern.compile("\\A[0-9]");
    private static Pattern REGEX_8 = Pattern.compile("\\A[A-Z]");
    private static Pattern REGEX_9 = Pattern.compile("\\A[0-9]");
    private static Pattern REGEX_10 = Pattern.compile("\\A[0-9]");
    private static Pattern REGEX_11 = Pattern.compile("\\A[0-9]");

    TreeNode _read_Prog() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Prog);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Prog, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(4);
            TreeNode address1 = FAILURE;
            address1 = _read_Spacing();
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                int index2 = offset;
                List<TreeNode> elements1 = new ArrayList<TreeNode>();
                TreeNode address3 = null;
                while (true) {
                    address3 = _read_Definition();
                    if (address3 != FAILURE) {
                        elements1.add(address3);
                    } else {
                        break;
                    }
                }
                if (elements1.size() >= 0) {
                    address2 = new TreeNode(input.substring(index2, offset), index2, elements1);
                    offset = offset;
                } else {
                    address2 = FAILURE;
                }
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                    TreeNode address4 = FAILURE;
                    int index3 = offset;
                    List<TreeNode> elements2 = new ArrayList<TreeNode>();
                    TreeNode address5 = null;
                    while (true) {
                        address5 = _read_Func();
                        if (address5 != FAILURE) {
                            elements2.add(address5);
                        } else {
                            break;
                        }
                    }
                    if (elements2.size() >= 1) {
                        address4 = new TreeNode(input.substring(index3, offset), index3, elements2);
                        offset = offset;
                    } else {
                        address4 = FAILURE;
                    }
                    if (address4 != FAILURE) {
                        elements0.add(2, address4);
                        TreeNode address6 = FAILURE;
                        address6 = _read_EndOfFile();
                        if (address6 != FAILURE) {
                            elements0.add(3, address6);
                        } else {
                            elements0 = null;
                            offset = index1;
                        }
                    } else {
                        elements0 = null;
                        offset = index1;
                    }
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeProg(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Definition() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Definition);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Definition, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(5);
            TreeNode address1 = FAILURE;
            address1 = _read_Data();
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Type();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                    TreeNode address3 = FAILURE;
                    address3 = _read_LBrace();
                    if (address3 != FAILURE) {
                        elements0.add(2, address3);
                        TreeNode address4 = FAILURE;
                        int index2 = offset;
                        List<TreeNode> elements1 = new ArrayList<TreeNode>();
                        TreeNode address5 = null;
                        while (true) {
                            address5 = _read_Declaration();
                            if (address5 != FAILURE) {
                                elements1.add(address5);
                            } else {
                                break;
                            }
                        }
                        if (elements1.size() >= 1) {
                            address4 = new TreeNode(input.substring(index2, offset), index2, elements1);
                            offset = offset;
                        } else {
                            address4 = FAILURE;
                        }
                        if (address4 != FAILURE) {
                            elements0.add(3, address4);
                            TreeNode address6 = FAILURE;
                            address6 = _read_RBrace();
                            if (address6 != FAILURE) {
                                elements0.add(4, address6);
                            } else {
                                elements0 = null;
                                offset = index1;
                            }
                        } else {
                            elements0 = null;
                            offset = index1;
                        }
                    } else {
                        elements0 = null;
                        offset = index1;
                    }
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeDefinition(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Declaration() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Declaration);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Declaration, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(4);
            TreeNode address1 = FAILURE;
            address1 = _read_Identifier();
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_ColonColon();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                    TreeNode address3 = FAILURE;
                    address3 = _read_Type();
                    if (address3 != FAILURE) {
                        elements0.add(2, address3);
                        TreeNode address4 = FAILURE;
                        address4 = _read_Semi();
                        if (address4 != FAILURE) {
                            elements0.add(3, address4);
                        } else {
                            elements0 = null;
                            offset = index1;
                        }
                    } else {
                        elements0 = null;
                        offset = index1;
                    }
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeDecl(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Func() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Func);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Func, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(8);
            TreeNode address1 = FAILURE;
            address1 = _read_Identifier();
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_LPar();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                    TreeNode address3 = FAILURE;
                    int index2 = offset;
                    address3 = _read_Params();
                    if (address3 == FAILURE) {
                        address3 = new TreeNode(input.substring(index2, index2), index2, new ArrayList<TreeNode>());
                        offset = index2;
                    }
                    if (address3 != FAILURE) {
                        elements0.add(2, address3);
                        TreeNode address4 = FAILURE;
                        address4 = _read_RPar();
                        if (address4 != FAILURE) {
                            elements0.add(3, address4);
                            TreeNode address5 = FAILURE;
                            int index3 = offset;
                            int index4 = offset;
                            List<TreeNode> elements1 = new ArrayList<TreeNode>(3);
                            TreeNode address6 = FAILURE;
                            address6 = _read_Colon();
                            if (address6 != FAILURE) {
                                elements1.add(0, address6);
                                TreeNode address7 = FAILURE;
                                address7 = _read_Type();
                                if (address7 != FAILURE) {
                                    elements1.add(1, address7);
                                    TreeNode address8 = FAILURE;
                                    int index5 = offset;
                                    List<TreeNode> elements2 = new ArrayList<TreeNode>();
                                    TreeNode address9 = null;
                                    while (true) {
                                        int index6 = offset;
                                        List<TreeNode> elements3 = new ArrayList<TreeNode>(2);
                                        TreeNode address10 = FAILURE;
                                        address10 = _read_Comma();
                                        if (address10 != FAILURE) {
                                            elements3.add(0, address10);
                                            TreeNode address11 = FAILURE;
                                            address11 = _read_Type();
                                            if (address11 != FAILURE) {
                                                elements3.add(1, address11);
                                            } else {
                                                elements3 = null;
                                                offset = index6;
                                            }
                                        } else {
                                            elements3 = null;
                                            offset = index6;
                                        }
                                        if (elements3 == null) {
                                            address9 = FAILURE;
                                        } else {
                                            address9 = new TreeNode6(input.substring(index6, offset), index6, elements3);
                                            offset = offset;
                                        }
                                        if (address9 != FAILURE) {
                                            elements2.add(address9);
                                        } else {
                                            break;
                                        }
                                    }
                                    if (elements2.size() >= 0) {
                                        address8 = new TreeNode(input.substring(index5, offset), index5, elements2);
                                        offset = offset;
                                    } else {
                                        address8 = FAILURE;
                                    }
                                    if (address8 != FAILURE) {
                                        elements1.add(2, address8);
                                    } else {
                                        elements1 = null;
                                        offset = index4;
                                    }
                                } else {
                                    elements1 = null;
                                    offset = index4;
                                }
                            } else {
                                elements1 = null;
                                offset = index4;
                            }
                            if (elements1 == null) {
                                address5 = FAILURE;
                            } else {
                                address5 = new TreeNode5(input.substring(index4, offset), index4, elements1);
                                offset = offset;
                            }
                            if (address5 == FAILURE) {
                                address5 = new TreeNode(input.substring(index3, index3), index3, new ArrayList<TreeNode>());
                                offset = index3;
                            }
                            if (address5 != FAILURE) {
                                elements0.add(4, address5);
                                TreeNode address12 = FAILURE;
                                address12 = _read_LBrace();
                                if (address12 != FAILURE) {
                                    elements0.add(5, address12);
                                    TreeNode address13 = FAILURE;
                                    int index7 = offset;
                                    List<TreeNode> elements4 = new ArrayList<TreeNode>();
                                    TreeNode address14 = null;
                                    while (true) {
                                        address14 = _read_Cmd();
                                        if (address14 != FAILURE) {
                                            elements4.add(address14);
                                        } else {
                                            break;
                                        }
                                    }
                                    if (elements4.size() >= 0) {
                                        address13 = new TreeNode(input.substring(index7, offset), index7, elements4);
                                        offset = offset;
                                    } else {
                                        address13 = FAILURE;
                                    }
                                    if (address13 != FAILURE) {
                                        elements0.add(6, address13);
                                        TreeNode address15 = FAILURE;
                                        address15 = _read_RBrace();
                                        if (address15 != FAILURE) {
                                            elements0.add(7, address15);
                                        } else {
                                            elements0 = null;
                                            offset = index1;
                                        }
                                    } else {
                                        elements0 = null;
                                        offset = index1;
                                    }
                                } else {
                                    elements0 = null;
                                    offset = index1;
                                }
                            } else {
                                elements0 = null;
                                offset = index1;
                            }
                        } else {
                            elements0 = null;
                            offset = index1;
                        }
                    } else {
                        elements0 = null;
                        offset = index1;
                    }
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeFunc(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Params() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Params);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Params, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(4);
            TreeNode address1 = FAILURE;
            address1 = _read_Identifier();
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_ColonColon();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                    TreeNode address3 = FAILURE;
                    address3 = _read_Type();
                    if (address3 != FAILURE) {
                        elements0.add(2, address3);
                        TreeNode address4 = FAILURE;
                        int index2 = offset;
                        List<TreeNode> elements1 = new ArrayList<TreeNode>();
                        TreeNode address5 = null;
                        while (true) {
                            int index3 = offset;
                            List<TreeNode> elements2 = new ArrayList<TreeNode>(4);
                            TreeNode address6 = FAILURE;
                            address6 = _read_Comma();
                            if (address6 != FAILURE) {
                                elements2.add(0, address6);
                                TreeNode address7 = FAILURE;
                                address7 = _read_Identifier();
                                if (address7 != FAILURE) {
                                    elements2.add(1, address7);
                                    TreeNode address8 = FAILURE;
                                    address8 = _read_ColonColon();
                                    if (address8 != FAILURE) {
                                        elements2.add(2, address8);
                                        TreeNode address9 = FAILURE;
                                        address9 = _read_Type();
                                        if (address9 != FAILURE) {
                                            elements2.add(3, address9);
                                        } else {
                                            elements2 = null;
                                            offset = index3;
                                        }
                                    } else {
                                        elements2 = null;
                                        offset = index3;
                                    }
                                } else {
                                    elements2 = null;
                                    offset = index3;
                                }
                            } else {
                                elements2 = null;
                                offset = index3;
                            }
                            if (elements2 == null) {
                                address5 = FAILURE;
                            } else {
                                address5 = new TreeNode8(input.substring(index3, offset), index3, elements2);
                                offset = offset;
                            }
                            if (address5 != FAILURE) {
                                elements1.add(address5);
                            } else {
                                break;
                            }
                        }
                        if (elements1.size() >= 0) {
                            address4 = new TreeNode(input.substring(index2, offset), index2, elements1);
                            offset = offset;
                        } else {
                            address4 = FAILURE;
                        }
                        if (address4 != FAILURE) {
                            elements0.add(3, address4);
                        } else {
                            elements0 = null;
                            offset = index1;
                        }
                    } else {
                        elements0 = null;
                        offset = index1;
                    }
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeParams(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Type() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Type);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Type, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            address1 = _read_Btype();
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                int index2 = offset;
                List<TreeNode> elements1 = new ArrayList<TreeNode>();
                TreeNode address3 = null;
                while (true) {
                    int index3 = offset;
                    List<TreeNode> elements2 = new ArrayList<TreeNode>(2);
                    TreeNode address4 = FAILURE;
                    address4 = _read_LBrack();
                    if (address4 != FAILURE) {
                        elements2.add(0, address4);
                        TreeNode address5 = FAILURE;
                        address5 = _read_RBrack();
                        if (address5 != FAILURE) {
                            elements2.add(1, address5);
                        } else {
                            elements2 = null;
                            offset = index3;
                        }
                    } else {
                        elements2 = null;
                        offset = index3;
                    }
                    if (elements2 == null) {
                        address3 = FAILURE;
                    } else {
                        address3 = new TreeNode10(input.substring(index3, offset), index3, elements2);
                        offset = offset;
                    }
                    if (address3 != FAILURE) {
                        elements1.add(address3);
                    } else {
                        break;
                    }
                }
                if (elements1.size() >= 0) {
                    address2 = new TreeNode(input.substring(index2, offset), index2, elements1);
                    offset = offset;
                } else {
                    address2 = FAILURE;
                }
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeType(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Btype() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Btype);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Btype, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            address0 = _read_TyInt();
            if (address0 == FAILURE) {
                offset = index1;
                address0 = _read_TyChar();
                if (address0 == FAILURE) {
                    offset = index1;
                    address0 = _read_TyBool();
                    if (address0 == FAILURE) {
                        offset = index1;
                        address0 = _read_TyFloat();
                        if (address0 == FAILURE) {
                            offset = index1;
                            address0 = _read_UserType();
                            if (address0 == FAILURE) {
                                offset = index1;
                            }
                        }
                    }
                }
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Cmd() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Cmd);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Cmd, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            int index2 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(3);
            TreeNode address1 = FAILURE;
            address1 = _read_LBrace();
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                int index3 = offset;
                List<TreeNode> elements1 = new ArrayList<TreeNode>();
                TreeNode address3 = null;
                while (true) {
                    address3 = _read_Cmd();
                    if (address3 != FAILURE) {
                        elements1.add(address3);
                    } else {
                        break;
                    }
                }
                if (elements1.size() >= 1) {
                    address2 = new TreeNode(input.substring(index3, offset), index3, elements1);
                    offset = offset;
                } else {
                    address2 = FAILURE;
                }
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                    TreeNode address4 = FAILURE;
                    address4 = _read_RBrace();
                    if (address4 != FAILURE) {
                        elements0.add(2, address4);
                    } else {
                        elements0 = null;
                        offset = index2;
                    }
                } else {
                    elements0 = null;
                    offset = index2;
                }
            } else {
                elements0 = null;
                offset = index2;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeCmdBrace(input, index2, offset, elements0);
                offset = offset;
            }
            if (address0 == FAILURE) {
                offset = index1;
                int index4 = offset;
                List<TreeNode> elements2 = new ArrayList<TreeNode>(6);
                TreeNode address5 = FAILURE;
                address5 = _read_If();
                if (address5 != FAILURE) {
                    elements2.add(0, address5);
                    TreeNode address6 = FAILURE;
                    address6 = _read_LPar();
                    if (address6 != FAILURE) {
                        elements2.add(1, address6);
                        TreeNode address7 = FAILURE;
                        address7 = _read_Exp();
                        if (address7 != FAILURE) {
                            elements2.add(2, address7);
                            TreeNode address8 = FAILURE;
                            address8 = _read_RPar();
                            if (address8 != FAILURE) {
                                elements2.add(3, address8);
                                TreeNode address9 = FAILURE;
                                address9 = _read_Cmd();
                                if (address9 != FAILURE) {
                                    elements2.add(4, address9);
                                    TreeNode address10 = FAILURE;
                                    int index5 = offset;
                                    int index6 = offset;
                                    List<TreeNode> elements3 = new ArrayList<TreeNode>(2);
                                    TreeNode address11 = FAILURE;
                                    address11 = _read_Else();
                                    if (address11 != FAILURE) {
                                        elements3.add(0, address11);
                                        TreeNode address12 = FAILURE;
                                        address12 = _read_Cmd();
                                        if (address12 != FAILURE) {
                                            elements3.add(1, address12);
                                        } else {
                                            elements3 = null;
                                            offset = index6;
                                        }
                                    } else {
                                        elements3 = null;
                                        offset = index6;
                                    }
                                    if (elements3 == null) {
                                        address10 = FAILURE;
                                    } else {
                                        address10 = new TreeNode13(input.substring(index6, offset), index6, elements3);
                                        offset = offset;
                                    }
                                    if (address10 == FAILURE) {
                                        address10 = new TreeNode(input.substring(index5, index5), index5, new ArrayList<TreeNode>());
                                        offset = index5;
                                    }
                                    if (address10 != FAILURE) {
                                        elements2.add(5, address10);
                                    } else {
                                        elements2 = null;
                                        offset = index4;
                                    }
                                } else {
                                    elements2 = null;
                                    offset = index4;
                                }
                            } else {
                                elements2 = null;
                                offset = index4;
                            }
                        } else {
                            elements2 = null;
                            offset = index4;
                        }
                    } else {
                        elements2 = null;
                        offset = index4;
                    }
                } else {
                    elements2 = null;
                    offset = index4;
                }
                if (elements2 == null) {
                    address0 = FAILURE;
                } else {
                    address0 = actions.MakeIf(input, index4, offset, elements2);
                    offset = offset;
                }
                if (address0 == FAILURE) {
                    offset = index1;
                    int index7 = offset;
                    List<TreeNode> elements4 = new ArrayList<TreeNode>(5);
                    TreeNode address13 = FAILURE;
                    address13 = _read_Iterate();
                    if (address13 != FAILURE) {
                        elements4.add(0, address13);
                        TreeNode address14 = FAILURE;
                        address14 = _read_LPar();
                        if (address14 != FAILURE) {
                            elements4.add(1, address14);
                            TreeNode address15 = FAILURE;
                            address15 = _read_Exp();
                            if (address15 != FAILURE) {
                                elements4.add(2, address15);
                                TreeNode address16 = FAILURE;
                                address16 = _read_RPar();
                                if (address16 != FAILURE) {
                                    elements4.add(3, address16);
                                    TreeNode address17 = FAILURE;
                                    address17 = _read_Cmd();
                                    if (address17 != FAILURE) {
                                        elements4.add(4, address17);
                                    } else {
                                        elements4 = null;
                                        offset = index7;
                                    }
                                } else {
                                    elements4 = null;
                                    offset = index7;
                                }
                            } else {
                                elements4 = null;
                                offset = index7;
                            }
                        } else {
                            elements4 = null;
                            offset = index7;
                        }
                    } else {
                        elements4 = null;
                        offset = index7;
                    }
                    if (elements4 == null) {
                        address0 = FAILURE;
                    } else {
                        address0 = actions.MakeIterate(input, index7, offset, elements4);
                        offset = offset;
                    }
                    if (address0 == FAILURE) {
                        offset = index1;
                        int index8 = offset;
                        List<TreeNode> elements5 = new ArrayList<TreeNode>(3);
                        TreeNode address18 = FAILURE;
                        address18 = _read_Read();
                        if (address18 != FAILURE) {
                            elements5.add(0, address18);
                            TreeNode address19 = FAILURE;
                            address19 = _read_LValue();
                            if (address19 != FAILURE) {
                                elements5.add(1, address19);
                                TreeNode address20 = FAILURE;
                                address20 = _read_Semi();
                                if (address20 != FAILURE) {
                                    elements5.add(2, address20);
                                } else {
                                    elements5 = null;
                                    offset = index8;
                                }
                            } else {
                                elements5 = null;
                                offset = index8;
                            }
                        } else {
                            elements5 = null;
                            offset = index8;
                        }
                        if (elements5 == null) {
                            address0 = FAILURE;
                        } else {
                            address0 = actions.MakeRead(input, index8, offset, elements5);
                            offset = offset;
                        }
                        if (address0 == FAILURE) {
                            offset = index1;
                            int index9 = offset;
                            List<TreeNode> elements6 = new ArrayList<TreeNode>(3);
                            TreeNode address21 = FAILURE;
                            address21 = _read_Print();
                            if (address21 != FAILURE) {
                                elements6.add(0, address21);
                                TreeNode address22 = FAILURE;
                                address22 = _read_Exp();
                                if (address22 != FAILURE) {
                                    elements6.add(1, address22);
                                    TreeNode address23 = FAILURE;
                                    address23 = _read_Semi();
                                    if (address23 != FAILURE) {
                                        elements6.add(2, address23);
                                    } else {
                                        elements6 = null;
                                        offset = index9;
                                    }
                                } else {
                                    elements6 = null;
                                    offset = index9;
                                }
                            } else {
                                elements6 = null;
                                offset = index9;
                            }
                            if (elements6 == null) {
                                address0 = FAILURE;
                            } else {
                                address0 = actions.MakePrint(input, index9, offset, elements6);
                                offset = offset;
                            }
                            if (address0 == FAILURE) {
                                offset = index1;
                                int index10 = offset;
                                List<TreeNode> elements7 = new ArrayList<TreeNode>(4);
                                TreeNode address24 = FAILURE;
                                address24 = _read_Return();
                                if (address24 != FAILURE) {
                                    elements7.add(0, address24);
                                    TreeNode address25 = FAILURE;
                                    address25 = _read_Exp();
                                    if (address25 != FAILURE) {
                                        elements7.add(1, address25);
                                        TreeNode address26 = FAILURE;
                                        int index11 = offset;
                                        List<TreeNode> elements8 = new ArrayList<TreeNode>();
                                        TreeNode address27 = null;
                                        while (true) {
                                            int index12 = offset;
                                            List<TreeNode> elements9 = new ArrayList<TreeNode>(2);
                                            TreeNode address28 = FAILURE;
                                            address28 = _read_Comma();
                                            if (address28 != FAILURE) {
                                                elements9.add(0, address28);
                                                TreeNode address29 = FAILURE;
                                                address29 = _read_Exp();
                                                if (address29 != FAILURE) {
                                                    elements9.add(1, address29);
                                                } else {
                                                    elements9 = null;
                                                    offset = index12;
                                                }
                                            } else {
                                                elements9 = null;
                                                offset = index12;
                                            }
                                            if (elements9 == null) {
                                                address27 = FAILURE;
                                            } else {
                                                address27 = new TreeNode18(input.substring(index12, offset), index12, elements9);
                                                offset = offset;
                                            }
                                            if (address27 != FAILURE) {
                                                elements8.add(address27);
                                            } else {
                                                break;
                                            }
                                        }
                                        if (elements8.size() >= 0) {
                                            address26 = new TreeNode(input.substring(index11, offset), index11, elements8);
                                            offset = offset;
                                        } else {
                                            address26 = FAILURE;
                                        }
                                        if (address26 != FAILURE) {
                                            elements7.add(2, address26);
                                            TreeNode address30 = FAILURE;
                                            address30 = _read_Semi();
                                            if (address30 != FAILURE) {
                                                elements7.add(3, address30);
                                            } else {
                                                elements7 = null;
                                                offset = index10;
                                            }
                                        } else {
                                            elements7 = null;
                                            offset = index10;
                                        }
                                    } else {
                                        elements7 = null;
                                        offset = index10;
                                    }
                                } else {
                                    elements7 = null;
                                    offset = index10;
                                }
                                if (elements7 == null) {
                                    address0 = FAILURE;
                                } else {
                                    address0 = actions.MakeReturn(input, index10, offset, elements7);
                                    offset = offset;
                                }
                                if (address0 == FAILURE) {
                                    offset = index1;
                                    int index13 = offset;
                                    List<TreeNode> elements10 = new ArrayList<TreeNode>(4);
                                    TreeNode address31 = FAILURE;
                                    address31 = _read_LValue();
                                    if (address31 != FAILURE) {
                                        elements10.add(0, address31);
                                        TreeNode address32 = FAILURE;
                                        address32 = _read_Eq();
                                        if (address32 != FAILURE) {
                                            elements10.add(1, address32);
                                            TreeNode address33 = FAILURE;
                                            address33 = _read_Exp();
                                            if (address33 != FAILURE) {
                                                elements10.add(2, address33);
                                                TreeNode address34 = FAILURE;
                                                address34 = _read_Semi();
                                                if (address34 != FAILURE) {
                                                    elements10.add(3, address34);
                                                } else {
                                                    elements10 = null;
                                                    offset = index13;
                                                }
                                            } else {
                                                elements10 = null;
                                                offset = index13;
                                            }
                                        } else {
                                            elements10 = null;
                                            offset = index13;
                                        }
                                    } else {
                                        elements10 = null;
                                        offset = index13;
                                    }
                                    if (elements10 == null) {
                                        address0 = FAILURE;
                                    } else {
                                        address0 = actions.MakeAttr(input, index13, offset, elements10);
                                        offset = offset;
                                    }
                                    if (address0 == FAILURE) {
                                        offset = index1;
                                        int index14 = offset;
                                        List<TreeNode> elements11 = new ArrayList<TreeNode>(6);
                                        TreeNode address35 = FAILURE;
                                        address35 = _read_Identifier();
                                        if (address35 != FAILURE) {
                                            elements11.add(0, address35);
                                            TreeNode address36 = FAILURE;
                                            address36 = _read_LPar();
                                            if (address36 != FAILURE) {
                                                elements11.add(1, address36);
                                                TreeNode address37 = FAILURE;
                                                int index15 = offset;
                                                address37 = _read_Exps();
                                                if (address37 == FAILURE) {
                                                    address37 = new TreeNode(input.substring(index15, index15), index15, new ArrayList<TreeNode>());
                                                    offset = index15;
                                                }
                                                if (address37 != FAILURE) {
                                                    elements11.add(2, address37);
                                                    TreeNode address38 = FAILURE;
                                                    address38 = _read_RPar();
                                                    if (address38 != FAILURE) {
                                                        elements11.add(3, address38);
                                                        TreeNode address39 = FAILURE;
                                                        int index16 = offset;
                                                        int index17 = offset;
                                                        List<TreeNode> elements12 = new ArrayList<TreeNode>(4);
                                                        TreeNode address40 = FAILURE;
                                                        address40 = _read_Lt();
                                                        if (address40 != FAILURE) {
                                                            elements12.add(0, address40);
                                                            TreeNode address41 = FAILURE;
                                                            address41 = _read_LValue();
                                                            if (address41 != FAILURE) {
                                                                elements12.add(1, address41);
                                                                TreeNode address42 = FAILURE;
                                                                int index18 = offset;
                                                                List<TreeNode> elements13 = new ArrayList<TreeNode>();
                                                                TreeNode address43 = null;
                                                                while (true) {
                                                                    int index19 = offset;
                                                                    List<TreeNode> elements14 = new ArrayList<TreeNode>(2);
                                                                    TreeNode address44 = FAILURE;
                                                                    address44 = _read_Comma();
                                                                    if (address44 != FAILURE) {
                                                                        elements14.add(0, address44);
                                                                        TreeNode address45 = FAILURE;
                                                                        address45 = _read_LValue();
                                                                        if (address45 != FAILURE) {
                                                                            elements14.add(1, address45);
                                                                        } else {
                                                                            elements14 = null;
                                                                            offset = index19;
                                                                        }
                                                                    } else {
                                                                        elements14 = null;
                                                                        offset = index19;
                                                                    }
                                                                    if (elements14 == null) {
                                                                        address43 = FAILURE;
                                                                    } else {
                                                                        address43 = new TreeNode22(input.substring(index19, offset), index19, elements14);
                                                                        offset = offset;
                                                                    }
                                                                    if (address43 != FAILURE) {
                                                                        elements13.add(address43);
                                                                    } else {
                                                                        break;
                                                                    }
                                                                }
                                                                if (elements13.size() >= 0) {
                                                                    address42 = new TreeNode(input.substring(index18, offset), index18, elements13);
                                                                    offset = offset;
                                                                } else {
                                                                    address42 = FAILURE;
                                                                }
                                                                if (address42 != FAILURE) {
                                                                    elements12.add(2, address42);
                                                                    TreeNode address46 = FAILURE;
                                                                    address46 = _read_Gt();
                                                                    if (address46 != FAILURE) {
                                                                        elements12.add(3, address46);
                                                                    } else {
                                                                        elements12 = null;
                                                                        offset = index17;
                                                                    }
                                                                } else {
                                                                    elements12 = null;
                                                                    offset = index17;
                                                                }
                                                            } else {
                                                                elements12 = null;
                                                                offset = index17;
                                                            }
                                                        } else {
                                                            elements12 = null;
                                                            offset = index17;
                                                        }
                                                        if (elements12 == null) {
                                                            address39 = FAILURE;
                                                        } else {
                                                            address39 = new TreeNode21(input.substring(index17, offset), index17, elements12);
                                                            offset = offset;
                                                        }
                                                        if (address39 == FAILURE) {
                                                            address39 = new TreeNode(input.substring(index16, index16), index16, new ArrayList<TreeNode>());
                                                            offset = index16;
                                                        }
                                                        if (address39 != FAILURE) {
                                                            elements11.add(4, address39);
                                                            TreeNode address47 = FAILURE;
                                                            address47 = _read_Semi();
                                                            if (address47 != FAILURE) {
                                                                elements11.add(5, address47);
                                                            } else {
                                                                elements11 = null;
                                                                offset = index14;
                                                            }
                                                        } else {
                                                            elements11 = null;
                                                            offset = index14;
                                                        }
                                                    } else {
                                                        elements11 = null;
                                                        offset = index14;
                                                    }
                                                } else {
                                                    elements11 = null;
                                                    offset = index14;
                                                }
                                            } else {
                                                elements11 = null;
                                                offset = index14;
                                            }
                                        } else {
                                            elements11 = null;
                                            offset = index14;
                                        }
                                        if (elements11 == null) {
                                            address0 = FAILURE;
                                        } else {
                                            address0 = actions.MakeCallAttr(input, index14, offset, elements11);
                                            offset = offset;
                                        }
                                        if (address0 == FAILURE) {
                                            offset = index1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Exp() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Exp);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Exp, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            address1 = _read_Rexp();
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                int index2 = offset;
                List<TreeNode> elements1 = new ArrayList<TreeNode>();
                TreeNode address3 = null;
                while (true) {
                    int index3 = offset;
                    List<TreeNode> elements2 = new ArrayList<TreeNode>(2);
                    TreeNode address4 = FAILURE;
                    address4 = _read_And();
                    if (address4 != FAILURE) {
                        elements2.add(0, address4);
                        TreeNode address5 = FAILURE;
                        address5 = _read_Rexp();
                        if (address5 != FAILURE) {
                            elements2.add(1, address5);
                        } else {
                            elements2 = null;
                            offset = index3;
                        }
                    } else {
                        elements2 = null;
                        offset = index3;
                    }
                    if (elements2 == null) {
                        address3 = FAILURE;
                    } else {
                        address3 = new TreeNode24(input.substring(index3, offset), index3, elements2);
                        offset = offset;
                    }
                    if (address3 != FAILURE) {
                        elements1.add(address3);
                    } else {
                        break;
                    }
                }
                if (elements1.size() >= 0) {
                    address2 = new TreeNode(input.substring(index2, offset), index2, elements1);
                    offset = offset;
                } else {
                    address2 = FAILURE;
                }
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeExp(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Rexp() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Rexp);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Rexp, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            int index2 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(3);
            TreeNode address1 = FAILURE;
            address1 = _read_Aexp();
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Lt();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                    TreeNode address3 = FAILURE;
                    address3 = _read_Aexp();
                    if (address3 != FAILURE) {
                        elements0.add(2, address3);
                    } else {
                        elements0 = null;
                        offset = index2;
                    }
                } else {
                    elements0 = null;
                    offset = index2;
                }
            } else {
                elements0 = null;
                offset = index2;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeLt(input, index2, offset, elements0);
                offset = offset;
            }
            if (address0 == FAILURE) {
                offset = index1;
                int index3 = offset;
                List<TreeNode> elements1 = new ArrayList<TreeNode>(2);
                TreeNode address4 = FAILURE;
                address4 = _read_Aexp();
                if (address4 != FAILURE) {
                    elements1.add(0, address4);
                    TreeNode address5 = FAILURE;
                    int index4 = offset;
                    List<TreeNode> elements2 = new ArrayList<TreeNode>();
                    TreeNode address6 = null;
                    while (true) {
                        int index5 = offset;
                        List<TreeNode> elements3 = new ArrayList<TreeNode>(2);
                        TreeNode address7 = FAILURE;
                        int index6 = offset;
                        address7 = _read_Eqeq();
                        if (address7 == FAILURE) {
                            offset = index6;
                            address7 = _read_Ne();
                            if (address7 == FAILURE) {
                                offset = index6;
                            }
                        }
                        if (address7 != FAILURE) {
                            elements3.add(0, address7);
                            TreeNode address8 = FAILURE;
                            address8 = _read_Aexp();
                            if (address8 != FAILURE) {
                                elements3.add(1, address8);
                            } else {
                                elements3 = null;
                                offset = index5;
                            }
                        } else {
                            elements3 = null;
                            offset = index5;
                        }
                        if (elements3 == null) {
                            address6 = FAILURE;
                        } else {
                            address6 = new TreeNode27(input.substring(index5, offset), index5, elements3);
                            offset = offset;
                        }
                        if (address6 != FAILURE) {
                            elements2.add(address6);
                        } else {
                            break;
                        }
                    }
                    if (elements2.size() >= 0) {
                        address5 = new TreeNode(input.substring(index4, offset), index4, elements2);
                        offset = offset;
                    } else {
                        address5 = FAILURE;
                    }
                    if (address5 != FAILURE) {
                        elements1.add(1, address5);
                    } else {
                        elements1 = null;
                        offset = index3;
                    }
                } else {
                    elements1 = null;
                    offset = index3;
                }
                if (elements1 == null) {
                    address0 = FAILURE;
                } else {
                    address0 = actions.MakeRexp(input, index3, offset, elements1);
                    offset = offset;
                }
                if (address0 == FAILURE) {
                    offset = index1;
                }
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Aexp() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Aexp);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Aexp, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            address1 = _read_Mexp();
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                int index2 = offset;
                List<TreeNode> elements1 = new ArrayList<TreeNode>();
                TreeNode address3 = null;
                while (true) {
                    int index3 = offset;
                    List<TreeNode> elements2 = new ArrayList<TreeNode>(2);
                    TreeNode address4 = FAILURE;
                    int index4 = offset;
                    address4 = _read_Plus();
                    if (address4 == FAILURE) {
                        offset = index4;
                        address4 = _read_Minus();
                        if (address4 == FAILURE) {
                            offset = index4;
                        }
                    }
                    if (address4 != FAILURE) {
                        elements2.add(0, address4);
                        TreeNode address5 = FAILURE;
                        address5 = _read_Mexp();
                        if (address5 != FAILURE) {
                            elements2.add(1, address5);
                        } else {
                            elements2 = null;
                            offset = index3;
                        }
                    } else {
                        elements2 = null;
                        offset = index3;
                    }
                    if (elements2 == null) {
                        address3 = FAILURE;
                    } else {
                        address3 = new TreeNode29(input.substring(index3, offset), index3, elements2);
                        offset = offset;
                    }
                    if (address3 != FAILURE) {
                        elements1.add(address3);
                    } else {
                        break;
                    }
                }
                if (elements1.size() >= 0) {
                    address2 = new TreeNode(input.substring(index2, offset), index2, elements1);
                    offset = offset;
                } else {
                    address2 = FAILURE;
                }
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeAexp(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Mexp() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Mexp);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Mexp, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            address1 = _read_Sexp();
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                int index2 = offset;
                List<TreeNode> elements1 = new ArrayList<TreeNode>();
                TreeNode address3 = null;
                while (true) {
                    int index3 = offset;
                    List<TreeNode> elements2 = new ArrayList<TreeNode>(2);
                    TreeNode address4 = FAILURE;
                    int index4 = offset;
                    address4 = _read_Times();
                    if (address4 == FAILURE) {
                        offset = index4;
                        address4 = _read_Div();
                        if (address4 == FAILURE) {
                            offset = index4;
                            address4 = _read_Mod();
                            if (address4 == FAILURE) {
                                offset = index4;
                            }
                        }
                    }
                    if (address4 != FAILURE) {
                        elements2.add(0, address4);
                        TreeNode address5 = FAILURE;
                        address5 = _read_Sexp();
                        if (address5 != FAILURE) {
                            elements2.add(1, address5);
                        } else {
                            elements2 = null;
                            offset = index3;
                        }
                    } else {
                        elements2 = null;
                        offset = index3;
                    }
                    if (elements2 == null) {
                        address3 = FAILURE;
                    } else {
                        address3 = new TreeNode31(input.substring(index3, offset), index3, elements2);
                        offset = offset;
                    }
                    if (address3 != FAILURE) {
                        elements1.add(address3);
                    } else {
                        break;
                    }
                }
                if (elements1.size() >= 0) {
                    address2 = new TreeNode(input.substring(index2, offset), index2, elements1);
                    offset = offset;
                } else {
                    address2 = FAILURE;
                }
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeMexp(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Sexp() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Sexp);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Sexp, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            int index2 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            address1 = _read_Not();
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Sexp();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index2;
                }
            } else {
                elements0 = null;
                offset = index2;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeNot(input, index2, offset, elements0);
                offset = offset;
            }
            if (address0 == FAILURE) {
                offset = index1;
                int index3 = offset;
                List<TreeNode> elements1 = new ArrayList<TreeNode>(2);
                TreeNode address3 = FAILURE;
                address3 = _read_Minus();
                if (address3 != FAILURE) {
                    elements1.add(0, address3);
                    TreeNode address4 = FAILURE;
                    address4 = _read_Sexp();
                    if (address4 != FAILURE) {
                        elements1.add(1, address4);
                    } else {
                        elements1 = null;
                        offset = index3;
                    }
                } else {
                    elements1 = null;
                    offset = index3;
                }
                if (elements1 == null) {
                    address0 = FAILURE;
                } else {
                    address0 = actions.MakeNegative(input, index3, offset, elements1);
                    offset = offset;
                }
                if (address0 == FAILURE) {
                    offset = index1;
                    address0 = _read_True();
                    if (address0 == FAILURE) {
                        offset = index1;
                        address0 = _read_False();
                        if (address0 == FAILURE) {
                            offset = index1;
                            address0 = _read_Null();
                            if (address0 == FAILURE) {
                                offset = index1;
                                address0 = _read_Decimal();
                                if (address0 == FAILURE) {
                                    offset = index1;
                                    address0 = _read_Integer();
                                    if (address0 == FAILURE) {
                                        offset = index1;
                                        address0 = _read_LiteralCharacter();
                                        if (address0 == FAILURE) {
                                            offset = index1;
                                            address0 = _read_Pexp();
                                            if (address0 == FAILURE) {
                                                offset = index1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Pexp() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Pexp);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Pexp, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            int index2 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(3);
            TreeNode address1 = FAILURE;
            address1 = _read_New();
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Type();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                    TreeNode address3 = FAILURE;
                    int index3 = offset;
                    int index4 = offset;
                    List<TreeNode> elements1 = new ArrayList<TreeNode>(3);
                    TreeNode address4 = FAILURE;
                    address4 = _read_LBrack();
                    if (address4 != FAILURE) {
                        elements1.add(0, address4);
                        TreeNode address5 = FAILURE;
                        address5 = _read_Exp();
                        if (address5 != FAILURE) {
                            elements1.add(1, address5);
                            TreeNode address6 = FAILURE;
                            address6 = _read_RBrack();
                            if (address6 != FAILURE) {
                                elements1.add(2, address6);
                            } else {
                                elements1 = null;
                                offset = index4;
                            }
                        } else {
                            elements1 = null;
                            offset = index4;
                        }
                    } else {
                        elements1 = null;
                        offset = index4;
                    }
                    if (elements1 == null) {
                        address3 = FAILURE;
                    } else {
                        address3 = new TreeNode35(input.substring(index4, offset), index4, elements1);
                        offset = offset;
                    }
                    if (address3 == FAILURE) {
                        address3 = new TreeNode(input.substring(index3, index3), index3, new ArrayList<TreeNode>());
                        offset = index3;
                    }
                    if (address3 != FAILURE) {
                        elements0.add(2, address3);
                    } else {
                        elements0 = null;
                        offset = index2;
                    }
                } else {
                    elements0 = null;
                    offset = index2;
                }
            } else {
                elements0 = null;
                offset = index2;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeNew(input, index2, offset, elements0);
                offset = offset;
            }
            if (address0 == FAILURE) {
                offset = index1;
                int index5 = offset;
                List<TreeNode> elements2 = new ArrayList<TreeNode>(7);
                TreeNode address7 = FAILURE;
                address7 = _read_Identifier();
                if (address7 != FAILURE) {
                    elements2.add(0, address7);
                    TreeNode address8 = FAILURE;
                    address8 = _read_LPar();
                    if (address8 != FAILURE) {
                        elements2.add(1, address8);
                        TreeNode address9 = FAILURE;
                        int index6 = offset;
                        address9 = _read_Exps();
                        if (address9 == FAILURE) {
                            address9 = new TreeNode(input.substring(index6, index6), index6, new ArrayList<TreeNode>());
                            offset = index6;
                        }
                        if (address9 != FAILURE) {
                            elements2.add(2, address9);
                            TreeNode address10 = FAILURE;
                            address10 = _read_RPar();
                            if (address10 != FAILURE) {
                                elements2.add(3, address10);
                                TreeNode address11 = FAILURE;
                                address11 = _read_LBrack();
                                if (address11 != FAILURE) {
                                    elements2.add(4, address11);
                                    TreeNode address12 = FAILURE;
                                    address12 = _read_Exp();
                                    if (address12 != FAILURE) {
                                        elements2.add(5, address12);
                                        TreeNode address13 = FAILURE;
                                        address13 = _read_RBrack();
                                        if (address13 != FAILURE) {
                                            elements2.add(6, address13);
                                        } else {
                                            elements2 = null;
                                            offset = index5;
                                        }
                                    } else {
                                        elements2 = null;
                                        offset = index5;
                                    }
                                } else {
                                    elements2 = null;
                                    offset = index5;
                                }
                            } else {
                                elements2 = null;
                                offset = index5;
                            }
                        } else {
                            elements2 = null;
                            offset = index5;
                        }
                    } else {
                        elements2 = null;
                        offset = index5;
                    }
                } else {
                    elements2 = null;
                    offset = index5;
                }
                if (elements2 == null) {
                    address0 = FAILURE;
                } else {
                    address0 = actions.MakeCallBrack(input, index5, offset, elements2);
                    offset = offset;
                }
                if (address0 == FAILURE) {
                    offset = index1;
                    address0 = _read_LValue();
                    if (address0 == FAILURE) {
                        offset = index1;
                        int index7 = offset;
                        List<TreeNode> elements3 = new ArrayList<TreeNode>(3);
                        TreeNode address14 = FAILURE;
                        address14 = _read_LPar();
                        if (address14 != FAILURE) {
                            elements3.add(0, address14);
                            TreeNode address15 = FAILURE;
                            address15 = _read_Exp();
                            if (address15 != FAILURE) {
                                elements3.add(1, address15);
                                TreeNode address16 = FAILURE;
                                address16 = _read_RPar();
                                if (address16 != FAILURE) {
                                    elements3.add(2, address16);
                                } else {
                                    elements3 = null;
                                    offset = index7;
                                }
                            } else {
                                elements3 = null;
                                offset = index7;
                            }
                        } else {
                            elements3 = null;
                            offset = index7;
                        }
                        if (elements3 == null) {
                            address0 = FAILURE;
                        } else {
                            address0 = actions.MakeParExp(input, index7, offset, elements3);
                            offset = offset;
                        }
                        if (address0 == FAILURE) {
                            offset = index1;
                        }
                    }
                }
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_LValue() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.LValue);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.LValue, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            address1 = _read_Identifier();
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                int index2 = offset;
                List<TreeNode> elements1 = new ArrayList<TreeNode>();
                TreeNode address3 = null;
                while (true) {
                    int index3 = offset;
                    int index4 = offset;
                    List<TreeNode> elements2 = new ArrayList<TreeNode>(3);
                    TreeNode address4 = FAILURE;
                    address4 = _read_LBrack();
                    if (address4 != FAILURE) {
                        elements2.add(0, address4);
                        TreeNode address5 = FAILURE;
                        address5 = _read_Exp();
                        if (address5 != FAILURE) {
                            elements2.add(1, address5);
                            TreeNode address6 = FAILURE;
                            address6 = _read_RBrack();
                            if (address6 != FAILURE) {
                                elements2.add(2, address6);
                            } else {
                                elements2 = null;
                                offset = index4;
                            }
                        } else {
                            elements2 = null;
                            offset = index4;
                        }
                    } else {
                        elements2 = null;
                        offset = index4;
                    }
                    if (elements2 == null) {
                        address3 = FAILURE;
                    } else {
                        address3 = new TreeNode39(input.substring(index4, offset), index4, elements2);
                        offset = offset;
                    }
                    if (address3 == FAILURE) {
                        offset = index3;
                        int index5 = offset;
                        List<TreeNode> elements3 = new ArrayList<TreeNode>(2);
                        TreeNode address7 = FAILURE;
                        address7 = _read_Dot();
                        if (address7 != FAILURE) {
                            elements3.add(0, address7);
                            TreeNode address8 = FAILURE;
                            address8 = _read_Identifier();
                            if (address8 != FAILURE) {
                                elements3.add(1, address8);
                            } else {
                                elements3 = null;
                                offset = index5;
                            }
                        } else {
                            elements3 = null;
                            offset = index5;
                        }
                        if (elements3 == null) {
                            address3 = FAILURE;
                        } else {
                            address3 = new TreeNode40(input.substring(index5, offset), index5, elements3);
                            offset = offset;
                        }
                        if (address3 == FAILURE) {
                            offset = index3;
                        }
                    }
                    if (address3 != FAILURE) {
                        elements1.add(address3);
                    } else {
                        break;
                    }
                }
                if (elements1.size() >= 0) {
                    address2 = new TreeNode(input.substring(index2, offset), index2, elements1);
                    offset = offset;
                } else {
                    address2 = FAILURE;
                }
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeLValue(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Exps() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Exps);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Exps, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            address1 = _read_Exp();
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                int index2 = offset;
                List<TreeNode> elements1 = new ArrayList<TreeNode>();
                TreeNode address3 = null;
                while (true) {
                    int index3 = offset;
                    List<TreeNode> elements2 = new ArrayList<TreeNode>(2);
                    TreeNode address4 = FAILURE;
                    address4 = _read_Comma();
                    if (address4 != FAILURE) {
                        elements2.add(0, address4);
                        TreeNode address5 = FAILURE;
                        address5 = _read_Exp();
                        if (address5 != FAILURE) {
                            elements2.add(1, address5);
                        } else {
                            elements2 = null;
                            offset = index3;
                        }
                    } else {
                        elements2 = null;
                        offset = index3;
                    }
                    if (elements2 == null) {
                        address3 = FAILURE;
                    } else {
                        address3 = new TreeNode42(input.substring(index3, offset), index3, elements2);
                        offset = offset;
                    }
                    if (address3 != FAILURE) {
                        elements1.add(address3);
                    } else {
                        break;
                    }
                }
                if (elements1.size() >= 0) {
                    address2 = new TreeNode(input.substring(index2, offset), index2, elements1);
                    offset = offset;
                } else {
                    address2 = FAILURE;
                }
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode41(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Identifier() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Identifier);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Identifier, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            int index2 = offset;
            List<TreeNode> elements1 = new ArrayList<TreeNode>(2);
            TreeNode address2 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && REGEX_1.matcher(chunk0).matches()) {
                address2 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address2 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Identifier", "[a-z]" });
                }
            }
            if (address2 != FAILURE) {
                elements1.add(0, address2);
                TreeNode address3 = FAILURE;
                int index3 = offset;
                List<TreeNode> elements2 = new ArrayList<TreeNode>();
                TreeNode address4 = null;
                while (true) {
                    int index4 = offset;
                    String chunk1 = null;
                    int max1 = offset + 1;
                    if (max1 <= inputSize) {
                        chunk1 = input.substring(offset, max1);
                    }
                    if (chunk1 != null && REGEX_2.matcher(chunk1).matches()) {
                        address4 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                        offset = offset + 1;
                    } else {
                        address4 = FAILURE;
                        if (offset > failure) {
                            failure = offset;
                            expected = new ArrayList<String[]>();
                        }
                        if (offset == failure) {
                            expected.add(new String[] { "LANG::Identifier", "[a-z]" });
                        }
                    }
                    if (address4 == FAILURE) {
                        offset = index4;
                        String chunk2 = null;
                        int max2 = offset + 1;
                        if (max2 <= inputSize) {
                            chunk2 = input.substring(offset, max2);
                        }
                        if (chunk2 != null && REGEX_3.matcher(chunk2).matches()) {
                            address4 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                            offset = offset + 1;
                        } else {
                            address4 = FAILURE;
                            if (offset > failure) {
                                failure = offset;
                                expected = new ArrayList<String[]>();
                            }
                            if (offset == failure) {
                                expected.add(new String[] { "LANG::Identifier", "[0-9]" });
                            }
                        }
                        if (address4 == FAILURE) {
                            offset = index4;
                            String chunk3 = null;
                            int max3 = offset + 1;
                            if (max3 <= inputSize) {
                                chunk3 = input.substring(offset, max3);
                            }
                            if (chunk3 != null && chunk3.equals("_")) {
                                address4 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                                offset = offset + 1;
                            } else {
                                address4 = FAILURE;
                                if (offset > failure) {
                                    failure = offset;
                                    expected = new ArrayList<String[]>();
                                }
                                if (offset == failure) {
                                    expected.add(new String[] { "LANG::Identifier", "\"_\"" });
                                }
                            }
                            if (address4 == FAILURE) {
                                offset = index4;
                                String chunk4 = null;
                                int max4 = offset + 1;
                                if (max4 <= inputSize) {
                                    chunk4 = input.substring(offset, max4);
                                }
                                if (chunk4 != null && REGEX_4.matcher(chunk4).matches()) {
                                    address4 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                                    offset = offset + 1;
                                } else {
                                    address4 = FAILURE;
                                    if (offset > failure) {
                                        failure = offset;
                                        expected = new ArrayList<String[]>();
                                    }
                                    if (offset == failure) {
                                        expected.add(new String[] { "LANG::Identifier", "[A-Z]" });
                                    }
                                }
                                if (address4 == FAILURE) {
                                    offset = index4;
                                }
                            }
                        }
                    }
                    if (address4 != FAILURE) {
                        elements2.add(address4);
                    } else {
                        break;
                    }
                }
                if (elements2.size() >= 0) {
                    address3 = new TreeNode(input.substring(index3, offset), index3, elements2);
                    offset = offset;
                } else {
                    address3 = FAILURE;
                }
                if (address3 != FAILURE) {
                    elements1.add(1, address3);
                } else {
                    elements1 = null;
                    offset = index2;
                }
            } else {
                elements1 = null;
                offset = index2;
            }
            if (elements1 == null) {
                address1 = FAILURE;
            } else {
                address1 = new TreeNode(input.substring(index2, offset), index2, elements1);
                offset = offset;
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address5 = FAILURE;
                address5 = _read_Spacing();
                if (address5 != FAILURE) {
                    elements0.add(1, address5);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode43(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_UserType() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.UserType);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.UserType, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            int index2 = offset;
            List<TreeNode> elements1 = new ArrayList<TreeNode>(2);
            TreeNode address2 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && REGEX_5.matcher(chunk0).matches()) {
                address2 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address2 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::UserType", "[A-Z]" });
                }
            }
            if (address2 != FAILURE) {
                elements1.add(0, address2);
                TreeNode address3 = FAILURE;
                int index3 = offset;
                List<TreeNode> elements2 = new ArrayList<TreeNode>();
                TreeNode address4 = null;
                while (true) {
                    int index4 = offset;
                    String chunk1 = null;
                    int max1 = offset + 1;
                    if (max1 <= inputSize) {
                        chunk1 = input.substring(offset, max1);
                    }
                    if (chunk1 != null && REGEX_6.matcher(chunk1).matches()) {
                        address4 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                        offset = offset + 1;
                    } else {
                        address4 = FAILURE;
                        if (offset > failure) {
                            failure = offset;
                            expected = new ArrayList<String[]>();
                        }
                        if (offset == failure) {
                            expected.add(new String[] { "LANG::UserType", "[a-z]" });
                        }
                    }
                    if (address4 == FAILURE) {
                        offset = index4;
                        String chunk2 = null;
                        int max2 = offset + 1;
                        if (max2 <= inputSize) {
                            chunk2 = input.substring(offset, max2);
                        }
                        if (chunk2 != null && REGEX_7.matcher(chunk2).matches()) {
                            address4 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                            offset = offset + 1;
                        } else {
                            address4 = FAILURE;
                            if (offset > failure) {
                                failure = offset;
                                expected = new ArrayList<String[]>();
                            }
                            if (offset == failure) {
                                expected.add(new String[] { "LANG::UserType", "[0-9]" });
                            }
                        }
                        if (address4 == FAILURE) {
                            offset = index4;
                            String chunk3 = null;
                            int max3 = offset + 1;
                            if (max3 <= inputSize) {
                                chunk3 = input.substring(offset, max3);
                            }
                            if (chunk3 != null && chunk3.equals("_")) {
                                address4 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                                offset = offset + 1;
                            } else {
                                address4 = FAILURE;
                                if (offset > failure) {
                                    failure = offset;
                                    expected = new ArrayList<String[]>();
                                }
                                if (offset == failure) {
                                    expected.add(new String[] { "LANG::UserType", "\"_\"" });
                                }
                            }
                            if (address4 == FAILURE) {
                                offset = index4;
                                String chunk4 = null;
                                int max4 = offset + 1;
                                if (max4 <= inputSize) {
                                    chunk4 = input.substring(offset, max4);
                                }
                                if (chunk4 != null && REGEX_8.matcher(chunk4).matches()) {
                                    address4 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                                    offset = offset + 1;
                                } else {
                                    address4 = FAILURE;
                                    if (offset > failure) {
                                        failure = offset;
                                        expected = new ArrayList<String[]>();
                                    }
                                    if (offset == failure) {
                                        expected.add(new String[] { "LANG::UserType", "[A-Z]" });
                                    }
                                }
                                if (address4 == FAILURE) {
                                    offset = index4;
                                }
                            }
                        }
                    }
                    if (address4 != FAILURE) {
                        elements2.add(address4);
                    } else {
                        break;
                    }
                }
                if (elements2.size() >= 0) {
                    address3 = new TreeNode(input.substring(index3, offset), index3, elements2);
                    offset = offset;
                } else {
                    address3 = FAILURE;
                }
                if (address3 != FAILURE) {
                    elements1.add(1, address3);
                } else {
                    elements1 = null;
                    offset = index2;
                }
            } else {
                elements1 = null;
                offset = index2;
            }
            if (elements1 == null) {
                address1 = FAILURE;
            } else {
                address1 = new TreeNode(input.substring(index2, offset), index2, elements1);
                offset = offset;
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address5 = FAILURE;
                address5 = _read_Spacing();
                if (address5 != FAILURE) {
                    elements0.add(1, address5);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeUserType(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_LiteralCharacter() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.LiteralCharacter);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.LiteralCharacter, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(4);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("'")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::LiteralCharacter", "\"'\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                int index2 = offset;
                String chunk1 = null;
                int max1 = offset + 2;
                if (max1 <= inputSize) {
                    chunk1 = input.substring(offset, max1);
                }
                if (chunk1 != null && chunk1.equals("\\n")) {
                    address2 = new TreeNode(input.substring(offset, offset + 2), offset, new ArrayList<TreeNode>());
                    offset = offset + 2;
                } else {
                    address2 = FAILURE;
                    if (offset > failure) {
                        failure = offset;
                        expected = new ArrayList<String[]>();
                    }
                    if (offset == failure) {
                        expected.add(new String[] { "LANG::LiteralCharacter", "\"\\\\n\"" });
                    }
                }
                if (address2 == FAILURE) {
                    offset = index2;
                    String chunk2 = null;
                    int max2 = offset + 2;
                    if (max2 <= inputSize) {
                        chunk2 = input.substring(offset, max2);
                    }
                    if (chunk2 != null && chunk2.equals("\\r")) {
                        address2 = new TreeNode(input.substring(offset, offset + 2), offset, new ArrayList<TreeNode>());
                        offset = offset + 2;
                    } else {
                        address2 = FAILURE;
                        if (offset > failure) {
                            failure = offset;
                            expected = new ArrayList<String[]>();
                        }
                        if (offset == failure) {
                            expected.add(new String[] { "LANG::LiteralCharacter", "\"\\\\r\"" });
                        }
                    }
                    if (address2 == FAILURE) {
                        offset = index2;
                        String chunk3 = null;
                        int max3 = offset + 2;
                        if (max3 <= inputSize) {
                            chunk3 = input.substring(offset, max3);
                        }
                        if (chunk3 != null && chunk3.equals("\\t")) {
                            address2 = new TreeNode(input.substring(offset, offset + 2), offset, new ArrayList<TreeNode>());
                            offset = offset + 2;
                        } else {
                            address2 = FAILURE;
                            if (offset > failure) {
                                failure = offset;
                                expected = new ArrayList<String[]>();
                            }
                            if (offset == failure) {
                                expected.add(new String[] { "LANG::LiteralCharacter", "\"\\\\t\"" });
                            }
                        }
                        if (address2 == FAILURE) {
                            offset = index2;
                            String chunk4 = null;
                            int max4 = offset + 2;
                            if (max4 <= inputSize) {
                                chunk4 = input.substring(offset, max4);
                            }
                            if (chunk4 != null && chunk4.equals("\\b")) {
                                address2 = new TreeNode(input.substring(offset, offset + 2), offset, new ArrayList<TreeNode>());
                                offset = offset + 2;
                            } else {
                                address2 = FAILURE;
                                if (offset > failure) {
                                    failure = offset;
                                    expected = new ArrayList<String[]>();
                                }
                                if (offset == failure) {
                                    expected.add(new String[] { "LANG::LiteralCharacter", "\"\\\\b\"" });
                                }
                            }
                            if (address2 == FAILURE) {
                                offset = index2;
                                String chunk5 = null;
                                int max5 = offset + 2;
                                if (max5 <= inputSize) {
                                    chunk5 = input.substring(offset, max5);
                                }
                                if (chunk5 != null && chunk5.equals("\\\\")) {
                                    address2 = new TreeNode(input.substring(offset, offset + 2), offset, new ArrayList<TreeNode>());
                                    offset = offset + 2;
                                } else {
                                    address2 = FAILURE;
                                    if (offset > failure) {
                                        failure = offset;
                                        expected = new ArrayList<String[]>();
                                    }
                                    if (offset == failure) {
                                        expected.add(new String[] { "LANG::LiteralCharacter", "\"\\\\\\\\\"" });
                                    }
                                }
                                if (address2 == FAILURE) {
                                    offset = index2;
                                    if (offset < inputSize) {
                                        address2 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                                        offset = offset + 1;
                                    } else {
                                        address2 = FAILURE;
                                        if (offset > failure) {
                                            failure = offset;
                                            expected = new ArrayList<String[]>();
                                        }
                                        if (offset == failure) {
                                            expected.add(new String[] { "LANG::LiteralCharacter", "<any char>" });
                                        }
                                    }
                                    if (address2 == FAILURE) {
                                        offset = index2;
                                    }
                                }
                            }
                        }
                    }
                }
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                    TreeNode address3 = FAILURE;
                    String chunk6 = null;
                    int max6 = offset + 1;
                    if (max6 <= inputSize) {
                        chunk6 = input.substring(offset, max6);
                    }
                    if (chunk6 != null && chunk6.equals("'")) {
                        address3 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                        offset = offset + 1;
                    } else {
                        address3 = FAILURE;
                        if (offset > failure) {
                            failure = offset;
                            expected = new ArrayList<String[]>();
                        }
                        if (offset == failure) {
                            expected.add(new String[] { "LANG::LiteralCharacter", "\"'\"" });
                        }
                    }
                    if (address3 != FAILURE) {
                        elements0.add(2, address3);
                        TreeNode address4 = FAILURE;
                        address4 = _read_Spacing();
                        if (address4 != FAILURE) {
                            elements0.add(3, address4);
                        } else {
                            elements0 = null;
                            offset = index1;
                        }
                    } else {
                        elements0 = null;
                        offset = index1;
                    }
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeLiteralCharacter(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Integer() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Integer);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Integer, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            int index2 = offset;
            List<TreeNode> elements1 = new ArrayList<TreeNode>();
            TreeNode address2 = null;
            while (true) {
                String chunk0 = null;
                int max0 = offset + 1;
                if (max0 <= inputSize) {
                    chunk0 = input.substring(offset, max0);
                }
                if (chunk0 != null && REGEX_9.matcher(chunk0).matches()) {
                    address2 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                    offset = offset + 1;
                } else {
                    address2 = FAILURE;
                    if (offset > failure) {
                        failure = offset;
                        expected = new ArrayList<String[]>();
                    }
                    if (offset == failure) {
                        expected.add(new String[] { "LANG::Integer", "[0-9]" });
                    }
                }
                if (address2 != FAILURE) {
                    elements1.add(address2);
                } else {
                    break;
                }
            }
            if (elements1.size() >= 1) {
                address1 = new TreeNode(input.substring(index2, offset), index2, elements1);
                offset = offset;
            } else {
                address1 = FAILURE;
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address3 = FAILURE;
                address3 = _read_Spacing();
                if (address3 != FAILURE) {
                    elements0.add(1, address3);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeInteger(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Decimal() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Decimal);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Decimal, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            int index2 = offset;
            List<TreeNode> elements1 = new ArrayList<TreeNode>(3);
            TreeNode address2 = FAILURE;
            int index3 = offset;
            List<TreeNode> elements2 = new ArrayList<TreeNode>();
            TreeNode address3 = null;
            while (true) {
                String chunk0 = null;
                int max0 = offset + 1;
                if (max0 <= inputSize) {
                    chunk0 = input.substring(offset, max0);
                }
                if (chunk0 != null && REGEX_10.matcher(chunk0).matches()) {
                    address3 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                    offset = offset + 1;
                } else {
                    address3 = FAILURE;
                    if (offset > failure) {
                        failure = offset;
                        expected = new ArrayList<String[]>();
                    }
                    if (offset == failure) {
                        expected.add(new String[] { "LANG::Decimal", "[0-9]" });
                    }
                }
                if (address3 != FAILURE) {
                    elements2.add(address3);
                } else {
                    break;
                }
            }
            if (elements2.size() >= 0) {
                address2 = new TreeNode(input.substring(index3, offset), index3, elements2);
                offset = offset;
            } else {
                address2 = FAILURE;
            }
            if (address2 != FAILURE) {
                elements1.add(0, address2);
                TreeNode address4 = FAILURE;
                address4 = _read_Dot();
                if (address4 != FAILURE) {
                    elements1.add(1, address4);
                    TreeNode address5 = FAILURE;
                    int index4 = offset;
                    List<TreeNode> elements3 = new ArrayList<TreeNode>();
                    TreeNode address6 = null;
                    while (true) {
                        String chunk1 = null;
                        int max1 = offset + 1;
                        if (max1 <= inputSize) {
                            chunk1 = input.substring(offset, max1);
                        }
                        if (chunk1 != null && REGEX_11.matcher(chunk1).matches()) {
                            address6 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                            offset = offset + 1;
                        } else {
                            address6 = FAILURE;
                            if (offset > failure) {
                                failure = offset;
                                expected = new ArrayList<String[]>();
                            }
                            if (offset == failure) {
                                expected.add(new String[] { "LANG::Decimal", "[0-9]" });
                            }
                        }
                        if (address6 != FAILURE) {
                            elements3.add(address6);
                        } else {
                            break;
                        }
                    }
                    if (elements3.size() >= 1) {
                        address5 = new TreeNode(input.substring(index4, offset), index4, elements3);
                        offset = offset;
                    } else {
                        address5 = FAILURE;
                    }
                    if (address5 != FAILURE) {
                        elements1.add(2, address5);
                    } else {
                        elements1 = null;
                        offset = index2;
                    }
                } else {
                    elements1 = null;
                    offset = index2;
                }
            } else {
                elements1 = null;
                offset = index2;
            }
            if (elements1 == null) {
                address1 = FAILURE;
            } else {
                address1 = new TreeNode48(input.substring(index2, offset), index2, elements1);
                offset = offset;
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address7 = FAILURE;
                address7 = _read_Spacing();
                if (address7 != FAILURE) {
                    elements0.add(1, address7);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeDecimal(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_TyInt() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.TyInt);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.TyInt, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 3;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("Int")) {
                address1 = new TreeNode(input.substring(offset, offset + 3), offset, new ArrayList<TreeNode>());
                offset = offset + 3;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::TyInt", "\"Int\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeTyInt(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_TyChar() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.TyChar);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.TyChar, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 4;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("Char")) {
                address1 = new TreeNode(input.substring(offset, offset + 4), offset, new ArrayList<TreeNode>());
                offset = offset + 4;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::TyChar", "\"Char\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeTyChar(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_TyBool() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.TyBool);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.TyBool, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 4;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("Bool")) {
                address1 = new TreeNode(input.substring(offset, offset + 4), offset, new ArrayList<TreeNode>());
                offset = offset + 4;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::TyBool", "\"Bool\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeTyBool(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_TyFloat() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.TyFloat);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.TyFloat, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 5;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("Float")) {
                address1 = new TreeNode(input.substring(offset, offset + 5), offset, new ArrayList<TreeNode>());
                offset = offset + 5;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::TyFloat", "\"Float\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeTyFloat(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Null() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Null);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Null, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 4;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("null")) {
                address1 = new TreeNode(input.substring(offset, offset + 4), offset, new ArrayList<TreeNode>());
                offset = offset + 4;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Null", "\"null\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeNull(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Return() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Return);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Return, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 6;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("return")) {
                address1 = new TreeNode(input.substring(offset, offset + 6), offset, new ArrayList<TreeNode>());
                offset = offset + 6;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Return", "\"return\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode54(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_New() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.New);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.New, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 3;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("new")) {
                address1 = new TreeNode(input.substring(offset, offset + 3), offset, new ArrayList<TreeNode>());
                offset = offset + 3;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::New", "\"new\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode55(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_If() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.If);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.If, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 2;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("if")) {
                address1 = new TreeNode(input.substring(offset, offset + 2), offset, new ArrayList<TreeNode>());
                offset = offset + 2;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::If", "\"if\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode56(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Else() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Else);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Else, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 4;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("else")) {
                address1 = new TreeNode(input.substring(offset, offset + 4), offset, new ArrayList<TreeNode>());
                offset = offset + 4;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Else", "\"else\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode57(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Iterate() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Iterate);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Iterate, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 7;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("iterate")) {
                address1 = new TreeNode(input.substring(offset, offset + 7), offset, new ArrayList<TreeNode>());
                offset = offset + 7;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Iterate", "\"iterate\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode58(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Print() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Print);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Print, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 5;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("print")) {
                address1 = new TreeNode(input.substring(offset, offset + 5), offset, new ArrayList<TreeNode>());
                offset = offset + 5;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Print", "\"print\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode59(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Read() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Read);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Read, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 4;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("read")) {
                address1 = new TreeNode(input.substring(offset, offset + 4), offset, new ArrayList<TreeNode>());
                offset = offset + 4;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Read", "\"read\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode60(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Data() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Data);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Data, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 4;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("data")) {
                address1 = new TreeNode(input.substring(offset, offset + 4), offset, new ArrayList<TreeNode>());
                offset = offset + 4;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Data", "\"data\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode61(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_ColonColon() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.ColonColon);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.ColonColon, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 2;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("::")) {
                address1 = new TreeNode(input.substring(offset, offset + 2), offset, new ArrayList<TreeNode>());
                offset = offset + 2;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::ColonColon", "\"::\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode62(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Colon() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Colon);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Colon, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals(":")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Colon", "\":\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode63(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_LPar() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.LPar);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.LPar, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("(")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::LPar", "\"(\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode64(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_RPar() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.RPar);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.RPar, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals(")")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::RPar", "\")\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode65(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_LBrack() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.LBrack);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.LBrack, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("[")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::LBrack", "\"[\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode66(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_RBrack() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.RBrack);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.RBrack, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("]")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::RBrack", "\"]\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode67(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_LBrace() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.LBrace);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.LBrace, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("{")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::LBrace", "\"{\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode68(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_RBrace() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.RBrace);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.RBrace, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("}")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::RBrace", "\"}\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode69(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Gt() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Gt);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Gt, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals(">")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Gt", "\">\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode70(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Lt() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Lt);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Lt, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("<")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Lt", "\"<\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode71(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Semi() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Semi);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Semi, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals(";")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Semi", "\";\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode72(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Dot() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Dot);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Dot, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals(".")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Dot", "\".\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode73(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Comma() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Comma);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Comma, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals(",")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Comma", "\",\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode74(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Eq() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Eq);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Eq, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("=")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Eq", "\"=\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode75(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Eqeq() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Eqeq);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Eqeq, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 2;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("==")) {
                address1 = new TreeNode(input.substring(offset, offset + 2), offset, new ArrayList<TreeNode>());
                offset = offset + 2;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Eqeq", "\"==\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode76(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Ne() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Ne);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Ne, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 2;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("!=")) {
                address1 = new TreeNode(input.substring(offset, offset + 2), offset, new ArrayList<TreeNode>());
                offset = offset + 2;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Ne", "\"!=\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode77(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Plus() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Plus);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Plus, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("+")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Plus", "\"+\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode78(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Minus() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Minus);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Minus, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("-")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Minus", "\"-\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode79(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Times() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Times);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Times, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("*")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Times", "\"*\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode80(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Div() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Div);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Div, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("/")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Div", "\"/\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode81(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Mod() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Mod);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Mod, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("%")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Mod", "\"%\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode82(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_And() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.And);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.And, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 2;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("&&")) {
                address1 = new TreeNode(input.substring(offset, offset + 2), offset, new ArrayList<TreeNode>());
                offset = offset + 2;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::And", "\"&&\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode83(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Not() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Not);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Not, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("!")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Not", "\"!\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode84(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Spacing() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Spacing);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Spacing, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>();
            TreeNode address1 = null;
            while (true) {
                int index2 = offset;
                address1 = _read_Space();
                if (address1 == FAILURE) {
                    offset = index2;
                    address1 = _read_Comment();
                    if (address1 == FAILURE) {
                        offset = index2;
                    }
                }
                if (address1 != FAILURE) {
                    elements0.add(address1);
                } else {
                    break;
                }
            }
            if (elements0.size() >= 0) {
                address0 = new TreeNode(input.substring(index1, offset), index1, elements0);
                offset = offset;
            } else {
                address0 = FAILURE;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_True() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.True);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.True, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 4;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("true")) {
                address1 = new TreeNode(input.substring(offset, offset + 4), offset, new ArrayList<TreeNode>());
                offset = offset + 4;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::True", "\"true\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeTrue(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_False() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.False);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.False, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 5;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("false")) {
                address1 = new TreeNode(input.substring(offset, offset + 5), offset, new ArrayList<TreeNode>());
                offset = offset + 5;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::False", "\"false\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                address2 = _read_Spacing();
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.MakeFalse(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Comment() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Comment);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Comment, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            int index2 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(3);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            int max0 = offset + 2;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("--")) {
                address1 = new TreeNode(input.substring(offset, offset + 2), offset, new ArrayList<TreeNode>());
                offset = offset + 2;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Comment", "\"--\"" });
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                int index3 = offset;
                List<TreeNode> elements1 = new ArrayList<TreeNode>();
                TreeNode address3 = null;
                while (true) {
                    int index4 = offset;
                    List<TreeNode> elements2 = new ArrayList<TreeNode>(2);
                    TreeNode address4 = FAILURE;
                    int index5 = offset;
                    address4 = _read_EndOfLine();
                    offset = index5;
                    if (address4 == FAILURE) {
                        address4 = new TreeNode(input.substring(offset, offset), offset, new ArrayList<TreeNode>());
                        offset = offset;
                    } else {
                        address4 = FAILURE;
                    }
                    if (address4 != FAILURE) {
                        elements2.add(0, address4);
                        TreeNode address5 = FAILURE;
                        if (offset < inputSize) {
                            address5 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                            offset = offset + 1;
                        } else {
                            address5 = FAILURE;
                            if (offset > failure) {
                                failure = offset;
                                expected = new ArrayList<String[]>();
                            }
                            if (offset == failure) {
                                expected.add(new String[] { "LANG::Comment", "<any char>" });
                            }
                        }
                        if (address5 != FAILURE) {
                            elements2.add(1, address5);
                        } else {
                            elements2 = null;
                            offset = index4;
                        }
                    } else {
                        elements2 = null;
                        offset = index4;
                    }
                    if (elements2 == null) {
                        address3 = FAILURE;
                    } else {
                        address3 = new TreeNode(input.substring(index4, offset), index4, elements2);
                        offset = offset;
                    }
                    if (address3 != FAILURE) {
                        elements1.add(address3);
                    } else {
                        break;
                    }
                }
                if (elements1.size() >= 0) {
                    address2 = new TreeNode(input.substring(index3, offset), index3, elements1);
                    offset = offset;
                } else {
                    address2 = FAILURE;
                }
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                    TreeNode address6 = FAILURE;
                    address6 = _read_EndOfLine();
                    if (address6 != FAILURE) {
                        elements0.add(2, address6);
                    } else {
                        elements0 = null;
                        offset = index2;
                    }
                } else {
                    elements0 = null;
                    offset = index2;
                }
            } else {
                elements0 = null;
                offset = index2;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode87(input.substring(index2, offset), index2, elements0);
                offset = offset;
            }
            if (address0 == FAILURE) {
                offset = index1;
                int index6 = offset;
                List<TreeNode> elements3 = new ArrayList<TreeNode>(3);
                TreeNode address7 = FAILURE;
                String chunk1 = null;
                int max1 = offset + 2;
                if (max1 <= inputSize) {
                    chunk1 = input.substring(offset, max1);
                }
                if (chunk1 != null && chunk1.equals("{-")) {
                    address7 = new TreeNode(input.substring(offset, offset + 2), offset, new ArrayList<TreeNode>());
                    offset = offset + 2;
                } else {
                    address7 = FAILURE;
                    if (offset > failure) {
                        failure = offset;
                        expected = new ArrayList<String[]>();
                    }
                    if (offset == failure) {
                        expected.add(new String[] { "LANG::Comment", "\"{-\"" });
                    }
                }
                if (address7 != FAILURE) {
                    elements3.add(0, address7);
                    TreeNode address8 = FAILURE;
                    int index7 = offset;
                    List<TreeNode> elements4 = new ArrayList<TreeNode>();
                    TreeNode address9 = null;
                    while (true) {
                        int index8 = offset;
                        List<TreeNode> elements5 = new ArrayList<TreeNode>(2);
                        TreeNode address10 = FAILURE;
                        int index9 = offset;
                        String chunk2 = null;
                        int max2 = offset + 2;
                        if (max2 <= inputSize) {
                            chunk2 = input.substring(offset, max2);
                        }
                        if (chunk2 != null && chunk2.equals("-}")) {
                            address10 = new TreeNode(input.substring(offset, offset + 2), offset, new ArrayList<TreeNode>());
                            offset = offset + 2;
                        } else {
                            address10 = FAILURE;
                            if (offset > failure) {
                                failure = offset;
                                expected = new ArrayList<String[]>();
                            }
                            if (offset == failure) {
                                expected.add(new String[] { "LANG::Comment", "\"-}\"" });
                            }
                        }
                        offset = index9;
                        if (address10 == FAILURE) {
                            address10 = new TreeNode(input.substring(offset, offset), offset, new ArrayList<TreeNode>());
                            offset = offset;
                        } else {
                            address10 = FAILURE;
                        }
                        if (address10 != FAILURE) {
                            elements5.add(0, address10);
                            TreeNode address11 = FAILURE;
                            if (offset < inputSize) {
                                address11 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                                offset = offset + 1;
                            } else {
                                address11 = FAILURE;
                                if (offset > failure) {
                                    failure = offset;
                                    expected = new ArrayList<String[]>();
                                }
                                if (offset == failure) {
                                    expected.add(new String[] { "LANG::Comment", "<any char>" });
                                }
                            }
                            if (address11 != FAILURE) {
                                elements5.add(1, address11);
                            } else {
                                elements5 = null;
                                offset = index8;
                            }
                        } else {
                            elements5 = null;
                            offset = index8;
                        }
                        if (elements5 == null) {
                            address9 = FAILURE;
                        } else {
                            address9 = new TreeNode(input.substring(index8, offset), index8, elements5);
                            offset = offset;
                        }
                        if (address9 != FAILURE) {
                            elements4.add(address9);
                        } else {
                            break;
                        }
                    }
                    if (elements4.size() >= 0) {
                        address8 = new TreeNode(input.substring(index7, offset), index7, elements4);
                        offset = offset;
                    } else {
                        address8 = FAILURE;
                    }
                    if (address8 != FAILURE) {
                        elements3.add(1, address8);
                        TreeNode address12 = FAILURE;
                        String chunk3 = null;
                        int max3 = offset + 2;
                        if (max3 <= inputSize) {
                            chunk3 = input.substring(offset, max3);
                        }
                        if (chunk3 != null && chunk3.equals("-}")) {
                            address12 = new TreeNode(input.substring(offset, offset + 2), offset, new ArrayList<TreeNode>());
                            offset = offset + 2;
                        } else {
                            address12 = FAILURE;
                            if (offset > failure) {
                                failure = offset;
                                expected = new ArrayList<String[]>();
                            }
                            if (offset == failure) {
                                expected.add(new String[] { "LANG::Comment", "\"-}\"" });
                            }
                        }
                        if (address12 != FAILURE) {
                            elements3.add(2, address12);
                        } else {
                            elements3 = null;
                            offset = index6;
                        }
                    } else {
                        elements3 = null;
                        offset = index6;
                    }
                } else {
                    elements3 = null;
                    offset = index6;
                }
                if (elements3 == null) {
                    address0 = FAILURE;
                } else {
                    address0 = new TreeNode(input.substring(index6, offset), index6, elements3);
                    offset = offset;
                }
                if (address0 == FAILURE) {
                    offset = index1;
                }
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_Space() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.Space);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.Space, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            String chunk0 = null;
            int max0 = offset + 1;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals(" ")) {
                address0 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address0 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::Space", "\" \"" });
                }
            }
            if (address0 == FAILURE) {
                offset = index1;
                String chunk1 = null;
                int max1 = offset + 1;
                if (max1 <= inputSize) {
                    chunk1 = input.substring(offset, max1);
                }
                if (chunk1 != null && chunk1.equals("\t")) {
                    address0 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                    offset = offset + 1;
                } else {
                    address0 = FAILURE;
                    if (offset > failure) {
                        failure = offset;
                        expected = new ArrayList<String[]>();
                    }
                    if (offset == failure) {
                        expected.add(new String[] { "LANG::Space", "\"\\t\"" });
                    }
                }
                if (address0 == FAILURE) {
                    offset = index1;
                    address0 = _read_EndOfLine();
                    if (address0 == FAILURE) {
                        offset = index1;
                    }
                }
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_EndOfLine() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.EndOfLine);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.EndOfLine, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            String chunk0 = null;
            int max0 = offset + 2;
            if (max0 <= inputSize) {
                chunk0 = input.substring(offset, max0);
            }
            if (chunk0 != null && chunk0.equals("\r\n")) {
                address0 = new TreeNode(input.substring(offset, offset + 2), offset, new ArrayList<TreeNode>());
                offset = offset + 2;
            } else {
                address0 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::EndOfLine", "\"\\r\\n\"" });
                }
            }
            if (address0 == FAILURE) {
                offset = index1;
                String chunk1 = null;
                int max1 = offset + 1;
                if (max1 <= inputSize) {
                    chunk1 = input.substring(offset, max1);
                }
                if (chunk1 != null && chunk1.equals("\n")) {
                    address0 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                    offset = offset + 1;
                } else {
                    address0 = FAILURE;
                    if (offset > failure) {
                        failure = offset;
                        expected = new ArrayList<String[]>();
                    }
                    if (offset == failure) {
                        expected.add(new String[] { "LANG::EndOfLine", "\"\\n\"" });
                    }
                }
                if (address0 == FAILURE) {
                    offset = index1;
                    String chunk2 = null;
                    int max2 = offset + 1;
                    if (max2 <= inputSize) {
                        chunk2 = input.substring(offset, max2);
                    }
                    if (chunk2 != null && chunk2.equals("\r")) {
                        address0 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                        offset = offset + 1;
                    } else {
                        address0 = FAILURE;
                        if (offset > failure) {
                            failure = offset;
                            expected = new ArrayList<String[]>();
                        }
                        if (offset == failure) {
                            expected.add(new String[] { "LANG::EndOfLine", "\"\\r\"" });
                        }
                    }
                    if (address0 == FAILURE) {
                        offset = index1;
                    }
                }
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_EndOfFile() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.EndOfFile);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.EndOfFile, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            if (offset < inputSize) {
                address0 = new TreeNode(input.substring(offset, offset + 1), offset, new ArrayList<TreeNode>());
                offset = offset + 1;
            } else {
                address0 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String[]>();
                }
                if (offset == failure) {
                    expected.add(new String[] { "LANG::EndOfFile", "<any char>" });
                }
            }
            offset = index1;
            if (address0 == FAILURE) {
                address0 = new TreeNode(input.substring(offset, offset), offset, new ArrayList<TreeNode>());
                offset = offset;
            } else {
                address0 = FAILURE;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }
}
