import java.util.Vector;
import java.lang.String;

public class Rooms {

	public Rooms() {

	}

	Vector<Room> roomVector = new Vector<Room>(5,1);
	
	/*         
	 *           [13]
	 *            |
	 *           [11]-[19]-[20]
	 *            |    |    |
	 *           [08]-[18]-[21]
	 *            |    |    |
	 * [14]-[09]-[04]-[02]-[03]-[10]-[15]-[17]
	 *                 |
	 *                [01]
	 *                 |
	 *                [12]
	 *                 |
	 *                [16]
	 */

	void initalizeRooms() {
		Room r0 = new Room();
		r0.setDesc("If you see this room, the game failed to quit properly.");
		r0.setName("Quit");
		roomVector.addElement(r0);
		Room r1 = new Room("Entryway","An empty room used for testing.", 1, true, 2, true, 12, false, -1, false, -1);
		r1.nDoorState = 3;
		r1.nDoorKey = 1;
		r1.sDoorState = 2;
		roomVector.addElement(r1);
		Room r2 = new Room("Intersection","A four-way intersection.", 2, true, 8, true, 1, true, 3, true, 4);
		r2.sDoorKey = r1.nDoorKey;
		r2.sDoorState = r1.nDoorState;
		roomVector.addElement(r2);
		Room r3 = new Room("Shop","A plain, unadorned, square room.", 3, true, 17, false, -1, true, 10, true, 2);
		roomVector.addElement(r3);
		Room r4 = new Room("Sword Room","A dead-end, you worthless fuckbasket.", 4, false, -1, false, -1, true, 2, true, 9);
		roomVector.addElement(r4);
		Room r5 = new Room();
		r5.setName("Inventory");
		roomVector.addElement(r5);
		Room r6 = new Room("Opening","A vast, green field spreads before you.", 6, false, -1, false, -1, false, -1, false, -1);
		roomVector.addElement(r6);
		Room r7 = new Room();
		r7.setName("null");
		roomVector.addElement(r7);
		Room r8 = new Room("Empty", "This room is suspiciously empty, as though someone recently came through here and removed everything.", 8, true, 11, true, 2, true, 17, false, -1);
		roomVector.addElement(r8);
		Room r9 = new Room("Barracks", "Small cots line the walls, and a firepit lights the foom from the center.", 9, false, -1, false, -1, true, 4, true, 14);
		roomVector.addElement(r9);
		Room r10 = new Room("Generic", "A bland, boring, generic room. Not very interesting.",10, true, 20, false, -1, true, 15, true, 3);
		roomVector.addElement(r10);
		Room r11 = new Room("Room 11","description",11,true,13,true,8,true,18,false,-1);
		Room r12 = new Room("Plotguy Room","This room is large, with a solid wooden floor. Bits of sunlight filter through the unrendered ceiling.",12,true,1,true,16,false,-1,false,-1);
		r12.sDoorState = 3;
		r12.sDoorKey = 0;
		r12.nDoorState = r1.sDoorState;
		Room r13 = new Room("Room 13","description",13,false,-1,true,11,false,-1,false,-1);
		Room r14 = new Room("Boss Room","description",14,false,-1,false,-1,true,9,false,-1);
		Room r15 = new Room("Room 15","description",15,false,-1,false,-1,true,17,true,10);
		Room r16 = new Room("Room 16","description",16,true,12,false,-1,false,-1,false,-1);
		roomVector.addElement(r11);
		roomVector.addElement(r12);
		roomVector.addElement(r13);
		roomVector.addElement(r14);
		roomVector.addElement(r15);
		roomVector.addElement(r16);
		Room r17 = new Room("Room 17", "Time and space are warped here.", 17, true, 18, true, 3, true, 20, true, 8);
		Room r18 = new Room("Room 18", "Time and space are warped here.", 18, false, -1, true, 17, true, 19, true, 11);
		Room r19 = new Room("Room 19", "Time and space are warped here.", 19, false, -1, true, 20, false, -1, true, 18);
		Room r20 = new Room("Room 20", "Time and space are warped here.", 20, true, 19, true, 10, false, -1, true, 17);
		roomVector.addElement(r17);
		roomVector.addElement(r18);
		roomVector.addElement(r19);
		roomVector.addElement(r20);
	}


	Room getRoom(int index) {
		Room r = roomVector.elementAt(index);
		return r;
	}

	Room getRoomFromName(String name){
		Room i = null;
		for (int n = 0; n < roomVector.size(); n++){
			String test = roomVector.elementAt(n).getName();
			if (name.equalsIgnoreCase(test)){
				i = roomVector.elementAt(n);
			}
		}
		return i;
	}
	
	Room getRoomFromID(int id){
			Room i = null;
			for (int n = 0; n < roomVector.size(); n++){
				int test = roomVector.elementAt(n).getId();
				if (test == id){
					i = roomVector.elementAt(n);
				}
			}
			return i;
	}

}
