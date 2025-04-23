import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Showtime {

    DatabaseOperation db = new DatabaseOperation();

    public void addshow(){
        JFrame add = new JFrame();
        add.setVisible(true);
        add.setSize(new Dimension(300,400));
        add.setResizable(false);
        
        JPanel addpan = new JPanel();
        addpan.setBackground(new Color(240, 101, 124));
        addpan.setPreferredSize(new Dimension(300,300));


        JLabel movie_name = new JLabel();
        movie_name.setText("Enter Movie ID");

        //text field
        JTextField entermovie = new JTextField();
        entermovie.setPreferredSize(new Dimension(250,30));
        movie_name.setFont(new Font("Serif",Font.ITALIC,15));

        
        JLabel genre = new JLabel();
        genre.setText("Enter Theater ID");

        //text field
        JTextField genretext = new JTextField();
        genretext.setPreferredSize(new Dimension(250,30));
        genre.setFont(new Font("Serif",Font.ITALIC,15));

        JLabel rating = new JLabel();
        rating.setText("Enter dateTime in format yyyy-mm-dd HH:mm:ss");

        //text field
        JTextField enterrating = new JTextField();
        enterrating.setPreferredSize(new Dimension(250,30));
        rating.setFont(new Font("Serif",Font.ITALIC,15));


        JButton userdetailsButton = new JButton();
        userdetailsButton.setBounds(20, 120, 60, 40);
        userdetailsButton.setPreferredSize(new Dimension(150,30));
        userdetailsButton.setText("Add Movie");
        
        addpan.add(movie_name);
        addpan.add(entermovie);
        addpan.add(genre);
        addpan.add(genretext);
        addpan.add(rating);
        addpan.add(enterrating);
        addpan.add(userdetailsButton);
        add.add(addpan);


        userdetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String title = entermovie.getText();
                int MovieID = Integer.parseInt(title);
                String genre = genretext.getText();
                int TheaterID = Integer.parseInt(genre);
                String dt = enterrating.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(dt, formatter);
                Timestamp showtime = Timestamp.valueOf(dateTime);

                String sql = "INSERT INTO Showtimes(MovieID,TheaterID,Showtime) VALUES(?,?,?)";
                Object[] values = {MovieID,TheaterID,showtime};
                int rowsAffected = db.executeUpdate(sql, values);
                if(rowsAffected>0){
                    System.out.println("show added  successfully");
                    JOptionPane.showMessageDialog(null, "show Added successfully");
                }
                else{
                    System.out.println("Something went wrong.Sign up failed.");
                    JOptionPane.showMessageDialog(null, "Somthing error");
                }
                    if(e.getSource()==userdetailsButton){
                    
                    }
                    }

            
        });


    }

    public void insertShowtime(int MovieID,int TheaterID,Timestamp showtime){
        String sql = "INSERT INTO Showtimes(MovieID,TheaterID,Showtime) VALUES(?,?,?)";
        Object[] values = {MovieID,TheaterID,showtime};
        int rowsAffected = db.executeUpdate(sql, values);
        if(rowsAffected>0)
            System.out.println("Showtime added successfully");
        else
            System.out.println("Something went wrong.Showtime not inserted.");
    
    }
    public void showmovies(){
        String sql = "SELECT title,rating from movies";
        List<Map<String,Object>> movies = db.getRecords(sql);
        for (Map<String, Object> showmovies : movies) {
            System.out.println("Showtime ID: " + showmovies.get("title"));
            System.out.println("Movie ID: " + showmovies.get("rating"));
            System.out.println("-----------------------------");
        }
    }
    
    public void seeshowtime(){
        String sql = "select ShowtimeID, title, TheaterName,Showtime from movies natural join showtimes Showtimes natural join theaters";
        List<Map<String,Object>> showtimes = db.getRecords(sql);

        //............................
        JLabel homel = new JLabel();
       
        JFrame home = new JFrame();
        home.setLayout(new BorderLayout());
        homel.setText("Available Movies");
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
            


        for (Map<String, Object> show : showtimes) {
           
            JPanel center = new JPanel();
            center.setFont(new Font("MV Boli",Font.PLAIN,20));//font size and fontfamily
            center.setBackground(new Color(240, 101, 124));
            
            center.setForeground(new Color(13, 13, 13));
            center.setOpaque(true);
            

           //JLabel id = new JLabel("Movie ID: " + movie.get("MovieId"));
            Object showID =  show.get("ShowtimeID");
            JLabel id = new JLabel("ShowID : "+ showID);
            JLabel mName = new JLabel("MovieName: " + show.get("title"));
            JLabel tname = new JLabel("TheaterName: " + show.get("TheaterName"));
            JLabel time = new JLabel("Timings: " + show.get("Showtime"));
            
            center.add(id);
            center.add(mName);
            center.add(tname);
            center.add(time);
            a.add(center);
            center.setPreferredSize(new Dimension(200,50));

        }

            JScrollPane scrollPane = new JScrollPane(a);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            home.add(scrollPane);
        //.........................
        
    }

    public void removeshowtime(){
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
        String sql = "select ShowtimeID, title, TheaterName,Showtime from movies natural join showtimes Showtimes natural join theaters";

        List<Map<String,Object>> showtimes = db.getRecords(sql);


        for (Map<String, Object> show : showtimes) {
           
            JPanel center = new JPanel();
            center.setFont(new Font("MV Boli",Font.PLAIN,20));//font size and fontfamily
            center.setBackground(new Color(240, 101, 124));
            
            center.setForeground(new Color(13, 13, 13));
            center.setOpaque(true);
            

           //JLabel id = new JLabel("Movie ID: " + movie.get("MovieId"));
            Object showID =  show.get("ShowtimeID");
            JLabel id = new JLabel("ShowID : "+ showID);
            JLabel mName = new JLabel("MovieName: " + show.get("title"));
            JLabel tname = new JLabel("TheaterName: " + show.get("TheaterName"));
            JLabel time = new JLabel("Timings: " + show.get("Showtime"));
            
            center.add(id);
            center.add(mName);
            center.add(tname);
            center.add(time);
            a.add(center);
            center.setPreferredSize(new Dimension(200,150));
            JButton cancel = new JButton();
            cancel.setText("Cancel Ticket");
            cancel.addActionListener(new ActionListener() {

                Object MovieID =  show.get("ShowtimeID");

                public void actionPerformed(ActionEvent e) {
                    if(e.getSource()==cancel){
                        int rowsAffected = db.removeshowtime((int) MovieID);
                    if(rowsAffected>0){
                        System.out.println("Show Removed successfully");
                        JOptionPane.showMessageDialog(null,"Show Removed");
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

    public void showShowtimesDetails(int showtimeID){
        String sql = "SELECT s.ShowtimeID, m.Title,m.Duration,s.Showtime from Showtimes s, Movies m where s.MovieID = m.MovieID and s.ShowtimeID = ? ";
        db.getShowtimeDetails(sql, showtimeID);
    }
    public int getTheaterCapacity(int showtime){
        String sql = "SELECT SeatingCapacity from theaters where TheaterID = (SELECT TheaterID from showtimes where ShowtimeID = ?)";
        return db.getSeatingCapacity(sql, showtime);
    }

}
