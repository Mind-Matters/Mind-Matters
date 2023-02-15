//API CODE STARTS HERE

document.addEventListener('DOMContentLoaded', function() {
    let calendarEl = document.getElementById('calendar');
    let calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        initialDate: '2023-02-08',
        selectable: true,
        editable: true,
        height: 450,
        dayMaxEvents: true,
        headerToolbar: {
            left: 'title,prev,next',
            center: 'addEventButton',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        customButtons: {
            addEventButton: {
                text: 'submit event',
                click: function() {
                    document.getElementById("event-form").submit();
                }
            }
        }
    });

    // populate calendar with events from db
    let events = /*[[${events}]]*/ null;
    if (events != null){
        events.forEach(function(event) {
            calendar.addEvent({
                title: event.title,
                start: event.date,
                description: event.description,
                allDay: true
            });
        });
    }
    calendar.render();
});

//document.getElementById("jsTest").innerHTML = [${testJs}];

