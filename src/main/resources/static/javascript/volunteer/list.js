// 풀캘린더 시작
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridDay'
        },
        buttonText: {
            month: '월간',
            week: '주간',
            day: '일간',
            list: '목록'
        },
        events: async function(fetchInfo, successCallback, failureCallback) {
            try {
                const response = await fetch('/calendar/events');
                const data = await response.json();
                const events = data.map(event => ({
                    title: event.title,
                    start: event.startDate,  // Assuming startDate is in ISO format
                    end: event.endDate     // Assuming endDate is in ISO format
                }));
                successCallback(events);
            } catch (error) {
                failureCallback(error);
            }
        },
        locale: 'ko'
    });
    calendar.render();
});

// 봉사목록 페이지네이션
$(document).ready(function () {
    $(".page-link").on("click", function () {
        $("#page").val($(this).data("page"));
        $("#searchForm").submit();
    });

    $("#btn_search").on("click", function () {
        $("#kw").val($("#search_kw").val());
        $("#page").val(0);
        $("#searchForm").submit();
    });
});