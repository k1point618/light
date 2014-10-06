package util;

import java.util.ArrayList;
import java.util.List;
/**
 * Goal has a name, a set of arguments,
 * 
 * @author kerengu
 * 
 */
public class Goal {

    private String name;
    private List<String> args;
    private int numArgs;

    public Goal(String name) {
        this.name = name;
        args = null;
    }

    public Goal(String name, List<String> args) {
        this.name = name;
        if (args != null) {
            this.args = args;
            this.numArgs = args.size();
        }
    }

    public Goal(String name, int numArgs) {
        this.name = name;
        this.args = null;
        this.numArgs = numArgs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArgs(ArrayList<String> args) {
        this.args = args;
        this.numArgs = args.size();
    }
    
    public String getName() {
        return name;
    }

    public List<String> getArgs() {
        return args;
    }
    
    public int getNumArgs() {
        return numArgs;
    }

    // Checks whether the state satisfies the set of conditions
    public boolean achieved(State state) {
        return false;
    }    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((args == null) ? 0 : args.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + numArgs;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Goal other = (Goal) obj;
        if (args == null) {
            if (other.args != null)
                return false;
        } else if (!args.equals(other.args))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (numArgs != other.numArgs)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Goal [name=" + name + ", args=" + args + ", numArgs=" + numArgs
                + "]";
    }

}
