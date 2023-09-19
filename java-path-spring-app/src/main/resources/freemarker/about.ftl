<!DOCTYPE html>
<html>
<#import "partial/siteTemplate.ftl" as siteTemplate>
<#import "/spring.ftl" as spring />
<head>
	<title>About us</title>
    <@siteTemplate.importantLibraries/>
</head>
<body>
<@siteTemplate.navbar currentPage="about"/>

<br>

<div class="container">
	<div align="center">
		<div class="row">
			<div class="col">
				<h1><@spring.message "about.header"/></h1>
				<h4><@spring.message "about.names"/></h4>
				<p><@spring.message "about.description"/></p>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col">
				<p id="timesVisitedParagraph"></p>
			</div>
		</div>
	</div>
</div>

<@siteTemplate.footer/>

<#-- Simple script which reads a "counter" cookie and replaces a paragraph with a text notifying the user about
the cookie (more specifically: the times he has visited the page) -->
<script>
	const cookieValue = getCookie("counter");
	document.getElementById("timesVisitedParagraph").textContent = "You have visited this page " + cookieValue + " " +
			"times!";

	// Function copied from the internet, basically access the wanted cookie
	function getCookie(name) {
		let cookie = {};
		document.cookie.split(';').forEach(function (el) {
			let [k, v] = el.split('=');
			cookie[k.trim()] = v;
		})
		return cookie[name];
	}
</script>
</body>
</html>
