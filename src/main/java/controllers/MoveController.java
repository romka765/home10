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
    private int arrayOfTurns[][];
    private int size;
    private boolean winGame = false;


//cookies, session


    //    начальные параметры игры
    private int[][] createArrayOfTurns(int length) {
        arrayOfTurns = new int[length][length];
// не обращать внимание, пытался избавиться от глобальных переменных
        //       ArrayList<Integer> arrayOfTurns = new ArrayList<Integer>(length);
        for (int i = 0; i < arrayOfTurns.length; i++) {
            for (int j = 0; j < arrayOfTurns.length; j++) {
                arrayOfTurns[i][j] = 0;
            }
        }
        return arrayOfTurns;
    }

    // не обращать внимание, пытался избавиться от глобальных переменных
    private int[][] turnList() {
        return arrayOfTurns;
    }

    private int createNumberOfPossibleMoves(int sizeOfField) {
        return sizeOfField * sizeOfField;
    }

    @RequestMapping("/start")          // Integer для того,чтобы сделать проверку. не придумал как по другому
    private String startGameParameters(@RequestParam("sizeOfField") Integer sizeOfField, Model model) {
        if (sizeOfField != null) {
            model.addAttribute("massOfTurns", createArrayOfTurns(createNumberOfPossibleMoves(sizeOfField)));
            model.addAttribute("numberOfPossibleMoves", createNumberOfPossibleMoves(sizeOfField));
            size = sizeOfField;

        }
        return "index.jsp";
    }

    // не обращать внимание, пытался избавиться от глобальных переменных
//    @RequestMapping("/move")
//    private String move(@RequestParam("num") int numberOfTurn, Model model){
//        turnList().set(numberOfTurn,1);
//        int compTurn;
//        do{
//            compTurn = random.nextInt(turnList().size()-1);
//            if((turnList().get(compTurn) == 0)){
//                turnList().set(compTurn,2);
//            }
//        } while (turnList().get(compTurn)!=0);
//
//        model.addAttribute("arrayOfTurns", arrayOfTurns);
//        return "/index.jsp";
//    }
    @RequestMapping("/move")
    private String move(HttpServletRequest request, Model model) {
        int valHorizon = parseInt("horizon");
        int valVertical = parseInt("vertical");
        arrayOfTurns[valHorizon][valVertical] = 1;
        int compHorizon;
        int compVertical;
        do {
            compHorizon = randomNumber(size);
            compVertical = randomNumber(size);
            if (arrayOfTurns[compHorizon][compVertical] == 0){
                arrayOfTurns[compHorizon][compVertical] = 2;
            }
        } while (arrayOfTurns[compHorizon][compVertical] != 0);

        model.addAttribute("arrayOfTurns", arrayOfTurns);

        return "/index.jsp";
    }
    
    private int parseInt(String request) {
        return Integer.parseInt(request);
    }

    private int randomNumber(int size){
        return random.nextInt(size-1);
    }

    private void getWinner(String playerName, int arrayOfTurns[][], int valHorizont, int valVertical, Model model){
        if (checkWinner(arrayOfTurns,valHorizont,valVertical)){
            model.addAttribute("playerName", playerName);
        }
    }

    private boolean checkWinner(int arrayOfTurns[][], int valHorizont, int valVertical) {
        //проверка горизонтальной линии
        if (!winGame) {
            for (int i = 0; i < arrayOfTurns.length - 1; i++) {
                if (arrayOfTurns[valHorizont][i] > 0) {
                    if (arrayOfTurns[valHorizont][i + 1] != arrayOfTurns[valHorizont][i]) {
                        winGame = false;
                        break;
                    } else winGame = true;
                } else break;
            }
        }
        //проверка вертикальной линии
        if (!winGame) {
            for (int i = 0; i < arrayOfTurns.length - 1; i++) {
                if (arrayOfTurns[i][valVertical] > 0) {
                    if (arrayOfTurns[i + 1][valVertical] != arrayOfTurns[i][valVertical]) {
                        winGame = false;
                        break;
                    } else winGame = true;
                } else break;
            }
        }
        // проверка главной диагонали (если относится)
        if (!winGame) {
            if (valHorizont == valVertical) {
                for (int i = 0; i < arrayOfTurns.length - 1; i++) {
                    if (arrayOfTurns[i][i] > 0) {
                        if (arrayOfTurns[i][i] != arrayOfTurns[i + 1][i + 1]) {
                            winGame = false;
                            break;
                        } else winGame = true;
                    } else break;
                }
            }
        }
        //проверка побочной диагонали
        if (!winGame) {
            for (int i = arrayOfTurns.length; i > 0; i--) {
                for (int j = 0; j < arrayOfTurns.length - 1; j++) {
                    if (arrayOfTurns[i][j] > 0) {
                        if (arrayOfTurns[i][j] != arrayOfTurns[i - 1][j + 1]) {
                            winGame = false;
                            break;
                        } else winGame = true;
                    } else break;
                }
            }
        }
        if(winGame) {
            return true;
        } return false;
    }

//                    }
//                    if(arrayOfTurns[i][vertical]==1) {
//                        playerPointsVertical++;
//                    }
//                    if(arrayOfTurns[i][vertical]==2){
//                        computerPointsVertical++;
//                    }
//                }
//                if(playerPointsHorizont==size || playerPointsVertical == size){
//                    winGame= true;
//                    return "Игрок";
//                }
//                if(computerPointsHorizont==size || computerPointsVertical == size){
//                    return "Компьютер";
//                }
//        }
//        if(!winGame){
//            int  playerPoints = 0;
//            int computerPoints = 0;
//            for(int i = 0; i<size; i++){
//                if (arrayOfTurns[i][i] == 1){
//                    playerPoints++;
//                }
//                if(arrayOfTurns[i][i]=2)
//            }
//        }
//    }
//
//    private boolean checkLine(int arrayOfTurns[][], int ){
//
//    }


}
