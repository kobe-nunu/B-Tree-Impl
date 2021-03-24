
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 

/**
 *
 * @author Ron Rachev
 */
public class TestBTree {
    private int           order; 
    private static Random rand;    /* Randomizer for the nodes & minimal degree */
    
    private BTree  generatedTree;  /* Pointer to the generated tree & functions */ 
    private String treeAscii; 
    
    private ArrayList<String > actionLog; /* Stores all the actions that are done on the tree */
    private ArrayList<Integer> nodeVals ;
    private int testNumber;
    
    private String  testStr;
    private boolean testPassed;
    
    public TestBTree(int testNumber)
    {  
        this.rand          = new Random(); 
        this.order         = randInt(2, 2); 
        this.generatedTree = new BTree(this.order);
        
        this.actionLog     = new ArrayList<>();
        this.nodeVals      = new ArrayList<>();
        this.testNumber    = testNumber;
        
        actionLog.add("\r\n");
        actionLog.add("START - TEST " + testNumber+"\r\n------------------------------");
        actionLog.add("BTree<Integer> generatedTree = new BTree<Integer>("+this.order+");"); 
    } 
    
    public TestBTree(String testCase)
    {
        this.testStr = testCase;
    }
    public static ArrayList<String> getAllMatches(String text, String regex) {
        ArrayList<String> matches = new ArrayList<String>();
        Matcher m = Pattern.compile("(?=(" + regex + "))").matcher(text);
        while(m.find()) {
            matches.add(m.group(1));
        }
        return matches;
    }

    public boolean parseAndExecuteTest(){
        String treeAfterInsert ="";
        String treeAfterDelete ="";
        boolean passedTest;
        
        int minimumDegreeStart = testStr.indexOf("Tree<Integer>(")+14;
        int minimumDegreeEnd   = minimumDegreeStart+1;
        
        this.order = Integer.parseInt(testStr.substring(minimumDegreeStart,minimumDegreeEnd)); 
        System.out.println("Minimum Degree: " + this.order);
        this.generatedTree = new BTree(this.order);
        
        /*
        parseAll insertion in this specific case
        */
        ArrayList<String> normalInsertion = getAllMatches(this.testStr,"generatedTree.insert2pass.*");
        
        for(int i = 0 ; i < normalInsertion.size(); i++)
        {
            String toInsert = normalInsertion.get(i).substring(26,28); 
            this.generatedTree.insert2pass(toInsert); 
        }
        treeAfterInsert = this.generatedTree.toString().replaceAll("\\D+","");
        /*
        parseAll normal delete in this specific case
        */
      
        ArrayList<String> normalDelete = getAllMatches(this.testStr,"generatedTree.delete.*");
        
        for(int i = 0 ; i < normalDelete.size(); i++)
        {
            String toDelete = normalDelete.get(i).substring(21,23); 
            this.generatedTree.delete(toDelete);  
        }
        treeAfterDelete = this.generatedTree.toString().replaceAll("\\D+","");
        String logTreeAfterInsert = testStr.split("------------------------------")[2].replaceAll("\\D+","");
        String logTreeAfterDelete = testStr.split("------------------------------")[4].replaceAll("\\D+",""); 
         
        //System.out.println(logTreeAfterInsert + " <> " + treeAfterInsert);
        //System.out.println(logTreeAfterDelete + " <> "  +treeAfterDelete);
        passedTest = treeAfterInsert.equals(logTreeAfterInsert) && treeAfterDelete.equals(logTreeAfterDelete);
        
        return passedTest;
    }
    
    public BTree getGeneratedTreeInstance(){ /* Returns the instance of the randomized tree */
        return this.generatedTree;
    }
    
    public int getTestNum(){ /* Returns our current test num*/
        return this.testNumber;
    }
    
    public String getLogAsString() /* Converts the log array list to a string*/
    {
        String log = "";
        for(int i = 0 ; i < actionLog.size(); i++)  log+= actionLog.get(i)+"\r\n"; 
        return log;
    } 
    
    public ArrayList<String> getLog()
    {
        return actionLog;
    }
    
    /* Returns a random integer within a given range */ 
    
    public static int randInt(int min, int max) {
    int randomNum = rand.nextInt((max - min) + 1) + min;
    return randomNum;
    }
  
    public void buildTree()
    {
        int numNodes       =  randInt(5,22); //holder for num of nodes
        int currentNodeVal = -1;
        
        for(int i = 0 ; i < numNodes ; i ++){
           currentNodeVal = randInt(11,99);  
           if(!nodeVals.contains(currentNodeVal)){
          
           generatedTree.insert2pass(currentNodeVal); 
           actionLog.add("generatedTree.insert2pass("+currentNodeVal+");");
           nodeVals.add(currentNodeVal);
           }
        }  
        actionLog.add("------------------------------"); 
        actionLog.add("\r\n"+getTreeAsciiState());
        actionLog.add("------------------------------\r\n");
    }
    
    public void removeRandomNodes()
    {
        int treeSize         = generatedTree.size();
        int numNodesToDelete = randInt(1,3);//treeSize-1
        
        Integer tempVal = -1;
         
        Collections.shuffle(nodeVals); //For randomization
          
        for(int i = 0 ; i < numNodesToDelete && i < nodeVals.size() ; i++)
        {
            tempVal = nodeVals.get(i); 
            nodeVals.remove(tempVal);
            System.out.println("Deleting --> " + tempVal);   
            System.out.println(generatedTree.toString());
            
            generatedTree.delete(tempVal); 
            actionLog.add("generatedTree.delete("+tempVal+")"); 
        }
        actionLog.add("\n" +"------------------------------");
        actionLog.add("\n"+generatedTree.toString()); 
        actionLog.add("\n" +"------------------------------");
        actionLog.add("\n"+"END - TEST " +testNumber); 
    }
    
    public String getTreeAsciiState()
    {
        return generatedTree.toString();
    } 
}
