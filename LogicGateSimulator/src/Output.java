
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JPanel;


public class Output {
    
    boolean input; 
    double x1, y1, width, height;
    double outX1, outY1;
    Color color;
    
    double inputSize = 25;
    
    public Output(double X1, double Y1, double Width, double Height){
        
        this.input = false;
        this.color = Color.red;
        
        this.x1 = X1;
        this.y1 = Y1;
        this.width = Width;
        this.height = Height;
        
        inputsAndOutputs();
        
    }
    
    public void inputsAndOutputs(){
        
        this.outX1 = this.x1 - width/2 - 10;
        this.outY1 = this.y1 + height/2;
        
    }
    
    public boolean checkConnections(double wireX1, double wireY1, double wireX2, double wireY2,
                                    double outX1, double outY1, double outX2, double outY2, boolean in){
        
        if( wireX1 > outX1 && wireY1 > outY1 && wireX1 < outX2 && wireY1 < outY2 ||
            wireX2 > outX1 && wireY2 > outY1 && wireX2 < outX2 && wireY2 < outY2){
        
           if(in == true){
               input = true;
           }
           else{
               input = false;
           }
           
           return true;
        
        }
        
        else{
            
            return false;
        }
        
        
    }
    
    public void createOutput(JPanel j){
        
        Graphics2D g = (Graphics2D) j.getGraphics();
        
        draw(g);
        
    }
    
    public void draw(Graphics2D g){
        
        
        if(input == false){
            g.setColor(Color.red);
        }
        
        else{
            g.setColor(Color.green);
        }
        
        g.fillRect( (int)x1, (int)y1, (int)width, (int)height);
        g.drawRect( (int)outX1, (int)outY1, (int)inputSize, (int)inputSize);

        
    }
    
    
}
