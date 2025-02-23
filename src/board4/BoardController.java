package board4;

import java.util.Scanner;

public class BoardController {
    BoardServiceDAO boardService = new BoardServiceDAO();
    Scanner in = new Scanner(System.in);

    public void list() {
        System.out.println("\n[게시물 목록]");
        System.out.println("-------------------------------------------");
        System.out.println("no \t\t write \t\t date \t\t title");
        System.out.println("-------------------------------------------");
        boardService.getBoards();
        System.out.println("-------------------------------------------");

        mainMenu();
    }

    public void read() {
        System.out.println("\n[게시물 읽기]");
        System.out.print("bno: ");
        int bno = in.nextInt();
        boardService.getBoardById(bno);
        while (true) {
            System.out.println("보조 메뉴: 1. Update | 2. Delete | 3. List");
            System.out.print("메뉴 선택: ");
            int subMenu = in.nextInt();
            in.nextLine();

            switch (subMenu) {
                case 1:
                    update(bno);
                    break;
                case 2:
                    delete(bno);
                    break;
                case 3:
                    list();
                    break;
                default:
                    break;
            }
        }

    }


    public void mainMenu() {
        while (true) {
            System.out.println("메인 메뉴: 1. Create \t 2. Read \t 3.Clear \t 4.Exit");
            System.out.print("메뉴 선택: ");
            int menu = in.nextInt();
            switch (menu) {
                case 1:
                    create();
                    break;
                case 2:
                    read();
                    break;
                case 3:
                    clear();
                    break;
                case 4:
                    System.out.println("** 게시판 종료 **");
                    System.exit(0);
            }
        }
    }

    public void clear() {
        System.out.println("\n[게시물 전체 삭제]");
        System.out.println("-------------------------------------------");
        System.out.println("보조 메뉴 1. OK | 2. Cancel");
        System.out.print("메뉴 선택: ");
        int subMenu = in.nextInt();
        if (subMenu == 1) {
            boardService.clearBoards();
            list();
        } else if (subMenu == 2) {
            System.out.println("삭제를 취소합니다.");
            list();
        } else {
            System.out.println("다시 입력해 주세요");
        }
    }

    public void create() {
        Board newboard = new Board();
        Scanner in = new Scanner(System.in);
        int maxBno = boardService.list.stream()
                .mapToInt(Board::getBno)
                .max()
                .orElse(0);

        newboard.setBno(maxBno + 1);
        System.out.println("\n[새 게시물 입력]");
        System.out.print("제목: ");
        String title = in.nextLine();
        newboard.setTitle(title);

        System.out.print("내용: ");
        String content = in.nextLine();
        newboard.setContent(content);

        System.out.print("작성자: ");
        String author = in.nextLine();
        newboard.setAuthor(author);
        System.out.println("-------------------------------------------");
        System.out.println("보조 메뉴 1. OK | 2. Cancel");
        System.out.print("메뉴 선택: ");
        int subMenu = in.nextInt();
        if (subMenu == 1) {
            boardService.insertBoard(newboard);
            list();
        } else if (subMenu == 2) {
            System.out.println("작성을 취소합니다.");
            list();
        } else {
            System.out.println("다시 입력해 주세요");
        }
    }

    public void update(int id) {
        System.out.println("\n[수정 내용 입력]");
        System.out.print("제목: ");
        String title = in.nextLine();
        System.out.print("내용: ");
        String content = in.nextLine();
        System.out.print("작성자: ");
        String author = in.nextLine();
        System.out.println("-------------------------------------------");

        System.out.println("보조 메뉴 1. OK | 2. Cancel");
        System.out.print("메뉴 선택: ");
        int subMenu = in.nextInt();

        if (subMenu == 1) {
            boardService.updateBoard(id, title, content, author);
            list();
        } else if (subMenu == 2) {
            System.out.println("수정을 취소합니다.");
            list();
        } else {
            System.out.println("다시 입력해 주세요");
        }


    }

    public void delete(int id) {
        boardService.deleteBoard(id);
        list();
    }
}
