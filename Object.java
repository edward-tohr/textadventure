// Hokay, so, what'll we do here...
// bool for equippable, enum for equipment slot? No, setting slot as SLOT_NONE should do.
// Vector roles for equippable by. (Adventurer should get a special exception?)
// int for power. There'll also need to be something to determine type (Enum time again!)
import main.Role;
import main.EquipSlot
import main.ObjectType;

class Object{
EquipSlot equipType = SLOT_NONE;
Vector<Role> equipBy;
ObjectType mainType = OBJECT_OTHER;
int power = 0; // Pulls multi-duty as attack, defense, damage, healing, etc. based on mainType.
int numHere = 0; // How many of this item are in this stack.

EquipSlot getSlot(){
	return equipType;
	}

void setSlot(EquipSlot slot){
	equipType = slot;
	}

Vector<Role> getEquippableBy(){
	return equipBy;
	}

void setEquippableBy(Vector<Role> roleVector) {
	equipBy = roleVector;
	}

void addEquippableBy(Role role) {
	if (!equipBy.contains(role)){
		equipBy.add(role);
		}
	}

void removeEquippableBy(Role role){
	if (equipBy.contains(role)){
		equipBy.remove(role);
		}
	}

ObjectType getType(){
	return mainType;
	}

void setType (ObjectType type){
	mainType = type;
	}

int getPower(){
	return power;
	}

void setPower(int pow){
	power = pow;
	}

int getNumHere(){
	return numHere;
	}

void setNumHere(int number){
	if (number < 0){
		number = 0;
		}
	numHere = number;
	}
}
