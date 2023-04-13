import java.awt.*;
import java.lang.Math;

public class Vector
{
    private double xi;  // Start x-coordinate
    private double yi;  // Start y-coordinate
    private double xt;  // End x-coordinate
    private double yt;  // End y-coordinate
    private double l;  // Length
    private Color color;  // Color of body

    public Vector()
    {
        this.xi = 0.0;
        this.yi = 0.0;
        this.xt = 0.0;
        this.yt = 0.0;
        this.l = 0.0;
        this.color = new Color(255, 255, 255);
    }

    public Vector(double xi, double yi, double xt, double yt, Color color)
    {
        this.xi = xi;
        this.yi = yi;
        this.xt = xt;
        this.yt = yt;
        this.l = Math.sqrt(Math.pow(xi - xt, 2) + Math.pow(yi - yt, 2));
        this.color = color;
    }

    // Methods
    public void drawVector(Graphics2D graphicsObj)
    {
        graphicsObj.setColor(this.color);
        graphicsObj.drawLine((int)xi, (int)(yi), (int)xt, (int)(yt));
        double radius = 4.0;
        graphicsObj.fillOval((int) (xi - radius), (int) (yi - radius), (int) (radius * 2), (int) (radius * 2));

        graphicsObj.setColor(Color.WHITE);
        graphicsObj.drawString("Velocity: "+Math.round(this.l / 10), (int)xt, (int)(yt));
    }



    // Getters

    public double getXi()
    {
        return this.xi;
    }

    public double getYi()
    {
        return this.yi;
    }

    public double getXt()
    {
        return this.xt;
    }

    public double getYt()
    {
        return this.yt;
    }

    public double getL()
    {
        return this.l;
    }

    public Color getColor()
    {
        return this.color;
    }


    // Setters

    public void setXi(double xi_temp)
    {
        this.xi = xi_temp;
        this.l = Math.sqrt(Math.pow(xi - xt, 2) + Math.pow(yi - yt, 2));
    }

    public void setYi(double yi_temp)
    {
        this.yi = yi_temp;
        this.l = Math.sqrt(Math.pow(xi - xt, 2) + Math.pow(yi - yt, 2));
    }

    public void setXt(double xt_temp)
    {
        this.xt = xt_temp;
        this.l = Math.sqrt(Math.pow(xi - xt, 2) + Math.pow(yi - yt, 2));
    }

    public void setYt(double yt_temp)
    {
        this.yt = yt_temp;
        this.l = Math.sqrt(Math.pow(xi - xt, 2) + Math.pow(yi - yt, 2));
    }

    public void setColor(Color color_temp)
    {
        this.color = color_temp;
    }


}
