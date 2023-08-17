package cinema;
import java.util.Scanner;
public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        menu(rows, seats);
    }
    public static void menu(int rows, int seats) {
        Scanner scanner = new Scanner(System.in);
        char[][] cinema = new char[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = 'S';
            }
        }
        System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
        int choice = scanner.nextInt();
        while(choice != 0) {
            if (choice == 1) {
                showSeats(cinema, rows, seats);
            } else if (choice == 2) {
                buyTicket(cinema, rows, seats);
            } else if (choice == 0) {
                System.exit(0);
            }
            else if (choice==3){
                statistics(cinema, rows, seats);
            }
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            choice = scanner.nextInt();
        }
    }

    public static void showSeats(char[][] cinema, int rows, int seats) {
        System.out.print("Cinema: \n  ");
        for (int j = 0; j < seats; j++) {
            System.out.print(j + 1 + " ");
        }
        System.out.println();
        for(int i = 0; i < rows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void buyTicket(char[][] cinema, int rows, int seats) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = scanner.nextInt();
        if (rowNumber >= 1 && rowNumber <= rows && seatNumber >= 1 && seatNumber <= seats) {
            if (cinema[rowNumber - 1][seatNumber - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
                buyTicket(cinema, rows, seats);
            }

            cinema[rowNumber - 1][seatNumber - 1] = 'B';

            int totalSeats = rows * seats;
            int ticketPrice = (totalSeats <= 60 || rowNumber <= rows / 2) ? 10 : 8;
            System.out.println("Ticket price: $" + ticketPrice);

        } else {
            System.out.println("Wrong input!");
            buyTicket(cinema, rows, seats);        }



    }
    public static void statistics(char [][]cinema,int raws, int seats){
        int totalSeats = raws * seats;
        int purchasedTickets = 0;
        int currentIncome = 0;
        int totalIncome = 0;

        for (int i = 0; i < raws; i++) {
            for (int j = 0; j < seats; j++) {
                if (cinema[i][j] == 'B') {
                    purchasedTickets++;
                    int ticketPrice = (totalSeats <= 60 || i < raws / 2) ? 10 : 8;
                    currentIncome += ticketPrice;

                }
            }
        }
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else {
            totalIncome = (raws / 2 * seats * 10) + (raws - raws / 2) * seats * 8;
        }
        double percentage = (double) purchasedTickets / totalSeats * 100;
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

}