import java.io.*;
import java.util.*;
import java.lang.*;

  class Word implements Comparable<Word>{
    private String key;
    private int value;

    public Word(String word, int frequency){
      key = word;
      value = frequency;
    }

    public String getKey(){
      return key;
    }

    public int getValue(){
      return value;
    }

    public int compareTo(Word word){
      if(this.value == word.value){ //If frequency of the words are equal,sort in lexicographic order
        return key.compareTo(word.key);
      }else if(this.value > word.value){ //If frequency is greater, give it greater precedence
        return -1;
      }else{ //If frequency is less, give it less precedence
        return 1;
      }
    }

}
