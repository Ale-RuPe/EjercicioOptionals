package optionals;

import java.util.Optional;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class EjercicioOptionals {
	
	public static void main(String[] args) {
		EjercicioOptionals main = new EjercicioOptionals();
		
		Insurance insurance = new Insurance("Segurito");
		Car car = new Car(insurance);
		Person person = new Person(car,18);
		
		System.out.println( main.getCarInsuranceName( Optional.of(person), 18 ) );
		
		car= null;
		Optional<Car> c = Optional.ofNullable(car);
		System.out.println( main.nullSafeFindCheapestInsurance(Optional.of(person), c).isPresent() );		
	}
	
	public String getCarInsuranceName(Optional<Person> person, int minAge) {
		//return p.getCar().getInsurance().getName();
		
		return person
				.filter( (p) -> p.getAge() >= minAge )
				.flatMap(Person::getCar)
				.flatMap(Car::getInsurance)
				.map(Insurance::getName)
				.orElse("Unknown");
	}
	
	Insurance findCheapestInsurance(Person p, Car c) {
		// logica blablabla
		Insurance cheapestCompany = new Insurance("Compañia Barata");
		return cheapestCompany;
	}
	
	public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> p, Optional<Car> c) {
		if( p.isPresent() && c.isPresent()) {
			return Optional.of( findCheapestInsurance(p.get(), c.get()) );
		}
		else {
			return Optional.empty();
		}
	}
	
	public int readDuration(Properties prop, String name) {		
		return Stream.of(prop)
			.filter(p->p.containsKey(name))
			.filter(p -> !p.getProperty(name).equals(null))
			.filter(  p->Pattern.matches("(-?[0-9]*)",p.getProperty(name)) )
			.map( p-> p.getProperty(name)  )
			.map(Integer::parseInt)
			.findFirst()
			.orElse(0);
	}
}
