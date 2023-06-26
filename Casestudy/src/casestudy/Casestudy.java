package casestudy;
//import java packages
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.PreparedStatement;

public class Casestudy{                                                             //class name
    String driver = "com.mysql.cj.jdbc.Driver";                                     //driver database
    String url ="jdbc:mysql://localhost:3306?zeroDateTimeBehavior=CONVERT_TO_NULL"; //url database
    String user = "root";                                                           //user database
    String pass = "";                                                               //password database
    String options_empty [] = {"Select categories first"};                          //array for empty combobox (sub category)
    String[] Cloth_Size = {"--Select--","S","M","L","XL","2XL","3XL","4XL"};        //array for cloth size
    String[] politeknik = {"--Select--","POLITEKNIK SEBERANG PERAI",                //array for list of politeknik in Malaysia
        "POLITEKNIK TUANKU SULTANAH BAHIYAH", "POLTEKNIK SULTAN IDRIS SHAH",
        "POLITEKNIK KOTA BHARU", "POLITEKNIK BALIK PULAU",
        "POLITENIK SULTAN MIZAN ZAINAL ABIDIN", "POLITEKNIK IBRAHIM SULTAN",
        "POLITEKNIK MUADZAM SHAH", "POLITEKNIK KUCHING",
        "KOLEJ KOMUNITI KUALA PILAH", "KOLEJ KOMUNITI JELEBU",
        "POLITEKNIK TUANKU SYED SIRAJUDDIN","POLITEKNIK SULTAN HAJI AHMAD SHAH",
        "POLITEKNIK SULTAN ABDUL HALIM MU'ADZAM SHAH","POLITEKNIK PORT DICKSON",
        "POLITEKNIK KOTA TERENGGANU", "POLITEKNIK KOTA KINABALU",
        "POLITEKNIK MUKAH", "POLITEKNIK UNGKU OMAR", "POLITEKNIK MERSING",
        "POLITEKNIK SULTAN AZLAN SHAH", "POLITEKNIK NILAI",
        "POLITEKNIK SHAH ALAM"};
    String[] Categories = {"--Select--","Simurosot", "Air Drone", "Androsot",      //array for categories
        "Robosot", "My Bot", "Hurocup", "Ai Bot", "Business"};
    String[] Simurosot = {"--Select--","Autonomous Drone Simulation Challenge",    //array for simurosot
        "Autonomous Car Race Challenge"};
    String[] Airdrone = {"--Select--","Autonomous Drone Line Follower",            //array for airdrone
        "Emergency Delivery and Tower Inspection", "FPV Drone Race"};
    String[] Androsot = {"--Select--","Androsot (Free Kick)",                      //array for androsot
        "Androsot (Dribble and Attack)", "Androsot (Soccer)"};
    String[] Robosot = {"--Select--","Robosot Race Challenge (Development)",       //array for robosot
        "Robosot Race Challenge (Applications)", "Robosot Avoidance Challenge"};
    String[] MyBot = {"--Select--","MyBOT ACR", "MyBOT Gladiator (Auto)"};         //array for mybot
    String[] Hurocup = {"--Select--","HuroCup Kid (Sprint)",                       //array for hurocup
        "HuroCup Kid (Weightlifting)", "HuroCup Kid (Lift & Carry)",
        "HuroCup Kid (Basketball)", "HuroCup Kid (Triple Jump)",
        "HuroCup Kid (Marathon)", "HuroCup Kid (Archery)"};
    String[] AiBot = {"--Select--","Voice-command Challenge",                       //array for aibot
        "Parking Challenge"};
    String[] Business = {"Business&Innovation"};                                    //array for business
    String name,id,nophone,email,clothSize,institution,category,                    //declare variables
            subCategory,accommodation,coachName,regDate,TotalFees;

    JFrame f;                                                                       //declare JFrame
    JLabel lblTitle,lblName,lblID,lblPhone,lblEmail,lblCSize,lblIns,                //declare JLabel
            lblCategories,lblSCategories,lblAcc,lblCouchName,lblDate,
            lblTotalFees;
    JTextField txtName,txtID,txtPhone,txtEmail,txtCName,txtDate,txtFees;            //declare JTextField
    JComboBox cloth_size,ins,categories,sub_categories;                             //declare JComboBox
    JRadioButton HPSP,homestay,hotel;                                               //declare JRadioButton
    JButton Sub,Clear,Search,Update,Del;                                            //declare JButton
    Casestudy(){                                                                    //method CaseStudy
        f = new JFrame();                                                           //create JFrame
        f.setTitle("Fira Registration System");                                     //set title
        f.setSize(550,650);                                                         //set size
        f.setLayout(null);                                                          //set layout
        f.setVisible(true);                                                         //set visibility
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                           //close JFrame if click exit
        
        lblTitle = new JLabel("FIRA ROBOTICs MALAYSIA");                            //create JLabel
        lblTitle.setBounds(5,5,500,70);                                             //set width,height,location
        lblTitle.setFont(new Font("Arial", Font.BOLD,25));                          //set font
        f.add(lblTitle);                                                            //add to JFrame
        
        lblName = new JLabel("Participant name:");                                  //create JLabel
        lblName.setBounds(5,80,180,30);                                             //set width,height,location
        lblName.setFont(new Font("Arial",Font.BOLD,15));                            //set font
        f.add(lblName);                                                             //add to JFrame
        
        txtName = new JTextField();                                                 //create JTextField
        txtName.setBounds(170,80,310,20);                                           //set width,height,location
        f.add(txtName);                                                             //add to JFrame
        
        lblID = new JLabel("Matric Number:");                                       //create JLabel
        lblID.setBounds(5,120,180,30);                                              //set width,height,location
        lblID.setFont(new Font("Arial",Font.BOLD,15));                              //set font
        f.add(lblID);                                                               //add to JFrame
        
        txtID = new JTextField();                                                   //create JTextField
        txtID.setBounds(170,126,310,20);                                            //set width,height,location
        f.add(txtID);                                                               //add to JFrame
        
        lblPhone = new JLabel("Phone Number:");                                     //create JLabel
        lblPhone.setBounds(5,160,180,30);                                           //set width,height,location
        lblPhone.setFont(new Font("Arial",Font.BOLD,15));                           //set font
        f.add(lblPhone);                                                            //add to JFrame
        
        txtPhone = new JTextField();                                                //create JTextField
        txtPhone.setBounds(170,166,310,20);                                         //set width,height,location
        f.add(txtPhone);                                                            //add to JFrame
        
        lblEmail = new JLabel("Email:");                                            //create JLabel
        lblEmail.setBounds(5,200,180,30);                                           //set width,height,location
        lblEmail.setFont(new Font("Arial",Font.BOLD,15));                           //set font
        f.add(lblEmail);                                                            //add to JFrame
        
        txtEmail = new JTextField();                                                //create JTextField
        txtEmail.setBounds(170,206,310,20);                                         //set width,height,location
        f.add(txtEmail);                                                            //add to JFrame
        
        lblCSize = new JLabel("Cloth:");                                            //create JLabel
        lblCSize.setBounds(5,240,180,30);                                           //set width,height,location
        lblCSize.setFont(new Font("Arial",Font.BOLD,15));                           //set font
        f.add(lblCSize);                                                            //add to JFrame
        
        cloth_size = new JComboBox(Cloth_Size);                                     //create JComboBox
        cloth_size.setBounds(170,246,310,20);                                       //set width,height,location
        f.add(cloth_size);                                                          //add to JFrame
        
        lblIns = new JLabel("Institution:");                                        //create JLabel
        lblIns.setBounds(5,280,180,30);                                             //set width,height,location
        lblIns.setFont(new Font("Arial",Font.BOLD,15));                             //set font
        f.add(lblIns);                                                              //add to JFrame
        
        ins = new JComboBox(politeknik);                                            //create JComboBox
        ins.setBounds(170,286,310,20);                                              //set width,height,location
        f.add(ins);                                                                 //add to JFrame
        
        lblCategories = new JLabel("Categories:");                                  //create JLabel
        lblCategories.setBounds(5,320,180,30);                                      //set width,height,location
        lblCategories.setFont(new Font("Arial",Font.BOLD,15));                      //set font
        f.add(lblCategories);                                                       //add to JFrame
        
        categories = new JComboBox(Categories);                                     //create JComboBox
        categories.setBounds(170,326,310,20);                                       //set width,height,location
        f.add(categories);                                                          //add to JFrame
        
       
        lblSCategories = new JLabel("Sub-categories:");                             //create JLabel
        lblSCategories.setBounds(5,360,180,30);                                     //set width,height,location
        lblSCategories.setFont(new Font("Arial",Font.BOLD,15));                     //set font
        f.add(lblSCategories);                                                      //add to JFrame
        
       categories.addActionListener((ActionEvent e) -> {                           //add action listener to JComboBox categories 
            switch (categories.getSelectedIndex()) {                                //switch statement
                case 0 -> {                                                         //if nothing is selected
                        DefaultComboBoxModel options = new DefaultComboBoxModel     //create new DefaultComboBoxModel
                        (options_empty);                                            //set array options_empty
                        sub_categories.setModel(options);                           //set JComboBox to defaut value
                    }
                case 1 -> {                                                         //if simurosot is selected
                        DefaultComboBoxModel options = new DefaultComboBoxModel     //create new DefaultComboBoxModel
                        (Simurosot);                                                //set array simurosot
                        sub_categories.setModel(options);                           //set JComboBox to defaut value
                    }
                case 2 -> {                                                         //if airdrone is selected
                        DefaultComboBoxModel options = new DefaultComboBoxModel     //create new DefaultComboBoxModel
                        (Airdrone);                                                 //set array airdrone
                        sub_categories.setModel(options);                           //set JComboBox to defaut value
                    }
                case 3 -> {                                                         //if androsot is selected
                        DefaultComboBoxModel options = new DefaultComboBoxModel     //create new DefaultComboBoxModel
                        ( Androsot);                                                //set array androsot
                        sub_categories.setModel(options);                           //set JComboBox to defaut value
                    }
                case 4 -> {                                                         //if robosot is selected
                        DefaultComboBoxModel options = new DefaultComboBoxModel     //create new DefaultComboBoxModel
                        ( Robosot);                                                 //set array robosot
                        sub_categories.setModel(options);                           //set JComboBox to defaut value
                    }
                case 5 -> {                                                         //if mybot is selected
                        DefaultComboBoxModel options = new DefaultComboBoxModel     //create new DefaultComboBoxModel
                        ( MyBot);                                                   //set array mybot
                        sub_categories.setModel(options);                           //set JComboBox to defaut value
                    }
                case 6 -> {                                                         //if hurocup is selected
                        DefaultComboBoxModel options = new DefaultComboBoxModel     //create new DefaultComboBoxModel
                        ( Hurocup);                                                 //set array hurocup
                        sub_categories.setModel(options);                           //set JComboBox to defaut value
                    }
                case 7 -> {                                                         //if aibot is selected
                        DefaultComboBoxModel options = new DefaultComboBoxModel     //create new DefaultComboBoxModel
                        ( AiBot);                                                   //set array aibot
                        sub_categories.setModel(options);                           //set JComboBox to defaut value
                    }
                case 8 -> {                                                         //if business is selected
                        DefaultComboBoxModel options = new DefaultComboBoxModel     //create new DefaultComboBoxModel
                        ( Business);                                                //set array business
                        sub_categories.setModel(options);                           //set JComboBox to defaut value
                    }
                default -> {
                        DefaultComboBoxModel options = new DefaultComboBoxModel     //create new DefaultComboBoxModel
                        (options_empty);                                            //set array options_empty
                        sub_categories.setModel(options);                           //set JComboBox to defaut value
                }
            }
        }
        );
 
        sub_categories = new JComboBox(options_empty);                              //create JComboBox
        sub_categories.setBounds(170,366,310,20);                                   //set width,height,location
        f.add(sub_categories);                                                      //add to JFrame
        
        lblAcc = new JLabel("Accommodation:");                                      //create JLabel
        lblAcc.setBounds(5,400,180,30);                                             //set width,height,location
        lblAcc.setFont(new Font("Arial",Font.BOLD,15));                             //set font
        f.add(lblAcc);                                                              //add to JFrame
        
        ButtonGroup b = new ButtonGroup();                                          //create button group
        
        HPSP = new JRadioButton("Hostel PSP");                                      //create JRadioButton
        HPSP.setBounds(170,400,120,30);                                             //set width,height,location
        HPSP.setFont(new Font("Arial",Font.BOLD,15));                               //set font style
        HPSP.setActionCommand("Hostel PSP");                                        //set action command
        f.add(HPSP);                                                                //add to JFrame
        b.add(HPSP);                                                                //add to button group
        
        homestay = new JRadioButton("Homestay");                                    //create JRadioButton
        homestay.setBounds(295,400,120,30);                                         //set width,height,location
        homestay.setFont(new Font("Arial",Font.BOLD,15));                           //set font
        homestay.setActionCommand("Homestay");                                      //set action command
        f.add(homestay);                                                            //add to JFrame
        b.add(homestay);                                                            //add to button group
        
        hotel = new JRadioButton("Hotel");                                          //create JRadioButton
        hotel.setBounds(420,400,120,30);                                            //set width,height,location
        hotel.setFont(new Font("Arial",Font.BOLD,15));                              //set font
        hotel.setActionCommand("Hotel");                                            //set action command
        f.add(hotel);                                                               //add to JFrame
        b.add(hotel);                                                               //add to button group
        
        lblCouchName = new JLabel("Couch Name:");                                   //create JLabel
        lblCouchName.setBounds(5,440,180,30);                                       //set width,height,location
        lblCouchName.setFont(new Font("Arial",Font.BOLD,15));                       //set font
        f.add(lblCouchName);                                                        //add to JFrame
        
        txtCName = new JTextField();                                                //create JTextField
        txtCName.setBounds(170,440,310,20);                                         //set width,height,location
        f.add(txtCName);                                                            //add to JFrame
        
        lblDate = new JLabel("Date of registration:");                              //create JLabel
        lblDate.setBounds(5,480,180,30);                                            //set width,height,location
        lblDate.setFont(new Font("Arial",Font.BOLD,15));                            //set font
        f.add(lblDate);                                                             //add to JFrame
        
        LocalDate currentDate = LocalDate.now();                                    //get local date
        DateTimeFormatter formatdate = DateTimeFormatter.ofPattern                  //create format for local date
        ("yyyy-MM-dd");
        String Today = currentDate.format(formatdate);                              //set current format

        txtDate = new JTextField(Today);                                            //create JTextField
        txtDate.setBounds(170,480,310,20);                                          //set width,height,location
        txtDate.setEnabled(false);                                                  //set JTextField as disabled
        f.add(txtDate);                                                             //add to JFrame
        
        lblTotalFees = new JLabel("Total Fees :");                                  //create JLabel
        lblTotalFees.setBounds(5,520,180,30);                                       //set width,height,location
        lblTotalFees.setFont(new Font("Arial",Font.BOLD,15));                       //set font
        f.add(lblTotalFees);                                                        //add to JFrame
        
        txtFees = new JTextField("RM ");                                            //create JTextField
        txtFees.setBounds(170,520,310,20);                                          //set width,height,location
        txtFees.setEnabled(false);                                                  //set JTextField as disabled
        f.add(txtFees);                                                             //add to JFrame
        
        Sub = new JButton("SUBMIT");                                                //create JButton
        Sub.setBounds(25,570,85,20);                                                //set width,height,location
        f.add(Sub);                                                                 //add to JFrame
        try{
            Class.forName(driver);                                                  //Load the JDBC driver
            Connection kon = DriverManager.getConnection(url, user, pass);          //Establish a database connection
            Statement stm = kon.createStatement();                                  //Create a Statement object
            String createDBQuery = "CREATE DATABASE IF NOT EXISTS fira";            //sql statement to create database
            stm.executeUpdate(createDBQuery);                                       //execute sql statement

            String useDBQuery = "USE fira";                                         // Switch to the Fira database
            stm.execute(useDBQuery);                                                //execute sql statement
        
            String createTableQuery = "CREATE TABLE IF NOT EXISTS peserta ("        // Create the peserta table
                    + "participant_name VARCHAR(100), "
                    + "id_num VARCHAR(12), "
                    + "phone VARCHAR(12), "
                    + "email VARCHAR(100), "
                    + "size VARCHAR(10), "
                    + "institute VARCHAR(200), "
                    + "category VARCHAR(300), "
                    + "sub_category VARCHAR(300), "
                    + "stay VARCHAR(100), "
                    + "coach VARCHAR(100), "
                    + "date_reg VARCHAR(100), "
                    + "fees FLOAT)";
        stm.executeUpdate(createTableQuery);                                        //execute sql statement
        }
        catch(ClassNotFoundException | SQLException e){                             //if failed to create database and table
            JOptionPane.showMessageDialog(null,"create database not success");      //popup message
        }
        
        Sub.addActionListener((ActionEvent a) -> {                                  //add action listener to JButton Sub
            double totalFees=30;                                                    //double variable
            if (HPSP.isSelected()) {                                                //if HPSP selected, do
                accommodation = "HOSTEL PSP";                                       //set accommodation to HOSTEL PSP
                totalFees += 15.0;                                                  //set totalFees to totalFees + 15.0
            } else if (homestay.isSelected()) {                                     //if homestay selected, do
                accommodation = "HOMESTAY";                                         //set accommodation to HOMESTAY
            } else if (hotel.isSelected()) {                                        //if hotel selected, do
                accommodation = "HOTEL";                                            //set accommodation to HOTEL
            }
            
            DecimalFormat df = new DecimalFormat("0.00");                           //create new DecimalFormat
            TotalFees = df.format(totalFees);                                       //set string TotalFees 2 digit decimal number
            txtFees.setEnabled(true);                                               //set txtFees Enabled to true
            txtFees.setText("RM "+TotalFees);                                       //set text to txtFees
            txtFees.setEnabled(false);                                              //set txtFees Enabled to false
            try{
                Class.forName(driver);                                              //Load the JDBC driver
                Connection kon = DriverManager.getConnection(url, user, pass);      //Establish a database connection
                Statement stm = kon.createStatement();                              //Create a Statement object
                
                String useDBQuery = "USE fira";                                     //Switch to the Fira database
                stm.execute(useDBQuery);                                            //execute sql statement
                
                name = txtName.getText().toUpperCase();                             //set string name value
                id = txtID.getText().toUpperCase();                                 //set string id value
                nophone = txtPhone.getText().toUpperCase();                         //set string nophone value
                email = txtEmail.getText();                                         //set string email value
                clothSize = ((String)cloth_size.getSelectedItem()).toUpperCase();   //set string clothSize value
                institution = ((String) ins.getSelectedItem()).toUpperCase();       //set string institution value
                category = ((String) categories.getSelectedItem()).toUpperCase();   //set string category value
                subCategory = (String) sub_categories.getSelectedItem();            //set string subCategory value
                subCategory=subCategory.toUpperCase();                              //set subCategory to uppercase
                coachName = txtCName.getText().toUpperCase();                       //set string coachName value
                regDate = txtDate.getText();                                        //set string regDate value
                
                if(!name.equals("") && !id.equals("") && !nophone.equals("") &&     //if form not null
                        !email.equals("") && !clothSize.equals("")&& 
                        !institution.equals("") && !category.equals("") && 
                        !subCategory.equals("") && !accommodation.equals("") && 
                        !coachName.equals("") && !regDate.equals("")){
                    String selectQuery = 
                            "SELECT id_num FROM peserta WHERE id_num = ?";          //Check if ID exists in peserta table
                    PreparedStatement selectStatement = 
                            kon.prepareStatement(selectQuery);                      //Prepare SQL statement for execution
                    selectStatement.setString(1, id);
                    ResultSet resultSet = selectStatement.executeQuery();           //Execute the SQL query and retrieve the result set

                    if (resultSet.next()) {                                         //Check if there is a next row in the result set
                        JOptionPane.showMessageDialog
                        (null, "You have already registered.");                     //popup message
                    } else {
                        String sql = "Insert into peserta (participant_name, "      //insert into table peserta in fira database
                            + "id_num, phone, email, size, institute, "
                            + "category, sub_category, stay, coach, "
                            + "date_reg, fees) values ('"
                            + name + "', '" + id + "', '" + nophone + "', '"
                            + email + "', '" + clothSize + "', '" + institution
                            + "', '" + category + "', '"+ subCategory + "', '"
                            + accommodation + "', '" + coachName + "', '"
                            + regDate + "', '" + totalFees + "')";

                        stm.executeUpdate(sql);                                     //execute sql statement
                        JOptionPane.showMessageDialog(null, "Successful Registered");//popup message
                    }   
                }
                else{
                    JOptionPane.showMessageDialog(null,"Please fill all the data"); //popup message
                }
            }
            catch(HeadlessException | ClassNotFoundException | SQLException e){     //Handle exception with multiple types
                JOptionPane.showMessageDialog(null,"not success");                  //popup message
            }
        }
        );
        
        Clear = new JButton("CLEAR");                                               //create JButton
        Clear.setBounds(120,570,85,20);                                             //set width,height,location
        f.add(Clear);                                                               //add to JFrame
        
        Clear.addActionListener((ActionEvent e) -> {                                //add action listener to JButton Clear
            txtName.setText("");                                                    //set txtName to ""
            txtID.setText("");                                                      //set txtID to ""
            txtPhone.setText("");                                                   //set txtPhone to ""
            txtEmail.setText("");                                                   //set txtEmail to ""
            cloth_size.setSelectedIndex(0);                                         //set selected Index 0 to cloth_size
            ins.setSelectedIndex(0);                                                //set selected Index 0 to ins
            categories.setSelectedIndex(0);                                         //set selected Index 0 to categories
            sub_categories.setSelectedIndex(0);                                     //set selected Index 0 to sub_categories
            b.clearSelection();                                                     //clear Selection on b
            txtCName.setText("");                                                   //set txtCName tu ""
            txtFees.setText("RM ");                                                 //set txtFees tu "RM "
        });
        
        Search = new JButton("SEARCH");                                             //create JButton
        Search.setBounds(215,570,85,20);                                            //set width,height,location
        f.add(Search);                                                              //add to JFrame
        
        Search.addActionListener((ActionEvent e) -> {                               //add action listener to JButton Search
            String idNumber = JOptionPane.showInputDialog
                        (f, "Enter ID Number:");                                    //Prompt the user for the ID number
            try {
                Class.forName(driver);                                              //Load the JDBC driver
                try (Connection kon = DriverManager.getConnection                   //Establish a database connection
                (url, user, pass);
                        Statement stn = kon.createStatement()) {                    //Create a Statement object
                    
                    String useDBQuery = "USE fira";                                 //Switch to the Fira database
                    stn.execute(useDBQuery);                                        //execute sql statement
                    
                    String sql = "SELECT * FROM peserta WHERE id_num = '" +         //select syntax form table peserta in fira database
                            idNumber + "'";
                    try (ResultSet rs = stn.executeQuery(sql)) {                    //Execute query and manage result
                        if (rs.next()) {                                            //Check if there is a next result
                            txtName.setText(rs.getString("participant_name"));      //Set text of txtName field
                            txtID.setText(rs.getString("id_num"));                  //Set text of txtID field
                            txtPhone.setText(rs.getString("phone"));                //Set text of txtPhone field
                            txtEmail.setText(rs.getString("email"));                //Set text of txtEmail field
                            cloth_size.setSelectedItem(rs.getString("size"));       //Set Selected Item of cloth_size field
                            ins.setSelectedItem(rs.getString("institute"));         //Set Selected Item of ins field
                            categories.setSelectedItem(rs.getString("category"));   //Set Selected Item of categories field
                            sub_categories.setSelectedItem(rs.getString             //Set Selected Item of sub_categories field
                                                ("sub_category"));
                            String accommodation1 = rs.getString("stay");           //Retrieve value of "stay" column
                            switch (accommodation1) {                               //switch statement
                                case "HOSTEL PSP" -> HPSP.setSelected(true);        //Set HPSP checkbox as selected 
                                case "HOTEL" -> hotel.setSelected(true);            //Set HPSP checkbox as selected
                                case "HOMESTAY" -> homestay.setSelected(true);      //Set HPSP checkbox as selected
                                default -> {
                                }
                            }
                            txtCName.setText(rs.getString("coach"));                //Set text of txtName field
                            txtDate.setText(rs.getString("date_reg"));              //Set text of txtName field
                            txtFees.setText(rs.getString("fees"));                  //Set text of txtName field
                        } else {                                                    //else statement
                            JOptionPane.showMessageDialog                           //popup Message
                            (f, "Participant not found.");
                        }
                    }
                }
            }catch (HeadlessException | ClassNotFoundException | SQLException ex) { //Catch and handle exceptions
                JOptionPane.showMessageDialog                                       //popup message
                (f, "Failed to retrieve participant data: " + ex.getMessage());
            }
        });
        
        Update = new JButton("UPDATE");                                             //create JButton
        Update.setBounds(310,570,85,20);                                            //set width,height,location
        f.add(Update);                                                              //add to JFrame
        
        Update.addActionListener((ActionEvent e) -> {                               //add action listener to JButton Update
            try {
                double totalFees=30;                                                //double variable
                if (HPSP.isSelected()) {                                            //if HPSP selected, do
                    accommodation = "HOSTEL PSP";                                   //set accommodation to HOSTEL PSP
                    totalFees += 15.0;                                              //set totalFees to totalFees + 15.0
                } else if (homestay.isSelected()) {                                 //if homestay selected, do
                    accommodation = "HOMESTAY";                                     //set accommodation to HOMESTAY
                } else if (hotel.isSelected()) {                                    //if hotel selected, do
                    accommodation = "HOTEL";                                        //set accommodation to HOTEL
                }
            
                Class.forName(driver);                                              //Load the JDBC driver
                try (Connection kon = DriverManager.getConnection(url, user, pass); //Establish a database connection
                        Statement stn = kon.createStatement()) {                    //Create a Statement object
                    
                    String useDBQuery = "USE fira";                                 // Switch to the Fira database
                    stn.execute(useDBQuery);                                        //execute sql statement
                    
                    name = txtName.getText().toUpperCase();                         //set string name value
                    id = txtID.getText().toUpperCase();                             //set string id value
                    nophone = txtPhone.getText().toUpperCase();                     //set string nophone value
                    email = txtEmail.getText();                                     //set string email value
                    clothSize = (String)cloth_size.getSelectedItem();               //set string clothSize value
                    clothSize=clothSize.toUpperCase();                              //set clothSize to upper
                    institution = ((String) ins.getSelectedItem()).toUpperCase();   //set string institution value
                    category = (String) categories.getSelectedItem();               //set string category value
                    category=category.toUpperCase();                                //set category to upper
                    subCategory = (String) sub_categories.getSelectedItem();        //set string subCategory value
                    subCategory=subCategory.toUpperCase();                          //set subCategory to uppercase
                    coachName = txtCName.getText().toUpperCase();                   //set string coachName value
                    regDate = txtDate.getText();                                    //set string regDate value
                    
                    String sql = "UPDATE peserta SET participant_name = '"          //update syntax form table peserta in fira database
                            + name + "', phone = '" + nophone + "', email = '"
                            + email + "', size = '" + clothSize
                            + "', institute = '" + institution
                            + "', category = '" + category
                            + "', sub_category = '" + subCategory
                            + "', stay = '" + accommodation
                            + "', coach = '" + coachName + "', date_reg = '"
                            + regDate + "', fees = '" + totalFees
                            + "' WHERE id_num = '" + id + "'";
                    
                    int rowsUpdated = stn.executeUpdate(sql);                       //Execute update query and get the number of updated rows
                    
                    if (rowsUpdated > 0) {                                          //if rowsUpdated > 0 , do
                        JOptionPane.showMessageDialog                               //popup message
                        (f, "Data updated successfully.");
                    } else {                                                        //if rowsUpdated !> 0 , do
                        JOptionPane.showMessageDialog                               //popup message
                        (f, "No rows were updated.");
                    }
                    
                }
            } catch (HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog                                       //popup message
                (f, "Failed to update data: " + ex.getMessage());
            }
        });
        
        Del = new JButton("DELETE");                                                //create JButton
        Del.setBounds(405,570,85,20);                                               //set width,height,location
        f.add(Del);                                                                 //add to JFrame
        
        Del.addActionListener((ActionEvent e) -> {                                  //add action listener to JButton Del
            String id1 = JOptionPane.showInputDialog                                //Prompt the user for the ID number
                        (f, "Enter ID Number:");
            try {
                Class.forName(driver);                                              //Load the JDBC driver
                try (Connection kon = DriverManager.getConnection(url, user, pass); //Establish a database connection
                        Statement stn = kon.createStatement()) {                    //Create a Statement object
                    
                    String useDBQuery = "USE fira";                                 // Switch to the Fira database
                    stn.execute(useDBQuery);                                        //execute sql statement
                    String sql = "DELETE FROM peserta WHERE id_num = '" + id1 + "'";//delete syntax form table peserta in fira database
                    int rowsDeleted = stn.executeUpdate(sql);                       //Execute delete query and get the number of deleted rows
                    if (rowsDeleted > 0) {                                          //if rowsDeleted > 0 , do
                        
                    txtName.setText("");                                            //set txtName to ""
                    txtID.setText("");                                              //set txtID to ""
                    txtPhone.setText("");                                           //set txtPhone to ""
                    txtEmail.setText("");                                           //set txtEmail to ""
                    cloth_size.setSelectedIndex(0);                                 //set selected Index 0 to cloth_size
                    ins.setSelectedIndex(0);                                        //set selected Index 0 to ins
                    categories.setSelectedIndex(0);                                 //set selected Index 0 to categories
                    sub_categories.setSelectedIndex(0);                             //set selected Index 0 to sub_categories
                    b.clearSelection();                                             //clear Selection on b
                    txtCName.setText("");                                           //set txtCName tu ""
                    txtFees.setText("RM ");                                          //set txtFees tu "RM "
                        
                        JOptionPane.showMessageDialog                               //popup message
                        (f, "Data deleted successfully.");
                    } else {
                        JOptionPane.showMessageDialog                               //popup message
                        (f, "No data were deleted.");
                    }
                }
            }catch (HeadlessException | ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog                                       //popup message
                (f, "Failed to delete data: " + ex.getMessage());
            }
        });
        
    }

    public static void main(String[] args) {
       Casestudy shw = new Casestudy();                                             //call method
    }
    
}