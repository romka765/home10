package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Random;

@Controller
@RequestMapping("/game")
public class MoveController {
    private Random random = new Random();
    private ArrayList<Integer> arrayOfTurns;
    private int size;


//cookies, session



//    начальные параметры игры
    private ArrayList createArrayOfTurns(int length){

 //       ArrayList<Integer> arrayOfTurns = new ArrayList<Integer>(length);
        for(int i = 0; i <arrayOfTurns.size(); i++){
            arrayOfTurns.add(i,0);
        }
        return arrayOfTurns;
    }
    private ArrayList turnList(){
        return arrayOfTurns;
    }

    private int createNumberOfPossibleMoves(int sizeOfField){
        return sizeOfField*sizeOfField;
    }

    @RequestMapping("/start")          // Integer для того,чтобы сделать проверку. не придумал как по другому
    private String startGameParameters(@RequestParam("sizeOfField") Integer sizeOfField, Model model){
        if (sizeOfField != null) {
            model.addAttribute("massOfTurns", createArrayOfTurns(createNumberOfPossibleMoves(sizeOfField)));
            model.addAttribute("numberOfPossibleMoves", createNumberOfPossibleMoves(sizeOfField));
            size = sizeOfField;

        }
        return "index.jsp";
    }

    @RequestMapping("/move")
    private String move(@RequestParam("num") int numberOfTurn, Model model){
        turnList().set(numberOfTurn,1);
        int compTurn;
        do{
            compTurn = random.nextInt(turnList().size()-1);
            if((turnList().get(compTurn) == 0)){
                turnList().set(compTurn,2);
            }
        } while (turnList().get(compTurn)!=0);

        model.addAttribute("arrayOfTurns", arrayOfTurns);
        return "/index.jsp";
    }

    private String checkWinner(ArrayList arrayOfTurns){
        if (arrayOfTurns !=null){
            int tempForCheckOne = size - 1;
            int tempForCheckTwo = size + 1;
            int j=0;
            for(int i = 0; i < size; i++){

                for(; j < turnList().size(); j+){

                }
            }
        }
    }

}