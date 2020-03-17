package classandobjects.geometry;

import com.sun.corba.se.impl.encoding.CDRInputObject;
import classandobjects.exceptionfigure.NegativeVarException;

public class Rectangle
{
    private double width;
    private double height;

    public double getWidth()
    {
        return width;
    }

    public double getHeight()
    {
        return height;
    }

    public Rectangle(double width, double height) throws NegativeVarException
    {
        if(width < 0)
        {
            System.out.println("Width can't be wrong!");
            throw new NegativeVarException(width);
        }
        else if (height < 0)
        {
            System.out.println("Height can't be wrong!");
            throw new NegativeVarException(height);
        }
        this.width = width;
        this.height = height;
    }

    public double RectangleSquareCalc()
    {
        return width * height;
    }

    public double RectanglePerimeterCalc()
    {
        return (width + height) / 2;
    }

    @Override
    public String toString()
    {
        return String.format("Rec.width = %.2f\nRec.height = %.2f\nRec.Square = %.2f\nRec.Perimeter = %.2f",width,height,RectangleSquareCalc(),RectanglePerimeterCalc());
    }
}
