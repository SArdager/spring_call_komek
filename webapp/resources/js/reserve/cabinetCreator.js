$(document).ready(function(){

    $('#btn_new_cabinet').on ('click', function(){
       var cityValue = $('#select_city').val();
       var covid = $('#covid').prop('checked');
       var children = $('#children').prop('checked');
       var ramp = $('#ramp').prop('checked');
       var smear = $('#smear').prop('checked');
       var injection = $('#injection').prop('checked');
       var additional = $('#additional').prop('checked');
       var discount = $('#discount').prop('checked');
       var card_pay = $('#card').prop('checked');
       var name = $('#cabinet_name').val();
       var address = $('#cabinet_address').val();
       var work_time = $('#cabinet_work_time').val();
       var trans = $('#cabinet_transport').val();
       var prick = $('#cabinet_prick').val();
       var covidValue = $('#cabinet_covid').val();
       var description = $('#cabinet_description').val();

       if(cityValue>1){
         var data = {cityId: cityValue, isCovid: covid, isChildren: children, isRamp: ramp,
            isSmear: smear, isInjection: injection, isAdditional: additional, isDiscount: discount,
            isCardPay: card_pay, cabinetName: name, cabinetAddress: address, cabinetWorkTime: work_time,
            cabinetPrick: prick, cabinetCovid: covidValue, transport: trans, cabinetDescription: description};

         $.ajax({
           url: 'createCabinetServlet',
           method: 'POST',
           dataType: 'text',
           data: data,
           success: function(message) {
                alert(message);
                $('#select_city').trigger ("change");
            },
            error:  function(response) {
                alert("Ошибка обращения в базу данных. Повторите.");
            }
         });
       } else {
            alert("Выберите город!!!");
       }
    });

});
