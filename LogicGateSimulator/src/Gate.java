
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


public class Gate{
    
    String type;
    boolean input1, input2, gateOnOff, connections;
    double inX1, inY1, inX2, inY2;
    double outX1, outY1;
    double x1, y1, width, height;
    
    int gateSize = 100;
    int inputSize = 15;
    int outputSize = inputSize;
    
    public Gate(String t, double X1, double Y1, double X2, double Y2){
        
        this.type = t;
        
        this.x1 = X1;
        this.y1 = Y1;
        this.width = X2;
        this.height = Y2;
        
        inputsAndOutputs();
        
        this.gateOnOff = false;

    }
    
    public void inputsAndOutputs(){
        
        this.inX1 = this.x1 + gateSize/8;
        this.inX2 = this.inX1;
        this.inY1 = this.y1 + gateSize/4;
        this.inY2 = this.y1 + gateSize/4 + gateSize/4;
        
        this.outX1 = this.x1 + gateSize - (gateSize/5);
        this.outY1 = this.y1 + gateSize/4;
        
    
    }
    
    public boolean checkConnections(double wireX1, double wireY1, double wireX2, double wireY2,
                                 double wireX3, double wireY3, double wireX4, double wireY4,
                                 double inX1, double inY1, double inX2, double inY2,
                                 double inX3, double inY3, double inX4, double inY4,
                                 boolean in1, boolean in2){
        
        if( (wireX1 > inX1 && wireY1 > inY1 && wireX1 < inX2 && wireY1 < inY2 ||
            wireX2 > inX1 && wireY2 > inY1 && wireX2 < inX2 && wireY2 < inY2 ||
            wireX3 > inX1 && wireY3 > inY1 && wireX3 < inX2 && wireY3 < inY2 ||
            wireX4 > inX1 && wireY4 > inY1 && wireX4 < inX2 && wireY4 < inY2) &&
            
            (wireX1 > inX3 && wireY1 > inY3 && wireX1 < inX4 && wireY1 < inY4 ||
            wireX2 > inX3 && wireY2 > inY3 && wireX2 < inX4 && wireY2 < inY4 ||
            wireX3 > inX3 && wireY3 > inY3 && wireX3 < inX4 && wireY3 < inY4 ||
            wireX4 > inX3 && wireY4 > inY3 && wireX4 < inX4 && wireY4 < inY4)){
            
            
            
            if(in1 == true)
                this.input1 = true;
            else
                this.input1 = false;
            
            if(in2 == true)
                this.input2 = true;
            else
                this.input2 = false;
        
            
            return true;
        }
        
        else{
            
            this.input1 = false;
            this.input2 = false;
            
            return false;
            
        }
        
        
        
    }
    
    
    public void onOffGate(){
        
        //Rules: http://whatis.techtarget.com/definition/logic-gate-AND-OR-XOR-NOT-NAND-NOR-and-XNOR
        
        
        //AND Gate
        if(this.type.equals("AND")){

            if(input1 == false || input2 == false)
                 this.gateOnOff = false;
            
            else if(input1 == true && input2 == true)
                 this.gateOnOff = true;
             
        }
        //OR Gate
        else if(this.type.equals("OR")){
            if(input1 == false && input2 == false)
                this.gateOnOff = false;

            else
                this.gateOnOff = true;
        }
        //XOR Gate
        else if(this.type.equals("XOR")){
            if(input1 == false && input2 == false || input1 == true && input2 == true)
                this.gateOnOff = false;

            else
                this.gateOnOff = true;
        }
        //NAND Gate
        else if(this.type.equals("NAND")){
            if(input1 == false || input2 == false)
                this.gateOnOff = true;

            else
                this.gateOnOff = false;
        }
        //NOR Gate
        else if(this.type.equals("NOR")){
            if(input1 == true || input2 == true)
                this.gateOnOff = false;

            else
                this.gateOnOff = true;
        }
        //XNOR Gate
        else if(this.type.equals("XNOR")){
            if(input1 == false && input2 == false || input1 == true && input2 == true)
                this.gateOnOff = true;

            else
                this.gateOnOff = false;
        }
        
    }
    
    public void createLogicGate(JPanel j){
        
        Graphics2D g = (Graphics2D) j.getGraphics();
        draw(g);
        
    }
    
    public void draw(Graphics2D g){
        
        g.setColor(Color.black);
        g.drawRect( (int)x1, (int)y1, (int)width, (int)height);
        g.setColor(Color.green);
        g.drawRect( (int)inX1, (int)inY1, (int)inputSize, (int)inputSize);
        g.drawRect( (int)inX2, (int)inY2, (int)inputSize, (int)inputSize);
        g.setColor(Color.red);
        g.drawRect( (int)outX1, (int)outY1, (int)outputSize, (int)outputSize);
        g.drawString(type, (int) x1 + 38, (int) y1 + 50);
        
        
    }
    
    
}


