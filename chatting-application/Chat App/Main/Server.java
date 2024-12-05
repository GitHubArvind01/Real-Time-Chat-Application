package Main;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class Server implements ActionListener {
    
    JTextField text;
    static JPanel a1;
        static Box vertical = Box.createVerticalBox();
        static JFrame f = new JFrame();
        static DataOutputStream dout;
        
        Server() {
            
            f.setLayout(null);
            
            JPanel p1 = new JPanel();
            p1.setBackground(new Color(23, 176, 0));
            p1.setBounds(0, 0, 450, 70);
            p1.setLayout(null);
            f.add(p1);
            
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/back.png"));
            Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel back = new JLabel(i3);
            back.setBounds(5, 20, 25, 25);
            p1.add(back);
            
            back.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent ae) {
                    System.exit(0);
                }
            });
            
            ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/akProfile.png"));
            Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            ImageIcon i6 = new ImageIcon(i5);
            JLabel profile = new JLabel(i6);
            profile.setBounds(40, 10, 50, 50);
            p1.add(profile);
            
             
            
            ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
            Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
            ImageIcon i15 = new ImageIcon(i14);
            JLabel morevert = new JLabel(i15);
            morevert.setBounds(420, 20, 10, 25);
            p1.add(morevert);
            
            JLabel name = new JLabel("Arvind Kumar");
            name.setBounds(110, 15, 150, 18);
            name.setForeground(Color.WHITE);
            name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
            p1.add(name);
            
            JLabel status = new JLabel("Online");
            status.setBounds(110, 35, 100, 18);
            status.setForeground(Color.WHITE);
            status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
            p1.add(status);
            
            a1 = new JPanel();
            a1.setBounds(5, 75, 440, 570);
            f.add(a1);
            
            //orignal text area button 
            text = new JTextField();
            text.setBounds(5, 655, 340, 40);
            text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
            f.add(text);
    
    
            //demo part 
    
    
            // Load the PNG image for the send icon
            ImageIcon sendIcon = new ImageIcon(ClassLoader.getSystemResource("icons/send icon.png"));
            Image sendImage = sendIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT); // You can adjust size
            sendIcon = new ImageIcon(sendImage);
            
            // Create the button and set the icon
            JButton send = new JButton(sendIcon);
            send.setBounds(320, 655, 140, 40); // Adjust the size to match your icon dimensions
            send.setBackground(new Color(255, 255, 255));
            send.setForeground(Color.WHITE);
            send.addActionListener(this);
            send.setText(""); // Remove text as it's replaced by the image
            send.setBorder(null); // Remove any border
            send.setFocusPainted(false); // Remove focus outline
            send.setFocusable(false); // Prevent the button from getting focus and showing a border
            
            // Prevent any content area changes on button click
            send.setContentAreaFilled(false); // Ensures the background doesn't change when clicked
            
            // Set the preferred size to prevent blinking size changes on click
            send.setPreferredSize(new Dimension(123, 40)); // Size of the button
            
            f.add(send);
            
            
            f.setSize(450, 700);
            f.setLocation(200, 50);
            f.setUndecorated(true);
            f.getContentPane().setBackground(Color.WHITE);
            
            f.setVisible(true);
        }
        
        public void actionPerformed(ActionEvent ae) {
            try {
                String out = text.getText();
        
                // Sent message (isSent = true)
                JPanel p2 = formatLabel(out, true);
        
                a1.setLayout(new BorderLayout());
        
                JPanel right = new JPanel(new BorderLayout());
                right.add(p2, BorderLayout.LINE_END);
                vertical.add(right);
                vertical.add(Box.createVerticalStrut(15));
        
                a1.add(vertical, BorderLayout.PAGE_START);
        
                dout.writeUTF(out);
        
                text.setText("");
        
                f.repaint();
                f.invalidate();
                f.validate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        public static JPanel formatLabel(String message, boolean isSent) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
            // Custom JLabel with rounded corners
            JLabel output = new JLabel("<html><p style=\"width: 150px\">" + message + "</p></html>") {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
                    // Set background color based on whether it's sent or received
                    g2.setColor(isSent ? new Color(37, 211, 102) : new Color(115, 115, 115)); // Green for sent, Gray for received
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        
                    super.paintComponent(g);
                }
        
                @Override
                protected void paintBorder(Graphics g) {
                    // No additional border
                }
        
                @Override
                public void setOpaque(boolean isOpaque) {
                    // Prevent default background clearing
                }
            };
        
            // Set label properties
            output.setFont(new Font("Tahoma", Font.BOLD, 16));
            output.setBorder(new EmptyBorder(15, 15, 15, 50)); // Padding inside the label
            output.setOpaque(false); // Custom painting handles the background
            output.setForeground(Color.WHITE);
            panel.add(output);
        
            // Add timestamp
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
            JLabel time = new JLabel();
            time.setText(sdf.format(cal.getTime()));
            time.setFont(new Font("Tahoma", Font.ITALIC, 12));
            time.setForeground(Color.GRAY);
        
            panel.add(time);
        
            return panel;
        }
        
        
        
        public static void main(String[] args) {
            new Server();
        
            try {
                ServerSocket skt = new ServerSocket(6001);
                while (true) {
                    Socket s = skt.accept();
                    DataInputStream din = new DataInputStream(s.getInputStream());
                    dout = new DataOutputStream(s.getOutputStream());
        
                    while (true) {
                        String msg = din.readUTF();
        
                        // Received message (isSent = false)
                        JPanel panel = formatLabel(msg, false);
        
                        JPanel left = new JPanel(new BorderLayout());
                        left.add(panel, BorderLayout.LINE_START);
                        vertical.add(left);
                        vertical.add(Box.createVerticalStrut(15));
        
                        a1.add(vertical, BorderLayout.PAGE_START);
                    f.validate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
