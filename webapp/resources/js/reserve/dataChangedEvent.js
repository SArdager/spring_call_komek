$(document).ready(function(){
    $('#select_city').change ( function(){
        if($('#checkbox_cabinet').is(':checked')){
            var cityValue = $('#select_city').val();
            if(cityValue>1){
                $.ajax({
                    url: 'cabinetSelectServlet',
                    method: 'POST',
                    dataType: 'json',
                    data: {cityId: cityValue},
                    success: function(cabinets) {
                        $('#select_cabinet').empty();
                        if(cabinets!=null & cabinets.length>0){
                            $.each(cabinets, function(key, cabinet){
                                $('#select_cabinet').append('<option value="' + cabinet.cabinetId + '">' + cabinet.cabinetName + '</option');
                            });

                            $('#cabinet_name').val("");
                            $('#cabinet_address').val("");
                            $('#cabinet_work_time').val("");
                            $('#cabinet_transport').val("");
                            $('#cabinet_prick').val("");
                            $('#cabinet_covid').val("");
                            $('#cabinet_description').val("");
                            $('#children').prop('checked', false);
                            $('#covid').prop('checked', false);
                            $('#ramp').prop('checked', false);
                            $('#smear').prop('checked', false);
                            $('#injection').prop('checked', false);
                            $('#additional').prop('checked', false);
                            $('#discount').prop('checked', false);
                            $('#card').prop('checked', false);
                        } else {
                        alert("Процедурный кабинет в базе отсутствует. Необходимо создать новый кабинет.");
                        }
                    },
                    error:  function(response) {
                        alert("Ошибка запроса в базу знаний. Повторите выбор параметров запроса.");
                    }
                });
            } else {
                alert("Необходимо выбрать город.");
            }
        } else {
            var cityValue = $('#select_city').val();
            var knowledgeValue = $('#select_knowledge').val();
            var typeValue = $('#select_type').val();
            var languageValue = $('input[name="language"]:checked').val();
            var findValue = "";

            var data = {city_id: cityValue, knowledge_id: knowledgeValue, type_id: typeValue,
                language: languageValue, words: findValue};
            $.ajax({
                url: 'changeTextServlet',
                method: 'POST',
                dataType: 'json',
                data: data,
                success: function(article) {
                    var body = document.getElementById("create_table");
                    body.innerHTML = '';
                    $('#text_id').val(article.articleId);
                    var text = article.text;
                    var col;
                    var row;
                    if(article.articleId>0){
                        col = article.col;
                        row = article.row;
                    } else {
                        col = 1;
                        row = 1;
                    }
                    $('#cols_number').val(col);
                    $('#rows_number').val(row);
                    var tbl  = document.createElement('table');
                    tbl.style.width  = '100%';
                    var begin = 0;
                    for(var i = 0; i < row; i++){
                        var tr = tbl.insertRow();
                        let el, td;
                        let start;
                        let end;
                        for(var j = 0; j < col; j++){
                            td = tr.insertCell();
                            el = document.createElement('textarea');
                            start = text.indexOf("<td>", begin);
                            end = text.indexOf("</td>", start);
                            let cellText = text.substring(start+4, end);
                            el.value = cellText;
                            el.setAttribute('type', 'text');
                            el.setAttribute('id', `textarea-${i}-${j}`);
                            el.setAttribute('contenteditable', true);
                            if(row==1){
                                el.setAttribute('style', 'height: 400px;');
                            }
                            td.appendChild(el);
                            begin = end;
                        }
                    }
                    body.appendChild(tbl);
                    if(row>1){
                        let t_areas = body.querySelectorAll('textarea');
                            for(let t_area of t_areas){
                                t_area.style.height = 'auto';
                                t_area.style.height = t_area.scrollHeight + "px";
                        }
                    }
                },
                error:  function(response) {
                    alert("Ошибка запроса в базу знаний. Повторите выбор параметров запроса.");
                }
            });
        }
    });
});
