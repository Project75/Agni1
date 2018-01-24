package com.nttdata.agni.files;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.nttdata.agni.transfomer.HL7Transformer;

import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.util.Hl7InputStreamMessageIterator;
//import ca.uhn.hl7v2.util.Hl7InputStreamMessageStringIterator;

public class HL7FileTransformer {
	
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("hl7_messages.txt");
		List<Message> msgList = new HL7FileTransformer().readHL7FromFile(file);
		System.out.println("Messages read - "+msgList.size());
	}
	public List<Message> readHL7FromFile(File file) throws FileNotFoundException {

			
		// Open an InputStream to read from the file
		
		InputStream is = new FileInputStream(file);
		
		// It's generally a good idea to buffer file IO
		is = new BufferedInputStream(is);
		
		// The following class is a HAPI utility that will iterate over
		// the messages which appear over an InputStream
		Hl7InputStreamMessageIterator iter = new Hl7InputStreamMessageIterator(is);
		Message message = null;
		List<Message> messageList = new ArrayList<Message>();
		while (iter.hasNext()) {
			
			 message = iter.next();
			 messageList.add(message);
			
		}
		
		return messageList;
		
		/*
		 * If you don't want the message parsed, you can also just
		 * read them in as strings.
		 */
		/*
		file = new File("hl7_messages.txt");
		is = new FileInputStream(file);
		is = new BufferedInputStream(is);
		Hl7InputStreamMessageStringIterator iter2 = new Hl7InputStreamMessageStringIterator(is); 
		
		while (iter2.hasNext()) {
			
			String next = iter2.next();
			
			// Do something with the message
			
		}
		*/
	}

}
