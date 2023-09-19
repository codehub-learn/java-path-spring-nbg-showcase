<!DOCTYPE html>
<html>
<#import "partial/siteTemplate.ftl" as siteTemplate>
<head>
	<title>Home</title>
    <@siteTemplate.importantLibraries/>
</head>
<body>
<@siteTemplate.navbar currentPage="index"/>

<br>

<div class="container">
	<div class="row" align="center">
		<div class="col">
			<h1>Welcome to our E-Shop!</h1>
			<hr>
			<p><b>Joke of the refresh</b>: ${joke}</p>
		</div>
	</div>
</div>

<@siteTemplate.footer/>
</body>
</html>
