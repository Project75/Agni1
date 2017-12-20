package com.nttdata.agni.resources.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.SortedSetMultimap;
import com.google.common.collect.TreeMultimap;

public class MutliMapTest {
	public static void main2(String[] args) {
	   
	     TreeMultimap<String,String> myTreeMultimap = TreeMultimap.create();
	    myTreeMultimap.put("patient.name.firstname", "Amaury ");
	    myTreeMultimap.put("patient.id", "111");
	    myTreeMultimap.put("patient.name.a", "firtval ");
	    myTreeMultimap.put("patient.name.firstname", "Eric ");
	    myTreeMultimap.put("patient.name.lastname", " Smith");
	    myTreeMultimap.put("patient.name.z", " lastval");
	    myTreeMultimap.put("patient.gender", "M");
	    
	    Map<String,Collection<String>> map = myTreeMultimap.asMap().subMap("patient.name.a", "patient.name.z");
	    for (Map.Entry<String, Collection<String>> entry : map.entrySet())
	    {
	        System.out.println(entry.getKey() + "/" + entry.getValue());
	    }
	    System.out.println("myTreeMultimap: " + myTreeMultimap);
	  }

	
    @SuppressWarnings("unused")
	public static void main(String... args) {
  Multimap<String, String> myMultimap = ArrayListMultimap.create();
   
  // Adding some key/value
  myMultimap.put("patient.name.firstname", "Amaury ");
  myMultimap.put("patient.id", "111");
  myMultimap.put("patient.name.firstname", "Eric ");
  myMultimap.put("patient.name.lastname", " Smith");
  myMultimap.put("patient.gender", "M");
   
  // Getting the size
  int size = myMultimap.size();
  System.out.println(size);  // 4
  List<NameType> NameTypeList = new ArrayList<NameType>();
  NameType nt =new NameType();
  Set<String> NameKeySet = myMultimap.keySet()
          .stream()
          .filter(s -> s.startsWith(nt.getLookup()))
          .collect(Collectors.toSet());
  
  for (String key: NameKeySet){
	  Collection<String> names = myMultimap.get(key);
	  Object[] a = null;
	if (key == (nt.getLookup()+".firstname"))
		for (String name: names){
			nt.addFamily( name);
		}
	  System.out.println(key+"==key,val=="+names);
	  System.out.println("nt.family"+nt.getFamily());
  }
  
  // Getting values
  Collection<String> name = myMultimap.get("patient.name");
  System.out.println("name:"+name); 
   
  Collection<String> identifier = myMultimap.get("id");
  System.out.println("identifier:"+identifier); // [Carrot]
   
  // Iterating over entire Mutlimap
  for(String value : myMultimap.values()) {
   System.out.println("all:"+value);
  }
  /* 
  // Removing a single value
  myMultimap.remove("Fruits","Pear");
  System.out.println(myMultimap.get("Fruits")); // [Bannana, Pear]
   
  // Remove all values for a key
  myMultimap.removeAll("Fruits");
  System.out.println(myMultimap.get("Fruits")); // [] (Empty Collection!)
  */
 }
}