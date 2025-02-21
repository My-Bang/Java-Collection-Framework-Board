package board3.service;

import board3.dto.BoardDTO;
import java.util.List;

public interface BoardService {
    void insertBoard(BoardDTO boardDto);
    void updateBoard(BoardDTO updatedBoard);
    void deleteBoard(BoardDTO boardNo);
    void clearBoard();
    BoardDTO getBoardById(int boardId);
    List<BoardDTO> getAllBoards();
}
