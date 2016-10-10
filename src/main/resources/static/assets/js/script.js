var routepoints;

$(document).ready(function () {
    $.ajax({
        url: 'http://localhost:8080/getDirections',
        dataType: 'json'
    }).then(function (data) {
        routepoints = data;
        initMap();
    })
});

function initMap() {
    var myOptions = {
            zoom: 25,
            center: new google.maps.LatLng(59.334591, 18.063240)
        },
        map = new google.maps.Map(document.getElementById('map'), myOptions),

        // Instantiate a directions service.
        directionsService = new google.maps.DirectionsService,
        directionsDisplay = new google.maps.DirectionsRenderer({
            map: map
        })

    for (var i = 0; i < routepoints.length; i++) {
        new google.maps.Marker({
            position: new google.maps.LatLng(routepoints[i].point.coordinateX
                , routepoints[i].point.coordinateY),
            title: routepoints[i].name,
            label: "Have a Brew",
            map: map

        });

        calculateAndDisplayRoute(directionsService, directionsDisplay, routepoints);
    }
};


    function calculateAndDisplayRoute(directionsService, directionsDisplay, routepoints) {
        var pointA,
            pointB;

        for (var i = 0; i < routepoints.length; i++)
            if(routepoints[i].name === "start"){
                pointA = new google.maps.LatLng(routepoints[i].point.coordinateX
                    , routepoints[i].point.coordinateY)
            }else if(routepoints[i].name === "destination") {
                pointB = new google.maps.LatLng(routepoints[i].point.coordinateX
                    , routepoints[i].point.coordinateY)
            };

        directionsService.route({
            origin: pointA,
            destination: pointB,
            travelMode: google.maps.TravelMode.TRANSIT

        }, function (response, status) {
            if (status == google.maps.DirectionsStatus.OK) {
                directionsDisplay.setDirections(response);
            } else {
                window.alert('Directions request failed due to ' + status);
            }
        });
    }

