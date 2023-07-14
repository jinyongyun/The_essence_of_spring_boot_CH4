# The_essence_of_spring_boot_CH4
스프링 부트의 정석 4번째
<img width="361" alt="스크린샷 2023-07-14 오후 11 37 09" src="https://github.com/jinyongyun/The_essence_of_spring_boot_CH4/assets/102133961/f2462ae1-20b2-4a39-8fd3-7e431a6578c8">


https://www.notion.so/4c4b0a4e389e4ec9bc9fc79aa9a48709?pvs=4


<h1>최종 게시판 결과</h1>
```java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.File;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Value("${uploadPath}")
    private String uploadPath;

    @GetMapping("/uploadForm")
    public String showUploadForm(){
        return "/board/uploadFile";
    }

    @PostMapping("/deleteFile")
    public ResponseEntity<String> deleteFile(String filename){
        System.out.println("filename = " + filename);

        File file = new File(uploadPath+filename);
        if(file.delete()==true)
            return new ResponseEntity<>("successfully deleted",HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value="/download",produces= MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(String filename){
        System.out.println("filename = " + filename);
        Resource resource = new FileSystemResource(uploadPath+filename);

        return new ResponseEntity<>(resource, HttpStatus.OK);

    }

  //  @PostMapping("/uploadFile")
    @PostMapping("/uploadAjax")
    @ResponseBody
    public ResponseEntity<List<String>> uploadFile(MultipartFile[] files) throws IOException {
        List<String> list = new ArrayList<>();

        for(MultipartFile file : files){
            System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename());
            System.out.println("file.getSize() = " + file.getSize());
            File upFile = new File(uploadPath, file.getOriginalFilename());
            file.transferTo(upFile); //업로드된 파일을 아까 지정해둔 download/upload 에 저장
            list.add(file.getOriginalFilename());
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/modify")
    public String modify(Long bno, Model model){
    Board board = boardService.read(bno);
    model.addAttribute("board", board);
    return "/board/write";
    }

    @PostMapping("/modify")
    public String modify(Board board){
        boardService.modify(board);
        return "redirect:/board/list";
    }


    @GetMapping("/write")
    public String showWriteForm(Model model){
     Board board = new Board();
     User user = new User();
     user.setId("bbb");
     board.setUser(user);
     model.addAttribute("board", board);
     return "/board/write";

    }

    @PostMapping("/write")
    public String writeBoard(Board board){
        board.setBno(11L); //자동 증가하도록 하는 것이 바람직
        User user = new User();
        user.setId("bbb");
        board.setUser(user);
        board.setViewCnt(0L);
        board.setInDate(new Date());
        board.setUpDate(new Date());
        boardService.write(board);
        return "redirect:/board/list";//글을 삭제한 다음 게시글 목록으로!
    }

    @PostMapping("/remove")
    public String remove(Long bno){
        boardService.remove(bno);
        return "redirect:/board/list";
    }


    @GetMapping("/read")
    public String read(Long bno, Model model){
        Board board = boardService.read(bno);
        model.addAttribute("board", board);
        return "/board/read"; // read.html을 뷰로 사용
    }


    @GetMapping("/list")
 public String getList(Model model){
     List<Board> list = boardService.getList();
     model.addAttribute("list",list);

     return "/board/list";
 }


}

```



![화면 기록 2023-07-14 오후 11 18 48](https://github.com/jinyongyun/The_essence_of_spring_boot_CH4/assets/102133961/a0810a13-19d4-42f2-8a22-c88350ea29c8)

