package classandobjects.geometry;
import classandobjects.exceptionfigure.NegativeVarException;

public class Circle
{
    private double radius;

    public Circle(double radius) throws NegativeVarException
    {
        if(radius < 0)
        {
            System.out.println("Radius can't be wrong!");
            throw new NegativeVarException(radius);
        }
        this.radius = radius;
    }

    public double getRadius()
    {
        return radius;
    }

    public double CircleLengthCalc()
    {
        return 2*Math.PI*radius;
    }

    public double CircleSquareCalc()
    {
        return Math.pow(Math.PI*radius,2);
    }

    @Override
    public String toString()
    {
        return String.format("Circ.Radius = %.2f\nCirc.Square = %.2f\nCirc.Length = %.2f",radius,CircleSquareCalc(),CircleLengthCalc());
    }
}
