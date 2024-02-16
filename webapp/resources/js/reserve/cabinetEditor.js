$(document).ready(function(){

    $('#btn_edit_cabinet').on ('click', function(){
        var cabinetValue = $('#select_cabinet').val();
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
        var description = $('#cabinet_description').val();
        var prick = $('#cabinet_prick').val();
        var covidValue = $('#cabinet_covid').val();

        if(cabinetValue!=null && cabinetValue>0){
            var data = {cabinetId: cabinetValue, isCovid: covid, isChildren: children, isRamp: ramp,
                isSmear: smear, isInjection: injection, isAdditional: additional, isDiscount: discount,
                isCardPay: card_pay, cabinetName: name, cabinetAddress: address, cabinetWorkTime: work_time,
                transport: trans, cabinetDescription: description, cabinetPrick : prick, cabinetCovid: covidValue};

            $.ajax({
               url: 'changeCabinetServlet',
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
            alert("Необходимо создать кабинет.");
        }
    });
});
