package langUtil.cellMachine;

import java.util.Stack;

public abstract class Value {
     
     public abstract int asInt();
     public abstract boolean asBool();
     public abstract float asFloat();
     public abstract char asChar();
     public abstract boolean equals(Value v);

}
