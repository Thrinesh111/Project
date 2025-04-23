import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Admin extends User{
    //instance fields
    Scanner sc = new Scanner(System.in);
    int choice,userID;
    String title, genre,synopsis;
    double rating;
    int duration,movieid,theaterid,showmtime_hour,showtime_min;
    Timestamp showtime;

    //Objects of different classes
    Movie m = new Movie();
    Theater t = new Theater();
    Showtime st = new Showtime();

    public int takeUserID(){
        System.out.println("Enter user id: ");
        return sc.nextInt();
    }

    public void adminMenu(){


        JFrame admin = new JFrame();
        admin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        admin.setSize(new Dimension(300,400));
        admin.setVisible(true);
        admin.setResizable(false);

        JPanel admiPanel = new JPanel();

        JLabel homel = new JLabel();

        homel.setText("Movie Ticket Booking System");
        homel.setFont(new Font("MV Boli",Font.PLAIN,20));//font size and fontfamily
        homel.setBackground(new Color(240, 101, 124));
        admiPanel.setBackground(new Color(240, 101, 124));
        homel.setForeground(new Color(13, 13, 13));
        homel.setOpaque(true);
        homel.setHorizontalAlignment(JLabel.CENTER);
        homel.setVerticalAlignment(JLabel.TOP);
    //home pannel 
        admiPanel.add(homel);

        JButton addmovies =new JButton();
        addmovies.setBounds(20, 200, 60, 40);
        addmovies.setPreferredSize(new Dimension(150,30));
        addmovies.setText("Add Movies");
        //booking ticket button
        JButton seemovies = new JButton();
        seemovies.setBounds(20, 70, 60, 40);
        seemovies.setPreferredSize(new Dimension(150,30));
        seemovies.setText("See Movies");

        //btn to see tickets 
        JButton addshow = new JButton();
        addshow.setBounds(20, 90, 60, 40);
        addshow.setPreferredSize(new Dimension(150,30));
        addshow.setText("Add Show");

        //btn to cancel ticket 
        JButton showtimes = new JButton();
        showtimes.setBounds(20, 120, 60, 40);
        showtimes.setPreferredSize(new Dimension(150,30));
        showtimes.setText("See Showtimes");

        JButton removeshowtimes = new JButton();
        removeshowtimes.setBounds(20, 120, 60, 40);
        removeshowtimes.setPreferredSize(new Dimension(150,30));
        removeshowtimes.setText("Remove Showtimes");

        JButton addtheater = new JButton();
        addtheater.setBounds(20, 120, 60, 40);
        addtheater.setPreferredSize(new Dimension(150,30));
        addtheater.setText("Add Theater");


        JButton seetheater = new JButton();
        seetheater.setBounds(20, 120, 60, 40);
        seetheater.setPreferredSize(new Dimension(150,30));
        seetheater.setText("See Theater");


        addmovies.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                
                if(e.getSource()==addmovies){
                    m.addMovies();
                }
            }

            
        });
    
        seemovies.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if(e.getSource()==seemovies){
                    m.showmoviesadmin();    
                }
            }
        });
    
        addtheater.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if(e.getSource()==addtheater){
                    t.addtheaters();
                }
            }
        });
        seetheater.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if(e.getSource()==seetheater){
                    t.showTheaters();
                }
            }
        });
    
        showtimes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if(e.getSource()==showtimes){
                    st.seeshowtime();
                }
            }
        });
        removeshowtimes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if(e.getSource()==removeshowtimes){
                    st.removeshowtime();
                }
            }
        });

        addshow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if(e.getSource()==addshow){
                    st.addshow();
                }
            }
        });

        admiPanel.add(addmovies);
        admiPanel.add(seemovies);
        admiPanel.add(addtheater);
        admiPanel.add(seetheater);
        admiPanel.add(showtimes);
        admiPanel.add(addshow);
        admiPanel.add(removeshowtimes);
        admin.add(admiPanel);

    }

}
