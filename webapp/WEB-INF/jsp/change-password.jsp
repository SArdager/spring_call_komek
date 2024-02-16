<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Change password page</title>
  <link rel="stylesheet" type="text/css" href="resources/css/main.css">
  <script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="resources/js/passwordChanger.js"></script>
</head>

<body>
  <section>
     <div class="container">
        <h1>Смена пароля</h1>
        <h3><div id="result_line"></div></h3>
        <br>
        <form:form id="change_password" action="change-password/change" method="post">
        <table>
            <tr>
                <td class="row_title">Новый пароль</td>
                <td><input type="password" id="password" name="password" size="40" autofocus="true" required/></td>
            </tr>
            <tr>
                <td class="row_title">Подтвердите пароль</td>
                <td><input type="password" id="confirm_password" size="40" autofocus="true" required/></td>
            </tr>
            <tr>
                <td class="row_title"></td>
                <td>
                <br>
                <br>
                <input type="button" id="btn_change_password" value="Сменить пароль"/></td>
            </tr>
        </table>
        </form:form>
     </div>
  </section>
    <script>
        $(document).ready(function(){
            $("h1").css("color", "blue");
            let resultLineValue;
            let clickNumber = 0;
            window.addEventListener("click", function(){
                clickNumber++;
                resultLineValue = $('#result_line').text();
                if(clickNumber==0){
                    $('#result_line').text("");
                }
                if(resultLineValue.length>0){
                    clickNumber = -1;
                }
            });
       });
    </script>

</body>
</html>