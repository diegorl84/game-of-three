var stompClient;
const URL = "http://localhost:8080"
var gameId;
var player;


function createGame(){
  player = $("#name").val()

  $.ajax({
    url: "/game-of-three/game",
    type: 'POST',
    dataType: "json",
    contentType:"application/json",
    data:  JSON.stringify({
       "name": player,
       "number":  new Number($("#number").val())
     }),
    error: function(e){
      alert(e.responseJSON.message, 'danger')
    },
    success: function(data){
      gameId = data.id;
      $("#infoplaceholder").append("<div class='alert alert-primary' role='alert'>Connected to game id: <strong>"+gameId+"</strong></div>");
      connectToSocket(gameId)
      showResponse(data)
      blockControllers(true);
    }
    })
}

function joinGame(){
  player = $("#name").val()
  let gameIdToConnect  = $("#gameIdToConnect").val()
  if(player == null || player === ""){
    alert("Invalid name")
  } else {
    $.ajax({
      url: "/game-of-three/join-game",
      type: 'POST',
      dataType: "json",
      contentType:"application/json",
      data: JSON.stringify({
         "name": player,
         "gameId": gameIdToConnect
       }),
      error: function(e){
        console.log
        alert(e.responseJSON.message, 'danger')
      },
      success: function(data){
        gameId = data.id;
         $("#infoplaceholder").append("<div class='alert alert-primary' role='alert'>Connected to game id: <strong>"+gameId+"</strong>. Playing against: "+ data.player1.name+"</div>");
        connectToSocket(gameId);
        showResponse(data)
      }

      })
  }
}

function joinRandomGame(){
  player = $("#name").val()
  let gameIdToConnect  = $("#gameIdToConnect").val()
  if(player == null || player === ""){
    alert("Invalid name")
  } else {
    $.ajax({
      url: "/game-of-three/join-random-game",
      type: 'POST',
      dataType: "json",
      contentType:"application/json",
      data: JSON.stringify({
         "name": player,
         "gameId": gameIdToConnect
       }),
      error: function(e){
        console.log(e)
        alert(e.responseJSON.message, 'danger')
      },
      success: function(data){

        gameId = data.id;
        $("#info_gameId").text("Connected to game #: " + gameId);
        connectToSocket(gameId);
        showResponse(data)
      }

      })
  }
}

function connectToSocket(id) {

    console.log("Creating game...")
    var socket = new SockJS('/game-of-three');

    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        blockMenu();
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/game/'+id, function (response) {
            let data = JSON.parse(response.body)

            showResponse(data);
        });
    });
}

function sendNumber(operation) {
   $.ajax({
     url: "/game-of-three/move",
     type: 'POST',
     dataType: "json",
     contentType:"application/json",
     data: JSON.stringify({
         "gameId": gameId,
         "operation": operation,
         "player": {
             "name": player
         }
     }),
     error: function(e){
       console.log(e)
       alert(e.responseJSON.message, 'danger')
     },
     success: function(data){
       showResponse(data)
       blockControllers(true);
     }

     })
}

function showResponse(data) {
     $("#listOfNumbers tr").remove();
    $.each(data.historyOfMove, function(index,value){
      $("#listOfNumbers").append("<tr><td>" + value.resultNumber + "</td></tr>");
    })
    if(data.status === 'FINISHED'){
      $("#info_game_status").text("Game over. Winner is: " + data.winner.name)
      blockControllers(true);
    }else{
      blockControllers(false);
    }


}

function blockMenu(){
  $( ".menu").prop('disabled', true);
}

function blockControllers(status){
  $( ".control").prop('disabled', status);
}

function alert(message, type) {
  $("#alertPlaceholder .alert").remove()
  var wrapper = document.createElement('div')
  wrapper.innerHTML = '<div class="alert alert-' + type + ' alert-dismissible" role="alert">' + message + '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>'
  $("#alertPlaceholder").append(wrapper)
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#create" ).click(function() { createGame(); });
    $( "#join" ).click(function() { joinGame(); });
    $( "#join-random" ).click(function() { joinRandomGame(); });
    $("#minus").click(function() { sendNumber(-1); });
    $("#zero").click(function() { sendNumber(0); });
    $("#plus").click(function() { sendNumber(1); });

});

