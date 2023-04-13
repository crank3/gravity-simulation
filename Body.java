import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;

/*
This class represents Celestial Bodies, which are circles with
a mass, radius, velocity, and other attributes.
 */

public class Body
{
    private final double scale = 1000.0;  // Number that all calculations are divided by
    private double x;  // Center's x-coordinate
    private double y;  // Center's y-coordinate
    private double r;  // Radius of body (kilometers)
    private double m;  // Mass of body (kilograms)
    private double dx;  // Velocity's x-component (kilometers per second)
    private double dy;  // Velocity's y-component (kilometers per second)
    private Color color;  // Color of body


    public Body()
    {
        this.x = 1000.0 / 2.0;
        this.y = 720.0 / 2.0;
        this.r = 5 + (m / 10);
        this.m = 10;
        this.dx = 0.0;
        this.dy = 0.0;
        this.color = new Color(0, 100, 255);
    }

    public Body(double x, double y, double r, double m, double dx, double dy, Color color)
    {
        this.x = x;
        this.y = y;
        this.r = 5 + (m / 10);
        this.m = m;
        this.dx = dx;
        this.dy = dy;
        this.color = color;
    }

    // Methods






    // Getters

    public double getX()
    {
        return this.x;
    }

    public double getY()
    {
        return this.y;
    }

    public double getR()
    {
        return this.r;
    }

    public double getM()
    {
        return this.m;
    }

    public double getDx()
    {
        return this.dx;
    }

    public double getDy()
    {
        return this.dy;
    }

    public Color getColor()
    {
        return this.color;
    }


    // Setters

    public void setX(double temp_x)
    {
        this.x = temp_x;
    }

    public void setY(double temp_y)
    {
        this.y = temp_y;
    }

    public void setR(double temp_r)
    {
        this.r = temp_r;
    }

    public void setM(double temp_m)
    {
        this.m = temp_m;
    }

    public void setDx(double temp_dx)
    {
        this.dx = temp_dx;
    }

    public void setDy(double temp_dy)
    {
        this.dy = temp_dy;
    }

    public void setColor(Color temp_color)
    {
        this.color = temp_color;
    }



}
