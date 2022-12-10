
/**
 * manipulates data about shipments needing to be dispatched via boat 
 *
 * @author Iris Carrigg
 * @version 11/09/2022
 */
public class Boat extends Vehicle
{
    // declare instance variable
    private int crew;

    /**
     * standard constructor for objects of class Boat
     * creates dummy object with no data
     * @param none
     * @return none
     */
    public Boat()
    {
        // invoke same class constructor
        this( "", 0.0, 0.0 );
    }

    /**
     * constructor for objects of class Boat
     * initializes instance variable, creates dummy object with no data
     * @param String, double, double
     * @return none
     */
    public Boat( String destination, double weight, double income )
    {
        // initialize instance variable, invoke superclass constructor 
        super( destination, income );
        this.crew = ( int )( Math.ceil( weight / 500 ) );
    }

    /**
     * gets value of this.crew
     * @param none
     * @return int
     */
    public int getCrew()
    {
        return this.crew;
    }

    /**
     * overridden inherited method
     * adds profit/loss for vehicle to revenue 
     * @param none
     * @return void
     */
    @Override
    public void addToTotalProfit()
    {
        super.setRevenue( this.calculateVehicleProfit() );
    }

    /**
     * overridden inherited method
     * calculates profit/loss for vehicle
     * @param none
     * @return double
     */
    @Override
    public double calculateVehicleProfit()
    {
        // declare and initialize local variable
        double profit = 0.0;

        // check destination and calculate profit accordingly
        switch( super.getDestination() )
        {
            case "Lancre":
                profit = super.getIncome() - 1000 - ( this.crew * 50 );
                break;
            case "Ueberwald":
                profit = super.getIncome() - 3000 - ( this.crew * 150 );
                break;
            case "Borogravia":
                profit = super.getIncome() - 3000 - ( this.crew * 150 );
                break;
            case "Klatch":
                profit = super.getIncome() - 1200 - ( this.crew * 60 );
                break;
        }

        return profit;
    }
}
