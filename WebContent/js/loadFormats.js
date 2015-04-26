$(document).ready(function() {
        $('a#formats').click(function(event) {
            var actionSource ='loadFormats';
             console.dir("entro") ;
            $.post('FortmatsServlet', {action:actionSource
            }, function(responseText) {
                $('#content').html(responseText);
            });
        });
    });
