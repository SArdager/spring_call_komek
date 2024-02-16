$(document).ready(function(){
     $("#btn_change_password").on("click", function(){
          $.ajax({
            url : 'changeUserPassword',
            method: "POST",
            dataType: "text",
            data : {login: $("#login").val(), password: $("#password").val(),  new_password: $("#new_password").val(), "confirm_password":$("#confirm_password").val()},
            success : function(message) {
            alert(message);
            },
            error:  function(response) {
            alert("Ошибка обращения в базу данных. Повторите.");
            }
          });
     });
});