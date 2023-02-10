function submitToDB() {
    var date = document.getElementById('date').value;
    var title = document.getElementById('title').value;
    var description = document.getElementById('description').value;
    var event = {
        "date": date,
        "title": title,
        "description": description
    };
    fetch('http://localhost:8081/dashboard'),{
        method: 'POST',
        headers: {
         Accept: 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            date: date,
            title: title,
            description: description
        })
    };
}
document.addEventListener('DOMContentLoaded', function () {
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
                click: function buttonTwo () {
                    submitToDB();
                    // var dateStr = prompt('Enter a date in YYYY-MM-DD format');
                    var dateStr = document.getElementById('date').value;
                    // var title = prompt('Enter a date in YYYY-MM-DD format');
                    var title = document.getElementById('title').value;
                    // var event = document.getElementById("description").value;
                    var date = new Date(dateStr + 'T00:00:00');// will be in local time
                    var eventForm = document.getElementById("eventForm");

                    if (!isNaN(date.valueOf())) { // valid?
                        calendar.addEvent({
                            title: title,
                            start: date,
                            allDay: true
                        });
                        //create mindmatters event, "event bean"
                        //make bean accessible
                        // send bean to java file ?
                        // event controller has @POSTMAPPING
                        alert('Great. Now, update your database...');
                    } else {
                        alert('Invalid date.');
                    }
                }
            }
        }
    });

    calendar.render();
});