package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
@RequestMapping("/game")
public class MoveController {
    private int mass []={0,0,0,0,0,0,0,0,0};
    private Random random;




    @RequestMapping("/move")
    private String move(HttpServletRequest request){
        int num = Integer.parseInt(request.getParameter("num"));
        request.setAttribute("v" + num, "X");
        request.setAttribute("c"+num,"1");
        mass[num]=1;
        boolean tmp = false;
        do{
            int temp = random.nextInt(9);
            if (mass[temp]==0){
                request.setAttribute("v" + temp, "O");
                request.setAttribute("c"+num,"1");
                mass[temp]=2;
            }
        } while (tmp == true);

        return "/index.jsp";
    }
}