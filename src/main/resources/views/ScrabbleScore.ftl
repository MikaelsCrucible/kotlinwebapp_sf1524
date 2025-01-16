<html lang="EN">
<head>
    <link rel="stylesheet" href="/assets/style.css">
    <title>Scrabble Score</title>
</head>
<body>
    <#list scores as score>
        <p>${score.first}: ${score.second}</p>
    </#list>
</body>
</html>