// https://angular-ui.github.io/

// setup app and pass ui.bootstrap as dep
var myApp = angular.module("angularTypeahead", ["ui.bootstrap"]);

// define factory for data source
myApp.factory("States", function(){
    var states = ["Slussen", "Medborgarplatsen", "Skanstull", "Gullmarsplan", "Skärmarbrink", "Blåsut", "Sandsborg", "Skogskyrkogården", "Tallkrogen", "Gubbängen", "Hökarängen", "Globen", "Enskede gård", "Sockenplan", "Svedmyra", "Stureby", "Hötorget", "Rådmansgatan", "Odenplan", "Sankt Eriksplan", "Fridhemsplan", "Thorildsplan", "Kristineberg", "Alvik", "Stora mossen", "Abrahamsberg", "Brommaplan", "Åkeshov", "Ängbyplan", "Islandstorget", "Blackeberg", "Råcksta", "Vällingby", "Bandhagen", "Högdalen", "Johannelund", "Hässelby gård", "T-Centralen", "Gamla stan", "Hammarbyhöjden", "Björkhagen", "Kärrtorp", "Bagarmossen", "Farsta", "Hässelby strand", "Rågsved", "Hagsätra", "T-Centralen", "Gamla stan", "Slussen", "Mariatorget", "Zinkensdamm", "Hornstull", "Liljeholmen", "Midsommarkransen", "Telefonplan", "Hägerstensåsen", "Västertorp", "Fruängen", "Aspudden", "Örnsberg", "Axelsberg","Mälarhöjden", "Bredäng","Sätra", "Östermalmstorg", "Karlaplan", "Gärdet","Ropsten", "Skärholmen", "Vårberg", "Farsta strand", "Vårby gård", "Masmo", "Fittja", "Stadion", "Tekniska högskolan", "Universitetet", "Alby", "Hallunda", "Norsborg", "T-Centralen", "Rådhuset", "Fridhemsplan", "Stadshagen", "Västra skogen", "Solna centrum", "Näckrosen", "Hallonbergen", "Rinkeby", "Tensta", "Hjulsta", "Kymlinge", "Kista", "Husby", "Akalla", "Kungsträdgården","Bergshamra", "Danderyds sjukhus", "Mörby", "Huvudsta", "Solna strand", "Sundbybergs centrum", "Duvbo", "Rissne", "Skarpnäck"];

    return states;

});

// setup controller and pass data source
myApp.controller("TypeaheadCtrl", function($scope, States) {

    $scope.selected = undefined;

    $scope.states = States;

});