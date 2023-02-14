function submitToDB(date, title, description, category1, category2, category3, category4, category5, category6, category7, category8, category9, category10) {
    // invisible form to submit to db
    document.getElementById("titleDb").value = title;
    document.getElementById("descriptionDb").value = description;
    document.getElementById("dateDb").value = date;
    document.getElementById("categoryDB1").checked = category1;
    document.getElementById("categoryDB2").checked = category2;
    document.getElementById("categoryDB3").checked = category3;
    document.getElementById("categoryDB4").checked = category4;
    document.getElementById("categoryDB5").checked = category5;
    document.getElementById("categoryDB6").checked = category6;
    document.getElementById("categoryDB7").checked = category7;
    document.getElementById("categoryDB8").checked = category8;
    document.getElementById("categoryDB9").checked = category9;
    document.getElementById("categoryDB10").checked = category10;
    document.getElementById("calendar-event-to-db").submit();
}


function editEvent(id, date, title, description, categories) {
    // invisible form to submit to db
    document.getElementById("idDb").value = id;
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
                text: 'add event',
                click: function() {

                    // gather data
                    let title = document.getElementById("title");
                    let description = document.getElementById("description");
                    let date = document.getElementById("date");

                    // collect category data by line
                    let category1 = document.getElementById("category1").checked;
                    let category2 = document.getElementById("category2").checked;
                    let category3 = document.getElementById("category3").checked;
                    let category4 = document.getElementById("category4").checked;
                    let category5 = document.getElementById("category5").checked;
                    let category6 = document.getElementById("category6").checked;
                    let category7 = document.getElementById("category7").checked;
                    let category8 = document.getElementById("category8").checked;
                    let category9 = document.getElementById("category9").checked;
                    let category10 = document.getElementById("category10").checked;


                    /* Collect category data as JSON*/
/*                    let categories = {
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
                    };*/

                    submitToDB(date, title, description, category1, category2, category3, category4, category5, category6, category7, category8, category9, category10);

                }
            }
        }
    },

        eventDidMount: function(info) {
            var tooltip = new Tooltip(info.el, {
                title: info.event.extendedProps.description,
                placement: 'top',
                trigger: 'hover',
                container: 'body'
            }
        );
    }

});


    // populate calendar with events from db
    let events = /*[[${events}]]*/;
    events.forEach(function(event) {
        calendar.addEvent({
            title: event.title,
            start: event.date,
            description: event.description,
            allDay: true
        });
    });
    calendar.render();
});

