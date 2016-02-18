public class equipment extends Item {
	int type = -1; // 0 == weapon, 1 == helmet, 2 == torso armor, 3 == gauntlets, 4 == leggings, 5 == boots, -1 == undefined
	int id = 0; //Unique for each type. Type + ID produces unique identifier for any equipment.
	int pow = 0; //Attack for weapons, defense for armor.
	boolean equipped = false; //Should be obvious.
	boolean canEquip = false; // Used in combination with equipBy to segregate equipment by class.
	byte equipBy = 0b00000000; //AND with player.role. If != 0, can equip. 	
	//0 0 0 0 0 0 0 0
	//| | | | | | |  \ Fighter
	//| | | | | |  \ Thief
	//| | | | |  \ Juggernaut
	//| | | |  \ Wizard
	//| | |  \ Healer
	//| |  \ Bard
	//|  \ Unused
	// \ Unused

	String[] verb = {"punch"}; // Used by weapons to display interesting combat messages.
	
	public String[] getVerb() {
		return verb;
	}
	public void setVerb(String[] verb) {
		this.verb = verb;
	}
	public equipment(String n, String d, boolean u, String l, int t, int i, int p, byte e){
		name = n;
		desc = d;
		unique = u;
		locatedAt = l;
		type = t;
		id = i;
		pow =  p;
		equipBy = e;
	}
	public equipment(){

	}

	equipment duplicateEquip(equipment i, Items it) {
		equipment temp = null;
		try {
			temp = (equipment) super.clone();
		} catch (CloneNotSupportedException e) {

			e.printStackTrace();
		}
		if (temp != null){
			do {
				String tempString = temp.name;
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
			} while (it.equipVector.contains(it.getEquipFromName(temp.name)));			


			it.equipVector.addElement(temp);
		}
		return temp;
	}

	public void setStats(int t, int i, int p){
		type = t;
		id = i;
		pow =  p;
	}

	public int getType() {
		return type;
	}

	public int getId() {
		return id;
	}

	public int getPow() {
		return pow;
	}

	public boolean isEquipped() {
		return equipped;
	}

	public byte getEquipppableBy(){
		return equipBy;
	}

	public void setEquippableBy(byte e){
		equipBy = e;
	}

}
