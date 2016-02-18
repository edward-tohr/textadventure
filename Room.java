public class Room {



	String desc = "This room has no description defined.";

	int id = 0;

	boolean north = false;
	boolean south = false;
	boolean east = false;
	boolean west = false;
	int nDoorState = 0; //0 is nonexistant, 1 is open, 2 is closed, 3 is locked.
	int sDoorState = 0;
	int eDoorState = 0;
	int wDoorState = 0;
	int nDoorKey = 0; //Links up to the use2 of a key. 0 is reserved for non-lockable doors.
	int sDoorKey = 0;
	int eDoorKey = 0;
	int wDoorKey = 0;

	int northexit = -1;
	int southexit = -1;
	int eastexit = -1;
	int westexit = -1;
	boolean hasItem = false;
	boolean hasEquip = false;
	int numItems = 0;
	int numEquips = 0;


	String name = "NULL";

	Room(){
	}

	Room(String na, String d, int i, boolean n, int no, boolean s, int so, boolean e, int ea, boolean w, int we) {
		name = na;
		desc = d;
		id = i;
		north = n;
		northexit = no;
		south= s;
		southexit= so;
		east= e;
		eastexit= ea;
		west= w;
		westexit= we;
	}

	void setDesc(String s) {
		desc = s;
	}

	void setId(int i) {
		id = i;
	}

	void setExits(boolean n, int no, boolean s, int so, boolean e, int ea, boolean w, int we) {
		north = n;
		south = s;
		east = e;
		west = w;
		northexit = no;
		southexit = so;
		eastexit = ea;
		westexit = we;

	}

	void setName(String n) {
		name = n;
	}

	void setItem(Item i, String n, String d, boolean u, String s){
		i = new Item(n,d,u,s); 
		i.desc = d;
		i.name = n;
		i.unique = u;
		i.locatedAt = s;
		numItems++;
		hasItem = true;
	}

	void setEquip(equipment e, String n, String d, boolean u, String l, int t, int i, int p, byte eq){
		e =  new equipment();
		e.name = n;
		e.desc = d;
		e.unique = u;
		e.locatedAt = l;
		e.type = t;
		e.id = i;
		e.pow =  p;
		e.equipBy = eq
				;
		numEquips++;
		hasEquip = true;
	}



	String getDesc() {
		return desc;
	}

	String getName() {
		return name;
	}

	int getId() {
		return id;
	}

	boolean canNorth() {
		if(!north){
			return false;
		}
		if (nDoorState == 0 || nDoorState == 1) {
			return true;
		} else {
			return false;
		}
	}

	boolean canSouth() {
		if(!south){
			return false;
		}
		if (sDoorState == 0 || sDoorState == 1) {
			return true;
		} else {
			return false;
		}
	}


	boolean canEast() {
		if(!east){
			return false;
		}
		if (eDoorState == 0 || eDoorState == 1) {
			return true;
		} else {
			return false;
		}
	}

	boolean canWest() {
		if(!west){
			return false;
		}
		if (wDoorState == 0 || wDoorState == 1) {
			return true;
		} else {
			return false;
		}
	}


	String getExits() {
		String exits;
		int numExits = 0;

		if (north == true) {
			numExits++;
		}

		if (south == true) {
			numExits++;
		}

		if (east == true) {
			numExits++;
		}

		if (west == true) {
			numExits++;
		}

		if (numExits > 1) {
			exits = "Obvious exits are ";

			if (north == true && numExits > 2) {
				exits += "north, ";
				numExits--;
			} 
			else if (north == true && numExits == 2) {
				exits += "north ";
				numExits--;

			} 
			else if (north == true && numExits == 1) {

				exits += "and north.";
			}

			if(south == true && numExits > 2) {
				exits += "south, ";
				numExits--;
			} 
			else if (south == true && numExits == 2) {
				exits += "south ";
				numExits--;

			} 
			else if (south == true && numExits == 1) {
				exits += "and south.";
			}


			if (east == true && numExits > 2) {
				exits += "east, ";
				numExits--;
			} 
			else if (east == true && numExits == 2) {
				exits += "east ";
				numExits--;

			} 
			else if (east == true && numExits == 1) {
				exits += "and east.";
			}


			if (west == true && numExits > 2) {
				exits += "west, ";
				numExits--;
			} 
			else if (west == true && numExits == 2) {
				exits += "west ";
				numExits--;

			} 
			else if (west == true && numExits == 1) {
				exits += "and west.";
			}
		}
		else if (numExits == 1) {
			exits = "Obvious exit is ";
			if (north == true) {
				exits += "north.";
			}

			if (south == true) {
				exits += "south.";
			}

			if (east == true) {
				exits += "east.";
			}

			if (west == true) {
				exits += "west.";
			}
		}
		else if (numExits == 0) {
			exits = "There are no obvious exits.";
		}
		else {
			exits = "There has been an error determining the exits. Yea, repent, all, for the end is nigh.";
		}

		return exits;
	}

	String getDoors() {
		String exits;
		int numExits = 0;

		if (nDoorState != 0) {
			numExits++;
		}

		if (sDoorState != 0) {
			numExits++;
		}

		if (eDoorState != 0) {
			numExits++;
		}

		if (wDoorState != 0) {
			numExits++;
		}

		if (numExits > 1) {
			exits = "There are doors in the ";

			if (nDoorState != 0 && numExits > 2) {
				exits += "north, ";
				numExits--;
			} 
			else if (nDoorState != 0 && numExits == 2) {
				exits += "north ";
				numExits--;

			} 
			else if (nDoorState != 0 && numExits == 1) {

				exits += "and north walls.";
			}

			if(sDoorState != 0 && numExits > 2) {
				exits += "south, ";
				numExits--;
			} 
			else if (sDoorState != 0 && numExits == 2) {
				exits += "south ";
				numExits--;

			} 
			else if (sDoorState != 0 && numExits == 1) {
				exits += "and south walls.";
			}


			if (eDoorState != 0 && numExits > 2) {
				exits += "east, ";
				numExits--;
			} 
			else if (eDoorState != 0 && numExits == 2) {
				exits += "east ";
				numExits--;

			} 
			else if (eDoorState != 0 && numExits == 1) {
				exits += "and east walls.";
			}


			if (wDoorState != 0 && numExits > 2) {
				exits += "west, ";
				numExits--;
			} 
			else if (wDoorState != 0 && numExits == 2) {
				exits += "west ";
				numExits--;

			} 
			else if (wDoorState != 0 && numExits == 1) {
				exits += "and west walls.";
			}
		}
		else if (numExits == 1) {
			exits = "There is a door in the ";
			if (nDoorState != 0) {
				exits += "north wall.";
			}

			if (sDoorState != 0) {
				exits += "south wall.";
			}

			if (eDoorState != 0) {
				exits += "east wall.";
			}

			if (wDoorState != 0) {
				exits += "west wall.";
			}
		}
		else if (numExits == 0) {
			exits = "";
		}
		else {
			exits = "There has been an error determining the doors. All is lost.";
		}

		return exits;
	}

	int getNorth() {
		return northexit;
	}

	int getSouth() {
		return southexit;
	}

	int getEast() {
		return eastexit;
	}

	int getWest() {
		return westexit;
	}

	Room changeRoom(Room ro){

		return ro;
	}


	void removeItem(Item i, String r, Rooms loc) {
		i.move(r, loc);
		numItems--;
		if (numItems == 0){
			this.hasItem = false;
		}

	}

	void removeEqu(equipment i, String r, Rooms loc) {
		i.move(r, loc);
		numEquips--;
		if (numEquips == 0){
			this.hasEquip = false;
		}
	}

	void setMob(Mob m){
		m = new Mob();
	}

	void setNPC(NPC n){
		n = new NPC();
	}

	void setMon(Monster m){
		m = new Monster();
	}

	void removeMob(Mob m){
		m = null;
	}

	void removeNPC(NPC n){
		n = new NPC();
	}

	void removeMon(Monster m){
		m = new Monster();
	}

	void rebuildItemArray(){

	}
	
}
