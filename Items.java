//import java.io.Serializable;
import java.util.Vector;


public class Items implements Cloneable{


	public Items() {
	}

	Vector<Item> itemVector = new Vector<Item>(4,1);
	Vector<equipment> equipVector = new Vector<equipment>(1,1);
	
	String[] DaggerVerb = {"stab", "cut", "shank"};
	String[] SwordVerb = {"stab", "cut", "hack"};
	String[] KatanaVerb = {"slice", "slash", "cut"};
	String[] AxeVerb = {"hack", "chop", "axe"};
	String[] PunchVerb = {"punch", "jab", "strike"};
	String[] StickVerb = {"smack", "hit", "beat"};
	String[] HammerVerb = {"bludgeon", "smash", "beat"};
	String[] RapierVerb = {"poke", "stab", "pierce"};
	String[] tempVerb;

	public void initializeItems() {

		Item i0 = new Item();
		i0.setName("null");
		itemVector.addElement(i0);
		
		Item i3 = new Item("aardvark", "Some sort of animal.", false, "Entryway");
		itemVector.addElement(i3);

		Item i4 = new Item("thingy", "It's... a thing. I guess.", true, "Boss Room");
		itemVector.addElement(i4);

		Item i5 = new Item("Health Potion", "A foul-smelling, horrid-tasting concoction that nonetheless heals wounds.", false, "Inventory");
		i5.setUse(1, 5);
		i5.cost = 10;
		itemVector.addElement(i5);

		Item i6 = new Item("Mana Potion", "A dark, bitter brew that increases alertness and removes fatigue.", false, "null");
		i6.setUse(2, 5);
		i6.cost = 10;
		itemVector.addElement(i6);
		
		Item i8 = new Item("Ambi Potion", "Bitter-tasting, vile-smelling, and generally unpleasant to consume, this viscous tonic nevertheless heals wounds and removes fatigue.", false, "Inventory");
		i8.setUse(3, 5);
		i8.cost = 30;
		itemVector.addElement(i8);
		
		Item i9 = new Item("Full Potion", "This tincture, though possessing an unholy stench, an even worse taste, and a texture not unlike slimy rot, somehow manages to completely revitalize the drinker.", false, "Inventory");
		i9.setUse(3, 999);
		i9.cost = 0;
		itemVector.addElement(i9);
		
		Item k1 = new Item("key", "A key that opens a door. \"ENTRYWAY\" is engraved on it.", false, "Inventory");
		k1.use = 5;
		k1.use2 = 1;
		itemVector.addElement(k1);
		
		Item k2 = new Item("Goblin\'s Head", "The horrendously ugly head of Gary, the Goblin King", true, "null");
		k2.setUse(5, 2);
		itemVector.addElement(k2);
		
		Item k3 = new Item ("Tutorial Key", "A bejewelled key, with \"PLOTGUY ROOM\" engraved into it in a flowing script.", true, "null");
		k3.setUse(5,3);
		itemVector.addElement(k3);
		
		Item sb0 = new Item ("Test Book", "A grimoire with \"DEBUG ITEM\" written on the cover.", true, "Inventory");
		sb0.use = 6;
		sb0.use2 = 17;
		itemVector.addElement(sb0);


		equipment w0 = new equipment("dagger", "A short metal blade used for stabbing.", false, "null", 0, 0, 1, (byte)0b11111111);
		w0.setVerb(DaggerVerb);
		equipVector.addElement(w0);
		equipment w1 = new equipment("sword", "A sharp chunk of metal with a less-sharp bit on one end.", false, "Sword Room", 0, 1, 5, (byte)0b00000101);
		w1.setVerb(SwordVerb);
		equipVector.addElement(w1);
		equipment h0 = new equipment("cap", "A small, leather cap. Protects the head.", false, "Inventory", 1, 0, 1, (byte)0b11111111);
		h0.numHave++;
		equipVector.addElement(h0);
		equipment c0 = new equipment("vest", "A small, leather vest. Protects the body.", false, "Inventory", 2, 0, 1, (byte)0b11111111);
		c0.numHave++;
		equipVector.addElement(c0);
		equipment g0 = new equipment("gloves", "A pair of small, leather gloves. Protects the hands.", false, "Inventory", 3, 0, 1, (byte)0b11111111);
		g0.numHave++;
		equipVector.addElement(g0);
		equipment l0 = new equipment("leggings", "A pair of small, leather leggings. Protects the legs.", false, "Inventory", 4, 0, 1, (byte)0b11111111);
		l0.numHave++;
		equipVector.addElement(l0);
		equipment b0 = new equipment("boots", "A pair of small, leather boots. Protects the feet.", false, "Inventory", 5, 0, 1, (byte)0b11111111);
		b0.numHave++;
		equipVector.addElement(b0);
		equipment c1 = new equipment("chainmail", "A shirt made of interlinked metal rings. Provides good defense.", false, "Intersection", 2, 2, 5, (byte)0b00100111);
		equipVector.addElement(c1);
		equipment w2 = new equipment("longsword", "A three-foot length of sharpened steel.", false, "Sword Room", 0, 2, 7, (byte)00000101);
		w2.setVerb(SwordVerb);
		equipVector.addElement(w2);
		equipment w3 = new equipment("staff", "A six-foot long, thick, gnarled stick. Looks fearsome, but not a very effective weapon.", false, "null", 0,3,1,(byte)0b00001001);
		w3.setVerb(StickVerb);
		equipVector.addElement(w3);
		equipment w4 = new equipment("flail", "A short wooden handle, with a thick iron ball attached via chain.", false, "null", 0 , 4, 5, (byte)0b00010101);
		w4.setVerb(HammerVerb);
		equipVector.addElement(w4);
		equipment w5 = new equipment("hammer", "A thick, heavy block of metal attached to a long handle. Vicious.", false, "null", 0, 5, 5, (byte)0b00000101);
		w5.setVerb(HammerVerb);
		equipVector.addElement(w5);
		equipment w6 = new equipment("rapier", "A thin-bladed sword, good for deft thrusting.", false, "null", 0, 6, 3, (byte)0b00100001);
		w6.setVerb(RapierVerb);
		equipVector.addElement(w6);
		equipment w7 = new equipment("katana", "Three feet of masterfully-folded hanzo steel. A tool of justice. Seriously, guys, stop laughing at me! I mean it!", false, "null", 0, 7, 7, (byte)0b00001001);
		w7.setVerb(KatanaVerb);
		equipVector.addElement(w7);
		equipment w8 = new equipment("Bill-Bec-de-Corbin-Glaive-Guisarme-Halberd-Ranseur", "It... um... it... it's a polearm.", true, "null", 0, 8,10, (byte)0b00001001);
		String[] tempVerb = {"um... s-stab? I think?", "deliver some sort of attack to", "um... cu- no, not a cut... more of a slashy-stab-hack-bludgeon-tickle, really... something. You do something to"};
		w8.setVerb(tempVerb);
		equipVector.addElement(w8);
	}

	void move(Item i, Room r){
		for (int n = 0; n < itemVector.size(); n++){
			Item temp = itemVector.elementAt(n);
			if (i.equals(temp)){
				itemVector.removeElement(i);
				temp.location = r;
				temp.locatedAt = r.name;
				itemVector.addElement(temp);
			}
		}
	}


	void move(equipment e, Room r){
		for (int n = 0; n < equipVector.size(); n++){
			equipment temp = equipVector.elementAt(n);
			if (e.equals(temp)){
				equipVector.removeElement(e);
				temp.location = r;
				temp.locatedAt = r.name;
				equipVector.addElement(temp);
			}
		}
	}

	Item getItem(int index) {
		Item i = itemVector.elementAt(index);
		return i;
	}

	Item getItemFromName(String name){
		Item i = null;
		for (int n = 0; n < itemVector.size(); n++){
			String test = itemVector.elementAt(n).getName();
			String nname = itemVector.elementAt(n).getNick();
			if (name.equalsIgnoreCase(test) || name.equalsIgnoreCase(nname)){
				i = itemVector.elementAt(n);
			}
		}

		return i;

	}
	
	Item getKeyFromID(int id){
		Item i = null;
		Vector<Item> ite = new Vector<Item>(1,1);
		
		for (int n=0; n < itemVector.size(); n ++){
			if (itemVector.elementAt(n).locatedAt.equalsIgnoreCase("Inventory") && itemVector.elementAt(n).use == 5) {
				ite.addElement(itemVector.elementAt(n));
			}
		}
			
		for (int n = 0; n < ite.size(); n++){
			int test = ite.elementAt(n).use2;
			if (test == id){
				i = ite.elementAt(n);
			}
		}

		return i;

	}

	Vector<Item> getItemsByRoom(String r){
		Vector<Item> roomItems = new Vector<Item>(1,1);
		for (int i=0; i < itemVector.size(); i ++){
			if (itemVector.elementAt(i).locatedAt.equalsIgnoreCase(r)) {
				roomItems.addElement(itemVector.elementAt(i));
			}
			

		}
		return roomItems;
	}

	Vector<Item> getItemsInInventory(){
		Vector<Item> roomItems = new Vector<Item>(1,1);
		for (int i=0; i < itemVector.size(); i ++){
			if (itemVector.elementAt(i).locatedAt.equalsIgnoreCase("Inventory")) {
				roomItems.addElement(itemVector.elementAt(i));
			}

		}
		return roomItems;
	}

	Vector<equipment> getEquipsByRoom(String r){
		Vector<equipment> roomEquip = new Vector<equipment>(1,1);
		for (int i=0; i < equipVector.size(); i ++){
			if (equipVector.elementAt(i).locatedAt.equalsIgnoreCase(r)) {
				roomEquip.addElement(equipVector.elementAt(i));
			}

		}
		return roomEquip;
	}

	Vector<equipment> getEquipsInInventory(){
		Vector<equipment> roomEquip = new Vector<equipment>(1,1);
		for (int i=0; i < equipVector.size(); i ++){
			if (equipVector.elementAt(i).locatedAt.equalsIgnoreCase("Inventory")) {
				roomEquip.addElement(equipVector.elementAt(i));
			}

		}
		return roomEquip;
	}

	equipment getEquipment(int index) {
		equipment i = equipVector.elementAt(index);
		return i;
	}

	equipment getEquipFromName(String name){
		equipment i = null;
		for (int n = 0; n < equipVector.size(); n++){
			String test = equipVector.elementAt(n).getName();
			String nname = equipVector.elementAt(n).getNick();
			if (name.equalsIgnoreCase(test) || name.equalsIgnoreCase(nname)){
				i = equipVector.elementAt(n);
			}
		}

		return i;

	}

	equipment getEquipFromID(int id){
		equipment i = null;
		for (int n = 0; n < equipVector.size(); n++){
			int test = equipVector.elementAt(n).getId();
			if (test == id){
				i = equipVector.elementAt(n);
			}
		}

		return i;

	}

	Vector<Item> getUsableItems(){
		Vector<Item> i = new Vector<Item>(1,1);
		i = getItemsInInventory();
		for (int n=0; n < i.size(); n ++){
			if (i.elementAt(n).use == 0 || i.elementAt(n).use == 5) {
				i.removeElement(itemVector.elementAt(n));
			}
		}
		return i;
	}
	
	Vector<equipment> getWeapons(){
		Vector<equipment> i = new Vector<equipment>(1,1);
		for (int n=0; n < equipVector.size(); n ++){
			if (equipVector.elementAt(n).type == 0 && equipVector.elementAt(n).locatedAt.equalsIgnoreCase("Inventory")) {
				i.addElement(equipVector.elementAt(n));
			}
		}
		return i;
	}


	void populateItems(Rooms loc, Player p){
		int itemLen = itemVector.size();
		int equipLen = equipVector.size();

		Item ite;
		Room rom;
		equipment equ;
		String loca;

		for (int i = 0; i < itemLen; i++) {

			loca = itemVector.elementAt(i).getLocatedAt();
			if (loca != null){
			rom = loc.getRoomFromName(loca);
			ite = itemVector.elementAt(i);
			ite.setLocation(rom);
			rom.setItem(ite, ite.name, ite.desc, ite.unique, ite.locatedAt);
			if (loca.equalsIgnoreCase("Inventory")){
				p.numItems++;
			}
			}

		}

		for (int i = 0; i < equipLen; i++) {

			loca = equipVector.elementAt(i).getLocatedAt();
			rom = loc.getRoomFromName(loca);
			equ = equipVector.elementAt(i);
			equ.setLocation(rom);
			rom.setEquip(equ, equ.name, equ.desc, equ.unique, equ.locatedAt, equ.type, equ.id, equ.pow, equ.equipBy);
			if (loca.equalsIgnoreCase("Inventory")){
				p.numItems++;
			}

		}
	}

	void setEquippable(Player p){
		byte temp = p.role;
		byte tempByte;
		boolean tempBool= false;
		equipment tempEquip;
		int equipLen = equipVector.size();
		for (int i = 0; i < equipLen; i++ ){
			tempBool = false;
			tempEquip = equipVector.elementAt(i);
			tempByte = tempEquip.getEquipppableBy();
			tempByte = (byte) (temp & tempByte);
			if (tempByte != 0){
				tempBool = true;
			}
			equipVector.elementAt(i).canEquip = tempBool;

		}
	}
}
