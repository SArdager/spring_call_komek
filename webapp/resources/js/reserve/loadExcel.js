$(document).ready(function(){
    $('#btn_excel').on ('click', function(){
       console.log("Click btn_excel");
       var fileNameValue = $('#excel_file').val();
       var startValue = $('#excel_start').val();
       var endValue = $('#excel_end').val();

            $.ajax({
                url: 'readExcelServlet',
                method: "POST",
                data: {excelPath: fileNameValue, start: startValue, end: endValue},
                dataType: "text",
                success: function(message){
                    alert(message);
                    $('#select_city').trigger("change");
                },
                error: function(response){
                    console.log(response.getClass.toString);
                    alert(response);
                }
            });
    });
});
