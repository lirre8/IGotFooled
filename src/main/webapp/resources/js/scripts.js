$(document).ready(function () {

    /**
     * Calculating the mean value of all ranks and returning an array with them splitted up per semester.
     * @param ranksArray
     * @param type the type of rank
     * @returns {Array}
     */
    function getRanksArray(ranksArray, type) {
        var sum = 0, steps = 6, springCount = 0, fallCount = 0;
        var dataArray = [5,5,5,5,5,5,5], currentYear = new Date().getFullYear();
        
        if (ranksArray == null) {
            return dataArray;
        }

        for (var i = 0; i < ranksArray.length; i++) {
            var dateParts = (ranksArray[i].date).split("/");
            var rankYear = dateParts[0];
            //All ranks on same year
            if (rankYear == currentYear) {
                //All ranks in spring that year
                if (dateParts[1] < 6) {
                    //Change start value of 5 to 0
                    if (springCount == 0) {
                        dataArray[steps -1] = 0;
                    }
                    springCount = springCount + 1;

                    sum = parseInt(dataArray[steps - 1]) + parseInt(ranksArray[i][type]);
                    dataArray[steps - 1] = sum;
                //All ranks in fall that year
                } else {
                  //Change start value of 5 to 0
                    if (fallCount == 0) {
                        dataArray[steps] = 0;
                    }
                    fallCount = fallCount + 1;

                    sum = parseInt(dataArray[steps]) + parseInt((ranksArray[i][type]));
                    dataArray[steps] = sum;                
                }
            } else {
                //Avoid dividing with 0
                if (springCount > 0) {
                    dataArray[steps - 1] = dataArray[steps - 1] / springCount; 
                }
                if (fallCount > 0) {
                    dataArray[steps] = dataArray[steps] / fallCount;
                }

                currentYear = currentYear - 1;
                steps = steps - 2;

                springCount = 0;
                fallCount = 0;
            }
        }
        //Avoid dividing with 0
        if (springCount > 0) {
            dataArray[steps - 1] = dataArray[steps - 1] / springCount; 
        }
        if (fallCount > 0) {
            dataArray[steps] = dataArray[steps] / fallCount;
        }
        return dataArray;
    }

    //Run if the courses work distribution is set
    if (typeof numberOfAssignments != 'undefined') {
        var workDistributionData = [
                    {
                        value: parseInt(numberOfAssignments),
                        color:"#F38630",
                        label : numberOfAssignments + ' Assignments',
                        labelColor : 'white',
                        labelFontSize : '16'
                    },
                    {
                        value : parseInt(numberOfExams),
                        color : "#E0E4CC",
                        label : numberOfExams + ' Exam',
                        labelColor : 'white',
                        labelFontSize : '16'
                    },
                    {
                        value : parseInt(numberOfProjects),
                        color : "#69D2E7",
                        label : numberOfProjects + ' Project',
                        labelColor : 'white',
                        labelFontSize : '16'
                    }           
                ];
        
        if (document.getElementById("work-distribution-canvas") != null) {
            new Chart(document.getElementById("work-distribution-canvas").getContext("2d")).Pie(workDistributionData, options);
        }
    }

    //Run if we are on a course page.
    if (typeof ranksArray != 'undefined') {
        var options = {
                //Boolean - If we show the scale above the chart data           
                scaleOverlay : false,
                
                //Boolean - If we want to override with a hard coded scale
                scaleOverride : true,
                
                //** Required if scaleOverride is true **
                //Number - The number of steps in a hard coded scale
                scaleSteps : 10,
                //Number - The value jump in the hard coded scale
                scaleStepWidth : 1,
                //Number - The scale starting value
                scaleStartValue : 0,
    
                //String - Colour of the scale line 
                scaleLineColor : "rgba(0,0,0,.1)",
                
                //Number - Pixel width of the scale line    
                scaleLineWidth : 1,
    
                //Boolean - Whether to show labels on the scale 
                scaleShowLabels : true,
                
                //Interpolated JS string - can access value
                scaleLabel : "<%=value%>",
                
                //String - Scale label font declaration for the scale label
                scaleFontFamily : "'Arial'",
                
                //Number - Scale label font size in pixels  
                scaleFontSize : 12,
                
                //String - Scale label font weight style    
                scaleFontStyle : "normal",
                
                //String - Scale label font colour  
                scaleFontColor : "#666",    
                
                ///Boolean - Whether grid lines are shown across the chart
                scaleShowGridLines : true,
                
                //String - Colour of the grid lines
                scaleGridLineColor : "rgba(0,0,0,.05)",
                
                //Number - Width of the grid lines
                scaleGridLineWidth : 1, 
                
                //Boolean - Whether the line is curved between points
                bezierCurve : true,
                
                //Boolean - Whether to show a dot for each point
                pointDot : true,
                
                //Number - Radius of each point dot in pixels
                pointDotRadius : 3,
                
                //Number - Pixel width of point dot stroke
                pointDotStrokeWidth : 1,
                
                //Boolean - Whether to show a stroke for datasets
                datasetStroke : true,
                
                //Number - Pixel width of dataset stroke
                datasetStrokeWidth : 2,
                
                //Boolean - Whether to fill the dataset with a colour
                datasetFill : true,
                
                //Boolean - Whether to animate the chart
                animation : true,
    
                //Number - Number of animation steps
                animationSteps : 60,
                
                //String - Animation easing effect
                animationEasing : "easeOutQuart",
    
                //Function - Fires when the animation is complete
                onAnimationComplete : null
                
            };
        
        var overallData = {
                labels : getLabelsArray(),
                datasets : [
                    {
                        fillColor : "rgba(40,232,30,0.5)",
                        strokeColor : "rgba(40,232,30,1)",
                        pointColor : "rgba(40,232,30,1)",
                        pointStrokeColor : "#fff",
                        data : getRanksArray(ranksArray, "avarageScore")
                    }]
            };
        
        var teachersData = {
                labels : getLabelsArray(),
                datasets : [
                    {
                        fillColor : "rgba(251,82,118,0.5)",
                        strokeColor : "rgba(251,82,118,1)",
                        pointColor : "rgba(251,82,118,1)",
                        pointStrokeColor : "#fff",
                        data : getRanksArray(ranksArray, "teachers")
                    }
                ]
            };
        var structureData = {
                labels : getLabelsArray(),
                datasets : [
                    {
                        fillColor : "rgba(255,238,28,0.5)",
                        strokeColor : "rgba(255,238,28,1)",
                        pointColor : "rgba(255,238,28,1)",
                        pointStrokeColor : "#fff",
                        data : getRanksArray(ranksArray, "structure")
                    }
                ]
            };
        var effortData = {
                labels : getLabelsArray(),
                datasets : [
                    {
                        fillColor : "rgba(67,155,255,0.5)",
                        strokeColor : "rgba(67,155,255,1)",
                        pointColor : "rgba(67,155,255,1)",
                        pointStrokeColor : "#fff",
                        data : getRanksArray(ranksArray, "workload")
                    }
                ]
            };

        function countRanksLastYear() {
            var countShowingRanks = 0;
            
            if (ranksArray == null) {
                return 0;
            }
            
            for (var i = 0; i < ranksArray.length; i++) {
                var dateParts = (ranksArray[i].date).split("/");
                currentYear = new Date().getFullYear();
                if (dateParts[0] == currentYear) {
                    countShowingRanks++;
                }   
            }
            if (countShowingRanks > 0) {
                $("#current-ranks > p > span, #current-ranks > p > span").html("Based on " + countShowingRanks + " rank/ranks during the last year.");
            }
        }
        
        countRanksLastYear();
    }

    function initiateRankingCharts() {
        if (document.getElementById("overall-score-canvas") != null) {
            new Chart(document.getElementById("overall-score-canvas").getContext("2d")).Line(overallData, options);
        }

        if (document.getElementById("teachers-score-canvas") != null) {
            new Chart(document.getElementById("teachers-score-canvas").getContext("2d")).Line(teachersData, options);
        }
        
        if (document.getElementById("structure-score-canvas") != null) {
            new Chart(document.getElementById("structure-score-canvas").getContext("2d")).Line(structureData, options);
        }
        
        if (document.getElementById("effort-score-canvas") != null) {
            new Chart(document.getElementById("effort-score-canvas").getContext("2d")).Line(effortData, options);
        }
    }
    initiateRankingCharts();

    $('.slider').slider();

    $("#carousel").carousel();

    $('#sidebar').affix(); 

    //Fix for menu in course found page
    var offset = 62;
    $('#sidebar li a').click(function(event) {
        event.preventDefault();
        $($(this).attr('href'))[0].scrollIntoView();
        scrollBy(0, -offset);
    });	
    
    //Adds http to course link if missing
    function addHTTP(url) {
        if (url.substring(0, 7).toUpperCase() != "HTTP://") {
            url = "http://" + url;
        };
        return url;
    };

    //Fixing course links on pageload
	if ($("#courseLink").text() != "") {
		$("#courseLink").attr("href", addHTTP(courseLink));
		$("#courseLink").text(addHTTP($("#courseLink").text()));
	}

	/**
	 * Code for fetching information about books
	 */
    var searchResult = Object();
    $("#isbn").keyup(function (event) {
    	var isbnString = $("#isbn").val();
    	isbnString = getOnlyNumbers(isbnString);
    	$(event.target).parent().find('p').remove();
    	
    	if(isbnString.length >= 10) {
    		$(event.target).parent().append('<p><i id="button-spinner" class="icon-spinner icon-spin"></i> Loading book</p>');
	    	$.ajax({
		    	url:"http://libris.kb.se/xsearch?query=isbn:" + isbnString + "&format_level=brief&format=json&callback=?",
		        type:'GET',
		        async:true,
		        dataType:'json',
		        data:JSON.stringify(searchResult),
		        contentType:"application/json; charset=utf-8",
		        success: function (searchResult) {
		        	$("#title").val(searchResult.xsearch.list[0].title);
		        	$("#creator").val(searchResult.xsearch.list[0].creator);
		        	$("#date").val(searchResult.xsearch.list[0].date);
		        	$("#language").val(searchResult.xsearch.list[0].language);
		        },
		        error: function (request, status, error) {
		            alert(error);
		        },
		        complete: function() {
		        	$(event.target).parent().find('p').remove();
		        }
		    });
    	}
    });
    
    ///////////// Search course //////////////////////
    
    $(".search-course").keyup(function(event) {
        if ($(".search-course").is(":focus")) {
            if (event.keyCode == 13) {
                searchCourse();
            }   
        }
    });

    $('.search-course-button').click(function() {
        searchCourse();
     });

    function searchCourse() {
        var id = $("input.search-course").val();
        if (id) {
            window.location.href = "/courses/" + id + "/x";
        } else {
            window.location.href = "/addcourse";
        }
    }

    $('input.search-course').typeahead([{
        template: ['<strong class="text-left">{{courseName}}</strong> <em id="course-id" style="float:right;">{{id}}</em><br>',
                   '{{courseCode}} at {{university}}'].join(''),
        prefetch: {
            url: '/typeahead/searchCourseCodeName',
            filter: function(data) {
                retval = [];
                for (var i = 0;  i < data.length;  i++) {
                    retval.push({
                        value: data[i].id,
                        tokens: [data[i].courseCode, data[i].courseName.split(" "), String(data[i].id), data[i].university.split(" ")].flatten(),
                        courseName: data[i].courseName,
                        courseCode: data[i].courseCode,
                        university: data[i].university,
                        id: data[i].id,
                    });
                }
                return retval;
            }
        },
        engine: Hogan
    }]);
    ///////////// End search course //////////////////////
});

/**
 * Utility for flatten a array. Changing [1,[2,3]] to [1,2,3] for example.
 */
Array.prototype.flatten = function() {
    var r = [];
    for (var i = 0; i < this.length; ++i) {
        var v = this[i];
        if (v instanceof Array) {
            Array.prototype.push.apply(this, v.flatten());
        } else {
            r.push(v);
        }
    }
    return r;
};

/**
 * Regexp function that sort a string to only contain numbers
 * @param string the string with different characters in
 * @returns a string with only numbers in
 */
function getOnlyNumbers(string) {
	return string.replace(/[^0-9]/g, '');
}

/**
 * Calculates the last 7 semesters
 * @returns {Array} with all the labels for the charts
 */
function getLabelsArray() {
    var labelsArray = [], fall = false;
    var currentYear = new Date().getFullYear();
    var currentMonth = new Date().getMonth();

    if (currentMonth >= 6) { fall = true; };
    
    for (var steps = 6; steps >= 0; steps--) {
        if (fall == true) {
            labelsArray[steps] = "Fall " + String(currentYear);
            fall = false;
        } else {
            labelsArray[steps] = "Spring " + String(currentYear);
            currentYear--;
            fall = true;
        }
    }
    return labelsArray;
}

/**
 * Returns the username if user are logged in
 * @param anonymous
 * @param userName
 * @returns
 */
function getUsername(anonymous, userName) {
	if (anonymous == true) {
		return "Anonymous";
	}
	return userName;
};

/* MILESTONE 3 */
//TODO : Typeahead for all fields
//TODO : Make searchfunction work with universities and courses, and to return more than one.
//TODO : Accept 7.5 as course points
//TODO : Short down real long webpageURLs in the coursepage
//TODO : Get linkedIn key and twitter key
//TODO : Make affärsid'e and evaluate
//TODO : Investigate why i need to pass commentForm to AdduserbookController
//TODO : Unique constraint course books and universities
//TODO : Remake front page
//TODO : Add sent buy in buy books emails
/* MILESTONE 4 */
//TODO : Translations to swedish
//TODO : Add more relevant information for universites
//TODO : Highscore list's of courses.
//TODO : Favorite courses and show them on profile, a star to click on beside coursename
//TODO : Rename coursePoints to courseCredits.
//TODO : Add password recovery
//TODO : The ability to add suggested requirements
//TODO : The ability to add real requirements
//TODO : Add up votes and down votes to comments
//TODO : Add the possibility to compare shools, courses, workloads and so on.
//TODO : Import courseras courses and universities
//TODO : Further develop individual rating with anonymous and so on.
//TODO : Add the ability to rank many courses at once with help of linkedin.
//TODO : Add courses from linked in, and sync other information from there like university and so on.
//TODO : Add like button/post on facebook
//TODO : Add TA's, possibility to own money buy helping others.
//TODO : Add file examination support
//TODO : Add lecture notes
//TODO : Add previous exams and a way to comment them
//TODO : Show rankings on map to see how it differs clearly over the world.
