<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
<style>
    table {
        border: 1px solid grey;
        border-collapse: collapse;
    }

    td {
        border: 1px solid grey;
        width: 20px;
        height: 20px;
        text-align: center;
    }
    .class {
        color: white;
    }

</style>
</head>
<body>
<h3 align="center">Tic-tac-toe</h3>
<table align="center">
    <c:if test = "${arrayOfTurns==null}">

    <p align="center">Выберите размер поля</p><br>
    <form action="/game/start">
        <input align="center" type="text" name="sizeOfField" pattern="[3-9]{1}" placeholder="Введите число от 3 до 9"><br>
        <input align="center" type="submit" value="Начать игру">
    </form>
    </c:if>
    <c:if test="${arrayOfTurns!=null}">
        <c:set var="val1" value="0"/>
        <c:forEach items="${arrayOfTurns}" var="item">
            <c:set var="val2" value="0"/>
            <tr>
            <c:forEach items="${item}" var="massItem">
                <c:if test="${checkWinner==false}">
                    <c:if test="${massItem==0}">
                        <td><a class="class" href="/game/move?idMass1=${val1}&idMass2=${val2}">z</a></td>
                    </c:if>
                    <c:if test="${massItem==1}">
                        <td>X</td>
                    </c:if>
                    <c:if test="${massItem==2}">
                        <td>O</td>
                    </c:if>
                    <c:if test="${checkWinner==true}">
                        <a class="class">-</a>
                    </c:if>
                    <c:set var = "val2" value="${val2+1}"/>
                </c:if>
            </c:forEach>
            </tr>
            <c:set var = "val1" value="${val1+1}"/>
        </c:forEach>
</table>
        <h3>
        <c:if test="${arrayOfTurns !=null}">
            <form action="/index.jsp">
                <input align="center" type="submit" value="Начать заново.">
            </form>
        </c:if>
        <c:if test="${checkWinner==true}">
            <br><hr>
            <h2 align="center">Игра окончена! Победил ${playerName}</h2>
        </c:if>
        <c:if test="${numberOfPossibleMoves==0}">
            <h2 align="center">Игра окончена вничью.</h2>
        </c:if>
        </c:if>
        </h3>
</body>
</html>
