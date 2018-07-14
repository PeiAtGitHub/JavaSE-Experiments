package pei.java.jse.lab.language;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author pei
 */
public class GenericsDemo {

    @Test
    public void inclusiveBounds() throws Exception {

        assertThat(new CarBuilderImpl().build()).isExactlyInstanceOf(Car.class);
        assertThat(new ElectricCarBuilderImpl().build()).isExactlyInstanceOf(ElectricCar.class);

    }

    class Vechile {
    }

    class Car extends Vechile {
    }

    class ElectricCar extends Car {
    }

    interface CarBuilder<T extends Car> {
        T build();
    }

    class CarBuilderImpl implements CarBuilder<Car> {
        @Override
        public Car build() {
            return new Car();
        }
    }

    class ElectricCarBuilderImpl implements CarBuilder<ElectricCar> {
        @Override
        public ElectricCar build() {
            return new ElectricCar();
        }
    }

}
