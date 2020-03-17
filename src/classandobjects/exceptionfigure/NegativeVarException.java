package classandobjects.exceptionfigure;

public class NegativeVarException extends Exception
{
    private double variable;

    public NegativeVarException(double variable)
    {
        this.variable = variable;
    }

    public String toString()
    {
        return String.format("NegativeVarException [%.2f]",variable);
    }
}
