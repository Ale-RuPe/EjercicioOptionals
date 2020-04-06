package optionals;

import java.util.Optional;

public class Person {
	private Car car;
	private int age;
	
	public Person(Car car, int age) {
		this.car = car;
		this.age = age;
	}
	
	public Optional<Car> getCar() {
		return Optional.ofNullable(car);
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}