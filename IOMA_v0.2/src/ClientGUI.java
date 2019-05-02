import javax.swing.*;
import java.io.PrintWriter;
import java.net.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI
{
  //Global variables
  private static Client chatClient;
  public static String UserName = "Annonymous";
  
  //GUI globals
  public static JFrame mainWindow = new JFrame();
  private static JButton b_about = new JButton();
  private static JButton b_connect = new JButton();
  private static JButton b_disconnect = new JButton();
  private static JButton b_help = new JButton();
  private static JButton b_send = new JButton();
  private static JLabel l_message = new JLabel("Message: ");
  public static JTextField tF_Message = new JTextField();
  private static JLabel l_Conversation = new JLabel();
  public static JTextArea tA_Conversation = new JTextArea();
  private static JScrollPane sP_Conversation = new JScrollPane();
  private static JLabel l_Online = new JLabel();
  public static JList jL_Online = new JList();
  private static JScrollPane sP_Online = new JScrollPane();
  private static JLabel l_LoggedInAs = new JLabel();
  private static JLabel l_LoggedInAsBox = new JLabel();
  
  //GUI globals - Log In Window
  public static JFrame logInWindow = new JFrame();
  public static JTextField tF_UserNameBox = new JTextField();
  private static JButton b_Enter = new JButton("Enter");
  private static JLabel l_EnterUserName = new JLabel("Enter username: ");
  private static JPanel p_LogIn = new JPanel();
  
  public static void main (String args[])
  {
    buildMainWindow();
    Initialize();
  }
  
  public static void connect()
  {
    try {
      final int port = 4445;
      final String host = "localhost";
      Socket socket = new Socket(host, port);
      System.out.println("You connected to: " + host);
      
      chatClient = new Client(socket);
      
      //Send name to add to online list
      PrintWriter out = new PrintWriter(socket.getOutputStream());
      out.println(UserName);
      out.flush();
    }
    catch (Exception e) 
    {
      System.out.print(e);
      JOptionPane.showMessageDialog(null, "Server not responding.");
      System.exit(0);
    }
  }
  
  public static void Initialize()
  {
    b_send.setEnabled(false);
    b_disconnect.setEnabled(false);
    b_connect.setEnabled(true);
  }
  
  public static void buildLogInWindow()
  {
    tF_UserNameBox.setColumns(10);
    logInWindow.setTitle("What is your name?");
    logInWindow.setSize(400, 100);
    logInWindow.setLocation(250, 200);
    logInWindow.setResizable(false);
    p_LogIn = new JPanel();
    p_LogIn.add(l_EnterUserName);
    p_LogIn.add(tF_UserNameBox);
    p_LogIn.add(b_Enter);
    logInWindow.add(p_LogIn);
    
    loginAction();
    logInWindow.setVisible(true);
  }
  
  public static void buildMainWindow()
  {
    mainWindow.setTitle(UserName + "'s Chat Box");
    mainWindow.setSize(450, 500);
    mainWindow.setLocation(220, 180);
    mainWindow.setResizable(false);
    configureMainWindow();
    mainWindowAction();
    mainWindow.setVisible(true);
  }
  
  public static void configureMainWindow()
  {
    mainWindow.setBackground(new Color(255, 255, 255));
    mainWindow.setSize(500, 320);
    mainWindow.getContentPane().setLayout(null);
    
    b_send.setBackground(new Color(0, 0, 255));
    b_send.setForeground(new Color(255, 255, 255));
    b_send.setText("Send");
    mainWindow.getContentPane().add(b_send);
    b_send.setBounds(250, 40, 81, 25);
    
    b_disconnect.setBackground(new Color(0, 0, 255));
    b_disconnect.setForeground(new Color(255, 255, 255));
    b_disconnect.setText("Disconnect");
    mainWindow.getContentPane().add(b_disconnect);
    b_disconnect.setBounds(10, 40, 110, 25);
    
    b_connect.setBackground(new Color(0, 0, 255));
    b_connect.setForeground(new Color(255, 255, 255));
    b_connect.setText("Connect");
    b_connect.setToolTipText("");
    mainWindow.getContentPane().add(b_connect);
    b_connect.setBounds(130, 40, 110, 25);
    
    b_help.setBackground(new Color(0, 0, 255));
    b_help.setForeground(new Color(255, 255, 255));
    b_help.setText("Help");
    mainWindow.getContentPane().add(b_help);
    b_help.setBounds(420, 40, 70, 25);
    
    b_about.setBackground(new Color(0, 0, 255));
    b_about.setForeground(new Color(255, 255, 255));
    b_about.setText("About");
    mainWindow.getContentPane().add(b_about);
    b_about.setBounds(340, 40, 70, 25);
    
    l_message.setText("Message: ");
    mainWindow.getContentPane().add(l_message);
    l_message.setBounds(10, 10, 60, 20);
    
    tF_Message.setForeground(new Color(0, 0, 0));
    tF_Message.requestFocus();
    mainWindow.getContentPane().add(tF_Message);
    tF_Message.setBounds(70, 4, 260, 30);
    
    l_Conversation.setHorizontalAlignment(SwingConstants.CENTER);
    l_Conversation.setText("Conversation");
    mainWindow.getContentPane().add(l_Conversation);
    l_Conversation.setBounds(100, 70, 140, 16);
    
    tA_Conversation.setColumns(20);
    tA_Conversation.setFont(new Font("Tahoma", 0, 12));
    tA_Conversation.setForeground(new Color(0, 0, 255));
    tA_Conversation.setLineWrap(true);
    tA_Conversation.setRows(5);
    tA_Conversation.setEditable(false);
    
    sP_Conversation.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    sP_Conversation.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    sP_Conversation.setViewportView(tA_Conversation);
    mainWindow.getContentPane().add(sP_Conversation);
    sP_Conversation.setBounds(10, 90, 330, 180);
    
    l_Online.setHorizontalAlignment(SwingConstants.CENTER);
    l_Online.setText("Currently Online");
    l_Online.setToolTipText("");
    mainWindow.getContentPane().add(l_Online);
    l_Online.setBounds(350, 70, 130, 16);
    
    //String [] testNames = {"Bob", "Sue", "Jenny", "Anna"};
    jL_Online.setForeground(new Color(0, 0, 0));
    //jL_Online.setListData(testNames);
    
    sP_Online.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    sP_Online.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    sP_Online.setViewportView(jL_Online);
    mainWindow.getContentPane().add(sP_Online);
    sP_Online.setBounds(350, 90, 130, 180);
    
    l_LoggedInAs.setFont(new Font("Tahoma", 0, 12));
    l_LoggedInAs.setText("Currently Logged In As");
    mainWindow.getContentPane().add(l_LoggedInAs);
    l_LoggedInAs.setBounds(348, 0, 140, 15);
    
    l_LoggedInAsBox.setHorizontalAlignment(SwingConstants.CENTER);
    l_LoggedInAsBox.setFont(new Font("Tahoma", 0, 12));
    l_LoggedInAsBox.setForeground(new Color(255, 0, 0));
    l_LoggedInAsBox.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
    mainWindow.getContentPane().add(l_LoggedInAsBox);
    l_LoggedInAsBox.setBounds(340, 17, 150, 20);
  }
  
  public static void loginAction()
  {
    b_Enter.addActionListener
    (
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent evt)
          {
            action_b_Enter();
          }
        }
        
    );
  }

  protected static void action_b_Enter()
  {
    if (!tF_UserNameBox.getText().equals(""))
    {
      UserName = tF_UserNameBox.getText().trim();
      l_LoggedInAsBox.setText(UserName);
      Server.CurrentUsers.add(UserName);
      mainWindow.setTitle(UserName + "'s Chat Box");
      //I chante the value of the below field to false
      logInWindow.setVisible(false);
      b_disconnect.setEnabled(true);
      b_connect.setEnabled(false);
      connect();
    }
    else {
      JOptionPane.showMessageDialog(null, "Please enter a name:");
    }
    
  }
  
  public static void mainWindowAction()
  {
    b_send.addActionListener(
        new ActionListener()
        {
          public void actionPerformed(ActionEvent evt)
          {
            action_b_Send();
          } 
        }
    );
    
    b_disconnect.addActionListener(
        new ActionListener()
        {
          public void actionPerformed(ActionEvent evt)
          {
            action_b_Disconnect();
          }
        }
    );
    
    b_connect.addActionListener(
        new ActionListener()
        {
          public void actionPerformed(ActionEvent evt)
          {
            buildLogInWindow();
          }
        }
    );
    
    b_help.addActionListener(
        new ActionListener()
        {
          public void actionPerformed(ActionEvent evt)
          {
            action_b_Help();
          }
        }
    );
    
    b_about.addActionListener(
        new ActionListener()
        {
          public void actionPerformed(ActionEvent evt)
          {
            action_b_Help();
          }
        }
    );
  }
  
  public static void action_b_Send()
  {
    if (!tF_Message.getText().equals(""))
    {
      chatClient.send(tF_Message.getText());
      tF_Message.requestFocus();
    }
  }
  
  public static void action_b_Disconnect()
  {
    try
    {
      chatClient.disconnect();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static void action_b_Help()
  {
    JOptionPane.showMessageDialog(null, "Multi-Client Chat Program");
  }
  
  

}
