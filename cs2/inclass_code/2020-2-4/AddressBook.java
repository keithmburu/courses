import java.util.ArrayList;

class AddressBook {
    ArrayList<Person> persons;

    public AddressBook() {
	this.persons = new ArrayList<>();
    }

    public ArrayList<Person> getPersons() {
	return this.persons;
    }

    public void setPersons(ArrayList<Person> persons) {
	this.persons = persons;
    }

    public void addPerson(String name, int age) {
	Person p = new Person(name, age);
	addPerson(p);
    }

    public void addPerson(Person person) {
	this.persons.add(person);
    }

    public Person findPerson(String name) {
	for(int i = 0; i < persons.size(); i++) {
	    Person p = persons.get(i);
	    if(p.getName().equals(name)) {
		return p;
	    }
	}
	return null;
    }

    public int getNumPersons() {
	return this.persons.size();
    }

    public void printAddressBook() {
	for(Person p : persons) { //for-each loop
	    System.out.println(p);
	}
    }

    public static void main(String[] args) {
	AddressBook ab = new AddressBook();

	Person p = new Person("Grounder", 25);
	p.setName("Scratch");
	ab.addPerson("Sonic", 30);
	ab.addPerson("Tails", 29);
	ab.addPerson("Knux", 28);

	Person found = ab.findPerson("Flicky");
	System.out.println(found);
    }
}
