package optionals;

import java.util.Optional;
import java.util.Properties;
import java.util.stream.Stream;

public class EjercicioOptionals {
	
	public static void main(String[] args) {
		EjercicioOptionals main = new EjercicioOptionals();
		
		Insurance insurance = new Insurance("Segurito");
		Car car = new Car(insurance);
		Person person = new Person(car,18);
		
		System.out.println( main.getCarInsuranceName( Optional.of(person), 18 ) );
		
		car = null;
		Optional<Person> p = Optional.ofNullable(person);
		Optional<Car> c = Optional.ofNullable(car);
		System.out.println( "NS:"+main.nullSafeFindCheapestInsurance(p, c).isPresent() );
		
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
	
	public Insurance findCheapestInsurance(Person p, Car c) {
		// logica blablabla
		Insurance cheapestCompany = new Insurance("Compania Barata");
		return cheapestCompany;
	}
	
	public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
//		if( person.isPresent() && car.isPresent()) {
//			return Optional.of( findCheapestInsurance(person.get(), car.get()) );
//		}
//		else {
//			return Optional.empty();
//		}	
		
		return person
				.filter( p-> p.getCar().equals(car) )
				.flatMap( Person::getCar )
				.map( c -> findCheapestInsurance(person.get(), c) );
	}
	
	public int readDuration(Properties prop, String name) {		
		return Stream.of(prop)
			.filter(p->p.containsKey(name))
			.filter(p -> !p.getProperty(name).equals(null))
			.filter(  p-> this.isNumeric(p.getProperty(name)) )
			.map( p-> p.getProperty(name)  )
			.map( Integer::parseInt )
			.findFirst()
			.orElse(0);
	}
	
	public boolean isNumeric(String cadena) {
        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
	
}
