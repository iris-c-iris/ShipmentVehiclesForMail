/**
 * base class for data manipulation regarding vehicle type, revenue, shipment income, and dispatch destination
 *
 * @author Iris Carrigg
 * @version 11/09/2022
 */
public abstract class Vehicle
{
    // declare instance variables
    private String destination;
    private double income;
    private static double revenue;

    /**
     * standard constructor for objects of class Vehicle
     * initializes instance variables, creates dummy object with no data
     * @param none
     * @return none
     */
    protected Vehicle()
    {
        // initialize instance variables
        this.destination = "";
        this.income = 0.0;
        revenue = 0.0;
    }

    /**
     * constructor for objects of class Vehicle
     * initializes instance variables, creates dummy object with no data
     * @param String, double
     * @return none
     */
    protected Vehicle( String destination, double income )
    {
        // initialize instance variables
        this.destination = destination;
        this.income = income;
        revenue = getRevenue();
    }

    /**
     * gets value of this.destination
     * @param none
     * @return String
     */
    public String getDestination()
    {
        return this.destination;
    }

    /**
     * gets value of this.income
     * @param none
     * @return double
     */
    public double getIncome()
    {
        return this.income;
    }

    /**
     * sets value of revenue
     * @param double
     * @return void
     */
    public static void setRevenue( double addRevenue )
    {
        revenue += addRevenue;
    }

    /**
     * gets value of revenue
     * @param none
     * @return double
     */
    public static double getRevenue()
    {
        return revenue;
    }

    /**
     * abstact method to be inherited and overridden
     * will add profit/loss for vehicle to revenue 
     * @param none
     * @return void
     */
    public abstract void addToTotalProfit();

    /**
     * abstact method to be inherited and overridden
     * will calculate profit/loss for vehicle
     * @param none
     * @return double
     */
    public abstract double calculateVehicleProfit();
}
