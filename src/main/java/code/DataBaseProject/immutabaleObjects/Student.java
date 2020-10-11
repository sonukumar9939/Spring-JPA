package code.DataBaseProject.immutabaleObjects;

public final class Student {

	private final int id;
	private final String name;
	private final Age age;

	public Student(int id, String name, Age age) {
		super();
		this.id = id;
		this.name = name;
		this.age=age;
		/*
		 * Age cloneAge = new Age(); cloneAge.setDay(age.getDay());
		 * cloneAge.setMonth(age.getMonth()); cloneAge.setYear(age.getYear()); this.age
		 * = cloneAge;
		 */
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Age getAge() throws CloneNotSupportedException {
		return (Age) age.clone();
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	public static void main(String[] args) throws CloneNotSupportedException {

		Student student = new Student(2, "Sonu", new Age(11,"Dec",1995));
		System.out.println(student.getAge().getYear());
		Age age = student.getAge();

		age.setYear(2000);
		System.out.println(student.getAge().getYear());

	}

}
