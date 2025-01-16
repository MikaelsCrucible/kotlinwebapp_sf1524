<html lang="EN">
    <head>
        <link rel="stylesheet" href="/assets/style.css">
        <title>Kotlin WebApp</title>
    </head>
    <body>
        <h1>Hello ${name}!</h1>
        <p>This is a very simple Kotlin webapp.</p>
        <p>Input 5 words and get their Scrabble scores</p>
        <form action="/submit" method="post">
            <label for="word1field">Word1:</label>
            <input type="text" id="word1field" name="word1">
            <br>
            <label for="word2field">Word2:</label>
            <input type="text" id="word2field" name="word2">
            <br>
            <label for="word3field">Word3:</label>
            <input type="text" id="word3field" name="word3">
            <br>
            <label for="word4field">Word4:</label>
            <input type="text" id="word4field" name="word4">
            <br>
            <label for="word5field">Word5:</label>
            <input type="text" id="word5field" name="word5">
            <br>
            <input type="submit" value="Calculate Score">
        </form>
    </body>
</html>