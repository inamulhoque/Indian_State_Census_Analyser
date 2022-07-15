public class CensusException extends Exception{
    enum ExceptionType{
        CENSUS_FILE_PROBLEM, CENSUS_INCORRECT_FILE_FORMAT, CENSUS_WRONG_DELIMITER_OR_WRONG_HEADER;
    }
    ExceptionType type;
    public CensusException(String message, ExceptionType type){
        super(message);
        this.type = type;
    }
    public CensusException(String message, ExceptionType type, Throwable cause){
        super(message,cause);
        this.type = type;
    }
}
