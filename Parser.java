
import java.lang.String;
//import java.util.Scanner;
import java.util.Vector;
//import java.io.*;

public class Parser {

	Parser() {
	}

	void look(Room r, String arg, Player p, Items i, Rooms loc, Mobs mo) {
		String desc;
		String exits;
		String here = r.name;
		//String temp;
		Vector<Item> itemsHere = i.getItemsByRoom(here);
		Vector<equipment> equipHere = i.getEquipsByRoom(here);
		Vector<NPC> npcHere = mo.getNPCByRoom(here, loc);
		Vector<Monster> monsHere = mo.getMonsterByRoom(here, loc);
		Vector<Item> invItems = i.getItemsInInventory();
		Vector<equipment> invEqu = i.getEquipsInInventory();
		String art = "a";
		String doors;

		int numItems = itemsHere.size();
		int numEquips = equipHere.size();
		int numNPCs = npcHere.size();
		int numMons = monsHere.size();


		switch(arg){
		case "null":
		case "here":
		case "room":

			minimap(r,loc,2);
			desc = r.getDesc();
			main.output(desc+ "\n");
			exits = r.getExits();
			main.output(exits+ "\n");
			doors = r.getDoors();
			if (!doors.isEmpty()){
				main.output(doors+ "\n");
			}


			switch(numItems) {
			case 0:
				break;

			case 1:
				art = getArticle(itemsHere.elementAt(0));
				main.output("There is " + art + " " + itemsHere.elementAt(0).name + " here.\n");
				break;

			default:
			StringBuffer outText = new StringBuffer();
				for (int n = 0; n < itemsHere.size() - 1; n++){
					if (n == 0){
					art = getArticle(itemsHere.elementAt(n),true);
					} else {
						art = getArticle(itemsHere.elementAt(n));
					}
					outText.append(art + " " + itemsHere.elementAt(n).name + ", ");
				}
				art = getArticle(itemsHere.elementAt(itemsHere.size()-1));
				outText.append("and " + art + " " + itemsHere.elementAt(itemsHere.size() - 1).name + " are here.\n");
				main.output(outText.toString());
				break;


			}

			switch(numEquips) {
			case 0:
				break;

			case 1:
				art = getArticle(equipHere.elementAt(0));
				main.output("There is " + art + " " + equipHere.elementAt(0).name + " here.\n");
				break;
			
			default:
			StringBuffer outText = new StringBuffer();
				for (int n = 0; n < equipHere.size() - 1; n++){
					if (n == 0){
					art = getArticle(equipHere.elementAt(n),true);
					}else {
					art = getArticle(equipHere.elementAt(n));
					}
					outText.append(art + " " + equipHere.elementAt(n).name + ", ");
				}

				art = getArticle(equipHere.elementAt(equipHere.size() -1));
				outText.append("and " + art + " " + equipHere.elementAt(equipHere.size() - 1).name + " are here.\n");
				main.output(outText.toString());
				break;


			}

			switch (numNPCs){
			case 0:
				break;

			case 1:
				art = getArticle(npcHere.elementAt(0), true);
				main.output(art + " " + npcHere.elementAt(0).name + " is " + npcHere.elementAt(0).position + " here.\n");
				break;

			
			default:
			StringBuffer outText = new StringBuffer();
				for (int n = 0; n < npcHere.size() - 1; n++){
					if (n == 0){
					art = getArticle(npcHere.elementAt(n), true);
					} else {
						art = getArticle(npcHere.elementAt(n));
					}
					outText.append(art + " " + npcHere.elementAt(n).name + " is " + npcHere.elementAt(n).position + ", ");
				}
				art = getArticle(npcHere.elementAt(npcHere.size() - 1));
				outText.append("and " + art + " " + npcHere.elementAt(npcHere.size() - 1).name + " is " + npcHere.elementAt(npcHere.size() - 1).position + " here.\n");
				main.output(outText.toString());
				break;
			}

			switch (numMons){
			case 0:
				break;

			case 1:
				art = getArticle(monsHere.elementAt(0), true);
				main.output(art + " " + monsHere.elementAt(0).name + " is " + monsHere.elementAt(0).position + " here.\n");
				break;
			
			default:
			StringBuffer outText = new StringBuffer();
       			for (int n = 0; n < monsHere.size() - 1; n++){
       				if (n == 0){
       					art = getArticle(monsHere.elementAt(n), true);
       				} else {
       					art = getArticle(monsHere.elementAt(n));
       				}
					outText.append(art + " " + monsHere.elementAt(n).name + " is " + monsHere.elementAt(n).position + ", ");
				}
       			art = getArticle(monsHere.elementAt(monsHere.size() -1));
				outText.append("and " + art + " " + monsHere.elementAt(monsHere.size() - 1).name + " is " + monsHere.elementAt(monsHere.size() - 1).position + " here.\n");
				main.output(outText.toString());
				break;


			}

			if (main.DEBUG){
				main.output("ROOM: " + r.name);
			}


			break;

		case "me":
		case "self":
			main.output("And just how am I supposed to do that?");
			break;

		case "mirror":
			main.output("Did you see a mirror anywhere in the room description? I didn't think so.");
			break;

		default: 
			Item itm = i.getItemFromName(arg);
			equipment equ = i.getEquipFromName(arg);
			Monster mon = mo.getMonsterFromName(arg);
			NPC npc = mo.getNPCFromName(arg);
			Item tempItm1 = itm;
			Item tempItm2 = itm;
			if (invItems.contains(itm)){
				itm = i.getItemFromName(arg);
			}
			
			if (itemsHere.contains(itm)){
				itm = i.getItemFromName(arg);
			}

			if (!itemsHere.contains(tempItm1) && !invItems.contains(tempItm2)) {
				itm = null;
			}	

			equipment tempEqu1 = equ;
			equipment tempEqu2 = equ;

			if (!equipHere.contains(tempEqu1) && !invEqu.contains(tempEqu2)) {
				equ = null;
			}	


			if(itm != null){				
				main.output(itm.getDesc());

			} else if (equ !=null) {

				main.output(equ.getDesc());

			}
			else if (mon !=null && monsHere.contains(mon)) {
				main.output(mon.getDesc());
			} 
			else if (npc !=null && npcHere.contains(npc)) {
				main.output(npc.getDesc());
			}
			else {
				main.output("There is nothing like that around to look at.");
			}
			break;

		}
	}

	void help(String arg) {
		switch (arg) {
		case "go":
		case "north":
		case "south":
		case "east":
		case "west":
			main.output("MOVEMENT COMMANDS\n");
			main.output("Syntax: [go] (direction)\n");
			main.output("These commands will move you in the specified direction, if possible.\n");
			main.output("Short form: 'n', 's', 'e', 'w' will all attempt to move in the appropriate direction.");
		break;
		case "take":
		case "grab":
		case "get":
				main.output("TAKE COMMAND\n");
				main.output("Syntax: take/get/grab (item)\n");
				main.output("This command will remove an item from the environment and place it in your inventory.");
				break;
		case "drop":
		case "throw":
		case "lose":
				main.output("DROP COMMAND\n");
				main.output("Syntax: drop/throw/lose (item)\n");
				main.output("This command will remove an item from your inventory and place it in the environment.");
				break;
		case "inventory":
		case "items":
				main.output("INVENTORY COMMAND\n");
				main.output("Syntax: inventory/items\n");
				main.output("This command will list all items currently in your inventory.\n");
				main.output("Short form: 'i'.");
				break;
		case "look":
		case "examine":
					main.output("LOOK COMMAND\n");
					main.output("Syntax: look/examine [object]\n");
					main.output("This command will provide a description of the object, or room if object is not specified.\n");
					main.output("Short form: 'l [object]'.");
					break;
		case "quit":
		case "exit":
			main.output("QUIT COMMAND\n");
			main.output("Syntax: quit/exit\n");
			main.output("This command will exit the game.");
			break;
		case "help":
					main.output("HELP COMMAND\n");
					main.output("Syntax: help [command]\n");
					main.output("This command provides help text on various commands. Under the syntax section, any terms in (parentheses) are required. Terms in [brackets] are optional. If there is more than one command listed, i.e. \"look/examine\", either one will be accepted by the parser.");
					break;
		case "help help":
					main.output("Ha ha. Very funny, jerkface.");
					break;
		case "equip":
		case "hold":
		case "wear":
				main.output("EQUIP COMMAND\n");
				main.output("Syntax: equip/hold/wear (eqipment)\n");
				main.output("This command will allow you to wear and use the various weapons and armor you find.");
				break;
		case "status":
		case "stats":
				main.output("STATUS COMMAND\n");
				main.output("Syntax: status/stats\n");
				main.output("This command gives a summary of your status.");
				break;
		case "attack":
		case "fight":
		case "kill":
			main.output("FIGHT COMMAND\n");
			main.output("Syntax: attack/fight/kill (target)\n");
			main.output("This command initiates combat with an NPC.");
			break;
		case "talk":
		case "chat":
		case "gab":
		case "speak":
		case "converse":
			main.output("TALK COMMAND\n");
			main.output("Syntax: talk/chat/gab/converse/speak (target)\n");
			main.output("This command allows you to speak with an NPC. This will also allow you to buy items from merchants.");
			break;
		case "lock":
			main.output("LOCK COMMAND\n");
			main.output("Syntax: lock (direction)\n");
			main.output("This command will attempt to lock the door in the specified direction. It will not work if there is no door, the door is already locked, or you lack the proper key.");
			break;
		case "unlock":
			main.output("UNLOCK COMMAND\n");
			main.output("Syntax: unlock (direction)\n");
			main.output("This command will attempt to unlock the door in the specified direction. It will not work if there is no door, the door is already unlocked, or you lack the proper key.");
			break;
		case "drink":
			main.output("DRINK COMMAND\n");
			main.output("Syntax: drink (potion)\n");
			main.output("This command will attempt to drink a potion in your inventory.");
			break;
		case "nickname":
			main.output("NICKNAME COMMAND\n");
			main.output("Syntax: nickname (monster/item) as (nickname)\n");
			main.output("This command will assign a nickname of your choice to an item or npc nearby.");
			break;
			
		

		default: 
			main.output("Use 'look' to examine your surroundings, and 'go (direction)' to move into an adjacent, available location. You can also 'take' or 'drop' objects, and 'wear' or 'hold' equipment. Use 'talk' to converse with NPCs, or 'fight' to attack them. When you are done, simply type 'quit'.\n");
			main.output("For more information on a specific command, type 'help (command)' e.g. 'help look', 'help take', etc.");
		}

	}

	Room north(Room ro, Rooms loc) {

		int roomNum = ro.getNorth();

		if (roomNum == -1) {
			syntaxError();		
		} else {
			ro = loc.getRoom(roomNum);
		}

		return ro;

	}

	Room south(Room ro, Rooms loc) {


		int roomNum = ro.getSouth();

		if (roomNum == -1) {
			syntaxError();		
		} else {
			ro = loc.getRoom(roomNum);
		}

		return ro;

	}

	Room east(Room ro, Rooms loc) {


		int roomNum = ro.getEast();

		if (roomNum == -1) {
			syntaxError();		
		} else {
			ro = loc.getRoom(roomNum);
		}

		return ro;

	}

	Room west(Room ro, Rooms loc) {


		int roomNum = ro.getWest();

		if (roomNum == -1) {
			syntaxError();		
		} else {
			ro = loc.getRoom(roomNum);
		}

		return ro;

	}

	Room go(String arg, Room r, Rooms loc, Player p, Items i, Mobs mo) {
		Room rnew;
		Item item;
		int door;
		int key;
		switch(arg){

		case "north":
			door = r.nDoorState;
			if (door == 3){
				key = r.nDoorKey;
				item = i.getKeyFromID(key);
				if (item != null){
					rnew = north(r, loc);
					if (rnew != r){
						r = rnew;
						main.output("You unlock the door and step through, locking it again behind you.");
					}
					look(r,"here",p, i, loc, mo);

					break;


				} else {
					main.output("You do not have the key to unlock this door.");
					break;
				}
			} else if (door == 2){
				rnew = north(r, loc);
				if (rnew != r){
					r = rnew;
					main.output("You open the door, step through, and close it again behind you.");
				}
				look(r,"here",p, i, loc, mo);

				break;

			} else {


				rnew = north(r, loc);
				if (rnew != r){
					r = rnew;
					main.output("You go north.");
				}
				look(r,"here",p, i, loc, mo);

				break;
			}


		case "south":
			door = r.sDoorState;
			if (door == 3){
				key = r.sDoorKey;
				item = i.getKeyFromID(key);
				if (item != null){
					rnew = south(r, loc);
					if (rnew != r){
						r = rnew;
						main.output("You unlock the door and step through, locking it again behind you.");
					}
					look(r,"here",p, i, loc, mo);

					break;


				} else {
					main.output("You do not have the key to unlock this door.");
					break;
				}
			} else if (door == 2){
				rnew = south(r, loc);
				if (rnew != r){
					r = rnew;
					main.output("You open the door, step through, and close it again behind you.");
				}
				look(r,"here",p, i, loc, mo);

				break;

			} else {


				rnew = south(r, loc);
				if (rnew != r){
					r = rnew;
					main.output("You go south.");
				}
				look(r,"here",p, i, loc, mo);

				break;
			}

		case "east":
			door = r.eDoorState;
			if (door == 3){
				key = r.eDoorKey;
				item = i.getKeyFromID(key);
				if (item != null){
					rnew = east(r, loc);
					if (rnew != r){
						r = rnew;
						main.output("You unlock the door and step through, locking it again behind you.");
					}
					look(r,"here",p, i, loc, mo);

					break;


				} else {
					main.output("You do not have the key to unlock this door.");
					break;
				}
			} else if (door == 2){
				rnew = east(r, loc);
				if (rnew != r){
					r = rnew;
					main.output("You open the door, step through, and close it again behind you.");
				}
				look(r,"here",p, i, loc, mo);

				break;

			} else {


				rnew = east(r, loc);
				if (rnew != r){
					r = rnew;
					main.output("You go east.");
				}
				look(r,"here",p, i, loc, mo);

				break;
			}


		case "west":
			door = r.wDoorState;
			if (door == 3){
				key = r.wDoorKey;
				item = i.getKeyFromID(key);
				if (item != null){
					rnew = west(r, loc);
					if (rnew != r){
						r = rnew;
						main.output("You unlock the door and step through, locking it again behind you.");
					}
					look(r,"here",p, i, loc, mo);

					break;


				} else {
					main.output("You do not have the key to unlock this door.");
					break;
				}
			} else if (door == 2){
				rnew = west(r, loc);
				if (rnew != r){
					r = rnew;
					main.output("You open the door, step through, and close it again behind you.");
				}
				look(r,"here",p, i, loc, mo);

				break;

			} else {


				rnew = west(r, loc);
				if (rnew != r){
					r = rnew;
					main.output("You go west.");
				}
				look(r,"here",p, i, loc, mo);

				break;
			}


		case "crazy":
		default:
			main.output("You go crazy.");
			break;

		}
		return r;

	}

	void take(String arg, Rooms loc, Player p, Items i, Room r) {

		Vector<Item> itemsHere = i.getItemsByRoom(r.name);
		Vector<equipment> equipHere = i.getEquipsByRoom(r.name);

		int numItems = itemsHere.size();
		int numEquip = equipHere.size();

		Item ite = i.getItemFromName(arg);
		equipment equ = i.getEquipFromName(arg);

		if (numItems > 0 && ite != null) {
			Item temp = null;
			for (int n = 0; n < numItems; n++){
				temp = itemsHere.elementAt(n);
				if (temp.equals(ite)){			
					Room inv = loc.getRoomFromName("Inventory");
					String art = getArticle(ite);
					ite.setLocation(inv);
					main.output("You picked up " + art + " " + ite.getName());
					ite.numHave++;
					int itemNum = p.getNumItems();
					itemNum++;
					p.setNumItems(itemNum);
					r.removeItem(ite,"Inventory", loc);
					numItems--;
					i.move(ite,inv);
				}
			}

		} else if (numEquip > 0 && equ != null) {
			equipment temp = null;
			for (int n = 0; n < numEquip; n++){
				temp = equipHere.elementAt(n);
				if (temp.equals(equ)){
					Room inv = loc.getRoomFromName("Inventory");
					String art = getArticle(equ);
					equ.setLocation(inv);
					main.output("You picked up " + art + " " + equ.getName());
					equ.numHave++;
					int itemNum = p.getNumItems();
					itemNum++;
					p.setNumItems(itemNum);
					r.removeEqu(equ, "Inventory", loc);
					numEquip--;
					equipHere.removeElement(equ);
					i.move(equ, inv);
				}
			}

		} else {
			main.output("There is nothing like that in here.");
		}
	}

	void drop(String arg, Rooms loc, Player p, Items i, Room r) {

		Vector<Item> itemsInv = i.getItemsInInventory();
		Vector<equipment> equipInv = i.getEquipsInInventory();

		Item ite = i.getItemFromName(arg);
		equipment equ = i.getEquipFromName(arg);
		if (ite != null && itemsInv.contains(ite)){
			if (ite.use ==  5){
				main.output("Dropping keys is a bad idea.");
			} else {
				Room inv = loc.getRoomFromName("Inventory");
				String art = getArticle(ite);
				if (ite.getLocation() == inv) {
					ite.setLocation(r);
					main.output("You dropped " + art + " " + ite.getName());
					ite.numHave--;
					p.numItems--;
					r.setItem(ite, ite.name, ite.desc, ite.unique, r.name);
					i.move(ite, r);
				}
			}
		}
		else if (equ != null && equipInv.contains(equ)){

			Room inv = loc.getRoomFromName("Inventory");
			String art = getArticle(equ);
			if (equ.getLocation() == inv) {
				equ.setLocation(r);
				main.output("You dropped " + art + " " + equ.getName());
				equ.numHave--;
				p.numItems--;
				r.setEquip(equ, equ.name, equ.desc, equ.unique, r.name, equ.type, equ.id, equ.pow, equ.equipBy);

				i.move(equ, r);

			}
		} 
		else {
			main.output("I have no such thing to drop");
		}
	}

	Room jumpToRoom(int id, Rooms loc){
		Room r;
		r = loc.roomVector.elementAt(id);
		return r;
	}

	void equip(String arg, Player p, Items it){
		equipment e = it.getEquipFromName(arg);
		if (e != null){
			p.equip(e,it);
		} else {
			main.output("I cannot equip that.");

		}
	}

	void stats(Player p, Items it, Room r, Magic mag, Skills skl){
		int tnl = p.level * 100;
		tnl -= p.xp;
		main.output("Name: " + p.name + "      ROLE: " + p.roles[p.intRole]);
		main.output("HP: "+ p.hp + "/" + p.maxHP + " MP: " + p.mp + "/" + p.maxMP + "\nSTR: " + p.str + "  DEX: " + p.dex + "  VIT: " + p.vit);
		main.output("INT: " + p.intel + "  WIS: " + p.wis + "  CHA: " + p.cha);
		main.output("ATK: " + p.atk + "  DEF: " + p.def);
		main.output("Level: " + p.level + "  XP: " + p.xp + "  TO NEXT LEVEL: " + tnl);
		String tempWeap;
		String tempHelm;
		String tempArmr;
		String tempGlov;
		String tempLegs;
		String tempBoot;
		equipment temp;

		if (p.weap == -1){
			tempWeap = "nothing";
		} else {
			temp = it.getEquipFromID(p.weap,0);
			tempWeap = temp.getName();
		}

		if (p.helm == -1){
			tempHelm = "nothing";
		} else {
			temp = it.getEquipFromID(p.helm,1);
			tempHelm = temp.getName();
		}

		if (p.armr == -1){
			tempArmr = "nothing";
		} else {
			temp = it.getEquipFromID(p.armr,2);
			tempArmr = temp.getName();
		}

		if (p.glov == -1){
			tempGlov = "nothing";
		} else {
			temp = it.getEquipFromID(p.glov,3);
			tempGlov = temp.getName();
		}

		if (p.legs == -1){
			tempLegs = "nothing";
		} else {
			temp = it.getEquipFromID(p.legs,4);
			tempLegs = temp.getName();
		}

		if (p.boot == -1){
			tempBoot = "nothing";
		} else {
			temp = it.getEquipFromID(p.boot,5);
			tempBoot = temp.getName();
		}

		main.output("Weapon: " + tempWeap + " Helmet: " + tempHelm + " Armor: " + tempArmr + "\nGauntlets: " + tempGlov + " Leggings: " + tempLegs + " Boots: " + tempBoot);

		main.output("Spells: ");
		for (int tempInt = 0; tempInt < mag.spellVector.size(); tempInt++){
			byte tempByte = mag.spellVector.elementAt(tempInt).learn;
			tempByte = (byte)(tempByte & p.role);
			if (tempByte != 0 && p.level >= mag.spellVector.elementAt(tempInt).level){
				main.output(mag.spellVector.elementAt(tempInt).name);
			}
		}
		main.output("Skills:");
		for (int tempInt = 0; tempInt < skl.skillVector.size(); tempInt++){
			byte tempByte = skl.skillVector.elementAt(tempInt).learn;
			tempByte = (byte)(tempByte & p.role);
			if (tempByte != 0 && p.level >= skl.skillVector.elementAt(tempInt).level){
				main.output(skl.skillVector.elementAt(tempInt).name);
			}
		}


	}

	void speak(String arg, Rooms loc, Player p, Mobs mo, Room r, Items it, Magic mag, Skills skl){

		NPC npc;
		Monster mon;
		String here = r.name;
		Vector<NPC> npcHere = mo.getNPCByRoom(here, loc);
		Vector<Monster> monHere = mo.getMonsterByRoom(here, loc);
		npc = mo.getNPCFromName(arg);
		mon = mo.getMonsterFromName(arg);

		if (npc != null){
			for (int i = 0; i < npcHere.size(); i++){
				if (npc.equals(npcHere.elementAt(i))){
					npc.speak(npc, p, it, loc, mo, mag, r, skl);
				}
			}
		}

		if (mon != null){
			for (int i = 0; i < monHere.size(); i++){
				if (mon.equals(monHere.elementAt(i))){
					mon.speak(mon);
				}
			}
		}
	}

	void charm(String arg, Rooms loc, Player p, Mobs mo, Room r, Items it){
		NPC npc;
		Monster mon;
		String here = r.name;
		Vector<NPC> npcHere = mo.getNPCByRoom(here, loc);
		Vector<Monster> monHere = mo.getMonsterByRoom(here, loc);
		npc = mo.getNPCFromName(arg);
		mon = mo.getMonsterFromName(arg);

		if (npc != null){
			for (int i = 0; i < npcHere.size(); i++){
				if (npc.equals(npcHere.elementAt(i))){
					
					
					npc.charm(p);
				}
			}
		

		if (mon != null){
			for (int i = 0; i < monHere.size(); i++){
				if (mon.equals(monHere.elementAt(i))){
					mon.charm(p);
				}
			}
		}
	}
	}
	
	void insult(String arg, Rooms loc, Player p, Mobs mo, Room r, Items it){
		NPC npc;
		Monster mon;
		String here = r.name;
		Vector<NPC> npcHere = mo.getNPCByRoom(here, loc);
		Vector<Monster> monHere = mo.getMonsterByRoom(here, loc);
		npc = mo.getNPCFromName(arg);
		mon = mo.getMonsterFromName(arg);

		if (npc != null){
			for (int i = 0; i < npcHere.size(); i++){
				if (npc.equals(npcHere.elementAt(i))){
					npc.threat(p, it, r, loc);
				}
			}
		}

		if (mon != null){
			for (int i = 0; i < monHere.size(); i++){
				if (mon.equals(monHere.elementAt(i))){
					mon.threat(p, it, r, loc);
				}
			}
		}
	}
	
	Room attack(String arg, Player p, boolean u, Rooms loc, Mobs m, Room r, Items it, Magic mag, Skills skl){
		Monster mon = null;
		NPC npc = null;
		Room room = r;
		String here = r.name;
		Vector<Monster> monHere = m.getMonsterByRoom(here, loc);
		mon = m.getMonsterFromName(arg);
		Vector<NPC> NPCHere = m.getNPCByRoom(here, loc);
		npc = m.getNPCFromName(arg);

		if (!monHere.contains(mon)){
			mon = null;
		}
		
		if (!NPCHere.contains(npc)){
			npc = null;
		}

		if(mon != null){
			int result = mon.combat(p, mon, u, loc, it, mag, skl);
			look(r, "here",p, it, loc, m);
			if (result == 0) {
				room = jumpToRoom(0, loc);
			}
		} else if(npc != null) {
			mon = (Monster) npc.monsterize(m);
			int result = mon.combat(p, mon, u, loc, it, mag, skl);
			look(r, "here",p, it, loc, m);
			if (result == 0) {
				room = jumpToRoom(0,loc);
			}
		}
		else {
			main.output("I don't see that monster here.");
		}

		return room;
	}

	void syntaxError() {
		main.output("Invalid command.");
	}

	void inventory(Player p, Items it, Rooms loc) {

		main.output("Your inventory contains:");
		Room inv = loc.getRoomFromName("Inventory");
		//int itemNum = p.getNumItems();
		//if (itemNum == 0) {
		//	main.output("nothing");
		//} else {
		for (int i = 0; i < it.itemVector.size(); i++){
			if (it.itemVector.elementAt(i).location == inv) {
				main.output(it.itemVector.elementAt(i).name);
				p.numItems++;
			}
		}
		for (int i = 0; i < it.equipVector.size(); i++){
			if (it.equipVector.elementAt(i).location == inv){
				if (it.equipVector.elementAt(i).equipped) {
					main.output(it.equipVector.elementAt(i).name + " (e)");
				} else {
					main.output(it.equipVector.elementAt(i).name);
				}
			}
		}

	}

	Room use(String word2, Player p, Rooms loc, Mobs mo, Items i, Room r, Magic ma) {
		String item;
		String target;
		String tempPrep;
		int tempInt = 0;
		do {
			tempPrep = word2.substring(tempInt, tempInt + 4);
			if (tempInt == word2.length() - 4){
				syntaxError();
				main.output("Try \"use (item) on (target)\".");
				break;
			}
			tempInt++;
		} while (!tempPrep.contentEquals(" on "));

		item = word2.substring(0, tempInt - 1);
		target = word2.substring(tempInt + 3, word2.length());

		Item it = i.getItemFromName(item);
		NPC n = mo.getNPCFromName(target);
		Monster m = mo.getMonsterFromName(target);
		if (!target.equalsIgnoreCase("me")){
			p = null;
		}
		if (it != null){
			it.use(p, n, m, r, i, ma);
		} else {
			main.output("You do not have that item.");
		}
		return r;
	}

	public void minimap(Room r, Rooms loc, int s){
		main.out("\n");
		int sightDist = s;
		int totalNorth = 0;
		int totalWest = 0;
		int totalEast = 0;
		int totalSouth = 0;
		Room temp = r;
		for (int i = 0; i < sightDist; i ++){
			boolean canNorth = temp.canNorth();
			if (canNorth) {
				totalNorth++;
				temp = loc.getRoomFromID(temp.getNorth());
			}
		}
		temp = r;
		for (int i = 0; i < sightDist; i ++){
			boolean canSouth = temp.canSouth();
			if (canSouth) {
				totalSouth++;
				temp = loc.getRoomFromID(temp.getSouth());
			}
		}
		temp = r;
		for (int i = 0; i < sightDist; i ++){
			boolean canWest = temp.canWest();
			if (canWest) {
				totalWest++;
				temp = loc.getRoomFromID(temp.getWest());
			}
		}
		temp = r;
		for (int i = 0; i < sightDist; i ++){
			boolean canEast = temp.canEast();
			if (canEast) {
				totalEast++;
				temp = loc.getRoomFromID(temp.getEast());
			}
		}
		mapNorth(totalWest,totalNorth);
		mapWest(totalWest);
		main.out("[*]");
		mapEast(totalEast);
		mapSouth(totalWest,totalSouth);
		main.out("\n\n");

	}

	public void mapNorth(int west, int north){
		int temp = west;
		for (int i = 0; i < north; i++){
			for (int n = 0; n < temp; n++){
				main.out("    ");
			}
			main.out("[ ]\n");
			temp = west;
			for (int n = 0; n < temp; n++){
				main.out("    ");
			}
			main.out(" |\n");
		}

	}

	public void mapWest(int west){
		for (int i = 0; i < west; i++){
			main.out("[ ]-");
		}

	}

	public void mapEast(int east){
		for (int i = 0; i < east; i++){
			main.out("-[ ]");
		}

	}

	public void mapSouth(int west, int south){
		int temp = west;
		for (int i = 0; i < south; i++){
			main.out("\n");
			for (int n = 0; n < temp; n++){
				main.out("    ");
			}
			main.out(" | ");
			temp = west;
			main.out("\n");
			for (int n = 0; n < temp; n++){
				main.out("    ");
			}
			main.out("[ ]");
		}

	}

	public void open(String arg, Room r, Rooms loc, Items i){
		char c;
		int temp;
		Room tempRoom;
		if (arg.isEmpty()){
			main.output("Open what?");
		} else if (arg.equalsIgnoreCase("door")){
			main.output("Which door?");			
		} else if (!arg.equalsIgnoreCase("north") && !arg.equalsIgnoreCase("south") && !arg.equalsIgnoreCase("east") && !arg.equalsIgnoreCase("west")){
			syntaxError();
		} else {
			c = arg.charAt(0);
			switch (c){

			case 'n':
				temp = r.nDoorState;
				tempRoom = loc.getRoomFromID(r.northexit);
				switch (temp){
				case 0:
					main.output("There is no door there!");
					break;
				case 1: main.output("That door is already open.");
				break;
				case 2: 
					main.output("You open the door.");
					r.nDoorState = 1;
					tempRoom.sDoorState = 1;
					break;
				case 3:
					Item tempKey = i.getKeyFromID(r.nDoorKey);
					if (tempKey != null){
						unlock(arg,r,loc,i);
						main.output("You open the door.");
						r.nDoorState = 1;
						tempRoom.sDoorState = 1;
						break;
					} else {
						main.output("This door is locked.");
					}
					break;
				}
				break;
			case 's':
				temp = r.sDoorState;
				tempRoom = loc.getRoomFromID(r.southexit);
				switch (temp){
				case 0:
					main.output("There is no door there!");
					break;
				case 1: main.output("That door is already open.");
				break;
				case 2: 
					main.output("You open the door.");
					r.sDoorState = 1;
					tempRoom.nDoorState = 1;
					break;
				case 3:
					Item tempKey = i.getKeyFromID(r.sDoorKey);
					if (tempKey != null){
						unlock(arg,r,loc,i);
						main.output("You open the door.");
						r.sDoorState = 1;
						tempRoom.nDoorState = 1;
						break;
					} else {
						main.output("This door is locked.");
					}
				}
				break;
			case 'e':
				temp = r.eDoorState;
				tempRoom = loc.getRoomFromID(r.eastexit);
				switch (temp){
				case 0:
					main.output("There is no door there!");
					break;
				case 1: main.output("That door is already open.");
				break;
				case 2: 
					main.output("You open the door.");
					r.eDoorState = 1;
			        tempRoom.wDoorState = 1;
					break;
				case 3:
					Item tempKey = i.getKeyFromID(r.eDoorKey);
					if (tempKey != null){
						unlock(arg,r,loc,i);
						main.output("You open the door.");
						r.eDoorState = 1;
						tempRoom.wDoorState = 1;
						break;
					} else {
						main.output("This door is locked.");
					}
				}
				break;
			case 'w':
				temp = r.wDoorState;
				tempRoom = loc.getRoomFromID(r.westexit);
				switch (temp){
				case 0:
					main.output("There is no door there!");
					break;
				case 1: main.output("That door is already open.");
				break;
				case 2: 
					main.output("You open the door.");
					r.wDoorState = 1;
					tempRoom.eDoorState = 1;
					break;
				case 3:
					Item tempKey = i.getKeyFromID(r.wDoorKey);
					if (tempKey != null){
						unlock(arg,r,loc,i);
						main.output("You open the door.");
						r.wDoorState = 1;
						tempRoom.eDoorState = 1;
						break;
					} else {
						main.output("This door is locked.");
					}
					break;
				}
				break;
			}
		}
	}

	public void unlock(String arg, Room r, Rooms loc, Items i){
		char c;
		int temp;
		Room tempRoom;
		if (arg.isEmpty()){
			main.output("Unlock what?");
		} else if (arg.equalsIgnoreCase("door")){
			main.output("Which door?");			
		} else if (!arg.equalsIgnoreCase("north") && !arg.equalsIgnoreCase("south") && !arg.equalsIgnoreCase("east") && !arg.equalsIgnoreCase("west")){
			syntaxError();
		} else {
			c = arg.charAt(0);
			switch (c){

			case 'n':
				temp = r.nDoorState;
				tempRoom = loc.getRoomFromID(r.northexit);
				switch (temp){
				case 0:
					main.output("There is no door there!");
					break;
				case 1:
				case 2: 
					main.output("That door is already unlocked.");
					break;
				case 3:
					Item tempKey = i.getKeyFromID(r.nDoorKey);
					if (tempKey != null){
						main.output("You unlock the door.");
						r.nDoorState = 2;
						tempRoom.sDoorState = 2;
						break;
					} else {
						main.output("You don't have the key.");
					}
					break;
				}
				break;
			case 's':
				temp = r.sDoorState;
				tempRoom = loc.getRoomFromID(r.southexit);
				switch (temp){
				case 0:
					main.output("There is no door there!");
					break;
				case 1:
				case 2: 
					main.output("That door is already unlocked.");
					break;
				case 3:
					Item tempKey = i.getKeyFromID(r.sDoorKey);
					if (tempKey != null){
						main.output("You unlock the door.");
						r.sDoorState = 2;
						tempRoom.nDoorState = 2;
						break;
					} else {
						main.output("You don't have the key.");
					}
					break;
				}
				break;
			case 'e':
				temp = r.eDoorState;
				tempRoom = loc.getRoomFromID(r.eastexit);
				switch (temp){
				case 0:
					main.output("There is no door there!");
					break;
				case 1:
				case 2: 
					main.output("That door is already unlocked.");
					break;
				case 3:
					Item tempKey = i.getKeyFromID(r.eDoorKey);
					if (tempKey != null){
						main.output("You unlock the door.");
						r.eDoorState = 2;
						tempRoom.wDoorState = 2;
						break;
					} else {
						main.output("You don't have the key.");
					}
					break;
				}
				break;
			case 'w':
				temp = r.wDoorState;
				tempRoom = loc.getRoomFromID(r.westexit);
				switch (temp){
				case 0:
					main.output("There is no door there!");
					break;
				case 1:
				case 2: 
					main.output("That door is already unlocked.");
					break;
				case 3:
					Item tempKey = i.getKeyFromID(r.wDoorKey);
					if (tempKey != null){
						main.output("You unlock the door.");
						r.wDoorState = 2;
						tempRoom.eDoorState = 2;
						break;
					} else {
						main.output("You don't have the key.");
					}
					break;
				}
				break;
			}
		}
	}

	public void close(String arg, Room r, Rooms loc){
		char c;
		int temp;
		Room tempRoom;
		if (arg.isEmpty()){
			main.output("Close what?");
		} else if (arg.equalsIgnoreCase("door")){
			main.output("Which door?");			
		} else if (!arg.equalsIgnoreCase("north") && !arg.equalsIgnoreCase("south") && !arg.equalsIgnoreCase("east") && !arg.equalsIgnoreCase("west")){
			syntaxError();
		} else {
			c = arg.charAt(0);
			switch (c){

			case 'n':
				temp = r.nDoorState;
				tempRoom = loc.getRoomFromID(r.northexit);
				switch (temp){
				case 0:
					main.output("There is no door there!");
					break;
				case 1: main.output("You close the door.");
				r.nDoorState = 2;
				tempRoom.sDoorState = 2;
				break;
				case 2: 
				case 3:
					main.output("That door is already closed.");
					break;
				}
				break;
			case 's':
				temp = r.sDoorState;
				tempRoom = loc.getRoomFromID(r.southexit);
				switch (temp){
				case 0:
					main.output("There is no door there!");
					break;
				case 1: main.output("You close the door.");
				r.sDoorState = 2;
				tempRoom.nDoorState = 2;
				break;
				case 2: 
				case 3:
					main.output("That door is already closed.");
					break;
				}
				break;
			case 'e':
				temp = r.eDoorState;
				tempRoom = loc.getRoomFromID(r.eastexit);
				switch (temp){
				case 0:
					main.output("There is no door there!");
					break;
				case 1: main.output("You close the door.");
				r.eDoorState = 2;
				tempRoom.wDoorState = 2;
				break;
				case 2: 
				case 3:
					main.output("That door is already closed.");
					break;
				}
				break;
			case 'w':
				temp = r.wDoorState;
				tempRoom = loc.getRoomFromID(r.westexit);
				switch (temp){
				case 0:
					main.output("There is no door there!");
					break;
				case 1: main.output("You close the door.");
				r.wDoorState = 2;
				tempRoom.eDoorState = 2;
				break;
				case 2: 
				case 3:
					main.output("That door is already closed.");
					break;
				}
				break;

			}
		}
	}

	public void lock(String arg, Room r, Rooms loc, Items i){
		char c;
		int temp;
		Room tempRoom;
		Item tempKey;
		if (arg.isEmpty()){
			main.output("Lock what?");
		} else if (arg.equalsIgnoreCase("door")){
			main.output("Which door?");			
		} else if (!arg.equalsIgnoreCase("north") && !arg.equalsIgnoreCase("south") && !arg.equalsIgnoreCase("east") && !arg.equalsIgnoreCase("west")){
			syntaxError();
		} else {
			c = arg.charAt(0);
			switch (c){

			case 'n':
				temp = r.nDoorState;
				tempRoom = loc.getRoomFromID(r.northexit);
				switch (temp){
				case 0:
					main.output("There is no door there!");
					break;
				case 1:
					tempKey = i.getKeyFromID(r.nDoorKey);
					if (tempKey != null){
						main.output("You close the door.");
						r.nDoorState = 2;
						tempRoom.sDoorState = 2;
					} else {
						main.output("You don't have the key.");
						break;
					}
				case 2: 
					tempKey = i.getKeyFromID(r.nDoorKey);
					if (tempKey != null){
						main.output("You lock the door.");
						r.nDoorState = 3;
						tempRoom.sDoorState = 3;
						break;
					} else {
						main.output("You don't have the key.");
					}
					break;

				case 3:
					main.output("That door is already locked.");
					break;

				}
				break;
			case 's':
				temp = r.sDoorState;
				tempRoom = loc.getRoomFromID(r.southexit);

				switch (temp){
				case 0:
					main.output("There is no door there!");
					break;
				case 1:
					tempKey = i.getKeyFromID(r.sDoorKey);
					if (tempKey != null){
						main.output("You close the door.");
						r.sDoorState = 2;
						tempRoom.nDoorState = 2;
					} else {
						main.output("You don't have the key.");
						break;
					}
				case 2: 
					tempKey = i.getKeyFromID(r.sDoorKey);
					if (tempKey != null){
						main.output("You lock the door.");
						r.sDoorState = 3;
						tempRoom.nDoorState = 3;
						break;
					} else {
						main.output("You don't have the key.");
					}
					break;

				case 3:
					main.output("That door is already locked.");
					break;

				}
				break;
			case 'e':
				temp = r.eDoorState;
				tempRoom = loc.getRoomFromID(r.eastexit);
				switch (temp){
				case 0:
					main.output("There is no door there!");
					break;
				case 1:
					tempKey = i.getKeyFromID(r.eDoorKey);
					if (tempKey != null){
						main.output("You close the door.");
						r.eDoorState = 2;
						tempRoom.wDoorState = 2;
					} else {
						main.output("You don't have the key.");
						break;
					}
				case 2: 
					tempKey = i.getKeyFromID(r.eDoorKey);
					if (tempKey != null){
						main.output("You lock the door.");
						r.eDoorState = 3;
						tempRoom.wDoorState = 3;
						break;
					} else {
						main.output("You don't have the key.");
					}
					break;

				case 3:
					main.output("That door is already locked.");
					break;

				}
				break;
			case 'w':
				temp = r.wDoorState;
				tempRoom = loc.getRoomFromID(r.westexit);
				switch (temp){
				case 0:
					main.output("There is no door there!");
					break;
				case 1:
					tempKey = i.getKeyFromID(r.wDoorKey);
					if (tempKey != null){
						main.output("You close the door.");
						r.wDoorState = 2;
						tempRoom.eDoorState = 2;
					} else {
						main.output("You don't have the key.");
						break;
					}
				case 2: 
					tempKey = i.getKeyFromID(r.wDoorKey);
					if (tempKey != null){
						main.output("You lock the door.");
						r.wDoorState = 3;
						tempRoom.eDoorState = 3;
						break;
					} else {
						main.output("You don't have the key.");
					}
					break;

				case 3:
					main.output("That door is already locked.");
					break;

				}
				break;
			}
		}
	}

	Room cast(String word2, Room r, Rooms loc, Magic mag, Player p, Mobs mo, Items it, Skills skl){
		Room room  = r;
		if (word2.equalsIgnoreCase("null")) {
			main.output("Cast what?");
		}
		else {
			//String here = r.name;
			String spell;
			String target;
			String tempPrep;
			int temp = 0;
			do {
				tempPrep = word2.substring(temp, temp + 4);
				if (temp == word2.length() - 4){
					syntaxError();
					main.output("Try \"cast (spell) on (target)\".");
					break;
				}
				temp++;
			} while (!tempPrep.contentEquals(" on "));

			spell = word2.substring(0, temp - 1);
			target = word2.substring(temp + 3, word2.length());
			
			temp = mag.cast(spell, target, r, loc, p, mo, it);
			
		switch (temp) {
		case 0:
			break;
			
		case 1: //Oops, you just killed yourself with that spell.
			room = jumpToRoom(0,loc);
		    return room;
			
		case 2:
			attack(target, p, false, loc, mo, r, it, mag, skl);
			break;
		case 3:
			NPC tempnpc = mo.getNPCFromName(target);
			tempnpc.monsterize(mo);
			attack(tempnpc.name,p,false,loc,mo,r,it,mag, skl);
			break;
			
			
		}
		}

		return r;	
	}
	
	Room nickname(Room r, String input, Mobs mo, Items it){
		String type = "nothing";
		Mob tempMob = null;
		Item tempItem = null;
		//int length = input.length();
		String arg;
		String nick;
		String tempPrep;

		int tempInt = 0;
		do {
			tempPrep = input.substring(tempInt, tempInt + 4);
			if (tempInt == input.length() - 4){
				syntaxError();
				main.output("Try \"nickname (creature/object) as (nickname)\".");
				break;
			}
			tempInt++;
		} while (!tempPrep.contentEquals(" as "));

		arg = input.substring(0, tempInt - 1);
		nick = input.substring(tempInt + 3, input.length());

		if (it.getItemFromName(arg) != null){
			tempItem = it.getItemFromName(arg);
			type = "item";
		}
		if(it.getEquipFromName(arg) != null) {
			tempItem = it.getEquipFromName(arg);
			type = "equipment";
		}
		if(mo.getNPCFromName(arg) != null){
			tempMob = mo.getNPCFromName(arg);
			type = "NPC";
		}
		if (mo.getMonsterFromName(arg) != null){
			tempMob = mo.getMonsterFromName(arg);
			type = "monster";
		}
		
		if (tempMob != null){
			tempMob.setNick(nick);
			main.output("Nicknamed " + type + " " + tempMob.name + " as \"" + nick + "\".");
		} else if(tempItem != null){
			tempItem.setNick(nick);
			main.output("Nicknamed " + type + " " + tempItem.name + " as \"" + nick + "\".");
		}else {
			syntaxError();
		}
		
		
		return r;
	}
	
	String getArticle(Item ite) {
		if(ite.isUnique()) {
			return "the";
		}  else if (ite.getName().charAt(0) == 'a'|| ite.getName().charAt(0) == 'e' || ite.getName().charAt(0) == 'i' || ite.getName().charAt(0) == 'o' || ite.getName().charAt(0) == 'u')  {
			return "an";
		} else {
			return "a";
		}
	}
	
	String getArticle(Item ite, boolean cap) {
		if (cap){
		if(ite.isUnique()) {
			return "The";
		}  else if (ite.getName().charAt(0) == 'a'|| ite.getName().charAt(0) == 'e' || ite.getName().charAt(0) == 'i' || ite.getName().charAt(0) == 'o' || ite.getName().charAt(0) == 'u')  {
			return "An";
		} else {
			return "A";
		}
		} else {
			return getArticle(ite);
		}
	}
	
	String getArticle(equipment equ) {
		if(equ.isUnique()) {
			return "the";
		}  else if (equ.getName().charAt(0) == 'a'|| equ.getName().charAt(0) == 'e' || equ.getName().charAt(0) == 'i' || equ.getName().charAt(0) == 'o' || equ.getName().charAt(0) == 'u')  {
			return "an";
		} else {
			return "a";
		}
	}
	
	String getArticle(equipment equ, boolean cap) {
		if (cap){
		if(equ.isUnique()) {
			return "The";
		}  else if (equ.getName().charAt(0) == 'a'|| equ.getName().charAt(0) == 'e' || equ.getName().charAt(0) == 'i' || equ.getName().charAt(0) == 'o' || equ.getName().charAt(0) == 'u')  {
			return "An";
		} else {
			return "A";
		}
		} else {
			return getArticle(equ);
		}
	}
	
	String getArticle(Mob npc) {
		if(npc.isUnique()) {
			return npc.title;
		}  else if (npc.getName().charAt(0) == 'a'|| npc.getName().charAt(0) == 'e' || npc.getName().charAt(0) == 'i' || npc.getName().charAt(0) == 'o' || npc.getName().charAt(0) == 'u')  {
			return "an";
		} else {
			return "a";
		}
	}
	
	String getArticle(Mob npc, boolean cap) {
		if (cap){
		if(npc.isUnique()) {
			return npc.title;
		}  else if (npc.getName().charAt(0) == 'a'|| npc.getName().charAt(0) == 'e' || npc.getName().charAt(0) == 'i' || npc.getName().charAt(0) == 'o' || npc.getName().charAt(0) == 'u')  {
			return "An";
		} else {
			return "A";
		}
		} else {
			return getArticle(npc);
		}
	}

	void cols(String numCols){
	int tempInt = main.OUT_WIDTH;
	if (numCols == "null"){
	main.output("Output width is: " + main.OUT_WIDTH);
	} else {
	try {
		tempInt = Integer.parseInt(numCols);
		} catch (NumberFormatException e){
			main.output("Error parsing the number! Please try again.");
			tempInt = main.OUT_WIDTH;
			}
		main.OUT_WIDTH = tempInt;
		main.output("Output width set to " + main.OUT_WIDTH);
		int width = (main.OUT_WIDTH * 7)+10;
		int height = (width * 3)/4;
		main.frame.setSize(width+20,height+20);
		main.textWindow.setSize(width,height);
		main.entryField.setSize(width,20);

	}
	}
	
	
	Room Parse (String input, Rooms loc, Room r, Player p, Items i, Mobs mo, Magic mag, Skills skl) {

		main.out("\n");
		input = input.toLowerCase();
		int spaceAt = input.indexOf(' ');

		int length = input.length();
		String word1;
		String word2;

		if (spaceAt != -1) {
			word1 = input.substring(0, spaceAt);
			word2 = input.substring(spaceAt, length);
		}

		else {
			word1 = input.substring(0, length);
			word2 = "null";
		}

		word1 = word1.trim();
		if (word1.isEmpty()){
			word1 = "null";
		}

		if (word2.charAt(0) == ' ') {
			word2 = (String) word2.substring(1);
		}
		switch (word1) {
		case "l":
		case "examine":
		case "look": 
			look(r, word2,p, i, loc, mo);
			break;

		case "help":
			help(word2);
			break;

		case "quit":
		case "exit":
			main.output("Quitting game.");
			r = jumpToRoom(0, loc);
			break;
		case "items":
		case "i":
		case "inventory":
			inventory(p, i, loc);
			break;
		case "n":
		case "north":
			r = go("north", r, loc, p, i, mo);
			break;
		case "s":
		case "south":
			r = go("south", r, loc, p, i, mo);
			break;
		case "e":
		case "east":
			r = go("east", r, loc, p, i, mo);
			break;
		case "w":
		case "west":
			r = go("west", r, loc, p, i, mo);
			break;

		case "go":
			r = go(word2, r, loc, p, i, mo);
			break;
		case "grab":
		case "get":
		case "take":
			take(word2, loc, p, i, r);
			break;
		case "throw":
		case "lose":
		case "drop":
			drop(word2, loc, p, i, r);
			break;
		case "wear":
		case "hold":
		case "equip":
			equip(word2,p,i);
			break;

		case "telprot":
			if(main.DEBUG){
				int id = Integer.parseInt(word2);
				r = jumpToRoom(id,loc);
			} else {
				syntaxError();
			}
			break;

		case "stats":
		case "status":
			stats(p,i,r, mag, skl);
			break;

		case "talk":
		case "speak":
		case "converse":
		case "gab":
		case "chat":
			speak(word2,loc,p,mo,r, i, mag, skl);
			break;

		case "attack":
		case "kill":
		case "fight":
			r = attack(word2,p,true,loc,mo, r, i, mag, skl);
			break;

		case "use":
			r = use(word2,p,loc,mo,i, r, mag);
			break;

		case "drink":
			r = use(word2 + " on me", p, loc, mo, i, r, mag);
			break;

		case "map":
			minimap(r, loc, 3);
			break;

		case "open":
			open(word2,r,loc,i);
			break;

		case "unlock":
			unlock(word2,r,loc,i);
			break;

		case "close":
		case "shut":
			close(word2,r, loc);
			break;

		case "lock":
			lock(word2,r,loc,i);
			break;

		case "cast":
			r = cast(word2,r,loc,mag,p, mo, i, skl);
			break;
			
		case "charm":
		case "flatter":
			charm(word2,loc,p,mo,r,i);
			break;
			
		case "threaten":
		case "insult":
			insult(word2,loc,p,mo,r,i);
			break;
			
		case "nickname":
			nickname(r,word2,mo,i);
			break;

		case "col":
		case "cols":
		case "column":
		case "columns":
			cols(word2);
			break;


		default: syntaxError();


		}
		main.out("\n");
		//main.output("returning r value " + r);
		return r;	

	}

}

