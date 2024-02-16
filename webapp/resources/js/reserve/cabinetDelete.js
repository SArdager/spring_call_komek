$(document).ready(function(){
    $('#btn_delete_cabinet').on ('click', function(){
    console.log("Click btn_delete_cabinet");
        var cabinetValue = $('#select_cabinet').val();
        if(cabinetValue!=null && cabinetValue>0){
            if(confirm("Подтвердите удаление процедурного кабинета")){
                $.ajax({
                   url: 'deleteCabinetServlet',
                   method: 'POST',
                   dataType: 'text',
                   data: {cabinetId: cabinetValue},
                   success: function(message) {
                        alert(message);
                        $('#select_city').trigger ("change");
                    },
                    error:  function(response) {
                        alert("Ошибка обращения в базу данных. Повторите.");
                    }
                });
            }
        } else {
            alert("Кабинет не выбран.");
        }
    });
});
