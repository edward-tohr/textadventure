//import java.io.Serializable;
import java.util.Vector;

public class Skills {
	public Skills() {
		
	}
	
	Vector<Skill> skillVector = new Vector<Skill>(1,1); 
	
	public void initializeSkills(){
	
	Skill t1 = new Skill((byte)0b00000010, 1, "Steal", "Takes an item from the target.", 1, 0);
	skillVector.addElement(t1);
	Skill t2 = new Skill((byte)0b00000010, 5, "Backstab", "Deals double damage, Can be used to initiate combat.", 0, 0);
	skillVector.addElement(t2);
	Skill t3 = new Skill((byte)0b00000010, 5, "Throw", "Throws a weapon from inventory to deal damage.", 2, 0);
	skillVector.addElement(t3);
	Skill f1 = new Skill((byte)0b00000001, 1, "Power Attack", "A powerful blow that deals more damage.", 0, 1);
	skillVector.addElement(f1);
	Skill f2 = new Skill((byte)0b00000001, 2, "Parry", "Deflectan opponent's attack and make their next strike weaker.", 3, 1);
	skillVector.addElement(f2);
	Skill f3 = new Skill((byte)0b00000001, 2, "Riposte", "Deflect an opponent's attack and make them unable to defend against your next strike.", 3, 0);
	skillVector.addElement(f3);
	Skill t4 = new Skill((byte)0b00000010, 1, "Flee", "Run away from an opponent without fail.", 4, 0);
	skillVector.addElement(t4);
}
	
	public Vector<Skill> getSkills(Player p){
		Vector<Skill> temp = new Vector<Skill>(1,1);
		for (int i = 0; i < skillVector.size(); i++){
			byte tempByte = 0;
			tempByte = (byte)(p.role & skillVector.elementAt(i).learn);
			if (tempByte !=0 && p.level >= skillVector.elementAt(i).level){
				temp.addElement(skillVector.elementAt(i));
			}
		}
		return temp;
		
	}
	
	Skill getSkillFromName(String name){
		Skill s = null;
		for (int n = 0; n < skillVector.size(); n++){
			String test = skillVector.elementAt(n).name;
			if (name.equalsIgnoreCase(test)){
				s = skillVector.elementAt(n);
			}
		}

		return s;

	}
	
}