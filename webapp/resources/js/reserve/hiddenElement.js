        var line_city = document.getElementById("line_city");
        var line_excel = document.getElementById("line_excel");
        var data_city = document.getElementById("data_city");
        var line_knowledge = document.getElementById("line_knowledge");
        var data_knowledge = document.getElementById("data_knowledge");
        var line_type = document.getElementById("line_type");
        var data_type = document.getElementById("data_type");
        var show_excel = document.getElementById("show_excel");
        var line_hint = document.getElementById("line_hint");
        var show_hint = document.getElementById("show_hint");
        var number_city = 1;
        var number_knowledge = 1;
        var number_type = 1;
        var number_excel = 1;
        var number_hint = 1;

        line_city.onclick = function(){
            number_city++;
            if(number_city%2==0){
                data_city.style.display = "block";
                line_city.textContent = "Убрать поле редактирования";
                line_city.style.color = "red";
            } else {
                data_city.style.display = "none";
                line_city.textContent = "Изменить или добавить новое название";
                line_city.style.color = "chocolate";
            }
            };
        line_knowledge.onclick = function(){
            number_knowledge++;
            if(number_knowledge%2==0){
                data_knowledge.style.display = "block";
                line_knowledge.textContent = "Убрать поле редактирования";
                line_knowledge.style.color = "red";
            } else {
                data_knowledge.style.display = "none";
                line_knowledge.textContent = "Изменить или добавить новое название";
                line_knowledge.style.color = "chocolate";
            }
            };
        line_type.onclick = function(){
            number_type++;
            if(number_type%2==0){
                data_type.style.display = "block";
                line_type.textContent = "Убрать поле редактирования";
                line_type.style.color = "red";
            } else {
                data_type.style.display = "none";
                line_type.textContent = "Изменить или добавить новое название";
                line_type.style.color = "chocolate";
            }
            };
        line_excel.onclick = function(){
            number_excel++;
            if(number_excel%2==0){
                show_excel.style.display = "block";
                line_excel.textContent = "Убрать поле загрузки Excel-файла";
                line_excel.style.color = "red";
            } else {
                show_excel.style.display = "none";
                line_excel.textContent = "Загрузить данные по кабинетам из Excel-файла";
                line_excel.style.color = "chocolate";
            }
        };

        line_hint.onclick = function(){
            number_hint++;
            if(number_hint%2==0){
                show_hint.style.display = "block";
                line_hint.textContent = "Убрать подсказку";
                line_hint.style.color = "red";
            } else {
                show_hint.style.display = "none";
                line_hint.textContent = "Подробнее про Excel-файл";
                line_hint.style.color = "chocolate";
            }
        };

