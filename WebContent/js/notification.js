$(document).ready(function() {
        $('a#notifications').click(function(event) {
            var actionSource ='loadFormats';
             console.dir("entro paso load formatss") ;
            $.post('NotificationServlet', {action:actionSource
            }, function(responseText) {
                $('#content').html(responseText);
            });
        });
    });