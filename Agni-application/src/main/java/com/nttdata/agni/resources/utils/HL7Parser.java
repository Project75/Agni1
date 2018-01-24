package com.nttdata.agni.resources.utils;


import com.nttdata.agni.resources.utils.TransformMap;
import com.nttdata.agni.transfomer.HL7Transformer;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Group; 
import ca.uhn.hl7v2.model.Message; 
import ca.uhn.hl7v2.model.Segment; 
import ca.uhn.hl7v2.model.Structure; 
import ca.uhn.hl7v2.model.Type; 
import ca.uhn.hl7v2.util.SegmentFinder; 
import ca.uhn.hl7v2.util.Terser; 

public class HL7Parser { 
	TransformMap hL7Map = new TransformMap();
	public static void main(String[] args){
		HL7Parser HL7Parser =new HL7Parser();
		HL7Transformer hl7Transformer = new HL7Transformer();
		String payload = "MSH|^~\\&|HIS|RIH|||199904140038||ADT^A01|||2.2\r"
                + "PID||100|^199&200&201||JOHN^DOE&MR~HAR^PAN&MR||M|\r";

		Message message=null;
		try {
			message = hl7Transformer.getHL7FromPayload(payload);
			//HL7Parser.extractValues(message);
			 Terser terser = new Terser(message);
	         

	        System.out.println("B-"+terser.get("MSH-9-1"));
	        
			TransformMap valueList = HL7Parser.getHL7Map(  message );
			System.out.println("=========");
			System.out.println(valueList.getMap().toString());
		} catch (HL7Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public TransformMap getHL7Map(Message message) throws Exception{
		extractValues(message);
		return this.hL7Map;
	}
	
  private  void extractValues( Message message ) throws Exception { 
	  
    Terser terser = new Terser( message ); 
    SegmentFinder finder = terser.getFinder(); 
 
    
    int childNr = 1; 
 
    while ( finder.hasNextChild() ) { 
 
      // next group in the message (MSH, PID, EVN and so on) 
      // 
      finder.nextChild(); 
      Structure[] structures = finder.getCurrentChildReps(); 
      for ( int i = 0; i < structures.length; i++ ) { 
        Structure structure = structures[i]; 
        parseStructure(  message, terser, structure, Integer.toString( childNr ) ); 
      } 
 
      childNr++; 
    } 
 
     
  } 
 
  private  void parseStructure(  Message message, Terser terser, Structure structure, 
      String structureNumber ) throws Exception { 
 
    //Map<String, List<String>> nameMap = NamesUtil.getInstance().getMap(); 
 
    if ( structure instanceof Segment ) { 
 
      Segment segment = (Segment) structure; 
      //String[] names = segment.getNames(); 
 
      for ( int fieldNum = 1; fieldNum <= segment.numFields(); fieldNum++ ) { 
        Type[] types = segment.getField( fieldNum ); int fieldRep=types.length;
        for ( int fieldNumCtr = 0; fieldNumCtr < types.length; fieldNumCtr++ ) { 
          int nrComponents = Terser.numComponents( types[fieldNumCtr] ); 
          for ( int compNum = 1; compNum <= nrComponents; compNum++ ) { 
            int nrSub = Terser.numSubComponents( types[fieldNumCtr], compNum ); 
            for ( int sc = 1; sc <= nrSub; sc++ ) { 
              String value = Terser.get( segment, fieldNum, fieldNumCtr, compNum, sc ); 
              // Primitive primitive = Terser.getPrimitive(types[t], c, sc); 
              if (value!=null){
            	  String key2 =null;
            	  String key = segment.getName()+"-"+fieldNum+"-"+compNum;
            	  //System.out.println("seg:"+segment.getName()+",fieldNum:"+fieldNum+",fieldCtr:"+fieldRep+"-nrComponents:"+nrComponents+"-nrSub:"+nrSub);
            	  if (fieldRep == 1 && nrSub==1){
            		  key2 = segment.getName()+"-"+fieldNum;
            		  hL7Map.put(key2,value);
            		  //System.out.println("yyy"+key2+"="+value);
            	  }
            	  if (sc>1){
            		  key = key+"-"+sc;
	              
            	  }
            	  //System.out.println("z-value="+value+","+segment.getName()+"-"+fieldNum+"-"+fieldNumCtr+"-"+compNum+"-"+sc);
	              hL7Map.put(key,value);
              }
              
              
            } 
          } 
        } 
      } 
 
    } else if ( structure instanceof Group ) { 
      Group group = (Group) structure; 
 
      String[] names = group.getNames(); 
 
      for ( int n = 1; n <= names.length; n++ ) { 
        String name = names[n - 1]; 
        Structure subStructure = group.get( name ); 
        parseStructure(  message, terser, subStructure, structureNumber + "." + n ); 
      } 
    } else { 
      throw new Exception( "oops, not handled yet!" ); 
    } 
 
  } 
 
}

