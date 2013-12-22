window.onload = function(){
    if(navigator.geolocation){
        navigator.geolocation.getCurrentPosition(handleGetCurrentPosition, onError);
    }
    var map;
    var clickedMarked;
 
    
    function onMapClick(e) {
        if (typeof clickedMarked != 'undefined') {
            map.removeLayer(clickedMarked);            
        }
        
        $("#latitude").val(e.latlng.lat);
        $("#longitude").val(e.latlng.lng);

        clickedMarked = L.marker(e.latlng).addTo(map);

        clickedMarked.bindPopup("<b>New scam location</b>").openPopup();
    }
   
    
    function handleGetCurrentPosition(location){

        map = L.map('map').setView([location.coords.latitude,  location.coords.longitude], 5);
        L.tileLayer('http://{s}.tile.cloudmade.com/87d15a1127ce4ae89f65ddb7589d3fee/997/256/{z}/{x}/{y}.png', {
            attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://cloudmade.com">CloudMade</a>',
            maxZoom: 18
        }).addTo(map);
        map.on('click', onMapClick);
        for(var i=0;i<cordinateArray.length;i++){
    
          marker = L.marker([cordinateArray[i].latitude, cordinateArray[i].longitude]).addTo(map);
          marker.bindPopup("<b>"+cordinateArray[i].title+"</b>").openPopup();
        
        }
    }
    
    function onError(){alert('Shittt omomomgogmogogmgm');}

   

    
    
   

    $('#money-checkbox').change(function(){
        if (this.checked) {
            $('#money-div').fadeIn('slow');
        }
        else {
            $('#money-div').fadeOut('slow');
        }                   
    });

    $('#violence-checkbox').change(function(){
        if (this.checked) {
            $('#violence-div').fadeIn('slow');
        }
        else {
            $('#violence-div').fadeOut('slow');
        }                   
    });

    $('#property-checkbox').change(function(){
        if (this.checked) {
            $('#property-div').fadeIn('slow');
        }
        else {
            $('#property-div').fadeOut('slow');
        }                   
    });
};