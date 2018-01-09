var stompClient = null;
var id = 0;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
       setConnected(true);
       console.log('Connected: ' + frame);
       stompClient.subscribe('/topic/message', function (message) {
           showMessage(JSON.parse(message.body));
       });
    });
}

function disconnect() {
    id = null;
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    stompClient.send("/app/chat", {}, JSON.stringify({"message" : $("#message").val(), "id" : id++}));
}

function showMessage(message) {
    var date = new Date(parseInt(message.time));
    $("#greetings").append("<tr><td>" + message.message.id + ", " + message.message.message + "----" + date + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
       e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendMessage();
    });
});