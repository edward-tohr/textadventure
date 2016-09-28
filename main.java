// Stuff assumed to be in main:
 public enum Gender {
 GENDER_MALE, GENDER_FEMALE, GENDER_UNDEFINED
 }

 public enum Ai {
 AI_DEFAULT, AI_RECKLESS, AI_CAUTIOUS, AI_CASTER_FIRE, AI_CASTER_ICE, AI_CASTER_LIGHTNING, AI_CASTER_HEAL
 }

 public enum Role {
 ROLE_FIGHTER, ROLE_ROGUE, ROLE_PALADIN, ROLE_WIZARD, ROLE_HEALER, ROLE_BARD, ROLE_ADVENTURER
 }

 public enum EquipSlot {
 SLOT_NONE, SLOT_HEAD, SLOT_BODY, SLOT_HANDS, SLOT_LEGS, SLOT_FEET, SLOT_WEAPON, SLOT_OTHER, SLOT_HAT
 }

 public enum ObjectType {
 OBJECT_EQUIPMENT, OBJECT_HEALTHPOTION, OBJECT_MANAPOTION, OBJECT_BOTHPOTION, OBJECT_KEY, OBJECT_OTHER
 }

 void output(String outtext){
 // do output stuff here
 }

 int randInt(int low, int high){
 // generate a random integer between low and high
 }

 String getInput(){
 // allow typing into text entry box, when enter is hit, return text in box.
 }
