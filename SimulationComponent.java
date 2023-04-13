import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.util.ArrayList;
import java.lang.Math;
import java.awt.event.ActionEvent;


public class SimulationComponent extends JComponent implements ActionListener, MouseListener
{

    private int WIDTH;
    private int HEIGHT;
    private Color color;

    private ArrayList<Body> bodies = new ArrayList<Body>();
    private Vector adding_object_vector = new Vector();
    public boolean paused = false;
    public boolean send_temp_body = false;
    public boolean get_temp_body = false;

    public Body temp_body = new Body();

    public SimulationComponent(int width, int height, Color color)
    {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.color = color;
    }


    // Methods

    public void clearBodies()
    {
        bodies.clear();
    }

    public void setTempBody(double x, double y, double r, double m, double dx, double dy, Color color)
    {
        temp_body = new Body(x, y, r, m, dx, dy, color);
    }

    public void addBody(Body body)
    {
        bodies.add(body);
    }

    public void delBody(Body body)
    {
        bodies.remove(body);
    }

    public int bodiesSize()
    {
        return bodies.size();
    }

    public Body getBody(int body_index)
    {
        return bodies.get(body_index);
    }





    @Override
    public void paintComponent(Graphics g)
    {
        // Cast to Graphics2D
        Graphics2D graphicsObj = (Graphics2D) g;

        // Draw Background
        Rectangle binRectangle = new Rectangle(0, 0, WIDTH, HEIGHT);
        graphicsObj.setColor(color);
        graphicsObj.fill(binRectangle);

        if (!paused)
        {
            // Update Bodies Velocity

            for (int i = 0; i < bodiesSize(); i++) {
                Body body1 = bodies.get(i);

                for (int j = 0; j < bodiesSize(); j++) {
                    Body body2 = bodies.get(j);
                    if (body1 != body2) {
                        double dx = body1.getDx();
                        double dy = body1.getDy();

                        double dist_x = body2.getX() - body1.getX();
                        double dist_y = body2.getY() - body1.getY();
                        double dist = Math.sqrt(Math.pow(dist_x, 2) + Math.pow(dist_y, 2));

                        double unit_x = dist_x / dist;
                        double unit_y = dist_y / dist;

                        double accel = (body2.getM()) / Math.abs(dist);

                        double ax = unit_x * accel;
                        double ay = unit_y * accel;


                        body1.setDx(dx + ax);
                        body1.setDy(dy + ay);
                    }
                }
            }


            // Move Bodies
            ArrayList<Body> bodies_after = new ArrayList<Body>();
            for (int i = 0; i < bodiesSize(); i++) {
                Body body = bodies.get(i);

                double new_x = body.getX() + body.getDx();
                double new_y = body.getY() + body.getDy();

                Body new_body = new Body(new_x, new_y, body.getR(), body.getM(), body.getDx(), body.getDy(), body.getColor());

                bodies_after.add(new_body);
            }
            bodies = bodies_after;
        }
        // Draw Bodies
        for (int i = 0; i < bodiesSize(); i++) {
            Body body = getBody(i);

            graphicsObj.setColor(body.getColor());
            graphicsObj.fillOval((int) (body.getX() - body.getR()), (int) (body.getY() - body.getR()), (int) (body.getR() * 2.0), (int) (body.getR() * 2.0));
        }


        // Draw Vector
        if (adding_object_vector.getXt() != 0.0)
        {
            adding_object_vector.drawVector(graphicsObj);
        }




    }

    public void actionPerformed(ActionEvent e)
    {
        if ("pause".equals(e.getActionCommand())) {
            this.paused = !paused;
            System.out.println(this.paused);
        }

        if ("add_body_button".equals(e.getActionCommand())) {
            System.out.println("Adding body!");
            send_temp_body = true;
        }

        if ("clear_button".equals(e.getActionCommand())) {
            System.out.println("Clearing bodies!");
            clearBodies();
        }
    }


    public void mousePressed(MouseEvent e) {
        System.out.println("MOUSE DOWN");

        if (adding_object_vector.getXt() != 0.0)
        {
            adding_object_vector = new Vector();
        }

        get_temp_body = !get_temp_body;
        PointerInfo pointer_info = MouseInfo.getPointerInfo();
        Point mouse_point = pointer_info.getLocation();
        int mouse_x = e.getX();
        int mouse_y = e.getY();

        System.out.println("mouse_x: "+mouse_x+", mouse_y: "+mouse_y);

        temp_body.setX(mouse_x);
        temp_body.setY(mouse_y);

        adding_object_vector.setXi(mouse_x);
        adding_object_vector.setYi(mouse_y);
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("MOUSE UP");

        get_temp_body = !get_temp_body;
        PointerInfo pointer_info = MouseInfo.getPointerInfo();
        Point mouse_point = pointer_info.getLocation();
        int mouse_x = e.getX();
        int mouse_y = e.getY();

        double down_x = temp_body.getX();
        double down_y = temp_body.getY();

        double dx_temp = (mouse_x - down_x) / 10;
        double dy_temp = (mouse_y - down_y) / 10;

        temp_body.setDx(dx_temp);
        temp_body.setDy(dy_temp);

        adding_object_vector.setXt(mouse_x);
        adding_object_vector.setYt(mouse_y);
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("MOUSE ENTERED SIM");
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("MOUSE LEFT SIM");
    }

    public void mouseClicked(MouseEvent e) {
        // means that mouse clicked without dragging
        System.out.println("MOUSE CLICKED (WITHOUT DRAGGING)");
    }

}
