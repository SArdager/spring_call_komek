$(document).ready(function(){
    $('#btn_create_table').on('click', function(){
        var col = $('#cols_number').val();
        var row = $('#rows_number').val();
        var body = document.getElementById("create_table");
        while(body.firstChild) {
            body.firstChild.remove();
            }
        var tbl;
        tbl = createTable(col, row, null, null, null);

        body.appendChild(tbl);
    });

    $('#btn_clean').on('click', function(){
        var body = document.getElementById("create_table");
        while(body.firstChild) {
            body.firstChild.remove();
            }
        $('#cols_number').val(1);
        $('#rows_number').val(1);
        var tbl;
        tbl = createTable(1, 1, null, null, null);
        body.appendChild(tbl);
    });

    $('#btn_add_row').on('click', function(){
        let col = $('#cols_number').val();
        let row = $('#rows_number').val();
        let add_row = $('#add_number').val();
        let add_col = null;
        var body = document.getElementById("create_table");
        var tbl = body.firstChild;
        let text = readText(col, row);
        tbl.remove();
        row++;
        tbl = createTable(col, row, text, add_col, add_row);
        body.appendChild(tbl);
        $('#rows_number').val(row);
    });

    $('#btn_add_col').on('click', function(){
        let col = $('#cols_number').val();
        let row = $('#rows_number').val();
        let add_col = $('#add_number').val();
        let add_row = null;
        var body = document.getElementById("create_table");
        var tbl = body.firstChild;
        let text = readText(col, row);
        tbl.remove();
        col++;
        tbl = createTable(col, row, text, add_col, add_row);
        body.appendChild(tbl);
        $('#cols_number').val(col);
    });

    $('#btn_del_row').on('click', function(){
        confirm("Данные, содержащиеся в удаляемой строке, будут утеряны !!!\n\nПродолжить операцию удаления?");
        let col = $('#cols_number').val();
        let row = $('#rows_number').val();
        let del_row = $('#add_number').val();
        let del_col = null;
        if(del_row==0 ){
            alert("Укажите номер удаляемой строки!!!")
        } else {
            var body = document.getElementById("create_table");
            var tbl = body.firstChild;
            if(del_row>row){
                del_row = row;
            }
            del_row--;
            let text = substringText(col, row, del_col, del_row);
            tbl.remove();
            row--;
            tbl = createTable(col, row, text, null, null);
            body.appendChild(tbl);
            $('#rows_number').val(row);
        }
    });

    $('#btn_del_col').on('click', function(){
        confirm("Данные, содержащиеся в удаляемой колонке, будут утеряны !!!\n\nПродолжить операцию удаления?");
        let col = $('#cols_number').val();
        let row = $('#rows_number').val();
        let del_col = $('#add_number').val();
        let del_row = null;
        if(del_col==0 ){
            alert("Укажите номер удаляемой строки!!!")
        } else {
            var body = document.getElementById("create_table");
            var tbl = body.firstChild;
            if(del_col>col){
                del_col = col;
            }
            del_col--;
            let text = substringText(col, row, del_col, del_row);
            tbl.remove();
            col--;
            tbl = createTable(col, row, text, null, null);
            body.appendChild(tbl);
            $('#cols_number').val(col);
        }
    });


    function createTable(col, row, text, add_col, add_row){
        tbl = document.createElement('table');
        tbl.style.width  = '100%';
        let begin = 0;
        if(add_col!=null){
            if(add_col==0 || add_col>=col){
              add_col = col-1;
            } else {
              add_col--;
            }
        }
        if(add_row!=null){
            if(add_row==0 || add_row>=row){
                add_row = row-1;
            } else {
                add_row--;
            }
        }
        for(var i = 0; i < row; i++){
            var tr = tbl.insertRow();
            let el, td, start, end;
            for(var j = 0; j < col; j++){
                td = tr.insertCell();
                el = document.createElement('textarea');
                if(text!=null && j!=add_col && i!=add_row){
                    start = text.indexOf("<td>", begin);
                    end = text.indexOf("</td>", start);
                    let cellText = text.substring(start+4, end);
                    el.value = cellText;
                    begin = end;
                }
                el.setAttribute('type', 'text');
                el.setAttribute('id', `textarea-${i}-${j}`);
                el.setAttribute('contenteditable', true);
                if(row==1){
                    el.setAttribute('style', 'height: 400px;');
                }
                td.appendChild(el);
            }
        }
        return tbl;
    }

    function readText(col, row){
        let t_body = document.getElementById("create_table");
        let t_tbl = t_body.firstChild;
        text = "";
        for(var r = 0; r < row; r++){
            text = text + "<tr>";
            let tr = t_tbl.rows[r];
            for(var c = 0; c < col; c++){
                let td = tr.getElementsByTagName('textarea');
                let cellText = "<td>" + td[c].value + "</td>";
                text = text + cellText;
           }
            text = text + "</tr>";
        }
        return text;
    }

    function substringText(col, row, del_col, del_row){
        let t_body = document.getElementById("create_table");
        let t_tbl = t_body.firstChild;
        text = "";
        for(var r = 0; r < row; r++){
            if(r!=del_row){
                text = text + "<tr>";
                let tr = t_tbl.rows[r];
                for(var c = 0; c < col; c++){
                    if(c!=del_col){
                        let td = tr.getElementsByTagName('textarea');
                        let cellText = "<td>" + td[c].value + "</td>";
                        text = text + cellText;
                    }
                }
                text = text + "</tr>";
            }
        }
        return text;
    }

});
