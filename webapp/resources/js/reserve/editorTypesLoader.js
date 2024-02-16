$(document).ready(function(){
    $('#select_knowledge').on ('change', function(){
       var knowledgeValue = $('#select_knowledge').val();
       var data = {selectedKnowledgeId: knowledgeValue};
       $.ajax({
           url: 'knowledgeTypesServlet',
           method: 'GET',
           dataType: 'json',
           data: data,
           success: function(response) {
           $('#select_type').empty();
           $.each(response, function(key, object){
                    $('#select_type').append('<option value="' + object.typeId + '">' + object.typeName + '</option');
           });
            var cityValue = $('#select_city').val();
            var knowledgeValue = $('#select_knowledge').val();
            var typeValue = $('#select_type').val();
            var languageValue = $('input[name="language"]:checked').val();
            var findValue = "";

            var data = {city_id: cityValue, knowledge_id: knowledgeValue, type_id: typeValue, language: languageValue, words: findValue};
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
            },
            error:  function(response) {
            }
       });
    });
});
