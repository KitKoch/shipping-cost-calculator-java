import java.util.Scanner;

public final class ShippingCostCalculator {

    private ShippingCostCalculator(){}

    private enum Shipping {
        LOW(0, 1, 3.5),
        MID_LOW(1, 3, 5.5),
        MID_HIGH(3, 10, 8.5),
        HIGH(10, 20, 10.5);

        final double lower, upper, cost;

        Shipping(double lower, double upper, double cost) {
            this.lower = lower;
            this.upper = upper;
            this.cost = cost;
        }

        static Shipping category(double weight) {
            for(int i = 0; i < values().length; i++) {
                if(weight > values()[i].lower && weight <= values()[i].upper) {
                    return values()[i];
                }
            }
            return null;
        }
    }

    private static double weightInputDouble(Scanner input, String msg) {
        final double max = Shipping.HIGH.upper;
        while(true) {
            System.out.print(msg);
            if(input.hasNextDouble()) {
                double weight = input.nextDouble();
                if(weight > 0 && weight <= max) {
                    return weight;
                }
                if(weight > max) {
                    System.out.println("The package cannot be shipped");
                    System.exit(0);
                }
                System.out.println("Invalid input");
            } else {
                System.out.println("Please enter a number");
                input.next();
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            double weight = weightInputDouble(input, "Please enter weight: ");
            Shipping category = Shipping.category(weight);
            if(category == null) {
                System.out.println("The package cannot be shipped");
                return;
            }
            System.out.printf("The shipping cost is %.2f", category.cost);
        }
    }
}
