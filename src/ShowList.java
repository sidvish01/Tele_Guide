import java.util.NoSuchElementException;

public class ShowList {

    private ShowNode head;
    private int size;

    ShowList(){
        this.head = null;
        size = 0;
    }

    ShowList(ShowList list){
      this.head = list.head;
      this.size = list.size;
    }

    void addToStart(TVShow show){
        head = new ShowNode(show, head);
        this.size += 1;
    }

    void insertAtIndex(TVShow show, int index){
        if (!(index < this.size && index >= 0)) throw new NoSuchElementException();
        else{
            int count = 0;
            ShowNode n = this.head;
            for (int i = 0 ; i < index; i++){
                n = n.next;
            }
            n.next = new ShowNode(show, n.next);
            this.size += 1;
        }
    }
    void deleteFromIndex(int index){
        if (!(index < this.size && index >= 0)) throw new NoSuchElementException();
        else{
            int count = 0;
            ShowNode n = this.head;
            for (int i = 0 ; i < index; i++){
                n = n.next;
            }
            n.next = n.next.getNext();
            this.size -= 1;
        }
    }

    boolean deleteFromStart(){
        if (this.head != null){
            this.head = head.next;
            this.size -= 1;
            return true;
        }
        else return false;
    }

    void replaceAtIndex(TVShow show, int index){
        if (!(index < this.size && index >= 0)) return;
        else{
            int count = 0;
            ShowNode n = this.head;
            for (int i = 0 ; i < index; i++){
                n = n.next;
            }
            n.next = new ShowNode(show, n.next.getNext());
        }
    }

    ShowNode find(String id){
        ShowNode n = this.head;
        int cnt = 0;
        while (n.next != null){
            if (n.show.showID.equals(id)) return n;
            n = n.next;
            cnt ++;
        }
        return null;
    }

    boolean contains(String id){
        ShowNode n = this.head;
        while (n.next != null){
            if (n.show.showID.equals(id)) return true;
            n = n.next;
        }
        return false;
    }

    boolean equals(TVShow show){
        ShowNode n = this.head;
        while (n.next != null){
            if (n.show.equals(show)) return true;
            n = n.next;
        }
        return false;
    }

    static class ShowNode{
        TVShow show;
        ShowNode next;

        ShowNode(){
            this.show = null;
            this.next = null;
        }

        ShowNode(TVShow tvShow, ShowNode node){
            this.show = (TVShow) tvShow.clone();
            this.next = node;
        }

        ShowNode(ShowNode node){
            this.show = (TVShow) node.show.clone();
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
