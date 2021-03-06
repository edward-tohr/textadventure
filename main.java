//TODO: Make player.role and associated variables enums? Maybe not,
//	since Java enums don't like to be coerced to ints.

//TODO: Rewrite Mob.java, especially combat() and talk(), since they
//	still output to, and expect input from, the console.

//TODO: Have Mobs, Spells, Rooms, Items, etc. parse from a file instead
//	of hardcoding everything.

//TODO: Have Rooms, Players, Mobs, etc. save info to a file.

//TODO: Have a better way to handle multiple copies of an item/mob in
//	the same room.

//TODO: Maybe make the player's inventory not depend on the contents
//	of a specific room?

//TODO: Maybe make quit() just exit gracefully, instead of teleporting
//	the player to a specific room?

//import java.util.Scanner;
//import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;

import java.lang.String;
public class main {
	public static boolean resizable = false;
	public static boolean quitting = false;
	public static boolean widescreen = false;
	public final static boolean DEBUG = true;
	public final static String SAVEPATH = "./javagame/save/";
	public boolean alive = true;
	public static JTextField entryField = new JTextField();
	public static JTextArea textWindow = new JTextArea();
	public static JScrollPane gameWindow = new JScrollPane(textWindow);
	public static JFrame frame = new JFrame("Text Adventure");
	public final static int ENTER = KeyEvent.VK_ENTER;
	public static int OUT_WIDTH = 90;
	public static Rooms loc = new Rooms();
	public static Player pl = new Player();
	public static Items it = new Items();
	public static Mobs mo = new Mobs();
	public static Magic mag = new Magic();
	public static Room r = new Room();
	public static Skills skl = new Skills();
	public static Parser p = new Parser();
	public static String input = "null";
	public static Font normalFont = new Font("Courier New",Font.BOLD,12);
	public final static int FONT_WIDTH = 7; //Yay for having to manually update this if I ever want to change the font.

	
	//A single function to handle all text output. Should make un-consoleing it easier later on.
	public static void out (String outtext){ 
		//System.out.print(outtext);
		textWindow.append(outtext);
		textWindow.repaint();
		
		
	}
	public static void output (String outtext){ 
	if (outtext.length() <= OUT_WIDTH-1){
		out(outtext);
		out("\n");
	} else {
		int lastSpace = outtext.lastIndexOf(' ',OUT_WIDTH-1);
		if (lastSpace <= 0){
		lastSpace = OUT_WIDTH-1;
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
					out(args[0] + " is not a number! Defaulting to 90.");
					OUT_WIDTH = 90;
					}
				}
		//JScrollPane scrollPane = new JScrollPane(textWindow);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
		int width = (OUT_WIDTH+1) * FONT_WIDTH;
		width += 10;
		int height = width * 3;
		height /=4;
		try{
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		}
		 catch (UnsupportedLookAndFeelException e) {
       // handle exception
    }
    catch (ClassNotFoundException e) {
       // handle exception
    }
    catch (InstantiationException e) {
       // handle exception
    }
    catch (IllegalAccessException e) {
       // handle exception
    }
		frame.setSize(width + 20, height + 20);
		resizable = false;
		textWindow.setSize(width, height);
		resizable = false;
		textWindow.setMargin(new Insets(5,5,5,5));
		entryField.setMargin(new Insets(1,5,1,0));
		textWindow.setLocation(0, 0);
		textWindow.setEditable(false);
		textWindow.setLineWrap(true);
		textWindow.setWrapStyleWord(true);
		textWindow.setVisible(true);
		entryField.setSize(width,20);
		resizable = false;
		entryField.addKeyListener(new KeyListen());
		textWindow.setFont(normalFont);
		entryField.setFont(normalFont);
		//gameWindow.add(textWindow);
	//	gameWindow.setVisible(true);
	//	frame.add(textWindow);
	//	frame.add(textField);
		frame.add(gameWindow,BorderLayout.CENTER);
		frame.add(entryField,BorderLayout.PAGE_END);
		DefaultCaret caret = (DefaultCaret)textWindow.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		entryField.setText("help");
        frame.setVisible(true);
	entryField.requestFocusInWindow();
	entryField.selectAll();
		
		//Scanner scanner = new Scanner( System.in );


		
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

		frame.addComponentListener(new ComponentListen());


		p.Parse("look", loc, r,pl,it, mo, mag, skl);

		//Runs Parser in a loop until user types "Quit"
		/*do {  
			//input = scanner.nextLine();

			try {
				r = p.Parse(input, loc, r, pl, it, mo, mag, skl);
			} catch(IllegalArgumentException e){
				System.err.println("IllegalArgumentException: " + e.getMessage());
			}
			//main.output(r);
		} while (r != loc.roomVector.elementAt(0));//while (!input.equalsIgnoreCase("quit"));
*/
	}



	public static void output(int outtext) {
		//System.out.println(outtext);
		output(Integer.toString(outtext));
		
	}

	public static String getInput(){
		//entryField.setText("");
		//if (
		return entryField.getText();
	}
}

class KeyListen extends KeyAdapter {
public void keyTyped(KeyEvent e){
if (e.getKeyChar() == '\n'){
if (!main.quitting){
main.output("> " + main.entryField.getText());
main.r = main.p.Parse(main.entryField.getText(),main.loc,main.r,main.pl,main.it,main.mo,main.mag,main.skl);
main.entryField.setText("");
if (main.r == main.loc.roomVector.elementAt(0)){
main.entryField.setEditable(false);
main.quitting = true;
}
} else {
System.exit(0);
}
}
}
}

class ComponentListen implements ComponentListener {
public void componentResized(ComponentEvent e){
if (main.resizable){
int newwidth = e.getComponent().getWidth();
newwidth -= 11;
newwidth /= 7;
main.p.Parse("cols " + Integer.toString(newwidth),main.loc, main.r,main.pl, main.it, main.mo, main.mag, main.skl);
main.resizable = false;
}
else {
main.resizable = true;
}
}
public void componentHidden(ComponentEvent e){}
public void componentMoved(ComponentEvent e){}
public void componentShown(ComponentEvent e){}
}


