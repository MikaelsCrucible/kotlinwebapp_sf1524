<html lang="EN">
<head>
    <link rel="stylesheet" href="/assets/style.css">
    <title>Posts</title>
</head>
<body>
<#list posts as post>
    <h2>${post.title}</h2>
    <p>${post.body}</p>
    <a href="/deletepost?id=${post.id}">Delete this post!</a>
    <br>
</#list>
<h1>Post something here</h1>
<form action="/addpost" method="post">
    <label for="title">Title:</label>
    <input type="text" id="titlefield" name="title">
    <br>
    <label for="body">Content:</label>
    <input type="text" id="bodyfield" name="body">
    <br>
    <input type="submit" value="Submit Post">
</form>
</body>
</html>