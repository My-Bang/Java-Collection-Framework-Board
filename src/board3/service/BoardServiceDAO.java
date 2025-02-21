package board3.service;

import board3.dto.BoardDTO;
import java.util.ArrayList;
import java.util.List;

public class BoardServiceDAO implements BoardService {
    private List<BoardDTO> boards = new ArrayList<>();

    @Override
    public void insertBoard(BoardDTO boardDto) {
        boards.add(boardDto);
    }

    @Override
    public void updateBoard(BoardDTO updatedBoard) {
        for (BoardDTO board : boards) {
            if (board.getId() == updatedBoard.getId()) {
                board.setTitle(updatedBoard.getTitle());
                board.setContent(updatedBoard.getContent());
                board.setWriter(updatedBoard.getWriter());
                break;
            }
        }
    }

    @Override
    public void deleteBoard(BoardDTO boardDto) {
        boards.removeIf(board -> board.getId() == boardDto.getId());
    }



    @Override
    public void clearBoard() {
        boards.clear();
    }

    @Override
    public BoardDTO getBoardById(int boardId) {
        for (BoardDTO board : boards) {
            if (board.getId() == boardId) {
                return board;
            }
        }
        return null;
    }

    @Override
    public List<BoardDTO> getAllBoards() {
        return boards;
    }
}
