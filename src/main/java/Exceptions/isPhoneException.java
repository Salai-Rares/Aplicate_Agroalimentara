package Exceptions;

public class isPhoneException {
    public static boolean isPhone(String strNum) {
        if(!strNum.startsWith("0"))
            return false;

        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
