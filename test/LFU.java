package test;


import java.util.HashMap;
import java.util.LinkedHashMap;



public class LFU implements CacheReplacementPolicy {
    
    LinkedHashMap<Integer,DoublyLinkedList>LFU_Cache; // A cache that will work by Integer which is the Frequency(key) of the word reffered to a value which is a Doublylinkedlist that i made. which the first element in that doubly linked list is the one that needs to be pulled out.
    HashMap<String,Node> wordMap; // A map that will just tell me if the word is mapped already or not.
    int MAX_SIZE;
    int minFreq=0; // will tell me wheres the updated minvalue in order not to search all over the hashmap

    public LFU()
    {
        this.LFU_Cache = new LinkedHashMap<Integer,DoublyLinkedList>();
        this.wordMap = new HashMap<String,Node>();

    }
      public void add(String word)
    {
        if(this.wordMap.containsKey(word))
            {
                Node node = wordMap.get(word);
                updateFreqUP(node);

            }
        else
        {
            // CACHE IS FULL / THE WORD IS LITERALLY NOT IN THE MAP
            if(wordMap.size()== MAX_SIZE)
            {
                remove();
            }
            Node newNode = new Node(word);
            addToFreqList(newNode, 1);
            minFreq=1;
        }
      
        
      

    }
	public String remove()
    {
        if(this.wordMap.size()==0)
        {
            return null;

        }
        else
        {
            
            DoublyLinkedList list = this.LFU_Cache.get(this.minFreq);
            Node n = list.head.next;
            list.removeNode(n);
            this.wordMap.remove(n.data);
            if(list.size==0)
                {
                
                    this.LFU_Cache.remove(minFreq);
                    if(this.LFU_Cache.isEmpty())
                    {
                        minFreq = 0;

                    }
                    else
                    {
                        this.minFreq = this.LFU_Cache.keySet().iterator().next();
                    }

                  
                }
           
               
                return n.data;
        
           
        }

       
    }
    public void updateFreqUP(Node randomNode)
    {
        

        for( int freq : this.LFU_Cache.keySet()) // from all over the set in lfucache
        {
            DoublyLinkedList list = this.LFU_Cache.get(freq); // get the list 
            if(list.contains(randomNode.data)) // if the list contains the requested node->word
            {
                list.removeNode(randomNode); // so delete that node from that list

            }
            if(freq == minFreq && list.size == 0) // if this is the minimum freq and the foarmer list size is now empty==0
            {
                minFreq++; // update the min freq
            }
            addToFreqList(randomNode,freq+1); // add to the freq++ list that node
            break;
        }

    }

    public void addToFreqList(Node node,int frq)
    {
        LFU_Cache.computeIfAbsent(frq, k->new DoublyLinkedList()).insertLast(node); // a method that if k is null it wil make a new list, if not, just insert
        if(!wordMap.containsKey(node.data)) // if our simple map doesnt contain that word pulled by a node
        {
            wordMap.put(node.data, node); // so add it
        }

    }

    
   


  /**
   * DoublyLinkedList
 
   */
  public class DoublyLinkedList { // each DLL will have a size of that list, a dummy head,tail which their data will be null!
    int size;
    Node head;
    Node tail;
    /**
     * InnerLFU
     */
                        public class DoublyLinkedListIterator { // making an inner class, iterator
                            Node current = head.next;
                        public boolean hasNext()
                            {
                                return current !=tail;
                            }
                        public Node next()
                            {
                                if(!hasNext())
                                {
                                    throw new java.util.NoSuchElementException();
                                }
                                Node node = current;
                                current = current.next;
                                return node;
                                

                            }
                        
                            
                        }

                        DoublyLinkedListIterator Iterator()
                        {
                            return new DoublyLinkedListIterator();
                        }
                        boolean contains(String word)
                        {
                            DoublyLinkedListIterator iterator = this.Iterator();
                            boolean FLAG = false;
                            while(iterator.hasNext())
                            {
                                if(iterator.current.data.equals(word))
                                {
                                    FLAG = true;
                                }
                                iterator.next();
                                

                            }
                            return FLAG;
                        }
                    
    DoublyLinkedList() // making the new connections.
    {
        this.size = 0;
        this.head = new Node();
        this.tail = new Node();
        this.head.next = tail;
        this.head.prev = tail;
        this.tail.prev = head;
        this.tail.next = head;
        this.head.data = null;
        this.tail.data=null;

    }
   
    void insertLast(Node node)
    {
        if(size==0)
        {
            node.next = tail;
            node.prev = head;
            head.next = node;
            tail.prev = node;
            
            size++;
        }
        else
        {
            Node lastNode = tail.prev;
            lastNode.next = node;
            node.prev= lastNode;
            node.next = tail;
            tail.prev = node;
            size++;
        }

    }

    public Node deleteFirst()
    {
        if(size==0)
        {
            System.out.println("SIZE IS 0 CANNOT DELETE!");
            return null;
        }
        if(size==1)
        {
           
            Node n = this.head.next;
           removeNode(n);
            return n;
        }
        Node firstNode = head.next;
       removeNode(firstNode);
        return firstNode;


    }
    public void removeNode(Node n )
    {
        n.prev.next = n.next;
        n.next.prev = n.prev;
        this.size--;
    }


  
    
  }
  /**
   * Node
   */
  public class Node {
    Node next;
    Node prev;
    String data;
    Node()
    {
        next=null;
        prev = null;
        this.data=null;
    }
    Node(String a )
    {
        this.data = a;
    }
    Node(Node n , Node b , String data1)
    {
        this.next = n;
        this.prev = b;
        this.data = data1;
    }

  
    
  }
    

}
