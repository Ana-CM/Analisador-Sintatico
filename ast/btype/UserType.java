package ast.btype;

/**
 * UserType
 */
public class UserType extends BType {

    private String name;

    public UserType(String name) {
        this.name = name;
    }

    public boolean match(BType t) {
        return t instanceof UserType && ((UserType) t).name.equals(name);
    }

    public String toString() {
        return this.name;
    }
}
