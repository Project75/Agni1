package com.nttdata.agni.resources.tobedeleted;

import java.util.NoSuchElementException; 

public class StringDelimiter 
{ 
 private String string; 
   private String delimiter; 
 private int cursor, delimiterLen; 
 
 public StringDelimiter(String string, String delimiter) 
 { 
  this.string = new String(string); 
  this.delimiter = new String(delimiter); 
  delimiterLen = delimiter.length(); 
  initialize(); 
   } 
 
 public StringDelimiter(String string, char delimiter) 
 { 
  //System.out.println("\nDelim=(" + String.valueOf(delimiter) + ")"); 
  this.string = new String(string); 
  this.delimiter = new String(String.valueOf(delimiter)); 
  delimiterLen = 1; 
  initialize(); 
  //System.out.println("\nDelim=(" + this.delimiter + "), len=" + delimiterLen); 
   } 
 
 public int totalTokens() 
 { 
      int oldCursor = cursor; 
      cursor = 0; 
      int total = countTokens(); 
      cursor = oldCursor; 
      return total; 
 } 
 
   public int countTokens() 
   { 
     if (!stateOk()) 
      return 0; 
     int count = 0, i = string.indexOf(delimiter, cursor), j = 0; 
     while (i != -1) 
     { 
        j = i; 
        i = string.indexOf(delimiter, i + delimiterLen); 
        count++; 
     } 
     if (j < string.length() && !string.substring(j).equals(delimiter)) 
       count++; 
     return count; 
   } 
 
 
 public boolean hasMoreTokens() 
 { 
  if (!stateOk()) 
   return false; 
  return string.indexOf(delimiter, cursor) != -1 || 
  cursor < string.length(); 
 } 
 
 
 public boolean hasMoreElements() 
 { 
  return hasMoreTokens(); 
 } 
 
 public String nextToken() 
 { 
  if (!stateOk()) 
    throw new NoSuchElementException( 
   "StringDelimiter.nextToken(): null or empty string."); 
  int i = string.indexOf(delimiter, cursor); 
  if (i == -1) 
  { 
     if (cursor < string.length()) 
     { 
    String token = string.substring(cursor); 
    cursor = string.length(); 
    return token; 
     } 
     else 
    throw new NoSuchElementException( 
     "StringDelimiter.nextToken(): no more tokens."); 
  } 
  else 
  { 
     String token = string.substring(cursor, i); 
     cursor = i + delimiterLen; 
     return token; 
  } 
 } 
 
 
 public String nextToken(String delimiter) 
 { 
  if (!stateOk()) 
    throw new NoSuchElementException( 
    "StringDelimiter.nextToken(): null or empty string or delimiter."); 
  this.delimiter = delimiter; 
  delimiterLen = delimiter.length(); 
  return nextToken(); 
 } 
 
 
 public Object nextElement() 
 { 
  return (Object) nextToken(); 
 } 
 
 
 public void reset() 
 { 
  initialize(); 
 } 
 
   private boolean stateOk() 
   { 
    return !(string == null || delimiter == null || 
        string.length() == 0 || delimiter.length() == 0 || 
        string.equals(delimiter)); 
   } 
 
   private void initialize() 
   { 
    cursor = 0; 
   } 
 
}