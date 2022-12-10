/**
 * manipulates data about shipments needing to be dispatched via train 
 *
 * @author Iris Carrigg
 * @version 11/09/2022
 */
public class Train extends Vehicle
{
    // declare instance variable
    private int cars;

    /**
     * standard constructor for objects of class Train
     * creates dummy object with no data
     * @param none
     * @return none
     */
    public Train()
    {
        // invoke same class constructor
        this( "", 0, 0.0 );
    }
    
    /**
     * constructor for objects of class Train
     * initializes instance variables, creates dummy object with no data
     * @param String, int, double
     * @return none
     */
    public Train( String destination, int cars, double income )
    {
        // initialize instance variable, invoke superclass constructor 
        super( destination, income );
        this.cars = cars;
    }

    /**
     * gets value of this.cars
     * @param none
     * @return int
     */
    public int getCars()
    {
        return this.cars;
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
                profit = super.getIncome() - 600 - ( this.cars * 100 );
                break;
            case "Ueberwald":
                profit = super.getIncome() - 4000 - ( this.cars * 100 );
                break;
            case "Borogravia":
                profit = super.getIncome() - 4000 - ( this.cars * 100 );
                break;
            case "Klatch":
                profit = super.getIncome() - 2100 - ( this.cars * 100 );
                break;
        }

        return profit;
    }
}
