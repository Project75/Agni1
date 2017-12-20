package com.nttdata.agni.files;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.blackbear.flatworm.ConfigurationReader;
import com.blackbear.flatworm.FileFormat;
import com.blackbear.flatworm.MatchedRecord;
import com.blackbear.flatworm.errors.*;
import com.blackbear.flatworm.converters.CoreConverters;

@SuppressWarnings("unused")
public class PatientFlatParser extends GenericFlatParser {
	public static void main2(String[] args) {
		PatientFlatParser PatientFlatworm = new PatientFlatParser();
		String input = 
       		 "FB2ABB23-C9D0-4D09-8464-49BF0B982F0F,Male,1947-12-28 02:45:40.547,Unknown,Married,Icelandic,18.08\r"+
       		 "64182B95-EB72-4E2B-BE77-8050B71498CE,Male,1952-01-18 19:51:12.917,African American,Separated,English,13.03\r"
      		  ;
		ArrayList<Patient> patients = PatientFlatworm.parsePatient(input);
		 System.out.println("zz:"+patients.size());
	}
    public ArrayList<Patient> parsePatient(String input) {
    	String conf= "patient-resource.xml";
    	//String input = "Patient";
         ConfigurationReader parser = new ConfigurationReader();
         ArrayList<Patient> patientList = new ArrayList<Patient>();
         try {
        	
             String result;
             
             Reader inputString = new StringReader(input);
             BufferedReader inFromUser = new BufferedReader(inputString);
            
                      
            
             FileFormat ff = parser.loadConfigurationFile(conf);
             //InputStream in = new FileInputStream(input);
             //BufferedReader bufIn = new BufferedReader(new InputStreamReader(in));
             MatchedRecord results;
             while ((results = ff.getNextRecord(inFromUser)) != null) {
                 if (results.getRecordName().equals("patientresource")) {
                    
                    Patient pat = (Patient)results.getBean("patient");
                    System.out.println("pat:"+pat.toString());
                    patientList.add(pat);
                 }
             }

         } catch (FlatwormUnsetFieldValueException flatwormUnsetFieldValueError) {
             flatwormUnsetFieldValueError.printStackTrace();  
         } catch (FlatwormConfigurationValueException flatwormConfigurationValueError) {
             flatwormConfigurationValueError.printStackTrace();
         } catch (FlatwormInvalidRecordException e) {
             e.printStackTrace(); 
         } catch (FlatwormInputLineLengthException e) {
             e.printStackTrace(); 
         } catch (FlatwormConversionException e) {
             e.printStackTrace(); 
         }catch (FlatwormCreatorException e) {
             e.printStackTrace(); 
         }
		return patientList;
    }
}