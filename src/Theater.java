import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Theater {
    private String Location;
    private int SeatingCapacity;

    private DatabaseOperation db = new DatabaseOperation();

    public void addtheaters(){
            JFrame add = new JFrame();
            add.setVisible(true);
            add.setSize(new Dimension(300,400));
            add.setResizable(false);
            
    
            JPanel addpan = new JPanel();
            addpan.setBackground(new Color(240, 101, 124));
            addpan.setPreferredSize(new Dimension(300,300));
    
    
            JLabel loc = new JLabel();
            loc.setText("Enter Theater Location");
    
            //text field
            JTextField Location_t = new JTextField();
            Location_t.setPreferredSize(new Dimension(250,30));
            loc.setFont(new Font("Serif",Font.ITALIC,15));
    
            
            JLabel cap = new JLabel();
            cap.setText("Enter Theater Seating Capacity");
    
            //text field
            JTextField Capacity = new JTextField();
            Capacity.setPreferredSize(new Dimension(250,30));
            cap.setFont(new Font("Serif",Font.ITALIC,15));
    
            JLabel name = new JLabel();
            name.setText("Enter Theater Name");

            JTextField theatername = new JTextField();
            theatername.setPreferredSize(new Dimension(250,30));
            name.setFont(new Font("Serif",Font.ITALIC,15));
    
            //text field
            
    
            //text field
            
    
            //text field
            
    
    
            JButton userdetailsButton = new JButton();
            userdetailsButton.setBounds(20, 120, 60, 40);
            userdetailsButton.setPreferredSize(new Dimension(150,30));
            userdetailsButton.setText("Add Theater");
            
    
    
    
            userdetailsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String Location = Location_t.getText();
                    String capacity = Capacity.getText();
                    int SeatingCapacity = Integer.parseInt(capacity);
                    String TheaterName = theatername.getText();

                    String sql = "INSERT INTO theaters (Location, SeatingCapacity,TheaterName) VALUES (?,?,?)";
                    Object[] values = {Location, SeatingCapacity, TheaterName};
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
    
    
            addpan.add(loc);
            addpan.add(Location_t);
            addpan.add(cap);
            addpan.add(Capacity);
            addpan.add(name);
            addpan.add(theatername);
            addpan.add(userdetailsButton);
            add.add(addpan);
    
    
    
    
        }
    
    public void insertTheater(String location,int SeatingCapacity){
        this.Location = location;
        this.SeatingCapacity = SeatingCapacity;
        String sql = "INSERT INTO theaters (Location, SeatingCapacity) VALUES (?,?)";
        Object[] values = {Location,SeatingCapacity};
        int rowsAffected = db.executeUpdate(sql, values);
        if(rowsAffected>0)
            System.out.println("Theater inserted successfully");
        else
            System.out.println("Something went wrong.Theater not inserted.");
    }

    public void showTheaters(){
        String sql = "SELECT * from theaters";
        List<Map<String,Object>> theaters = db.getRecords(sql);

        
        JLabel homel = new JLabel();
       
        JFrame home = new JFrame();
        home.setLayout(new BorderLayout());
        homel.setText("Available Theaters");
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
            


        for (Map<String, Object> theater : theaters) {
           
            JPanel center = new JPanel();
            center.setFont(new Font("MV Boli",Font.PLAIN,20));//font size and fontfamily
            center.setBackground(new Color(240, 101, 124));
            
            center.setForeground(new Color(13, 13, 13));
            center.setOpaque(true);
            

           //JLabel id = new JLabel("Movie ID: " + movie.get("MovieId"));
            JLabel id = new JLabel("Theater Id: "+theater.get("TheaterID"));
            JLabel name = new JLabel("Location: " + theater.get("Location"));
            JLabel rating = new JLabel("SeatingCapacity: " + theater.get("SeatingCapacity"));
            JLabel time = new JLabel("TheaterName: " + theater.get("TheaterName"));
            

            
            center.add(id);
            center.add(name);
            center.add(rating);
            center.add(time);
            a.add(center);
            center.setPreferredSize(new Dimension(200,30));

        }

            JScrollPane scrollPane = new JScrollPane(a);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            home.add(scrollPane);

        for (Map<String, Object> theater : theaters) {

            System.out.println("Theater ID: " + theater.get("TheaterID"));
            System.out.println("Location: " + theater.get("Location"));
            System.out.println("Seating Capacity: " + theater.get("SeatingCapacity"));
            System.out.println("-----------------------------");
            
        }

    }

}