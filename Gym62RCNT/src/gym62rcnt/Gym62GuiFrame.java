/*
    This Code Created By 62nd Gymnasium of Athens
    
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

    This Code Created By 62nd Gymnasium of Athens 
    2018 
 */
package gym62rcnt;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


/**
 *
 * @author vlastos
 */
public class Gym62GuiFrame extends javax.swing.JFrame{
    
    public static javax.swing.JSlider jSliderSpeedL;
    public static javax.swing.JTextField jTextSpeedL;
    public static javax.swing.JSlider jSliderSpeedR;
    public static javax.swing.JTextField jTextSpeedR;
    
    public static javax.swing.JSlider jSliderDistanceF;
    public static javax.swing.JTextField jTextDistanceF;    
    public static javax.swing.JSlider jSliderDistanceB;
    public static javax.swing.JTextField jTextDistanceB;
    
    public static javax.swing.JSlider jSliderAbLight;
    public static javax.swing.JTextField jTextAbLight; 
    public static javax.swing.JSlider jSliderTrHeat;
    public static javax.swing.JTextField jTextTrHeat; 
    public static javax.swing.JSlider jSliderAbTemp;
    public static javax.swing.JTextField jTextAbTemp;
    public static javax.swing.JSlider jSliderSetSpeed;
    public static javax.swing.JTextField jTextSetSpeed;
    public static javax.swing.JTextField jTextGetSpeed;
    
    public static javax.swing.JSlider jSliderSetStopTime;
    public static javax.swing.JTextField jTextSetStopTime;
    public static javax.swing.JTextField jTextGetStopTime;
    
    public static javax.swing.JSlider jSliderSetMoveTime;
    public static javax.swing.JTextField jTextSetMoveTime;
    public static javax.swing.JTextField jTextGetMoveTime;
    
    public static javax.swing.JSlider jSliderSetFrontObject;
    public static javax.swing.JTextField jTextSetFrontObject;
    
    public static javax.swing.JSlider jSliderSetBackObject;
    public static javax.swing.JTextField jTextSetBackObject;
    
    public static javax.swing.JSlider jSliderSetTrig;
    
    public static javax.swing.JTextField jTextSetTrig; 
    public static javax.swing.JTextField jTextGetTrig; 
     
    public static javax.swing.JButton jButtonConnect;
    public static javax.swing.JButton jButtonDissConnect;
    public static javax.swing.JButton jButtonStart;
    public static javax.swing.JButton jButtonStop;
    
    public static javax.swing.JButton jButtonForward;
    public static javax.swing.JButton jButtonBackward;
    public static javax.swing.JButton jButtonLeft;
    public static javax.swing.JButton jButtonRight;
    
    public javax.swing.JPanel jPanelThermalGrid;    
    public static javax.swing.JTextField jTextFieldIp;
    public static javax.swing.JTextField jTextFieldIpPort;
    
    public javax.swing.JPanel jPanelVideoCamera;
    public static javax.swing.JTextField jTextFieldCamIp;
    public static javax.swing.JTextField jTextFieldCamPort;
    
    public static javax.swing.JRadioButton jCheckRGB;
    public static javax.swing.JRadioButton jCheckTextOn;
    
    
    public javax.swing.JPanel jPanelThermalHorizon;
    public javax.swing.JPanel jPanelHeatPointer;
    private javax.swing.JLabel jLabelGym;
     
    public javax.swing.JPanel jPanelThermalPointer;
       
    public static javax.swing.JPanel contentPanel;   
    
    public static GridDraw gDraw;
    public static HorizonDraw hDraw;
    public static MovePointerDraw pDraw;
    
   
    static boolean StartFlag=false;
       
    public Gym62GuiFrame() {
        
    Gym62InitFrame();
    
}
    @SuppressWarnings("unchecked")
    
   private void Gym62InitFrame () {
       
       
             
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gym62 Robot Controll");
        setPreferredSize(new java.awt.Dimension(800, 650));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 650));
        
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);	
        contentPanel.setLayout(null);
       
        // Create Objets
        jSliderSpeedL = new javax.swing.JSlider();
        jTextSpeedL = new javax.swing.JTextField();
        jSliderSpeedR = new javax.swing.JSlider();
        jTextSpeedR = new javax.swing.JTextField();        
        jSliderAbTemp = new javax.swing.JSlider();
        jTextAbTemp = new javax.swing.JTextField();        
        jSliderTrHeat = new javax.swing.JSlider();
        jTextTrHeat = new javax.swing.JTextField();        
        jSliderAbLight = new javax.swing.JSlider();
        jTextAbLight = new javax.swing.JTextField();        
        jSliderDistanceB = new javax.swing.JSlider();
        jTextDistanceB = new javax.swing.JTextField();        
        jSliderDistanceF = new javax.swing.JSlider();
        jTextDistanceF = new javax.swing.JTextField();        
        jSliderSetStopTime = new javax.swing.JSlider();
        jTextSetStopTime = new javax.swing.JTextField(); 
        jTextGetStopTime = new javax.swing.JTextField(); 
        
        jSliderSetMoveTime = new javax.swing.JSlider();
        jTextSetMoveTime = new javax.swing.JTextField(); 
        jTextGetMoveTime = new javax.swing.JTextField(); 
        
        jSliderSetFrontObject= new javax.swing.JSlider();
        jTextSetFrontObject = new javax.swing.JTextField(); 
        
        jSliderSetBackObject= new javax.swing.JSlider();
        jTextSetBackObject = new javax.swing.JTextField(); 
        
                
        jSliderSetTrig = new javax.swing.JSlider();
        jTextSetTrig = new javax.swing.JTextField();
        jTextGetTrig = new javax.swing.JTextField();
        jSliderSetSpeed = new javax.swing.JSlider();
        jTextSetSpeed = new javax.swing.JTextField();
        jTextGetSpeed = new javax.swing.JTextField();        
        jButtonConnect = new javax.swing.JButton();
        jButtonDissConnect = new javax.swing.JButton();
        jButtonStart = new javax.swing.JButton();
        jButtonStop = new javax.swing.JButton();
        
        jButtonForward= new javax.swing.JButton();
        jButtonBackward =  new javax.swing.JButton();
        jButtonLeft =  new javax.swing.JButton();
        jButtonRight =  new javax.swing.JButton();
        
        
        jTextFieldIp = new javax.swing.JTextField();
        jTextFieldIpPort = new javax.swing.JTextField();
        
        jPanelThermalGrid = new javax.swing.JPanel();  
        
        jPanelThermalHorizon = new javax.swing.JPanel(); 
        jPanelThermalPointer = new javax.swing.JPanel(); 
        
        jPanelHeatPointer = new javax.swing.JPanel();        
        jLabelGym = new javax.swing.JLabel();
        
        jPanelVideoCamera = new javax.swing.JPanel();  
        jTextFieldCamIp=new javax.swing.JTextField();
        jTextFieldCamPort=new javax.swing.JTextField();
        
        jCheckRGB =new javax.swing.JRadioButton();
        jCheckTextOn= new javax.swing.JRadioButton();
        
        // Initialise Objetcs
        // Left Speed Slider
        jSliderSpeedL.setMajorTickSpacing(50);       
        jSliderSpeedL.setMaximum(255);
        jSliderSpeedL.setMinimum(-255);
        jSliderSpeedL.setOrientation(javax.swing.JSlider.VERTICAL);
        jSliderSpeedL.setBounds(5, 5, 100, 220);
        jSliderSpeedL.setPaintTicks(true);
        jSliderSpeedL.setPaintTrack(false);
        jSliderSpeedL.setToolTipText("Ταχύτητα Αριστερής Ερπύστριας");
        jSliderSpeedL.setValue(0);
        jSliderSpeedL.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Speed Left", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 2, 12))); // NOI18N
        jSliderSpeedL.setRequestFocusEnabled(false);
        jTextSpeedL.setText(String.valueOf(jSliderSpeedL.getValue()));
        jTextSpeedL.setBounds(7,20, 30, 18);
        jSliderSpeedL.add(jTextSpeedL); 
        contentPanel.add(jSliderSpeedL);        
        
        
        
        // Right Speed Slider
        jSliderSpeedR.setMajorTickSpacing(50);
        jSliderSpeedR.setMaximum(255);
        jSliderSpeedR.setMinimum(-255);
        jSliderSpeedR.setOrientation(javax.swing.JSlider.VERTICAL);
        jSliderSpeedR.setBounds(105, 5, 100, 220);
        jSliderSpeedR.setPaintTicks(true);
        jSliderSpeedR.setPaintTrack(false);
        jSliderSpeedR.setToolTipText("Ταχύτητα Δεξιάς Ερπύστριας");
        jSliderSpeedR.setValue(0);
        jSliderSpeedR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Speed Right", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 2, 12))); // NOI18N
        jSliderSpeedR.setRequestFocusEnabled(false);
        jTextSpeedR.setText(String.valueOf(jSliderSpeedR.getValue())); // Read the current value. Convert it to string and put it on container
        jTextSpeedR.setBounds(7,20, 30, 18);
        jSliderSpeedR.add(jTextSpeedR);
        contentPanel.add(jSliderSpeedR);
        
         
        // Distance Front Slider
        jSliderDistanceF.setMaximum(200);
        jSliderDistanceF.setMinorTickSpacing(10);
        jSliderDistanceF.setOrientation(javax.swing.JSlider.HORIZONTAL);
        jSliderDistanceF.setBounds(5, 230, 200, 50);
        jSliderDistanceF.setPaintTicks(true);
        jSliderDistanceF.setToolTipText("Απόσταση Αντικειμένου Μπροστά");
        jSliderDistanceF.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Απόσταση Μπροστά", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 2, 12))); // NOI18N
        jSliderDistanceF.setRequestFocusEnabled(false);
        jTextDistanceF.setText(String.valueOf(jSliderDistanceF.getValue()));
        jTextDistanceF.setBounds(168,0, 30, 18);
        jSliderDistanceF.add(jTextDistanceF);
        contentPanel.add(jSliderDistanceF);

        // Distance Back Slider
        jSliderDistanceB.setMaximum(200);
        jSliderDistanceB.setMinorTickSpacing(10);
        jSliderDistanceB.setOrientation(javax.swing.JSlider.HORIZONTAL);
        jSliderDistanceB.setBounds(5, 285, 200, 50);
        jSliderDistanceB.setPaintTicks(true);
        jSliderDistanceB.setToolTipText("Απόσταση Αντικειμένου πίσω");
        jSliderDistanceB.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Απόσταση Πίσω", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 2, 12))); // NOI18N
        jSliderDistanceB.setRequestFocusEnabled(false);
        jTextDistanceB.setText(String.valueOf(jSliderDistanceB.getValue()));
        jTextDistanceB.setBounds(168,0, 30, 18);
        jSliderDistanceB.add(jTextDistanceB);
         contentPanel.add(jSliderDistanceB);
        
        // Ambient Light Slider 
        jSliderAbLight.setMaximum(255);
        jSliderAbLight.setMinorTickSpacing(10);
        jSliderAbLight.setOrientation(javax.swing.JSlider.HORIZONTAL);
        jSliderAbLight.setBounds(5, 340, 200, 50);
        jSliderAbLight.setPaintTicks(true);
        jSliderAbLight.setPaintTrack(false);
        jSliderAbLight.setToolTipText("Φωτεινότητα Περιβάλλοντος");
        jSliderAbLight.setValue(70);
        jSliderAbLight.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ένταση Φωτός Περ. Lux", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 2, 12))); // NOI18N
        jSliderAbLight.setRequestFocusEnabled(false);
        jTextAbLight.setText(String.valueOf(jSliderAbLight.getValue()));
        jTextAbLight.setBounds(168,0, 30, 18);
        jSliderAbLight.add(jTextAbLight);
        contentPanel.add(jSliderAbLight);
        
        // Transmited Heat Slider
        jSliderTrHeat.setMaximum(100);
        jSliderTrHeat.setMinimum(-20);
        jSliderTrHeat.setMinorTickSpacing(10);
        jSliderTrHeat.setOrientation(javax.swing.JSlider.HORIZONTAL);
        jSliderTrHeat.setBounds(5, 395, 200, 50);
        jSliderTrHeat.setPaintTicks(true);
        jSliderTrHeat.setPaintTrack(false);
        jSliderTrHeat.setToolTipText("Θερμότητα Αισθητήρα");
        jSliderTrHeat.setValue(25);
        jSliderTrHeat.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Θερ. Αισθητήρα C", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 2, 12))); // NOI18N
        jSliderTrHeat.setRequestFocusEnabled(false);
        jTextTrHeat.setText(String.valueOf(jSliderTrHeat.getValue()));
        jTextTrHeat.setBounds(164,0, 34, 18);
        jSliderTrHeat.add(jTextTrHeat);
        contentPanel.add(jSliderTrHeat);
        
        // Ambient Heat Slider
        jSliderAbTemp.setMaximum(60);
        jSliderAbTemp.setMinimum(-20);
        jSliderAbTemp.setMinorTickSpacing(10);
        jSliderAbTemp.setOrientation(javax.swing.JSlider.HORIZONTAL);
        jSliderAbTemp.setBounds(5, 450, 200, 50);
        jSliderAbTemp.setPaintTicks(true);
        jSliderAbTemp.setPaintTrack(false);
        jSliderAbTemp.setToolTipText("Θερμοκρασία Περιβάλλοντος");
        jSliderAbTemp.setValue(25);
        jSliderAbTemp.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Θερ. Περιβάλλοντος C", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 2, 12))); // NOI18N
        jSliderAbTemp.setRequestFocusEnabled(false);
        jTextAbTemp.setText(String.valueOf(jSliderAbTemp.getValue()));
        jTextAbTemp.setBounds(164,0, 34, 18);
        jSliderAbTemp.add(jTextAbTemp);
        contentPanel.add(jSliderAbTemp);
       
        // General Speed Slider
        jSliderSetSpeed.setMajorTickSpacing(10);
        jSliderSetSpeed.setOrientation(javax.swing.JSlider.HORIZONTAL);
        jSliderSetSpeed.setBounds(220, 450, 270, 50);
        jSliderSetSpeed.setMaximum(254);
        jSliderSetSpeed.setMinimum(100);
        jSliderSetSpeed.setMinorTickSpacing(5);
        jSliderSetSpeed.setPaintTicks(true);
        jSliderSetSpeed.setToolTipText("Θέσε Μέγιστη ταχύτητα");
        jSliderSetSpeed.setValue(250);
        jSliderSetSpeed.setBorder(javax.swing.BorderFactory.createTitledBorder(null,"Ταχύτητα Πλοήγησης", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 2, 12))); // NOI18N
        jSliderSetSpeed.setFocusable(false);
        jTextSetSpeed.setText(String.valueOf(jSliderSetSpeed.getValue()));
        jTextSetSpeed.setBounds(168,0, 30, 18);
        jSliderSetSpeed.add(jTextSetSpeed);
        contentPanel.add(jSliderSetSpeed);
                
        
        
        jSliderSetSpeed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int data = jSliderSetSpeed.getValue();
                                jTextSetSpeed.setText(String.valueOf(data));			
			}
                        }); // Speed Slider Listener        
        jSliderSetSpeed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
               // Send data packet
                // Send data packet              
               try {                   
                   // Send String with Command plus data from slider               
                   Gym62RCNT.SendString("*Sp-"+jTextSetSpeed.getText()+"\n");                   
               }
               catch (IOException ex)
  		    {
  		      Logger.getLogger(Gym62RCNT.class.getName()).log(Level.SEVERE, null, ex);
  		    } 
            }
        });
        
        
        
        
        // Stop Time For Readings
        jSliderSetStopTime.setMajorTickSpacing(100);
        jSliderSetStopTime.setOrientation(javax.swing.JSlider.HORIZONTAL);
        jSliderSetStopTime.setBounds(500, 450, 270, 50);
        jSliderSetStopTime.setMaximum(1000);
        jSliderSetStopTime.setMinimum(1);
        jSliderSetStopTime.setMinorTickSpacing(100);
        jSliderSetStopTime.setPaintTicks(true);        
        jSliderSetStopTime.setToolTipText("Θέσε Χρόνο Αναμονής Ανίχνευσης");
        jSliderSetStopTime.setBorder(javax.swing.BorderFactory.createTitledBorder(null," Χρόνος Ανίχνευσης (Stop)", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 2, 12))); // NOI18N
        jSliderSetStopTime.setFocusable(false);
        jSliderSetStopTime.setValue(500);
        jTextSetStopTime.setText(String.valueOf(jSliderSetStopTime.getValue()));
        jTextSetStopTime.setBounds(178,0, 35, 18);
        jSliderSetStopTime.add(jTextSetStopTime);
        contentPanel.add(jSliderSetStopTime);
               
        
        
        jSliderSetStopTime.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int data = jSliderSetStopTime.getValue();
                                jTextSetStopTime.setText(String.valueOf(data));			
			}
                        }); // Speed Slider Listener        
        jSliderSetStopTime.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
               // Send data packet
                // Send data packet              
               try {                   
                   // Send String with Command plus data from slider               
                   Gym62RCNT.SendString("*Ts-"+jTextSetStopTime.getText()+"\n");                   
               }
               catch (IOException ex)
  		    {
  		      Logger.getLogger(Gym62RCNT.class.getName()).log(Level.SEVERE, null, ex);
  		    } 
            }
        });
        
        //**************************************************
        // Move Time For Readings
        jSliderSetMoveTime.setMajorTickSpacing(100);
        jSliderSetMoveTime.setOrientation(javax.swing.JSlider.HORIZONTAL);
        jSliderSetMoveTime.setBounds(570, 380, 210, 50);
        jSliderSetMoveTime.setMaximum(1000);
        jSliderSetMoveTime.setMinimum(1);
        jSliderSetMoveTime.setMinorTickSpacing(200);
        jSliderSetMoveTime.setPaintTicks(true);        
        jSliderSetMoveTime.setToolTipText("Θέσε Χρόνο Κίνησης Περιστροφής Ανίχνευσης");
        jSliderSetMoveTime.setBorder(javax.swing.BorderFactory.createTitledBorder(null," Χρόνος Ανίχνευσης (Rotate)", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 2, 12))); // NOI18N
        jSliderSetMoveTime.setFocusable(false);
        jSliderSetMoveTime.setValue(100);
        jTextSetMoveTime.setText(String.valueOf(jSliderSetMoveTime.getValue()));
        jTextSetMoveTime.setBounds(170,0, 35, 18);
        jSliderSetMoveTime.add(jTextSetMoveTime);
        contentPanel.add(jSliderSetMoveTime);
               
        
        
        jSliderSetMoveTime.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int data = jSliderSetMoveTime.getValue();
                                jTextSetMoveTime.setText(String.valueOf(data));			
			}
                        }); // Speed Slider Listener        
        jSliderSetMoveTime.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
               // Send data packet
                // Send data packet              
               try {                   
                   // Send String with Command plus data from slider               
                   Gym62RCNT.SendString("*Tm-"+jTextSetMoveTime.getText()+"\n");                   
               }
               catch (IOException ex)
  		    {
  		      Logger.getLogger(Gym62RCNT.class.getName()).log(Level.SEVERE, null, ex);
  		    } 
            }
        });
        //*************************************************
        
        
        //Set Front Object limit distance 
        jSliderSetFrontObject.setMajorTickSpacing(10);
        jSliderSetFrontObject.setOrientation(javax.swing.JSlider.HORIZONTAL);
        jSliderSetFrontObject.setBounds(570, 260, 210, 50);
        jSliderSetFrontObject.setMaximum(100);
        jSliderSetFrontObject.setMinimum(10);
        jSliderSetFrontObject.setMinorTickSpacing(5);
        jSliderSetFrontObject.setPaintTicks(true);        
        jSliderSetFrontObject.setToolTipText("Θέσε απόσταση ανίχνευσης αντικειμένου Μπροστά");
        jSliderSetFrontObject.setBorder(javax.swing.BorderFactory.createTitledBorder(null," Αντικείμενο Μπροστά", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 2, 12))); // NOI18N
        jSliderSetFrontObject.setFocusable(false);
        jSliderSetFrontObject.setValue(50);
        jTextSetFrontObject.setText(String.valueOf(jSliderSetFrontObject.getValue()));
        jTextSetFrontObject.setBounds(170,0, 35, 18);
        jSliderSetFrontObject.add(jTextSetFrontObject);
        contentPanel.add(jSliderSetFrontObject);
               
        
        
        jSliderSetFrontObject.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int data = jSliderSetFrontObject.getValue();
                                jTextSetFrontObject.setText(String.valueOf(data));			
			}
                        }); // Speed Slider Listener        
        jSliderSetFrontObject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
               // Send data packet
                // Send data packet              
               try {                   
                   // Send String with Command plus data from slider               
                   Gym62RCNT.SendString("*Df-"+jTextSetFrontObject.getText()+"\n");                   
               }
               catch (IOException ex)
  		    {
  		      Logger.getLogger(Gym62RCNT.class.getName()).log(Level.SEVERE, null, ex);
  		    } 
            }
        });
                
          
       //Set Back Object limit distance 
        jSliderSetBackObject.setMajorTickSpacing(10);
        jSliderSetBackObject.setOrientation(javax.swing.JSlider.HORIZONTAL);
        jSliderSetBackObject.setBounds(570, 315, 210, 50);
        jSliderSetBackObject.setMaximum(100);
        jSliderSetBackObject.setMinimum(10);
        jSliderSetBackObject.setMinorTickSpacing(5);
        jSliderSetBackObject.setPaintTicks(true);        
        jSliderSetBackObject.setToolTipText("Θέσε απόσταση ανίχνευσης αντικειμένου Πίσω");
        jSliderSetBackObject.setBorder(javax.swing.BorderFactory.createTitledBorder(null," Αντικείμενο Πίσω", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 2, 12))); // NOI18N
        jSliderSetBackObject.setFocusable(false);
        jSliderSetBackObject.setValue(50);
        jTextSetBackObject.setText(String.valueOf(jSliderSetBackObject.getValue()));
        jTextSetBackObject.setBounds(170,0, 35, 18);
        jSliderSetBackObject.add(jTextSetBackObject);
        contentPanel.add(jSliderSetBackObject);
               
        
        
        jSliderSetBackObject.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int data = jSliderSetBackObject.getValue();
                                jTextSetBackObject.setText(String.valueOf(data));			
			}
                        }); // Speed Slider Listener        
        jSliderSetBackObject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
               // Send data packet
                // Send data packet              
               try {                   
                   // Send String with Command plus data from slider               
                   Gym62RCNT.SendString("*Db-"+jTextSetBackObject.getText()+"\n");                   
               }
               catch (IOException ex)
  		    {
  		      Logger.getLogger(Gym62RCNT.class.getName()).log(Level.SEVERE, null, ex);
  		    } 
            }
        }); 
        
        
                
        // Thermal Triger Slider
        jSliderSetTrig.setMajorTickSpacing(10);
        jSliderSetTrig.setOrientation(javax.swing.JSlider.HORIZONTAL);
        jSliderSetTrig.setBounds(5, 505, 630, 50);
        jSliderSetTrig.setMaximum(127);
        jSliderSetTrig.setMinimum(10);
        jSliderSetTrig.setMinorTickSpacing(5);
        jSliderSetTrig.setPaintTicks(true);
        jSliderSetTrig.setToolTipText("Θέσε Αναζήτηση Θερμότητας");
        jSliderSetTrig.setValue(36);
        jSliderSetTrig.setBorder(javax.swing.BorderFactory.createTitledBorder(null,"Θερμότητα Πυροδότησης", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 2, 12))); // NOI18N
        jSliderSetTrig.setFocusable(false);
        jTextSetTrig.setText(String.valueOf(jSliderSetTrig.getValue()));
        jTextSetTrig.setBounds(168,0, 30, 18);
        jSliderSetTrig.add(jTextSetTrig);
        contentPanel.add(jSliderSetTrig);
        
                      
        jSliderSetTrig.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int data = jSliderSetTrig.getValue();
                                jTextSetTrig.setText(String.valueOf(data));
                                gDraw.Max=data;
                                hDraw.Max=data;
                                gDraw.repaint();
                                hDraw.repaint();
			}
                        }); // Speed Slider Listener        
        jSliderSetTrig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
               // Send data packet              
               try {                   
                   // Send String with Command plus data from slider               
                   Gym62RCNT.SendString("*Tr-"+jTextSetTrig.getText()+"\n");       
                   
               }
               catch (IOException ex)
  		    {
  		      Logger.getLogger(Gym62RCNT.class.getName()).log(Level.SEVERE, null, ex);
  		    } 
                
            }
        });
        

        jButtonConnect.setText("Connect");
        jButtonConnect.setToolTipText("Σύνδεση TCP/IP");
        //jButtonConnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Diconected.png")));
        jButtonConnect.setBounds(6, 560, 110, 55);
        jButtonConnect.setBackground(Color.GRAY);
        contentPanel.add(jButtonConnect); 
        jButtonConnect.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(java.awt.event.ActionEvent evt) {
    	      
           try { Gym62RCNT.SocketConnet();}
  		  catch (IOException ex)
  		    {
  		      Logger.getLogger(Gym62RCNT.class.getName()).log(Level.SEVERE, null, ex);
  		    } 
        }
        });
       
                                        
        jButtonDissConnect.setText("DissConnect");
        jButtonDissConnect.setToolTipText("Αποσύνδεση TCP/IP");
        jButtonDissConnect.setBounds(126, 560, 110, 55);
        jButtonDissConnect.setBackground(Color.RED);
        contentPanel.add(jButtonDissConnect); 
        jButtonDissConnect.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(java.awt.event.ActionEvent evt) {
    	      
           try { Gym62RCNT.SocketDisconnect();}
  		  catch (IOException ex)
  		    {
  		      Logger.getLogger(Gym62RCNT.class.getName()).log(Level.SEVERE, null, ex);
  		    } 
        }
        });
        
             
        
        jTextFieldIpPort.setText("10001");
        jTextFieldIpPort.setToolTipText("Robot Ip Port");
        jTextFieldIpPort.setBounds(260, 560, 60, 40);
        jTextFieldIpPort.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Port"), "Port"));
        contentPanel.add(jTextFieldIpPort);        
        
        //jTextFieldIp.setText("192.168.11.1");
        jTextFieldIp.setText("192.168.11.254");
        jTextFieldIp.setToolTipText("Robot Ip");
        jTextFieldIp.setBounds(330, 560, 125, 40);
        jTextFieldIp.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("IP Address"), "IP Address"));
        contentPanel.add(jTextFieldIp);
        
        
     
        
        jButtonStart.setText("Stoped");
        jButtonStart.setToolTipText("Ενεργοποίηση");
        jButtonStart.setBackground(Color.RED);    
        jButtonStart.setBounds(660, 515, 110, 40);        
        contentPanel.add(jButtonStart);  
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(java.awt.event.ActionEvent evt) {
    	      
           try { Gym62RCNT.StartStop(StartFlag);}
  		  catch (IOException ex)
  		    {
  		      Logger.getLogger(Gym62RCNT.class.getName()).log(Level.SEVERE, null, ex);
  		    } 
        }
        });
        
        jButtonForward.setText("Up");
        jButtonForward.setToolTipText("Move Forward");
        jButtonForward.setBounds(480, 560, 70, 40);
        contentPanel.add(jButtonForward); 
        jButtonForward.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
               // Send data packet Send String with Command               
               try { Gym62RCNT.SendString("*Fwd\n");}// Move forward command               
               catch (IOException ex)
  		    {Logger.getLogger(Gym62RCNT.class.getName()).log(Level.SEVERE, null, ex);} 
                
            }
        });      
        jButtonForward.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
               // Send data packet Send String with Command        
               try {Gym62RCNT.SendString("*Swd\n");}// Move StopWard                        
               catch (IOException ex)
  		    {Logger.getLogger(Gym62RCNT.class.getName()).log(Level.SEVERE, null, ex);} 
            }
        });
        
        
        
        
        jButtonBackward.setText("Back");
        jButtonBackward.setToolTipText("Move Backward");
        jButtonBackward.setBounds(560, 560, 70, 40);
        contentPanel.add(jButtonBackward); 
        jButtonBackward.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
               // Send data packet Send String with Command               
               try { Gym62RCNT.SendString("*Bwd\n");}// Move Backward command               
               catch (IOException ex)
  		    {Logger.getLogger(Gym62RCNT.class.getName()).log(Level.SEVERE, null, ex);} 
                
            }
        });      
        jButtonBackward.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
               // Send data packet Send String with Command        
               try {Gym62RCNT.SendString("*Swd\n");}// Move StopWard Command                        
               catch (IOException ex)
  		    {Logger.getLogger(Gym62RCNT.class.getName()).log(Level.SEVERE, null, ex);} 
            }
        });
        
        
        jButtonLeft.setText("<<");
        jButtonLeft.setToolTipText("Move Left");
        jButtonLeft.setBounds(640, 560, 70, 40);
        contentPanel.add(jButtonLeft); 
        jButtonLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
               // Send data packet Send String with Command               
               try { Gym62RCNT.SendString("*Lwd\n");}// Move Backward command               
               catch (IOException ex)
  		    {Logger.getLogger(Gym62RCNT.class.getName()).log(Level.SEVERE, null, ex);} 
                
            }
        });      
        jButtonLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
               // Send data packet Send String with Command        
               try {Gym62RCNT.SendString("*Swd\n");}// Move StopWard Command                        
               catch (IOException ex)
  		    {Logger.getLogger(Gym62RCNT.class.getName()).log(Level.SEVERE, null, ex);} 
            }
        });
        
        jButtonRight.setText(">>");
        jButtonRight.setToolTipText("Move Left");
        jButtonRight.setBounds(720, 560, 70, 40);
        contentPanel.add(jButtonRight); 
        jButtonRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
               // Send data packet Send String with Command               
               try { Gym62RCNT.SendString("*Rwd\n");}// Move Backward command               
               catch (IOException ex)
  		    {Logger.getLogger(Gym62RCNT.class.getName()).log(Level.SEVERE, null, ex);} 
                
            }
        });      
        jButtonRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
               // Send data packet Send String with Command        
               try {Gym62RCNT.SendString("*Swd\n");}// Move StopWard Command                        
               catch (IOException ex)
  		    {Logger.getLogger(Gym62RCNT.class.getName()).log(Level.SEVERE, null, ex);} 
            }
        });
        

        jPanelThermalGrid.setBorder(javax.swing.BorderFactory.createTitledBorder("Γενικό Θερμικό Ίχνος"));
        jPanelThermalGrid.setToolTipText("Γενικό Θερμικό Ίχνος");
        jPanelThermalGrid.setBounds(220,13, 340, 350); 
        gDraw = new GridDraw();
        jPanelThermalGrid.add(gDraw);
        contentPanel.add(jPanelThermalGrid);
        
        
        jCheckRGB.setToolTipText("Χρωματιστό");
        jCheckRGB.setBounds(558,10, 55, 40); 
        jCheckRGB.setSelected(true);
        jCheckRGB.setText("RGB");
        jCheckRGB.setToolTipText("Με χρώμα");
        jCheckRGB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckRGB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jCheckRGB.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jCheckRGB.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        contentPanel.add(jCheckRGB);
        
        
        jCheckTextOn.setBounds(558,45, 55, 40); 
        jCheckTextOn.setSelected(true);
        jCheckTextOn.setText("Temps");
        jCheckTextOn.setToolTipText("Θερμοκρασίες");
        jCheckTextOn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckTextOn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jCheckTextOn.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jCheckTextOn.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        contentPanel.add(jCheckTextOn);
        
            
               
        jPanelThermalHorizon.setBorder(javax.swing.BorderFactory.createTitledBorder("Ορίζοντας Μέγιστης Θερμότητας"));
        jPanelThermalHorizon.setToolTipText("Ορίζοντας Μέγιστης Θερμότητας");
        jPanelThermalHorizon.setBounds(220,370, 340, 60);  
        hDraw = new HorizonDraw();
        jPanelThermalHorizon.add(hDraw);
        contentPanel.add(jPanelThermalHorizon);
        
        jPanelThermalPointer.setBorder(javax.swing.BorderFactory.createTitledBorder("Δείκτης Πλοήγησης"));
        jPanelThermalPointer.setToolTipText("Δείκτης Πλοήγησης με βάση την μέγιστη διαφορά θερμότητας");
        jPanelThermalPointer.setBounds(570,200,212,60);
        pDraw = new MovePointerDraw();
        jPanelThermalPointer.add(pDraw);
        contentPanel.add(jPanelThermalPointer);
        
       // jPanelHeatPointer.setBorder(javax.swing.BorderFactory.createTitledBorder("Δείκτης Πλοήγησης"));
       // jPanelHeatPointer.setToolTipText("Δείκτης Πλοήγησης");
       // jPanelHeatPointer.setBounds(220,350, 260, 60);        
       // contentPanel.add(jPanelHeatPointer);
        
       
        jLabelGym.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabelGym.setText("<html><p style=\"text-align:center;\">62o Γυμνάσιο Αθηνών<br>Gym62 Robot<br>Fire Tracker</html>");
        //jLabelGym.setText("Java Controlled Device");
        jLabelGym.setBounds(580,70, 420, 100);
        contentPanel.add(jLabelGym);
       
   }
   
     
   public class GridDraw extends JPanel 
	{	// Syntax of 2d Tables is Rows,Coloumns (y,x)
                public float GridTemps[][]={
                    {25,25,25,25,25,25,25,25},
                    {25,25,25,25,25,25,25,25},
                    {25,25,25,25,25,25,25,25},
                    {25,25,26,27,27,26,25,25},
                    {25,25,26,27,27,26,25,25},
                    {25,25,25,25,25,25,25,25},
                    {25,25,25,25,25,25,25,25},
                    {25,25,25,25,25,25,25,25}
                };                
                private float MaxTemp=0;
                private float MinTemp=300;                 
                
                private int Max=jSliderSetTrig.getValue();
                
		//private static final long serialVersionUID = 1L;	
		public void paintComponent(Graphics g) 
		{
			// Draws the full thermal grid in the panel attached
			this.setBackground(Color.GRAY);	
			this.setBounds(10, 20, 320, 320);			
			int x,y;
                        int red;
                        int blue;
                        float multiplier=0;
                        String str=null;
			
                        // Get Maximum and minimum Temperatures
                        for (x=0;x<8;x++)
                        {
                            for (y=0;y<8;y++)
                            {
                                if (GridTemps[y][x]>MaxTemp) {MaxTemp=GridTemps[y][x];}
                                if (GridTemps[y][x]<MinTemp) {MinTemp=GridTemps[y][x];}
                            }
                        }
                        
                        // find red multiplier acording to maximum temperarure
                        multiplier=255/MaxTemp;
                        
                        for (x=0;x<8;x++)
                        {
                            for (y=0;y<8;y++){
                               red=(int)(GridTemps[y][x]*multiplier);
                               //red=red*2;
                               if (red>255) red=255;
                               blue=(int)(255-red);
                               if (blue<0) blue=0;
                               
                               if (jCheckRGB.isSelected())
                                {
                               if (GridTemps[y][x]>=Max) g.setColor(new Color(red,0,blue)); 
                               else g.setColor(Color.BLUE);
                                }
                               else
                               {
                                 if (GridTemps[y][x]>=Max) g.setColor(new Color(red,red,red)); 
                                 else g.setColor(Color.BLACK);  
                               }
                               
                               //if (GridTemps[y][x]==MaxTemp) g.draw3DRect(x*30, y*30,30,30,true);
                               //else g.draw3DRect(x*30, y*30,30,30,false);
                              g.fillRect(x*40,y*40,40,40); 
                              if(jCheckTextOn.isSelected())
                              {
                              if (jCheckRGB.isSelected()) g.setColor(Color.white);
                              else g.setColor(Color.RED);
                              str=String.valueOf(GridTemps[y][x]);
                              g.drawString(str, x*40, (y*40)+20);
                              
                              }
                            }                        
                        }
                        
		}// Function PaintThermals		
	}// class ThermalDraw
   
   
   public class HorizonDraw extends JPanel {
       
       public float HORIZON_TEMPS[]={25,25,26,27,27,26,25,25};
       
        private float MaxTemp=0;
        private float MinTemp=300;   
       
       private int Max=jSliderSetTrig.getValue();
       // Draws the Mean Value of the grid in Horizon on a panel attached
       public void paintComponent(Graphics g) 
		{
                    this.setBackground(Color.GRAY);	
                    this.setBounds(10, 20, 320, 30);			
                    int x;
                    int c=0;
                    int red;
                    int blue;
                    float multiplier=0;
                    String str=null;
                    
                    for (x=0;x<8;x++)
                        {
                         if (HORIZON_TEMPS[x]>=MaxTemp) {MaxTemp=HORIZON_TEMPS[x];}                                         
                        }
                        
                        multiplier=255/MaxTemp;                
                    
                    for (x=0;x<8;x++)
                    {
                     red=(int)(HORIZON_TEMPS[x]*multiplier);
                     //red=red*2;
                     if (red>255) red=255;
                     blue=(int)(255-red);
                     if (blue<0) blue=0;   
                     
                     if (jCheckRGB.isSelected())
                     {
                     if (HORIZON_TEMPS[x]>Max) g.setColor(new Color(red,0,blue));                     
                     else g.setColor(Color.BLUE);
                     }
                     else
                     {
                     if (HORIZON_TEMPS[x]>Max) g.setColor(new Color(red,red,red));                     
                     else g.setColor(Color.BLACK);  
                     }
                                      
                     g.fillRect(x*40,0,40,30); 
                     if(jCheckTextOn.isSelected())
                     {
                     if (jCheckRGB.isSelected()) g.setColor(Color.white);
                     else g.setColor(Color.RED);
                     str=String.valueOf(HORIZON_TEMPS[x]);
                     g.drawString(str, x*40, 20);
                     }
                    }
                }
       
     
       
   }// HorizonDraw Class
   
   public class MovePointerDraw extends JPanel {
       public float POINTER_TABLE[]={0,0,0,0,0,0,0,0};
       BufferedImage bi;
       public void paintComponent(Graphics g) 
		{
                    
                    this.setBounds(5, 25, 212, 60);	
                    int x;
           try {
               bi = ImageIO.read(new File("src/images/fire25.png"));
           } catch (IOException ex) {
               Logger.getLogger(Gym62GuiFrame.class.getName()).log(Level.SEVERE, null, ex);
           }
                    for(x=0;x<8;x++)
                    {
                        g.setColor(Color.BLACK);
                        g.fillRect(x*25,0,25,25);
                        g.setColor(Color.RED);
                        g.drawRect(x*25,0,25,25);                        
                        if (POINTER_TABLE[x]==1) {g.drawImage(bi,x*25,0, null);}                                         
                       
                       
                    }
                }
       
   }
   
   
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
            java.util.logging.Logger.getLogger(Gym62GuiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gym62GuiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gym62GuiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gym62GuiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gym62GuiFrame().setVisible(true);
                
            }
        });
    }
   
    
}
