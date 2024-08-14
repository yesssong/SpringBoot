package com.githrd.demo_fileupload.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import jakarta.servlet.ServletContext;

@Controller
public class FileUploadController {

    @Autowired
    ServletContext application;

    // uploaad1.do?title=제목&photo=사진.jpg

    // title, photo 따로 받기 (따로 관리) - vo로 받아서 한 번에 관리 가능하나 불필요함 걍 따로 하기

    // @RequestMapping(value="/upload1.do", method = RequestMethod.POST)
    @PostMapping("/upload1.do")         // 파라미터로 들어온 애 중에 photo 있으면
    public String upload1(String title, @RequestParam MultipartFile photo, Model model) throws IllegalStateException, IOException{     // @RequestParam 작성 안하면 null값 떨어짐

        // 저장 할 위치 정보 구하기
        String absPath = application.getRealPath("/images/");
        //System.out.println(absPath);    // 실제 경로 확인용 - 터미널에 찍히도록 함

        // 파일 업로드 처리
        String filename = "no_file";

        if(!photo.isEmpty()){
            filename = photo.getOriginalFilename();
            File f = new File(absPath, filename);

            if(f.exists()){
                long tm = System.currentTimeMillis();
                filename = String.format("%d_%s", tm, filename);
                f = new File(absPath, filename);
            }
            // 예외처리 throws
            photo.transferTo(f);
        }
        model.addAttribute("title", title);
        model.addAttribute("filename", filename);

        return "result1";
    }//end: upload1()

    // uploaad2.do?title=제목&photo=사진.jpg&photo=사진2.jpg  ->  같은 이름으로 들어올 시 배열 or ArrayList로 받음
    @PostMapping("/upload2.do")                                     // ArrayList로 받아도 됨 -> 근데 배열이 더 편하대요 쌤이
    public String upload2(String title, @RequestParam(name="photo") MultipartFile [] photo_array, Model model) throws IllegalStateException, IOException{

        // 저장 할 위치 정보 구하기 -> 절대경로 구하기
        String absPath = application.getRealPath("/images/");

        String filename1 = "no_file";
        String filename2 = "no_file";

        for(int i=0; i<photo_array.length; i++){

            MultipartFile photo = photo_array[i];

            // 위에 코드 복붙
            if(!photo.isEmpty()){
                // String 붙인 이유 : 여기 안에서만 사용할 임시변수 선언
                String filename = photo.getOriginalFilename();
                File f = new File(absPath, filename);
    
                if(f.exists()){
                    long tm = System.currentTimeMillis();
                    filename = String.format("%d_%s", tm, filename);
                    f = new File(absPath, filename);
                }
                // 예외처리 throws
                photo.transferTo(f);
                // 전역변수에 임시변수 넣기
                if(i==0) filename1 = filename;      // 첫번째 파일일 경우 
                else if(i==1) filename2 = filename; // 두번째 파일일 경우
            }
        }// end: for

        model.addAttribute("title", title);
        model.addAttribute("filename1", filename1);
        model.addAttribute("filename2", filename2);

        return "result2";
    }//end: upload2()

    @PostMapping("/upload3.do")                                     
    public String upload3(String title, @RequestParam(name="photo") List<MultipartFile> photo_list, Model model) throws IllegalStateException, IOException{

        // 저장 할 위치 정보 구하기 -> 절대경로 구하기
        String absPath = application.getRealPath("/images/");

        List<String> filename_list = new ArrayList<String>();

        for(MultipartFile photo : photo_list){
            // 위에 코드 복붙
            if(!photo.isEmpty()){
            // String 붙인 이유 : 여기 안에서만 사용할 임시변수 선언
            String filename = photo.getOriginalFilename();
            File f = new File(absPath, filename);

            if(f.exists()){
                long tm = System.currentTimeMillis();
                filename = String.format("%d_%s", tm, filename);
                f = new File(absPath, filename);
            }
            // 예외처리 throws
            photo.transferTo(f);

            filename_list.add(filename);

            }        
        }   // end: for

        model.addAttribute("title", title);
        model.addAttribute("filename_list", filename_list);

        return "result3";   // result3.jsp 만들기
    }

}
