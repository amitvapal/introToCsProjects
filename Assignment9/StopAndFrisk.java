import java.util.*;

/**
 * The StopAndFrisk class represents stop-and-frisk data, provided by
 * the New York Police Department (NYPD), that is used to compare
 * during when the policy was put in place and after the policy ended.
 * 
 * @author Tanvi Yamarthy
 * @author Vidushi Jindal
 */
public class StopAndFrisk {

    /*
     * The ArrayList keeps track of years that are loaded from CSV data file.
     * Each SFYear corresponds to 1 year of SFRecords. 
     * Each SFRecord corresponds to one stop and frisk occurrence.
     */ 
    private ArrayList<SFYear> database; 

    /*
     * Constructor creates and initializes the @database array
     * 
     * DO NOT update nor remove this constructor
     */
    public StopAndFrisk () {
        database = new ArrayList<>();
    }

    /*
     * Getter method for the database.
     * *** DO NOT REMOVE nor update this method ****
     */
    public ArrayList<SFYear> getDatabase() {
        return database;
    }

    /**
     * This method reads the records information from an input csv file and populates 
     * the database.
     * 
     * Each stop and frisk record is a line in the input csv file.
     * 
     * 1. Open file utilizing StdIn.setFile(csvFile)
     * 2. While the input still contains lines:
     *    - Read a record line (see assignment description on how to do this)
     *    - Create an object of type SFRecord containing the record information
     *    - If the record's year has already is present in the database:
     *        - Add the SFRecord to the year's records
     *    - If the record's year is not present in the database:
     *        - Create a new SFYear 
     *        - Add the SFRecord to the new SFYear
     *        - Add the new SFYear to the database ArrayList
     * 
     * @param csvFile
     */
    public void readFile ( String csvFile ) {

        // DO NOT remove these two lines
        StdIn.setFile(csvFile); // Opens the file
        StdIn.readLine();       // Reads and discards the header line

        // WRITE YOUR CODE HERE
        String text = "";
        while((text = StdIn.readLine()) != null){
            String[] recordEntries = text.split(",");

            int year = Integer.parseInt(recordEntries[0]);

            String description = recordEntries[2];

            String gender = recordEntries[52];

            String race = recordEntries[66];

            String location = recordEntries[71];

            Boolean arrested = recordEntries[13].equals("Y");

            Boolean frisked = recordEntries[16].equals("Y");

            SFRecord record = new SFRecord(description, arrested, frisked, gender, race, location);
            boolean found = false;

            for(SFYear sfYear : database){
                if(sfYear.getcurrentYear() == year){
                    found = true;
                    sfYear.addRecord(record);
                    break;
                } 

            }
            if(found == false){
                SFYear newYear = new SFYear(year);
                newYear.addRecord(record);
                database.add(newYear);
            

        }

            

    }
         
        
        





    }

    /**
     * This method returns the stop and frisk records of a given year where 
     * the people that was stopped was of the specified race.
     * 
     * @param year we are only interested in the records of year.
     * @param race we are only interested in the records of stops of people of race. 
     * @return an ArrayList containing all stop and frisk records for people of the 
     * parameters race and year.
     */

    public ArrayList<SFRecord> populationStopped ( int year, String race ) {

        // WRITE YOUR CODE HERE
        ArrayList<SFRecord> records = new ArrayList<>();

        for(SFYear sfYear : database){
            if(sfYear.getcurrentYear() == year){
                for(SFRecord record : sfYear.getRecordsForYear()){
                    if(record.getRace().equals(race)){
                        records.add(record);
                    }

                }


            }

        }
        return records; // update the return value
        

    }

    /**
     * This method computes the percentage of records where the person was frisked and the
     * percentage of records where the person was arrested.
     * 
     * @param year we are only interested in the records of year.
     * @return the percent of the population that were frisked and the percent that
     *         were arrested.
     */
    public double[] friskedVSArrested ( int year ) {
        
        // WRITE YOUR CODE HERE
        double[] friskedArrested = new double[2];
        double frisked = 0;
        double arrested = 0;
        double total = 0;
        for(SFYear sfYear : database){
            if(sfYear.getcurrentYear() == year){
                for(SFRecord record : sfYear.getRecordsForYear()){
                    if(record.getFrisked() == true){
                        frisked++;
                    }
                    if(record.getArrested() == true){
                        arrested++;
                    }
                    total++;
                }
            }
        }
        friskedArrested[0] = (frisked/total)*100;
        friskedArrested[1] = (arrested/total)*100;
        return friskedArrested;   
    }

    /**
     * This method keeps track of the fraction of Black females, Black males,
     * White females and White males that were stopped for any reason.
     * Drawing out the exact table helps visualize the gender bias.
     * 
     * @param year we are only interested in the records of year.
     * @return a 2D array of percent of number of White and Black females
     *         versus the number of White and Black males.
     */
    public double[][] genderBias ( int year ) {

        // WRITE YOUR CODE HERE
        double[][] genderBias = new double[2][3];
        double blackFemales = 0;
        double blackMales = 0;
        double whiteFemales = 0;
        double whiteMales = 0;
        double totalBlack = 0;
        double totalWhite = 0;
        for(SFYear sfYear : database){
            if(sfYear.getcurrentYear() == year){
                for(SFRecord record : sfYear.getRecordsForYear()){
                    if(record.getRace().equals("B")){
                        totalBlack++;
                        if(record.getGender().equals("F")){
                            blackFemales++;
                        }
                        if(record.getGender().equals("M")){
                            blackMales++;
                        }
                    }
                    if(record.getRace().equals("W")){
                        totalWhite++;
                        if(record.getGender().equals("F")){
                            whiteFemales++;
                        }
                        if(record.getGender().equals("M")){
                            whiteMales++;
                        }
                    }
                    
                }
            }
        }
        genderBias[0][0] = (blackFemales/totalBlack)*100*0.5;
        genderBias[0][1] = (whiteFemales/totalWhite)*100*0.5;
        genderBias[0][2] = (blackFemales/totalBlack)*100*0.5 + (whiteFemales/totalWhite)*100*0.5;
        genderBias[1][0] = (blackMales/totalBlack)*100*0.5;
        genderBias[1][1] = (whiteMales/totalWhite)*100*0.5;
        genderBias[1][2] = (blackMales/totalBlack)*100*0.5 + (whiteMales/totalWhite)*100*0.5;
        return genderBias; // update the return value
    }

    /**
     * This method checks to see if there has been increase or decrease 
     * in a certain crime from year 1 to year 2.
     * 
     * Expect year1 to preceed year2 or be equal.
     * 
     * @param crimeDescription
     * @param year1 first year to compare.
     * @param year2 second year to compare.
     * @return 
     */

    public double crimeIncrease ( String crimeDescription, int year1, int year2 ) {
        
        // WRITE YOUR CODE HERE
        double crimeIncrease = 0.0;
        double crime1 = 0;
        double crime2 = 0;
        double total1 = 0;
        double total2 = 0;
        for(SFYear sfYear : database){
            if(sfYear.getcurrentYear() == year1){
                for(SFRecord record : sfYear.getRecordsForYear()){
                    total1++;
                    if((record.getDescription()).indexOf(crimeDescription) != -1){
                        crime1++;
                    }
                }
            }
            if(sfYear.getcurrentYear() == year2){
                for(SFRecord record : sfYear.getRecordsForYear()){
                    total2++;
                    if((record.getDescription()).indexOf(crimeDescription) != -1){
                        crime2++;
                    }
                }
            }
        }
        
        double crimeInc = ((crime2/total2)*100) - ((crime1/total1)*100);
        return crimeInc; // update the return value 
    }

    /**
     * This method outputs the NYC borough where the most amount of stops 
     * occurred in a given year. This method will mainly analyze the five 
     * following boroughs in New York City: Brooklyn, Manhattan, Bronx, 
     * Queens, and Staten Island.
     * 
     * @param year we are only interested in the records of year.
     * @return the borough with the greatest number of stops
     */
    public String mostCommonBorough ( int year ) {

        // WRITE YOUR CODE HERE
        String mostCommonBorough = "";
        double brooklyn = 0;
        double manhattan = 0;
        double bronx = 0;
        double queens = 0;
        double statenIsland = 0;
        for(SFYear sfYear : database){
            if(sfYear.getcurrentYear() == year){
                for(SFRecord record : sfYear.getRecordsForYear()){
                    if(record.getLocation().equalsIgnoreCase("BROOKLYN")){
                        brooklyn++;
                    }
                    if(record.getLocation().equalsIgnoreCase("MANHATTAN")){
                        manhattan++;
                    }
                    if(record.getLocation().equalsIgnoreCase("BRONX")){
                        bronx++;
                    }
                    if(record.getLocation().equalsIgnoreCase("QUEENS")){
                        queens++;
                    }
                    if(record.getLocation().equalsIgnoreCase("STATEN ISLAND")){
                        statenIsland++;
                    }
                }
            }
        }
        if(brooklyn > manhattan && brooklyn > bronx && brooklyn > queens && brooklyn > statenIsland){
            mostCommonBorough = "Brooklyn";
        }
        if(manhattan > brooklyn && manhattan > bronx && manhattan > queens && manhattan > statenIsland){
            mostCommonBorough = "Manhattan";
        }
        if(bronx > manhattan && bronx > brooklyn && bronx > queens && bronx > statenIsland){
            mostCommonBorough = "Bronx";
        }
        if(queens > manhattan && queens > bronx && queens > brooklyn && queens > statenIsland){
            mostCommonBorough = "Queens";
        }
        if(statenIsland > manhattan && statenIsland > bronx && statenIsland > queens && statenIsland > brooklyn){
            mostCommonBorough = "Staten Island";
        }
        return mostCommonBorough;



        
    }

}
