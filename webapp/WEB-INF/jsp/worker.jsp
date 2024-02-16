<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Knowledge Base Work</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="resources/js/work.js"></script>
    <script type="text/javascript" src="resources/js/workLoader.js"></script>
</head>
<body>
    <section>
    <div class="container">
        <div class="user_title">
            <span id="user_title_name"></span>
            <a href="change-password">Сменить пароль</a>
            <a href="logout">Выйти</a>
        </div>
        <div class="user_title">
            <span id="editor_show_href" style="display:none;">
                <a href="editor">Редактирование записей</a>
            </span>
            <sec:authorize access="hasRole('ADMIN')">
                <a href="admin">Администрирование</a>
            </sec:authorize>
        </div>
        <hr>
        <h1>БАЗА ДАННЫХ "КОМЕК"</h1>
        <hr>
        <h3><div id="result_line"></div></h3>
      <div class="page_column">
      <div class="row_column">
        <div class="item_column">
            <div class="main_block">
                <div class="field">
                    <label style="margin-right: 30px;">Город</label>
                    <select id="select_city" >
                      <c:forEach var="city" items="${cities}">
                          <option value=${city.id}>${city.cityName}</option>
                      </c:forEach>
                    </select>
                </div>
                <div class="field" id="show_select" style="display:none;">
                    <label style="margin-right: 30px;">Выбор ПК</label>
                    <select id="select_cabinet">
                    </select>
                </div>
            </div>
            <p>
              <div class="row_info" id="show_language">
                  <div class="radio_name"><input type="radio" name="language" value="kz">-казахский</input></div>
                  <div><input type="radio" name="language" value="ru" checked="checked">-русский</input></div>
              </div>
            </p>
            <div style="color: maroon; text-decoration: underline;">
                <input type="checkbox" id="checkbox_cabinet" >Процедурный кабинет</input>
            </div>
        </div>

        <div class="item_column">
          <div class="knowledge_part" id="show_knowledge">
            <div class="main_block">
                <div class="field">
                    <label>Область знаний</label>
                    <select id="select_knowledge">
                        <c:forEach var="knowledge" items="${knowledgeList}">
                            <option value=${knowledge.id}>${knowledge.knowledgeName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="field">
                    <label>Категория знаний</label>
                    <select id="select_type">
                    </select>
                </div>
            </div>
          </div>

          <div id="show_cabinet" style="display:none;">
            <div>Параметры отбора:</div>
            <div class="page_column">
              <div class="row_column">
                <div class="radio_column_left">
                    <input type="checkbox" id="covid" /><span class="color_black">сovid</span><br>
                    <input type="checkbox" id="children" /><span class="color_black">дети</span><br>
                    <input type="checkbox" id="ramp" /><span class="color_black">пандус</span><br>
                    <input type="checkbox" id="discount" /><span class="color_black">скидки</span>
                </div>
                <div class="radio_column_right">
                    <input type="checkbox" id="smear" /><span class="color_black">мазки</span><br>
                    <input type="checkbox" id="injection" /><span class="color_black">инъекции</span><br>
                    <input type="checkbox" id="card" /><span class="color_black">оплата картой</span><br>
                    <input type="checkbox" id="additional" /><span class="color_black">доп. услуги</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      </div>
      <div class="data_field" id="show_data">
        <div class = "row_info">
            <div class="find_label">Поиск слов по базе данных</div>
            <input type="text" id="find_word" style="width: 250px; margin-right: 30px;"/>
            <button class="cabinet_btn" id="btn_find" >Показать</button>
            <button class="cabinet_btn" id="btn_clean_result" >Очистить</button>
        </div>
        <div class = "hidden_line" id="result_field_line">Развернуть поле результатов поиска</div>
        <div id="show_result" style="display:none;">
           <div class = "table_title"><strong><u>Результаты поиска</u></strong></div>
           <div class = "scroll_table">
              <table>
                <thead>
                  <tr>
                    <th width='150'>Область знаний</th>
                    <th width='150'>Категория</th>
                    <th width='100'>Город</th>
                    <th width='800'>Информация по поиску</th>
                  </tr>
                </thead>
              </table>
              <div class = "scroll_table_body">
                <table>
                 <tbody id = "article_table_body">
                 </tbody>
                </table>
              </div>
           </div>
        </div>

        <div class="row">
            <h3>Информация по выбранному городу</h3>
            <div class="hidden_line" id="city_field_line">Развернуть поле</div>
        </div>
        <div class = "table_body" id = "article_city" hidden = "true" style = "height: auto; max-height: 150px; overflow-y: auto;">
          <table>
            <tbody id = "city_text_table_body">
            </tbody>
          </table>
        </div>

        <h3>Общая информация</h3>
          <div class = "table_body">
            <table>
              <tbody id = "text_table_body">
              </tbody>
            </table>
          </div>
      </div>

      <div class="table_field" id="show_table" style="display:none;">
        <div class = "row_info">
            <button class="cabinet_btn" id="btn_table" >Показать</button>
            <div class="find_label"> Поиск текста:</div>
            <input type="text" id="find_text" style="width: 250px;"/>
        </div>
        <div class = "table_title"><strong><u>Процедурные кабинеты</u></strong></div>
        <div class = "scroll_table">
          <table>
            <thead>
              <tr>
                <th width='150'>Название</th>
                <th width='150'>Адрес</th>
                <th width='150'>Ориентир</th>
                <th width='150'>Время</th>
                <th width='125'>COVID</th>
                <th width='125'>Инъекции</th>
                <th width='350'>Описание</th>
              </tr>
            </thead>
          </table>
          <div class = "scroll_table_body">
            <table>
              <tbody id = "cabinet_table_body">
                <c:forEach items="${cabinets}" var="cabinet">
                  <tr>
                    <td>${cabinet.cabinetName}</td>
                    <td>${cabinet.cabinetAddress}</td>
                    <td>${cabinet.transport}</td>
                    <td>${cabinet.cabinetWorkTime}</td>
                    <td>${cabinet.cabinetCovid}</td>
                    <td>${cabinet.cabinetPrick}</td>
                    <td>${cabinet.cabinetDescription}</td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
    </section>

    <script>
        $(document).ready(function(){
            $("h1").css("color", "blue");
            let name = "${user.userFirstname}";
            $('#user_title_name').text(name.substring(0, 1) + ". ${user.userSurname}");
            let editor = "${user.editor}";
            if(editor){
                $('#editor_show_href').css("display", "block");
            }
            let resultLineValue;
            let clickNumber = 0;
            window.addEventListener("click", function(){
                clickNumber++;
                resultLineValue = $('#result_line').text();
                if(clickNumber==0){
                    $('#result_line').text("");
                }
                if(resultLineValue.length>0){
                    clickNumber = -1;
                }
            });
            $('#select_knowledge').trigger("change");
        });
    </script>

    <div class="buffer" style = "height: 5em;">
    </div>
    </div>
  </body>
</html>


