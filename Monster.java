
public class Monster extends Mob {

	public Monster() {

	}

	public void speak(Monster mon){
		speech = mon.getSpeech();
		int terms = speech.length;
		double rand = Math.random();
		rand *= terms*10;
		int rando = (int)rand % terms;
		main.output(speech[rando]);
	}
	
	public NPC npcize(Mobs mobs){
		NPC newmon = new NPC();
		newmon.id = this.id;
		newmon.hp = this.hp;
		newmon.maxHP = this.maxHP;
		newmon.mp = this.mp;
		newmon.maxMP = this.maxMP;
		newmon.atk = this.atk;
		newmon.def = this.def;
		newmon.str = this.str;
		newmon.dex = this.dex;
		newmon.vit = this.vit;
		newmon.intel = this.intel;
		newmon.wis = this.wis;
		newmon.cha = this.cha;
		newmon.dis = this.dis;
		newmon.fear = this.fear;
		newmon.res = this.res;
		newmon.hate = this.hate;
		newmon.type = 0;
		newmon.unique = this.unique;
		newmon.title = this.title;
		newmon.name = this.name;
		newmon.desc = this.desc;
		newmon.position = this.position;
		newmon.xp = this.xp;
		newmon.gold = this.gold;
		newmon.locatedAt = this.locatedAt;
		newmon.location = this.location;
		newmon.bodyplan = this.bodyplan;
		newmon.idle = this.idle;
		newmon.combat = this.combat;
		newmon.speech = this.speech;
		newmon.like = this.like;
		newmon.dislike = this.dislike;
		newmon.afraid = this.afraid;
		newmon.respect = this.respect;
		newmon.disrespect = this.disrespect;
		newmon.violent = this.violent;
		newmon.engage = this.engage;
		newmon.weapon = this.weapon;
		newmon.drop = this.drop;
		newmon.dropchance = this.dropchance;
		newmon.flee = this.flee;
		newmon.personality = this.personality;
		this.kill();
		this.locatedAt = "null";
		mobs.npcVector.addElement(newmon);
		mobs.monVector.removeElement(this);
		return newmon;
	}
}
