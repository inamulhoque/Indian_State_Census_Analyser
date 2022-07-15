import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class Census {
    public int loadData(String csvFilePath) throws CensusException{
        try {
            if (csvFilePath.contains("txt")){
                throw new CensusException("File must be in csv format",CensusException.ExceptionType.CENSUS_INCORRECT_FILE_FORMAT);
            }
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IndianCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndianCensusCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndianCensusCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IndianCensusCSV> censusCSVIterator = csvToBean.iterator();
            int numberOfEntries = 0;
            while(censusCSVIterator.hasNext()) {
                numberOfEntries++;
                IndianCensusCSV censusData = censusCSVIterator.next();
            }
            return numberOfEntries;
        }
        catch(IOException e) {
            throw new CensusException(e.getMessage(), CensusException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        catch(RuntimeException e) {
            throw new CensusException("CSV File Must Have Comma As Delimiter Or Has Incorrect Header", CensusException.ExceptionType.CENSUS_WRONG_DELIMITER_OR_WRONG_HEADER);
        }
        }
    }
