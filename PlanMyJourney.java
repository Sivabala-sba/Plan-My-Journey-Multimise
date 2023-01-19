import java.util.Scanner;

class Train {
    String trainName;
    String trainNumber;
    String from;
    String to;
    int reservedSeats;
    int acSeats;
    int nonAcSeats;
    float reservedFare;
    float acFare;
    float nonAcFare;
    float tax;

    Train(String trainName, String trainNumber, String from, String to, int reservedSeats, int acSeats, int nonAcSeats, float reservedFare, float acFare, float nonAcFare, float tax) {
        this.trainName = trainName;
        this.trainNumber = trainNumber;
        this.from = from;
        this.to = to;
        this.reservedSeats = reservedSeats;
        this.acSeats = acSeats;
        this.nonAcSeats = nonAcSeats;
        this.reservedFare = reservedFare;
        this.acFare = acFare;
        this.nonAcFare = nonAcFare;
        this.tax = tax;
    }
}

class Ticket {
    String passengerName;
    String trainName;
    String trainNumber;
    String from;
    String to;
    String coach;
    int numberOfTickets;
    boolean food;
    boolean insurance;
    float totalCost;

    Ticket(String passengerName, String trainName, String trainNumber, String from, String to, String coach, int numberOfTickets, boolean food, boolean insurance, float totalCost) {
        this.passengerName = passengerName;
        this.trainName = trainName;
        this.trainNumber = trainNumber;
        this.from = from;
        this.to = to;
        this.coach = coach;
        this.numberOfTickets = numberOfTickets;
        this.food = food;
        this.insurance = insurance;
        this.totalCost = totalCost;
    }
}

class PlanMyJourney {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Train[] trains = {
                new Train("AAA","1", "Chennai", "Bangalore", 5, 10, 10, 550, 380, 210, 3),
                new Train("BBB", "2","Chennai", "Delhi", 5, 10, 10, 550, 380, 210, 3),
                new Train("CCC", "3","Delhi", "Chennai", 5, 10, 10, 550, 380, 210, 3),
                new Train("DDD", "4","Bangalore", "Chennai", 5, 10, 10, 550, 380, 210, 3),
                new Train("EEE", "5","Chennai", "Mumbai", 5, 10, 10, 550, 380, 210, 3)
        };

        // prompt the user to enter the train number and from-to location
        System.out.print("Enter the train number: ");
        String trainNumber = input.nextLine();
        System.out.print("Enter the from location: ");
        String from = input.nextLine();
        System.out.print("Enter the to location: ");
        String to = input.nextLine();

        // search for the train with the given number and from-to location
        Train selectedTrain = null;
        for (Train train : trains) {
            if (train.trainNumber.equals(trainNumber) && train.from.equals(from) && train.to.equals(to)) {
                selectedTrain = train;
                break;
            }
        }

        if (selectedTrain == null) {
            System.out.println("Train not found with the given number and from-to location.");
            return;
        }

        // prompt the user to enter the coach and number of tickets
        System.out.print("Enter the coach (R/A/N): ");
        String coach = input.nextLine();
        System.out.print("Enter the number of tickets: ");
        int numberOfTickets = input.nextInt();

        // calculate the fare and total cost
        float fare = 0;
        if (coach.equals("R")) {
            fare = selectedTrain.reservedFare;
        } else if (coach.equals("A")) {
            fare = selectedTrain.acFare;
        } else if (coach.equals("N")) {
            fare = selectedTrain.nonAcFare;
        }
        float totalCost = fare * numberOfTickets + (fare * numberOfTickets * selectedTrain.tax / 100);

        // prompt the user to enter the passenger name and whether they want food and insurance
        input.nextLine(); // to consume the remaining newline character
        System.out.print("Enter the passenger name: ");
        String passengerName = input.nextLine();
        System.out.print("Do you want food (y/n)? ");
        boolean food = input.nextLine().equals("y");
        System.out.print("Do you want insurance (y/n)? ");
        boolean insurance = input.nextLine().equals("y");

// calculate the final cost with food and insurance
        if (food) {
            totalCost += 100;
        }
        if (insurance) {
            totalCost += 15 * numberOfTickets;
        }

// create a new Ticket object with the booking details
        Ticket booking = new Ticket(passengerName, selectedTrain.trainName, selectedTrain.trainNumber, selectedTrain.from, selectedTrain.to, coach, numberOfTickets, food, insurance, totalCost);

// check for available seats
        /*SeatsAvailability seats = new SeatsAvailability(selectedTrain.reservedSeats, selectedTrain.acSeats, selectedTrain.nonAcSeats);
        if (coach.equals("R") && seats.reservedSeats < numberOfTickets) {
            System.out.println("Not enough reserved seats available.");
            return;
        } else if (coach.equals("A") && seats.acSeats < numberOfTickets) {
            System.out.println("Not enough AC seats available.");
            return;
        } else if (coach.equals("N") && seats.nonAcSeats < numberOfTickets) {
            System.out.println("Not enough non-AC seats available.");
            return;
        }

// update the available seats
        if (coach.equals("R")) {
            seats.reservedSeats -= numberOfTickets;
        } else if (coach.equals("A")) {
            seats.acSeats -= numberOfTickets;
        } else if (coach.equals("N")) {
            seats.nonAcSeats -= numberOfTickets;
        }*/

// print the booking details
        System.out.println("Booking details:");
        System.out.println("Passenger name: " + booking.passengerName);
        System.out.println("Train name: " + booking.trainName);
        System.out.println("Train number: " + booking.trainNumber);
        System.out.println("From: " + booking.from);
        System.out.println("To: " + booking.to);
        System.out.println("Coach: " + booking.coach);
        System.out.println("Number of tickets: " + booking.numberOfTickets);
        System.out.println("Food: " + (booking.food ? "Yes" : "No"));
        System.out.println("Insurance: " + (booking.insurance ? "Yes" : "No"));
        System.out.println("Total cost: Rs." + booking.totalCost);
    }
}