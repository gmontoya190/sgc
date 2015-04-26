$(document).ready(function() {
    console.dir("hello");
    var url = 'http://api.themoviedb.org/3/',
        mode = 'search/movie?query=',
        input,
        movieName,
        key = '&api_key=470fd2ec8853e25d2f8d86f685d2270e';

    $('a#formats').on("click" ,function() {
        var input = "app",
            movieName = encodeURI(input);
            console.dir("hello");
        $.ajax({
            type: 'GET',
            url: url + mode + input + key,
            async: false,
            jsonpCallback: 'testing',
            contentType: 'application/json',
            dataType: 'jsonp',
            success: function(json) {
                console.dir(json);
                var obj= JSON.stringify(json);
                //console.l
                var object= JSON.parse(obj);
                var resultsSize=object.results.length;
                var htmlBuilder; 
                console.dir(resultsSize);
                //for (i=0; i<resultsSize; i++) {
                     htmlBuilder='<div class="row" id="row_results">';
                     htmlBuilder+='<div class="col-md-3" style="background-color:lavenderblush;">';
                     htmlBuilder+='<img src="http://image.tmdb.org/t/p/w500"'+object.results[0].poster_path;
                     htmlBuilder+=';'
                     htmlBuilder+=object.results[0].original_title;
                     htmlBuilder+='</div></div>';
                     //$('#rows').html(htmlBuilder);
                     
                //}
               
                //return object.results;

                
            },
            error: function(e) {
                console.log(e.message);
            }
        });
    });
});