package springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class FileController {

    //文件操作
    @GetMapping("/file")
    @ResponseBody
    public String file(){
        //读取文件
        File file = new File("hello.txt");
        //File file = new File("../hello.txt"); //相对路径
        if(file.exists()) {
            System.out.println(file.isDirectory());
            System.out.println(file.isFile());
            //文件重命名，能移动文件但必须在同一个分区
            File renameFile=new File("../SpringBootDemo/src/main/resources/new Hello.txt");
            System.out.println(file.renameTo(renameFile));

//            file.delete();
//            System.out.println("文件删除成功");
        }else{
            System.out.println("文件不存在");
            try {
                file.createNewFile();
                System.out.println("文件已经创建");
            } catch (IOException e) {
                System.out.println("文件创建失败");
            }
        }


        return "file";
    }

    //遍历文件夹
    @GetMapping("/folder")
    @ResponseBody
    public String folder(){
        File file = new File("../SpringBootDemo");
        printFiles(file,1);
        return "打印文件";
    }

    private void printFiles(File dir,int tab) {
        if(dir.isDirectory() ){
            File[] next = dir.listFiles();
            for (int i = 0; i < next.length; i++) {
                for (int j = 0; j < tab; j++) {
                    System.out.print("---");
                }
                System.out.println(next[i].getName());
                if(next[i].isDirectory()){
                    printFiles(next[i],tab+1);
                }
            }
        }
    }
}
