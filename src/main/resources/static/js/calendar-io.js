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

                    /* Collect category data as JSON*/
                    let categories = {
                        "category1": document.getElementById("category1").checked,
                        "category2": document.getElementById("category2").checked,
                        "category3": document.getElementById("category3").checked,
                        "category4": document.getElementById("category4").checked,
                        "category5": document.getElementById("category5").checked,
                        "category6": document.getElementById("category6").checked,
                        "category7": document.getElementById("category7").checked,
                        "category8": document.getElementById("category8").checked,
                        "category9": document.getElementById("category9").checked,
                        "category10": document.getElementById("category10").checked
                    };

                    submitToDB(date, title, description, categories);

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