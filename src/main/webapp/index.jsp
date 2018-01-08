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
    <c:if test = "${arrayOfTurns == null}">

    <p align="center">Выберите размер поля</p><br>
    <form action="/game/start">
        <input align="center" type="text" name="sizeOfField" pattern="[3-9]{1}|[0-1]{,2}" placeholder="Введите число от 3 до 10"><br>
        <input align="center" type="submit" value="Начать игру">
    </form>
    </c:if>
    <c:if test="${arrayOfTurns != null}">
        <c:set var="counter" value="0"/>
        <tr>
        <c:forEach items="${arrayOfTurns}" var="item">
            <c:if test="${checkWinner == false}">
                <c:if test="${item ==0}">
                    <a class="class" href="/game/move?num=${counter}"
                </c:if>
                <c:if test="${item == 1}">
                    <td>X</td>
                </c:if>
                <c:if test="${item == 2}">
                    <td>O</td>
                </c:if>
                <c:if test="${checkWinner == true}">
                    <a class="class">-</a>
                </c:if>
                <c:if test="${(counter+1)%size ==0}">
                    </tr>
                </c:if>
                <c:set var = "counter" value="${counter+1}"/>
            </c:if>
        </c:forEach>
</table>
        <c:if test="${arrayOfTurns !=null}">
            <form action="/index.jsp">
                <input type="submit" value="Начать заново.">
            </form>
        </c:if>
        <c:if test="${checkWinner == true}">
            <br><hr>
            <h2 align="center">Игра окончена! Победил ${playerName}}</h2>
        </c:if>
        <c:if test="${numberOfPossibleMoves == 0}">
            <h2 align="center">Игра окончена вничью.</h2>
        </c:if>
    </c:if>
</body>

<%--<h2 align="center">Крестики-нолики</h2>--%>
<%--<hr>--%>
<%--<table align="center">--%>
    <%--<tr>--%>
        <%--<td> <a class = "class${c0}" href = "/game/move?num=0">" ${v0} "</a>  </td>--%>
        <%--<td> <a class = "class${c1}" href = "/game/move?num=1">" ${v1} "</a>  </td>--%>
        <%--<td> <a class = "class${c2}" href = "/game/move?num=2">" ${v2} "</a> </td>--%>


    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td> <a class = "class${c3}" href = "/game/move?num=3">" ${v3} "</a> </td>--%>
        <%--<td> <a class = "class${c4}" href = "/game/move?num=4">" ${v4} "</a> </td>--%>
        <%--<td> <a class = "class${c5}" href = "/game/move?num=5">" ${v5} "</a> </td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td> <a class = "class${c6}" href = "/game/move?num=6">" ${v6} "</a> </td>--%>
        <%--<td> <a class = "class${c7}" href = "/game/move?num=7">" ${v7} "</a> </td>--%>
        <%--<td> <a class = "class${c8}" href = "/game/move?num=8">" ${v8} "</a> </td>--%>
    <%--</tr>--%>
<%--</table>--%>
<%--<hr>--%>
