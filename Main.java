/*
Holden Kenney
CMPSC 221: Final Project

Celestial Body Simulation:
This program simulates the interactions between celestial bodies
brought about by the gravitational forces between them.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread;
import java.lang.Math;
import java.util.Random;
import java.util.Scanner;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import javax.swing.border.Border;


// replit requires the client class to be renamed as Main, rather than HistogramViewer as in zyBook
public class Main{

    public static void main(String[] args) {

        // Constants
        int WIDTH = (int) (1300);
        int HEIGHT = (int) (720);
        int top_margin = 50;

        Random rand = new Random(); // Making random number generator

        System.out.println(
                "\nREAD ----------->  If nothing else happens after this print statement," +
                        " then for some reason the code stalls at making a new" +
                        " JFrame and there is nothing I can do about it...");
        JFrame appFrame = new JFrame("Celestial Body Simulation"); // Making jframe
        //appFrame.setLayout(new FlowLayout());
        appFrame.setSize(WIDTH, HEIGHT);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // BorderLayout





        // Static JComponents

        // Simulation Window
        Border border_sim = BorderFactory.createLineBorder(Color.BLUE);

        SimulationComponent sim = new SimulationComponent(WIDTH, HEIGHT - top_margin, Color.BLACK);
        sim.setBorder(border_sim);
        sim.addMouseListener(sim);
        appFrame.add(sim, BorderLayout.CENTER);


        // (Top of Page)

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout());
        textPanel.setSize(WIDTH, top_margin);
        appFrame.add(textPanel, BorderLayout.PAGE_START);

        Border border_text = BorderFactory.createLineBorder(Color.BLACK);
        Dimension dimension_text_entry = new Dimension(50, 25);

        JToggleButton pause_button = new JToggleButton("Pause / Play");
        pause_button.setBorder(border_text);
        pause_button.setPreferredSize(new Dimension(100, 50));
        pause_button.setActionCommand("pause");
        pause_button.addActionListener(sim);
        textPanel.add(pause_button);


        // x text object
        JTextField x_header = new JTextField("X: ");
        x_header.setEditable(false);
        textPanel.add(x_header);

        JTextField x_text = new JTextField("0.0");
        x_text.setBorder(border_text);
        x_text.setPreferredSize(dimension_text_entry);
        textPanel.add(x_text);

        // y text object
        JTextField y_header = new JTextField("Y: ");
        y_header.setEditable(false);
        textPanel.add(y_header);

        JTextField y_text = new JTextField("0.0");
        y_text.setBorder(border_text);
        y_text.setPreferredSize(dimension_text_entry);
        textPanel.add(y_text);

        // m text object
        JTextField m_header = new JTextField("Mass: ");
        m_header.setEditable(false);
        textPanel.add(m_header);

        JTextField m_text = new JTextField("0.0");
        m_text.setBorder(border_text);
        m_text.setPreferredSize(dimension_text_entry);
        textPanel.add(m_text);

        // dx text object
        JTextField dx_header = new JTextField("Initial X Velocity: ");
        dx_header.setEditable(false);
        textPanel.add(dx_header);

        JTextField dx_text = new JTextField("0.0");
        dx_text.setBorder(border_text);
        dx_text.setPreferredSize(dimension_text_entry);
        textPanel.add(dx_text);

        // dy text object
        JTextField dy_header = new JTextField("Initial Y Velocity: ");
        dy_header.setEditable(false);
        textPanel.add(dy_header);

        JTextField dy_text = new JTextField("0.0");
        dy_text.setBorder(border_text);
        dy_text.setPreferredSize(dimension_text_entry);
        textPanel.add(dy_text);

        // color_r text object
        JTextField color_r_header = new JTextField("Red: ");
        color_r_header.setEditable(false);
        textPanel.add(color_r_header);

        JTextField color_r_text = new JTextField("255");
        color_r_text.setBorder(border_text);
        color_r_text.setPreferredSize(dimension_text_entry);
        textPanel.add(color_r_text);

        // color_g text object
        JTextField color_g_header = new JTextField("Green: ");
        color_g_header.setEditable(false);
        textPanel.add(color_g_header);

        JTextField color_g_text = new JTextField("0");
        color_g_text.setBorder(border_text);
        color_g_text.setPreferredSize(dimension_text_entry);
        textPanel.add(color_g_text);

        // color_b text object
        JTextField color_b_header = new JTextField("Blue: ");
        color_b_header.setEditable(false);
        textPanel.add(color_b_header);

        JTextField color_b_text = new JTextField("0");
        color_b_text.setBorder(border_text);
        color_b_text.setPreferredSize(dimension_text_entry);
        textPanel.add(color_b_text);

        // OK Button
        JButton add_body_button = new JButton("Add Object");
        add_body_button.setBorder(border_text);
        add_body_button.setPreferredSize(new Dimension(100, 50));
        add_body_button.setActionCommand("add_body_button");
        add_body_button.addActionListener(sim);
        textPanel.add(add_body_button);

        // Clear Button
        JButton clear_button = new JButton("Clear Objects");
        clear_button.setBorder(border_text);
        clear_button.setPreferredSize(new Dimension(100, 50));
        clear_button.setActionCommand("clear_button");
        clear_button.addActionListener(sim);
        textPanel.add(clear_button);





        // Bodies
        Body earth = new Body(500.0, 400.0, 6.0, 60.0, 0.0, 0.0, Color.BLUE);
        sim.addBody(earth);

        Body body2 = new Body(300.0, 500.0, 6.0, 5.0, 0.0, -2.0, Color.GREEN);
        sim.addBody(body2);








        // Running Loop

        boolean running = true;
        while (running) {




            // stuff
            if (sim.send_temp_body)
            {
                sim.send_temp_body = false;
                sim.addBody(new Body(Double.parseDouble(x_text.getText()),
                        Double.parseDouble(y_text.getText()),
                        6,
                        Double.parseDouble(m_text.getText()),
                        Double.parseDouble(dx_text.getText()),
                        Double.parseDouble(dy_text.getText()),
                        new Color(Integer.parseInt(color_r_text.getText()), Integer.parseInt(color_g_text.getText()), Integer.parseInt(color_b_text.getText()))));
            }

            if (sim.get_temp_body)
            {
                sim.get_temp_body = false;

                x_text.setText(""+sim.temp_body.getX());
                y_text.setText(""+sim.temp_body.getY());

                dx_text.setText(""+sim.temp_body.getDx());
                dy_text.setText(""+sim.temp_body.getDy());
            }




            try {
                //System.out.println("Looped :)");
                Thread.sleep(16);  // program runs at 62.5 fps
            } catch(InterruptedException e) {
                System.out.println("DIDN'T LOOP :(");
            }



            // END (refreshing and re-adding graph to jframe)
            appFrame.revalidate();
            appFrame.repaint();

            // Adding Things (should put everything addable in an arraylist)






            appFrame.setVisible(true);
        }

    }

}// end of Main
