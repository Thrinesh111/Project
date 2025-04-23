import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class User{ 
    String name,username,password,address,phone;
    int choice;
    Scanner sc = new Scanner(System.in);
    Showtime st = new Showtime();
    Booking b = new Booking();
    Movie m = new Movie();


    public void userMenu(int userID){

    JPanel homePanel = new JPanel();

    //show time 
    JButton show =new JButton();
    show.setBounds(20, 200, 60, 40);
    show.setPreferredSize(new Dimension(150,30));
    show.setText("Show Movies");
    //booking ticket button
    JButton bookticketbtn = new JButton();
    bookticketbtn.setBounds(20, 70, 60, 40);
    bookticketbtn.setPreferredSize(new Dimension(150,30));
    bookticketbtn.setText("BookTicket");

    //btn to see tickets 
    JButton seetkt = new JButton();
    seetkt.setBounds(20, 90, 60, 40);
    seetkt.setPreferredSize(new Dimension(150,30));
    seetkt.setText("SeeBookings");

    //btn to cancel ticket 
    JButton canceltkt = new JButton();
    canceltkt.setBounds(20, 120, 60, 40);
    canceltkt.setPreferredSize(new Dimension(150,30));
    canceltkt.setText("CancelTicket");

    JLabel homel = new JLabel();
    JFrame home = new JFrame();
    homel.setText("Movie Ticket Booking System");
    homel.setFont(new Font("MV Boli",Font.PLAIN,20));//font size and fontfamily
    homel.setBackground(new Color(240, 101, 124));
    homePanel.setBackground(new Color(240, 101, 124));
    homel.setForeground(new Color(13, 13, 13));
    homel.setOpaque(true);
    homel.setHorizontalAlignment(JLabel.CENTER);
    homel.setVerticalAlignment(JLabel.TOP);
    //home pannel 
    homePanel.add(homel);
    homePanel.add(show);
    //homePanel.add(bookticketbtn);
    homePanel.add(seetkt);
    homePanel.add(canceltkt);
    //home settings 
    home.setVisible(true);
    home.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    home.setSize(320, 250);
    home.add(homePanel);
    home.setResizable(false);
        //action litener for user
        show.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            
            
            if(e.getSource()==show){
                m.showMovies((int) userID);
            }
        }
    });

    bookticketbtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource()==bookticketbtn){
                //b.bookTicket(userID);
            }
        }
    });

    seetkt.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource()==seetkt){
                b.seeTicket(userID);
            }
        }
    });

    canceltkt.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource()==canceltkt){
                b.cancelTicket(userID);
            }
        }
    });
        
    }
}
