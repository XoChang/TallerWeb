package Group3.JobsMadeEasy.util;


public class JobsMadeEasyException extends Exception {

    private String message;

    public JobsMadeEasyException(String message) {
        super(message);
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "JobsMadeEasyException{" +
                "message='" + message + '\'' +
                '}';
    }

}
