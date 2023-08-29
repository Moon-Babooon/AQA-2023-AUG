package org.prog.collections;

import org.prog.cars.Car;
import org.prog.cars.Owner;

import java.util.List;

public class MapsDemo {

    private static List<Car> cars;
    private static List<Owner> carOwners;

    //TODO: add code that verifies that owners with certain name/lastname have car with certain color
    //TODO: * verify that specific Owner has car with color
    public static void main(String[] args) {
        Owner johnKyiv = getOwner("John", "Doe");
        Owner johnPoltava = getOwner("John", "Doe");

        //John Doe -> AA0000TT
        carOwners = List.of(
                johnKyiv, //red
                johnPoltava, //green
                getOwner("Jane", "Doe"),
                getOwner("Aghata", "Kristie"),
                getOwner("Alan", "Poe")
        );

        cars = List.of(
                getCar(carOwners.get(0), "AA0000TT", "blue"),
                getCar(carOwners.get(1), "AB0011TT", "blue"),
                getCar(carOwners.get(2), "AC0022TT", "green"),
                getCar(carOwners.get(2), "AB0033TT", "yellow"),
                getCar(carOwners.get(2), "AC0044TT", "red"),
                getCar(carOwners.get(3), "AD0055TT", "black"),
                getCar(carOwners.get(3), "AE0066TT", "white"),
                getCar(carOwners.get(4), "AC0077TT", "purple")
        );

//        System.out.println(checkCarOwner("red", "John", "Doe"));
//        System.out.println(checkCarOwner("blue", "John", "Doe"));
//
//        System.out.println("======================");

        System.out.println(checkSpecificOwnerHasCarWithColor(carOwners.get(1), "blue"));
        System.out.println(checkSpecificOwnerHasCarWithColorBroken(carOwners.get(1), "blue"));
    }

    private static Owner getOwner(String name, String lastName) {
        Owner owner = new Owner();
        owner.name = name;
        owner.lastName = lastName;
        return owner;
    }

    private static Car getCar(Owner owner, String plateNumber, String color) {
        Car car = new Car();
        car.owner = owner;
        car.plateNumber = plateNumber;
        car.color = color;
        return car;
    }

    //TODO: Home work NO *
    private static boolean checkCarOwner(String carColor, String name, String lastName) {
        return cars.stream().anyMatch(car -> {
            return name.equals(car.owner.name) && lastName.equals(car.owner.lastName) && carColor.equals(car.color);
        });
    }

    //TODO: Home work NO *
    private static boolean checkSpecificOwnerHasCarWithColor(Owner owner, String carColor) {
        return cars.stream().anyMatch(car -> {
            if (car.color.equals(carColor) && car.owner == owner) {
                System.out.println(car.plateNumber);
                return true;
            }
            return false;
        });
    }

    private static boolean checkSpecificOwnerHasCarWithColorBroken(Owner owner, String carColor) {
        return cars.stream().anyMatch(car -> {
            if (car.color.equals(carColor) && car.owner.equals(owner)) {
                System.out.println(car.plateNumber);
                return true;
            }
            return false;
        });
    }
}
