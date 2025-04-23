import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Booking {
    Showtime s = new Showtime();
    DatabaseOperation db = new DatabaseOperation();
    Scanner sc = new Scanner(System.in);

    
    public void bookTicket(int ShowtimeID , int userID){       
        
        
        JFrame booking = new JFrame();
        booking.setVisible(true);
        booking.setBackground(new Color(240, 101, 124));
        booking.setSize(400,400);
        
        JPanel book = new JPanel();
        booking.setLayout(new BorderLayout());
        book.setBackground(new Color(240, 101, 124));
        
        book.setLayout(new BoxLayout(book, BoxLayout.Y_AXIS));
        
        //book.setPreferredSize(new Dimension(400,400));

        JLabel seat = new JLabel();
        seat.setText("Available seats");
        seat.setFont(new Font("MV Boli",Font.PLAIN,20));//font size and fontfamily
        seat.setBackground(new Color(240, 101, 124));
        
        seat.setForeground(new Color(13, 13, 13));
        seat.setOpaque(true);
        seat.setHorizontalAlignment(JLabel.CENTER);
        seat.setVerticalAlignment(JLabel.TOP);
        book.add(seat);
        booking.add(seat,BorderLayout.NORTH);
        


        int capacity = s.getTheaterCapacity(ShowtimeID);
        ArrayList<Integer> bookedSeats = db.getBookedSeats(ShowtimeID);

        
        for (int i = 1 ; i<= capacity ; i++){

            final int seatnum = i;
            
            
            JPanel seats = new JPanel();
            seats.setBackground(new Color(240, 101, 124));

            if(bookedSeats.contains(seatnum)){
                //JPanel seatPanel = new JPanel();
                JButton seating =new JButton();
                seating.setText("X");
                seating.setPreferredSize(new Dimension(50,50));
                //seatPanel.setPreferredSize(new Dimension(30,30));
                System.out.print("X ");
                seating.setBackground(Color.red);
               // seatPanel.add(seating);
                seats.add(seating);

            }else{
               // JPanel seatPanel = new JPanel();
                JButton seating =new JButton();
                String a = String.valueOf(seatnum);
                seating.setText(a);
                seating.setBackground(Color.green);
                seating.setPreferredSize(new Dimension(60,60));
                //seatPanel.setPreferredSize(new Dimension(30,30));
                //seatPanel.add(seating);
                
                System.out.print(seatnum + " ");
                
                seating.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        
                        if(e.getSource()==seating){
                            String sql = "insert into bookings(userid,showtimeid,selectedseats,paymentstatus) values(?,?,?,1)";
                            Object[] values = {userID,ShowtimeID,seatnum};
                            int rowsAffected = db.executeUpdate(sql, values);
                            if(rowsAffected>0){
                                System.out.println("Booking completed successfully");
                                JOptionPane.showMessageDialog(null,"Booking Completed");
                        }
                            else
                            System.out.println("Something went wrong.Booking failed.");
                        }
                    }
                });
                seats.add(seating);
                
                
            }

            if(i %8 == 0){
                System.out.println();
            }
            //seats.setPreferredSize(new Dimension(60,60));
            book.add(seats);
        }
            
            



        //....................................................
        
        booking.add(book);
        
        JScrollPane scrollPane = new JScrollPane(book);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        booking.add(scrollPane);
        
    }
    

    public void seeTicket(int userID){
        
        
        JLabel homel = new JLabel();
       
        JFrame home = new JFrame();
        home.setLayout(new BorderLayout());
        homel.setText("Tickets Booked");
        homel.setFont(new Font("MV Boli",Font.PLAIN,20));//font size and fontfamily
        homel.setBackground(new Color(240, 101, 124));
        
        homel.setForeground(new Color(13, 13, 13));
        homel.setOpaque(true);
        homel.setHorizontalAlignment(JLabel.CENTER);
        homel.setVerticalAlignment(JLabel.TOP);
        //home settings 
        home.setVisible(true);
        home.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        home.setSize(420, 250);
        home.add(homel,BorderLayout.NORTH);


        JPanel a = new JPanel();
        a.setLayout(new BoxLayout(a, BoxLayout.Y_AXIS));
        a.setFont(new Font("MV Boli",Font.PLAIN,20));//font size and fontfamily
        a.setBackground(new Color(240, 101, 124));
        
        a.setForeground(new Color(13, 13, 13));
        a.setOpaque(true);
        String sql = "SELECT movies.title, theaters.TheaterName, showtimes.Showtime, bookings.SelectedSeats FROM bookings JOIN showtimes ON bookings.ShowtimeID = showtimes.ShowtimeID JOIN movies ON showtimes.MovieID = movies.MovieId JOIN theaters ON showtimes.TheaterID = theaters.TheaterID WHERE bookings.UserID = "+userID;
        List<Map<String, Object>> movies = db.getRecords(sql);


        for (Map<String, Object> movie : movies) {
        
            JPanel center = new JPanel();
            center.setFont(new Font("MV Boli",Font.PLAIN,20));//font size and fontfamily
            center.setBackground(new Color(240, 101, 124));
            
            center.setForeground(new Color(13, 13, 13));
            center.setOpaque(true);
            

           //JLabel id = new JLabel("Movie ID: " + movie.get("MovieId"));
            
            JLabel name = new JLabel("Name: " + movie.get("title"));
            JLabel rating = new JLabel("Theater: " + movie.get("TheaterName"));
            JLabel time = new JLabel("Showtime " + movie.get("Showtime"));
            JLabel gen = new JLabel("seat: Number" + movie.get("SelectedSeats"));
            
           

            //center.add(id);
            center.add(name);
            center.add(rating);
            center.add(time);
            center.add(gen);
            
            a.add(center);
            center.setPreferredSize(new Dimension(200,70));
        
        

        }

        home.add(a);

        JScrollPane scrollPane = new JScrollPane(a);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        home.add(scrollPane);
        


        
    }

    public void cancelTicket(int userID){


        JLabel homel = new JLabel();
       
        JFrame home = new JFrame();
        home.setLayout(new BorderLayout());
        homel.setText("Tickets Booked");
        homel.setFont(new Font("MV Boli",Font.PLAIN,20));//font size and fontfamily
        homel.setBackground(new Color(240, 101, 124));
        
        homel.setForeground(new Color(13, 13, 13));
        homel.setOpaque(true);
        homel.setHorizontalAlignment(JLabel.CENTER);
        homel.setVerticalAlignment(JLabel.TOP);
        //home settings 
        home.setVisible(true);
        home.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        home.setSize(420, 250);
        home.add(homel,BorderLayout.NORTH);


        JPanel a = new JPanel();
        a.setLayout(new BoxLayout(a, BoxLayout.Y_AXIS));
        a.setFont(new Font("MV Boli",Font.PLAIN,20));//font size and fontfamily
        a.setBackground(new Color(240, 101, 124));
        
        a.setForeground(new Color(13, 13, 13));
        a.setOpaque(true);
        String sql = "SELECT movies.title, theaters.TheaterName, showtimes.Showtime, bookings.SelectedSeats, bookings.BookingID FROM bookings JOIN showtimes ON bookings.ShowtimeID = showtimes.ShowtimeID JOIN movies ON showtimes.MovieID = movies.MovieId JOIN theaters ON showtimes.TheaterID = theaters.TheaterID WHERE bookings.UserID = "+userID;
        List<Map<String, Object>> movies = db.getRecords(sql);


        for (Map<String, Object> movie : movies) {
        
            JPanel center = new JPanel();
            center.setFont(new Font("MV Boli",Font.PLAIN,20));//font size and fontfamily
            center.setBackground(new Color(240, 101, 124));
            
            center.setForeground(new Color(13, 13, 13));
            center.setOpaque(true);
            

           //JLabel id = new JLabel("Movie ID: " + movie.get("MovieId"));
            
            JLabel name = new JLabel("Name: " + movie.get("title"));
            JLabel rating = new JLabel("Theater: " + movie.get("TheaterName"));
            JLabel time = new JLabel("Showtime " + movie.get("Showtime"));
            JLabel gen = new JLabel("seat: Number" + movie.get("SelectedSeats"));
            
           

            //center.add(id);
            center.add(name);
            center.add(rating);
            center.add(time);
            center.add(gen);
            
            a.add(center);
            center.setPreferredSize(new Dimension(200,70));
            JButton cancel = new JButton();
            cancel.setText("Cancel Ticket");
            cancel.addActionListener(new ActionListener() {

                Object MovieID =  movie.get("BookingID");

                public void actionPerformed(ActionEvent e) {
                    if(e.getSource()==cancel){
                        int rowsAffected = db.removeBooking((int) MovieID);
                    if(rowsAffected>0){
                        System.out.println("Booking cancelled successfully");
                        JOptionPane.showMessageDialog(null,"Booking Canceled");
                    }
                    else
                        System.out.println("Something went wrong.Booking not cancelled.");
                    }
                }
            });
            center.add(cancel);
        

        }

        home.add(a);

        JScrollPane scrollPane = new JScrollPane(a);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        home.add(scrollPane);
        

    }
}
