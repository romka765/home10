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
    private int numberOfPossibleMoves;


//cookies, session


    //    начальные параметры игры
    private int[][] createArrayOfTurns(int size) {
        arrayOfTurns = null;
        arrayOfTurns = new int[size][size];
// не обращать внимание, пытался избавиться от глобальных переменных
        //       ArrayList<Integer> arrayOfTurns = new ArrayList<Integer>(length);
        for (int i = 0; i < arrayOfTurns.length; i++) {
            for (int j = 0; j < arrayOfTurns[i].length; j++) {
                arrayOfTurns[i][j] = 0;
            }
        }
        return arrayOfTurns;
    }

    // не обращать внимание, пытался избавиться от глобальных переменных
//    private int[][] turnList() {
//        return arrayOfTurns;
//    }

    private int createNumberOfPossibleMoves(int sizeOfField) {
        numberOfPossibleMoves= sizeOfField * sizeOfField;
        return numberOfPossibleMoves;
    }

    @RequestMapping("/start")          // Integer для того,чтобы сделать проверку. не придумал как по другому
    private String startGameParameters(@RequestParam("sizeOfField") Integer sizeOfField, Model model) {
        winGame= false;
        if (sizeOfField != null) {
            size = sizeOfField;
            createNumberOfPossibleMoves(size);
            createArrayOfTurns(size);
        }
        System.out.println(arrayOfTurns.toString());
        model.addAttribute("arrayOfTurns", arrayOfTurns);
        model.addAttribute("numberOfPossibleMoves", numberOfPossibleMoves);
        model.addAttribute("checkWinner", winGame);
        return "/index.jsp";
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
        int valHorizon = parseInt(request.getParameter("idMass1"));
        int valVertical = parseInt(request.getParameter("idMass2"));
        arrayOfTurns[valHorizon][valVertical] = 1;
        if (!checkWinner(arrayOfTurns, valHorizon, valVertical)) {
            getWinner("Игрок", arrayOfTurns, valHorizon, valVertical, model);
            numberOfPossibleMoves--;
        }
            if (!winGame) {
                int compHorizon;
                int compVertical;
                do {
                    compHorizon = randomNumber(size-1);
                    compVertical = randomNumber(size-1);
                    if (arrayOfTurns[compHorizon][compVertical] == 0) {
                        arrayOfTurns[compHorizon][compVertical] = 2;
                    }
                } while (arrayOfTurns[compHorizon][compVertical] == 0 && arrayOfTurns[compHorizon][compVertical] == 1);
                numberOfPossibleMoves--;
                getWinner("Компьютер", arrayOfTurns, valHorizon, valVertical, model);
            }
            model.addAttribute("arrayOfTurns", arrayOfTurns);
            model.addAttribute("numberOfPossibleMoves", numberOfPossibleMoves);
            model.addAttribute("checkWinner", winGame);
            return "/index.jsp";
    }
    
    private int parseInt(String request) {
        return Integer.parseInt(request);
    }

    private int randomNumber(int size){
        return random.nextInt(size-1);
    }

    private void getWinner(String playerName, int arrayOfTurns[][], int valHorizon, int valVertical, Model model){
        if (checkWinner(arrayOfTurns,valHorizon,valVertical)){
            model.addAttribute("sizeOfField", null);
            model.addAttribute("playerName", playerName);
        }
    }

    private boolean checkWinner(int arrayOfTurns[][], int valHorizon, int valVertical) {
        //проверка горизонтальной линии
        if (!winGame) {
            for (int i = 0; i < arrayOfTurns.length - 1; i++) {
                if (arrayOfTurns[valHorizon][i] > 0) {
                    if (arrayOfTurns[valHorizon][i + 1] != arrayOfTurns[valHorizon][i]) {
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
            if (valHorizon == valVertical) {
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
            for (int i = arrayOfTurns.length-1; i > 0; i--) {
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
}
