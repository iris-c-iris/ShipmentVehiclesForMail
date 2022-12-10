/**
 * Reads .txt file containing shipment info
 * manipulates read data 
 * prints report on vehicle dispatches, profits/losses
 *
 * @author Iris Carrigg
 * @version 11/09/2022
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;

public class Project6 {
    //DATE AND TIME YOU FIRST START WORKING ON THIS ASSIGNMENT (date AND time): <--------------------
    //ANSWER:      11/09/2022 9:10AM<--------------------

    //DO NOT ALTER THE MAIN METHOD
    public static void main( String[] args ) throws FileNotFoundException {
        //input stream
        Scanner in = new Scanner( new File( "info1.txt" ) );

        //read the input file and populate the array list
        ArrayList< Vehicle > allUnsorted = readData( in );

        //output for half credit
        printVehicles( allUnsorted );

        //sort the array list by destination 
        //1. Lancre, 2. Ueberwald, 3. Borogravia, 4. Klatch
        //and for each destination 1. boats, 2. trains, 3. coaches
        ArrayList< Vehicle > allSorted = sortVehicles( allUnsorted );

        //output for half credit
        printVehicles( allSorted );

        //print revenue
        System.out.printf( "Today's Ankh-Morpork Post Office Profit:  $%,10.2f%n%n", Vehicle.getRevenue() );
    } //DO NOT ALTER THE MAIN METHOD

    /**
     * Reads data from .txt file into ArrayList<>
     * @param Scanner object
     * @return ArrayList< Vehicle >
     */
    public static ArrayList< Vehicle > readData( Scanner in ) {
        // declare and initialize local variables
        String destination = "";
        String vehicleType = "";
        double weight = 0.0;
        int numberOfCars = 0;
        double income = 0.0;

        // create ArrayList 
        ArrayList< Vehicle > unsorted = new ArrayList<>();

        // read file into ArrayList
        while( in.hasNext() )
        {
            destination = in.next();
            vehicleType = in.next();

            // check for car value vs weight/income value
            if( in.hasNextInt() )
                numberOfCars = in.nextInt();
            else
                weight = in.nextDouble();

            income = in.nextDouble();

            // append objects to ArrayList
            switch( vehicleType.toLowerCase() )
            {
                case "train":
                    unsorted.add( new Train( destination, numberOfCars, income ) );
                    break;
                case "coach":
                    unsorted.add( new Coach( destination, weight, income ) );
                    break;
                case "boat":
                    unsorted.add( new Boat( destination, weight, income ) );
                    break;
            }
        }

        // add vehicle profit to total profit
        for( int i = 0; i < unsorted.size(); i++ )
        {
            unsorted.get( i ).calculateVehicleProfit();
            unsorted.get( i ).addToTotalProfit();
        }

        // close in stream
        in.close();

        return unsorted;
    }

    /**
     * sorts an ArrayList by location and vehicle type
     * @param ArrayList< Vehicle >
     * @return ArrayList< Vehicle >
     */
    public static ArrayList< Vehicle > sortVehicles( ArrayList< Vehicle > unsorted ) {
        // declare local variables
        int j, k, x, y;

        // create ArrayLists for temporary data storage while sorting
        ArrayList< Vehicle > sorted = new ArrayList<>(), placeHolderTrain = new ArrayList<>(), placeHolderBoat = new ArrayList<>(), placeHolderCoach = new ArrayList<>(), placeHolderGen = new ArrayList<>();

        // create Array of Strings containing shipment destinations
        String[] destination = { "lancre", "ueberwald", "borogravia", "klatch" };

        // iterate through Array
        for( j = 0; j < destination.length; j++ )
        {
            // iterate through unsorted ArrayList
            for( k = 0; k < unsorted.size(); k++ )
            {
                // check if destination of unsorted at index k is the same as destination[k]
                if( unsorted.get( k ).getDestination().toLowerCase().equals( destination[ j ] ) )
                {
                    // check for class instance type and append to appropriate placeHolder ArrayList
                    if( unsorted.get( k ) instanceof Train )
                        placeHolderTrain.add( unsorted.get( k ) );
                    if( unsorted.get( k ) instanceof Boat )
                        placeHolderBoat.add( unsorted.get( k ) );
                    if( unsorted.get( k ) instanceof Coach )
                        placeHolderCoach.add( unsorted.get (k ) );                
                } 
            }
        }

        // append placeHolder ArrayLists to generic placeHolder ArrayList
        placeHolderGen.addAll( placeHolderTrain );
        placeHolderGen.addAll( placeHolderBoat );
        placeHolderGen.addAll( placeHolderCoach );

        // iterate through Array
        for( j = 0; j < destination.length; j++ )
        {
            // iterate through unsorted ArrayList
            for( x = 0; x < unsorted.size(); x++ )
            {
                // check if destination[x] is the same as destination of placeHolderGen at index k and that for correct class instance type
                if( ( x < placeHolderGen.size() ) && ( placeHolderGen.get( x ).getDestination().toLowerCase().equals( destination[ j ] ) ) && ( placeHolderGen.get( x ) instanceof Train ) )
                {
                    sorted.add( placeHolderGen.get( x ) );
                }
                if( ( x < placeHolderGen.size() ) && ( placeHolderGen.get( x ).getDestination().toLowerCase().equals( destination[ j ] ) ) && ( placeHolderGen.get( x ) instanceof Boat ) )
                {
                    sorted.add( placeHolderGen.get( x ) );
                }
                if( ( x < placeHolderGen.size() ) && ( placeHolderGen.get( x ).getDestination().toLowerCase().equals( destination[ j ] ) ) && ( placeHolderGen.get( x ) instanceof Coach ) )
                {
                    sorted.add( placeHolderGen.get( x ) );
                }
            }
        }

        return sorted;
    }

    /**
     * prints a report of vehicle dispatches
     * @param ArrayList< Vehicle >
     * @return void
     */
    public static void printVehicles( ArrayList< Vehicle > v ) {
        // declare and initialize local variable
        String profitOrLoss = "";

        System.out.println( "Ankh-Morpork Post Office mail dispatches:" );

        // iterate through ArrayList
        for ( Vehicle vehicle : v ) {
            // check for profit or loss
            if( vehicle.calculateVehicleProfit() >= 0 )
                profitOrLoss = "PROFIT";
            else
                profitOrLoss = "LOSS";  

            // check for instance of Boat, Train, or Coach and print appropriate report
            if( vehicle instanceof Boat ) {
                System.out.printf( "Boat  to %10s", vehicle.getDestination() );
                System.out.printf( " (%3d crews)%9s: $ %,9.2f%n", ( (Boat)vehicle ).getCrew(),profitOrLoss, Math.abs( ( (Boat)vehicle ).calculateVehicleProfit() ) );
            }
            else if( vehicle instanceof Train ) {
                System.out.printf( "Train to %10s", vehicle.getDestination() );
                System.out.printf(" (%3d cars)%10s: $ %,9.2f%n", ( (Train)vehicle ).getCars(), profitOrLoss, Math.abs( ( (Train)vehicle ).calculateVehicleProfit() ) );
            }
            else {
                System.out.printf( "Coach to %10s", vehicle.getDestination() );
                System.out.printf( " (%3d horses)%8s: $ %,9.2f%n", ( (Coach)vehicle ).getHorses(), profitOrLoss, Math.abs( ( (Coach)vehicle ).calculateVehicleProfit() ) );
            }
        }

        System.out.println();
    }
}
