        var show_registration = document.getElementById("show_registration");
        var registration_form = document.getElementById("registration_form");
        var show_reset = document.getElementById("show_reset");
        var reset_form = document.getElementById("reset_form");
        var show_right = document.getElementById("show_right");
        var right_form = document.getElementById("right_form");
        var show_delete = document.getElementById("show_delete");
        var delete_form = document.getElementById("delete_form");
        var show_search = document.getElementById("show_search");
        var search_form = document.getElementById("search_form");
        var number = 1;
        var number_reset = 1;
        var number_right = 1;
        var number_delete = 1;
        var number_search = 1;

        show_registration.onclick = function(){
            number++;
            if(number%2==0){
                registration_form.style.display = "block";
                show_registration.textContent = "Скрыть форму регистрации пользователя";
                show_registration.style.color = "aqua";
            } else {
                registration_form.style.display = "none";
                show_registration.textContent = "Развернуть форму регистрации пользователя";
                show_registration.style.color = "chocolate";
            }
        };


        show_reset.onclick = function(){
            number_reset++;
            if(number_reset%2==0){
                reset_form.style.display = "block";
                show_reset.textContent = "Скрыть форму сброса пароля пользователя";
                show_reset.style.color = "aqua";
            } else {
                reset_form.style.display = "none";
                show_reset.textContent = "Развернуть форму сброса пароля пользователя";
                show_reset.style.color = "chocolate";
            }
        };

         show_right.onclick = function(){
             number_right++;
             if(number_right%2==0){
                 right_form.style.display = "block";
                 show_right.textContent = "Скрыть форму изменения прав пользователя";
                 show_right.style.color = "aqua";
             } else {
                 right_form.style.display = "none";
                 show_right.textContent = "Развернуть форму изменения прав пользователя";
                 show_right.style.color = "chocolate";
             }
         };

         show_delete.onclick = function(){
             number_delete++;
             if(number_delete%2==0){
                 delete_form.style.display = "block";
                 show_delete.textContent = "Скрыть форму удаления пользователя";
                 show_delete.style.color = "aqua";
             } else {
                 delete_form.style.display = "none";
                 show_delete.textContent = "Развернуть форму удаления пользователя";
                 show_delete.style.color = "chocolate";
             }
         };
         show_search.onclick = function(){
             number_search++;
             if(number_search%2==0){
                 search_form.style.display = "block";
                 show_search.textContent = "Скрыть форму поиска логина пользователя";
                 show_search.style.color = "aqua";
             } else {
                 search_form.style.display = "none";
                 show_search.textContent = "Развернуть форму поиска логина пользователя";
                 show_search.style.color = "chocolate";
             }
         };
