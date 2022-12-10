/**
 * manipulates data about shipments needing to be dispatched via coach 
 *
 * @author Iris Carrigg
 * @version 11/09/2022
 */
public class Coach extends Vehicle
{
    // declare instance variables
    private int horses;
    private int crew;

    /**
     * standard constructor for objects of class Coach
     * creates dummy object with no data
     * @param none
     * @return none
     */
    public Coach()
    {
        // invoke same class constructor
        this( "", 0.0, 0.0 );
    }

    /**
     * constructor for objects of class Coach
     * initializes instance variables, creates dummy object with no data
     * @param String, double, double
     * @return none
     */
    public Coach( String destination, double weight, double income )
    {
        // initialize instance variables, invoke superclass constructor 
        super( destination, income );
        this.horses = ( int )( Math.ceil( weight / 100 ) );

        // check if more crew members are needed 
        if(this.horses > 4)
            this.crew = 2;
        else
            this.crew = 1;
    }

    /**
     * gets value of this.horses
     * @param none
     * @return int
     */
    public int getHorses()
    {
        return this.horses;
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
                profit = super.getIncome() - 200 - ( this.crew * 50 ) - ( this.horses * 30 );
                break;
            case "Ueberwald":
                profit = super.getIncome() - 200 - ( this.crew * 150 ) - ( this.horses * 100 );
                break;
            case "Borogravia":
                profit = super.getIncome() - 200 - ( this.crew * 150 ) - ( this.horses * 100 );
                break;
            case "Klatch":
                profit = super.getIncome() - 200 - ( this.crew * 60 ) - ( this.horses * 50 );
                break;
        }

        return profit;
    }
}
