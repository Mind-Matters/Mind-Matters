function submitToDB(date, title, description, categories) {
    // invisible form to submit to db
    document.getElementById("titleDb").value = title;
    document.getElementById("descriptionDb").value = description;
    document.getElementById("dateDb").value = date;
    document.getElementById("categoriesDb").value = categories;
    document.getElementById("calendar-event-to-db").submit();
}

//API CODE STARTS HERE
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
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

                    // var dateStr = prompt('Enter a date in YYYY-MM-DD format');
                    var dateStr = document.getElementById('date').value;
                    // var title = prompt('Enter a date in YYYY-MM-DD format');
                    var title = document.getElementById('title').value;
                    var description = document.getElementById("description").value;
                    var date = new Date(dateStr + 'T00:00:00');// will be in local time
                    var categories = document.getElementById("categories");
                    submitToDB(date, title, description, categories);

/*                    if (!isNaN(date.valueOf())) { // valid?
                        calendar.addEvent({
                            title: title,
                            start: date,
                            allDay: true
                        });
                        alert('Great. Now, update your database...');
                    } else {
                        alert('Invalid date.');
                    }*/
                }
            }
        }

    });

    // populate calendar with events from db
    let events = /*[[${events}]]*/;
    events.forEach(function(event) {
        calendar.addEvent({
            title: event.title,
            start: event.date,
            allDay: true
        });
    });
    calendar.render();
});