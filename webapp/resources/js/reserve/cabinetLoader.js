$(document).ready(function(){
    $('#select_cabinet').on ('change', function(){
        var cabinetValue = $('#select_cabinet').val();
        $.ajax({
            url: 'cabinetServlet',
            method: 'GET',
            dataType: 'json',
            data: {cabinetId: cabinetValue},
            success: function(cabinet) {
                if(cabinet.isChildrenService){
                    $('#children').prop('checked', true);
                } else {
                    $('#children').prop('checked', false);
                }
                if(cabinet.isCovidService){
                    $('#covid').prop('checked', true);
                } else {
                    $('#covid').prop('checked', false);
                }
                if(cabinet.isRampExist){
                    $('#ramp').prop('checked', true);
                } else {
                    $('#ramp').prop('checked', false);
                }
                if(cabinet.isSmearService){
                    $('#smear').prop('checked', true);
                } else {
                    $('#smear').prop('checked', false);
                }
                if(cabinet.isInjectionService){
                    $('#injection').prop('checked', true);
                } else {
                    $('#injection').prop('checked', false);
                }
                if(cabinet.isAdditionalService){
                    $('#additional').prop('checked', true);
                } else {
                    $('#additional').prop('checked', false);
                }
                if(cabinet.isDiscount){
                    $('#discount').prop('checked', true);
                } else {
                    $('#discount').prop('checked', false);
                }
                if(cabinet.isCardPay){
                    $('#card').prop('checked', true);
                } else {
                    $('#card').prop('checked', false);
                }
                $('#cabinet_id').empty();
                $('#cabinet_name').empty();
                $('#cabinet_address').empty();
                $('#cabinet_transport').empty();
                $('#cabinet_work_time').empty();
                $('#cabinet_covid').empty();
                $('#cabinet_prick').empty();
                $('#cabinet_description').empty();

                $('#cabinet_id').val(cabinet.cabinetId);
                $('#cabinet_name').val(cabinet.cabinetName);
                $('#cabinet_address').val(cabinet.cabinetAddress);
                $('#cabinet_transport').val(cabinet.transport);
                $('#cabinet_work_time').val(cabinet.cabinetWorkTime);
                $('#cabinet_covid').val(cabinet.cabinetCovid);
                $('#cabinet_prick').val(cabinet.cabinetPrick);
                $('#cabinet_description').val(cabinet.cabinetDescription);

                let t_areas = $('#show_cabinet').querySelectorAll('textarea');
                    for(let t_area of t_areas){
                        t_area.style.height = 'auto';
                        t_area.style.height = t_area.scrollHeight + 'px';
                }
            },
            error:  function(response) {}
        });
    });
});
