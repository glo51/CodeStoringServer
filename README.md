# CodeStoringServer

It's a Spring server that allows to store code snippets in H2 database.
<br><br>
You can operate with your code in two ways:
<br>
By browser:
  - /code/new &emsp; opens form to upload the code and returns it's id in an alert
  - /code/latest &emsp; shows latest 10 codes without restrictions
  - /code/{id} &emsp; opens site with specific code

By REST API:
  - POST /api/code/new &emsp; requires JSON with fields: String code, int time, int views
  - GET /api/code/latest&emsp; returns JSON with 10 recently uploaded codes
  - GET /api/code/{id} &emsp; returns JSON with specified code

Code model has two restriction fields:
  - Long time - specified in seconds, after this time the code will not be available anymore
  - Integer views - it specifies how many times the code can be seen
