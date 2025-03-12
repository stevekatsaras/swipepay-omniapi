package io.swipepay.omniapi.batch.payload;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import io.swipepay.omniapi.common.exception.RequestException;

public class BatchFactory {
	
	public static BatchRequest buildRequest(String csvPayload) throws RequestException {
		BatchRequest batchRequest = null;
		
		
		
		CSVReader csvReader = new CSVReaderBuilder(new StringReader(csvPayload)).build();
		
		
		
		
		List<String[]> records;
		try {
			records = csvReader.readAll();
			for (String[] record : records) {
			    System.out.println(record[0]);
			    System.out.println(record[1]);
			    System.out.println("---------------------------");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		
		return  batchRequest;
	}
}