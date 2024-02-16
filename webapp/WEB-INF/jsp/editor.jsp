<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Knowledge Base Editor</title>
    <link rel="stylesheet" type="text/css" href="resources/css/edit.css">
    <script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="resources/js/editorTypesLoader.js"></script>
    <script type="text/javascript" src="resources/js/textLoader.js"></script>
    <script type="text/javascript" src="resources/js/dataChangedEvent.js"></script>
    <script type="text/javascript" src="resources/js/cabinetLoader.js"></script>
    <script type="text/javascript" src="resources/js/cabinetEditor.js"></script>
    <script type="text/javascript" src="resources/js/cabinetCreator.js"></script>
    <script type="text/javascript" src="resources/js/cabinetDelete.js"></script>
    <script type="text/javascript" src="resources/js/loadExcel.js"></script>
    <script type="text/javascript" src="resources/js/articleField.js"></script>
</head>

<body>
    <section>
    <div class="container">
        <div class="user_title">
            <span id="user_title_name"></span>
            <a href="logout">Выйти</a>
        </div>
        <div class="user_title">
            <sec:authorize access="hasRole('ADMIN')">
                <a href="admin">Администрирование</a>
            </sec:authorize>
        </div>
        <hr>
        <h1>РЕДАКТИРОВАНИЕ БАЗЫ ДАННЫХ "КОМЕК"</h1>
        <hr>
        <a href="worker">Вернуться</a>
        <br>
         <div style="display: flex; flex-direction: row; justify-content: right; margin-right: 30px; color: maroon; text-decoration: underline;" >
            <input type="checkbox" id="checkbox_cabinet" />Процедурный кабинет</div>
         <form >
            <div class="location_city">Выберите  город</div>
            <select id="select_city" name="city_id" style="width: 100%;">
                <c:forEach var="city" items="${cities}">
                <option value=${city.cityId}>${city.cityName}</option>
                </c:forEach>
            </select>
            <div class ="title_field" id="line_city">Изменить или добавить новое название</div>
                <div class = "data_field" id="data_city">
                    <div>Поле ввода нового названия:</div>
                    <input class="cabinet_field" type="text" name="city_name" maxlength="45"/>
                    <div class="row_btn">
                        <input formaction="addCityServlet" formmethod="post" type="submit" value="Дополнить" />
                        <input formaction="changeCityNameServlet" formmethod="post" type="submit" value="Изменить название" />
                        <input formaction="deleteCityServlet" formmethod="post" type="submit" value="Удалить" onclick="return confirmDelete(this);" />
                    </div>
                </div>
            <br>

          <div class="knowledge_part" id="show_knowledge">
            <div class="choose_knowledge">Выберите область знаний</div>
            <select id="select_knowledge" name="knowledge_id" style="width: 100%;">
                <c:forEach var="knowledge" items="${knowledgeList}">
                <option value=${knowledge.knowledgeId}>${knowledge.knowledgeName}</option>
                </c:forEach>
            </select>
            <div class = "title_field" id="line_knowledge">Изменить или добавить новое название</div>
                <div class = "data_field" id="data_knowledge">
                    <div>Поле ввода нового названия:</div>
                    <input class="cabinet_field" type="text" name="knowledge_name" maxlength="120" />
                    <div class="row_btn">
                        <input formaction="addKnowledgeServlet" formmethod="post" type="submit" value="Дополнить" />
                        <input formaction="changeKnowledgeServlet" formmethod="post" type="submit" value="Изменить название" />
                        <input formaction="deleteKnowledgeServlet" formmethod="post" type="submit" value="Удалить" onclick="return confirmDelete(this);" />
                    </div>
                </div>
            <br>

            <div class="select_knowledge_type">Выберите категорию знаний</div>
            <select id="select_type" name="type_id" style="width: 100%;">
            </select>
            <div class = "title_field" id="line_type">Изменить или добавить новое название</div>
                <div class = "data_field" id="data_type">
                    <div>Поле ввода нового названия:</div>
                    <input class="cabinet_field" type="text" name="type_name" maxlength="120" />
                    <div class="row_btn" id="row_type">
                        <input formaction="addTypeServlet" formmethod="post" type="submit" value="Дополнить" />
                        <input formaction="changeTypeServlet" formmethod="post" type="submit" value="Изменить название" />
                        <input formaction="deleteTypeServlet" formmethod="post" type="submit" value="Удалить" onclick="return confirmDelete(this);" />
                    </div>
                </div>
            <br>
          </div>
         </form>
         <script>
         function confirmDelete(f) {
             return confirm("ВНИМАНИЕ!!!\nИз базы данных будут удалены все записи, связанные с данным элементом.\n" +
             "ЭТА ОПЕРАЦИЯ НЕ ВОССТАНОВИМА!!!\n\nПродолжить операцию удаления?");
         }
         </script>
      </div>
    </section>

    <section>
    <div class="container">
        <div class="knowledge_part" id="show_data">
            <div class="row_info">
                <div style="margin-right: 40px;">Выберите язык:</div>
                <div class="radio_line" style="font-size: 1.0em;">
                    <input type="radio" name="language" value="kz" />-казахский
                    <input type="radio" name="language" value="ru" checked="checked" />-русский
                </div>
            </div>
            <div class="row_info">
                <button id ="btn_clean" >Очистить поле</button>
                <button id="btn_create_table">Создать новое поле</button>
                <label for="cols_number">кол-во колонок</label>
                <input type="number" id="cols_number" size = "3" value="1" style="margin-right: 40px;"/>
                <label for="rows_number">кол-во строк</label>
                <input type="number" id="rows_number" size = "3" value="1"/>
            </div>
            <p><div class="row_btn">
                <button id ="btn_add_row" >Вставить строку</button>
                <button id ="btn_add_col" >Вставить колонку</button>
                <button id ="btn_del_row" >Удалить строку</button>
                <button id ="btn_del_col" >Удалить колонку</button>
                <label for="add_number" >номер колонки(строки)</label>
                <input type="number" id="add_number" size = "3" value="0"/>
            </div></p>
            <div class="row_btn">
                <button id="btn_bold_article" >Полужирный</button>
                <button id="btn_cur_article" >Курсив</button>
                <button id="btn_under_article" >Подчеркнуть</button>
                <button id="btn_color_article" >Выделить цветом</button>
                <button id ="btn_text" style="background-color: wheat; font-weight:bold">Внести в базу</button>
            </div>
            <hr>
            <input type="hidden" id="text_id" value=${article.articleId} />
            <div class = "table_body" id="create_table">
              <table id="table">
                <tbody id = "text_table_body">
                </tbody>
              </table>
            </div>
        </div>
        <div id="show_cabinet" style="display: none;">
            <div>Выберите процедурный кабинет</div>
            <select id="select_cabinet" name="cabinetId" style="width: 100%;">
            </select>
            <p><div class="row_btn">
                <button id="btn_load_cabinet" >Отобразить</button>
                <button id="btn_edit_cabinet" >Изменить информацию</button>
                <button id="btn_new_cabinet" >Создать новый кабинет</button>
                <button id="btn_delete_cabinet" >Удалить кабинет</button>
                <button id="btn_clean_cabinet" >Очистить поля формы</button>
            </div></p><hr>

            <div class="title_field" id="line_excel" >Загрузить данные по кабинетам из Excel-файла</div>
            <div id="show_excel" style="display: none;">
              <form class="form_column" id="excel_form">
                 <div class="main_block" style="float: left;">
                 <div class="main_block" style="float: left;">
                    <div class="field">
                        <label for="excel_file">Укажите место расположения и название файла</label>
                        <input type="text" id="excel_file" size="40" required/>
                    </div>
                    <div class="field">
                        <label for="excel_start">Первая строка загрузки файла</label>
                        <input type="text" id="excel_start" size="5" required/>
                    </div>
                    <div class="field">
                        <label for="excel_end">Последняя  строка  загрузки </label>
                        <input type="text" id="excel_end" size="5" required/>
                    </div>
                 </div>
                 </div>
              </form>
              <div class="row_btn">
                  <div class="title_field" id="line_hint" >Подробнее про Excel-файл</div>
                  <button id="btn_excel" >Загрузить Excel-файл</button>
              </div>
                <div id="show_hint" style="display: none;">
                  <p style="color:maroon; font-size:0.8em" >Для копирования данных процедурного кабинета создайте Excel-файл.<br>
                  Путь файла с расширением и его расположение требуется указать с поле выбора загрузки в следующем формате: <u><i>D:/ExchangeFolder/Кабинеты.xlsx</i></u><br>
                  На первой странице (листе) файла должны содержаться колонки с данными в следующем порядке:<br>
                  1 - Город; 2 - Описание; 3 - График; 4 - Covid-19.<br> Остальные колонки не копируются, за исключением,
                  если ячейка Covid-19 будет пустой, будет скопированы значения из следующей колонки.<br>
                  Первая строка  не копируется и оставлена под размещение названий колонок).</p>
                </div>
            </div>
            <hr>
            <div><b>Отметьте параметры отбора:</b></div>
            <div class="page_column">
              <div style="display: table-row;">
                <div class="radio_column_firth">
                    <input type="checkbox" id="covid" name="isCovid"/><span>сovid</span><br>
                    <input type="checkbox" id="children" name="isChildren"/><span>дети</span><br>
                    <input type="checkbox" id="ramp" name="isRamp"/><span>пандус</span>
                </div>
                <div class="radio_column_second">
                    <input type="checkbox" id="discount" name="isDiscount"/><span>скидки</span><br>
                    <input type="checkbox" id="smear" name="isSmear"/><span>мазки</span><br>
                    <input type="checkbox" id="injection" name="isInjection"/><span>инъекции</span>
                </div>
                <div class="radio_column_third">
                    <input type="checkbox" id="card" name="isCardPay"/><span>оплата картой</span><br>
                    <input type="checkbox" id="additional" name="isAdditional"/><span>доп. услуги</span><br>
                </div>
              </div>
            </div>
            <div><b>Характеристики процедурного кабинета:</b></div>
            <div class="row_btn">
                <span style="margin-right: 40px;">Название</span>
                <button id="btn_bold" >Полужирный</button>
                <button id="btn_cur" >Курсив</button>
                <button id="btn_under" >Подчеркнуть</button>
                <button id="btn_color" >Выделить цветом</button>
            </div>
            <div>
                <input class="cabinet_field" type="text" id="cabinet_name" name="cabinetName" maxlength="120" >${cabinet.cabinetName}</input>
            </div>
            <div>Адрес</div>
            <div>
                <input class="cabinet_field" type="text" id="cabinet_address" name="cabinetAddress" maxlength="220">${cabinet.cabinetAddress}</input>
            </div>
            <div class="transport_col">
                <div>Ориентир</div>
                <textarea id= "cabinet_transport" name="transport" rows="2" maxlength="490">${cabinet.transport}</textarea>
            </div>
            <div class="time_col">
                <div>Время</div>
                <textarea id= "cabinet_work_time" name="cabinetWorkTime" rows="2" maxlength="490">${cabinet.cabinetWorkTime}</textarea>
            </div>
            <div class="covid_col">
                <div>Covid</div>
                <textarea id= "cabinet_covid" name="cabinetCovid" rows="1" maxlength="490">${cabinet.cabinetCovid}</textarea>
            </div>
            <div class="prick_col">
                <div>Инъекции</div>
                <textarea id= "cabinet_prick" name="cabinetPrick" rows="1" maxlength="230">${cabinet.cabinetPrick}</textarea>
            </div>
            <div>Описание</div>
            <textarea id= "cabinet_description" name="cabinetDescription" rows="6" maxlength="950">${cabinet.cabinetDescription}</textarea>
        </div>
    </div>


    </section>

    <script type="text/javascript" src="js/hiddenElement.js"></script>

    <script>
        $(document).ready(function(){
            $("h1").css("color", "red");
            let name = "${user.userFirstname}";
            document.getElementById("user_title_name").textContent = name.substring(0, 1) + ". ${user.userSurname}";
        });
    </script>

    <script>
        $(document).ready(function(){
            $('#select_knowledge').trigger ("change");

        });
    </script>

    <script>
        $(document).ready(function(){
            $('#select_type').on('change', function(){
                $('#select_city').trigger ("change");
            });
        });
    </script>

    <script>
        $(document).ready(function(){
            $('input:radio[name="language"]').on('change', function(){
            $('#select_city').trigger ("change");
            });
        });
    </script>

    <script>
        var line_admin = document.getElementById("line_admin");
        var line_return = document.getElementById("line_return");

        line_admin.onclick = function(){
            $('#admin').submit();
        };
        line_return.onclick = function(){
            $('#return').submit();
        };
    </script>

    <script>
        $(document).ready(function(){
            var show_knowledge = document.getElementById("show_knowledge");
            var show_cabinet = document.getElementById("show_cabinet");
            var show_data = document.getElementById("show_data");

            $('#checkbox_cabinet').on('click', function(){
                if($(this).is(':checked')){
                    show_knowledge.style.display = "none";
                    show_cabinet.style.display = "block";
                    show_data.style.display = "none";
                } else {
                    show_knowledge.style.display = "block";
                    show_cabinet.style.display = "none";
                    show_data.style.display = "block";
                }
                $('#select_city').trigger ("change");
            });
        });
    </script>
    <script>
        $(document).ready(function(){
            $('#btn_load_cabinet').on('click', function(){
                $('#select_cabinet').trigger ("change");
            });

            $('#btn_clean_cabinet').on('click', function(){
                $('#cabinet_name').val("");
                $('#cabinet_address').val("");
                $('#cabinet_work_time').val("");
                $('#cabinet_transport').val("");
                $('#cabinet_prick').val("");
                $('#cabinet_covid').val("");
                $('#cabinet_description').val("");
                $('#children').prop('checked', false);
                $('#covid').prop('checked', false);
                $('#ramp').prop('checked', false);
                $('#smear').prop('checked', false);
                $('#injection').prop('checked', false);
                $('#additional').prop('checked', false);
                $('#discount').prop('checked', false);
                $('#card').prop('checked', false);
            });
        });
    </script>
    <script>
        $(document).ready(function(){
            document.onselectionchange = function() {
                let node = document.getSelection().anchorNode;
                let tagName = node.tagName;
                if(tagName == "TEXTAREA"){
                    this.oninput = function(){
                    node.style.height = 'auto';
                    node.style.height = node.scrollHeight + 'px';
                    };
                }
                if(tagName == "INPUT" || tagName == "TEXTAREA"){
                    this.onselect = function(){
                        $('#btn_bold').on('click', function(){
                            console.log("btn_bold was clicked");
                            accentSelection("b");
                        });
                        $('#btn_color').on('click', function(){
                            accentSelection("span");
                        });
                        $('#btn_cur').on('click', function(){
                            accentSelection("i");
                        });
                        $('#btn_under').on('click', function(){
                            accentSelection("u");
                        });

                        $('#btn_bold_article').on('click', function(){
                            accentSelection("b");
                        });
                        $('#btn_color_article').on('click', function(){
                            accentSelection("span");
                        });
                        $('#btn_cur_article').on('click', function(){
                            accentSelection("i");
                        });
                        $('#btn_under_article').on('click', function(){
                            accentSelection("u");
                        });

                        function accentSelection(accent){
                            let selStart = node.selectionStart;
                            let selEnd = node.selectionEnd;
                            if(selStart==selEnd){
                                return;
                            }
                            let selected = node.value.slice(selStart, selEnd);
                            node.setRangeText("<" + accent + ">" + selected + "</" + accent + ">", selStart, selEnd, "end");
                        };
                    };

                }
            };
        });
    </script>
    <div class="buffer" style = "height: 5em;">
    </div>
    </body>
</html>

