/**
 * BSTDictionary code.
 * @Momin Mushtaha
 * Sysc 2100 Assignment5
 * @101114546
 */
public class BSTDictionary<E,K extends Sortable> implements Dictionary<E,K>
{   //BST Dictionary root
    BSTNode<E,K> dictionaryRoot; 
    
    public BSTDictionary(BSTNode<E,K> dictionaryRoot)
    {  //Constructor for binary search tree
        
       this.dictionaryRoot = dictionaryRoot;
       
    }
    public BSTDictionary()
    {   //Constructor for binary search tree
        
        this(null);
        
    }
    //1st method
    public E search(K key) 
    {   // Calls insert method mentioned in Dictionary.java
        // Search for a node in tree using a key
        BSTNode<E, K> nodeDetected;
        
        nodeDetected = nodeSearching(key);
        
        if(nodeDetected != null) 
        {
            
            return nodeSearching(key).getElement(); 
            
        }
        else
        {
        
            return null; 
            
        }
    }
     public BSTNode<E, K> nodeSearching(K key) 
    {
        //Related to search method
        if(dictionaryRoot == null) 
        {
            
            return null; 
            
        }
        if(key == null) 
        {
            
            return null; 
            
        }
        if(key.compareTo(dictionaryRoot.getKey()) == 0) 
        {
            
            return dictionaryRoot;
            
        }
        
        else if(key.compareTo(dictionaryRoot.getKey()) < 0) 
        {
            
            return searchBelowIt(dictionaryRoot.getLeft(), key); 
            
        }
        
        else if(key.compareTo(dictionaryRoot.getKey()) > 0) 
        {
            
            return searchBelowIt(dictionaryRoot.getRight(), key); 
            
        }
        else 
        {
            
            System.out.println("this is an Error");
            
            return null;
            
        }
    }
    public BSTNode<E,K> searchBelowIt(BSTNode<E, K> node, K key) 
    {
        if(node == null) 
        {
            
            return null; 
            
        }
        if(key.compareTo(node.getKey()) == 0) 
        {
            
            return node;
            
        }
        else if (key.compareTo(node.getKey()) < 0) 
        {
            
            return searchBelowIt(node.getLeft(), key);
            
        }
        else if(key.compareTo(node.getKey()) > 0) 
        {
            
            return searchBelowIt(node.getRight(), key);
            
        }
        else
        {
            
            return null;
            
        }
    }
    //2nd method
    public void insert(K key, E element) 
    {   //Calls insert method mentioned in Dictionary.java   
        //Inserts a key into the tree
        if(dictionaryRoot == null) 
        {
            
            dictionaryRoot = new BSTNode<E, K>(key, element, null, null);
            
        }
        else 
        {
            
            insertNodeBelow(dictionaryRoot, key, element);
            
        }
    }
    public void insertNodeBelow(BSTNode<E, K> node, K key, E element) 
    {   //Inserts a node below a specific node 
        //Belongs to insert
        if(key.compareTo(node.getKey()) == 0) 
        {    
        } 
        else if(key.compareTo(node.getKey()) < 0) 
        {
            if(node.getLeft() == null) 
            {
                
                node.setLeft(new BSTNode<E, K>(key, element, null, null));
                
            }
            else
            {
                
                insertNodeBelow(node.getLeft(), key, element);
                
            }
        }
        else if(key.compareTo(node.getKey()) > 0) 
        {
            if(node.getRight() == null) 
            {
                
                node.setRight(new BSTNode<E, K>(key, element, null, null));
                
            }
            else
            {
                
                insertNodeBelow(node.getRight(), key, element);
                
            }
        }
        else
        {
            
            System.out.println("this is an Error");
            
        }
    }
    //3rd method
    public void delete(K key) 
    {   //Calls delete method mentioned in Dictionary.java
        //Deletes a node using a key
        this.dictionaryRoot = nodeDeletionAlgorithm(dictionaryRoot, key);
        
    }
    public BSTNode<E, K> doubleNodeDeletion(BSTNode<E, K> node) 
    {  //This code deletes a double node if existed(determines if it is the case in NodeDeletionAlgorithm
       if(node.getLeft() == null)
       {
           
          return node.getRight();
          
       }
       
       else
       {
           
          node.setLeft(doubleNodeDeletion(node.getLeft()));
          
       }
       
       return node;
       
    }
    public BSTNode<E, K> nodeDeletionAlgorithm(BSTNode<E,K> node, K key)
    {      //Compares and determines the right case to work with
           if(key.compareTo(node.getKey()) == 0)
           {
               if((node.getRight() == null) && (node.getLeft() == null)) 
               {//both doesnt exist
               
                return null; 
                
                }
                else if((node.getRight() == null) && (node.getLeft() != null))
                {//right doesnt exist
               
                return node.getLeft();
                
                }
           
                else if((node.getRight() != null) && (node.getLeft() == null)) 
                {//left doesnt exist 
               
                return node.getRight();
                
                }
           
                else if((node.getRight() != null) && (node.getLeft() != null))
                {//both exist 
               
                 BSTNode<E, K> theReplacement = minimumDetection(node.getRight()); 
               
                 BSTNode<E, K> backupLeft = node.getLeft();
               
                 theReplacement.setRight(doubleNodeDeletion(node.getRight())); 
               
                 theReplacement.setLeft(backupLeft);
               
                 return theReplacement;
               
                }
                else
                {
               
                 return node;
               
                }
           }     
           else if(key.compareTo(node.getKey()) < 0)
           {
               if(node.getLeft() != null)
               {
               
                node.left = nodeDeletionAlgorithm(node.getLeft(), key);
              
                return node; 
              
               }
           }  
           else
           {
               if(node.getRight() != null)
               {
               
                node.right = nodeDeletionAlgorithm(node.getRight(), key);
              
                return node;
                
               }
           }
           
           return node;
           
    }
    public BSTNode<E, K> minimumDetection(BSTNode<E, K> node) 
    {   //Finds the minimum value of a certain node
        while(node.getLeft() != null) 
        {
            
            node = node.getLeft();
            
        }
        
        return node;
        
    }
    //4th method
    public void printTree() 
    {   //prints the given tree
        //Calls printTree method mentioned in Dictionary.java
        sortingInorder(dictionaryRoot);
        
    }
    public void sortingInorder(BSTNode<E,K> node) 
    {   //Sorting in order
        if(node != null) 
        {
            
            sortingInorder(node.getLeft()); 
            
            System.out.println("The key is" + node.getKey().toString() + "The element is" + node.getElement().toString());
            
            sortingInorder(node.getRight()); 
            
        }
    }
    //5th method 
    public int depth()
    {   //Calls depth method mentioned in Dictionary.java
        
        return findingTheDepth(dictionaryRoot, 0);
        
    } 
    int findingTheDepth(BSTNode<E,K> node, int ourDepth) 
    {   //Depth of sorted tree
        if(node != null) 
        {
            
            return Math.max(findingTheDepth(node.getLeft(), ourDepth+1), findingTheDepth(node.getRight(), ourDepth+1));
        
        }
        else
        {
            
            return ourDepth;
            
        }    
    }
}
