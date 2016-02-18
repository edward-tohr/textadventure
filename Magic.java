import java.util.Scanner;
import java.util.Vector;


public class Magic {
// spell ani replace codes:
// -TARGET- becomes title + name. i.e. "You punch Testificate Defaultus."
// -TARGET_POS- is the possessive form of the target. i.e. "Testificate Defaultus's arm falls off."
// -TARGET_PRO- is the pronoun for the target. i.e. he, she, it, etc.
// -POS_PRO- is the possessive pronoun. his, hers, its, etc.
// "-TARGET- looks confused as -POS_PRO- arm falls off. With a shrug, -TARGET_PRO- faces you. After several minutes, -TARGET_POS- arm disappears."
// that would result in "Testificate Defaultus looks confused as his arm falls off. With a shrug, he faces you. After several minutes, Testificate Defaultus's arm disappears."
	
	Vector<Spell> spellVector = new Vector<Spell>(1,1);
	
	void initalizeSpells() {
		Spell s1 = new Spell(1,5, (byte)0b00101000, 1, 0);
		s1.name = "Arcane Strike";
		s1.desc = "A blast of focused energy. Non-elemental.";
		s1.ani = "-TARGET- is struck by invisible force!";
		spellVector.addElement(s1);
		
		Spell s2 = new Spell(1,5, (byte)0b00110000, 2, 5);
		s2.name = "Light Healing";
		s2.desc = "Holy energy restores a small amount of hit points.";
		s2.ani =  "-TARGET_POS- wounds begin to knit shut.";
		spellVector.addElement(s2);
		
		Spell s3 = new Spell(5,15, (byte)0b00101000, 5, 1);
		s3.name = "Fireball";
		s3.desc = "A ball of flame is launched at a foe, dealing significant fire damage.";
		s3.ani = "A sphere of fire leaves a smoking trail through the air, as it impacts -TARGET-!";
		spellVector.addElement(s3);
		
		Spell s4 = new Spell(5,15, (byte)0b00101000, 5, 2);
		s4.name = "Frostbite";
		s4.desc = "The target is encased in sub-zero ice, dealing significant ice damage.";
		s4.ani = "-TARGET_POS- breath is visible for a moment, before -TARGET_PRO- becomes encased in ice!";
		spellVector.addElement(s4);
		
		Spell s5 = new Spell(5,15, (byte)0b00101000, 5, 3);
		s5.name = "Lightning Bolt";
		s5.desc = "A bolt of electricity is launched at a foe, dealing significant lightning damage.";
		s5.ani = "A brilliant, jagged line flickers into existance for a moment, heading towards -TARGET-!";
		spellVector.addElement(s5);
		
		Spell s6 = new Spell(5,15, (byte)0b00101000, 5, 4);
		s6.name = "Stalagmite";
		s6.desc = "A spike of rock erupts underneath a foe, dealing significant earth damage.";
		s6.ani = "The earth rumbles, and sharp rocks suddenly impale -TARGET- from below!";
		spellVector.addElement(s6);
		
		Spell s7 = new Spell(5, 10, (byte)0b00110000, 5, 5);
		s7.name = "Healing";
		s7.desc = "Holy energy restores a moderate amount of hit points.";
		s7.ani = "-TARGET_POS- wounds glow briefly, before fading away.";
		spellVector.addElement(s7);
		
		Spell s8 = new Spell(5,10, (byte)0b00100000, 1, 6);
		s8.name = "Charm Person";
		s8.desc = "Whether through heartfelt words, a moving sonnet, powerful music, or just magical mind-screwery, the target perceives the caster in a more favorable light.";
		s8.ani = "-TARGET_POS- features soften, and -TARGET_PRO- seems a little more fond of you.";
		spellVector.addElement(s8);
		
		Spell s9 = new Spell(0,-100, (byte)0b00100000, 1, 6);
		s9.name = "Debug Decharm";
		spellVector.addElement(s9);
		
		Spell s10 = new Spell(0,100, (byte)0b00100000, 1, 7);
		s10.name = "Debug Respect";
		spellVector.addElement(s10);
		
		Spell s11 = new Spell(0,-100, (byte)0b00100000, 1, 7);
		s11.name = "Debug Disrespect";
		spellVector.addElement(s11);
		
		Spell s12 = new Spell(0,100, (byte)0b00100000, 1, 8);
		s12.name = "Debug Fear";
		spellVector.addElement(s12);
		
		Spell s13 = new Spell(0,-100, (byte)0b00100000, 1, 8);
		s13.name = "Debug Defear";
		spellVector.addElement(s13);
		
		Spell s14 = new Spell(0,100, (byte)0b00100000, 1, 9);
		s14.name = "Debug Hate";
		spellVector.addElement(s14);
		
		Spell s15 = new Spell(0,-100, (byte)0b00100000, 1, 9);
		s15.name = "Debug Dehate";
		spellVector.addElement(s15);
		
		Spell s16 = new Spell(0,0, (byte)0b00100000, 1, 10);
		s16.name = "Monsterize";
		spellVector.addElement(s16);
		
		Spell s17 = new Spell(0,0, (byte)0b00100000, 1, 10);
		s17.name = "NCPify";
		spellVector.addElement(s17);
		
		Spell s18 = new Spell(0,100,(byte)0b00000000,1,0);
		s18.name = "Debug Grimoire";
		spellVector.addElement(s18);
		
	}
	
	public Vector<Spell> getSpells(Player p){
		Vector<Spell> temp = new Vector<Spell>(1,1);
		for (int i = 0; i < spellVector.size(); i++){
			byte tempByte = 0;
			tempByte = (byte)(p.role & spellVector.elementAt(i).learn);
			if (tempByte !=0 && p.level >= spellVector.elementAt(i).level){
				temp.addElement(spellVector.elementAt(i));
			}
		}
		return temp;
		
	}
	
	public Vector<Spell> getHealSpells(Player p){
		Vector<Spell> temp = new Vector<Spell>(1,1);
		for (int i = 0; i < spellVector.size(); i++){
			byte tempByte = 0;
			tempByte = (byte)(p.role & spellVector.elementAt(i).learn);
			if (tempByte !=0 && p.level >= spellVector.elementAt(i).level && spellVector.elementAt(i).element == 6){
				temp.addElement(spellVector.elementAt(i));
			}
		}
		return temp;
		
	}
	
	Spell getSpellFromName(String name){
		Spell s = null;
		for (int n = 0; n < spellVector.size(); n++){
			String test = spellVector.elementAt(n).name;
			if (name.equalsIgnoreCase(test)){
				s = spellVector.elementAt(n);
			}
		}

		return s;

	}
	
	int cast(String spell, String target, Room room, Rooms loc, Player p, Mobs mo, Items it){
		Scanner scanner = new Scanner( System.in );
		String input;
		String here = room.name;
	    Vector<Spell> spells = getSpells(p);
	    int result = 0;
	    /*
	     * 0 = no extra processing needed
	     * 1 = player dead
	     * 2 = combat
	     * 3 = NPC combat
	     */
			Spell temp = getSpellFromName(spell);
			if (spells.contains(temp)) {
				if (target.equalsIgnoreCase("me")){
					main.output("Really cast " + temp.name + " on yourself?");
					input = scanner.nextLine();
					input = input.toUpperCase();
					String tempString = temp.ani;
					char tempchar = input.charAt(0);
					if (tempchar == 'Y'){
						int rand = Mob.rand(0, p.castAni.length - 1);
						main.output("You " + p.castAni[rand] + ".");
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

						main.output(tempString);

						int dam  = temp.power + ((p.intel-10)/5);
						double tempDoub = dam *1.5;
						int rando = Mob.rand(0, (int)tempDoub);
						dam += rando;
						if (temp.element < 5){
							p.hp -= dam;
							main.output("You dealt " + dam + " damage!");
						} else if (temp.element == 5 ) {
							p.hp += dam;

							if (p.hp > p.maxHP){
								p.hp = p.maxHP;
							}
							main.output("You recovered " + dam + " HP.");
						} else if (temp.element == 6) {
							main.output("You already think very highly of yourself.");
						} else if (temp.element == 7) {
							main.output("As an adventurer, your self-respect is actually quite good.");
						}else if (temp.element == 8) {
							main.output("Being afraid of yourself wouldn't solve anything, would it?.");
						}else if (temp.element == 9) {
							main.output("Y'know, most people try to reduce their self-loathing.");
						}

						if (p.hp <= 0){
							main.output("You have died.");
							room = loc.getRoomFromName("quit");
							return 1;
						}
					}
				}

				else {
					Vector<Monster> mons = mo.getMonsterByRoom(here, loc);
					Vector<NPC> npcs = mo.getNPCByRoom(here, loc);
					Monster tempMon = mo.getMonsterFromName(target);
					NPC tempNPC = mo.getNPCFromName(target);
					if (mons.contains(tempMon)){
						main.output("Really cast " + temp.name + " on " + tempMon.name +"?");
						input = scanner.nextLine();
						input = input.toUpperCase();
						String tempString = temp.ani;
						char tempchar = input.charAt(0);
						if (tempchar == 'Y'){
							int rand = Mob.rand(0, p.castAni.length - 1);
							main.output("You " + p.castAni[rand] + ".");
							String art = "The";
							if (tempMon.unique){
								art = tempMon.title;
							}
							String temp2 = art + " " + tempMon.name;
							if (tempString.startsWith("-TARGET- ")){ 
								tempString = tempString.replaceAll("-TARGET-", temp2);
							}
							if (tempString.startsWith("-TARGET_POS- ")){ 
								tempString = tempString.replaceAll("-TARGET_POS-", temp2 + "'s");
							}

							art = "the";
							if (tempMon.unique){
								art = tempMon.title;
							}
							temp2 = art + " " + tempMon.name;

							if (tempString.contains("-TARGET-")){ 
								tempString = tempString.replaceAll("-TARGET-", temp2);
							}

							if (tempString.contains("-TARGET_POS-")){ 
								tempString = tempString.replaceAll("-TARGET_POS-", temp2 + "'s");
							}

							switch (tempMon.gender) {
							case 0:
								temp2 = "he";
								break;
							case 1:
								temp2 = "she";
								break;
							default:
								temp2 = "it";
								break;
							}

							if (tempString.contains("-TARGET_PRO-")){ 
								tempString = tempString.replaceAll("-TARGET_PRO-", temp2);
							}

							switch (tempMon.gender) {
							case 0:
								temp2 = "his";
								break;
							case 1:
								temp2 = "her";
								break;
							default:
								temp2 = "its";
								break;
							}


							if (tempString.contains("-POS_PRO-")){ 
								tempString = tempString.replaceAll("-POS_PRO-", temp2);
							}

							main.output(tempString);

							int dam  = temp.power + ((p.intel-10)/5);
							double tempDoub = dam *1.5;
							int rando = Mob.rand(0, (int)tempDoub);
							dam += rando;
							if (temp.element < 5){
								tempMon.hp -= dam;
								main.output("You dealt " + dam + " damage!");
								if (tempMon.hp <= 0){
									main.output(art + " " + tempMon.name + " falls over, dead!");
									tempMon.kill();
									tempMon.locatedAt = "null";
									tempMon.location = loc.getRoomFromName("null");
									p.xp += tempMon.xp;
									p.gold += tempMon.gold;
									main.output("You win! You gain " + tempMon.xp + " XP and " + tempMon.gold + " gold!");
									if (p.xp >= p.level *100){
										p.levelUp(this);
									}
									if (!tempMon.drop.equalsIgnoreCase("null")) {
										rando = Mob.rand(0,99);
										if (rando < tempMon.dropchance){
											Item tempitem = it.getItemFromName(tempMon.drop);
											if (tempitem != null) {
												tempitem = tempitem.duplicateItem(tempitem, it);
												tempitem.locatedAt = "Inventory";
												Room itemroom = loc.getRoomFromName("Inventory");
												tempitem.location = itemroom;
												main.output("You found a " + tempitem.name + ".");
											}
										}
									}
								} else {
									result = 2;
								}

							} else if (temp.element == 5 ) {
								tempMon.hp += dam;

								if (tempMon.hp > tempMon.maxHP){
									tempMon.hp = tempMon.maxHP;
								}
								main.output("You healed " + dam + " HP.");
							}
							else if (temp.element == 6) {
								tempMon.mageCharm((p.cha-10)/2); 
							}
							
							else if (temp.element == 10){
								tempMon.npcize(mo);
							}
						}

					}

					if (npcs.contains(tempNPC)){
						main.output("Really cast " + temp.name + " on " + tempNPC.name +"?");
						input = scanner.nextLine();
						input = input.toUpperCase();
						String tempString = temp.ani;
						char tempchar = input.charAt(0);
						if (tempchar == 'Y'){
							int rand = Mob.rand(0, p.castAni.length - 1);
							main.output("You " + p.castAni[rand] + ".");
							String art = "The";
							if (tempNPC.unique){
								art = tempNPC.title;
							}
							String temp2 = art + " " + tempNPC.name;
							if (tempString.startsWith("-TARGET- ")){ 
								tempString = tempString.replaceAll("-TARGET-", temp2);
							}
							if (tempString.startsWith("-TARGET_POS- ")){ 
								tempString = tempString.replaceAll("-TARGET_POS-", temp2 + "'s");
							}

							art = "the";
							if (tempNPC.unique){
								art = tempNPC.title;
							}
							temp2 = art + " " + tempNPC.name;

							if (tempString.contains("-TARGET-")){ 
								tempString = tempString.replaceAll("-TARGET-", temp2);
							}

							if (tempString.contains("-TARGET_POS-")){ 
								tempString = tempString.replaceAll("-TARGET_POS-", temp2 + "'s");
							}

							switch (tempNPC.gender) {
							case 0:
								temp2 = "he";
								break;
							case 1:
								temp2 = "she";
								break;
							default:
								temp2 = "it";
								break;
							}

							if (tempString.contains("-TARGET_PRO-")){ 
								tempString = tempString.replaceAll("-TARGET_PRO-", temp2);
							}

							switch (tempNPC.gender) {
							case 0:
								temp2 = "his";
								break;
							case 1:
								temp2 = "her";
								break;
							default:
								temp2 = "its";
								break;
							}


							if (tempString.contains("-POS_PRO-")){ 
								tempString = tempString.replaceAll("-POS_PRO-", temp2);
							}

							main.output(tempString);

							int dam  = temp.power + ((p.intel-10)/5);
							double tempDoub = dam *1.5;
							int rando = Mob.rand(0, (int)tempDoub);
							dam += rando;
							if (temp.element < 5){
								tempNPC.hp -= dam;
								main.output("You dealt " + dam + " damage!");
								if (tempNPC.hp <= 0){
									main.output(art + " " + tempNPC.name + " falls over, dead!");
									tempNPC.kill();
									tempNPC.locatedAt = "null";
									tempNPC.location = loc.getRoomFromName("null");
									p.xp += tempNPC.xp;
									p.gold += tempNPC.gold;
									main.output("You win! You gain " + tempNPC.xp + " XP and " + tempNPC.gold + " gold!");
									if (p.xp >= p.level *100){
										p.levelUp(this);
									}
									if (!tempNPC.drop.equalsIgnoreCase("null")) {
										rando = Mob.rand(0,99);
										if (rando < tempNPC.dropchance){
											Item tempitem = it.getItemFromName(tempNPC.drop);
											if (tempitem != null) {
												tempitem = tempitem.duplicateItem(tempitem, it);
												tempitem.locatedAt = "Inventory";
												Room itemroom = loc.getRoomFromName("Inventory");
												tempitem.location = itemroom;
												main.output("You found a " + tempitem.name + ".");
											}
										}
									}
								} else {
									result = 3;
								}
							} else if (temp.element == 5 ) {
								tempNPC.hp += dam;

								if (tempNPC.hp > tempNPC.maxHP){
									tempNPC.hp = tempNPC.maxHP;
								}
								main.output("You healed " + dam + " HP.");
							}
							else if (temp.element == 6) {
								tempNPC.mageCharm(temp.power + ((p.cha-10)/2));
								
							}
							else if (temp.element == 7) {
								tempNPC.mageRespect(temp.power + ((p.cha-10)/2));
								
							}
							
							else if (temp.element == 8) {
								tempNPC.mageFear(temp.power + ((p.cha-10)/2));
								
							}
							
							else if (temp.element == 9) {
								tempNPC.mageHate(temp.power + ((p.cha-10)/2));
								
							}
							
							else if (temp.element == 10){
								tempNPC.monsterize(mo);
							}
						}

					}
				}
			}


			else {
				main.output("You do not know that spell.");
			}

		return result;


	}
	
	
	
}