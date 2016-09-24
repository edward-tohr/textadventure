// Class Actor will include all NPC's monsters, and other interactables.
// Should just include basic info, and getters/setters.
// anything more complicated than that should probably go in main or parser or such.
// Preliminary list of attributes:
// HP: Health. Kinda important for killable things.
// Max HP: Max health. Also important.
// Disposition: how the actor feels about the player.
// Fear: Also how the actor feels about the player.
// Affiliation: will determine how the actor interacts with other actors.
// Combat Stats: same as player's. Important for getting numbers in combat.
//               attack, defense, xp, gold, speed, charisma, etc.
// Location: the Room that the actor is currently located in.
// Name
// Title
// Nickname
// Description
// Position
// Gender
// Bodyplan
// Idle
// Combat
// Speech (regular speech, disposition moderate, fear low)
//  Polite (speech, high disposition, low fear)
//  Afraid (speech, high fear, low disposition)
//  Dislike (speech, low fear, low disposition)
//  Hate (speech, very low fear/disposition)
//  Respect (high fear, high disposition)
//  Engage (used when entering combat)
// AI
// Item drop
// Drop chance
// Flee
// Weapon
// Hat

class Actor{
   int health = 255;
   int maxHealth = 255;
   int disposition = 128;
   int fear = 0;
   Faction affiliation = main.DEFAULT_FACTION;
   int str = 0;
   int dex = 0;
   int con = 0;
   int intel = 0;
   int wis = 0;
   int cha = 0;
   int atk = 0;
   int def = 0;
   int xp = 0;
   int gold = 0;
   Room currentRoom = null;
   String name = "Defaultus";
   String title = "Testificate";
   String nickname = null;
   String description = "An actor which has not had a description defined.";
   String position = "standing";
   Gender gender = main.GENDER_UNDEFINED;
   Vector<String> bodyplan;
   Vector<String> idle;
   Vector<String> combat;
   Vector<String> speech;
   Vector<String> polite;
   Vector<String> afraid;
   Vector<String> dislike;
   Vector<String> hate;
   Vector<String> respect;
   Vector<String> engage;
   Ai ai = main.AI_DEFAULT;
   Object drop = null;
   int dropChance = 0;
   int flee = 0;
   Object weapon = null;
   Object hat = null;

   int getHealth(){
      return health;
   }

   void setHealth(int hp){
      health = hp;
      if (health > maxHealth){
         health = maxHealth;
      }
      if (health < 0){
         health = 0;
      }
   }

   void changeHealth(int hp){
   //convenience function to change HP by specified amount, instead of to specified amount.
      setHealth(health + hp);
   }

   int getMaxHealth(){
      return maxHealth;
   }

   void setMaxHealth(int maxhp){
      if (maxhp < 0){
         main.output("Error! Max HP cannot be negative!");
      }
      else {
         maxHealth = maxhp;
         if (health > maxHealth){
            health = maxHealth;
         }
      }
   }

   void changeMaxHealth(int maxhp){
      setMaxHealth(maxHealth + maxhp);
   }

   int getDisposition(){
      return disposition;
   }

   void setDisposition(int dis){
      disposition = dis;
      if (disposition < 0){
         disposition = 0;
      }
      else if (disposition > 255){
         disposition = 255;
      }
   }

   void changeDisposition(int dis){
      setDisposition(disposition + dis);
   }

   int getFear(){
      return fear;
   }

   void setFear(int fer){
      fear = fer;
      if (fear < 0){
         fear = 0;
      }
      else if (fear > 255){
         fear = 255;
      }
   }

   void changeFear(int fer){
      setFear(fear + fer);
   }

   Faction getAffiliation(){
      return affiliation;
   }

   void setAffiliation(Faction faction){
      affiliation = faction;
   }


}
