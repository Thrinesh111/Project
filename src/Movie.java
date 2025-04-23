import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Movie {
    Booking b = new Booking();
    private DatabaseOperation db = new DatabaseOperation();
    

    // Methods

    // Method to insert movie into database

    public void addMovies() {
        JFrame add = new JFrame();
        add.setVisible(true);
        add.setSize(new Dimension(300,400));
        add.setResizable(false);
        

        JPanel addpan = new JPanel();
        addpan.setBackground(new Color(240, 101, 124));
        addpan.setPreferredSize(new Dimension(300,300));


        JLabel movie_name = new JLabel();
        movie_name.setText("Enter Movie name");

        //text field
        JTextField entermovie = new JTextField();
        entermovie.setPreferredSize(new Dimension(250,30));
        movie_name.setFont(new Font("Serif",Font.ITALIC,15));

        
        JLabel genre = new JLabel();
        genre.setText("Enter Movie Gnere");

        //text field
        JTextField genretext = new JTextField();
        genretext.setPreferredSize(new Dimension(250,30));
        genre.setFont(new Font("Serif",Font.ITALIC,15));

        JLabel rating = new JLabel();
        rating.setText("Enter Rating of movie (in decimal out of 10)");

        //text field
        JTextField enterrating = new JTextField();
        enterrating.setPreferredSize(new Dimension(250,30));
        rating.setFont(new Font("Serif",Font.ITALIC,15));


        JLabel duration = new JLabel();
        duration.setText("Enter Duration of Movie(in minutes)");

        //text field
        JTextField enterduration = new JTextField();
        enterduration.setPreferredSize(new Dimension(250,30));
        duration.setFont(new Font("Serif",Font.ITALIC,15));

        JLabel synopsis = new JLabel();
        synopsis.setText("write Synopsis");

        //text field
        JTextField entersynopsis = new JTextField();
        entersynopsis.setPreferredSize(new Dimension(250,30));
        synopsis.setFont(new Font("Serif",Font.ITALIC,15));



        JButton userdetailsButton = new JButton();
        userdetailsButton.setBounds(20, 120, 60, 40);
        userdetailsButton.setPreferredSize(new Dimension(150,30));
        userdetailsButton.setText("Add Movie");
        



        userdetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String title = entermovie.getText();
                String genre = genretext.getText();
                String ratingText = enterrating.getText();
                String durationtext = enterduration.getText();
                String synopsis = entersynopsis.getText();
                double rating = Double.parseDouble(ratingText);
                int duration = Integer.parseInt(durationtext);


                String sql = "INSERT INTO movies (title, genre, rating, duration, synopsis) VALUES (?,?,?,?,?)";
                Object[] values = {title, genre, rating, duration, synopsis};
                int rowsAffected = db.executeUpdate(sql, values);
                if(rowsAffected>0){
                    System.out.println("movie added  successfully");
                    JOptionPane.showMessageDialog(null, "Movie Added successfully");
                }
                else{
                    System.out.println("Something went wrong.Sign up failed.");
                    JOptionPane.showMessageDialog(null, "Somthing error");
                }
                    if(e.getSource()==userdetailsButton){
                    
                    }
                    }
        });


        addpan.add(movie_name);
        addpan.add(entermovie);
        addpan.add(genre);
        addpan.add(genretext);
        addpan.add(rating);
        addpan.add(enterrating);
        addpan.add(duration);
        addpan.add(enterduration);
        addpan.add(synopsis);
        addpan.add(entersynopsis);
        addpan.add(userdetailsButton);
        add.add(addpan);




    }

    
    public void insertMovie(String title, String genre, double rating, int duration, String synopsis){
        String sql = "INSERT INTO movies (title, genre, rating, duration, synopsis) VALUES (?,?,?,?,?)";
        Object[] values = {title, genre, rating, duration, synopsis};
        int rowsAffected = db.executeUpdate(sql, values);
        if(rowsAffected>0)
            System.out.println("Movie inserted successfully");
        else
            System.out.println("Something went wrong.Movie not inserted.");
    }

    //Method to view all movies in database
    public void showMovies(int UserID) {
        String sql = "SELECT MovieId,title,genre,rating,duration FROM Movies";
        List<Map<String, Object>> movies = db.getRecords(sql);

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
            


        for (Map<String, Object> movie : movies) {
           
            JPanel center = new JPanel();
            center.setFont(new Font("MV Boli",Font.PLAIN,20));//font size and fontfamily
            center.setBackground(new Color(240, 101, 124));
            
            center.setForeground(new Color(13, 13, 13));
            center.setOpaque(true);
            

           //JLabel id = new JLabel("Movie ID: " + movie.get("MovieId"));
            Object MovieID =  movie.get("MovieId");
            JLabel id = new JLabel("movieID "+ MovieID);
            JLabel name = new JLabel("Name: " + movie.get("title"));
            JLabel rating = new JLabel("Rating: " + movie.get("rating"));
            JLabel time = new JLabel("Duration(mins): " + movie.get("duration"));
            JLabel gen = new JLabel("Genre: " + movie.get("genre"));
            JButton book =new JButton();
            book.setPreferredSize(new Dimension(155,30));
            book.setText("booknow");
            book.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource()==book){
                        showtheaters((int) MovieID,(int) UserID);
                    }
                }
            });

            center.add(id);
            center.add(name);
            center.add(rating);
            center.add(time);
            center.add(gen);
            center.add(book);
            
            a.add(center);
            center.setPreferredSize(new Dimension(200,150));

        }

            JScrollPane scrollPane = new JScrollPane(a);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            home.add(scrollPane);
        
        
        
    }
    //view movies for admin without booking button
    public void showmoviesadmin(){
        String sql = "SELECT MovieId,title,genre,rating,duration FROM Movies";
        List<Map<String, Object>> movies = db.getRecords(sql);

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
            


        for (Map<String, Object> movie : movies) {
           
            JPanel center = new JPanel();
            center.setFont(new Font("MV Boli",Font.PLAIN,20));//font size and fontfamily
            center.setBackground(new Color(240, 101, 124));
            
            center.setForeground(new Color(13, 13, 13));
            center.setOpaque(true);
            

           //JLabel id = new JLabel("Movie ID: " + movie.get("MovieId"));
            Object MovieID =  movie.get("MovieId");
            JLabel id = new JLabel("movieID "+ MovieID);
            JLabel name = new JLabel("Name: " + movie.get("title"));
            JLabel rating = new JLabel("Rating: " + movie.get("rating"));
            JLabel time = new JLabel("Duration(mins): " + movie.get("duration"));
            JLabel gen = new JLabel("Genre: " + movie.get("genre"));
            

            center.add(id);
            center.add(name);
            center.add(rating);
            center.add(time);
            center.add(gen);
            
            a.add(center);
            center.setPreferredSize(new Dimension(200,50));

        }

            JScrollPane scrollPane = new JScrollPane(a);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            home.add(scrollPane);

    }
    public void showtheaters(int MovieID , int userID){
        String sql = "select ShowtimeID,TheaterName,showtime from movies natural join showtimes  Showtimes natural join theaters where MovieID = "+MovieID;
        List<Map<String, Object>> show = db.getRecords(sql);
        JLabel homel = new JLabel();
       
        JFrame home = new JFrame();
        home.setLayout(new BorderLayout());
        homel.setText("Available Shows");
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
            


        for (Map<String, Object> theater : show) {
           
            JPanel center = new JPanel();
            center.setFont(new Font("MV Boli",Font.PLAIN,20));//font size and fontfamily
            center.setBackground(new Color(240, 101, 124));
            
            center.setForeground(new Color(13, 13, 13));
            center.setOpaque(true);
            
            Object ShowID =  theater.get("ShowtimeID");
           JLabel id = new JLabel("Show ID: " + theater.get("ShowtimeID"));
            
           JLabel tname = new JLabel("TheaterName: " + theater.get("TheaterName"));
           JLabel time = new JLabel("Timings: " + theater.get("Showtime"));
            


           JButton book =new JButton();
            book.setPreferredSize(new Dimension(155,30));
            book.setText("booknow");
            book.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource()==book){
                        b.bookTicket((int) ShowID ,(int) userID);
                    }
                }
            });

            //center.add(id);
            center.add(tname);
            center.add(time);
            center.add(book);
            a.add(center);
            center.setPreferredSize(new Dimension(200,30));

        }

            JScrollPane scrollPane = new JScrollPane(a);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            home.add(scrollPane);
/* 
        for (Map<String, Object> theater : theaters) {

            System.out.println("Theater ID: " + theater.get("TheaterID"));
            System.out.println("Location: " + theater.get("Location"));
            System.out.println("Seating Capacity: " + theater.get("SeatingCapacity"));
            System.out.println("-----------------------------");
            
        

        }*/



    }



}
