import java.util.Scanner;

/**
 * An object of this class is a TV Programme
 * Attributes of a TV Programme are ID, Name, Start time and End time
 */
public class TVShow implements Watchable{
    String showID;
    String showName;
    double startTime;
    double endTime;

    /**
     * Parameterized constructor creates a new TV Show with passed parameters
     * @param showID Id
     * @param showName Name
     * @param startTime Start Time
     * @param endTime End Time
     */
    public TVShow(String showID, String showName, double startTime, double endTime) {
        this.showID = showID;
        this.showName = showName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     *Copy constructor creates a TV show with a new id and other attributes same as passed object
     * @param tvShow TV show object
     * @param showID New ID
     */
    public TVShow(TVShow tvShow, String showID) {
        this.showID = showID;
        this.showName = tvShow.showName;
        this.startTime = tvShow.startTime;
        this.endTime = tvShow.endTime;
    }

    public String getShowID() {
        return showID;
    }

    public String getShowName() {
        return showName;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setShowID(String showID) {
        this.showID = showID;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public String toString() {
        return "Show ID: " + this.showID + ", Show Name: " + this.showName + ", Start Time: " + this.startTime + ", End Time: " +this.endTime;
    }

    /**
     * Checks whether two TV show have same attributes except the Show ID
     * @param obj TV show object
     * @return true if all attributes are same else false
     */
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        else{
            TVShow tvShow = (TVShow) obj;
            return this.showName.equals(tvShow.showName) && this.startTime == tvShow.startTime && this.endTime == tvShow.endTime;
        }
    }

    /**
     * Clone method that overrides the clone of object class
     * @return a clone of the TV show with new ID
     */
    public Object clone(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a new Show Id: ");
        String newShowID = sc.next();
        return new TVShow(this, newShowID);
    }

    /**
     * Implementation of method from watchable, checks time clash between two shoes
     * @param s Tv show object
     * @return "Same Time" if there is a clash and the show is on same time as other, "Different Time" if there's no clash, "Some Overlap" if there is a clash and two show have any overlap in their timings
     */
    public String isOnSameTime(TVShow s){
        //System.out.println(s.showID+ " " +s.startTime+ " " +s.endTime+  "    "+this.showID+ " " +this.startTime+ " " +this.endTime);
        if (this.startTime == s.startTime && this.endTime == s.endTime) return "Same Time";
        else if (this.endTime <= s.startTime || this.startTime >= s.endTime )   return "Different Time";
        else return "Some Overlap";
    }

//    public static void main(String[] args){
//        TVShow s1 = new TVShow("1", "a", 20.00, 22.00); //base time
//        TVShow s2 = new TVShow("2", "b", 21.00, 21.30); //overlap
//        TVShow s3 = new TVShow("3", "c", 20.30, 21.30); //overlap
//        TVShow s4 = new TVShow("4", "d", 21.30, 22.30); //overlap
//        TVShow s5 = new TVShow("5", "e", 19.30, 21.300); //overlap
//        TVShow s6 = new TVShow("6", "f", 21.00, 22.00); //overlap
//        TVShow s7 = new TVShow("7", "g", 20.00, 21.00); //overlap
//        TVShow s8 = new TVShow("8", "h", 21.00, 23.00); //overlap
//        TVShow s9 = new TVShow("9", "i", 19.30, 22.30); //overlap
//        TVShow s10 = new TVShow("10", "j", 20.00, 22.00); //same
//        TVShow s11 = new TVShow("11", "l", 19.00, 20.00); //before
//        TVShow s12 = new TVShow("12", "m", 22.00, 23.00); //after
//        TVShow s13 = new TVShow("13", "n", 19.00, 19.30); // before
//        TVShow s14 = new TVShow("14", "o", 22.30, 23.00); // after
//
//        System.out.println(s1.isOnSameTime(s2));
//        System.out.println(s1.isOnSameTime(s3));
//        System.out.println(s1.isOnSameTime(s4));
//        System.out.println(s1.isOnSameTime(s5));
//        System.out.println(s1.isOnSameTime(s6));
//        System.out.println(s1.isOnSameTime(s7));
//        System.out.println(s1.isOnSameTime(s8));
//        System.out.println(s1.isOnSameTime(s9));
//        System.out.println(s1.isOnSameTime(s10));
//        System.out.println(s1.isOnSameTime(s11));
//        System.out.println(s1.isOnSameTime(s12));
//        System.out.println(s1.isOnSameTime(s13));
//        System.out.println(s1.isOnSameTime(s14));
//
//
//    }

}

