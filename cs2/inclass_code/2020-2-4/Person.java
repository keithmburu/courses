class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
	setName(name);
	setAge(age);
    }

    public Person() {

    }
    
    public void setName(String name) {
	this.name = name;
    }
    
    public String getName() {
	return this.name;
    }
    
    private int getAge() {
	return age;
    }

    public void setAge(int age) {
	if(age < 0) {
	    System.err.println("Age must be positive.");
	    System.err.println("Age not set.");
	} else {
	    this.age = age;
	}
    }

    @Override
    public String toString() {
	return name + ", " + age;
    }
}
