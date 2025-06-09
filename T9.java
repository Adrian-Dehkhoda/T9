import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class T9
{
    class Node 
    {
        public Node[] next;
        public boolean valid;

        public Node() 
        {
            next = new Node[27];
            valid = false;
        }
    }

    public void loadFile(String file) throws IOException
    {
        try(BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String word;
            while((word = br.readLine()) != null)
            {
                add(word.trim());
            }
        }
    }

    private Node root = new Node();

    private T9()
    {
        root = new Node();
    }

    private static int code(char character) 
    {
        switch (character) 
        {
            case 'a' :
                return 0;
            case 'b' :
                return 1;
            case 'c' :
                return 2;
            case 'd' :
                return 3;
            case 'e' :
                return 4;
            case 'f' :
                return 5;
            case 'g' :
                return 6;
            case 'h' :
                return 7;
            case 'i' :
                return 8;
            case 'j' :
                return 9;
            case 'k' :
                return 10;
            case 'l' :
                return 11;
            case 'm' :
                return 12;
            case 'n' :
                return 13;
            case 'o' :
                return 14;
            case 'p' :
                return 15;
            case 'r' :
                return 16;
            case 's' :
                return 17;
            case 't' :
                return 18;
            case 'u' :
                return 19;
            case 'v' :
                return 20;
            case 'x' :
                return 21;
            case 'y' :
                return 22;
            case 'z' :
                return 23;
            case 'å' :
                return 24;
            case 'ä' :
                return 25;
            case 'ö' :
                return 26;
        }
        return -1;
    }
    
    private static char character(Integer code) 
    {
        switch (code) 
        {
            case 0 :
                return 'a';
            case 1 :
                return 'b';
            case 2 :
                return 'c';
            case 3 :
                return 'd';
            case 4 :
                return 'e';
            case 5 :
                return 'f';
            case 6 :
                return 'g';
            case 7 :
                return 'h';
            case 8 :
                return 'i';
            case 9 :
                return 'j';
            case 10 :
                return 'k';
            case 11 :
                return 'l';
            case 12 :
                return 'm';
            case 13 :
                return 'n';
            case 14 :
                return 'o';
            case 15 :
                return 'p';
            case 16 :
                return 'r';
            case 17 :
                return 's';
            case 18 :
                return 't';
            case 19 :
                return 'u';
            case 20 :
                return 'v';
            case 21 :
                return 'x';
            case 22 :
                return 'y';
            case 23 :
                return 'z';
            case 24 :
                return 'å';
            case 25 :
                return 'ä';
            case 26 :
                return 'ö';
        }
        return ' ';
    }

    private static Integer index(char key)       
    {
        switch(key)
        {
            case '1':
                return 0;
            case '2':
                return 1;
            case '3':
                return 2;
            case '4':
                return 3;
            case '5':
                return 4;
            case '6':
                return 5;
            case '7':
                return 6;
            case '8':
                return 7;
            case '9':
                return 8;
            
        }
        return -1;
    }

    private void add(String word)
    {
        Node current = root;

        for(int x = 0; x < word.length(); x++)
        {
            int charCode = code(word.charAt(x));

            if(current.next[charCode] == null)
            {
                current.next[charCode] = new Node();
            }
                
            current = current.next[charCode];
        }
        current.valid = true;
    }

    private boolean search(String word)
    {
        Node current = root;

        for(int x = 0; x < word.length(); x++)
        {
            int charCode = code(word.charAt(x));

            if(charCode == -1 || current.next[charCode] == null)
            {
                return false;
            }

            current = current.next[charCode];
        }

        return current.valid;
    }

    private LinkedList<String> decode(String keySequence)
    {
        LinkedList<String> possibleWords = new LinkedList<>();
        collect(root, 0, keySequence, "", possibleWords);
        return possibleWords;
    }

    private void collect(Node node, int treeDepth, String keySequence, String currentWord, LinkedList<String> result)
    {
        //System.out.println("Collect called: Depth = " + treeDepth + ", CurrentWord = " + currentWord);
        
        if(treeDepth == keySequence.length())
        {
            if(node.valid)
            {
                //System.out.println("Valid word found: " + currentWord);
                result.add(currentWord);
            }
            return;
        }
        
        char key = keySequence.charAt(treeDepth);
        int[] charCodes = getT9Chars(key);

        for(int charCode: charCodes)
        {
            if(node.next[charCode] != null)
            {
                //System.out.println("Traversing character: " + character(charCode) + " for key " + key);
                collect(node.next[charCode], treeDepth + 1, keySequence, currentWord + character(charCode), result);
            }
        }
    }

    private static int[] getT9Chars(char key)
    {
        switch (key) 
        {
            case '1' :
                return new int[]{0, 1, 2};
            case '2' :
                return new int[]{3, 4, 5};
            case '3' :
                return new int[]{6, 7, 8};
            case '4' :
                return new int[]{9, 10, 11};
            case '5' :
                return new int[]{12, 13, 14};
            case '6' :
                return new int[]{15, 16, 17};
            case '7' :
                return new int[]{18, 19, 20};
            case '8' :
                return new int[]{21, 22, 23};
            case '9' :
                return new int[]{24, 25, 26};
        }
        return new int[]{};
    }

    /*private static String encode(String word)
    {
        StringBuilder encodedSequence = new StringBuilder();
        
        for(char character: word.toCharArray())
        {
            int[] keys = getT9Chars(character);

            if(keys.length > 0)
                encodedSequence.append(keys[0]);
        }
        return encodedSequence.toString();
    }*/

    public static void main(String[] args) 
    {
        T9 t9 = new T9();

        //t9.add("abc");
        //t9.add("def");
        
        try 
        {
            t9.loadFile("kelly.txt");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        String keySequence = "96";
        List<String> words = t9.decode(keySequence);
        
        for (String word : words) 
        {
            System.out.println("Decoded word: " + word);
        }

        
    }
}


