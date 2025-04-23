import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;

import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MovieTicketDriver {
    static Scanner sc = new Scanner(System.in);
    

    static DatabaseOperation db = new DatabaseOperation();
    
    //methods to sign up and login for user class
    static void user_signup(){
        

        

        JPanel userPanel = new JPanel();
        JFrame user_signFrame = new JFrame();
        user_signFrame.setVisible(true);
        user_signFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        user_signFrame.setSize(320, 420);
        userPanel.setBackground(new Color(240, 101, 124));
        user_signFrame.add(userPanel);
        user_signFrame.setResizable(false);
        JLabel usname = new JLabel();
        usname.setText("Enter the user name");

        //text field
        JTextField usernametext = new JTextField();
        usernametext.setPreferredSize(new Dimension(250,30));
        usname.setFont(new Font("Serif",Font.ITALIC,15));



        userPanel.add(usname);
        userPanel.add(usernametext);

        JLabel pass = new JLabel();
        pass.setText("create your password");

        //text field
        JTextField passwordtext = new JTextField();
        passwordtext.setPreferredSize(new Dimension(250,30));
        pass.setFont(new Font("Serif",Font.ITALIC,15));



        userPanel.add(pass);
        userPanel.add(passwordtext);
        

        JLabel phn = new JLabel();
        phn.setText("Enter phone number");

        //text field
        JTextField phonetext = new JTextField();
        phonetext.setPreferredSize(new Dimension(250,30));
        phn.setFont(new Font("Serif",Font.ITALIC,15));



        userPanel.add(phn);
        userPanel.add(phonetext);
        
        JLabel your_name = new JLabel();
        your_name.setText("Enter your name");

        //text field
        JTextField nametext = new JTextField();
        nametext.setPreferredSize(new Dimension(250,30));
        your_name.setFont(new Font("Serif",Font.ITALIC,15));



        userPanel.add(your_name);
        userPanel.add(nametext);
        
        JLabel addr = new JLabel();
        addr.setText("Enter the Address");

        //text field
        JTextField addresstext = new JTextField();
        addresstext.setPreferredSize(new Dimension(250,30));
        addr.setFont(new Font("Serif",Font.ITALIC,15));



        userPanel.add(addr);
        userPanel.add(addresstext);
        
       
        JButton userdetailsButton = new JButton();
        userdetailsButton.setBounds(20, 120, 60, 40);
        userdetailsButton.setPreferredSize(new Dimension(150,30));
        userdetailsButton.setText("Sign in");
        userPanel.add(userdetailsButton);



        userdetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String username = usernametext.getText();
                String password = passwordtext.getText();
                String phone = phonetext.getText();
                String address = addresstext.getText();
                String name = nametext.getText();


                String sql = "INSERT INTO users(Username,Password,Name,Phone,Address) values(?,?,?,?,?)";
                Object[] values = {username,password,name,phone,address};
                int rowsAffected = db.executeUpdate(sql, values); 
                if(rowsAffected>0){
                    System.out.println("User registered successfully");
                    JOptionPane.showMessageDialog(null, "User regestered successfully");
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

    static void user_login(){
        JPanel userloginPanel = new JPanel();
        userloginPanel.setBackground(new Color(240, 101, 124));
        JFrame userloginFrame = new JFrame();
        JLabel userloginname = new JLabel();
        JButton login = new JButton();
        JTextField usernametext = new JTextField();
        JTextField passwordtext = new JTextField();
        userloginPanel.setPreferredSize(new Dimension(100,300));
        userloginname.setText("Enter user name  ");
        usernametext.setPreferredSize(new Dimension(250,30));
        userloginname.setFont(new Font("Serif",Font.ITALIC,15));
        userloginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        userloginPanel.add(userloginname);
        userloginPanel.add(usernametext);

        JLabel pass = new JLabel();
        pass.setText("Enter your password");

        //text field
    
        passwordtext.setPreferredSize(new Dimension(250,30));
        pass.setFont(new Font("Serif",Font.ITALIC,15));



        userloginPanel.add(pass);
        userloginPanel.add(passwordtext);
        userloginFrame.add(userloginPanel);
        userloginFrame.setVisible(true);
        userloginFrame.setSize(420,200);
        // submit button
        login.setBounds(20, 200, 60, 40);
        login.setPreferredSize(new Dimension(150,30));
        login.setText("login");
        userloginPanel.add(login);
        
        
        


        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String username = usernametext.getText();
                String password = passwordtext.getText();

                String sql = "SELECT Password from users where username = ?";
                String pass_real = db.validatePass(sql, username);
                
                
                if(e.getSource()==login){

                    if (username.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "please enter both username and password.");
                        return;
                    }
                    

                    if (password.equals(pass_real)){
                        System.out.println("Login Succesfull!");
                        JOptionPane.showMessageDialog(null,"login succesfull");
                       User u = new User();
                       sql = "SELECT UserID from users where username = ?";
                       int userID = db.fetchUserID(sql, username);
                       u.userMenu(userID);
                    }else{
                        System.out.println("Invalid password! Try again!");
                        JOptionPane.showMessageDialog(null,"incorect password");
                    }
                }
            }
        });
        

        /* 
        System.out.println("Enter your username: ");
        String username = sc.next();
        
        System.out.println("Enter your password: ");
        String password = sc.next();

        String sql = "SELECT Password from users where username = ?";
        String pass_real = db.validatePass(sql, username);

        if (password.equals(pass_real)){
            System.out.println("Login Succesfull!");
           User u = new User();
           sql = "SELECT UserID from users where username = ?";
           int userID = db.fetchUserID(sql, username);
           u.userMenu(userID);
        }else{
            System.out.println("Invalid password! Try again!");
        }
        */
    }


    //methods to sign up and login for admin class
    static void admin_signup(){

        JPanel userPanel = new JPanel();
        userPanel.setBackground(new Color(240, 101, 124));
        JFrame user_signFrame = new JFrame();
        user_signFrame.setVisible(true);
        user_signFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        user_signFrame.setSize(320, 420);
        user_signFrame.add(userPanel);
        user_signFrame.setResizable(false);
        JLabel usname = new JLabel();
        usname.setText("Enter the user name");

        //text field
        JTextField usernametext = new JTextField();
        usernametext.setPreferredSize(new Dimension(250,30));
        usname.setFont(new Font("Serif",Font.ITALIC,15));



        userPanel.add(usname);
        userPanel.add(usernametext);

        JLabel pass = new JLabel();
        pass.setText("create your password");

        //text field
        JTextField passwordtext = new JTextField();
        passwordtext.setPreferredSize(new Dimension(250,30));
        pass.setFont(new Font("Serif",Font.ITALIC,15));



        userPanel.add(pass);
        userPanel.add(passwordtext);


        JButton userdetailsButton = new JButton();
        userdetailsButton.setBounds(20, 120, 60, 40);
        userdetailsButton.setPreferredSize(new Dimension(150,30));
        userdetailsButton.setText("Sign in");
        userPanel.add(userdetailsButton);
        userdetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String username = usernametext.getText();
                String password = passwordtext.getText();
                
                String sql = "INSERT INTO admin(Username,Password) values(?,?)";
                Object[] values = {username,password};
            
                int rowsAffected = db.executeUpdate(sql, values);

                
                if(rowsAffected>0){
                    System.out.println("User registered successfully");
                    JOptionPane.showMessageDialog(null, "Admin regestered successfully");
                }
                else{
                    System.out.println("Something went wrong.Sign up failed.");
                    JOptionPane.showMessageDialog(null, "Somthing error");
                }
                    if(e.getSource()==userdetailsButton){
                    
                    }
                    }
        });

        /* 
        System.out.println("Enter your username: ");
        String username = sc.next();
        
        System.out.println("Enter your password: ");
        String password = sc.next();

        String sql = "INSERT INTO admin(Username,Password) values(?,?)";
        Object[] values = {username,password};
        int rowsAffected = db.executeUpdate(sql, values); 
        if(rowsAffected>0)
            System.out.println("Admin registered successfully");
        else
            System.out.println("Something went wrong.Sign up failed.");
            */
    }

    static void admin_login(){

        JPanel userloginPanel = new JPanel();
        userloginPanel.setBackground(new Color(240, 101, 124));
        JFrame userloginFrame = new JFrame();
        JLabel userloginname = new JLabel();
        JButton login = new JButton();
        JTextField usernametext = new JTextField();
        JTextField passwordtext = new JTextField();

        userloginname.setText("Enter user name  ");
        usernametext.setPreferredSize(new Dimension(250,30));
        userloginname.setFont(new Font("Serif",Font.ITALIC,15));



        userloginPanel.add(userloginname);
        userloginPanel.add(usernametext);

        JLabel pass = new JLabel();
        pass.setText("Enter your password");

        //text field
    
        passwordtext.setPreferredSize(new Dimension(250,30));
        pass.setFont(new Font("Serif",Font.ITALIC,15));



        userloginPanel.add(pass);
        userloginPanel.add(passwordtext);
        userloginFrame.add(userloginPanel);
        userloginFrame.setVisible(true);
        userloginFrame.setSize(420,200);
        // submit button
        login.setBounds(20, 200, 60, 40);
        login.setPreferredSize(new Dimension(150,30));
        login.setText("login");
        userloginPanel.add(login);


        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String username = usernametext.getText();
                String password = passwordtext.getText();

                String sql = "SELECT Password from admin where username = ?";
                String pass_real = db.validatePass(sql, username);
                
                
                if(e.getSource()==login){

                    if (username.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "please enter both username and password.");
                        return;
                    }
                    

                    if (password.equals(pass_real)){
                        System.out.println("Login Succesfull!");
                        JOptionPane.showMessageDialog(null,"login succesfull");
                        Admin a = new Admin();
                        a.adminMenu();
                        
                    }else{
                        System.out.println("Invalid password! Try again!");
                        JOptionPane.showMessageDialog(null,"incorect password");
                    }
                }
            }
        });

        /* 
        System.out.println("Enter your username: ");
        String username = sc.next();
        
        System.out.println("Enter your password: ");
        String password = sc.next();

        String sql = "SELECT Password from admin where username = ?";
        String pass_real = db.validatePass(sql, username);

        if (password.equals(pass_real)){
            System.out.println("Login Succesfull!");
            Admin a = new Admin();
            a.adminMenu();
        }else{
            System.out.println("Invalid password! Try again!");
        }*/
    }
    



    //the main page where user gets option to do so
        public static void main(String[] args) {
        Scanner sc2 = new Scanner(System.in);
         //jpannel 
    JPanel homePanel = new JPanel();

    //user sign in button
    JButton user_sign_in =new JButton();
    user_sign_in.setBounds(20, 200, 60, 40);
    user_sign_in.setPreferredSize(new Dimension(150,30));
    user_sign_in.setText("Sign in as user");
    //user login button
    JButton Login_User = new JButton();
    Login_User.setBounds(20, 70, 60, 40);
    Login_User.setPreferredSize(new Dimension(150,30));
    Login_User.setText("Login as user");

    //admin sign in button
    JButton Admin_sign_in = new JButton();
    Admin_sign_in.setBounds(20, 90, 60, 40);
    Admin_sign_in.setPreferredSize(new Dimension(150,30));
    Admin_sign_in.setText("Sign in as Admin");

    //admin login button
    JButton admin_login = new JButton();
    admin_login.setBounds(20, 120, 60, 40);
    admin_login.setPreferredSize(new Dimension(150,30));
    admin_login.setText("Admin Login");

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
    homePanel.add(user_sign_in);
    homePanel.add(Login_User);
    homePanel.add(Admin_sign_in);
    homePanel.add(admin_login);
    //home settings 
    home.setVisible(true);
    home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    home.setSize(320, 250);
    home.add(homePanel);
    home.setResizable(false);
        //action litener for user
    user_sign_in.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            
            
            if(e.getSource()==user_sign_in){
                user_signup();
            }
        }
    });

    Login_User.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource()==Login_User){
                user_login();
            }
        }
    });

    Admin_sign_in.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource()==Admin_sign_in){
                admin_signup();
            }
        }
    });

    admin_login.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource()==admin_login){
                admin_login();
            }
        }
    });


        int choice;
        System.out.println("----- Movie Ticket Booking System -----");
        System.out.println("Press 1 to sign up as user.");
        System.out.println("Press 2 to login as user.");
        System.out.println("Press 3 to sign up as admin.");
        System.out.println("Press 4 to login as admin.");
        System.out.println("----- --------------------------- -----");
        System.out.print("Enter your choice:");
        choice = sc2.nextInt();
        switch(choice){
            case 1:
                //user signup
                user_signup();
                break;
            case 2: 
                //user login
                user_login();
                break;
            case 3:
                //admin signup
                admin_signup();
                break;
            case 4:
                //admin login
                admin_login();
                break;
        }
        sc2.close();
    }

}
