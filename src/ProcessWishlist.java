import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ProcessWishlist {

//    ShowList listGuide ;
//    ShowList listInterests = new ShowList(listGuide);

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

    private void addToGuide(ShowList listGuide, String id, String name, double start, double end) {
        if (!listGuide.contains(id)){
            TVShow show = new TVShow(id, name, start, end);
            if (!listGuide.equals(show)){
                listGuide.addToStart(show);
            }
        }
    }

    void processInterests(ArrayList<String> watching, ArrayList<String> wishlist) throws FileNotFoundException {
//        ArrayList<String> watching  = new ArrayList<String>(0);
//        ArrayList<String> wishlist  = new ArrayList<String>(0);

        Scanner sc = new Scanner(new FileInputStream("Interest.txt"));
        if (sc.next().equals("Watching")){
            while(sc.hasNextLine()){
                sc.nextLine();
                if (sc.next().equals("Wishlist")) break;
                watching.add(sc.next());
            }
            while(sc.hasNextLine()){
                sc.nextLine();
                wishlist.add(sc.next());
            }
        }
        else System.out.println("File is not in valid format");
    }

    public static void main(String[] args) throws FileNotFoundException {

        ShowList listGuide = new ShowList();

        ProcessWishlist pwl = new ProcessWishlist();
        pwl.processGuide(listGuide);

        ArrayList<String> watching  = new ArrayList<String>(0);
        ArrayList<String> wishlist  = new ArrayList<String>(0);
        pwl.processInterests(watching, wishlist);

        for (int i = 0; i < wishlist.size(); i++){
            for (int j = 0; j < watching.size(); j++){
                if ((listGuide.find(wishlist.get(i)).show.isOnSameTime(listGuide.find(watching.get(j)).show)).equals("Same Time"))
                    System.out.println("User can't watch show " + listGuide.find(wishlist.get(i)).show.getShowID() + " as they will begin another show at same time.");
                if ((listGuide.find(wishlist.get(i)).show.isOnSameTime(listGuide.find(watching.get(j)).show)).equals("Some Overlap"))
                    System.out.println("User can't watch show " + listGuide.find(wishlist.get(i)).show.getShowID() + " as they will will not be finished with a show they are watching.");
                if ((listGuide.find(wishlist.get(i)).show.isOnSameTime(listGuide.find(watching.get(j)).show)).equals("Different Time"))
                    System.out.println("User can watch show " + listGuide.find(wishlist.get(i)).show.getShowID() + " as they are not watching anything else at the same time.");
            }
        }


    }





}