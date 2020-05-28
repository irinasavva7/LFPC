public class Token {

    private int lineNumber;
    private TokenType type;
    private String value;

    public Token(TokenType type, String value, int lineNumber) {
        this.type = type;
        this.value = value;
        this.lineNumber = lineNumber;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public String toString() {

        String returnString = "Token Type:: ";
        returnString += type.toString() + "\nThe value: ";
        returnString += value;
        returnString += "\nIn the line: ";
        returnString += lineNumber;
        return returnString;
    }
}