$(document).ready(function(){

    $('#checkbox_cabinet').on('click', function(){
        if($(this).is(':checked')){
            $('#show_knowledge').css("display", "none");
            $('#show_language').css("display", "none");
            $('#show_cabinet').css("display", "block");
            $('#show_data').css("display", "none");
            $('#show_table').css("display", "block");
            $('#show_select').css("display", "block");
            $('#select_city').trigger ("change");
        } else {
            $('#show_knowledge').css("display", "block");
            $('#show_language').css("display", "block");
            $('#show_cabinet').css("display", "none");
            $('#show_data').css("display", "block");
            $('#show_table').css("display", "none");
            $('#show_select').css("display", "none");
            $('#select_city').trigger ("change");
        }
    });

    $('#city_field_line').click(function(){
        if($('#article_city').attr("hidden")){
            $('#article_city').attr("hidden", false);
            $('#city_field_line').text("Свернуть поле");
        } else {
            $('#article_city').attr("hidden", true);
            $('#city_field_line').text("Развернуть поле");
        }
    });

    $('#result_field_line').click(function(){
        if($('#show_result').css("display") == "none"){
            $('#show_result').css("display", "block");
            $('#result_field_line').text("Свернуть поле результатов поиска");
        } else {
            $('#show_result').css("display", "none");
            $('#result_field_line').text("Развернуть поле результатов поиска");
        }
    });

    $('#btn_clean_result').click (function(){
        $('#find_word').val("");
        $('#show_result').css("display", "none");
        $('#result_field_line').text("Развернуть поле результатов поиска");
        $('#article_table_body').empty();
    });

    $('input:radio[name="language"]').change ( function(){
        $('#select_city').trigger ("change");
    });
    $('#select_type').change ( function(){
        $('#select_city').trigger ("change");
    });


});
