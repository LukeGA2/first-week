//comes from Internet

import java.awt.*;
import javax.swing.*;
public class Simulator extends JApplet implements ElevatorSim, Runnable {

	private int totalPeople;     // Number of people handled
	private int inBuilding;      // People in building now
	private int leftBuilding;    // People who left building
	private int avgWait;         // Average no. people waiting
	private int avgRide;         // Average no. people in elevators
	private int tookStair;       // Number people who walked
	private int totalTime;       // Seconds simulation has run
	Building theBuilding;	  // Building simulation object
	Action   theAction;    //  Action object
	GridBagLayout	theLayout;
	GridBagConstraints theConstraint;
	Thread runner;
   boolean keepRunning;
	private JLabel totalPeopleLabel;
	private JLabel inBuildingLabel;
	private JLabel leftBuildingLabel;
	private JLabel avgWaitLabel;
	private JLabel avgRideLabel;
	private JLabel tookStairLabel;
	private JLabel timeRemainingLabel;
	private JLabel totalTimeLabel;
   Container theContainer;
   Insets inset;
	haha
	public void init() {
      inset = new Insets(0,2,0,2);
      theContainer = getContentPane();
      theContainer.setBackground(new Color(255,255,255));
		theContainer.setFont(new Font("Dialog",Font.PLAIN,9));
		theLayout = new GridBagLayout();
		theConstraint = new GridBagConstraints();
      theConstraint.insets = inset;
		theContainer.setLayout(theLayout);
		totalPeopleLabel = new JLabel("0000");
		inBuildingLabel = new JLabel("0000");
		leftBuildingLabel = new JLabel("0000");
		avgWaitLabel = new JLabel("0000");
		avgRideLabel = new JLabel("0000");
		tookStairLabel = new JLabel("0000");
		timeRemainingLabel = new JLabel("0000");
		totalTimeLabel = new JLabel("0000");
		constrain(this, new JLabel("Floors"), 0, 0, 1, 1);
		//System.out.println(getFont().getName());
		//System.out.println(getFont().getSize());
		
		theAction  = new Action(this);
		theBuilding = new Building(this);
		
		
		constrain(this, new JLabel("Total"), 0, -1, 1, 1);
		constrain(this, new JLabel("People"), 0, -1, 1, 1);
		constrain(this, new JLabel("In"),1, -1, 1,1);
		constrain(this, new JLabel("Bldg"),1, -1, 1,1);
		constrain(this, new JLabel("Left"), 2, -1, 1, 1);
		constrain(this, new JLabel("Bldg"), 2, -1, 1, 1);
		constrain(this, new JLabel("Avg #"), 3, -1, 1, 1);
		constrain(this, new JLabel("Waiting"), 3, -1, 1, 1);
		constrain(this, new JLabel("Avg #"), 4, -1, 1, 1);
		constrain(this, new JLabel("Riding"), 4, -1, 1, 1);
		constrain(this, new JLabel("Took"), 5, -1, 1, 1);
		constrain(this, new JLabel("Stair"), 5, -1, 1, 1);
		constrain(this, new JLabel("Num"), 6, -1, 1, 1);
		constrain(this, new JLabel("Elv"), 6, -1, 1, 1);
		constrain(this, new JLabel("Num"), 7, -1, 1, 1);
		constrain(this, new JLabel("Flr"), 7, -1, 1, 1);
		constrain(this, new JLabel("Seconds"), 8, -1, 1, 1);
		constrain(this, new JLabel("Left"), 8, -1, 1, 1);
		constrain(this, new JLabel("Elapsed"), 9, -1, 1, 1);
		constrain(this, new JLabel("Time"), 9, -1, 1, 1);
		constrain(this, totalPeopleLabel, 0, -1, 1, 1);
		constrain(this, inBuildingLabel, 1, -1, 1, 1);
		constrain(this, leftBuildingLabel, 2, -1, 1, 1);
		constrain(this, avgWaitLabel, 3, -1, 1, 1);
		constrain(this, avgRideLabel, 4, -1, 1, 1);
		constrain(this, tookStairLabel, 5, -1, 1, 1);
		constrain(this, new JLabel(String.valueOf(MAXELEVS)), 6, -1, 1, 1);
		constrain(this, new JLabel(String.valueOf(MAXFLOORS)), 7, -1, 1, 1);
		constrain(this, timeRemainingLabel, 8, -1, 1, 1);
		constrain(this, totalTimeLabel, 9, -1, 1, 1);
	}
	public void start() {
		if (runner == null); {
          keepRunning = true;
			runner = new Thread(this);
			runner.start();
		}
	}
	public void stop() {
		if (runner != null); {
         keepRunning = false;
			//runner.stop();
			runner = null;
      }
	}
	
	public void run() {

	/* -- This while-loop handles the entire simulation. It cycles
	while the building object (named theAction) returns true through
	its `continues()' function. */
	int i = 0;
	while (keepRunning && theBuilding.continues()) {
		try { 
         Thread.sleep(250);
      }catch (InterruptedException e) {}
		//System.out.println("Round: " + i++ + "\n");
		theBuilding.perform(); // Perform all simulation actions
		theBuilding.display(); // Update the display
		validate();
	}
	//System.out.println("****************** Done ***************" + "\n");

   }
	public void constrain(Simulator sim, JLabel lb, int x ,int y, int height, int width) {
		theConstraint.gridheight = height;
		theConstraint.gridwidth = width;
		theConstraint.anchor = GridBagConstraints.NORTH;
		sim.theConstraint.gridx = x;
		sim.theConstraint.gridy = y;
		sim.theLayout.setConstraints(lb, sim.theConstraint);
		theContainer.add(lb);
	}
	public void totalPeoplePlus(int increase) { 
		totalPeople += increase;
		totalPeopleLabel.setText(String.valueOf(totalPeople));
	}
	public void totalPeopleMinus(int decrease) {
		totalPeople -= decrease;
		totalPeopleLabel.setText(String.valueOf(totalPeople));
	}
	public void inBuildingPlus(int increase) {
		inBuilding += increase;
		inBuildingLabel.setText(String.valueOf(inBuilding));
	}
	public void inBuildingMinus(int decrease) {
		inBuilding -= decrease;
		inBuildingLabel.setText(String.valueOf(inBuilding));
	}
	public void tookStairPlus(int increase) {
		tookStair += increase;
		tookStairLabel.setText(String.valueOf(tookStair));
	}
	public void tookStairMinus(int decrease) {
		tookStair -= decrease;
		tookStairLabel.setText(String.valueOf(tookStair));
	}
	public void leftBuildingPlus(int increase) {
		leftBuilding += increase;
		leftBuildingLabel.setText(String.valueOf(leftBuilding));
	}
	public void totalTimePlus(int increase) {
		totalTime += increase;
		totalTimeLabel.setText(String.valueOf(totalTime));
	}
	public void avgWaitSet(int avg) {
		avgWait = avg;
		avgWaitLabel.setText(String.valueOf(avgWait));
	}
	public void avgRideSet(int avg) {
		avgRide = avg;
		avgRideLabel.setText(String.valueOf(avgRide));
	}
	public void timeRemainingDisp(int time) {
		timeRemainingLabel.setText(String.valueOf(time));
	}
}
