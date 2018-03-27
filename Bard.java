/*Nevan Samadhana:1539153
This program takes in desired length and rank of a word and outputs the word
*/
import java.io.*;
import java.util.*;
import java.lang.*;
class Bard{
  public static void main(String [] args)throws IOException{
    if(args.length < 2){
     System.out.println("Usage: java –jar Bard.jar <input file> <output file>");
     System.exit(1);
    }

    Scanner file = new Scanner(new File("shakespeare.txt"));
    Hashtable<String,Integer> hashtable = new Hashtable<String,Integer>(); //Creates hashtable
    Scanner in = new Scanner(new File(args[0]));
    PrintWriter out = new PrintWriter(new FileWriter(args[1]));

    while(file.hasNextLine()){
      String line = file.nextLine(); //Goes line by line
      line = line.replace("?"," "); //Each replace subs the unwanted punctuation with whitespace so it can be split later
      line = line.replace(',',' ');
      line = line.replace('.',' ');
      line = line.replace('!',' ');
      line = line.replace(':',' ');
      line = line.replace(';',' ');
      line = line.replace('[',' ');
      line = line.replace(']',' ');
      line = line.toLowerCase(); //Makes everything lowercase
      String [] tokens = line.split("\\s+"); //Array tokens of all words from the lines

      for(int i = 0; i < tokens.length; i++){
        if(hashtable.containsKey(tokens[i])){ //If the key has been seen before, increment the value by one
          hashtable.put(tokens[i],hashtable.get(tokens[i])+1);
        }else{ //If the word has not been seen before, add another key value pair with the frequency set to one
          hashtable.put(tokens[i],1);
        }
      }
    }


    Set<String> keyset = hashtable.keySet(); //https://beginnersbook.com/2014/07/hashtable-iterator-example-java/
    Iterator<String> itr = keyset.iterator();
    int max = 1;
    String str;
    while (itr.hasNext()){
      str = itr.next();
      if(str.length()>max){
        max = str.length(); //Gets us the length of the longest word so we can create the array of arraylists with correct length
      }
    }

    ArrayList<Word>[] list = new ArrayList[max+1]; //Array of arraylists
    for(int i = 1; i <= max; i ++){
      list[i]=new ArrayList<Word>();
      itr = keyset.iterator();
      while (itr.hasNext()){
        str = itr.next();
        if(str.length()==i){ //If the length of the word is equal to the index, create an instance of the word and insert into the
          list[i].add(new Word(str,hashtable.get(str)));
        }
      }
    }

    for(int i = 1; i<=max; i++){ //Sorts the arraylists by frequency by calling on CompareTo()
      Collections.sort(list[i]);
    }

    while(in.hasNextLine()){
      String line = in.nextLine();
      String [] tokens = line.trim().split("\\s+");
      int length = Integer.parseInt(tokens[0]); //Obtains query length
      int rank = Integer.parseInt(tokens[1]); //Obtains query rank

      if(length > list.length-1 || list[length].size() <= rank){
        out.println("-");
      }else{
        out.println(list[length].get(rank).getKey());
      }
    }

    in.close();
    out.close();
    file.close();
  }
}
