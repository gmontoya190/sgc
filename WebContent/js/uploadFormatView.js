$(document).ready(function() {
        $('#content').click(function(event) {
            var actionSource ='uploadFormatView';
            $.post('FortmatsServlet', {action:actionSource           	 
            }, function(response) { 
                //$(location).attr('href',"uploadFile.jsp");       
            
            });
        });


});


