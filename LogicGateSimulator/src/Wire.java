
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import javax.swing.JPanel;


public class Wire {
    
    double xStart, yStart, xEnd, yEnd;
    boolean onOff, connected;
    Stroke newStroke = new BasicStroke(5);
    
    public Wire(double xstart, double ystart, double xend, double yend){
        
        this.xStart = xstart;
        this.yStart = ystart;
        this.xEnd = xend;
        this.yEnd = yend;
        
        this.onOff = false;
        this.connected = false;


    }

    public boolean wiresConnected(double wireX1, double wireY1, double wireX2, double wireY2, double inX1, double inY1, double inX2, double inY2){
    
            if( wireX1 > inX1 && wireY1 > inY1 && wireX1 < inX2 && wireY1 < inY2 ||
                wireX2 > inX1 && wireY2 > inY1 && wireX2 < inX2 && wireY2 < inY2){
                
                this.connected = true;
                return true;
            }
            else{
                
                this.connected = false;
                return false;
            }
    
    }
    
    public void onOffWire(boolean input){
        
        if(input == true){
            this.onOff = true;
        }

        else{
            this.onOff = false;
        }
        
    }
    
    public void createWireImage(JPanel j){       
        
        Graphics2D g = (Graphics2D) j.getGraphics();
        draw(g);
        

    }
    
    public void draw( Graphics2D g) {
        
        if(onOff == false){
            g.setColor(Color.red);
        }
        
        else{
            g.setColor(Color.green);
        }
        
        g.setStroke(newStroke);
        g.drawLine((int) this.xStart, (int) this.yStart, (int) this.xEnd, (int) this.yEnd);
        
    }  
    
}
