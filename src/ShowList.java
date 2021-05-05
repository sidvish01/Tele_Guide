import java.util.NoSuchElementException;

/**
 * Class that implements a linked list with TV show objects as its nodes
 */
public class ShowList {

    private ShowNode head;
    private int size;

    /**
     * Default constructor
     */
    ShowList(){
        this.head = null;
        size = 0;
    }

    /**
     * Copy constructor creates another list similar to the passed list
     * @param list Object that contains a Linked list of TV shoes
     */
    ShowList(ShowList list){
      this.head = list.head;
      this.size = list.size;
    }

    /**
     * Adds TV show to the start of the list
     * @param show Object of TV show type
     */
    void addToStart(TVShow show){
        ShowNode n = new ShowNode(show, head);
        head = n;
        this.size += 1;
        n = null;
    }

    /**
     * Adds TV show at a given index in the list
     * @param show Object of TV show type
     * @param index position where the Tv show needs to be added
     */
    void insertAtIndex(TVShow show, int index){
        if (!(index < this.size || index >= 0)) throw new NoSuchElementException();
        else{
            ShowNode n = this.head;
            for (int i = 0 ; i < index; i++){
                n = n.next;
            }
            n.next = new ShowNode(show, n.next);
            this.size += 1;
        }
    }

    /**
     * Deletes TV show at a given index in the list
     * @param index position of the show that needs to be deleted
     */
    void deleteFromIndex(int index){
        if (!(index < this.size && index >= 0)) throw new NoSuchElementException();
        else{
            ShowNode n = this.head;
            for (int i = 0 ; i < index; i++){
                n = n.next;
            }
            n.next = n.next.getNext();
            this.size -= 1;
        }
    }

    /**
     * deletes Tv show from the start of the list
     * @return false if the list is empty false otherwise
     */
    boolean deleteFromStart(){
        if (this.head != null){
            this.head = head.next;
            this.size -= 1;
            return true;
        }
        else return false;
    }

    /**
     * Replaces a Tv show with another at a given index
     * @param show Replacement Tv show
     * @param index position of Tv show that needs to be replaced
     */
    void replaceAtIndex(TVShow show, int index){
        if (!(index < this.size && index >= 0)) return;
        else{
            ShowNode n = this.head;
            for (int i = 0 ; i < index; i++){
                n = n.next;
            }
            n.next = new ShowNode(show, n.next.getNext());
        }
    }

    /**
     * Returns the node of TV show if its found in the list
     * @param id Id that of the TV show that is to be searched for in the list
     * @return Node containing Tv Show
     */
    ShowNode find(String id){
        ShowNode n = this.head;
        int cnt = 0;
        while (n != null){
            if (n.show.showID.equals(id)) return n;
            n = n.next;
            cnt ++;
        }
        return null;
    }

    /**
     * cChecks whether list contains a TV show with the given ID
     * @param id Id that needs to be searched
     * @return true if Show is found else false
     */
    boolean contains(String id){
        ShowNode n = this.head;
        if (this.head != null){
            while (n != null){
                if (n.show.showID.equals(id)) return true;
                n = n.next;
            }
        }
        return false;

    }

    /**
     * Checks whether all TV show nodes in a list are similar to the ones in the passed list
     * @param list Linked list with TV show nodes as object
     * @return true if both lists are similar else false
     */
    boolean equals(ShowList list){
        ShowNode n = this.head;
        ShowNode m = list.head;
        if (this.size != list.size) return false;
        while (n != null){
            if (!n.show.equals(m.show)) return false;
            n = n.next;
            m = m.next;
        }
        return true;
    }

    /**
     * Displays all the TV show nodes in a list
     */
    void printList(){
        ShowNode n = this.head;
        while (n != null){
            System.out.println(n.show.toString());
            n = n.next;
        }
    }

    /**
     * Inner class with TV show nodes as its object
     */
    static class ShowNode{
        TVShow show;
        ShowNode next;

        ShowNode(){
            this.show = null;
            this.next = null;
        }

        ShowNode(TVShow tvShow, ShowNode node){
            this.show =  tvShow;
            this.next = node;
        }

        ShowNode(ShowNode node){
            this.show = node.show;
            this.next = node.next;
        }

        public Object clone(){
            return new ShowNode(this);
        }

        public TVShow getShow() {
            return show;
        }

        public void setShow(TVShow show) {
            this.show = show;
        }

        public ShowNode getNext() {
            return next;
        }

        public void setNext(ShowNode next) {
            this.next = next;
        }
    }
}
