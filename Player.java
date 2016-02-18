import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;

public class Player extends Mob{
	public Player() {

	}

	public Player(String na, int s, int d, int c, int i, int w, int ch) {
		name = na;
		str = s;
		dex = d;
		vit = c;
		intel = i;
		wis = w;
		cha = ch;

	}

	int numItems = 0;
	String name = "Duder McGuyson";
	int str = 20;
	double strPart = 0;
	int dex = 20;
	double dexPart = 0;
	int vit = 20;
	double vitPart = 0;
	int intel = 20;
	double intelPart = 0;
	int wis = 20;
	double wisPart = 0;
	int cha = 20;
	double chaPart = 0;
	int hp = 10;
	int mp = 10;
	int maxHP = 10;
	int maxMP = 10;
	int hpMod = 1;
	int mpMod = 1;

	int level = 10;
	int xp = 0;
	int gold = 0;
	byte role = (byte)0b11111111;
	int intRole = 0;
	String roleName = "Adventurer";
	String[] roles = {"Adventurer", "Fighter", "Thief", "Juggernaut", "Wizard", "Healer", "Bard"};
	String[] castAni = {"cast a spell"};
	//Roles are classes, and affect stats in various ways. Fighters(1) get higher bonuses to their attack from their weapons, thieves(2) get a bonus to dodging, juggernauts(3) get bonus HP.
	//wizards(4) gain access to attack magic and MP based on int. Healers(5) have healing magic, based on WIS, and bards(6) gain a little of both, with MP based on charisma.
	// default role is adventurer, 0. Does not get bonuses.


	boolean repeat = false;

	int weap = 0;
	int helm = 0;
	int armr = 0;
	int glov = 0;
	int legs = 0;
	int boot = 0;

	int atk = 0;
	int def = 0;

	int tempHelm = 0;
	int tempArmr = 0;
	int tempGlov = 0;
	int tempLegs = 0;
	int tempBoot = 0;

	int combatStyle = 0;

	String weapName = "fist";
	String[] combat = {"punch"};

	String[] bodyplan = {"head", "neck", "torso", "left arm", "right arm", "left leg", "right leg"};

	int getNumItems() {
		return numItems;
	}

	void setNumItems(int i){
		numItems = i;
	}

	void equip(equipment e, Items i){
		equipment temp = null;
		if (e.canEquip) {
			if (e.equipped) {
				main.output("That's already equippped.");
			} else {
				if (role == 1) {
					e.pow *= 1.5;
				}

				switch (e.type) {
				case 0: //weapon
					if (weap != 0){
						temp = i.getEquipFromID(weap);
						temp.equipped = false;
						main.output("Unequipped " + temp.getName() + ".");
					}
					atk = e.pow;
					e.equipped = true;
					weap = e.id;
					weapName = e.name;
					combat = e.verb;
					break;
				case 1: //helmet
					if (helm != 0){
						temp = i.getEquipFromID(helm);
						temp.equipped = false;
						main.output("Unequipped " + temp.getName() + ".");
					}
					def -= tempHelm;
					tempHelm = e.pow;
					def += tempHelm;
					e.equipped = true;
					helm = e.id;
					break;
				case 2: //Body armor
					if (armr != 0){
						temp = i.getEquipFromID(armr);
						temp.equipped = false;
						main.output("Unequipped " + temp.getName() + ".");
					}
					def -= tempArmr;
					tempArmr = e.pow;
					def += tempArmr;
					e.equipped = true;
					armr = e.id;
					break;
				case 3: //Gloves
					if (glov != 0){
						temp = i.getEquipFromID(glov);
						temp.equipped = false;
						main.output("Unequipped " + temp.getName() + ".");
					}
					def -= tempGlov;
					tempGlov = e.pow;
					def += tempGlov;
					e.equipped = true;
					glov = e.id;
					break;
				case 4: //leggings
					if (legs != 0){
						temp = i.getEquipFromID(legs);
						temp.equipped = false;
						main.output("Unequipped " + temp.getName() + ".");
					}
					def -= tempLegs;
					tempLegs = e.pow;
					def += tempLegs;
					e.equipped = true;
					legs = e.id;
					break;
				case 5: //boots
					if (boot != 0){
						temp = i.getEquipFromID(boot);
						temp.equipped = false;
						main.output("Unequipped " + temp.getName() + ".");
					}
					def -= tempBoot;
					tempBoot = e.pow;
					def += tempBoot;
					e.equipped = true;
					boot = e.id;
					break;
				}
				main.output("Equipped " + e.getName() + ".");
			}
		} else {
			main.output("I cannot equip that.");
		}

	}


	void charGen() {
		level = 1;
		Scanner scanner = new Scanner( System.in );
		String input;
		boolean okay;
		int points;
		char choice;
		boolean goodName;
		do {
			okay = false;
			points = 65;
			goodName = false;
			main.output("Welcome to character creation! First off, what is your name?");
			while (!goodName){
				input = scanner.nextLine();
				name = input;
				main.output("Is " + name + " the name you want?");
				input = scanner.nextLine();
				input = input.toUpperCase();
				input = input.replaceAll("[^YN]", " ");
				input = input.trim();
				if (input.isEmpty()){
					input = "N";
				}
				choice = input.charAt(0);
				if (choice == 'Y'){
					goodName = true;
				}
			}

			main.output("A: Answer a series of questions to determine stats");
			main.output("B: Buy stat points from pool");
			main.output("C: Roll randomly for stats");
			input = scanner.nextLine();
			input = input.toUpperCase();
			input = input.replaceAll("[^ABCDZ]", " ");
			if (!main.DEBUG)
			input = input.replaceAll("[^ABCZ]", " ");
			input = input.trim();
			if (input.isEmpty()){
				input = "Z";
			}

			choice = input.charAt(0);
			switch (input){
			case "Z":
				break;
			case "A":
				int rand;
				str += 3;
				dex += 3;
				vit += 3;
				intel += 3;
				wis += 3;
				cha +=3;
				String[] question = {"Do you solve your problems with physical violence?","Do you prefer physical activity to studying?","Do you have trouble connecting with people due to your bulk?","Do you prefer powerful, wild attacks to more precise, weaker ones?","Do you often work out to the point of exhaustion?","Do you feel that understanding nature destroys its beauty?","Do you feel that common sense is a rare gift in this day and age?", "Are you sometimes so enamored with the beauty of the world that you forget to eat?", "Do you avoid physical violence at all costs?", "Do you slow down, and take time to appreciate the small things?","Do you have the desire to understand everything?","Do you feel that you cannot appreciate something without first understaning it completely?", "Do you find that you are unable to relate to those who know less than you?", "Do you often forsake physical activity in order to study?","Do you find yourself not acting until you have fully analyzed a situation?","Did you get through school by gaining favor with the professor, instead of doing the work?","Do you find that things naturally seem to be set up to help you?","You and a friend, who is larger than you, are moving some heavy boxes. Do you make sure he gets the heavier boxes?","Do you find yourself drawn to spending time with crowds?","When traveling to a somewhat distant destination, would you convince a friend to give you a ride, instead of walking?","Do you find that your body is particularly bulky?","Do you prefer running to weightlifting?","Do you often work harder, not smarter?","Are you able to shrug off injuries that would incapacitate others?", "Are you unusually stoic?","Do you find that your body is slender and agile?","Do you prefer to use less force, to achieve greater control?","Do you work with your fingers more than your mind?","Do you often rush into situations without first looking around?","Do you find yourself being too impatient to connect with others?"};


				main.output("You will be asked twenty questions about your personality. Please answer each one with either a Y or an N.");
				do {


					int[]qus = getUniqueRand();
					for(int i = 0; i < 20; i++){
						rand = qus[i];

						String q = question[rand];
						main.output(q + "\n");
						input = scanner.nextLine();
						input = input.toUpperCase();
						input = input.replaceAll("[^YN]", " ");
						input = input.trim();
						if (input.isEmpty()){
							input = "N";
						}
						choice = input.charAt(0);
						if (choice == 'Y') {
							if (rand == 0 | rand == 1 | rand == 2 | rand == 3 | rand == 4) {
								str++;
							}
							if (rand == 5 | rand == 6 | rand == 7 | rand == 8 | rand == 9) {
								wis++;
							}
							if (rand == 10 | rand == 11 | rand == 12 | rand == 13 | rand == 14) {
								intel++;
							}
							if (rand == 15 | rand == 16 | rand == 17 | rand == 18 | rand == 19) {
								cha++;
							}
							if (rand == 20 | rand == 21 | rand == 22 | rand == 23 | rand == 24) {
								vit++;
							}
							if (rand == 25 | rand == 26 | rand == 27 | rand == 28 | rand == 29) {
								dex++;
							}
							if (rand == 0 | rand == 11 | rand == 16 | rand == 23 | rand == 28) {
								wis--;
							}
							if (rand == 1 | rand == 5 | rand == 15 | rand == 22 | rand == 27) {
								intel--;
							}
							if (rand == 2 | rand == 6 | rand == 12 | rand == 24 | rand == 29) {
								cha--;
							}
							if (rand == 3 | rand == 9 | rand == 14 | rand == 18 | rand == 20) {
								dex--;
							}
							if (rand == 4 | rand == 7 | rand == 13 | rand == 19 | rand == 25) {
								vit--;
							}
							if (rand == 8 | rand == 10 | rand == 17 | rand == 21 | rand == 26) {
								str--;
							}
						}
					}

					main.output("STR: " + str + " DEX: " + dex + " VIT: " + vit);
					main.output("INT: " + intel + " WIS " + wis + " CHA: " + cha);
					main.output("Are these stats okay? (Y/N)");
					do {
						input = scanner.nextLine();
						input = input.toUpperCase();
						input = input.replaceAll("[^YN]", " ");
						input = input.trim();
						if (input.isEmpty()){
							input = "N";
						}
						choice = input.charAt(0);

					} while(choice != 'Y' && choice != 'N');

					if (choice == 'Y'){
						okay = true;

					}

					if (choice == 'N') {
						okay = false;
					}


				} while (!okay);
				break;
			case "B":
				int strCost;
				int dexCost;
				int vitCost;
				int intCost;
				int wisCost;
				int chaCost;
				char stat;
				boolean buy = true;

				do {
					strCost = str-8;
					intCost = intel-8;
					dexCost = dex-8;
					wisCost = wis-8;
					vitCost = vit-8;
					chaCost = cha-8;
					main.output("Points remaining: " + points);
					main.output("STR: " + str + "(" + strCost + ")   INT: " + intel + "(" + intCost + ")");
					main.output("DEX: " + dex + "(" + dexCost + ")   WIS: " + wis + "(" + wisCost + ")");
					main.output("VIT: " + vit + "(" + vitCost + ")   CHA: " + cha + "(" + chaCost + ")");
					main.output("Which stat would you like to change? ([S]tr, [D]ex, [V]it, [I]nt, [W]is, [C]ha? [F] to finish, [+] to enter increase mode, [-] to enter decrease mode)");
					input = scanner.nextLine();
					input = input.toUpperCase();
					input = input.replaceAll("[^SDVIWCF+-]", " ");
					input = input.trim();
					if (input.isEmpty()){
						input = "+";
					}
					stat = input.charAt(0);

					switch (stat){
					case 'S':
						if (buy){
							if (strCost > points){
								main.output("Not enough points!");
							} else {
								str++;
								points -= strCost;
							}
						} else{
							if (strCost > 0) {
								str--;
								points += strCost;
							} else {
								main.output("That stat cannot be decreased any lower.");
							}
						}
						break;
					case 'D':
						if (buy){
							if (dexCost > points){
								main.output("Not enough points!");
							} else {
								dex++;
								points -= dexCost;
							}
						} else {
							if (dexCost > 0){
								dex--;
								points += dexCost;
							} else {
								main.output("That stat cannot be decreased any lower.");
							}
						}
						break;
					case 'V':
						if (buy){
							if (vitCost > points){
								main.output("Not enough points!");
							} else {
								vit++;
								points -= vitCost;
							}
						} else {
							if (vitCost > 0){
								vit--;
								points += vitCost;
							} else {
								main.output("That stat cannot be decreased any lower.");
							}
						}
						break;
					case 'I':
						if (buy){
							if (intCost > points){
								main.output("Not enough points!");
							} else {
								intel++;
								points -= intCost;
							}
						} else {
							if (intCost > 0){
								intel--;
								points += intCost;
							} else {
								main.output("That stat cannot be decreased any lower.");
							}
						}
						break;
					case 'W':
						if (buy){
							if (wisCost > points){
								main.output("Not enough points!");
							} else {
								wis++;
								points -= wisCost;
							}
						} else {
							if (wisCost > 0){
								wis--;
								points += wisCost;
							} else {
								main.output("That stat cannot be decreased any lower.");
							}
						}
						break;
					case 'C':
						if (buy){
							if (chaCost > points){
								main.output("Not enough points!");
							} else {
								cha++;
								points -= chaCost;
							}
						} else {
							if (chaCost > 0){
								cha--;
								points += chaCost;
							} else {
								main.output("That stat cannot be decreased any lower.");
							}
						}
						break;

					case '+':
						if (buy){
							main.output("You are already in stat-increase mode.");
						} else {
							buy = true;
							main.output("Switched to stat-increase mode.");
						}
						break;

					case '-':
						if (!buy){
							main.output("You are already in stat-decrease mode.");
						} else {
							buy = false;
							main.output("Switched to stat-decrease mode.");
						}
						break;

					case 'F':
						main.output("Name: " + name);
						main.output("STR: " + str + " DEX: " + dex + " VIT: " + vit);
						main.output("INT: " + intel + " WIS " + wis + " CHA: " + cha);
						main.output("Are these stats okay? (Y/N)");
						do {
							input = scanner.nextLine();
							input = input.toUpperCase();
							input = input.replaceAll("[^YN]", " ");
							input = input.trim();
							if (input.isEmpty()){
								input = "N";
							}
							stat = input.charAt(0);

						} while(stat != 'Y' && stat != 'N');

						if (stat == 'Y'){
							okay = true;

						}

						if (stat == 'N') {
							okay = false;
						}



					}
				} while (!okay);
				break;
				
			case "C":
				do {
				int temp;
				int temp2;
				str = 6;
				vit = 6;
				dex = 6;
				intel = 6;
				wis = 6;
				cha = 6;
				System.out.print("Rolling strength... ");
				temp = Mob.rand(1,6);
				temp2 = Mob.rand(1,6);
				main.output("("+temp+"),("+temp2+")+6 " + (temp+temp2+6) );
				str += temp;
				str += temp2;
				System.out.print("Rolling dexterity... ");
				temp = Mob.rand(1,6);
				temp2 = Mob.rand(1,6);
				main.output("("+temp+"),("+temp2+")+6 " + (temp+temp2+6) );
				dex += temp;
				dex += temp2;
				System.out.print("Rolling vitality... ");
				temp = Mob.rand(1,6);
				temp2 = Mob.rand(1,6);
				main.output("("+temp+"),("+temp2+")+6 " + (temp+temp2+6) );
				vit += temp;
				vit += temp2;
				System.out.print("Rolling intelligence... ");
				temp = Mob.rand(1,6);
				temp2 = Mob.rand(1,6);
				main.output("("+temp+"),("+temp2+")+6 " + (temp+temp2+6) );
				intel += temp;
				intel += temp2;
				System.out.print("Rolling wisdom... ");
				temp = Mob.rand(1,6);
				temp2 = Mob.rand(1,6);
				main.output("("+temp+"),("+temp2+")+6 " + (temp+temp2+6) );
				wis += temp;
				wis += temp2;
				System.out.print("Rolling charisma... ");
				temp = Mob.rand(1,6);
				temp2 = Mob.rand(1,6);
				main.output("("+temp+"),("+temp2+")+6 " + (temp+temp2+6) );
				cha += temp;
				cha += temp2;
				
				main.output("STR: " + str + " DEX: " + dex + " VIT: " + vit);
				main.output("INT: " + intel + " WIS " + wis + " CHA: " + cha);
				main.output("Are these stats okay? (Y/N)");
				do {
					input = scanner.nextLine();
					input = input.toUpperCase();
					input = input.replaceAll("[^YN]", " ");
					input = input.trim();
					if (input.isEmpty()){
						input = "N";
					}
					choice = input.charAt(0);

				} while(choice != 'Y' && choice != 'N');

				if (choice == 'Y'){
					okay = true;

				}

				if (choice == 'N') {
					okay = false;
				}


			} while (!okay);
			break;
			
			case "D":

				do {
					main.output("What should your strength be?");
					input = scanner.nextLine();
					input = input.replaceAll("[^0123456789]", " ");
					input = input.trim();
					if (input.isEmpty()){
						input = "10";
					}
					int tempStr = Integer.parseInt(input);
					if (tempStr > 18) {
						tempStr = 18;
					}
					str = tempStr;

					main.output("What should your dexterity be?");
					input = scanner.nextLine();
					input = input.replaceAll("[^0123456789]", " ");
					input = input.trim();
					if (input.isEmpty()){
						input = "10";
					}
					int tempDex = Integer.parseInt(input);
					if (tempDex > 18) {
						tempDex = 18;
					}
					dex = tempDex;

					main.output("What should your vitality be?");
					input = scanner.nextLine();
					input = input.replaceAll("[^0123456789]", " ");
					input = input.trim();
					if (input.isEmpty()){
						input = "10";
					}
					int tempVit = Integer.parseInt(input);
					if (tempVit > 18) {
						tempVit = 18;
					}
					vit = tempVit;

					main.output("What should your intelligence be?");
					input = scanner.nextLine();
					input = input.replaceAll("[^0123456789]", " ");
					input = input.trim();
					if (input.isEmpty()){
						input = "10";
					}
					int tempIntel = Integer.parseInt(input);
					if (tempIntel > 18) {
						tempIntel = 18;
					}
					intel = tempIntel;

					main.output("What should your wisdom be?");
					input = scanner.nextLine();
					input = input.replaceAll("[^0123456789]", " ");
					input = input.trim();
					if (input.isEmpty()){
						input = "10";
					}
					int tempWis = Integer.parseInt(input);
					if (tempWis > 18) {
						tempWis = 18;
					}
					wis = tempWis;

					main.output("What should your charisma be?");
					input = scanner.nextLine();
					input = input.replaceAll("[^0123456789]", " ");
					input = input.trim();
					if (input.isEmpty()){
						input = "10";
					}
					int tempCha = Integer.parseInt(input);
					if (tempCha > 18) {
						tempCha = 18;
					}
					cha = tempCha;


					main.output("Your name is: "+ name + " and your stats are:\nSTR: " + str + " DEX: " + dex + " VIT: " + vit + "\nINT: " + intel + " WIS: " + wis + " CHA: " + cha + "\nIs this okay? (Y/N)");

					do {
						input = scanner.nextLine();
						input = input.toUpperCase();
						input = input.replaceAll("[^YN]", " ");
						input = input.trim();
						if (input.isEmpty()){
							input = "N";
						}
						choice = input.charAt(0);

					} while(choice != 'Y' && choice != 'N');

					if (choice == 'Y'){
						okay = true;

					}

					if (choice == 'N') {
						okay = false;
					}

				} while(!okay);


				break;
			default:
				break;
			}	

			main.output("Next, pick from one of the six available roles for your character to fill.");
			main.output("Fighters, trained in the use of weapons and armor, rely on their strength to deal damage. Weapons are more effective for them than for other roles");
			main.output("Thieves, agile and dexterous, rely on their dexterity stat most often. They are better at dodging blows than other roles.");
			main.output("Juggernauts, unstoppable and massive. With their high vitality, they withstand attacks that would leave others downed. They receive more hit points than other roles.");
			main.output("Wizards, trained in the lore of magic. Putting their intelligence to use, they can decimate foes from afar. They are capable of casting offensive magic.");
			main.output("Healers, compassionate and gentle. With their restorative magic, they can heal nearly any wound. Healers can cast curative spells, restoring HP.");
			main.output("Bards, jacks of all trades, but masters of none. They rely on their charisma to charm others into revealing their secrets. Bards learn small amounts of damaging and healing magic.");

			input = scanner.nextLine();
			input = input.toUpperCase();
			input = input.replaceAll("[^FTJWHB]", " ");
			input = input.trim();
			if (input.isEmpty()){
				input = "F";
			}
			choice = input.charAt(0);

			switch (choice){
			case 'F':
				main.output("Do you wish to be a Fighter?");
				role = (byte)0b00000001;
				intRole = 1;
				hpMod = 10;
				mpMod = 0;
				break;

			case 'T':
				main.output("Do you wish to be a Thief?");
				role = (byte)0b00000010;
				intRole = 2;
				hpMod = 8;
				mpMod = 0;
				break;

			case 'J':
				main.output("Do you wish to be a Juggernaut?");
				role = (byte)0b00000100;
				intRole = 3;
				hpMod = 12;
				mpMod = 0;
				break;

			case 'W':
				main.output("Do you wish to be a Wizard?");
				role = (byte)0b00001000;
				intRole = 4;
				hpMod = 4;
				mpMod = 8;
				break;

			case 'H':
				main.output("Do you wish to be a Healer?");
				role = (byte)0b00010000;
				intRole = 5;
				hpMod = 6;
				mpMod = 6;
				break;

			case 'B':
				main.output("Do you wish to be a Bard?");
				role = (byte)0b00100000;
				intRole = 6;
				hpMod = 8;
				mpMod = 2;
				break;


			}

			input = scanner.nextLine();
			input = input.toUpperCase();
			input = input.replaceAll("[^Y]", " ");
			input = input.trim();
			if (input.isEmpty()){
				input = "N";
			}
			choice = input.charAt(0);

			switch(choice){
			case 'y':
			case 'Y':
				repeat = true;
				main.output(name + " the " + roles[intRole] + ", your adventure will now begin!");
				switch(intRole){
				case 1:
					maxHP = 10;
					maxHP += ((vit-10)/2);
					maxMP = 0;
					break;
				case 2:
					maxHP = 8;
					maxHP += ((vit-10)/2);
					maxMP = 0;
					break;
				case 3:
					maxHP = 12;
					maxHP += vit;
					maxMP = 0;
					break;
				case 4:
					maxHP = 4;
					maxHP += ((vit-10)/2);
					maxMP = 8;
					maxMP += ((intel-10)/2);
					String[] wizString = {"mutter a few arcane syllables, and hold out your hand", "perform a complicated gesture and point forward", "transfix your target with a stare", "perform the necessary rituals to shape the universe to your will", "draw up arcane symbols", "ritualistically intone an incantation"};
					castAni = wizString;
					break;
				case 5:
					maxHP = 6;
					maxHP += ((vit-10)/2);
					maxMP = 6;
					maxMP += ((wis-10)/2);
					String[] healString = {"kneel and offer a short prayer to your deity", "ask for divine intervention", "perform the necessary rituals to convince the gods to intervine on your behalf"};
				castAni = healString;
					break;
				case 6:
					maxHP = 8;
					maxHP += ((vit-10)/2);
					maxMP = 2;
					maxMP += ((cha-10)/2);
					String[] bardString = {"take out your instrument and play a few notes", "begin dancing madly", "convince reality itself to do you a favor", "envision an effect and call it into being"};
				castAni = bardString;
					break;
				}
				hp = maxHP;
				mp = maxMP;
				break;
			}




		} while (!repeat);
	}
	int[] getUniqueRand(){
		int [] nums = new int[20];
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for(int i = 0; i < 30; i++)
		{
			numbers.add(i);
		}

		Collections.shuffle(numbers);
		for (int i = 0; i < 20; i++){
			nums[i] = numbers.get(i);
		}

		return nums;
	}
	
	public void levelUp(Magic mag){
		main.output("You have leveled up!");
		level++;
		xp = 0;
		int tempInt;
		switch (intRole){
		case 1:
			strPart += 1/2;
			dexPart += 1/3;
			vitPart += 1/3;
			intelPart += 1/8;
			wisPart += 1/5;
			chaPart += 1/5;
			break;
		case 2:
			strPart += 1/3;
			dexPart += 1/2;
			vitPart += 1/3;
			intelPart += 1/8;
			wisPart += 1/5;
			chaPart += 1/5;
			break;
		case 3:
			strPart += 1/3;
			dexPart += 1/3;
			vitPart += 1/2;
			intelPart += 1/8;
			wisPart += 1/5;
			chaPart += 1/5;
			break;
		case 4:
			strPart += 1/8;
			dexPart += 1/5;
			vitPart += 1/5;
			intelPart += 1/2;
			wisPart += 1/3;
			chaPart += 1/3;
			break;
		case 5:
			strPart += 1/5;
			dexPart += 1/5;
			vitPart += 1/5;
			intelPart += 1/4;
			wisPart += 1/2;
			chaPart += 1/4;
			break;
		case 6:
			strPart += 1/4;
			dexPart += 1/4;
			vitPart += 1/4;
			intelPart += 1/4;
			wisPart += 1/4;
			chaPart += 1/3;
			break;
		default: 
			strPart += 1/4;
			dexPart += 1/4;
			vitPart += 1/4;
			intelPart += 1/4;
			wisPart += 1/4;
			chaPart += 1/4;
			break;
		}
		if (strPart >= 1){
			str++;
			strPart--;
			main.output("STR +1");
		}
		
		if (dexPart >= 1){
			dex++;
			dexPart--;
			main.output("DEX +1");
		}
		
		if (vitPart >= 1){
			vit++;
			vitPart--;
			main.output("VIT +1");
		}
		
		if (intelPart >= 1){
			intel++;
			intelPart--;
			main.output("INT +1");
		}
		
		if (wisPart >= 1){
			wis++;
			wisPart--;
			main.output("WIS +1");
		}
		
		if (chaPart >= 1){
			cha++;
			chaPart--;
			main.output("CHA +1");
		}
		

		if (intRole == 3){
			tempInt = hpMod + vit;
		} else {
			tempInt = hpMod + ((vit - 10)/2);
		}
		maxHP += tempInt;
		main.output("You gained " + tempInt + " HP.");
		tempInt = 0;
		if (intRole == 4){
			tempInt = mpMod + ((intel - 10)/2);
		}
		if (intRole == 5){
			tempInt = mpMod + ((wis - 10)/2);
		}
		if (intRole == 6){
			tempInt = mpMod + ((cha - 10)/2);
		}
		if (tempInt != 0) {
			maxMP += tempInt;
			main.output("You gained " + tempInt + " MP." );
		}
		
		for (int temp = 0; temp < mag.spellVector.size(); temp++){
			byte tempByte = mag.spellVector.elementAt(temp).learn;
			tempByte = (byte) (role & tempByte);
			if (tempByte > 0){
				if (mag.spellVector.elementAt(temp).level == level){
					main.output("Learned a new spell: " + mag.spellVector.elementAt(temp).name + ".");
				}
				
			}
		}
		
		hp = maxHP;
		mp = maxMP;
		
	}
	
	
}
