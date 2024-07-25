document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridDay'
        },
        selectable: true,
        selectHelper: true,
        events: '/calendar/events',
        select: function(info) {
            var title = prompt('봉사활동 일정을 등록하세요');
            if (title) {
                var eventData = {
                    title: title,
                    start: info.startStr,
                    end: info.endStr
                };
                calendar.addEvent(eventData);

                $.ajax({
                    url: '/calendar/calendars',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(eventData)
                });
            }
            calendar.unselect();
        }
    });
    calendar.render();
});

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