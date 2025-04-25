

class CDLLNode {
    int key,val;
    CDLLNode prev,next;
    public CDLLNode(int k , int v){
        this.key=k;
        this.val=v;
    }}
class CDLL{
    CDLLNode head;
    public CDLL(){
        head=null;
    }
    void insAtBegin(int k,int v){
        CDLLNode nn = new CDLLNode(k,v);
        nn.next=nn;nn.prev=nn;
        if(head==null){
            head= nn;
            return;
        }
        CDLLNode last = head.prev;
        nn.next = head; head.prev=nn;
        last.next=nn; nn.prev=last;
        head= nn;
    }
    void printLL(){
       if(head== null) return;
       System.out.print(head.key+" ");
       CDLLNode temp= head.next;
        while(temp!=head){
            System.out.print(temp.key+" ");
            temp=temp.next;
        }
        System.out.println();
    }
    void delNode(){
        if(head==null) return;
        CDLLNode last = head.prev;
        if(last==head){
            head=null;
            return;
        }
        last.prev.next= head;
        head.prev = last.prev;

    }
    void moveATFront(CDLLNode nodetoMove){
        if (nodetoMove==head) return;
        
        nodetoMove.prev.next=nodetoMove.next;
        nodetoMove.next.prev= nodetoMove.prev;
        CDLLNode last = head.prev;
        nodetoMove.next=head;
        head.prev= nodetoMove;
        last.next=nodetoMove;
        nodetoMove.prev=last;
        head= nodetoMove;
    }
}


    class LRUCache{
    public static void main(String[] args) {
        CDLL list1= new CDLL();
        list1.insAtBegin(0,0); list1.insAtBegin(1,111); list1.insAtBegin(3,303);
        list1.printLL();
        list1.delNode();
        list1.printLL();
        list1.moveATFront(list1.head.next);
        list1.printLL();
        list1.moveATFront(list1.head.next);
        
    }
}
