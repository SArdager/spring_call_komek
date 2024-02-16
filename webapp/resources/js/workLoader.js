$(document).ready(function(){
    $('#select_knowledge').change (function(){
        $.ajax({
            url: 'load-data/types',
            method: 'POST',
            dataType: 'json',
            data: {selectedKnowledgeId: $('#select_knowledge').val()},
            success: function(types) {
                $('#select_type').empty();
                $.each(types, function(key, type){
                    $('#select_type').append('<option value="' + type.id + '">' + type.typeName + '</option');
                });
                loadCityText();
                loadCommonText();
            },
            error:  function(response) {
                window.scrollTo({ top: 0, behavior: 'smooth' });
                $('#result_line').html("Ошибка обращения в базу данных. Перегрузите страницу.");
            }
        });
    });

    function loadCityText(){
console.log("cityValue: " + $('#select_city').val());
        if($('#select_city').val()==1){
            let body = $('#city_text_table_body');
            body.html('');
            let new_lines_html = "<tr><td>Город не был выбран.</td></tr>";
console.log("new_lines_html: " + new_lines_html);
            body.prepend(new_lines_html);
        } else {
            $.ajax({
                url: 'load-data/text',
                method: 'POST',
                dataType: 'json',
                data: {cityId: $('#select_city').val(), typeId: $('#select_type').val(),
                   language: $('input[name="language"]:checked').val(), words: $('#find_word').val()},
                success: function(article) {
                    let body = $('#city_text_table_body');
                    body.html('');
                    let new_lines_html = article.text.replace(/\n/g, '<br/>');
console.log("new_lines_html: " + new_lines_html);
                    body.prepend(new_lines_html);
                },
                error:  function(response) {
                   window.scrollTo({ top: 0, behavior: 'smooth' });
                   $('#result_line').html("Ошибка обращения в базу данных. Перегрузите страницу.");
                }
             });
        }
    }

    function loadCommonText(){
        $.ajax({
            url: 'load-data/text',
            method: 'POST',
            dataType: 'json',
            data: {cityId: 1, typeId: $('#select_type').val(),
                language: $('input[name="language"]:checked').val(), words: $('#find_word').val()},
            success: function(article) {
                let text_body = $('#text_table_body');
                text_body.html('');
                let text_lines_html = article.text.replace(/\n/g, '<br/>');
                text_body.prepend(text_lines_html);
            },
            error:  function(response) {
                window.scrollTo({ top: 0, behavior: 'smooth' });
                $('#result_line').html("Ошибка обращения в базу данных. Перегрузите страницу.");
            }
        });
    }

    $('#select_type').change ( function(){
        loadCityText();
        loadCommonText();
    });

    $('#select_city').change ( function(){
        if($('#checkbox_cabinet').is(':checked')){
            if($('#select_city').val()>1){
                loadCabinetsFromParameters();
                loadCabinetsSelect();
            } else {
                window.scrollTo({ top: 0, behavior: 'smooth' });
                $('#result_line').html("Выберите город");
            }
        } else {
            loadCityText();
        }
    });

    function loadCabinetsFromParameters(){
        $.ajax({
            url : 'load-data/choose-cabinets',
            method: "POST",
            dataType: "json",
            data : {cityId: $('#select_city').val(), covid: $('#covid').prop('checked'), children: $('#children').prop('checked'),
                       smear: $('#smear').prop('checked'), injection: $('#injection').prop('checked'), ramp: $('#ramp').prop('checked'),
                       additional: $('#additional').prop('checked'), discount: $('#discount').prop('checked'), cardPay: $('#card').prop('checked')},
            success : function(cabinets) {
                var new_lines_html ='';
                var body = $('#cabinet_table_body');
                body.html('');
                if(cabinets != null && cabinets.length>0){
                    $.each(cabinets, function(key, cabinet){
                        new_lines_html+="<tr><td width ='150'>" + cabinet.cabinetName +
                            "</td><td width='150'>" + cabinet.cabinetAddress +
                            "</td><td width='150'>" + cabinet.transport.replace(/\n/g, '<br/>') +
                            "</td><td width='150'>" + cabinet.cabinetWorkTime.replace(/\n/g, '<br/>') +
                            "</td><td width='125'>" + cabinet.cabinetCovid.replace(/\n/g, '<br/>') +
                            "</td><td width='125'>" + cabinet.cabinetPrick.replace(/\n/g, '<br/>') +
                            "</td><td width='350'>" + cabinet.cabinetDescription.replace(/\n/g, '<br/>') +
                            "</td></tr>";
                    });
                } else {
                    new_lines_html="<tr colspan='7'><td>Отсутствуют кабинеты, удовлетворяющие всем условиям отбора</td></tr>"
                }
                body.prepend(new_lines_html);
            },
            error:  function(response) {
                window.scrollTo({ top: 0, behavior: 'smooth' });
                $('#result_line').html("Ошибка обращения в базу данных. Перегрузите страницу.");
            }
        });
    }

    function loadCabinetsSelect(){
        $.ajax({
            url: 'load-data/cabinets',
            method: 'POST',
            dataType: 'json',
            data: {cityId: $('#select_city').val()},
            success: function(cabinets) {
                $('#select_cabinet').empty();
                $.each(cabinets, function(key, cabinet){
                    $('#select_cabinet').append('<option value="' + cabinet.cabinetId + '">' + cabinet.cabinetName + '</option');
                });
            },
            error:  function(response) {
                window.scrollTo({ top: 0, behavior: 'smooth' });
                $('#result_line').html("Ошибка обращения в базу данных. Перегрузите страницу.");
            }
        });
    }

    $('#btn_table').click (function(){
        let cityValue = $('#select_city').val();
        if(cityValue>1){
            loadCabinetsFromParameters();
        } else {
            window.scrollTo({ top: 0, behavior: 'smooth' });
            $('#result_line').html("Выберите город");
        }
    });

    $('#select_cabinet').change ( function(){
        let cabinetValue = $('#select_cabinet').val();
        if(cabinetValue!=null & cabinetValue>0){
            $.ajax({
                url: 'load-data/cabinet',
                method: 'POST',
                dataType: 'json',
                data: {cabinetId: cabinetValue},
                success: function(cabinet) {
                    $('#children').prop('checked', Boolean.parseBoolean(cabinet.isChildrenService));
                    $('#covid').prop('checked', Boolean.parseBoolean(cabinet.isCovidService));
                    $('#ramp').prop('checked', Boolean.parseBoolean(cabinet.isRampExist));
                    $('#injection').prop('checked', Boolean.parseBoolean(cabinet.isInjectionService));
                    $('#smear').prop('checked', Boolean.parseBoolean(cabinet.isSmearService));
                    $('#additional').prop('checked', Boolean.parseBoolean(cabinet.isAdditionalService));
                    $('#discount').prop('checked', Boolean.parseBoolean(cabinet.isDiscount));
                    $('#card').prop('checked', Boolean.parseBoolean(cabinet.isCardPay));
                    let body = $('#cabinet_table_body');
                    body.html('');
                    let line_html="<tr><td width ='150'>" + cabinet.cabinetName +
                        "</td><td width='150'>" + cabinet.cabinetAddress +
                        "</td><td width='150'>" + cabinet.transport.replace(/\n/g, '<br/>') +
                        "</td><td width='150'>" + cabinet.cabinetWorkTime.replace(/\n/g, '<br/>') +
                        "</td><td width='125'>" + cabinet.cabinetCovid.replace(/\n/g, '<br/>') +
                        "</td><td width='125'>" + cabinet.cabinetPrick.replace(/\n/g, '<br/>') +
                        "</td><td width='350'>" + cabinet.cabinetDescription.replace(/\n/g, '<br/>') +
                        "</td></tr>";
                    body.prepend(line_html);
               },
               error:  function(response) {
                    window.scrollTo({ top: 0, behavior: 'smooth' });
                    $('#result_line').html("Ошибка обращения в базу данных. Перегрузите страницу.");
               }
           });
        }
    });

    let findText = document.getElementById("find_text");
    find_text.oninput = function(){
        let textValue = $('#find_text').val().trim().toUpperCase();
        if(textValue.length>3){
            $('#cabinet_table_body tr').each(function(index){
                var trValue = $($(this)).text().toUpperCase();
                var htmlValue = $(this).html().toUpperCase();
                if(trValue.indexOf(textValue)<0){
                    $(this).closest("tr").remove();
                }
            });
        }
        if(textValue.length==0){
            $('#btn_table').click();
        }
    };

    $('#btn_find').click (function(){
        $('#show_result').css("display", "block");
        $('#result_field_line').text("Свернуть поле результатов поиска");
        $.ajax({
            url : 'load-data/find-text',
            method: "POST",
            dataType: "json",
            data : {text: $('#find_word').val().trim()},
            success : function(articles) {
                var new_lines_html ='';
                var body = $('#article_table_body');
                body.html('');
                    $.each(articles, function(key, article){
                        new_lines_html+="<tr><td width='150'>" + article.knowledgeName +
                            "</td><td width='150'>" + article.typeName +
                            "</td><td width='100'>" + article.cityName +
                            "</td><td width='800'>" + article.text.replace(/\n/g, '<br/>') +
                            "</td></tr>";
                    });
                body.prepend(new_lines_html);
            },
            error:  function(response) {
            alert("Ошибка обращения в базу данных. Повторите.");
            }
        });

    });

});

