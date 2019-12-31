<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html lang="en">
<head>
    <title>Document</title>
    <script src="​http://cdn-hangzhou.goeasy.io/goeasy.js​"></script>
    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-2.2.1.min.js"></script>
</head>
<body>
<script>
    var goEasy = new GoEasy({
        appkey: "BS-572dda274c5347509e6f27ac1890d8e4"
    });
    goEasy.subscribe({
        channel: "my_channel",
        onMessage: function (message) {
            alert("Channel:" + message.channel + " content:" + message.content);
        }
    });
</script>
</body>
</html>