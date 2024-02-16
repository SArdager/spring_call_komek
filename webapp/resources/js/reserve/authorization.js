$(document).ready(function(){
     $("#btn_auth").on("click", function(){
        $.ajax({
            url: 'authorization',
            method: "POST",
            dataType: "json",
            data: {login: $('#login').val(), password: $('#password').val()},
            success: function(userId) {
                if(userId>0){
                    $('#user_auth').submit();
                } else if(userId==0) {
                    alert("Введен неправильный пароль пользователя.");
                } else if(userId==-7){
                    alert("Введен неправильный пароль администратора.");
                } else if(userId==-21){
                    $('#user_setup').submit();
                } else {
                    alert("Введенный логин отсутствует в базе данных.");
                }
            },
            error:  function(response) {
                alert("Ошибка запроса. Проверьте интернет и повторите запрос.");
            }
        });
    });
});
