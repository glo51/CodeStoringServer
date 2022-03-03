<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Create</title>
        <script>
            function send() {
                let object = {
                    "code": document.getElementById("code_snippet").value,
                    "time": document.getElementById("time_restriction").value,
                    "views": document.getElementById("views_restriction").value
                };

                let json = JSON.stringify(object);

                let xhr = new XMLHttpRequest();
                xhr.open("POST", '/api/code/new', false)
                xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                xhr.send(json);

                if (xhr.status === 200) {
                    alert("Success, this code's id is:\n" + xhr.responseText.toString().slice(7, -2));
                }
            }
        </script>
        <style>
            body {
                text-align: center;
            }
        </style>
    </head>

    <body>
        <form>
            <label for="code_snippet"></label>
            <textarea id="code_snippet" maxlength="2048" placeholder="Your code:" cols="100" rows="40" required></textarea>
            <br><br>
            <label for="time_restriction">Seconds to delete: </label>
            <input id="time_restriction" type="number" value="0" placeholder="0 or less to ignore" required/>
            <br>
            <label for="views_restriction">Views limit: </label>
            <input id="views_restriction" type="number" value="0" placeholder="0 or less to ignore" required/>
            <br><br>
            <button id="send_snippet" type="submit" onclick="send()">Submit</button>
        </form>
    </body>
</html>