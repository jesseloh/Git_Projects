package TheatreSeatBooking;

/*
Simple seat reservation app.
Allows input for booking single seat or contiguous seats.
For contiguous seats, app will identify the closest seat options based on seat/row selection and number of seats.
Features for future development:
- saving of status of reservations when quitting and loading from last saved status
- generate booking id for reserved seats
- cancellation of bookings
 */


import java.util.Scanner;

public class BookingAgent {

    //Setting of rows and total number of seats. Assume that each row has the same number of seats.
    static int rows = 10;
    static int totalSeats = 100;
    static Theatre victoriaConcertHall = new Theatre("Victoria Concert Hall", rows, totalSeats);

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        victoriaConcertHall.printSeatMap();
        menuOptions();
    }

    public static void menuOptions() {
        System.out.print("""
                [Menu]
                [1] - Book single seat
                [2] - Book contiguous seats within a chosen row
                [3] - Book contiguous seats* within a range of rows
                      *Contiguous seats will be provided based on seats available closest to preference given within given range
                [4] - Quit
                
                Menu Selection:\s""");

        String option = scanner.next().toUpperCase();
        switch (option) {
            case "1" -> {
                System.out.print("Row: ");
                char row = Character.toUpperCase(scanner.next().charAt(0));
                System.out.print("Seat number: ");
                int seatNo = scanner.nextInt();
                bookSeat(victoriaConcertHall, row, seatNo);
                menuOptions();
            }
            case "2" -> {
                System.out.print("Number of seats: ");
                int noOfSeats = scanner.nextInt();
                System.out.print("Row: ");
                char row = Character.toUpperCase(scanner.next().charAt(0));
                System.out.print("From seat number: ");
                int seatNoStart = scanner.nextInt();
                System.out.print("To seat number: ");
                int seatNoEnd = scanner.nextInt();
                bookSeats(victoriaConcertHall, noOfSeats, row, seatNoStart, seatNoEnd);
                menuOptions();
            }
            case "3" -> {
                System.out.print("Number of seats: ");
                int noOfSeats = scanner.nextInt();
                System.out.print("From Row: ");
                char rowStart = Character.toUpperCase(scanner.next().charAt(0));
                System.out.print("To Row: ");
                char rowEnd = Character.toUpperCase(scanner.next().charAt(0));
                System.out.print("From seat number: ");
                int seatNoStart = scanner.nextInt();
                System.out.print("To seat number: ");
                int seatNoEnd = scanner.nextInt();
                bookSeats(victoriaConcertHall, noOfSeats, rowStart, rowEnd, seatNoStart, seatNoEnd);
                menuOptions();
            }
            case "Q" -> {break;}
            default -> {
                System.out.println("Invalid option, please try again");
                menuOptions();
            }

        }
    }

    private static void bookSeat(Theatre theatre, char row, int seatNo) {

        String seat = theatre.reserveSeat(row, seatNo);
        if (seat != null) {
            System.out.println("Congratulations! Your reserved seat is " + seat);
            theatre.printSeatMap();
        } else {
            System.out.println("Sorry! Unable to reserve " + row + seatNo);
        }
    }

    private static void bookSeats(Theatre theatre, int tickets, char minRow,
                                  int minSeat, int maxSeat ) {

        bookSeats(theatre, tickets, minRow, minRow, minSeat, maxSeat);
    }

    private static void bookSeats(Theatre theatre, int tickets,
                                  char minRow, char maxRow,
                                  int minSeat, int maxSeat) {

        var seats =
                theatre.reserveSeats(tickets, minRow, maxRow, minSeat, maxSeat);
        if (seats != null) {
            System.out.println("Congratulations! Your reserved seats are " + seats);
            theatre.printSeatMap();
        } else {
            System.out.println("Sorry! No matching contiguous seats in rows: "
                    + minRow + " - " + maxRow);
        }
    }
}
