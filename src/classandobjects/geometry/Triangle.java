package classandobjects.geometry;
import classandobjects.exceptionfigure.NegativeVarException;

public class Triangle
{
    private double a;
    private double b;
    private double c;
    private double pPer;
    public Triangle(double a, double b, double c) throws NegativeVarException
    {
        if(a < 0)
        {
            throw new NegativeVarException(a);
        }
        else if(b < 0)
        {
            throw new NegativeVarException(b);
        }
        else if (c < 0)
        {
            throw new NegativeVarException(c);
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }



    public double TriangleSquareCalc()
    {
        pPer = this.a + this.b + this.c ; //полупериметр для формулы Герона
        return Math.sqrt(pPer*(pPer - this.a)*(pPer - this.b)*(pPer - this.c)); //формула Герона
    }

    public double TrianglePerimeterCalc()
    {
        return a + b + c;
    }

    @Override
    public String toString()
    {
        return String.format("Triangle (%.2f, %.2f, %.2f)\nTriang.Square = %.2f\nTriang.Perimeter = %.2f",a,b,c,TriangleSquareCalc(),TrianglePerimeterCalc());
    }
}
