import org.testng.Assert;
import org.testng.annotations.Test;
import org.junit.rules.ExpectedException;

public class CensusTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndianStatesCensus.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndianStatesCensusData.csv";
    private static final String INCORRECT_FILE_FORMAT = "./src/test/resources/CensusDataInWrongFormat.txt";
    private static final String CSV_WITH_WRONG_DELIMITER = "./src/test/resources/CensusDataWithWrongDelimiter.csv";
    private static final String CSV_WITH_INCORRECT_HEADER = "./src/test/resources/CensusDataIncorrectHeader.csv";


    @Test
    public void outputFromCSVFileCorrectPath() {
        try {
            Census census = new Census();
            int numOfRecords = census.loadData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(33, numOfRecords);
        }
        catch (CensusException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void WithWrongFileThrowException() {
        try {
            Census census = new Census();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusException.class);
            census.loadData(WRONG_CSV_FILE_PATH);
        }
        catch (CensusException e) {
            Assert.assertEquals(CensusException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void correctPathButWrongFileFormatThrowException() {

        try {
            Census census = new Census();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusException.class);
            census.loadData(INCORRECT_FILE_FORMAT);
        }
        catch (CensusException e) {
            Assert.assertEquals(CensusException.ExceptionType.CENSUS_INCORRECT_FILE_FORMAT, e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void whenCustomDelimiterShouldThrowException() {

        try {
            Census census = new Census();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusException.class);
            census.loadData(CSV_WITH_WRONG_DELIMITER);
        }
        catch (CensusException e) {
            Assert.assertEquals(CensusException.ExceptionType.CENSUS_WRONG_DELIMITER_OR_WRONG_HEADER, e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void whenIncorrectHeader_ShouldThrowException() {

        try {
            Census census = new Census();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusException.class);
            census.loadData(CSV_WITH_INCORRECT_HEADER);
        }
        catch (CensusException e) {
            Assert.assertEquals(CensusException.ExceptionType.CENSUS_WRONG_DELIMITER_OR_WRONG_HEADER, e.type);
            e.printStackTrace();
        }
    }
}
