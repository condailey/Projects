//Author: Connor Dailey
//Email: cddail25@g.holycross.edu
//Date: 11/5/2021
//Example: java Decompress


//Huffman set of functions
public class Huffman{
    //lookUpCode function
    public static char lookUpCode(String codeFileName, String target){
        In file = new In(codeFileName);
        //loop to find the corresponding number with a name.
        while(file.hasNextLine()){
            String name = file.readString();
            int number = file.readInt();
            file.readLine();
            //conditional for if the name equals desired string.
            if(name.equals(target)){
                char ch = (char)number;
                return ch;
            }
        }
        return (char)0; 
    }//End of lookUpCode

    //huffmanDecode function
    public static int[] huffmanDecode(String input, String output){
        //initilization of variables
        In inFile = new In(input);
        Out ouFile = new Out(output);
        int bits = 0;
        int chDec = 0;
        //empty target string
        String target = "";
        //loop to go through the input file
        while(inFile.hasNextLine()){  
            char ch = inFile.readChar();
            //ignores if the character is not a 1 nor a 0.
            if(ch != '0' && ch != '1'){
            //checks for matches    
            }else{
                target += ch;
                bits++;
                char check = lookUpCode("code-table.txt", target);
                if(check != (char)0){
                    ouFile.print(check);
                    target = "";
                    chDec++;
                }
            }
        }
        //establish the return array
        int[] array = {bits, chDec};
        return array;  
    }//end of huffmanDecode
}//end of Huffman