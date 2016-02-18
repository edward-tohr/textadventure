
public class Item implements Cloneable{


	String desc = "No description has been defined for this item.";
	String name = "This item has no name.";
	String nick = "nada";
	boolean unique = false;
	Room location = null;
	String locatedAt = "Quit";
	static boolean equipped = false;
	int use = 0; // 0 is unusable, 1 is health potion, 2 is mana potion, 3 restores both HP and MP, 4 deals damage, 5 is key
	int use2 = 0; // Sub-effect of use. Controls how much healing/damage a potion does, which door a key unlocks, etc.
	int cost = 0;
	int numHave = 0;

	public Item() {

	}

	public Item(String n, String d, boolean u, String s) {
		desc = d;
		name = n;
		unique = u;
		locatedAt = s;
		nick = n;
	}

	public void setUse(int u, int v){
		use = u;
		use2 = v;

	}
	
	public void setNick (String nname){
		nick = nname;
	}
	
	public void autoNick(){
		nick = name;
	}
	
	public String getNick(){
		return nick;
	}

	public String getDesc() {
		return desc;
	}

	public String getName() {
		return name;
	}

	public boolean isUnique() {
		return unique;
	}

	public Room getLocation(){
		return location;
	}

	public String getLocatedAt(){
		return locatedAt;
	}

	public void setName(String n){
		name = n;
	}

	public void setDesc(String d){
		desc = d;
	}

	public void setUnique(Boolean u){
		unique = u;
	}

	public void setLocation(Room r) {
		location = r;
	}

	public void setLocatedAt(String s) {
		locatedAt = s;
	}

	public String look() {
		return desc;
	}

	public void move(String r, Rooms loc){
		Room temp= null;
		temp = loc.getRoomFromName(r);
		if (temp != null){
			this.location = temp;
			this.locatedAt = r;
		}	
	}

	public void move(Room r){
		this.location = r;
	}

	Item duplicateItem(Item i, Items it) {
		Item temp = null;
		try {
			temp = (Item) super.clone();
		} catch (CloneNotSupportedException e) {

			e.printStackTrace();
		}
		if (temp != null){
			do {
				String tempString = temp.name + " ";
				tempString = tempString.replaceAll("[^0123456789]", " ");
				tempString = tempString.trim();
				if (tempString.isEmpty()){
					tempString += String.valueOf(0);
				}
				int tempInt = Integer.parseInt(tempString);
				tempInt++;
				tempString = String.valueOf(tempInt);
				String tempString2 = i.name;
				tempString2 = tempString2.substring(0, tempString2.length());
				temp.name = tempString2.concat(tempString);
			} while (it.itemVector.contains(it.getItemFromName(temp.name)));			


			it.itemVector.addElement(temp);
		}
		return temp;
	}

	void use(Player p, NPC n, Monster m, Room r, Items it, Magic ma){
		switch (use){
		case 0: // Unusable items
			main.output("This item cannot be used.");
			break;
		case 1: //Health potions
			if (p != null){
				main.output("You drink the " + name + ".");
				int temp = use2;
				double rand = Math.random();
				rand *= (use2*5);
				int rando = (int)rand % (use2/2);
				int temp2 = rando;
				rand = Math.random();
				rand *= 100;
				rando = (int)rand % 2;
				if (rando == 1){
					temp += temp2;
				}
				else {
					temp -= temp2;
				}
				p.hp += temp;
				if (p.hp > p.maxHP) {
					p.hp = p.maxHP;
					main.output ("You are fully healed!");
				} else {
					main.output("You recover " + temp + " HP!");
				}

				Item remove = it.getItemFromName(this.getName());
				it.itemVector.remove(remove);

				break;

			} else if (n != null) {
				main.output ("You give the " + name + " to " + n.name + ".");
				int temp = use2;
				double rand = Math.random();
				rand *= (use2*5);
				int rando = (int)rand % (use2/2);
				int temp2 = rando;
				rand = Math.random();
				rand *= 100;
				rando = (int)rand % 2;
				if (rando == 1){
					temp += temp2;
				}
				else {
					temp -= temp2;
				}
				n.hp += temp;
				if (n.hp > n.maxHP) {
					n.hp = n.maxHP;
					main.output ("They look very healthy.");
				} else {
					main.output("They look to have recovered.");
				}

				Item remove = it.getItemFromName(this.getName());
				it.itemVector.remove(remove);
				break;

			} else if (m != null){
				main.output ("You give the " + name + " to " + m.name + ".");
				int temp = use2;
				double rand = Math.random();
				rand *= (use2*5);
				int rando = (int)rand % (use2/2);
				int temp2 = rando;
				rand = Math.random();
				rand *= 100;
				rando = (int)rand % 2;
				if (rando == 1){
					temp += temp2;
				}
				else {
					temp -= temp2;
				}
				m.hp += temp;
				if (m.hp > m.maxHP) {
					m.hp = m.maxHP;
					main.output ("They look very healthy.");
				} else {
					main.output("They look to have recovered.");
				}

				Item remove = it.getItemFromName(this.getName());
				it.itemVector.remove(remove);
				break;

			} else {
				main.output("That is not a valid target.");
				break;
			}

		case 2: //Mana potions.

			if (p != null){
				main.output ("You drink the " + name + ".");
				int temp = use2;
				double rand = Math.random();
				rand *= (use2*5);
				int rando = (int)rand % (use2/2);
				int temp2 = rando;
				rand = Math.random();
				rand *= 100;
				rando = (int)rand % 2;
				if (rando == 1){
					temp += temp2;
				}
				else {
					temp -= temp2;
				}
				p.mp += temp;
				if (p.mp > p.maxMP) {
					p.mp = p.maxMP;
					main.output ("You are fully alert!");
				} else {
					main.output("You recover " + temp + " MP!");
				}

				Item remove = it.getItemFromName(this.getName());
				it.itemVector.remove(remove);
				break;

			} else if (n != null) {
				main.output ("You give the " + name + " to " + n.name + ".");
				int temp = use2;
				double rand = Math.random();
				rand *= (use2*5);
				int rando = (int)rand % (use2/2);
				int temp2 = rando;
				rand = Math.random();
				rand *= 100;
				rando = (int)rand % 2;
				if (rando == 1){
					temp += temp2;
				}
				else {
					temp -= temp2;
				}
				n.mp += temp;
				if (n.mp > n.maxMP) {
					n.mp = n.maxMP;
					main.output ("They look very alert.");
				} else {
					main.output("They look to have more energy.");
				}
				Item remove = it.getItemFromName(this.getName());
				it.itemVector.remove(remove);
				break;

			} else if (m != null){
				main.output ("You give the " + name + " to " + m.name + ".");
				int temp = use2;
				double rand = Math.random();
				rand *= (use2*5);
				int rando = (int)rand % (use2/2);
				int temp2 = rando;
				rand = Math.random();
				rand *= 100;
				rando = (int)rand % 2;
				if (rando == 1){
					temp += temp2;
				}
				else {
					temp -= temp2;
				}
				m.mp += temp;
				if (m.mp > m.maxMP) {
					m.mp = m.maxMP;
					main.output ("They look very alert.");
				} else {
					main.output("They look to have more energy.");
				}
				Item remove = it.getItemFromName(this.getName());
				it.itemVector.remove(remove);
				break;

			} else {
				main.output("That is not a valid target.");
				break;
			}
			
		case 3: //Health and mana potions
			if (p != null){
				main.output ("You drink the " + name + ".");
				int temp = use2;
				double rand = Math.random();
				rand *= (use2*5);
				int rando = (int)rand % (use2/2);
				int temp2 = rando;
				rand = Math.random();
				rand *= 100;
				rando = (int)rand % 2;
				if (rando == 1){
					temp += temp2;
				}
				else {
					temp -= temp2;
				}
				p.hp += temp;
				p.mp += temp/2;
				if (p.hp > p.maxHP) {
					p.hp = p.maxHP;
					main.output ("You are fully healed!");
				} else {
					main.output("You recover " + temp + " HP!");
				}
				if (p.mp > p.maxMP){
					p.mp = p.maxMP;
					main.output ("You are completely refreshed!");
				} else {
					main.output ("You recover " + temp/2 + " MP!");
				}

				Item remove = it.getItemFromName(this.getName());
				it.itemVector.remove(remove);

				break;
					

			} else if (n != null) {
				main.output ("You give the " + name + " to " + n.name + ".");
				int temp = use2;
				double rand = Math.random();
				rand *= (use2*5);
				int rando = (int)rand % (use2/2);
				int temp2 = rando;
				rand = Math.random();
				rand *= 100;
				rando = (int)rand % 2;
				if (rando == 1){
					temp += temp2;
				}
				else {
					temp -= temp2;
				}
				n.hp += temp;
				n.mp += temp/2;
				if (n.hp > n.maxHP) {
					n.hp = n.maxHP;
					main.output ("They look very healthy.");
				} else {
					main.output("They look to have recovered.");
				}
				if (n.mp > n.maxMP) {
					n.mp = n.maxMP;
					main.output ("They look very refreshed.");
				} else {
					main.output("They look more alert.");
				}

				Item remove = it.getItemFromName(this.getName());
				it.itemVector.remove(remove);
				break;

			} else if (m != null){
				main.output ("You give the " + name + " to " + m.name + ".");
				int temp = use2;
				double rand = Math.random();
				rand *= (use2*5);
				int rando = (int)rand % (use2/2);
				int temp2 = rando;
				rand = Math.random();
				rand *= 100;
				rando = (int)rand % 2;
				if (rando == 1){
					temp += temp2;
				}
				else {
					temp -= temp2;
				}
				m.hp += temp;
				if (m.hp > m.maxHP) {
					m.hp = m.maxHP;
					main.output ("They look very healthy.");
				} else {
					main.output("They look to have recovered.");
				}
				
				if (m.mp > m.maxMP) {
					m.mp = m.maxMP;
					main.output ("They look very refreshed.");
				} else {
					main.output("They look more alert.");
				}

				Item remove = it.getItemFromName(this.getName());
				it.itemVector.remove(remove);
				break;

			} else {
				main.output("That is not a valid target.");
				break;
			}
		case 6:
			ma.spellVector.elementAt(use2).learn = (byte)0b11111111;
			ma.spellVector.elementAt(use2).level = 1;
		}
		
	}
}