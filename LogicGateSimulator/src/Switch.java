
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JPanel;


public class Switch {
    
    boolean output; 
    double x1, y1, width, height;
    double outX1, outY1;
    Color color;
    
    double outputSize = 25;
    
    public Switch(double X1, double Y1, double Width, double Height){
        
        this.output = false;
        this.color = Color.red;
        
        this.x1 = X1;
        this.y1 = Y1;
        this.width = Width;
        this.height = Height;
        
        inputsAndOutputs();
        
    }
    
    public void inputsAndOutputs(){
        
        this.outX1 = this.x1 + this.width + 10;
        this.outY1 = this.y1 + height/2;
        
    }
    
    public void createSwitch(JPanel j){
        
        Graphics2D g = (Graphics2D) j.getGraphics();
        
        draw(g);
        
    }
    
    public void draw(Graphics2D g){
        
        
        g.setColor(color);
        
        
        g.fillRect( (int)x1, (int)y1, (int)width, (int)height);
        g.drawRect( (int)outX1, (int)outY1, (int)outputSize, (int)outputSize);

        

        
    }
    
}
