public class One {
    public static void main(String[] args) {
        

        Person person1 = new Person("John");
        NicePerson nicePerson1 = new NicePerson("Bob");
    }
}

class Person {
    String name;
    public Person(String name) {
        this.name = name;
    }

    public String Greeting() {
        return "Hello!";
    }
}

class NicePerson extends Person{

    public NicePerson(String name) {
        super(name);
    }

    @Override
    public String Greeting() {
        return "Hello, my name is " + this.name+ ". Nice to meet you!";
    }


}