public class IsbnNormalizerValidator {

    public static String normalizeCode(String raw) {
        if (raw == null) return "";

        String trimmed = raw.trim();
        if (trimmed.length() < 3) return trimmed;

        String pubCode = trimmed.substring(0, 3).toUpperCase();
        String remainder = trimmed.substring(3);

        return pubCode + remainder;
    }

    public static String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: wrong length";
        }

        // Validate first 3 characters are letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        // Validate remaining 10 characters are digits
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: body must contain digits only";
            }
        }

        // Format: [PUBCODE] YEAR: 20XX | CATALOG: 123456
        String pubCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(pubCode).append("] ");
        sb.append("YEAR: ").append(year);
        sb.append(" | CATALOG: ").append(catalog);

        return sb.toString();
    }

    public static void main(String[] args) {
        String raw1 = " pen2026004251 ";
        String normalized1 = normalizeCode(raw1);
        System.out.println(validateAndFormat(normalized1));

        String raw2 = "12N2026004251";
        String normalized2 = normalizeCode(raw2);
        System.out.println(validateAndFormat(normalized2));
    }
}