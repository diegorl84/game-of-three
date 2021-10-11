var stompClient;
const URL = "http://localhost:8080"
var gameId;
var player;

function createGame(){
  player = $("#name").val()
  if(player == null || player === ""){
    alert("Invalid name")
  }else{
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
        console.log(e)
      },
      success: function(data){
        console.log(data.id)
        gameId = data.id;
        $("#info_gameId").text("Connected to game #: " + gameId);
        connectToSocket(gameId)
        showResponse(data)
        blockControllers(true);
      }

      })
  }
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
        console.log(e)
      },
      success: function(data){
        console.log(data)
        gameId = data.id;
        $("#info_gameId").text("Connected to game #: " + gameId);
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
      },
      success: function(data){
        console.log(data)
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
            console.log(data);
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
         },
         success: function(data){
           console.log(data)
           showResponse(data)
           blockControllers(true);
         }

         })
}

function showResponse(data) {
     $("#listOfNumbers tr").remove();
    $.each(data.historyOfNumber, function(index,value){
      $("#listOfNumbers").append("<tr><td>" + value + "</td></tr>");
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

