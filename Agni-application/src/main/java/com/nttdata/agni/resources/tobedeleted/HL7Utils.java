package com.nttdata.agni.resources.tobedeleted;

import java.util.HashMap;

public class HL7Utils 
{ 
		public static final char CARRIAGERETURN = (char)0xd; 
		public static final char LINEFEED = (char)0xa; 
		public static final char PIPE = '|'; 
		public static final char CARET = '^';  // component separator 
		public static final char TILDE = '~';  // repetition separator 
		public static final char AMP = '&';    // subcomponent separator 
        public static final String ENCODINGCHARACTERS = "^~\\&"; 
 
        private String segType; 
        private String level1FieldTag = ""; 
        private String level2FieldTag = ""; 
        private String level3FieldTag = ""; 
         
        private String ackCode; 
        private String msgControlId; 
        private String msgControlIdMsa; 
        private String messageType; 
        private String eventType; 
        HashMap<String,String> hl7Map = new HashMap<String,String>();
        private boolean ackReceived = false; 
         
        public HL7Utils() { } 
 
        public HashMap<String,String> GetHL7DataMap(String msg) 
        { 
        	
        	decomposeSegments(msg); 
        	return hl7Map;
        }
        
        public  HashMap<String,String> GetHL7FieldMapNonRepeating(String HL7fieldName, String fieldvalue) 
        {         	
        	hl7Map = new HashMap<String,String>();
        	decomposeSubfields(HL7fieldName,fieldvalue,0);
        	return hl7Map;
        } 
 
        public boolean isAck() 
        { 
            return ackReceived; 
        } 
         
        public  void decomposeSegments(String str) 
        { 
        	
            int i=0; 
            StringDelimiter sd; 
            sd = new StringDelimiter(str, HL7Utils.CARRIAGERETURN); 
            while (sd.hasMoreTokens()) 
            { 
                String nt = sd.nextToken(); 
                //System.out.println("Token :: " + nt); 
                if (nt.indexOf(HL7Utils.PIPE) >= 0) 
                    decomposeFields(nt); 
                i++; 
            } 
        } 
         
        public  void decomposeFields(String str) 
        { 
            int i=0; 

            StringDelimiter sd; 
            sd = new StringDelimiter(str, HL7Utils.PIPE); 
            while (sd.hasMoreTokens()) 
            { 
                String nt = sd.nextToken(); 
                if (i == 0) { 
                    segType = nt; 
                    // for segments other than MSH, 
                    // the second field actually begins at 1 
                    if (nt.equals("MSH")) i++; 
                } 
                level1FieldTag = segType + "." + i; 
                //System.out.println("level1FieldTag: "+level1FieldTag + "(" + nt.length() + ")" + "\t[" + nt + "]"); 
                //String ptr = level1FieldTag; 
                if (nt.length() > 0)
                	hl7Map.put(level1FieldTag, nt) ;
                // this applies to an MSA segment [[part of an MSH-MSA ack/nak]] 
                /*if (ptr.equalsIgnoreCase("MSA.1")) 
                { 
                    if (nt.equalsIgnoreCase("AA") || nt.equalsIgnoreCase("CA")) 
                        ackReceived = true; 
                    else 
                        ackReceived = false; 
                    ackCode = nt; 
                } 
                if (ptr.equalsIgnoreCase("MSA.2")) 
                { 
                    msgControlIdMsa = nt; 
                } 
                if (ptr.equalsIgnoreCase("MSH.10")) 
                { 
                    msgControlId = nt; 
                } */
 
                if (!isMshDelimiterField(segType, i)) 
                { 
                    if (nt.indexOf(HL7Utils.TILDE) >= 0) 
                        decomposeRepeats(nt); 
                    else if (nt.indexOf(HL7Utils.CARET) >= 0) 
                        decomposeSubfields(nt,0); 
                } 
                i++; 
            } 
        } 
         
        public  void decomposeSubfields(String str,int index) 
        { 
        	System.out.println("decompose subfields"); 
            int i=0;   
            StringDelimiter sd; 

            sd = new StringDelimiter(str, HL7Utils.CARET); 
            while (sd.hasMoreTokens()) 
            { 
                String nt = sd.nextToken();
                if (index > 0){
                	level2FieldTag = level1FieldTag + "." + (i+1)+"["+index+"]"; 
                }else
                	level2FieldTag = level1FieldTag + "." + (i+1);
                //System.out.println(level2FieldTag + "(" + nt.length() + ")" + "\t[" + nt + "]"); 
                //System.out.println("fieldtag="+level2FieldTag+" nt="+nt); 
                if (nt.length() > 0)
                	hl7Map.put(level2FieldTag, nt) ;
                if (nt.indexOf(HL7Utils.AMP) >= 0) 
                    decomposeRepeats(nt); 
                i++; 
            } 
 
        }
        public  void decomposeSubfields(String level1FieldTag, String str,int index) 
        { 
        	System.out.println("decompose subfields"); 
            int i=0;   
            StringDelimiter sd; 

            sd = new StringDelimiter(str, HL7Utils.CARET); 
            while (sd.hasMoreTokens()) 
            { 
                String nt = sd.nextToken();
                if (index > 0){
                	level2FieldTag = level1FieldTag + "." + (i+1)+"["+index+"]"; 
                }else
                	level2FieldTag = level1FieldTag + "." + (i+1);
                //System.out.println(level2FieldTag + "(" + nt.length() + ")" + "\t[" + nt + "]"); 
                System.out.println("fieldtag="+level2FieldTag+" nt="+nt); 
                if (nt.length() > 0)
                	hl7Map.put(level2FieldTag, nt) ;
                if (nt.indexOf(HL7Utils.AMP) >= 0) 
                    decomposeRepeats(nt); 
                i++; 
            } 
 
        }      

        
        public  void decomposeRepeats(String str) 
        { 
            int i=1; 
            StringDelimiter sd; 
            sd = new StringDelimiter(str, HL7Utils.TILDE); 
            while (sd.hasMoreTokens()) 
            { 
                String nt = sd.nextToken(); 
                //System.out.println("Repeating \t{" + i + "}" + "(" + nt.length() + ")" + "\t[" + nt + "]"); 
                if (nt.indexOf(HL7Utils.CARET) >= 0) 
                    decomposeSubfields(nt,i); 
                i++; 
            } 
        } 
 
        public static boolean isMshDelimiterField(String segment, int position) 
 { 
  if ((segment.equals("MSH")) & (position==2)) 
   return true; 
  else 
   return false; 
 } 
 
        public String getMessageControlIdMsa() 
        { 
            return msgControlIdMsa; 
        } 
        public String getMessageControlId() 
        { 
            return msgControlId; 
        } 
        public String getAcknowledgementCode() 
        { 
            return ackCode; 
        } 
        public String getMessageType() { 
            return messageType; 
        } 
        public String getEventType() { 
            System.out.println("returning event type "+eventType); 
            return eventType; 
        }

		public HashMap<String, String> getHl7Map() {
			return hl7Map;
		}

		public void setHl7Map(HashMap<String, String> hl7Map) {
			this.hl7Map = hl7Map;
		} 
 
}