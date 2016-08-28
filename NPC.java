import java.util.Vector;

public class NPC extends Mob {
	String[] inventory; // For shopkeepers, this determines what they sell. 
	String input;
	char choice;
	int num;
	boolean tempBool = false;
	public NPC () {

	}


	public void speak(NPC npc, Player p, Items i, Rooms loc, Mobs mob, Magic mag, Room r, Skills skl) {
		String[] tempSpeech;

		tempSpeech = npc.getSpeech(); //Default -> high dispostion/respect -> low disposition/high respect -> high dispostion/low respect -> high hatred -> low res + dis -> high fear
		if (npc.dis >= 75 && npc.res >= 75) {
			tempSpeech = npc.getLike();
		}
		if (npc.dis <=25 && npc.res >= 75) {
			tempSpeech = npc.getPolite();
		}
		if (npc.res <= 75 && npc.res < 75) {
			tempSpeech = npc.getRespect();
		}
		if (npc.hate >= 75) {
			//Not sure why I had these two in here?
			//int tempInt = (npc.dis + npc.res)/2;
			//tempInt -= npc.hate;
			if (npc.hate > npc.fear){
				tempSpeech = npc.getEngage();
				tempBool = true;
			} else{
			tempSpeech = npc.getViolent();
			}
			
		}
		if (npc.res <= 25 && npc.dis <= 25) {
			tempSpeech = npc.getDisrespect();
		}
		if (npc.fear >= 75) {
			tempSpeech = npc.getAfraid();
		}
		int terms = tempSpeech.length;
		double rand = Math.random();
		rand *= terms*10;
		int rando = (int)rand % terms;
		main.output(tempSpeech[rando]);
		if (tempBool) {
			Monster m = (Monster)this.monsterize(mob);
			combat(p, m, false, loc, i, mag, skl);
		}
		if (npc.type == 1) { // If item merchant...
			Vector<Item> itemsHere = new Vector<Item>(1,1);
			for (int n = 0; n < inventory.length; n++){
				for (int z = 0; z < i.itemVector.size(); z++){
					Item temp = i.getItemFromName(inventory[n]);
					if (temp.equals(i.itemVector.elementAt(z))) {
						itemsHere.addElement(temp);
					} //close if
				} // close inner for
			}// close outer for

			ishop(itemsHere, p, loc, i, loc);
		} else if (npc.type == 2){ // If equipment merchant...
			Vector<equipment> equipsHere = new Vector<equipment>(1,1);
			for (int n = 0; n < inventory.length; n++){
				for (int z = 0; z < i.equipVector.size(); z++){
					equipment temp = i.getEquipFromName(inventory[n]);
					if (temp.equals(i.equipVector.elementAt(z))) {
						equipsHere.addElement(temp);
					} //close if
				} // close inner for
			}// close outer for

			eshop(equipsHere, p, loc, i, loc);
		} else if (npc.type ==3){ // If it's the guy who wants you to kill the Goblin King...
			if (i.getItemFromName("Goblin\'s Head").locatedAt == "Inventory" ) { // If you have killed Gary...
				if (i.getItemFromName("Tutorial Key").locatedAt == "Inventory") { // ... and you already have the key...
					main.output("What are you waiting for? Go south, to your destiny!");
				}
				else { // If you don't have the key...
					main.output("Oh, you have killed Gary! Here, take this key and head through the southern door.");
					Room inv = loc.getRoomFromName("Inventory");
					i.getItemFromName("Tutorial Key").move(inv);
					i.getItemFromName("Tutorial Key").setLocatedAt("Inventory");
					r.sDoorKey = 3;
				}
				
			} else { // If you haven't killed Gary...
				main.output("Oh, brave traveller. Please, go forth and slay the King of the Goblins, Gary!");
			}
			
		}
	}



//TODO: This needs to get input from main.entryField, not scanner.
	public void ishop(Vector<Item> i, Player p, Rooms loc, Items it, Rooms r){
		int g = p.gold;
		char c;
		Room inv = loc.getRoomFromName("Inventory");
		boolean done = false;

		do {
			main.output("What'll it be? Buying, or selling?");
			
			input = main.getInput();
			input = input.toUpperCase();
			input = input.replaceAll("[^BS]", " ");
			input = input.trim();
			if (input.isEmpty()){
				input = "B";
			}
			c = input.charAt(0);
			
			switch (c){
			case 'B':
			
			main.output("Current gold: " + g);
			for(int n = 0; n < i.size(); n++){
				main.output(n+1 + ": " + i.elementAt(n).name + "     " + i.elementAt(n).cost);
			}
			input = main.getInput();
			input = input.toUpperCase();
			input = input.replaceAll("[^0123456789]", " ");
			input = input.trim();
			if (input.isEmpty()){
				int temp = i.size();
				temp+= 10;
				input = String.valueOf(temp);
			}
			num = Integer.parseInt(input);
			num--;
			if (num >= i.size()){
				main.output("I don't seem to have that many different items");
			} else {
				if(i.elementAt(num).cost > g){
					main.output("You don't have enough gold for that.");

				}else {
					g -= i.elementAt(num).cost;
					Item temp = i.elementAt(num).duplicateItem(i.elementAt(num), it);
					temp.numHave++;
					temp.setLocation(inv);
					temp.setLocatedAt("Inventory");
					temp.move(inv);
					main.output("Thank you for your purchase.");

				}//close else
			}//close else
			break;
			
			case 'S':
				Vector<Item> have = it.getItemsInInventory();
				main.output("Current gold: " + g);
				for(int n = 0; n < have.size(); n++){
					main.output(n+1 + ": " + have.elementAt(n).name + "     " + have.elementAt(n).cost);
				}
				input = main.getInput();
				input = input.toUpperCase();
				input = input.replaceAll("[^0123456789]", " ");
				input = input.trim();
				if (input.isEmpty()){
					int temp = i.size();
					temp+= 10;
					input = String.valueOf(temp);
				}
				num = Integer.parseInt(input);
				num--;
				if (num >= have.size()){
					main.output("You don't seem to have that many different items");
				} else {
						if (have.elementAt(num).use != 5){ // If the item isn't a key...
						g += have.elementAt(num).cost;
						Item temp = have.elementAt(num);
						Room n = loc.getRoomFromName("null");
						temp.setLocatedAt("null");
						temp.setLocation(n);
						temp.move("null", loc);
						main.output("Thank you!");
						} else {
						main.output("Oh, I can't buy that!");
						}

					}//close else
				break;
				}//close switch
			
			
				main.output("Is there anything else you want?");
				input = main.getInput();
				input = input.toUpperCase();
				input = input.replaceAll("[^N]", " ");
				input = input.trim();
				if (input.isEmpty()){
					input += "Y";
				}
				choice = input.charAt(0);
				if (choice == 'N'){
					done = true;
				}		

		} while(!done);
		
		

	}

//TODO: This needs to get input from main.entryField, not scanner.
	public void eshop(Vector<equipment> i, Player p, Rooms loc, Items it, Rooms r){
		int g = p.gold;
		int c;
		Room inv = loc.getRoomFromName("Inventory");
		boolean done = false;

		do {
			main.output("What'll it be? Buying, or selling?");
			
			input = main.getInput();
			input = input.toUpperCase();
			input = input.replaceAll("[^BS]", " ");
			input = input.trim();
			if (input.isEmpty()){
				input = "B";
			}
			c = input.charAt(0);
			
			switch (c){
			case 'B':
			
			main.output("Current gold: " + g);
			for(int n = 0; n < i.size(); n++){
				main.output(n+1 + ": " + i.elementAt(n).name + "     " + i.elementAt(n).cost);
			}
			input = main.getInput();
			input = input.toUpperCase();
			input = input.replaceAll("[^0123456789]", " ");
			input = input.trim();
			if (input.isEmpty()){
				int temp = i.size();
				temp+= 10;
				input = String.valueOf(temp);
			}
			num = Integer.parseInt(input);
			num--;
			if (num >= i.size()){
				main.output("I don't seem to have that many different items");
			} else {
				if(i.elementAt(num).cost > g){
					main.output("You don't have enough gold for that.");

				}else {
					g -= i.elementAt(num).cost;
					equipment temp = i.elementAt(num).duplicateEquip(i.elementAt(num), it);
					temp.numHave++;
					temp.setLocation(inv);
					temp.setLocatedAt("Inventory");
					temp.move(inv);
					main.output("Thank you for your purchase.");

				}//close else
			}//close else
			break;
			
			case 'S':
				Vector<equipment> have = it.getEquipsInInventory();
				main.output("Current gold: " + g);
				for(int n = 0; n < have.size(); n++){
					main.output(n+1 + ": " + have.elementAt(n).name + "     " + have.elementAt(n).cost);
				}
				input = main.getInput();
				input = input.toUpperCase();
				input = input.replaceAll("[^0123456789]", " ");
				input = input.trim();
				if (input.isEmpty()){
					int temp = i.size();
					temp+= 10;
					input = String.valueOf(temp);
				}
				num = Integer.parseInt(input);
				num--;
				if (num >= have.size()){
					main.output("You don't seem to have that many different items");
				} else {
						g += have.elementAt(num).cost;

						equipment temp = have.elementAt(num);
						Room n = loc.getRoomFromName("null");
						temp.setLocatedAt("null");
						temp.setLocation(n);
						temp.move("null", loc);
						
						main.output("Thank you!");

					}//close else
				break;
				}//close switch
			
			
				main.output("Is there anything else you want?");
				input = main.getInput();
				input = input.toUpperCase();
				input = input.replaceAll("[^N]", " ");
				input = input.trim();
				if (input.isEmpty()){
					input += "Y";
				}
				choice = input.charAt(0);
				if (choice == 'N'){
					done = true;
				}		

		} while(!done);
		
		

	}

	public Mob monsterize(Mobs mobs){
		Monster newmon = new Monster();
		newmon.id = this.id;
		newmon.hp = this.hp;
		newmon.maxHP = this.maxHP;
		newmon.mp = this.mp;
		newmon.maxMP = this.maxMP;
		newmon.atk = this.atk;
		newmon.def = this.def;
		newmon.str = this.str;
		newmon.dex = this.dex;
		newmon.vit = this.vit;
		newmon.intel = this.intel;
		newmon.wis = this.wis;
		newmon.cha = this.cha;
		newmon.dis = this.dis;
		newmon.fear = this.fear;
		newmon.res = this.res;
		newmon.hate = this.hate;
		newmon.type = 0;
		newmon.unique = this.unique;
		newmon.title = this.title;
		newmon.name = this.name;
		newmon.desc = this.desc;
		newmon.position = this.position;
		newmon.xp = this.xp;
		newmon.gold = this.gold;
		newmon.locatedAt = this.locatedAt;
		newmon.location = this.location;
		newmon.bodyplan = this.bodyplan;
		newmon.idle = this.idle;
		newmon.combat = this.combat;
		newmon.speech = this.speech;
		newmon.like = this.like;
		newmon.dislike = this.dislike;
		newmon.afraid = this.afraid;
		newmon.respect = this.respect;
		newmon.disrespect = this.disrespect;
		newmon.violent = this.violent;
		newmon.engage = this.engage;
		newmon.weapon = this.weapon;
		newmon.drop = this.drop;
		newmon.dropchance = this.dropchance;
		newmon.flee = this.flee;
		newmon.personality = this.personality;
		this.kill();
		this.locatedAt = "null";
		mobs.monVector.addElement(newmon);
		mobs.npcVector.removeElement(this);
		return newmon;
	}
}
