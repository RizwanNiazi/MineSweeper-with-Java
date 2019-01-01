/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.HEIGHT;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;

/**
 *
 * @author l164251
 */
public class grid extends javax.swing.JFrame {
String player;
int size;
Timer timer;
int count;
int r,c;
int no_of_mines;
JButton[][] b;
int [][]myarray;
final int mine=-5;
int smile=0;
final int happy=-20;
final int flag_variable=-30;
int flag_count=0;
int lifes=3;
int game_end=0;
int count_time=0;
int score=0;
    /**
     * Creates new form grid
     */
    public grid(String n,int ss) 
    {
       initComponents();
       size=ss;
       name.setText(n);
       no_of_mines=size+7;
       if(size==12)
           smile=0;
       else if(size==10)
               smile=1;
       else smile=2;
        player=n;
       flag_count=no_of_mines; 
       mines.setText(no_of_mines+"");
       power.setText(lifes+"");
     //  flag.setText(flag_count+"");
       initialize();
        UIManager.put("Button.disabledForeground", Color.red);
         UIManager.put("Button.disabledBackground", Color.red);
         UIManager.put("Button.disabledText", Color.PINK);
       // JPanel p=new JPanel();
            createmines();
    }
    
    public void initialize()
    {
        this.setSize(50*size+150,50*size+150);
     
         
       // this.setLayout(new GridLayout(10, 10));
         int x=50;
         int y=50; 
         r=size;
         c=size;
            
            b= new JButton[r][c];
            myarray= new int[r][c];
            for (int i = 1; i <= r; i++) {
            b[i-1]=new JButton[c];
            for(int j=1;j<=c;j++)
            {
                  b[i-1][j-1]= new JButton();
                  b[i-1][j-1].setBackground(new Color(41, 41, 61));
                  b[i-1][j-1].setForeground(Color.WHITE);
                    //button.setSize(50, 50);
                  b[i-1][j-1].setBounds(x, y, 40, 40);
                  x=x+37;
                  this.add(b[i-1][j-1]);
                  b[i-1][j-1].addActionListener(new java.awt.event.ActionListener() 
                  {
                    
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                     button_ActionPerformed(evt);
                    }
                     });
                  //p.add(button);
                  
            }
            y=y+37;
            x=50;
            
            
        }
            this.setBackground(Color.red);
             
            calltimer();
    }
//        JButton button = new JButton();
//         button.setBackground(Color.black);
//         // button.setBounds(50, 50);
//          button.setBounds(400, 250, 100, 100);
//           button.setText("");
//            this.add(button);
            public void calltimer()
            {
                    timer = new Timer(1000, new ActionListener() {
                      int p1;
                    int p2;
                    int te=10000;
                      @Override
                  public void actionPerformed(ActionEvent e) {
                      p1 = count_time % 60;
                      p2 = count_time / 60;
                      int cck=0;
                    count_time++;
                    if (count_time < 1000000) {
                        if(p1<10)
                        {
                             tm.setText("0"+Integer.toString(p2)+":"+"0"+Integer.toString(p1));
                        }
                        else
                        {
                            if(count_time>te)
                        {
                            if(lifes>1)
                            {
                                lifes--;
                                power.setText(lifes+"");
                            }
                        }
                        te=te+10000;
                            tm.setText("0"+Integer.toString(p2)+":"+Integer.toString(p1));
                        }
                        
                        if(count_time>te)
                        {
                            if(lifes>1)
                            {
                                lifes--;
                                power.setText(lifes+"");
                            }
                        }
                        te=te+10000;
                    } else {
                      ((Timer) (e.getSource())).stop();
                    }
                  }
                });
                timer.setInitialDelay(0);
                
            }
//            
            
            public void createmines()
            {
               
                for(int i=0;i<no_of_mines;i++)
                {
                    int r1=(int)(Math.random()*size);
                    int r2=(int)(Math.random()*size);
                    if(myarray[r1][r2]==mine)
                    {
                        i--;
                    }
                    else myarray[r1][r2]=mine;
                }
                for(int i=0;i<smile;i++)
                {
                    int r1=(int)(Math.random()*size);
                    int r2=(int)(Math.random()*size);
                    if(myarray[r1][r2]==mine)
                    {
                        i--;
                    }
                    else myarray[r1][r2]=happy;
                }
                
                for(int i=0;i<size;i++)
                {
                    for(int j=0;j<size;j++)
                    {
                        if(myarray[i][j]!=mine && myarray[i][j]!=happy)
                        {
                            int mines_around_me=0;
                            if(i>0 && j>0 && myarray[i-1][j-1]==mine)
                            {
                                mines_around_me++;
                                //Top left
                            }
                            if(j>0 && myarray[i][j-1]==mine)
                            {
                                mines_around_me++;
                                //Left
                            }
                            if(i>0 && myarray[i-1][j]==mine)
                            {
                                mines_around_me++;
                                //Up
                            }
                            if(i>0 && j<size-1 && myarray[i-1][j+1]==mine)
                            {
                                mines_around_me++;
                                //Top right
                            }
                            if(j<size-1 && myarray[i][j+1]==mine)
                            {
                                mines_around_me++;
                                //right
                            }
                            if(i<size-1 && j<size-1 && myarray[i+1][j+1]==mine)
                            {
                                mines_around_me++;
                                //Bottom right
                            }
                            if(i<size-1 && myarray[i+1][j]==mine)
                            {
                                mines_around_me++;
                                //Bottom
                            }
                            if(i<size-1 && j>0 && myarray[i+1][j-1]==mine)
                            {
                                mines_around_me++;
                                //Bottom left
                            }
                            myarray[i][j]=mines_around_me;
                        }
                    }
                }
            }
  //  timer.start();
    
    
 
    private void button_ActionPerformed(java.awt.event.ActionEvent evt) {                                           
       timer.start();
          UIManager.put("Button.disabledForeground", Color.red);
         UIManager.put("Button.disabledBackground", Color.red);
        // UIManager.put(Button.disabledForeground, Color.PINK);
        if(game_end==0)
        {
          for(int i=0;i<size;i++)
          {
                for(int j=0;j<size;j++)
                {    
                    if(evt.getSource().equals(b[i][j]))
                    {
                           
                           if(myarray[i][j]==mine)
                           {
                               no_of_mines--;
                               mines.setText(no_of_mines+"");
                              
                               
                             //  power.setText(lifes+"");
                               if(no_of_mines==0)
                               {
                                   b[i][j].setEnabled(false);
                                   // JOptionPane.showMessageDialog(rootPane, "Congrats!! You have successfully opened all boxes without mine", "Yahoooo!", HEIGHT);
                               }
                               if(lifes>1)
                               {
                                    lifes--;
                                power.setText(lifes+"");
                               b[i][j].setIcon(new javax.swing.ImageIcon("E:\\Semester 5\\OOAD Lab\\Lab_10\\Resized\\bomb_ic11.jpg"));
                                 b[i][j].setMargin(new Insets(0,0,0,0));
                                 b[i][j].setBorder(null);
                                 b[i][j].setEnabled(false);
                                 JOptionPane.showMessageDialog(rootPane, "Oooopss you clicked on a mine and lost one of your life", "One life lost!", HEIGHT);
                               }
                               else
                               {
                                   power.setText(0+"");
                                   calculatescore();
                                   lostgame();
                                   this.setVisible(false);
                                    new game_end(player,score).setVisible(true);
                               }
                                 
                           }
                            if(myarray[i][j]==happy)
                           {
                               smile--;
                               lifes++;
                               power.setText(lifes+"");
                               
                               b[i][j].setIcon(new javax.swing.ImageIcon("E:\\Semester 5\\OOAD Lab\\Lab_10\\Resized\\happy3.png"));
                               b[i][j].setEnabled(false);  
                               b[i][j].setMargin(new Insets(0,0,0,0));
                                 b[i][j].setBorder(null);
                                 b[i][j].setBackground(new Color(41, 41, 61));
                           }
                            if(myarray[i][j]==0)
                            {
                                    b[i][j].setText(myarray[i][j]+"");
                                    b[i][j].setEnabled(false);
                                    ArrayList<Integer> t=new ArrayList<>(); 
                                    t.add(i*(size*10)+j);
                                    open_zeros_from_neighbour(t);
                                    wingame();
                            }
                            else
                            {
                                b[i][j].setText(myarray[i][j]+"");
                                b[i][j].setEnabled(false);
                                 wingame();
                            }
                           
                          
                            
                            //flag.setText(flag_count+"");
                    }
                }
          }
        }
    }
  
    //tm = new JLabel("...");
    //setLayout(new GridBagLayout());
    //add(label);
  public void calculatescore()
  {
      
      score=score+(size*5);
      int time_bonus=600-count_time;
      score=score+time_bonus;
      
  }
    
   public void wingame()
   {
       int win=1;
       for(int i=0;i<size;i++)
       {
           for(int j=0;j<size;j++)
           {
               if(myarray[i][j]!=mine && b[i][j].isEnabled()==true)
               {
                   win=0;
               }
           }
       }
       if(win==1 && game_end==0)
       {
           calculatescore();
           game_end=1;
           JOptionPane.showMessageDialog(rootPane, "Congrats!! You have successfully opened all boxes without mine", "Yahoooo!", HEIGHT);
           this.setVisible(false);
           new game_end(player,score).setVisible(true);
       }
   }
    
// public void open_zeros_from_neighbour(int x,int y)
  public void open_zeros_from_neighbour(ArrayList<Integer> myList)       
 {
     if(myList.size()==0 )
     {
         //base case for recursion
         System.out.println("awdawd  :   ");
         return;
     }
     else
     {
         int factor=size*10;
         int i=myList.get(0)/factor;
         int j=myList.get(0)%factor;
         System.out.println("Count  :   ");
          System.out.println(myList.size());
         myList.remove(0);
         if(myarray[i][j]==0 )
         {
             if(i>0 && j>0 && myarray[i-1][j-1]!=happy &&b[i-1][j-1].isEnabled())
             {      
                 //Top left
                 b[i-1][j-1].setText(myarray[i-1][j-1]+"");
                 b[i-1][j-1].setEnabled(false);
                 if(myarray[i-1][j-1]==0)
                 {
                     myList.add((i-1)*factor+(j-1));
                 }
             }            
            if(j>0 && myarray[i][j-1]!=happy &&b[i][j-1].isEnabled())
            {
                 //Left
               b[i][j-1].setText(myarray[i][j-1]+"");
                b[i][j-1].setEnabled(false);
               if(myarray[i][j-1]==0)
                 {
                     myList.add((i)*factor+(j-1));
                 }
               
            }
            if(i>0 && myarray[i-1][j]!=happy &&b[i-1][j].isEnabled())
            {
                 //Up
                b[i-1][j].setText(myarray[i-1][j]+"");
                 b[i-1][j].setEnabled(false);
                if(myarray[i-1][j]==0)
                 {
                     myList.add((i-1)*factor+(j));
                 }
            }
            if(i>0 && j<size-1 && myarray[i-1][j+1]!=happy &&b[i-1][j+1].isEnabled())
            {
                 //Top right
               b[i-1][j+1].setText(myarray[i-1][j+1]+"");
                b[i-1][j+1].setEnabled(false);
               if(myarray[i-1][j+1]==0)
                 {
                     myList.add((i-1)*factor+(j+1));
                 }
               
            }
            if(j<size-1 && myarray[i][j+1]!=happy &&b[i][j+1].isEnabled())
            {
                  //right
                b[i][j+1].setText(myarray[i][j+1]+"");
                 b[i][j+1].setEnabled(false);
                if(myarray[i][j+1]==0)
                 {
                     myList.add((i)*factor+(j+1));
                 }
              
            }
            if(i<size-1 && j<size-1 && myarray[i+1][j+1]!=happy &&b[i+1][j+1].isEnabled())
            {
                //Bottom right
                b[i+1][j+1].setText(myarray[i+1][j+1]+"");
                 b[i+1][j+1].setEnabled(false);
                if(myarray[i+1][j+1]==0)
                 {
                     myList.add((i+1)*factor+(j+1));
                 }
                
            }
            if(i<size-1 && myarray[i+1][j]!=happy &&b[i+1][j].isEnabled())
            {
                 //Bottom
                b[i+1][j].setText(myarray[i+1][j]+"");
                 b[i+1][j].setEnabled(false);
                if(myarray[i+1][j]==0)
                 {
                     myList.add((i+1)*factor+(j));
                 }
               
            }
            if(i<size-1 && j>0 && myarray[i+1][j-1]!=happy &&b[i+1][j-1].isEnabled())
            {
                 //Bottom left
               b[i+1][j-1].setText(myarray[i+1][j-1]+"");
                b[i+1][j-1].setEnabled(false);
               if(myarray[i+1][j-1]==0)
                 {
                    myList.add((i+1)*factor+(j-1));
                 }
               
            }
            
           //return;
         }
        open_zeros_from_neighbour(myList); 
     }
 }
    
  public void lostgame()
  {
         for(int i=0;i<size;i++)
          {
                for(int j=0;j<size;j++)
                {    
                    if(myarray[i][j]==happy)
                    {
                                 b[i][j].setIcon(new javax.swing.ImageIcon("E:\\Semester 5\\OOAD Lab\\Lab_10\\Resized\\happy3.png"));
                                 b[i][j].setMargin(new Insets(0,0,0,4));
                                 b[i][j].setBorder(null);
                                 b[i][j].setEnabled(false);
                    }
                    if(myarray[i][j]==mine)
                    {
                                 b[i][j].setIcon(new javax.swing.ImageIcon("E:\\Semester 5\\OOAD Lab\\Lab_10\\Resized\\bomb_ic11.jpg"));
                                 b[i][j].setMargin(new Insets(0,0,0,4));
                                 b[i][j].setBorder(null);
                                 b[i][j].setEnabled(false);
                    }
                    
                    else 
                    {
                        b[i][j].setText(myarray[i][j]+"");
                        b[i][j].setEnabled(false);
                    }
                   // b[i][j].setEnabled(false);
                }
                
          }
         game_end=1;
        JOptionPane.showMessageDialog(rootPane, "Oooopss you clicked on a mine and you have no life remaining", "Game Over!", HEIGHT);
  }
  //@Override
                public Dimension getPreferredSize()
                {
                  return new Dimension(200, 200);
                }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        power = new javax.swing.JTextField();
        mines = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tm = new javax.swing.JTextField();
        pause_time = new javax.swing.JButton();
        start_time = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        jLabel2.setText("Score : ");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 51, 51));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setIcon(new javax.swing.ImageIcon("E:\\Semester 5\\OOAD Lab\\Lab_10\\Resized\\life1.png")); // NOI18N

        power.setText("00");
        power.setFocusable(false);

        mines.setText("00");
        mines.setFocusable(false);

        jLabel3.setIcon(new javax.swing.ImageIcon("E:\\Semester 5\\OOAD Lab\\Lab_10\\Resized\\bomb1.jpg")); // NOI18N

        jLabel5.setBackground(new java.awt.Color(204, 204, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon("E:\\Semester 5\\OOAD Lab\\Lab_10\\Resized\\timee1.jpg")); // NOI18N

        tm.setText("00:00");
        tm.setFocusable(false);

        pause_time.setText("Pause");
        pause_time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pause_timeActionPerformed(evt);
            }
        });

        start_time.setText("Start");
        start_time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start_timeActionPerformed(evt);
            }
        });

        jLabel6.setText("Playing as : ");

        name.setFocusable(false);

        jLabel7.setFont(new java.awt.Font("Script MT Bold", 0, 18)); // NOI18N
        jLabel7.setText("Developed by S.Saqib");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(498, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(tm, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pause_time, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(start_time, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(power, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mines, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(start_time)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pause_time)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tm, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(power, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mines, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void start_timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start_timeActionPerformed
        // TODO add your handling code here:
            timer.start();
    }//GEN-LAST:event_start_timeActionPerformed

    private void pause_timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pause_timeActionPerformed
        // TODO add your handling code here:
        timer.stop();
        
    }//GEN-LAST:event_pause_timeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(grid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(grid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(grid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(grid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new grid("saqib",10).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField mines;
    private javax.swing.JTextField name;
    private javax.swing.JButton pause_time;
    private javax.swing.JTextField power;
    private javax.swing.JButton start_time;
    private javax.swing.JTextField tm;
    // End of variables declaration//GEN-END:variables
}
