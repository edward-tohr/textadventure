//TODO: Lots of stuff needs to be done with the window, but I'll settle for making it close
//	automatically when the user quits. Eventually.

//TODO: Make player.role and associated variables enums.

import java.util.Scanner;
//import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;

import java.lang.String;

public class main {
	public final static boolean DEBUG = true;
	public final static String SAVEPATH = "./javagame/save/";
	public boolean alive = true;
	public static JTextField textField = new JTextField();
	public static JTextArea textWindow = new JTextArea();
	public static JScrollPane gameWindow = new JScrollPane(textWindow);
	public static JFrame frame = new JFrame("Text Adventure");
	public final static int ENTER = KeyEvent.VK_ENTER;
	public static int OUT_WIDTH = 80;
	public static int outCol = 0;
	

	
	//A single function to handle all text output. Should make un-consoleing it easier later on.
	public static void out (String outtext){ 
		System.out.print(outtext);
		textWindow.append(outtext);
		
		
	}
	public static void output (String outtext){ 
	outCol = 0;
	if (outtext.length() <= OUT_WIDTH){
		out(outtext);
		if (outtext.length() < OUT_WIDTH){
		out("\n");
		}
	} else {
		int lastSpace = outtext.lastIndexOf(' ',OUT_WIDTH);
		if (lastSpace <= 0){
		lastSpace = OUT_WIDTH;
		}
		StringBuffer outString = new StringBuffer(outtext.substring(0,lastSpace));
		output(outString.toString());
		output(outtext.substring(lastSpace+1));


	}
	}
	
	public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == ENTER){
           
        }
     }
	public static void main (String[] args) {
		if (args.length > 0){

			try {
				OUT_WIDTH= Integer.parseInt(args[0]);
				} catch (NumberFormatException e){
					out(args[0] + " is not a number! Defaulting to 80.");
					OUT_WIDTH = 80;
					}
				}
		//JScrollPane scrollPane = new JScrollPane(textWindow);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
		frame.setSize(660, 500);
		textWindow.setSize(640, 480);
		textWindow.setLocation(0, 0);
		textWindow.setEditable(false);
		textWindow.setLineWrap(true);
		textWindow.setWrapStyleWord(true);
		textWindow.setVisible(true);
		textField.setBounds(0, 480, 640, 20);
		//gameWindow.add(textWindow);
	//	gameWindow.setVisible(true);
	//	frame.add(textWindow);
	//	frame.add(textField);
		frame.add(gameWindow);
		DefaultCaret caret = (DefaultCaret)textWindow.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        frame.setVisible(true);
		
		Scanner scanner = new Scanner( System.in );
		String input = null;
		Rooms loc = null; 
		Player pl = null;
		Items it = null;
		Mobs mo = null;
		Magic mag = null;
		Room r = null;
		Skills skl = null;
		Parser p = new Parser();

		
			loc = new Rooms();	
			pl = new Player();
			it = new Items();
			mo = new Mobs();
			mag = new Magic();
			skl = new Skills();
			loc.initalizeRooms();
			it.initializeItems();
			mo.initializeMobs();
			mo.populateMobs(loc);
			mag.initalizeSpells();
			skl.initializeSkills();


			it.populateItems(loc, pl);
			if (!main.DEBUG){
				pl.charGen();
				Room inv = loc.getRoomFromName("Inventory");
				if (pl.role == 0b00000001)
					it.getEquipFromName("longsword").move(inv);
				if (pl.role == 0b00000010)
					it.getEquipFromName("dagger").move(inv);
				if (pl.role == 0b00000100)
					it.getEquipFromName("hammer").move(inv);
				if (pl.role == 0b00001000)
					it.getEquipFromName("staff").move(inv);
				if (pl.role == 0b00010000)
					it.getEquipFromName("flail").move(inv);
				if (pl.role == 0b00100000)
					it.getEquipFromName("rapier").move(inv);
			}
			if (main.DEBUG) {
				pl.gold = 65535;
			}
			it.setEquippable(pl);

			r = loc.getRoom(1);

		//ASCII art title!
		out(" ###  ####  #   # ##### #   # #####\n#   # #   # #   # #     ##  #   #  \n##### #   # #   # ###   # # #   #  \n#   # #   #  # #  #     #  ##   #  \n#   # ####    #   ##### #   #   #  \n"); 
		out("\n A text adventure by Jeff Morse.\nType \"quit\" to exit, and 'help' to get help.\n");

		p.Parse("look", loc, r,pl,it, mo, mag, skl);

		//Runs Parser in a loop until user types "Quit"
		do {  
			input = scanner.nextLine();

			try {
				r = p.Parse(input, loc, r, pl, it, mo, mag, skl);
			} catch(IllegalArgumentException e){
				System.err.println("IllegalArgumentException: " + e.getMessage());
			}
			//main.output(r);
		} while (r != loc.roomVector.elementAt(0));//while (!input.equalsIgnoreCase("quit"));

	}



	public static void output(int outtext) {
		System.out.println(outtext);
		
	}


}
