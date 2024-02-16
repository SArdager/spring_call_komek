package kz.kdlolymp.springcallkomek.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kz.kdlolymp.springcallkomek.controller.serializers.ArticleSerializer;
import kz.kdlolymp.springcallkomek.controller.serializers.CabinetSerializer;
import kz.kdlolymp.springcallkomek.controller.serializers.TypeSerializer;
import kz.kdlolymp.springcallkomek.controller.serializers.UserSerializer;
import kz.kdlolymp.springcallkomek.entity.*;
import kz.kdlolymp.springcallkomek.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WorkController {
    @Autowired
    private UserService userService;
    @Autowired
    private KnowledgeTypeService typeService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CabinetService cabinetService;
    private String message;
    private Gson gson = new Gson();


    @PostMapping("/load-data/user")
    public void loadUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(req.getParameter("id"));
        User user = userService.findUserById(id);
        resp.setContentType("json");
        resp.setCharacterEncoding("UTF-8");
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(User.class, new UserSerializer());
        resp.getWriter().print(builder.create().toJson(user));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @PostMapping("/load-data/types")
    public void loadTypes(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        int knowledgeId = Integer.parseInt(req.getParameter("selectedKnowledgeId"));
        List<KnowledgeType> types = typeService.getTypesByKnowledgeId(knowledgeId);
        resp.setContentType("json");
        resp.setCharacterEncoding("UTF-8");
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(KnowledgeType.class, new TypeSerializer());
        resp.getWriter().print(builder.create().toJson(types));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @PostMapping("/load-data/text")
    public void loadCityText(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        int cityId = Integer.parseInt(req.getParameter("cityId"));
        int typeId = Integer.parseInt(req.getParameter("typeId"));
        String language = req.getParameter("language");
        String words = "";
        if(req.getParameter("words")!=null){
            words = req.getParameter("words");
        };
        Article article = articleService.getCityArticle(cityId, typeId, language);
        if(article != null && article.getText().length()>0 && words.length()>0){
            article = findWords(article, words);
        }
        System.out.println("article, text " + article.getText());
        resp.setContentType("json");
        resp.setCharacterEncoding("UTF-8");
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Article.class, new ArticleSerializer());
        resp.getWriter().print(builder.create().toJson(article));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    private Article findWords(Article article, String text) {
        String fullText = article.getText();
        if(text.length()>0) {
            String[] words = text.split(" ", 5);
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                int index = fullText.indexOf(word);
                while (index > 0) {
                   fullText = fullText.substring(0, index) + "<b>" + fullText.substring(index, index + word.length()) +
                           "</b>" + fullText.substring(index + word.length());
                    index = fullText.indexOf(word, index + word.length() + 7);
                }
            }
            article.setText(fullText.trim());
        }
        return article;
    }

    @PostMapping("/load-data/choose-cabinets")
    public void loadCabinetsFromParameters(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        int cityId = Integer.parseInt(req.getParameter("cityId"));
        boolean covid = Boolean.parseBoolean(req.getParameter("covid"));
        boolean children = Boolean.parseBoolean(req.getParameter("children"));
        boolean smear = Boolean.parseBoolean(req.getParameter("smear"));
        boolean injection = Boolean.parseBoolean(req.getParameter("injection"));
        boolean ramp = Boolean.parseBoolean(req.getParameter("ramp"));
        boolean additional = Boolean.parseBoolean(req.getParameter("additional"));
        boolean discount = Boolean.parseBoolean(req.getParameter("discount"));
        boolean cardPay = Boolean.parseBoolean(req.getParameter("cardPay"));
        List<Cabinet> cabinets = cabinetService.getCabinetsByParameters(cityId, covid, children, smear, injection, ramp,
                additional, discount, cardPay);

        resp.setContentType("json");
        resp.setCharacterEncoding("UTF-8");
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Cabinet.class, new CabinetSerializer());
        resp.getWriter().print(builder.create().toJson(cabinets));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @PostMapping("/load-data/cabinets")
    public void loadCabinets(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        int cityId = Integer.parseInt(req.getParameter("cityId"));
        List<Cabinet> cabinets = cabinetService.getCabinetsByCity(cityId);

        resp.setContentType("json");
        resp.setCharacterEncoding("UTF-8");
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Cabinet.class, new CabinetSerializer());
        resp.getWriter().print(builder.create().toJson(cabinets));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @PostMapping("/load-data/cabinet")
    public void loadCabinet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        int cabinetId = Integer.parseInt(req.getParameter("cabinetId"));
        Cabinet cabinet = cabinetService.getCabinetById(cabinetId);

        resp.setContentType("json");
        resp.setCharacterEncoding("UTF-8");
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Cabinet.class, new CabinetSerializer());
        resp.getWriter().print(builder.create().toJson(cabinet));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @PostMapping("/load-data/find-text")
    public void findArticlesWithText(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String text = req.getParameter("text");
        List<Article> articles = articleService.getArticlesByText(text);

        resp.setContentType("json");
        resp.setCharacterEncoding("UTF-8");
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Article.class, new ArticleSerializer());
        resp.getWriter().print(builder.create().toJson(articles));
        resp.getWriter().flush();
        resp.getWriter().close();
    }





//    @PostMapping("/user/load-data/parcels-export-excel")
//    public void exportExcelParcels(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        req.setCharacterEncoding("UTF-8");
//        int departmentId = Integer.parseInt(req.getParameter("exportDepartmentId"));
//        Department department = departmentService.findDepartmentById(departmentId);
//        String departmentName = department.getDepartmentName() + ", " + department.getBranch().getBranchName();
//        LocalDateTime[] localDateTimes = getLocalDateTime(req.getParameter("startDate"), req.getParameter("endDate"));
//        LocalDateTime startDateTime = localDateTimes[0];
//        LocalDateTime endDateTime = localDateTimes[1];
//        DateTimeFormatter rightFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        String startDateString = startDateTime.format(rightFormatter);
//        String endDateString = endDateTime.format(rightFormatter);
//
//        List<Parcel> parcels = parcelService.getParcelsForExportExcel(departmentId, startDateTime, endDateTime);
//        LocalDate currentDate = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String currentDateString = currentDate.format(formatter);
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=parcels_" + currentDateString + ".xlsx";
//        resp.setContentType("application/octet-stream");
//        resp.setHeader(headerKey, headerValue);
//        ParcelExcelExporter excelExporter = new ParcelExcelExporter(parcels, departmentName, startDateString, endDateString);
//        excelExporter.export(resp);
//    }
//
//    @PostMapping("/user/load-data/ready_parcels")
//    public void loadReadyParcels(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        req.setCharacterEncoding("UTF-8");
//        User user = getUserFromAuthentication();
//        if(user!=null) {
//            List<Parcel> parcels = parcelService.getWaitedParcelsByDepartmentId(user.getDepartmentId());
//            GsonBuilder builder = new GsonBuilder();
//            builder.registerTypeAdapter(Parcel.class, new RouteParcelSerializer());
//            resp.setContentType("json");
//            resp.setCharacterEncoding("UTF-8");
//            resp.getWriter().print(builder.create().toJson(parcels));
//            resp.getWriter().flush();
//            resp.getWriter().close();
//        }
//    }

//    @PostMapping("/user/load-data/journal-export-excel")
//    public void exportExcelNotes(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        req.setCharacterEncoding("UTF-8");
//        int departmentId = Integer.parseInt(req.getParameter("exportDepartmentId"));
//        Department department = departmentService.findDepartmentById(departmentId);
//        String departmentName = department.getDepartmentName() + ", " + department.getBranch().getBranchName();
//        LocalDateTime[] localDateTimes = getLocalDateTime(req.getParameter("startDate"), req.getParameter("endDate"));
//        LocalDateTime startDateTime = localDateTimes[0];
//        LocalDateTime endDateTime = localDateTimes[1];
//
//        DateTimeFormatter rightFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        String startDateString = startDateTime.format(rightFormatter);
//        String endDateString = endDateTime.format(rightFormatter);
//        List<ContainerNote> notes = containerNoteService.getNotesForExportExcel(departmentId, startDateTime, endDateTime);
//        LocalDate currentDate = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String currentDateString = currentDate.format(formatter);
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=notes_" + currentDateString + ".xlsx";
//        resp.setContentType("application/octet-stream");
//        resp.setHeader(headerKey, headerValue);
//        JournalExcelExporter excelExporter = new JournalExcelExporter(notes, departmentName, startDateString, endDateString);
//        excelExporter.export(resp);
//    }

    private LocalDateTime[] getLocalDateTime(String fromDate, String untilDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime endDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime startDateTime;
        if(untilDate.length()>0){
            LocalDate endDate = LocalDate.parse(untilDate, formatter);
            LocalTime endTime = LocalTime.of(23, 59);
            endDateTime = LocalDateTime.of(endDate,endTime);
        }
        if(endDateTime.isAfter(LocalDateTime.now())){
            endDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        }
        if(fromDate.length()>0){
            LocalDate startDate = LocalDate.parse(fromDate, formatter);
            LocalTime startTime = LocalTime.of(0, 0);
            startDateTime = LocalDateTime.of(startDate, startTime);
        } else {
            startDateTime = endDateTime.minusMonths(1);
        }
        LocalDateTime[] localDateTimes = new LocalDateTime[2];
        localDateTimes[0] = startDateTime;
        localDateTimes[1] = endDateTime;
        return localDateTimes;
    }


//    @PostMapping("/user/change-rights")
//    public  void changeRights(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        req.setCharacterEncoding("UTF-8");
//        User user = new User();
//        if(req.getParameter("userId")!=null && Long.parseLong(req.getParameter("userId"))>0) {
//            Long userId = Long.parseLong(req.getParameter("userId"));
//            user = userService.findUserById(userId);
//        }
//        int departmentId = Integer.parseInt(req.getParameter("departmentId"));
//        Department department = departmentService.findDepartmentById(departmentId);
//        String rights = req.getParameter("rights");
//        boolean isRightsNeedAdd = true;
//        String oldValue = "reset";
//
//        List<UserRights> userRightsList = user.getUserRightsList();
//        if(userRightsList!=null && userRightsList.size()>0) {
//            for (int i = 0; i < userRightsList.size(); i++) {
//                UserRights userRights = userRightsList.get(i);
//                int currentDepartmentId = userRights.getDepartment().getId();
//                if (currentDepartmentId == departmentId) {
//                    isRightsNeedAdd = false;
//                    oldValue = userRights.getRights();
//                    if (!rights.equals("reset")) {
//                        userRights.setRights(rights);
//                        userRightsService.changeUserRights(userRights);
//                    } else {
//                        userRightsList.remove(i);
//                        userRightsService.deleteUserRights(userRights);
//                    }
//                    user.setUserRightsList(userRightsList);
//                }
//            }
//        }
//        if(isRightsNeedAdd && !rights.equals("reset")){
//            UserRights newUserRights = new UserRights();
//            newUserRights.setDepartment(department);
//            newUserRights.setRights(rights);
//            newUserRights.setUser(user);
//            UserRights userRights = userRightsService.addNewUserRights(newUserRights);
//            user.addUserRights(userRights);
//        }
//        String userRights;
//        if(rights.equals("reader")){userRights = "просмотр записей и получение отчетов";}
//        else if(rights.equals("editor")){userRights = "внесение и редактирование записей";}
//        else if(rights.equals("courier")){userRights = "курьер - внесение записей";}
//        else if(rights.equals("changer")){userRights = "просмотр записей и изменение срока доставки";}
//        else if(rights.equals("righter")){userRights = "просмотр записей и редактирование прав";}
//        else if(rights.equals("chef")){userRights = "полные права по лаборатории";}
//        else if(rights.equals("account")){userRights = "по учету термоконтейнеров";}
//        else if(rights.equals("creator")){userRights = "по созданию и отправке почтовых корреспонденций";}
//        else if(rights.equals("reset")){userRights = "убраны права доступа";}
//        else {userRights = "undefined";}
//
//        if(userService.saveUser(user)){
//            String userName = user.getUserSurname() + " " + user.getUserFirstname();
//            String email = user.getEmail();
//            if(!rights.equals("reset")){
//                String departmentString = department.getDepartmentName() + ", " + department.getBranch().getBranchName();
//                String finalUserRights = userRights;
//                Thread sendMessage = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        emailService.sendNewRightsMessage(userName, email, departmentString, finalUserRights);
//                    }
//                });
//                sendMessage.start();
//                message = "Пользователю " + userName + " добавлены (изменены) права. \n" +
//                        "Сообщение об изменении прав отправлено на корпоративный почтовый адрес пользователя.";
//            } else {
//                message = "Пользователю " + userName + " убраны права по объекту.";
//            }
//        } else {
//            message = "Ошибка изменения прав пользователя";
//        }
//        User userEditor = getUserFromAuthentication();
//        if(oldValue.equals("reader")){oldValue = "просмотр записей и получение отчетов";}
//        else if(oldValue.equals("editor")){oldValue = "внесение и редактирование записей";}
//        else if(oldValue.equals("courier")){oldValue = "курьер - внесение записей";}
//        else if(oldValue.equals("changer")){oldValue = "просмотр записей и изменение срока доставки";}
//        else if(oldValue.equals("righter")){oldValue = "просмотр записей и редактирование прав";}
//        else if(oldValue.equals("chef")){oldValue = "полные права по лаборатории";}
//        else if(oldValue.equals("account")){oldValue = "по учету термоконтейнеров";}
//        else if(oldValue.equals("creator")){userRights = "по созданию и отправке почтовых корреспонденций";}
//        else if(oldValue.equals("reset")){oldValue = "отсутствуют права";}
//        else {oldValue = "undefined";}
//        eventLogService.saveEvent(userEditor, departmentId, user.getId(), userRights, oldValue, 2);
//
//        resp.setContentType("text");
//        resp.setCharacterEncoding("UTF-8");
//        resp.getWriter().print(message);
//        resp.getWriter().flush();
//        resp.getWriter().close();
//    }
//
//    @PostMapping("/user/del-user")
//    public  void delUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        req.setCharacterEncoding("UTF-8");
//        User user = new User();
//        if(req.getParameter("userId")!=null && Long.parseLong(req.getParameter("userId"))>0) {
//            Long userId = Long.parseLong(req.getParameter("userId"));
//            user = userService.findUserById(userId);
//        }
//        String userName = user.getUserSurname() + " " + user.getUserFirstname();
//        user.setEnabled(false);
//        User userEditor = getUserFromAuthentication();
//        if(userService.saveUser(user)){
//            message = "Пользователю " + userName + " закрыт доступ в систему.";
//            eventLogService.saveEvent(userEditor, 1, user.getId(), message, "", 2);
//
//        } else {
//            message = "Ошибка удаления пользователя: " + userName;
//        }
//
//        resp.setContentType("text");
//        resp.setCharacterEncoding("UTF-8");
//        resp.getWriter().print(message);
//        resp.getWriter().flush();
//        resp.getWriter().close();
//    }
//


//    @PostMapping("/admin/load-data/totalLogs")
//    public void loadTotalLogs(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        req.setCharacterEncoding("UTF-8");
//        int eventId = Integer.parseInt(req.getParameter("eventId"));
//        int branchId = Integer.parseInt(req.getParameter("branchId"));
//        LocalDateTime[] localDateTimes = getLocalDateTime(req.getParameter("startDate"), req.getParameter("endDate"));
//        LocalDateTime startDateTime = localDateTimes[0];
//        LocalDateTime endDateTime = localDateTimes[1];
//        int count = eventLogService.getAllLogsNumberByDates(eventId, branchId, startDateTime, endDateTime);
//        resp.setContentType("json");
//        resp.setCharacterEncoding("UTF-8");
//        resp.getWriter().print(this.gson.toJson(count));
//        resp.getWriter().flush();
//        resp.getWriter().close();
//    }
//
//    @PostMapping("/admin/load-data/logs-journal")
//    public void loadLogsJournal(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        req.setCharacterEncoding("UTF-8");
//        int eventId = Integer.parseInt(req.getParameter("eventId"));
//        int branchId = Integer.parseInt(req.getParameter("branchId"));
//        int pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
//        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
//        LocalDateTime[] localDateTimes = getLocalDateTime(req.getParameter("startDate"), req.getParameter("endDate"));
//        LocalDateTime startDateTime = localDateTimes[0];
//        LocalDateTime endDateTime = localDateTimes[1];
//        List<EventLog> logs;
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        logs = eventLogService.getLogsByDates(eventId, branchId, startDateTime, endDateTime, pageable);
//        resp.setContentType("json");
//        resp.setCharacterEncoding("UTF-8");
//        if(eventId==1){
//            gson = new GsonBuilder()
//                    .registerTypeAdapter(EventLog.class, new EventLogSerializer(userService, departmentService))
//                    .create();
//        } else {
//            gson = new GsonBuilder()
//                    .registerTypeAdapter(EventLog.class, new EventLogSerializer(userService, departmentService))
//                    .create();
//        }
//        resp.getWriter().print(gson.toJson(logs));
//        resp.getWriter().flush();
//        resp.getWriter().close();
//    }
//
//    @PostMapping("/admin/load-data/logs-exportExcel")
//    public void exportEventLogs(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        req.setCharacterEncoding("UTF-8");
//        int eventId = Integer.parseInt(req.getParameter("eventId"));
//        LocalDateTime[] localDateTimes = getLocalDateTime(req.getParameter("startDate"), req.getParameter("endDate"));
//        LocalDateTime startDateTime = localDateTimes[0];
//        LocalDateTime endDateTime = localDateTimes[1];
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        DateTimeFormatter rightFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        String startDateString = startDateTime.format(rightFormatter);
//        String endDateString = endDateTime.format(rightFormatter);
//        List<EventLog> logs = eventLogService.getLogsForExportExcel(eventId, branchId, startDateTime, endDateTime);
//        LocalDate currentDate = LocalDate.now();
//        String currentDateString = currentDate.format(formatter);
//        String headerKey = "Content-Disposition";
//        String headerValue;
//            headerValue = "attachment; filename=deliver_time_logs_" + currentDateString + ".xlsx";
//        resp.setContentType("application/octet-stream");
//        resp.setHeader(headerKey, headerValue);
//        LogsExcelExporter excelExporter = new LogsExcelExporter(logs, eventId, branchName, startDateString, endDateString, userService, departmentService);
//        excelExporter.export(resp);
//    }
//

}

