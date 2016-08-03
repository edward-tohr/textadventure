import java.util.Vector;


public class Mobs {


	public Mobs() {
	}

	Vector<NPC> npcVector = new Vector<NPC>(1,1);
	Vector<Monster> monVector = new Vector<Monster>(1,1);

	public void initializeMobs() {

		NPC n0 = new NPC();
		n0.setName("null");
		npcVector.addElement(n0);

		NPC n1 = new NPC();
		n1.setLocatedAt("Entryway");
		npcVector.addElement(n1);

		NPC n2 = new NPC();
		n2.setPosition("snarling viciously");
		n2.setName("aardvark");
		n2.setLocatedAt("Entryway");
		n2.setDesc("A nocturnal burrowing mammal with long ears, a tubular snout, and a long extensible tongue, feeding on ants and termites. This one seems a bit more sapient than others of his kind.");
		n2.setUnique(false);
		npcVector.addElement(n2);

		NPC n3 = new NPC();
		n3.setPosition("running in circles");
		n3.setName("kravdraa");
		n3.setLocatedAt("Entryway");
		n3.setDesc("A diurnal flying reptile with short ears, a small, jagged nose, and taste receptors on its teeth, feeding on cacti and eagles. This one seems to be more energetic than others of his kind.");
		n3.setHp(10);
		n3.setUnique(false);
		npcVector.addElement(n3);

		NPC n4 = new NPC();
		n4.setPosition("hawking his wares");
		n4.setTitle("Item Merchant");
		n4.setName("Derpus");
		n4.setNick("Item Merchant");
		n4.setDesc("Sweaty, wearing striped pantaloons and a matching poofy hat, this merchant looks like his name implies. Of course, it's not a good idea to anger the guy who sells you health potions.");
		n4.setUnique(true);
		String[] sn4Inv = {"Health Potion", "Mana Potion"};
		n4.inventory = sn4Inv;
		n4.setLocatedAt("Shop");
		n4.type = 1;
		npcVector.addElement(n4);

		NPC n5 = new NPC();
		n5.setPosition("hawking his wares");
		n5.setTitle("Equipment Merchant");
		n5.setName("Herpus");
		n5.setNick("Equipment Merchant");
		n5.setDesc("Sweaty, wearing striped pantaloons and a matching poofy hat, this merchant looks like his name implies. Of course, angering the guy with the collection of weapons on his back is a bad idea.");
		n5.setUnique(true);
		String[] sn5Inv = {"dagger", "cap", "vest"};
		n5.inventory = sn5Inv;
		n5.setLocatedAt("Shop");
		n5.type = 2;
		npcVector.addElement(n5);
		
		NPC n6 =  new NPC();
		n6.setPosition("meditating on a solid table");
		n6.setName("The Tutorial Guru");
		n6.setNick("guru");
		n6.setDesc("He looks like a guru.");
		n6.setUnique(true);
		n6.type = 3;
		n6.setLocatedAt("Plotguy Room");
		npcVector.addElement(n6);

		Monster m0 = new Monster();
		m0.setName("null");
		monVector.addElement(m0);

		Monster m1 = new Monster();
		m1.setName("Aardvarkius Maximus");
		m1.setNick("AM");
		m1.setTitle("The Immortal");
		m1.setPosition("being an affront to the laws of Man and God");
		m1.setAtk(2);
		m1.setDef(0);
		String[] sm1BP = {"snout", "face", "body", "tail"};
		m1.setBodyplan(sm1BP);
		String[] sm1S = {"How's it going?", "You look well.", "I'm just sitting here, enjoying my tea."};
		m1.setSpeech(sm1S);
		m1.setDesc("Dude, it's just a freaking aardvark. Chill.");
		m1.setDex(5);
		m1.setStr(5);
		m1.setHp(10);
		m1.setFlee(0);
		String[] sm1Atk = {"cut", "scratch", "rend asunder the very fabric of reality at"};
		m1.setCombat(sm1Atk);
		m1.setUnique(true);
		m1.setXp(100);
		m1.setGold(10);
		m1.setLocatedAt("Entryway");
		m1.weapon = "claws";
		m1.setDrop("Health Potion");
		m1.setDropchance(100);
		m1.personality = 3;
		monVector.addElement(m1);

		Monster m2 = new Monster();
		String[] sm2S = {"Yo!"};
		m2.setName("Tyrone-o-saurus");
		m2.setNick("tyrone");
		m2.setTitle("Reverend");
		m2.setPosition("skateboarding");
		m2.setLocatedAt("Entryway");
		m2.setDesc("It's a skateboarding T-Rex in a cassock and sunglasses.");
		m2.setAllSpeech(sm2S);
		m2.setXp(200);
		m2.setDex(5);
		m2.setHp(10);
		m2.setDef(0);
		monVector.addElement(m2);

		Monster m3 = new Monster();
		m3.setName("Terminator");
		m3.setTitle("The");
		m3.setPosition("terminating");
		m3.setDesc("Not a killer robot, fortunately.");
		m3.setLocatedAt("Entryway");
		monVector.addElement(m3);
		
		Monster m4 = new Monster();
		m4.setName("Goblin");
		m4.unique = false;
		m4.setPosition("roasting a rat on a spit");
		m4.setDesc("This creature is nasty, brutish, and short. It drools around its tusks.");
		m4.setLocatedAt("Barracks");
		m4.atk = 5;
		m4.def = 5;
		m4.maxHP = 10;
		m4.hp = 10;
		m4.setXp(15);
		m4.setGold(20);
		m4.weapon = "club";
		String[] sm4Atk = {"beat", "smack", "club", "bludgeon"};
		String[] sm4s = {"Huh? What do you want?", "What are you doing here?", "Tryin' to steal my dinner?"};
		String[] sm4Bp = {"head", "left tusk", "right tusk", "neck", "chest", "stomach", "fat", "left arm", "right arm", "left leg", "right leg"};
		String[] sm4E = {"Graah! Die!", "Murder!", "I'll chew your face off!", "I'll roast you for supper!"};
		m4.setCombat(sm4Atk);
		m4.setAllSpeech(sm4s);
		m4.setBodyplan(sm4Bp);
		m4.setEngage(sm4E);
		monVector.addElement(m4);
		
		Monster m5 = new Monster();
		m5.copyMob(m4);
		m5.setPosition("picking raw meat from his tusks");
		monVector.addElement(m5);
		
		Monster m6 = new Monster();
		m6.copyMob(m4);
		m6.setPosition("relaxing on a cot");
		monVector.addElement(m6);
				
		Monster b0 = new Monster();
		b0.setName("Gary");
		b0.setTitle("The Goblin King,");
		b0.setNick("king");
		b0.setPosition("oppressing the proletariat");
		b0.setDesc("Corpulent and snaggletoothed, this vicious goblin looks like he was beaten daily with the whole Ugly Tree. One of the tusks protruding from his mouth is broken.");
		b0.setLocatedAt("Boss Room");
		b0.maxHP = 100;
		b0.hp = 100;
		String[] sb0S = {"What do you want, peon?"};
		String[] sb0Atk = {"punch", "hit", "beat", "pound"};
		String[] sb0Bp = {"head", "left tusk", "neck", "chest", "stomach", "fat", "left arm", "right arm", "left leg", "right leg"};
		String[] sb0E = {"You little punk! I'll kill you!"}; 
		b0.setSpeech(sb0S);
		b0.setCombat(sb0Atk);
		b0.weapon = "fists";
		b0.setBodyplan(sb0Bp);
		b0.setEngage(sb0E);
		b0.setLocatedAt("Boss Room");
		b0.personality = 21;
		b0.atk = 10;
		b0.def = 10;
		monVector.addElement(b0);
		


	}

	NPC getNPC(int index) {
		NPC i = npcVector.elementAt(index);
		return i;
	}

	NPC getNPCFromName(String name){
		NPC i = null;
		for (int n = 0; n < npcVector.size(); n++){
			String test = npcVector.elementAt(n).getName();
			String nname = npcVector.elementAt(n).getNick();
			if (name.equalsIgnoreCase(test) || name.equalsIgnoreCase(nname)){
				i = npcVector.elementAt(n);
			}
		}

		return i;

	}

	Monster getMonster(int index) {
		Monster i = monVector.elementAt(index);
		return i;
	}

Monster getMonsterFromName(String name){
		Monster i = null;
		for (int n = 0; n < monVector.size(); n++){
			String test = monVector.elementAt(n).getName();
			String nname = monVector.elementAt(n).getNick();
			if (name.equalsIgnoreCase(test) || name.equalsIgnoreCase(nname)){
				i = monVector.elementAt(n);
			}
		}

		return i;

	}

	Monster getMonsterFromID(int id){
		Monster i = null;
		for (int n = 0; n < monVector.size(); n++){
			int test = monVector.elementAt(n).getId();
			if (test == id){
				i = monVector.elementAt(n);
			}
		}

		return i;

	}



	void move(Monster m, Room r){
		for (int n = 0; n < monVector.size(); n++){
			Monster temp = monVector.elementAt(n);
			if (m.equals(temp)){
				monVector.removeElement(m);
				temp.location = r;
				temp.locatedAt = r.name;
				monVector.addElement(temp);
			}
		}
	}

	void move(NPC i, Room r){
		for (int n = 0; n < npcVector.size(); n++){
			NPC temp = npcVector.elementAt(n);
			if (i.equals(temp)){
				npcVector.removeElement(i);
				temp.location = r;
				temp.locatedAt = r.name;
				npcVector.addElement(temp);
			}
		}
	}

	Vector<NPC> getNPCByRoom(String r, Rooms loc){
		Vector<NPC> roomNPC = new Vector<NPC>(1,1);
		//Room rom = loc.getRoomFromName(r);
		for (int i=0; i < npcVector.size(); i ++){
			if (npcVector.elementAt(i).locatedAt.equalsIgnoreCase(r)) {
				roomNPC.addElement(npcVector.elementAt(i));
			}

		}
		return roomNPC;
	}

	Vector<Monster> getMonsterByRoom(String r, Rooms loc){
		Vector<Monster> roomMonster = new Vector<Monster>(1,1);
		//Room rom = loc.getRoomFromName(r);
		for (int i=0; i < monVector.size(); i ++){
			if (monVector.elementAt(i).locatedAt.equalsIgnoreCase(r)) {
				roomMonster.addElement(monVector.elementAt(i));
			}

		}
		return roomMonster;
	}


	void populateMobs(Rooms loc){
		int npcLen = npcVector.size();
		int monLen = monVector.size();

		NPC npc;
		Room rom;
		Monster mon;
		String loca;

		for (int i = 0; i < npcLen; i++) {

			loca = npcVector.elementAt(i).getLocatedAt();
			rom = loc.getRoomFromName(loca);
			npc = npcVector.elementAt(i);
			move(npc, rom);
		}

		for (int i = 0; i < monLen; i++) {

			loca = monVector.elementAt(i).getLocatedAt();
			rom = loc.getRoomFromName(loca);
			mon = monVector.elementAt(i);
			move(mon, rom);


		}
	}


}
