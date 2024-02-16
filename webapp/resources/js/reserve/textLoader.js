$(document).ready(function(){
    $('#btn_text').click ( function(){
        var col = $('#cols_number').val();
        var row = $('#rows_number').val();
        var text = "";
        var cellText = "";
        var body = document.getElementById("create_table");
        var tbl = body.firstChild;
        for(var i = 0; i < row; i++){
            text = text + "<tr>";
            var tr = tbl.rows[i];
            for(var j = 0; j < col; j++){
                var td = tr.getElementsByTagName('textarea');
                cellText = "<td>" + td[j].value + "</td>";
                text = text + cellText;
           }
            text = text + "</tr>";
        }

        var data = {city_id: $('#select_city').val(), knowledge_id: $('#select_knowledge').val(),
            type_id: $('#select_type').val(), article_text: text,
            article_id: $('#text_id').val(), language: $('input[name="language"]:checked').val(),
            col: $('#cols_number').val(), row: $('#rows_number').val()};

        if($('#select_knowledge').val()>0 && $('#select_type').val()>0) {
            $.ajax({
                url : 'editTextServlet',
                method: "POST",
                dataType: "html",
                data : data,
                success : function(message) {
                    alert(message);
                }
            });
        } else {
            alert("Для записи нового текста необходимо указать область и категорию знаний.");
        }
    });

});
