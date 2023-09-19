<#macro importantLibraries>
<#-- Important Libraries -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<#-- Important Libraries -->
</#macro>

<#macro navbar currentPage>
<#-- currentPage is a parameter set in this macro-->
	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="/mvc">ACME E-Shop</a>

		<div class="collapse navbar-collapse" id="navbarWithLogOut">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <#-- Conditional with the ?then built-in function... perhaps not the best as it checks for every
				single one, maybe an extra boolean would help in not checking everything, but leaving it as it is
				for now -->
				<li class="nav-item ${(currentPage=='index')?then('active','')}">
					<a class="nav-link" href="/mvc">Home <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item ${(currentPage=='products')?then('active','')}">
					<a class="nav-link" href="/mvc/products">Our Products</a>
				</li>
				<li class="nav-item ${(currentPage=='findOrder')?then('active','')}">
					<a class="nav-link" href="/mvc/findOrder">Find Your Order</a>
				</li>
				<li class="nav-item ${(currentPage=='about')?then('active','')}">
					<a class="nav-link" href="/mvc/about">About Us</a>
				</li>
			</ul>
            <#-- proper string is "btn-outline-success" but in case the user has clicked on the button, I want the
			String to be "btn-success"-->
			<a href="/mvc/registerCustomer" class="btn btn-${(currentPage=='registerCustomer')?then('','outline-')
            }success my-2 my-sm-0" role="button">Register</a>
		</div>
	</nav>
	<!-- Navbar -->
</#macro>

<#macro footer>
	<!-- Footer -->
	<footer class="bg-dark text-center text-white fixed-bottom">
		<!-- Copyright -->
		<div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
			© 2021 Copyright:
			<a class="text-white" href="/mvc">ACME E-Shop</a>
		</div>
		<!-- Copyright -->
	</footer>
	<!-- Footer -->
</#macro>
