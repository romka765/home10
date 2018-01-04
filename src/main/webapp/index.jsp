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

    .class1 {
        color: black;
        pointer-events: none;
    }

</style>
</head>
<body>

<h2 align="center">Крестики-нолики</h2>
<hr>
<table align="center">
    <tr>
        <td> <a class = "class${c0}" href = "/game/move?num=0">" ${v0} "</a>  </td>
        <td> <a class = "class${c1}" href = "/game/move?num=1">" ${v1} "</a>  </td>
        <td> <a class = "class${c2}" href = "/game/move?num=2">" ${v2} "</a> </td>


    </tr>
    <tr>
        <td> <a class = "class${c3}" href = "/game/move?num=3">" ${v3} "</a> </td>
        <td> <a class = "class${c4}" href = "/game/move?num=4">" ${v4} "</a> </td>
        <td> <a class = "class${c5}" href = "/game/move?num=5">" ${v5} "</a> </td>
    </tr>
    <tr>
        <td> <a class = "class${c6}" href = "/game/move?num=6">" ${v6} "</a> </td>
        <td> <a class = "class${c7}" href = "/game/move?num=7">" ${v7} "</a> </td>
        <td> <a class = "class${c8}" href = "/game/move?num=8">" ${v8} "</a> </td>
    </tr>
</table>
<hr>
