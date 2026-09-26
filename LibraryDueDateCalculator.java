import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class LibraryItem {
    protected String title;
    protected LocalDate currentDate = LocalDate.of(2023, 10, 26);

    public LibraryItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract LocalDate calculateDueDate();
}

class BookItem extends LibraryItem {
    public BookItem(String title) {
        super(title);
    }

    @Override
    public LocalDate calculateDueDate() {
        return currentDate.plusDays(14);
    }
}

class DVDItem extends LibraryItem {
    public DVDItem(String title) {
        super(title);
    }

    @Override
    public LocalDate calculateDueDate() {
        return currentDate.plusDays(7);
    }
}

class MagazineItem extends LibraryItem {
    public MagazineItem(String title) {
        super(title);
    }

    @Override
    public LocalDate calculateDueDate() {
        return currentDate.plusDays(3);
    }
}

public class LibraryDueDateCalculator {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            if (!sc.hasNextInt()) return;

            int N = sc.nextInt();
            sc.nextLine(); // Clear newline

            List<LibraryItem> items = new ArrayList<>();
            Pattern pattern = Pattern.compile("^(\\S+)\\s+\"(.*)\"$");

            for (int i = 0; i < N; i++) {
                if (!sc.hasNextLine()) break;
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    i--;
                    continue;
                }

                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String type = matcher.group(1);
                    String title = matcher.group(2);

                    switch (type) {
                        case "BOOK":
                            items.add(new BookItem(title));
                            break;
                        case "DVD":
                            items.add(new DVDItem(title));
                            break;
                        case "MAGAZINE":
                            items.add(new MagazineItem(title));
                            break;
                    }
                }
            }

            for (LibraryItem item : items) {
                System.out.println(item.getTitle() + ": " + item.calculateDueDate());
            }
        }
    }
}