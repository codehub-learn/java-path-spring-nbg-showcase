<!DOCTYPE html>
<html>
<#import "partial/siteTemplate.ftl" as siteTemplate>
<head>
	<title>Register</title>
    <@siteTemplate.importantLibraries/>
</head>
<body>
<@siteTemplate.navbar currentPage="registerCustomer"/>

<br>

<div class="container">
	<div class="row" align="center">
		<div class="col">
			<h1>Register Here</h1>

			<hr>

            <#include "partial/registerForm.ftl">
		</div>
	</div>
</div>

<@siteTemplate.footer/>
</body>
</html>
