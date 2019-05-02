package ioma.gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class iomaGUI extends JFrame
{

  private JPanel contentPane;

  /**
   * Launch the application.
   */
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          iomaGUI frame = new iomaGUI();
          frame.setVisible(true);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public iomaGUI()
  {
    setTitle("Inter Office Messaging Application v.1");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 747, 751);
    contentPane = new JPanel();
    contentPane.setAutoscrolls(true);
    contentPane.setFont(new Font("Calibri", Font.PLAIN, 17));
    contentPane.setBackground(new Color(0, 139, 139));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JSeparator separator = new JSeparator();
    separator.setBackground(new Color(255, 255, 255));
    separator.setOrientation(SwingConstants.VERTICAL);
    separator.setForeground(new Color(255, 255, 255));
    separator.setBounds(224, 67, 25, 624);
    contentPane.add(separator);
    
    JLabel lblPeers = new JLabel("Peers");
    lblPeers.setForeground(new Color(255, 255, 255));
    lblPeers.setHorizontalAlignment(SwingConstants.CENTER);
    lblPeers.setFont(new Font("Calibri", Font.BOLD, 27));
    lblPeers.setBounds(12, 13, 200, 34);
    contentPane.add(lblPeers);
    
    JLabel lblMessageList = new JLabel("Message List");
    lblMessageList.setForeground(new Color(255, 255, 255));
    lblMessageList.setHorizontalAlignment(SwingConstants.CENTER);
    lblMessageList.setFont(new Font("Calibri", Font.BOLD, 27));
    lblMessageList.setBounds(235, 13, 482, 34);
    contentPane.add(lblMessageList);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(235, 559, 482, -497);
    contentPane.add(scrollPane);
    
    JSeparator separator_1 = new JSeparator();
    separator_1.setForeground(new Color(255, 255, 255));
    separator_1.setBounds(12, 52, 705, 10);
    contentPane.add(separator_1);
    
    JButton btnSend = new JButton("Send");
    btnSend.setBackground(new Color(51, 255, 102));
    btnSend.setBounds(645, 572, 72, 119);
    contentPane.add(btnSend);
    
    JSeparator separator_2 = new JSeparator();
    separator_2.setForeground(new Color(255, 255, 255));
    separator_2.setBounds(235, 555, 482, 2);
    contentPane.add(separator_2);
    
    JTextArea messageTextArea = new JTextArea();
    messageTextArea.setBorder(new LineBorder(new Color(0, 0, 0)));
    messageTextArea.setBounds(235, 572, 398, 119);
    messageTextArea.setLineWrap(true);
    messageTextArea.setWrapStyleWord(true);
    contentPane.add(messageTextArea);
    
    JTextPane chatAreaTextPane = new JTextPane();
    chatAreaTextPane.setBackground(new Color(176, 224, 230));
    chatAreaTextPane.setBorder(new LineBorder(null));
    chatAreaTextPane.setBounds(235, 67, 482, 475);
    contentPane.add(chatAreaTextPane);
    
    JTextPane peersTextPane = new JTextPane();
    peersTextPane.setBorder(new LineBorder(new Color(0, 0, 0)));
    peersTextPane.setBounds(12, 67, 200, 475);
    contentPane.add(peersTextPane);
    
    JButton btnDownload = new JButton("Download all messages");
    btnDownload.setBackground(new Color(255, 204, 51));
    btnDownload.setBounds(12, 657, 200, 34);
    contentPane.add(btnDownload);
    
    JSeparator separator_3 = new JSeparator();
    separator_3.setForeground(new Color(255, 255, 255));
    separator_3.setBounds(12, 555, 200, 2);
    contentPane.add(separator_3);
    
    JButton btnStartConnection = new JButton("Disconnect");
    btnStartConnection.setBackground(Color.RED);
    btnStartConnection.setBounds(12, 614, 200, 34);
    contentPane.add(btnStartConnection);
    
    JSeparator separator_4 = new JSeparator();
    separator_4.setForeground(new Color(255, 255, 255));
    separator_4.setOrientation(SwingConstants.VERTICAL);
    separator_4.setBounds(224, 13, 2, 34);
    contentPane.add(separator_4);
    
    JButton button = new JButton("Start Connection");
    button.setBackground(Color.GREEN);
    button.setBounds(12, 572, 200, 34);
    contentPane.add(button);
  }
}
