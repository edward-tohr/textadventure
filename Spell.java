//import java.util.Scanner;
//import java.util.Vector;


public class Spell {
	int cost = 0;
	int power = 0;
	byte learn = 0b00000000;
	int level = 0;
	int element = 0; //0 is non-elemental, 1 for fire, 2 for water, 3 for earth, 4 for lightning, 5 for healing, 6 for disposition modification, 7 for respect, 8 for fear, 9 for hatred.
	String name = "NotASpell";
	String desc = "Not a spell";
	String ani = "Nothing happens.";
	

	public Spell (int c, int p, byte l, int lv, int e){
		cost = c;
		power = p;
		learn = l;
		level = lv;
		element = e;
	}
	
	public Spell() {
		
	}
	
	

	
}
