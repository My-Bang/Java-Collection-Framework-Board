package board3.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class BoardDTO {
    private int id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;

    public BoardDTO(int id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = LocalDateTime.now();
    }

    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return createdAt.format(formatter);
    }

    @Override
    public String toString() {
        return id + "\t" + writer + "\t" + getFormattedCreatedAt() + "\t" + title;
    }
}