package board4;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Board {
    private int bno;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;


    public Board() {
        this.createdAt = LocalDateTime.now();

    }

    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return createdAt.format(formatter);
    }


    @Override
    public String toString() {
        return bno + "\t\t" + author + "\t\t" + getFormattedCreatedAt() + "\t\t" + title;
    }
}
