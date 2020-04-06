package optionals;


import java.util.Optional;

public class Car {
	private Insurance insurance;

	public Car(Insurance insurance) {
		this.insurance = insurance;
	}
	
	public Optional<Insurance> getInsurance() {
		return Optional.ofNullable(insurance);
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}
}