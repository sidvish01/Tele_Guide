import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProcessWishlist {

//    ShowList listGuide ;
//    ShowList listInterests = new ShowList(listGuide);

    /**
     * Method Reads from the File that contains TV Guide
     * @param listGuide object of Show List class containing an empty linked list
     * @throws FileNotFoundException if the file is not found in the directory
     */
    void processGuide(ShowList listGuide) throws FileNotFoundException {
         Scanner sc = new Scanner(new FileInputStream("TVGuide.txt"));
         String name = "";
         String id = "";
         double start = 0.0;
         double end = 0.0;

         while(sc.hasNextLine()){
             id = sc.next();
             name = sc.next();
             sc.nextLine();
             String t = sc.next();
             if (t.equals("S")) start = sc.nextDouble();
             sc.nextLine();
             t = sc.next();
             if (t.equals("E")) end = sc.nextDouble();
             sc.nextLine();
             if (sc.hasNextLine()) sc.nextLine();

             addToGuide(listGuide, id, name, start, end);


         }
         sc.close();
    }

    /**
     * Method adds TV Show read from file to the linked list
     * @param listGuide Linked list with Tv show nodes
     * @param id id of the show to be added
     * @param name name of the show to be added
     * @param start start time of the show to be added
     * @param end end time of the show to be added
     */
    private void addToGuide(ShowList listGuide, String id, String name, double start, double end) {
        if (!listGuide.contains(id)){
            TVShow show = new TVShow(id, name, start, end);
            listGuide.addToStart(show);
//            if (!listGuide.equals(show)){
//                listGuide.addToStart(show);
//            }
        }
    }

    /**
     * Reads the from the file containing information about user preferences and adds them to either watching or wishlist Arraylist
     * @param watching Arraylist to store the id of the shows user is watching currently
     * @param wishlist Arraylist to store the id of shows that user wishes to watch
     * @throws FileNotFoundException if the file is not found in the directory
     */
    void processInterests(ArrayList<String> watching, ArrayList<String> wishlist) throws FileNotFoundException {
//        ArrayList<String> watching  = new ArrayList<String>(0);
//        ArrayList<String> wishlist  = new ArrayList<String>(0);

        Scanner sc = new Scanner(new FileInputStream("Interest.txt"));
        if (sc.nextLine().equals("Watching")){
            while(sc.hasNextLine()){
                String wID = sc.nextLine();
                if (wID.equals("Wishlist")) break;
                watching.add(wID);
            }
            while(sc.hasNextLine()){
                wishlist.add(sc.nextLine());
            }
        }
        else System.out.println("File is not in valid format");
    }

    /**
     * Driver method to check which show from the Interests.txt file will the user be able to watch considering the ones he is already watching
     * @param args ..
     * @throws FileNotFoundException if the file is not found in the directory
     */
    public static void main(String[] args) throws FileNotFoundException {

        ShowList listGuide = new ShowList();

        ProcessWishlist pwl = new ProcessWishlist();
        pwl.processGuide(listGuide);
        System.out.println("This is the list of the TV shows added to the stored in the Linked List");
        listGuide.printList();

        ArrayList<String> watching  = new ArrayList<String>(0);
        ArrayList<String> wishlist  = new ArrayList<String>(0);
        pwl.processInterests(watching, wishlist);

        int sameTime = 0;
        int differentTime = 0;
        int someOverlap = 0;

        System.out.println();
        System.out.println("Here is the details of the shows that user can/cannot watch from his wishlist-");
        System.out.println();

        for (int i = 0; i < wishlist.size(); i++){
            for (int j = 0; j < watching.size(); j++) {
                if ((listGuide.find(wishlist.get(i)).show.isOnSameTime(listGuide.find(watching.get(j)).show)).equals("Same Time")) sameTime += 1;
                if ((listGuide.find(wishlist.get(i)).show.isOnSameTime(listGuide.find(watching.get(j)).show)).equals("Some Overlap")) someOverlap += 1;
                if ((listGuide.find(wishlist.get(i)).show.isOnSameTime(listGuide.find(watching.get(j)).show)).equals("Different Time")) differentTime += 1;
            }
            if (sameTime > 0){
                System.out.println("User can't watch show " + listGuide.find(wishlist.get(i)).show.getShowID() + " as they will begin another show at same time.");
            }
            else if (someOverlap > 0){
                System.out.println("User can't watch show " + listGuide.find(wishlist.get(i)).show.getShowID() + " as they will will not be finished with a show they are watching.");
            }
            else{
                //System.out.println(differentTime == watching.size());
                System.out.println("User can watch show " + listGuide.find(wishlist.get(i)).show.getShowID() + " as they are not watching anything else at the same time.");
            }
            sameTime = 0;
            differentTime = 0;
            someOverlap = 0;
        }


    }





}