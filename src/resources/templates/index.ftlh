<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Code</title>
        <link rel="stylesheet" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
        <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
        <script>hljs.initHighlightingOnLoad();</script>
    </head>

    <body>
        <div>
            <#if Code.isViewsRestriction()>
                <span id="views_restriction">Remaining views: <b>${Code.views}</b></span>
                <br>
            </#if>
            <#if Code.isTimeRestriction()>
                <span id="time_restriction">Remaining seconds: <b>${Code.time}</b></span>
                <br>
            </#if>
            <span id="load_date">${Code.date}</span>
            <pre id="code_snippet"><code>${Code.code}</code></pre>
        </div>
    </body>
</html>