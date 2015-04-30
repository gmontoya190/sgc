$(document).ready(function() {
        $('a#tasks').click(function(event) {
            $.post('ActivitiesView', {
            }, function(responseText) {
                $('#content').html(responseText);
            });
        });
    });