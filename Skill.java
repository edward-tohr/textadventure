public class Skill{
	
	byte learn = 0b00000000; // Classes learned by.
	int level = 0; //Level learned at.
	String name = "null";
	String desc = "Not really a skill.";
	int effect = 0; //0 = auto-crit, 1 = steal item. Others to come as I think of them.
	int sub_effect = 0; // Gonna have different meanings based on main effect.
	
	public Skill(byte lrn, int lvl, String n, String d, int e, int se){
		learn = lrn;
		level = lvl;
		name = n;
		desc = d;
		effect = e;
		sub_effect = se;
		
	}
	
	public Skill(){
		
	}
}