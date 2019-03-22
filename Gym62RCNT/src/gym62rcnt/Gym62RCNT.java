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
 */
/*
Send Controll Codes are
    *Str -> Start
    *Stp -> Stop
    *Trxxx -> Triger data
    *Spxxx -> Speed data
    *Tmxxx -> Wait Reading Time Data
*/
package gym62rcnt;


import static gym62rcnt.Gym62GuiFrame.jTextGetTrig;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vlastos
 */
public class Gym62RCNT {

  static Socket socket=null;
  static OutputStream DataOutputs=null;
  static OutputStreamWriter StreamOutputs=null;
  static InputStream DataInputs=null; 
  //static BufferedReader BufferedInputs =null;  
  static byte ReadedData[]= new byte[50];
  static int len;
  static Thread listener;
  //BufferedReader input =  new BufferedReader(new InputStreamReader(s.getInputStream())); 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        Gym62GuiFrame Gym62GUI= new Gym62GuiFrame();     
        Gym62GUI.setVisible(true);
        
        
       //GetInputBuffer();
      
    }
    
    
 
    
  static class StartInputBufferdRead extends Thread
  {
           
          String str;
          String[] strParts;
          String strNum;                         
          @Override                                    
          public void run() { 
             
              try {
                  
                  BufferedReader BufferedInputs =new BufferedReader(new InputStreamReader(socket.getInputStream())); 
                    
                 // BufferedInputs=new BufferedReader(new InputStreamReader(socket.getInputStream()));                  
                
                                   
              while ((str=BufferedInputs.readLine())!=null)                   
             {
                       
                      System.out.println(str); 
                      if (str.contains("!tmp-")) // Ambient temperarure
                      {
                          strParts = str.split("-");
                          strNum = strParts[1];
                          float val =Float.parseFloat(strNum);                         
                          Gym62GuiFrame.jSliderAbTemp.setValue((int)val);
                          Gym62GuiFrame.jTextAbTemp.setText(strNum);
                          Gym62GuiFrame.contentPanel.repaint();
                          
                      }
                      if (str.contains("!lgt-")) // ambient light
                      {
                          strParts = str.split("-");
                          strNum = strParts[1];
                          int val =Integer.parseInt(strNum);
                          Gym62GuiFrame.jSliderAbLight.setValue((int)val);
                          Gym62GuiFrame.jTextAbLight.setText(strNum);
                          Gym62GuiFrame.contentPanel.repaint();
                      }
                      if (str.contains("!spr-")) // Speed of right caterpilar
                      {
                          strParts = str.split("-");
                          strNum = strParts[1];
                          int val =Integer.parseInt(strNum);                          
                          Gym62GuiFrame.jSliderSpeedR.setValue((int)val);
                          Gym62GuiFrame.jTextSpeedR.setText(strNum);
                          Gym62GuiFrame.contentPanel.repaint();
                      }
                      if (str.contains("!spl-")) // Speed of left caterpilar
                      {
                          strParts = str.split("-");
                          strNum = strParts[1];
                          int val =Integer.parseInt(strNum);
                          Gym62GuiFrame.jSliderSpeedL.setValue((int)val);
                          Gym62GuiFrame.jTextSpeedL.setText(strNum);
                          Gym62GuiFrame.contentPanel.repaint();
                      }
                      if (str.contains("!dtf-")) // Distance front
                      {
                          strParts = str.split("-");
                          strNum = strParts[1];
                          int val =Integer.parseInt(strNum);
                          Gym62GuiFrame.jSliderDistanceF.setValue((int)val);
                          Gym62GuiFrame.jTextDistanceF.setText(strNum);
                          Gym62GuiFrame.contentPanel.repaint();
                      }
                      if (str.contains("!dtb-")) // Distance back
                      {
                          strParts = str.split("-");
                          strNum = strParts[1];
                          int val =Integer.parseInt(strNum);
                          Gym62GuiFrame.jSliderDistanceB.setValue((int)val);
                          Gym62GuiFrame.jTextDistanceB.setText(strNum);
                          Gym62GuiFrame.contentPanel.repaint();
                      }
                      if (str.contains("!htt-")) // Sensor Internal Temp
                      {
                          strParts = str.split("-");
                          strNum = strParts[1];
                          float val =Float.parseFloat(strNum);
                          Gym62GuiFrame.jSliderTrHeat.setValue((int)val);
                          Gym62GuiFrame.jTextTrHeat.setText(strNum);
                          Gym62GuiFrame.contentPanel.repaint();
                      }
                      /*
                      if (str.contains("!trg-")) // Fire Triger temperature
                      {
                          strParts = str.split("-");
                          strNum = strParts[1];
                          Gym62GuiFrame.jTextGetTrig.setText(strNum);
                          Gym62GuiFrame.contentPanel.repaint();
                      }
                     
                      if (str.contains("!spd-")) // Vehicle speed
                      {
                          strParts = str.split("-");
                          strNum = strParts[1];
                          Gym62GuiFrame.jTextGetSpeed.setText(strNum);
                          Gym62GuiFrame.contentPanel.repaint();
                      }
                     
                      if (str.contains("!tme-")) // Stay time for readings
                      {
                          strParts = str.split("-");
                          strNum = strParts[1];
                          Gym62GuiFrame.jTextGetTime.setText(strNum);
                          Gym62GuiFrame.contentPanel.repaint();
                      }
                      */
                      
                      if (str.contains("!ro") || str.contains("!hrz-") || str.contains("!ptr-"))
                      {
                          ParseThermalString(str);
                      }
             
                                      
                }//while
              } catch (IOException ex) {
                  Logger.getLogger(Gym62RCNT.class.getName()).log(Level.SEVERE, null, ex);
              } 
              
                              
                                    
          }//run                               
      }// class
     
      
  
    
  static void StartStop(boolean flag) throws IOException
  {
      String strT;
      if (flag==false) 
      {
          strT="*Str\n";
          Gym62GuiFrame.jButtonStart.setBackground(Color.GREEN);               
          Gym62GuiFrame.jButtonStart.setText("Started");
          Gym62GuiFrame.jButtonStart.setToolTipText("Απενεργοποίηση");
          Gym62GuiFrame.StartFlag=true;
      }
      else 
      {
          strT="*Stp\n";
          Gym62GuiFrame.jButtonStart.setBackground(Color.RED);               
          Gym62GuiFrame.jButtonStart.setText("Stoped");
          Gym62GuiFrame.jButtonStart.setToolTipText("Ενεργοποίηση");
          Gym62GuiFrame.StartFlag=false;
      }      
      try
		{
                  StreamOutputs.write(strT,0, strT.length());
                  StreamOutputs.flush();				    		
		  Thread.sleep(200); // 200ms Delay
		}
		catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
      
  }
  // **************** TCP/IP Connect Disconnect Method ***************************** 
  static void SocketConnet() throws IOException 
    {        
        
        
	  String host = Gym62GuiFrame.jTextFieldIp.getText();
	  int port = Integer.parseInt(Gym62GuiFrame.jTextFieldIpPort.getText());
                     
	  
	  try {
			 socket = new Socket(host,port);
			 DataOutputs = socket.getOutputStream();
                         DataInputs = socket.getInputStream();                         
			 StreamOutputs =new OutputStreamWriter(socket.getOutputStream(), "UTF-8");                        
                         StartInputBufferdRead InputThread = new StartInputBufferdRead();  // create a read thread                  
                        
                         
			if (socket.isClosed()==false) { 
				Gym62GuiFrame.jButtonConnect.setBackground(Color.GREEN); 
                                Gym62GuiFrame.jButtonDissConnect.setBackground(Color.GRAY); 
				//Gym62GUI.jButtonConnect.setIcon(new ImageIcon(KronosGasGUI.class.getResource("/KronosControll/icons/Connected.png"))); 
				Gym62GuiFrame.jButtonConnect.setText("Connected");                               
                                InputThread.start(); // start thread      
                                
				}
                        
                        
			
			else {
					Gym62GuiFrame.jButtonConnect.setBackground(Color.GRAY);
                                        Gym62GuiFrame.jButtonDissConnect.setBackground(Color.RED);
					
					}																	
			
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
          
         
         
  }
    
  static void SocketDisconnect() throws IOException
  {
	  try {
		  DataOutputs.close(); 
                  DataInputs.close();
                  StreamOutputs.close();                 
		  socket.close();	 
		  
			if (socket.isClosed()==true) 
			{ 
				Gym62GuiFrame.jButtonConnect.setBackground(Color.GRAY);
                                Gym62GuiFrame.jButtonDissConnect.setBackground(Color.RED); 
				//KronosGasGUI.btnConnectButton.setIcon(new ImageIcon(KronosGasGUI.class.getResource("/KronosControll/icons/Diconnected.png"))); 
				Gym62GuiFrame.jButtonConnect.setText("Connect");
				}
			else 
			{
				Gym62GuiFrame.jButtonConnect.setBackground(Color.GREEN); 
				Gym62GuiFrame.jButtonDissConnect.setBackground(Color.GRAY); 
				}																	
			
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
  }
// *******************************************************************************************
public static void SendByte(char bte)  throws IOException
	{
            
            try
		{
                  DataOutputs.write(bte);      
		  //Thread.sleep(200); // 200ms Delay
		}
		catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 		  
		  
	}// send data



public static void SendString(String str)  throws IOException
{
    try
		{
                  StreamOutputs.write(str,0, str.length());
                  StreamOutputs.flush();				    		
		  //Thread.sleep(200); // 200ms Delay
		}
		catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
    
}


public static void ParseThermalString (String str) throws IOException
{
    String stringParts[];
    int n;
     try
		{
                  if (str.contains("!ro0-"))
                      {
                          stringParts = str.split("-");
                         
                          for (n=0;n<8;n++)
                          {
                          //Gym62GuiFrame.gDraw.GridTemps[1][n]=Integer.parseInt(stringParts[n+1]);
                          Gym62GuiFrame.gDraw.GridTemps[0][n]=Float.parseFloat(stringParts[n+1]);
                          }
                      }
                  
                  if (str.contains("!ro1-"))
                      {
                          stringParts = str.split("-");
                         
                          for (n=0;n<8;n++)
                          {
                          //Gym62GuiFrame.gDraw.GridTemps[2][n]=Integer.parseInt(stringParts[n+1]);
                          Gym62GuiFrame.gDraw.GridTemps[1][n]=Float.parseFloat(stringParts[n+1]);
                          }
                      }
                  
                  if (str.contains("!ro2-"))
                      {
                          stringParts = str.split("-");
                         
                          for (n=0;n<8;n++)
                          {
                         // Gym62GuiFrame.gDraw.GridTemps[3][n]=Integer.parseInt(stringParts[n+1]);
                          Gym62GuiFrame.gDraw.GridTemps[2][n]=Float.parseFloat(stringParts[n+1]);
                          }
                      }
                  
                  if (str.contains("!ro3-"))
                      {
                          stringParts = str.split("-");
                         
                          for (n=0;n<8;n++)
                          {
                          //Gym62GuiFrame.gDraw.GridTemps[4][n]=Integer.parseInt(stringParts[n+1]);
                          Gym62GuiFrame.gDraw.GridTemps[3][n]=Float.parseFloat(stringParts[n+1]);
                          }
                      }
                  
                  if (str.contains("!ro4-"))
                      {
                          stringParts = str.split("-");
                         
                          for (n=0;n<8;n++)
                          {
                          //Gym62GuiFrame.gDraw.GridTemps[5][n]=Integer.parseInt(stringParts[n+1]);
                          Gym62GuiFrame.gDraw.GridTemps[4][n]=Float.parseFloat(stringParts[n+1]);
                          }
                          
                      }
                  
                  if (str.contains("!ro5-"))
                      {
                          stringParts = str.split("-");
                         
                          for (n=0;n<8;n++)
                          {
                          //Gym62GuiFrame.gDraw.GridTemps[6][n]=Integer.parseInt(stringParts[n+1]);
                          Gym62GuiFrame.gDraw.GridTemps[5][n]=Float.parseFloat(stringParts[n+1]);
                          }
                          
                      }
                  
                  if (str.contains("!ro6-"))
                      {
                          stringParts = str.split("-");
                         
                          for (n=0;n<8;n++)
                          {
                          //Gym62GuiFrame.gDraw.GridTemps[7][n]=Integer.parseInt(stringParts[n+1]);
                          Gym62GuiFrame.gDraw.GridTemps[6][n]=Float.parseFloat(stringParts[n+1]);
                          }
                          
                      }
                  
                  if (str.contains("!ro7-"))
                      {
                          stringParts = str.split("-");
                         
                          for (n=0;n<8;n++)
                          {
                          Gym62GuiFrame.gDraw.GridTemps[7][n]=Float.parseFloat(stringParts[n+1]);
                          }
                         
                      }
                  
                  if (str.contains("!hrz-"))
                      {
                          stringParts = str.split("-");
                         
                          for (n=0;n<8;n++)
                          {
                         // Gym62GuiFrame.hDraw.HORIZON_TEMPS[n]=Integer.parseInt(stringParts[n+1]);
                          Gym62GuiFrame.hDraw.HORIZON_TEMPS[n]=Float.parseFloat(stringParts[n+1]);
                          }
                         
                      }
                  
                  if (str.contains("!ptr-"))
                      {
                          stringParts = str.split("-");
                         
                          for (n=0;n<8;n++)
                          {
                          Gym62GuiFrame.pDraw.POINTER_TABLE[n]=Integer.parseInt(stringParts[n+1]);
                          }
                         
                      }
                    
                    Gym62GuiFrame.gDraw.repaint();
                    Gym62GuiFrame.hDraw.repaint();
                    Gym62GuiFrame.pDraw.repaint();
		}
		catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
    
}


}
