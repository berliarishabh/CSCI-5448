<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1">

		<title>Movie Review | Join Us</title>

		<!-- Loading third party fonts -->
		<link href="http://fonts.googleapis.com/css?family=Roboto:300,400,700|" rel="stylesheet" type="text/css">
		<link href="fonts/font-awesome.min.css" rel="stylesheet" type="text/css">

		<!-- Loading main css file -->
		<link rel="stylesheet" href="style.css">

		<!--[if lt IE 9]>
		<script src="js/ie-support/html5.js"></script>
		<script src="js/ie-support/respond.js"></script>
		<![endif]-->

	</head>


	<body>


		<div id="site-content">
			<header class="site-header">
				<div class="container">
					<a href="index.html" id="branding">
						<img src="images/logo.png" alt="" class="logo">
						<div class="logo-copy">
							<h1 class="site-title">Rated.</h1>
							<small class="site-description">Rate. Review. Repeat</small>
						</div>
					</a> <!-- #branding -->

					<div class="main-navigation">
						<button type="button" class="menu-toggle"><i class="fa fa-bars"></i></button>
						<ul class="menu">
							<li class="menu-item"><a href="index.html">Home</a></li>
							<li class="menu-item"><a href="about.html">About</a></li>
							<li class="menu-item"><a href="review.html">Movie reviews</a></li>
							<li class="menu-item current-menu-item"><a href="joinus.html">Join us</a></li>
							<li class="menu-item"><a href="contact.html">Contact</a></li>
						</ul> <!-- .menu -->

						<form action="#" class="search-form">
							<input type="text" placeholder="Search...">
							<button><i class="fa fa-search"></i></button>
						</form>
					</div> <!-- .main-navigation -->

					<div class="mobile-navigation"></div>
				</div>
			</header>
			<main class="main-content">
				<div class="container">
					<div class="page">
						<div class="breadcrumbs">
							<a href="index.html">Home</a>
							<span>Login/Sign Up</span>
						</div>

						<div class="content">




								<div class="row" id="content">

						<div class="medium-8 columns">
						<div class="left large-10 columns">

						<div class="col" style="margin-right:25%">
						     <div class="well">
						       <form action="/MovieRatingSystem/login" method="POST" class="form-inline">
						         <p>Log in or create an account.</p>
						         <div class="form-group form-space">
						           Name : <input name="name" type="text" placeholder="Username" required class="form-control">
						         </div>
										 <br>
						         <div class="form-group form-space">
						           Password : <input name="password" type="password" placeholder="Password" required class="form-control">
						         </div>
										 <br>
						         <div class="form-group form-more-space">
						           <button id="log-in-btn" type="submit" formaction="/MovieRatingSystem/login" class="btn btn-default">Login</button>
						           <button id="new-account-btn" type="submit" formaction="/MovieRatingSystem/signup" class="btn btn-default">Create Account</button>
						         </div>
						       </form>
						     </div>
						   </div>
						 </div>
						 </div>
							</div>
						</div>
					</div>

				</div> <!-- .container -->
			</main>
			<footer class="site-footer">
				<div class="container">
					<div class="row">




					</div> <!-- .row -->

					<div class="row">
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
						</div>
						<div class="row">
								<br>
								<br>
								<br>
								<br>

							</div>
				</div> <!-- .container -->

			</footer>
		</div>
		<!-- Default snippet for navigation -->



		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/plugins.js"></script>
		<script src="js/app.js"></script>

	</body>

</html>
