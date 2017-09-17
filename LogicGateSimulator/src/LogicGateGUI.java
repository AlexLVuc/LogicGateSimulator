
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class LogicGateGUI extends javax.swing.JFrame {
    
    //Used as icon on top of the simulator
    ImageIcon img = new ImageIcon("C:/Users/Alexander/Desktop/LogicGateSimulator/LogicGateSimulator/Icon.jpg");
    
    //Used as the same colour as both JPanels. Used as possible reference.
    Color greyBox = new Color(152,152,152);
    
    //Boolean statements regarding to which component a user is adding at any given time.
    //Also includes moveOption and removingComponent which determines if the user is moving components or removing them.
    static boolean addingLogicGate = false;
    static boolean addingSwitch = false;
    static boolean addingOutput = false;
    static boolean moveOption = false;
    static boolean removingComponent = false;
    
    //Index of the component that needs to be moved / deletecd.
    int toBeDeleted;
    int toBeMoved;
    
    //Respective arrays / information about component objects.
    //WIRE
    Wire[] wires = new Wire[50];
    int numWires = 0;
    double wireStartx, wireStarty, wireEndx, wireEndy;
    
    //GATE
    Gate[] gates = new Gate[50];
    int numGates = 0;
    static String gateType;
    double gateCornerx1, gateCornery1;
    double gateWidth = 100;
    double gateHeight = gateWidth;
    
    //INPUTS
    Switch[] switches = new Switch[50];
    int numSwitches = 0;
    double switchx1, switchy1;
    double widthSwitch = 50;
    double heightSwitch = widthSwitch;
    
    //OUTPUTS
    Output[] outputs = new Output[50];
    int numOutputs = 0;
    double outputx1, outputy1;
    double widthOutput = 50;
    double heightOutput = widthOutput;
    
    //Strings used in textArea or used as reference.
    //Used as reference for used gates
    String[] comboBoxString = new String[] { "AND", "OR", "XOR", "NAND", "NOR", "XNOR" };
    //Used to the temporary type of gate that is being moved/deleted.
    String type = "Man that's a lot of fields!";
    
    //Text box messages.
     static String genericMessage = "Welcome to the Logic Gate Simulator! Here you can simulate "
                        + "basic digital circuitry via logic gates, outputs and inputs. "
                        + "For any questions regarding this software and/or any questions "
                        + "about logic gates or digital circuitry, refer to the help tab above.";
     static String gateMessage = "You selected a logic gate. Logic gates are the "
                        + "fundamental building blocks of digital circuitry. Most "
                        + "logic gates have two inputs and one output. Each of these "
                        + "are represented with either a (0) or a (1) at all times.";
     static String inputMessage = "You selected an input. Inputs determine the input "
                        + " of a logic gate that is connected to the switch via wire.";
     static String outputMessage = "You selected an output. Outputs have one single "
                        + "input that can be connected to a gate and will either turn "
                        + "on or off depending on the set input.";
     static String wireMessage = "You are placing wire. Wire is the basic building block "
                        + "for connecting components together.";
     static String helpMessage = " If you have any questions or require assistance, see the help tab.";
    
    
    //Constructor
    public LogicGateGUI() {
        initComponents();
        
        //Set titlebar information
        setTitle("Logic Gate Simulator");
        setIconImage(img.getImage());
        
        //Wrap text in text area
        testArea.setLineWrap(true);
        
        
    }
    
    //Here are the procedures to test of a set of components are connected
    //Using procedures which can be called from other classes, it determines whether
    //the x and y of a wire is inside the input or output box of a component.
    
    //Checks if wires are connected to switches
    public void checkWiresWithSwitches(){
        int h = 100;
        for (int i = 0; i < numSwitches; i++) {
            for (int j = 0; j < numWires; j++) {
                
                //Not necessary anymore
                if(j != h){                       
                    
                    //Checks the x and y coordinates of both sides of wires and determines if any wire is connected to a switches output box.
                    //A wire is connected if it is inside the output box.
                    boolean checkIfWireConnected = wires[j].wiresConnected(wires[j].xStart, wires[j].yStart, wires[j].xEnd, wires[j].yEnd, 
                                       switches[i].outX1, switches[i].outY1, switches[i].outX1 + switches[i].outputSize,
                                       switches[i].outY1 + switches[i].outputSize);
                    
                    //Changes the colour of the wire depending on the specific switch it is connected to.
                    //Green = on or (1)
                    //Red = off or (0)
                    if(checkIfWireConnected == true){

                        wires[j].onOffWire(switches[i].output);
                        wires[j].createWireImage(mainJPanel);


                    }
                }
            }   
        }  
    }
    
    //Checks case of wires that are connected to switches are also connected to gates.
    public void checkSwitchWiresWithGates(){
        
        //Originally used to fix a problem where the code would not detect a wire and a gate if
        //they were in the same index in their respective arrays.
        int l = 100;
        int m = 100;
        
        //Uses a triple for-loop to check the connection of two wires, as all gates have two inputs, so
        //two sets of wires must be checked at every given moment, that being the ends of the wire and the
        //starting place of them.
        for (int i = 0; i < numGates; i++) {
            for (int j = 0; j < numWires; j++) {
                for (int k = 0; k < numWires; k++) {

                    if(j != l && k != m && j != k){
                        
                        //Checks if both wires are connected to the gates input slots.
                        boolean checkIfWireConnected = gates[i].checkConnections(wires[j].xStart, wires[j].yStart, wires[j].xEnd, wires[j].yEnd, 
                                      wires[k].xStart, wires[k].yStart, wires[k].xEnd, wires[k].yEnd, 
                                      gates[i].inX1, gates[i].inY1, gates[i].inX1 + gates[i].inputSize, gates[i].inY1 + gates[i].inputSize, 
                                      gates[i].inX2, gates[i].inY2, gates[i].inX2 + gates[i].inputSize, gates[i].inY2 + gates[i].inputSize,  wires[j].onOff, wires[k].onOff);
                        
                        
                        //If both wires are connected, this determines the output of the specific gate.
                        if(checkIfWireConnected == true){

                            gates[i].onOffGate();
                            gates[i].createLogicGate(mainJPanel);


                        }   
                    }    
                }   
            }   
        }     
    }
    
    //Checks that a gate has an output wire attached to it. 
    public void checkGateWires(){
        
        //Same use as the previous procedure
        int h = 100;
        for (int i = 0; i < numGates; i++) {
            for (int j = 0; j < numWires; j++) {
                
                //Checks if wires are connected inside of the respective gate output boxes.
                if(j != h){
                    boolean checkIfWireConnected = wires[j].wiresConnected(wires[j].xStart, wires[j].yStart, wires[j].xEnd, wires[j].yEnd, 
                                           gates[i].outX1, gates[i].outY1, gates[i].outX1 + gates[i].outputSize,
                                           gates[i].outY1 + gates[i].outputSize);
                    
                    //If they are connected, this sets the output of a selected wire to be whatever the respective
                    //gate's output is set to.
                    if(checkIfWireConnected == true){
                        
                        checkSwitchWiresWithGates();
                        wires[j].onOffWire(gates[i].gateOnOff);
                        wires[j].createWireImage(mainJPanel);
                        
                    }   
                }
            }
        }      
    }
    
    //Checks that a gate is connected to an output.
    public void checkGateWiresWithOutput(){
        
        //Same use as the previous procedure
        int h = 100;
        for (int i = 0; i < numOutputs; i++) {
            for (int j = 0; j < numWires; j++) {
                
                if(j != h){
                    
                    //This checks if wires which are connected to gate outputs are also connected to output's input boxes.
                    boolean checkIfWireConnected = outputs[i].checkConnections(wires[j].xStart,  wires[j].yStart, 
                            wires[j].xEnd, wires[j].yEnd, outputs[i].outX1, outputs[i].outY1, outputs[i].outX1 + outputs[i].inputSize, 
                            outputs[i].outY1 + outputs[i].inputSize, wires[j].onOff);
                    
                    //If a wire is connected, this creates the output.
                    if(checkIfWireConnected == true){
                        
                        outputs[i].createOutput(mainJPanel);

                    }
                }
            }
        }
    }
    

    //This function takes in two x and y coordinates which are your mouse at the time of pressing the screen
    //Used to determine which component a user is moving/deleting by setting the index of that specific object
    //As well as setting the specific object type, set as a string.
    public int checkAllComponents(double x, double y){
        
        //Original values
        int arrayValue = 100;
        boolean found = false;
        
        //Each foorloop has a case where if the component is already found, the for oop stops.
        //Each forloop has a test case to determine if the user is over a specific component,
        //If a user is over that component, it sets the object type as well as the index of that object.
        //It also sets found to true, breaking all remaining forloops.
        
        //GATES
        for (int i = 0; i < numGates; i++) {
            
            if(found == true){                
                break;
            }
            
            else if( x > gates[i].x1 && x < gates[i].x1 + gates[i].gateSize &&
                     y > gates[i].y1 && y < gates[i].y1 + gates[i].gateSize){
                
                type = "Gate";
                arrayValue = i;
                found = true;

            }
            
        }
        
        //INPUTS
        for (int i = 0; i < numSwitches; i++) {
            if(found == true){               
                break;
            }
            else if( x > switches[i].x1 && x < switches[i].x1 + switches[i].width &&
                     y > switches[i].y1 && y < switches[i].y1 + switches[i].width){
                
                type = "Switch";
                arrayValue = i;
                found = true;
                
            }
            
        }
        
        //OUTPUTS
        for (int i = 0; i < numOutputs; i++) {
            if(found == true){                
                break;
            }
            else if( x > outputs[i].x1 && x < outputs[i].x1 + outputs[i].width &&
                     y > outputs[i].y1 && y < outputs[i].y1 + outputs[i].width){
                
                type = "Output";
                arrayValue = i;
                found = true;
                
            }
            
        }
        
        //return call
        return arrayValue;
        
        
    }
    
    //Once a user has selected an object and is currently moving it, this procedure is called.
    public void repaintJPanel(int x){
        
        //Temp arrays are set as the original arrays.
        Gate[] newGateArray = gates;
        Switch[] newSwitchArray = switches;
        Output[] newOutputArray = outputs; 
        Wire[] newWireArray = wires;
        
        //Arrays are renewed
        gates = new Gate[50];
        switches = new Switch[50];
        outputs = new Output[50];
        wires = new Wire[50];
        
        //The reason that you replace the array with an empty one is for when a user wants to remove a component
        //This procedure takes the object type, and if the user is removing the component, the arrays are reset.
        //Each forloop goes through the array or the respective object, and when the index "x" is the same as the
        //forloop index as well as the same type is selected, the procedure skips over that, and continues reassigning 
        //the rest of the objects in the arrays accordingly without the object which is being removed. This is done by
        //using variable y, which has the same value of the forloop index until an object is removed, then y does not increase
        //during that rotation of the loop.
        
        //GATE
        int y = 0;
        for (int i = 0; i < numGates; i++) {
            if(type.equals("Gate") && i == x){
                //Redrawing gate while moving
                if(moveOption == true){
                    gates[y] = newGateArray[i];
                    gates[y].inputsAndOutputs();
                    gates[y].createLogicGate(mainJPanel);
                    y++;
                }

            }
            //Redrawing the gate regularly
            else{
                gates[y] = newGateArray[i];
                gates[y].createLogicGate(mainJPanel);
                y ++;
            }
        }
        
        //Changed numgates accordingly
        if(moveOption == false){
            if(numGates != y)
                numGates --;
        }
        
        //INPUTS
        y = 0;
        for (int i = 0; i < numSwitches; i++) {
            if(type.equals("Switch") && i == x){
                //Moves
                if(moveOption == true){
                    switches[y] = newSwitchArray[i];
                    switches[y].inputsAndOutputs();
                    switches[y].createSwitch(mainJPanel);
                    y++;
                }
            }
            //Regular
            else{
                switches[y] = newSwitchArray[i];
                switches[y].createSwitch(mainJPanel);
                y++;
            }
        }
        
        //Resets number of switches accordingly
        if(moveOption == false){
            if(numSwitches != y)
                numSwitches --;
        }
        
        //OUTPUTS
        y = 0;
        for (int i = 0; i < numOutputs; i++) {
            if(type.equals("Output") && i == x){
                //Moves
                if(moveOption == true){
                    outputs[y] = newOutputArray[i];
                    outputs[y].inputsAndOutputs();
                    outputs[y].createOutput(mainJPanel);
                    y++;
                }
            }
            //Regular
            else{
                outputs[y] = newOutputArray[i];
                outputs[y].createOutput(mainJPanel);
                y++;
            }
        }
        
        //Sets number of outputs accordingly
        if(moveOption == false){
            if(numOutputs != y)
                numOutputs --;
        }
        
        //Though the user cannot move wires, they also need to be redrawn.
        //WIRES
        y = 0;
        for (int i = 0; i < numWires; i++) {
            wires[i] = newWireArray[i];
            wires[i].createWireImage(mainJPanel);
            
        }
        
        //Resets type
        if(removingComponent == true)
            type = "";
    }
    
    public void movingComponent(double x, double y){

        if(type.equals("Gate")){
            gates[toBeMoved].x1 = x;
            gates[toBeMoved].y1 = y;

        }
        
        else if(type.equals("Switch")){

            switches[toBeMoved].x1 = x;
            switches[toBeMoved].y1 = y;

        }

        else if(type.equals("Output")){

            outputs[toBeMoved].x1 = x;
            outputs[toBeMoved].y1 = y;

        }

//        else if(type.equals("Wire")){
//
//            wires[toBeMoved].xStart = x;
//            wires[toBeMoved].yStart = y;
//            wires[toBeMoved].createWireImage(jPanel2);
//
//        }
        
        
    }
    
    //This procedure draws the help component on the second jPanel
    //Basically displays what you are going to be placing
    public void drawHelpComponent(JPanel j){
        
        //Sets the moveOptions and removeModes to false to remove any possible errors
        moveOption = false;
        moveModeOption.setSelected(false);
        removingComponent = false;
        removeModeOption.setSelected(false);
        
        //Draws components
        Graphics2D g = (Graphics2D) j.getGraphics();
        draw(g);
        
        
    }
    
    //Draws the actual "help" component on the second jPanel
    //Draws the object in the very middle of the jPanel
    public void draw(Graphics2D g){
        
        //Resets the jPanel
        g.setColor(greyBox);
        g.fillRect(0,0,360,360);
        
        //GATES
        if(addingLogicGate == true){
            
            g.setColor(Color.black);
            g.drawRect( (int)120, (int)120, (int)100, (int)100);
            g.setColor(Color.green);
            g.drawRect( (int)120 + 100/8, (int)120 + 100/4, (int)15, (int)15);
            g.drawRect( (int)120 + 100/8, (int)120 + 100/2, (int)15, (int)15);
            g.setColor(Color.red);
            g.drawRect( (int)120 + 100 -(100/5), (int)120 + 100/4, (int)15, (int)15);
            g.drawString(gateType, (int) 120 + 38, (int) 120 + 50);
        }
        
        //INPUTS
        else if(addingSwitch == true){
            
            g.setColor(Color.red);
            g.fillRect( (int)140, (int)140, (int)widthSwitch, (int)heightSwitch);
            g.drawRect( (int)140+(int)widthSwitch+10, (int)140+(int)heightSwitch/2, (int)25, (int)25);
            
        }
        
        //OUTPUTS
        else if(addingOutput == true){
            
            g.setColor(Color.red);
            g.fillRect( (int)140, (int)140, (int)widthOutput, (int)heightOutput);
            g.drawRect( (int)140 - (int)widthOutput/2 - 10, (int)140 + (int)heightOutput/2, (int)25, (int)25);
            
        }
        
    }
    
    //All events based on actions taken from GUI options given.
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jScrollPane2 = new javax.swing.JScrollPane();
        testArea = new javax.swing.JTextArea();
        mainJPanel = new javax.swing.JPanel();
        secondaryJPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        addGateAND = new javax.swing.JMenuItem();
        addGateOR = new javax.swing.JMenuItem();
        addGateXOR = new javax.swing.JMenuItem();
        addGateNAND = new javax.swing.JMenuItem();
        addGateNOR = new javax.swing.JMenuItem();
        addGateXNOR = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        addOutput = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        addSwitch = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        moveModeOption = new javax.swing.JCheckBoxMenuItem();
        removeModeOption = new javax.swing.JCheckBoxMenuItem();
        jMenu3 = new javax.swing.JMenu();
        clearScreen = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        testArea.setColumns(20);
        testArea.setRows(5);
        testArea.setText("Welcome to the Logic Gate Simulator!");
        jScrollPane2.setViewportView(testArea);

        mainJPanel.setBackground(new java.awt.Color(153, 153, 153));
        mainJPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanelMouseDragged(evt);
            }
        });
        mainJPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mainJPanelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout mainJPanelLayout = new javax.swing.GroupLayout(mainJPanel);
        mainJPanel.setLayout(mainJPanelLayout);
        mainJPanelLayout.setHorizontalGroup(
            mainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 938, Short.MAX_VALUE)
        );
        mainJPanelLayout.setVerticalGroup(
            mainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        secondaryJPanel.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout secondaryJPanelLayout = new javax.swing.GroupLayout(secondaryJPanel);
        secondaryJPanel.setLayout(secondaryJPanelLayout);
        secondaryJPanelLayout.setHorizontalGroup(
            secondaryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 355, Short.MAX_VALUE)
        );
        secondaryJPanelLayout.setVerticalGroup(
            secondaryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );

        jMenu1.setText("Add Components");

        jMenu4.setText("Add Logic Gate");

        addGateAND.setText("AND");
        addGateAND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGateANDActionPerformed(evt);
            }
        });
        jMenu4.add(addGateAND);

        addGateOR.setText("OR");
        addGateOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGateORActionPerformed(evt);
            }
        });
        jMenu4.add(addGateOR);

        addGateXOR.setText("XOR");
        addGateXOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGateXORActionPerformed(evt);
            }
        });
        jMenu4.add(addGateXOR);

        addGateNAND.setText("NAND");
        addGateNAND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGateNANDActionPerformed(evt);
            }
        });
        jMenu4.add(addGateNAND);

        addGateNOR.setText("NOR");
        addGateNOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGateNORActionPerformed(evt);
            }
        });
        jMenu4.add(addGateNOR);

        addGateXNOR.setText("XNOR");
        addGateXNOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGateXNORActionPerformed(evt);
            }
        });
        jMenu4.add(addGateXNOR);

        jMenu1.add(jMenu4);

        jMenu5.setText("Add Output");

        addOutput.setText("Basic Output");
        addOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOutputActionPerformed(evt);
            }
        });
        jMenu5.add(addOutput);

        jMenu1.add(jMenu5);

        jMenu6.setText("Add Input");

        addSwitch.setText("Basic Switch");
        addSwitch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSwitchActionPerformed(evt);
            }
        });
        jMenu6.add(addSwitch);

        jMenu1.add(jMenu6);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Component Options");

        moveModeOption.setSelected(false);
        moveModeOption.setText("Toggle Move Mode");
        moveModeOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveModeOptionActionPerformed(evt);
            }
        });
        jMenu2.add(moveModeOption);

        removeModeOption.setSelected(false);
        removeModeOption.setText("Toggle Remove Mode");
        removeModeOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeModeOptionActionPerformed(evt);
            }
        });
        jMenu2.add(removeModeOption);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Screen Options");

        clearScreen.setText("Clear Screen");
        clearScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearScreenActionPerformed(evt);
            }
        });
        jMenu3.add(clearScreen);

        jMenuBar1.add(jMenu3);

        jMenu8.setText("Toolbars");

        jMenuItem3.setText("Quick-Option Toolbar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem3);

        jMenuBar1.add(jMenu8);

        jMenu7.setText("Help");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");
        jMenu7.add(jRadioButtonMenuItem1);

        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(secondaryJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(secondaryJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelMouseClicked
        
        
    }//GEN-LAST:event_jPanelMouseClicked

    private void jPanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelMouseReleased
        
        //Used to determine if the user is over a switch or not
        boolean overSwitch = false;
        
        //This forloop determines whether the user is over a switch when their mouse is clicked, meaning that
        //when the user clicks over an input, the input will change for that specific input and the boolean
        //statement "overSwitch" will equal true
        for (int i = 0; i < numSwitches; i++) {
            if( evt.getX() > switches[i].x1 && evt.getX() < switches[i].x1 + switches[i].width &&
                evt.getY() > switches[i].y1 && evt.getY() < switches[i].y1 + switches[i].height ){
                overSwitch = true; 
            }
        }
        //If ALL boolean statements are false, the user can then add a wire to the screen.
        //Creates a wire depending on the users location when the first clicked the mouse to when they released it.
        if(addingLogicGate == false && addingSwitch == false && addingOutput == false && removingComponent == false && moveOption == false && overSwitch == false){
            wireEndx = evt.getX();
            wireEndy = evt.getY();
            
            //Creating the wire
            wires[numWires] = new Wire(wireStartx, wireStarty, wireEndx, wireEndy);
            wires[numWires].createWireImage(mainJPanel);
            
            numWires ++;

        }
        
        //If statements regarding toggling of options, adding of objects, etc.
        //If a boolean is true and under the category "adding", it will be changed to
        //False and the number of that specific object will increase.
        //If a boolean is true and under the category "toggle" it will repaint
        //the jPanel according to the index given to it
        if(removingComponent == true){
            repaintJPanel(toBeDeleted);
        }   
        if(moveOption == true){           
            repaintJPanel(toBeMoved);           
        }      
        if(addingLogicGate == true){          
            numGates ++;
            addingLogicGate = false;          
        }      
        if(addingSwitch == true){          
            numSwitches ++;
            addingSwitch = false;          
        }      
        if(addingOutput == true){            
            numOutputs ++;
            addingOutput = false;
        }
        
        //If statements called to determine the connection status of components
        //Certain conditions must be met for each procedure to be called, as 
        //the program does not want to check gatewires when there is no gates in the
        //first place.
        if(numSwitches >= 1 && numWires >= 1){
            checkWiresWithSwitches();
        }
        if(numGates >= 1 && numWires >= 2){
            checkSwitchWiresWithGates();
        }
        if(numGates >= 1 && numWires >= 1){
            checkGateWires();
        }
        if(numGates >= 1 && numOutputs >= 1 && numWires >= 2 && numSwitches >= 2){
            checkGateWiresWithOutput();
        }
        
        
        

    }//GEN-LAST:event_jPanelMouseReleased

    private void jPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelMouseDragged
        
        //MouseDragging is used for creating wire, however it is officially used to move components
        //While the user drags the component, the jPanel clears, updates, the component moves
        //and the overall jPanel gets redrawn for every drag.
        if(moveOption == true){

            mainJPanel.removeAll();
            mainJPanel.updateUI();
            
            movingComponent(evt.getX(), evt.getY());
            repaintJPanel(toBeMoved);
            
        }
        
    }//GEN-LAST:event_jPanelMouseDragged

    private void mainJPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainJPanelMousePressed

        //If ALL boolean statements are false, the user may create a wire.
        if(addingLogicGate == false && addingSwitch == false && addingOutput == false && moveOption == false && removingComponent == false){
            
            wireStartx = evt.getX();
            wireStarty = evt.getY();
            
            //Also checks if the user is over a switch
            //If switches[i] = 0 then break. 0 in an array of integers means
            //That the array is not filled at this time.
            for (int i = 0; i < numSwitches; i++) {
                if(switches[i].x1 == 0){

                    break;

                }
                
                //If the user is over a switch, it changes colour and thus the output.
                //It then recreates the switch object
                else{
                    if( evt.getX() > switches[i].x1 && evt.getX() < switches[i].x1 + switches[i].width &&
                        evt.getY() > switches[i].y1 && evt.getY() < switches[i].y1 + switches[i].height){
                        
                        //Change to red if the switch is already on
                        if(switches[i].output == true){
                            switches[i].output = false;
                            switches[i].color = Color.red;

                        }
                        //Change to green if the switch is already off
                        else{
                            switches[i].output = true;
                            switches[i].color = Color.green;

                        }
                        
                        //Recreate the switch
                        switches[i].createSwitch(mainJPanel);
                    }
                }      
            }
        }

        else{
            
            //If the user is adding a component, program will then determine what component they are adding
            //The user will be able to place the component directly where the click (That is, the middle of the component 
            //Will be where the user designated it goes.
            //It also determines if the user is asking to remove a specific component from the jPanel.
            
            double middlex = evt.getX();
            double middley = evt.getY();
            
            //GATE
            if(addingLogicGate == true){
                gateCornerx1 = middlex - gateWidth/2;
                gateCornery1 = middley - gateHeight/2;

                gates[numGates] = new Gate(gateType, gateCornerx1, gateCornery1, gateWidth, gateHeight);

                gates[numGates].createLogicGate(mainJPanel);
            }
            //INPUT
            else if(addingSwitch == true){
                switchx1 = middlex - widthSwitch/2;
                switchy1 = middley - heightSwitch/2;

                switches[numSwitches] = new Switch(switchx1, switchy1, widthSwitch, heightSwitch);

                switches[numSwitches].createSwitch(mainJPanel);
                
                
            }
            //OUTPUT
            else if(addingOutput == true){
                outputx1 = middlex - widthOutput/2;
                outputy1 = middley - heightOutput/2;

                outputs[numOutputs] = new Output(outputx1, outputy1, widthOutput, heightOutput);

                outputs[numOutputs].createOutput(mainJPanel);
                
            }
            //RE/MOVING Components
            //Checks if the user is adding or removing a specific component, and
            //Checks the components to determine which one the user wants to move
            //Or delete from the screen.
            else if(removingComponent == true || moveOption == true){
                
                if(removingComponent == true)
                    toBeDeleted = checkAllComponents(middlex, middley);
                
                else
                    toBeMoved = checkAllComponents(middlex, middley);
                
                mainJPanel.removeAll();
                mainJPanel.updateUI();
                
            }
        }
        
    }//GEN-LAST:event_mainJPanelMousePressed
    
    //These are all gate adding event calls
    //All these have three key aspects
    //addingLogicGate = true
    //gateType = whatever type selected
    //drawHelpComponent, which is explained above. 
    //These calls also set the text of the jTextPanel accordingly.
    private void addGateORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGateORActionPerformed
       
        addingLogicGate = true;
        gateType = "OR";
        
        drawHelpComponent(secondaryJPanel);
        
    }//GEN-LAST:event_addGateORActionPerformed

    private void addGateANDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGateANDActionPerformed
        
        
        addingLogicGate = true;
        gateType = "AND";
        
        drawHelpComponent(secondaryJPanel);
        
    }//GEN-LAST:event_addGateANDActionPerformed

    private void addGateXORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGateXORActionPerformed
        
        addingLogicGate = true;
        gateType = "XOR";
        
        testArea.setText(gateMessage + helpMessage);
        drawHelpComponent(secondaryJPanel);
      
    }//GEN-LAST:event_addGateXORActionPerformed

    private void addGateNANDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGateNANDActionPerformed
        
        addingLogicGate = true;
        gateType = "NAND";
        
        testArea.setText(gateMessage + helpMessage);
        drawHelpComponent(secondaryJPanel);
      
    }//GEN-LAST:event_addGateNANDActionPerformed

    private void addGateNORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGateNORActionPerformed
        
        addingLogicGate = true;
        gateType = "NOR";
        
        testArea.setText(gateMessage + helpMessage);
        drawHelpComponent(secondaryJPanel);
      
    }//GEN-LAST:event_addGateNORActionPerformed

    private void addGateXNORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGateXNORActionPerformed
        
        addingLogicGate = true;
        gateType = "XNOR";
        
        testArea.setText(gateMessage + helpMessage);
        drawHelpComponent(secondaryJPanel);
      
    }//GEN-LAST:event_addGateXNORActionPerformed

    //These next two are adding outputs and adding inputs.
    //Basically sets the boolean statement, the textArea, and 
    //Draws the help component
    private void addOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOutputActionPerformed
        
        addingOutput = true;
        
        testArea.setText(gateMessage + helpMessage);
        drawHelpComponent(secondaryJPanel);
        
    }//GEN-LAST:event_addOutputActionPerformed

    private void addSwitchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSwitchActionPerformed
        
        addingSwitch = true;
        
        testArea.setText(gateMessage + helpMessage);
        drawHelpComponent(secondaryJPanel);
        
    }//GEN-LAST:event_addSwitchActionPerformed
    
    
    private void clearScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearScreenActionPerformed
        
        //This event call resets the following:
        //Object arrays, numVariables, and specific boolean statements.
        //It also repaints the jPanels accordignly.
        gates = new Gate[50];
        wires = new Wire[50];
        switches = new Switch[50];
        outputs = new Output[50];
        
        numGates = 0;
        numWires = 0;
        numSwitches = 0;
        numOutputs = 0;
        
        addingLogicGate = false;
        addingSwitch = false;
        addingOutput = false;
        
        mainJPanel.removeAll();
        mainJPanel.updateUI();
        
    }//GEN-LAST:event_clearScreenActionPerformed

    private void moveModeOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveModeOptionActionPerformed
        //This event call is selected when the user toggles on moveMode
        //This allows the user to move components on the screen wherever they want.
        //In order to do this, the moveOption toggle, boolean must be set to true
        //as well as the removeComponent option set to the opposite boolean statements.
        
        if(moveOption == false){
            if(removingComponent == true){
                removingComponent = false;
                removeModeOption.setSelected(false);
                
            }
            moveModeOption.setSelected(true);
            moveOption = true;
        }
        else{
            moveModeOption.setSelected(false);
            moveOption = false;
        }
        
    }//GEN-LAST:event_moveModeOptionActionPerformed

    private void removeModeOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeModeOptionActionPerformed
        //This event call is selected when the user toggles on removeComponent
        //This allows the user to remove any component on the jPanel
        //In order to do this, the removeComponent toggle, boolean must be set to true
        //as well as the removeComponent option set to the opposite boolean statements.
        if(removingComponent == false){
            if(moveOption == true){
                moveOption = false;
                moveModeOption.setSelected(false);
                
            }
            removeModeOption.setSelected(true);
            removingComponent = true;
        }
        else{
            removeModeOption.setSelected(false);
            removingComponent = false;
        }
        
    }//GEN-LAST:event_removeModeOptionActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        
        //Turns on the toolbar
        new AddingComponentToolBar().setVisible(true);
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    //MAIN
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LogicGateGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogicGateGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogicGateGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogicGateGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //RUN
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogicGateGUI().setVisible(true);
                
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addGateAND;
    private javax.swing.JMenuItem addGateNAND;
    private javax.swing.JMenuItem addGateNOR;
    private javax.swing.JMenuItem addGateOR;
    private javax.swing.JMenuItem addGateXNOR;
    private javax.swing.JMenuItem addGateXOR;
    private javax.swing.JMenuItem addOutput;
    private javax.swing.JMenuItem addSwitch;
    private javax.swing.JMenuItem clearScreen;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel mainJPanel;
    public static javax.swing.JCheckBoxMenuItem moveModeOption;
    public static javax.swing.JCheckBoxMenuItem removeModeOption;
    private javax.swing.JPanel secondaryJPanel;
    private static javax.swing.JTextArea testArea;
    // End of variables declaration//GEN-END:variables
}
