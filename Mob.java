import java.util.Vector;

public class Mob {
	String input;
	char choice;

	int id = 0; // Unique ID

	int hp = 65535; //Creature's hit points.
	int maxHP = 65535;
	int mp = 255; //Creature's magic points. Used to cast spells.
	int maxMP = 255;
	int atk = 0; // Creature's attack.
	int def = 255; //Creature's defense.

	int str = 10; //Strength. Affects damage done with physical attacks.
	int dex = 10; //Dexterity. Affects chance to hit and dodge.
	int vit = 10; //Vitality. Gonna have to think of something for this to do.
	int intel = 10; //Intelligence. Affects damage done with spells.
	int wis = 10; //Wisdom. Affects magic defense??? I think? It should.
	int cha = 10; //Charisma. Doesn't really do anything just yet.


	//Redundant values are here to modify dialog.
	int dis = 100; //disposition. How much the NPC likes the player.
	int fear = 0; //Fear. How likely the NPC is to avoid fighting the player.
	int res = 100; //Respect. How highly the NPC thinks of the player.
	int hate = 0; //Hate. How likely the NPC is to attack the player.


	int type = 0; //Types. 0 is normal, generic NPC. 1 is item merchant, 2 is equipment merchant. Others can be defined as necessary.
	// For monsters, 0 is again normal, 1 is boss, 2 is quest target?

	boolean isDead = false; // Should be obvious.

	boolean unique = true; //Is there just one of this NPC, e.g. Lord Voldemort, or are there many? e.g. a peasant.
	String title = "Testificate"; // If unique, this will come before the mob's name. Otherwise, will use the standard articles. e.g. "Lord British is standing here." "a peasant is standing here." "an aardvark is standing here".
	String name = "Defaultus"; // Creature's name.
	String nick = "test"; //Shortened form of name, so it can be easier to type in.
	String desc = "A creature that has not had its description defined."; //Message printed when 'look'-ing at the creature.
	String position = "standing"; // In case you want Testificate Defaultus to be sitting in his office, or rolling in the grass or whatever. Null will print "standing here".
	int xp = 0; // XP awarded for killing creature.
	int gold = 0; // Gold that the creature will drop upon death.

	String locatedAt = "null"; // In which room the NPC can be found.
	Room location = null;

	int gender = 2; // 0 for male, 1 for female, 2 for other. Entirely for pronoun purposes.

	String[] bodyplan = {"head", "neck", "torso", "left arm", "right arm", "left leg", "right leg"}; // Body parts. Used for combat messages. e.g. "You stab Testificate Defaultus in the left arm!"
	String[] idle = {"idles", "twiddles his thumbs", "does nothing of note", "suddenly keels over dead, only to resurrect himself"}; // Messages to use when idle in the room for a period of time. e.g. "Villager kicks at the dirt."
	String[] combat = {"punch", "headbutt", "kick", "intricately karate", "bite", "shoot", "nom"}; //Verbs to use in combat messages. e.g. "the rat bites you in the leg!"
	String[] speech = {"Undefined dialog tree! You should probably fix this. Just sayin'."}; //Just in case the player manages to speak to something that doesn't have a defined speech tree, these are the default defaults.
	String[] like = {"Hey!", "Hello there, friend.", "How may I help you today?", "Yes, friend? Is there something you need?"}; // Default greetings when dispostion is high.
	String[] polite = {"Good day.", "Greetings.", "Hello."}; //Default greetings when dispostion is low, but respect is high.
	String[] dislike = {"Oh, it's you.", "What do you want?", "Say what you will and be done with it."}; //Default greetings when dispostion is low
	String[] afraid = {"O-oh, it's... it's you...", "H-hello, there...", "C-can I do anything f-for you?"}; //Default greetings when fear is high
	String[] respect = {"A fine day to you.", "Greetings! Is there anything at all I may assist you with?", "I am honored to make your acquaintance"}; //Default greetings when respect is high
	String[] disrespect = {"What do you want?", "Get out of my face.", "Ugh, you again.", "Just leave me alone, you slimy goon."}; //Default greetings when respect and disposition are low
	String[] violent = {"Are you looking to get killed? Because I'll do it.", "I've been sharpening my sword, just for you."}; //Default greetins when hatred is high
	String[] engage = {"Time to die!", "I'll decorate my home with your innards!", "This is the part where you fall down and bleed to death!", "I am going to hit you until you run out of vital fluids to leak."}; //Default string when entering combat
	String weapon = "fist"; //Default name for what they attack you with. I.E. Testificate Defaultus attempts to intricately karate you in the face with his [fist/sword/shoe/Infinite Blade of Annihilation/etc]

	String drop = "null"; // Item they will drop upon death. null for no drop.
	String steal = "null"; //If you want to have a special item available for stealing.
	int dropchance = 0; // Chance of dropping an item.
	int flee = 0; // percentate of HP at which they'll break off combat and flee.

	int personality = 0; //This should really be called AI, but eh.
	// Personality: 0 uses balanced combat option, never flees. 1 is offensive, also never flees, 2 is defensive, again, does not flee. 3 attempts to flee every round. 4-6 are balanced, offensive, defensive, but attempts to flee on threshold.
	// 7 starts offensive, shifts to balanced and defensive as HP falls. 8 starts balanced, shifts to offensive when HP is below half.
	// 9-11 use low-level magic, fire, ice, and lightning. 12 fights defensively and heals. 13-16 are same, but moderate-level. 17-20 repeat for high level.
	// Other values can be added as necessary. They will be added here for reference.
	// 21: Goblin King. At half health, he picks up a club and hits harder.

	public Mob() {

	}

	public void copyMob(Monster m){
		this.afraid = m.afraid;
		this.atk = m.atk;
		this.bodyplan = m.bodyplan;
		this.cha = m.cha;
		this.combat = m.combat;
		this.def = m.def;
		this.dex = m.dex;
		this.desc = m.desc;
		this.dis = m.dis;
		this.dislike = m.dislike;
		this.disrespect = m.disrespect;
		this.drop = m.drop;
		this.dropchance = m.dropchance;
		this.engage = m.engage;
		this.fear = m.fear;
		this.flee = m.flee;
		this.gender = m.gender;
		this.gold = m.gold;
		this.hate = m.hate;
		this.hp = m.hp;
		this.idle = m.idle;
		this.intel = m.intel;
		this.like = m.like;
		this.locatedAt = m.locatedAt;
		this.location = m.location;
		this.maxHP = m.maxHP;
		this.maxMP = m.maxMP;
		this.mp = m.mp;
		this.name = m.name;
		this.personality = m.personality;
		this.polite = m.polite;
		this.position = m.position;
		this.speech = m.speech;
		this.str = m.str;
		this.title = m.title;
		this.type = m.type;
		this.unique = m.unique;
		this.violent = m.violent;
		this.vit = m.vit;
		this.weapon = m.weapon;
		this.wis = m.wis;
		this.xp = m.xp;
	}

	//TODO: This needs to get input from main.entryField.
	int combat(Player p, Monster m, boolean u, Rooms loc, Items it, Magic mag, Skills skl) { //if u == true, player initiated combat. False if NPC did. Returns 1 if player survives, 0 if he dies.
		int result = 1;
		// All of these get a +1 to try and prevent div0 errors.
		int pdex = p.dex+1;
		int mdex = m.dex+1;
		int pstr = p.str+1;
		int mstr = m.str+1;
		int patk = p.atk+1;
		int matk = m.atk+1;
		int pdef = p.def+1;
		int mdef = m.def+1;
		String pweap = p.weapName;
		String[] pverb = p.combat;
		String[] pPlan = p.bodyplan;
		String mweap = m.weapon;
		String[] mverb = m.combat;
		String[] mPlan = m.bodyplan;
		int role = p.intRole;
		int pers = m.personality;
		double rand;
		int rando;
		boolean pTurn = false; // Determines whether or not it is the player's turn.
		boolean hit = false;
		boolean crit = false;
		boolean defend = false;
		boolean confirm = false;
		int tempHit;
		int tempDod;
		int dam;
		int plOption = p.combatStyle;
		boolean atkWeak = false;
		boolean defWeak = false;
		int clearTurns = 0; // When > 0, decrements each turn. When 0, clears the weak flags. 
		boolean parry = false; //If true, player auto-dodges.
		StringBuffer outText = new StringBuffer();




		String tempArt; //Temporary article. Title if the creature is unique, "the" otherwise.
		boolean fleeing = false; //Used to break off combat by either party.
		if (unique){
			tempArt = title;
		} else {
			tempArt = "the";
		}

		if (u){
			main.output("You draw your weapon and attack " + tempArt + " " + name + "!");
			pTurn = true;
		} else {
			if (!unique)
				tempArt = "The";
			main.output(tempArt + " " + name + " seems aggressive!");
		}

		do{

			if(pTurn){
				confirm = false;
				parry = false;
				if (clearTurns > 0)
					clearTurns--;
				if (clearTurns == 0){
					atkWeak = false;
					defWeak = false;
				}

				do {
					main.output("What will you do?");
					main.output("'A'ttack, 'D'efend, 'I'tem, 'M'agic,'F'lee, 'S'kills or change 'O'ptions?");
					input = main.getInput();
					input = input.toUpperCase();
					input = input.replaceAll("[^ADIMFSO]", " ");
				} while (input.length() == 0);
				choice = input.charAt(0);
				switch (choice){
				case 'A':
					tempHit = pdex;
					tempDod = mdex;
					hit = false;
					crit = false;

					dam = patk;
					dam += ((pstr-10) /2);

					if (plOption == 1){ //If player is using the offensive option...
						dam = patk;
						dam += pstr; // Use strength score instead of mod.
					}

					if (plOption == 2){ //If player is using defensive option...
						dam = patk;                     
						dam -= Math.abs(((pstr-10)/2)); //Subtract strength mod instead of adding. Use absolute value to prevent increase in attack from negative strength mod.
					}

					tempHit -=10;
					tempHit /=2;
					tempDod -=10;
					tempDod /=2;
					rand = Math.random();
					rand *= (pdex)*10;
					tempHit += ((int)rand % pdex)+1;
					rand = Math.random();
					rand *= (mdex)*10;
					tempDod += ((int)rand % mdex)+1;
					if (tempHit >= tempDod){
						hit = true;
						rando = rand(1,20);
						if ( rando == 20){
							crit = true;
							dam *= 2;
						}
					}
					rando = rand(0,pverb.length - 1);
					outText.append("You attempt to " + pverb[rando] + " ");
					rando = rand(0,mPlan.length - 1);
					if (!unique)
						tempArt = "the";
					outText.append(tempArt + " " + m.name + " in the " + mPlan[rando] + " with your " + pweap);

					double tempDef = (double) mdef;
					if (defWeak)
						tempDef = tempDef/2;
					tempDef = Math.sqrt(tempDef);


					if (pers == 1) {
						tempDef *= .75;
					}

					if (pers == 2) {
						tempDef *= 1.5;
					}

					dam -= (int)tempDef;

					if (!hit || dam <= 0){
						outText.append(", but the attack fails to connect!");

					}

					else if (crit){
						outText.append(" and succeed wonderfully! You deal " + dam + " damage!");
						m.hp -= dam;
					} else {
						outText.append(" and deal " + dam + " damage!");
						m.hp -= dam;
					}
					main.output(outText.toString());
					outText.delete(0,outText.length());

					break;
					

				case 'D':
					defend = true;
					break;

				case 'I':
					Vector<Item> combatItems = it.getUsableItems();
					if (combatItems.size() == 0){
						main.output("You have no usable items!");
						break;
					}

					do {

						for (int n = 0; n < combatItems.size(); n++){
							main.output(n+1 + ": " + combatItems.elementAt(n).name);
						}
						input = main.getInput();
						input = input.replaceAll("[^0123456789]", " ");
						input = input.trim();
						if (input.isEmpty()){
							int temp = combatItems.size();
							temp+= 10;
							input = String.valueOf(temp);
						}
						int num = Integer.parseInt(input);
						num--;

						if (num >= combatItems.size()){
							main.output("Invalid input.");
						} else {
							main.output("Using " + combatItems.elementAt(num).name + ". Is this okay?");
							input = main.getInput();
							input = input.toUpperCase();
							input = input.replaceAll("[^YN]", " ");
							input = input.trim();
							if (input.isEmpty()){
								input = "N";
							}

							if (input.equalsIgnoreCase("Y")){
								confirm = true;
								NPC n = null;
								Room r = null;
								Monster tempMon = null;
								Player tempPlay = null;
								if (combatItems.elementAt(num).use == 1 || combatItems.elementAt(num).use == 2 || combatItems.elementAt(num).use == 3) {
									tempPlay = p;
								}
								if (combatItems.elementAt(num).use == 4) {
									tempMon = m;

								}
								combatItems.elementAt(num).use(tempPlay, n, tempMon, r, it, mag);

							}
						}



					} while (!confirm);

					break;

				case 'M':
					Vector<Spell> spellVector = mag.getSpells(p);
					if (spellVector.size() == 0){
						main.output("You have no available spells!");
						break;
					}

					do {

						for (int n = 0; n < spellVector.size(); n++){
							main.output(n+1 + ": " + spellVector.elementAt(n).name + "     Cost:" + spellVector.elementAt(n).cost);
						}
						input = main.getInput();
						input = input.replaceAll("[^0123456789]", " ");
						input = input.trim();
						if (input.isEmpty()){
							int temp = spellVector.size();
							temp+= 10;
							input = String.valueOf(temp);
						}
						int num = Integer.parseInt(input);
						num--;

						if (num >= spellVector.size()){
							main.output("Invalid input.");
						} else {
							if (spellVector.elementAt(num).cost > p.mp){
								main.output("You don't have enough MP to cast that spell.");
								break;
							}
							if (!main.DEBUG)
								p.mp -= spellVector.elementAt(num).cost;
							main.output("Using " + spellVector.elementAt(num).name + ". Is this okay?");
							input = main.getInput();
							input = input.toUpperCase();
							input = input.replaceAll("[^YN]", " ");
							input = input.trim();
							if (input.isEmpty()){
								input = "N";
							}

							if (input.equalsIgnoreCase("Y")){
								Spell spell = spellVector.elementAt(num);
								confirm = true;
								String tempString = spell.ani;
								rando = rand(0,p.castAni.length - 1);

								String art;
								String temp2;
								double tempDoub;
								if (spell.element != 5){

									if (tempString.startsWith("-TARGET-")){
										art = "The";
										if(m.isUnique()) {
											art = m.title;
										} 
										temp2 = art + " " + m.name;
										tempString = tempString.replaceAll("-TARGET-", temp2);

									} 
									if (tempString.contains("-TARGET-")) {
										art = "the";
										if(m.isUnique()) {
											art = m.title;
										} 
										temp2 = art + " " + m.name;
										tempString = tempString.replaceAll("-TARGET-", temp2);
									}

									if (tempString.startsWith("-TARGET_POS-")){
										art = "The";
										if(m.isUnique()) {
											art = m.title;
										} 
										temp2 = art + " " + m.name + "'s";
										tempString = tempString.replaceAll("-TARGET_POS-", temp2);

									} 
									if (tempString.contains("-TARGET_POS-")) {
										art = "the";
										if(m.isUnique()) {
											art = m.title;
										} 
										temp2 = art + " " + m.name + "'s";
										tempString = tempString.replaceAll("-TARGET_POS-", temp2);
									}

									if (tempString.contains("-TARGET_PRO-")){
										switch (m.gender){
										case 0:
											temp2 = "he";
											break;

										case 1:
											temp2 = "she";
											break;

										default:
											temp2 = "it";
										}
										tempString = tempString.replaceAll("-TARGET_PRO-", temp2);

									} 
									if (tempString.contains("-POS_PRO-")) {
										switch (m.gender){
										case 0:
											temp2 = "his";
											break;

										case 1:
											temp2 = "hers";
											break;

										default:
											temp2 = "its";
										}

										tempString = tempString.replaceAll("-POS_PRO-", temp2);
									}		

								} else {
									if (tempString.startsWith("-TARGET- ")){ 
										tempString = tempString.replaceAll("-TARGET-", "You");
									}
									if (tempString.startsWith("-TARGET_POS- ")){ 
										tempString = tempString.replaceAll("-TARGET_POS-", "Your");
									} 

									if (tempString.contains("-TARGET-")){ 
										tempString = tempString.replaceAll("-TARGET-", "you");
									}

									if (tempString.contains("-TARGET_POS-")){ 
										tempString = tempString.replaceAll("-TARGET_POS-", "your");
									}

									if (tempString.contains("-TARGET_PRO-")){ 
										tempString = tempString.replaceAll("-TARGET_PRO-", "you");
									}

									if (tempString.contains("-POS_PRO-")){ 
										tempString = tempString.replaceAll("-POS_PRO-", "your");
									}
								}
								main.output("You " + p.castAni[rando] + ".");
								main.output(tempString);
								dam  = spell.power + ((p.intel-10)/5);
								tempDoub = dam *1.5;
								rando = rand(0, (int)tempDoub);
								dam += rando;
								if (spell.element == 5){
									p.hp += dam;
									if (p.hp > p.maxHP){
										p.hp = p.maxHP;
									}
									main.output("You recovered " + dam + " HP.");
								} else {
									m.hp -= dam;
									main.output("You dealt " + dam + " damage!");
								}

							}
						}



					} while (!confirm);

					break;

				case 'O':

					main.output("What will you do?");
					main.output("Be 'A'gressive, be 'D'efensive, be 'B'alanced.");
					main.output("You are currently ");
					if (plOption == 0)
						outText.append("balanced");
					if (plOption == 1)
						outText.append("aggressive");
					if (plOption ==2)
						outText.append("defensive");
					outText.append(".\n");
					main.output(outText.toString());
					outText.delete(0,outText.length());
					input = main.getInput();
					input = input.toUpperCase();
					input = input.replaceAll("[^ABD]", " ");
					char temp = input.charAt(0);
					switch (temp){
					case 'A':
						if (plOption != 1){
							plOption = 1;
							p.combatStyle = 1;
							main.output("You have switched to a more aggressive combat style.");
						} else {
							main.output("You remain aggressive.");
						}
						break;

					case 'B':
						if (plOption != 0){
							plOption = 0;
							p.combatStyle = 0;
							main.output("You have switched to a more balanced combat style.");
						} else {
							main.output("You continue to be balanced.");
						}
						break;

					case 'D':
						if (plOption != 2){
							plOption = 2;
							p.combatStyle = 2;
							main.output("You have switched to a more defensive combat style.");
						} else {
							main.output("You keep being defensive.");
						}
						break;

					}
					break;


				case 'S':
					Vector<Skill> skillVector = skl.getSkills(p);
					if (skillVector.size() == 0){
						main.output("You have no available skills!");
						break;
					}

					do {
						for (int n = 0; n < skillVector.size(); n++){

							main.output(n+1 + ": " + skillVector.elementAt(n).name);
						}
						input = main.getInput();
						input = input.replaceAll("[^0123456789]", " ");
						input = input.trim();
						if (input.isEmpty()){
							int tempInt = skillVector.size();
							tempInt += 10;
							input = String.valueOf(tempInt);
						}
						int num = Integer.parseInt(input);
						num--;

						if (num >= skillVector.size()){
							main.output("Invalid input.");
						} else {
							Skill s = skillVector.elementAt(num);
							main.output("Using " + s.name+ ", is that okay?");
						}
						input = main.getInput();
						input = input.toUpperCase();
						input = input.replaceAll("[^YN]", " ");
						input = input.trim();
						temp = input.charAt(0);
						if (input.isEmpty()) {
							temp = 'N';
						}
						if (temp == 'Y') {
							confirm = true;
							Skill skill = skillVector.elementAt(num);
							switch (skill.effect){
							case 0: //Auto-crit skills
								dam = patk;
								dam += (pstr-10)/2;
								dam *=2;
								dam -= m.def;
								if (dam > 0){
									main.output("You use " + skill.name + "! You deal " + dam + " damage!");
									m.hp -= dam;}
								else{
									dam = 0;
									main.output("You use " +skill.name +", but the attack glances off!");
								}
								break;

							case 1: //Steal
								if (m.drop == "null"){
									main.output("Nothing to steal!");
									break;
								}
								rando = rand(0,99);
								if (rando < skill.sub_effect){
									main.output("Stole " + m.drop + "!");
									m.setDrop("null");
								} else {
									main.output("Failed to steal!");
								}
								break;

							case 2: //Throwing a weapon. Bypasses defense?
								Vector<equipment> w = new Vector<equipment>(1,1);
								w = it.getWeapons();
								if (w.size() == 0){
									main.output("Nothing to throw!");
									break;
								}
								do {
									for (int n = 0; n < w.size(); n++){

										main.output(n+1 + ": " + w.elementAt(n).name);
									}
									input = main.getInput();
									input = input.replaceAll("[^0123456789]", " ");
									input = input.trim();
									if (input.isEmpty()){
										int tempInt = w.size();
										tempInt += 10;
										input = String.valueOf(tempInt);
									}
									num = Integer.parseInt(input);
									num--;

									if (num >= w.size()){
										main.output("Invalid input.");
									} else {
										equipment toss = w.elementAt(num);
										main.output("Throwing " + toss.name+ ", is that okay?");
									}
									input = main.getInput();
									input = input.toUpperCase();
									input = input.replaceAll("[^YN]", " ");
									input = input.trim();
									temp = input.charAt(0);
									if (input.isEmpty()) {
										temp = 'N';
									}
									if (temp == 'Y') {
										confirm = true;
										equipment t = w.elementAt(num);
										dam = t.pow;
										dam *= 1.5;
										dam += p.dex;
										t.setLocatedAt("null");
										t.setLocation(loc.getRoomFromName("null"));
										m.hp -= dam;
										main.output("You threw the " + t.name + "! Dealt " + dam + " damage!");
									}

								} while (!confirm);

								break;

							case 3: //Block an attack, and reduce stats for the following turn. sub 0 weakens defense, sub 1 weakens offense.
								parry = true;
								if (skill.sub_effect == 0)
									defWeak = true;
								if (skill.sub_effect == 1)
									atkWeak = true;
								clearTurns = 2;						

								break;

							case 4: //Flee
								fleeing = true;
								main.output("You run away!");
								break;
							}
						}

					} while (!confirm);
					break;


				case 'F':
					int tempInt = rand(0,p.dex);
					int temp2 = rand(0,m.dex);
					if (tempInt > temp2){
						main.output("You run away!");
						fleeing = true;
					} else {
						main.output("You fail to run away!");
					}
					break;

				}
				pTurn = false;
				if (m.hp <= 0){
					m.kill();
					m.setLocatedAt("null");
					fleeing = true;
					main.output("You win! You gain " + m.xp + " XP and " + m.gold + " gold!");
					p.xp += m.xp;
					p.gold =+ m.gold;
					if (p.xp >= p.level *100){
						p.levelUp(mag);
					}
					if (!drop.equalsIgnoreCase("null")) {
						rando = rand(0,99);
						if (rando < m.dropchance){
							Item temp = it.getItemFromName(m.drop);
							if (temp != null) {
								temp = temp.duplicateItem(temp, it);
								temp.locatedAt = "Inventory";
								Room r = loc.getRoomFromName("Inventory");
								temp.location = r;
								main.output("You found a " + temp.name + ".");
							}
						}

					}
					if (pers == 21){
						main.output("You take Gary's head.");
						Item temp = it.getItemFromName("Goblin\'s Head");
						temp.move(loc.getRoomFromName("Inventory"));
						temp.setLocatedAt("Inventory");
					}
				}
			} else {
				int tempInt = m.flee;
				tempInt += m.fear/20;
				double tempDoub = tempInt/m.maxHP;
				if (pers == 0 || pers == 1 || pers == 2){
					tempDoub = 0;
				}
				if (pers != 3 || m.hp > tempDoub){
					if (pers == 21 && mweap == "fists" && m.hp <= m.maxHP/2){
						main.output("The Goblin King growls menacingly and picks up a club!");
						mweap = "club";
						mverb = it.HammerVerb;
						matk += matk/2;

					}
					tempHit = m.dex;
					tempDod = pdex;
					hit = false;
					crit = false;

					dam = matk;
					if (atkWeak){
						dam /= 2;
					}
					dam += ((mstr-10) /2);

					if (pers == 1){ //If monster is using the offensive option...
						dam = matk;
						dam += mstr; // Use strength score instead of mod.
					}

					if (pers == 2){ //If monster is using defensive option...
						dam = matk;                     
						dam -= Math.abs(((mstr-10)/2)); //Subtract strength mod instead of adding. Use absolute value to prevent increase in attack from negative strength mod.
					}

					tempHit -=10;
					tempHit /=2;
					tempDod -=10;
					tempDod /=2;
					rand = Math.random();
					rand *= (mdex)*10;
					tempHit += ((int)rand % mdex)+1;
					rand = Math.random();
					rand *= (pdex)*10;

					if (role != 2){
						tempDod += ((int)rand % pdex)+1;
					}
					else {
						tempDod += pdex;
					}
					if (tempHit >= tempDod){
						hit = true;
						rando = rand(1,20);
						if ( rando == 20){
							crit = true;
							dam *= 1.3;
						}
					}

					if (parry) {
						hit = false;
						crit = false;
						dam = 0;
					}
					rando = rand(0,mverb.length - 1);
					if(!unique)
						tempArt = "The";
					outText.append(tempArt + " " + m.name + " attempts to " + mverb[rando] + " ");
					rando = rand(0,pPlan.length - 1);
					String mpos;
					switch (gender) {
					case 0:
						mpos = "his";
						break;
					case 1:
						mpos = "her";
						break;
					default:
						mpos = "its";
						break;

					}
					outText.append("you in the " + pPlan[rando] + " with " + mpos + " " + mweap);


					double tempDef = (double) pdef;
					tempDef = Math.sqrt(tempDef);


					if (plOption == 1) {
						tempDef *= .75;
					}

					if (plOption == 2) {
						tempDef *= 1.5;
					}

					if (defend){
						tempDef *= 2;
					}

					dam -= (int)tempDef;

					if (parry) {
						outText.append(", but you defend against it!");
					} 

					else if (!hit || dam <= 0){
						outText.append(", but the attack fails to connect!");
					}

					else if (crit){
						outText.append(" and succeeds painfully! You take " + dam + " damage!");
						p.hp -= dam;
					} 

					else {
						outText.append(" and deals " + dam + " damage!");
						p.hp -= dam;
					}
					main.output(outText.toString());
					outText.delete(0,outText.length());


				} else {
					String art = "The";
					if(m.isUnique()) {
						art = m.title;
					} 
					tempInt = rand(0,p.dex);
					int temp2 = rand(0,m.dex);
					if (tempInt < temp2){
						main.output(art + " " + m.name+ " runs away!");
						fleeing = true;
					} else {
						main.output(art + " " + m.name+ " fails to run away!");
					}


				}
				pTurn = true;
				if (p.hp <= 0){
					main.output("You have died.");
					result = 0;
					return result;

				}

			}

			/*
			 * Basics of combat:
			 * initiator of combat goes first
			 * opposed dexterity check to see if attack hits (Dex mod + rand(0,dex score)) (modified by weapon?)
			 * Strength + attack opposes defense for damage
			 * if damage <= 0, attack glances off
			 * Options: Offensive (increases attack, lowers defense)
			 *          Defensive (lowers attack, increases defense)
			 *          Balanced (no modifiers)
			 *          Flee (sets fleeing flag)
			 *          Magic
			 *          
			 * Enemies choose combat option based on HP and personality.
			 */

		} while (!fleeing);
		return result;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public Room getLocation() {
		return location;
	}

	public void setLocation(Room location) {
		this.location = location;
	}

	public String getLocatedAt() {
		return locatedAt;
	}

	public void setLocatedAt(String locatedAt) {
		this.locatedAt = locatedAt;
	}

	public String[] getBodyplan() {
		return bodyplan;
	}

	public void setBodyplan(String[] bodyplan) {
		this.bodyplan = bodyplan;
	}

	public String[] getIdle() {
		return idle;
	}

	public void setIdle(String[] idle) {
		this.idle = idle;
	}

	public String[] getSpeech() {
		return speech;
	}

	public void setSpeech(String[] speech) {
		this.speech = speech;
	}

	public String getDrop() {
		return drop;
	}

	public void setDrop(String drop) {
		this.drop = drop;
	}

	public int getDropchance() {
		return dropchance;
	}

	public void setDropchance(int dropchance) {
		this.dropchance = dropchance;
	}

	public int getFlee() {
		return flee;
	}

	public void setFlee(int flee) {
		this.flee = flee;
	}

	public int getPersonality() {
		return personality;
	}

	public void setPersonality(int personality) {
		this.personality = personality;
	}

	public int getStr() {
		return str;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public int getDex() {
		return dex;
	}

	public void setDex(int dex) {
		this.dex = dex;
	}

	public int getVit() {
		return vit;
	}

	public void setVit(int vit) {
		this.vit = vit;
	}

	public int getIntel() {
		return intel;
	}

	public void setIntel(int intel) {
		this.intel = intel;
	}

	public int getWis() {
		return wis;
	}

	public void setWis(int wis) {
		this.wis = wis;
	}

	public int getCha() {
		return cha;
	}

	public void setCha(int cha) {
		this.cha = cha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean isDead(){
		return isDead;
	}

	public void kill(){
		isDead = true;

	}

	public void revive(){
		isDead = false;
	}

	public String[] getCombat() {
		return combat;
	}

	public void setCombat(String[] combat) {
		this.combat = combat;
	}



	public String[] getDislike() {
		return dislike;
	}
	public void setDislike(String[] dislike) {
		this.dislike = dislike;
	}
	public String[] getLike() {
		return like;
	}
	public void setLike(String[] like) {
		this.like = like;
	}
	public String[] getAfraid() {
		return afraid;
	}
	public void setAfraid(String[] afraid) {
		this.afraid = afraid;
	}
	public String[] getRespect() {
		return respect;
	}
	public void setRespect(String[] respect) {
		this.respect = respect;
	}
	public String[] getDisrespect() {
		return disrespect;
	}
	public void setDisrespect(String[] disrespect) {
		this.disrespect = disrespect;
	}
	public String[] getViolent() {
		return violent;
	}
	public void setViolent(String[] violent) {
		this.violent = violent;
	}
	public String[] getEngage() {
		return engage;
	}
	public void setEngage(String[] engage) {
		this.engage = engage;
	}
	public String[] getPolite(){
		return polite;
	}
	public void setPolite(String[] polite) {
		this.polite = polite;
	}

	public void setAllSpeech(String[] speech){
		this.dislike = speech;
		this.disrespect = speech;
		this.like = speech;
		this.afraid = speech;
		this.polite = speech;
		this.speech = speech;
		this.respect = speech;
		this.violent = speech;
	}

	public void speak(String arg, Rooms loc, Player p, Mobs mo, Room r) {
	}

	public static int rand(int low, int high){

		if (low >= high){
			return high;
		}

		double rando;
		int rand;
		high++;
		high -= low;
		rando = Math.random();
		rando *= high * 10;
		rand = (int)(rando % high);
		rand += low;
		return rand;
	}

	public void charm(Player p){

		int cha1 = p.cha;
		int cha2 = cha;

		int temp = cha1 - cha2; //Subtract player's charisma from npc's. Add ten, then multiply the result by five. This gives a 50% chance of success if both parties have equal charisma, and a 10-point difference is required to guarantee success or failure.
		temp += 10;
		temp *= 5;
		//Make sure value is between 0 and 100. Not that having a 200% chance of success would break anything, but...
		if (temp > 100){
			temp = 100;
		}

		if (temp < 0){
			temp = 0;
		}

		int rand = rand(0, 99);
		if (rand < temp){
			dis += 10;
			dis +=((p.cha-10)/2);
			if (dis > 100)
				dis = 100;
			main.output("Oh, why thank you.");

		}
		else {
			dis -= 10;
			dis -= ((p.cha-10)/2) - 5;
			if (dis < 0)
				dis = 0;
			main.output("Try your flattery elsewhere.");
		}
	}

	public void threat(Player p, Items it, Room r, Rooms rs){
		//Compare the charisma, strength, and vitality mods, and add the results together along with half of the player's weapon power. Set this value as "threatmod".
		int chaMod = (p.cha-10)/2;
		int strMod = (p.str-10)/2;
		int vitMod = (p.vit-10)/2;
		int weap = 0;
		if (p.weap !=0){
			equipment tempEquip = it.getEquipFromName(p.weapName);
			weap = tempEquip.pow;		
		}

		int chaRes = (cha-10)/2;
		int strRes = (str-10)/2;
		int vitRes = (vit-10)/2;

		int chaDif = chaMod - chaRes;
		int strDif = strMod - strRes;
		int vitDif = vitMod - vitRes;

		int threatMod = chaDif + strDif + vitDif + (weap/2);
		fear += threatMod; //Add threatmod to the npc's existing fear value. Constrain to between 0 and 100. If fear hits 100, make the npc run away.
		if (fear < 0)
			fear = 0;
		if (fear > 100)
			fear = 100;

		if (threatMod > 0)
			main.output("Whoa, hold on. No need to get violent...");
		if (threatMod > 0 && fear == 100) {
			String dir = runAway(r,rs);
			String tempArt = "";
			if (unique){
				tempArt = title;
			} else {
				tempArt = "The";
			}
			main.output(tempArt + " " + name + " flees " + dir + "!");
		}
		if (threatMod <= 0)
			main.output("Ha! You expect to frighten me like that?");
	}

	public void mageCharm(int pow) {
		dis += pow;
		if (dis > 100)
			dis = 100;
		if (dis < 0)
			dis = 0;
		main.output(dis);
	}


	public void mageRespect(int pow) {
		res += pow;
		if (res > 100)
			res = 100;
		if (res < 0)
			res = 0;
		main.output(res);
	}


	public void mageFear(int pow) {
		fear += pow;
		if (fear > 100)
			fear = 100;
		if (fear < 0)
			fear = 0;
		main.output(fear);
	}


	public void mageHate(int pow) {
		hate += pow;
		if (hate > 100)
			hate = 100;
		if (hate < 0)
			hate = 0;
		main.output(hate);
	}

	public void npcify(Mobs mobs, Monster mon){
		mon.npcize(mobs);
	}

	public void monsterfy(Mobs mobs, NPC npc){
		npc.monsterize(mobs);
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

	public int getGender(){
	return gender;
	}


	public String runAway(Room r, Rooms rs){
		int tempExits = 0;
		boolean n = false;
		boolean s = false;
		boolean e = false;
		boolean w = false;
		String direction = "n/a";
		Room output = r;

		if (r.getNorth() != -1){
			n = true;
		}

		if (r.getSouth() != -1){
			s = true;
		}

		if (r.getEast() != -1){
			e = true;
		}

		if (r.getWest() != -1){
			w = true;
		}

		if (n){
			tempExits++;
		}
		if (s){
			tempExits++;
		}
		if (e){
			tempExits++;
		}
		if (w){
			tempExits++;
		}

		if (tempExits >= 1){

			double rand = Math.random() * 255;
			int choice = (int)rand % tempExits;
			int[] roomArray = new int[tempExits];
			for (int i = 0; i < tempExits; i++) {
				if (n) {
					roomArray[i] = r.getNorth();
					n = false;
				} else if (s) {
					roomArray[i] = r.getSouth();
					s = false;
				} else if (e){
					roomArray[i] = r.getEast();
					e = false;
				} else if (w){
					roomArray[i] = r.getWest();
					w = false;
				}
			}
			output = rs.getRoom(roomArray[choice]);
			if (roomArray[choice] == r.getNorth()) {
				direction = "north";
			}
			if (roomArray[choice] == r.getSouth()) {
				direction = "south";
			}
			if (roomArray[choice] == r.getEast()) {
				direction = "east";
			}
			if (roomArray[choice] == r.getWest()) {
				direction = "west";
			}
			location = output;
			locatedAt = output.getName();

		}
		return direction;



	}
}
